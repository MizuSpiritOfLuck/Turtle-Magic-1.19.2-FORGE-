package net.felix.turtle_magic.item.custom;

import net.felix.turtle_magic.entity.TMEntityTypes;
import net.felix.turtle_magic.entity.custom.CoverShellEntity;
import net.felix.turtle_magic.entity.custom.TestudoShellEntity;
import net.felix.turtle_magic.util.TMMethods;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TempStaff extends Item {
    public TempStaff(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            CoverShellEntity coverShell = new CoverShellEntity(level, player.getX(), player.getY(), player.getZ(), player);
            level.addFreshEntity(coverShell);
        }

/*                if(!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
                    TestudoShellEntity testudoShell = new TestudoShellEntity(level, player.getX(), player.getY(), player.getZ(), player);
                    level.addFreshEntity(testudoShell);
                }*/
        /*if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && !player.isCrouching()) {
            double d0 = Math.min(getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY).getBlockPos().getY(), player.getY());
            double d1 = Math.max(getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY).getBlockPos().getY(), player.getY()) + 1.0D;
            float f = (float) Mth.atan2(getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY).getBlockPos().getZ() - player.getZ(), getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY).getBlockPos().getX() - player.getX());
            if (player.distanceToSqr(getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY).getLocation()) < 9.0D) {
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
        }*/
        return super.use(level, player, hand);
    }
}
