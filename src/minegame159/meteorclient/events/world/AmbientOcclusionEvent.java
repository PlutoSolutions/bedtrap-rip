/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.world;

public class AmbientOcclusionEvent {
    public float lightLevel = -1.0f;
    private static final AmbientOcclusionEvent INSTANCE = new AmbientOcclusionEvent();

    public static AmbientOcclusionEvent get() {
        AmbientOcclusionEvent.INSTANCE.lightLevel = -1.0f;
        return INSTANCE;
    }
}

