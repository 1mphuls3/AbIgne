package com.github.Imphuls3.abigne.common.block;

import com.github.Imphuls3.abigne.common.blockentity.PipeBlockEntity;
import com.github.Imphuls3.abigne.core.block.AbIgneBlock;
import com.github.Imphuls3.abigne.core.blockentity.ITankProvider;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import com.github.Imphuls3.abigne.core.registry.ItemRegistry;
import com.google.common.base.Predicates;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.model.IDynamicBakedModel;

import java.util.stream.Stream;

public class PipeBlock extends AbIgneBlock<PipeBlockEntity> {
    static final VoxelShape
            UP = Shapes.box(0.375, 0.625, 0.375, 0.625, 1, 0.625),
            DOWN = Shapes.box(0.375, 0, 0.375, 0.625, 0.375, 0.625),
            EAST = Shapes.box(0.625, 0.375, 0.375, 1, 0.625, 0.625),
            WEST = Shapes.box(0, 0.375, 0.375, 0.375, 0.625, 0.625),
            NORTH = Shapes.box(0.375, 0.375, 0, 0.625, 0.625, 0.375),
            SOUTH = Shapes.box(0.375, 0.375, 0.625, 0.625, 0.625, 1),
            CENTER = Shapes.box(0.375, 0.375, 0.375, 0.625, 0.625, 0.625);

    static final VoxelShape[] SHAPES = new VoxelShape[36];
    static {
        VoxelShape[] FACES = new VoxelShape[]{ DOWN, UP, NORTH, SOUTH, WEST, EAST };
        for (Direction in : Direction.values()) {
            for (Direction out : Direction.values()) {
                SHAPES[in.ordinal() * 6 + out.ordinal()] = Shapes.or(FACES[in.ordinal()], FACES[out.ordinal()], CENTER);
            }
        }
    }

    public static final DirectionProperty
            IN = DirectionProperty.create("in", Predicates.alwaysTrue()),
            OUT = DirectionProperty.create("out", Predicates.alwaysTrue());
    public static final BooleanProperty
            IN_ATTACHED = BooleanProperty.create("attachin"),
            OUT_ATTACHED = BooleanProperty.create("attachout");

    public PipeBlock(BlockBehaviour.Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityRegistry.FLUID_PIPE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getInteractionShape(state, level, pos);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getInteractionShape(state, level, pos);
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof PipeBlockEntity pipe) {
            if(pipe.facade != null) {
                return Stream.of(pipe.facade.getBlock().getShape(pipe.facade.getBlock().defaultBlockState(), level, pos, CollisionContext.empty()),
                                SHAPES[state.getValue(IN).ordinal() * 6 + state.getValue(OUT).ordinal()]).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
            }
        }
        return SHAPES[state.getValue(IN).ordinal() * 6 + state.getValue(OUT).ordinal()];
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(IN, context.getClickedFace().getOpposite())
                .setValue(OUT, context.getClickedFace())
                .setValue(IN_ATTACHED, context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace().getOpposite()))
                                .getBlock() == this)
                .setValue(OUT_ATTACHED, context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace()))
                                .getBlock() == this &&
                                context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace()))
                                        .getValue(IN) == context.getClickedFace());
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos pos, BlockPos facingPos) {
        if (facing == state.getValue(OUT))
            state = state.setValue(OUT_ATTACHED, facingState.getBlock() == this && facingState.getValue(IN) == facing.getOpposite());
        if (facing == state.getValue(IN))
            state = state.setValue(IN_ATTACHED, facingState.getBlock() == this && facingState.getValue(OUT) == facing.getOpposite());
        if (facingState.getBlock() == this && facingPos.relative(facingState.getValue(IN)).equals(pos)
                && facing != state.getValue(IN)) {
            state = state.setValue(OUT, facing).setValue(OUT_ATTACHED, true);
        }
        if(!state.getValue(OUT_ATTACHED) && facingState.getBlock() instanceof ITankProvider tank && tank.isOutput(facing.getOpposite())) {
            state = state.setValue(OUT, facing);
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IN).add(OUT).add(IN_ATTACHED).add(OUT_ATTACHED);
    }
}
