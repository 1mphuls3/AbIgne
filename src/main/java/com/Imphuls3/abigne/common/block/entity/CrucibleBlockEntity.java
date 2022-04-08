package com.Imphuls3.abigne.common.block.entity;

import com.Imphuls3.abigne.common.block.entity.utils.ModBlockEntity;
import com.Imphuls3.abigne.common.inventory.ModBlockEntityInventory;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import com.Imphuls3.abigne.core.helper.DataHelper;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class CrucibleBlockEntity extends ModBlockEntity {
    public float speed;
    public int progress;
    public int spinUp;

    public ArrayList<BlockPos> acceleratorPositions = new ArrayList<>();
    public boolean updateAccelerators;

    public boolean updateRecipe;

    public ModBlockEntityInventory inventory;
    public ModBlockEntityInventory extrasInventory;

    public CrucibleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public CrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.CRUCIBLE.get(), pos, state);

        inventory = new ModBlockEntityInventory(1, 64) {
            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                updateRecipe = true;
                BlockHelper.updateAndNotifyState(level, worldPosition);
            }
        };
        extrasInventory = new ModBlockEntityInventory(8, 1) {
            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                BlockHelper.updateAndNotifyState(level, worldPosition);
            }
        };
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        inventory.save(compound);
        extrasInventory.save(compound, "extrasInventory");
    }

    @Override
    public void load(CompoundTag compound) {
        updateRecipe = true;
        inventory.load(compound);
        extrasInventory.load(compound, "extrasInventory");
        super.load(compound);
    }


    @Override
    public void onBreak() {
        inventory.dumpItems(level, worldPosition);
        extrasInventory.dumpItems(level, worldPosition);
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if (hand.equals(InteractionHand.MAIN_HAND)) {
            ItemStack heldStack = player.getMainHandItem();

            if (!(heldStack.getItem() == Items.IRON_INGOT)) {
                ItemStack stack = inventory.interact(level, player, hand);
                if (!stack.isEmpty())
                {
                    return InteractionResult.SUCCESS;
                }
            }
            extrasInventory.interact(level, player, hand);
            if (heldStack.isEmpty()) {
                return InteractionResult.SUCCESS;
            }
        }
        return super.onUse(player, hand);
    }

    @Override
    public void tick() {
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