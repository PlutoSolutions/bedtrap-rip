/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.meteor;

import minegame159.meteorclient.events.Cancellable;
import minegame159.meteorclient.utils.misc.input.KeyAction;

public class KeyEvent
extends Cancellable {
    public KeyAction action;
    public int key;
    private static final KeyEvent INSTANCE = new KeyEvent();

    public static KeyEvent get(int n, KeyAction keyAction) {
        INSTANCE.setCancelled(false);
        KeyEvent.INSTANCE.key = n;
        KeyEvent.INSTANCE.action = keyAction;
        return INSTANCE;
    }
}

