package com.github.Imphuls3.abigne.client.renderer.block;

import com.github.Imphuls3.abigne.common.block.PipeBlock;
import com.github.Imphuls3.abigne.common.blockentity.PipeBlockEntity;
import com.github.Imphuls3.abigne.core.helper.RenderHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.client.model.data.ModelData;

import java.awt.*;
import java.awt.font.FontRenderContext;

public class PipeRenderer implements BlockEntityRenderer<PipeBlockEntity> {
    private final BlockEntityRendererProvider.Context context;
    public PipeRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    public boolean attached(BlockState b, Direction d) {
        return b.getValue(PipeBlock.IN) == d && b.getValue(PipeBlock.IN_ATTACHED)
                || b.getValue(PipeBlock.OUT) == d && b.getValue(PipeBlock.OUT_ATTACHED);
    }

    static final float L = 0.40625f, U = 0.59375f, W = U - L;

    @Override
    public void render(PipeBlockEntity pipe, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if(pipe.facade != null) {
            final BlockRenderDispatcher dispatcher = context.getBlockRenderDispatcher();
            stack.pushPose();
            dispatcher.renderSingleBlock(pipe.facade.getBlock().defaultBlockState(), stack, buffer, packedLight
                    , packedOverlay, ModelData.EMPTY, null);
            stack.popPose();
        }
        if (pipe.tank.getFluid().getAmount() != 0) {
            /*Font font = context.getFont();
            stack.pushPose();
            stack.translate(0.5, 1, 0.5);
            stack.scale(0.02F, 0.02F, 0.02F);
            stack.mulPose(Vector3f.YP.rotationDegrees(180));
            stack.mulPose(Vector3f.ZP.rotationDegrees(180));
            font.draw(stack, String.valueOf(pipe.tank.getFluid().getAmount()), 1, 0, new Color(255, 255, 255).getRGB());
            stack.popPose();*/

            float fill = (float) pipe.tank.getFluid().getAmount() / pipe.tank.getCapacity();
            if (fill > 1) fill = 1;
            BlockState state = pipe.getBlockState();
            IClientFluidTypeExtensions properties = IClientFluidTypeExtensions.of(pipe.tank.getFluid().getFluid());
            TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(properties.getStillTexture());
            VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.translucent());
            boolean west = state.getValue(PipeBlock.IN) == Direction.WEST || state.getValue(PipeBlock.OUT) == Direction.WEST,
                    east = state.getValue(PipeBlock.IN) == Direction.EAST || state.getValue(PipeBlock.OUT) == Direction.EAST,
                    down = state.getValue(PipeBlock.IN) == Direction.DOWN || state.getValue(PipeBlock.OUT) == Direction.DOWN,
                    up = state.getValue(PipeBlock.IN) == Direction.UP || state.getValue(PipeBlock.OUT) == Direction.UP,
                    north = state.getValue(PipeBlock.IN) == Direction.NORTH || state.getValue(PipeBlock.OUT) == Direction.NORTH,
                    south = state.getValue(PipeBlock.IN) == Direction.SOUTH || state.getValue(PipeBlock.OUT) == Direction.SOUTH;
            Color color = new Color(properties.getTintColor());
            if (pipe.getTank().getFluid().getFluid() instanceof WaterFluid) {
                color = new Color(BiomeColors.getAverageWaterColor(pipe.getLevel(), pipe.getBlockPos()));
            }
            if (up && down) {
                RenderHelper.cube(stack, vertexConsumer, sprite,
                        L, L, L, U, U, U,
                        color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), packedLight,
                        !west, !east, !down, !up, !north, !south);
            } else if (up) {
                if (fill < 1) RenderHelper.cube(stack, vertexConsumer, sprite,
                        L, L + fill * W, L, U, U, U,
                        color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), packedLight,
                        true, true, !down, !up, true, true);
                RenderHelper.cube(stack, vertexConsumer, sprite,
                        L, L, L, U, L + fill * W, U,
                        color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), packedLight,
                        !west, !east, !down, !up, !north, !south);
            } else {
                RenderHelper.cube(stack, vertexConsumer, sprite,
                        L, L, L, U, L + fill * W, U,
                        color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), packedLight,
                        !west, !east, !down, !up, !north, !south);
            }
            if (west) {
                RenderHelper.cube(stack, vertexConsumer, sprite,
                        0, L, L, L, L + W * fill, U,
                        color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), packedLight,
                        !attached(state, Direction.WEST), false, true, true, true, true);
            }
            if (east) {
                RenderHelper.cube(stack, vertexConsumer, sprite,
                        U, L, L, 1, L + W * fill, U,
                        color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), packedLight,
                        false, !attached(state, Direction.EAST), true, true, true, true);
            }
            if (down) {
                RenderHelper.cube(stack, vertexConsumer, sprite,
                        L, 0, L, U, L, U,
                        color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), packedLight,
                        true, true, !attached(state, Direction.DOWN), false, true, true);
            }
            if (up) {
                RenderHelper.cube(stack, vertexConsumer, sprite,
                        L, U, L, U, 1, U,
                        color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), packedLight,
                        true, true, false, !attached(state, Direction.UP), true, true);
            }
            if (north) {
                RenderHelper.cube(stack, vertexConsumer, sprite,
                        L, L, 0, U, L + W * fill, L,
                        color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), packedLight,
                        true, true, true, true, !attached(state, Direction.NORTH), false);
            }
            if (south) {
                RenderHelper.cube(stack, vertexConsumer, sprite,
                        L, L, U, U, L + W * fill, 1,
                        color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha(), packedLight,
                        true, true, true, true, false, !attached(state, Direction.SOUTH));
            }
        }
    }
}
