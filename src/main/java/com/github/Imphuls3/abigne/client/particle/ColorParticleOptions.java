package com.github.Imphuls3.abigne.client.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import org.checkerframework.checker.units.qual.C;

import java.awt.*;

import static com.github.Imphuls3.abigne.core.helper.RegistryHelper.getRegistryName;

public class ColorParticleOptions implements ParticleOptions {
    private ParticleType<ColorParticleOptions> type;

    public static final Codec<ColorParticleOptions> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.FLOAT.fieldOf("r").forGetter(d -> d.color.getRed()/255F),
                    Codec.FLOAT.fieldOf("g").forGetter(d -> d.color.getGreen()/255F),
                    Codec.FLOAT.fieldOf("b").forGetter(d -> d.color.getBlue()/255F),
                    Codec.BOOL.fieldOf("disableDepthTest").forGetter(d-> d.disableDepthTest),
                    Codec.FLOAT.fieldOf("size").forGetter(d -> d.size),
                    Codec.FLOAT.fieldOf("alpha").forGetter(d -> d.alpha),
                    Codec.INT.fieldOf("age").forGetter(d -> d.age)
            )
            .apply(instance, ColorParticleOptions::new));

    public Color color;
    public boolean disableDepthTest;
    public float size = .25f;
    public float alpha = 1.0f;
    public int age = 36;

    static final ParticleOptions.Deserializer<ColorParticleOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {
        @Override
        public ColorParticleOptions fromCommand(ParticleType<ColorParticleOptions> type, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            return new ColorParticleOptions(type, new Color(reader.readInt()), reader.readBoolean());
        }

        @Override
        public ColorParticleOptions fromNetwork(ParticleType<ColorParticleOptions> type, FriendlyByteBuf buffer) {
            return new ColorParticleOptions(type, new Color(buffer.readInt()), buffer.readBoolean());
        }
    };

    public ColorParticleOptions(float r, float g, float b, boolean disableDepthTest, float size, float alpha, int age){
        this(ParticleRegistry.FLAME_TYPE.get(), new Color(r,g,b), disableDepthTest, size, alpha, age);
    }
    public ColorParticleOptions(Color color, boolean disableDepthTest, float size, float alpha, int age){
        this(ParticleRegistry.FLAME_TYPE.get(), color, disableDepthTest, size, alpha, age);
    }

    public ColorParticleOptions(ParticleType<ColorParticleOptions> particleTypeData, Color color, boolean disableDepthTest){
        this(particleTypeData, color, disableDepthTest, 0.25f, 1.0f, 36);
    }
    public ColorParticleOptions(ParticleType<ColorParticleOptions> particleTypeData, Color color, boolean disableDepthTest, float size, float alpha, int age) {
        this.type = particleTypeData;
        this.color = color;
        this.disableDepthTest = disableDepthTest;
        this.size = size;
        this.alpha = alpha;
        this.age = age;
    }

    @Override
    public ParticleType<ColorParticleOptions> getType() {
        return type;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf packetBuffer) {
        packetBuffer.writeInt(color.getRGB());
    }

    @Override
    public String writeToString() {
        return getRegistryName(type).toString() + " " + color.getRGB();
    }
}
