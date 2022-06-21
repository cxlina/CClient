package com.github.cxlina.cclient.cosmetics;

import com.github.cxlina.cclient.util.TextureAnimation;
import net.minecraft.util.Identifier;

import java.util.Timer;
import java.util.TimerTask;

public class CosmeticTextures {

    public static Identifier DRAGON_WINGS = new Identifier("cclient", "textures/cosmetics/wings/0.png");
    public static TextureAnimation CAPE = new TextureAnimation("textures/cosmetics/cape/", 42);

    public static void scheduleCapeAnimationUpdates() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                CAPE.next();
            }
        }, 0L, 40L);
    }
}