package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AttributeRegistry {
    static DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, AbIgne.MODID);

    /*public static RegistryObject<Attribute> MAX_SOUL_HEARTS = ATTRIBUTES.register("max_soul_hearts",
            () -> new RangedAttribute(AbIgne.MODID + ".max_soul_hearts", 25, 0, 2000).setSyncable(true));


    @SubscribeEvent
    public void addCustomAttributes(EntityAttributeModificationEvent event) {
        for (EntityType<? extends LivingEntity> t : event.getTypes()) {
            if (event.has(t, Attributes.MAX_HEALTH)) {
                event.add(t, AttributeRegistry.MAX_SOUL_HEARTS.get());
            }
        }
    }*/

    public static void register(IEventBus bus) {
        ATTRIBUTES.register(bus);
    }
}
