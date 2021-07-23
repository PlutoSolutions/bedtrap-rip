/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna.ptr;

import com.sun.jna.ptr.ByReference;

public class ShortByReference
extends ByReference {
    public ShortByReference(short s) {
        super(2);
        this.setValue(s);
    }

    public ShortByReference() {
        this(0);
    }

    public void setValue(short s) {
        this.getPointer().setShort(0L, s);
    }

    public short getValue() {
        return this.getPointer().getShort(0L);
    }
}

