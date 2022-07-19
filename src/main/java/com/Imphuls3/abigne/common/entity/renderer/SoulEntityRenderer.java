package com.Imphuls3.abigne.common.entity.renderer;

import com.Imphuls3.abigne.common.entity.SoulEntity;
import com.Imphuls3.abigne.common.entity.SoulModel;
import com.lowdragmc.shimmer.client.postprocessing.PostProcessing;
import com.lowdragmc.shimmer.client.shader.RenderUtils;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.Imphuls3.abigne.core.helper.ResourceLocationHelper.prefix;

public class SoulEntityRenderer<T extends Entity> extends GeoEntityRenderer<SoulEntity> {

    public SoulEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new SoulModel());
        this.shadowRadius = 0.6f;
    }

    @Override
    public ResourceLocation getTextureLocation(SoulEntity pEntity) {
        return prefix("textures/entity/soul/soul.png");
    }

    @Override
    public RenderType getRenderType(SoulEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
