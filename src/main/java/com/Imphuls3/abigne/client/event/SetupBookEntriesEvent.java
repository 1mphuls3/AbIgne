package com.Imphuls3.abigne.client.event;

import net.minecraftforge.eventbus.api.Event;

public class SetupBookEntriesEvent extends Event {
    public SetupBookEntriesEvent() {
    }

    @Override
    public boolean isCancelable() {
        return false;
    }
}
