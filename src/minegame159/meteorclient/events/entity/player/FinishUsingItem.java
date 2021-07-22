/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1799
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1799;

public class FinishUsingItem {
    private static final FinishUsingItem INSTANCE = new FinishUsingItem();
    public class_1799 itemStack;

    public static FinishUsingItem get(class_1799 class_17992) {
        FinishUsingItem.INSTANCE.itemStack = class_17992;
        return INSTANCE;
    }
}

