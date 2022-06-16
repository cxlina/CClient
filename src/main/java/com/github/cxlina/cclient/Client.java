package com.github.cxlina.cclient;

import com.github.cxlina.cclient.cosmetics.wings.WingsModel;
import com.github.cxlina.cclient.cosmetics.wings.WingsRenderer;
import com.github.cxlina.cclient.discord.RichPresence;
import com.github.cxlina.cclient.event.Listener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

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
        EntityModelLayerRegistry.registerModelLayer(WingsRenderer.LAYER, WingsModel::getTexturedModelData);
    }
}