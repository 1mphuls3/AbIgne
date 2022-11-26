package com.github.Imphuls3.abigne.common.blockentity;

import com.github.Imphuls3.abigne.core.blockentity.AbIgneBlockEntity;
import com.github.Imphuls3.abigne.core.blockentity.ExtendedItemStackHandler;
import com.github.Imphuls3.abigne.core.helper.BlockHelper;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
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
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class InfuserBlockEntity extends AbIgneBlockEntity {
    int maxIgnis = 10000;

    public ExtendedItemStackHandler inventory = new ExtendedItemStackHandler(2, 64) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

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
        if(!player.isCrouching()){
            inventory.interact(player.level, player, hand);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void onBreak() {
        inventory.dropItems(level, worldPosition);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        inventory.save(nbt, "inventory");
        nbt.putFloat("alpha", alpha);
        nbt.putFloat("red", red);
        nbt.putFloat("green", green);
        nbt.putFloat("blue", blue);
        nbt.putFloat("red2", red2);
        nbt.putFloat("green2", green2);
        nbt.putFloat("blue2", blue2);
        nbt.putFloat("speed", speed);
        nbt.putFloat("scale", scale);
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

    @Override
    public void load(CompoundTag nbt) {
        inventory.load(nbt, "inventory");
        alpha = nbt.getFloat("alpha");
        red = nbt.getFloat("red");
        green = nbt.getFloat("green");
        blue = nbt.getFloat("blue");
        red2 = nbt.getFloat("red2");
        green2 = nbt.getFloat("green2");
        blue2 = nbt.getFloat("blue2");
        scale = nbt.getFloat("scale");
        speed = nbt.getFloat("speed");
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
        return super.getCapability(cap, side);
    }
}
