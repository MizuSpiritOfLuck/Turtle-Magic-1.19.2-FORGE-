package net.felix.turtle_magic.item.custom;

import net.felix.turtle_magic.item.custom.base.TurtleStaffBase;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TurtleStaffLv6 extends TurtleStaffBase {
    public static int lv6mode;

    public TurtleStaffLv6(Properties properties) {
        super(properties);
        lv6mode = mode;
        mode0 = true;
        mode1 = true;
        mode2 = true;
        mode3 = true;
        mode4 = true;
        mode5 = true;
    }

    @Override
    protected void changeMode(Level level, Player player, InteractionHand hand) {
        super.changeMode(level, player, hand);
        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && player.isCrouching()) {
            if(lv6mode < 5) {
                lv6mode++;
            } else if(lv6mode == 5) {
                lv6mode = 0;
            }
        }
    }
}
