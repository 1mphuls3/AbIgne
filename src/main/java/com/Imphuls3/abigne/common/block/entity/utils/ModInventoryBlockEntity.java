package com.Imphuls3.abigne.common.block.entity.utils;

import com.Imphuls3.abigne.core.helper.DataHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;

public class ModInventoryBlockEntity extends ModBlockEntity {
    //A base block entity with a single inventory
    public ModBlockEntityInventory inv;

    public ModInventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    //places items in the slot when right clicked with an item
    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        inv.interact(player.level, player, hand);
        return InteractionResult.SUCCESS;
    }

    //drops items when broken
    @Override
    public void onBreak() {
        inv.dropItems(level, DataHelper.fromBlockPos(worldPosition).add(0.5f,0.5f,0.5f));
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        inv.save(compound);
        super.saveAdditional(compound);
    }

    @Override
    public void load(CompoundTag compound) {
        inv.load(compound);
        super.load(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap)
    {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return inv.invOptional.cast();
        }
        return super.getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side)
    {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
        {
            return inv.invOptional.cast();
        }
        return super.getCapability(cap, side);
    }
}
