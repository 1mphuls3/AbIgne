package com.github.Imphuls3.abigne.client.renderer.block;

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

public class PedestalRenderer<T extends PedestalBlockEntity> implements BlockEntityRenderer<T> {

    public  PedestalRenderer(BlockEntityRendererProvider.Context context){
    }
    @Override
    public void render(PedestalBlockEntity blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Level level = Minecraft.getInstance().level;
        if (!blockEntityIn.inventory.getStackInSlot(0).isEmpty()) {
            ItemStack stack = blockEntityIn.inventory.getStackInSlot(0);
            stackIn.pushPose();
            float yDiff = Mth.sin((System.currentTimeMillis() % 86400000) / 850F) * 0.1F + 0.1F;
            stackIn.translate(0.5D, 1D + yDiff, 0.5D);
            stackIn.mulPose(Vector3f.YP.rotationDegrees((level.getGameTime()) * 3 + partialTicks));
            stackIn.scale(0.5F, 0.5F, 0.5F);

            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, stackIn, bufferIn, 0);
            stackIn.popPose();
        }
    }
/*
    @Override
    public void render(T blockEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);
        renderOrb(poseStack, Color.YELLOW, Color.CYAN, Color.YELLOW);
        poseStack.popPose();
    }

    private static final ResourceLocation TEST = prefix("textures/vfx/uv_test.png");
    public static final RenderType TEST_TYPE = RenderTypeRegistry.ADD.apply();

    public static void renderOrb(PoseStack poseStack, Color primaryColor, Color secondaryColor, Color trinaryColor) {
        poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180f));
        VFX.create().setPosColorTexLightmapDefaultFormat().setAlpha(0.5f).renderQuad(DELAYED_RENDER.getBuffer(TEST_TYPE), poseStack, 1);
    }*/
}
