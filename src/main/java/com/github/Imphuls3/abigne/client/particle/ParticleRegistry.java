package com.github.Imphuls3.abigne.client.particle;

import com.github.Imphuls3.abigne.AbIgne;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

@Mod.EventBusSubscriber(modid = AbIgne.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleRegistry {
    @ObjectHolder(AbIgne.MODID + ":" + FlameParticleData.NAME) public static ParticleType<ColorParticleTypeData> FLAME_TYPE;
    @ObjectHolder(AbIgne.MODID + ":" + SparkleParticleData.NAME) public static ParticleType<ColorParticleTypeData> SPARKLE_TYPE;

    @SubscribeEvent
    public static void registerParticles(RegistryEvent.Register<ParticleType<?>> event) {
        IForgeRegistry<ParticleType<?>> r = event.getRegistry();
        r.register( new FlameParticleType().setRegistryName(FlameParticleData.NAME));
        r.register( new FlameParticleType().setRegistryName(SparkleParticleData.NAME));
    }

    @SuppressWarnings("resource")
    @SubscribeEvent
    public static void registerFactories(ParticleFactoryRegisterEvent event) {
        Minecraft.getInstance().particleEngine.register(FLAME_TYPE, FlameParticleData::new);
        Minecraft.getInstance().particleEngine.register(SPARKLE_TYPE, SparkleParticleData::new);
    }
}
