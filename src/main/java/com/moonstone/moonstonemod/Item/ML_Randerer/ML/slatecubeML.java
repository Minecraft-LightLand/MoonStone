package com.moonstone.moonstonemod.Item.ML_Randerer.ML;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.moonstone.moonstonemod.Item.ML_Randerer.Exmodle;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class slatecubeML extends Exmodle {
    public final ModelPart slatecube;

    public slatecubeML(ModelPart part) {
        this.slatecube = part.getChild("slatecube");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();
        CubeDeformation cube = new CubeDeformation(1.0F);
        part.addOrReplaceChild("slatecube",
                CubeListBuilder.create().texOffs(0, 0).
                        addBox(-4.0F, 1.0F, 3.0F, 8.0F, 8.0F, 8.0F, cube),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(mesh, 32, 16);
    }

    public void setRotations(float x, float y, float z) {

    }

    @Override
    public void renderToBuffer(PoseStack matrix, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.slatecube.render(matrix, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }


}



