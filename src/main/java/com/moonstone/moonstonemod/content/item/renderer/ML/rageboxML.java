package com.moonstone.moonstonemod.content.item.renderer.ML;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.content.item.renderer.Exmodle;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class rageboxML extends Exmodle {
	public final ModelPart mbox;

	public rageboxML(ModelPart part) {
		this.mbox = part.getChild("ragebox");
	}

	public static LayerDefinition createLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();
		CubeDeformation cube = new CubeDeformation(1.0F);
		part.addOrReplaceChild("ragebox",
				CubeListBuilder.create().texOffs(0, 0).
						addBox(-4.0F, -30.0F, -4.0F, 8.0F, 8.0F, 8.0F, cube),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(mesh, 32, 32);
	}

	public void setRotations(float x, float y, float z) {

	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.mbox.render(matrix, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}


}


