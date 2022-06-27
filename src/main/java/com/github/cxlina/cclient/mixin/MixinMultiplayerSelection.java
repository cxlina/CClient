package com.github.cxlina.cclient.mixin;

public class MixinMultiplayerSelection {

    /*
    @Mixin(ClientPlayNetworkHandler.class)
    public static class MixinClientPlayNetworkHandler {

        @Shadow
        @Final
        private MinecraftClient client;

        @Shadow
        @Final
        private static Text DISCONNECT_LOST_TEXT;

        @Inject(method = "onDisconnected", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/DisconnectedScreen;<init>(Lnet/minecraft/client/gui/screen/Screen;Lnet/minecraft/text/Text;Lnet/minecraft/text/Text;)V", ordinal = 1, shift = At.Shift.BEFORE))
        public void cclient$changeMultiplayerScreen(Text reason, CallbackInfo ci) {
            ci.cancel();
            this.client.setScreen(new DisconnectedScreen(new ServerSelectionScreen(new TitleScreen()), DISCONNECT_LOST_TEXT, reason));
        }
    }

    @Mixin(TitleScreen.class)
    public static abstract class MixinTitleScreen extends Screen {

        protected MixinTitleScreen(Text title) {
            super(title);
        }

        @Inject(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 1, shift = At.Shift.BEFORE), cancellable = true)
        public void cclient$changeMultiplayerScreen(int y, int spacingY, CallbackInfo ci) {
            ci.cancel();
            this.addDrawableChild(new ButtonWidget((int) (this.width / 2 - 100), (int) (y + spacingY), (int) 200, (int) 20, (Text) Text.translatable((String) "menu.multiplayer"), button -> {
                this.client.setScreen(new ServerSelectionScreen(new TitleScreen()));
            }));
            this.addDrawableChild(new ButtonWidget((int) (this.width / 2 - 100), (int) (y + spacingY * 2), (int) 200, (int) 20, (Text) Text.translatable((String) "menu.online"), button -> {
                this.client.setScreen(new RealmsMainScreen(this));
            }));

        }
    }

    @Mixin(ChatPreviewWarningScreen.class)
    public static class MixinChatPreviewWarningScreen {

        @Inject(method = "method_44067", at = @At("HEAD"), cancellable = true)
        public void cclient$changeMultiplayerScreen(ButtonWidget button, CallbackInfo ci) {
            ci.cancel();
            MinecraftClient.getInstance().world.disconnect();
            MinecraftClient.getInstance().disconnect();
            MinecraftClient.getInstance().setScreen(new ServerSelectionScreen(new TitleScreen()));
        }
    }
     */
}
