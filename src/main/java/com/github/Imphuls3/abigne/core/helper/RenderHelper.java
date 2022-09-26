package com.github.Imphuls3.abigne.core.helper;

import com.github.Imphuls3.abigne.client.event.ClientModEvents;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
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

    public static void vertexPos(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z) {
        vertexConsumer.vertex(last, x, y, z).endVertex();
    }

    public static void vertexPosUV(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float u, float v) {
        vertexConsumer.vertex(last, x, y, z).uv(u, v).endVertex();
    }

    public static void vertexPosUVLight(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float u, float v, int light) {
        vertexConsumer.vertex(last, x, y, z).uv(u, v).uv2(light).endVertex();
    }

    public static void vertexPosColor(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float r, float g, float b, float a) {
        vertexConsumer.vertex(last, x, y, z).color(r, g, b, a).endVertex();
    }

    public static void vertexPosColorUV(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float r, float g, float b, float a, float u, float v) {
        vertexConsumer.vertex(last, x, y, z).color(r, g, b, a).uv(u, v).endVertex();
    }

    public static void vertexPosColorUVLight(VertexConsumer vertexConsumer, Matrix4f last, float x, float y, float z, float r, float g, float b, float a, float u, float v, int light) {
        vertexConsumer.vertex(last, x, y, z).color(r, g, b, a).uv(u, v).uv2(light).endVertex();
    }

    private static final float ROOT_3 = (float)(Math.sqrt(3.0D) / 2.0D);

    public static void dragon(PoseStack mStack, MultiBufferSource buf, double x, double y, double z, float radius, float r, float g, float b) {
        float f5 = 0.5f; // max number of beams
        float f7 = Math.min(f5 > 0.8F ? (f5 - 0.8F) / 0.2F : 0.0F, 1.0F);
        Random random = new Random(432L);
        VertexConsumer builder = buf.getBuffer(ADD);
        mStack.pushPose();
        mStack.translate(x, y, z);

        float rotation = (float)(ClientModEvents.getClientTicks() / 200);

        for(int i = 0; (float)i < (f5 + f5 * f5) / 2.0F * 60.0F; ++i) {
            mStack.mulPose(Vector3f.XP.rotationDegrees(random.nextFloat() * 360.0F));
            mStack.mulPose(Vector3f.YP.rotationDegrees(random.nextFloat() * 360.0F));
            mStack.mulPose(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.0F));
            mStack.mulPose(Vector3f.XP.rotationDegrees(random.nextFloat() * 360.0F));
            mStack.mulPose(Vector3f.YP.rotationDegrees(random.nextFloat() * 360.0F));
            mStack.mulPose(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.0F + rotation * 90.0F));
            float f3 = random.nextFloat() * 20.0F + 5.0F + f7 * 10.0F;
            float f4 = random.nextFloat() * 2.0F + 1.0F + f7 * 2.0F;
            f3 *= 0.05f * radius;
            f4 *= 0.05f * radius;
            Matrix4f mat = mStack.last().pose();
            float alpha = 1 - f7;

            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).endVertex();
            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).endVertex();
            builder.vertex(mat, -ROOT_3 * f4, f3, -0.5F * f4).color(r, g, b, 0).endVertex();
            builder.vertex(mat, ROOT_3 * f4, f3, -0.5F * f4).color(r, g, b, 0).endVertex();
            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).endVertex();
            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).endVertex();
            builder.vertex(mat, ROOT_3 * f4, f3, -0.5F * f4).color(r, g, b, 0).endVertex();
            builder.vertex(mat, 0.0F, f3, 1.0F * f4).color(r, g, b, 0).endVertex();
            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).endVertex();
            builder.vertex(mat, 0.0F, 0.0F, 0.0F).color(r, g, b, alpha).endVertex();
            builder.vertex(mat, 0.0F, f3, 1.0F * f4).color(r, g, b, 0).endVertex();
            builder.vertex(mat, -ROOT_3 * f4, f3, -0.5F * f4).color(r, g, b, 0).endVertex();
        }

        mStack.popPose();
    }

    public static Vector3f parametricSphere(float u, float v, float r) {
        return new Vector3f(Mth.cos(u) * Mth.sin(v) * r, Mth.cos(v) * r, Mth.sin(u) * Mth.sin(v) * r);
    }
}
