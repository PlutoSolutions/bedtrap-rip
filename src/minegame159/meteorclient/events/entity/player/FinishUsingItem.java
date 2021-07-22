/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1799;

public class FinishUsingItem {
    private static final /* synthetic */ FinishUsingItem INSTANCE;
    public /* synthetic */ class_1799 itemStack;

    public FinishUsingItem() {
        FinishUsingItem llllllllllllllllIlllllIIlllIIlIl;
    }

    public static FinishUsingItem get(class_1799 llllllllllllllllIlllllIIlllIIIlI) {
        FinishUsingItem.INSTANCE.itemStack = llllllllllllllllIlllllIIlllIIIlI;
        return INSTANCE;
    }

    static {
        INSTANCE = new FinishUsingItem();
    }
}

