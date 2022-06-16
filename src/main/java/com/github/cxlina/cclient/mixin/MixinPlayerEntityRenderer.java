package com.github.cxlina.cclient.mixin;

import com.github.cxlina.cclient.cosmetics.wings.WingsRenderer;
import de.cxlina.clib.color.RGBColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
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
    }

    @Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("TAIL"))
    public void cclient$showNameplate(AbstractClientPlayerEntity player, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!player.getUuid().equals(MinecraftClient.getInstance().getSession().getProfile().getId())) {
            return;
        }
        float f = ((Entity) player).getHeight() + 0.5f;
        matrices.push();
        matrices.translate(0.0, f, 0.0);
        matrices.multiply(this.dispatcher.getRotation());
        matrices.scale(-0.02f, -0.02f, 0.025F);
        Matrix4f matrix4f = matrices.peek().getPositionMatrix();
        Text t = Text.of("§7[§bUwU§7] ").copy().append(player.getDisplayName());
        getTextRenderer().draw(t, -this.getTextRenderer().getWidth(t) / 2, 0, RGBColor.of(255, 150, 255, player.isSneaking() ? 70 : 255).getRGBValue(), false, matrix4f, vertexConsumers, false, 0, light);
        matrices.pop();
    }
}
