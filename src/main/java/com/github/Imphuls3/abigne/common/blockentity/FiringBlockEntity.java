package com.github.Imphuls3.abigne.common.blockentity;

import com.github.Imphuls3.abigne.common.block.FiringBlock;
import com.github.Imphuls3.abigne.common.entity.ManaBurst;
import com.github.Imphuls3.abigne.core.blockentity.AbIgneBlockEntity;
import com.github.Imphuls3.abigne.core.blockentity.ExtendedItemStackHandler;
import com.github.Imphuls3.abigne.core.helper.BlockHelper;
import com.github.Imphuls3.abigne.core.helper.VecHelper;
import com.github.Imphuls3.abigne.core.registry.BlockEntityRegistry;
import com.github.Imphuls3.abigne.core.registry.EntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

import static com.github.Imphuls3.abigne.core.helper.VecHelper.CENTER;

public class FiringBlockEntity extends AbIgneBlockEntity {
    public ExtendedItemStackHandler inventory = new ExtendedItemStackHandler(1, 64) {
        @Override
        public void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            BlockHelper.updateStateAndNeighbor(level, worldPosition);
        }
    };

    public FiringBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public FiringBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FIRING_BLOCK.get(), pos, state);
    }

    @Override
    public InteractionResult onUse(Player player, InteractionHand hand) {
        if(!player.isCrouching()){
            inventory.interact(player.level, player, hand);
        }
        return InteractionResult.SUCCESS;
    }

    int i = 1;
    public void tick(Level level, BlockState state, BlockPos pos) {
        if(i == 20) {
            Direction dir = state.getValue(FiringBlock.FACING);
            for (int j = 0; j < 15; j++) {
                pos = pos.relative(dir);
                if(level.getBlockEntity(pos) != null) {
                    shootBeam(dir);
                }
            }
            i = 1;
        } else {
            i++;
        }
    }

    private void shootBeam(Direction dir) {
        Vec3 pos = VecHelper.vec3FromBlockPos(getBlockPos().relative(dir)).add(CENTER).subtract(dir.getStepX()*0.2D, dir.getStepY()*0.2D, dir.getStepZ()*0.2D);
        Vec3i vel = dir.getNormal();
        Vec3 velocity = new Vec3(vel.getX()/5F, 0F, vel.getZ()/5F);
        ManaBurst manaBurst = new ManaBurst(EntityRegistry.MANA.get(), level);
        level.addFreshEntity(manaBurst.shoot(pos.x, pos.y, pos.z, velocity.x, velocity.y, velocity.z, null));
    }

    @Override
    public void onBreak() {
        inventory.dropItems(level, worldPosition);
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        inventory.save(nbt, "inventory");
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        inventory.load(nbt, "inventory");
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