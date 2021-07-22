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
    public /* synthetic */ class_265 shape;
    public /* synthetic */ class_2680 state;
    private static final /* synthetic */ FluidCollisionShapeEvent INSTANCE;

    static {
        INSTANCE = new FluidCollisionShapeEvent();
    }

    public FluidCollisionShapeEvent() {
        FluidCollisionShapeEvent lIlllllIIlllIl;
    }

    public static FluidCollisionShapeEvent get(class_2680 lIlllllIIllIlI) {
        FluidCollisionShapeEvent.INSTANCE.state = lIlllllIIllIlI;
        FluidCollisionShapeEvent.INSTANCE.shape = null;
        return INSTANCE;
    }
}

