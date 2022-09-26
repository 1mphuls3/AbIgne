package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.item.*;
import com.github.Imphuls3.abigne.common.item.util.EffectBlockItem;
import com.github.Imphuls3.abigne.common.item.util.FuelItem;
import com.github.Imphuls3.abigne.common.item.util.LoreItem;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings({"UnusedDeclaration"})
public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            AbIgne.MODID);

    public static final RegistryObject<BlockItem> FIRING_BLOCK = ITEMS.register("firing_block",
            () -> new BlockItem(BlockRegistry.FIRING_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> NECROTIC_SHARD = ITEMS.register("necrotic_shard",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> AMETHYST_FRAGMENT = ITEMS.register("amethyst_fragment",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<ForgeSpawnEggItem> SOUL_SPAWN_EGG = ITEMS.register("soul_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.SOUL, 0x377b80, 0x5deaf5,
                    new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> TRANSPORTER = ITEMS.register("transporter",
            () -> new BlockItem(BlockRegistry.TRANSPORTER.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<TransporterBinder> BINDER = ITEMS.register("binder",
            () -> new TransporterBinder(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> FLAME = ITEMS.register("flame",
            () -> new BlockItem(BlockRegistry.FLAME.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    //region plants
    public static final RegistryObject<BlockItem> HEMLOCK = ITEMS.register("hemlock",
            () -> new EffectBlockItem(MobEffects.POISON, BlockRegistry.HEMLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> BELLADONA = ITEMS.register("belladona",
            () -> new EffectBlockItem(MobEffects.POISON, BlockRegistry.BELLADONA.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    //endregion

    public static final RegistryObject<LemegetonItem> LEMEGETON = ITEMS.register("lemegeton",
            () -> new LemegetonItem(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<LemegetonItem> NECRONOMICON = ITEMS.register("necronomicon",
            () -> new LemegetonItem(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_PEDESTAL = ITEMS.register("black_calcite_pedestal",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_PEDESTAL = ITEMS.register("infused_planks_pedestal",
            () -> new BlockItem(BlockRegistry.INFUSED_PLANKS_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSER = ITEMS.register("infuser",
            () -> new BlockItem(BlockRegistry.INFUSER.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> CRUCIBLE = ITEMS.register("crucible",
            () -> new BlockItem(BlockRegistry.CRUCIBLE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> IGNIS_GLASS = ITEMS.register("ignis_infused_glass",
            () -> new BlockItem(BlockRegistry.IGNIS_GLASS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<FuelItem> INFUSED_COAL = ITEMS.register("infused_coal",
            () -> new FuelItem(new Item.Properties().tab(AbIgne.itemGroup), 4800));

    //region tarnished metal
    public static final RegistryObject<BlockItem> TARNISHED_STEEL_BLOCK = ITEMS.register("tarnished_steel_block",
            () -> new BlockItem(BlockRegistry.TARNISHED_STEEL_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<Item> TARNISHED_STEEL_INGOT = ITEMS.register("tarnished_steel_ingot",
            () -> new LoreItem(new Item.Properties().tab(AbIgne.itemGroup), "tarnished_steel_ingot"));
    public static final RegistryObject<Item> TARNISHED_STEEL_NUGGET = ITEMS.register("tarnished_steel_nugget",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<SwordItem> TARNISHED_SWORD = ITEMS.register("tarnished_steel_sword",
            () -> new SwordItem(TierRegistry.TarnishedTier.INSTANCE, 3, -2.2F, new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<ShovelItem> TARNISHED_SHOVEL = ITEMS.register("tarnished_steel_shovel",
            () -> new ShovelItem(TierRegistry.TarnishedTier.INSTANCE, 1.5F, -2.8F, (new Item.Properties()).tab(AbIgne.itemGroup)));
    public static final RegistryObject<PickaxeItem> TARNISHED_PICKAXE = ITEMS.register("tarnished_steel_pickaxe",
            () -> new PickaxeItem(TierRegistry.TarnishedTier.INSTANCE, 1, -2.6F, (new Item.Properties()).tab(AbIgne.itemGroup)));
    public static final RegistryObject<AxeItem> TARNISHED_AXE = ITEMS.register("tarnished_steel_axe",
            () -> new AxeItem(TierRegistry.TarnishedTier.INSTANCE, 6.0F, -2.9F, (new Item.Properties()).tab(AbIgne.itemGroup)));
    public static final RegistryObject<HoeItem> TARNISHED_HOE = ITEMS.register("tarnished_steel_hoe",
            () -> new HoeItem(TierRegistry.TarnishedTier.INSTANCE, -2, -0.8F, (new Item.Properties()).tab(AbIgne.itemGroup)));
    //endregion

    //region infused planks
    public static final RegistryObject<BlockItem> INFUSED_PLANKS = ITEMS.register("infused_planks",
            () -> new BlockItem(BlockRegistry.INFUSED_PLANKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> POLISHED_INFUSED_WOOD = ITEMS.register("polished_infused_wood",
            () -> new BlockItem(BlockRegistry.POLISHED_INFUSED_WOOD.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_TILE = ITEMS.register("infused_wood_tile",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_TILE.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_TILE_2 = ITEMS.register("infused_wood_tile_2",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_TILE_2.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> CHISELED_INFUSED_WOOD = ITEMS.register("chiseled_infused_wood",
            () -> new BlockItem(BlockRegistry.CHISELED_INFUSED_WOOD.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_BEAM = ITEMS.register("infused_wood_beam",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_BEAM.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_BEAM_CAP = ITEMS.register("infused_wood_beam_cap",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_BEAM_CAP.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_PLANKS_SLAB = ITEMS.register("infused_planks_slab",
            () -> new BlockItem(BlockRegistry.INFUSED_PLANKS_SLAB.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> POLISHED_INFUSED_WOOD_SLAB = ITEMS.register("polished_infused_wood_slab",
            () -> new BlockItem(BlockRegistry.POLISHED_INFUSED_WOOD_SLAB.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_TILE_SLAB = ITEMS.register("infused_wood_tile_slab",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_TILE_SLAB.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_TILE2_SLAB = ITEMS.register("infused_wood_tile_2_slab",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_TILE_2_SLAB.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_PLANKS_STAIRS = ITEMS.register("infused_planks_stairs",
            () -> new BlockItem(BlockRegistry.INFUSED_PLANKS_STAIRS.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> POLISHED_INFUSED_WOOD_STAIRS = ITEMS.register("polished_infused_wood_stairs",
            () -> new BlockItem(BlockRegistry.POLISHED_INFUSED_WOOD_STAIRS.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_TILE_STAIRS = ITEMS.register("infused_wood_tile_stairs",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_TILE_STAIRS.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_TILE2_STAIRS = ITEMS.register("infused_wood_tile_2_stairs",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_TILE_2_STAIRS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_LOG = ITEMS.register("infused_log",
            () -> new BlockItem(BlockRegistry.INFUSED_LOG.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> STRIPPED_INFUSED_LOG = ITEMS.register("stripped_infused_log",
            () -> new BlockItem(BlockRegistry.STRIPPED_INFUSED_LOG.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_WOOD_DOOR = ITEMS.register("infused_wood_door",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_DOOR.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_TRAPDOOR = ITEMS.register("infused_wood_trapdoor",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_TRAPDOOR.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    //endregion

    //region black calcite
    public static final RegistryObject<BlockItem> POLISHED_BLACK_CALCITE = ITEMS.register("polished_black_calcite",
            () -> new BlockItem(BlockRegistry.POLISHED_BLACK_CALCITE.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> BLACK_CALCITE_BRICKS = ITEMS.register("black_calcite_bricks",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> BLACK_CALCITE_TILES = ITEMS.register("black_calcite_tiles",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_TILES.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> LARGE_BLACK_CALCITE_BRICKS = ITEMS.register("large_black_calcite_bricks",
            () -> new BlockItem(BlockRegistry.LARGE_BLACK_CALCITE_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> CHISELED_BLACK_CALCITE_BRICKS = ITEMS.register("chiseled_black_calcite_bricks",
            () -> new BlockItem(BlockRegistry.CHISELED_BLACK_CALCITE_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> BLACK_CALCITE_PILLAR = ITEMS.register("black_calcite_pillar",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_PILLAR.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> BLACK_CALCITE_PILLAR_CAP = ITEMS.register("black_calcite_pillar_cap",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_PILLAR_CAP.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> POLISHED_BLACK_CALCITE_SLAB = ITEMS.register("polished_black_calcite_slab",
            () -> new BlockItem(BlockRegistry.POLISHED_BLACK_CALCITE_SLAB.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> BLACK_CALCITE_BRICKS_SLAB = ITEMS.register("black_calcite_bricks_slab",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_BRICKS_SLAB.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> BLACK_CALCITE_TILES_SLAB = ITEMS.register("black_calcite_tiles_slab",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_TILES_SLAB.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> LARGE_BLACK_CALCITE_BRICKS_SLAB = ITEMS.register("large_black_calcite_bricks_slab",
            () -> new BlockItem(BlockRegistry.LARGE_BLACK_CALCITE_BRICKS_SLAB.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> POLISHED_BLACK_CALCITE_STAIR = ITEMS.register("polished_black_calcite_stairs",
            () -> new BlockItem(BlockRegistry.POLISHED_BLACK_CALCITE_STAIRS.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> BLACK_CALCITE_BRICKS_STAIRS = ITEMS.register("black_calcite_bricks_stairs",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_BRICKS_STAIRS.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> BLACK_CALCITE_TILES_STAIRS = ITEMS.register("black_calcite_tiles_stairs",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_TILES_STAIRS.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> LARGE_BLACK_CALCITE_BRICKS_STAIRS = ITEMS.register("large_black_calcite_bricks_stairs",
            () -> new BlockItem(BlockRegistry.LARGE_BLACK_CALCITE_BRICKS_STAIRS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    //endregion black calcite

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
