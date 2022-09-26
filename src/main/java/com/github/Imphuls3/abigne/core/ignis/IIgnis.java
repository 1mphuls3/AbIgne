package com.github.Imphuls3.abigne.core.ignis;

public interface IIgnis {
    int getTransferRate();

    boolean canAcceptIgnis();

    int getIgnis();

    int getMaxIgnis();

    void setMaxIgnis(int amountMax);

    int setIgnis(final int amountSet);

    int addIgnis(final int amountAdd);

    int removeIgnis(final int amountRemove);
}
