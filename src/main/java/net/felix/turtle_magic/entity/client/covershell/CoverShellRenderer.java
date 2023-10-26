package net.felix.turtle_magic.entity.client.covershell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.felix.turtle_magic.entity.client.TMModelLayers;
import net.felix.turtle_magic.entity.client.twirlingshell.TwirlingShellModel;
import net.felix.turtle_magic.entity.custom.CoverShellEntity;
import net.felix.turtle_magic.entity.custom.TwirlingShellEntity;
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
public class CoverShellRenderer extends EntityRenderer<CoverShellEntity> {
    protected final EntityModel<CoverShellEntity> model;

    public CoverShellRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new CoverShellModel<>(context.bakeLayer(TMModelLayers.COVER_SHELL_LAYER));
    }

    public void render(CoverShellEntity coverShell, float f1, float f2, PoseStack stack, MultiBufferSource source, int i1) {
        super.render(coverShell, f1, f2, stack, source, i1);
        stack.pushPose();
        stack.scale(-1.0f, -1.0f, 1.0f);
        stack.translate(0, -1.25, 0);
        model.setupAnim(coverShell, 1.0f, 1.0f, 1.6f, 1.0f, 1.0f);
        VertexConsumer vertexConsumer = source.getBuffer(this.model.renderType(this.getTextureLocation(coverShell)));
        model.renderToBuffer(stack, vertexConsumer, i1, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        stack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(CoverShellEntity coverShell) {
        return TMMethods.TURTLE_SHELL;
    }
}