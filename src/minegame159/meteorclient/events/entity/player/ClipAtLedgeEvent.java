/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events.entity.player;

public class ClipAtLedgeEvent {
    private static final /* synthetic */ ClipAtLedgeEvent INSTANCE;
    private /* synthetic */ boolean clip;
    private /* synthetic */ boolean set;

    public boolean isSet() {
        ClipAtLedgeEvent llllllllllllllllllIllIlIIllIllll;
        return llllllllllllllllllIllIlIIllIllll.set;
    }

    public void reset() {
        llllllllllllllllllIllIlIIlllIlll.set = false;
    }

    public boolean isClip() {
        ClipAtLedgeEvent llllllllllllllllllIllIlIIllIlIll;
        return llllllllllllllllllIllIlIIllIlIll.clip;
    }

    public ClipAtLedgeEvent() {
        ClipAtLedgeEvent llllllllllllllllllIllIlIIllllIll;
    }

    public static ClipAtLedgeEvent get() {
        INSTANCE.reset();
        return INSTANCE;
    }

    static {
        INSTANCE = new ClipAtLedgeEvent();
    }

    public void setClip(boolean llllllllllllllllllIllIlIIlllIIIl) {
        llllllllllllllllllIllIlIIlllIlII.set = true;
        llllllllllllllllllIllIlIIlllIlII.clip = llllllllllllllllllIllIlIIlllIIIl;
    }
}

