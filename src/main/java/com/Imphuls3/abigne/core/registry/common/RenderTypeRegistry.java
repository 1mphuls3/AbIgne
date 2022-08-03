package com.Imphuls3.abigne.core.registry.common;

import com.lowdragmc.shimmer.client.postprocessing.PostProcessing;
import net.minecraft.resources.ResourceLocation;

public class RenderTypeRegistry {
    public static final PostProcessing SMOKE_WARP = PostProcessing.registerPost("smoke_warp", new ResourceLocation("abigne","shaders/post/smoke_warp.json"));
    public static final PostProcessing FIRE = PostProcessing.registerPost("fire", new ResourceLocation("abigne","shaders/post/fire.json"));
    public static final PostProcessing CHROMATIC_ABBERATION = PostProcessing.registerPost("chroma_abb", new ResourceLocation("abigne","shaders/post/chroma_abb.json"));
}

