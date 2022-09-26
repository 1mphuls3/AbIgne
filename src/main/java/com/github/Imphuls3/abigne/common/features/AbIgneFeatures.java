package com.github.Imphuls3.abigne.common.features;

import com.github.Imphuls3.abigne.config.GeodeConfig;
import com.github.Imphuls3.abigne.core.registry.BlockRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class AbIgneFeatures {
    public static void initialize() {
    }

    public static GeodeFeature IGNIS_GEODE = new GeodeFeature("ignis", GeodeConfig.ignisGeodeRarity.get(),
            GeodeConfig.ignisGeodeMinY.get(),
            GeodeConfig.ignisGeodeMaxY.get());

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> BELLADONA = FeatureUtils.register("belladona",
            Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BlockRegistry.BELLADONA.get().defaultBlockState(), 2)), 64));
    public static final Holder<PlacedFeature> BELLADONA_PLACE = PlacementUtils.register("flower_warm",
            BELLADONA, RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> HEMLOCK = FeatureUtils.register("hemlock",
            Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BlockRegistry.HEMLOCK.get().defaultBlockState(), 2)), 64));
    public static final Holder<PlacedFeature> HEMLOCK_PLACE = PlacementUtils.register("hemlock",
            HEMLOCK, RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    private static RandomPatchConfiguration grassPatch(BlockStateProvider state, int tries) {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(state)));
    }

}
