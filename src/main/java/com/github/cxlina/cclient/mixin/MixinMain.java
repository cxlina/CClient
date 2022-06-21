package com.github.cxlina.cclient.mixin;

import com.github.cxlina.cclient.util.LoginUtil;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import net.minecraft.client.main.Main;
import net.minecraft.client.util.Session;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Optional;

@Mixin(Main.class)
public class MixinMain {

    @ModifyArgs(method = "main", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Session;<init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Optional;Ljava/util/Optional;Lnet/minecraft/client/util/Session$AccountType;)V"))
    private static void login(Args args) {
        try {
            MicrosoftAuthenticator auth = new MicrosoftAuthenticator();
            MicrosoftAuthResult res = auth.loginWithCredentials(LoginUtil.getEmail(), LoginUtil.getPassword()); // The LoginUtil.java isn't included, as it contains personal Data used for logging in into the game inside my IDE.
            args.setAll(res.getProfile().getName(), res.getProfile().getId(), res.getAccessToken(), Optional.of("0"), Optional.of("0"), Session.AccountType.MSA);
        } catch (MicrosoftAuthenticationException e) {
            e.printStackTrace();
        }
    }
}
