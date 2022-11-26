package com.github.Imphuls3.abigne.common.item.util;

import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.UUID;

public class ModCurioItem implements ICurio, ICapabilityProvider {
    private final ItemStack stack;
    private static final String TAG_BAUBLE_UUID = "curioUUID";

    public ModCurioItem(ItemStack stack) {
        this.stack = stack;
    }

    public static UUID getBaubleUUID(ItemStack stack) {
        var tag = stack.getOrCreateTag();
        if (!tag.hasUUID(TAG_BAUBLE_UUID)) {
            UUID uuid = UUID.randomUUID();
            tag.putUUID(TAG_BAUBLE_UUID, uuid);
        }

        return tag.getUUID(TAG_BAUBLE_UUID);
    }

    @Override
    public <ICurio> LazyOptional<ICurio> getCapability(Capability<ICurio> cap) {
        return cap == CuriosCapability.ITEM ? LazyOptional.of(() -> (ICurio)this) : LazyOptional.empty();
    }

    @Override
    public <ICurio> LazyOptional<ICurio> getCapability(Capability<ICurio> cap, Direction side) {
        return getCapability(cap);
    }

    @Override
    public ItemStack getStack() {
        return stack;
    }
}
