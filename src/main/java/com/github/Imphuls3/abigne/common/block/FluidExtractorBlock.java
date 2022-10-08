package com.github.Imphuls3.abigne.common.block;

import com.github.Imphuls3.abigne.core.block.AbIgneBlock;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class FluidExtractorBlock extends AbIgneBlock {
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(4, 14, 4, 12, 16, 12),
            Block.box(5, 16, 5, 11, 23, 11),
            Block.box(5, 20, 5, 11, 20, 11),
            Block.box(7, 23, 7, 9, 27, 9),
            Block.box(6, 27, 6, 10, 28, 10),
            Block.box(2, 0, 2, 4, 12, 4),
            Block.box(12, 0, 2, 14, 12, 4),
            Block.box(0, 12, 0, 16, 14, 16),
            Block.box(1, 8, 1, 15, 10, 2),
            Block.box(1, 8, 14, 15, 10, 15),
            Block.box(14, 8, 2, 15, 10, 14),
            Block.box(1, 8, 2, 2, 10, 14),
            Block.box(2, 0, 12, 4, 12, 14),
            Block.box(12, 0, 12, 14, 12, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public FluidExtractorBlock(Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityRegistry.FLUID_EXTRACTOR);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
