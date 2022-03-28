package com.Imphuls3.abigne.client.renderer.block;

import ca.weblite.objc.Client;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.Imphuls3.abigne.common.block.entity.PedestalBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class PedestalRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    private final BlockEntityRendererProvider.Context context;

    public PedestalRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(PedestalBlockEntity blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        Level level = Minecraft.getInstance().level;

        if (!blockEntityIn.getItem().isEmpty()) {
            stackIn.pushPose();
            stackIn.translate(0.5f, 1.3f, 0.5f);
            stackIn.mulPose(Vector3f.YP.rotationDegrees((level.getGameTime()) * 3 + partialTicks));
            stackIn.scale(0.6f, 0.6f, 0.6f);

            renderer.renderStatic(blockEntityIn.getItem(), ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, stackIn, bufferIn, 0);

            stackIn.popPose();
        }
    }
}

