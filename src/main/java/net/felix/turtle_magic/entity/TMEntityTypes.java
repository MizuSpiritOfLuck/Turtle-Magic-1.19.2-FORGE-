package net.felix.turtle_magic.entity;

import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TMEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TurtleMagic.MOD_ID);

    public static final RegistryObject<EntityType<DescendingShellEntity>> DESCENDING_SHELL =
            ENTITY_TYPES.register("descending_shell",
                    () -> EntityType.Builder.of(DescendingShellEntity::new, MobCategory.MISC)
                            .sized(5.0f, 5.0f)
                            .build(new ResourceLocation(TurtleMagic.MOD_ID, "descending_shell").toString()));

    public static final RegistryObject<EntityType<TwirlingShellEntity>> TWIRLING_SHELL =
            ENTITY_TYPES.register("twirling_shell",
                    () -> EntityType.Builder.<TwirlingShellEntity>of(TwirlingShellEntity::new, MobCategory.MISC)
                            .sized(2.0f, 2.0f)
                            .build(new ResourceLocation(TurtleMagic.MOD_ID, "twirling_shell").toString()));

    public static final RegistryObject<EntityType<SnapperFangs>> SNAPPER_FANG =
            ENTITY_TYPES.register("snapper_fang",
                    () -> EntityType.Builder.<SnapperFangs>of(SnapperFangs::new, MobCategory.MISC)
                            .sized(0.5f, 0.8f)
                            .clientTrackingRange(6)
                            .updateInterval(2)
                            .build(new ResourceLocation(TurtleMagic.MOD_ID, "snapper_fang").toString()));

    public static final RegistryObject<EntityType<TestudoShellEntity>> TESTUDO =
            ENTITY_TYPES.register("testudo",
                    () -> EntityType.Builder.<TestudoShellEntity>of(TestudoShellEntity::new, MobCategory.MISC)
                            .sized(8.0f, 3.5f)
                            .updateInterval(1)
                            .build(new ResourceLocation(TurtleMagic.MOD_ID, "testudo").toString()));

    public static final RegistryObject<EntityType<CoverShellEntity>> COVER_SHELL =
            ENTITY_TYPES.register("cover_shell",
                    () -> EntityType.Builder.<CoverShellEntity>of(CoverShellEntity::new, MobCategory.MISC)
                            .sized(3.5f, 1.2f)
                            .updateInterval(1)
                            .build(new ResourceLocation(TurtleMagic.MOD_ID, "cover_shell").toString()));

    public static final RegistryObject<EntityType<MagicTurtle>> MAGIC_TURTLE =
            ENTITY_TYPES.register("magic_turtle",
                    () -> EntityType.Builder.<MagicTurtle>of(MagicTurtle::new, MobCategory.CREATURE)
                            .sized(1.2F, 0.4F)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(TurtleMagic.MOD_ID, "magic_turtle").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

