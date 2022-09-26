package com.github.Imphuls3.abigne.common.block;

import com.github.Imphuls3.abigne.common.blockentity.CrucibleBlockEntity;
import com.github.Imphuls3.abigne.core.block.AbIgneBlock;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import com.github.Imphuls3.abigne.core.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class CrucibleBlock extends AbIgneBlock<CrucibleBlockEntity> {
    /*public static final VoxelShape SHAPE = makeShape();
    public static final VoxelShape RENDER_SHAPE = makeRenderShape();*/

    public static final BooleanProperty HASPIPEN = BooleanProperty.create("pipe_north");
    public static final BooleanProperty HASPIPEE = BooleanProperty.create("pipe_east");
    public static final BooleanProperty HASPIPES = BooleanProperty.create("pipe_south");
    public static final BooleanProperty HASPIPEW = BooleanProperty.create("pipe_west");

    public CrucibleBlock(Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityRegistry.CRUCIBLE);
        this.registerDefaultState(this.getStateDefinition().any().setValue(HASPIPEN, false).setValue(HASPIPEE, false).setValue(HASPIPES, false).setValue(HASPIPEW, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HASPIPEN, HASPIPEE, HASPIPES, HASPIPEW);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(HASPIPEN, false).setValue(HASPIPEE, false).setValue(HASPIPES, false).setValue(HASPIPEW, false);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit)
    {
        if (player.getItemInHand(handIn).getItem().equals(ItemRegistry.PYROLITE_SHARD_ACTIVE.get()))
        {
            if(player.getDirection() == Direction.NORTH){
                level.setBlockAndUpdate(pos,state.setValue(HASPIPEN, !state.getValue(HASPIPEN)));
            }
            if(player.getDirection() == Direction.EAST){
                level.setBlockAndUpdate(pos,state.setValue(HASPIPEE, !state.getValue(HASPIPEE)));
            }
            if(player.getDirection() == Direction.SOUTH){
                level.setBlockAndUpdate(pos,state.setValue(HASPIPES, !state.getValue(HASPIPES)));
            }
            if(player.getDirection() == Direction.WEST){
                level.setBlockAndUpdate(pos,state.setValue(HASPIPEW, !state.getValue(HASPIPEW)));
            }
            player.swing(handIn);
            return InteractionResult.SUCCESS;
        }
        return super.use(state, level, pos, player, handIn, hit);
    }

   /* @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        if(player.getDirection() == Direction.NORTH){
            level.setBlockAndUpdate(pos,state.setValue(HASPIPEN, !state.getValue(HASPIPEN)));
        }
        if(player.getDirection() == Direction.EAST){
            level.setBlockAndUpdate(pos,state.setValue(HASPIPEE, !state.getValue(HASPIPEE)));
        }
        if(player.getDirection() == Direction.SOUTH){
            level.setBlockAndUpdate(pos,state.setValue(HASPIPES, !state.getValue(HASPIPES)));
        }
        if(player.getDirection() == Direction.WEST){
            level.setBlockAndUpdate(pos,state.setValue(HASPIPEW, !state.getValue(HASPIPEW)));
        }
    }*/
    /* @Override
    public void onNeighborChange(BlockState state, LevelReader reader, BlockPos pos, BlockPos neighbor) {
        if(reader.getBlockState(neighbor).getBlock().equals(BlockInit.CHARRED_LOG)){

        }
    }*/
/*
    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter getter, BlockPos pos) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return RENDER_SHAPE;
    }
    public static VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.25, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.25, 0.1875, 0.8125, 0.625, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.625, 0, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 1, 0, 0.1875, 1.0625, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 1, 0.8125, 0.1875, 1.0625, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 1, 0, 1, 1.0625, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 1, 0.8125, 1, 1.0625, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0.3125, 1, 0.375, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0, 0.6875, 0.375, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.8125, 0.6875, 0.375, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.3125, 0.1875, 0.375, 0.6875), BooleanOp.OR);

        return shape;
    }
    public static VoxelShape makeRenderShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.0625, 0.9375, 0.25, 0.9375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 0.25, 0.1875, 0.8125, 0.625, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.625, 0, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.125, 0.5625, -0.125, 0.1875, 1.0625, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.125, 0.5625, 0.8125, 0.1875, 1.0625, 1.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0.5625, -0.125, 1.125, 1.0625, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0.5625, 0.8125, 1.125, 1.0625, 1.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 0, 0.3125, 1, 0.375, 0.6875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0, 0.6875, 0.375, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.8125, 0.6875, 0.375, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.3125, 0.1875, 0.375, 0.6875), BooleanOp.OR);

        return shape;
    }*/
}
