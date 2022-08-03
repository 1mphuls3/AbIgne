package com.Imphuls3.abigne.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class BleedEffect extends MobEffect {
    int i;
    protected BleedEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if(!livingEntity.level.isClientSide) {
            if(i == 5) {
                livingEntity.hurt(AbIgneDamageSources.BLEED, (amplifier+1));
                i = 0;
            } else {
                i++;
            }
        }
        super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
