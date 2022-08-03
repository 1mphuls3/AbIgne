package com.Imphuls3.abigne.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class CalmingTorchConfig {

    public static DoubleValue range;
    public static IntValue ignisConsumption;
    public static DoubleValue maxHealthAffected;
    public static DoubleValue mobSightRange;

    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        COMMON_BUILDER.comment("Settings for calming torch block").push("calming_torch");
        range = COMMON_BUILDER
                .comment("The range that the calming torch affects, height affected is 1/4 of this number, do not push this too high or it may cause lag [Default: 32]")
                .defineInRange("range", 32.0, 0, Double.MAX_VALUE);
        ignisConsumption = COMMON_BUILDER
                .comment("The amount of ignis the calming torch consumes per tick while active [Default: 100]")
                .defineInRange("ignisConsumption", 100, 0, Integer.MAX_VALUE);
        maxHealthAffected = COMMON_BUILDER
                .comment("The max health of mobs the calming torch affects, affects all enemies with a max health under this value")
                .comment("Note: this is measured in health points, not hearts, 26 health points is equal to 13 hearts  [Default: 26.0]")
                .defineInRange("maxHealthAffected", 26, 0, Double.MAX_VALUE);
        mobSightRange = COMMON_BUILDER
                .comment("The range that the calming torch sets mobs sight to (in blocks) [Default: 10.0]")
                .defineInRange("mobSightRange", 10.0, 0, Double.MAX_VALUE);
        COMMON_BUILDER.pop();
    }
}
