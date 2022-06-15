package com.github.cxlina.cclient.event.impl;

import de.cxlina.clib.event.Event;

public class SendClientCommandEvent extends Event {

    private final String command;

    public SendClientCommandEvent(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
