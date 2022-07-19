package com.Imphuls3.abigne.api.ignis;

import net.minecraft.world.item.Item;

public abstract class AbstractWandPart extends Item implements IIginsItem {
    int capacity = 1000;
    int tier = 1;
    String type;

    public AbstractWandPart(Properties properties) {
        super(properties);
    }

    public String getPartType(){
        return this.type;
    }

    @Override
    public int getTransferRate() {
        return capacity;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public int getTier() {
        return tier;
    }
}
