package com.github.Imphuls3.abigne.common.world.feature;

import com.github.Imphuls3.abigne.AbIgne;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class PlacedFeaturesRegistry {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, AbIgne.MODID);

    public static final RegistryObject<PlacedFeature> SALT_PLACED = PLACED_FEATURES.register("salt_placed",
            () -> new PlacedFeature(ConfiguredFeatures.SALT_BLOCK.getHolder().get(),
                    commonOrePlacement(1, HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(40)))));

    public static List<PlacementModifier> orePlacement(PlacementModifier modifier, PlacementModifier modifier2) {
        return List.of(modifier, InSquarePlacement.spread(), modifier2, BiomeFilter.biome());
    }
    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier modifier) {
        return orePlacement(CountPlacement.of(count), modifier);
    }

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
