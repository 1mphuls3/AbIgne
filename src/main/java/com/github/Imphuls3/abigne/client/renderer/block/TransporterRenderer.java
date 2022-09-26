package com.github.Imphuls3.abigne.client.renderer.block;

import com.github.Imphuls3.abigne.common.blockentity.ItemTransporterBlockEntity;
import com.github.Imphuls3.abigne.core.Easing;
import com.github.Imphuls3.abigne.core.helper.VecHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class TransporterRenderer<T extends ItemTransporterBlockEntity> implements BlockEntityRenderer<T> {
    int i;
    int k;
    public TransporterRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(T blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
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
        if(blockEntityIn.bound != null/* && blockEntityIn.canTransfer()*/){
            Vec3 pos = VecHelper.vec3FromBlockPos(blockEntityIn.getBlockPos());
            pos = pos.add(0.5, 1.5, 0.5);
            Vec3 pos2 = VecHelper.vec3FromBlockPos(blockEntityIn.bound.getBlockPos());
            pos2 = pos2.add(0.5, 1.5, 0.5);
            if(k == 5) {
                if(i <= 25) {
                    i++;
                    Vec3 pos3 = VecHelper.easedLerpY(Easing.LINEAR, i/25F, pos, pos2);
                    level.addAlwaysVisibleParticle(ParticleTypes.FLAME, pos3.x, pos3.y, pos3.z, 0, 0.01, 0);
                } else {
                    i = 0;
                }
                k = 0;
            } else {
                k++;
            }
        }
    }
}
