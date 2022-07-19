package com.Imphuls3.abigne.core.helper;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.renderer.GameRenderer;

public class RenderHelper {
    public static void blit(PoseStack stack, int x, int y, double width, double height, float u, float v, float xCanvasSize, float yCanvasSize) {
        innerBlit(stack, x, y, width, height, u / xCanvasSize, v / yCanvasSize, (float) width / xCanvasSize, (float) height / yCanvasSize);
    }

    public static void innerBlit(PoseStack stack, int x, int y, double width, double height, float u, float v, float uWidth, float vHeight) {
        Matrix4f last = stack.last().pose();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(last, (float) x, (float) y + (float) height, 0).uv(u, v + vHeight).endVertex();
        bufferbuilder.vertex(last, (float) x + (float) width, (float) y + (float) height, 0).uv(u + uWidth, v + vHeight).endVertex();
        bufferbuilder.vertex(last, (float) x + (float) width, (float) y, 0).uv(u + uWidth, v).endVertex();
        bufferbuilder.vertex(last, (float) x, (float) y, 0).uv(u, v).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
    }
}
