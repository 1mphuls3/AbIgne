package com.Imphuls3.abigne;

import com.Imphuls3.abigne.config.Config;
import com.Imphuls3.abigne.common.effect.EffectsRegistry;
import com.Imphuls3.abigne.common.effect.PotionsRegistry;
import com.Imphuls3.abigne.core.registry.common.EntityRegistration;
import com.Imphuls3.abigne.common.features.AbIgneFeatures;
import com.Imphuls3.abigne.core.registry.common.RecipeRegistry;
import com.Imphuls3.abigne.common.worldgen.AbIgneWorldGen;
import com.Imphuls3.abigne.core.registry.common.BlockEntityRegistry;
import com.Imphuls3.abigne.core.registry.common.BlockRegistry;
import com.Imphuls3.abigne.core.registry.common.ItemRegistry;
import net.minecraft.resources.ResourceLocation;
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

@Mod(AbIgne.MODID)
public class AbIgne {
    public static final String MODID = "abigne";

    public static CreativeModeTab itemGroup = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), MODID) {
        @Override
        public ItemStack makeIcon() {
            return ItemRegistry.PYROLITE_SHARD_ACTIVE.get().getDefaultInstance();
        }
    };

    private static final Logger LOGGER = LogManager.getLogger();

    public AbIgne() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockEntityRegistry.register(eventBus);
        BlockRegistry.register(eventBus);
        ItemRegistry.register(eventBus);
        PotionsRegistry.register(eventBus);
        EffectsRegistry.register(eventBus);
        RecipeRegistry.register(eventBus);
        EntityRegistration.register(eventBus);
        Config.register();

        eventBus.addListener(this::sendImc);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new AbIgneWorldGen());
    }

    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(MODID, path);
    }

    private void setup(final FMLCommonSetupEvent event) {
        AbIgneFeatures.initialize();
    }

    private void sendImc(InterModEnqueueEvent event){
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CHARM.getMessageBuilder().build());
    }
}
