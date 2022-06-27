package com.github.cxlina.cclient.util;

import com.github.cxlina.cclient.event.impl.ClientTickEvent;
import com.github.cxlina.cclient.mixin.MixinMinecraftClient;
import de.cxlina.clib.event.EventHandler;
import de.cxlina.clib.event.EventUtil;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    private boolean isClicking = false;
    int notClickedSince = 0;

    public static final KeyBinding KB_DEBUG_HITBOXES = KeyBindingHelper.registerKeyBinding(new KeyBinding("Show Hitboxes", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F6, "Debug"));
    public static final KeyBinding KB_DEBUG_CHUNK_BORDERS = KeyBindingHelper.registerKeyBinding(new KeyBinding("Show Chunk-Borders", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F7, "Debug"));
    public static final KeyBinding KB_AUTOCLICK = KeyBindingHelper.registerKeyBinding(new KeyBinding("Auto-Click", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, "Util"));

    public KeyBindings() {
        EventUtil.register(this);
    }

    @EventHandler
    public void onTick(ClientTickEvent e) {
        //Multi-Presses

        //Single Presses.
        if (KB_DEBUG_HITBOXES.wasPressed()) {
            MinecraftClient.getInstance().getEntityRenderDispatcher().setRenderHitboxes(!MinecraftClient.getInstance().getEntityRenderDispatcher().shouldRenderHitboxes());
        } else if (KB_DEBUG_CHUNK_BORDERS.wasPressed()) {
            MinecraftClient.getInstance().debugRenderer.toggleShowChunkBorder();
        }
        if (KB_AUTOCLICK.wasPressed()) {
            this.isClicking = !this.isClicking;
        }
        if (this.isClicking) {
            if (this.notClickedSince >= 20) {
                ((MixinMinecraftClient.IMixinMinecraftClient) MinecraftClient.getInstance()).invokeDoAttack();
                this.notClickedSince = 0;
            } else {
                this.notClickedSince++;
            }
        }
    }
}