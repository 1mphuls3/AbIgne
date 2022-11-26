package com.github.Imphuls3.abigne.client.renderer.block;

import com.github.Imphuls3.abigne.common.block.AlembicBlock;
import com.github.Imphuls3.abigne.common.blockentity.AlembicBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class AlembicRenderer<T extends AlembicBlockEntity> implements BlockEntityRenderer<T> {
    int i = 0;
    public AlembicRenderer(BlockEntityRendererProvider.Context context){
    }
    @Override
    public void render(T blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Level level = blockEntityIn.getLevel();
        BlockPos pos = blockEntityIn.getBlockPos();
        BlockState state = level.getBlockState(pos);
        double offsetX = 0;
        double offsetZ = 0;
        Direction direction = Direction.NORTH;
        if(state.getBlock() instanceof AlembicBlock) {
            direction = state.getValue(AlembicBlock.FACING);
            direction = direction.getClockWise();
            offsetX = direction.getStepX()/4D - direction.getStepX()/16F;
            offsetZ = direction.getStepZ()/4D - direction.getStepZ()/16F;
        }
        if (!blockEntityIn.inventory.getStackInSlot(0).isEmpty()) {
            ItemStack stack = blockEntityIn.inventory.getStackInSlot(0);
            stackIn.pushPose();
            stackIn.translate(0.5D + offsetX, 0.5D, 0.5D + offsetZ);
            stackIn.mulPose(direction.getClockWise().getRotation());
            stackIn.scale(0.5F, 0.5F, 0.5F);
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, stackIn, bufferIn, 0);
            stackIn.popPose();
        }
    }
}
