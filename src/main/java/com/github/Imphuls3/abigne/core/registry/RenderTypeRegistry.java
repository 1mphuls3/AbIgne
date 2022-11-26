package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.client.event.RenderHandler;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static com.github.Imphuls3.abigne.client.event.RenderHandler.EXPAND_THE_BUFFERS;
import static com.mojang.blaze3d.vertex.DefaultVertexFormat.PARTICLE;

public class RenderTypeRegistry extends RenderType{
    private RenderTypeRegistry(String s, VertexFormat v, VertexFormat.Mode m, int i, boolean b, boolean b2, Runnable r, Runnable r2) {
        super(s, v, m, i, b, b2, r, r2);
        throw new IllegalStateException("This class is not meant to be constructed!");
    }

    public static final RenderType ADD = create(AbIgne.MODID + ":additive_rendertype",
            DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS,
            262144, true, true, RenderType.CompositeState.builder()
                    .setLightmapState(LIGHTMAP)
                    .setShaderState(RENDERTYPE_TRIPWIRE_SHADER)
                    .setTextureState(BLOCK_SHEET_MIPPED)
                    .setTransparencyState(StateShards.ADDITIVE_TRANSPARENCY)
                    .setOutputState(WEATHER_TARGET)
                    .createCompositeState(true));

    public static final RenderType ADDITIVE_PARTICLE = RenderType.create(AbIgne.MODID + ":additive_particle", PARTICLE, VertexFormat.Mode.QUADS,
            EXPAND_THE_BUFFERS ? 262144 : 256, false, false, RenderType.CompositeState.builder()
                    .setShaderState(new ShaderStateShard(ShaderRegistry::getParticleShader))
                    .setWriteMaskState(new WriteMaskStateShard(true, true))
                    .setLightmapState(new LightmapStateShard(false))
                    .setTransparencyState(StateShards.ADDITIVE_TRANSPARENCY)
                    .setTextureState(new TextureStateShard(TextureAtlas.LOCATION_PARTICLES, false, false))
                    .setCullState(new CullStateShard(true))
                    .createCompositeState(true));

    public class StateShards extends RenderStateShard {
        public StateShards(String string, Runnable runnable, Runnable runnabler) {
            super(string, runnable, runnabler);
            throw new IllegalStateException("This class is not meant to be constructed!");
        }

        public static final TransparencyStateShard ADDITIVE_TRANSPARENCY = new TransparencyStateShard("additive_transparency", () -> {
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        }, () -> {
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.depthMask(true);
        });

        public static final TransparencyStateShard NORMAL_TRANSPARENCY = new TransparencyStateShard("normal_transparency", () -> {
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        }, () -> {
            RenderSystem.disableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.depthMask(true);
        });
    }

    public static class ParticleRenderTypes {
        public static final ParticleRenderType ADDITIVE_PARTICLE_RENDERTYPE = new ParticleRenderType() {
            @Override
            public void begin(BufferBuilder builder, TextureManager textureManager) {
                RenderSystem.depthMask(false);
                RenderSystem.enableBlend();
                RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                RenderSystem.setShader(ShaderRegistry::getParticleShader);
                RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
                RenderHandler.PARTICLE_MATRIX = RenderSystem.getModelViewMatrix();
                builder.begin(VertexFormat.Mode.QUADS, PARTICLE);
            }

            @Override
            public void end(Tesselator tessellator) {
                tessellator.end();
            }

            @Override
            public String toString() {
                return AbIgne.MODID + ":additive_particle_rendertype";
            }
        };

        public static final ParticleRenderType TRANSPARENT_PARTICLE_RENDERTYPE = new ParticleRenderType() {
            @Override
            public void begin(BufferBuilder builder, TextureManager manager) {
                RenderSystem.depthMask(false);
                RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
                RenderSystem.enableBlend();
                RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
                RenderHandler.PARTICLE_MATRIX = RenderSystem.getModelViewMatrix();
                builder.begin(VertexFormat.Mode.QUADS, PARTICLE);
            }

            @Override
            public void end(Tesselator tesselator) {
                tesselator.end();
                RenderSystem.depthMask(true);
                RenderSystem.disableBlend();
                RenderSystem.defaultBlendFunc();
            }

            @Override
            public String toString() {
                return AbIgne.MODID + ":transparent_particle_rendertype";
            }
        };
    }
}
