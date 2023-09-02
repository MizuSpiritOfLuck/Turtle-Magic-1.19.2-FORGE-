package net.felix.turtle_magic.entity.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;

public class TravelToBlockGoal extends Goal {
    protected BlockPos target;

    @Override
    public boolean canUse() {
        return false;
    }



}
