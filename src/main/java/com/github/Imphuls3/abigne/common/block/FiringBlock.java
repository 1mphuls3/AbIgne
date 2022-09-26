package com.github.Imphuls3.abigne.common.block;

import com.github.Imphuls3.abigne.common.blockentity.FiringBlockEntity;
import com.github.Imphuls3.abigne.core.block.AbIgneBlock;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class FiringBlock extends AbIgneBlock<FiringBlockEntity> {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public FiringBlock(Properties properties) {
        super(properties);
        setBlockEntity(BlockEntityRegistry.FIRING_BLOCK);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
        return blockstate;
    }
}
