package com.github.Imphuls3.abigne.client.renderer.block;

import com.github.Imphuls3.abigne.common.blockentity.FluidEmitterBlockEntity;
import com.github.Imphuls3.abigne.core.helper.RenderHelper;
import com.github.Imphuls3.abigne.core.registry.RenderTypeRegistry;
import com.github.Imphuls3.abigne.core.registry.ShaderRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;

public class FluidEmitterRenderer<T extends FluidEmitterBlockEntity> implements BlockEntityRenderer<T> {

    public FluidEmitterRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(T blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        VertexConsumer vertexConsumer = bufferIn.getBuffer(RenderTypeRegistry.ADD);
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(TextureRenderer.WHITE);
        Level level = Minecraft.getInstance().level;
        if (blockEntityIn.tank.getFluid() != FluidStack.EMPTY) {
            FluidStack fluidStack = blockEntityIn.tank.getFluid();
            IClientFluidTypeExtensions properties = IClientFluidTypeExtensions.of(fluidStack.getFluid());
            TextureAtlasSprite fluidSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(properties.getStillTexture());
            Color color;
            if(fluidStack.getFluid() instanceof WaterFluid) {
                color = new Color(BiomeColors.getAverageWaterColor(level, blockEntityIn.getBlockPos()));
            } else {
                color = new Color(properties.getTintColor());
            }
            stackIn.pushPose();
            stackIn.translate(0.5D, 0.375D, 0.005);
            for (int i = 0; i < 4; i++) {
                stackIn.scale(0.5F, 0.5F, 0.5F);
                RenderHelper.renderSprite(vertexConsumer, stackIn.last().pose(), stackIn, sprite, 0.25F, color, 0.8F, LightTexture.FULL_BRIGHT);
                stackIn.scale(2F, 2F, 2F);
                stackIn.mulPose(Vector3f.YP.rotationDegrees(90));
                stackIn.translate(-8.05/16F, 0, -8.05/16F);
            }
            stackIn.popPose();
        }
    }
}
