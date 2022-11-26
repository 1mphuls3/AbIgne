package com.github.Imphuls3.abigne.client.particle;

import com.github.Imphuls3.abigne.core.Easing;
import com.github.Imphuls3.abigne.core.helper.ColorHelper;
import com.github.Imphuls3.abigne.core.registry.RenderTypeRegistry;
import com.github.Imphuls3.abigne.core.registry.ShaderRegistry;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;
import java.util.Random;

@OnlyIn(Dist.CLIENT)
public class ParticleSpark extends TextureSheetParticle {
    public float colorR = 0;
    public float colorG = 0;
    public float colorB = 0;
    public float initScale = 0;
    public float initAlpha = 0;

    public boolean disableDepthTest;

    public ParticleSpark(ClientLevel worldIn, double x, double y, double z, double vx, double vy, double vz, float r, float g, float b, float a, float scale, int lifetime, SpriteSet sprite, boolean disableDepthTest) {
        super(worldIn, x, y, z, 0, 0, 0);
        this.hasPhysics = true;
        this.colorR = r;
        this.colorG = g;
        this.colorB = b;
        if (this.colorR > 1.0) {
            this.colorR = this.colorR / 255.0f;
        }
        if (this.colorG > 1.0) {
            this.colorG = this.colorG / 255.0f;
        }
        if (this.colorB > 1.0) {
            this.colorB = this.colorB / 255.0f;
        }
        this.setColor(colorR, colorG, colorB);
        this.lifetime = (int) ((float) lifetime * 0.5f);
        this.quadSize = scale;
        this.initScale = scale;
        this.xd = vx * 2.0f;
        this.yd = vy * 2.0f;
        this.zd = vz * 2.0f;
        this.alpha = a;
        this.oRoll = roll;
        this.pickSprite(sprite);
        this.disableDepthTest = disableDepthTest;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return RenderTypeRegistry.ParticleRenderTypes.ADDITIVE_PARTICLE_RENDERTYPE;
    }

    @Override
    public int getLightColor(float pTicks) {
        return 255;
    }

    @Override
    public void tick() {
        super.tick();

        if (new Random().nextInt(6) == 0) {
            this.age++;
        }
        float lifeCoeff = (float)this.age/(float)this.lifetime;
        this.setSize(initScale, initScale*lifeCoeff);

        Color color = ColorHelper.colorLerp(Easing.LINEAR, lifeCoeff, new Color(colorR, colorG, colorB), new Color(5, 2, 2));
        this.setColor(color.getRed()/255F, color.getGreen()/255F, color.getBlue()/255F);
    }

    @Override
    public boolean isAlive() {
        return this.age < this.lifetime;
    }
}
