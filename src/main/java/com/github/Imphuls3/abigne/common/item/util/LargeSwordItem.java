package com.github.Imphuls3.abigne.common.item.util;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class LargeSwordItem extends SwordItem {
    /**
     * Used so data gen doesnt generate a regular item model for swords that have a different held model
     * */
    public LargeSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
