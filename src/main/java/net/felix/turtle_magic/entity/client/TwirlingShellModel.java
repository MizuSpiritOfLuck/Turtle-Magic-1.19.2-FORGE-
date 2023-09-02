package net.felix.turtle_magic.entity.client;

import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.custom.DescendingShellEntity;
import net.felix.turtle_magic.entity.custom.TwirlingShellEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TwirlingShellModel extends AnimatedGeoModel<TwirlingShellEntity> {

    @Override
    public ResourceLocation getModelResource(TwirlingShellEntity object) {
        return new ResourceLocation(TurtleMagic.MOD_ID, "geo/twirling_shell.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TwirlingShellEntity object) {
        return new ResourceLocation(TurtleMagic.MOD_ID, "textures/entity/twirling_shell.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TwirlingShellEntity animatable) {
        return new ResourceLocation(TurtleMagic.MOD_ID, "animations/twirling_shell.animation.json");
    }
}
