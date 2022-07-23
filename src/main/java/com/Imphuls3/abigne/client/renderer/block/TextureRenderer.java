package com.Imphuls3.abigne.client.renderer.block;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.client.postprocess.ModPostProcessing;
import com.Imphuls3.abigne.client.renderer.CustomRenderType;
import com.Imphuls3.abigne.common.block.InfuserBlock;
import com.Imphuls3.abigne.common.block.entity.InfuserBlockEntity;
import com.lowdragmc.shimmer.client.postprocessing.PostProcessing;
import com.lowdragmc.shimmer.client.shader.RenderUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class TextureRenderer implements BlockEntityRenderer<InfuserBlockEntity> {
    public static final ResourceLocation HALO = new ResourceLocation(AbIgne.MODID, "effect/circle");

    public TextureRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(InfuserBlockEntity blockEntity, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlay) {
        int brightness = LightTexture.FULL_BRIGHT;

        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(HALO);

        stackIn.pushPose();

        stackIn.translate(0.5, 0.02, 0.5);

        stackIn.mulPose(Quaternion.fromXYZ(new Vector3f(1.5708F, 0.0F, 0.0F)));

        PostProcessing effect = PostProcessing.BLOOM_UNREAL;

        PoseStack finalStack = RenderUtils.copyPoseStack(stackIn);
        Matrix4f matrix = finalStack.last().pose();

        float alpha = blockEntity.getAlpha();
        float red = blockEntity.getRed();
        float green = blockEntity.getGreen();
        float blue = blockEntity.getBlue();
        float scale = blockEntity.getScale();

        effect.postEntity(buffer -> {
            VertexConsumer vertexConsumer = buffer.getBuffer(CustomRenderType.ADD);
            vertexConsumer.vertex(matrix, -scale, -scale, 0.0f).color(red, green, blue, alpha)
                    .uv(sprite.getU0(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex();
            vertexConsumer.vertex(matrix, -scale, scale, 0.0f).color(red, green, blue, alpha)
                    .uv(sprite.getU0(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
            vertexConsumer.vertex(matrix, scale, scale, 0.0f).color(red, green, blue, alpha)
                    .uv(sprite.getU1(), sprite.getV1()).uv2(brightness).normal(1,0,0).endVertex();
            vertexConsumer.vertex(matrix, scale, -scale, 0.0f).color(red, green, blue, alpha)
                    .uv(sprite.getU1(), sprite.getV0()).uv2(brightness).normal(1,0,0).endVertex(); });
        stackIn.popPose();

        Level level = Minecraft.getInstance().level;
        if (!blockEntity.inventory.getStackInSlot(0).isEmpty()) {
            ItemStack stack = blockEntity.inventory.getStackInSlot(0);
            stackIn.pushPose();
            float yDiff = Mth.sin((System.currentTimeMillis() % 86400000) / 850F) * 0.1F + 0.1F;
            stackIn.translate(0.5D, 0.4D + yDiff, 0.5D);
            stackIn.mulPose(Vector3f.YP.rotationDegrees((level.getGameTime()) * 3 + partialTicks));
            stackIn.scale(0.5F, 0.5F, 0.5F);

            PostProcessing effect2 = ModPostProcessing.CHROMATIC_ABBERATION;

            PoseStack finalStack2 = RenderUtils.copyPoseStack(stackIn);
            effect2.postEntity(buffer -> {
                Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, finalStack2, buffer, 0);
            });
            stackIn.popPose();
        }
    }
}
