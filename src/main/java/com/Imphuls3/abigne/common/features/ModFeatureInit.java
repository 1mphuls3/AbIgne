package com.Imphuls3.abigne.common.features;

import com.Imphuls3.abigne.common.config.GeodeConfig;
import com.Imphuls3.abigne.core.init.BlockInit;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;
import java.util.function.Supplier;

public class ModFeatureInit {
        protected final String NAME;

        protected final Holder<ConfiguredFeature<GeodeConfiguration, ?>> GEODE;
        protected final Holder<PlacedFeature> GEODE_PLACED;

        public String getName() {
            return NAME;
        }

        public Holder<PlacedFeature> getPlacedFeature() {
            return GEODE_PLACED;
        }

        public ModFeatureInit(String name, Integer rarityConfig, Integer minYConfig, Integer maxYConfig) {
            NAME = name;

            GEODE = FeatureUtils.register(name + "_geode", Feature.GEODE, new GeodeConfiguration(
                    new GeodeBlockSettings(BlockStateProvider.simple(Blocks.AIR), SimpleStateProvider.simple(BlockInit.IGNIS_GEM_BLOCK.get()),
                            SimpleStateProvider.simple(BlockInit.BUDDING_IGNIS_GEM_BLOCK.get()), BlockStateProvider.simple(BlockInit.HELLSTONE.get()),
                            SimpleStateProvider.simple(Blocks.SMOOTH_BASALT),
                            List.of(BlockInit.SMALL_IGNIS_BUD.get().defaultBlockState(),
                                    BlockInit.MEDIUM_IGNIS_BUD.get().defaultBlockState(),
                                    BlockInit.LARGE_IGNIS_BUD.get().defaultBlockState(),
                                    BlockInit.IGNIS_CLUSTER.get().defaultBlockState()),
                            BlockTags.FEATURES_CANNOT_REPLACE, BlockTags.GEODE_INVALID_BLOCKS),
                    new GeodeLayerSettings(1.7D, 2.2D, 3.2D, 4.2D),
                    new GeodeCrackSettings(0.95D, 2.0D, 2), 0.35D, 0.083D, true,
                    UniformInt.of(4, 6), UniformInt.of(3, 4),
                    UniformInt.of(1, 2), -16, 16, 0.05D, 1));

            GEODE_PLACED = PlacementUtils.register(name + "_geode", GEODE, RarityFilter.onAverageOnceEvery(rarityConfig),
                    HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(minYConfig),
                            VerticalAnchor.absolute(maxYConfig)),
                    BiomeFilter.biome());
        }
}