package com.Imphuls3.abigne.common.blockentity;

import com.Imphuls3.abigne.core.systems.ignis.AbstractIgnisMachine;
import com.Imphuls3.abigne.core.systems.blockentity.AbIgneInventory;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import com.Imphuls3.abigne.core.registry.common.BlockEntityRegistry;
import com.Imphuls3.abigne.core.registry.common.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;

public class CrucibleBlockEntity extends AbstractIgnisMachine {
    public boolean updateRecipe;

    private int maxIgnis = 500;

    public AbIgneInventory inventory = new AbIgneInventory(8, 1, t -> !(t.getItem() == ItemRegistry.IGNIS_NUGGET.get())) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

    public CrucibleBlockEntity(BlockEntityType<? extends CrucibleBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public CrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.CRUCIBLE.get(), pos, state);

    }

    private void craft(){
        for (int i = 0; i < inventory.getSlots(); i++) {
            if(inventory.getStackInSlot(i).getItem() == ItemRegistry.PYROLITE_SHARD.get()/* && this.getIgnis() >= 100*/){
                inventory.setStackInSlot(i, ItemStack.EMPTY);
/*                this.removeIgnis(100)*/;
                ItemEntity item = new ItemEntity(level, getBlockPos().getX() + 0.5, getBlockPos().getY() + 1, getBlockPos().getZ() + 0.5,
                        ItemRegistry.IGNIS_INGOT.get().getDefaultInstance());
                level.addFreshEntity(item);
            }
        }
    }

    @Override
    public void tick(Level level, BlockState state, BlockPos pos) {
        if(!this.level.isClientSide){
            if(!this.level.hasNeighborSignal(this.getBlockPos())){
                craft();
            }
        }
    }

    public InteractionResult onUse(Player player, InteractionHand hand) {
        if (level.isClientSide) {
            return InteractionResult.FAIL;
        }
        if (hand.equals(InteractionHand.MAIN_HAND)) {
            ItemStack stack = inventory.invInteract(level, player, hand);
            if (!stack.isEmpty()) {
                return InteractionResult.SUCCESS;
            }
        }
        return super.onUse(player, hand);
    }

    public void explodeBlock(Direction facing){
        BlockPos pos = this.getBlockPos().relative(facing);
        this.level.destroyBlock(pos, true);
    }
    @Override
    public int getTransferRate() {
        return getMaxIgnis();
    }

    @Override
    public int getMaxIgnis() {
        return this.maxIgnis;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        inventory.save(nbt, "mainInv");
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        updateRecipe = true;
        inventory.load(nbt, "mainInv");
        super.load(nbt);
    }


    @Override
    public void onBreak() {
        inventory.dropItems(level, worldPosition);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventory.invOptional.cast();
        }
        return super.getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction direction) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventory.invOptional.cast();
        }
        return super.getCapability(cap, direction);
    }
}