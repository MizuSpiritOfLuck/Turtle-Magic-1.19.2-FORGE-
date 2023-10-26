package net.felix.turtle_magic.item.custom;

import net.felix.turtle_magic.item.custom.base.TurtleStaffBase;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TurtleStaffLv4 extends TurtleStaffBase {
    public static int lv4mode;

    public TurtleStaffLv4(Properties properties) {
        super(properties);
        lv4mode = mode;
        mode0 = true;
        mode1 = true;
        mode2 = true;
        mode3 = true;
    }

    @Override
    protected void changeMode(Level level, Player player, InteractionHand hand) {
        super.changeMode(level, player, hand);
        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && player.isCrouching()) {
            if(lv4mode < 3) {
                lv4mode++;
            } else if(lv4mode == 3) {
                lv4mode = 0;
            }
        }
    }
}
