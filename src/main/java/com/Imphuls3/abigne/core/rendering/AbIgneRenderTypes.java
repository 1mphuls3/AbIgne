package com.Imphuls3.abigne.core.rendering;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.TripWireBlock;

public class AbIgneRenderTypes extends RenderType {
    public AbIgneRenderTypes(String name, VertexFormat vertexFormat, VertexFormat.Mode mode, int bufferSize, boolean affectsCrumbling, boolean sortOnUpload, Runnable setup, Runnable clear) {
        super(name, vertexFormat, mode, bufferSize, affectsCrumbling, sortOnUpload, setup, clear);
    }
    private static RenderType.CompositeState state() {
        return RenderType.CompositeState.builder()
                .setLightmapState(LIGHTMAP)
                .setShaderState(RENDERTYPE_TRIPWIRE_SHADER)
                .setTextureState(BLOCK_SHEET_MIPPED)
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setOutputState(WEATHER_TARGET)
                .createCompositeState(true);
    }

    public static final RenderType ADD = create("translucent",
            DefaultVertexFormat.BLOCK, VertexFormat.Mode.QUADS,
            262144, true, true,
            state());

}
