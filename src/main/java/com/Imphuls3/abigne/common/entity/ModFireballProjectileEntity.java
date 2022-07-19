package com.Imphuls3.abigne.common.entity;

import com.Imphuls3.abigne.core.helper.BlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class ModFireballProjectileEntity extends SpellProjectileEntity {
    public ModFireballProjectileEntity(EntityType<?> entityType, Level level) {
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
            level.addParticle(ParticleTypes.FLAME, lerpX, lerpY, lerpZ, -norm.x, -norm.y, -norm.z);
        }
    }

    @Override
    protected void onImpact(HitResult ray, Entity target) {
        target.hurt(DamageSource.indirectMagic(this, level.getPlayerByUUID(casterId)), 5.0f);
        onImpact(ray);
    }

    @Override
    protected void onImpact(HitResult ray) {
        removeAfterChangingDimensions();
        if (!level.isClientSide) {
            Vec3 pos = ray.getLocation();
            BlockPos bp = BlockHelper.blockPosfromVec3(pos);
            if(level.getBlockState(bp).getBlock() instanceof AirBlock) {
                level.setBlock(bp, Blocks.FIRE.defaultBlockState(), 2);
                level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.FIRECHARGE_USE, SoundSource.NEUTRAL, 0.6f, random.nextFloat() * 0.2f + 0.9f);
            }
        }
    }
}
