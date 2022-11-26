package com.github.Imphuls3.abigne.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

import java.util.HashMap;
import java.util.Map;

public class FluidTransportConfig {

    public static IntValue fluidFlowRate;

    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("Settings for fluid transportation").push("fluid transportation");
        fluidFlowRate = COMMON_BUILDER
                .comment("The rate that fluids will transfer in pipes, this value is affected by the fluids viscosity (This value is the flow rate of water) [Default: 100]")
                .defineInRange("fluidFlowRate", 100, 0, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();
    }
}
