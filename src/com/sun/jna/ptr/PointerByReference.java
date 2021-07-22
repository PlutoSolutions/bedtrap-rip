/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.ptr;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByReference;

public class PointerByReference
extends ByReference {
    public PointerByReference() {
        lllllllllllllllllIllIllIIIIIIIll(null);
        PointerByReference lllllllllllllllllIllIllIIIIIIIll;
    }

    public Pointer getValue() {
        PointerByReference lllllllllllllllllIllIlIlllllIlII;
        return lllllllllllllllllIllIlIlllllIlII.getPointer().getPointer(0L);
    }

    public void setValue(Pointer lllllllllllllllllIllIlIlllllIlll) {
        PointerByReference lllllllllllllllllIllIlIllllllIlI;
        lllllllllllllllllIllIlIllllllIlI.getPointer().setPointer(0L, lllllllllllllllllIllIlIlllllIlll);
    }

    public PointerByReference(Pointer lllllllllllllllllIllIlIlllllllIl) {
        super(Pointer.SIZE);
        PointerByReference lllllllllllllllllIllIllIIIIIIIII;
        lllllllllllllllllIllIllIIIIIIIII.setValue(lllllllllllllllllIllIlIlllllllIl);
    }
}

