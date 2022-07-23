package com.Imphuls3.abigne.core.init;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.block.*;
import com.Imphuls3.abigne.common.block.custom.*;
import com.Imphuls3.abigne.common.block.pipe.PipeBlock;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.*;

import java.util.function.ToIntFunction;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AbIgne.MODID);

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
        };
    }

    public static final RegistryObject<EnflamedAshBlock> ENFLAMED_ASH = BLOCKS.register("enflamed_ash", () -> new EnflamedAshBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion().noCollission()));

    public static final RegistryObject<Block> TORCH = BLOCKS.register("calming_torch", () -> new CalmingTorchBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)
            .lightLevel(litBlockEmission(8))));

    public static final RegistryObject<Block> WALL = BLOCKS.register("wall_lamp", () -> new WallLamp(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> PIPE = BLOCKS.register("pipe", () -> new PipeBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> BLACK_CALCITE_PEDESTAL = BLOCKS.register("black_calcite_pedestal", () -> new PedestalBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> INFUSED_WOOD_PEDESTAL = BLOCKS.register("infused_wood_pedestal", () -> new PedestalBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> SOUL_INFUSED_WOOD_PEDESTAL = BLOCKS.register("soul_infused_wood_pedestal", () -> new PedestalBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> TRANSPORTER = BLOCKS.register("transporter", () -> new ItemTransporterBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CRUCIBLE = BLOCKS.register("crucible", () -> new CrucibleBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> INFUSER = BLOCKS.register("infuser", () -> new InfuserBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()));

    public static final RegistryObject<Block> VOID = BLOCKS.register("void", () -> new IgnisVoidBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> RITUAL_PEDESTAL = BLOCKS.register("ritual", () -> new CenterPedestalBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> IGNIS_GLASS = BLOCKS.register("ignis_infused_glass",
            () -> new StainedGlassBlock(DyeColor.RED, BlockBehaviour.Properties.copy(Blocks.RED_STAINED_GLASS).lightLevel((light) -> {
                return 5;
            })));

    public static final RegistryObject<Block> IGNIS_BLOCK = BLOCKS.register("ignis_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).strength(5.0F, 6.0F)));

    public static final RegistryObject<Block> TEMPERED_BLOCK = BLOCKS.register("tempered_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> INFUSED_PLANKS = BLOCKS.register("infused_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> INFUSED_PLANKS_TILE = BLOCKS.register("infused_planks_tile",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> INFUSED_PLANKS_TILE2 = BLOCKS.register("infused_planks_tile_2",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> INFUSED_PLANKS_TILE_INDENT = BLOCKS.register("infused_planks_tile_indent",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<FullyRotatingPillarBlock> INFUSED_PLANKS_PILLAR = BLOCKS.register("infused_planks_pillar",
            () -> new FullyRotatingPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> SOUL_INFUSED_PLANKS = BLOCKS.register("soul_infused_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> SOUL_INFUSED_PLANKS_TILE = BLOCKS.register("soul_infused_planks_tile",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> SOUL_INFUSED_PLANKS_TILE_2 = BLOCKS.register("soul_infused_planks_tile_2",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> SOUL_INFUSED_PLANKS_TILE_INDENT = BLOCKS.register("soul_infused_planks_tile_indent",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<FullyRotatingPillarBlock> SOUL_INFUSED_PLANKS_PILLAR = BLOCKS.register("soul_infused_planks_pillar",
            () -> new FullyRotatingPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> CHARRED_LOG = BLOCKS.register("charred_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> CHARRED_WOOD = BLOCKS.register("charred_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> CHARRED_PLANKS = BLOCKS.register("charred_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> BLACK_CALCITE = BLOCKS.register("black_calcite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> POLISHED_BLACK_CALCITE = BLOCKS.register("polished_black_calcite",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> BLACK_CALCITE_TILES = BLOCKS.register("black_calcite_tiles",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> POLISHED_BLACK_CALCITE_INDENT = BLOCKS.register("polished_black_calcite_indent",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> BLACK_CALCITE_TILES_INDENT = BLOCKS.register("black_calcite_tiles_indent",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> BLACK_CALCITE_BRICKS = BLOCKS.register("black_calcite_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> CHISELED_BLACK_CALCITE_BRICKS = BLOCKS.register("chiseled_black_calcite_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CHISELED_STONE_BRICKS)));

    public static final RegistryObject<Block> BLACK_CALCITE_PILLAR = BLOCKS.register("black_calcite_pillar",
            () -> new FullyRotatingPillarBlock(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> BLACK_CALCITE_PILLAR_CAP = BLOCKS.register("black_calcite_pillar_cap",
            () -> new FullyRotatingPillarBlock(BlockBehaviour.Properties.copy(Blocks.CALCITE)));

    public static final RegistryObject<Block> PYROLITE_GEM_BLOCK = BLOCKS.register("pyrolite_gem_block",
            () -> new AmethystBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)));

    public static final RegistryObject<Block> BUDDING_PYROLITE_GEM_BLOCK = BLOCKS.register("budding_pyrolite",
            () -> new BuddingIgnisBlock(BlockBehaviour.Properties.copy(Blocks.BUDDING_AMETHYST)));

    public static final RegistryObject<Block> PYROLITE_CLUSTER = BLOCKS.register("pyrolite_cluster",
            () -> new IgnisClusterBlock(7, 3, BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((p_152632_) -> {
                return 5;
            })));

    public static final RegistryObject<Block> SMALL_PYROLITE_BUD = BLOCKS.register("small_pyrolite_bud",
            () -> new IgnisClusterBlock(5, 3, BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((p_152632_) -> {
                return 4;
            })));

    public static final RegistryObject<Block> MEDIUM_PYROLITE_BUD = BLOCKS.register("medium_pyrolite_bud",
            () -> new IgnisClusterBlock(4, 3, BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((p_152632_) -> {
                return 2;
            })));

    public static final RegistryObject<Block> LARGE_PYROLITE_BUD = BLOCKS.register("large_pyrolite_bud",
            () -> new IgnisClusterBlock(3, 4, BlockBehaviour.Properties.of(Material.AMETHYST).randomTicks().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel((p_152632_) -> {
                return 1;
            })));
}