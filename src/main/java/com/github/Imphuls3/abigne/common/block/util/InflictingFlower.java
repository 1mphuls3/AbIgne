package com.github.Imphuls3.abigne.common.block.util;

import java.util.Random;

import com.github.Imphuls3.abigne.client.particle.ParticleColor;
import com.github.Imphuls3.abigne.client.particle.SparkleParticleData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class InflictingFlower extends FlowerBlock {
    public MobEffect effect;
    private int duration;
    private ParticleColor color;

    /**
     * Basically copied Wither Rose block using the effect in the constructor parameter instead of Wither
     * @param effect Effect given to entity touching it (also used for the flower's suspicious stew)
     * @param duration Duration the effect should last (in seconds) (also used for the flower's suspicious stew)
     * @param color Color used for particles spawned by this block.
     * */
    public InflictingFlower(MobEffect effect, int duration, ParticleColor color, BlockBehaviour.Properties pProperties) {
        super(effect, duration, pProperties);
        this.effect = effect;
        this.duration = duration*20;
        this.color = color;
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, Random random) {
        float vel = 0.015F;
        VoxelShape voxelshape = this.getShape(state, level, pos, CollisionContext.empty());
        Vec3 vec3 = voxelshape.bounds().getCenter();
        double d0 = (double)pos.getX() + vec3.x;
        double d1 = (double)pos.getZ() + vec3.z;
        for(int i = 0; i < 3; ++i) {
            if (random.nextBoolean()) {
                ParticleOptions data = SparkleParticleData.createData(color, true, 0.1F, 0.8F, 35);
                level.addParticle(data, d0 + random.nextDouble() / 5.0D, (double)pos.getY() + 0.2 + (0.5D - random.nextDouble()), d1 + random.nextDouble() / 5.0D,
                        Mth.randomBetween(new Random(), -vel, vel), Mth.randomBetween(new Random(), -vel, vel), Mth.randomBetween(new Random(), -vel, vel));
            }
        }
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && level.getDifficulty() != Difficulty.PEACEFUL) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)entity;
                if (livingEntity.canBeAffected(new MobEffectInstance(this.effect, 5)) && !livingEntity.hasEffect(this.effect)) {
                    livingEntity.addEffect(new MobEffectInstance(this.effect, this.duration));
                }
            }
        }
    }
}
