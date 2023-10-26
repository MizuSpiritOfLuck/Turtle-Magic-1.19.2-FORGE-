package net.felix.turtle_magic.block;

import net.felix.turtle_magic.TMCreativeTab;
import net.felix.turtle_magic.block.custom.MagicTurtleEggBlock;
import net.felix.turtle_magic.item.TMItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static net.felix.turtle_magic.TurtleMagic.MOD_ID;

public class TMBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> MAGIC_TURTLE_EGG = registryBlock("magic_turtle_egg",
            () -> new MagicTurtleEggBlock(BlockBehaviour.Properties.of(Material.EGG, MaterialColor.SAND)
                                .strength(0.5F)
                                .sound(SoundType.METAL)
                                .randomTicks()
                                .noOcclusion()),
                                TMCreativeTab.TM_TAB);

    private static <T extends Block>RegistryObject<T> registryBlock(String name,
      Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
      CreativeModeTab tab) {
        return TMItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {BLOCKS.register(eventBus);}
}
