package com.github.Imphuls3.abigne.core.helper;

import com.github.Imphuls3.abigne.core.Easing;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class VecHelper {
    public static final Vec3 MIDDLE = new Vec3(.5, 0, .5);
    public static final Vec3 CENTER = new Vec3(.5, .5, .5);

    public static Vec3 vec3FromBlockPos(BlockPos pos) {
        return new Vec3(pos.getX(), pos.getY(), pos.getZ());
    }

    public static BlockPos blockPosfromVec3(Vec3 pos) {
        return new BlockPos(pos.x(), pos.y(), pos.z());
    }

    public static Vec3 radialOffset(Vec3 pos, float distance, float current, float total) {
        double angle = current / total * (Math.PI * 2);
        double dx2 = (distance * Math.cos(angle));
        double dz2 = (distance * Math.sin(angle));

        Vec3 vector = new Vec3(dx2, 0, dz2);
        double x = vector.x * distance;
        double z = vector.z * distance;
        return pos.add(new Vec3(x, 0, z));
    }

    public static Vec3 easedLerp(Easing easing, float delta, Vec3 from, Vec3 to) {
        delta = Mth.clamp(delta, 0, 1);
        float ease = easing.ease(delta, 0, 1, 1);
        return new Vec3(Mth.lerp(ease, from.x, to.x), Mth.lerp(ease, from.y, to.y), Mth.lerp(ease, from.z, to.z));
    }

    public static Vec3 easedLerpX(Easing easing, float delta, Vec3 from, Vec3 to) {
        delta = Mth.clamp(delta, 0, 1);
        float ease = easing.ease(delta, 0, 1, 1);
        return new Vec3(Mth.lerp(ease, from.x, to.x), Mth.lerp(delta, from.y, to.y), Mth.lerp(delta, from.z, to.z));
    }

    public static Vec3 easedLerpY(Easing easing, float delta, Vec3 from, Vec3 to) {
        delta = Mth.clamp(delta, 0, 1);
        float ease = easing.ease(delta, 0, 1, 1);
        return new Vec3(Mth.lerp(delta, from.x, to.x), Mth.lerp(ease, from.y, to.y), Mth.lerp(delta, from.z, to.z));
    }

    public static Vec3 easedLerpZ(Easing easing, float delta, Vec3 from, Vec3 to) {
        delta = Mth.clamp(delta, 0, 1);
        float ease = easing.ease(delta, 0, 1, 1);
        return new Vec3(Mth.lerp(delta, from.x, to.x), Mth.lerp(delta, from.y, to.y), Mth.lerp(ease, from.z, to.z));
    }
}
