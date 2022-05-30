package com.Imphuls3.abigne.core.init;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.block.custom.FuelItem;
import com.Imphuls3.abigne.common.item.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            AbIgne.MOD_ID);

    public static final RegistryObject<Wand> WAND = ITEMS.register("wand",
            () -> new Wand(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<WandPart> EMBERWOOD_HANDLE = ITEMS.register("emberwood_handle",
            () -> new WandPart(1, 1000, 100, "handle", new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> TRANSPORTER_ITEM = ITEMS.register("transporter",
            () -> new BlockItem(BlockInit.TRANSPORTER.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> VOID_ITEM = ITEMS.register("void",
            () -> new BlockItem(BlockInit.VOID.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<TransporterBinder> BINDER = ITEMS.register("binder",
            () -> new TransporterBinder(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> WALL_ITEM = ITEMS.register("wall_lamp",
            () -> new BlockItem(BlockInit.WALL.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> PIPE_ITEM = ITEMS.register("pipe",
            () -> new BlockItem(BlockInit.PIPE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<PhilosophiaItem> PHILOSOPHIA = ITEMS.register("philosophia",
            () -> new PhilosophiaItem(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<IgnisShield> IGNIS_SHIELD = ITEMS.register("ignis_shield",
            () -> new IgnisShield(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> PEDESTAL_ITEM = ITEMS.register("pedestal",
            () -> new BlockItem(BlockInit.PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSER_ITEM = ITEMS.register("infuser",
            () -> new BlockItem(BlockInit.INFUSER.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CRUCIBLE_ITEM = ITEMS.register("crucible",
            () -> new BlockItem(BlockInit.CRUCIBLE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> RITUAL_PEDESTAL_ITEM = ITEMS.register("ritual",
            () -> new BlockItem(BlockInit.RITUAL_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));


    public static final RegistryObject<BlockItem> IGNIS_GLASS_ITEM = ITEMS.register("ignis_infused_glass",
            () -> new BlockItem(BlockInit.IGNIS_GLASS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<FuelItem> INFUSED_COAL = ITEMS.register("infused_coal",
            () -> new FuelItem(new Item.Properties().tab(AbIgne.itemGroup), 4800));

    public static final RegistryObject<BlockItem> IGNIS_BLOCK_ITEM = ITEMS.register("ignis_block",
            () -> new BlockItem(BlockInit.IGNIS_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> IGNIS_INGOT = ITEMS.register("ignis_ingot",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> IGNIS_NUGGET = ITEMS.register("ignis_nugget",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> TEMPERED_BLOCK_ITEM = ITEMS.register("tempered_block",
            () -> new BlockItem(BlockInit.TEMPERED_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> TEMPERED_INGOT = ITEMS.register("tempered_ingot",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> TEMPERED_NUGGET = ITEMS.register("tempered_nugget",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

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

    public static final RegistryObject<BlockItem> EMBER_PLANKS_TILE_ITEM = ITEMS.register("ember_planks_tile",
            () -> new BlockItem(BlockInit.EMBER_PLANKS_TILE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHARRED_LOG_ITEM = ITEMS.register("charred_log",
            () -> new BlockItem(BlockInit.CHARRED_LOG.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHARRED_WOOD_ITEM = ITEMS.register("charred_wood",
            () -> new BlockItem(BlockInit.CHARRED_WOOD.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHARRED_PLANKS_ITEM = ITEMS.register("charred_planks",
            () -> new BlockItem(BlockInit.CHARRED_PLANKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_ITEM = ITEMS.register("black_calcite",
            () -> new BlockItem(BlockInit.BLACK_CALCITE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> POLISHED_BLACK_CALCITE_ITEM = ITEMS.register("polished_black_calcite",
            () -> new BlockItem(BlockInit.POLISHED_BLACK_CALCITE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_TILES_ITEM = ITEMS.register("black_calcite_tiles",
            () -> new BlockItem(BlockInit.BLACK_CALCITE_TILES.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> POLISHED_BLACK_CALCITE_INDENT_ITEM = ITEMS.register("polished_black_calcite_indent",
            () -> new BlockItem(BlockInit.POLISHED_BLACK_CALCITE_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_TILES_INDENT_ITEM = ITEMS.register("black_calcite_tiles_indent",
            () -> new BlockItem(BlockInit.BLACK_CALCITE_TILES_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_BRICKS_ITEM = ITEMS.register("black_calcite_bricks",
            () -> new BlockItem(BlockInit.BLACK_CALCITE_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHISELED_BLACK_CALCITE_BRICKS_ITEM = ITEMS.register("chiseled_black_calcite_bricks",
            () -> new BlockItem(BlockInit.CHISELED_BLACK_CALCITE_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_PILLAR_ITEM = ITEMS.register("black_calcite_pillar",
            () -> new BlockItem(BlockInit.BLACK_CALCITE_PILLAR.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_PILLAR_CAP_ITEM = ITEMS.register("black_calcite_pillar_cap",
            () -> new BlockItem(BlockInit.BLACK_CALCITE_PILLAR_CAP.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> ASHES = ITEMS.register("ashes",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> PYROLITE_SHARD = ITEMS.register("pyrolite_shard",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> PYROLITE_GEM_BLOCK_ITEM = ITEMS.register("pyrolite_gem_block",
            () -> new BlockItem(BlockInit.PYROLITE_GEM_BLOCK.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BUDDING_PYROLITE_GEM_BLOCK_ITEM = ITEMS.register("budding_pyrolite",
            () -> new BlockItem(BlockInit.BUDDING_PYROLITE_GEM_BLOCK.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> PYROLITE_CLUSTER_ITEM = ITEMS.register("pyrolite_cluster",
            () -> new BlockItem(BlockInit.PYROLITE_CLUSTER.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SMALL_PYROLITE_BUD_ITEM = ITEMS.register("small_pyrolite_bud",
            () -> new BlockItem(BlockInit.SMALL_PYROLITE_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> MEDIUM_PYROLITE_BUD_ITEM = ITEMS.register("medium_pyrolite_bud",
            () -> new BlockItem(BlockInit.MEDIUM_PYROLITE_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> LARGE_PYROLITE_BUD_ITEM = ITEMS.register("large_pyrolite_bud",
            () -> new BlockItem(BlockInit.LARGE_PYROLITE_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static Item.Properties defaultItemProperties() {
        return new Item.Properties().tab(AbIgne.itemGroup);
    }
}
