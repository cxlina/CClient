package com.github.cxlina.cclient.util;

import net.minecraft.util.Identifier;

public class TextureAnimation {

    private String path;
    private int currentIndex;
    private int textureAmount;

    public TextureAnimation(String path, int textureAmount) {
        this.path = path;
        this.textureAmount = textureAmount;
        this.currentIndex = 0;
    }

    public int next() {
        if (this.currentIndex < textureAmount - 1)
            return this.currentIndex++;
        return this.currentIndex = 0;
    }

    public Identifier getCurrentTexture() {
        return new Identifier("cclient", this.path + (this.path.endsWith("/") ? "" : "/") + this.currentIndex + ".png");
    }
}
