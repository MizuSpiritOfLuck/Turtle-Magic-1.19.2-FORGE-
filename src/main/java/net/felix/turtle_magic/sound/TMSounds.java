package net.felix.turtle_magic.sound;

import net.felix.turtle_magic.TurtleMagic;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TMSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TurtleMagic.MOD_ID);


    public static final RegistryObject<SoundEvent> EVOCATION_BELL_RING =
            registerSounds("evocation_bell_ring");


    public static RegistryObject<SoundEvent> registerSounds(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(TurtleMagic.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
