/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.game;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_437;

public class OpenScreenEvent
extends Cancellable {
    private static final OpenScreenEvent INSTANCE = new OpenScreenEvent();
    public class_437 screen;

    public static OpenScreenEvent get(class_437 class_4372) {
        INSTANCE.setCancelled(false);
        OpenScreenEvent.INSTANCE.screen = class_4372;
        return INSTANCE;
    }
}

