/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

import com.sun.jna.ToNativeContext;

public interface ToNativeConverter {
    public Class<?> nativeType();

    public Object toNative(Object var1, ToNativeContext var2);
}

