package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.entity.LifeStealProjectile;
import com.github.Imphuls3.abigne.common.entity.ManaBurst;
import com.github.Imphuls3.abigne.common.entity.SoulEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, AbIgne.MODID);

    public static final RegistryObject<EntityType<SoulEntity>> SOUL = ENTITY_TYPES.register("soul",
            ()-> EntityType.Builder.of(SoulEntity::new, MobCategory.MISC)
                    .sized(0.8F, 0.8F)
                    .fireImmune()
                    .build(AbIgne.modPath("soul").toString()));

    public static final RegistryObject<EntityType<LifeStealProjectile>> FIREBALL = ENTITY_TYPES.register("fireball_spell",
            ()-> EntityType.Builder.of(LifeStealProjectile::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .fireImmune()
                    .build(AbIgne.modPath("fireball_spell").toString()));

    public static final RegistryObject<EntityType<ManaBurst>> MANA = ENTITY_TYPES.register("mana_burst",
            ()-> EntityType.Builder.of(ManaBurst::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .fireImmune()
                    .build(AbIgne.modPath("mana_burst").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
