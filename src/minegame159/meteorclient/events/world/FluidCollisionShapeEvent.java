/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_265
 *  net.minecraft.class_2680
 */
package minegame159.meteorclient.events.world;

import net.minecraft.class_265;
import net.minecraft.class_2680;

public class FluidCollisionShapeEvent {
    public class_265 shape;
    public class_2680 state;
    private static final FluidCollisionShapeEvent INSTANCE = new FluidCollisionShapeEvent();

    public static FluidCollisionShapeEvent get(class_2680 class_26802) {
        FluidCollisionShapeEvent.INSTANCE.state = class_26802;
        FluidCollisionShapeEvent.INSTANCE.shape = null;
        return INSTANCE;
    }
}

