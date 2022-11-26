package com.github.Imphuls3.abigne.client.renderer.block;

import com.github.Imphuls3.abigne.common.block.TankBlock;
import com.github.Imphuls3.abigne.common.blockentity.TankBlockEntity;
import com.github.Imphuls3.abigne.core.helper.RenderHelper;
import com.github.Imphuls3.abigne.core.registry.ShaderRegistry;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

import java.awt.*;

import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;
import static net.minecraft.client.renderer.texture.OverlayTexture.v;

public class TankRenderer<T extends TankBlockEntity> implements BlockEntityRenderer<T> {

    BlockRenderDispatcher renderDispatcher;
    public TankRenderer(BlockEntityRendererProvider.Context context){
        renderDispatcher = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(T blockEntity, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if(blockEntity.getLevel().getBlockState(blockEntity.getBlockPos()).getBlock() instanceof TankBlock) {
            IClientFluidTypeExtensions properties = IClientFluidTypeExtensions.of(blockEntity.tank.getFluid().getFluid());
            if (blockEntity.tank.getFluid().getAmount() == 0) return;
            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(properties.getStillTexture());
            Color color = new Color(properties.getTintColor());
            if(blockEntity.getTank().getFluid().getFluid() instanceof WaterFluid) {
                color = new Color(BiomeColors.getAverageWaterColor(blockEntity.getLevel(), blockEntity.getBlockPos()));
            }
            float fill = (float)blockEntity.tank.getFluid().getAmount()/(float)blockEntity.tank.getCapacity();
            if (fill > 1) fill = 1;
            float h = blockEntity.getLevel().getBlockState(blockEntity.getBlockPos()).getValue(TankBlock.UP) ? 15.98F/16F : 1;
            VertexConsumer vertexConsumer = bufferIn.getBuffer(RenderType.translucent());
            RenderHelper.cube(stackIn, vertexConsumer, sprite,
                    0.098F/16F, 0.098F/16F, 0.098F/16F,
                    15.98F/16F, h * fill, 15.98F/16F,
                    color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), combinedLightIn,
                    true, true, false,
                    fill < 1 || (blockEntity.getLevel().getBlockEntity(blockEntity.getBlockPos().above()) instanceof TankBlockEntity tankBlock && tankBlock.getTank().isEmpty()),
                    true, true);
        }
    }
}
