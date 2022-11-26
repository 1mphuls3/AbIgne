package com.github.Imphuls3.abigne.common.blockentity;

import com.github.Imphuls3.abigne.common.block.TankBlock;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nonnull;

public class TankBlockEntity extends FluidTankBlockEntity implements ITankProvider {
    public ExtendedItemStackHandler inventory = new ExtendedItemStackHandler(1, 64) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

    public TankBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public TankBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.TANK.get(), pos, state);
    }

    @Override
    public void tick(Level level, BlockState state, BlockPos pos) {
        if (level.getBlockEntity(pos.below())instanceof TankBlockEntity tank) {
            if ((tank).getTank().getFluid().getAmount() < (tank).getTank().getCapacity()
                    && this.getTank().getFluid().getAmount() > 0) {
                int change = this.getTank().getFluid().getAmount();
                if (tank.getTank().getFluid().getAmount() + change > tank.getTank().getCapacity())
                    change = tank.getTank().getCapacity() - tank.getTank().getFluid().getAmount();
                tank.getTank().fill(new FluidStack(this.getTank().getFluid().getFluid(), change), IFluidHandler.FluidAction.EXECUTE);
                this.getTank().setFluidAmount(this.getTank().getFluid().getAmount()-change);
                tank.getTank().setChanged();
                this.getTank().setChanged();
            }
        }
        super.tick(level, state, pos);
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        int j = 1;
        int k = 1;
        if(!player.isCrouching() && player.getItemInHand(hand).getItem() instanceof BucketItem){
            if(!level.getBlockState(getBlockPos()).getValue(TankBlock.DOWN) && this.getTank().getPressure() != 1) {
                transferBucket(player, hand, this.getTank(), 1000, IFluidHandler.FluidAction.EXECUTE);
            } else if(level.getBlockState(getBlockPos()).getValue(TankBlock.UP) && this.getTank().getPressure() == 1) {
                for (int i = 0; i < k; i++) {
                    if(level.getBlockEntity(worldPosition.above(k)) instanceof TankBlockEntity t) {
                        if(t.getTank().getPressure() == 1) {
                            k++;
                        } else if(t.getTank().getFluid().getAmount() <= t.getTank().getCapacity()-1000) {
                            transferBucket(player, hand, t.getTank(), 1000, IFluidHandler.FluidAction.EXECUTE);
                        }
                    }
                }
            } else if(level.getBlockState(getBlockPos()).getValue(TankBlock.DOWN)) {
                for (int i = 0; i < j; i++) {
                    if(level.getBlockEntity(worldPosition.below(j)) instanceof TankBlockEntity t) {
                        TankBlockEntity tank = (TankBlockEntity)level.getBlockEntity(t.getBlockPos().above());
                        if(t.getTank().getPressure() == 1) {
                            transferBucket(player, hand, tank.getTank(), 1000, IFluidHandler.FluidAction.EXECUTE);
                        } else if(t.getTank().getFluid().getAmount() > t.getTank().getCapacity()-1000) {
                            int amount = t.getTank().getCapacity()-t.getTank().getFluid().getAmount();
                            transferBucket(player, hand, t.getTank(), amount, IFluidHandler.FluidAction.EXECUTE);
                            transferBucket(player, hand, tank.getTank(), 1000-amount, IFluidHandler.FluidAction.EXECUTE);
                        } else if(level.getBlockEntity(t.getBlockPos().below()) instanceof TankBlockEntity) {
                            j++;
                        } else {
                            transferBucket(player, hand, t.getTank(), 1000, IFluidHandler.FluidAction.EXECUTE);
                        }
                    }
                }
            }
        }
        if(player.getItemInHand(hand).isEmpty()) { //TODO REMOVE AFTER DEV
            this.tank.setFluid(FluidStack.EMPTY);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        inventory.save(nbt, "inventory");
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        inventory.load(nbt, "inventory");
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
            return LazyOptional.of(() -> tank).cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public boolean isOutput(Direction direction) {
        return level.getBlockEntity(worldPosition.relative(direction)) instanceof PipeBlockEntity;
    }

    @Override
    public boolean isInput(Direction direction) {
        return true;
    }

    @Override
    public boolean isPressurized() {
        return false;
    }
}
