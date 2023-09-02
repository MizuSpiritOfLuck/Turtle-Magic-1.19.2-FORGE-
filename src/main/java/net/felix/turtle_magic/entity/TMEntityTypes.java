package net.felix.turtle_magic.entity;

import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
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
                    () -> EntityType.Builder.of(TwirlingShellEntity::new, MobCategory.MISC)
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
                            .sized(8.0f, 8.0f)
                            .build(new ResourceLocation(TurtleMagic.MOD_ID, "testudo").toString()));

    public static final RegistryObject<EntityType<CoverShellEntity>> COVER_SHELL =
            ENTITY_TYPES.register("cover_shell",
                    () -> EntityType.Builder.<CoverShellEntity>of(CoverShellEntity::new, MobCategory.MISC)
                            .sized(3.0f, 1.0f)
                            .build(new ResourceLocation(TurtleMagic.MOD_ID, "cover_shell").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

