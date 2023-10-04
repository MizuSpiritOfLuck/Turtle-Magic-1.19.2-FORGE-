package net.felix.turtle_magic.entity.custom;

import net.felix.turtle_magic.entity.TMEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class TwirlingShellEntity extends Animal {
    LivingEntity summoner;
    private boolean hasLimitedLife;
    private int limitedLifeTicks;

    public TwirlingShellEntity(EntityType<? extends TwirlingShellEntity> type, Level level) {
        super(type, level);
    }

    public TwirlingShellEntity(Level level, double d1, double d2, double d3, LivingEntity entity) {
        this(TMEntityTypes.TWIRLING_SHELL.get(), level);
        this.setSummoner(entity);
        this.setYRot(getSummoner().getYRot());
        this.setPos(d1, d2, d3);
    }

    public LivingEntity getSummoner() {
        return this.summoner;
    }

    public void setSummoner(LivingEntity summoner) {
        this.summoner = summoner;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return null;
    }

    @Override
    protected void defineSynchedData() {

    }

    public void tick() {

        /*if (this.hasLimitedLife && --this.limitedLifeTicks <= 0) {
            this.limitedLifeTicks = 20;
            this.hurt(DamageSource.STARVE, 1.0F);
        }*/
    }

    @Override
    public HumanoidArm getMainArm() {
        return null;
    }

    public void setLimitedLife(int i) {
        this.hasLimitedLife = true;
        this.limitedLifeTicks = i;
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("LifeTicks")) {
            this.setLimitedLife(tag.getInt("LifeTicks"));
        }
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot p_21127_) {
        return null;
    }

    @Override
    public void setItemSlot(EquipmentSlot p_21036_, ItemStack p_21037_) {

    }

    public void addAdditionalSaveData(CompoundTag tag) {
        if (this.hasLimitedLife) {
            tag.putInt("LifeTicks", this.limitedLifeTicks);
        }
    }

/*    public class TwirlingShellMoveToBlockGoal extends MoveToBlockGoal {

        public TwirlingShellMoveToBlockGoal(double d1, int i1, int i2) {
            super(TwirlingShellEntity.this, d1, i1, i2);
        }

        protected boolean isValidTarget(LevelReader p_28680_, BlockPos p_28681_) {
            BlockState blockstate = p_28680_.getBlockState(p_28681_);
            return blockstate.is(getSummoner());
        }

        public void tick() {
            if (this.isReachedTarget()) {
                this.onReachedTarget();
            }
            super.tick();
        }

        protected void onReachedTarget() {
            this.mob.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400));
        }

        public boolean canUse() {
            return super.canUse();
        }
    }*/

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
