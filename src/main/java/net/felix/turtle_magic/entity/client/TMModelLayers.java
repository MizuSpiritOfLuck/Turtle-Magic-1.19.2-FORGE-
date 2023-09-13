package net.felix.turtle_magic.entity.client;

import net.felix.turtle_magic.TurtleMagic;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class TMModelLayers {
    public static final ModelLayerLocation TESTUDO_LAYER = new ModelLayerLocation(
            new ResourceLocation(TurtleMagic.MOD_ID, "testudo_layer"), "testudo_layer");

    public static final ModelLayerLocation TWIRLING_SHELL_LAYER = new ModelLayerLocation(
            new ResourceLocation(TurtleMagic.MOD_ID, "twirling_shell_layer"), "twirling_shell_layer");

    public static final ModelLayerLocation COVER_SHELL_LAYER = new ModelLayerLocation(
            new ResourceLocation(TurtleMagic.MOD_ID, "cover_shell_layer"), "cover_shell_layer");
}
