/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.meteor;

import minegame159.meteorclient.events.Cancellable;
import minegame159.meteorclient.utils.misc.input.KeyAction;

public class MouseButtonEvent
extends Cancellable {
    private static final /* synthetic */ MouseButtonEvent INSTANCE;
    public /* synthetic */ int button;
    public /* synthetic */ KeyAction action;

    static {
        INSTANCE = new MouseButtonEvent();
    }

    public static MouseButtonEvent get(int lllllIIIlIlII, KeyAction lllllIIIlIIIl) {
        INSTANCE.setCancelled(false);
        MouseButtonEvent.INSTANCE.button = lllllIIIlIlII;
        MouseButtonEvent.INSTANCE.action = lllllIIIlIIIl;
        return INSTANCE;
    }

    public MouseButtonEvent() {
        MouseButtonEvent lllllIIIlIlll;
    }
}

