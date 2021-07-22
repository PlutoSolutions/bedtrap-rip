/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1799;

public class PickItemsEvent {
    public /* synthetic */ int count;
    public /* synthetic */ class_1799 itemStack;
    private static final /* synthetic */ PickItemsEvent INSTANCE;

    public static PickItemsEvent get(class_1799 lllllllllllllllllIlIllIllIIIlIII, int lllllllllllllllllIlIllIllIIIIlII) {
        PickItemsEvent.INSTANCE.itemStack = lllllllllllllllllIlIllIllIIIlIII;
        PickItemsEvent.INSTANCE.count = lllllllllllllllllIlIllIllIIIIlII;
        return INSTANCE;
    }

    static {
        INSTANCE = new PickItemsEvent();
    }

    public PickItemsEvent() {
        PickItemsEvent lllllllllllllllllIlIllIllIIlIIII;
    }
}

