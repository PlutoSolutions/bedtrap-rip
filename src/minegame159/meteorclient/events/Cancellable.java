/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.events;

import meteordevelopment.orbit.ICancellable;

public class Cancellable
implements ICancellable {
    private boolean cancelled = false;

    @Override
    public void setCancelled(boolean bl) {
        this.cancelled = bl;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }
}

