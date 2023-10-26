package net.felix.turtle_magic.item.custom.base;

import net.felix.turtle_magic.entity.TMEntityTypes;
import net.felix.turtle_magic.entity.custom.*;
import net.felix.turtle_magic.util.TMMethods;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipBlockStateContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class TurtleStaffBase extends Item {
    public TurtleStaffBase(Properties properties) {
        super(properties);
    }
    protected static boolean shouldDamage = false;
    protected static int mode;
    public static boolean mode0 = false;
    public static boolean mode1 = false;
    public static boolean mode2 = false;
    public static boolean mode3 = false;
    public static boolean mode4 = false;
    public static boolean mode5 = false;
    public static boolean mode6 = false;

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean b) {
        super.damageItem(stack, 1, Minecraft.getInstance().player, null);
        if(this.getDamage(new ItemStack(this)) == 360000 && shouldDamage) {
            assert Minecraft.getInstance().player != null;
            onBroken(level, Minecraft.getInstance().player);
        }
    }

    private void onBroken(Level level, Player player) {
        MagicTurtle magicTurtle = new MagicTurtle(TMEntityTypes.MAGIC_TURTLE.get(), level);
        magicTurtle.setPos(player.position());
        magicTurtle.setBaby(true);
        level.addFreshEntity(magicTurtle);
    }

    protected void changeMode(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND && player.isCrouching()) {
            if(mode < 7) {
                mode++;
            } else if(mode == 7) {
                mode = 0;
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
      BlockHitResult povHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);

        changeMode(level, player, hand);

        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && !player.isCrouching() && mode == 0 && mode0) {
            TestudoShellEntity testudoShell = new TestudoShellEntity(level, player.getX(), player.getY(), player.getZ(), player);
            level.addFreshEntity(testudoShell);
        }

        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && !player.isCrouching() && mode == 1 && mode1) {
            CoverShellEntity coverShell = new CoverShellEntity(level, player.getX(), player.getY(), player.getZ(), player);
            level.addFreshEntity(coverShell);
        }

        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && !player.isCrouching() && mode == 2 && mode2) {
            double d0 = Math.min(povHitResult.getBlockPos().getY(), player.getY());
            double d1 = Math.max(povHitResult.getBlockPos().getY(), player.getY() + 1.0F);
            float f = (float) Mth.atan2(povHitResult.getBlockPos().getZ() - player.getZ(), povHitResult.getBlockPos().getX() - player.getX());
            if (player.distanceToSqr(povHitResult.getLocation()) < 9.0D) {
                for(int i = 0; i < 5; ++i) {
                    float f1 = f + (float)i * (float)Math.PI * 0.4F;
                    TMMethods.createSpellEntity(player.getX() + (double)Mth.cos(f1) * 1.5D, player.getZ() + (double)Mth.sin(f1) * 1.5D, d0, d1, f1, 0, player);
                }

                for(int k = 0; k < 8; ++k) {
                    float f2 = f + (float)k * (float)Math.PI * 2.0F / 8.0F + 1.2566371F;
                    TMMethods.createSpellEntity(player.getX() + (double)Mth.cos(f2) * 2.5D, player.getZ() + (double)Mth.sin(f2) * 2.5D, d0, d1, f2, 3, player);
                }
            } else {
                for(int l = 0; l < 16; ++l) {
                    double d2 = 1.25D * (double)(l + 1);
                    int j = 1 * l;
                    TMMethods.createSpellEntity(player.getX() + (double)Mth.cos(f) * d2, player.getZ() + (double)Mth.sin(f) * d2, d0, d1, f, j, player);
                }
            }
        }

        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && !player.isCrouching() && mode == 3 && mode3) {
            TwirlingShellEntity twirlingShell = new TwirlingShellEntity(level, player.getX(), player.getY(), player.getZ(), player);
            level.addFreshEntity(twirlingShell);
        }

        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && !player.isCrouching() && mode == 4 && mode4) {
            for(LivingEntity livingentity : level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(10, 10, 10))) {
                if(livingentity != player) {
                    livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 3));
                }
            }
        }

        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && !player.isCrouching() && mode == 5 && mode5) {
            for(LivingEntity livingentity : level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(15, 15, 15))) {
                if(!(livingentity instanceof Player) && !(livingentity instanceof Turtle)) {
                    Turtle turtle = new Turtle(EntityType.TURTLE, level);
                    turtle.setPos(livingentity.position());
                    turtle.setYRot(livingentity.getYRot());
                    livingentity.discard();
                    level.addFreshEntity(turtle);
                }
                if(livingentity instanceof Player && livingentity != player) {
                    livingentity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 3));
                    livingentity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 2));
                }
            }
        }

        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && !player.isCrouching() && mode == 6 && mode6) {
            DescendingShellEntity descendingShell = new DescendingShellEntity(TMEntityTypes.DESCENDING_SHELL.get(), level);
            descendingShell.setPos(BlockHitResult.miss(player.getLookAngle(), player.getDirection(), povHitResult.getBlockPos()).getBlockPos().getX(), BlockHitResult.miss(povHitResult.getLocation(), player.getDirection(), povHitResult.getBlockPos()).getBlockPos().getY() + 100, BlockHitResult.miss(povHitResult.getLocation(), player.getDirection(), povHitResult.getBlockPos()).getBlockPos().getZ());
            level.addFreshEntity(descendingShell);

        }
        return super.use(level, player, hand);
    }
}
