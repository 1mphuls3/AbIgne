package com.Imphuls3.abigne.common.block.custom;

import com.Imphuls3.abigne.core.registry.common.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;

import java.util.Random;

@SuppressWarnings("deprecation")
public class BuddingPyroliteBlock extends AmethystBlock {
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingPyroliteBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }


    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = BlockRegistry.SMALL_PYROLITE_BUD.get().defaultBlockState().getBlock();
            } else if (blockstate.is(BlockRegistry.SMALL_PYROLITE_BUD.get().defaultBlockState().getBlock()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BlockRegistry.MEDIUM_PYROLITE_BUD.get().defaultBlockState().getBlock();
            } else if (blockstate.is(BlockRegistry.MEDIUM_PYROLITE_BUD.get().defaultBlockState().getBlock()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BlockRegistry.LARGE_PYROLITE_BUD.get().defaultBlockState().getBlock();
            } else if (blockstate.is(BlockRegistry.LARGE_PYROLITE_BUD.get().defaultBlockState().getBlock()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BlockRegistry.PYROLITE_CLUSTER.get().defaultBlockState().getBlock();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED,
                        Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                level.setBlockAndUpdate(blockpos, blockstate1);
            }

        }
    }

    public static boolean canClusterGrowAtState(BlockState pState) {
        return pState.isAir() || pState.is(Blocks.WATER) && pState.getFluidState().getAmount() == 8;
    }
}
