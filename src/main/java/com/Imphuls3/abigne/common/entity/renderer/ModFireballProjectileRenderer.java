package com.Imphuls3.abigne.common.entity.renderer;

import com.Imphuls3.abigne.common.entity.ModFireballProjectileEntity;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

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
