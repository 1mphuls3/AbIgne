package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.block.PedestalBlock;
import com.github.Imphuls3.abigne.common.block.PipeBlock;
import com.github.Imphuls3.abigne.common.blockentity.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public final class BlockEntityRegistry {

    public static Block[] getAllBlocks(Class<?>... blockClasses) {
        Collection<RegistryObject<Block>> blocks = BlockRegistry.BLOCKS.getEntries();
        ArrayList<Block> matchingBlocks = new ArrayList<>();
        for (RegistryObject<Block> registryObject : blocks) {
            if (Arrays.stream(blockClasses).anyMatch(b -> b.isInstance(registryObject.get()))) {
                matchingBlocks.add(registryObject.get());
            }
        }
        return matchingBlocks.toArray(new Block[0]);
    }

    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITY_TYPES, AbIgne.MODID);

    public static final RegistryObject<BlockEntityType<PedestalBlockEntity>> PEDESTAL = BLOCKENTITIES.register("pedestal",
            () -> BlockEntityType.Builder.of(PedestalBlockEntity::new, getAllBlocks(PedestalBlock.class)).build(null));

    public static final RegistryObject<BlockEntityType<FluidExtractor>> FLUID_EXTRACTOR = BLOCKENTITIES.register("fluid_extractor",
            () -> BlockEntityType.Builder.of(FluidExtractor::new, BlockRegistry.FLUID_EXTRACTOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<TankBlockEntity>> TANK = BLOCKENTITIES.register("tank",
            () -> BlockEntityType.Builder.of(TankBlockEntity::new, BlockRegistry.TANK.get()).build(null));

    public static final RegistryObject<BlockEntityType<PipeBlockEntity>> FLUID_PIPE = BLOCKENTITIES.register("fluid_pipe",
            () -> BlockEntityType.Builder.of(PipeBlockEntity::new, BlockRegistry.FLUID_PIPE.get()).build(null));

    public static final RegistryObject<BlockEntityType<CopperPotBlockEntity>> COPPER_POT = BLOCKENTITIES.register("copper_pot",
            () -> BlockEntityType.Builder.of(CopperPotBlockEntity::new, BlockRegistry.COPPER_POT.get()).build(null));

    public static final RegistryObject<BlockEntityType<FluidEmitterBlockEntity>> FLUID_EMITTER = BLOCKENTITIES.register("fluid_emitter",
            () -> BlockEntityType.Builder.of(FluidEmitterBlockEntity::new, BlockRegistry.FLUID_EMITTER.get()).build(null));

    public static final RegistryObject<BlockEntityType<FiringBlockEntity>> FIRING_BLOCK = BLOCKENTITIES.register("firing_block",
            () -> BlockEntityType.Builder.of(FiringBlockEntity::new, BlockRegistry.FIRING_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<InfuserBlockEntity>> INFUSER = BLOCKENTITIES.register("infuser",
            () -> BlockEntityType.Builder.of(InfuserBlockEntity::new, BlockRegistry.INFUSER.get()).build(null));

    public static final RegistryObject<BlockEntityType<FlameBlockEntity>> FLAME = BLOCKENTITIES.register("flame",
            () -> BlockEntityType.Builder.of(FlameBlockEntity::new, BlockRegistry.FLAME.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCKENTITIES.register(eventBus);
    }
}
