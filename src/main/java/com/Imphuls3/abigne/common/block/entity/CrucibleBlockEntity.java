package com.Imphuls3.abigne.common.block.entity;

import com.Imphuls3.abigne.AbIgne;
import com.Imphuls3.abigne.api.ignis.AbstractIgnisMachine;
import com.Imphuls3.abigne.common.block.entity.utils.ModInventory;
import com.Imphuls3.abigne.common.item.Wand;
import com.Imphuls3.abigne.common.item.WandPart;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import com.Imphuls3.abigne.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;

public class CrucibleBlockEntity extends AbstractIgnisMachine {
    public boolean updateRecipe;

    private int maxIgnis = 500;

    public ModInventory mainInv;
    public ModInventory outInv;

    public CrucibleBlockEntity(BlockEntityType<? extends CrucibleBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public CrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.CRUCIBLE.get(), pos, state);
        mainInv = new ModInventory(8, 1, t -> !(t.getItem() == ItemInit.IGNIS_NUGGET.get())) {
            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                BlockHelper.updateStateAndNeighbor(level, worldPosition);
            }
        };
        outInv = new ModInventory(1, 64) {
            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                BlockHelper.updateStateAndNeighbor(level, worldPosition);
            }
        };
    }

    private void craft(){
        for (int i = 0; i < 8; i++) {
            int count;
            if(mainInv.getStackInSlot(i).getItem() == ItemInit.PYROLITE_SHARD.get() && this.getIgnis() >= 100){
                mainInv.setStackInSlot(i, ItemStack.EMPTY);
                this.removeIgnis(100);
                if(outInv.getStackInSlot(0).getItem() == ItemInit.IGNIS_INGOT.get()){
                    count = outInv.getStackInSlot(0).getCount();
                    outInv.getStackInSlot(0).setCount(count + 1);
                } else {
                    outInv.setStackInSlot(0, ItemInit.IGNIS_INGOT.get().getDefaultInstance());
                }
            }
        }
    }

    @Override
    public void tick() {
        if(!level.isClientSide){
            if(!this.level.hasNeighborSignal(this.getBlockPos())){
                craft();
            }
        }
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if (level.isClientSide) {
            return InteractionResult.FAIL;
        }

        String ignisText = "Ignis amount: " + this.getIgnis();
        TextComponent msg = new TextComponent(ignisText);
        player.sendMessage(msg, player.getUUID());
        if(player.getItemInHand(hand).getItem() == Items.STICK){;
            outInv.setStackInSlot(0, ItemInit.WAND.get().getDefaultInstance());
            return InteractionResult.SUCCESS;
        }
        if (hand.equals(InteractionHand.MAIN_HAND)) {
            ItemStack heldStack = player.getMainHandItem();
            if (!outInv.getStackInSlot(0).isEmpty() && heldStack.isEmpty()) {
                ItemStack stack = outInv.invInteract(level, player, hand);
                if (!stack.isEmpty()) {
                    return InteractionResult.SUCCESS;
                }
            }
            mainInv.invInteract(level, player, hand);
            if (heldStack.isEmpty()) {
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.FAIL;
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
        mainInv.save(nbt, "mainInv");
        outInv.save(nbt, "outInv");
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        updateRecipe = true;
        mainInv.load(nbt, "mainInv");
        outInv.load(nbt, "outInv");
        super.load(nbt);
    }


    @Override
    public void onBreak() {
        mainInv.dropItems(level, worldPosition);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return mainInv.invOptional.cast();
        }
        return super.getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction direction) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && direction == Direction.DOWN) {
            return outInv.invOptional.cast();
        } else if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return mainInv.invOptional.cast();
        }
        return super.getCapability(cap, direction);
    }
}