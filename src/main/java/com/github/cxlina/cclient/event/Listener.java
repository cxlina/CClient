package com.github.cxlina.cclient.event;

import com.github.cxlina.cclient.event.impl.SendChatMessageEvent;
import com.github.cxlina.cclient.event.impl.SendClientCommandEvent;
import com.github.cxlina.cclient.ui.ClientSettingsUI;
import de.cxlina.clib.event.EventHandler;
import de.cxlina.clib.event.EventUtil;
import net.minecraft.client.MinecraftClient;

public class Listener {

    public Listener() {
        EventUtil.register(this);
    }

    @EventHandler
    public void onSendChatMessage(SendChatMessageEvent e) {
        String message = e.getMessage();
        if (message.startsWith(",c ")) {
            e.setCancelled(true);
            new SendClientCommandEvent(message.substring(3)).call();
        }
    }

    @EventHandler
    public void onSendClientCommand(SendClientCommandEvent e) {
        switch (e.getCommand()) {
            case "settings" -> {
                MinecraftClient.getInstance().setScreen(new ClientSettingsUI(null));
                System.out.println("Set screen.");
            }
            case "version" -> {

            }
        }
    }
}
