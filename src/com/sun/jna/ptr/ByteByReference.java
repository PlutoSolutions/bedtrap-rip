/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.ptr;

import com.sun.jna.ptr.ByReference;

public class ByteByReference
extends ByReference {
    public void setValue(byte lllllllllllllllllIllIIIIIllIllll) {
        ByteByReference lllllllllllllllllIllIIIIIlllIIII;
        lllllllllllllllllIllIIIIIlllIIII.getPointer().setByte(0L, lllllllllllllllllIllIIIIIllIllll);
    }

    public ByteByReference() {
        lllllllllllllllllIllIIIIIllllIll(0);
        ByteByReference lllllllllllllllllIllIIIIIllllIll;
    }

    public byte getValue() {
        ByteByReference lllllllllllllllllIllIIIIIllIllIl;
        return lllllllllllllllllIllIIIIIllIllIl.getPointer().getByte(0L);
    }

    public ByteByReference(byte lllllllllllllllllIllIIIIIlllIlIl) {
        super(1);
        ByteByReference lllllllllllllllllIllIIIIIlllIllI;
        lllllllllllllllllIllIIIIIlllIllI.setValue(lllllllllllllllllIllIIIIIlllIlIl);
    }
}

