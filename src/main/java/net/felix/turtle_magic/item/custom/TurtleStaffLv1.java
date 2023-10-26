package net.felix.turtle_magic.item.custom;

import net.felix.turtle_magic.item.custom.base.TurtleStaffBase;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TurtleStaffLv1 extends TurtleStaffBase {
    public static int lv1mode;

    public TurtleStaffLv1(Properties properties) {
        super(properties);
        lv1mode = mode;
        mode0 = true;
    }

    @Override
    protected void changeMode(Level level, Player player, InteractionHand hand) {
        super.changeMode(level, player, hand);
        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && player.isCrouching()) {
            lv1mode = 0;
        }

    }
}
