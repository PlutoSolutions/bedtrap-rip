/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package minegame159.meteorclient.events.entity;

import minegame159.meteorclient.events.Cancellable;
import net.minecraft.class_1799;

public class DropItemsEvent
extends Cancellable {
    public /* synthetic */ class_1799 itemStack;
    private static final /* synthetic */ DropItemsEvent INSTANCE;

    static {
        INSTANCE = new DropItemsEvent();
    }

    public static DropItemsEvent get(class_1799 llllIlIlIIIllIl) {
        INSTANCE.setCancelled(false);
        DropItemsEvent.INSTANCE.itemStack = llllIlIlIIIllIl;
        return INSTANCE;
    }

    public DropItemsEvent() {
        DropItemsEvent llllIlIlIIlIIIl;
    }
}

