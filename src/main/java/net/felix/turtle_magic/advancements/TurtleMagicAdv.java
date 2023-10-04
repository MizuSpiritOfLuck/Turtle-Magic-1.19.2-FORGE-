package net.felix.turtle_magic.advancements;

import net.felix.turtle_magic.TurtleMagic;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class TurtleMagicAdv implements Consumer<Consumer<Advancement>> {

    @Override
    public void accept(Consumer<Advancement> advancementConsumer) {

        Advancement advancement = Advancement.Builder.advancement().display(Items.TURTLE_EGG,
                Component.translatable(TurtleMagic.MOD_ID, "advancements.turtle_magic.root.title"),
                Component.translatable(TurtleMagic.MOD_ID, "advancements.turtle_magic.root.description"),
                new ResourceLocation("textures/blocks/sand.png"),
                FrameType.TASK, false, false, false).addCriterion("turtle_egg",
                InventoryChangeTrigger.TriggerInstance.hasItems(Items.TURTLE_EGG)).save(advancementConsumer, "turtle_magic/root");
    }
}
