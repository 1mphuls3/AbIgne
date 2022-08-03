package com.Imphuls3.abigne.common.blockentity;

import com.Imphuls3.abigne.core.systems.ignis.AbstractIgnisMachine;
import com.Imphuls3.abigne.core.registry.common.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class IgnisVoidBlockEntity extends AbstractIgnisMachine {
    private int maxIgnis = Integer.MAX_VALUE;

    public IgnisVoidBlockEntity(BlockEntityType<? extends IgnisVoidBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public IgnisVoidBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.VOID.get(), pos, state);
    }

    @Override
    public void tick(Level level, BlockState state, BlockPos pos) {
        if(!this.level.isClientSide){
            this.setIgnis(0);
        }
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        return super.onUse(player, hand);
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
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
    }
}