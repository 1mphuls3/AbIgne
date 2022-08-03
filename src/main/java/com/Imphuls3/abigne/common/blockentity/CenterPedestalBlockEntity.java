package com.Imphuls3.abigne.common.blockentity;

import com.Imphuls3.abigne.core.systems.blockentity.AbIgneInventory;
import com.Imphuls3.abigne.core.systems.blockentity.AbIgneInventoryBlockEntity;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import com.Imphuls3.abigne.core.registry.common.BlockEntityRegistry;
import com.Imphuls3.abigne.core.registry.common.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class CenterPedestalBlockEntity extends AbIgneInventoryBlockEntity {
    public AbIgneInventory inventory = new AbIgneInventory(1, 64) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

    public CenterPedestalBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public CenterPedestalBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.RITUAL_PEDESTAL.get(), pos, state);
    }

    public void ritualActivate() {
        List<PedestalBlockEntity> blockEntities = BlockHelper.getBlockEntitiesWithinAABB(PedestalBlockEntity.class, this.getLevel(), BlockHelper.defaultBounds(this.getBlockPos()));
        List<Integer> indexes = new ArrayList<>();
        int beOneCount;
        int beTwoCount;

        //search through all Block Entities within AABB
        for (int i = 0; i < blockEntities.size(); i++) {

            int count = this.inventory.getStackInSlot(0).getCount();

            //get the index of the blockentity with a Copper ingot or Ignis shard in it
            if(blockEntities.get(i).inventory.getStackInSlot(0).getItem() == Items.COPPER_INGOT || blockEntities.get(i).inventory.getStackInSlot(0).getItem() == ItemRegistry.PYROLITE_SHARD.get()){
                indexes.add(i);
            }

            //check if there are at least two blocks with a Copper ingot or Ignis shard
            if(indexes.size() > 1){
                beOneCount = blockEntities.get(indexes.get(0)).inventory.getStackInSlot(0).getCount();
                beTwoCount = blockEntities.get(indexes.get(1)).inventory.getStackInSlot(0).getCount();

                if(blockEntities.get(indexes.get(0)).inventory.getStackInSlot(0).getItem() == Items.COPPER_INGOT
                        || blockEntities.get(indexes.get(0)).inventory.getStackInSlot(0).getItem() == ItemRegistry.PYROLITE_SHARD.get()){

                    if(blockEntities.get(indexes.get(1)).inventory.getStackInSlot(0).getItem() == Items.COPPER_INGOT
                            || blockEntities.get(indexes.get(1)).inventory.getStackInSlot(0).getItem() == ItemRegistry.PYROLITE_SHARD.get()){

                        blockEntities.get(indexes.get(0)).inventory.getStackInSlot(0).setCount(beOneCount - 1);
                        if(blockEntities.get(indexes.get(0)).inventory.getStackInSlot(0).getCount() == 0){
                            blockEntities.get(indexes.get(0)).inventory.setStackInSlot(0, ItemStack.EMPTY);
                        }

                        blockEntities.get(indexes.get(1)).inventory.getStackInSlot(0).setCount(beTwoCount - 1);
                        if(blockEntities.get(indexes.get(1)).inventory.getStackInSlot(0).getCount() == 0){
                            blockEntities.get(indexes.get(1)).inventory.setStackInSlot(0, ItemStack.EMPTY);
                        }

                        this.inventory.setStackInSlot(0, ItemRegistry.IGNIS_INGOT.get().getDefaultInstance());
                        this.inventory.getStackInSlot(0).setCount(count + 1);

                        indexes.clear();
                    }
                }
            }
        }
    }

    @Override
    public void tick(Level level, BlockState state, BlockPos pos) {
        if(!this.level.isClientSide()){
            ritualActivate();
        }
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if(!player.isCrouching()){
            inventory.invInteract(player.level, player, hand);
        }
        return InteractionResult.SUCCESS;
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