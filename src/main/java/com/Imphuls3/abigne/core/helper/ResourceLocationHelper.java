package com.Imphuls3.abigne.core.helper;

import com.Imphuls3.abigne.AbIgne;
import net.minecraft.resources.ResourceLocation;

public class ResourceLocationHelper {
    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(AbIgne.MODID, path);
    }
}
