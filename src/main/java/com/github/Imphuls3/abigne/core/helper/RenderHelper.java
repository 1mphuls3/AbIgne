package com.github.Imphuls3.abigne.core.helper;

import com.github.Imphuls3.abigne.client.event.ClientModEvents;
import com.github.Imphuls3.abigne.core.registry.ShaderRegistry;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Mth;

import java.awt.*;
import java.util.Random;

import static com.github.Imphuls3.abigne.core.registry.ShaderRegistry.AbIgneRenderTypes.ADD;

public class RenderHelper {
    public static final int FULL_BRIGHT = 15728880;

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
        renderSprite(vertexConsumer, matrix, stack, sprite, scale, color.getRed(), color.getGreen(), color.getBlue(), alpha, brightness);
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
                                       float width, float height, Color color, float alpha, int brightness) {
        renderRectangle(vertexConsumer, matrix, stack, sprite, width, height, color.getRed(), color.getGreen(), color.getBlue(), alpha, brightness);
    }
}
