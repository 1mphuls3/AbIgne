package com.Imphuls3.abigne.common.features;

import com.Imphuls3.abigne.config.GeodeConfig;

public class AbIgneFeatures {
    public static void initialize() {

    }
    public static AbIgneFeatureRegistry IGNIS_GEODE = new AbIgneFeatureRegistry("ignis", GeodeConfig.ignisGeodeRarity.get(),
            GeodeConfig.ignisGeodeMinY.get(),
            GeodeConfig.ignisGeodeMaxY.get());
}
