package com.github.cxlina.cclient.ui.widget;

import de.cxlina.clib.color.RGBColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class SettingsButtonWidget extends ButtonWidget {

    public SettingsButtonWidget(int x, int y, int width, int height, Text message, PressAction onPress) {
        super(x, y, width, height, message, onPress);
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        fill(matrices, this.x, this.y, this.x + this.width, this.y + this.height, RGBColor.of(0, 0, 0, this.isHovered() ? 150 : 125).getRGBValue());
        drawCenteredText(matrices, MinecraftClient.getInstance().textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, this.isHovered() ? RGBColor.WHITE.getRGBValue() : RGBColor.LIGHT_GRAY.getRGBValue());
        if (this.isHovered()) {
            this.renderTooltip(matrices, mouseX, mouseY);
        }
    }
}
