package com.Imphuls3.abigne.core.init;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.common.block.CrucibleBlock;
import com.Imphuls3.abigne.common.block.PedestalBlock;
import com.Imphuls3.abigne.common.block.custom.ModFlammableRotatedPillarBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.*;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            AbIgne.MOD_ID);

    public static final RegistryObject<Block> PEDESTAL = BLOCKS.register("pedestal", () -> new PedestalBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> CRUCIBLE = BLOCKS.register("crucible", () -> new CrucibleBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static final RegistryObject<Block> IGNIS_BLOCK = BLOCKS.register("ignis_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> EMBER_LOG = BLOCKS.register("ember_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).noOcclusion()));

    public static final RegistryObject<Block> STRIPPED_EMBER_LOG = BLOCKS.register("stripped_ember_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).noOcclusion()));

    public static final RegistryObject<Block> EMBER_WOOD = BLOCKS.register("ember_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).noOcclusion()));

    public static final RegistryObject<Block> STRIPPED_EMBER_WOOD = BLOCKS.register("stripped_ember_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).noOcclusion()));

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

    public static final RegistryObject<Block> CHARRED_LOG = BLOCKS.register("charred_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).noOcclusion()));

    public static final RegistryObject<Block> CHARRED_WOOD = BLOCKS.register("charred_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).noOcclusion()));

    public static final RegistryObject<Block> CHARRED_PLANKS = BLOCKS.register("charred_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> ASHEN_BRICKS = BLOCKS.register("ashen_bricks_soft",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> ASHEN_BRICKS_HARD = BLOCKS.register("ashen_bricks_hard",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> ASHEN_BRICKS_SMALL = BLOCKS.register("ashen_bricks_small",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> ASHEN_TILE = BLOCKS.register("ashen_tile",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> ASHEN_TILE_SMALL = BLOCKS.register("ashen_tile_small",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    public static final RegistryObject<Block> ASHEN_TILE_TINY = BLOCKS.register("ashen_tile_tiny",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));
}