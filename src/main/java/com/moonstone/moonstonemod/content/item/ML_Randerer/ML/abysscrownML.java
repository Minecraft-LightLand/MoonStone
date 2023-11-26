package com.moonstone.moonstonemod.content.item.ML_Randerer.ML;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.content.item.ML_Randerer.Exmodle;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class abysscrownML extends Exmodle {
	public final ModelPart abysscrown;

	public abysscrownML(ModelPart part) {
		this.abysscrown = part.getChild("abysscrown");
	}

	public static LayerDefinition createLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition part = mesh.getRoot();
		CubeDeformation cube = new CubeDeformation(1.0F);
		part.addOrReplaceChild("abysscrown",
				CubeListBuilder.create().texOffs(0, 0).
						addBox(-3.0F, -21.0F, -3.0F, 6, 4, 6, cube),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		return LayerDefinition.create(mesh, 32, 32);
	}

	public void setRotations(float x, float y, float z) {

	}

	@Override
	public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.abysscrown.render(matrix, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}


}
