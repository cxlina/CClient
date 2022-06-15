package com.github.cxlina.cclient;

import com.github.cxlina.cclient.discord.RichPresence;
import com.github.cxlina.cclient.event.Listener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Client implements ClientModInitializer {

    private static Client instance;
    private Listener listener;
    private RichPresence richPresence;

    public void initialize() {
        this.listener = new Listener();
        this.richPresence = new RichPresence();
    }

    public static Client getInstance() {
        if (instance == null) instance = new Client();
        return instance;
    }

    @Override
    public void onInitializeClient() {

    }
}