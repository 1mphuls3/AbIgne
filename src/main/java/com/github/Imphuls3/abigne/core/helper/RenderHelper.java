package com.github.Imphuls3.abigne.core.helper;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

import java.awt.*;

public class RenderHelper {
    public static final int FULL_BRIGHT = 15728880;

    public static void renderSprite(VertexConsumer vertexConsumer, Matrix4f matrix, PoseStack stack, TextureAtlasSprite sprite,
                                    float scale, float r, float g, float b, float alpha, int brightness) {
        vertexConsumer.vertex(matrix, -scale, -scale, 0.0f).color(r/255F, g/255F, b/255F, alpha)
                .uv(sprite.getU0(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, -scale, scale, 0.0f).color(r/255F, g/255F, b/255F, alpha)
                .uv(sprite.getU0(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, scale, scale, 0.0f).color(r/255F, g/255F, b/255F, alpha)
                .uv(sprite.getU1(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, scale, -scale, 0.0f).color(r/255F, g/255F, b/255F, alpha)
                .uv(sprite.getU1(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
    }

    public static void renderSprite(VertexConsumer vertexConsumer, Matrix4f matrix, PoseStack stack, TextureAtlasSprite sprite,
                                    float scale, Color color, float alpha, int brightness) {
        vertexConsumer.vertex(matrix, -scale, -scale, 0.0f).color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, alpha)
                .uv(sprite.getU0(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, -scale, scale, 0.0f).color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, alpha)
                .uv(sprite.getU0(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, scale, scale, 0.0f).color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, alpha)
                .uv(sprite.getU1(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, scale, -scale, 0.0f).color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, alpha)
                .uv(sprite.getU1(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
    }

    public static void renderRectangle(VertexConsumer vertexConsumer, Matrix4f matrix, PoseStack stack, TextureAtlasSprite sprite,
                                       float width, float height, float r, float g, float b, float alpha, int brightness) {
        vertexConsumer.vertex(matrix, -width, -height, 0.0f).color(r/255F, g/255F, b/255F, alpha)
                .uv(sprite.getU0(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, -width, height, 0.0f).color(r/255F, g/255F, b/255F, alpha)
                .uv(sprite.getU0(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, width, height, 0.0f).color(r/255F, g/255F, b/255F, alpha)
                .uv(sprite.getU1(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, width, -height, 0.0f).color(r/255F, g/255F, b/255F, alpha)
                .uv(sprite.getU1(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
    }

    public static void renderRectangle(VertexConsumer vertexConsumer, Matrix4f matrix, PoseStack stack, TextureAtlasSprite sprite,
                                       float start, float end, Color color, float alpha, int brightness) {
        vertexConsumer.vertex(matrix, -start, -start, 0.0f).color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, alpha)
                .uv(sprite.getU0(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, -start, start, 0.0f).color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, alpha)
                .uv(sprite.getU0(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, end, end, 0.0f).color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, alpha)
                .uv(sprite.getU1(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, end, -end, 0.0f).color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, alpha)
                .uv(sprite.getU1(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
    }

    public static void cube(PoseStack stack, VertexConsumer vertexConsumer, TextureAtlasSprite sprite,
                            float x1, float y1, float z1, float x2, float y2, float z2,
                            int r, int g, int b, int a, int light,
                            boolean renderWest, boolean renderEast, boolean renderDown, boolean renderUp, boolean renderNorth, boolean renderSouth) {
        Matrix4f mat = stack.last().pose();
        if (renderUp) {
            vertexConsumer.vertex(mat, x1, y2, z1).color(r, g, b, a).uv(sprite.getU(z1 * 16), sprite.getV(x1 * 16)).uv2(light).normal(0, 1, 0).endVertex();
            vertexConsumer.vertex(mat, x1, y2, z2).color(r, g, b, a).uv(sprite.getU(z2 * 16), sprite.getV(x1 * 16)).uv2(light).normal(0, 1, 0).endVertex();
            vertexConsumer.vertex(mat, x2, y2, z2).color(r, g, b, a).uv(sprite.getU(z2 * 16), sprite.getV(x2 * 16)).uv2(light).normal(0, 1, 0).endVertex();
            vertexConsumer.vertex(mat, x2, y2, z1).color(r, g, b, a).uv(sprite.getU(z1 * 16), sprite.getV(x2 * 16)).uv2(light).normal(0, 1, 0).endVertex();
        }
        if (renderDown) {
            vertexConsumer.vertex(mat, x1, y1, z2).color(r, g, b, a).uv(sprite.getU(z1 * 16), sprite.getV(x1 * 16)).uv2(light).normal(0, -1, 0).endVertex();
            vertexConsumer.vertex(mat, x1, y1, z1).color(r, g, b, a).uv(sprite.getU(z2 * 16), sprite.getV(x1 * 16)).uv2(light).normal(0, -1, 0).endVertex();
            vertexConsumer.vertex(mat, x2, y1, z1).color(r, g, b, a).uv(sprite.getU(z2 * 16), sprite.getV(x2 * 16)).uv2(light).normal(0, -1, 0).endVertex();
            vertexConsumer.vertex(mat, x2, y1, z2).color(r, g, b, a).uv(sprite.getU(z1 * 16), sprite.getV(x2 * 16)).uv2(light).normal(0, -1, 0).endVertex();
        }
        if (renderNorth) {
            vertexConsumer.vertex(mat, x2, y1, z1).color(r, g, b, a).uv(sprite.getU(x1 * 16), sprite.getV(y1 * 16)).uv2(light).normal(0, 0, -1).endVertex();
            vertexConsumer.vertex(mat, x1, y1, z1).color(r, g, b, a).uv(sprite.getU(x2 * 16), sprite.getV(y1 * 16)).uv2(light).normal(0, 0, -1).endVertex();
            vertexConsumer.vertex(mat, x1, y2, z1).color(r, g, b, a).uv(sprite.getU(x2 * 16), sprite.getV(y2 * 16)).uv2(light).normal(0, 0, -1).endVertex();
            vertexConsumer.vertex(mat, x2, y2, z1).color(r, g, b, a).uv(sprite.getU(x1 * 16), sprite.getV(y2 * 16)).uv2(light).normal(0, 0, -1).endVertex();
        }
        if (renderSouth) {
            vertexConsumer.vertex(mat, x1, y1, z2).color(r, g, b, a).uv(sprite.getU(x1 * 16), sprite.getV(y1 * 16)).uv2(light).normal(0, 0, 1).endVertex();
            vertexConsumer.vertex(mat, x2, y1, z2).color(r, g, b, a).uv(sprite.getU(x2 * 16), sprite.getV(y1 * 16)).uv2(light).normal(0, 0, 1).endVertex();
            vertexConsumer.vertex(mat, x2, y2, z2).color(r, g, b, a).uv(sprite.getU(x2 * 16), sprite.getV(y2 * 16)).uv2(light).normal(0, 0, 1).endVertex();
            vertexConsumer.vertex(mat, x1, y2, z2).color(r, g, b, a).uv(sprite.getU(x1 * 16), sprite.getV(y2 * 16)).uv2(light).normal(0, 0, 1).endVertex();
        }
        if (renderWest) {
            vertexConsumer.vertex(mat, x1, y1, z1).color(r, g, b, a).uv(sprite.getU(z1 * 16), sprite.getV(y1 * 16)).uv2(light).normal(-1, 0, 0).endVertex();
            vertexConsumer.vertex(mat, x1, y1, z2).color(r, g, b, a).uv(sprite.getU(z2 * 16), sprite.getV(y1 * 16)).uv2(light).normal(-1, 0, 0).endVertex();
            vertexConsumer.vertex(mat, x1, y2, z2).color(r, g, b, a).uv(sprite.getU(z2 * 16), sprite.getV(y2 * 16)).uv2(light).normal(-1, 0, 0).endVertex();
            vertexConsumer.vertex(mat, x1, y2, z1).color(r, g, b, a).uv(sprite.getU(z1 * 16), sprite.getV(y2 * 16)).uv2(light).normal(-1, 0, 0).endVertex();
        }
        if (renderEast) {
            vertexConsumer.vertex(mat, x2, y1, z2).color(r, g, b, a).uv(sprite.getU(z1 * 16), sprite.getV(y1 * 16)).uv2(light).normal(1, 0, 0).endVertex();
            vertexConsumer.vertex(mat, x2, y1, z1).color(r, g, b, a).uv(sprite.getU(z2 * 16), sprite.getV(y1 * 16)).uv2(light).normal(1, 0, 0).endVertex();
            vertexConsumer.vertex(mat, x2, y2, z1).color(r, g, b, a).uv(sprite.getU(z2 * 16), sprite.getV(y2 * 16)).uv2(light).normal(1, 0, 0).endVertex();
            vertexConsumer.vertex(mat, x2, y2, z2).color(r, g, b, a).uv(sprite.getU(z1 * 16), sprite.getV(y2 * 16)).uv2(light).normal(1, 0, 0).endVertex();
        }
    }
}
