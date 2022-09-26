package com.github.Imphuls3.abigne.common.item.util;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class EffectBlockItem extends BlockItem {
    private MobEffect effect;

    public EffectBlockItem(MobEffect effect, Block block, Properties properties) {
        super(block, properties);
        this.effect = effect;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean selected) {
        if((selected && entity instanceof LivingEntity) || (entity instanceof Player player && player.getItemInHand(InteractionHand.OFF_HAND).getItem() == this)) {
            LivingEntity livingEntity = (LivingEntity)entity;
            if(livingEntity.canBeAffected(new MobEffectInstance(this.effect, 2*20)) && !livingEntity.hasEffect(effect)) {
                livingEntity.addEffect(new MobEffectInstance(effect, 2*20));
            }
        }
        super.inventoryTick(stack, level, entity, slotId, selected);
    }
}
