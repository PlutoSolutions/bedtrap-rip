/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events;

import meteordevelopment.orbit.ICancellable;

public class Cancellable
implements ICancellable {
    private /* synthetic */ boolean cancelled;

    @Override
    public void setCancelled(boolean llIIIIlIIIIIIlI) {
        llIIIIlIIIIIIll.cancelled = llIIIIlIIIIIIlI;
    }

    @Override
    public boolean isCancelled() {
        Cancellable llIIIIlIIIIIIII;
        return llIIIIlIIIIIIII.cancelled;
    }

    public Cancellable() {
        Cancellable llIIIIlIIIIlIII;
        llIIIIlIIIIlIII.cancelled = false;
    }
}

