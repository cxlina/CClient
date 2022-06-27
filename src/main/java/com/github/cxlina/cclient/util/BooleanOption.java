package com.github.cxlina.cclient.util;

public class BooleanOption {

    private final String optionValue;
    private boolean enabled;
    private final String friendlyName;

    public BooleanOption(String optionValue, String friendlyName) {
        this.optionValue = optionValue;
        this.friendlyName = friendlyName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public String translate() {
        return enabled ? "On" : "Off";
    }
}
