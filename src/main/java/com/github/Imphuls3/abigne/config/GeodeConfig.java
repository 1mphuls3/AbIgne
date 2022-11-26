package com.github.Imphuls3.abigne.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class GeodeConfig {

    public static BooleanValue generateCinnabarGeode;
    public static IntValue CinnabarGeodeMinY;
    public static IntValue CinnabarGeodeMaxY;
    public static IntValue CinnabarGeodeRarity;

    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("Settings for geode generation").push("geode");
        generateCinnabarGeode = COMMON_BUILDER
                .comment("Generate Cinnabar Geode [Default: true]")
                .define("generateCinnabarGeode", true);
        CinnabarGeodeMinY = COMMON_BUILDER
                .comment("The Min Y level that an Cinnabar geode will generate at (above minimum world height) [Default: 2]")
                .defineInRange("CinnabarGeodeMinY", 2, 0, Integer.MAX_VALUE);
        CinnabarGeodeMaxY = COMMON_BUILDER
                .comment("The max Y level that an Cinnabar geode will generate at [Default: 50]")
                .defineInRange("CinnabarGeodeMaxY", 50, 0, Integer.MAX_VALUE);
        CinnabarGeodeRarity = COMMON_BUILDER
                .comment("The rarity of Cinnabar geodes [Default: 15]")
                .defineInRange("CinnabarGeodeRarity", 15, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
}
