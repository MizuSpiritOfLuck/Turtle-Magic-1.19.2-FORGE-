package net.felix.turtle_magic.entity.client.twirlingshell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.client.TMModelLayers;
import net.felix.turtle_magic.entity.client.testudoshell.TestudoShellModel;
import net.felix.turtle_magic.entity.custom.TestudoShellEntity;
import net.felix.turtle_magic.entity.custom.TwirlingShellEntity;
import net.felix.turtle_magic.util.TMMethods;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TwirlingShellRenderer extends EntityRenderer<TwirlingShellEntity> {
    protected final EntityModel<TwirlingShellEntity> model;

    public TwirlingShellRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new TwirlingShellModel<>(context.bakeLayer(TMModelLayers.TWIRLING_SHELL_LAYER));
    }

    public void render(TwirlingShellEntity twirlingShell, float f1, float f2, PoseStack stack, MultiBufferSource source, int i1) {
        super.render(twirlingShell, f1, f2, stack, source, i1);
        stack.pushPose();
        model.setupAnim(twirlingShell, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        VertexConsumer vertexConsumer = source.getBuffer(this.model.renderType(this.getTextureLocation(twirlingShell)));
        model.renderToBuffer(stack, vertexConsumer, i1, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        stack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(TwirlingShellEntity twirlingShell) {
        return TMMethods.TURTLE_SHELL;
    }
}