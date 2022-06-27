package com.github.cxlina.cclient.mixin;

import com.github.cxlina.cclient.Client;
import com.github.cxlina.cclient.event.impl.ClientTickEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient {

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    public void cclient$initialize(RunArgs args, CallbackInfo ci) {
        Client.getInstance().initialize();
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void cclient$tick(CallbackInfo ci) {
        new ClientTickEvent().call();
    }

    @Mixin(MinecraftClient.class)
    public static interface IMixinMinecraftClient {

        @Invoker("doAttack")
        boolean invokeDoAttack();
    }
}
