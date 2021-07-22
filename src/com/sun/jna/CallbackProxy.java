/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Callback;

public interface CallbackProxy
extends Callback {
    public Class<?> getReturnType();

    public Object callback(Object[] var1);

    public Class<?>[] getParameterTypes();
}

