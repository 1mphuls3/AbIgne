package com.Imphuls3.abigne.api.Ignis;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.UUID;

public interface IIgnisBurst {
    BlockPos NO_SOURCE = new BlockPos(0, Integer.MIN_VALUE, 0);

    boolean isFake();

    int getColor();

    void setColor(int color);

    int getIgnis();

    void setIgnis(int mana);

    int getStartingIgnis();

    void setStartingIgnis(int mana);

    int getMinIgnisLoss();

    void setMinIgnisLoss(int minIgnisLoss);

    float getIgnisLossPerTick();

    void setIgnisLossPerTick(float mana);

    float getBurstGravity();

    void setGravity(float gravity);

    BlockPos getBurstSourceBlockPos();

    void setBurstSourceCoords(BlockPos pos);

    ItemStack getSourceLens();

    void setSourceLens(ItemStack lens);

    boolean hasAlreadyCollidedAt(BlockPos pos);

    void setCollidedAt(BlockPos pos);

    int getTicksExisted();

    void setFake(boolean fake);

    void setShooterUUID(UUID uuid);

    UUID getShooterUUID();

    void ping();

    boolean hasWarped();

    void setWarped(boolean warped);

    int getOrbitTime();

    void setOrbitTime(int time);

    boolean hasTripped();

    void setTripped(boolean tripped);


    @Nullable
    BlockPos getMagnetizedPos();

    void setMagnetizePos(@Nullable BlockPos pos);

    boolean hasLeftSource();

    default ThrowableProjectile entity() {
        return (ThrowableProjectile) this;
    }
}
