package com.Imphuls3.abigne.client.renderer.block;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.client.renderer.CustomRenderType;
import com.Imphuls3.abigne.common.block.entity.WallLampEntity;
import com.lowdragmc.shimmer.client.postprocessing.PostProcessing;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

import java.util.Random;

public class WallLampRenderer implements BlockEntityRenderer<WallLampEntity> {
    public WallLampRenderer(BlockEntityRendererProvider.Context context){

    }

    @Override
    public void render(WallLampEntity blockEntity, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        BlockPos pos = blockEntity.getBlockPos();
        Random random = new Random();

        PostProcessing.BLOOM_UNREAL.postParticle(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.75,
                Mth.randomBetween(random, -0.015F, 0.015F),
                Mth.randomBetween(random, 0F, 0.02F),
                Mth.randomBetween(random, -0.015F, 0.015F));
        PostProcessing.BLOOM_UNREAL.postParticle(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.75,
                Mth.randomBetween(random, -0.0035F, 0.0035F),
                Mth.randomBetween(random, 0F, 0.045F),
                Mth.randomBetween(random, -0.0035F, 0.0035F));
    }
}
