package com.Imphuls3.abigne.common.entity.renderer;

import com.Imphuls3.abigne.common.entity.ModFireballProjectileEntity;
import com.Imphuls3.abigne.common.entity.SoulEntity;
import com.Imphuls3.abigne.common.entity.SoulModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import static com.Imphuls3.abigne.core.helper.ResourceLocationHelper.prefix;

public class ModFireballProjectileRenderer<T extends Entity> extends EntityRenderer<ModFireballProjectileEntity> {

    public ModFireballProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.6f;
    }

    @Override
    public ResourceLocation getTextureLocation(ModFireballProjectileEntity pEntity) {
        return null;
    }

    @Override
    public boolean shouldRender(ModFireballProjectileEntity pLivingEntity, Frustum pCamera, double pCamX, double pCamY, double pCamZ) {
        return false;
    }
}
