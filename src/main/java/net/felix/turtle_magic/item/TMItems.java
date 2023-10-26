package net.felix.turtle_magic.item;

import net.felix.turtle_magic.TMCreativeTab;
import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TMItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TurtleMagic.MOD_ID);

    public static final RegistryObject<Item> UPGRADE_1 = ITEMS.register("upgrade_1",
            () -> new Item(new Item.Properties().tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> UPGRADE_2 = ITEMS.register("upgrade_2",
            () -> new Item(new Item.Properties().tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> UPGRADE_3 = ITEMS.register("upgrade_3",
            () -> new Item(new Item.Properties().tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> UPGRADE_4 = ITEMS.register("upgrade_4",
            () -> new Item(new Item.Properties().tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> UPGRADE_5 = ITEMS.register("upgrade_5",
            () -> new Item(new Item.Properties().tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> UPGRADE_6 = ITEMS.register("upgrade_6",
            () -> new Item(new Item.Properties().tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> TURTLE_STAFF_LV1 = ITEMS.register("turtle_staff_lv1",
            () -> new TurtleStaffLv1(new Item.Properties().durability(360000).tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> TURTLE_STAFF_LV2 = ITEMS.register("turtle_staff_lv2",
            () -> new TurtleStaffLv2(new Item.Properties().durability(360000).tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> TURTLE_STAFF_LV3 = ITEMS.register("turtle_staff_lv3",
            () -> new TurtleStaffLv3(new Item.Properties().durability(360000).tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> TURTLE_STAFF_LV4 = ITEMS.register("turtle_staff_lv4",
            () -> new TurtleStaffLv4(new Item.Properties().durability(360000).tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> TURTLE_STAFF_LV5 = ITEMS.register("turtle_staff_lv5",
            () -> new TurtleStaffLv5(new Item.Properties().durability(360000).tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> TURTLE_STAFF_LV6 = ITEMS.register("turtle_staff_lv6",
            () -> new TurtleStaffLv6(new Item.Properties().durability(360000).tab(TMCreativeTab.TM_TAB)));

    public static final RegistryObject<Item> TURTLE_STAFF_LV7 = ITEMS.register("turtle_staff_lv7",
            () -> new TurtleStaffLv7(new Item.Properties().durability(360000).tab(TMCreativeTab.TM_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
