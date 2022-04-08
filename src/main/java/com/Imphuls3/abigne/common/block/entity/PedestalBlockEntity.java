package com.Imphuls3.abigne.common.block.entity;

import com.Imphuls3.abigne.common.block.entity.utils.ModInventoryBlockEntity;
import com.Imphuls3.abigne.common.block.iface.IInventoryProvider;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import com.Imphuls3.abigne.core.helper.DataHelper;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import com.Imphuls3.abigne.common.inventory.ModBlockEntityInventory;
import com.Imphuls3.abigne.core.init.BlockInit;
import com.Imphuls3.abigne.core.init.ItemInit;
import net.minecraft.client.particle.AshParticle;
import net.minecraft.client.particle.ExplodeParticle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.commands.ParticleCommand;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;

public class PedestalBlockEntity extends ModInventoryBlockEntity implements IInventoryProvider {
    int craftTimer = 0;

    public PedestalBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.PEDESTAL.get(), pos, state);
        inv = new ModBlockEntityInventory(1, 64) {
            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                BlockHelper.updateAndNotifyState(level, worldPosition);
            }
        };
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if(!player.isCrouching()){
            inv.interact(player.level, player, hand);
        }
        return InteractionResult.SUCCESS;
    }


    public void infuse() {
        int count = this.inv.getStackInSlot(0).getCount();
        if(this.inv.getStackInSlot(0).getItem() == BlockInit.CHARRED_LOG.get().asItem()) {
            this.inv.clearInv();
            this.inv.setStackInSlot(0, ItemInit.ASHES.get().getDefaultInstance());
            this.inv.getStackInSlot(0).setCount(count);
        } else if(this.inv.getStackInSlot(0).getItem() == Blocks.OAK_LOG.asItem()) {
            this.inv.clearInv();
            this.inv.setStackInSlot(0, ItemInit.CHARRED_LOG_ITEM.get().getDefaultInstance());
            this.inv.getStackInSlot(0).setCount(count);
        }
    }

    @Override
    public ModBlockEntityInventory providedInv() {
        return inv;
    }

    @Override
    public Vec3 providedIPos() {
        return itemPos(this);
    }

    public static Vec3 itemPos(ModInventoryBlockEntity blockEntity) {
        return DataHelper.fromBlockPos(blockEntity.getBlockPos()).add(offset());
    }

    public static Vec3 offset() {
        return new Vec3(0.5f, 1.1f, 0.5f);
    }

    @Override
    public void tick() {
        if (!level.isClientSide) {
            if(!this.inv.getStackInSlot(0).isEmpty()){
                craftTimer++;
                if(craftTimer == (10*(this.inv.getStackInSlot(0).getCount()))) {
                    craftTimer = 0;
                    this.infuse();

                }
            } else {
                craftTimer = 0;
            }
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inv.invOptional.cast();
        }
        return super.getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inv.invOptional.cast();
        }
        return super.getCapability(cap, side);
    }
}