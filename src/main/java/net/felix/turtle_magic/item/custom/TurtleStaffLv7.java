package net.felix.turtle_magic.item.custom;

import net.felix.turtle_magic.item.custom.base.TurtleStaffBase;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TurtleStaffLv7 extends TurtleStaffBase {
    public static int lv7mode;

    public TurtleStaffLv7(Properties properties) {
        super(properties);
        shouldDamage = true;
        lv7mode = mode;
        mode0 = true;
        mode1 = true;
        mode2 = true;
        mode3 = true;
        mode4 = true;
        mode5 = true;
        mode6 = true;
    }

    @Override
    protected void changeMode(Level level, Player player, InteractionHand hand) {
        super.changeMode(level, player, hand);
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND && player.isCrouching()) {
            if (lv7mode < 7) {
                lv7mode++;
            } else if (lv7mode == 7) {
                lv7mode = 0;
            }
            player.sendSystemMessage(Component.literal("Mode: " + lv7mode));
        }
    }
}
