package com.github.Imphuls3.abigne.core.registry;

public class AttributeRegistry {
    /*static DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, AbIgne.MODID);

    public static RegistryObject<Attribute>
            MAX_SOUL_HEARTS = ATTRIBUTES.register("max_soul_hearts", () -> new RangedAttribute(AbIgne.MODID + ".max_soul_hearts", 25, 0, 2000).setSyncable(true));


    @SubscribeEvent
    public void addCustomAttributes(EntityAttributeModificationEvent event) {
        for (EntityType<? extends LivingEntity> t : event.getTypes()) {
            if (event.has(t, Attributes.MAX_HEALTH)) {
                event.add(t, AttributeRegistry.MAX_SOUL_HEARTS.get());
            }
        }
    }

    public void register(IEventBus bus){
        ATTRIBUTES.register(bus);
    }*/
}
