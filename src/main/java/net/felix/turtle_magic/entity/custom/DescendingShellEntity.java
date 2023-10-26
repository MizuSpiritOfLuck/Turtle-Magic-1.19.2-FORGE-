package net.felix.turtle_magic.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DescendingShellEntity extends Entity {

    public DescendingShellEntity(EntityType<? extends DescendingShellEntity> type, Level level) {
        super(type, level);
    }
    public final AnimationState spinAnimationState = new AnimationState();

    @Override
    protected void defineSynchedData() {

    }

    public void tick() {
        if(level.isClientSide()) {
            this.spinAnimationState.startIfStopped(this.tickCount);
        }

        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.1D, 0.0D));
        }

        this.move(MoverType.SELF, this.getDeltaMovement());

        if (this.onGround) {
            if (!this.level.isClientSide) {
                this.explode();
                this.discard();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level.isClientSide) {
                this.level.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY() + 2, this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    protected void explode() {
        this.level.explode(this, this.getX(), this.getY(), this.getZ(), 25.0F, Explosion.BlockInteraction.BREAK);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) { }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) { }

    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
