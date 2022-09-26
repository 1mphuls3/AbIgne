package com.github.Imphuls3.abigne.common.worldgen;

import com.github.Imphuls3.abigne.config.GeodeConfig;
import com.github.Imphuls3.abigne.common.features.AbIgneFeatures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AbIgneWorldGen {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void biomeLoadingEvent(BiomeLoadingEvent event) {
        ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        BiomeGenerationSettingsBuilder builder = event.getGeneration();

        if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.NETHER)) {
            if (GeodeConfig.generateIgnisGeode.get()) {
                builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AbIgneFeatures.IGNIS_GEODE.getPlacedFeature());
            }
        }
        if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.FOREST) && !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.COLD)
                && BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT)) {
            if (true) {
                builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AbIgneFeatures.BELLADONA_PLACE);
            }
        }
        if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.PLAINS) && !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.HOT)) {
            if (true) {
                builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, AbIgneFeatures.HEMLOCK_PLACE);
            }
        }
    }
}
