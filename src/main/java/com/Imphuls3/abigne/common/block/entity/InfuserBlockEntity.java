package com.Imphuls3.abigne.common.block.entity;

import com.Imphuls3.abigne.api.ignis.AbstractIgnisMachine;
import com.Imphuls3.abigne.common.block.entity.utils.ModInventory;
import com.Imphuls3.abigne.common.item.util.ModTags;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;

public class InfuserBlockEntity extends AbstractIgnisMachine {
    int maxIgnis = 10000;

    public ModInventory inventory = new ModInventory(2, 64) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };
    public ModInventory catalystInv = new ModInventory(1, 1) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

    private int progress = 0;
    private int maxProgress = 72;


    public InfuserBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public InfuserBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.INFUSER.get(), pos, state);
    }

    @Override
    public void tick(Level level, BlockState state, BlockPos pos) {

    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if (level.isClientSide) {
            return InteractionResult.FAIL;
        }
        if (hand.equals(InteractionHand.MAIN_HAND)) {
            ItemStack heldStack = player.getMainHandItem();
            if (heldStack.is(ModTags.Items.INFUSER_CATALYSTS) || inventory.getItems().isEmpty()) {
                ItemStack stack = catalystInv.insertItem(1, heldStack, false);
                if (!stack.isEmpty()) {
                    return InteractionResult.SUCCESS;
                }
            }
            inventory.invInteract(level, player, hand);
            if (heldStack.isEmpty()) {
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.FAIL;
            }
        }
        return super.onUse(player, hand);
    }

    @Override
    public void onBreak() {
        inventory.dropItems(level, worldPosition);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        inventory.save(nbt, "inventory");
        catalystInv.save(nbt, "catalysts");
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        inventory.load(nbt, "inventory");
        catalystInv.load(nbt, "catalysts");
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

    @Override
    public int getTransferRate() {
        return maxIgnis;
    }
}
