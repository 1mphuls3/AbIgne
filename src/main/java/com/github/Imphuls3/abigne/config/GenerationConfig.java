package com.github.Imphuls3.abigne.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class GenerationConfig {

    public static IntValue defaultFluidFlowRate;

    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("Settings for fluid transportation").push("fluid transportation");
        defaultFluidFlowRate = COMMON_BUILDER
                .comment("The default rate that fluids will transfer in pipes, affected by a fluids viscosity (Water is this value minus 1) [Default: 20]")
                .defineInRange("defaultFluidFlowRate", 20, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
}
