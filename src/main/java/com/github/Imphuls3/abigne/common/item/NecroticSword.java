package com.github.Imphuls3.abigne.common.item;

import com.github.Imphuls3.abigne.common.effect.EffectsRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NecroticSword extends SwordItem {
    public NecroticSword(Tier tier, int attackModifier, float speedModifier, Properties properties) {
        super(tier, attackModifier, speedModifier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag advanced) {
        tooltip.add(new TextComponent(""));
        tooltip.add(new TextComponent("" + ChatFormatting.RED + "Necrosis (0:03)"));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.getMobType() != MobType.UNDEAD) {
            target.addEffect(new MobEffectInstance(EffectsRegistry.NECROSIS.get(), 60));
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
