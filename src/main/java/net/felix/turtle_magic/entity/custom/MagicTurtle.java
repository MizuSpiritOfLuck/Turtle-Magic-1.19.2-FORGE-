package net.felix.turtle_magic.entity.custom;

import net.felix.turtle_magic.block.TMBlocks;
import net.felix.turtle_magic.block.custom.MagicTurtleEggBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class MagicTurtle extends Turtle {
    private static final EntityDataAccessor<BlockPos> HOME_POS = SynchedEntityData.defineId(MagicTurtle.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(MagicTurtle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> LAYING_EGG = SynchedEntityData.defineId(MagicTurtle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<BlockPos> TRAVEL_POS = SynchedEntityData.defineId(MagicTurtle.class, EntityDataSerializers.BLOCK_POS);
    private static final EntityDataAccessor<Boolean> GOING_HOME = SynchedEntityData.defineId(MagicTurtle.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> TRAVELLING = SynchedEntityData.defineId(MagicTurtle.class, EntityDataSerializers.BOOLEAN);
    public static final Ingredient FOOD_ITEMS = Ingredient.of(Blocks.SEAGRASS.asItem());
    int layEggCounter;
    public static final Predicate<LivingEntity> BABY_ON_LAND_SELECTOR = (livingEntity) -> {
        return livingEntity.isBaby() && !livingEntity.isInWater();
    };

    public MagicTurtle(EntityType<? extends Turtle> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_IRON_CLOSED, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_WOOD_CLOSED, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DOOR_OPEN, -1.0F);
        this.moveControl = new MagicTurtle.TurtleMoveControl(this);
        this.maxUpStep = 1.0F;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public void setHomePos(BlockPos blockPos) {
        this.entityData.set(HOME_POS, this.blockPosition());
    }

    BlockPos getHomePos() {
        return this.entityData.get(HOME_POS);
    }

    void setTravelPos(BlockPos blockPos) {
        this.entityData.set(TRAVEL_POS, blockPos);
    }

    BlockPos getTravelPos() {
        return this.entityData.get(TRAVEL_POS);
    }

    public boolean hasEgg() {
        return this.entityData.get(HAS_EGG);
    }

    void setHasEgg(boolean hasEgg) {
        this.entityData.set(HAS_EGG, hasEgg);
    }

    public boolean isLayingEgg() {
        return this.entityData.get(LAYING_EGG);
    }

    void setLayingEgg(boolean layingEgg) {
        this.layEggCounter = layingEgg ? 1 : 0;
        this.entityData.set(LAYING_EGG, layingEgg);
    }

    boolean isGoingHome() {
        return this.entityData.get(GOING_HOME);
    }

    void setGoingHome(boolean goingHome) {
        this.entityData.set(GOING_HOME, goingHome);
    }

    boolean isTravelling() {
        return this.entityData.get(TRAVELLING);
    }

    void setTravelling(boolean travelling) {
        this.entityData.set(TRAVELLING, travelling);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOME_POS, BlockPos.ZERO);
        this.entityData.define(HAS_EGG, false);
        this.entityData.define(TRAVEL_POS, BlockPos.ZERO);
        this.entityData.define(GOING_HOME, false);
        this.entityData.define(TRAVELLING, false);
        this.entityData.define(LAYING_EGG, false);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("HomePosX", this.getHomePos().getX());
        tag.putInt("HomePosY", this.getHomePos().getY());
        tag.putInt("HomePosZ", this.getHomePos().getZ());
        tag.putBoolean("HasEgg", this.hasEgg());
        tag.putInt("TravelPosX", this.getTravelPos().getX());
        tag.putInt("TravelPosY", this.getTravelPos().getY());
        tag.putInt("TravelPosZ", this.getTravelPos().getZ());
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        int i = tag.getInt("HomePosX");
        int j = tag.getInt("HomePosY");
        int k = tag.getInt("HomePosZ");
        this.setHomePos(new BlockPos(i, j, k));
        super.readAdditionalSaveData(tag);
        this.setHasEgg(tag.getBoolean("HasEgg"));
        int l = tag.getInt("TravelPosX");
        int i1 = tag.getInt("TravelPosY");
        int j1 = tag.getInt("TravelPosZ");
        this.setTravelPos(new BlockPos(l, i1, j1));
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType spawnType, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
        this.setHomePos(this.blockPosition());
        this.setTravelPos(BlockPos.ZERO);
        return super.finalizeSpawn(levelAccessor, difficultyInstance, spawnType, groupData, tag);
    }

    public static boolean checkTurtleSpawnRules(EntityType<Turtle> entityType, LevelAccessor accessor, MobSpawnType spawnType, BlockPos pos, RandomSource source) {
        return pos.getY() < accessor.getSeaLevel() + 4 && isBrightEnoughToSpawn(accessor, pos);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MagicTurtle.TurtlePanicGoal(this, 1.2D));
        this.goalSelector.addGoal(1, new MagicTurtle.TurtleBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new MagicTurtle.TurtleLayEggGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.1D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new MagicTurtle.TurtleGoToWaterGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new MagicTurtle.TurtleGoHomeGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new MagicTurtle.TurtleTravelGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(9, new MagicTurtle.TurtleRandomStrollGoal(this, 1.0D, 100));
    }

    public static AttributeSupplier setAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .build();
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    public int getAmbientSoundInterval() {
        return 200;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return !this.isInWater() && this.onGround && !this.isBaby() ? SoundEvents.TURTLE_AMBIENT_LAND : super.getAmbientSound();
    }

    protected void playSwimSound(float f1) {
        super.playSwimSound(f1 * 1.5F);
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.TURTLE_SWIM;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return this.isBaby() ? SoundEvents.TURTLE_HURT_BABY : SoundEvents.TURTLE_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return this.isBaby() ? SoundEvents.TURTLE_DEATH_BABY : SoundEvents.TURTLE_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        SoundEvent soundevent = this.isBaby() ? SoundEvents.TURTLE_SHAMBLE_BABY : SoundEvents.TURTLE_SHAMBLE;
        this.playSound(soundevent, 0.15F, 1.0F);
    }

    public boolean canFallInLove() {
        return super.canFallInLove() && !this.hasEgg();
    }

    protected float nextStep() {
        return this.moveDist + 0.15F;
    }

    public float getScale() {
        return this.isBaby() ? 0.3F : 1.0F;
    }

    protected PathNavigation createNavigation(Level level) {
        return new MagicTurtle.TurtlePathNavigation(this, level);
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return EntityType.TURTLE.create(level);
    }

    public boolean isFood(ItemStack stack) {
        return stack.is(Blocks.SEAGRASS.asItem());
    }

    public float getWalkTargetValue(BlockPos pos, LevelReader reader) {
        if (!this.isGoingHome() && reader.getFluidState(pos).is(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return reader.getPathfindingCostFromLightLevels(pos);
        }
    }

    public void aiStep() {
        super.aiStep();
        if (this.isAlive() && this.isLayingEgg() && this.layEggCounter >= 1 && this.layEggCounter % 5 == 0) {
            BlockPos blockpos = this.blockPosition();
                this.level.levelEvent(2001, blockpos, Block.getId(this.level.getBlockState(blockpos.below())));
        }

    }

    protected void ageBoundaryReached() {
        super.ageBoundaryReached();
        if (!this.isBaby() && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
            this.spawnAtLocation(Items.SCUTE, 1);
        }

    }

    public void travel(Vec3 vec3) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(0.1F, vec3);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null && (!this.isGoingHome() || !this.getHomePos().closerToCenterThan(this.position(), 20.0D))) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(vec3);
        }

    }

    public boolean canBeLeashed(Player player) {
        return false;
    }

    public void thunderHit(ServerLevel level, LightningBolt lightningBolt) {
        this.hurt(DamageSource.LIGHTNING_BOLT, Float.MAX_VALUE);
    }

    static class TurtleBreedGoal extends BreedGoal {
        private final MagicTurtle turtle;

        TurtleBreedGoal(MagicTurtle magicTurtle, double d1) {
            super(magicTurtle, d1);
            this.turtle = magicTurtle;
        }

        public boolean canUse() {
            return super.canUse() && !this.turtle.hasEgg();
        }

        protected void breed() {
            ServerPlayer serverplayer = this.animal.getLoveCause();
            if (serverplayer == null && this.partner.getLoveCause() != null) {
                serverplayer = this.partner.getLoveCause();
            }

            if (serverplayer != null) {
                serverplayer.awardStat(Stats.ANIMALS_BRED);
                CriteriaTriggers.BRED_ANIMALS.trigger(serverplayer, this.animal, this.partner, (AgeableMob)null);
            }

            this.turtle.setHasEgg(true);
            this.animal.resetLove();
            this.partner.resetLove();
            RandomSource randomsource = this.animal.getRandom();
            if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
                this.level.addFreshEntity(new ExperienceOrb(this.level, this.animal.getX(), this.animal.getY(), this.animal.getZ(), randomsource.nextInt(7) + 1));
            }

        }
    }

    static class TurtleGoHomeGoal extends Goal {
        private final MagicTurtle turtle;
        private final double speedModifier;
        private boolean stuck;
        private int closeToHomeTryTicks;
        private static final int GIVE_UP_TICKS = 600;

        TurtleGoHomeGoal(MagicTurtle magicTurtle, double d1) {
            this.turtle = magicTurtle;
            this.speedModifier = d1;
        }

        public boolean canUse() {
            if (this.turtle.isBaby()) {
                return false;
            } else if (this.turtle.hasEgg()) {
                return true;
            } else if (this.turtle.getRandom().nextInt(reducedTickDelay(700)) != 0) {
                return false;
            } else {
                return !this.turtle.getHomePos().closerToCenterThan(this.turtle.position(), 64.0D);
            }
        }

        public void start() {
            this.turtle.setGoingHome(true);
            this.stuck = false;
            this.closeToHomeTryTicks = 0;
        }

        public void stop() {
            this.turtle.setGoingHome(false);
        }

        public boolean canContinueToUse() {
            return !this.turtle.getHomePos().closerToCenterThan(this.turtle.position(), 7.0D) && !this.stuck && this.closeToHomeTryTicks <= this.adjustedTickDelay(600);
        }

        public void tick() {
            BlockPos blockpos = this.turtle.getHomePos();
            boolean flag = blockpos.closerToCenterThan(this.turtle.position(), 16.0D);
            if (flag) {
                ++this.closeToHomeTryTicks;
            }

            if (this.turtle.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atBottomCenterOf(blockpos);
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.turtle, 16, 3, vec3, (double)((float)Math.PI / 10F));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.turtle, 8, 7, vec3, (double)((float)Math.PI / 2F));
                }

                if (vec31 != null && !flag && !this.turtle.level.getBlockState(new BlockPos(vec31)).is(Blocks.WATER)) {
                    vec31 = DefaultRandomPos.getPosTowards(this.turtle, 16, 5, vec3, (double)((float)Math.PI / 2F));
                }

                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }

                this.turtle.getNavigation().moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
            }

        }
    }

    static class TurtleGoToWaterGoal extends MoveToBlockGoal {
        private static final int GIVE_UP_TICKS = 1200;
        private final MagicTurtle turtle;

        TurtleGoToWaterGoal(MagicTurtle p_30262_, double p_30263_) {
            super(p_30262_, p_30262_.isBaby() ? 2.0D : p_30263_, 24);
            this.turtle = p_30262_;
            this.verticalSearchStart = -1;
        }

        public boolean canContinueToUse() {
            return !this.turtle.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.turtle.level, this.blockPos);
        }

        public boolean canUse() {
            if (this.turtle.isBaby() && !this.turtle.isInWater()) {
                return super.canUse();
            } else {
                return !this.turtle.isGoingHome() && !this.turtle.isInWater() && !this.turtle.hasEgg() ? super.canUse() : false;
            }
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 160 == 0;
        }

        protected boolean isValidTarget(LevelReader reader, BlockPos pos) {
            return reader.getBlockState(pos).is(Blocks.WATER);
        }
    }

    static class TurtleLayEggGoal extends MoveToBlockGoal {
        private final MagicTurtle turtle;

        TurtleLayEggGoal(MagicTurtle magicTurtle, double d1) {
            super(magicTurtle, d1, 16);
            this.turtle = magicTurtle;
        }

        public boolean canUse() {
            return this.turtle.hasEgg() && this.turtle.getHomePos().closerToCenterThan(this.turtle.position(), 9.0D) ? super.canUse() : false;
        }

        public boolean canContinueToUse() {
            return super.canContinueToUse() && this.turtle.hasEgg() && this.turtle.getHomePos().closerToCenterThan(this.turtle.position(), 9.0D);
        }

        public void tick() {
            super.tick();
            BlockPos blockpos = this.turtle.blockPosition();
                if (this.turtle.layEggCounter < 1) {
                    this.turtle.setLayingEgg(true);
                } else if (this.turtle.layEggCounter > this.adjustedTickDelay(200)) {
                    Level level = this.turtle.level;
                    level.playSound((Player)null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
                    level.setBlock(this.blockPos, TMBlocks.MAGIC_TURTLE_EGG.get().defaultBlockState().setValue(MagicTurtleEggBlock.EGGS, Integer.valueOf(this.turtle.random.nextInt(4) + 1)), 3);
                    this.turtle.setHasEgg(false);
                    this.turtle.setLayingEgg(false);
                    this.turtle.setInLoveTime(600);
                }

                if (this.turtle.isLayingEgg()) {
                    ++this.turtle.layEggCounter;
                }

        }

        protected boolean isValidTarget(LevelReader reader, BlockPos pos) {
            return !reader.isEmptyBlock(pos.above());
        }
    }

    static class TurtleMoveControl extends MoveControl {
        private final MagicTurtle turtle;

        TurtleMoveControl(MagicTurtle magicTurtle) {
            super(magicTurtle);
            this.turtle = magicTurtle;
        }

        private void updateSpeed() {
            if (this.turtle.isInWater()) {
                this.turtle.setDeltaMovement(this.turtle.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
                if (!this.turtle.getHomePos().closerToCenterThan(this.turtle.position(), 16.0D)) {
                    this.turtle.setSpeed(Math.max(this.turtle.getSpeed() / 2.0F, 0.08F));
                }

                if (this.turtle.isBaby()) {
                    this.turtle.setSpeed(Math.max(this.turtle.getSpeed() / 3.0F, 0.06F));
                }
            } else if (this.turtle.onGround) {
                this.turtle.setSpeed(Math.max(this.turtle.getSpeed() / 2.0F, 0.06F));
            }

        }

        public void tick() {
            this.updateSpeed();
            if (this.operation == MoveControl.Operation.MOVE_TO && !this.turtle.getNavigation().isDone()) {
                double d0 = this.wantedX - this.turtle.getX();
                double d1 = this.wantedY - this.turtle.getY();
                double d2 = this.wantedZ - this.turtle.getZ();
                double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 /= d3;
                float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                this.turtle.setYRot(this.rotlerp(this.turtle.getYRot(), f, 90.0F));
                this.turtle.yBodyRot = this.turtle.getYRot();
                float f1 = (float)(this.speedModifier * this.turtle.getAttributeValue(Attributes.MOVEMENT_SPEED));
                this.turtle.setSpeed(Mth.lerp(0.125F, this.turtle.getSpeed(), f1));
                this.turtle.setDeltaMovement(this.turtle.getDeltaMovement().add(0.0D, (double)this.turtle.getSpeed() * d1 * 0.1D, 0.0D));
            } else {
                this.turtle.setSpeed(0.0F);
            }
        }
    }

    static class TurtlePanicGoal extends PanicGoal {
        TurtlePanicGoal(MagicTurtle magicTurtle, double d1) {
            super(magicTurtle, d1);
        }

        public boolean canUse() {
            if (!this.shouldPanic()) {
                return false;
            } else {
                BlockPos blockpos = this.lookForWater(this.mob.level, this.mob, 7);
                if (blockpos != null) {
                    this.posX = (double)blockpos.getX();
                    this.posY = (double)blockpos.getY();
                    this.posZ = (double)blockpos.getZ();
                    return true;
                } else {
                    return this.findRandomPosition();
                }
            }
        }
    }

    static class TurtlePathNavigation extends AmphibiousPathNavigation {
        TurtlePathNavigation(MagicTurtle magicTurtle, Level level) {
            super(magicTurtle, level);
        }

        public boolean isStableDestination(BlockPos pos) {
            Mob mob = this.mob;
            if (mob instanceof MagicTurtle turtle) {
                if (turtle.isTravelling()) {
                    return this.level.getBlockState(pos).is(Blocks.WATER);
                }
            }

            return !this.level.getBlockState(pos.below()).isAir();
        }
    }

    static class TurtleRandomStrollGoal extends RandomStrollGoal {
        private final MagicTurtle turtle;

        TurtleRandomStrollGoal(MagicTurtle magicTurtle, double d1, int i1) {
            super(magicTurtle, d1, i1);
            this.turtle = magicTurtle;
        }

        public boolean canUse() {
            return !this.mob.isInWater() && !this.turtle.isGoingHome() && !this.turtle.hasEgg() ? super.canUse() : false;
        }
    }

    static class TurtleTravelGoal extends Goal {
        private final MagicTurtle turtle;
        private final double speedModifier;
        private boolean stuck;

        TurtleTravelGoal(MagicTurtle magicTurtle, double d1) {
            this.turtle = magicTurtle;
            this.speedModifier = d1;
        }

        public boolean canUse() {
            return !this.turtle.isGoingHome() && !this.turtle.hasEgg() && this.turtle.isInWater();
        }

        public void start() {
            int i = 512;
            int j = 4;
            RandomSource randomsource = this.turtle.random;
            int k = randomsource.nextInt(1025) - 512;
            int l = randomsource.nextInt(9) - 4;
            int i1 = randomsource.nextInt(1025) - 512;
            if ((double)l + this.turtle.getY() > (double)(this.turtle.level.getSeaLevel() - 1)) {
                l = 0;
            }

            BlockPos blockpos = new BlockPos((double)k + this.turtle.getX(), (double)l + this.turtle.getY(), (double)i1 + this.turtle.getZ());
            this.turtle.setTravelPos(blockpos);
            this.turtle.setTravelling(true);
            this.stuck = false;
        }

        public void tick() {
            if (this.turtle.getNavigation().isDone()) {
                Vec3 vec3 = Vec3.atBottomCenterOf(this.turtle.getTravelPos());
                Vec3 vec31 = DefaultRandomPos.getPosTowards(this.turtle, 16, 3, vec3, (double)((float)Math.PI / 10F));
                if (vec31 == null) {
                    vec31 = DefaultRandomPos.getPosTowards(this.turtle, 8, 7, vec3, (double)((float)Math.PI / 2F));
                }

                if (vec31 != null) {
                    int i = Mth.floor(vec31.x);
                    int j = Mth.floor(vec31.z);
                    int k = 34;
                    if (!this.turtle.level.hasChunksAt(i - 34, j - 34, i + 34, j + 34)) {
                        vec31 = null;
                    }
                }

                if (vec31 == null) {
                    this.stuck = true;
                    return;
                }

                this.turtle.getNavigation().moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
            }

        }

        public boolean canContinueToUse() {
            return !this.turtle.getNavigation().isDone() && !this.stuck && !this.turtle.isGoingHome() && !this.turtle.isInLove() && !this.turtle.hasEgg();
        }

        public void stop() {
            this.turtle.setTravelling(false);
            super.stop();
        }
    }
}