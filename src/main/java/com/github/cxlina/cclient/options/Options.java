package com.github.cxlina.cclient.options;

import com.github.cxlina.cclient.Client;

public class Options {

    public boolean richPresenceEnabled;
    public boolean cosmeticWingsEnabled;
    public boolean cosmeticCapeEnabled;

    public Options() {
        this.load();
    }

    public void save() {
        new Thread(() -> {
            Client.getInstance().getConfig().set("richPresenceEnabled", this.richPresenceEnabled);
            Client.getInstance().getConfig().set("cosmeticWingsEnabled", this.cosmeticWingsEnabled);
            Client.getInstance().getConfig().set("cosmeticCapeEnabled", this.cosmeticCapeEnabled);
            System.out.println("Saved.");
        }).start();
    }

    public void load() {
        new Thread(() -> {
            this.richPresenceEnabled = Client.getInstance().getConfig().get(Boolean.class, "richPresenceEnabled", true);
            this.cosmeticWingsEnabled = Client.getInstance().getConfig().get(Boolean.class, "cosmeticWingsEnabled", true);
            this.cosmeticCapeEnabled = Client.getInstance().getConfig().get(Boolean.class, "cosmeticCapeEnabled", true);
            System.out.println("Loaded.");
        }).start();
    }
}
