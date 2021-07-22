/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.ptr;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.ByReference;

public class NativeLongByReference
extends ByReference {
    public NativeLong getValue() {
        return this.getPointer().getNativeLong(0L);
    }

    public NativeLongByReference(NativeLong nativeLong) {
        super(NativeLong.SIZE);
        this.setValue(nativeLong);
    }

    public NativeLongByReference() {
        this(new NativeLong(0L));
    }

    public void setValue(NativeLong nativeLong) {
        this.getPointer().setNativeLong(0L, nativeLong);
    }
}

