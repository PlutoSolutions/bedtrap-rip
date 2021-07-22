/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.ptr;

import com.sun.jna.ptr.ByReference;

public class FloatByReference
extends ByReference {
    public FloatByReference() {
        lllllIlIlIIlIll(0.0f);
        FloatByReference lllllIlIlIIlIll;
    }

    public void setValue(float lllllIlIlIIIIII) {
        FloatByReference lllllIlIlIIIIIl;
        lllllIlIlIIIIIl.getPointer().setFloat(0L, lllllIlIlIIIIII);
    }

    public float getValue() {
        FloatByReference lllllIlIIllllII;
        return lllllIlIIllllII.getPointer().getFloat(0L);
    }

    public FloatByReference(float lllllIlIlIIIllI) {
        super(4);
        FloatByReference lllllIlIlIIIlIl;
        lllllIlIlIIIlIl.setValue(lllllIlIlIIIllI);
    }
}

