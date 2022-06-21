package com.github.cxlina.cclient.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class RichPresence {

    private DiscordRPC rpc = DiscordRPC.INSTANCE;
    private DiscordRichPresence presence;
    private boolean running = false;

    public void start() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> System.out.println("RichPresence Initialized.");
        this.rpc.Discord_Initialize("986720434399612981", handlers, true, null);
        this.presence = new DiscordRichPresence();
        this.presence.largeImageKey = "icon";
        this.presence.largeImageText = "Minecraft";
        this.presence.details = "Version: 1.19";
        this.rpc.Discord_UpdatePresence(this.presence);
        this.running = true;
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

    public void shutdown() {
        this.rpc.Discord_Shutdown();
        this.running = false;
    }

    public boolean isRunning() {
        return running;
    }
}
