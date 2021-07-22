/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.meteor;

import minegame159.meteorclient.events.Cancellable;
import minegame159.meteorclient.utils.misc.input.KeyAction;

public class KeyEvent
extends Cancellable {
    public /* synthetic */ KeyAction action;
    public /* synthetic */ int key;
    private static final /* synthetic */ KeyEvent INSTANCE;

    public static KeyEvent get(int llllllllllllllllllIlIIlIlIlIllIl, KeyAction llllllllllllllllllIlIIlIlIlIllII) {
        INSTANCE.setCancelled(false);
        KeyEvent.INSTANCE.key = llllllllllllllllllIlIIlIlIlIllIl;
        KeyEvent.INSTANCE.action = llllllllllllllllllIlIIlIlIlIllII;
        return INSTANCE;
    }

    public KeyEvent() {
        KeyEvent llllllllllllllllllIlIIlIlIllIIll;
    }

    static {
        INSTANCE = new KeyEvent();
    }
}

