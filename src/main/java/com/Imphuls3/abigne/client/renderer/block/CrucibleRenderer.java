package com.Imphuls3.abigne.client.renderer.block;

import com.Imphuls3.abigne.common.block.entity.CrucibleBlockEntity;
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

public class CrucibleRenderer implements BlockEntityRenderer<CrucibleBlockEntity> {

    public CrucibleRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(CrucibleBlockEntity blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
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
        for(double i = 0; i < 8; i++){
            float time = blockEntityIn.getLevel().getGameTime() + partialTicks;
            ItemStack stack = blockEntityIn.extrasInventory.getStackInSlot((int)i);
            if (!blockEntityIn.extrasInventory.getStackInSlot((int)i).isEmpty()) {
                stackIn.pushPose();
                float yDiff = Mth.sin((System.currentTimeMillis() % 86400000) / 1000F) * 0.1F + 0.1F;
                stackIn.translate(0.5D + (Math.sin(0.8 * i) / 2.75), 1.2D + yDiff, 0.5D + (Math.cos(0.8 * i) / 2.75));
                stackIn.mulPose(Vector3f.YP.rotationDegrees((level.getGameTime()) * 3 + partialTicks));
                stackIn.scale(0.35F, 0.35F, 0.35F);
                Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, stackIn, bufferIn, 0);
                stackIn.popPose();
            }
        }
    }
}