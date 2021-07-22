/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_239
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_239;

public class ItemUseCrosshairTargetEvent {
    private static final ItemUseCrosshairTargetEvent INSTANCE = new ItemUseCrosshairTargetEvent();
    public class_239 target;

    public static ItemUseCrosshairTargetEvent get(class_239 class_2392) {
        ItemUseCrosshairTargetEvent.INSTANCE.target = class_2392;
        return INSTANCE;
    }
}

