package com.Imphuls3.abigne.common.worldgen;

import com.Imphuls3.abigne.common.config.GeodeConfig;
import com.Imphuls3.abigne.common.features.ModFeatures;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModWorldGen {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void biomeLoadingEvent(BiomeLoadingEvent event) {
        ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        BiomeGenerationSettingsBuilder builder = event.getGeneration();

        if (BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.NETHER)) {
            if (GeodeConfig.generateIgnisGeode.get()) {
                builder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, ModFeatures.IGNIS_GEODE.getPlacedFeature());
            }
        }
    }
}
