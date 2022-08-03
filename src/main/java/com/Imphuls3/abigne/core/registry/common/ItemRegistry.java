package com.Imphuls3.abigne.core.registry.common;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.item.FuelItem;
import com.Imphuls3.abigne.common.item.*;
import com.Imphuls3.abigne.common.item.wand.Wand;
import com.Imphuls3.abigne.common.item.wand.WandPart;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            AbIgne.MODID);

    public static final RegistryObject<ForgeSpawnEggItem> SOUL_SPAWN_EGG = ITEMS.register("soul_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistration.SOUL, 0x377b80, 0x5deaf5,
                    new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<FireBallCaster> FIRE_WAND = ITEMS.register("fire_wand",
            () -> new FireBallCaster(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> ENFLAMED_ASH = ITEMS.register("enflamed_ash",
            () -> new BlockItem(BlockRegistry.ENFLAMED_ASH.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<SoulStaff> SOUL_STAFF = ITEMS.register("soul_staff",
            () -> new SoulStaff(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Wand> WAND = ITEMS.register("wand",
            () -> new Wand(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<WandPart> EMBERWOOD_HANDLE = ITEMS.register("emberwood_handle",
            () -> new WandPart(1, 1000, "handle", new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> TORCH = ITEMS.register("calming_torch",
            () -> new BlockItem(BlockRegistry.TORCH.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> TRANSPORTER = ITEMS.register("transporter",
            () -> new BlockItem(BlockRegistry.TRANSPORTER.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> VOID = ITEMS.register("void",
            () -> new BlockItem(BlockRegistry.VOID.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<TransporterBinder> BINDER = ITEMS.register("binder",
            () -> new TransporterBinder(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> WALL = ITEMS.register("wall_lamp",
            () -> new BlockItem(BlockRegistry.WALL.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> PIPE = ITEMS.register("pipe",
            () -> new BlockItem(BlockRegistry.PIPE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<PhilosophiaItem> PHILOSOPHIA = ITEMS.register("philosophia",
            () -> new PhilosophiaItem(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<IgnisShield> IGNIS_SHIELD = ITEMS.register("ignis_shield",
            () -> new IgnisShield(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CALCITE_PEDESTAL = ITEMS.register("calcite_pedestal",
            () -> new BlockItem(BlockRegistry.CALCITE_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> BLACK_CALCITE_PEDESTAL = ITEMS.register("black_calcite_pedestal",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_PEDESTAL = ITEMS.register("infused_planks_pedestal",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> SOUL_INFUSED_WOOD_PEDESTAL = ITEMS.register("soul_infused_planks_pedestal",
            () -> new BlockItem(BlockRegistry.SOUL_INFUSED_WOOD_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSER = ITEMS.register("infuser",
            () -> new BlockItem(BlockRegistry.INFUSER.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CRUCIBLE = ITEMS.register("crucible",
            () -> new BlockItem(BlockRegistry.CRUCIBLE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> RITUAL_PEDESTAL = ITEMS.register("ritual",
            () -> new BlockItem(BlockRegistry.RITUAL_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));


    public static final RegistryObject<BlockItem> IGNIS_GLASS = ITEMS.register("ignis_infused_glass",
            () -> new BlockItem(BlockRegistry.IGNIS_GLASS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<FuelItem> INFUSED_COAL = ITEMS.register("infused_coal",
            () -> new FuelItem(new Item.Properties().tab(AbIgne.itemGroup), 4800));

    public static final RegistryObject<BlockItem> IGNIS_BLOCK = ITEMS.register("ignis_block",
            () -> new BlockItem(BlockRegistry.IGNIS_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> IGNIS_INGOT = ITEMS.register("ignis_ingot",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> IGNIS_NUGGET = ITEMS.register("ignis_nugget",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_PLANKS = ITEMS.register("infused_planks",
            () -> new BlockItem(BlockRegistry.INFUSED_PLANKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_PLANKS_TILE = ITEMS.register("infused_planks_tile",
            () -> new BlockItem(BlockRegistry.INFUSED_PLANKS_TILE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_PLANKS_TILE2 = ITEMS.register("infused_planks_tile_2",
            () -> new BlockItem(BlockRegistry.INFUSED_PLANKS_TILE2.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_PLANKS_TILE_INDENT = ITEMS.register("infused_planks_tile_indent",
            () -> new BlockItem(BlockRegistry.INFUSED_PLANKS_TILE_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_LOG = ITEMS.register("infused_log",
            () -> new BlockItem(BlockRegistry.INFUSED_LOG.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> STRIPPED_INFUSED_LOG = ITEMS.register("stripped_infused_log",
            () -> new BlockItem(BlockRegistry.STRIPPED_INFUSED_LOG.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SOUL_INFUSED_PLANKS = ITEMS.register("soul_infused_planks",
            () -> new BlockItem(BlockRegistry.SOUL_INFUSED_PLANKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SOUL_INFUSED_PLANKS_TILE = ITEMS.register("soul_infused_planks_tile",
            () -> new BlockItem(BlockRegistry.SOUL_INFUSED_PLANKS_TILE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SOUL_INFUSED_PLANKS_TILE_2 = ITEMS.register("soul_infused_planks_tile_2",
            () -> new BlockItem(BlockRegistry.SOUL_INFUSED_PLANKS_TILE_2.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SOUL_INFUSED_PLANKS_TILE_INDENT = ITEMS.register("soul_infused_planks_tile_indent",
            () -> new BlockItem(BlockRegistry.SOUL_INFUSED_PLANKS_TILE_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SOUL_INFUSED_LOG = ITEMS.register("soul_infused_log",
            () -> new BlockItem(BlockRegistry.SOUL_INFUSED_LOG.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> STRIPPED_SOUL_INFUSED_LOG = ITEMS.register("stripped_soul_infused_log",
            () -> new BlockItem(BlockRegistry.STRIPPED_SOUL_INFUSED_LOG.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE = ITEMS.register("black_calcite",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> POLISHED_BLACK_CALCITE = ITEMS.register("polished_black_calcite",
            () -> new BlockItem(BlockRegistry.POLISHED_BLACK_CALCITE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_TILES = ITEMS.register("black_calcite_tiles",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_TILES.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> POLISHED_BLACK_CALCITE_INDENT = ITEMS.register("polished_black_calcite_indent",
            () -> new BlockItem(BlockRegistry.POLISHED_BLACK_CALCITE_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_TILES_INDENT = ITEMS.register("black_calcite_tiles_indent",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_TILES_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_BRICKS = ITEMS.register("black_calcite_bricks",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHISELED_BLACK_CALCITE_BRICKS = ITEMS.register("chiseled_black_calcite_bricks",
            () -> new BlockItem(BlockRegistry.CHISELED_BLACK_CALCITE_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_PILLAR = ITEMS.register("black_calcite_pillar",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_PILLAR.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_PILLAR_CAP = ITEMS.register("black_calcite_pillar_cap",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_PILLAR_CAP.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> POLISHED_CALCITE = ITEMS.register("polished_calcite",
            () -> new BlockItem(BlockRegistry.POLISHED_CALCITE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CALCITE_TILES = ITEMS.register("calcite_tiles",
            () -> new BlockItem(BlockRegistry.CALCITE_TILES.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> POLISHED_CALCITE_INDENT = ITEMS.register("polished_calcite_indent",
            () -> new BlockItem(BlockRegistry.POLISHED_CALCITE_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CALCITE_TILES_INDENT = ITEMS.register("calcite_tiles_indent",
            () -> new BlockItem(BlockRegistry.CALCITE_TILES_INDENT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CALCITE_BRICKS = ITEMS.register("calcite_bricks",
            () -> new BlockItem(BlockRegistry.CALCITE_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CHISELED_CALCITE_BRICKS = ITEMS.register("chiseled_calcite_bricks",
            () -> new BlockItem(BlockRegistry.CHISELED_CALCITE_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CALCITE_PILLAR = ITEMS.register("calcite_pillar",
            () -> new BlockItem(BlockRegistry.CALCITE_PILLAR.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CALCITE_PILLAR_CAP = ITEMS.register("calcite_pillar_cap",
            () -> new BlockItem(BlockRegistry.CALCITE_PILLAR_CAP.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> PYROLITE_SHARD = ITEMS.register("pyrolite_shard",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> PYROLITE_SHARD_ACTIVE = ITEMS.register("pyrolite_shard_active",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> PYROLITE_GEM = ITEMS.register("pyrolite_gem",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> PYRO_DUST = ITEMS.register("pyro_dust",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> PYROLITE_GEM_BLOCK = ITEMS.register("pyrolite_gem_block",
            () -> new BlockItem(BlockRegistry.PYROLITE_GEM_BLOCK.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BUDDING_PYROLITE_GEM_BLOCK = ITEMS.register("budding_pyrolite",
            () -> new BlockItem(BlockRegistry.BUDDING_PYROLITE_GEM_BLOCK.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> PYROLITE_CLUSTER = ITEMS.register("pyrolite_cluster",
            () -> new BlockItem(BlockRegistry.PYROLITE_CLUSTER.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> SMALL_PYROLITE_BUD = ITEMS.register("small_pyrolite_bud",
            () -> new BlockItem(BlockRegistry.SMALL_PYROLITE_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> MEDIUM_PYROLITE_BUD = ITEMS.register("medium_pyrolite_bud",
            () -> new BlockItem(BlockRegistry.MEDIUM_PYROLITE_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> LARGE_PYROLITE_BUD = ITEMS.register("large_pyrolite_bud",
            () -> new BlockItem(BlockRegistry.LARGE_PYROLITE_BUD.get(),new Item.Properties().tab(AbIgne.itemGroup)));

    public static Item.Properties defaultItemProperties() {
        return new Item.Properties().tab(AbIgne.itemGroup);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
