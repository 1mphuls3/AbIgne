package com.Imphuls3.abigne.core.helper;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

public class RenderHelper {

    public static void renderSprite(VertexConsumer vertexConsumer, Matrix4f matrix, PoseStack stack, TextureAtlasSprite sprite,
                                              float scale, float r, float g, float b, float alpha, int brightness) {
        vertexConsumer.vertex(matrix, -scale, -scale, 0.0f).color(r, g, b, alpha)
                .uv(sprite.getU0(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, -scale, scale, 0.0f).color(r, g, b, alpha)
                .uv(sprite.getU0(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, scale, scale, 0.0f).color(r, g, b, alpha)
                .uv(sprite.getU1(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, scale, -scale, 0.0f).color(r, g, b, alpha)
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
        vertexConsumer.vertex(matrix, -width, -height, 0.0f).color(r, g, b, alpha)
                .uv(sprite.getU0(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, -width, height, 0.0f).color(r, g, b, alpha)
                .uv(sprite.getU0(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, width, height, 0.0f).color(r, g, b, alpha)
                .uv(sprite.getU1(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        vertexConsumer.vertex(matrix, width, -height, 0.0f).color(r, g, b, alpha)
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
}
