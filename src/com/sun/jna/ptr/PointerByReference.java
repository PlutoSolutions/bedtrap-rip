/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.ptr;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByReference;

public class PointerByReference
extends ByReference {
    public PointerByReference() {
        this(null);
    }

    public Pointer getValue() {
        return this.getPointer().getPointer(0L);
    }

    public void setValue(Pointer pointer) {
        this.getPointer().setPointer(0L, pointer);
    }

    public PointerByReference(Pointer pointer) {
        super(Pointer.SIZE);
        this.setValue(pointer);
    }
}

