package com.github.Imphuls3.abigne.common.entity;

import com.github.Imphuls3.abigne.client.particle.ParticleColor;
import com.github.Imphuls3.abigne.client.particle.SparkleParticleData;
import com.github.Imphuls3.abigne.core.DamageSourceRegistry;
import com.github.Imphuls3.abigne.core.blockentity.ModInventory;
import com.github.Imphuls3.abigne.core.registry.ItemRegistry;
import com.github.Imphuls3.abigne.core.spell.SpellProjectileEntity;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ManaBurst extends SpellProjectileEntity {
    public ModInventory inventory = new ModInventory(1, 64);

    public ManaBurst(EntityType<?> entityType, Level level) {
        super(entityType, level);
        inventory.setStackInSlot(0, ItemRegistry.NECROTIC_SHARD.get().getDefaultInstance());
    }

    @Override
    public void tick() {
        Vec3 motion = getDeltaMovement();
        setDeltaMovement(motion.x, 0, motion.z);
        this.baseTick();
        HitResult ray = ProjectileUtil.getHitResult(this, (entity) -> !entity.isSpectator());
        if(!level.isClientSide) {
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

        Vec3 movement = getDeltaMovement();
        Vec3 position = position();
        Vec3 norm = movement.normalize().scale(0.025f);
        for (int i = 0; i < 4; i ++) {
            double lerpX = Mth.lerp(i/4F, xo, position.x);
            double lerpY = Mth.lerp(i/4F, yo, position.y);
            double lerpZ = Mth.lerp(i/4F, zo, position.z);
            ParticleOptions data = SparkleParticleData.createData(new ParticleColor(75, 255, 25), true, 0.25F, 1F, 45);
            level.addParticle(data, lerpX, lerpY, lerpZ, norm.x, norm.y, norm.z);
        }
    }

    @Override
    public Iterable<ItemStack> getHandSlots() {
        return inventory.getStacks();
    }

    @Override
    public Vec3 getHandHoldingItemAngle(Item item) {
        return super.getHandHoldingItemAngle(item);
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {
        super.setItemSlot(pSlot, pStack);
    }

    @Override
    public void onRemovedFromWorld() {
        float vel = 0.05F;
        if(level.isClientSide){
            for (int i = 0; i < 8; i++) {
                ParticleOptions data = SparkleParticleData.createData(new ParticleColor(75, 255, 25), true, 0.2F, 1F, 30);
                level.addParticle(data, position().x, position().y, position().z,
                        Mth.randomBetween(new Random(), -vel, vel), Mth.randomBetween(new Random(), -vel, vel), Mth.randomBetween(new Random(), -vel, vel));
            }
        }
        super.onRemovedFromWorld();
    }

    @Override
    protected void onImpact(HitResult ray, Entity target) {
        target.hurt(DamageSourceRegistry.NECROTIC, 1F);
        onImpact(ray);
    }

    @Override
    protected void onImpact(HitResult ray) {
        removeAfterChangingDimensions();
    }

    @Override
    public boolean save(CompoundTag nbt) {
        inventory.save(nbt);
        return super.save(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        inventory.load(nbt);
        super.load(nbt);
    }
}
