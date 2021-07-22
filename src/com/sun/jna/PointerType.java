/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import com.sun.jna.NativeMapped;
import com.sun.jna.Pointer;

public abstract class PointerType
implements NativeMapped {
    private /* synthetic */ Pointer pointer;

    @Override
    public Class<?> nativeType() {
        return Pointer.class;
    }

    public boolean equals(Object lllllllllllllllllllIlllIIIIIIIlI) {
        PointerType lllllllllllllllllllIlllIIIIIIlIl;
        if (lllllllllllllllllllIlllIIIIIIIlI == lllllllllllllllllllIlllIIIIIIlIl) {
            return true;
        }
        if (lllllllllllllllllllIlllIIIIIIIlI instanceof PointerType) {
            Pointer lllllllllllllllllllIlllIIIIIIllI = ((PointerType)lllllllllllllllllllIlllIIIIIIIlI).getPointer();
            if (lllllllllllllllllllIlllIIIIIIlIl.pointer == null) {
                return lllllllllllllllllllIlllIIIIIIllI == null;
            }
            return lllllllllllllllllllIlllIIIIIIlIl.pointer.equals(lllllllllllllllllllIlllIIIIIIllI);
        }
        return false;
    }

    public void setPointer(Pointer lllllllllllllllllllIlllIIIIllIIl) {
        lllllllllllllllllllIlllIIIIllIlI.pointer = lllllllllllllllllllIlllIIIIllIIl;
    }

    protected PointerType(Pointer lllllllllllllllllllIlllIIIlIIllI) {
        PointerType lllllllllllllllllllIlllIIIlIIlll;
        lllllllllllllllllllIlllIIIlIIlll.pointer = lllllllllllllllllllIlllIIIlIIllI;
    }

    public Pointer getPointer() {
        PointerType lllllllllllllllllllIlllIIIlIIIII;
        return lllllllllllllllllllIlllIIIlIIIII.pointer;
    }

    @Override
    public Object fromNative(Object lllllllllllllllllllIlllIIIIlIIIl, FromNativeContext lllllllllllllllllllIlllIIIIlIIII) {
        PointerType lllllllllllllllllllIlllIIIIlIIlI;
        if (lllllllllllllllllllIlllIIIIlIIIl == null) {
            return null;
        }
        try {
            PointerType lllllllllllllllllllIlllIIIIlIlIl = (PointerType)lllllllllllllllllllIlllIIIIlIIlI.getClass().newInstance();
            lllllllllllllllllllIlllIIIIlIlIl.pointer = (Pointer)lllllllllllllllllllIlllIIIIlIIIl;
            return lllllllllllllllllllIlllIIIIlIlIl;
        }
        catch (InstantiationException lllllllllllllllllllIlllIIIIlIlII) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Can't instantiate ").append(lllllllllllllllllllIlllIIIIlIIlI.getClass())));
        }
        catch (IllegalAccessException lllllllllllllllllllIlllIIIIlIIll) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Not allowed to instantiate ").append(lllllllllllllllllllIlllIIIIlIIlI.getClass())));
        }
    }

    protected PointerType() {
        PointerType lllllllllllllllllllIlllIIIlIllII;
        lllllllllllllllllllIlllIIIlIllII.pointer = Pointer.NULL;
    }

    public int hashCode() {
        PointerType lllllllllllllllllllIlllIIIIIlIll;
        return lllllllllllllllllllIlllIIIIIlIll.pointer != null ? lllllllllllllllllllIlllIIIIIlIll.pointer.hashCode() : 0;
    }

    @Override
    public Object toNative() {
        PointerType lllllllllllllllllllIlllIIIlIIIll;
        return lllllllllllllllllllIlllIIIlIIIll.getPointer();
    }

    public String toString() {
        PointerType lllllllllllllllllllIllIllllllllI;
        return lllllllllllllllllllIllIllllllllI.pointer == null ? "NULL" : String.valueOf(new StringBuilder().append(lllllllllllllllllllIllIllllllllI.pointer.toString()).append(" (").append(super.toString()).append(")"));
    }
}

