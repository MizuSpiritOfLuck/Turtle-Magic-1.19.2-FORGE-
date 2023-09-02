package net.felix.turtle_magic.util;

import net.felix.turtle_magic.entity.custom.SnapperFangs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TMMethods {
    public static final String MAGIC_SPELL_FAIL = "message.regna_magia.magic_spell_fail";
    public static final String NO_SPELL_LOCK = "message.regna_magia.no_spell_lock";

    public static boolean hasBlockClose(ServerPlayer player, ServerLevel level, int range, Block block) {
        return level.getBlockStates(player.getBoundingBox().inflate(range))
                .filter(state -> state.is(block)).toArray().length > 0;
    }

    public static void playSound(Level level, BlockPos pos, SoundEvent sound, SoundSource source) {
        RandomSource randomsource = level.getRandom();
        level.playSound((Player)null, pos, sound, source, 3.0F, (randomsource.nextFloat() - randomsource.nextFloat()) * 0.2F + 1.0F);
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
