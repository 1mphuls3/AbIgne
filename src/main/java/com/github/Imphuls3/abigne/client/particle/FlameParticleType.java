package com.github.Imphuls3.abigne.client.particle;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;

public class FlameParticleType extends ParticleType<ColorParticleTypeData> {
    public FlameParticleType() {
        super(false, ColorParticleTypeData.DESERIALIZER);
    }

    @Override
    public Codec<ColorParticleTypeData> codec() {
        return ColorParticleTypeData.CODEC;
    }
}