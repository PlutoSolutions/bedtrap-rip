/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.meteor;

import minegame159.meteorclient.events.Cancellable;

public class CharTypedEvent
extends Cancellable {
    public /* synthetic */ char c;
    private static final /* synthetic */ CharTypedEvent INSTANCE;

    public CharTypedEvent() {
        CharTypedEvent lllllllllllllllllIIllllllIlIlIll;
    }

    static {
        INSTANCE = new CharTypedEvent();
    }

    public static CharTypedEvent get(char lllllllllllllllllIIllllllIlIlIIl) {
        INSTANCE.setCancelled(false);
        CharTypedEvent.INSTANCE.c = lllllllllllllllllIIllllllIlIlIIl;
        return INSTANCE;
    }
}

