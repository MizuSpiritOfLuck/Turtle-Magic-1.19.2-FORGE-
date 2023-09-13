package net.felix.turtle_magic.entity.custom;

import net.felix.turtle_magic.entity.TMEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
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

public class TwirlingShellEntity extends Entity {
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

    @Override
    protected void defineSynchedData() {

    }

    public void tick() {

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
        if (tag.contains("LifeTicks")) {
            this.setLimitedLife(tag.getInt("LifeTicks"));
        }
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        if (this.hasLimitedLife) {
            tag.putInt("LifeTicks", this.limitedLifeTicks);
        }
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
