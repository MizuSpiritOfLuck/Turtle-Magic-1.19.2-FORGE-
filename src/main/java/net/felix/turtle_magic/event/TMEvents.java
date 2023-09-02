package net.felix.turtle_magic.event;

import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.TMEntityTypes;
import net.felix.turtle_magic.entity.custom.CoverShellEntity;
import net.felix.turtle_magic.entity.custom.DescendingShellEntity;
import net.felix.turtle_magic.entity.custom.TestudoShellEntity;
import net.felix.turtle_magic.entity.custom.TwirlingShellEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class TMEvents {

    @Mod.EventBusSubscriber(modid = TurtleMagic.MOD_ID)
    public static class ForgeEvents {


        @Mod.EventBusSubscriber(modid = TurtleMagic.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class TMEventBusEvents {
            @SubscribeEvent
            public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
                event.put(TMEntityTypes.DESCENDING_SHELL.get(), DescendingShellEntity.setAttributes());
                event.put(TMEntityTypes.TWIRLING_SHELL.get(), TwirlingShellEntity.setAttributes());
                event.put(TMEntityTypes.COVER_SHELL.get(), CoverShellEntity.setAttributes());
            }
        }
    }
}