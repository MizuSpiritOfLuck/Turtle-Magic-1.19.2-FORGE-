package net.felix.turtle_magic.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.custom.CoverShellEntity;
import net.felix.turtle_magic.entity.custom.TwirlingShellEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class CoverShellRenderer extends GeoEntityRenderer<CoverShellEntity> {

    public CoverShellRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CoverShellModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CoverShellEntity instance) {
        return new ResourceLocation(TurtleMagic.MOD_ID, "textures/entity/cover_shell.png");
    }

    @Override
    public RenderType getRenderType(CoverShellEntity animatable, float partialTick, PoseStack stack,
                                    @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer,
                                    int packedLight, ResourceLocation texture) {
        stack.scale(1.5f, 1.5f, 1.5f);
        return super.getRenderType(animatable, partialTick, stack, bufferSource, buffer, packedLight, texture);
    }
}
