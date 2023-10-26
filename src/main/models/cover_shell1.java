// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class cover_shell<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "cover_shell"), "main");
	private final ModelPart CoverShells;

	public cover_shell(ModelPart root) {
		this.CoverShells = root.getChild("CoverShells");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition CoverShells = partdefinition.addOrReplaceChild("CoverShells", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 1.043F, 0.0F));

		PartDefinition TurtleShell = CoverShells.addOrReplaceChild("TurtleShell", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, -20.0F, 0.0F, -1.043F, 0.0F));

		PartDefinition Underbelly = TurtleShell.addOrReplaceChild("Underbelly", CubeListBuilder.create().texOffs(51, 55).addBox(-7.0F, -0.5F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition Belly = TurtleShell.addOrReplaceChild("Belly", CubeListBuilder.create().texOffs(0, 49).addBox(-8.0F, -1.5F, -8.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition Shell = TurtleShell.addOrReplaceChild("Shell", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.5F, -10.0F, 20.0F, 2.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Shell_r1 = Shell.addOrReplaceChild("Shell_r1", CubeListBuilder.create().texOffs(0, 23).addBox(-9.0F, -3.0F, -9.0F, 18.0F, 6.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition TurtleShell2 = CoverShells.addOrReplaceChild("TurtleShell2", CubeListBuilder.create(), PartPose.offsetAndRotation(21.0F, -15.0F, 15.0F, 0.0F, -1.043F, 0.0F));

		PartDefinition Underbelly2 = TurtleShell2.addOrReplaceChild("Underbelly2", CubeListBuilder.create().texOffs(51, 55).addBox(-7.0F, -0.5F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition Belly2 = TurtleShell2.addOrReplaceChild("Belly2", CubeListBuilder.create().texOffs(0, 49).addBox(-8.0F, -1.5F, -8.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition Shell2 = TurtleShell2.addOrReplaceChild("Shell2", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.5F, -10.0F, 20.0F, 2.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Shell_r2 = Shell2.addOrReplaceChild("Shell_r2", CubeListBuilder.create().texOffs(0, 23).addBox(-9.0F, -3.0F, -9.0F, 18.0F, 6.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition TurtleShell3 = CoverShells.addOrReplaceChild("TurtleShell3", CubeListBuilder.create(), PartPose.offsetAndRotation(-21.0F, -15.0F, 15.0F, 0.0F, -1.043F, 0.0F));

		PartDefinition Underbelly3 = TurtleShell3.addOrReplaceChild("Underbelly3", CubeListBuilder.create().texOffs(51, 55).addBox(-7.0F, -0.5F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition Belly3 = TurtleShell3.addOrReplaceChild("Belly3", CubeListBuilder.create().texOffs(0, 49).addBox(-8.0F, -1.5F, -8.0F, 16.0F, 3.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition Shell3 = TurtleShell3.addOrReplaceChild("Shell3", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.5F, -10.0F, 20.0F, 2.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition Shell_r3 = Shell3.addOrReplaceChild("Shell_r3", CubeListBuilder.create().texOffs(0, 23).addBox(-9.0F, -3.0F, -9.0F, 18.0F, 6.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		CoverShells.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}