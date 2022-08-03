package com.Imphuls3.abigne.common.item.util;

import com.Imphuls3.abigne.AbIgne;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AbIgneTags {
    public static class Blocks {
        private static final TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(AbIgne.MODID, name));
        }

        public static final TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> INFUSER_CATALYSTS = tag("infuser_catalysts");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(AbIgne.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
