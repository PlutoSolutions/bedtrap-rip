/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.game;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_2561;

public class ReceiveMessageEvent
extends Cancellable {
    private static final ReceiveMessageEvent INSTANCE = new ReceiveMessageEvent();
    public class_2561 message;
    public int id;

    public static ReceiveMessageEvent get(class_2561 class_25612, int n) {
        INSTANCE.setCancelled(false);
        ReceiveMessageEvent.INSTANCE.message = class_25612;
        ReceiveMessageEvent.INSTANCE.id = n;
        return INSTANCE;
    }
}

