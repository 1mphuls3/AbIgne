package com.Imphuls3.abigne.core.init;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.block.entity.CrucibleBlockEntity;
import com.Imphuls3.abigne.common.block.entity.PedestalBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BE = DeferredRegister
            .create(ForgeRegistries.BLOCK_ENTITIES, AbIgne.MOD_ID);

    public static final RegistryObject<BlockEntityType<PedestalBlockEntity>> PEDESTAL = BE.register("pedestal",
            () -> BlockEntityType.Builder.of(PedestalBlockEntity::new, BlockInit.PEDESTAL.get()).build(null));

    public static final RegistryObject<BlockEntityType<CrucibleBlockEntity>> CRUCIBLE = BE.register("crucible",
            () -> BlockEntityType.Builder.of(CrucibleBlockEntity::new, BlockInit.CRUCIBLE.get()).build(null));
}
