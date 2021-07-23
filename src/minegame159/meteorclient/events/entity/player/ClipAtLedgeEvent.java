/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.events.entity.player;

public class ClipAtLedgeEvent {
    private static final ClipAtLedgeEvent INSTANCE = new ClipAtLedgeEvent();
    private boolean clip;
    private boolean set;

    public boolean isSet() {
        return this.set;
    }

    public void reset() {
        this.set = false;
    }

    public boolean isClip() {
        return this.clip;
    }

    public static ClipAtLedgeEvent get() {
        INSTANCE.reset();
        return INSTANCE;
    }

    public void setClip(boolean bl) {
        this.set = true;
        this.clip = bl;
    }
}

