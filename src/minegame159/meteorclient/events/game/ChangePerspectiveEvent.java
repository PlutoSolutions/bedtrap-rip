/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_5498
 */
package minegame159.meteorclient.events.game;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_5498;

public class ChangePerspectiveEvent
extends Cancellable {
    private static final /* synthetic */ ChangePerspectiveEvent INSTANCE;
    public /* synthetic */ class_5498 perspective;

    public ChangePerspectiveEvent() {
        ChangePerspectiveEvent lIIIIIIIIllIIII;
    }

    static {
        INSTANCE = new ChangePerspectiveEvent();
    }

    public static ChangePerspectiveEvent get(class_5498 lIIIIIIIIlIllII) {
        INSTANCE.setCancelled(false);
        ChangePerspectiveEvent.INSTANCE.perspective = lIIIIIIIIlIllII;
        return INSTANCE;
    }
}

