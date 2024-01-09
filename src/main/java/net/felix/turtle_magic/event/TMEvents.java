package net.felix.turtle_magic.event;

import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.TMEntityTypes;
import net.felix.turtle_magic.entity.client.TMModelLayers;
import net.felix.turtle_magic.entity.client.covershell.CoverShellModel;
import net.felix.turtle_magic.entity.client.descendingshell.DescendingShellModel;
import net.felix.turtle_magic.entity.client.testudoshell.TestudoShellModel;
import net.felix.turtle_magic.entity.client.twirlingshell.TwirlingShellModel;
import net.felix.turtle_magic.entity.custom.MagicTurtle;
import net.felix.turtle_magic.entity.custom.TwirlingShellEntity;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class TMEvents {

    @Mod.EventBusSubscriber(modid = TurtleMagic.MOD_ID)
    public static class ForgeEvents {



        @Mod.EventBusSubscriber(modid = TurtleMagic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class TMEventBusEvents {

            @SubscribeEvent
            public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
                event.put(TMEntityTypes.MAGIC_TURTLE.get(), MagicTurtle.setAttributes());
            }

            @SubscribeEvent
            public static void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions event) {
                event.registerLayerDefinition(TMModelLayers.TESTUDO_LAYER, TestudoShellModel::createBodyLayer);
                event.registerLayerDefinition(TMModelLayers.TWIRLING_SHELL_LAYER, TwirlingShellModel::createBodyLayer);
                event.registerLayerDefinition(TMModelLayers.COVER_SHELL_LAYER, CoverShellModel::createBodyLayer);
                event.registerLayerDefinition(TMModelLayers.DESCENDING_SHELL_LAYER, DescendingShellModel::createBodyLayer);
            }
        }
    }
}