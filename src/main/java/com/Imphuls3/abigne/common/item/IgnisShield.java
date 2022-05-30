package com.Imphuls3.abigne.common.item;

import com.Imphuls3.abigne.core.init.ItemInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class IgnisShield extends Item implements ICurio {

    public IgnisShield(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getStack() {
        return ItemInit.IGNIS_SHIELD.get().getDefaultInstance();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean selected) {
        if(entity.isOnFire()){
            entity.clearFire();
        }
        super.inventoryTick(stack, level, entity, slotId, selected);
    }
}
