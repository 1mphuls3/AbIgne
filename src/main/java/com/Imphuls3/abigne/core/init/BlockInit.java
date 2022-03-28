package com.Imphuls3.abigne.core.init;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.block.PedestalBlock;
import com.Imphuls3.abigne.common.block.entity.PedestalBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            AbIgne.MOD_ID);

    public static final RegistryObject<PedestalBlock> ITEM_PEDESTAL = BLOCKS.register("pedestal",
            () -> new PedestalBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)));
}