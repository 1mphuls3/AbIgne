package com.github.Imphuls3.abigne.core.registry;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class TierRegistry {
    public static class SilverTier implements Tier {
        @Override
        public int getLevel() {
            return 2;
        }

        @Override
        public int getUses() {
            return 350;
        }

        @Override
        public float getSpeed() {
            return 6.0F;
        }

        @Override
        public float getAttackDamageBonus() {
            return 2;
        }

        @Override
        public int getEnchantmentValue() {
            return 18;
        }

        @Override
        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(ItemRegistry.SILVER_INGOT.get()));
        }

        public static SilverTier INSTANCE = new SilverTier();
    }
}
