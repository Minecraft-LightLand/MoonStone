package com.moonstone.moonstonemod.content.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;

public abstract class Exmodle extends Model {
	public Exmodle() {
		super(RenderType::entityTranslucent);
	}

	public abstract void setRotations(float paramFloat1, float paramFloat2, float paramFloat3);

	public void renderHelmToBuffer(PoseStack matrix, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
	}

	public void openMouthForTrophy(float mouthOpen) {
	}
}

