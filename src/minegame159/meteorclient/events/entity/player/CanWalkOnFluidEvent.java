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
    private static final CanWalkOnFluidEvent INSTANCE = new CanWalkOnFluidEvent();
    public class_1309 entity;
    public boolean walkOnFluid;
    public class_3611 fluid;

    public static CanWalkOnFluidEvent get(class_1309 class_13092, class_3611 class_36112) {
        CanWalkOnFluidEvent.INSTANCE.entity = class_13092;
        CanWalkOnFluidEvent.INSTANCE.fluid = class_36112;
        CanWalkOnFluidEvent.INSTANCE.walkOnFluid = false;
        return INSTANCE;
    }
}

