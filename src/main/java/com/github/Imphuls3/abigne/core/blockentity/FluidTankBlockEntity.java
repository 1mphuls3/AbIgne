package com.github.Imphuls3.abigne.core.blockentity;

import com.github.Imphuls3.abigne.config.FluidTransportConfig;
import com.github.Imphuls3.abigne.core.helper.BlockHelper;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.ArrayList;
import java.util.List;

public abstract class FluidTankBlockEntity extends AbIgneBlockEntity implements ITankProvider {
    public ExtendedFluidTank tank = new ExtendedFluidTank(getTankCapacity()){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

    public FluidTankBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state)  {
        super(type, pos, state);
    }

    @Override
    public void tick(Level level, BlockState state, BlockPos pos) {
        if (tank.getFluid().getAmount() == 0) return;
        List<ITankProvider> tanks = new ArrayList<>();
        for (Direction d : Direction.values()) {
            if (isOutput(d)) {
                BlockEntity t = level.getBlockEntity(worldPosition.relative(d));
                if (t instanceof ITankProvider toTank
                        && (d != Direction.UP)
                        && (toTank.isInput(d.getOpposite()))
                        && (toTank.getTank().fill(tank.getFluid(), IFluidHandler.FluidAction.SIMULATE) > 0)
                        && (toTank.getTank().getPressure() < tank.getPressure() || d == Direction.DOWN)) //TODO add valves/routing and redstone logistics, sprinklers
                    tanks.add(toTank);
            }
        }
        if (tanks.isEmpty()) return;
        for(ITankProvider tank : tanks) {
            if(this.getTank().getPressure() > tank.getTank().getPressure()) {
                int viscosity = this.getTank().getFluid().getFluid().getFluidType().getViscosity();
                int flowRate = FluidTransportConfig.fluidFlowRate.get()/* default 100 *//(viscosity/500);
                int diff = Math.min(flowRate, Integer.MAX_VALUE);
                tank.getTank().setFluid(new FluidStack(this.getTank().getFluid(), tank.getTank().getFluid().getAmount()+diff));
                this.getTank().setFluid(new FluidStack(this.getTank().getFluid(), this.getTank().getFluidAmount()-diff));
                //TODO this is literal garbage code
            }
        }
    }

    /*@Override
    public boolean isPressurized() {
        List<BlockEntity> list = new ArrayList<>();
        for (int i = 0; i <= 7; i++) { //TODO If there is a pressurizer within 7 blocks below the pipe
            BlockEntity blockEntity = this.getLevel().getBlockEntity(getBlockPos().relative(Direction.UP));
            list.add(blockEntity);
        }
        return !list.isEmpty();
    }*/

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if(!player.isCrouching()){
            transferBucket(player, hand, this.getTank(), 1000, IFluidHandler.FluidAction.EXECUTE);
        } else if(player.getItemInHand(hand).isEmpty()) { //TODO REMOVE AFTER DEV
            this.tank.setFluid(FluidStack.EMPTY);
        }
        return InteractionResult.SUCCESS;
    }

    public boolean transferBucket(Player player, InteractionHand hand, FluidTank tank, int amount, IFluidHandler.FluidAction action) {
        if (player.getItemInHand(hand).getItem() == Items.BUCKET && tank.getFluidAmount() >= amount) {
            if(!action.simulate()) {
                player.setItemInHand(hand, new ItemStack(tank.getFluid().getFluid().getBucket()));
                tank.drain(amount, IFluidHandler.FluidAction.EXECUTE);
            }
            return true;
        } else if (player.getItemInHand(hand).getItem() instanceof BucketItem item && !(item instanceof MobBucketItem) &&
                (tank.fill(new FluidStack(item.getFluid(), amount), IFluidHandler.FluidAction.SIMULATE) > 0)) {
            if(!action.simulate()) {
                tank.fill(new FluidStack(item.getFluid(), amount), IFluidHandler.FluidAction.EXECUTE);
                if(!player.isCreative()) {
                    player.setItemInHand(hand, Items.BUCKET.getDefaultInstance());
                }
            }
            return true;
        }
        return false;
    }

    public int getTankCapacity(){
        return 8000;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.put("tank", tank.writeToNBT(new CompoundTag()));
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        tank.readFromNBT(nbt);
        if (nbt.contains("tank")) {
            tank.setFluid(FluidStack.loadFluidStackFromNBT(nbt.getCompound("tank")));
        } else {
            tank.setFluid(FluidStack.EMPTY);
        }
        super.load(nbt);
    }

    @Override
    public ExtendedFluidTank getTank() {
        return tank;
    }
}
