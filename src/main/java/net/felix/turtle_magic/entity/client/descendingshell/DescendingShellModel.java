package net.felix.turtle_magic.entity.client.descendingshell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.felix.turtle_magic.entity.custom.DescendingShellEntity;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class DescendingShellModel<T extends DescendingShellEntity> extends HierarchicalModel<T> {
    private final ModelPart TurtleShell;

    public DescendingShellModel(ModelPart root) {
        this.TurtleShell = root.getChild("TurtleShell");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition TurtleShell = partdefinition.addOrReplaceChild("TurtleShell", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition Turtle = TurtleShell.addOrReplaceChild("Turtle", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.0F, 0.0F, -3.1416F));
        PartDefinition Belly = Turtle.addOrReplaceChild("Belly", CubeListBuilder.create().texOffs(0, 49).addBox(-8.0F, -1.5F, -8.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));
        PartDefinition Shell = Turtle.addOrReplaceChild("Shell", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.5F, -10.0F, 20.0F, 2.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
        PartDefinition Shell_r1 = Shell.addOrReplaceChild("Shell_r1", CubeListBuilder.create().texOffs(0, 23).addBox(-9.0F, -3.0F, -9.0F, 18.0F, 6.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public static final AnimationDefinition DESCENDING_SHELL_SPIN = AnimationDefinition.Builder.withLength(0.5f).looping()
            .addAnimation("Turtle",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 360f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity.spinAnimationState, DESCENDING_SHELL_SPIN, ageInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        TurtleShell.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return TurtleShell;
    }
}