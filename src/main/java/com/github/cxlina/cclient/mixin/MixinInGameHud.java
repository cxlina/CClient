package com.github.cxlina.cclient.mixin;

import com.github.cxlina.cclient.Client;
import de.cxlina.clib.color.RGBColor;
import de.cxlina.clib.render.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Locale;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderAutosaveIndicator(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.AFTER))
    public void cclient$renderOverlays(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        int y = 2;
        if (!client.options.debugEnabled && (client.currentScreen == null || client.currentScreen instanceof InventoryScreen || client.currentScreen instanceof ChatScreen)) {
            if (Client.getInstance().getOptions().overlayFPS.isEnabled()) {
                RenderUtil.INSTANCE.drawText(2, y, "§6[§bFPS: " + IMixinMinecraftClient.cclient$getCurrentFPS() + "§6]", RGBColor.WHITE, 1.0F, "minecraft", "default");
                y += 9;
            }
            if (Client.getInstance().getOptions().overlayCoords.isEnabled()) {
                RenderUtil.INSTANCE.drawText(2, y, "§6[§bCoords: " + this.getCoordinatesFormat() + "§6]", RGBColor.WHITE, 1.0F, "minecraft", "default");
                y += 9;
            }
        }
    }

    private String getCoordinatesFormat() {
        return String.format("§b%.1f §7|§b %.1f §7|§b %.1f", this.client.getCameraEntity().getX(), client.getCameraEntity().getY(), client.getCameraEntity().getZ());
    }

    @Mixin(MinecraftClient.class)
    private interface IMixinMinecraftClient {


        @Accessor("currentFps")
        static int cclient$getCurrentFPS() {
            throw new AssertionError();
        }
    }
}
