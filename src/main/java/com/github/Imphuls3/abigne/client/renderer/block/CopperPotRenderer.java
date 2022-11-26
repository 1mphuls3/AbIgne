package com.github.Imphuls3.abigne.client.renderer.block;

import com.github.Imphuls3.abigne.common.blockentity.CopperPotBlockEntity;
import com.github.Imphuls3.abigne.common.blockentity.TankBlockEntity;
import com.github.Imphuls3.abigne.core.helper.RenderHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.awt.*;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class CopperPotRenderer<T extends CopperPotBlockEntity> implements BlockEntityRenderer<T> {

    public CopperPotRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(T blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Level level = Minecraft.getInstance().level;
        if (!blockEntityIn.inventory.getStackInSlot(0).isEmpty()) {
            float scale = blockEntityIn.inventory.getStackInSlot(0).getItem() instanceof BlockItem ? 1F : 0.5F;
            ItemStack stack = blockEntityIn.inventory.getStackInSlot(0);
            stackIn.pushPose();
            stackIn.translate(0.5D, 0.5D, 0.5D);
            stackIn.scale(scale, scale, scale);
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, stackIn, bufferIn, 0);
            stackIn.popPose();
        }
        if (blockEntityIn.tank.getFluid() != FluidStack.EMPTY || blockEntityIn.tank2.getFluid() != FluidStack.EMPTY) {
            FluidStack fluidStack = blockEntityIn.tank.getFluid();
            FluidStack fluidStack2 = blockEntityIn.tank2.getFluid();
            float offset1 = ((float)blockEntityIn.tank.getFluid().getAmount()/(blockEntityIn.tank.getFluid().getAmount()+blockEntityIn.tank2.getFluid().getAmount()))*(10/16F);
            float offset2 = ((float)blockEntityIn.tank2.getFluid().getAmount()/(blockEntityIn.tank.getFluid().getAmount()+blockEntityIn.tank2.getFluid().getAmount()))*(10/16F);
            IClientFluidTypeExtensions properties = IClientFluidTypeExtensions.of(fluidStack.getFluid());
            IClientFluidTypeExtensions properties2 = IClientFluidTypeExtensions.of(fluidStack2.getFluid());
            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(properties.getStillTexture());
            TextureAtlasSprite sprite2 = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(properties2.getStillTexture());
            VertexConsumer vertexConsumer = bufferIn.getBuffer(RenderType.translucent());
            Color color = new Color(properties.getTintColor());
            Color color2 = new Color(properties2.getTintColor());
            if(fluidStack.getFluid() instanceof WaterFluid) color = new Color(BiomeColors.getAverageWaterColor(level, blockEntityIn.getBlockPos()));
            if(fluidStack2.getFluid() instanceof WaterFluid) color2 = new Color(BiomeColors.getAverageWaterColor(level, blockEntityIn.getBlockPos()));

            stackIn.pushPose();
            RenderHelper.cube(stackIn, vertexConsumer, sprite, 3/16F, 0, 3/16F, offset1 + 3/16F, 15/16F, 13/16F,
                    color.getRed(), color.getGreen(), color.getBlue(), !fluidStack.isEmpty() ? color.getAlpha() : 0, combinedLightIn,
                    false, false, false, true, false, false);
            stackIn.mulPose(Vector3f.YP.rotationDegrees(180));
            stackIn.translate(-1, 0, -1);
            RenderHelper.cube(stackIn, vertexConsumer, sprite2, 3/16F, 0, 3/16F, offset2 + 3/16F, 15/16F, 13/16F,
                    color2.getRed(), color2.getGreen(), color2.getBlue(), !fluidStack2.isEmpty() ? color2.getAlpha() : 0, combinedLightIn,
                    false, false, false, true, false, false);
            stackIn.popPose();
        }
    }
}
