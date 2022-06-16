package com.github.cxlina.cclient.mixin;

import com.github.cxlina.cclient.event.impl.SendChatMessageEvent;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public abstract class MixinChatScreen extends Screen {

    @Shadow
    protected TextFieldWidget chatField;

    protected MixinChatScreen(Text title) {
        super(title);
    }

    @Inject(method = "keyPressed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ChatScreen;sendMessage(Ljava/lang/String;Z)V", shift = At.Shift.BEFORE), cancellable = true)
    public void cclient$sendMessage(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        client.setScreen(null);
        SendChatMessageEvent e = new SendChatMessageEvent(this.chatField.getText());
        e.call();
        if (e.isCancelled()) {
            cir.setReturnValue(true);
        }
        this.client.inGameHud.getChatHud().addToMessageHistory(this.chatField.getText());
    }
}
