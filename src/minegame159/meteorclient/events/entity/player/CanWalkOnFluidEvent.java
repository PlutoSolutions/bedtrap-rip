/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 *  net.minecraft.class_3611
 */
package minegame159.meteorclient.events.entity.player;

import net.minecraft.class_1309;
import net.minecraft.class_3611;

public class CanWalkOnFluidEvent {
    private static final /* synthetic */ CanWalkOnFluidEvent INSTANCE;
    public /* synthetic */ class_1309 entity;
    public /* synthetic */ boolean walkOnFluid;
    public /* synthetic */ class_3611 fluid;

    public static CanWalkOnFluidEvent get(class_1309 lIIllllIIIlIlII, class_3611 lIIllllIIIlIIIl) {
        CanWalkOnFluidEvent.INSTANCE.entity = lIIllllIIIlIlII;
        CanWalkOnFluidEvent.INSTANCE.fluid = lIIllllIIIlIIIl;
        CanWalkOnFluidEvent.INSTANCE.walkOnFluid = false;
        return INSTANCE;
    }

    static {
        INSTANCE = new CanWalkOnFluidEvent();
    }

    public CanWalkOnFluidEvent() {
        CanWalkOnFluidEvent lIIllllIIIlIlll;
    }
}

