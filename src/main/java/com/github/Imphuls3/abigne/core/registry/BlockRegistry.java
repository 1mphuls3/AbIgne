package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.block.*;
import com.github.Imphuls3.abigne.common.block.PipeBlock;
import com.github.Imphuls3.abigne.common.block.util.*;
import com.github.Imphuls3.abigne.common.block.util.TwoLayeredBlock;
import com.github.Imphuls3.abigne.config.FluidTransportConfig;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntFunction;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AbIgne.MODID);

    private static ToIntFunction<BlockState> litBlockEmission(int emmision) {
        return (state) -> {
            return state.getValue(BlockStateProperties.LIT) ? emmision : 0;
        };
    }
    public static final RegistryObject<FiringBlock> FIRING_BLOCK = BLOCKS.register("firing_block", () -> new FiringBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<FluidExtractorBlock> FLUID_EXTRACTOR = BLOCKS.register("fluid_extractor",
            () -> new FluidExtractorBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<AlembicBlock> ALEMBIC = BLOCKS.register("alembic",
            () -> new AlembicBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<TankBlock> TANK = BLOCKS.register("tank",
            () -> new TankBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion()));

    public static final RegistryObject<PipeBlock> FLUID_PIPE = BLOCKS.register("fluid_pipe",
            () -> new PipeBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).noOcclusion()));

    public static final RegistryObject<CopperPotBlock> COPPER_POT = BLOCKS.register("copper_pot",
            () -> new CopperPotBlock(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK).noOcclusion()));

    public static final RegistryObject<FluidEmitterBlock> FLUID_EMITTER = BLOCKS.register("fluid_emitter",
            () -> new FluidEmitterBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));

    public static final RegistryObject<Block> FLAME = BLOCKS.register("flame", () -> new FlameBlock(BlockBehaviour.Properties.copy(Blocks.STRUCTURE_VOID).noOcclusion().noCollission().instabreak()));

    public static final RegistryObject<Block> TIN_BLOCK = BLOCKS.register("alchemical_tin_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F, 6.0F)));

    public static final RegistryObject<Block> LEAD_BLOCK = BLOCKS.register("alchemical_lead_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F, 6.0F)));

    public static final RegistryObject<Block> SILVER_BLOCK = BLOCKS.register("alchemical_silver_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(5.0F, 6.0F)));

    public static final RegistryObject<Block> MOONSTONE_BLOCK = BLOCKS.register("moonstone_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).strength(5.0F, 6.0F)));

    public static final RegistryObject<Block> SUNSTONE_BLOCK = BLOCKS.register("sunstone_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).strength(5.0F, 6.0F)));

    public static final RegistryObject<Block> CINNABAR_ORE = BLOCKS.register("cinnabar_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).strength(3.0F, 3.0F)));

/*
    public static final RegistryObject<Block> CINNABAR_BLOCK = BLOCKS.register("cinnabar_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> MERCURY_BLOCK = BLOCKS.register("mercury_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> SULFUR_BLOCK = BLOCKS.register("sulfur_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));*/
    public static final RegistryObject<Block> SALT_BLOCK = BLOCKS.register("salt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> POLISHED_SALT_BLOCK = BLOCKS.register("polished_salt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> SALT_BRICKS = BLOCKS.register("salt_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> CHISELED_SALT_BLOCK = BLOCKS.register("chiseled_salt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    //Plants
    public static final RegistryObject<FlowerBlock> HEMLOCK = BLOCKS.register("hemlock",
            () -> new InflictingFlower(MobEffects.POISON, 8, new Color(125, 255, 15), BlockBehaviour.Properties.copy(Blocks.POPPY).dynamicShape().noCollission()));
    public static final RegistryObject<FlowerPotBlock> POTTED_HEMLOCK = BLOCKS.register("potted_hemlock",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, HEMLOCK, BlockBehaviour.Properties.copy(Blocks.POPPY).dynamicShape()));
    public static final RegistryObject<FlowerBlock> BELLADONA = BLOCKS.register("belladona",
            () -> new InflictingFlower(MobEffects.POISON, 8, new Color(125, 255, 15), BlockBehaviour.Properties.copy(Blocks.POPPY).dynamicShape().noCollission()));
    public static final RegistryObject<FlowerPotBlock> POTTED_BELLADONA = BLOCKS.register("potted_belladona",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, BELLADONA, BlockBehaviour.Properties.copy(Blocks.POPPY).dynamicShape()));
    public static final RegistryObject<FlowerBlock> FROST_BLOOM = BLOCKS.register("frost_bloom",
            () -> new InflictingFlower(MobEffects.MOVEMENT_SLOWDOWN, 8, new Color(151, 178, 232), BlockBehaviour.Properties.copy(Blocks.POPPY).dynamicShape().noCollission()));
    public static final RegistryObject<FlowerPotBlock> POTTED_FROST_BLOOM = BLOCKS.register("potted_frost_bloom",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, FROST_BLOOM, BlockBehaviour.Properties.copy(Blocks.POPPY).dynamicShape()));
    //

    public static final RegistryObject<Block> BLACK_CALCITE_PEDESTAL = BLOCKS.register("black_calcite_pedestal", () -> new PedestalBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> INFUSED_WOOD_PEDESTAL = BLOCKS.register("infused_wood_pedestal", () -> new PedestalBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> INFUSER = BLOCKS.register("infuser", () -> new InfuserBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));

    public static final RegistryObject<Block> INFUSED_GLASS = BLOCKS.register("infused_glass",
            () -> new StainedGlassBlock(DyeColor.LIGHT_BLUE, BlockBehaviour.Properties.copy(Blocks.LIGHT_BLUE_STAINED_GLASS)));

    //Infused planks
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
    public static final RegistryObject<ModLogBlock> INFUSED_LOG = BLOCKS.register("infused_log",
            () -> new ModLogBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get()), STRIPPED_INFUSED_LOG));
    public static final RegistryObject<ModLogBlock> INFUSED_WOOD = BLOCKS.register("infused_wood",
            () -> new ModLogBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get()), STRIPPED_INFUSED_LOG));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_INFUSED_WOOD = BLOCKS.register("stripped_infused_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get())));

    public static final RegistryObject<LeavesBlock> HICKORY_LEAVES = BLOCKS.register("hickory_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<DoorBlock> INFUSED_WOOD_DOOR = BLOCKS.register("infused_wood_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> INFUSED_WOOD_TRAPDOOR = BLOCKS.register("infused_wood_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get()).noOcclusion()));

    public static final RegistryObject<TableBlock> INFUSED_WOOD_TABLE = BLOCKS.register("infused_wood_planks_table",
            () -> new TableBlock(BlockBehaviour.Properties.copy(INFUSED_PLANKS.get()).noOcclusion()));
    //

    //Black calcite
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
    //

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}