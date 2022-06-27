package com.github.cxlina.cclient.cosmetics.cape;

import com.github.cxlina.cclient.Client;
import com.github.cxlina.cclient.cosmetics.CosmeticTextures;
import com.github.cxlina.cclient.util.Util;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

public class CapeRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    private float capeRotateX;
    private float capeRotateY;
    private float capeRotateZ;

    public CapeRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    public void render(MatrixStack pMatrixStack, VertexConsumerProvider pBuffer, int pPackedLight, AbstractClientPlayerEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (pLivingEntity.canRenderCapeTexture() && !pLivingEntity.isInvisible() && !pLivingEntity.isPartVisible(PlayerModelPart.CAPE) && Client.getInstance().getOptions().cosmeticCape.isEnabled()) {
            ItemStack itemstack = pLivingEntity.getEquippedStack(EquipmentSlot.CHEST);
            if (!itemstack.isOf(Items.ELYTRA)) {
                pMatrixStack.push();
                pMatrixStack.translate(0.0D, 0.0D, 0.125D);
                double d0 = MathHelper.lerp((double) pPartialTicks, pLivingEntity.prevCapeX, pLivingEntity.capeX) - MathHelper.lerp((double) pPartialTicks, pLivingEntity.prevX, pLivingEntity.getX());
                double d1 = MathHelper.lerp((double) pPartialTicks, pLivingEntity.prevCapeY, pLivingEntity.capeY) - MathHelper.lerp((double) pPartialTicks, pLivingEntity.prevY, pLivingEntity.getY());
                double d2 = MathHelper.lerp((double) pPartialTicks, pLivingEntity.prevCapeZ, pLivingEntity.capeZ) - MathHelper.lerp((double) pPartialTicks, pLivingEntity.prevZ, pLivingEntity.getZ());
                float f = pLivingEntity.prevBodyYaw + (pLivingEntity.bodyYaw - pLivingEntity.prevBodyYaw);
                double d3 = (double) MathHelper.sin(f * ((float) Math.PI / 180F));
                double d4 = (double) (-MathHelper.cos(f * ((float) Math.PI / 180F)));
                float f1 = (float) d1 * 10.0F;
                f1 = MathHelper.clamp(f1, -6.0F, 32.0F);
                float f2 = (float) (d0 * d3 + d2 * d4) * 100.0F;
                f2 = MathHelper.clamp(f2, 0.0F, 150.0F);
                float f3 = (float) (d0 * d4 - d2 * d3) * 100.0F;
                f3 = MathHelper.clamp(f3, -20.0F, 20.0F);

                if (f2 < 0.0F) {
                    f2 = 0.0F;
                }

                if (f2 > 165.0F) {
                    f2 = 165.0F;
                }

                if (f1 < -5.0F) {
                    f1 = -5.0F;
                }

                float f4 = MathHelper.lerp(pPartialTicks, pLivingEntity.prevStrideDistance, pLivingEntity.strideDistance);
                f1 += MathHelper.sin(MathHelper.lerp(pPartialTicks, pLivingEntity.prevHorizontalSpeed, pLivingEntity.horizontalSpeed) * 6.0F) * 32.0F * f4;

                if (pLivingEntity.isSneaking()) {
                    f1 += 25.0F;
                }

                float f5 = Util.getAverageFrameTimeSec() * 20.0F;
                f5 = Util.limit(f5, 0.02F, 1.0F);
                this.capeRotateX = MathHelper.lerp(f5, capeRotateX, 6.0F + f2 / 2.0F + f1);
                this.capeRotateZ = MathHelper.lerp(f5, capeRotateZ, f3 / 2.0F);
                this.capeRotateY = MathHelper.lerp(f5, capeRotateY, 180.0F - f3 / 2.0F);
                pMatrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(capeRotateX));
                pMatrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(capeRotateZ));
                pMatrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(capeRotateY));
                VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderLayer.getEntitySolid(CosmeticTextures.CAPE.getCurrentTexture()));
                this.getContextModel().renderCape(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.DEFAULT_UV);
                pMatrixStack.pop();
            }
        }
    }
}
