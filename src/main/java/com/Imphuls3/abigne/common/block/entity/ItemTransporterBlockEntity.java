package com.Imphuls3.abigne.common.block.entity;

import com.Imphuls3.abigne.api.ignis.AbstractIgnisMachine;
import com.Imphuls3.abigne.common.block.entity.utils.ModInventory;
import com.Imphuls3.abigne.common.item.TransporterBinder;
import com.Imphuls3.abigne.core.helper.BlockHelper;
import com.Imphuls3.abigne.core.helper.MathHelper;
import com.Imphuls3.abigne.core.helper.NBTHelper;
import com.Imphuls3.abigne.core.init.BlockEntityInit;
import com.Imphuls3.abigne.core.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;

public class ItemTransporterBlockEntity extends AbstractIgnisMachine {
    private final int maxIgnis = 1000;

    public ModInventory inventory;
    public ItemTransporterBlockEntity bound;
    public BlockPos boundPos;

    public ItemTransporterBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ItemTransporterBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.TRANSPORTER.get(), pos, state);
        inventory = new ModInventory(1, 64, t -> !(t.getItem() instanceof TransporterBinder)) {
            @Override
            public void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                BlockHelper.updateStateAndNeighbor(level, worldPosition);
            }
        };
    }

    int timer;
    float dist;
    float diag;
    @Override
    public void tick() {
        if(!this.getLevel().isClientSide()) {
            if(bound != null/* && canTransfer()*/) {
                float xDiff = Mth.abs(this.getBlockPos().getX() - bound.getBlockPos().getX());
                float yDiff = Mth.abs(this.getBlockPos().getY() - bound.getBlockPos().getY());
                float zDiff = Mth.abs(this.getBlockPos().getZ() - bound.getBlockPos().getZ());
/*                diag = Mth.sqrt((xDiff * xDiff) + (zDiff * zDiff));
                dist = Mth.sqrt((diag * diag) + (yDiff * yDiff));*/
                dist = MathHelper.pythagorean3D(xDiff, yDiff, zDiff);
                if(!inventory.getStackInSlot(0).isEmpty()) {
                    timer++;
                    if(timer == 10 * (int)dist) {
                        /*if(dist > 10) {
                            this.burnItems();
                        } else {*/
                            transferItems(bound, this, dist);
/*                            bound.addIgnis();*/
/*                        }*/
                        timer = 0;
                    }
                } else {
                    timer = 0;
                }
            }
        }
    }


    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if(!player.isCrouching()) {
            inventory.invInteract(player.level, player, hand);
            String txt = "Ignis amount: " + this.getIgnis();
            TextComponent msg = new TextComponent(txt);
            player.sendMessage(msg, player.getUUID());
        }
        if(player.getItemInHand(hand).getItem() == ItemInit.PYROLITE_SHARD.get()) {
            this.addIgnis(100);
        }
        if(player.getItemInHand(hand).getItem() instanceof TransporterBinder) {
            this.boundPos = TransporterBinder.getPos();
            this.bound = (ItemTransporterBlockEntity) getLevel().getBlockEntity(boundPos);
            String txt = "Bound!";
            TextComponent msg = new TextComponent(txt);
            player.sendMessage(msg, player.getUUID());
        }
        return InteractionResult.SUCCESS;
    }

    public void burnItems() {
        this.inventory.clear();
        BlockPos pos = this.getBlockPos();
        for (int i = 0; i < 5; i++) {
            this.getLevel().addParticle(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY() + 1.25, pos.getZ() + 0.5,
                    0, 0.3, 0);
            this.getLevel().addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 1.25, pos.getZ() + 0.5,
                    0, 0.45, 0);
        }
    }

    public boolean canTransfer() {
        return !level.hasNeighborSignal(this.getBlockPos());
    }

    /**
    * Remove a small amount of ignis from "from" and add a portion of the ignis to "to" depending on distance
    * */
    public void transferItems(ItemTransporterBlockEntity to, ItemTransporterBlockEntity from, float dist) {
        if(!(from.inventory.getStackInSlot(0).isEmpty()) && to.inventory.getStackInSlot(0).isEmpty() /* && from.getIgnis() >= 20*/) {
            int count = from.inventory.getStackInSlot(0).getCount();
/*            this.removeIgnis(20);*/
            to.inventory.setStackInSlot(0, from.inventory.getStackInSlot(0).copy());
            to.inventory.getStackInSlot(0).setCount(count);
            from.inventory.clear();
        }
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        inventory.save(nbt, "inventory");
        if(boundPos != null){
            saveBound(nbt);
        }
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        inventory.load(nbt, "inventory");
        loadBound(level, nbt);
        super.load(nbt);
    }

    private void saveBound(CompoundTag nbt) {
        CompoundTag boundTag = new CompoundTag();
        BlockPos boundPos = this.boundPos;
        if (boundPos != null) {
            NBTHelper.saveBlockPos(boundTag, boundPos);
            nbt.put("bound", boundTag);
        }
    }

    void loadBound(Level level, CompoundTag nbt) {
        CompoundTag boundTag = nbt.getCompound("bound");
        BlockPos pos = NBTHelper.loadBlockPos(boundTag);
        if (level != null && level.getBlockEntity(pos) instanceof ItemTransporterBlockEntity) {
            boundPos = pos;
            bound = (ItemTransporterBlockEntity) level.getBlockEntity(pos);
        }
    }

    @Override
    public void onBreak() {
        inventory.dropItems(level, worldPosition);
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
    public int getMaxIgnis() {
        return this.maxIgnis;
    }

    @Override
    public int getTransferRate() {
        return this.getMaxIgnis();
    }
}