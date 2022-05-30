package com.Imphuls3.abigne.client.event;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.client.renderer.block.*;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import com.Imphuls3.abigne.core.init.BlockInit;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AbIgne.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {

    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlockInit.PEDESTAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.CRUCIBLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.WALL.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockInit.PYROLITE_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.SMALL_PYROLITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.MEDIUM_PYROLITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.LARGE_PYROLITE_BUD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockInit.IGNIS_GLASS.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(BlockInit.PIPE.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ItemBlockRenderTypes.setRenderLayer(BlockInit.IGNIS_GLASS.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void EntityRenderersEvent$RegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityInit.PEDESTAL.get(), PedestalRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.CRUCIBLE.get(), CrucibleRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.INFUSER.get(), InfuserRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.RITUAL_PEDESTAL.get(), RitualRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.WALL.get(), WallLampRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.PIPE.get(), PipeRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.TRANSPORTER.get(), TransporterRenderer::new);
    }
}
