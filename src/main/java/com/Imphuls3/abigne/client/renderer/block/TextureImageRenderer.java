package com.Imphuls3.abigne.client.renderer.block;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.client.renderer.CustomRenderType;
import com.Imphuls3.abigne.common.block.entity.InfuserBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;

public class TextureImageRenderer implements BlockEntityRenderer<BlockEntity> {
    public static final ResourceLocation HALO = new ResourceLocation(AbIgne.MODID, "effect/halo");

    public TextureImageRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(BlockEntity blockEntity, float pPartialTick, PoseStack stackIn, MultiBufferSource bufferIn, int pPackedLight, int pPackedOverlay) {
        int brightness = LightTexture.FULL_BRIGHT;
        float scale = 1f;

        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(HALO);

        stackIn.pushPose();

        stackIn.translate(0.5, 1, 0.5);

        stackIn.mulPose(Quaternion.fromXYZ(new Vector3f(1.5708F, 0.0F, 0.0F)));

        VertexConsumer buffer = bufferIn.getBuffer(CustomRenderType.ADD);
        Matrix4f matrix = stackIn.last().pose();

        buffer.vertex(matrix, -scale, -scale, 0.0f).color(1.0f, 0.5f, 0.5f, 0.4f)
                .uv(sprite.getU0(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
        buffer.vertex(matrix, -scale, scale, 0.0f).color(1.0f, 0.5f, 0.5f, 0.4f)
                .uv(sprite.getU0(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        buffer.vertex(matrix, scale, scale, 0.0f).color(1.0f, 0.5f, 0.5f, 0.4f)
                .uv(sprite.getU1(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
        buffer.vertex(matrix, scale, -scale, 0.0f).color(1.0f, 0.5f, 0.5f, 0.4f)
                .uv(sprite.getU1(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
        stackIn.popPose();
    }
}
