package com.github.cxlina.cclient.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;

public class RichPresence {

    private DiscordRPC rpc = DiscordRPC.INSTANCE;
    private DiscordRichPresence presence;

    public RichPresence() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> System.out.println("RichPresence Initialized.");
        this.rpc.Discord_Initialize("986720434399612981", handlers, true, null);
        this.presence = new DiscordRichPresence();
        this.presence.startTimestamp = System.currentTimeMillis() / 1000;
        this.presence.details = "Logged in as: " + MinecraftClient.getInstance().getSession().getUsername();
        this.presence.state = "Version: " + MinecraftClient.getInstance().getGameVersion();
        this.presence.largeImageKey = "icon";
        this.presence.largeImageText = "Minecraft";
        this.rpc.Discord_UpdatePresence(presence);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                this.rpc.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
        }, "Rich-Presence-Callback").start();
    }
}
