package com.Imphuls3.abigne;

import com.Imphuls3.abigne.common.config.Config;
import com.Imphuls3.abigne.common.features.ModFeatures;
import com.Imphuls3.abigne.common.worldgen.ModWorldGen;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import com.Imphuls3.abigne.core.init.BlockInit;
import com.Imphuls3.abigne.core.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod(AbIgne.MOD_ID)
public class AbIgne {
    public static final String MOD_ID = "abigne";

    public static CreativeModeTab itemGroup = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return ItemInit.IGNIS_INGOT.get().getDefaultInstance();
        }
    };

    private static final Logger LOGGER = LogManager.getLogger();

    public AbIgne() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockEntityInit.BE.register(bus);
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        Config.register();

        bus.addListener(this::sendImc);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ModWorldGen());
    }

    private void setup(final FMLCommonSetupEvent event) {
        ModFeatures.initialize();
    }

    private void sendImc(InterModEnqueueEvent event){
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CHARM.getMessageBuilder().build());
    }
}
