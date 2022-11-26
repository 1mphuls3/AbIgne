package com.github.Imphuls3.abigne.client.event;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.entity.SoulEntity;
import com.github.Imphuls3.abigne.common.entity.renderer.SoulEntityRenderer;
import com.github.Imphuls3.abigne.core.registry.EntityRegistry;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import com.github.Imphuls3.abigne.core.registry.BlockRegistry;
import com.github.Imphuls3.abigne.client.renderer.block.*;
import com.github.Imphuls3.abigne.core.registry.FluidRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = AbIgne.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents {
    private ClientModEvents() {
    }

    @SubscribeEvent//TODO: FIX THIS
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(EntityRegistry.SOUL.get(), SoulEntityRenderer::new);
        EntityRenderers.register(EntityRegistry.FIREBALL.get(), NoopRenderer::new);
        EntityRenderers.register(EntityRegistry.MANA.get(), NoopRenderer::new);
        EntityRenderers.register(EntityRegistry.SYMBOL_PROJECTILE.get(), NoopRenderer::new);
        EntityRenderers.register(EntityRegistry.SILVER_EXPLOSIVE.get(), NoopRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.INFUSER.get(), RenderType.tripwire());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.FLUID_EXTRACTOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.ALEMBIC.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.TANK.get(), RenderType.tripwire());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.FLUID_PIPE.get(), RenderType.tripwire());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.COPPER_POT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.FLUID_EMITTER.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.INFUSED_WOOD_TABLE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.FLAME.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public static void EntityRenderersEvent$RegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegistry.PEDESTAL.get(), PedestalRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.FLUID_EXTRACTOR.get(), FluidExtractorRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.TANK.get(), TankRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.FLUID_PIPE.get(), PipeRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.COPPER_POT.get(), CopperPotRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.FLUID_EMITTER.get(), FluidEmitterRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.ALEMBIC.get(), AlembicRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.INFUSER.get(), TextureRenderer::new);
        event.registerBlockEntityRenderer(BlockEntityRegistry.FIRING_BLOCK.get(), FiringBlockRenderer::new);
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(EntityRegistry.SOUL.get(), SoulEntity.setAttributes());
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getAtlas().location().equals(InventoryMenu.BLOCK_ATLAS)) {
            event.addSprite(TextureRenderer.CIRCLE);
            event.addSprite(TextureRenderer.CIRCLE_OUTER);
            event.addSprite(TextureRenderer.WHITE);
            event.addSprite(SoulEntityRenderer.SYMBOL);
        }
    }
}
