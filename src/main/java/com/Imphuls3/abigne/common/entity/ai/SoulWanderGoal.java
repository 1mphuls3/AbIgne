package com.Imphuls3.abigne.common.entity.ai;

import com.Imphuls3.abigne.common.entity.SoulEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class SoulWanderGoal extends Goal {
    SoulEntity soul;
    double speed;
    
    public SoulWanderGoal(SoulEntity soul, double speed) {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.soul = soul;
        this.speed = speed;
    }

    public boolean canUse() {
        return soul.getNavigation().isDone() && soul.getRandom().nextInt(3) == 0;
    }

    public boolean canContinueToUse() {
        return soul.getNavigation().isInProgress();
    }

    public void start() {
        Vec3 vec3 = this.getRandomLocation();
        if (vec3 != null) {
            soul.getNavigation().moveTo(soul.getNavigation().createPath(new BlockPos(vec3), 1), speed);
        }
    }

    @Nullable
    private Vec3 getRandomLocation() {
        Vec3 vec3 = soul.getViewVector(0.0F);
        Vec3 vec32 = HoverRandomPos.getPos(soul, 8, 7, vec3.x, vec3.z, ((float)Math.PI / 2F), 3, 1);
        return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(soul, 16, 6, -2, vec3.x, vec3.z, ((float)Math.PI / 2F));
    }
}
