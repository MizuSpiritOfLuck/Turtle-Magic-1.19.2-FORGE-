package net.felix.turtle_magic.entity.client;

import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.custom.CoverShellEntity;
import net.felix.turtle_magic.entity.custom.TwirlingShellEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CoverShellModel extends AnimatedGeoModel<CoverShellEntity> {

    @Override
    public ResourceLocation getModelResource(CoverShellEntity object) {
        return new ResourceLocation(TurtleMagic.MOD_ID, "geo/cover_shell.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CoverShellEntity object) {
        return new ResourceLocation(TurtleMagic.MOD_ID, "textures/entity/turtle_shell.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CoverShellEntity animatable) {
        return new ResourceLocation(TurtleMagic.MOD_ID, "animations/cover_shell.animation.json");
    }
}
