package com.github.Imphuls3.abigne.core.data;

import com.github.Imphuls3.abigne.core.registry.BlockRegistry;
import com.github.Imphuls3.abigne.core.registry.ItemRegistry;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;
import static net.minecraft.data.recipes.ShapelessRecipeBuilder.shapeless;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        //region black calcite
        shaped4x4(consumer, BlockRegistry.LARGE_BLACK_CALCITE_BRICKS.get(), BlockRegistry.POLISHED_BLACK_CALCITE.get());
        shaped4x4(consumer, BlockRegistry.BLACK_CALCITE_TILES.get(), BlockRegistry.LARGE_BLACK_CALCITE_BRICKS.get());
        shaped4x4(consumer, BlockRegistry.BLACK_CALCITE_BRICKS.get(), BlockRegistry.BLACK_CALCITE_TILES.get());
        shaped(BlockRegistry.BLACK_CALCITE_PILLAR.get(), 2).define('C', BlockRegistry.POLISHED_BLACK_CALCITE.get())
                .pattern("C")
                .pattern("C")
                .unlockedBy("has_polished_black_calcite", inventoryTrigger(ItemPredicate.Builder.item().of(BlockRegistry.POLISHED_BLACK_CALCITE.get()).build())).save(consumer);
        shaped(BlockRegistry.BLACK_CALCITE_PILLAR_CAP.get(), 2).define('C', BlockRegistry.POLISHED_BLACK_CALCITE.get()).define('P', BlockRegistry.BLACK_CALCITE_PILLAR.get()).pattern("C").pattern("P")
                .unlockedBy("has_polished_black_calcite", inventoryTrigger(ItemPredicate.Builder.item().of(BlockRegistry.POLISHED_BLACK_CALCITE.get()).build()))
                .unlockedBy("has_black_calcite_pillar", inventoryTrigger(ItemPredicate.Builder.item().of(BlockRegistry.BLACK_CALCITE_PILLAR.get()).build()))
                .save(consumer);
        shaped(BlockRegistry.BLACK_CALCITE_PEDESTAL.get()).define('C', BlockRegistry.POLISHED_BLACK_CALCITE_SLAB.get()).define('B', BlockRegistry.POLISHED_BLACK_CALCITE.get())
                .pattern("CCC")
                .pattern(" B ")
                .pattern("CCC")
                .unlockedBy("has_polished_black_calcite", inventoryTrigger(ItemPredicate.Builder.item().of(BlockRegistry.POLISHED_BLACK_CALCITE.get()).build())).save(consumer);
        shapedStairs(consumer, ItemRegistry.POLISHED_BLACK_CALCITE_STAIR.get(), ItemRegistry.POLISHED_BLACK_CALCITE.get());
        shapedStairs(consumer, ItemRegistry.LARGE_BLACK_CALCITE_BRICKS_STAIRS.get(), ItemRegistry.LARGE_BLACK_CALCITE_BRICKS.get());
        shapedStairs(consumer, ItemRegistry.BLACK_CALCITE_TILES_STAIRS.get(), ItemRegistry.BLACK_CALCITE_TILES.get());
        shapedStairs(consumer, ItemRegistry.BLACK_CALCITE_BRICKS_STAIRS.get(), ItemRegistry.BLACK_CALCITE_BRICKS.get());

        shapedSlab(consumer, ItemRegistry.POLISHED_BLACK_CALCITE_SLAB.get(), ItemRegistry.POLISHED_BLACK_CALCITE.get());
        shapedSlab(consumer, ItemRegistry.LARGE_BLACK_CALCITE_BRICKS_SLAB.get(), ItemRegistry.LARGE_BLACK_CALCITE_BRICKS.get());
        shapedSlab(consumer, ItemRegistry.BLACK_CALCITE_TILES_SLAB.get(), ItemRegistry.BLACK_CALCITE_TILES.get());
        shapedSlab(consumer, ItemRegistry.BLACK_CALCITE_BRICKS_SLAB.get(), ItemRegistry.BLACK_CALCITE_BRICKS.get());
        //endregion

        //region infused wood
        shapeless(BlockRegistry.INFUSED_PLANKS.get(), 4)
                .requires(BlockRegistry.INFUSED_LOG.get())
                .unlockedBy("has_infused_log", inventoryTrigger(ItemPredicate.Builder.item().of(BlockRegistry.INFUSED_LOG.get()).build())).save(consumer);
        shaped4x4(consumer, BlockRegistry.POLISHED_INFUSED_WOOD.get(), BlockRegistry.INFUSED_PLANKS.get());
        shaped4x4(consumer, BlockRegistry.INFUSED_WOOD_TILE.get(), BlockRegistry.POLISHED_INFUSED_WOOD.get());
        shaped4x4(consumer, BlockRegistry.INFUSED_WOOD_TILE_2.get(), BlockRegistry.INFUSED_WOOD_TILE.get());
        shaped(BlockRegistry.INFUSED_WOOD_PEDESTAL.get()).define('I', BlockRegistry.POLISHED_INFUSED_WOOD_SLAB.get()).define('W', BlockRegistry.POLISHED_INFUSED_WOOD.get())
                .pattern("III")
                .pattern(" W ")
                .pattern("III")
                .unlockedBy("has_infused_planks_slab", inventoryTrigger(ItemPredicate.Builder.item().of(BlockRegistry.POLISHED_INFUSED_WOOD_SLAB.get()).build())).save(consumer);
        shapedStairs(consumer, BlockRegistry.INFUSED_PLANKS_STAIRS.get(), BlockRegistry.INFUSED_PLANKS.get());
        shapedStairs(consumer, BlockRegistry.INFUSED_WOOD_TILE_STAIRS.get(), BlockRegistry.INFUSED_WOOD_TILE.get());
        shapedStairs(consumer, BlockRegistry.INFUSED_WOOD_TILE_2_STAIRS.get(), BlockRegistry.INFUSED_WOOD_TILE_2.get());
        shapedStairs(consumer, BlockRegistry.POLISHED_INFUSED_WOOD_STAIRS.get(), BlockRegistry.POLISHED_INFUSED_WOOD.get());

        shapedSlab(consumer, BlockRegistry.INFUSED_PLANKS_SLAB.get(), BlockRegistry.INFUSED_PLANKS.get());
        shapedSlab(consumer, BlockRegistry.INFUSED_WOOD_TILE_SLAB.get(), BlockRegistry.INFUSED_WOOD_TILE.get());
        shapedSlab(consumer, BlockRegistry.INFUSED_WOOD_TILE_2_SLAB.get(), BlockRegistry.INFUSED_WOOD_TILE_2.get());
        shapedSlab(consumer, BlockRegistry.POLISHED_INFUSED_WOOD_SLAB.get(), BlockRegistry.POLISHED_INFUSED_WOOD.get());
        //endregion
    }
    private static void shaped4x4(Consumer<FinishedRecipe> recipeConsumer, ItemLike out, ItemLike input) {
        shaped(out, 4).define('#', input).pattern("##").pattern("##").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }

    private static void shapedDoor(Consumer<FinishedRecipe> recipeConsumer, ItemLike door, ItemLike input) {
        shaped(door, 3).define('#', input).pattern("##").pattern("##").pattern("##").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }

    private static void shapedFence(Consumer<FinishedRecipe> recipeConsumer, ItemLike fence, ItemLike input) {
        shaped(fence, 3).define('#', Tags.Items.RODS_WOODEN).define('W', input).pattern("W#W").pattern("W#W").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }

    private static void shapedFenceGate(Consumer<FinishedRecipe> recipeConsumer, ItemLike fenceGate, ItemLike input) {
        shaped(fenceGate).define('#', Tags.Items.RODS_WOODEN).define('W', input).pattern("#W#").pattern("#W#").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }

    private static void shapedPressurePlate(Consumer<FinishedRecipe> recipeConsumer, ItemLike pressurePlate, ItemLike input) {
        shaped(pressurePlate).define('#', input).pattern("##").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }

    private static void shapedSlab(Consumer<FinishedRecipe> recipeConsumer, ItemLike slab, ItemLike input) {
        shaped(slab, 6).define('#', input).pattern("###").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }

    private static void shapedStairs(Consumer<FinishedRecipe> recipeConsumer, ItemLike stairs, ItemLike input) {
        shaped(stairs, 4).define('#', input).pattern("#  ").pattern("## ").pattern("###").unlockedBy("has_input", has(input)).save(recipeConsumer);
    }

}
