package com.github.Imphuls3.abigne.common.block;

import com.github.Imphuls3.abigne.common.blockentity.TankBlockEntity;
import com.github.Imphuls3.abigne.core.block.AbIgneBlock;
import com.github.Imphuls3.abigne.core.blockentity.ITankProvider;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class TankBlock extends AbIgneBlock<TankBlockEntity> {

    public static BooleanProperty UP = BooleanProperty.create("up"), DOWN = BooleanProperty.create("down");

    public TankBlock(Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityRegistry.TANK);
        registerDefaultState(this.getStateDefinition().any().setValue(UP, false).setValue(DOWN, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        return updateShape(level, blockpos, super.getStateForPlacement(context));
    }

    @Override
    public BlockState updateShape(BlockState state, Direction dir, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return updateShape(level, pos, state);
    }

    protected BlockState updateShape(BlockGetter level, BlockPos pos, BlockState state) {
        BlockState blockstate = level.getBlockState(pos.relative(Direction.UP));
        BlockState blockstate1 = level.getBlockState(pos.relative(Direction.DOWN));
        boolean connectUp = blockstate.getBlock() == this,
                connectDown = blockstate1.getBlock() == this;
        return state
                .setValue(UP, connectUp).setValue(DOWN, connectDown);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP).add(DOWN);
        super.createBlockStateDefinition(builder);
    }
}
