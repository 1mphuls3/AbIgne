package com.github.Imphuls3.abigne.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ParticleOptions;

import java.awt.*;

public class SparkleParticleData implements ParticleProvider<ColorParticleOptions> {
    private final SpriteSet spriteSet;
    public static final String NAME = "sparkle_particle";

    public SparkleParticleData(SpriteSet sprite) {
        this.spriteSet = sprite;
    }

    @Override
    public Particle createParticle(ColorParticleOptions data, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        return new ParticleSparkle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, data.color.getRed(), data.color.getGreen(), data.color.getBlue(), data.alpha, data.size, data.age, this.spriteSet, data.disableDepthTest);
    }

    public static ParticleOptions createData(Color color) {
        return new ColorParticleOptions(ParticleRegistry.SPARKLE_TYPE.get(), color, false);
    }

    public static ParticleOptions createData(Color color, boolean disableDepthTest) {
        return new ColorParticleOptions(ParticleRegistry.SPARKLE_TYPE.get(), color, disableDepthTest, 0.25f, 0.75f, 36);
    }

    public static ParticleOptions createData(Color color, boolean disableDepthTest, float size, float alpha, int age) {
        return new ColorParticleOptions(ParticleRegistry.SPARKLE_TYPE.get(), color, disableDepthTest, size, alpha, age);
    }

    public static ParticleOptions createData(Color color, float size, float alpha, int age) {
        return new ColorParticleOptions(ParticleRegistry.SPARKLE_TYPE.get(), color, false, size, alpha, age);
    }
}
