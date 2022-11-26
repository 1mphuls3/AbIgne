package com.github.Imphuls3.abigne.core.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;

import java.util.function.Predicate;

public class ExtendedFluidTank extends FluidTank {
    public final LazyOptional<FluidTank> fluidOptional = LazyOptional.of(() -> this);

    public ExtendedFluidTank(int capacity) {
        super(capacity);
    }

    public ExtendedFluidTank(int capacity, Predicate<FluidStack> validator) {
        super(capacity, validator);
    }

    public float getPressure() {
        return (float)getFluid().getAmount()/getCapacity();
    }

    public boolean fill(FluidStack stack) {
        if (!getFluid().isEmpty() && getFluid().getFluid() != stack.getFluid()) return false;
        if (getFluid().isEmpty()) setFluid(stack);
        setFluidAmount(stack.getAmount());
        return true;
    }

    public void setFluidAmount(int amount) {
        this.getFluid().setAmount(amount);
    }

    public void save(String name, CompoundTag nbt) {
        CompoundTag tag = new CompoundTag();
        nbt.put(name, this.writeToNBT(tag));
    }

    public ExtendedFluidTank load(CompoundTag nbt) {
        this.setFluid(FluidStack.loadFluidStackFromNBT(nbt));
        return this;
    }

    public void setChanged(){
        this.onContentsChanged();
    }

    public int clearContents(){
        FluidStack ret = FluidStack.EMPTY;
        this.setFluid(ret);
        return ret.getAmount();
    }
}
