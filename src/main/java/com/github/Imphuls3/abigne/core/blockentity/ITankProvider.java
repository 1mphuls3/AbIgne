package com.github.Imphuls3.abigne.core.blockentity;

import net.minecraft.core.Direction;

public interface ITankProvider {
    ExtendedFluidTank getTank();
    boolean isOutput(Direction direction);
    boolean isInput(Direction direction);
    boolean isPressurized();
}
