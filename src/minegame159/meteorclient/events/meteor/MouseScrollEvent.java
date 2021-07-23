/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.meteor;

import minegame159.meteorclient.events.Cancellable;

public class MouseScrollEvent
extends Cancellable {
    public double value;
    private static final MouseScrollEvent INSTANCE = new MouseScrollEvent();

    public static MouseScrollEvent get(double d) {
        INSTANCE.setCancelled(false);
        MouseScrollEvent.INSTANCE.value = d;
        return INSTANCE;
    }
}

