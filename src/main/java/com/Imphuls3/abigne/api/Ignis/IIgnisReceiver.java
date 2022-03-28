package com.Imphuls3.abigne.api.Ignis;

import com.Imphuls3.abigne.api.Ignis.IIgnisBlock;

public interface IIgnisReceiver extends IIgnisBlock {
    boolean isFull();

    void receiveIgnis(int mana);

    boolean canReceiveIgnisFromBursts();
}
