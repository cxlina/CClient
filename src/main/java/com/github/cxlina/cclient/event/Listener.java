package com.github.cxlina.cclient.event;

import com.github.cxlina.cclient.Client;
import com.github.cxlina.cclient.cosmetics.wings.WingsRenderer;
import com.github.cxlina.cclient.event.impl.SendChatMessageEvent;
import com.github.cxlina.cclient.event.impl.SendClientCommandEvent;
import com.github.cxlina.cclient.util.Constants;
import de.cxlina.clib.event.EventHandler;
import de.cxlina.clib.event.EventUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

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
            case "version" -> {
                MinecraftClient.getInstance().player.sendMessage(Text.of("§7[§bCClient§7] §eVersion: §b" + Constants.CLIENT_VERSION));
            }
            case "rpc" -> {
                if (Client.getInstance().getRichPresence().isRunning()) {
                    Client.getInstance().getRichPresence().shutdown();
                    MinecraftClient.getInstance().player.sendMessage(Text.of("§7[§bCClient§7] §aRPC successfully shut down."));
                } else {
                    Client.getInstance().getRichPresence().start();
                    MinecraftClient.getInstance().player.sendMessage(Text.of("§7[§bCClient§7] §aRPC successfully started."));
                }
            }
            case "wings toggle" -> {
                if (WingsRenderer.isEnabled()) {
                    WingsRenderer.setEnabled(false);
                    MinecraftClient.getInstance().player.sendMessage(Text.of("§7[§bCClient§7] §aWings disabled."));
                } else {
                    WingsRenderer.setEnabled(true);
                    MinecraftClient.getInstance().player.sendMessage(Text.of("§7[§bCClient§7] §aWings enabled."));
                }
            }
        }
    }
}
