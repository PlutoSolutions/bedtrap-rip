/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.ptr;

import com.sun.jna.ptr.ByReference;

public class ShortByReference
extends ByReference {
    public ShortByReference(short lIlIlllIllIllIl) {
        super(2);
        ShortByReference lIlIlllIlllIIII;
        lIlIlllIlllIIII.setValue(lIlIlllIllIllIl);
    }

    public ShortByReference() {
        lIlIlllIlllIIll(0);
        ShortByReference lIlIlllIlllIIll;
    }

    public void setValue(short lIlIlllIllIlIIl) {
        ShortByReference lIlIlllIllIlIlI;
        lIlIlllIllIlIlI.getPointer().setShort(0L, lIlIlllIllIlIIl);
    }

    public short getValue() {
        ShortByReference lIlIlllIllIIlII;
        return lIlIlllIllIIlII.getPointer().getShort(0L);
    }
}

