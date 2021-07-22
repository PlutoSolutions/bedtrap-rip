/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1799;

public class PickItemsEvent {
    public int count;
    public class_1799 itemStack;
    private static final PickItemsEvent INSTANCE = new PickItemsEvent();

    public static PickItemsEvent get(class_1799 class_17992, int n) {
        PickItemsEvent.INSTANCE.itemStack = class_17992;
        PickItemsEvent.INSTANCE.count = n;
        return INSTANCE;
    }
}

