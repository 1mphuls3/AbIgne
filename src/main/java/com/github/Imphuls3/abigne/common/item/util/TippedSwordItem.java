package com.github.Imphuls3.abigne.common.item.util;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class TippedSwordItem extends SwordItem {
    private MobEffect effect;
    private int duration;
    private int amplifier;

    /**
     * @param effect a MobEffect given to an entity hit by this sword
     * @param duration the duration of the effect given to an entity hit by this sword (in seconds)
     * */
    public TippedSwordItem(Tier tier, MobEffect effect, int duration, int amplifier, int damageModifier, float speedModifier, Properties properties) {
        super(tier, damageModifier, speedModifier, properties);
        this.effect = effect;
        this.duration = duration*20;
        this.amplifier = amplifier;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(attacker.canBeAffected(new MobEffectInstance(effect, duration, amplifier))) {
            target.addEffect(new MobEffectInstance(effect, duration, amplifier));
        }
        stack.hurtAndBreak(1, attacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }
}
