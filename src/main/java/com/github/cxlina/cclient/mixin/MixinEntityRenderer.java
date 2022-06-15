package com.github.cxlina.cclient.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer {

    @Shadow
    public abstract TextRenderer getTextRenderer();

    @Shadow
    @Final
    private TextRenderer textRenderer;

    @Inject(method = "renderLabelIfPresent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/text/Text;FFIZLnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;ZII)I", ordinal = 0, shift = At.Shift.BEFORE), cancellable = true)
    public void cclient$disableBackground(Entity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        ci.cancel();
        if (!entity.isSneaking())
            textRenderer.draw(text, -textRenderer.getWidth(text) / 2, (float) 0, -1, false, matrices.peek().getPositionMatrix(), vertexConsumers, !entity.isSneaking(), 0, light);
        matrices.pop();
    }
}
