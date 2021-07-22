/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.world;

public class AmbientOcclusionEvent {
    public /* synthetic */ float lightLevel;
    private static final /* synthetic */ AmbientOcclusionEvent INSTANCE;

    public AmbientOcclusionEvent() {
        AmbientOcclusionEvent lIlllllIlIIIII;
        lIlllllIlIIIII.lightLevel = -1.0f;
    }

    public static AmbientOcclusionEvent get() {
        AmbientOcclusionEvent.INSTANCE.lightLevel = -1.0f;
        return INSTANCE;
    }

    static {
        INSTANCE = new AmbientOcclusionEvent();
    }
}

