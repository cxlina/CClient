package com.github.cxlina.cclient.mixin;

import de.cxlina.clib.color.RGBColor;
import de.cxlina.clib.render.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderAutosaveIndicator(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
    public void cclient$renderOverlays(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        RenderUtil.INSTANCE.drawText(2, 2, "§6[§7" + IMixinMinecraftClient.cclient$getCurrentFPS() + " FPS§6]", RGBColor.WHITE, 1.0F, "minecraft", "default");
    }

    @Mixin(MinecraftClient.class)
    private interface IMixinMinecraftClient {

        @Accessor("currentFps")
        static int cclient$getCurrentFPS() {
            throw new AssertionError();
        }
    }
}
