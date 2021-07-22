/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.ptr;

import com.sun.jna.ptr.ByReference;

public class LongByReference
extends ByReference {
    public LongByReference(long llllllllllllllllIlllIIlIlIIIIIll) {
        super(8);
        LongByReference llllllllllllllllIlllIIlIlIIIIlII;
        llllllllllllllllIlllIIlIlIIIIlII.setValue(llllllllllllllllIlllIIlIlIIIIIll);
    }

    public long getValue() {
        LongByReference llllllllllllllllIlllIIlIIllllIIl;
        return llllllllllllllllIlllIIlIIllllIIl.getPointer().getLong(0L);
    }

    public void setValue(long llllllllllllllllIlllIIlIIlllllIl) {
        LongByReference llllllllllllllllIlllIIlIIlllllII;
        llllllllllllllllIlllIIlIIlllllII.getPointer().setLong(0L, llllllllllllllllIlllIIlIIlllllIl);
    }

    public LongByReference() {
        llllllllllllllllIlllIIlIlIIIlIII(0L);
        LongByReference llllllllllllllllIlllIIlIlIIIlIII;
    }
}

