package com.github.Imphuls3.abigne.client.renderer.block;

import com.github.Imphuls3.abigne.common.block.AlembicBlock;
import com.github.Imphuls3.abigne.common.blockentity.AlembicBlockEntity;
import com.github.Imphuls3.abigne.common.blockentity.PedestalBlockEntity;
import com.github.Imphuls3.abigne.core.helper.RenderHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class AlembicRenderer<T extends AlembicBlockEntity> implements BlockEntityRenderer<T> {

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
            stackIn.pushPose();
            stackIn.translate(0.5D, 1D, 0.5D);
            renderFluid(stackIn, bufferIn, new FluidStack(Fluids.WATER, 1000), 25);
            stackIn.popPose();
        }
    }

    private static void renderFluid(PoseStack stack, MultiBufferSource buffer, FluidStack fluidStack, float alpha){
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.translucent());
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(fluidStack.getFluid().getAttributes().getStillTexture(fluidStack));
        int color = fluidStack.getFluid().getAttributes().getColor(fluidStack);
        alpha *= (color >> 24 & 255) / 255f;
        float red = (color >> 16 & 255) / 255f;
        float green = (color >> 8 & 255) / 255f;
        float blue = (color & 255) / 255f;

        RenderHelper.renderSprite(vertexConsumer, stack.last().pose(), stack, sprite, 0.6F, red, green, blue, alpha, LightTexture.FULL_BRIGHT);
    }
}
