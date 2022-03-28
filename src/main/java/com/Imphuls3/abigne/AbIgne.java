package com.Imphuls3.abigne;

import com.Imphuls3.abigne.core.init.BlockEntityInit;
import com.Imphuls3.abigne.core.init.BlockInit;
import com.Imphuls3.abigne.core.init.ItemInit;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AbIgne.MOD_ID)
public class AbIgne
{
    public static final String MOD_ID = "abigne";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public AbIgne() {
        // Register the setup method for modloading
        var bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockEntityInit.BLOCK_ENTITIES.register(bus);
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}
