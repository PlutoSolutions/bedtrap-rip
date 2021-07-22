/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;

public interface NativeMapped {
    public Object fromNative(Object var1, FromNativeContext var2);

    public Class<?> nativeType();

    public Object toNative();
}

