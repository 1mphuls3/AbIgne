package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.block.PedestalBlock;
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

    public static Block[] getBlocks(Class<?>... blockClasses) {
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
            .create(ForgeRegistries.BLOCK_ENTITIES, AbIgne.MODID);

    public static final RegistryObject<BlockEntityType<PedestalBlockEntity>> PEDESTAL = BLOCKENTITIES.register("pedestal",
            () -> BlockEntityType.Builder.of(PedestalBlockEntity::new, getBlocks(PedestalBlock.class)).build(null));

    public static final RegistryObject<BlockEntityType<FluidExtractor>> FLUID_EXTRACTOR = BLOCKENTITIES.register("fluid_extractor",
            () -> BlockEntityType.Builder.of(FluidExtractor::new, BlockRegistry.FLUID_EXTRACTOR.get()).build(null));

    public static final RegistryObject<BlockEntityType<AlembicBlockEntity>> ALEMBIC = BLOCKENTITIES.register("alembic",
            () -> BlockEntityType.Builder.of(AlembicBlockEntity::new, BlockRegistry.ALEMBIC.get()).build(null));

    public static final RegistryObject<BlockEntityType<FiringBlockEntity>> FIRING_BLOCK = BLOCKENTITIES.register("firing_block",
            () -> BlockEntityType.Builder.of(FiringBlockEntity::new, BlockRegistry.FIRING_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<CrucibleBlockEntity>> CRUCIBLE = BLOCKENTITIES.register("crucible",
            () -> BlockEntityType.Builder.of(CrucibleBlockEntity::new, BlockRegistry.CRUCIBLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<InfuserBlockEntity>> INFUSER = BLOCKENTITIES.register("infuser",
            () -> BlockEntityType.Builder.of(InfuserBlockEntity::new, BlockRegistry.INFUSER.get()).build(null));

    public static final RegistryObject<BlockEntityType<FlameBlockEntity>> FLAME = BLOCKENTITIES.register("flame",
            () -> BlockEntityType.Builder.of(FlameBlockEntity::new, BlockRegistry.FLAME.get()).build(null));

    public static final RegistryObject<BlockEntityType<ItemTransporterBlockEntity>> TRANSPORTER = BLOCKENTITIES.register("transporter",
            () -> BlockEntityType.Builder.of(ItemTransporterBlockEntity::new, BlockRegistry.TRANSPORTER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCKENTITIES.register(eventBus);
    }
}
