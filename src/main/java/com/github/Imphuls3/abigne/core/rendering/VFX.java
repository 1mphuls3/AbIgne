package com.github.Imphuls3.abigne.core.rendering;

import com.github.Imphuls3.abigne.core.helper.RenderHelper;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

public class VFX {
    public static VFX create() {
        return new VFX();
    }

    float r = 1, g = 1, b = 1, a = 1;
    float xOffset = 0, yOffset = 0, zOffset = 0;
    int light = RenderHelper.FULL_BRIGHT;
    float u0 = 0, v0 = 0, u1 = 1, v1 = 1;

    VertexFormat format;
    WorldVertexPlacementSupplier supplier;

    public VFX setPosColorDefaultFormat() {
        return setVertexSupplier((c, l, x, y, z, u, v) -> {
            if (l == null)
                c.vertex(x, y, z).color(this.r, this.g, this.b, this.a).endVertex();
            else
                c.vertex(l, x, y, z).color(this.r, this.g, this.b, this.a).endVertex();
        }).setFormat(DefaultVertexFormat.POSITION_COLOR);
    }

    public VFX setPosColorLightmapDefaultFormat() {
        return setVertexSupplier((c, l, x, y, z, u, v) -> {
            if (l == null)
                c.vertex(x, y, z).color(this.r, this.g, this.b, this.a).uv2(this.light).endVertex();
            else
                c.vertex(l, x, y, z).color(this.r, this.g, this.b, this.a).uv2(this.light).endVertex();

        }).setFormat(DefaultVertexFormat.POSITION_COLOR_LIGHTMAP);
    }

    public VFX setPosTexDefaultFormat() {
        return setVertexSupplier((c, l, x, y, z, u, v) -> {
            if (l == null)
                c.vertex(x, y, z).uv(u, v).endVertex();
            else
                c.vertex(l, x, y, z).uv(u, v).endVertex();
        }).setFormat(DefaultVertexFormat.POSITION_TEX);
    }

    public VFX setPosColorTexDefaultFormat() {
        return setVertexSupplier((c, l, x, y, z, u, v) -> {
            if (l == null)
                c.vertex(x, y, z).color(this.r, this.g, this.b, this.a).uv(u, v).endVertex();
            else
                c.vertex(l, x, y, z).color(this.r, this.g, this.b, this.a).uv(u, v).endVertex();
        }).setFormat(DefaultVertexFormat.POSITION_COLOR_TEX);
    }

    public VFX setPosColorTexLightmapDefaultFormat() {
        return setVertexSupplier((c, l, x, y, z, u, v) -> {
            if (l == null)
                c.vertex(x, y, z).color(this.r, this.g, this.b, this.a).uv(u, v).uv2(this.light).endVertex();
            else
                c.vertex(l, x, y, z).color(this.r, this.g, this.b, this.a).uv(u, v).uv2(this.light).endVertex();
        }).setFormat(DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP);
    }

    public VFX setFormat(VertexFormat format) {
        this.format = format;
        return this;
    }

    public VFX setVertexSupplier(WorldVertexPlacementSupplier supplier) {
        this.supplier = supplier;
        return this;
    }

    public VFX setColor(Color color) {
        return setColor(color.getRed(), color.getGreen(), color.getBlue());
    }

    public VFX setColor(Color color, float a) {
        return setColor(color).setAlpha(a);
    }

    public VFX setColor(float r, float g, float b, float a) {
        return setColor(r, g, b).setAlpha(a);
    }

    public VFX setColor(float r, float g, float b) {
        this.r = r / 255f;
        this.g = g / 255f;
        this.b = b / 255f;
        return this;
    }

    public VFX setAlpha(float a) {
        this.a = a;
        return this;
    }

    public VFX setOffset(float xOffset, float yOffset, float zOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        return this;
    }

    public VFX setLight(int light) {
        this.light = light;
        return this;
    }

    public VFX setUV(float u0, float v0, float u1, float v1) {
        this.u0 = u0;
        this.v0 = v0;
        this.u1 = u1;
        this.v1 = v1;
        return this;
    }

    public VFX renderBeam(VertexConsumer vertexConsumer, PoseStack stack, Vec3 start, Vec3 end, float width) {
        Minecraft minecraft = Minecraft.getInstance();
        start.add(xOffset, yOffset, zOffset);
        end.add(xOffset, yOffset, zOffset);
        stack.translate(-start.x, -start.y, -start.z);
        Vec3 cameraPosition = minecraft.getBlockEntityRenderDispatcher().camera.getPosition();
        Vec3 delta = end.subtract(start);
        Vec3 normal = start.subtract(cameraPosition).cross(delta).normalize().multiply(width / 2f, width / 2f, width / 2f);
        Matrix4f last = stack.last().pose();
        Vec3[] positions = new Vec3[]{start.subtract(normal), start.add(normal), end.add(normal), end.subtract(normal)};

        supplier.placeVertex(vertexConsumer, last, (float) positions[0].x, (float) positions[0].y, (float) positions[0].z, u0, v1);
        supplier.placeVertex(vertexConsumer, last, (float) positions[1].x, (float) positions[1].y, (float) positions[1].z, u1, v1);
        supplier.placeVertex(vertexConsumer, last, (float) positions[2].x, (float) positions[2].y, (float) positions[2].z, u1, v0);
        supplier.placeVertex(vertexConsumer, last, (float) positions[3].x, (float) positions[3].y, (float) positions[3].z, u0, v0);
        stack.translate(start.x, start.y, start.z);
        return this;
    }

    public VFX renderQuad(VertexConsumer vertexConsumer, PoseStack stack, float size) {
        return renderQuad(vertexConsumer, stack, size, size);
    }

