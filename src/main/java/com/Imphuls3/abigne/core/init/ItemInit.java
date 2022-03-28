package com.Imphuls3.abigne.core.init;

import com.Imphuls3.abigne.AbIgne;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            AbIgne.MOD_ID);

    public static final RegistryObject<BlockItem> PEDESTAL_ITEM = ITEMS.register("pedestal",
            () -> new BlockItem(BlockInit.ITEM_PEDESTAL.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
}
