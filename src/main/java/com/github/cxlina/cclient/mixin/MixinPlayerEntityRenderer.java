package com.github.cxlina.cclient.mixin;

import com.github.cxlina.cclient.cosmetics.cape.CapeRenderer;
import com.github.cxlina.cclient.cosmetics.wings.WingsRenderer;
import de.cxlina.clib.color.RGBColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class MixinPlayerEntityRenderer extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {

    protected MixinPlayerEntityRenderer(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void cclient$registerCosmetics(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        this.addFeature(new WingsRenderer<>((PlayerEntityRenderer) (Object) this, ctx.getModelLoader()));
        this.addFeature(new CapeRenderer((PlayerEntityRenderer) (Object) this));
    }

    @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("TAIL"))
    public void cclient$showNameplate(AbstractClientPlayerEntity player, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        double d = this.dispatcher.getSquaredDistanceToCamera(player);
        if (d > 4096.0) {
            return;
        }
        boolean bl = player.isSneaking();
        float f = player.getHeight() + 0.5f;
        int i = "deadmau5".equals(player.getDisplayName().getString()) ? -10 : 0;
        matrices.push();
        matrices.translate(0.0, f, 0.0);
        matrices.multiply(this.dispatcher.getRotation());
        matrices.scale(-0.025f, -0.025f, 0.025f);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        float g = MinecraftClient.getInstance().options.getTextBackgroundOpacity(0.25f);
        int j = (int) (g * 255.0f) << 24;
        TextRenderer textRenderer = this.getTextRenderer();
        float h = -textRenderer.getWidth(player.getDisplayName()) / 2;
        textRenderer.draw(player.getDisplayName(), h, (float) i, RGBColor.of(255, 0, 0, 255).getRGBValue(), false, matrix4f, vertexConsumers, true, RGBColor.of(0, 0, 0, 0).getRGBValue(), light);
        matrices.pop();
    }
}
