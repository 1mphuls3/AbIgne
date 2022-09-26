package com.github.Imphuls3.abigne.core.data;

import com.github.Imphuls3.abigne.AbIgne;
import com.github.Imphuls3.abigne.core.registry.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static com.github.Imphuls3.abigne.core.registry.BlockRegistry.BLOCKS;
import static net.minecraft.tags.BlockTags.*;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, AbIgne.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        /*tag(Tags.Blocks.ORES).add();*/
        tag(BEACON_BASE_BLOCKS).add(BlockRegistry.TARNISHED_STEEL_BLOCK.get());

        tag(BlockTags.SLABS).add(getModBlocks(b -> b instanceof SlabBlock));
        tag(BlockTags.STAIRS).add(getModBlocks(b -> b instanceof StairBlock));
        tag(BlockTags.WALLS).add(getModBlocks(b -> b instanceof WallBlock));
        tag(BlockTags.FENCES).add(getModBlocks(b -> b instanceof FenceBlock));
        tag(BlockTags.FENCE_GATES).add(getModBlocks(b -> b instanceof FenceGateBlock));
        tag(BlockTags.LEAVES).add(getModBlocks(b -> b instanceof LeavesBlock));
        tag(DOORS).add(getModBlocks(b -> b instanceof DoorBlock));
        tag(TRAPDOORS).add(getModBlocks(b -> b instanceof TrapDoorBlock));
        tag(BUTTONS).add(getModBlocks(b -> b instanceof ButtonBlock));
        tag(PRESSURE_PLATES).add(getModBlocks(b -> b instanceof PressurePlateBlock));
        tag(DIRT).add(getModBlocks(b -> b instanceof GrassBlock || b instanceof FarmBlock));
        tag(SAPLINGS).add(getModBlocks(b -> b instanceof SaplingBlock));

        tag(LOGS).add(getModBlocks(b -> b.getRegistryName().getPath().endsWith("_log")));
        tag(PLANKS).add(getModBlocks(b -> b.getRegistryName().getPath().endsWith("_planks")));
        tag(WOODEN_BUTTONS).add(getModBlocks(b -> b.getRegistryName().getPath().endsWith("_button") && b.getRegistryName().getPath().contains("wood")));
        tag(WOODEN_FENCES).add(getModBlocks(b -> b.getRegistryName().getPath().endsWith("_fence") && b.getRegistryName().getPath().contains("wood")));
        tag(WOODEN_DOORS).add(getModBlocks(b -> b.getRegistryName().getPath().endsWith("_door") && b.getRegistryName().getPath().contains("wood")));
        tag(WOODEN_STAIRS).add(getModBlocks(b -> b.getRegistryName().getPath().endsWith("_stairs") && b.getRegistryName().getPath().contains("wood")));
        tag(WOODEN_SLABS).add(getModBlocks(b -> b.getRegistryName().getPath().endsWith("_slab") && b.getRegistryName().getPath().contains("wood")));
        tag(WOODEN_TRAPDOORS).add(getModBlocks(b -> b.getRegistryName().getPath().endsWith("_trapdoor") && b.getRegistryName().getPath().contains("wood")));
        tag(WOODEN_PRESSURE_PLATES).add(getModBlocks(b -> b.getRegistryName().getPath().endsWith("_pressure_plate") && b.getRegistryName().getPath().contains("wood")));

        for (RegistryObject<Block> block : BLOCKS.getEntries()) {
            if (block.get().getRegistryName().getPath().contains("wood") || block.get().getRegistryName().getPath().contains("planks")) {
                tag(MINEABLE_WITH_AXE).add(block.get());
            }
        }
    }

    @Override
    public String getName() {
        return "Abigne Block Tags";
    }

    @Nonnull
    private Block[] getModBlocks(Predicate<Block> predicate) {
        List<Block> ret = new ArrayList<>(Collections.emptyList());
        BLOCKS.getEntries().stream()
                .filter(b -> predicate.test(b.get())).forEach(b -> ret.add(b.get()));
        return ret.toArray(new Block[0]);
    }
}
