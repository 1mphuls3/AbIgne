package com.github.Imphuls3.abigne.common.entity;

import com.github.Imphuls3.abigne.client.particle.SmokeParticleData;
import com.github.Imphuls3.abigne.core.spell.SpellProjectileEntity;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

public class SilverNitrateExplosive extends SpellProjectileEntity {

    public SilverNitrateExplosive(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 motion = getDeltaMovement();
        Vec3 pos = position();
        Vec3 norm = motion.normalize().scale(0.025f);
        for (int i = 0; i < 8; i ++) {
            double lerpX = Mth.lerp(i / 8.0f, xo, pos.x);
            double lerpY = Mth.lerp(i / 8.0f, yo, pos.y);
            double lerpZ = Mth.lerp(i / 8.0f, zo, pos.z);
            float scale = Mth.randomBetween(RandomSource.create(), 0.35F, 0.25F);
            ParticleOptions data = SmokeParticleData.createData(new Color(177, 219, 121), true, scale, 0.5F, 60);
            level.addParticle(data, lerpX, lerpY, lerpZ, norm.x, norm.y, norm.z);
        }
    }

    @Override
    public void onRemovedFromWorld() {
        float vel = 0.05F;
        if(level.isClientSide){
            for (int i = 0; i < 8; i++) {
                ParticleOptions data = SmokeParticleData.createData(new Color(177, 219, 121), true, 0.2F, 0.5F, 30);
                level.addParticle(data, position().x, position().y, position().z,
                        Mth.randomBetween(RandomSource.create(), -vel, vel), Mth.randomBetween(RandomSource.create(), -vel, vel), Mth.randomBetween(RandomSource.create(), -vel, vel));
            }
        }
        super.onRemovedFromWorld();
    }

    @Override
    protected void onImpact(HitResult ray, Entity target) {
        onImpact(ray);
    }

    @Override
    protected void onImpact(HitResult ray) {
        level.explode(this, getX(), getY(), getZ(), 3, Explosion.BlockInteraction.NONE);
        removeAfterChangingDimensions();
    }
}
