package net.felix.turtle_magic.entity.client.covershell;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.felix.turtle_magic.entity.custom.CoverShellEntity;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.definitions.FrogAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class CoverShellModel<T extends CoverShellEntity> extends HierarchicalModel<T> {
    private final ModelPart CoverShell;

    public CoverShellModel(ModelPart root) {
        this.CoverShell = root.getChild("CoverShell");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition CoverShell = partdefinition.addOrReplaceChild("CoverShell", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.5215F, 0.0F));
        PartDefinition TurtleShells = CoverShell.addOrReplaceChild("TurtleShells", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition TurtleShell = TurtleShells.addOrReplaceChild("TurtleShell", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, -20.0F, 0.0F, -0.5215F, 0.0F));
        PartDefinition Underbelly = TurtleShell.addOrReplaceChild("Underbelly", CubeListBuilder.create().texOffs(51, 55).addBox(-7.0F, -0.5F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));
        PartDefinition Belly = TurtleShell.addOrReplaceChild("Belly", CubeListBuilder.create().texOffs(0, 49).addBox(-8.0F, -1.5F, -8.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));
        PartDefinition Shell = TurtleShell.addOrReplaceChild("Shell", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.5F, -10.0F, 20.0F, 2.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
        PartDefinition Shell_r1 = Shell.addOrReplaceChild("Shell_r1", CubeListBuilder.create().texOffs(0, 23).addBox(-9.0F, -3.0F, -9.0F, 18.0F, 6.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 3.1416F, 0.0F));
        PartDefinition TurtleShell2 = TurtleShells.addOrReplaceChild("TurtleShell2", CubeListBuilder.create(), PartPose.offsetAndRotation(21.0F, -15.0F, 15.0F, 0.0F, -0.5215F, 0.0F));
        PartDefinition Underbelly2 = TurtleShell2.addOrReplaceChild("Underbelly2", CubeListBuilder.create().texOffs(51, 55).addBox(-7.0F, -0.5F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));
        PartDefinition Belly2 = TurtleShell2.addOrReplaceChild("Belly2", CubeListBuilder.create().texOffs(0, 49).addBox(-8.0F, -1.5F, -8.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));
        PartDefinition Shell2 = TurtleShell2.addOrReplaceChild("Shell2", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.5F, -10.0F, 20.0F, 2.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
        PartDefinition Shell_r2 = Shell2.addOrReplaceChild("Shell_r2", CubeListBuilder.create().texOffs(0, 23).addBox(-9.0F, -3.0F, -9.0F, 18.0F, 6.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 3.1416F, 0.0F));
        PartDefinition TurtleShell3 = TurtleShells.addOrReplaceChild("TurtleShell3", CubeListBuilder.create(), PartPose.offsetAndRotation(-21.0F, -15.0F, 15.0F, 0.0F, -0.5215F, 0.0F));
        PartDefinition Underbelly3 = TurtleShell3.addOrReplaceChild("Underbelly3", CubeListBuilder.create().texOffs(51, 55).addBox(-7.0F, -0.5F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));
        PartDefinition Belly3 = TurtleShell3.addOrReplaceChild("Belly3", CubeListBuilder.create().texOffs(0, 49).addBox(-8.0F, -1.5F, -8.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));
        PartDefinition Shell3 = TurtleShell3.addOrReplaceChild("Shell3", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.5F, -10.0F, 20.0F, 2.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
        PartDefinition Shell_r3 = Shell3.addOrReplaceChild("Shell_r3", CubeListBuilder.create().texOffs(0, 23).addBox(-9.0F, -3.0F, -9.0F, 18.0F, 6.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    public static final AnimationDefinition COVER_SHELL_SPIN = AnimationDefinition.Builder.withLength(1f).looping()
            .addAnimation("TurtleShells",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 360f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("TurtleShell",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, -360f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("TurtleShell2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, -360f, 0f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("TurtleShell3",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1f, KeyframeAnimations.degreeVec(0f, -360f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.animate(entity.spinAnimationState, COVER_SHELL_SPIN, ageInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        CoverShell.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return CoverShell;
    }
}