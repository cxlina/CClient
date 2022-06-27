package com.github.cxlina.cclient;

import com.github.cxlina.cclient.cosmetics.CosmeticTextures;
import com.github.cxlina.cclient.cosmetics.wings.WingsModel;
import com.github.cxlina.cclient.cosmetics.wings.WingsRenderer;
import com.github.cxlina.cclient.discord.RichPresence;
import com.github.cxlina.cclient.event.Listener;
import com.github.cxlina.cclient.io.Configuration;
import com.github.cxlina.cclient.options.Options;
import com.github.cxlina.cclient.util.KeyBindings;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

@Environment(EnvType.CLIENT)
public class Client implements ClientModInitializer {

    private static Client instance;
    private Listener listener;
    private RichPresence richPresence;
    private Configuration config;
    private Options options;
    private KeyBindings keyBindings;

    public void initialize() {
        this.config = new Configuration("./cclient.yml");
        this.options = new Options();
        this.listener = new Listener();
        this.richPresence = new RichPresence();
        if (this.options.richPresence.isEnabled())
            this.richPresence.start();
    }

    public static Client getInstance() {
        if (instance == null) instance = new Client();
        return instance;
    }

    @Override
    public void onInitializeClient() {
        this.keyBindings = new KeyBindings();
        EntityModelLayerRegistry.registerModelLayer(WingsRenderer.LAYER, WingsModel::getTexturedModelData);
        CosmeticTextures.scheduleCapeAnimationUpdates();
    }

    public RichPresence getRichPresence() {
        return richPresence;
    }

    public Configuration getConfig() {
        return config;
    }

    public Options getOptions() {
        return options;
    }

}