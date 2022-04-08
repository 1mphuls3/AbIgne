package com.Imphuls3.abigne.common.block;

import com.Imphuls3.abigne.common.block.entity.CrucibleBlockEntity;
import com.Imphuls3.abigne.common.block.utils.WaterLoggedBlock;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CrucibleBlock extends WaterLoggedBlock<CrucibleBlockEntity>
{
    public static final VoxelShape SHAPE = makeShape();
    public static final VoxelShape RENDER_SHAPE = makeRenderShape();
    public CrucibleBlock(Properties properties)
    {
        super(properties);
        setTile(BlockEntityInit.CRUCIBLE);
    }

    @Override
    public VoxelShape getInteractionShape(BlockState stateIn, BlockGetter getterIn, BlockPos posIn) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState stateIn, BlockGetter getterIn, BlockPos posIn, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getShape(BlockState stateIn, BlockGetter getterIn, BlockPos posIn, CollisionContext context) {
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
    }
}
