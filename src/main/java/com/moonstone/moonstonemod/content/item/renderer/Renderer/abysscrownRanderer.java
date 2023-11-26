package com.moonstone.moonstonemod.content.item.renderer.Renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.content.item.renderer.CuriosLayerDefinitions;
import com.moonstone.moonstonemod.content.item.renderer.ML.abysscrownML;
import com.moonstone.moonstonemod.init.MoonstoneMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class abysscrownRanderer implements ICurioRenderer {
	private static final ResourceLocation CROWN_TEXTURE = new ResourceLocation(MoonstoneMod.MODID, "textures/entity/abysscrown.png");

	private final abysscrownML abysscrown = new abysscrownML(Minecraft.getInstance().getEntityModels().bakeLayer(CuriosLayerDefinitions.abysscrown));

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

		followHeadRotations(slotContext.entity(), this.abysscrown.abysscrown);
		VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, RenderType.armorCutoutNoCull(CROWN_TEXTURE), false, stack
				.hasFoil());
		this.abysscrown
				.renderToBuffer(matrixStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	static void followHeadRotations(final LivingEntity livingEntity,
									final ModelPart... renderers) {

		EntityRenderer<? super LivingEntity> render =
				Minecraft.getInstance().getEntityRenderDispatcher()
						.getRenderer(livingEntity);

		if (render instanceof LivingEntityRenderer) {
			@SuppressWarnings("unchecked") LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>
					livingRenderer = (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) render;
			EntityModel<LivingEntity> model = livingRenderer.getModel();

			if (model instanceof HumanoidModel) {

				for (ModelPart renderer : renderers) {
					renderer.copyFrom(((HumanoidModel<LivingEntity>) model).head);
					renderer.xScale = renderer.xScale / 2;
					renderer.yScale = renderer.yScale / 2;

					renderer.zScale = renderer.zScale / 2;
				}
			}
		}
	}
}