    public VFX renderQuad(VertexConsumer vertexConsumer, PoseStack stack, float width, float height) {
        Vector3f[] positions = new Vector3f[]{new Vector3f(-1, -1, 0), new Vector3f(1, -1, 0), new Vector3f(1, 1, 0), new Vector3f(-1, 1, 0)};
        return renderQuad(vertexConsumer, stack, positions, width, height);
    }

    public VFX renderQuad(VertexConsumer vertexConsumer, PoseStack stack, Vector3f[] positions, float size) {
        return renderQuad(vertexConsumer, stack, positions, size, size);
    }

    public VFX renderQuad(VertexConsumer vertexConsumer, PoseStack stack, Vector3f[] positions, float width, float height) {
        Matrix4f last = stack.last().pose();
        stack.translate(xOffset, yOffset, zOffset);
        for (Vector3f position : positions) {
            position.mul(width, height, width);
        }
        supplier.placeVertex(vertexConsumer, last, positions[0].x(), positions[0].y(), positions[0].z(), u0, v1);
        supplier.placeVertex(vertexConsumer, last, positions[1].x(), positions[1].y(), positions[1].z(), u1, v1);
        supplier.placeVertex(vertexConsumer, last, positions[2].x(), positions[2].y(), positions[2].z(), u1, v0);
        supplier.placeVertex(vertexConsumer, last, positions[3].x(), positions[3].y(), positions[3].z(), u0, v0);
        stack.translate(-xOffset, -yOffset, -zOffset);
        return this;
    }
    public VFX renderScreenSpaceQuad(VertexConsumer vertexConsumer, PoseStack stack, float size) {
        return renderScreenSpaceQuad(vertexConsumer, stack, size, size);
    }

    public VFX renderScreenSpaceQuad(VertexConsumer vertexConsumer, PoseStack stack, float width, float height) {
        Vector3f[] positions = new Vector3f[]{new Vector3f(-1, -1, 0), new Vector3f(1, -1, 0), new Vector3f(1, 1, 0), new Vector3f(-1, 1, 0)};
        return renderScreenSpaceQuad(vertexConsumer, stack, positions, width, height);
    }

    public VFX renderScreenSpaceQuad(VertexConsumer vertexConsumer, PoseStack stack, Vector3f[] positions, float size) {
        return renderScreenSpaceQuad(vertexConsumer, stack, positions, size, size);
    }

    public VFX renderScreenSpaceQuad(VertexConsumer vertexConsumer, PoseStack stack, Vector3f[] positions, float width, float height) {
        Matrix4f last = stack.last().pose();
        stack.translate(xOffset, yOffset, zOffset);
        for (Vector3f position : positions) {
            position.mul(width, height, width);
            position.transform(stack.last().normal());
        }
        supplier.placeVertex(vertexConsumer, last, positions[0].x(), positions[0].y(), positions[0].z(), u0, v1);
        supplier.placeVertex(vertexConsumer, last, positions[1].x(), positions[1].y(), positions[1].z(), u1, v1);
        supplier.placeVertex(vertexConsumer, last, positions[2].x(), positions[2].y(), positions[2].z(), u1, v0);
        supplier.placeVertex(vertexConsumer, last, positions[3].x(), positions[3].y(), positions[3].z(), u0, v0);
        stack.translate(-xOffset, -yOffset, -zOffset);
        return this;
    }

    public VFX renderSphere(VertexConsumer vertexConsumer, PoseStack stack, float radius, int longs, int lats) {
        Matrix4f last = stack.last().pose();
        float startU = 0;
        float startV = 0;
        float endU = Mth.PI * 2;
        float endV = Mth.PI;
        float stepU = (endU - startU) / longs;
        float stepV = (endV - startV) / lats;
        for (int i = 0; i < longs; ++i) {
            // U-points
            for (int j = 0; j < lats; ++j) {
                // V-points
                float u = i * stepU + startU;
                float v = j * stepV + startV;
                float un = (i + 1 == longs) ? endU : (i + 1) * stepU + startU;
                float vn = (j + 1 == lats) ? endV : (j + 1) * stepV + startV;
                Vector3f p0 = RenderHelper.parametricSphere(u, v, radius);
                Vector3f p1 = RenderHelper.parametricSphere(u, vn, radius);
                Vector3f p2 = RenderHelper.parametricSphere(un, v, radius);
                Vector3f p3 = RenderHelper.parametricSphere(un, vn, radius);

                float textureU = u / endU * radius;
                float textureV = v / endV * radius;
                float textureUN = un / endU * radius;
                float textureVN = vn / endV * radius;
                RenderHelper.vertexPosColorUVLight(vertexConsumer, last, p0.x(), p0.y(), p0.z(), r, g, b, a, textureU, textureV, light);
                RenderHelper.vertexPosColorUVLight(vertexConsumer, last, p2.x(), p2.y(), p2.z(), r, g, b, a, textureUN, textureV, light);
                RenderHelper.vertexPosColorUVLight(vertexConsumer, last, p1.x(), p1.y(), p1.z(), r, g, b, a, textureU, textureVN, light);

                RenderHelper.vertexPosColorUVLight(vertexConsumer, last, p3.x(), p3.y(), p3.z(), r, g, b, a, textureUN, textureVN, light);
                RenderHelper.vertexPosColorUVLight(vertexConsumer, last, p1.x(), p1.y(), p1.z(), r, g, b, a, textureU, textureVN, light);
                RenderHelper.vertexPosColorUVLight(vertexConsumer, last, p2.x(), p2.y(), p2.z(), r, g, b, a, textureUN, textureV, light);
            }
        }
        return this;
    }


    public interface WorldVertexPlacementSupplier {
        void placeVertex(VertexConsumer consumer, Matrix4f last, float x, float y, float z, float u, float v);
    }
}
