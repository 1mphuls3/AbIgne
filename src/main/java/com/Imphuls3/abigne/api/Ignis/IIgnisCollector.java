package com.Imphuls3.abigne.api.Ignis;

public interface IIgnisCollector extends IIgnisReceiver {
    void onClientDisplayTick();

    float getIgnisYieldMultiplier(IIgnisBurst burst);

    int getMaxIgnis();
}
