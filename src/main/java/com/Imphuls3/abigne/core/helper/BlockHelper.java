package com.Imphuls3.abigne.core.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BlockHelper {

    /**
     * Returns block below the given pos
     * */
    public static Block getBlockBelow(Level level, BlockPos pos){
        return level.getBlockState(pos.below()).getBlock();
    }

    /**
     * Returns a new Vec3 from a Block Pos
     * */
    public static Vec3 v3fromBlockPos(BlockPos pos) {
        return new Vec3(pos.getX(), pos.getY(), pos.getZ());
    }


    /**
    * Returns a new BlockPos from a Vec3
    * */
    public static BlockPos blockPosfromVec3(Vec3 pos) {
        return new BlockPos(pos.x(), pos.y(), pos.z());
    }

    /**
     * Returns a list of block entities within a certain AABB
     * */
    public static <T> List<T> getBlockEntitiesWithinAABB(Class<T> type, Level level, AABB bounds) {
        List<T> blockEntityList = new ArrayList<>();
        for (int i = (int)Math.floor(bounds.minX); i < (int)Math.ceil(bounds.maxX) + 16; i += 16) {
            for (int k = (int)Math.floor(bounds.minZ); k < (int)Math.ceil(bounds.maxZ) + 16; k += 16) {
                ChunkAccess chunk = level.getChunk(new BlockPos(i, 0, k));
                Set<BlockPos> blockEntities = chunk.getBlockEntitiesPos();
                for (BlockPos pos : blockEntities) if (bounds.contains(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5)) {
                    BlockEntity t = level.getBlockEntity(pos);
                    if (type.isInstance(t)) blockEntityList.add((T)t);
                }
            }
        }
        return blockEntityList;
    }

    /**
     * returns a default AABB
     * mainly for use in rituals
     * */
    public static AABB defaultBounds(BlockPos pos) {
        return new AABB(pos.getX() - 10, pos.getY() - 5, pos.getZ() - 10, pos.getX() + 10, pos.getY() + 10, pos.getZ() + 10);
    }

    public static void updateState(BlockState state, Level level, BlockPos pos) {
        level.sendBlockUpdated(pos, state, state, 2);
        level.blockEntityChanged(pos);
    }

    public static void updateStateAndNeighbor(BlockState state, Level level, BlockPos pos) {
        updateState(state, level, pos);
        state.updateNeighbourShapes(level, pos, 2);
        level.updateNeighbourForOutputSignal(pos, state.getBlock());
    }

    public static void updateStateAndNeighbor(Level level, BlockPos pos) {
        updateStateAndNeighbor(level.getBlockState(pos), level, pos);
    }
}
