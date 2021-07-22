/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import com.sun.jna.NativeMapped;

public abstract class IntegerType
extends Number
implements NativeMapped {
    private /* synthetic */ int size;
    private /* synthetic */ boolean unsigned;
    private /* synthetic */ long value;
    private /* synthetic */ Number number;
    private static final /* synthetic */ long serialVersionUID = 1L;

    @Override
    public int intValue() {
        IntegerType llIIIllIIllIll;
        return (int)llIIIllIIllIll.value;
    }

    public void setValue(long llIIIllIllIlll) {
        IntegerType llIIIllIlllIII;
        long llIIIllIllIllI = llIIIllIllIlll;
        llIIIllIlllIII.value = llIIIllIllIlll;
        switch (llIIIllIlllIII.size) {
            case 1: {
                if (llIIIllIlllIII.unsigned) {
                    llIIIllIlllIII.value = llIIIllIllIlll & 0xFFL;
                }
                llIIIllIllIllI = (byte)llIIIllIllIlll;
                llIIIllIlllIII.number = (byte)llIIIllIllIlll;
                break;
            }
            case 2: {
                if (llIIIllIlllIII.unsigned) {
                    llIIIllIlllIII.value = llIIIllIllIlll & 0xFFFFL;
                }
                llIIIllIllIllI = (short)llIIIllIllIlll;
                llIIIllIlllIII.number = (short)llIIIllIllIlll;
                break;
            }
            case 4: {
                if (llIIIllIlllIII.unsigned) {
                    llIIIllIlllIII.value = llIIIllIllIlll & 0xFFFFFFFFL;
                }
                llIIIllIllIllI = (int)llIIIllIllIlll;
                llIIIllIlllIII.number = (int)llIIIllIllIlll;
                break;
            }
            case 8: {
                llIIIllIlllIII.number = llIIIllIllIlll;
                break;
            }
            default: {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported size: ").append(llIIIllIlllIII.size)));
            }
        }
        if (llIIIllIlllIII.size < 8) {
            long llIIIllIlllIIl = (1L << llIIIllIlllIII.size * 8) - 1L ^ 0xFFFFFFFFFFFFFFFFL;
            if (llIIIllIllIlll < 0L && llIIIllIllIllI != llIIIllIllIlll || llIIIllIllIlll >= 0L && (llIIIllIlllIIl & llIIIllIllIlll) != 0L) {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Argument value 0x").append(Long.toHexString(llIIIllIllIlll)).append(" exceeds native capacity (").append(llIIIllIlllIII.size).append(" bytes) mask=0x").append(Long.toHexString(llIIIllIlllIIl))));
            }
        }
    }

    public IntegerType(int llIIIlllIIlllI, long llIIIlllIIllIl) {
        llIIIlllIIllII(llIIIlllIIlllI, llIIIlllIIllIl, false);
        IntegerType llIIIlllIIllII;
    }

    public boolean equals(Object llIIIllIIIlIll) {
        IntegerType llIIIllIIIllII;
        return llIIIllIIIlIll instanceof IntegerType && llIIIllIIIllII.number.equals(((IntegerType)llIIIllIIIlIll).number);
    }

    public IntegerType(int llIIIlllIlIlII, boolean llIIIlllIlIIll) {
        llIIIlllIllIII(llIIIlllIlIlII, 0L, llIIIlllIlIIll);
        IntegerType llIIIlllIllIII;
    }

    public static final int compare(long llIIIlIlllIllI, long llIIIlIlllIlIl) {
        if (llIIIlIlllIllI == llIIIlIlllIlIl) {
            return 0;
        }
        if (llIIIlIlllIllI < llIIIlIlllIlIl) {
            return -1;
        }
        return 1;
    }

    @Override
    public Class<?> nativeType() {
        IntegerType llIIIllIIllllI;
        return llIIIllIIllllI.number.getClass();
    }

    public IntegerType(int llIIIlllIlllII) {
        llIIIlllIlllIl(llIIIlllIlllII, 0L, false);
        IntegerType llIIIlllIlllIl;
    }

    public static int compare(IntegerType llIIIlIlllllII, long llIIIlIllllIll) {
        if (llIIIlIlllllII == null) {
            return 1;
        }
        return IntegerType.compare(llIIIlIlllllII.longValue(), llIIIlIllllIll);
    }

    @Override
    public float floatValue() {
        IntegerType llIIIllIIlIlIl;
        return llIIIllIIlIlIl.number.floatValue();
    }

    public IntegerType(int llIIIlllIIIlII, long llIIIlllIIIIll, boolean llIIIlllIIIIlI) {
        IntegerType llIIIlllIIIIIl;
        llIIIlllIIIIIl.size = llIIIlllIIIlII;
        llIIIlllIIIIIl.unsigned = llIIIlllIIIIlI;
        llIIIlllIIIIIl.setValue(llIIIlllIIIIll);
    }

    @Override
    public long longValue() {
        IntegerType llIIIllIIllIII;
        return llIIIllIIllIII.value;
    }

    public static <T extends IntegerType> int compare(T llIIIllIIIIIII, T llIIIllIIIIIIl) {
        if (llIIIllIIIIIII == llIIIllIIIIIIl) {
            return 0;
        }
        if (llIIIllIIIIIII == null) {
            return 1;
        }
        if (llIIIllIIIIIIl == null) {
            return -1;
        }
        return IntegerType.compare(llIIIllIIIIIII.longValue(), llIIIllIIIIIIl.longValue());
    }

    @Override
    public Object toNative() {
        IntegerType llIIIllIlIllll;
        return llIIIllIlIllll.number;
    }

    public String toString() {
        IntegerType llIIIllIIIlIII;
        return llIIIllIIIlIII.number.toString();
    }

    @Override
    public Object fromNative(Object llIIIllIlIIIlI, FromNativeContext llIIIllIlIIlIl) {
        IntegerType llIIIllIlIIIll;
        long llIIIllIlIIlII = llIIIllIlIIIlI == null ? 0L : ((Number)llIIIllIlIIIlI).longValue();
        try {
            IntegerType llIIIllIlIlIlI = (IntegerType)llIIIllIlIIIll.getClass().newInstance();
            llIIIllIlIlIlI.setValue(llIIIllIlIIlII);
            return llIIIllIlIlIlI;
        }
        catch (InstantiationException llIIIllIlIlIIl) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Can't instantiate ").append(llIIIllIlIIIll.getClass())));
        }
        catch (IllegalAccessException llIIIllIlIlIII) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Not allowed to instantiate ").append(llIIIllIlIIIll.getClass())));
        }
    }

    @Override
    public double doubleValue() {
        IntegerType llIIIllIIlIIlI;
        return llIIIllIIlIIlI.number.doubleValue();
    }

    public int hashCode() {
        IntegerType llIIIllIIIIllI;
        return llIIIllIIIIllI.number.hashCode();
    }
}

