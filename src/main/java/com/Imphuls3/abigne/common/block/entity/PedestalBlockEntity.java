package com.Imphuls3.abigne.common.block.entity;

import com.Imphuls3.abigne.common.block.entity.utils.ModBlockEntity;
import com.Imphuls3.abigne.common.block.entity.utils.ModInventory;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;

public class PedestalBlockEntity extends ModBlockEntity {
    public ModInventory inventory = new ModInventory(1, 64) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

    public PedestalBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.PEDESTAL.get(), pos, state);
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if(!player.isCrouching()){
            inventory.invInteract(player.level, player, hand);
        }
        return InteractionResult.SUCCESS;
    }

    public void tick(Level level, BlockState state, BlockPos pos) {

    }

    @Override
    public void onBreak() {
        inventory.dropItems(level, worldPosition);
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
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventory.invOptional.cast();
        }
        return super.getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventory.invOptional.cast();
        }
        return super.getCapability(cap, side);
    }
}