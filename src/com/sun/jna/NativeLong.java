/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.IntegerType;
import com.sun.jna.Native;

public class NativeLong
extends IntegerType {
    public static final /* synthetic */ int SIZE;
    private static final /* synthetic */ long serialVersionUID = 1L;

    public NativeLong() {
        llllllllllllllllIlllllIlIIIIllIl(0L);
        NativeLong llllllllllllllllIlllllIlIIIIllIl;
    }

    public NativeLong(long llllllllllllllllIlllllIlIIIIIlll) {
        llllllllllllllllIlllllIlIIIIlIII(llllllllllllllllIlllllIlIIIIIlll, false);
        NativeLong llllllllllllllllIlllllIlIIIIlIII;
    }

    public NativeLong(long llllllllllllllllIlllllIlIIIIIIlI, boolean llllllllllllllllIlllllIIlllllllI) {
        super(SIZE, llllllllllllllllIlllllIlIIIIIIlI, llllllllllllllllIlllllIIlllllllI);
        NativeLong llllllllllllllllIlllllIlIIIIIIII;
    }

    static {
        SIZE = Native.LONG_SIZE;
    }
}

