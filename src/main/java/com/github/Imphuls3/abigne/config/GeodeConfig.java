package com.github.Imphuls3.abigne.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class GeodeConfig {

    public static BooleanValue generateIgnisGeode;
    public static IntValue ignisGeodeMinY;
    public static IntValue ignisGeodeMaxY;
    public static IntValue ignisGeodeRarity;

    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("Settings for geode generation").push("geode");
        generateIgnisGeode = COMMON_BUILDER
                .comment("Generate Ignis Geode [Default: true]")
                .define("generateIgnisGeode", true);
        ignisGeodeMinY = COMMON_BUILDER
                .comment("The Min Y level that an Ignis geode will generate at (above minimum world height) [Default: 2]")
                .defineInRange("ignisGeodeMinY", 2, 0, Integer.MAX_VALUE);
        ignisGeodeMaxY = COMMON_BUILDER
                .comment("The max Y level that an Ignis geode will generate at [Default: 50]")
                .defineInRange("ignisGeodeMaxY", 50, 0, Integer.MAX_VALUE);
        ignisGeodeRarity = COMMON_BUILDER
                .comment("The rarity of Ignis geodes [Default: 15]")
                .defineInRange("ignisGeodeRarity", 15, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
}
