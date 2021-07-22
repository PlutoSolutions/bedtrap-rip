/*
 * Decompiled with CFR 0.150.
 */
package meteordevelopment.orbit;

public interface ICancellable {
    default public void cancel() {
        this.setCancelled(true);
    }

    public void setCancelled(boolean var1);

    public boolean isCancelled();
}

