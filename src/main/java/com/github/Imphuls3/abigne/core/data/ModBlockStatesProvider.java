package com.github.Imphuls3.abigne.core.data;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.block.FlameBlock;
import com.github.Imphuls3.abigne.common.block.InfuserBlock;
import com.github.Imphuls3.abigne.common.block.PedestalBlock;
import com.github.Imphuls3.abigne.common.block.util.*;
import com.github.Imphuls3.abigne.core.block.AbIgneBlock;
import com.github.Imphuls3.abigne.core.helper.DataHelper;
import com.github.Imphuls3.abigne.core.registry.BlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.*;

import static com.github.Imphuls3.abigne.AbIgne.modPath;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, AbIgne.MODID, exFileHelper);
    }

    @Nonnull
    @Override
    public String getName() {
        return "AbIgne BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {
        Set<RegistryObject<Block>> blocks = new HashSet<>(BlockRegistry.BLOCKS.getEntries());

        DataHelper.takeAll(blocks, i -> i.get() instanceof PedestalBlock).forEach(this::pedestalBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof FlameBlock || i.get() instanceof InfuserBlock).forEach(this::invisibleBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof TwoLayeredBlock).forEach(this::layeredBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof LeavesBlock).forEach(this::cutoutBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof AbstractGlassBlock).forEach(this::translucentBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof StairBlock).forEach(this::stairsBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof DoorBlock).forEach(this::doorBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof TrapDoorBlock).forEach(this::trapdoorBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof BushBlock).forEach(this::plantBlock);
        DataHelper.takeAll(blocks, i -> i.get() instanceof FlowerPotBlock).forEach(this::flowerPotBlock);

        DataHelper.takeAll(blocks, i -> !(i.get() instanceof FullyRotatingPillarBlock || i.get() instanceof RotatedPillarBlock || i.get() instanceof ModLogBlock
                || i.get() instanceof AbIgneBlock || i.get() instanceof SlabBlock || i.get() instanceof LiquidBlock)).forEach(this::basicBlock);

        Collection<RegistryObject<Block>> slabs = DataHelper.takeAll(blocks, b -> b.get() instanceof SlabBlock);
        slabs.forEach(this::slabBlock);
    }

    public void basicBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get());
    }

    public void translucentBlock(RegistryObject<Block> blockRegistryObject) {
        basicBlockWithRendertype(blockRegistryObject, "translucent");
    }

    public void cutoutBlock(RegistryObject<Block> blockRegistryObject) {
        basicBlockWithRendertype(blockRegistryObject, "cutout");
    }

    public void basicBlockWithRendertype(RegistryObject<Block> blockRegistryObject, String renderType) {
        String name = Registry.BLOCK.getKey(blockRegistryObject.get()).getPath();
        String modelLocation = "block/block";
        ModelFile block = models().withExistingParent(name, new ResourceLocation(modelLocation)).texture("all", modPath("block/" + name)).renderType(renderType);
        getVariantBuilder(blockRegistryObject.get()).forAllStates(s -> ConfiguredModel.builder().modelFile(block).build());
    }

    public void slabBlock(RegistryObject<Block> blockRegistryObject) {
        String name = Registry.BLOCK.getKey(blockRegistryObject.get()).getPath();
        String baseName = name.substring(0, name.length()-5);
        slabBlock((SlabBlock) blockRegistryObject.get(), modPath(baseName), modPath("block/" + baseName));
    }

    public void stairsBlock(RegistryObject<Block> blockRegistryObject) {
        String name = Registry.BLOCK.getKey(blockRegistryObject.get()).getPath();
        String baseName = name.substring(0, name.length()-7);
        stairsBlock((StairBlock) blockRegistryObject.get(), modPath("block/" + baseName));
    }

    public void pedestalBlock(RegistryObject<Block> blockRegistryObject) {
        String name = Registry.BLOCK.getKey(blockRegistryObject.get()).getPath();
        String modelLocation = "block/util_pedestal_block";
        ModelFile pedestal = models().withExistingParent(name, modPath(modelLocation)).texture("all", modPath("block/pedestals/" + name)).renderType("cutout");
        getVariantBuilder(blockRegistryObject.get()).forAllStates(s -> ConfiguredModel.builder().modelFile(pedestal).build());
    }

    public void invisibleBlock(RegistryObject<Block> blockRegistryObject) {
        String name = Registry.BLOCK.getKey(blockRegistryObject.get()).getPath();
        ModelFile empty = models().withExistingParent(name, new ResourceLocation("block/air")).texture("particle", modPath("item/flame")).renderType("translucent");
        getVariantBuilder(blockRegistryObject.get()).forAllStates(s -> ConfiguredModel.builder().modelFile(empty).build());
    }

    public void doorBlock(RegistryObject<Block> blockRegistryObject) {
        String name = Registry.BLOCK.getKey(blockRegistryObject.get()).getPath();
        doorBlockWithRenderType((DoorBlock) blockRegistryObject.get(), modPath("block/" + name + "_bottom"), modPath("block/" + name + "_top"), "cutout");
    }

    public void trapdoorBlock(RegistryObject<Block> blockRegistryObject) {
        trapdoorBlockWithRenderType((TrapDoorBlock) blockRegistryObject.get(), blockTexture(blockRegistryObject.get()), true, "cutout");
    }

    public void layeredBlock(RegistryObject<Block> blockRegistryObject) {
        String name = Registry.BLOCK.getKey(blockRegistryObject.get()).getPath();
        String modelLocation = "block/util_cube_glow_2_all";
        ModelFile pedestal = models().withExistingParent(name, modPath(modelLocation)).texture("all", modPath("block/" + name)).texture("glow", modPath("block/" + name + "_glow")).renderType("cutout");
        getVariantBuilder(blockRegistryObject.get()).forAllStates(s -> ConfiguredModel.builder().modelFile(pedestal).build());
    }

    public void plantBlock(RegistryObject<Block> blockRegistryObject) {
        String name = Registry.BLOCK.getKey(blockRegistryObject.get()).getPath();
        ModelFile cross = models().withExistingParent(name, new ResourceLocation("block/cross")).texture("cross", modPath("block/" + name)).renderType("cutout");
        getVariantBuilder(blockRegistryObject.get()).forAllStates(s -> ConfiguredModel.builder().modelFile(cross).build());
    }

    public void flowerPotBlock(RegistryObject<Block> blockRegistryObject) {
        String name = Registry.BLOCK.getKey(blockRegistryObject.get()).getPath();
        String flower = name.substring(7);
        ModelFile cross = models().withExistingParent(name, new ResourceLocation("block/flower_pot_cross")).texture("plant", modPath("block/" + flower)).renderType("cutout");
        getVariantBuilder(blockRegistryObject.get()).forAllStates(s -> ConfiguredModel.builder().modelFile(cross).build());
    }
}
