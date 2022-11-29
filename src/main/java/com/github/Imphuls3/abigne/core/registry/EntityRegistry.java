package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.github.Imphuls3.abigne.AbIgne.modPath;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AbIgne.MODID);

    public static final RegistryObject<EntityType<LifeStealProjectile>> FIREBALL = ENTITY_TYPES.register("fireball_spell",
            ()-> EntityType.Builder.of(LifeStealProjectile::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .fireImmune()
                    .build(modPath("fireball_spell").toString()));

    public static final RegistryObject<EntityType<ManaBurst>> MANA = ENTITY_TYPES.register("mana_burst",
            ()-> EntityType.Builder.of(ManaBurst::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .fireImmune()
                    .build(modPath("mana_burst").toString()));

    public static final RegistryObject<EntityType<WispEntity>> WISP = ENTITY_TYPES.register("wisp",
            ()-> EntityType.Builder.of(WispEntity::new, MobCategory.MONSTER)
                    .sized(1F, 1F)
                    .build(modPath("wisp").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
