package com.Imphuls3.abigne.core.helper;

import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataHelper {
    public static Vec3 fromBlockPos(BlockPos pos) {
        return new Vec3(pos.getX(), pos.getY(), pos.getZ());
    }

    public static Vector3f fromBlockPosVec3f(BlockPos pos) {
        return new Vector3f(pos.getX(), pos.getY(), pos.getZ());
    }

    public static Vec3 randPos(BlockPos pos, Random rand, double min, double max) {
        double x = Mth.nextDouble(rand, min, max) + pos.getX();
        double y = Mth.nextDouble(rand, min, max) + pos.getY();
        double z = Mth.nextDouble(rand, min, max) + pos.getZ();
        return new Vec3(x, y, z);
    }

    public static <T> Collection<T> getAll(Collection<? extends T> src, T... items) {
        return List.copyOf(getAll(src, t -> Arrays.stream(items).anyMatch(tAgain -> tAgain.getClass().isInstance(t))));
    }

    public static <T> Collection<T> getAll(Collection<T> src, Predicate<T> pred) {
        return src.stream().filter(pred).collect(Collectors.toList());
    }
}
