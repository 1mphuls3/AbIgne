package com.github.Imphuls3.abigne.common.block;

import com.github.Imphuls3.abigne.common.blockentity.CopperPotBlockEntity;
import com.github.Imphuls3.abigne.core.block.AbIgneBlock;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;
import java.util.stream.Stream;

public class CopperPotBlock extends AbIgneBlock<CopperPotBlockEntity> {
    private final VoxelShape SHAPE = Stream.of(
            Block.box(1, 4, 1, 3, 14, 15),
            Block.box(0, 0, 0, 4, 10, 1),
            Block.box(12, 0, 0, 16, 10, 1),
            Block.box(4, 5, 0, 12, 9, 1),
            Block.box(0, 0, 12, 1, 10, 15),
            Block.box(0, 5, 4, 1, 9, 12),
            Block.box(15, 5, 4, 16, 9, 12),
            Block.box(0, 0, 1, 1, 10, 4),
            Block.box(15, 0, 1, 16, 10, 4),
            Block.box(15, 0, 12, 16, 10, 15),
            Block.box(12, 0, 15, 16, 10, 16),
            Block.box(4, 5, 15, 12, 9, 16),
            Block.box(0, 0, 15, 4, 10, 16),
            Block.box(2, 3, 2, 14, 5, 14),
            Block.box(3, 4, 1, 13, 14, 3),
            Block.box(3, 14, 3, 13, 15, 4),
            Block.box(3, 14, 12, 13, 15, 13),
            Block.box(3, 14, 4, 4, 15, 12),
            Block.box(12, 14, 4, 13, 15, 12),
            Block.box(12, 15, 2, 14, 16, 14),
            Block.box(4, 15, 2, 12, 16, 4),
            Block.box(4, 15, 12, 12, 16, 14),
            Block.box(2, 15, 2, 4, 16, 14),
            Block.box(3, 4, 13, 13, 14, 15),
            Block.box(13, 4, 1, 15, 14, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CopperPotBlock(Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityRegistry.COPPER_POT);
    }

    /*@Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }*/
}
