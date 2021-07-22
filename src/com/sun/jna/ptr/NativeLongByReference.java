/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.ptr;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.ByReference;

public class NativeLongByReference
extends ByReference {
    public NativeLong getValue() {
        NativeLongByReference lIIlIlllIIlllll;
        return lIIlIlllIIlllll.getPointer().getNativeLong(0L);
    }

    public NativeLongByReference(NativeLong lIIlIlllIlIlIIl) {
        super(NativeLong.SIZE);
        NativeLongByReference lIIlIlllIlIlIII;
        lIIlIlllIlIlIII.setValue(lIIlIlllIlIlIIl);
    }

    public NativeLongByReference() {
        lIIlIlllIlIlllI(new NativeLong(0L));
        NativeLongByReference lIIlIlllIlIlllI;
    }

    public void setValue(NativeLong lIIlIlllIlIIIll) {
        NativeLongByReference lIIlIlllIlIIIlI;
        lIIlIlllIlIIIlI.getPointer().setNativeLong(0L, lIIlIlllIlIIIll);
    }
}

