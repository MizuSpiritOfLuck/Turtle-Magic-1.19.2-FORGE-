package net.felix.turtle_magic.entity.client.testudoshell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.felix.turtle_magic.TurtleMagic;
import net.felix.turtle_magic.entity.client.TMModelLayers;
import net.felix.turtle_magic.entity.custom.TestudoShellEntity;
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
public class TestudoShellRenderer extends EntityRenderer<TestudoShellEntity> {
    protected final EntityModel<TestudoShellEntity> model;

    public TestudoShellRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new TestudoShellModel<>(context.bakeLayer(TMModelLayers.TESTUDO_LAYER));
    }

    public void render(TestudoShellEntity testudoShell, float f1, float f2, PoseStack stack, MultiBufferSource source, int i1) {
        super.render(testudoShell, f1, f2, stack, source, i1);
        stack.pushPose();
        stack.scale(-6.5f, -6.5f, 6.5f);
        stack.translate(0, -1.25, 0);
        model.setupAnim(testudoShell, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        VertexConsumer vertexConsumer = source.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(testudoShell)));
        model.renderToBuffer(stack, vertexConsumer, i1, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        stack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(TestudoShellEntity testudoShell) {
        return new ResourceLocation(TurtleMagic.MOD_ID, "textures/entity/testudo_shell.png");
    }
}