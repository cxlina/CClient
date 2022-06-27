package com.github.cxlina.cclient.ui.screen;

import com.github.cxlina.cclient.Client;
import com.github.cxlina.cclient.ui.widget.SettingsButtonWidget;
import com.github.cxlina.cclient.util.BooleanOption;
import de.cxlina.clib.color.RGBColor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ClientSettingsUI extends Screen {

    private final Screen prevScreen;

    public ClientSettingsUI(Screen prevScreen) {
        super(Text.of("CClient-Settings"));
        this.prevScreen = prevScreen;
    }

    @Override
    protected void init() {
        this.clearChildren();
        int s = (int) client.getWindow().getScaleFactor();
        int x = 10 * s;
        int y = 10 * s;
        for (BooleanOption option : Client.getInstance().getOptions().options) {
            if (y > this.height - (30 * s)) {
                x += 104;
                y = 0;
            }
            this.addDrawableChild(new SettingsButtonWidget(x, y, 100, 20, Text.of(option.getFriendlyName() + ": " + option.translate()), button -> {
                option.setEnabled(!option.isEnabled());
                this.init();
            }));
            y += 24;
        }

        this.addDrawableChild(new SettingsButtonWidget(this.width / 2 - 100, this.height - 30, 200, 20, ScreenTexts.DONE, button -> {
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
        if (this.client.world != null) {
            fill(matrices, 0, 0, this.width, this.height, RGBColor.of(0, 0, 0, 70).getRGBValue());
        } else {
            this.renderBackgroundTexture(0);
        }
        super.render(matrices, mouseX, mouseY, delta);
    }
}