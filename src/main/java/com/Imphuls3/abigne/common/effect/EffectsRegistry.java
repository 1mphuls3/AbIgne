package com.Imphuls3.abigne.common.effect;

import com.Imphuls3.abigne.AbIgne;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectsRegistry {
    public static DeferredRegister<MobEffect> MOD_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AbIgne.MODID);

    public static final RegistryObject<MobEffect> BLEED = MOD_EFFECTS.register("bleed",
            () -> new BleedEffect(MobEffectCategory.HARMFUL, 9643043));

    public static void register(IEventBus eventBus) {
        MOD_EFFECTS.register(eventBus);
    }
}
