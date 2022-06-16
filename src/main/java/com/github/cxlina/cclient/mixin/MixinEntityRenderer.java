package com.github.cxlina.cclient.mixin;

import de.cxlina.clib.color.RGBColor;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer<T extends Entity> {

    @Shadow
    @Final
    protected EntityRenderDispatcher dispatcher;

    @ModifyArgs(method = "renderLabelIfPresent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/text/Text;FFIZLnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;ZII)I"))
    public void cclient$disableNameplateBackground(Args args) {
        args.set(3, RGBColor.of(255, 150, 255, this.dispatcher.targetedEntity.isSneaking() ? 70 : 255).getRGBValue());
        args.set(8, 0);
    }
}
