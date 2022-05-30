package com.Imphuls3.abigne.common.block.custom;

import com.Imphuls3.abigne.core.init.BlockInit;
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
public class BuddingIgnisBlock extends AmethystBlock {
    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingIgnisBlock(BlockBehaviour.Properties p_152726_) {
        super(p_152726_);
    }


    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.DESTROY;
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        if (pRandom.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[pRandom.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pPos.relative(direction);
            BlockState blockstate = pLevel.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = BlockInit.SMALL_PYROLITE_BUD.get().defaultBlockState().getBlock();
            } else if (blockstate.is(BlockInit.SMALL_PYROLITE_BUD.get().defaultBlockState().getBlock()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BlockInit.MEDIUM_PYROLITE_BUD.get().defaultBlockState().getBlock();
            } else if (blockstate.is(BlockInit.MEDIUM_PYROLITE_BUD.get().defaultBlockState().getBlock()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BlockInit.LARGE_PYROLITE_BUD.get().defaultBlockState().getBlock();
            } else if (blockstate.is(BlockInit.LARGE_PYROLITE_BUD.get().defaultBlockState().getBlock()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = BlockInit.PYROLITE_CLUSTER.get().defaultBlockState().getBlock();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState().setValue(AmethystClusterBlock.FACING, direction).setValue(AmethystClusterBlock.WATERLOGGED,
                        Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                pLevel.setBlockAndUpdate(blockpos, blockstate1);
            }

        }
    }

    public static boolean canClusterGrowAtState(BlockState pState) {
        return pState.isAir() || pState.is(Blocks.WATER) && pState.getFluidState().getAmount() == 8;
    }
}
