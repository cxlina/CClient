package com.github.cxlina.cclient.mixin;

import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public abstract class MixinScreen extends AbstractParentElement implements Drawable {

    @Inject(method = "render", at = @At("TAIL"))
    public void cclient$drawCursor(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
    }
}
