package com.github.Imphuls3.abigne.client.event;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.recipe.InfusionRecipe;
import com.github.Imphuls3.abigne.core.registry.EntityRegistry;
import com.github.Imphuls3.abigne.common.entity.SoulEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AbIgne.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ModEventBus {
    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, InfusionRecipe.Type.ID, InfusionRecipe.Type.INSTANCE);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.SOUL.get(), SoulEntity.setAttributes());
    }
}
