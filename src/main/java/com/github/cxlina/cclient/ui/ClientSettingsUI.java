package com.github.cxlina.cclient.ui;

import com.github.cxlina.cclient.ui.widget.SettingsButtonWidget;
import de.cxlina.clib.color.RGBColor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class ClientSettingsUI extends Screen {

    private final Screen prevScreen;

    public ClientSettingsUI(Screen prevScreen) {
        super(Text.of("CClient-Settings"));
        this.prevScreen = prevScreen;
    }

    @Override
    protected void init() {
        this.addDrawableChild(new SettingsButtonWidget(5, 5, 75, 20, Text.of("Button"), button -> {
            System.out.println("UwU");
        }));
    }

    @Override
    public void close() {
        client.setScreen(this.prevScreen);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        fill(matrices, 0, 0, this.width, this.height, RGBColor.of(0, 0, 0, 25).getRGBValue());
        super.render(matrices, mouseX, mouseY, delta);
    }
}
