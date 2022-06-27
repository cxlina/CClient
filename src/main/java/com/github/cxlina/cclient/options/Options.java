package com.github.cxlina.cclient.options;

import com.github.cxlina.cclient.Client;
import com.github.cxlina.cclient.util.BooleanOption;

public class Options {

    public  final BooleanOption richPresence = new BooleanOption("richPresenceEnabled", "Rich Presence");
    public  final BooleanOption cosmeticWings = new BooleanOption("cosmeticWingsEnabled", "Wings");
    public  final BooleanOption cosmeticCape = new BooleanOption("cosmeticCapeEnabled", "Cape");

    public final BooleanOption[] options = new BooleanOption[]{
            richPresence,
            cosmeticWings,
            cosmeticCape
    };

    public Options() {
        this.load();
    }

    public void save() {
        for (BooleanOption o : this.options) {
            Client.getInstance().getConfig().set(o.getOptionValue(), o.isEnabled());
        }
    }

    public void load() {
        for (BooleanOption o : this.options) {
            o.setEnabled(Client.getInstance().getConfig().get(Boolean.class, o.getOptionValue(), true));
        }
    }
}
