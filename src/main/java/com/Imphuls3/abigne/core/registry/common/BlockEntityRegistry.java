package com.Imphuls3.abigne.core.registry.common;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.block.PedestalBlock;
import com.Imphuls3.abigne.common.block.pipe.PipeBlockEntity;
import com.Imphuls3.abigne.common.blockentity.*;
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

    public static final RegistryObject<BlockEntityType<CalmingTorchBlockEntity>> TORCH = BLOCKENTITIES.register("calming_torch",
            () -> BlockEntityType.Builder.of(CalmingTorchBlockEntity::new, BlockRegistry.TORCH.get()).build(null));

    public static final RegistryObject<BlockEntityType<PedestalBlockEntity>> PEDESTAL = BLOCKENTITIES.register("pedestal",
            () -> BlockEntityType.Builder.of(PedestalBlockEntity::new, getBlocks(PedestalBlock.class)).build(null));

    public static final RegistryObject<BlockEntityType<CrucibleBlockEntity>> CRUCIBLE = BLOCKENTITIES.register("crucible",
            () -> BlockEntityType.Builder.of(CrucibleBlockEntity::new, BlockRegistry.CRUCIBLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<InfuserBlockEntity>> INFUSER = BLOCKENTITIES.register("infuser",
            () -> BlockEntityType.Builder.of(InfuserBlockEntity::new, BlockRegistry.INFUSER.get()).build(null));

    public static final RegistryObject<BlockEntityType<CenterPedestalBlockEntity>> RITUAL_PEDESTAL = BLOCKENTITIES.register("ritual",
            () -> BlockEntityType.Builder.of(CenterPedestalBlockEntity::new, BlockRegistry.RITUAL_PEDESTAL.get()).build(null));

    public static final RegistryObject<BlockEntityType<WallLampEntity>> WALL = BLOCKENTITIES.register("wall_lamp",
            () -> BlockEntityType.Builder.of(WallLampEntity::new, BlockRegistry.WALL.get()).build(null));

    public static final RegistryObject<BlockEntityType<PipeBlockEntity>> PIPE = BLOCKENTITIES.register("pipe",
            () -> BlockEntityType.Builder.of(PipeBlockEntity::new, BlockRegistry.PIPE.get()).build(null));

    public static final RegistryObject<BlockEntityType<IgnisVoidBlockEntity>> VOID = BLOCKENTITIES.register("void",
            () -> BlockEntityType.Builder.of(IgnisVoidBlockEntity::new, BlockRegistry.VOID.get()).build(null));

    public static final RegistryObject<BlockEntityType<ItemTransporterBlockEntity>> TRANSPORTER = BLOCKENTITIES.register("transporter",
            () -> BlockEntityType.Builder.of(ItemTransporterBlockEntity::new, BlockRegistry.TRANSPORTER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCKENTITIES.register(eventBus);
    }
}
