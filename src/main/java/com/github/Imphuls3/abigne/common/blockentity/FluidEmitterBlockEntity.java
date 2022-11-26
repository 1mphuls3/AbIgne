package com.github.Imphuls3.abigne.common.blockentity;

import com.github.Imphuls3.abigne.core.blockentity.AbIgneBlockEntity;
import com.github.Imphuls3.abigne.core.blockentity.ExtendedFluidTank;
import com.github.Imphuls3.abigne.core.helper.BlockHelper;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class FluidEmitterBlockEntity extends AbIgneBlockEntity implements IFluidTank {
    public ExtendedFluidTank tank = new ExtendedFluidTank(1000){
        @Override
        protected void onContentsChanged() {
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

    public FluidEmitterBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public FluidEmitterBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FLUID_EMITTER.get(), pos, state);
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if(!player.isCrouching()){
            if (player.getItemInHand(hand).getItem() == Items.BUCKET && tank.getFluidAmount() > 0) {
                player.setItemInHand(hand, new ItemStack(tank.getFluid().getFluid().getBucket()));
                tank.drain(1000, IFluidHandler.FluidAction.EXECUTE);
            } else if (player.getItemInHand(hand).getItem() instanceof BucketItem item && !(item instanceof MobBucketItem) &&
                    (tank.fill(new FluidStack(item.getFluid(), item.getFluid().getAmount(item.getFluid().defaultFluidState())), IFluidHandler.FluidAction.SIMULATE) > 0)) {
                tank.fill(new FluidStack(item.getFluid(), item.getFluid().getAmount(item.getFluid().defaultFluidState())), IFluidHandler.FluidAction.EXECUTE);
                player.setItemInHand(hand, Items.BUCKET.getDefaultInstance());
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        tank.save("tank_1", nbt);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        tank.load(nbt.getCompound("tank_1"));
        super.load(nbt);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return super.getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return tank.fluidOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @NotNull
    @Override
    public FluidStack getFluid() {
        return tank.getFluid();
    }

    @Override
    public int getFluidAmount() {
        return tank.getFluidAmount();
    }

    @Override
    public int getCapacity() {
        return tank.getCapacity();
    }

    @Override
    public boolean isFluidValid(FluidStack stack) {
        return true;
    }

    @Override
    public int fill(FluidStack resource, IFluidHandler.FluidAction action) {
        return tank.fill(resource, action);
    }

    @NotNull
    @Override
    public FluidStack drain(int maxDrain, IFluidHandler.FluidAction action) {
        return tank.drain(maxDrain, action);
    }

    @NotNull
    @Override
    public FluidStack drain(FluidStack resource, IFluidHandler.FluidAction action) {
        return drain(resource, action);
    }
}
