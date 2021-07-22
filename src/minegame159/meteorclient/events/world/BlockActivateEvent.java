/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.events.world;

import net.minecraft.class_2680;

public class BlockActivateEvent {
    public class_2680 blockState;
    private static final BlockActivateEvent INSTANCE = new BlockActivateEvent();

    public static BlockActivateEvent get(class_2680 class_26802) {
        BlockActivateEvent.INSTANCE.blockState = class_26802;
        return INSTANCE;
    }
}

