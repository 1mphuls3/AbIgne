package com.Imphuls3.abigne.client.event;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.entity.EntityInit;
import com.Imphuls3.abigne.common.entity.SoulEntity;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AbIgne.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ModEventBus {
   /* @SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent evt) {
        ModParticles.FactoryHandler.registerFactories(new ModParticles.FactoryHandler.Consumer() {
            @Override
            public <T extends ParticleOptions> void register(ParticleType<T> type, Function<SpriteSet, ParticleProvider<T>> constructor) {
                Minecraft.getInstance().particleEngine.register(type, constructor::apply);
            }
        });
    }*/

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event){
        /*Registry.register(Registry.RECIPE_TYPE, InfusionRecipe.Type.ID, InfusionRecipe.Type.INSTANCE);*/
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(EntityInit.SOUL.get(), SoulEntity.setAttributes());
    }
}
