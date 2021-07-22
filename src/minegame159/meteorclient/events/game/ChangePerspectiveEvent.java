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
    private static final ChangePerspectiveEvent INSTANCE = new ChangePerspectiveEvent();
    public class_5498 perspective;

    public static ChangePerspectiveEvent get(class_5498 class_54982) {
        INSTANCE.setCancelled(false);
        ChangePerspectiveEvent.INSTANCE.perspective = class_54982;
        return INSTANCE;
    }
}

