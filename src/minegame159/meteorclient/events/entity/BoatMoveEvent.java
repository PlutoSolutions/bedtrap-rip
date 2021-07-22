/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1690
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1690;

public class BoatMoveEvent {
    private static final BoatMoveEvent INSTANCE = new BoatMoveEvent();
    public class_1690 boat;

    public static BoatMoveEvent get(class_1690 class_16902) {
        BoatMoveEvent.INSTANCE.boat = class_16902;
        return INSTANCE;
    }
}

