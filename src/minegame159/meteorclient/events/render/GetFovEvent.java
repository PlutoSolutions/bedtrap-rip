/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.render;

public class GetFovEvent {
    private static final /* synthetic */ GetFovEvent INSTANCE;
    public /* synthetic */ double fov;

    public GetFovEvent() {
        GetFovEvent llllllllllllllllllllIlIIIIIlIlll;
    }

    public static GetFovEvent get(double llllllllllllllllllllIlIIIIIlIIll) {
        GetFovEvent.INSTANCE.fov = llllllllllllllllllllIlIIIIIlIIll;
        return INSTANCE;
    }

    static {
        INSTANCE = new GetFovEvent();
    }
}

