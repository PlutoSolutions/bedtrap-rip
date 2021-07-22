/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.ptr;

import com.sun.jna.ptr.ByReference;

public class DoubleByReference
extends ByReference {
    public void setValue(double llllllllllllllllllIIlIIIIIIIIIll) {
        DoubleByReference llllllllllllllllllIIlIIIIIIIIllI;
        llllllllllllllllllIIlIIIIIIIIllI.getPointer().setDouble(0L, llllllllllllllllllIIlIIIIIIIIIll);
    }

    public DoubleByReference() {
        llllllllllllllllllIIlIIIIIIlIIII(0.0);
        DoubleByReference llllllllllllllllllIIlIIIIIIlIIII;
    }

    public double getValue() {
        DoubleByReference llllllllllllllllllIIlIIIIIIIIIIl;
        return llllllllllllllllllIIlIIIIIIIIIIl.getPointer().getDouble(0L);
    }

    public DoubleByReference(double llllllllllllllllllIIlIIIIIIIlIIl) {
        super(8);
        DoubleByReference llllllllllllllllllIIlIIIIIIIlIlI;
        llllllllllllllllllIIlIIIIIIIlIlI.setValue(llllllllllllllllllIIlIIIIIIIlIIl);
    }
}

