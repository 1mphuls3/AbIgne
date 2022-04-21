package com.Imphuls3.abigne.core.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.ArrayList;
import java.util.function.Predicate;

public class BlockHelper {
    public static BlockState getStateExistingProperties(BlockState stateOld, BlockState stateNew) {
        BlockState state = stateNew;
        for (Property<?> property : stateOld.getProperties()) {
            if (stateNew.hasProperty(property)) {
                state = newStateWithOldProperty(stateOld, state, property);
            }
        }
        return state;
    }

    public static BlockState setStateExistingProperties(Level level, BlockPos pos, BlockState newState, int flags) {
        BlockState stateOld = level.getBlockState(pos);
        BlockState state = getStateExistingProperties(stateOld, newState);
        level.sendBlockUpdated(pos, stateOld, state, flags);
        level.setBlock(pos, state, flags);
        return state;
    }

    public static <T extends Comparable<T>> BlockState newStateWithOldProperty(BlockState stateOld, BlockState stateNew, Property<T> property) {
        return stateNew.setValue(property, stateOld.getValue(property));
    }

    public static void updateState(BlockState state, Level level, BlockPos pos) {
        level.sendBlockUpdated(pos, state, state, 2);
        level.blockEntityChanged(pos);
    }

    public static void updateAndNotifyState(Level level, BlockPos pos) {
        updateAndNotifyState(level.getBlockState(pos), level, pos);
    }

    public static void updateAndNotifyState(BlockState state, Level level, BlockPos pos) {
        updateState(state, level, pos);
        state.updateNeighbourShapes(level, pos, 2);
        level.updateNeighbourForOutputSignal(pos, state.getBlock());
    }
}
