package com.github.Imphuls3.abigne.client.renderer.block;

import com.github.Imphuls3.abigne.common.blockentity.FluidExtractor;
import com.github.Imphuls3.abigne.common.blockentity.PedestalBlockEntity;
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

public class FluidExtractorRenderer<T extends FluidExtractor> implements BlockEntityRenderer<T> {
    public FluidExtractorRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(FluidExtractor blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (!blockEntityIn.inventory.getStackInSlot(0).isEmpty()) {
            ItemStack stack = blockEntityIn.inventory.getStackInSlot(0);
            stackIn.pushPose();
            stackIn.translate(0.5D, 1.2D, 0.5D);
            stackIn.scale(0.5F, 0.5F, 0.5F);

            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, stackIn, bufferIn, 0);
            stackIn.popPose();
        }
    }
}
