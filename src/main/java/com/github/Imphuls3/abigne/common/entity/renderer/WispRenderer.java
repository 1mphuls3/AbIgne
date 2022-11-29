package com.github.Imphuls3.abigne.common.entity.renderer;

import com.github.Imphuls3.abigne.client.particle.ColorParticleOptions;
import com.github.Imphuls3.abigne.client.particle.FlameParticleData;
import com.github.Imphuls3.abigne.common.entity.WispEntity;
import com.github.Imphuls3.abigne.common.entity.WispModel;
import com.github.Imphuls3.abigne.core.helper.VecHelper;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

import static com.github.Imphuls3.abigne.AbIgne.modPath;

public class WispRenderer extends MobRenderer<WispEntity, WispModel> {
    ResourceLocation TEXTURE = modPath("textures/entity/wisp/wisp.png");

    public WispRenderer(EntityRendererProvider.Context context) {
        super(context, new WispModel(context.bakeLayer(WispModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public void render(WispEntity entity, float yaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, yaw, partialTicks, stack, buffer, packedLight);
    }

    @Nullable
    @Override
    protected RenderType getRenderType(WispEntity entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderType.entityTranslucent(getTextureLocation(entity));
    }

    @Override
    public ResourceLocation getTextureLocation(WispEntity entity) {
        return TEXTURE;
    }
}
