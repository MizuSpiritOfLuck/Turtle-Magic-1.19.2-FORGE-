package net.felix.turtle_magic.entity.custom;

import net.felix.turtle_magic.entity.TMEntityTypes;
import net.felix.turtle_magic.item.TMItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.UUID;

public class CoverShellEntity extends Entity {
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    private boolean hasLimitedLife;
    private int limitedLifeTicks;

    public CoverShellEntity(EntityType<? extends CoverShellEntity> type, Level level) {
        super(type, level);
    }
    public final AnimationState spinAnimationState = new AnimationState();


    public CoverShellEntity(Level level, double d1, double d2, double d3, LivingEntity entity) {
        this(TMEntityTypes.COVER_SHELL.get(), level);
        this.setOwner(entity);
        this.setYRot(getOwner().getYRot());
        this.setPos(d1, d2, d3);
    }

    @Override
    protected void defineSynchedData() {

    }

    public void tick() {
        if(level.isClientSide()) {
            this.spinAnimationState.startIfStopped(this.tickCount);
        }

        for(LivingEntity livingentity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox())) {
            if(!(livingentity instanceof Player)) {
                livingentity.hurt(DamageSource.GENERIC, 3);
            }
        }

        for(LivingEntity livingentity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox())) {
            if(!(livingentity == this.owner)) {
                livingentity.hurt(DamageSource.GENERIC, 3);
            }
        }
        if(this.getOwner() != null) {
            this.setPos(getOwner().position());
            this.setYRot(getOwner().getYRot());
        }

        if (this.hasLimitedLife && --this.limitedLifeTicks <= 0) {
            this.limitedLifeTicks = 10;
            this.hurt(DamageSource.STARVE, 1.0F);
            this.discard();
        }
    }

    public void setOwner(@Nullable LivingEntity entity) {
        this.owner = entity;
        this.ownerUUID = entity == null ? null : entity.getUUID();
    }

    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }

        return this.owner;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public void setLimitedLife(int i) {
        this.hasLimitedLife = true;
        this.limitedLifeTicks = i;
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        if (tag.hasUUID("Owner")) {
            this.ownerUUID = tag.getUUID("Owner");
        }
        if (tag.contains("LifeTicks")) {
            this.setLimitedLife(tag.getInt("LifeTicks"));
        }
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        if (this.ownerUUID != null) {
            tag.putUUID("Owner", this.ownerUUID);
        }
        if (this.hasLimitedLife) {
            tag.putInt("LifeTicks", this.limitedLifeTicks);
        }
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public boolean hurt(DamageSource source, float f1) {
        return source == DamageSource.STARVE;
    }

}
