package com.Imphuls3.abigne.common.entity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.Imphuls3.abigne.AbIgne.prefix;

public class SoulModel extends AnimatedGeoModel<SoulEntity> {
    @Override
    public ResourceLocation getModelLocation(SoulEntity object) {
        return prefix("geo/soul.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(SoulEntity object) {
        return prefix("textures/entity/soul/soul.png");
    }

    @Override
    public void registerModelRenderer(IBone modelRenderer) {
        super.registerModelRenderer(modelRenderer);
    }

    @Override
    public ResourceLocation getAnimationFileLocation(SoulEntity animatable) {
        return null;
    }
}
