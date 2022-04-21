package com.Imphuls3.abigne.core.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class DataHelper {
    public static Vec3 fromBlockPos(BlockPos pos) {
        return new Vec3(pos.getX(), pos.getY(), pos.getZ());
    }
}
