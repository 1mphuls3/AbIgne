
package com.Imphuls3.abigne.common.block;

import com.Imphuls3.abigne.common.blockentity.CenterPedestalBlockEntity;
import com.Imphuls3.abigne.core.systems.block.AbIgneBlock;
import com.Imphuls3.abigne.core.registry.common.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class CenterPedestalBlock extends AbIgneBlock<CenterPedestalBlockEntity> {
    public static final VoxelShape SHAPE = Stream.of(
            Block.box(4, 0, 4, 12, 4, 12),
            Block.box(5, 4, 5, 11, 10, 11),
            Block.box(3, 10, 3, 13, 13, 13)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CenterPedestalBlock(Properties properties)
    {
        super(properties);
        setBlockEntity(BlockEntityRegistry.RITUAL_PEDESTAL);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}