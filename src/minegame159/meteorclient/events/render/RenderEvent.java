/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_4587
 */
package minegame159.meteorclient.events.render;

import net.minecraft.class_4587;

public class RenderEvent {
    private static final /* synthetic */ RenderEvent INSTANCE;
    public /* synthetic */ float tickDelta;
    public /* synthetic */ double offsetY;
    public /* synthetic */ double offsetX;
    public /* synthetic */ double offsetZ;
    public /* synthetic */ class_4587 matrices;

    static {
        INSTANCE = new RenderEvent();
    }

    public static RenderEvent get(class_4587 llllllllllllllllllIIIlIllIIlllll, float llllllllllllllllllIIIlIllIlIIIll, double llllllllllllllllllIIIlIllIlIIIlI, double llllllllllllllllllIIIlIllIIlllII, double llllllllllllllllllIIIlIllIIllIll) {
        RenderEvent.INSTANCE.matrices = llllllllllllllllllIIIlIllIIlllll;
        RenderEvent.INSTANCE.tickDelta = llllllllllllllllllIIIlIllIlIIIll;
        RenderEvent.INSTANCE.offsetX = llllllllllllllllllIIIlIllIlIIIlI;
        RenderEvent.INSTANCE.offsetY = llllllllllllllllllIIIlIllIIlllII;
        RenderEvent.INSTANCE.offsetZ = llllllllllllllllllIIIlIllIIllIll;
        return INSTANCE;
    }

    public RenderEvent() {
        RenderEvent llllllllllllllllllIIIlIllIlIlIll;
    }
}

