/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.entity;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_1799;

public class DropItemsEvent
extends Cancellable {
    public class_1799 itemStack;
    private static final DropItemsEvent INSTANCE = new DropItemsEvent();

    public static DropItemsEvent get(class_1799 class_17992) {
        INSTANCE.setCancelled(false);
        DropItemsEvent.INSTANCE.itemStack = class_17992;
        return INSTANCE;
    }
}

