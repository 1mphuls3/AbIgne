package com.github.Imphuls3.abigne.common.blockentity;

import com.github.Imphuls3.abigne.core.blockentity.AbIgneBlockEntity;
import com.github.Imphuls3.abigne.core.blockentity.ExtendedItemStackHandler;
import com.github.Imphuls3.abigne.core.helper.BlockHelper;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DaylightDetectorBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DaylightDetectorBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import java.util.List;

public class AstralFocusBlockEntity extends AbIgneBlockEntity {
    public ExtendedItemStackHandler inventory = new ExtendedItemStackHandler(1, 64) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };
    public boolean isAstralDay;
    public boolean isAstralNight;

    public AstralFocusBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    /*public AstralFocusBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.PEDESTAL.get(), pos, state);
    }*/

    public void tick(Level level, BlockState state, BlockPos pos) {
        if(level.isDay() && level.dimension() == Level.OVERWORLD) isAstralDay = true; else if(level.dimension() == Level.OVERWORLD) isAstralNight = true;
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
        nbt.putBoolean("astralDay", isAstralDay);
        nbt.putBoolean("astralNight", isAstralNight);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        inventory.load(nbt, "inventory");
        isAstralDay = nbt.contains("astralDay") && nbt.getBoolean("astralDay");
        isAstralNight = nbt.contains("astralNight") && nbt.getBoolean("astralNight");
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