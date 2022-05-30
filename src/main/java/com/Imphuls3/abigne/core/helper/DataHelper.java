package com.Imphuls3.abigne.core.helper;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class DataHelper {
    /**
     * Returns a new Vec3 from a Block Pos
     * */
    public static Vec3 v3fromBlockPos(BlockPos pos) {
        return new Vec3(pos.getX(), pos.getY(), pos.getZ());
    }
}
