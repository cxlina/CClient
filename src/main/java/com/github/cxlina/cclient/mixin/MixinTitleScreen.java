package com.github.cxlina.cclient.mixin;

import com.github.cxlina.cclient.ui.screen.ClientSettingsUI;
import com.google.common.util.concurrent.Runnables;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PressableTextWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen extends Screen {

    @Shadow
    private @Nullable String splashText;

    @Shadow
    @Final
    public static Text COPYRIGHT;

    @Shadow
    @Final
    private static Identifier ACCESSIBILITY_ICON_TEXTURE;

    @Shadow
    private Screen realmsNotificationGui;

    @Shadow
    protected abstract boolean areRealmsNotificationsEnabled();

    protected MixinTitleScreen(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At(value = "HEAD"), cancellable = true)
    public void cclient$addButtons(CallbackInfo ci) {
        ci.cancel();
        if (this.splashText == null) {
            this.splashText = this.client.getSplashTextLoader().get();
        }
        int i = this.textRenderer.getWidth(COPYRIGHT);
        int j = this.width - i - 2;
        int k = 24;
        int l = this.height / 4 + 48;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l - 24, 200, 20, Text.translatable("menu.singleplayer"), button -> this.client.setScreen(new SelectWorldScreen(this))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l, 200, 20, Text.translatable("menu.multiplayer"), button -> this.client.setScreen(new MultiplayerScreen(this))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l + 24, 200, 20, Text.translatable("menu.online"), button -> this.client.setScreen(new RealmsMainScreen(this))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l + 48, 200, 20, Text.of("Client-Settings"), button -> this.client.setScreen(new ClientSettingsUI(this))));
        this.addDrawableChild(new TexturedButtonWidget(this.width / 2 - 124, l + 72 + 12, 20, 20, 0, 106, 20, ButtonWidget.WIDGETS_TEXTURE, 256, 256, button -> this.client.setScreen(new LanguageOptionsScreen((Screen) this, this.client.options, this.client.getLanguageManager())), Text.translatable("narrator.button.language")));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l + 72 + 12, 98, 20, Text.translatable("menu.options"), button -> this.client.setScreen(new OptionsScreen(this, this.client.options))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 2, l + 72 + 12, 98, 20, Text.translatable("menu.quit"), button -> this.client.scheduleStop()));
        this.addDrawableChild(new TexturedButtonWidget(this.width / 2 + 104, l + 72 + 12, 20, 20, 0, 0, 20, ACCESSIBILITY_ICON_TEXTURE, 32, 64, button -> this.client.setScreen(new AccessibilityOptionsScreen(this, this.client.options)), Text.translatable("narrator.button.accessibility")));
        this.addDrawableChild(new PressableTextWidget(j, this.height - 10, i, 10, COPYRIGHT, button -> this.client.setScreen(new CreditsScreen(false, Runnables.doNothing())), this.textRenderer));
        this.client.setConnectedToRealms(false);
    }
}
