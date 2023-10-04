package net.felix.turtle_magic.event;

import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.client.SpellHudOverlay;
import net.felix.turtle_magic.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Position;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = TurtleMagic.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.TOGGLE_SPELLHUD_KEY.consumeClick()) {
                SpellHudOverlay.showSpellHud = !SpellHudOverlay.showSpellHud;
            }
        }

        @Mod.EventBusSubscriber(modid = TurtleMagic.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
        public static class ClientModBusEvents {
            @SubscribeEvent
            public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
                event.registerAboveAll("spell_hud", SpellHudOverlay.SPELL_ICONS);
            }
            @SubscribeEvent
            public static void onKeyRegister(RegisterKeyMappingsEvent event) {
                event.register(KeyBinding.TOGGLE_SPELLHUD_KEY);
            }
        }
    }
}
