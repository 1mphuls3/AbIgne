package com.github.Imphuls3.abigne.client.event;

import com.github.Imphuls3.abigne.core.registry.RenderTypeRegistry;
import com.github.Imphuls3.abigne.core.registry.ShaderRegistry;
import com.mojang.blaze3d.pipeline.MainTarget;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

import static com.mojang.blaze3d.platform.GlConst.GL_DRAW_FRAMEBUFFER;
import static net.minecraft.client.Minecraft.ON_OSX;

public class RenderHandler {
    @OnlyIn(Dist.CLIENT)
    static MultiBufferSource.BufferSource DELAYED_RENDER = null;
    public static Matrix4f PARTICLE_MATRIX = null;
    public static boolean EXPAND_THE_BUFFERS = ModList.get().isLoaded("rubidium");

    @OnlyIn(Dist.CLIENT)
    public static MultiBufferSource.BufferSource getDelayedRender() {
        boolean expandedBuffer = ModList.get().isLoaded("rubidium");
        int size = expandedBuffer ? 262144 : 256;
        if (DELAYED_RENDER == null) {
            Map<RenderType, BufferBuilder> buffers = new HashMap<>();
            for (RenderType type : new RenderType[]{
                    RenderTypeRegistry.ADDITIVE_PARTICLE}) {
                buffers.put(type, new BufferBuilder(type.bufferSize()));
            }
            DELAYED_RENDER = MultiBufferSource.immediateWithBuffers(buffers, new BufferBuilder(size));
        }
        return DELAYED_RENDER;
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRenderLast(RenderLevelStageEvent event) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_WEATHER) {
            RenderSystem.getModelViewStack().pushPose();
            RenderSystem.getModelViewStack().setIdentity();
            if (PARTICLE_MATRIX != null) {
                RenderSystem.getModelViewStack().mulPoseMatrix(PARTICLE_MATRIX);
            }
            RenderSystem.applyModelViewMatrix();
            getDelayedRender().endBatch(RenderTypeRegistry.ADDITIVE_PARTICLE);
            /*getDelayedRender().endBatch(ShaderRegistry.RenderTypeRegistry.TRANSPARENT_PARTICLE);*/
            RenderSystem.getModelViewStack().popPose();
        }
    }
}
