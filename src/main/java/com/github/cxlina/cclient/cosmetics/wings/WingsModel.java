package com.github.cxlina.cclient.cosmetics.wings;

import com.github.cxlina.cclient.cosmetics.CosmeticTextures;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.model.*;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.Collections;

public class WingsModel<T extends LivingEntity> extends AnimalModel<T> {


    private final ModelPart leftWing;
    private final ModelPart leftWingTip;
    private final ModelPart rightWing;
    private final ModelPart rightWingTip;

    public WingsModel(ModelPart root) {
        this.leftWing = root.getChild(EntityModelPartNames.LEFT_WING);
        this.leftWingTip = leftWing.getChild(EntityModelPartNames.LEFT_WING_TIP);
        this.rightWing = root.getChild(EntityModelPartNames.RIGHT_WING);
        this.rightWingTip = rightWing.getChild(EntityModelPartNames.RIGHT_WING_TIP);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartLeftWing = modelPartData.addChild(EntityModelPartNames.LEFT_WING, ModelPartBuilder.create().mirrored().cuboid("bone", 0.0F, -1.0F, -1.0F, 10, 2, 2, Dilation.NONE, 0, 0).cuboid("skin", 0.0F, 0.0F, 0.5F, 10, 0, 10, Dilation.NONE, -10, 8), ModelTransform.pivot(1.0F, 0.0F, 1.0F));
        modelPartLeftWing.addChild(EntityModelPartNames.LEFT_WING_TIP, ModelPartBuilder.create().mirrored().cuboid("bone", 0.0F, -0.5F, -0.5F, 10, 1, 1, Dilation.NONE, 0, 5).cuboid("skin", 0.0F, 0.0F, 0.5F, 10, 0, 10, Dilation.NONE, -10, 18), ModelTransform.pivot(10.0F, 0.0F, 0.0F));
        ModelPartData modelPartRightWing = modelPartData.addChild(EntityModelPartNames.RIGHT_WING, ModelPartBuilder.create().cuboid("bone", -10.0F, -1.0F, -1.0F, 10, 2, 2, Dilation.NONE, 0, 0).cuboid("skin", -10.0F, 0.0F, 0.5F, 10, 0, 10, Dilation.NONE, -10, 8), ModelTransform.pivot(-1.0F, 0.0F, 1.0F));
        modelPartRightWing.addChild(EntityModelPartNames.RIGHT_WING_TIP, ModelPartBuilder.create().cuboid("bone", -10.0F, -0.5F, -0.5F, 10, 1, 1, Dilation.NONE, 0, 5).cuboid("skin", -10.0F, 0.0F, 0.5F, 10, 0, 10, Dilation.NONE, -10, 18), ModelTransform.pivot(-10.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 30, 30);
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return Collections.emptyList();
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return Arrays.asList(this.leftWing, this.rightWing, this.leftWingTip, this.rightWingTip);
    }

    public void render(ClientPlayerEntity player, MatrixStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float partialTicks) {
        if (player != null) {
            matrixStackIn.push();
            matrixStackIn.scale(0.7F, 0.7F, 0.7F);
            if (player.isInSneakingPose()) {
                this.rightWing.pivotY = 5.3F;
                this.leftWing.pivotY = 5.3F;
            } else {
                this.rightWing.pivotY = 2.4F;
                this.leftWing.pivotY = 2.4F;
            }
            this.leftWing.pivotZ = 2;
            this.rightWing.pivotZ = 2;
            RenderSystem.setShaderTexture(0, CosmeticTextures.DRAGON_WINGS);
            float f = (float) (System.currentTimeMillis() % 1000L) / 1000.0F * 3.1415927F * 2.0F;
            GL11.glEnable(2884);
            this.rightWing.pitch = (float) Math.toRadians(-65.0D) - (float) Math.cos(f) * 0.2F;
            this.rightWing.yaw = (float) Math.toRadians(45.0D) + (float) Math.sin(f) * 0.4F;
            this.rightWing.roll = (float) Math.toRadians(20.0D);
            this.rightWingTip.roll = -((float) (Math.sin((double) (f + 2.0F)) + 0.5D)) * 0.75F;
            this.leftWing.pitch = this.rightWing.pitch;
            this.leftWing.yaw = -this.rightWing.yaw;
            this.leftWing.roll = -this.rightWing.roll;
            this.leftWingTip.roll = -this.rightWingTip.roll;
            this.leftWing.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
            this.rightWing.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
            matrixStackIn.pop();
        }
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
}
