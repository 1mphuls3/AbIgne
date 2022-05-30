package com.Imphuls3.abigne.common.item;

import com.Imphuls3.abigne.api.ignis.AbstractWandPart;

public class WandPart extends AbstractWandPart {
    int tier;
    int capacity;
    int regen;
    String type;


    /**
     * Type is either "handle", "end" or "extra", unless you mess with the rest of the wand code in your addon
     * Don't ask me how to do that or how to fix it if something breaks
     * */
    public WandPart(int tier, int capacity, int regen, String type, Properties properties){
        super(properties);
        this.tier = tier;
        this.capacity = capacity;
        this.regen = regen;
        this.type = type;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getRegen() {
        return regen;
    }

    @Override
    public int getTier() {
        return tier;
    }

    @Override
    public String getPartType() {
        return type;
    }
}
