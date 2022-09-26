package com.github.Imphuls3.abigne.core.data;

import com.github.Imphuls3.abigne.AbIgne;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = AbIgne.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new ModBlockTagsProvider(generator, existingFileHelper));
        generator.addProvider(new ModRecipeProvider(generator));
        generator.addProvider(new ModLootTableProvider(generator));
        generator.addProvider(new ModItemModelProvider(generator, existingFileHelper));
        generator.addProvider(new ModBlockStatesProvider(generator, existingFileHelper));
    }
}
