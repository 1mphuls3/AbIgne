package com.Imphuls3.abigne.client.event;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.client.renderer.block.*;
import com.Imphuls3.abigne.common.entity.renderer.ModFireballProjectileRenderer;
import com.Imphuls3.abigne.common.entity.renderer.SoulEntityRenderer;
import com.Imphuls3.abigne.core.registry.common.EntityRegistration;
import com.Imphuls3.abigne.core.registry.common.BlockEntityRegistry;
import com.Imphuls3.abigne.core.registry.common.BlockRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AbIgne.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {

    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityRegistration.SOUL.get(), SoulEntityRenderer::new);
        EntityRenderers.register(EntityRegistration.FIREBALL.get(), ModFireballProjectileRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.INFUSED_WOOD_PEDESTAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BLACK_CALCITE_PEDESTAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.INFUSER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CRUCIBLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.WALL.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.PYROLITE_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SMALL_PYROLITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.MEDIUM_PYROLITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.LARGE_PYROLITE_BUD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.ENFLAMED_ASH.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.IGNIS_GLASS.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.PIPE.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.IGNIS_GLASS.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void EntityRenderersEvent$RegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegistry.PEDESTAL.get(), PedestalRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.CRUCIBLE.get(), CrucibleRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.INFUSER.get(), TextureRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.RITUAL_PEDESTAL.get(), RitualRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.WALL.get(), WallLampRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.PIPE.get(), PipeRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.TRANSPORTER.get(), TransporterRenderer::new);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            event.addSprite(TextureRenderer.CIRCLE);
            event.addSprite(TextureRenderer.CIRCLE_OUTER);
        }
    }
}
