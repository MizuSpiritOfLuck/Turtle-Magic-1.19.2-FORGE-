package net.felix.turtle_magic.entity.client.descendingshell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.custom.DescendingShellEntity;
import net.felix.turtle_magic.util.TMMethods;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class DescendingShellRenderer extends GeoEntityRenderer<DescendingShellEntity> {

    public DescendingShellRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DescendingShellModel());
        this.shadowRadius = 6f;
    }

    @Override
    public ResourceLocation getTextureLocation(DescendingShellEntity instance) {
        return TMMethods.TURTLE_SHELL;
    }

    @Override
    public RenderType getRenderType(DescendingShellEntity animatable, float partialTick, PoseStack stack,
                                    @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer,
                                    int packedLight, ResourceLocation texture) {
        stack.scale(3.5f, 3.5f, 3.5f);
        return super.getRenderType(animatable, partialTick, stack, bufferSource, buffer, packedLight, texture);
    }
}
