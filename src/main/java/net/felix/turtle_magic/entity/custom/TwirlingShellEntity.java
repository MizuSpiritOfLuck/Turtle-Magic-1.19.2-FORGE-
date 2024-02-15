package net.felix.turtle_magic.entity.custom;

import net.felix.turtle_magic.entity.TMEntityTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TwirlingShellEntity extends LivingEntity {
    LivingEntity summoner;

    public TwirlingShellEntity(EntityType<? extends TwirlingShellEntity> type, Level level) {
        super(type, level);
    }

    public final AnimationState spinAnimationState = new AnimationState();
    public final AnimationState deathAnimationState = new AnimationState();

    public TwirlingShellEntity(Level level, LivingEntity entity, Vec3 destination) {
        this(TMEntityTypes.TWIRLING_SHELL.get(), level);
        this.setSummoner(entity);
        this.travel(destination);
    }

    public static AttributeSupplier setAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .build();
    }

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        return false;
    }

    public void setSummoner(LivingEntity summoner) {
        this.summoner = summoner;
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

    @Override
    public HumanoidArm getMainArm() {
        return null;
    }
}
