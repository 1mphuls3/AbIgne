package com.github.Imphuls3.abigne.common.entity;

import com.github.Imphuls3.abigne.client.particle.SparkleParticleData;
import com.github.Imphuls3.abigne.core.spell.SpellProjectileEntity;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

public class SymbolProjectile extends SpellProjectileEntity {

    public SymbolProjectile(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        Vec3 motion = getDeltaMovement();
        setDeltaMovement(motion.x, (motion.y > 0 ? motion.y * 0.98 : motion.y) - 0.03f, motion.z);
        this.baseTick();
        if (!level.isClientSide) {
            HitResult ray = ProjectileUtil.getHitResult(this, (entity) -> !entity.isSpectator() && !entity.getUUID().equals(casterId));
            if (ray.getType() == HitResult.Type.ENTITY) {
                onImpact(ray, ((EntityHitResult)ray).getEntity());
            }
            else if (ray.getType() == HitResult.Type.BLOCK) {
                onImpact(ray);
            }
        }

        Vec3 pos = position();
        xo = pos.x;
        yo = pos.y;
        zo = pos.z;
        setPos(pos.x + motion.x, pos.y + motion.y, pos.z + motion.z);

        Vec3 motion2 = getDeltaMovement();
        Vec3 pos2 = position();
        Vec3 norm = motion2.normalize().scale(0.025f);
        ParticleOptions data1 = SparkleParticleData.createData(new Color(135, 2, 215), true, 0.3F, 1F, 15);
        level.addParticle(data1, pos2.x, pos2.y, pos2.z, norm.x, norm.y, norm.z);
        for (int i = 0; i < 16; i ++) {
            double lerpX = Mth.lerp(i / 16F, xo, pos2.x);
            double lerpY = Mth.lerp(i / 16F, yo, pos2.y);
            double lerpZ = Mth.lerp(i / 16F, zo, pos2.z);
            ParticleOptions data2 = SparkleParticleData.createData(new Color(80, 10, 135), true, 0.3F, 1F, 25);
            level.addParticle(data2, lerpX, lerpY, lerpZ, norm.x, norm.y, norm.z);
        }
    }

    @Override
    public void onRemovedFromWorld() {
        float vel = 0.075F;
        if(level.isClientSide){
            for (int i = 0; i < 16; i++) {
                ParticleOptions data = SparkleParticleData.createData(new Color(75, 6, 200), true, 0.2F, 1F, 30);
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
        removeAfterChangingDimensions();
    }
}
