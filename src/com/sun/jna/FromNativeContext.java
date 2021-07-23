/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

public class FromNativeContext {
    private Class<?> type;

    FromNativeContext(Class<?> clazz) {
        this.type = clazz;
    }

    public Class<?> getTargetType() {
        return this.type;
    }
}

