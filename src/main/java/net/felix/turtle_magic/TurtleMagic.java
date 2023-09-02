package net.felix.turtle_magic;

import net.felix.turtle_magic.block.TMBlocks;
import net.felix.turtle_magic.entity.TMEntityTypes;
import net.felix.turtle_magic.entity.client.DescendingShellRenderer;
import net.felix.turtle_magic.entity.client.SnapperFangsRenderer;
import net.felix.turtle_magic.entity.client.TestudoShellRenderer;
import net.felix.turtle_magic.entity.client.TwirlingShellRenderer;
import net.felix.turtle_magic.item.TMItems;
import net.felix.turtle_magic.networking.TMMessages;
import net.felix.turtle_magic.sound.TMSounds;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(TurtleMagic.MOD_ID)
public class TurtleMagic
{
    public static final String MOD_ID = "turtle_magic";

    public TurtleMagic()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        TMBlocks.register(modEventBus);
        TMItems.register(modEventBus);
        TMEntityTypes.register(modEventBus);
        TMMessages.register();
        TMSounds.register(modEventBus);

        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) { }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) { }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(TMEntityTypes.DESCENDING_SHELL.get(), DescendingShellRenderer::new);
            EntityRenderers.register(TMEntityTypes.TWIRLING_SHELL.get(), TwirlingShellRenderer::new);
            EntityRenderers.register(TMEntityTypes.SNAPPER_FANG.get(), SnapperFangsRenderer::new);
            EntityRenderers.register(TMEntityTypes.TESTUDO.get(), TestudoShellRenderer::new);
        }
    }
}
