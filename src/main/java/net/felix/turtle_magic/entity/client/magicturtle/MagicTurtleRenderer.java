package net.felix.turtle_magic.entity.client.magicturtle;

import com.mojang.blaze3d.vertex.PoseStack;
import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.custom.MagicTurtle;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagicTurtleRenderer extends MobRenderer<MagicTurtle, MagicTurtleModel<MagicTurtle>> {
    private static final ResourceLocation TURTLE_LOCATION = new ResourceLocation(TurtleMagic.MOD_ID, "textures/entity/magic_turtle.png");

    public MagicTurtleRenderer(EntityRendererProvider.Context context) {
        super(context, new MagicTurtleModel<>(context.bakeLayer(ModelLayers.TURTLE)), 0.7F);
    }

    public void render(MagicTurtle magicTurtle, float f1, float f2, PoseStack stack, MultiBufferSource source, int i1) {
        if (magicTurtle.isBaby()) {
            this.shadowRadius *= 0.5F;
        }
        super.render(magicTurtle, f1, f2, stack, source, i1);
    }

    public ResourceLocation getTextureLocation(MagicTurtle magicTurtle) {
        return TURTLE_LOCATION;
    }
}