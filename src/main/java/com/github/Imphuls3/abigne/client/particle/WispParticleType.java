package com.github.Imphuls3.abigne.client.particle;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;

public class WispParticleType extends ParticleType<ColorParticleOptions> {
    public WispParticleType() {
        super(false, ColorParticleOptions.DESERIALIZER);
    }

    @Override
    public Codec<ColorParticleOptions> codec() {
        return ColorParticleOptions.CODEC;
    }
}