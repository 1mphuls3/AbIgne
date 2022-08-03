package com.Imphuls3.abigne.client.renderer.block;

import com.Imphuls3.abigne.core.rendering.AbIgneRenderTypes;
import com.Imphuls3.abigne.core.registry.common.RenderTypeRegistry;
import com.Imphuls3.abigne.common.blockentity.InfuserBlockEntity;
import com.Imphuls3.abigne.core.Easing;
import com.Imphuls3.abigne.core.helper.ColorHelper;
import com.Imphuls3.abigne.core.helper.VecHelper;
import com.Imphuls3.abigne.core.helper.RenderHelper;
import com.lowdragmc.shimmer.client.postprocessing.PostProcessing;
import com.lowdragmc.shimmer.client.shader.RenderUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.awt.*;

import static com.Imphuls3.abigne.AbIgne.prefix;
import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class TextureRenderer implements BlockEntityRenderer<InfuserBlockEntity> {
    public static final ResourceLocation CIRCLE = prefix("effect/circle");
    public static final ResourceLocation CIRCLE_OUTER = prefix("effect/circle_outer");

    public TextureRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(InfuserBlockEntity blockEntity, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlay) {
        Level level = Minecraft.getInstance().level;
        int brightness = LightTexture.FULL_BRIGHT;
        float alpha = blockEntity.getAlpha();
        float red = blockEntity.getRed();
        float green = blockEntity.getGreen();
        float blue = blockEntity.getBlue();
        float red2 = blockEntity.getRed2();
        float green2 = blockEntity.getGreen2();
        float blue2 = blockEntity.getBlue2();
        float scale = blockEntity.getScale();

        float speed = blockEntity.getSpeed();
        float scale2 = blockEntity.getScale2();

        Color color = new Color(red, green, blue);
        Color color2 = new Color(red2, green2, blue2);

        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(CIRCLE);
        TextureAtlasSprite sprite2 = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(CIRCLE_OUTER);

        stackIn.pushPose();
        stackIn.translate(0.5, 0.02, 0.5);
        stackIn.mulPose(Vector3f.XP.rotationDegrees(90));

        PoseStack finalStack = RenderUtils.copyPoseStack(stackIn);
        Matrix4f matrix = finalStack.last().pose();
        PostProcessing effect = PostProcessing.BLOOM_UNREAL;

        effect.postEntity(buffer -> {
            VertexConsumer vertexConsumer = buffer.getBuffer(AbIgneRenderTypes.ADD);
            Color col = ColorHelper.multicolorLerp(Easing.LINEAR, Mth.sin((System.currentTimeMillis() % 86400000) / 850F) * speed + 0.1F, color, color2);
            RenderHelper.renderSprite(vertexConsumer, matrix, finalStack, sprite, scale, col, alpha, brightness);
            for (int i = 0; i < 16; i++) {
                Vec3 pos = VecHelper.vec3FromBlockPos(blockEntity.getBlockPos());
                pos = pos.add(0.5, 0.2, 0.5);
                Vec3 cir = VecHelper.radialOffset(pos, 1,  i, 16);
                effect.postParticle(ParticleTypes.FLAME, cir.x, cir.y, cir.z, 0, 0.01, 0);
            }
        });

        stackIn.popPose();

        /*stackIn.pushPose();
        stackIn.translate(0.5, 0.02, 0.5);
        stackIn.mulPose(Vector3f.XP.rotationDegrees(90));
        stackIn.mulPose(Vector3f.YP.rotationDegrees((level.getGameTime()) * speed + partialTicks));

        PoseStack finalStack2 = RenderUtils.copyPoseStack(stackIn);

        effect.postEntity(buffer -> {
            VertexConsumer vertexConsumer2 = buffer.getBuffer(CustomRenderType.ADD);
            RenderHelper.renderSprite(vertexConsumer2, matrix, finalStack2, sprite2, scale2, new Color(red, green, blue), alpha, brightness);
        });
        stackIn.popPose();*/

        if (!blockEntity.inventory.getStackInSlot(0).isEmpty()) {
            ItemStack stack = blockEntity.inventory.getStackInSlot(0);
            stackIn.pushPose();
            float yDiff = Mth.sin((System.currentTimeMillis() % 86400000) / 850F) * 0.1F + 0.1F;
            stackIn.translate(0.5D, 0.4D + yDiff, 0.5D);
            stackIn.mulPose(Vector3f.YP.rotationDegrees((level.getGameTime()) * 3 + partialTicks));
            stackIn.scale(0.5F, 0.5F, 0.5F);

            PostProcessing effect3 = RenderTypeRegistry.CHROMATIC_ABBERATION;

            PoseStack finalStack3 = RenderUtils.copyPoseStack(stackIn);
            effect3.postEntity(buffer -> {
                Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, finalStack3, buffer, 0);
            });
            stackIn.popPose();
        }
    }
}