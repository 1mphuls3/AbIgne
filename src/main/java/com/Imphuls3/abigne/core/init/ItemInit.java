package com.Imphuls3.abigne.core.init;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.block.custom.BuddingIgnisBlock;
import com.Imphuls3.abigne.common.block.custom.IgnisClusterBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            AbIgne.MOD_ID);

    public static final RegistryObject<Item> IGNIS_SHIELD = ITEMS.register("ignis_shield",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> PEDESTAL_ITEM = ITEMS.register("pedestal",
            () -> new BlockItem(BlockInit.PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CRUCIBLE_ITEM = ITEMS.register("crucible",
            () -> new BlockItem(BlockInit.CRUCIBLE.get(), new Item.Properties().tab(AbIgne.itemGroup)));


    public static final RegistryObject<Item> IGNIS_INGOT = ITEMS.register("ignis_ingot",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> IGNIS_NUGGET = ITEMS.register("ignis_nugget",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> IGNIS_BLOCK_ITEM = ITEMS.register("ignis_block",
            () -> new BlockItem(BlockInit.IGNIS_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> EMBER_LOG_ITEM = ITEMS.register("ember_log",
            () -> new BlockItem(BlockInit.EMBER_LOG.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> EMBER_WOOD_ITEM = ITEMS.register("ember_wood",
            () -> new BlockItem(BlockInit.EMBER_WOOD.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> STRIPPED_EMBER_LOG_ITEM = ITEMS.register("stripped_ember_log",
            () -> new BlockItem(BlockInit.STRIPPED_EMBER_LOG.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> STRIPPED_EMBER_WOOD_ITEM = ITEMS.register("stripped_ember_wood",
            () -> new BlockItem(BlockInit.STRIPPED_EMBER_WOOD.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> EMBER_PLANKS_ITEM = ITEMS.register("ember_planks",
            () -> new BlockItem(BlockInit.EMBER_PLANKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHARRED_LOG_ITEM = ITEMS.register("charred_log",
            () -> new BlockItem(BlockInit.CHARRED_LOG.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHARRED_WOOD_ITEM = ITEMS.register("charred_wood",
            () -> new BlockItem(BlockInit.CHARRED_WOOD.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHARRED_PLANKS_ITEM = ITEMS.register("charred_planks",
            () -> new BlockItem(BlockInit.CHARRED_PLANKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> HELLSTONE_ITEM = ITEMS.register("hellstone",
            () -> new BlockItem(BlockInit.HELLSTONE.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> HELLSTONE_BRICKS_ITEM = ITEMS.register("hellstone_bricks",
            () -> new BlockItem(BlockInit.HELLSTONE_BRICKS.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> ASHES = ITEMS.register("ashes",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> ASHEN_BRICKS_ITEM = ITEMS.register("ashen_bricks_soft",
            () -> new BlockItem(BlockInit.ASHEN_BRICKS.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> ASHEN_BRICKS_HARD_ITEM = ITEMS.register("ashen_bricks_hard",
            () -> new BlockItem(BlockInit.ASHEN_BRICKS_HARD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> ASHEN_BRICKS_SMALL_ITEM = ITEMS.register("ashen_bricks_small",
            () -> new BlockItem(BlockInit.ASHEN_BRICKS_SMALL.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> ASHEN_TILE_ITEM = ITEMS.register("ashen_tile",
            () -> new BlockItem(BlockInit.ASHEN_TILE.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> ASHEN_TILE_SMALL_ITEM = ITEMS.register("ashen_tile_small",
            () -> new BlockItem(BlockInit.ASHEN_TILE_SMALL.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> ASHEN_TILE_TINY_ITEM = ITEMS.register("ashen_tile_tiny",
            () -> new BlockItem(BlockInit.ASHEN_TILE_TINY.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> IGNIS_SHARD = ITEMS.register("ignis_shard",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> IGNIS_GEM_BLOCK_ITEM = ITEMS.register("ignis_gem_block",
            () -> new BlockItem(BlockInit.IGNIS_GEM_BLOCK.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BUDDING_IGNIS_GEM_BLOCK_ITEM = ITEMS.register("budding_ignis",
            () -> new BlockItem(BlockInit.BUDDING_IGNIS_GEM_BLOCK.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> IGNIS_CLUSTER_ITEM = ITEMS.register("ignis_cluster",
            () -> new BlockItem(BlockInit.IGNIS_CLUSTER.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SMALL_IGNIS_BUD_ITEM = ITEMS.register("small_ignis_bud",
            () -> new BlockItem(BlockInit.SMALL_IGNIS_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> MEDIUM_IGNIS_BUD_ITEM = ITEMS.register("medium_ignis_bud",
            () -> new BlockItem(BlockInit.MEDIUM_IGNIS_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> LARGE_IGNIS_BUD_ITEM = ITEMS.register("large_ignis_bud",
            () -> new BlockItem(BlockInit.LARGE_IGNIS_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static Item.Properties defaultItemProperties() {
        return new Item.Properties().tab(AbIgne.itemGroup);
    }
}
