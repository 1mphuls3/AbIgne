package com.github.Imphuls3.abigne.common.blockentity;

import com.github.Imphuls3.abigne.core.blockentity.*;
import com.github.Imphuls3.abigne.core.helper.BlockHelper;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;

public class CopperPotBlockEntity extends FluidTankBlockEntity implements ITankProvider {
    public ExtendedFluidTank tank2 = new ExtendedFluidTank(getTankCapacity()){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

    public ExtendedItemStackHandler inventory = new ExtendedItemStackHandler(1, 64) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

    public CopperPotBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public CopperPotBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.COPPER_POT.get(), pos, state);
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if(!player.isCrouching()){
            int amount = 1000;
            if(transferBucket(player, hand, tank, amount, IFluidHandler.FluidAction.SIMULATE)) {
                transferBucket(player, hand, tank, amount, IFluidHandler.FluidAction.EXECUTE);
            } else if(transferBucket(player, hand, tank2, amount, IFluidHandler.FluidAction.SIMULATE)) {
                transferBucket(player, hand, tank2, amount, IFluidHandler.FluidAction.EXECUTE);
            }
            //Items
            if(!(player.getItemInHand(hand).getItem() instanceof BucketItem)) {
                inventory.interact(player.level, player, hand);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        inventory.save(nbt, "inventory");
        nbt.put("tank2", tank2.writeToNBT(new CompoundTag()));
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        inventory.load(nbt, "inventory");
        if (nbt.contains("tank2")) {
            tank2.setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompound("tank2")));
        } else {
            tank2.setFluid(FluidStack.EMPTY);
        }
        super.load(nbt);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return inventory.invOptional.cast();
        }
        return super.getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return inventory.invOptional.cast();
        }
        if (cap == ForgeCapabilities.FLUID_HANDLER && side.equals(Direction.UP)) {
            return tank.fluidOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public ExtendedFluidTank getTank() {
        return tank.getFluid().getAmount() == tank.getCapacity() ? tank2 : tank;
    }

    public ExtendedFluidTank getTank(int index) {
        return index == 0 ? tank : tank2;
    }

    @Override
    public boolean isOutput(Direction direction) {
        return direction != Direction.UP;
    }

    @Override
    public boolean isInput(Direction direction) {
        return direction == Direction.UP;
    }

    @Override
    public boolean isPressurized() {
        return false;
    }
}
