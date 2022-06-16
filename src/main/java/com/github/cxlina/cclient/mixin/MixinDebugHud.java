package com.github.cxlina.cclient.mixin;

import com.github.cxlina.cclient.util.Constants;
import de.cxlina.clib.color.RGBColor;
import de.cxlina.clib.render.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.ChunkPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(DebugHud.class)
public class MixinDebugHud {

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    private @Nullable ChunkPos pos;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/DebugHud;renderLeftText(Lnet/minecraft/client/util/math/MatrixStack;)V", shift = At.Shift.BEFORE), cancellable = true)
    public void renderDebugInfo(MatrixStack matrices, CallbackInfo ci) {
        ci.cancel();
        int i = 2;
        float scale = 1.0F;
        List<String> list = new ArrayList<>();
        list.add("§5CClient 1.19 v" + Constants.CLIENT_VERSION);
        list.add("");
        String s = this.client.getCameraEntity().getBlockPos().getX() + " | " + this.client.getCameraEntity().getBlockPos().getY() + " | " + this.client.getCameraEntity().getBlockPos().getZ();
        list.add("§eCoordinates: §b" + s);
        list.add("§eBiome: §b" + this.client.getCameraEntity().world.getBiome(this.client.cameraEntity.getBlockPos()).getKey().get().getValue().getPath());
        

        for (String text : list) {
            RenderUtil.INSTANCE.drawText(2, i, text, RGBColor.WHITE, scale, "minecraft", "default");
            i += 9;
        }
        this.client.getProfiler().pop();
    }
}