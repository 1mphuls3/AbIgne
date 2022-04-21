package com.Imphuls3.abigne.common.item;

import com.Imphuls3.abigne.core.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class IgnisShield implements ICurio {

    @Override
    public ItemStack getStack() {
        return ItemInit.IGNIS_SHIELD.get().getDefaultInstance();
    }

    @Override
    public void curioTick(SlotContext slotContext) {
        LivingEntity entity = slotContext.entity();
        if (entity instanceof Player) {
            Player player = ((Player) entity);
            if(player.isOnFire()){
                player.clearFire();
            }
        }
    }
}
