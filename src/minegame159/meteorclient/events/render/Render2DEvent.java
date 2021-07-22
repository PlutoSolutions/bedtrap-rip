/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.render;

public class Render2DEvent {
    private static final /* synthetic */ Render2DEvent INSTANCE;
    public /* synthetic */ int screenHeight;
    public /* synthetic */ int screenWidth;
    public /* synthetic */ float tickDelta;

    static {
        INSTANCE = new Render2DEvent();
    }

    public Render2DEvent() {
        Render2DEvent lllllllllllllllllIllIIlIIlIlIllI;
    }

    public static Render2DEvent get(int lllllllllllllllllIllIIlIIlIlIIlI, int lllllllllllllllllIllIIlIIlIIlllI, float lllllllllllllllllIllIIlIIlIlIIII) {
        Render2DEvent.INSTANCE.screenWidth = lllllllllllllllllIllIIlIIlIlIIlI;
        Render2DEvent.INSTANCE.screenHeight = lllllllllllllllllIllIIlIIlIIlllI;
        Render2DEvent.INSTANCE.tickDelta = lllllllllllllllllIllIIlIIlIlIIII;
        return INSTANCE;
    }
}

