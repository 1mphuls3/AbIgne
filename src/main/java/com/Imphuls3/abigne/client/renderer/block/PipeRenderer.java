
package com.Imphuls3.abigne.client.renderer.block;

import com.Imphuls3.abigne.common.block.pipe.PipeBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;

public class PipeRenderer implements BlockEntityRenderer<PipeBlockEntity> {

    public PipeRenderer(BlockEntityRendererProvider.Context context){
    }

    @Override
    public void render(PipeBlockEntity blockEntityIn, float partialTicks, PoseStack stackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Level level = Minecraft.getInstance().level;
        if(blockEntityIn.getIgnis() > 0) {
            level.addParticle(ParticleTypes.FLAME, blockEntityIn.getBlockPos().getX() + 0.5, blockEntityIn.getBlockPos().getY() + 0.5, blockEntityIn.getBlockPos().getZ() + 0.5,
                    0, 0, 0);
        }
    }
}
