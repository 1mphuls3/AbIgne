package com.Imphuls3.abigne.api.ignis;

public interface IIgnis {
    void consumeIgnis(int amount);

    void fillIgnis(int amount);

    void setIgnis(int amount);

    int getIgnis();

    int getMaxIgnis();

    boolean isFull();
}
