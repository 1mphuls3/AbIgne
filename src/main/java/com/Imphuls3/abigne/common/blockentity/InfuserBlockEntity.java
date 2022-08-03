package com.Imphuls3.abigne.common.blockentity;

import com.Imphuls3.abigne.core.systems.ignis.AbstractIgnisMachine;
import com.Imphuls3.abigne.core.systems.blockentity.AbIgneInventory;
import com.Imphuls3.abigne.common.item.util.AbIgneTags;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import com.Imphuls3.abigne.core.registry.common.BlockEntityRegistry;
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

    public AbIgneInventory inventory = new AbIgneInventory(2, 64) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };
    public AbIgneInventory catalystInv = new AbIgneInventory(1, 1) {
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
        super(BlockEntityRegistry.INFUSER.get(), pos, state);
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
            if (heldStack.is(AbIgneTags.Items.INFUSER_CATALYSTS) || inventory.getItems().isEmpty()) {
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
        nbt.putFloat("alpha", alpha);
        nbt.putFloat("red", red);
        nbt.putFloat("green", green);
        nbt.putFloat("blue", blue);
        nbt.putFloat("red2", red2);
        nbt.putFloat("green2", green2);
        nbt.putFloat("blue2", blue2);
        nbt.putFloat("speed", speed);
        nbt.putFloat("scale", scale);
        nbt.putFloat("scale2", scale2);
        super.saveAdditional(nbt);
    }

    float alpha = 0.9f;
    float red = 1f;
    float green = 0.1f;
    float blue = 0.1f;
    float red2 = 0.5f;
    float green2 = 0.1f;
    float blue2 = 1f;
    float speed = 3f;
    float scale = 2f;
    float scale2 = 3f;

    public float getAlpha() {
        return alpha;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getRed2() {
        return red2;
    }

    public float getGreen2() {
        return green2;
    }

    public float getBlue2() {
        return blue2;
    }

    public float getSpeed() {
        return speed;
    }

    public float getScale(){
        return scale;
    }

    public float getScale2(){
        return scale2;
    }

    @Override
    public void load(CompoundTag nbt) {
        inventory.load(nbt, "inventory");
        catalystInv.load(nbt, "catalysts");
        alpha = nbt.getFloat("alpha");
        red = nbt.getFloat("red");
        green = nbt.getFloat("green");
        blue = nbt.getFloat("blue");
        red2 = nbt.getFloat("red2");
        green2 = nbt.getFloat("green2");
        blue2 = nbt.getFloat("blue2");
        speed = nbt.getFloat("speed");
        scale = nbt.getFloat("scale");
        scale2 = nbt.getFloat("scale2");
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
