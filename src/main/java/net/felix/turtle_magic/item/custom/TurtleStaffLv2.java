package net.felix.turtle_magic.item.custom;

import net.felix.turtle_magic.item.custom.base.TurtleStaffBase;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TurtleStaffLv2 extends TurtleStaffBase {
    public static int lv2mode;

    public TurtleStaffLv2(Properties properties) {
        super(properties);
        lv2mode = mode;
        mode0 = true;
        mode1 = true;
    }

    @Override
    protected void changeMode(Level level, Player player, InteractionHand hand) {
        super.changeMode(level, player, hand);
        if(!level.isClientSide && hand == InteractionHand.MAIN_HAND && player.isCrouching()) {
            if(lv2mode < 1) {
                lv2mode++;
            } else if(lv2mode == 1) {
                lv2mode = 0;
            }
        }
    }
}
