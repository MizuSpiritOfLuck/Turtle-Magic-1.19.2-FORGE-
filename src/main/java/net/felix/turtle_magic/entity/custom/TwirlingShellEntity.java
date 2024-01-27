package net.felix.turtle_magic.entity.custom;

import net.felix.turtle_magic.entity.TMEntityTypes;
import net.felix.turtle_magic.util.TMMethods;
import net.felix.turtle_magic.world.TMDamageSource;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class TwirlingShellEntity extends LargeFireball {
    LivingEntity summoner;

    public TwirlingShellEntity(EntityType<? extends TwirlingShellEntity> type, Level level) {
        super(type, level);
    }

    public final AnimationState spinAnimationState = new AnimationState();
    public final AnimationState deathAnimationState = new AnimationState();

    public TwirlingShellEntity(Level level, LivingEntity entity) {
        this(TMEntityTypes.TWIRLING_SHELL.get(), level);
        this.setSummoner(entity);
    }

    public TwirlingShellEntity(Level level, LivingEntity entity, double d1, double d2, double d3, int i1) {
        super(level, entity, d1, d2, d3, i1);
        this.setSummoner(entity);
        Vec3 vec3 = entity.getViewVector(1.0F);
        double d4 = d1 - (this.getX() + vec3.x);
        double d5 = d2 - (this.getY());
        double d6 = d3 - (this.getZ() + vec3.z);
        this.xPower = d4 / 0.1d;
        this.yPower = d5 / 0.1d;
        this.zPower = d6 / 0.1d;
    }

    @Override
    protected void onHit(HitResult hitResult) {

    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        this.discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {

    }

    public LivingEntity getSummoner() {
        return this.summoner;
    }

    public void setSummoner(LivingEntity summoner) {
        this.summoner = summoner;
    }
}
