package com.Imphuls3.abigne.common.blockentity;

import com.Imphuls3.abigne.core.systems.ignis.AbstractIgnisMachine;
import com.Imphuls3.abigne.config.CalmingTorchConfig;
import com.Imphuls3.abigne.core.registry.common.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

import static com.Imphuls3.abigne.common.block.CalmingTorchBlock.LIT;

public class CalmingTorchBlockEntity extends AbstractIgnisMachine {
    private int maxIgnis = 1000;
    boolean active;

    double range = CalmingTorchConfig.range.get();
    double healthAffected = CalmingTorchConfig.maxHealthAffected.get();
    int consumption = CalmingTorchConfig.ignisConsumption.get();
    double sightRange = CalmingTorchConfig.mobSightRange.get();

    public CalmingTorchBlockEntity(BlockEntityType<? extends CalmingTorchBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public CalmingTorchBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.TORCH.get(), pos, state);
    }

    @Override
    public void tick(Level level, BlockState state, BlockPos pos) {
        if(state.getValue(LIT)) {
            Random random = new Random();
            this.level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.FIRE_AMBIENT, SoundSource.NEUTRAL, 0.6f, random.nextFloat() * 0.7F + 0.3F);
            AABB aabb = new AABB(pos.getX() -(range/2D), pos.getY(), pos.getZ() -(range/2D), pos.getX() +(range/2D), pos.getY() +(range/4D), pos.getZ() +(range/2D));
            List<Mob> mobs = this.level.getEntitiesOfClass(Mob.class, aabb);
            for (Mob mob: mobs) {
                if(mob instanceof Enemy && mob.getMaxHealth() < healthAffected) {
                    mob.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(sightRange);
                    mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 0));
                    mob.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 0));
                    if(mob instanceof Creeper creeper) {
                        creeper.setSwellDir(-1);
                    }
                }
            }
            List<Projectile> projectiles = this.level.getEntitiesOfClass(Projectile.class, aabb);
            for (Projectile projectile: projectiles) {
                if(projectile.getOwner() instanceof Mob owner && owner.getMaxHealth() < healthAffected) {
                    Vec3 projPos = projectile.getPosition(0);
                    for (int i = 0; i < 5; i++) {
                        level.addParticle(ParticleTypes.FLAME, projPos.x, projPos.y, projPos.z,
                                (random.nextFloat()-0.5)/8, (random.nextFloat()-0.5)/7, (random.nextFloat()-0.5)/8);
                    }
                    for (int i = 0; i < 3; i++) {
                        level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, projPos.x, projPos.y, projPos.z,
                                (random.nextFloat()-0.5)/8, (random.nextFloat()-0.5)/7, (random.nextFloat()-0.5)/8);
                    }
                    level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.NEUTRAL,
                            0.6f, random.nextFloat() * 0.8F);
                    projectile.discard();
                }
            }
        }
    }

    @Override
    public int getTransferRate() {
        return getMaxIgnis();
    }

    @Override
    public int getMaxIgnis() {
        return this.maxIgnis;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
    }
}