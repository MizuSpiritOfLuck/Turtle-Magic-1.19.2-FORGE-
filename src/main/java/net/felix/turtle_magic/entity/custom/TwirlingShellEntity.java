package net.felix.turtle_magic.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class TwirlingShellEntity extends Monster implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    LivingEntity summoner;
    private boolean hasLimitedLife;
    private int limitedLifeTicks;

    public TwirlingShellEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1.0f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1000000.0F)
                .build();
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

    public void tick() {
        if(this.firstTick && this.getSummoner() != null) {
            this.setRot(this.getSummoner().getXRot(), this.getSummoner().getYRot());
        }


        /*if (this.hasLimitedLife && --this.limitedLifeTicks <= 0) {
            this.limitedLifeTicks = 20;
            this.hurt(DamageSource.STARVE, 1.0F);
        }*/
    }

    public void setLimitedLife(int i) {
        this.hasLimitedLife = true;
        this.limitedLifeTicks = i;
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (tag.contains("LifeTicks")) {
            this.setLimitedLife(tag.getInt("LifeTicks"));
        }
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        if (this.hasLimitedLife) {
            tag.putInt("LifeTicks", this.limitedLifeTicks);
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
/*        if(this.isDeadOrDying()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.twirling_shell.death", false));
        }*/
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.twirling_shell.spin", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
