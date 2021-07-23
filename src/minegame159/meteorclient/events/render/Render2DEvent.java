/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.render;

public class Render2DEvent {
    private static final Render2DEvent INSTANCE = new Render2DEvent();
    public int screenHeight;
    public int screenWidth;
    public float tickDelta;

    public static Render2DEvent get(int n, int n2, float f) {
        Render2DEvent.INSTANCE.screenWidth = n;
        Render2DEvent.INSTANCE.screenHeight = n2;
        Render2DEvent.INSTANCE.tickDelta = f;
        return INSTANCE;
    }
}

