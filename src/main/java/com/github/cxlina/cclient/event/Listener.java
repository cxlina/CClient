package com.github.cxlina.cclient.event;

import com.github.cxlina.cclient.event.impl.ClientTickEvent;
import com.github.cxlina.cclient.event.impl.SendChatMessageEvent;
import com.github.cxlina.cclient.event.impl.SendClientCommandEvent;
import de.cxlina.clib.event.EventHandler;
import de.cxlina.clib.event.EventUtil;

public class Listener {

    public Listener() {
        EventUtil.register(this);
    }

    @EventHandler
    public void onTick(ClientTickEvent e) {
    }

    @EventHandler
    public void onSendChatMessage(SendChatMessageEvent e) {

    }

    @EventHandler
    public void onSendClientCommand(SendClientCommandEvent e) {

    }
}
