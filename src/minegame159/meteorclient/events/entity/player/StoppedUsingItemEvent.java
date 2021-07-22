/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1799;

public class StoppedUsingItemEvent {
    public /* synthetic */ class_1799 itemStack;
    private static final /* synthetic */ StoppedUsingItemEvent INSTANCE;

    static {
        INSTANCE = new StoppedUsingItemEvent();
    }

    public static StoppedUsingItemEvent get(class_1799 llllllllllllllllllIlIllllIllllIl) {
        StoppedUsingItemEvent.INSTANCE.itemStack = llllllllllllllllllIlIllllIllllIl;
        return INSTANCE;
    }

    public StoppedUsingItemEvent() {
        StoppedUsingItemEvent llllllllllllllllllIlIlllllIIIIIl;
    }
}

