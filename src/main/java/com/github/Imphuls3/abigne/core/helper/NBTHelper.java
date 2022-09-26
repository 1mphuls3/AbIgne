package com.github.Imphuls3.abigne.core.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

public class NBTHelper {
    public static void saveBlockPos(CompoundTag nbt, BlockPos pos) {
        nbt.putInt("x", pos.getX());
        nbt.putInt("y", pos.getY());
        nbt.putInt("z", pos.getZ());
    }

    public static BlockPos loadBlockPos(CompoundTag nbt) {
        return new BlockPos(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z"));
    }
}
