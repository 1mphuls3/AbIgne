package com.Imphuls3.abigne.common.item;

import com.Imphuls3.abigne.core.registry.common.ItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class IgnisShield extends Item implements ICurio {

    public IgnisShield(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getStack() {
        return ItemRegistry.IGNIS_SHIELD.get().getDefaultInstance();
    }

    @Override
    public void curioTick(SlotContext slotContext) {
        if(slotContext.entity().isOnFire()){
            slotContext.entity().clearFire();
        }
        ICurio.super.curioTick(slotContext);
    }
}
