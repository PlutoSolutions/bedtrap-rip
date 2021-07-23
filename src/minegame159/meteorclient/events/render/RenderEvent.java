/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.render;

import net.minecraft.class_4587;

public class RenderEvent {
    private static final RenderEvent INSTANCE = new RenderEvent();
    public float tickDelta;
    public double offsetY;
    public double offsetX;
    public double offsetZ;
    public class_4587 matrices;

    public static RenderEvent get(class_4587 class_45872, float f, double d, double d2, double d3) {
        RenderEvent.INSTANCE.matrices = class_45872;
        RenderEvent.INSTANCE.tickDelta = f;
        RenderEvent.INSTANCE.offsetX = d;
        RenderEvent.INSTANCE.offsetY = d2;
        RenderEvent.INSTANCE.offsetZ = d3;
        return INSTANCE;
    }
}

