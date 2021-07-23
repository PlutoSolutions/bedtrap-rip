/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna.ptr;

import com.sun.jna.ptr.ByReference;

public class LongByReference
extends ByReference {
    public LongByReference(long l) {
        super(8);
        this.setValue(l);
    }

    public long getValue() {
        return this.getPointer().getLong(0L);
    }

    public void setValue(long l) {
        this.getPointer().setLong(0L, l);
    }

    public LongByReference() {
        this(0L);
    }
}

