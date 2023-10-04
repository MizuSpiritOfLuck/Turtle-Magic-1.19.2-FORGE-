package net.felix.turtle_magic.entity.client.snapperfangs;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.custom.SnapperFangs;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SnapperFangsRenderer extends EntityRenderer<SnapperFangs> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(TurtleMagic.MOD_ID, "textures/entity/snapper_fangs.png");
    private final SnapperFangsModel<SnapperFangs> model;

    public SnapperFangsRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SnapperFangsModel<>(context.bakeLayer(ModelLayers.EVOKER_FANGS));
    }

    public void render(SnapperFangs snapperFangs, float p_114529_, float p_114530_, PoseStack stack, MultiBufferSource bufferSource, int p_114533_) {
        float f = snapperFangs.getAnimationProgress(p_114530_);
        if (f != 0.0F) {
            float f1 = 2.0F;
            if (f > 0.9F) {
                f1 *= (1.0F - f) / 0.1F;
            }

            stack.pushPose();
            stack.mulPose(Vector3f.YP.rotationDegrees(90.0F - snapperFangs.getYRot()));
            stack.scale(-f1, -f1, f1);
            float f2 = 0.03125F;
            stack.translate(0.0D, -0.626D, 0.0D);
            stack.scale(0.5F, 0.5F, 0.5F);
            this.model.setupAnim(snapperFangs, f, 0.0F, 0.0F, snapperFangs.getYRot(), snapperFangs.getXRot());
            VertexConsumer vertexconsumer = bufferSource.getBuffer(this.model.renderType(TEXTURE_LOCATION));
            this.model.renderToBuffer(stack, vertexconsumer, p_114533_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            stack.popPose();
            super.render(snapperFangs, p_114529_, p_114530_, stack, bufferSource, p_114533_);
        }
    }

    public ResourceLocation getTextureLocation(SnapperFangs p_114526_) {
        return TEXTURE_LOCATION;
    }
}
