package com.github.Imphuls3.abigne.common.blockentity;

import com.github.Imphuls3.abigne.common.block.PipeBlock;
import com.github.Imphuls3.abigne.core.blockentity.ITankProvider;
import com.github.Imphuls3.abigne.core.blockentity.FluidTankBlockEntity;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import com.github.Imphuls3.abigne.core.registry.ItemRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class PipeBlockEntity extends FluidTankBlockEntity implements ITankProvider {
    public BlockItem facade;

    public PipeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public PipeBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FLUID_PIPE.get(), pos, state);
    }

    @Override
    public void onPlayerBreak(Player player) {
        if(!player.isCreative()) {
            level.addFreshEntity(new ItemEntity(level, worldPosition.getX() + 0.5F, worldPosition.getY() + 0.8F, worldPosition.getZ() + 0.5F,
                    new ItemStack(facade.asItem(), 1)));
        }
        super.onBreak();
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if(player.isCrouching() && player.getItemInHand(hand).isEmpty()) {
            if (facade != null) {
                if (!player.isCreative()) {
                    level.addFreshEntity(new ItemEntity(level, worldPosition.getX() + 0.5F, worldPosition.getY() + 0.8F, worldPosition.getZ() + 0.5F,
                            new ItemStack(facade.asItem(), 1)));
                }
                this.facade = null;
            }
        }
        if(!player.isCrouching() && player.getItemInHand(hand).getItem() != ItemRegistry.FLUID_PIPE.get()) {
            if (player.getItemInHand(hand).getItem() instanceof BlockItem item && !(item.getBlock() instanceof LiquidBlock) && !item.getBlock().hasDynamicShape() && facade == null) {
                this.facade = item;
                if(!player.isCreative()) {
                    player.setItemInHand(hand, new ItemStack(player.getItemInHand(hand).getItem(), player.getItemInHand(hand).getCount()-1));
                }
            }
            super.onUse(player, hand);
        }
        if(player.getItemInHand(hand).isEmpty()) { //TODO REMOVE AFTER DEV
            this.tank.setFluid(FluidStack.EMPTY);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isOutput(Direction direction) {
        return getBlockState().getValue(PipeBlock.OUT) == direction;
    }

    @Override
    public boolean isInput(Direction direction) {
        return getBlockState().getValue(PipeBlock.IN) == direction;
    }

    @Override
    public boolean isPressurized() {
        return false;
    }

    @Override
    public int getTankCapacity() {
        return 1000;
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        if(facade != null) {
            nbt.putString("facade", ForgeRegistries.ITEMS.getKey(facade).toString());
        }
        super.saveAdditional(nbt);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        if(nbt.contains("facade")) {
            ResourceLocation name = new ResourceLocation(nbt.getString("facade"));
            facade = (BlockItem)ForgeRegistries.ITEMS.getValue(name);
        }
        super.load(nbt);
    }
}
