package com.github.cxlina.cclient.cosmetics.wings;

import com.github.cxlina.cclient.mixin.IMixinEntityModelLayers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;

public class WingsRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {

    private WingsModel<T> model;
    public static EntityModelLayer LAYER = IMixinEntityModelLayers.cclient$registerMain("cosmetics_dragonwings");

    public WingsRenderer(FeatureRendererContext<T, M> context, EntityModelLoader loader) {
        super(context);
        this.model = new WingsModel<>(loader.getModelPart(LAYER));
    }

    @Override
    public void render(MatrixStack matrixStackIn, VertexConsumerProvider bufferIn, int packedLightIn, T entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch) {
        if (entityLivingBaseIn instanceof ClientPlayerEntity) {
            boolean elytraEquipped = entityLivingBaseIn.getEquippedStack(EquipmentSlot.CHEST).isOf(Items.ELYTRA);
            if (!elytraEquipped) {
                if (entityLivingBaseIn.getUuid().equals(MinecraftClient.getInstance().getSession().getProfile().getId())) {
                    ClientPlayerEntity p = (ClientPlayerEntity) entityLivingBaseIn;
                    matrixStackIn.push();
                    VertexConsumer builder = bufferIn.getBuffer(RenderLayer.getArmorCutoutNoCull(this.model.texture));
                    this.model.render(p, matrixStackIn, builder, packedLightIn, OverlayTexture.DEFAULT_UV, partialTicks);
                    matrixStackIn.pop();
                }
            }
        }
    }
}