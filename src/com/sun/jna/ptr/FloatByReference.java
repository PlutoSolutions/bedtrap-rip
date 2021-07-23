/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna.ptr;

import com.sun.jna.ptr.ByReference;

public class FloatByReference
extends ByReference {
    public FloatByReference() {
        this(0.0f);
    }

    public void setValue(float f) {
        this.getPointer().setFloat(0L, f);
    }

    public float getValue() {
        return this.getPointer().getFloat(0L);
    }

    public FloatByReference(float f) {
        super(4);
        this.setValue(f);
    }
}

