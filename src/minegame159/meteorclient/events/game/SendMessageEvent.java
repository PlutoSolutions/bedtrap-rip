/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.game;

import minegame159.meteorclient.events.Cancellable;

public class SendMessageEvent
extends Cancellable {
    private static final SendMessageEvent INSTANCE = new SendMessageEvent();
    public String message;

    public static SendMessageEvent get(String string) {
        INSTANCE.setCancelled(false);
        SendMessageEvent.INSTANCE.message = string;
        return INSTANCE;
    }
}

