/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.misc;

public interface ICopyable<T extends ICopyable<T>> {
    public T copy();

    public T set(T var1);
}

