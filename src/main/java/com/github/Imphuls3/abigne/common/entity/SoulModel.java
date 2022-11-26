package com.github.Imphuls3.abigne.common.entity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.github.Imphuls3.abigne.AbIgne.modPath;

public class SoulModel extends AnimatedGeoModel<SoulEntity> {
    @Override
    public void registerModelRenderer(IBone modelRenderer) {
        super.registerModelRenderer(modelRenderer);
    }

    @Override
    public ResourceLocation getModelResource(SoulEntity object) {
        return modPath("geo/soul.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SoulEntity object) {
        return modPath("textures/entity/soul/soul.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SoulEntity animatable) {
        return null;
    }
}
