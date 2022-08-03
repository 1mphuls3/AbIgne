package com.Imphuls3.abigne.client.renderer.block;

import com.Imphuls3.abigne.common.blockentity.InfuserBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class InfuserRenderer implements BlockEntityRenderer<InfuserBlockEntity> {

    public InfuserRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(InfuserBlockEntity blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Level level = Minecraft.getInstance().level;

        if (!blockEntityIn.inventory.getStackInSlot(0).isEmpty()) {
            ItemStack stack = blockEntityIn.inventory.getStackInSlot(0);
            stackIn.pushPose();
            float yDiff = Mth.sin((System.currentTimeMillis() % 86400000) / 1000F) * 0.1F + 0.1F;
            stackIn.translate(0.5D, 1.2D + yDiff, 0.5D);
            stackIn.mulPose(Vector3f.YP.rotationDegrees((level.getGameTime()) * 3 + partialTicks));
            stackIn.scale(0.5F, 0.5F, 0.5F);
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, stackIn, bufferIn, 0);
            stackIn.popPose();
        }
        if (!blockEntityIn.inventory.getStackInSlot(1).isEmpty()) {
            ItemStack stack = blockEntityIn.inventory.getStackInSlot(1);
            stackIn.pushPose();
            stackIn.translate(0.5D, 1.2D, 0.5D);
            stackIn.scale(0.8F, 0.25F, 0.8F);
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, stackIn, bufferIn, 0);
            stackIn.popPose();
        }
    }
}
