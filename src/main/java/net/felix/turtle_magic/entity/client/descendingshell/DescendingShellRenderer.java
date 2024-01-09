package net.felix.turtle_magic.entity.client.descendingshell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.felix.turtle_magic.entity.client.TMModelLayers;
import net.felix.turtle_magic.entity.custom.DescendingShellEntity;
import net.felix.turtle_magic.util.TMMethods;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DescendingShellRenderer extends EntityRenderer<DescendingShellEntity> {
    protected final EntityModel<DescendingShellEntity> model;

    public DescendingShellRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new DescendingShellModel<>(context.bakeLayer(TMModelLayers.DESCENDING_SHELL_LAYER));
    }

    public void render(DescendingShellEntity descendingShell, float f1, float f2, PoseStack stack, MultiBufferSource source, int i1) {
        super.render(descendingShell, f1, f2, stack, source, i1);
        stack.pushPose();
        stack.scale(-3.0f, -3.0f, 3.0f);
        stack.translate(0, -1.25, 0);
        model.setupAnim(descendingShell, 1.0f, 1.0f, 1.4f, 1.0f, 1.0f);
        VertexConsumer vertexConsumer = source.getBuffer(this.model.renderType(this.getTextureLocation(descendingShell)));
        model.renderToBuffer(stack, vertexConsumer, i1, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        stack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(DescendingShellEntity descendingShell) {
        return TMMethods.TURTLE_SHELL;
    }
}