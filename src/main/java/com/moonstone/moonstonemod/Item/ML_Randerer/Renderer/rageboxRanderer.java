package com.moonstone.moonstonemod.Item.ML_Randerer.Renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.Item.ML_Randerer.CuriosLayerDefinitions;
import com.moonstone.moonstonemod.Item.ML_Randerer.ML.rageboxML;
import com.moonstone.moonstonemod.Item.MoonStoneItem.R.ragebox;
import com.moonstone.moonstonemod.MoonstoneMod;
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

public class rageboxRanderer implements ICurioRenderer {
    private static final ResourceLocation CROWN_TEXTURE = new ResourceLocation(MoonstoneMod.MODID, "textures/entity/ragebox.png");

    private final rageboxML rage = new rageboxML(Minecraft.getInstance().getEntityModels().bakeLayer(CuriosLayerDefinitions.ragebox));

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


        followHeadRotations(slotContext.entity(), rage.mbox);

        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(renderTypeBuffer, RenderType.armorCutoutNoCull(CROWN_TEXTURE), false, stack
                .hasFoil());
        this.rage
                .renderToBuffer(matrixStack, vertexconsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }
    private static void followHeadRotations(LivingEntity livingEntity, ModelPart... renderers) {
        EntityRenderer<? super LivingEntity> render =
                Minecraft.getInstance().getEntityRenderDispatcher()
                        .getRenderer(livingEntity);
        if (render instanceof LivingEntityRenderer) {
            @SuppressWarnings("unchecked") LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>
                    livingRenderer = (LivingEntityRenderer<LivingEntity, EntityModel<LivingEntity>>) render;
            EntityModel<LivingEntity> model = livingRenderer.getModel();
            if (model instanceof HumanoidModel)
                for (ModelPart renderer : renderers) {

                    renderer.xScale = 0.5F;
                    renderer.yScale = 0.5F;
                    renderer.zScale = 0.5F;

                    renderer.yRot = ragebox.a;
                }
        }
    }

}




