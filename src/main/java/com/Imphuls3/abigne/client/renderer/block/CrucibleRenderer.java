package com.Imphuls3.abigne.client.renderer.block;

import com.Imphuls3.abigne.common.block.entity.CrucibleBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class CrucibleRenderer implements BlockEntityRenderer<CrucibleBlockEntity> {

    public CrucibleRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(CrucibleBlockEntity blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Level level = Minecraft.getInstance().level;
        int numSlotsInUse = 0;
        for (int i = 0; i < 8; i++) {
            if(!blockEntityIn.inventory.getStackInSlot(i).isEmpty()){
                numSlotsInUse++;
            }
        }
        for(int k = 0; k < numSlotsInUse; k++){
            ItemStack stack = blockEntityIn.inventory.getStackInSlot(k);
            double size = 0.75;
            double xDiff = Math.cos((((double) level.getGameTime() + partialTicks) / 8) + (k * (2 * Math.PI) / numSlotsInUse)) * (size);
            double zDiff = Math.sin((((double) level.getGameTime() + partialTicks) / 8) + (k * (2 * Math.PI) / numSlotsInUse)) * (size);
            /*double ring = Math.sin((((double) level.getGameTime() + partialTicks) / 8) + (k * (2 * Math.PI) / 8)) * (0.25);*/
            //causes an angled ring, maybe useful for something else?
            double yDiff = -Math.sin((((double) level.getGameTime() + partialTicks) / 9) + ((2 * Math.PI) / 8)) * (0.11);

            if (!blockEntityIn.inventory.getStackInSlot(k).isEmpty()) {
                stackIn.pushPose();
                stackIn.translate(0.5D + xDiff, 1.4D + yDiff, 0.5D + zDiff);
                stackIn.mulPose(Vector3f.YP.rotationDegrees((level.getGameTime()) * 3 + partialTicks));
                stackIn.scale(0.35F, 0.35F, 0.35F);
                Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, stackIn, bufferIn, 0);
                stackIn.popPose();
            }
        }
        if(blockEntityIn.getIgnis() > 0){
            ParticleUtils.spawnParticleOnFace(level, blockEntityIn.getBlockPos(), Direction.UP, ParticleTypes.FLAME);
            /*level.addParticle(ParticleTypes.FLAME, blockEntityIn.getBlockPos().getX(), blockEntityIn.getBlockPos().getY(), blockEntityIn.getBlockPos().getZ(),
                    0, 0, 0);*/
        }
    }
}
