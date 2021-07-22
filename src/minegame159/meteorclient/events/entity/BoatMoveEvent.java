/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1690
 */
package minegame159.meteorclient.events.entity;

import net.minecraft.class_1690;

public class BoatMoveEvent {
    private static final /* synthetic */ BoatMoveEvent INSTANCE;
    public /* synthetic */ class_1690 boat;

    public BoatMoveEvent() {
        BoatMoveEvent lllllllllllllllllIIlIlIIIIIllIII;
    }

    static {
        INSTANCE = new BoatMoveEvent();
    }

    public static BoatMoveEvent get(class_1690 lllllllllllllllllIIlIlIIIIIlIllI) {
        BoatMoveEvent.INSTANCE.boat = lllllllllllllllllIIlIlIIIIIlIllI;
        return INSTANCE;
    }
}

