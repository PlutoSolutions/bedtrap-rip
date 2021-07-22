/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1799;

public class StoppedUsingItemEvent {
    public class_1799 itemStack;
    private static final StoppedUsingItemEvent INSTANCE = new StoppedUsingItemEvent();

    public static StoppedUsingItemEvent get(class_1799 class_17992) {
        StoppedUsingItemEvent.INSTANCE.itemStack = class_17992;
        return INSTANCE;
    }
}

