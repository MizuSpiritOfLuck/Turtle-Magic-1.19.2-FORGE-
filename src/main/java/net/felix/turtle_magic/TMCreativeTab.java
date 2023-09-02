package net.felix.turtle_magic;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TMCreativeTab {
    public static final CreativeModeTab TM_TAB = new CreativeModeTab("tm_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.TURTLE_EGG);
        }
    };
}
