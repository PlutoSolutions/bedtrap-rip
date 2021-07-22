/*
 * Decompiled with CFR 0.150.
 */
package meteordevelopment.orbit.listeners;

public interface IListener {
    public void call(Object var1);

    public int getPriority();

    public boolean isStatic();

    public Class<?> getTarget();
}

