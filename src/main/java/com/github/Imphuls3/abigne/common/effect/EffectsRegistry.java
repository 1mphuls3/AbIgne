package com.github.Imphuls3.abigne.common.effect;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.core.helper.ColorHelper;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectsRegistry {
    public static DeferredRegister<MobEffect> MOD_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AbIgne.MODID);

    public static final RegistryObject<MobEffect> NECROSIS = MOD_EFFECTS.register("necrosis",
            () -> new NecrosisEffect(MobEffectCategory.HARMFUL, ColorHelper.packColor(255, 22, 19, 31)));

    public static void register(IEventBus eventBus) {
        MOD_EFFECTS.register(eventBus);
    }
}
