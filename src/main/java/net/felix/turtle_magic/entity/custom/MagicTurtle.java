package net.felix.turtle_magic.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TurtleEggBlock;

public class MagicTurtle extends Turtle {

    private static final EntityDataAccessor<Boolean> LAYING_EGG = SynchedEntityData.defineId(Turtle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(MagicTurtle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<BlockPos> HOME_POS = SynchedEntityData.defineId(MagicTurtle.class, EntityDataSerializers.BLOCK_POS);
    int layEggCounter;

    public MagicTurtle(EntityType<? extends Turtle> type, Level level) {
        super(type, level);
    }

    BlockPos getHomePos() {
        return this.entityData.get(HOME_POS);
    }

    void setLayingEgg(boolean b) {
        this.layEggCounter = b ? 1 : 0;
        this.entityData.set(LAYING_EGG, b);
    }

    void setHasEgg(boolean b) {
        this.entityData.set(HAS_EGG, b);
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MagicTurtle.TurtleLayEggGoal(this, 1.0D));
    }

    static class TurtleLayEggGoal extends MoveToBlockGoal {
        private final MagicTurtle magicTurtle;

        TurtleLayEggGoal(MagicTurtle magicTurtle, double d1) {
            super(magicTurtle, d1, 16);
            this.magicTurtle = magicTurtle;
        }

        public boolean canUse() {
            return this.magicTurtle.hasEgg() && this.magicTurtle.getHomePos().closerToCenterThan(this.magicTurtle.position(), 9.0D) ? super.canUse() : false;
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse() && this.magicTurtle.hasEgg() && this.magicTurtle.getHomePos().closerToCenterThan(this.magicTurtle.position(), 9.0D);
        }

        public void tick() {
            super.tick();
            BlockPos blockpos = this.magicTurtle.blockPosition();
            if (!this.magicTurtle.isInWater() && this.isReachedTarget()) {
                if (this.magicTurtle.layEggCounter < 1) {
                    this.magicTurtle.setLayingEgg(true);
                } else if (this.magicTurtle.layEggCounter > this.adjustedTickDelay(200)) {
                    Level level = this.magicTurtle.level;
                    level.playSound((Player) null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
                    level.setBlock(this.blockPos.above(), Blocks.TURTLE_EGG.defaultBlockState().setValue(TurtleEggBlock.EGGS, Integer.valueOf(this.magicTurtle.random.nextInt(4) + 1)), 3);
                    this.magicTurtle.setHasEgg(false);
                    this.magicTurtle.setLayingEgg(false);
                    this.magicTurtle.setInLoveTime(600);
                }

                if (this.magicTurtle.isLayingEgg()) {
                    ++this.magicTurtle.layEggCounter;
                }
            }

        }

        @Override
        protected boolean isValidTarget(LevelReader levelReader, BlockPos blockPos) {
            return !levelReader.isEmptyBlock(blockPos.above()) ? false : TurtleEggBlock.isSand(levelReader, blockPos);
        }
    }
}
