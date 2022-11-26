package com.github.Imphuls3.abigne.client.particle;

import com.github.Imphuls3.abigne.AbIgne;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

@Mod.EventBusSubscriber(modid = AbIgne.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AbIgne.MODID);

    public static final RegistryObject<ParticleType<ColorParticleOptions>> FLAME_TYPE = PARTICLES.register(FlameParticleData.NAME, FlameParticleType::new);
    public static final RegistryObject<ParticleType<ColorParticleOptions>> SPARKLE_TYPE = PARTICLES.register(SparkleParticleData.NAME, SparkleParticleType::new);
    public static final RegistryObject<ParticleType<ColorParticleOptions>> SPARK_TYPE = PARTICLES.register(SparkParticleData.NAME, SparkParticleType::new);
    public static final RegistryObject<ParticleType<ColorParticleOptions>> SMOKE_TYPE = PARTICLES.register(SmokeParticleData.NAME, SmokeParticleType::new);
    public static final RegistryObject<ParticleType<ColorParticleOptions>> WISP_TYPE = PARTICLES.register(WispParticleData.NAME, WispParticleType::new);

    @SubscribeEvent
    public static void registerFactories(RegisterParticleProvidersEvent event) {
        event.register(FLAME_TYPE.get(), FlameParticleData::new);
        event.register(SPARKLE_TYPE.get(), SparkleParticleData::new);
        event.register(SPARK_TYPE.get(), SparkParticleData::new);
        event.register(SMOKE_TYPE.get(), SmokeParticleData::new);
        event.register(WISP_TYPE.get(), WispParticleData::new);
    }

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}
