/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import com.sun.jna.NativeMapped;

public abstract class IntegerType
extends Number
implements NativeMapped {
    private int size;
    private boolean unsigned;
    private long value;
    private Number number;
    private static final long serialVersionUID = 1L;

    @Override
    public int intValue() {
        return (int)this.value;
    }

    public void setValue(long l) {
        long l2 = l;
        this.value = l;
        switch (this.size) {
            case 1: {
                if (this.unsigned) {
                    this.value = l & 0xFFL;
                }
                l2 = (byte)l;
                this.number = (byte)l;
                break;
            }
            case 2: {
                if (this.unsigned) {
                    this.value = l & 0xFFFFL;
                }
                l2 = (short)l;
                this.number = (short)l;
                break;
            }
            case 4: {
                if (this.unsigned) {
                    this.value = l & 0xFFFFFFFFL;
                }
                l2 = (int)l;
                this.number = (int)l;
                break;
            }
            case 8: {
                this.number = l;
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported size: ").append(this.size)));
            }
        }
        if (this.size < 8) {
            long l3 = (1L << this.size * 8) - 1L ^ 0xFFFFFFFFFFFFFFFFL;
            if (l < 0L && l2 != l || l >= 0L && (l3 & l) != 0L) {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Argument value 0x").append(Long.toHexString(l)).append(" exceeds native capacity (").append(this.size).append(" bytes) mask=0x").append(Long.toHexString(l3))));
            }
        }
    }

    public IntegerType(int n, long l) {
        this(n, l, false);
    }

    public boolean equals(Object object) {
        return object instanceof IntegerType && this.number.equals(((IntegerType)object).number);
    }

    public IntegerType(int n, boolean bl) {
        this(n, 0L, bl);
    }

    public static final int compare(long l, long l2) {
        if (l == l2) {
            return 0;
        }
        if (l < l2) {
            return -1;
        }
        return 1;
    }

    @Override
    public Class<?> nativeType() {
        return this.number.getClass();
    }

    public IntegerType(int n) {
        this(n, 0L, false);
    }

    public static int compare(IntegerType integerType, long l) {
        if (integerType == null) {
            return 1;
        }
        return IntegerType.compare(integerType.longValue(), l);
    }

    @Override
    public float floatValue() {
        return this.number.floatValue();
    }

    public IntegerType(int n, long l, boolean bl) {
        this.size = n;
        this.unsigned = bl;
        this.setValue(l);
    }

    @Override
    public long longValue() {
        return this.value;
    }

    public static <T extends IntegerType> int compare(T t, T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return 1;
        }
        if (t2 == null) {
            return -1;
        }
        return IntegerType.compare(t.longValue(), t2.longValue());
    }

    @Override
    public Object toNative() {
        return this.number;
    }

    public String toString() {
        return this.number.toString();
    }

    @Override
    public Object fromNative(Object object, FromNativeContext fromNativeContext) {
        long l = object == null ? 0L : ((Number)object).longValue();
        try {
            IntegerType integerType = (IntegerType)this.getClass().newInstance();
            integerType.setValue(l);
            return integerType;
        }
        catch (IllegalAccessException | InstantiationException reflectiveOperationException) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Can't instantiate ").append(this.getClass())));
        }
    }

    @Override
    public double doubleValue() {
        return this.number.doubleValue();
    }

    public int hashCode() {
        return this.number.hashCode();
    }
}

