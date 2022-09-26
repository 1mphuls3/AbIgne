package com.github.Imphuls3.abigne.client.renderer.block;

import com.github.Imphuls3.abigne.client.particle.FlameParticleData;
import com.github.Imphuls3.abigne.client.particle.ParticleColor;
import com.github.Imphuls3.abigne.common.blockentity.InfuserBlockEntity;
import com.github.Imphuls3.abigne.core.Easing;
import com.github.Imphuls3.abigne.core.helper.ColorHelper;
import com.github.Imphuls3.abigne.core.helper.VecHelper;
import com.github.Imphuls3.abigne.core.helper.RenderHelper;
import com.github.Imphuls3.abigne.core.registry.ShaderRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.awt.*;
import java.util.Random;

import static com.github.Imphuls3.abigne.AbIgne.modPath;
import static net.minecraft.client.renderer.texture.OverlayTexture.NO_OVERLAY;

public class TextureRenderer<T extends InfuserBlockEntity> implements BlockEntityRenderer<T> {
    public static final ResourceLocation WHITE = modPath("effect/white");
    public static final ResourceLocation CIRCLE = modPath("effect/circle");
    public static final ResourceLocation CIRCLE_OUTER = modPath("effect/circle_outer");

    public TextureRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(T blockEntity, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlay) {
        Level level = Minecraft.getInstance().level;
        int brightness = RenderHelper.FULL_BRIGHT;
        float alpha = blockEntity.getAlpha();
        float red = blockEntity.getRed();
        float green = blockEntity.getGreen();
        float blue = blockEntity.getBlue();
        float red2 = blockEntity.getRed2();
        float green2 = blockEntity.getGreen2();
        float blue2 = blockEntity.getBlue2();
        float scale = blockEntity.getScale();
        float speed = blockEntity.getSpeed();

        Color color = new Color(red, green, blue);
        Color color2 = new Color(red2, green2, blue2);

        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(CIRCLE);
        TextureAtlasSprite white = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(WHITE);

        stackIn.pushPose();
        stackIn.translate(0.5, 0.001, 0.5);
        stackIn.mulPose(Vector3f.XP.rotationDegrees(90));

        Matrix4f matrix = stackIn.last().pose();

        VertexConsumer vertexConsumer = bufferIn.getBuffer(ShaderRegistry.AbIgneRenderTypes.ADD);
        Color col = ColorHelper.multicolorLerp(Easing.LINEAR, Mth.sin((System.currentTimeMillis() % 86400000) / 850F) * speed + 0.1F, color, color2);
        RenderHelper.renderSprite(vertexConsumer, matrix, stackIn, sprite, scale, col, alpha, brightness);
        Vec3 pos = VecHelper.vec3FromBlockPos(blockEntity.getBlockPos());
        pos = pos.add(0.5, 0.2, 0.5);
        for (int i = 0; i < 16; i++) {
            Vec3 cir = VecHelper.radialOffset(pos, 1,  i, 16);
            Random random = new Random();
            float rand = Mth.randomBetween(random, -0.2F, 0.2F);
            float rand2 = Mth.randomBetween(random, -0.2F, 0.2F);
            level.addParticle(FlameParticleData.createData(new ParticleColor(col.getRed(), col.getGreen(), col.getBlue())), cir.x + rand, cir.y, cir.z + rand2, 0, 0.05, 0);
        }

        stackIn.popPose();
        if (!blockEntity.inventory.getStackInSlot(0).isEmpty()) {
            ItemStack stack = blockEntity.inventory.getStackInSlot(0);
            stackIn.pushPose();
            float yDiff = Mth.sin((System.currentTimeMillis() % 86400000) / 850F) * 0.1F + 0.1F;
            stackIn.translate(0.5D, 0.4D + yDiff + (((double)blockEntity.progress/blockEntity.maxProgress)/0.5D)-0.25D, 0.5D);
            stackIn.mulPose(Vector3f.YP.rotationDegrees((level.getGameTime()) * 3 + partialTicks));
            stackIn.scale(0.5F, 0.5F, 0.5F);

            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, combinedLightIn, NO_OVERLAY, stackIn, bufferIn, 0);
            stackIn.popPose();
        }
    }
}
