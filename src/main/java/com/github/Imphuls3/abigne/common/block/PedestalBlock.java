package com.github.Imphuls3.abigne.common.block;

import com.github.Imphuls3.abigne.common.blockentity.PedestalBlockEntity;
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

public class PedestalBlock extends AbIgneBlock<PedestalBlockEntity> {
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(4, 0, 4, 12, 2, 12),
            Block.box(4, 10, 4, 12, 12, 12),
            Block.box(5, 2, 5, 11, 10, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public PedestalBlock(Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityRegistry.PEDESTAL);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
