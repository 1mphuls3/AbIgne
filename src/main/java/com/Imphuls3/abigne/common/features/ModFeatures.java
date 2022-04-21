package com.Imphuls3.abigne.common.features;

import com.Imphuls3.abigne.common.config.GeodeConfig;

public class ModFeatures {
    public static void initialize() {

    }
    public static ModFeatureInit IGNIS_GEODE = new ModFeatureInit("ignis", GeodeConfig.ignisGeodeRarity.get(),
            GeodeConfig.ignisGeodeMinY.get(),
            GeodeConfig.ignisGeodeMaxY.get());
}
