/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna.ptr;

import com.sun.jna.ptr.ByReference;

public class ByteByReference
extends ByReference {
    public void setValue(byte by) {
        this.getPointer().setByte(0L, by);
    }

    public ByteByReference() {
        this(0);
    }

    public byte getValue() {
        return this.getPointer().getByte(0L);
    }

    public ByteByReference(byte by) {
        super(1);
        this.setValue(by);
    }
}

