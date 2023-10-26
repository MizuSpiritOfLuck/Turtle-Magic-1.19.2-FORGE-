package net.felix.turtle_magic.item.custom;

import net.felix.turtle_magic.item.custom.base.TurtleStaffBase;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TurtleStaffLv3 extends TurtleStaffBase {
    public static int lv3mode;

    public TurtleStaffLv3(Properties properties) {
        super(properties);
        lv3mode = mode;
        mode0 = true;
        mode1 = true;
        mode2 = true;
    }

    @Override
    protected void changeMode(Level level, Player player, InteractionHand hand) {
        super.changeMode(level, player, hand);
        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && player.isCrouching()) {
            if(lv3mode < 2) {
                lv3mode++;
            } else if(lv3mode == 2) {
                lv3mode = 0;
            }
        }
    }
}
