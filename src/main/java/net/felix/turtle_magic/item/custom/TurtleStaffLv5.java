package net.felix.turtle_magic.item.custom;

import net.felix.turtle_magic.item.custom.base.TurtleStaffBase;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TurtleStaffLv5 extends TurtleStaffBase {
    public static int lv5mode;

    public TurtleStaffLv5(Properties properties) {
        super(properties);
        lv5mode = mode;
        mode0 = true;
        mode1 = true;
        mode2 = true;
        mode3 = true;
        mode4 = true;
    }

    @Override
    protected void changeMode(Level level, Player player, InteractionHand hand) {
        super.changeMode(level, player, hand);
        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && player.isCrouching()) {
            if(lv5mode < 4) {
                lv5mode++;
            } else if(lv5mode == 4) {
                lv5mode = 0;
            }
        }
    }
}
