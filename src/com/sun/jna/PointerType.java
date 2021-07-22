/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import com.sun.jna.NativeMapped;
import com.sun.jna.Pointer;

public abstract class PointerType
implements NativeMapped {
    private Pointer pointer;

    @Override
    public Class<?> nativeType() {
        return Pointer.class;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof PointerType) {
            Pointer pointer = ((PointerType)object).getPointer();
            if (this.pointer == null) {
                return pointer == null;
            }
            return this.pointer.equals(pointer);
        }
        return false;
    }

    public void setPointer(Pointer pointer) {
        this.pointer = pointer;
    }

    protected PointerType(Pointer pointer) {
        this.pointer = pointer;
    }

    public Pointer getPointer() {
        return this.pointer;
    }

    @Override
    public Object fromNative(Object object, FromNativeContext fromNativeContext) {
        if (object == null) {
            return null;
        }
        try {
            PointerType pointerType = (PointerType)this.getClass().newInstance();
            pointerType.pointer = (Pointer)object;
            return pointerType;
        }
        catch (IllegalAccessException | InstantiationException reflectiveOperationException) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Can't instantiate ").append(this.getClass())));
        }
    }

    protected PointerType() {
        this.pointer = Pointer.NULL;
    }

    public int hashCode() {
        return this.pointer != null ? this.pointer.hashCode() : 0;
    }

    @Override
    public Object toNative() {
        return this.getPointer();
    }

    public String toString() {
        return this.pointer == null ? "NULL" : String.valueOf(new StringBuilder().append(this.pointer.toString()).append(" (").append(super.toString()).append(")"));
    }
}

