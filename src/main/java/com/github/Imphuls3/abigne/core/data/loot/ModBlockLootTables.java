package com.github.Imphuls3.abigne.core.data.loot;

import com.github.Imphuls3.abigne.core.registry.BlockRegistry;
import com.github.Imphuls3.abigne.core.registry.ItemRegistry;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Set;

import static com.github.Imphuls3.abigne.core.helper.DataHelper.takeAll;

public class ModBlockLootTables extends BlockLoot {

    @Override
    protected void addTables() {
        Set<RegistryObject<Block>> blocks = new HashSet<>(BlockRegistry.BLOCKS.getEntries());

        /*takeAll(blocks, i -> !(i.get() instanceof PyroliteClusterBlock || i.get() instanceof PyroliteClusterBlock)).forEach(this::dropSelf);
*/
        this.dropSelf(BlockRegistry.PYROLITE_GEM_BLOCK.get());
        this.add(BlockRegistry.BUDDING_PYROLITE_GEM_BLOCK.get(), noDrop());
        this.dropWhenSilkTouch(BlockRegistry.SMALL_PYROLITE_BUD.get());
        this.dropWhenSilkTouch(BlockRegistry.MEDIUM_PYROLITE_BUD.get());
        this.dropWhenSilkTouch(BlockRegistry.LARGE_PYROLITE_BUD.get());
        this.add(BlockRegistry.PYROLITE_CLUSTER.get(),
                (block) -> createOreDrop(BlockRegistry.PYROLITE_CLUSTER.get(), ItemRegistry.PYROLITE_SHARD.get()));
        this.add(BlockRegistry.PYROLITE_CLUSTER.get(),
                (block) -> createSilkTouchDispatchTable(block, LootItem.lootTableItem(ItemRegistry.PYROLITE_CLUSTER.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F)))
                        .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE)).when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .otherwise(applyExplosionDecay(block, LootItem.lootTableItem(ItemRegistry.PYROLITE_CLUSTER.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))))));

        takeAll(blocks, b -> b.get() instanceof DoorBlock).forEach(b -> add(b.get(), createDoorTable(b.get())));

        blocks.forEach(this::dropSelf);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    public void dropSelf(RegistryObject<Block> block) {
        this.dropOther(block.get(), block.get());
    }
}
