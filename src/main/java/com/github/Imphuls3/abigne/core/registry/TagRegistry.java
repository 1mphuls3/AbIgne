package com.github.Imphuls3.abigne.core.registry;

import com.github.Imphuls3.abigne.AbIgne;
import net.minecraft.core.Registry;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

import static com.github.Imphuls3.abigne.AbIgne.modPath;

public class TagRegistry {
    public static class Blocks {
        private static final TagKey<Block> abigneTag(String name) {
            return net.minecraft.tags.BlockTags.create(new ResourceLocation(AbIgne.MODID, name));
        }

        public static final TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {

        private static TagKey<Item> abigneTag(String path) {
            return TagKey.create(Registry.ITEM_REGISTRY, modPath(path));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
