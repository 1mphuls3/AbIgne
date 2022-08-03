package com.Imphuls3.abigne.core.registry.common;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.entity.ModFireballProjectileEntity;
import com.Imphuls3.abigne.common.entity.SoulEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.Imphuls3.abigne.AbIgne.prefix;

public class EntityRegistration {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, AbIgne.MODID);

    public static final RegistryObject<EntityType<SoulEntity>> SOUL = ENTITY_TYPES.register("soul",
            ()-> EntityType.Builder.of(SoulEntity::new, MobCategory.CREATURE)
                    .sized(0.8F, 0.8F)
                    .fireImmune()
                    .build(prefix("soul").toString()));

    public static final RegistryObject<EntityType<ModFireballProjectileEntity>> FIREBALL = ENTITY_TYPES.register("fireball_spell",
            ()-> EntityType.Builder.of(ModFireballProjectileEntity::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .fireImmune()
                    .build(prefix("fireball_spell").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
