package com.github.Imphuls3.abigne.core.data;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.common.block.FlameBlock;
import com.github.Imphuls3.abigne.common.block.PedestalBlock;
import com.github.Imphuls3.abigne.core.registry.ItemRegistry;
import com.github.Imphuls3.abigne.core.helper.DataHelper;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

import static com.github.Imphuls3.abigne.AbIgne.modPath;
import static com.github.Imphuls3.abigne.core.helper.DataHelper.takeAll;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, AbIgne.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<RegistryObject<Item>> items = new HashSet<>(ItemRegistry.ITEMS.getEntries());

        DataHelper.takeAll(items, i -> i.get() instanceof SwordItem).forEach(this::handheldItem);
        DataHelper.takeAll(items, i -> i.get() instanceof PickaxeItem).forEach(this::handheldItem);
        DataHelper.takeAll(items, i -> i.get() instanceof AxeItem).forEach(this::handheldItem);
        DataHelper.takeAll(items, i -> i.get() instanceof ShovelItem).forEach(this::handheldItem);
        DataHelper.takeAll(items, i -> i.get() instanceof HoeItem).forEach(this::handheldItem);
        DataHelper.takeAll(items, i -> i.get() instanceof ForgeSpawnEggItem).forEach(this::eggItem);

        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof PedestalBlock).forEach(this::pedestalBlock);
        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof FlameBlock).forEach(this::generatedItem);

        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof BushBlock).forEach(this::generatedItem);
        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof DoorBlock).forEach(this::generatedItem);
        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem && ((BlockItem) i.get()).getBlock() instanceof TrapDoorBlock).forEach(this::trapdoorBlockItem);

        DataHelper.takeAll(items, i -> i.get() instanceof BlockItem).forEach(this::blockItem);
        
        items.forEach(this::generatedItem);
    }

    private static final ResourceLocation GENERATED = new ResourceLocation("item/generated");
    private static final ResourceLocation HANDHELD = new ResourceLocation("item/handheld");

    private void blockItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(modPath("block/" + name)));
    }

    private void handheldItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, HANDHELD).texture("layer0", modPath("item/" + name));
    }

    private void generatedItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, GENERATED).texture("layer0", modPath("item/" + name));
    }

    private void trapdoorBlockItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(modPath("block/" + name + "_bottom")));
    }

    private void eggItem(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        withExistingParent(name, new ResourceLocation("item/template_spawn_egg"));
    }

    private void pedestalBlock(RegistryObject<Item> i) {
        String name = Registry.ITEM.getKey(i.get()).getPath();
        getBuilder(name).parent(new ModelFile.UncheckedModelFile(modPath("block/util_pedestal_block"))).texture("all", modPath("block/pedestals/" + name));
    }
}
