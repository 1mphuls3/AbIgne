package com.Imphuls3.abigne.common.block.entity;

import com.Imphuls3.abigne.api.ignis.IIgnis;
import com.Imphuls3.abigne.common.block.entity.utils.ModBlockEntity;
import com.Imphuls3.abigne.common.block.entity.utils.ModBlockEntityInventory;
import com.Imphuls3.abigne.common.ritual.Ritual;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import com.Imphuls3.abigne.core.helper.DataHelper;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import com.Imphuls3.abigne.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import java.util.List;

public class CrucibleBlockEntity extends ModBlockEntity {

    public boolean updateRecipe;
    public ModBlockEntityInventory inventory;
    public ModBlockEntityInventory extrasInventory;

    public CrucibleBlockEntity(BlockEntityType<? extends CrucibleBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
    public CrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.CRUCIBLE.get(), pos, state);

        inventory = new ModBlockEntityInventory(1, 1) {
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

    private void crucibleAlloy(){
        List<PedestalBlockEntity> blockEntities = Ritual.getBlockEntitiesWithinAABB(PedestalBlockEntity.class, level, Ritual.getDefaultBounds(this.getBlockPos()));
        //PedestalBlockEntity bEOne = blockEntities.get(0);;
        //if(bEOne != null){
            if(inventory.getStackInSlot(0).getItem() == Items.COPPER_INGOT && extrasInventory.getStackInSlot(0).getItem() == ItemInit.IGNIS_SHARD.get()
                    /*&& bEOne.inv.getStackInSlot(0).getItem() == ItemInit.IGNIS_SHARD.get()*/) {

                /*bEOne.inv.getStackInSlot(0).setCount(bEOne.inv.getStackInSlot(0).getCount() - 1);*/
                this.inventory.clearInv();
                this.extrasInventory.getStackInSlot(0).setCount(0);
                this.inventory.setStackInSlot(0, ItemInit.IGNIS_INGOT.get().getDefaultInstance());
                this.inventory.getStackInSlot(0).setCount(1);
                for (int i = 0; i < 8; i++) {
                    this.level.addParticle(ParticleTypes.FLAME, this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(),
                            Mth.randomBetween(getLevel().random, 0, 0.5f),
                            Mth.randomBetween(getLevel().random, 0, 1),
                            Mth.randomBetween(getLevel().random, 0, 0.5f));
                }
            }
        //}
    }

    @Override
    public void tick() {
        if(!level.isClientSide()){
            crucibleAlloy();
            if(!this.inventory.getStackInSlot(0).isEmpty()){

            }
        }
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if (level.isClientSide) {
            return InteractionResult.FAIL;
        }
        if (hand.equals(InteractionHand.MAIN_HAND)) {
            ItemStack heldStack = player.getMainHandItem();
            if (!inventory.getStackInSlot(0).isEmpty()) {
                ItemStack stack = extrasInventory.interact(level, player, hand);
                if (!stack.isEmpty()) {
                    return InteractionResult.SUCCESS;
                }
            }
            inventory.interact(level, player, hand);
            if (heldStack.isEmpty()) {
                return InteractionResult.SUCCESS;
            } else {
                return InteractionResult.FAIL;
            }
        }
        return super.onUse(player, hand);
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
        inventory.dropItems(level, worldPosition);
        extrasInventory.dropItems(level, worldPosition);
    }

    public static Vec3 itemPos(CrucibleBlockEntity blockEntity) {
        return DataHelper.fromBlockPos(blockEntity.getBlockPos()).add(blockEntity.itemOffset());
    }

    public Vec3 itemOffset() {
        return new Vec3(0.5f, 1.25f, 0.5f);
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