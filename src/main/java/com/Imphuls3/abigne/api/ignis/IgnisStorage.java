package com.Imphuls3.abigne.api.ignis;

public class IgnisStorage implements IIgnis{
    int ignis;
    int maxIgnis;
    boolean isFull;
    @Override
    public void consumeIgnis(int amount) {
        this.ignis -= amount;
    }

    @Override
    public void fillIgnis(int amount) {
        this.ignis += amount;
    }

    @Override
    public void setIgnis(int amount) {
        this.ignis = amount;
    }

    @Override
    public int getIgnis() {
        return this.ignis;
    }

    @Override
    public int getMaxIgnis() {
        return this.maxIgnis;
    }

    @Override
    public boolean isFull() {
        return this.isFull;
    }
}
