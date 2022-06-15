package com.github.cxlina.cclient.event.impl;

import de.cxlina.clib.event.EventCancelable;

public class SendChatMessageEvent extends EventCancelable {

    private String message;

    public SendChatMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
