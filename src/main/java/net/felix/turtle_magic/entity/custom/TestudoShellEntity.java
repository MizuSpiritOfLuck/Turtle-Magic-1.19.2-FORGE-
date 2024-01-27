package net.felix.turtle_magic.entity.custom;

import net.felix.turtle_magic.entity.TMEntityTypes;
import net.felix.turtle_magic.sound.TMSounds;
import net.felix.turtle_magic.util.TMMethods;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

public class TestudoShellEntity extends LivingEntity {
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;

    public TestudoShellEntity(EntityType<? extends LivingEntity> type, Level level) {
        super(type, level);
    }

    public TestudoShellEntity(Level level, double d1, double d2, double d3, LivingEntity entity) {
        this(TMEntityTypes.TESTUDO.get(), level);
        this.setOwner(entity);
        this.setPos(d1, d2, d3);
    }


    public static AttributeSupplier setAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1000.0F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1000.0D)
                .build();
    }

    @Override
    public boolean hurt(DamageSource source, float f1) {
        owner.sendSystemMessage(Component.literal("Health = " + this.getHealth()));
        return super.hurt(source, f1);
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

        if(this.dead) {
            TMMethods.playSound(level, this.blockPosition(), TMSounds.TESTUDO_SHELL_BREAK.get(), SoundSource.NEUTRAL);
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
            Entity entity = ((ServerLevel) this.level).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity) entity;
            }
        }
        return this.owner;
    }


    public void addAdditionalSaveData(CompoundTag tag) {
        if (this.ownerUUID != null) {
            tag.putUUID("Owner", this.ownerUUID);
        }
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        if (tag.hasUUID("Owner")) {
            this.ownerUUID = tag.getUUID("Owner");
        }
    }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    protected void defineSynchedData() { }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot p_21127_) {
        return null;
    }

    @Override
    public void setItemSlot(EquipmentSlot p_21036_, ItemStack p_21037_) { }

    @Override
    public HumanoidArm getMainArm() {
        return null;
    }
}
