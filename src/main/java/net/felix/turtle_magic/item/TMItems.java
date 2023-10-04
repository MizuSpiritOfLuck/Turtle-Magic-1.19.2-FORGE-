package net.felix.turtle_magic.item;

import net.felix.turtle_magic.TMCreativeTab;
import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.item.custom.TempStaff;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TMItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TurtleMagic.MOD_ID);

    public static final RegistryObject<Item> TEMP_STAFF = ITEMS.register("temp_staff",
            () -> new TempStaff(new Item.Properties().durability(120000).tab(TMCreativeTab.TM_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
