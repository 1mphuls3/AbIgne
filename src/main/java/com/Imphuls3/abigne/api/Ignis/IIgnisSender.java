package com.Imphuls3.abigne.api.Ignis;

import com.Imphuls3.abigne.api.Ignis.IIgnisBlock;

import java.util.UUID;

public interface IIgnisSender extends IIgnisBlock {
    void setCanShoot(boolean canShoot);

    int getBurstParticleTick();

    void setBurstParticleTick(int i);

    int getLastBurstDeathTick();

    void setLastBurstDeathTick(int ticksExisted);

    IIgnisBurst runBurstSimulation();

    void commitRedirection();

    void pingback(IIgnisBurst burst, UUID expectedIdentity);

    UUID getIdentifier();
}
