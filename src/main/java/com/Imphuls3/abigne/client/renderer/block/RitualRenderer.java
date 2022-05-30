package com.Imphuls3.abigne.client.renderer.block;

import com.Imphuls3.abigne.common.block.entity.CenterPedestalBlockEntity;
import com.Imphuls3.abigne.common.block.entity.CrucibleBlockEntity;
import com.Imphuls3.abigne.common.block.entity.PedestalBlockEntity;
import com.Imphuls3.abigne.common.ritual.Ritual;
import com.Imphuls3.abigne.core.init.BlockInit;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.common.Tags;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class RitualRenderer implements BlockEntityRenderer<CenterPedestalBlockEntity> {
    private final BlockEntityRendererProvider.Context context;

    public RitualRenderer(BlockEntityRendererProvider.Context context){
        this.context = context;
    }

    @Override
    public void render(CenterPedestalBlockEntity blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Level level = Minecraft.getInstance().level;
        final BlockRenderDispatcher dispatcher = this.context.getBlockRenderDispatcher();

        if (!blockEntityIn.inv.getStackInSlot(0).isEmpty()) {
            ItemStack stack = blockEntityIn.inv.getStackInSlot(0);
            stackIn.pushPose();
            float yDiff = Mth.sin((System.currentTimeMillis() % 86400000) / 1000F) * 0.1F + 0.1F;
            stackIn.translate(0.5D, 1.2D + yDiff, 0.5D);
            stackIn.mulPose(Vector3f.YP.rotationDegrees((level.getGameTime()) * 3 + partialTicks));
            stackIn.scale(0.5F, 0.5F, 0.5F);
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, stackIn, bufferIn, 0);
            stackIn.popPose();
        }
        stackIn.pushPose();
        stackIn.translate(0.5D, 2D, 0.5D);
        stackIn.mulPose(Vector3f.YP.rotationDegrees((level.getGameTime()) * 3 + partialTicks));
        stackIn.mulPose(Vector3f.XP.rotationDegrees(45));
        stackIn.mulPose(Vector3f.ZN.rotationDegrees(45));
        stackIn.scale(0.5F, 0.5F, 0.5F);
        dispatcher.renderSingleBlock(Blocks.GLASS.defaultBlockState(), stackIn, bufferIn, combinedLightIn, combinedOverlayIn,
                EmptyModelData.INSTANCE);
        stackIn.popPose();
    }
}
