package com.Imphuls3.abigne.core.init;

import com.Imphuls3.abigne.AbIgne;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            AbIgne.MOD_ID);

    public static final RegistryObject<BlockItem> PEDESTAL_ITEM = ITEMS.register("pedestal",
            () -> new BlockItem(BlockInit.ITEM_PEDESTAL.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> IGNIS_INGOT = ITEMS.register("ignis_ingot",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> IGNIS_NUGGET = ITEMS.register("ignis_nugget",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> IGNIS_BLOCK_ITEM = ITEMS.register("ignis_block",
            () -> new BlockItem(BlockInit.IGNIS_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> EMBER_LOG_ITEM = ITEMS.register("ember_log",
            () -> new BlockItem(BlockInit.EMBER_LOG.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> EMBER_WOOD_ITEM = ITEMS.register("ember_wood",
            () -> new BlockItem(BlockInit.EMBER_WOOD.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> STRIPPED_EMBER_LOG_ITEM = ITEMS.register("stripped_ember_log",
            () -> new BlockItem(BlockInit.STRIPPED_EMBER_LOG.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> STRIPPED_EMBER_WOOD_ITEM = ITEMS.register("stripped_ember_wood",
            () -> new BlockItem(BlockInit.STRIPPED_EMBER_WOOD.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> EMBER_PLANKS_ITEM = ITEMS.register("ember_planks",
            () -> new BlockItem(BlockInit.EMBER_PLANKS.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> CHARRED_LOG_ITEM = ITEMS.register("charred_log",
            () -> new BlockItem(BlockInit.CHARRED_LOG.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> CHARRED_WOOD_ITEM = ITEMS.register("charred_wood",
            () -> new BlockItem(BlockInit.CHARRED_WOOD.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> CHARRED_PLANKS_ITEM = ITEMS.register("charred_planks",
            () -> new BlockItem(BlockInit.CHARRED_PLANKS.get(), new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<Item> ASHES = ITEMS.register("ashes",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> ASHEN_BRICKS = ITEMS.register("ashen_bricks_soft",
            () -> new BlockItem(BlockInit.ASHEN_BRICKS.get(),new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> ASHEN_BRICKS_HARD = ITEMS.register("ashen_bricks_hard",
            () -> new BlockItem(BlockInit.ASHEN_BRICKS_HARD.get(),new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> ASHEN_BRICKS_SMALL = ITEMS.register("ashen_bricks_small",
            () -> new BlockItem(BlockInit.ASHEN_BRICKS_SMALL.get(),new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> ASHEN_TILE = ITEMS.register("ashen_tile",
            () -> new BlockItem(BlockInit.ASHEN_TILE.get(),new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> ASHEN_TILE_SMALL = ITEMS.register("ashen_tile_small",
            () -> new BlockItem(BlockInit.ASHEN_TILE_SMALL.get(),new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    public static final RegistryObject<BlockItem> ASHEN_TILE_TINY = ITEMS.register("ashen_tile_tiny",
            () -> new BlockItem(BlockInit.ASHEN_TILE_TINY.get(),new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));
}
