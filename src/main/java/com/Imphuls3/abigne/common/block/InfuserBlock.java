package com.Imphuls3.abigne.common.block;

import com.Imphuls3.abigne.core.systems.block.AbIgneBlock;
import com.Imphuls3.abigne.core.registry.common.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class InfuserBlock extends AbIgneBlock {
    public static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 0.25D, 13.0D);

    public InfuserBlock(Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityRegistry.INFUSER);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
