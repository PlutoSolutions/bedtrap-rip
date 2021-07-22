/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.meteor;

import minegame159.meteorclient.events.Cancellable;
import minegame159.meteorclient.utils.misc.input.KeyAction;

public class MouseButtonEvent
extends Cancellable {
    private static final MouseButtonEvent INSTANCE = new MouseButtonEvent();
    public int button;
    public KeyAction action;

    public static MouseButtonEvent get(int n, KeyAction keyAction) {
        INSTANCE.setCancelled(false);
        MouseButtonEvent.INSTANCE.button = n;
        MouseButtonEvent.INSTANCE.action = keyAction;
        return INSTANCE;
    }
}

