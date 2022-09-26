package com.github.Imphuls3.abigne.client.event;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.entity.renderer.SoulEntityRenderer;
import com.github.Imphuls3.abigne.core.registry.EntityRegistry;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import com.github.Imphuls3.abigne.core.registry.BlockRegistry;
import com.github.Imphuls3.abigne.client.renderer.block.*;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AbIgne.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {

    private ClientModEvents() {
    }

    @OnlyIn(Dist.CLIENT)
    static float clientTicks = 0;

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRenderLast(RenderLevelLastEvent event) {
        clientTicks += event.getPartialTick();
    }

    @OnlyIn(Dist.CLIENT)
    public static float getClientTicks() {
        return clientTicks;
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityRegistry.SOUL.get(), SoulEntityRenderer::new);
        EntityRenderers.register(EntityRegistry.FIREBALL.get(), NoopRenderer::new);
        EntityRenderers.register(EntityRegistry.MANA.get(), NoopRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BLACK_CALCITE_PILLAR_CAP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.INFUSED_PLANKS_PEDESTAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BLACK_CALCITE_PEDESTAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.INFUSER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.CRUCIBLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.FLAME.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.PYROLITE_CLUSTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.SMALL_PYROLITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.MEDIUM_PYROLITE_BUD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.LARGE_PYROLITE_BUD.get(), RenderType.cutout());

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.IGNIS_GLASS.get(), RenderType.translucent());

        //region doorsnstuff
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.INFUSED_WOOD_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.INFUSED_WOOD_TRAPDOOR.get(), RenderType.cutout());
        //endregion

        //region plants
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.HEMLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BELLADONA.get(), RenderType.cutout());
        //endregion
    }

    @SubscribeEvent
    public static void EntityRenderersEvent$RegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegistry.PEDESTAL.get(), PedestalRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.CRUCIBLE.get(), CrucibleRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.INFUSER.get(), TextureRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.TRANSPORTER.get(), TransporterRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.FIRING_BLOCK.get(), FiringBlockRenderer::new);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            event.addSprite(TextureRenderer.CIRCLE);
            event.addSprite(TextureRenderer.CIRCLE_OUTER);
            event.addSprite(TextureRenderer.WHITE);
            event.addSprite(SoulEntityRenderer.SYMBOL);
        }
    }
}
