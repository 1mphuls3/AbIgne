package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.entity.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AbIgne.MODID);

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

    public static final RegistryObject<EntityType<SilverNitrateExplosive>> SILVER_EXPLOSIVE = ENTITY_TYPES.register("silver_explosive",
            ()-> EntityType.Builder.of(SilverNitrateExplosive::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .fireImmune()
                    .build(AbIgne.modPath("silver_explosive").toString()));

    public static final RegistryObject<EntityType<SymbolProjectile>> SYMBOL_PROJECTILE = ENTITY_TYPES.register("symbol_projectile",
            ()-> EntityType.Builder.of(SymbolProjectile::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .fireImmune()
                    .build(AbIgne.modPath("symbol_projectile").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
