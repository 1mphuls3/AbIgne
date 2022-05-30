package com.Imphuls3.abigne.core.init;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.block.*;
import com.Imphuls3.abigne.common.block.custom.BuddingIgnisBlock;
import com.Imphuls3.abigne.common.block.custom.FullyRotatingPillarBlock;
import com.Imphuls3.abigne.common.block.custom.IgnisClusterBlock;
import com.Imphuls3.abigne.common.block.custom.ModFlammableRotatedPillarBlock;
import com.Imphuls3.abigne.common.block.pipe.PipeBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.*;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            AbIgne.MOD_ID);

    public static final RegistryObject<Block> WALL = BLOCKS.register("wall_lamp", () -> new WallLamp(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> PIPE = BLOCKS.register("pipe", () -> new PipeBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> PEDESTAL = BLOCKS.register("pedestal", () -> new PedestalBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> TRANSPORTER = BLOCKS.register("transporter", () -> new ItemTransporterBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CRUCIBLE = BLOCKS.register("crucible", () -> new CrucibleBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> INFUSER = BLOCKS.register("infuser", () -> new InfuserBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

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

    public static final RegistryObject<Block> EMBER_LOG = BLOCKS.register("ember_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_EMBER_LOG = BLOCKS.register("stripped_ember_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<Block> EMBER_WOOD = BLOCKS.register("ember_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> STRIPPED_EMBER_WOOD = BLOCKS.register("stripped_ember_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> EMBER_PLANKS = BLOCKS.register("ember_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> EMBER_PLANKS_TILE = BLOCKS.register("ember_planks_tile",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }
            });

    public static final RegistryObject<Block> CHARRED_LOG = BLOCKS.register("charred_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> CHARRED_WOOD = BLOCKS.register("charred_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> CHARRED_PLANKS = BLOCKS.register("charred_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
                    return 5;
                }
            });

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