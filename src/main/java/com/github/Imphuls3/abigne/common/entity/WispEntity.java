package com.github.Imphuls3.abigne.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class WispEntity extends Mob {
    public WispEntity(EntityType<? extends Mob> type, Level level) {
        super(type, level);
    }
    Vec3 moveTargetPoint = Vec3.ZERO;
    BlockPos anchorPoint = BlockPos.ZERO;

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5)
                .add(Attributes.ATTACK_DAMAGE, 3F)
                .add(Attributes.ATTACK_SPEED, 2F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.FLYING_SPEED, 0.6F)
                .add(Attributes.FOLLOW_RANGE, Integer.MAX_VALUE/16D)
                .build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new CircleAnchorGoal());
        super.registerGoals();
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.anchorPoint = this.blockPosition().above(5);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("AX")) {
            this.anchorPoint = new BlockPos(pCompound.getInt("AX"), pCompound.getInt("AY"), pCompound.getInt("AZ"));
        }
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("AX", this.anchorPoint.getX());
        pCompound.putInt("AY", this.anchorPoint.getY());
        pCompound.putInt("AZ", this.anchorPoint.getZ());
    }

    @Override
    public MobType getMobType() {
        return MobTypes.PSIONIC;
    }


    class CircleAnchorGoal extends MoveTargetGoal {
        private float angle;
        private float distance;
        private float height;
        private float clockwise;

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return WispEntity.this.getTarget() == null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.distance = 5.0F + WispEntity.this.random.nextFloat() * 10.0F;
            this.height = -4.0F + WispEntity.this.random.nextFloat() * 9.0F;
            this.clockwise = WispEntity.this.random.nextBoolean() ? 1.0F : -1.0F;
            this.selectNext();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (WispEntity.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {
                this.height = -4.0F + WispEntity.this.random.nextFloat() * 9.0F;
            }

            if (WispEntity.this.random.nextInt(this.adjustedTickDelay(250)) == 0) {
                ++this.distance;
                if (this.distance > 15.0F) {
                    this.distance = 5.0F;
                    this.clockwise = -this.clockwise;
                }
            }

            if (WispEntity.this.random.nextInt(this.adjustedTickDelay(450)) == 0) {
                this.angle = WispEntity.this.random.nextFloat() * 2.0F * (float)Math.PI;
                this.selectNext();
            }

            if (this.touchingTarget()) {
                this.selectNext();
            }

            if (WispEntity.this.moveTargetPoint.y < WispEntity.this.getY() && !WispEntity.this.level.isEmptyBlock(WispEntity.this.blockPosition().below(1))) {
                this.height = Math.max(1.0F, this.height);
                this.selectNext();
            }

            if (WispEntity.this.moveTargetPoint.y > WispEntity.this.getY() && !WispEntity.this.level.isEmptyBlock(WispEntity.this.blockPosition().above(1))) {
                this.height = Math.min(-1.0F, this.height);
                this.selectNext();
            }

        }

        private void selectNext() {
            if (BlockPos.ZERO.equals(WispEntity.this.anchorPoint)) {
                WispEntity.this.anchorPoint = WispEntity.this.blockPosition();
            }

            this.angle += this.clockwise * 15.0F * ((float)Math.PI / 180F);
            WispEntity.this.moveTargetPoint = Vec3.atLowerCornerOf(WispEntity.this.anchorPoint).add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * Mth.sin(this.angle)));
        }
    }

    abstract class MoveTargetGoal extends Goal {
        public MoveTargetGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        protected boolean touchingTarget() {
            return WispEntity.this.moveTargetPoint.distanceToSqr(WispEntity.this.getX(), WispEntity.this.getY(), WispEntity.this.getZ()) < 4.0D;
        }
    }
}
