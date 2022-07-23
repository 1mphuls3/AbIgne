package com.Imphuls3.abigne.client.event;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.client.renderer.block.*;
import com.Imphuls3.abigne.common.block.WallLamp;
import com.Imphuls3.abigne.common.entity.renderer.ModFireballProjectileRenderer;
import com.Imphuls3.abigne.common.entity.renderer.SoulEntityRenderer;
import com.Imphuls3.abigne.common.entity.EntityInit;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import com.Imphuls3.abigne.core.init.BlockInit;
import com.lowdragmc.shimmer.client.light.ColorPointLight;
import com.lowdragmc.shimmer.client.light.LightManager;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.world.level.block.WallBlock;
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
        EntityRenderers.register(EntityInit.SOUL.get(), SoulEntityRenderer::new);
        EntityRenderers.register(EntityInit.FIREBALL.get(), ModFireballProjectileRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(BlockInit.INFUSED_WOOD_PEDESTAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.BLACK_CALCITE_PEDESTAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.INFUSER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.CRUCIBLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.WALL.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockInit.PYROLITE_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.SMALL_PYROLITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.MEDIUM_PYROLITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockInit.LARGE_PYROLITE_BUD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockInit.ENFLAMED_ASH.get(), RenderType.cutout());

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
        event.registerBlockEntityRenderer(BlockEntityInit.INFUSER.get(), TextureRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.RITUAL_PEDESTAL.get(), RitualRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.WALL.get(), WallLampRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.PIPE.get(), PipeRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityInit.TRANSPORTER.get(), TransporterRenderer::new);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            event.addSprite(TextureRenderer.HALO);
        }
    }
}
