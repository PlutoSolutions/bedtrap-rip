/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.ptr;

import com.sun.jna.ptr.ByReference;

public class IntByReference
extends ByReference {
    public void setValue(int llllllllllllllllIllIIIlIlllIIIIl) {
        IntByReference llllllllllllllllIllIIIlIlllIIIII;
        llllllllllllllllIllIIIlIlllIIIII.getPointer().setInt(0L, llllllllllllllllIllIIIlIlllIIIIl);
    }

    public IntByReference(int llllllllllllllllIllIIIlIlllIIlIl) {
        super(4);
        IntByReference llllllllllllllllIllIIIlIlllIlIII;
        llllllllllllllllIllIIIlIlllIlIII.setValue(llllllllllllllllIllIIIlIlllIIlIl);
    }

    public IntByReference() {
        llllllllllllllllIllIIIlIlllIllII(0);
        IntByReference llllllllllllllllIllIIIlIlllIllII;
    }

    public int getValue() {
        IntByReference llllllllllllllllIllIIIlIllIlllIl;
        return llllllllllllllllIllIIIlIllIlllIl.getPointer().getInt(0L);
    }
}

