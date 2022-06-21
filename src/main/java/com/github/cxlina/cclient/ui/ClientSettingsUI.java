package com.github.cxlina.cclient.ui;

import com.github.cxlina.cclient.Client;
import com.github.cxlina.cclient.options.Options;
import com.github.cxlina.cclient.ui.widget.SettingsButtonWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ClientSettingsUI extends Screen {

    private final Screen prevScreen;
    private ButtonListWidget list;

    public ClientSettingsUI(Screen prevScreen) {
        super(Text.of("CClient-Settings"));
        this.prevScreen = prevScreen;
    }

    @Override
    protected void init() {
        this.clearChildren();
        Options options = Client.getInstance().getOptions();
        int i = 24;
        int j = 24;
        this.addDrawableChild(new SettingsButtonWidget(i, j, 125, 20, Text.of("Rich Presence: " + options.richPresenceEnabled), button -> {
            options.richPresenceEnabled = !options.richPresenceEnabled;
            if (options.richPresenceEnabled) Client.getInstance().getRichPresence().start();
            else Client.getInstance().getRichPresence().shutdown();
            this.init();
        }));
        j += 24;
        this.addDrawableChild(new SettingsButtonWidget(i, j, 125, 20, Text.of("Cape: " + options.cosmeticCapeEnabled), button -> {
            options.cosmeticCapeEnabled = !options.cosmeticCapeEnabled;
            this.init();
        }));
        j += 24;
        this.addDrawableChild(new SettingsButtonWidget(i, j, 125, 20, Text.of("Wings: " + options.cosmeticWingsEnabled), button -> {
            options.cosmeticWingsEnabled = !options.cosmeticWingsEnabled;
            this.init();
        }));

        this.addDrawableChild(new SettingsButtonWidget(this.width / 2 - 50, this.height - 48, 100, 20, ScreenTexts.DONE, button -> {
            this.close();
        }));
    }

    @Override
    public void close() {
        Client.getInstance().getOptions().save();
        client.setScreen(this.prevScreen);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
