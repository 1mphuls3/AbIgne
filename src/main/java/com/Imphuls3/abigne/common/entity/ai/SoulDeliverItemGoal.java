package com.Imphuls3.abigne.common.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.level.LevelReader;

public class SoulDeliverItemGoal extends MoveTowardsTargetGoal {

    public SoulDeliverItemGoal(PathfinderMob mob, double speedMod, int range) {
        super(mob, speedMod, range);
    }


}
