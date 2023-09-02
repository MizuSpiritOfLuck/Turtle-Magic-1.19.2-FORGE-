package net.felix.turtle_magic.world;

import net.felix.turtle_magic.entity.custom.DescendingShellEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class TMDamageSource {
    private boolean damageHelmet;
    private boolean bypassArmor;
    private boolean bypassInvul;
    private boolean bypassMagic;
    private boolean bypassEnchantments;
    private float exhaustion = 0.1F;
    private boolean isFireSource;
    private boolean isProjectile;
    private boolean scalesWithDifficulty;
    private boolean isMagic;
    private boolean isExplosion;
    private boolean isFall;
    private boolean noAggro;
    public final String msgId;

/*    public static DamageSource descendingShell(DescendingShellEntity descendingShell) {
        return (new EntityDamageSource("descendingShell", descendingShell)).setProjectile();
    }*/


    public String toString() {
        return "DamageSource (" + this.msgId + ")";
    }

    public boolean isProjectile() {
        return this.isProjectile;
    }

    public TMDamageSource setProjectile() {
        this.isProjectile = true;
        return this;
    }

    public boolean isExplosion() {
        return this.isExplosion;
    }

    public TMDamageSource setExplosion() {
        this.isExplosion = true;
        return this;
    }

    public boolean isBypassArmor() {
        return this.bypassArmor;
    }

    public boolean isDamageHelmet() {
        return this.damageHelmet;
    }

    public float getFoodExhaustion() {
        return this.exhaustion;
    }

    public boolean isBypassInvul() {
        return this.bypassInvul;
    }

    public boolean isBypassMagic() {
        return this.bypassMagic;
    }

    public boolean isBypassEnchantments() {
        return this.bypassEnchantments;
    }

    public TMDamageSource(String p_19333_) {
        this.msgId = p_19333_;
    }

    @Nullable
    public Entity getDirectEntity() {
        return this.getEntity();
    }

    @Nullable
    public Entity getEntity() {
        return null;
    }

    public TMDamageSource bypassArmor() {
        this.bypassArmor = true;
        this.exhaustion = 0.0F;
        return this;
    }

    public TMDamageSource damageHelmet() {
        this.damageHelmet = true;
        return this;
    }

    public TMDamageSource bypassInvul() {
        this.bypassInvul = true;
        return this;
    }

    public TMDamageSource bypassMagic() {
        this.bypassMagic = true;
        this.exhaustion = 0.0F;
        return this;
    }

    public TMDamageSource bypassEnchantments() {
        this.bypassEnchantments = true;
        return this;
    }

    public TMDamageSource setIsFire() {
        this.isFireSource = true;
        return this;
    }

    public TMDamageSource setNoAggro() {
        this.noAggro = true;
        return this;
    }

    public Component getLocalizedDeathMessage(LivingEntity livingEntity) {
        LivingEntity livingentity = livingEntity.getKillCredit();
        String s = "death.attack." + this.msgId;
        String s1 = s + ".player";
        return livingentity != null ? Component.translatable(s1, livingEntity.getDisplayName(), livingentity.getDisplayName()) : Component.translatable(s, livingEntity.getDisplayName());
    }

    public boolean isFire() {
        return this.isFireSource;
    }

    public boolean isNoAggro() {
        return this.noAggro;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public TMDamageSource setScalesWithDifficulty() {
        this.scalesWithDifficulty = true;
        return this;
    }

    public boolean scalesWithDifficulty() {
        return this.scalesWithDifficulty;
    }

    public boolean isMagic() {
        return this.isMagic;
    }

    public TMDamageSource setMagic() {
        this.isMagic = true;
        return this;
    }

    public boolean isFall() {
        return this.isFall;
    }

    public TMDamageSource setIsFall() {
        this.isFall = true;
        return this;
    }

    public boolean isCreativePlayer() {
        Entity entity = this.getEntity();
        return entity instanceof Player && ((Player)entity).getAbilities().instabuild;
    }

    @Nullable
    public Vec3 getSourcePosition() {
        return null;
    }
}
