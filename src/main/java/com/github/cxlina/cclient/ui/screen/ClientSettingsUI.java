package com.github.cxlina.cclient.ui.screen;

import com.github.cxlina.cclient.Client;
import com.github.cxlina.cclient.ui.widget.SettingsButtonWidget;
import com.github.cxlina.cclient.util.BooleanOption;
import de.cxlina.clib.color.RGBColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ClientSettingsUI extends Screen {

    private final Screen prevScreen;
    private int activeTab;

    public ClientSettingsUI(Screen prevScreen) {
        super(Text.of("CClient-Settings"));
        this.prevScreen = prevScreen;
        this.activeTab = 0;
    }

    @Override
    protected void init() {
        this.clearChildren();
        this.initTopButtons();
        this.initActiveTab();
        this.addDrawableChild(new SettingsButtonWidget(this.width / 2 - 100, this.height - 30, 200, 20, ScreenTexts.DONE, button -> {
            this.close();
        }));
    }

    private void initTopButtons() {
        int s = (int) client.getWindow().getScaleFactor();

        this.addDrawableChild(new SettingsButtonWidget((2 * s), (2 * s), 75, 20, Text.of("General"), button -> this.setActiveTab(0)) {
            @Override
            public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
                if (activeTab == 0) {
                    fill(matrices, this.x, this.y, this.x + this.width, this.y + this.height, RGBColor.of(0, 0, 0, 200).getRGBValue());
                    drawCenteredText(matrices, MinecraftClient.getInstance().textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, RGBColor.WHITE.getRGBValue());
                    if (this.isHovered()) this.renderTooltip(matrices, mouseX, mouseY);
                } else super.renderButton(matrices, mouseX, mouseY, delta);
            }
        });

        this.addDrawableChild(new SettingsButtonWidget((2 * s) + 79, (2 * s), 75, 20, Text.of("Overlays"), button -> this.setActiveTab(1)) {
            @Override
            public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
                if (activeTab == 1) {
                    fill(matrices, this.x, this.y, this.x + this.width, this.y + this.height, RGBColor.of(0, 0, 0, 200).getRGBValue());
                    drawCenteredText(matrices, MinecraftClient.getInstance().textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, RGBColor.WHITE.getRGBValue());
                    if (this.isHovered()) this.renderTooltip(matrices, mouseX, mouseY);
                } else super.renderButton(matrices, mouseX, mouseY, delta);
            }
        });

        this.addDrawableChild(new SettingsButtonWidget((2 * s) + 158, (2 * s), 75, 20, Text.of("Cosmetics"), button -> this.setActiveTab(2)) {
            @Override
            public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
                if (activeTab == 2) {
                    fill(matrices, this.x, this.y, this.x + this.width, this.y + this.height, RGBColor.of(0, 0, 0, 200).getRGBValue());
                    drawCenteredText(matrices, MinecraftClient.getInstance().textRenderer, this.getMessage(), this.x + this.width / 2, this.y + (this.height - 8) / 2, RGBColor.WHITE.getRGBValue());
                    if (this.isHovered()) this.renderTooltip(matrices, mouseX, mouseY);
                } else super.renderButton(matrices, mouseX, mouseY, delta);
            }
        });
    }

    private void initActiveTab() {
        int s = (int) client.getWindow().getScaleFactor();
        int x = 5 * s;
        int y = (5 * s) + 30;
        int spacing = 21;
        switch (activeTab) {
            case 0 -> {
                for (BooleanOption option : Client.getInstance().getOptions().generalOptions) {
                    if (y > this.height - (30 * s)) {
                        x += 104;
                        y = 25;
                    }
                    this.addDrawableChild(new SettingsButtonWidget(x, y, 100, 20, Text.of(option.getFriendlyName() + ": " + option.translate()), button -> {
                        option.setEnabled(!option.isEnabled());
                        if (option == Client.getInstance().getOptions().richPresence) {
                            if (option.isEnabled()) Client.getInstance().getRichPresence().start();
                            else Client.getInstance().getRichPresence().shutdown();
                        }
                        Client.getInstance().getOptions().save();
                        this.init();
                    }));
                    y += spacing;
                }
            }
            case 1 -> {
                for (BooleanOption option : Client.getInstance().getOptions().overlayOptions) {
                    if (y > this.height - (30 * s)) {
                        x += 104;
                        y = 25;
                    }
                    this.addDrawableChild(new SettingsButtonWidget(x, y, 100, 20, Text.of(option.getFriendlyName() + ": " + option.translate()), button -> {
                        option.setEnabled(!option.isEnabled());
                        Client.getInstance().getOptions().save();
                        this.init();
                    }));
                    y += spacing;
                }
            }
            case 2 -> {
                for (BooleanOption option : Client.getInstance().getOptions().cosmeticsOptions) {
                    if (y > this.height - (30 * s)) {
                        x += 104;
                        y = 25;
                    }
                    this.addDrawableChild(new SettingsButtonWidget(x, y, 100, 20, Text.of(option.getFriendlyName() + ": " + option.translate()), button -> {
                        option.setEnabled(!option.isEnabled());
                        Client.getInstance().getOptions().save();
                        this.init();
                    }));
                    y += spacing;
                }
            }
        }
    }

    @Override
    public void close() {
        client.setScreen(this.prevScreen);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
    }

    public void setActiveTab(int activeTab) {
        this.activeTab = activeTab;
        this.init();
    }
}