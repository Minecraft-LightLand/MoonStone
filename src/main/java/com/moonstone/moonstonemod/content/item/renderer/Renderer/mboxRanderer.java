package com.moonstone.moonstonemod.content.item.renderer.Renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.content.item.renderer.CuriosLayerDefinitions;
import com.moonstone.moonstonemod.content.item.renderer.ML.mboxML;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class mboxRanderer implements ICurioRenderer {
	private static final ResourceLocation CROWN_TEXTURE = new ResourceLocation(MoonstoneMod.MODID, "textures/entity/mbox.png");

	private final mboxML mbox = new mboxML(Minecraft.getInstance().getEntityModels().bakeLayer(CuriosLayerDefinitions.mbox));

	@Override
	public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack,
																		  SlotContext slotContext,
																		  PoseStack matrixStack,
																		  RenderLayerParent<T, M> renderLayerParent,
																		  MultiBufferSource renderTypeBuffer,
																		  int light, float limbSwing,
																		  float limbSwingAmount,
																		  float partialTicks,
																		  float ageInTicks,
																		  float netHeadYaw,
																		  float headPitch) {

		ICurioRenderer.translateIfSneaking(matrixStack, slotContext.entity());
		ICurioRenderer.rotateIfSneaking(matrixStack, slotContext.entity());
		VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, RenderType.armorCutoutNoCull(CROWN_TEXTURE), false, stack
				.hasFoil());
		this.mbox
				.renderToBuffer(matrixStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
}


