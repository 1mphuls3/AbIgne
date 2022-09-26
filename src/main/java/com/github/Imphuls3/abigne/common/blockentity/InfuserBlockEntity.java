package com.github.Imphuls3.abigne.common.blockentity;

import com.github.Imphuls3.abigne.common.recipe.InfusionRecipe;
import com.github.Imphuls3.abigne.core.blockentity.ModInventory;
import com.github.Imphuls3.abigne.core.helper.BlockHelper;
import com.github.Imphuls3.abigne.core.ignis.AbstractIgnisMachine;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import java.util.Optional;

public class InfuserBlockEntity extends AbstractIgnisMachine {
    int maxIgnis = 10000;

    public ModInventory inventory = new ModInventory(2, 64) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

    public int progress = 0;
    public final int maxProgress = 72;

    public InfuserBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public InfuserBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.INFUSER.get(), pos, state);
    }


    @Override
    public void tick(Level level, BlockState state, BlockPos pos) {
        if(hasRecipe(this)) {
            this.progress++;
            setChanged(level, pos, state);
            if(this.progress > this.maxProgress) {
                craftItem(this);
            }
        } else {
            this.resetProgress();
            setChanged(level, pos, state);
        }
    }

    private static boolean hasRecipe(InfuserBlockEntity blockEntity) {
        Level level = blockEntity.getLevel();
        SimpleContainer inventory = new SimpleContainer(blockEntity.inventory.getSlots());
        for (int i = 0; i < blockEntity.inventory.getSlots(); i++) {
            inventory.setItem(i, blockEntity.inventory.getStackInSlot(i));
        }

        Optional<InfusionRecipe> match = level.getRecipeManager()
                .getRecipeFor(InfusionRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory) & canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private static void craftItem(InfuserBlockEntity blockEntity) {
        Level level = blockEntity.getLevel();
        SimpleContainer inventory = new SimpleContainer(blockEntity.inventory.getSlots());
        for (int i = 0; i < blockEntity.inventory.getSlots(); i++) {
            inventory.setItem(i, blockEntity.inventory.getStackInSlot(i));
        }

        Optional<InfusionRecipe> match = level.getRecipeManager()
                .getRecipeFor(InfusionRecipe.Type.INSTANCE, inventory, level);

        if (match.isPresent()) {
            blockEntity.inventory.extractItem(0, 1, false);

            blockEntity.inventory.setStackInSlot(1, new ItemStack(match.get().getResultItem().getItem(),
                    blockEntity.inventory.getStackInSlot(1).getCount() + 1));

            blockEntity.resetProgress();
        }
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inv, ItemStack output) {
        return inv.getItem(1).getItem() == output.getItem() || inv.getItem(1).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inv) {
        return inv.getItem(1).getMaxStackSize() > inv.getItem(1).getCount();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if(!player.isCrouching()){
            inventory.invInteract(player.level, player, hand);
        } else {
            resetProgress();
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void onBreak() {
        inventory.dropItems(level, worldPosition);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.putInt("infuser.progress", progress);
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
        progress = nbt.getInt("infuser.progress");
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
