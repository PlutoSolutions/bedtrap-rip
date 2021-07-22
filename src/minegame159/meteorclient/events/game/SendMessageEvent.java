/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.game;

import minegame159.meteorclient.events.Cancellable;

public class SendMessageEvent
extends Cancellable {
    private static final /* synthetic */ SendMessageEvent INSTANCE;
    public /* synthetic */ String message;

    public static SendMessageEvent get(String llllllllllllllllIllIIlIlIIIIIIlI) {
        INSTANCE.setCancelled(false);
        SendMessageEvent.INSTANCE.message = llllllllllllllllIllIIlIlIIIIIIlI;
        return INSTANCE;
    }

    static {
        INSTANCE = new SendMessageEvent();
    }

    public SendMessageEvent() {
        SendMessageEvent llllllllllllllllIllIIlIlIIIIIlIl;
    }
}

