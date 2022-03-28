package com.Imphuls3.abigne.core.init;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.block.entity.PedestalBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITIES, AbIgne.MOD_ID);

    public static final RegistryObject<BlockEntityType<PedestalBlockEntity>> ITEM_PEDESTAL = BLOCK_ENTITIES.register("pedestal",
            () -> BlockEntityType.Builder.of(PedestalBlockEntity::new, BlockInit.ITEM_PEDESTAL.get()).build(null));
}
