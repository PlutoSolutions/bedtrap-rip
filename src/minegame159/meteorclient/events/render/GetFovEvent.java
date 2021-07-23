/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.render;

public class GetFovEvent {
    private static final GetFovEvent INSTANCE = new GetFovEvent();
    public double fov;

    public static GetFovEvent get(double d) {
        GetFovEvent.INSTANCE.fov = d;
        return INSTANCE;
    }
}

