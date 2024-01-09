package net.felix.turtle_magic.entity.custom;

import net.felix.turtle_magic.entity.TMEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

public class TestudoShellEntity extends Entity {
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    ArrayList<LivingEntity> allies;

    public TestudoShellEntity(EntityType<? extends TestudoShellEntity> type, Level level) {
        super(type, level);
    }

    public TestudoShellEntity(Level level, double d1, double d2, double d3, LivingEntity entity) {
        this(TMEntityTypes.TESTUDO.get(), level);
        this.setOwner(entity);
        this.setYRot(getOwner().getYRot());
        this.setPos(d1, d2, d3);
    }

    protected void defineSynchedData() {
    }

    public void tick() {
        for(LivingEntity livingentity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox())) {
                if(!(livingentity instanceof Player)) {
                    this.pushEntity(livingentity);
                }
        }
        if(this.getOwner() != null) {
            this.setPos(getOwner().position());
            this.setYRot(getOwner().getYRot());
        }
    }


    private void pushEntity(LivingEntity entity) {
            this.push(entity);
            this.push(entity);
            this.push(entity);
            this.push(entity);
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

    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.hasUUID("Owner")) {
            this.ownerUUID = tag.getUUID("Owner");
        }

    }

    protected void addAdditionalSaveData(CompoundTag tag) {
        if (this.ownerUUID != null) {
            tag.putUUID("Owner", this.ownerUUID);
        }
    }

    public boolean hurt(DamageSource source, float f1) {
        return false;
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
