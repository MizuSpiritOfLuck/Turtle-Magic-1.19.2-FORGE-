package net.felix.turtle_magic.util;

import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.custom.SnapperFangs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class TMMethods {
    public static final ResourceLocation TURTLE_SHELL = new ResourceLocation(TurtleMagic.MOD_ID, "textures/entity/turtle_shell.png");

    public static @NotNull Vec3 clipWithDistance(@NotNull LivingEntity livingEntity, @NotNull Level level, double clipDistance) {
        double vecX = Math.sin(-livingEntity.getYRot() * (Math.PI / 180.0) - Math.PI) * -Math.cos(-livingEntity.getXRot() * (Math.PI / 180.0));
        double vecY = Math.sin(-livingEntity.getXRot() * (Math.PI / 180.0));
        double vecZ = Math.cos(-livingEntity.getYRot() * (Math.PI / 180.0) - Math.PI) * -Math.cos(-livingEntity.getXRot() * (Math.PI / 180.0));
        return level.clip(new ClipContext(livingEntity.getEyePosition(), livingEntity.getEyePosition().add(vecX * clipDistance, vecY * clipDistance, vecZ * clipDistance), ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, livingEntity)).getLocation();
    }

    public static boolean hasBlockClose(ServerPlayer player, ServerLevel level, int range, Block block) {
        return level.getBlockStates(player.getBoundingBox().inflate(range))
                .filter(state -> state.is(block)).toArray().length > 0;
    }

    public static void playSound(Level level, BlockPos pos, SoundEvent sound, SoundSource source) {
        RandomSource randomsource = level.getRandom();
        level.playSound((Player)null, pos, sound, source, 3.0F, (randomsource.nextFloat() - randomsource.nextFloat()) * 0.2F + 1.0F);
    }

    public static Explosion explode(@Nullable Entity entity, Level level, double posX, double posY, double posZ, float power, Boolean b, Explosion.BlockInteraction blockInteraction, DamageSource damageSource) {
        Explosion explosion = new Explosion(level, entity, damageSource, (ExplosionDamageCalculator) null, posX, posY, posZ, power, b, blockInteraction);
        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(level, explosion)) return explosion;
        explosion.explode();
        explosion.finalizeExplosion(true);
        return explosion;
    }

    public static void createSpellEntity(double d1, double d2, double d3, double d4, float f1, int i1, Player player) {
        BlockPos blockpos = new BlockPos(d1, d4, d2);
        boolean flag = false;
        double d0 = 0.0D;

        do {
            BlockPos blockpos1 = blockpos.below();
            BlockState blockstate = player.level.getBlockState(blockpos1);
            if (blockstate.isFaceSturdy(player.level, blockpos1, Direction.UP)) {
                if (!player.level.isEmptyBlock(blockpos)) {
                    BlockState blockstate1 = player.level.getBlockState(blockpos);
                    VoxelShape voxelshape = blockstate1.getCollisionShape(player.level, blockpos);
                    if (!voxelshape.isEmpty()) {
                        d0 = voxelshape.max(Direction.Axis.Y);
                    }
                }

                flag = true;
                break;
            }

            blockpos = blockpos.below();
        } while(blockpos.getY() >= Mth.floor(d3) - 1);

        if (flag) {
            player.level.addFreshEntity(new SnapperFangs(player.level, d1, (double)blockpos.getY() + d0, d2, f1, i1, player));
        }
    }
}
