package com.github.Imphuls3.abigne.core;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;

public class DamageSourceRegistry {

    public static final String NECROTIC_DAMAGE= "necrotic";
    public static final DamageSource NECROTIC = new DamageSource(NECROTIC_DAMAGE).bypassArmor();
    public static DamageSource dealNecroticDamage(Entity attacker, LivingEntity hit) {
        if (!hit.getMobType().equals(MobType.UNDEAD)) {
            return new EntityDamageSource(NECROTIC_DAMAGE, attacker).bypassArmor().setMagic();
        } else {
            return null;
        }
    }

    //TODO: LIFESTEAL
    /*public static void lifesteal(Entity attacker, Mob hit, float amount) {
        if (!hit.getMobType().equals(MobType.UNDEAD)) {
            hit.hurt(dealNecroticDamage(attacker, hit), amount);
            attacker.hurt(HEALING, -amount);
        }
    }*/
}
