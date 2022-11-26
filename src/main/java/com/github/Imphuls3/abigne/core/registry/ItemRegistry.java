package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.item.*;
import com.github.Imphuls3.abigne.common.item.util.*;
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

    public static final RegistryObject<BlockItem> FLUID_EXTRACTOR = ITEMS.register("fluid_extractor",
            () -> new BlockItem(BlockRegistry.FLUID_EXTRACTOR.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> ALEMBIC = ITEMS.register("alembic",
            () -> new BlockItem(BlockRegistry.ALEMBIC.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> TANK = ITEMS.register("tank",
            () -> new BlockItem(BlockRegistry.TANK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> COPPER_POT = ITEMS.register("copper_pot",
            () -> new BlockItem(BlockRegistry.COPPER_POT.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> FLUID_PIPE = ITEMS.register("fluid_pipe",
            () -> new BlockItem(BlockRegistry.FLUID_PIPE.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> FLUID_EMITTER = ITEMS.register("fluid_emitter",
            () -> new BlockItem(BlockRegistry.FLUID_EMITTER.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> NECROTIC_SHARD = ITEMS.register("necrotic_shard",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> AMETHYST_FRAGMENT = ITEMS.register("amethyst_fragment",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> MOONSTONE = ITEMS.register("moonstone",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> MOONSTONE_BLOCK = ITEMS.register("moonstone_block",
            () -> new BlockItem(BlockRegistry.MOONSTONE_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<MoonstoneRing> MOONSTONE_RING = ITEMS.register("moonstone_ring",
            () -> new MoonstoneRing(new Item.Properties().tab(AbIgne.itemGroup).stacksTo(1)));

    public static final RegistryObject<Item> SUNSTONE = ITEMS.register("sunstone",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> SUNSTONE_BLOCK = ITEMS.register("sunstone_block",
            () -> new BlockItem(BlockRegistry.SUNSTONE_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<SunstoneRing> SUNSTONE_RING = ITEMS.register("sunstone_ring",
            () -> new SunstoneRing(new Item.Properties().tab(AbIgne.itemGroup).stacksTo(1)));

    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("alchemical_tin_ingot",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<Item> TIN_NUGGET = ITEMS.register("alchemical_tin_nugget",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> TIN_BLOCK = ITEMS.register("alchemical_tin_block",
            () -> new BlockItem(BlockRegistry.TIN_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("alchemical_lead_ingot",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<Item> LEAD_NUGGET = ITEMS.register("alchemical_lead_nugget",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> LEAD_BLOCK = ITEMS.register("alchemical_lead_block",
            () -> new BlockItem(BlockRegistry.LEAD_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("alchemical_silver_ingot",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<Item> SILVER_NUGGET = ITEMS.register("alchemical_silver_nugget",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<Item> SILVER_DUST = ITEMS.register("alchemical_silver_dust",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> SILVER_BLOCK = ITEMS.register("alchemical_silver_block",
            () -> new BlockItem(BlockRegistry.SILVER_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BladeOfTheNecromancer> BLADE_OF_THE_NECROMANCER = ITEMS.register("blade_of_the_necromancer",
            () -> new BladeOfTheNecromancer(TierRegistry.SilverTier.INSTANCE, 1, -3F, new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<LargeSwordItem> SILVER_SWORD = ITEMS.register("silver_sword",
            () -> new LargeSwordItem(TierRegistry.SilverTier.INSTANCE, 6, -3F, new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<PickaxeItem> SILVER_PICKAXE = ITEMS.register("silver_pickaxe",
            () -> new PickaxeItem(TierRegistry.SilverTier.INSTANCE, 1, -2.6F, new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<SwordItem> SILVER_DAGGER = ITEMS.register("silver_dagger",
            () -> new SwordItem(TierRegistry.SilverTier.INSTANCE, 2, -1.8F, new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<TippedSwordItem> POISON_TIPPED_DAGGER = ITEMS.register("poison_tipped_dagger",
            () -> new TippedSwordItem(TierRegistry.SilverTier.INSTANCE, MobEffects.POISON, 10, 0, 1, -1.8F, new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<TippedSwordItem> FROST_TIPPED_DAGGER = ITEMS.register("frost_tipped_dagger",
            () -> new TippedSwordItem(TierRegistry.SilverTier.INSTANCE, MobEffects.MOVEMENT_SLOWDOWN, 10, 2, 1, -1.8F, new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> IRON_DUST = ITEMS.register("iron_dust",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<Item> IRON_OXIDE = ITEMS.register("iron_oxide",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> CINNABAR = ITEMS.register("cinnabar",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> CINNABAR_ORE = ITEMS.register("cinnabar_ore",
            () -> new BlockItem(BlockRegistry.CINNABAR_ORE.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<Item> MERCURY = ITEMS.register("mercury",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<Item> POTASH = ITEMS.register("potash",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<Item> SALT = ITEMS.register("salt",
            () -> new Item(new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> SALT_BLOCK = ITEMS.register("salt_block",
            () -> new BlockItem(BlockRegistry.SALT_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> POLISHED_SALT_BLOCK = ITEMS.register("polished_salt_block",
            () -> new BlockItem(BlockRegistry.POLISHED_SALT_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> SALT_BRICKS = ITEMS.register("salt_bricks",
            () -> new BlockItem(BlockRegistry.SALT_BRICKS.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> CHISELED_SALT_BLOCK = ITEMS.register("chiseled_salt_block",
            () -> new BlockItem(BlockRegistry.CHISELED_SALT_BLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<ForgeSpawnEggItem> SOUL_SPAWN_EGG = ITEMS.register("soul_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.SOUL, 0x377b80, 0x5deaf5,
                    new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> FLAME = ITEMS.register("flame",
            () -> new BlockItem(BlockRegistry.FLAME.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<SilverNitrateBag> SILVER_NITRATE_BAG = ITEMS.register("silver_nitrate_bag",
            () -> new SilverNitrateBag(new Item.Properties().tab(AbIgne.itemGroup)));
    //Plants
    public static final RegistryObject<BlockItem> HEMLOCK = ITEMS.register("hemlock",
            () -> new EffectBlockItem(MobEffects.POISON, BlockRegistry.HEMLOCK.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> BELLADONA = ITEMS.register("belladona",
            () -> new EffectBlockItem(MobEffects.POISON, BlockRegistry.BELLADONA.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> FROST_BLOOM = ITEMS.register("frost_bloom",
            () -> new EffectBlockItem(MobEffects.MOVEMENT_SLOWDOWN, BlockRegistry.FROST_BLOOM.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    //

    public static final RegistryObject<LemegetonItem> LEMEGETON = ITEMS.register("lemegeton",
            () -> new LemegetonItem(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<LemegetonItem> NECRONOMICON = ITEMS.register("necronomicon",
            () -> new LemegetonItem(new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> BLACK_CALCITE_PEDESTAL = ITEMS.register("black_calcite_pedestal",
            () -> new BlockItem(BlockRegistry.BLACK_CALCITE_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_PEDESTAL = ITEMS.register("infused_wood_pedestal",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_PEDESTAL.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSER = ITEMS.register("infuser",
            () -> new BlockItem(BlockRegistry.INFUSER.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_GLASS = ITEMS.register("infused_glass",
            () -> new BlockItem(BlockRegistry.INFUSED_GLASS.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    //Infused planks
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
    public static final RegistryObject<BlockItem> INFUSED_WOOD = ITEMS.register("infused_wood",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> STRIPPED_INFUSED_WOOD = ITEMS.register("stripped_infused_wood",
            () -> new BlockItem(BlockRegistry.STRIPPED_INFUSED_WOOD.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> HICKORY_LEAVES = ITEMS.register("hickory_leaves",
            () -> new BlockItem(BlockRegistry.HICKORY_LEAVES.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_WOOD_DOOR = ITEMS.register("infused_wood_door",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_DOOR.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    public static final RegistryObject<BlockItem> INFUSED_WOOD_TRAPDOOR = ITEMS.register("infused_wood_trapdoor",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_TRAPDOOR.get(), new Item.Properties().tab(AbIgne.itemGroup)));

    public static final RegistryObject<BlockItem> INFUSED_WOOD_TABLE = ITEMS.register("infused_wood_planks_table",
            () -> new BlockItem(BlockRegistry.INFUSED_WOOD_TABLE.get(), new Item.Properties().tab(AbIgne.itemGroup)));
    //
    //Black calcite
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
    //

    public static final RegistryObject<BucketItem> MOLTEN_SILVER_BUCKET = ITEMS.register("molten_alchemical_silver_bucket",
            () -> new BucketItem(FluidRegistry.SOURCE_MOLTEN_SILVER, new Item.Properties().tab(AbIgne.itemGroup).craftRemainder(Items.BUCKET).stacksTo(1)));

    public static Item.Properties defaultItemProperties() {
        return new Item.Properties().tab(AbIgne.itemGroup);
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
