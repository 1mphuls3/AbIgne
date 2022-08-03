package com.Imphuls3.abigne.common.entity.ai;

import com.Imphuls3.abigne.common.block.InfuserBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;

public class SoulRechargeGoal extends MoveToBlockGoal {
    PathfinderMob mob;
    public boolean reachedTarget;

    public SoulRechargeGoal(PathfinderMob mob, double speed, int range) {
        super(mob, speed, range);
        this.mob = mob;
    }

    @Override
    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        if(level.getBlockState(pos).getBlock() instanceof InfuserBlock) {
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        BlockPos blockpos = this.getMoveToTarget();
        if (!blockpos.closerToCenterThan(this.mob.position(), this.acceptedDistance())) {
            this.reachedTarget = false;
            ++this.tryTicks;
            if (this.shouldRecalculatePath()) {
                this.mob.getNavigation().moveTo(blockpos.getX() + 0.5D, blockpos.getY() + 1D, blockpos.getZ() + 0.5D, this.speedModifier);
            }
        } else {
            this.reachedTarget = true;
            rechargeSoul();
            --this.tryTicks;
        }
    }

    private void rechargeSoul() {
        /*ServerLevel level = (ServerLevel) this.mob.level;
        BlockPos pos = this.blockPos;
        if(isValidTarget(level, pos)) {
            InfuserBlockEntity charger = (InfuserBlockEntity) level.getBlockEntity(blockPos);
            SoulEntity soul = (SoulEntity) mob;
            if(soul.getIgnis() == 0) {
                soul.transferIgnis(charger, soul);
            }
        }*/
    }
}
