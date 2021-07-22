/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

public class FromNativeContext {
    private Class<?> type;

    FromNativeContext(Class<?> class_) {
        this.type = class_;
    }

    public Class<?> getTargetType() {
        return this.type;
    }
}

