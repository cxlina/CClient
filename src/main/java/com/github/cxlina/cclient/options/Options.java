package com.github.cxlina.cclient.options;

import com.github.cxlina.cclient.Client;
import com.github.cxlina.cclient.util.BooleanOption;

public class Options {

    public final BooleanOption richPresence = new BooleanOption("richPresenceEnabled", "Rich Presence");
    public final BooleanOption overlayFPS = new BooleanOption("overlayFPSEnabled", "FPS Overlay");
    public final BooleanOption overlayCoords = new BooleanOption("overlayCoordsEnabled", "Coords Overlay");

    public final BooleanOption cosmeticWings = new BooleanOption("cosmeticWingsEnabled", "Wings");
    public final BooleanOption cosmeticCape = new BooleanOption("cosmeticCapeEnabled", "Cape");

    public final BooleanOption[] generalOptions = new BooleanOption[]{
            richPresence,
    };

    public final BooleanOption[] overlayOptions = new BooleanOption[]{
            overlayFPS,
            overlayCoords
    };

    public final BooleanOption[] cosmeticsOptions = new BooleanOption[]{
            cosmeticWings,
            cosmeticCape
    };

    public Options() {
        this.load();
    }

    public void save() {
        for (BooleanOption o : this.generalOptions) {
            Client.getInstance().getConfig().set(o.getOptionValue(), o.isEnabled());
        }
        for (BooleanOption o : this.overlayOptions) {
            Client.getInstance().getConfig().set(o.getOptionValue(), o.isEnabled());
        }
        for (BooleanOption o : this.cosmeticsOptions) {
            Client.getInstance().getConfig().set(o.getOptionValue(), o.isEnabled());
        }
    }

    public void load() {
        for (BooleanOption o : this.generalOptions) {
            o.setEnabled(Client.getInstance().getConfig().get(Boolean.class, o.getOptionValue(), true));
        }
        for (BooleanOption o : this.overlayOptions) {
            o.setEnabled(Client.getInstance().getConfig().get(Boolean.class, o.getOptionValue(), true));
        }
        for (BooleanOption o : this.cosmeticsOptions) {
            o.setEnabled(Client.getInstance().getConfig().get(Boolean.class, o.getOptionValue(), true));
        }
    }
}
