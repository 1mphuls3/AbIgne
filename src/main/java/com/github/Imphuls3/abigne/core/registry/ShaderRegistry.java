package com.github.Imphuls3.abigne.core.registry;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.io.IOException;

public class ShaderRegistry {

    public static ShaderInstance SPRITE_PARTICLE_SHADER;
    public static ShaderInstance getSpriteParticleShader() { return SPRITE_PARTICLE_SHADER; }

    @SubscribeEvent
    public void shaderRegistry(RegisterShadersEvent event) throws IOException {
        event.registerShader(new ShaderInstance(event.getResourceManager(), new ResourceLocation("abigne:sprite_particle"), DefaultVertexFormat.PARTICLE),
                shader -> { SPRITE_PARTICLE_SHADER = shader; });
    }

    public static class AbIgneRenderTypes extends RenderType {
        private AbIgneRenderTypes(String s, VertexFormat v, VertexFormat.Mode m, int i, boolean b, boolean b2, Runnable r, Runnable r2) {
            super(s, v, m, i, b, b2, r, r2);
            throw new IllegalStateException("This class is not meant to be constructed!");
        }

        private static RenderType.CompositeState additiveTransparency() {
            return RenderType.CompositeState.builder()
                    .setLightmapState(LIGHTMAP)
                    .setShaderState(RENDERTYPE_TRIPWIRE_SHADER)
                    .setTextureState(BLOCK_SHEET_MIPPED)
                    .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                    .setOutputState(WEATHER_TARGET)
                    .createCompositeState(true);
        }

        public static final RenderType ADD = create("translucent",
                DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS,
                262144, true, true,
                additiveTransparency());
    }

    public static class ParticleRenderTypes {
        public static final ParticleRenderType TRANSPARENT_PARTICLE_RENDERTYPE = new ParticleRenderType() {
            @Override
            public void begin(BufferBuilder buffer, TextureManager textureManager) {
                Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
                RenderSystem.enableBlend();
                RenderSystem.enableCull();
                RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
                RenderSystem.enableDepthTest();
                RenderSystem.depthMask(false);
                RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.value, GlStateManager.DestFactor.ONE.value);
                buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
            }

            @Override
            public void end(Tesselator tessellator) {
                tessellator.end();
            }

            @Override
            public String toString() {
                return "abigne:flame_rend";
            }
        };

        public static final ParticleRenderType FLAME_RENDERTYPE_DEPTH = new ParticleRenderType() {
            @Override
            public void begin(BufferBuilder buffer, TextureManager textureManager) {
                Minecraft.getInstance().gameRenderer.lightTexture().turnOnLightLayer();
                RenderSystem.enableBlend();
                RenderSystem.enableCull();
                RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
                RenderSystem.enableDepthTest();
                RenderSystem.depthMask(true );
                RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.value, GlStateManager.DestFactor.ONE.value);
                buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
            }

            @Override
            public void end(Tesselator tessellator) {
                tessellator.end();
            }

            @Override
            public String toString() {
                return "abigne:flame_rend";
            }
        };
    }
}
