package com.github.Imphuls3.abigne;

import com.github.Imphuls3.abigne.client.event.RenderHandler;
import com.github.Imphuls3.abigne.client.particle.ParticleRegistry;
import com.github.Imphuls3.abigne.common.world.feature.ConfiguredFeatures;
import com.github.Imphuls3.abigne.common.world.feature.PlacedFeaturesRegistry;
import com.github.Imphuls3.abigne.config.Config;
import com.github.Imphuls3.abigne.common.effect.EffectsRegistry;
import com.github.Imphuls3.abigne.common.effect.PotionsRegistry;
import com.github.Imphuls3.abigne.core.registry.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
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
            return ItemRegistry.NECRONOMICON.get().getDefaultInstance();
        }
    };

    private static final Logger LOGGER = LogManager.getLogger();

    public AbIgne() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        AttributeRegistry.register(eventBus);
        BlockEntityRegistry.register(eventBus);
        BlockRegistry.register(eventBus);
        ItemRegistry.register(eventBus);
        FluidTypeRegistry.register(eventBus);
        FluidRegistry.register(eventBus);
        PotionsRegistry.register(eventBus);
        EffectsRegistry.register(eventBus);
        EntityRegistry.register(eventBus);

        ParticleRegistry.register(eventBus);

        Config.register();

        ConfiguredFeatures.register(eventBus);
        PlacedFeaturesRegistry.register(eventBus);

        eventBus.addListener(this::sendImc);
        eventBus.addListener(this::setup);
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> {
            MinecraftForge.EVENT_BUS.register(new RenderHandler());
            return new Object();
        });
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void featureSetup(final IEventBus event) {
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockRegistry.HEMLOCK.getId(), BlockRegistry.POTTED_HEMLOCK);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockRegistry.BELLADONA.getId(), BlockRegistry.POTTED_BELLADONA);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockRegistry.FROST_BLOOM.getId(), BlockRegistry.POTTED_FROST_BLOOM);
        });
    }

    private void sendImc(InterModEnqueueEvent event){
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CHARM.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.RING.getMessageBuilder().size(2).build());
    }

    public static ResourceLocation modPath(String path) {
        return new ResourceLocation(MODID, path);
    }
}
