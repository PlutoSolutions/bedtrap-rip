/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.IntegerType;
import com.sun.jna.Native;

public class NativeLong
extends IntegerType {
    public static final int SIZE = Native.LONG_SIZE;
    private static final long serialVersionUID = 1L;

    public NativeLong() {
        this(0L);
    }

    public NativeLong(long l) {
        this(l, false);
    }

    public NativeLong(long l, boolean bl) {
        super(SIZE, l, bl);
    }
}

