package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.client.particle.ParticleColor;
import com.github.Imphuls3.abigne.common.block.*;
import com.github.Imphuls3.abigne.common.block.util.*;
import com.github.Imphuls3.abigne.common.block.util.TwoLayeredBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import java.util.function.ToIntFunction;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AbIgne.MODID);

    private static ToIntFunction<BlockState> litBlockEmission(int emmision) {
        return (state) -> {
            return state.getValue(BlockStateProperties.LIT) ? emmision : 0;
        };
    }
    public static final RegistryObject<FiringBlock> FIRING_BLOCK = BLOCKS.register("firing_block", () -> new FiringBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> FLAME = BLOCKS.register("flame", () -> new FlameBlock(BlockBehaviour.Properties.of(Material.AIR).noCollission().instabreak()));

    //region plants
    public static final RegistryObject<FlowerBlock> HEMLOCK = BLOCKS.register("hemlock",
            () -> new InflictingFlower(MobEffects.POISON, 8, new ParticleColor(125, 255, 15), BlockBehaviour.Properties.copy(Blocks.POPPY).dynamicShape().noCollission()));
    public static final RegistryObject<FlowerBlock> BELLADONA = BLOCKS.register("belladona",
            () -> new InflictingFlower(MobEffects.POISON, 8, new ParticleColor(125, 255, 15), BlockBehaviour.Properties.copy(Blocks.POPPY).dynamicShape().noCollission()));
    //endregion

    public static final RegistryObject<Block> BLACK_CALCITE_PEDESTAL = BLOCKS.register("black_calcite_pedestal", () -> new PedestalBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> INFUSED_PLANKS_PEDESTAL = BLOCKS.register("infused_planks_pedestal", () -> new PedestalBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> TRANSPORTER = BLOCKS.register("transporter", () -> new ItemTransporterBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CRUCIBLE = BLOCKS.register("crucible", () -> new CrucibleBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> INFUSER = BLOCKS.register("infuser", () -> new InfuserBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));

    public static final RegistryObject<Block> IGNIS_GLASS = BLOCKS.register("ignis_infused_glass",
            () -> new StainedGlassBlock(DyeColor.RED, BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS).lightLevel((light) -> {
                return 5;
            })));

    public static final RegistryObject<Block> TARNISHED_STEEL_BLOCK = BLOCKS.register("tarnished_steel_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F, 6.0F)));

    //region infused planks
    public static final RegistryObject<Block> INFUSED_PLANKS = BLOCKS.register("infused_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F)));
    public static final RegistryObject<Block> POLISHED_INFUSED_WOOD = BLOCKS.register("polished_infused_wood",
            () -> new Block(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<Block> INFUSED_WOOD_TILE = BLOCKS.register("infused_wood_tile",
            () -> new Block(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<Block> INFUSED_WOOD_TILE_2 = BLOCKS.register("infused_wood_tile_2",
            () -> new Block(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<Block> CHISELED_INFUSED_WOOD = BLOCKS.register("chiseled_infused_wood",
            () -> new Block(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<Block> INFUSED_WOOD_BEAM = BLOCKS.register("infused_wood_beam",
            () -> new FullyRotatingPillarBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<Block> INFUSED_WOOD_BEAM_CAP = BLOCKS.register("infused_wood_beam_cap",
            () -> new FullyRotatingPillarBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));

    public static final RegistryObject<Block> INFUSED_PLANKS_SLAB = BLOCKS.register("infused_planks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<Block> POLISHED_INFUSED_WOOD_SLAB = BLOCKS.register("polished_infused_wood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<Block> INFUSED_WOOD_TILE_SLAB = BLOCKS.register("infused_wood_tile_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<Block> INFUSED_WOOD_TILE_2_SLAB = BLOCKS.register("infused_wood_tile_2_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));

    public static final RegistryObject<Block> INFUSED_PLANKS_STAIRS = BLOCKS.register("infused_planks_stairs",
            () -> new StairBlock(() -> INFUSED_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<Block> POLISHED_INFUSED_WOOD_STAIRS = BLOCKS.register("polished_infused_wood_stairs",
            () -> new StairBlock(() -> INFUSED_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<Block> INFUSED_WOOD_TILE_STAIRS = BLOCKS.register("infused_wood_tile_stairs",
            () -> new StairBlock(() -> INFUSED_WOOD_TILE.get().defaultBlockState(), BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<Block> INFUSED_WOOD_TILE_2_STAIRS = BLOCKS.register("infused_wood_tile_2_stairs",
            () -> new StairBlock(() -> INFUSED_WOOD_TILE_2.get().defaultBlockState(), BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));

    public static final RegistryObject<RotatedPillarBlock> STRIPPED_INFUSED_LOG = BLOCKS.register("stripped_infused_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));
    public static final RegistryObject<AbIgneLogBlock> INFUSED_LOG = BLOCKS.register("infused_log",
            () -> new AbIgneLogBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get()), STRIPPED_INFUSED_LOG));

    public static final RegistryObject<DoorBlock> INFUSED_WOOD_DOOR = BLOCKS.register("infused_wood_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> INFUSED_WOOD_TRAPDOOR = BLOCKS.register("infused_wood_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get()).noOcclusion()));
    //endregion

    //region black calcite
    public static final RegistryObject<Block> POLISHED_BLACK_CALCITE = BLOCKS.register("polished_black_calcite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS).requiresCorrectToolForDrops().strength(1.5F, 9.0F)));
    public static final RegistryObject<Block> BLACK_CALCITE_BRICKS = BLOCKS.register("black_calcite_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));
    public static final RegistryObject<Block> CHISELED_BLACK_CALCITE_BRICKS = BLOCKS.register("chiseled_black_calcite_bricks",
            () -> new TwoLayeredBlock(BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));
    public static final RegistryObject<Block> BLACK_CALCITE_TILES = BLOCKS.register("black_calcite_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));
    public static final RegistryObject<Block> LARGE_BLACK_CALCITE_BRICKS = BLOCKS.register("large_black_calcite_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));
    public static final RegistryObject<Block> BLACK_CALCITE_PILLAR = BLOCKS.register("black_calcite_pillar",
            () -> new FullyRotatingPillarBlock(BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));
    public static final RegistryObject<Block> BLACK_CALCITE_PILLAR_CAP = BLOCKS.register("black_calcite_pillar_cap",
            () -> new FullyRotatingPillarBlock(BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));

    public static final RegistryObject<Block> POLISHED_BLACK_CALCITE_SLAB = BLOCKS.register("polished_black_calcite_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));
    public static final RegistryObject<Block> BLACK_CALCITE_BRICKS_SLAB = BLOCKS.register("black_calcite_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));
    public static final RegistryObject<Block> BLACK_CALCITE_TILES_SLAB = BLOCKS.register("black_calcite_tiles_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));
    public static final RegistryObject<Block> LARGE_BLACK_CALCITE_BRICKS_SLAB = BLOCKS.register("large_black_calcite_bricks_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));

    public static final RegistryObject<Block> POLISHED_BLACK_CALCITE_STAIRS = BLOCKS.register("polished_black_calcite_stairs",
            () -> new StairBlock(() -> POLISHED_BLACK_CALCITE.get().defaultBlockState(), BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));
    public static final RegistryObject<Block> BLACK_CALCITE_BRICKS_STAIRS = BLOCKS.register("black_calcite_bricks_stairs",
            () -> new StairBlock(() -> BLACK_CALCITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));
    public static final RegistryObject<Block> BLACK_CALCITE_TILES_STAIRS = BLOCKS.register("black_calcite_tiles_stairs",
            () -> new StairBlock(() -> BLACK_CALCITE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));
    public static final RegistryObject<Block> LARGE_BLACK_CALCITE_BRICKS_STAIRS = BLOCKS.register("large_black_calcite_bricks_stairs",
            () -> new StairBlock(() -> LARGE_BLACK_CALCITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(POLISHED_BLACK_CALCITE.get())));
    //endregion black calcite

    public static final RegistryObject<Block> PYROLITE_GEM_BLOCK = BLOCKS.register("pyrolite_gem_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));

    public static final RegistryObject<BuddingPyroliteBlock> BUDDING_PYROLITE_GEM_BLOCK = BLOCKS.register("budding_pyrolite",
            () -> new BuddingPyroliteBlock(BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST)));

    public static final RegistryObject<PyroliteClusterBlock> PYROLITE_CLUSTER = BLOCKS.register("pyrolite_cluster",
            () -> new PyroliteClusterBlock(7, 3, BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((p_152632_) -> {
                return 5;
            })));

    public static final RegistryObject<PyroliteClusterBlock> SMALL_PYROLITE_BUD = BLOCKS.register("small_pyrolite_bud",
            () -> new PyroliteClusterBlock(5, 3, BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((p_152632_) -> {
                return 4;
            })));

    public static final RegistryObject<PyroliteClusterBlock> MEDIUM_PYROLITE_BUD = BLOCKS.register("medium_pyrolite_bud",
            () -> new PyroliteClusterBlock(4, 3, BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((p_152632_) -> {
                return 2;
            })));

    public static final RegistryObject<Block> LARGE_PYROLITE_BUD = BLOCKS.register("large_pyrolite_bud",
            () -> new PyroliteClusterBlock(3, 4, BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((p_152632_) -> {
                return 1;
            })));
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}