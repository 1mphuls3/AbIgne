package com.Imphuls3.abigne.core.init;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.block.custom.FuelItem;
import com.Imphuls3.abigne.common.entity.EntityInit;
import com.Imphuls3.abigne.common.item.*;
import com.Imphuls3.abigne.common.item.wand.Wand;
import com.Imphuls3.abigne.common.item.wand.WandPart;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            AbIgne.MODID);

    public static final RegistryObject<ForgeSpawnEggItem> SOUL_SPAWN_EGG = ITEMS.register("soul_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityInit.SOUL, 0x377b80, 0x5deaf5,
                    new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<FireBallCaster> FIRE_WAND = ITEMS.register("fire_wand",
            () -> new FireBallCaster(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> ENFLAMED_ASH = ITEMS.register("enflamed_ash",
            () -> new BlockItem(BlockInit.ENFLAMED_ASH.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<SoulStaff> SOUL_STAFF = ITEMS.register("soul_staff",
            () -> new SoulStaff(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Wand> WAND = ITEMS.register("wand",
            () -> new Wand(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<WandPart> EMBERWOOD_HANDLE = ITEMS.register("emberwood_handle",
            () -> new WandPart(1, 1000, "handle", new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> TORCH = ITEMS.register("calming_torch",
            () -> new BlockItem(BlockInit.TORCH.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> TRANSPORTER = ITEMS.register("transporter",
            () -> new BlockItem(BlockInit.TRANSPORTER.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> VOID = ITEMS.register("void",
            () -> new BlockItem(BlockInit.VOID.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<TransporterBinder> BINDER = ITEMS.register("binder",
            () -> new TransporterBinder(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> WALL = ITEMS.register("wall_lamp",
            () -> new BlockItem(BlockInit.WALL.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> PIPE = ITEMS.register("pipe",
            () -> new BlockItem(BlockInit.PIPE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<PhilosophiaItem> PHILOSOPHIA = ITEMS.register("philosophia",
            () -> new PhilosophiaItem(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<IgnisShield> IGNIS_SHIELD = ITEMS.register("ignis_shield",
            () -> new IgnisShield(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_PEDESTAL = ITEMS.register("black_calcite_pedestal",
            () -> new BlockItem(BlockInit.BLACK_CALCITE_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_PEDESTAL = ITEMS.register("infused_planks_pedestal",
            () -> new BlockItem(BlockInit.INFUSED_WOOD_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> SOUL_INFUSED_WOOD_PEDESTAL = ITEMS.register("soul_infused_planks_pedestal",
            () -> new BlockItem(BlockInit.SOUL_INFUSED_WOOD_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSER = ITEMS.register("infuser",
            () -> new BlockItem(BlockInit.INFUSER.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CRUCIBLE = ITEMS.register("crucible",
            () -> new BlockItem(BlockInit.CRUCIBLE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> RITUAL_PEDESTAL = ITEMS.register("ritual",
            () -> new BlockItem(BlockInit.RITUAL_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));


    public static final RegistryObject<BlockItem> IGNIS_GLASS = ITEMS.register("ignis_infused_glass",
            () -> new BlockItem(BlockInit.IGNIS_GLASS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<FuelItem> INFUSED_COAL = ITEMS.register("infused_coal",
            () -> new FuelItem(new Item.Properties().tab(AbIgne.itemGroup), 4800));

    public static final RegistryObject<BlockItem> IGNIS_BLOCK = ITEMS.register("ignis_block",
            () -> new BlockItem(BlockInit.IGNIS_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> IGNIS_INGOT = ITEMS.register("ignis_ingot",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> IGNIS_NUGGET = ITEMS.register("ignis_nugget",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> TEMPERED_BLOCK = ITEMS.register("tempered_block",
            () -> new BlockItem(BlockInit.TEMPERED_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> TEMPERED_INGOT = ITEMS.register("tempered_ingot",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> TEMPERED_NUGGET = ITEMS.register("tempered_nugget",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_PLANKS = ITEMS.register("infused_planks",
            () -> new BlockItem(BlockInit.INFUSED_PLANKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_PLANKS_TILE = ITEMS.register("infused_planks_tile",
            () -> new BlockItem(BlockInit.INFUSED_PLANKS_TILE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_PLANKS_TILE_INDENT = ITEMS.register("infused_planks_tile_indent",
            () -> new BlockItem(BlockInit.INFUSED_PLANKS_TILE_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_PLANKS_PILLAR = ITEMS.register("infused_planks_pillar",
            () -> new BlockItem(BlockInit.INFUSED_PLANKS_PILLAR.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SOUL_INFUSED_PLANKS = ITEMS.register("soul_infused_planks",
            () -> new BlockItem(BlockInit.SOUL_INFUSED_PLANKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SOUL_INFUSED_PLANKS_TILE = ITEMS.register("soul_infused_planks_tile",
            () -> new BlockItem(BlockInit.SOUL_INFUSED_PLANKS_TILE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SOUL_INFUSED_PLANKS_TILE_INDENT = ITEMS.register("soul_infused_planks_tile_indent",
            () -> new BlockItem(BlockInit.SOUL_INFUSED_PLANKS_TILE_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SOUL_INFUSED_PLANKS_PILLAR = ITEMS.register("soul_infused_planks_pillar",
            () -> new BlockItem(BlockInit.SOUL_INFUSED_PLANKS_PILLAR.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHARRED_LOG = ITEMS.register("charred_log",
            () -> new BlockItem(BlockInit.CHARRED_LOG.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHARRED_WOOD = ITEMS.register("charred_wood",
            () -> new BlockItem(BlockInit.CHARRED_WOOD.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHARRED_PLANKS = ITEMS.register("charred_planks",
            () -> new BlockItem(BlockInit.CHARRED_PLANKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE = ITEMS.register("black_calcite",
            () -> new BlockItem(BlockInit.BLACK_CALCITE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> POLISHED_BLACK_CALCITE = ITEMS.register("polished_black_calcite",
            () -> new BlockItem(BlockInit.POLISHED_BLACK_CALCITE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_TILES = ITEMS.register("black_calcite_tiles",
            () -> new BlockItem(BlockInit.BLACK_CALCITE_TILES.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> POLISHED_BLACK_CALCITE_INDENT = ITEMS.register("polished_black_calcite_indent",
            () -> new BlockItem(BlockInit.POLISHED_BLACK_CALCITE_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_TILES_INDENT = ITEMS.register("black_calcite_tiles_indent",
            () -> new BlockItem(BlockInit.BLACK_CALCITE_TILES_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_BRICKS = ITEMS.register("black_calcite_bricks",
            () -> new BlockItem(BlockInit.BLACK_CALCITE_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHISELED_BLACK_CALCITE_BRICKS = ITEMS.register("chiseled_black_calcite_bricks",
            () -> new BlockItem(BlockInit.CHISELED_BLACK_CALCITE_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_PILLAR = ITEMS.register("black_calcite_pillar",
            () -> new BlockItem(BlockInit.BLACK_CALCITE_PILLAR.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_PILLAR_CAP = ITEMS.register("black_calcite_pillar_cap",
            () -> new BlockItem(BlockInit.BLACK_CALCITE_PILLAR_CAP.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> ASHES = ITEMS.register("ashes",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> PYROLITE_SHARD = ITEMS.register("pyrolite_shard",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> PYROLITE_GEM_BLOCK = ITEMS.register("pyrolite_gem_block",
            () -> new BlockItem(BlockInit.PYROLITE_GEM_BLOCK.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BUDDING_PYROLITE_GEM_BLOCK = ITEMS.register("budding_pyrolite",
            () -> new BlockItem(BlockInit.BUDDING_PYROLITE_GEM_BLOCK.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> PYROLITE_CLUSTER = ITEMS.register("pyrolite_cluster",
            () -> new BlockItem(BlockInit.PYROLITE_CLUSTER.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SMALL_PYROLITE_BUD = ITEMS.register("small_pyrolite_bud",
            () -> new BlockItem(BlockInit.SMALL_PYROLITE_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> MEDIUM_PYROLITE_BUD = ITEMS.register("medium_pyrolite_bud",
            () -> new BlockItem(BlockInit.MEDIUM_PYROLITE_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> LARGE_PYROLITE_BUD = ITEMS.register("large_pyrolite_bud",
            () -> new BlockItem(BlockInit.LARGE_PYROLITE_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static Item.Properties defaultItemProperties() {
        return new Item.Properties().tab(AbIgne.itemGroup);
    }
}
