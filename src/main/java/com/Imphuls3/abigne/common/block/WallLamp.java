package com.Imphuls3.abigne.common.block;

import com.Imphuls3.abigne.common.block.entity.WallLampEntity;
import com.Imphuls3.abigne.common.block.utils.ModBlock;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class WallLamp extends ModBlock<WallLampEntity> {
    public static final VoxelShape SHAPE = makeShape();

    public WallLamp(Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityInit.WALL);
    }

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
        return SHAPE;
    }

    public static VoxelShape makeShape(){
        VoxelShape shape = Stream.of(
                Block.box(5, 7.5, 10, 11, 8.5, 16),
                Block.box(7.5, 6.5, 11, 8.5, 7.5, 12),
                Block.box(7.5, 5.5, 12, 8.5, 6.5, 14),
                Block.box(7.5, 4.5, 14, 8.5, 5.5, 16)
        ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
        return shape;
    }
}
