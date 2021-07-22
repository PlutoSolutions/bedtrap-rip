/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Callback;
import com.sun.jna.CallbackReference;
import com.sun.jna.FromNativeContext;
import com.sun.jna.Function;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.NativeMapped;
import com.sun.jna.NativeMappedConverter;
import com.sun.jna.Platform;
import com.sun.jna.Structure;
import com.sun.jna.ToNativeContext;
import com.sun.jna.WString;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class Pointer {
    public static final /* synthetic */ int SIZE;
    public static final /* synthetic */ Pointer NULL;
    protected /* synthetic */ long peer;

    public void write(long llllllllllllllllllIIIIIllIIIlIIl, Pointer[] llllllllllllllllllIIIIIllIIIlIII, int llllllllllllllllllIIIIIllIIIllII, int llllllllllllllllllIIIIIllIIIIllI) {
        for (int llllllllllllllllllIIIIIllIIlIIII = 0; llllllllllllllllllIIIIIllIIlIIII < llllllllllllllllllIIIIIllIIIIllI; ++llllllllllllllllllIIIIIllIIlIIII) {
            Pointer llllllllllllllllllIIIIIllIIIllll;
            llllllllllllllllllIIIIIllIIIllll.setPointer(llllllllllllllllllIIIIIllIIIlIIl + (long)(llllllllllllllllllIIIIIllIIlIIII * SIZE), llllllllllllllllllIIIIIllIIIlIII[llllllllllllllllllIIIIIllIIIllII + llllllllllllllllllIIIIIllIIlIIII]);
        }
    }

    public void setNativeLong(long lllllllllllllllllIlllllllIIllIII, NativeLong lllllllllllllllllIlllllllIIlIlII) {
        Pointer lllllllllllllllllIlllllllIIlIllI;
        if (NativeLong.SIZE == 8) {
            lllllllllllllllllIlllllllIIlIllI.setLong(lllllllllllllllllIlllllllIIllIII, lllllllllllllllllIlllllllIIlIlII.longValue());
        } else {
            lllllllllllllllllIlllllllIIlIllI.setInt(lllllllllllllllllIlllllllIIllIII, lllllllllllllllllIlllllllIIlIlII.intValue());
        }
    }

    public String[] getWideStringArray(long llllllllllllllllllIIIIIIIlIIlIlI) {
        Pointer llllllllllllllllllIIIIIIIlIIlIIl;
        return llllllllllllllllllIIIIIIIlIIlIIl.getWideStringArray(llllllllllllllllllIIIIIIIlIIlIlI, -1);
    }

    public Pointer[] getPointerArray(long llllllllllllllllllIIIIIIIllllllI) {
        Pointer llllllllllllllllllIIIIIIlIIIIlII;
        ArrayList<Pointer> llllllllllllllllllIIIIIIlIIIIIlI = new ArrayList<Pointer>();
        int llllllllllllllllllIIIIIIlIIIIIIl = 0;
        Pointer llllllllllllllllllIIIIIIlIIIIIII = llllllllllllllllllIIIIIIlIIIIlII.getPointer(llllllllllllllllllIIIIIIIllllllI);
        while (llllllllllllllllllIIIIIIlIIIIIII != null) {
            llllllllllllllllllIIIIIIlIIIIIlI.add(llllllllllllllllllIIIIIIlIIIIIII);
            llllllllllllllllllIIIIIIlIIIIIII = llllllllllllllllllIIIIIIlIIIIlII.getPointer(llllllllllllllllllIIIIIIIllllllI + (long)(llllllllllllllllllIIIIIIlIIIIIIl += SIZE));
        }
        return llllllllllllllllllIIIIIIlIIIIIlI.toArray(new Pointer[llllllllllllllllllIIIIIIlIIIIIlI.size()]);
    }

    public void read(long llllllllllllllllllIIIIlIIIlIIIII, double[] llllllllllllllllllIIIIlIIIIllIlI, int llllllllllllllllllIIIIlIIIIllIIl, int llllllllllllllllllIIIIlIIIIllIII) {
        Pointer llllllllllllllllllIIIIlIIIIlllII;
        Native.read(llllllllllllllllllIIIIlIIIIlllII, llllllllllllllllllIIIIlIIIIlllII.peer, llllllllllllllllllIIIIlIIIlIIIII, llllllllllllllllllIIIIlIIIIllIlI, llllllllllllllllllIIIIlIIIIllIIl, llllllllllllllllllIIIIlIIIIllIII);
    }

    public String getWideString(long llllllllllllllllllIIIIIIlllIllll) {
        Pointer llllllllllllllllllIIIIIIlllIlllI;
        return Native.getWideString(llllllllllllllllllIIIIIIlllIlllI, llllllllllllllllllIIIIIIlllIlllI.peer, llllllllllllllllllIIIIIIlllIllll);
    }

    public void read(long llllllllllllllllllIIIIlIIIIIlIll, Pointer[] llllllllllllllllllIIIIlIIIIIlIlI, int llllllllllllllllllIIIIlIIIIIIlII, int llllllllllllllllllIIIIlIIIIIlIII) {
        for (int llllllllllllllllllIIIIlIIIIIllIl = 0; llllllllllllllllllIIIIlIIIIIllIl < llllllllllllllllllIIIIlIIIIIlIII; ++llllllllllllllllllIIIIlIIIIIllIl) {
            Pointer llllllllllllllllllIIIIlIIIIIIlll;
            Pointer llllllllllllllllllIIIIlIIIIIllll = llllllllllllllllllIIIIlIIIIIIlll.getPointer(llllllllllllllllllIIIIlIIIIIlIll + (long)(llllllllllllllllllIIIIlIIIIIllIl * SIZE));
            Pointer llllllllllllllllllIIIIlIIIIIlllI = llllllllllllllllllIIIIlIIIIIlIlI[llllllllllllllllllIIIIlIIIIIllIl + llllllllllllllllllIIIIlIIIIIIlII];
            if (llllllllllllllllllIIIIlIIIIIlllI != null && llllllllllllllllllIIIIlIIIIIllll != null && llllllllllllllllllIIIIlIIIIIllll.peer == llllllllllllllllllIIIIlIIIIIlllI.peer) continue;
            llllllllllllllllllIIIIlIIIIIlIlI[llllllllllllllllllIIIIlIIIIIllIl + llllllllllllllllllIIIIlIIIIIIlII] = llllllllllllllllllIIIIlIIIIIllll;
        }
    }

    public void setInt(long lllllllllllllllllIlllllllIlIlIlI, int lllllllllllllllllIlllllllIlIlIIl) {
        Pointer lllllllllllllllllIlllllllIlIlIll;
        Native.setInt(lllllllllllllllllIlllllllIlIlIll, lllllllllllllllllIlllllllIlIlIll.peer, lllllllllllllllllIlllllllIlIlIlI, lllllllllllllllllIlllllllIlIlIIl);
    }

    @Deprecated
    public String getString(long llllllllllllllllllIIIIIIllllIlII, boolean llllllllllllllllllIIIIIIllllIIll) {
        Pointer llllllllllllllllllIIIIIIlllllIII;
        return llllllllllllllllllIIIIIIllllIIll ? llllllllllllllllllIIIIIIlllllIII.getWideString(llllllllllllllllllIIIIIIllllIlII) : llllllllllllllllllIIIIIIlllllIII.getString(llllllllllllllllllIIIIIIllllIlII);
    }

    public void write(long llllllllllllllllllIIIIIllIIllIlI, double[] llllllllllllllllllIIIIIllIIllIIl, int llllllllllllllllllIIIIIllIIllIII, int llllllllllllllllllIIIIIllIIlIlll) {
        Pointer llllllllllllllllllIIIIIllIIllIll;
        Native.write(llllllllllllllllllIIIIIllIIllIll, llllllllllllllllllIIIIIllIIllIll.peer, llllllllllllllllllIIIIIllIIllIlI, llllllllllllllllllIIIIIllIIllIIl, llllllllllllllllllIIIIIllIIllIII, llllllllllllllllllIIIIIllIIlIlll);
    }

    public void setFloat(long lllllllllllllllllIlllllllIIIllII, float lllllllllllllllllIlllllllIIIlIll) {
        Pointer lllllllllllllllllIlllllllIIIllIl;
        Native.setFloat(lllllllllllllllllIlllllllIIIllIl, lllllllllllllllllIlllllllIIIllIl.peer, lllllllllllllllllIlllllllIIIllII, lllllllllllllllllIlllllllIIIlIll);
    }

    public float getFloat(long llllllllllllllllllIIIIIlIIIlIIIl) {
        Pointer llllllllllllllllllIIIIIlIIIlIIlI;
        return Native.getFloat(llllllllllllllllllIIIIIlIIIlIIlI, llllllllllllllllllIIIIIlIIIlIIlI.peer, llllllllllllllllllIIIIIlIIIlIIIl);
    }

    public long indexOf(long llllllllllllllllllIIIIlIlIIIIIlI, byte llllllllllllllllllIIIIlIlIIIIIIl) {
        Pointer llllllllllllllllllIIIIlIlIIIIllI;
        return Native.indexOf(llllllllllllllllllIIIIlIlIIIIllI, llllllllllllllllllIIIIlIlIIIIllI.peer, llllllllllllllllllIIIIlIlIIIIIlI, llllllllllllllllllIIIIlIlIIIIIIl);
    }

    public void write(long llllllllllllllllllIIIIIlllIlIllI, char[] llllllllllllllllllIIIIIlllIlIlIl, int llllllllllllllllllIIIIIlllIlIlII, int llllllllllllllllllIIIIIlllIlIIll) {
        Pointer llllllllllllllllllIIIIIlllIlIlll;
        Native.write(llllllllllllllllllIIIIIlllIlIlll, llllllllllllllllllIIIIIlllIlIlll.peer, llllllllllllllllllIIIIIlllIlIllI, llllllllllllllllllIIIIIlllIlIlIl, llllllllllllllllllIIIIIlllIlIlII, llllllllllllllllllIIIIIlllIlIIll);
    }

    public String getString(long llllllllllllllllllIIIIIIlllIIIlI, String llllllllllllllllllIIIIIIlllIIIIl) {
        Pointer llllllllllllllllllIIIIIIlllIIIll;
        return Native.getString(llllllllllllllllllIIIIIIlllIIIll, llllllllllllllllllIIIIIIlllIIIlI, llllllllllllllllllIIIIIIlllIIIIl);
    }

    public void setChar(long lllllllllllllllllIlllllllIllIIll, char lllllllllllllllllIlllllllIllIIlI) {
        Pointer lllllllllllllllllIlllllllIllIlII;
        Native.setChar(lllllllllllllllllIlllllllIllIlII, lllllllllllllllllIlllllllIllIlII.peer, lllllllllllllllllIlllllllIllIIll, lllllllllllllllllIlllllllIllIIlI);
    }

    Pointer() {
        Pointer llllllllllllllllllIIIIlIlIlIllII;
    }

    public void write(long llllllllllllllllllIIIIIllIlllIII, long[] llllllllllllllllllIIIIIllIllllII, int llllllllllllllllllIIIIIllIlllIll, int llllllllllllllllllIIIIIllIlllIlI) {
        Pointer llllllllllllllllllIIIIIllIlllIIl;
        Native.write(llllllllllllllllllIIIIIllIlllIIl, llllllllllllllllllIIIIIllIlllIIl.peer, llllllllllllllllllIIIIIllIlllIII, llllllllllllllllllIIIIIllIllllII, llllllllllllllllllIIIIIllIlllIll, llllllllllllllllllIIIIIllIlllIlI);
    }

    public boolean equals(Object llllllllllllllllllIIIIlIlIIIllll) {
        Pointer llllllllllllllllllIIIIlIlIIIlllI;
        if (llllllllllllllllllIIIIlIlIIIllll == llllllllllllllllllIIIIlIlIIIlllI) {
            return true;
        }
        if (llllllllllllllllllIIIIlIlIIIllll == null) {
            return false;
        }
        return llllllllllllllllllIIIIlIlIIIllll instanceof Pointer && ((Pointer)llllllllllllllllllIIIIlIlIIIllll).peer == llllllllllllllllllIIIIlIlIIIlllI.peer;
    }

    public Pointer(long llllllllllllllllllIIIIlIlIlIIllI) {
        Pointer llllllllllllllllllIIIIlIlIlIIlll;
        llllllllllllllllllIIIIlIlIlIIlll.peer = llllllllllllllllllIIIIlIlIlIIllI;
    }

    static {
        SIZE = Native.POINTER_SIZE;
        if (SIZE == 0) {
            throw new Error("Native library not initialized");
        }
        NULL = null;
    }

    public void setString(long lllllllllllllllllIllllllIlIIIllI, String lllllllllllllllllIllllllIlIIlIlI, String lllllllllllllllllIllllllIlIIIlII) {
        Pointer lllllllllllllllllIllllllIlIIIlll;
        byte[] lllllllllllllllllIllllllIlIIlIII = Native.getBytes(lllllllllllllllllIllllllIlIIlIlI, lllllllllllllllllIllllllIlIIIlII);
        lllllllllllllllllIllllllIlIIIlll.write(lllllllllllllllllIllllllIlIIIllI, lllllllllllllllllIllllllIlIIlIII, 0, lllllllllllllllllIllllllIlIIlIII.length);
        lllllllllllllllllIllllllIlIIIlll.setByte(lllllllllllllllllIllllllIlIIIllI + (long)lllllllllllllllllIllllllIlIIlIII.length, (byte)0);
    }

    public void setString(long lllllllllllllllllIllllllIlIlllII, WString lllllllllllllllllIllllllIlIllIll) {
        Pointer lllllllllllllllllIllllllIllIIIII;
        lllllllllllllllllIllllllIllIIIII.setWideString(lllllllllllllllllIllllllIlIlllII, lllllllllllllllllIllllllIlIllIll == null ? null : lllllllllllllllllIllllllIlIllIll.toString());
    }

    public void write(long llllllllllllllllllIIIIIlllllIlII, byte[] llllllllllllllllllIIIIIlllllIIll, int llllllllllllllllllIIIIIlllllIIlI, int llllllllllllllllllIIIIIlllllIIIl) {
        Pointer llllllllllllllllllIIIIIllllllIlI;
        Native.write(llllllllllllllllllIIIIIllllllIlI, llllllllllllllllllIIIIIllllllIlI.peer, llllllllllllllllllIIIIIlllllIlII, llllllllllllllllllIIIIIlllllIIll, llllllllllllllllllIIIIIlllllIIlI, llllllllllllllllllIIIIIlllllIIIl);
    }

    public String dump(long lllllllllllllllllIllllllIIlIllll, int lllllllllllllllllIllllllIIllIlIl) {
        int lllllllllllllllllIllllllIIllIlII = 4;
        String lllllllllllllllllIllllllIIllIIll = "memory dump";
        StringWriter lllllllllllllllllIllllllIIllIIlI = new StringWriter("memory dump".length() + 2 + lllllllllllllllllIllllllIIllIlIl * 2 + lllllllllllllllllIllllllIIllIlIl / 4 * 4);
        PrintWriter lllllllllllllllllIllllllIIllIIIl = new PrintWriter(lllllllllllllllllIllllllIIllIIlI);
        lllllllllllllllllIllllllIIllIIIl.println("memory dump");
        for (int lllllllllllllllllIllllllIIlllIII = 0; lllllllllllllllllIllllllIIlllIII < lllllllllllllllllIllllllIIllIlIl; ++lllllllllllllllllIllllllIIlllIII) {
            Pointer lllllllllllllllllIllllllIIllIlll;
            byte lllllllllllllllllIllllllIIlllIIl = lllllllllllllllllIllllllIIllIlll.getByte(lllllllllllllllllIllllllIIlIllll + (long)lllllllllllllllllIllllllIIlllIII);
            if (lllllllllllllllllIllllllIIlllIII % 4 == 0) {
                lllllllllllllllllIllllllIIllIIIl.print("[");
            }
            if (lllllllllllllllllIllllllIIlllIIl >= 0 && lllllllllllllllllIllllllIIlllIIl < 16) {
                lllllllllllllllllIllllllIIllIIIl.print("0");
            }
            lllllllllllllllllIllllllIIllIIIl.print(Integer.toHexString(lllllllllllllllllIllllllIIlllIIl & 0xFF));
            if (lllllllllllllllllIllllllIIlllIII % 4 != 3 || lllllllllllllllllIllllllIIlllIII >= lllllllllllllllllIllllllIIllIlIl - 1) continue;
            lllllllllllllllllIllllllIIllIIIl.println("]");
        }
        if (lllllllllllllllllIllllllIIllIIlI.getBuffer().charAt(lllllllllllllllllIllllllIIllIIlI.getBuffer().length() - 2) != ']') {
            lllllllllllllllllIllllllIIllIIIl.println("]");
        }
        return lllllllllllllllllIllllllIIllIIlI.toString();
    }

    public static long nativeValue(Pointer lllllllllllllllllIllllllIIlIIIlI) {
        return lllllllllllllllllIllllllIIlIIIlI == null ? 0L : lllllllllllllllllIllllllIIlIIIlI.peer;
    }

    public Pointer getPointer(long llllllllllllllllllIIIIIlIIIIIlll) {
        Pointer llllllllllllllllllIIIIIlIIIIlIII;
        return Native.getPointer(llllllllllllllllllIIIIIlIIIIlIII.peer + llllllllllllllllllIIIIIlIIIIIlll);
    }

    public void clear(long llllllllllllllllllIIIIlIlIIlIlIl) {
        Pointer llllllllllllllllllIIIIlIlIIlIlII;
        llllllllllllllllllIIIIlIlIIlIlII.setMemory(0L, llllllllllllllllllIIIIlIlIIlIlIl, (byte)0);
    }

    public String toString() {
        Pointer lllllllllllllllllIllllllIIlIIllI;
        return String.valueOf(new StringBuilder().append("native@0x").append(Long.toHexString(lllllllllllllllllIllllllIIlIIllI.peer)));
    }

    public String[] getStringArray(long llllllllllllllllllIIIIIIIIlIIlII, int llllllllllllllllllIIIIIIIIIlllII, String llllllllllllllllllIIIIIIIIIllIll) {
        Pointer llllllllllllllllllIIIIIIIIIllllI;
        ArrayList<String> llllllllllllllllllIIIIIIIIlIIIIl = new ArrayList<String>();
        int llllllllllllllllllIIIIIIIIIlllll = 0;
        if (llllllllllllllllllIIIIIIIIIlllII != -1) {
            Pointer llllllllllllllllllIIIIIIIIlIIlll = llllllllllllllllllIIIIIIIIIllllI.getPointer(llllllllllllllllllIIIIIIIIlIIlII + (long)llllllllllllllllllIIIIIIIIIlllll);
            int llllllllllllllllllIIIIIIIIlIlIII = 0;
            while (llllllllllllllllllIIIIIIIIlIlIII++ < llllllllllllllllllIIIIIIIIIlllII) {
                String llllllllllllllllllIIIIIIIIlIlIIl = llllllllllllllllllIIIIIIIIlIIlll == null ? null : ("--WIDE-STRING--".equals(llllllllllllllllllIIIIIIIIIllIll) ? llllllllllllllllllIIIIIIIIlIIlll.getWideString(0L) : llllllllllllllllllIIIIIIIIlIIlll.getString(0L, llllllllllllllllllIIIIIIIIIllIll));
                llllllllllllllllllIIIIIIIIlIIIIl.add(llllllllllllllllllIIIIIIIIlIlIIl);
                if (llllllllllllllllllIIIIIIIIlIlIII >= llllllllllllllllllIIIIIIIIIlllII) continue;
                llllllllllllllllllIIIIIIIIlIIlll = llllllllllllllllllIIIIIIIIIllllI.getPointer(llllllllllllllllllIIIIIIIIlIIlII + (long)(llllllllllllllllllIIIIIIIIIlllll += SIZE));
            }
        } else {
            Pointer llllllllllllllllllIIIIIIIIlIIIII;
            while ((llllllllllllllllllIIIIIIIIlIIIII = llllllllllllllllllIIIIIIIIIllllI.getPointer(llllllllllllllllllIIIIIIIIlIIlII + (long)llllllllllllllllllIIIIIIIIIlllll)) != null) {
                String llllllllllllllllllIIIIIIIIlIIllI = llllllllllllllllllIIIIIIIIlIIIII == null ? null : ("--WIDE-STRING--".equals(llllllllllllllllllIIIIIIIIIllIll) ? llllllllllllllllllIIIIIIIIlIIIII.getWideString(0L) : llllllllllllllllllIIIIIIIIlIIIII.getString(0L, llllllllllllllllllIIIIIIIIIllIll));
                llllllllllllllllllIIIIIIIIlIIIIl.add(llllllllllllllllllIIIIIIIIlIIllI);
                llllllllllllllllllIIIIIIIIIlllll += SIZE;
            }
        }
        return llllllllllllllllllIIIIIIIIlIIIIl.toArray(new String[llllllllllllllllllIIIIIIIIlIIIIl.size()]);
    }

    public long getLong(long llllllllllllllllllIIIIIlIIIlllll) {
        Pointer llllllllllllllllllIIIIIlIIlIIIII;
        return Native.getLong(llllllllllllllllllIIIIIlIIlIIIII, llllllllllllllllllIIIIIlIIlIIIII.peer, llllllllllllllllllIIIIIlIIIlllll);
    }

    public Pointer share(long llllllllllllllllllIIIIlIlIIllIIl, long llllllllllllllllllIIIIlIlIIllIll) {
        Pointer llllllllllllllllllIIIIlIlIIllIlI;
        if (llllllllllllllllllIIIIlIlIIllIIl == 0L) {
            return llllllllllllllllllIIIIlIlIIllIlI;
        }
        return new Pointer(llllllllllllllllllIIIIlIlIIllIlI.peer + llllllllllllllllllIIIIlIlIIllIIl);
    }

    Object getValue(long llllllllllllllllllIIIIIlIllIllIl, Class<?> llllllllllllllllllIIIIIlIllIIlll, Object llllllllllllllllllIIIIIlIllIlIll) {
        Pointer llllllllllllllllllIIIIIlIllIlIIl;
        Object llllllllllllllllllIIIIIlIllIlIlI = null;
        if (Structure.class.isAssignableFrom(llllllllllllllllllIIIIIlIllIIlll)) {
            Structure llllllllllllllllllIIIIIlIlllllII = (Structure)llllllllllllllllllIIIIIlIllIlIll;
            if (Structure.ByReference.class.isAssignableFrom(llllllllllllllllllIIIIIlIllIIlll)) {
                llllllllllllllllllIIIIIlIlllllII = Structure.updateStructureByReference(llllllllllllllllllIIIIIlIllIIlll, llllllllllllllllllIIIIIlIlllllII, llllllllllllllllllIIIIIlIllIlIIl.getPointer(llllllllllllllllllIIIIIlIllIllIl));
            } else {
                llllllllllllllllllIIIIIlIlllllII.useMemory(llllllllllllllllllIIIIIlIllIlIIl, (int)llllllllllllllllllIIIIIlIllIllIl, true);
                llllllllllllllllllIIIIIlIlllllII.read();
            }
            llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIlllllII;
        } else if (llllllllllllllllllIIIIIlIllIIlll == Boolean.TYPE || llllllllllllllllllIIIIIlIllIIlll == Boolean.class) {
            llllllllllllllllllIIIIIlIllIlIlI = Function.valueOf(llllllllllllllllllIIIIIlIllIlIIl.getInt(llllllllllllllllllIIIIIlIllIllIl) != 0);
        } else if (llllllllllllllllllIIIIIlIllIIlll == Byte.TYPE || llllllllllllllllllIIIIIlIllIIlll == Byte.class) {
            llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllIlIIl.getByte(llllllllllllllllllIIIIIlIllIllIl);
        } else if (llllllllllllllllllIIIIIlIllIIlll == Short.TYPE || llllllllllllllllllIIIIIlIllIIlll == Short.class) {
            llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllIlIIl.getShort(llllllllllllllllllIIIIIlIllIllIl);
        } else if (llllllllllllllllllIIIIIlIllIIlll == Character.TYPE || llllllllllllllllllIIIIIlIllIIlll == Character.class) {
            llllllllllllllllllIIIIIlIllIlIlI = Character.valueOf(llllllllllllllllllIIIIIlIllIlIIl.getChar(llllllllllllllllllIIIIIlIllIllIl));
        } else if (llllllllllllllllllIIIIIlIllIIlll == Integer.TYPE || llllllllllllllllllIIIIIlIllIIlll == Integer.class) {
            llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllIlIIl.getInt(llllllllllllllllllIIIIIlIllIllIl);
        } else if (llllllllllllllllllIIIIIlIllIIlll == Long.TYPE || llllllllllllllllllIIIIIlIllIIlll == Long.class) {
            llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllIlIIl.getLong(llllllllllllllllllIIIIIlIllIllIl);
        } else if (llllllllllllllllllIIIIIlIllIIlll == Float.TYPE || llllllllllllllllllIIIIIlIllIIlll == Float.class) {
            llllllllllllllllllIIIIIlIllIlIlI = Float.valueOf(llllllllllllllllllIIIIIlIllIlIIl.getFloat(llllllllllllllllllIIIIIlIllIllIl));
        } else if (llllllllllllllllllIIIIIlIllIIlll == Double.TYPE || llllllllllllllllllIIIIIlIllIIlll == Double.class) {
            llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllIlIIl.getDouble(llllllllllllllllllIIIIIlIllIllIl);
        } else if (Pointer.class.isAssignableFrom(llllllllllllllllllIIIIIlIllIIlll)) {
            Pointer llllllllllllllllllIIIIIlIllllIlI = llllllllllllllllllIIIIIlIllIlIIl.getPointer(llllllllllllllllllIIIIIlIllIllIl);
            if (llllllllllllllllllIIIIIlIllllIlI != null) {
                Pointer llllllllllllllllllIIIIIlIllllIll;
                Pointer pointer = llllllllllllllllllIIIIIlIllllIll = llllllllllllllllllIIIIIlIllIlIll instanceof Pointer ? (Pointer)llllllllllllllllllIIIIIlIllIlIll : null;
                llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllllIll == null || llllllllllllllllllIIIIIlIllllIlI.peer != llllllllllllllllllIIIIIlIllllIll.peer ? llllllllllllllllllIIIIIlIllllIlI : llllllllllllllllllIIIIIlIllllIll;
            }
        } else if (llllllllllllllllllIIIIIlIllIIlll == String.class) {
            Pointer llllllllllllllllllIIIIIlIllllIIl = llllllllllllllllllIIIIIlIllIlIIl.getPointer(llllllllllllllllllIIIIIlIllIllIl);
            llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllllIIl != null ? llllllllllllllllllIIIIIlIllllIIl.getString(0L) : null;
        } else if (llllllllllllllllllIIIIIlIllIIlll == WString.class) {
            Pointer llllllllllllllllllIIIIIlIllllIII = llllllllllllllllllIIIIIlIllIlIIl.getPointer(llllllllllllllllllIIIIIlIllIllIl);
            llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllllIII != null ? new WString(llllllllllllllllllIIIIIlIllllIII.getWideString(0L)) : null;
        } else if (Callback.class.isAssignableFrom(llllllllllllllllllIIIIIlIllIIlll)) {
            Pointer llllllllllllllllllIIIIIlIlllIlIl = llllllllllllllllllIIIIIlIllIlIIl.getPointer(llllllllllllllllllIIIIIlIllIllIl);
            if (llllllllllllllllllIIIIIlIlllIlIl == null) {
                llllllllllllllllllIIIIIlIllIlIlI = null;
            } else {
                Callback llllllllllllllllllIIIIIlIlllIlll = (Callback)llllllllllllllllllIIIIIlIllIlIll;
                Pointer llllllllllllllllllIIIIIlIlllIllI = CallbackReference.getFunctionPointer(llllllllllllllllllIIIIIlIlllIlll);
                if (!llllllllllllllllllIIIIIlIlllIlIl.equals(llllllllllllllllllIIIIIlIlllIllI)) {
                    llllllllllllllllllIIIIIlIlllIlll = CallbackReference.getCallback(llllllllllllllllllIIIIIlIllIIlll, llllllllllllllllllIIIIIlIlllIlIl);
                }
                llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIlllIlll;
            }
        } else if (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(llllllllllllllllllIIIIIlIllIIlll)) {
            Pointer llllllllllllllllllIIIIIlIlllIIll = llllllllllllllllllIIIIIlIllIlIIl.getPointer(llllllllllllllllllIIIIIlIllIllIl);
            if (llllllllllllllllllIIIIIlIlllIIll == null) {
                llllllllllllllllllIIIIIlIllIlIlI = null;
            } else {
                Pointer llllllllllllllllllIIIIIlIlllIlII;
                Pointer pointer = llllllllllllllllllIIIIIlIlllIlII = llllllllllllllllllIIIIIlIllIlIll == null ? null : Native.getDirectBufferPointer((Buffer)llllllllllllllllllIIIIIlIllIlIll);
                if (llllllllllllllllllIIIIIlIlllIlII == null || !llllllllllllllllllIIIIIlIlllIlII.equals(llllllllllllllllllIIIIIlIlllIIll)) {
                    throw new IllegalStateException("Can't autogenerate a direct buffer on memory read");
                }
                llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllIlIll;
            }
        } else if (NativeMapped.class.isAssignableFrom(llllllllllllllllllIIIIIlIllIIlll)) {
            NativeMapped llllllllllllllllllIIIIIlIllIllll = (NativeMapped)llllllllllllllllllIIIIIlIllIlIll;
            if (llllllllllllllllllIIIIIlIllIllll != null) {
                Object llllllllllllllllllIIIIIlIlllIIlI = llllllllllllllllllIIIIIlIllIlIIl.getValue(llllllllllllllllllIIIIIlIllIllIl, llllllllllllllllllIIIIIlIllIllll.nativeType(), null);
                llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllIllll.fromNative(llllllllllllllllllIIIIIlIlllIIlI, new FromNativeContext(llllllllllllllllllIIIIIlIllIIlll));
                if (llllllllllllllllllIIIIIlIllIllll.equals(llllllllllllllllllIIIIIlIllIlIlI)) {
                    llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllIllll;
                }
            } else {
                NativeMappedConverter llllllllllllllllllIIIIIlIlllIIIl = NativeMappedConverter.getInstance(llllllllllllllllllIIIIIlIllIIlll);
                Object llllllllllllllllllIIIIIlIlllIIII = llllllllllllllllllIIIIIlIllIlIIl.getValue(llllllllllllllllllIIIIIlIllIllIl, llllllllllllllllllIIIIIlIlllIIIl.nativeType(), null);
                llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIlllIIIl.fromNative(llllllllllllllllllIIIIIlIlllIIII, new FromNativeContext(llllllllllllllllllIIIIIlIllIIlll));
            }
        } else if (llllllllllllllllllIIIIIlIllIIlll.isArray()) {
            llllllllllllllllllIIIIIlIllIlIlI = llllllllllllllllllIIIIIlIllIlIll;
            if (llllllllllllllllllIIIIIlIllIlIlI == null) {
                throw new IllegalStateException("Need an initialized array");
            }
            llllllllllllllllllIIIIIlIllIlIIl.readArray(llllllllllllllllllIIIIIlIllIllIl, llllllllllllllllllIIIIIlIllIlIlI, llllllllllllllllllIIIIIlIllIIlll.getComponentType());
        } else {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Reading \"").append(llllllllllllllllllIIIIIlIllIIlll).append("\" from memory is not supported")));
        }
        return llllllllllllllllllIIIIIlIllIlIlI;
    }

    public int getInt(long llllllllllllllllllIIIIIlIIlIIlIl) {
        Pointer llllllllllllllllllIIIIIlIIlIIlII;
        return Native.getInt(llllllllllllllllllIIIIIlIIlIIlII, llllllllllllllllllIIIIIlIIlIIlII.peer, llllllllllllllllllIIIIIlIIlIIlIl);
    }

    public char getChar(long llllllllllllllllllIIIIIlIIllIIIl) {
        Pointer llllllllllllllllllIIIIIlIIllIIlI;
        return Native.getChar(llllllllllllllllllIIIIIlIIllIIlI, llllllllllllllllllIIIIIlIIllIIlI.peer, llllllllllllllllllIIIIIlIIllIIIl);
    }

    public void write(long llllllllllllllllllIIIIIllllIIlIl, short[] llllllllllllllllllIIIIIllllIlIIl, int llllllllllllllllllIIIIIllllIlIII, int llllllllllllllllllIIIIIllllIIlll) {
        Pointer llllllllllllllllllIIIIIllllIIllI;
        Native.write(llllllllllllllllllIIIIIllllIIllI, llllllllllllllllllIIIIIllllIIllI.peer, llllllllllllllllllIIIIIllllIIlIl, llllllllllllllllllIIIIIllllIlIIl, llllllllllllllllllIIIIIllllIlIII, llllllllllllllllllIIIIIllllIIlll);
    }

    void setValue(long llllllllllllllllllIIIIIIIIIIIllI, Object llllllllllllllllllIIIIIIIIIIlIIl, Class<?> llllllllllllllllllIIIIIIIIIIlIII) {
        Pointer llllllllllllllllllIIIIIIIIIIlIll;
        if (llllllllllllllllllIIIIIIIIIIlIII == Boolean.TYPE || llllllllllllllllllIIIIIIIIIIlIII == Boolean.class) {
            llllllllllllllllllIIIIIIIIIIlIll.setInt(llllllllllllllllllIIIIIIIIIIIllI, Boolean.TRUE.equals(llllllllllllllllllIIIIIIIIIIlIIl) ? -1 : 0);
        } else if (llllllllllllllllllIIIIIIIIIIlIII == Byte.TYPE || llllllllllllllllllIIIIIIIIIIlIII == Byte.class) {
            llllllllllllllllllIIIIIIIIIIlIll.setByte(llllllllllllllllllIIIIIIIIIIIllI, llllllllllllllllllIIIIIIIIIIlIIl == null ? (byte)0 : (Byte)llllllllllllllllllIIIIIIIIIIlIIl);
        } else if (llllllllllllllllllIIIIIIIIIIlIII == Short.TYPE || llllllllllllllllllIIIIIIIIIIlIII == Short.class) {
            llllllllllllllllllIIIIIIIIIIlIll.setShort(llllllllllllllllllIIIIIIIIIIIllI, llllllllllllllllllIIIIIIIIIIlIIl == null ? (short)0 : (Short)llllllllllllllllllIIIIIIIIIIlIIl);
        } else if (llllllllllllllllllIIIIIIIIIIlIII == Character.TYPE || llllllllllllllllllIIIIIIIIIIlIII == Character.class) {
            llllllllllllllllllIIIIIIIIIIlIll.setChar(llllllllllllllllllIIIIIIIIIIIllI, llllllllllllllllllIIIIIIIIIIlIIl == null ? (char)'\u0000' : ((Character)llllllllllllllllllIIIIIIIIIIlIIl).charValue());
        } else if (llllllllllllllllllIIIIIIIIIIlIII == Integer.TYPE || llllllllllllllllllIIIIIIIIIIlIII == Integer.class) {
            llllllllllllllllllIIIIIIIIIIlIll.setInt(llllllllllllllllllIIIIIIIIIIIllI, llllllllllllllllllIIIIIIIIIIlIIl == null ? 0 : (Integer)llllllllllllllllllIIIIIIIIIIlIIl);
        } else if (llllllllllllllllllIIIIIIIIIIlIII == Long.TYPE || llllllllllllllllllIIIIIIIIIIlIII == Long.class) {
            llllllllllllllllllIIIIIIIIIIlIll.setLong(llllllllllllllllllIIIIIIIIIIIllI, llllllllllllllllllIIIIIIIIIIlIIl == null ? 0L : (Long)llllllllllllllllllIIIIIIIIIIlIIl);
        } else if (llllllllllllllllllIIIIIIIIIIlIII == Float.TYPE || llllllllllllllllllIIIIIIIIIIlIII == Float.class) {
            llllllllllllllllllIIIIIIIIIIlIll.setFloat(llllllllllllllllllIIIIIIIIIIIllI, llllllllllllllllllIIIIIIIIIIlIIl == null ? 0.0f : ((Float)llllllllllllllllllIIIIIIIIIIlIIl).floatValue());
        } else if (llllllllllllllllllIIIIIIIIIIlIII == Double.TYPE || llllllllllllllllllIIIIIIIIIIlIII == Double.class) {
            llllllllllllllllllIIIIIIIIIIlIll.setDouble(llllllllllllllllllIIIIIIIIIIIllI, llllllllllllllllllIIIIIIIIIIlIIl == null ? 0.0 : (Double)llllllllllllllllllIIIIIIIIIIlIIl);
        } else if (llllllllllllllllllIIIIIIIIIIlIII == Pointer.class) {
            llllllllllllllllllIIIIIIIIIIlIll.setPointer(llllllllllllllllllIIIIIIIIIIIllI, (Pointer)llllllllllllllllllIIIIIIIIIIlIIl);
        } else if (llllllllllllllllllIIIIIIIIIIlIII == String.class) {
            llllllllllllllllllIIIIIIIIIIlIll.setPointer(llllllllllllllllllIIIIIIIIIIIllI, (Pointer)llllllllllllllllllIIIIIIIIIIlIIl);
        } else if (llllllllllllllllllIIIIIIIIIIlIII == WString.class) {
            llllllllllllllllllIIIIIIIIIIlIll.setPointer(llllllllllllllllllIIIIIIIIIIIllI, (Pointer)llllllllllllllllllIIIIIIIIIIlIIl);
        } else if (Structure.class.isAssignableFrom(llllllllllllllllllIIIIIIIIIIlIII)) {
            Structure llllllllllllllllllIIIIIIIIIIllll = (Structure)llllllllllllllllllIIIIIIIIIIlIIl;
            if (Structure.ByReference.class.isAssignableFrom(llllllllllllllllllIIIIIIIIIIlIII)) {
                llllllllllllllllllIIIIIIIIIIlIll.setPointer(llllllllllllllllllIIIIIIIIIIIllI, llllllllllllllllllIIIIIIIIIIllll == null ? null : llllllllllllllllllIIIIIIIIIIllll.getPointer());
                if (llllllllllllllllllIIIIIIIIIIllll != null) {
                    llllllllllllllllllIIIIIIIIIIllll.autoWrite();
                }
            } else {
                llllllllllllllllllIIIIIIIIIIllll.useMemory(llllllllllllllllllIIIIIIIIIIlIll, (int)llllllllllllllllllIIIIIIIIIIIllI, true);
                llllllllllllllllllIIIIIIIIIIllll.write();
            }
        } else if (Callback.class.isAssignableFrom(llllllllllllllllllIIIIIIIIIIlIII)) {
            llllllllllllllllllIIIIIIIIIIlIll.setPointer(llllllllllllllllllIIIIIIIIIIIllI, CallbackReference.getFunctionPointer((Callback)llllllllllllllllllIIIIIIIIIIlIIl));
        } else if (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(llllllllllllllllllIIIIIIIIIIlIII)) {
            Pointer llllllllllllllllllIIIIIIIIIIlllI = llllllllllllllllllIIIIIIIIIIlIIl == null ? null : Native.getDirectBufferPointer((Buffer)llllllllllllllllllIIIIIIIIIIlIIl);
            llllllllllllllllllIIIIIIIIIIlIll.setPointer(llllllllllllllllllIIIIIIIIIIIllI, llllllllllllllllllIIIIIIIIIIlllI);
        } else if (NativeMapped.class.isAssignableFrom(llllllllllllllllllIIIIIIIIIIlIII)) {
            NativeMappedConverter llllllllllllllllllIIIIIIIIIIllIl = NativeMappedConverter.getInstance(llllllllllllllllllIIIIIIIIIIlIII);
            Class<?> llllllllllllllllllIIIIIIIIIIllII = llllllllllllllllllIIIIIIIIIIllIl.nativeType();
            llllllllllllllllllIIIIIIIIIIlIll.setValue(llllllllllllllllllIIIIIIIIIIIllI, llllllllllllllllllIIIIIIIIIIllIl.toNative(llllllllllllllllllIIIIIIIIIIlIIl, new ToNativeContext()), llllllllllllllllllIIIIIIIIIIllII);
        } else if (llllllllllllllllllIIIIIIIIIIlIII.isArray()) {
            llllllllllllllllllIIIIIIIIIIlIll.writeArray(llllllllllllllllllIIIIIIIIIIIllI, llllllllllllllllllIIIIIIIIIIlIIl, llllllllllllllllllIIIIIIIIIIlIII.getComponentType());
        } else {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Writing ").append(llllllllllllllllllIIIIIIIIIIlIII).append(" to memory is not supported")));
        }
    }

    public void setDouble(long lllllllllllllllllIlllllllIIIIllI, double lllllllllllllllllIlllllllIIIIlIl) {
        Pointer lllllllllllllllllIlllllllIIIIlll;
        Native.setDouble(lllllllllllllllllIlllllllIIIIlll, lllllllllllllllllIlllllllIIIIlll.peer, lllllllllllllllllIlllllllIIIIllI, lllllllllllllllllIlllllllIIIIlIl);
    }

    public NativeLong getNativeLong(long llllllllllllllllllIIIIIlIIIllIIl) {
        Pointer llllllllllllllllllIIIIIlIIIllIII;
        return new NativeLong(NativeLong.SIZE == 8 ? llllllllllllllllllIIIIIlIIIllIII.getLong(llllllllllllllllllIIIIIlIIIllIIl) : (long)llllllllllllllllllIIIIIlIIIllIII.getInt(llllllllllllllllllIIIIIlIIIllIIl));
    }

    public Pointer[] getPointerArray(long llllllllllllllllllIIIIIIIlllIlIl, int llllllllllllllllllIIIIIIIlllIIII) {
        Pointer llllllllllllllllllIIIIIIIlllIllI;
        Pointer[] llllllllllllllllllIIIIIIIlllIIll = new Pointer[llllllllllllllllllIIIIIIIlllIIII];
        llllllllllllllllllIIIIIIIlllIllI.read(llllllllllllllllllIIIIIIIlllIlIl, llllllllllllllllllIIIIIIIlllIIll, 0, llllllllllllllllllIIIIIIIlllIIII);
        return llllllllllllllllllIIIIIIIlllIIll;
    }

    public Pointer share(long llllllllllllllllllIIIIlIlIlIIIlI) {
        Pointer llllllllllllllllllIIIIlIlIlIIIIl;
        return llllllllllllllllllIIIIlIlIlIIIIl.share(llllllllllllllllllIIIIlIlIlIIIlI, 0L);
    }

    public String[] getWideStringArray(long llllllllllllllllllIIIIIIIlIIIIII, int llllllllllllllllllIIIIIIIIllllll) {
        Pointer llllllllllllllllllIIIIIIIlIIIlII;
        return llllllllllllllllllIIIIIIIlIIIlII.getStringArray(llllllllllllllllllIIIIIIIlIIIIII, llllllllllllllllllIIIIIIIIllllll, "--WIDE-STRING--");
    }

    public void setPointer(long lllllllllllllllllIllllllIlllllIl, Pointer lllllllllllllllllIllllllIllllIIl) {
        Pointer lllllllllllllllllIllllllIllllIll;
        Native.setPointer(lllllllllllllllllIllllllIllllIll, lllllllllllllllllIllllllIllllIll.peer, lllllllllllllllllIllllllIlllllIl, lllllllllllllllllIllllllIllllIIl != null ? lllllllllllllllllIllllllIllllIIl.peer : 0L);
    }

    public ByteBuffer getByteBuffer(long llllllllllllllllllIIIIIIllllllIl, long llllllllllllllllllIIIIIIllllllII) {
        Pointer llllllllllllllllllIIIIIIlllllllI;
        return Native.getDirectByteBuffer(llllllllllllllllllIIIIIIlllllllI, llllllllllllllllllIIIIIIlllllllI.peer, llllllllllllllllllIIIIIIllllllIl, llllllllllllllllllIIIIIIllllllII).order(ByteOrder.nativeOrder());
    }

    @Deprecated
    public String[] getStringArray(long llllllllllllllllllIIIIIIIlIIllll, boolean llllllllllllllllllIIIIIIIlIlIIIl) {
        Pointer llllllllllllllllllIIIIIIIlIlIIll;
        return llllllllllllllllllIIIIIIIlIlIIll.getStringArray(llllllllllllllllllIIIIIIIlIIllll, -1, llllllllllllllllllIIIIIIIlIlIIIl);
    }

    public void read(long llllllllllllllllllIIIIlIIlIlllII, char[] llllllllllllllllllIIIIlIIlIlIllI, int llllllllllllllllllIIIIlIIlIllIlI, int llllllllllllllllllIIIIlIIlIlIlII) {
        Pointer llllllllllllllllllIIIIlIIlIlllIl;
        Native.read(llllllllllllllllllIIIIlIIlIlllIl, llllllllllllllllllIIIIlIIlIlllIl.peer, llllllllllllllllllIIIIlIIlIlllII, llllllllllllllllllIIIIlIIlIlIllI, llllllllllllllllllIIIIlIIlIllIlI, llllllllllllllllllIIIIlIIlIlIlII);
    }

    @Deprecated
    public void setString(long lllllllllllllllllIllllllIllIllll, String lllllllllllllllllIllllllIlllIIlI, boolean lllllllllllllllllIllllllIllIllIl) {
        Pointer lllllllllllllllllIllllllIlllIIII;
        if (lllllllllllllllllIllllllIllIllIl) {
            lllllllllllllllllIllllllIlllIIII.setWideString(lllllllllllllllllIllllllIllIllll, lllllllllllllllllIllllllIlllIIlI);
        } else {
            lllllllllllllllllIllllllIlllIIII.setString(lllllllllllllllllIllllllIllIllll, lllllllllllllllllIllllllIlllIIlI);
        }
    }

    public void setString(long lllllllllllllllllIllllllIlIlIllI, String lllllllllllllllllIllllllIlIlIIlI) {
        Pointer lllllllllllllllllIllllllIlIlIlll;
        lllllllllllllllllIllllllIlIlIlll.setString(lllllllllllllllllIllllllIlIlIllI, lllllllllllllllllIllllllIlIlIIlI, Native.getDefaultStringEncoding());
    }

    public double[] getDoubleArray(long llllllllllllllllllIIIIIIlIIlIIII, int llllllllllllllllllIIIIIIlIIIlIll) {
        Pointer llllllllllllllllllIIIIIIlIIIllIl;
        double[] llllllllllllllllllIIIIIIlIIIlllI = new double[llllllllllllllllllIIIIIIlIIIlIll];
        llllllllllllllllllIIIIIIlIIIllIl.read(llllllllllllllllllIIIIIIlIIlIIII, llllllllllllllllllIIIIIIlIIIlllI, 0, llllllllllllllllllIIIIIIlIIIlIll);
        return llllllllllllllllllIIIIIIlIIIlllI;
    }

    public void read(long llllllllllllllllllIIIIlIIllIIllI, short[] llllllllllllllllllIIIIlIIllIIlIl, int llllllllllllllllllIIIIlIIllIIlII, int llllllllllllllllllIIIIlIIllIlIII) {
        Pointer llllllllllllllllllIIIIlIIllIllII;
        Native.read(llllllllllllllllllIIIIlIIllIllII, llllllllllllllllllIIIIlIIllIllII.peer, llllllllllllllllllIIIIlIIllIIllI, llllllllllllllllllIIIIlIIllIIlIl, llllllllllllllllllIIIIlIIllIIlII, llllllllllllllllllIIIIlIIllIlIII);
    }

    public void write(long llllllllllllllllllIIIIIlllIIllII, int[] llllllllllllllllllIIIIIlllIIIllI, int llllllllllllllllllIIIIIlllIIlIlI, int llllllllllllllllllIIIIIlllIIlIIl) {
        Pointer llllllllllllllllllIIIIIlllIIlIII;
        Native.write(llllllllllllllllllIIIIIlllIIlIII, llllllllllllllllllIIIIIlllIIlIII.peer, llllllllllllllllllIIIIIlllIIllII, llllllllllllllllllIIIIIlllIIIllI, llllllllllllllllllIIIIIlllIIlIlI, llllllllllllllllllIIIIIlllIIlIIl);
    }

    public String[] getStringArray(long llllllllllllllllllIIIIIIIllIIlII, String llllllllllllllllllIIIIIIIllIIIII) {
        Pointer llllllllllllllllllIIIIIIIllIIlIl;
        return llllllllllllllllllIIIIIIIllIIlIl.getStringArray(llllllllllllllllllIIIIIIIllIIlII, -1, llllllllllllllllllIIIIIIIllIIIII);
    }

    public String[] getStringArray(long llllllllllllllllllIIIIIIIlIllIll, int llllllllllllllllllIIIIIIIlIlIlll) {
        Pointer llllllllllllllllllIIIIIIIlIlllII;
        return llllllllllllllllllIIIIIIIlIlllII.getStringArray(llllllllllllllllllIIIIIIIlIllIll, llllllllllllllllllIIIIIIIlIlIlll, Native.getDefaultStringEncoding());
    }

    public void setMemory(long lllllllllllllllllIllllllllIlIIII, long lllllllllllllllllIllllllllIIllll, byte lllllllllllllllllIllllllllIIlIlI) {
        Pointer lllllllllllllllllIllllllllIIllIl;
        Native.setMemory(lllllllllllllllllIllllllllIIllIl, lllllllllllllllllIllllllllIIllIl.peer, lllllllllllllllllIllllllllIlIIII, lllllllllllllllllIllllllllIIllll, lllllllllllllllllIllllllllIIlIlI);
    }

    public int[] getIntArray(long llllllllllllllllllIIIIIIlIllIlII, int llllllllllllllllllIIIIIIlIlIllll) {
        Pointer llllllllllllllllllIIIIIIlIllIIIl;
        int[] llllllllllllllllllIIIIIIlIllIIlI = new int[llllllllllllllllllIIIIIIlIlIllll];
        llllllllllllllllllIIIIIIlIllIIIl.read(llllllllllllllllllIIIIIIlIllIlII, llllllllllllllllllIIIIIIlIllIIlI, 0, llllllllllllllllllIIIIIIlIlIllll);
        return llllllllllllllllllIIIIIIlIllIIlI;
    }

    public String getString(long llllllllllllllllllIIIIIIlllIIlll) {
        Pointer llllllllllllllllllIIIIIIlllIlIII;
        return llllllllllllllllllIIIIIIlllIlIII.getString(llllllllllllllllllIIIIIIlllIIlll, Native.getDefaultStringEncoding());
    }

    public void setWideString(long lllllllllllllllllIllllllIllIlIII, String lllllllllllllllllIllllllIllIIlII) {
        Pointer lllllllllllllllllIllllllIllIlIIl;
        Native.setWideString(lllllllllllllllllIllllllIllIlIIl, lllllllllllllllllIllllllIllIlIIl.peer, lllllllllllllllllIllllllIllIlIII, lllllllllllllllllIllllllIllIIlII);
    }

    public static void nativeValue(Pointer lllllllllllllllllIllllllIIIlllll, long lllllllllllllllllIllllllIIIlllII) {
        lllllllllllllllllIllllllIIIlllll.peer = lllllllllllllllllIllllllIIIlllII;
    }

    public void read(long llllllllllllllllllIIIIlIIIlllllI, long[] llllllllllllllllllIIIIlIIIlllIII, int llllllllllllllllllIIIIlIIIllllII, int llllllllllllllllllIIIIlIIIllIllI) {
        Pointer llllllllllllllllllIIIIlIIIlllIlI;
        Native.read(llllllllllllllllllIIIIlIIIlllIlI, llllllllllllllllllIIIIlIIIlllIlI.peer, llllllllllllllllllIIIIlIIIlllllI, llllllllllllllllllIIIIlIIIlllIII, llllllllllllllllllIIIIlIIIllllII, llllllllllllllllllIIIIlIIIllIllI);
    }

    public float[] getFloatArray(long llllllllllllllllllIIIIIIlIIllIII, int llllllllllllllllllIIIIIIlIIllIll) {
        Pointer llllllllllllllllllIIIIIIlIIlllIl;
        float[] llllllllllllllllllIIIIIIlIIllIlI = new float[llllllllllllllllllIIIIIIlIIllIll];
        llllllllllllllllllIIIIIIlIIlllIl.read(llllllllllllllllllIIIIIIlIIllIII, llllllllllllllllllIIIIIIlIIllIlI, 0, llllllllllllllllllIIIIIIlIIllIll);
        return llllllllllllllllllIIIIIIlIIllIlI;
    }

    public byte[] getByteArray(long llllllllllllllllllIIIIIIllIlIlII, int llllllllllllllllllIIIIIIllIlIlll) {
        Pointer llllllllllllllllllIIIIIIllIllIIl;
        byte[] llllllllllllllllllIIIIIIllIlIllI = new byte[llllllllllllllllllIIIIIIllIlIlll];
        llllllllllllllllllIIIIIIllIllIIl.read(llllllllllllllllllIIIIIIllIlIlII, llllllllllllllllllIIIIIIllIlIllI, 0, llllllllllllllllllIIIIIIllIlIlll);
        return llllllllllllllllllIIIIIIllIlIllI;
    }

    public long[] getLongArray(long llllllllllllllllllIIIIIIlIlIlIII, int llllllllllllllllllIIIIIIlIlIIIll) {
        Pointer llllllllllllllllllIIIIIIlIlIIlIl;
        long[] llllllllllllllllllIIIIIIlIlIIllI = new long[llllllllllllllllllIIIIIIlIlIIIll];
        llllllllllllllllllIIIIIIlIlIIlIl.read(llllllllllllllllllIIIIIIlIlIlIII, llllllllllllllllllIIIIIIlIlIIllI, 0, llllllllllllllllllIIIIIIlIlIIIll);
        return llllllllllllllllllIIIIIIlIlIIllI;
    }

    private void writeArray(long lllllllllllllllllIlllllllllIIIlI, Object lllllllllllllllllIlllllllllIIIIl, Class<?> lllllllllllllllllIlllllllllIIIII) {
        Pointer lllllllllllllllllIlllllllllIIIll;
        if (lllllllllllllllllIlllllllllIIIII == Byte.TYPE) {
            byte[] lllllllllllllllllIllllllllllIlll = (byte[])lllllllllllllllllIlllllllllIIIIl;
            lllllllllllllllllIlllllllllIIIll.write(lllllllllllllllllIlllllllllIIIlI, lllllllllllllllllIllllllllllIlll, 0, lllllllllllllllllIllllllllllIlll.length);
        } else if (lllllllllllllllllIlllllllllIIIII == Short.TYPE) {
            short[] lllllllllllllllllIllllllllllIllI = (short[])lllllllllllllllllIlllllllllIIIIl;
            lllllllllllllllllIlllllllllIIIll.write(lllllllllllllllllIlllllllllIIIlI, lllllllllllllllllIllllllllllIllI, 0, lllllllllllllllllIllllllllllIllI.length);
        } else if (lllllllllllllllllIlllllllllIIIII == Character.TYPE) {
            char[] lllllllllllllllllIllllllllllIlIl = (char[])lllllllllllllllllIlllllllllIIIIl;
            lllllllllllllllllIlllllllllIIIll.write(lllllllllllllllllIlllllllllIIIlI, lllllllllllllllllIllllllllllIlIl, 0, lllllllllllllllllIllllllllllIlIl.length);
        } else if (lllllllllllllllllIlllllllllIIIII == Integer.TYPE) {
            int[] lllllllllllllllllIllllllllllIlII = (int[])lllllllllllllllllIlllllllllIIIIl;
            lllllllllllllllllIlllllllllIIIll.write(lllllllllllllllllIlllllllllIIIlI, lllllllllllllllllIllllllllllIlII, 0, lllllllllllllllllIllllllllllIlII.length);
        } else if (lllllllllllllllllIlllllllllIIIII == Long.TYPE) {
            long[] lllllllllllllllllIllllllllllIIll = (long[])lllllllllllllllllIlllllllllIIIIl;
            lllllllllllllllllIlllllllllIIIll.write(lllllllllllllllllIlllllllllIIIlI, lllllllllllllllllIllllllllllIIll, 0, lllllllllllllllllIllllllllllIIll.length);
        } else if (lllllllllllllllllIlllllllllIIIII == Float.TYPE) {
            float[] lllllllllllllllllIllllllllllIIlI = (float[])lllllllllllllllllIlllllllllIIIIl;
            lllllllllllllllllIlllllllllIIIll.write(lllllllllllllllllIlllllllllIIIlI, lllllllllllllllllIllllllllllIIlI, 0, lllllllllllllllllIllllllllllIIlI.length);
        } else if (lllllllllllllllllIlllllllllIIIII == Double.TYPE) {
            double[] lllllllllllllllllIllllllllllIIIl = (double[])lllllllllllllllllIlllllllllIIIIl;
            lllllllllllllllllIlllllllllIIIll.write(lllllllllllllllllIlllllllllIIIlI, lllllllllllllllllIllllllllllIIIl, 0, lllllllllllllllllIllllllllllIIIl.length);
        } else if (Pointer.class.isAssignableFrom(lllllllllllllllllIlllllllllIIIII)) {
            Pointer[] lllllllllllllllllIllllllllllIIII = (Pointer[])lllllllllllllllllIlllllllllIIIIl;
            lllllllllllllllllIlllllllllIIIll.write(lllllllllllllllllIlllllllllIIIlI, lllllllllllllllllIllllllllllIIII, 0, lllllllllllllllllIllllllllllIIII.length);
        } else if (Structure.class.isAssignableFrom(lllllllllllllllllIlllllllllIIIII)) {
            Structure[] lllllllllllllllllIlllllllllIlIlI = (Structure[])lllllllllllllllllIlllllllllIIIIl;
            if (Structure.ByReference.class.isAssignableFrom(lllllllllllllllllIlllllllllIIIII)) {
                Pointer[] lllllllllllllllllIlllllllllIlllI = new Pointer[lllllllllllllllllIlllllllllIlIlI.length];
                for (int lllllllllllllllllIlllllllllIllll = 0; lllllllllllllllllIlllllllllIllll < lllllllllllllllllIlllllllllIlIlI.length; ++lllllllllllllllllIlllllllllIllll) {
                    if (lllllllllllllllllIlllllllllIlIlI[lllllllllllllllllIlllllllllIllll] == null) {
                        lllllllllllllllllIlllllllllIlllI[lllllllllllllllllIlllllllllIllll] = null;
                        continue;
                    }
                    lllllllllllllllllIlllllllllIlllI[lllllllllllllllllIlllllllllIllll] = lllllllllllllllllIlllllllllIlIlI[lllllllllllllllllIlllllllllIllll].getPointer();
                    lllllllllllllllllIlllllllllIlIlI[lllllllllllllllllIlllllllllIllll].write();
                }
                lllllllllllllllllIlllllllllIIIll.write(lllllllllllllllllIlllllllllIIIlI, lllllllllllllllllIlllllllllIlllI, 0, lllllllllllllllllIlllllllllIlllI.length);
            } else {
                Structure lllllllllllllllllIlllllllllIllII = lllllllllllllllllIlllllllllIlIlI[0];
                if (lllllllllllllllllIlllllllllIllII == null) {
                    lllllllllllllllllIlllllllllIlIlI[0] = lllllllllllllllllIlllllllllIllII = Structure.newInstance(lllllllllllllllllIlllllllllIIIII, lllllllllllllllllIlllllllllIIIll.share(lllllllllllllllllIlllllllllIIIlI));
                } else {
                    lllllllllllllllllIlllllllllIllII.useMemory(lllllllllllllllllIlllllllllIIIll, (int)lllllllllllllllllIlllllllllIIIlI, true);
                }
                lllllllllllllllllIlllllllllIllII.write();
                Structure[] lllllllllllllllllIlllllllllIlIll = lllllllllllllllllIlllllllllIllII.toArray(lllllllllllllllllIlllllllllIlIlI.length);
                for (int lllllllllllllllllIlllllllllIllIl = 1; lllllllllllllllllIlllllllllIllIl < lllllllllllllllllIlllllllllIlIlI.length; ++lllllllllllllllllIlllllllllIllIl) {
                    if (lllllllllllllllllIlllllllllIlIlI[lllllllllllllllllIlllllllllIllIl] == null) {
                        lllllllllllllllllIlllllllllIlIlI[lllllllllllllllllIlllllllllIllIl] = lllllllllllllllllIlllllllllIlIll[lllllllllllllllllIlllllllllIllIl];
                    } else {
                        lllllllllllllllllIlllllllllIlIlI[lllllllllllllllllIlllllllllIllIl].useMemory(lllllllllllllllllIlllllllllIIIll, (int)(lllllllllllllllllIlllllllllIIIlI + (long)(lllllllllllllllllIlllllllllIllIl * lllllllllllllllllIlllllllllIlIlI[lllllllllllllllllIlllllllllIllIl].size())), true);
                    }
                    lllllllllllllllllIlllllllllIlIlI[lllllllllllllllllIlllllllllIllIl].write();
                }
            }
        } else if (NativeMapped.class.isAssignableFrom(lllllllllllllllllIlllllllllIIIII)) {
            NativeMapped[] lllllllllllllllllIlllllllllIIlll = (NativeMapped[])lllllllllllllllllIlllllllllIIIIl;
            NativeMappedConverter lllllllllllllllllIlllllllllIIllI = NativeMappedConverter.getInstance(lllllllllllllllllIlllllllllIIIII);
            Class<?> lllllllllllllllllIlllllllllIIlIl = lllllllllllllllllIlllllllllIIllI.nativeType();
            int lllllllllllllllllIlllllllllIIlII = Native.getNativeSize(lllllllllllllllllIlllllllllIIIIl.getClass(), lllllllllllllllllIlllllllllIIIIl) / lllllllllllllllllIlllllllllIIlll.length;
            for (int lllllllllllllllllIlllllllllIlIII = 0; lllllllllllllllllIlllllllllIlIII < lllllllllllllllllIlllllllllIIlll.length; ++lllllllllllllllllIlllllllllIlIII) {
                Object lllllllllllllllllIlllllllllIlIIl = lllllllllllllllllIlllllllllIIllI.toNative(lllllllllllllllllIlllllllllIIlll[lllllllllllllllllIlllllllllIlIII], new ToNativeContext());
                lllllllllllllllllIlllllllllIIIll.setValue(lllllllllllllllllIlllllllllIIIlI + (long)(lllllllllllllllllIlllllllllIlIII * lllllllllllllllllIlllllllllIIlII), lllllllllllllllllIlllllllllIlIIl, lllllllllllllllllIlllllllllIIlIl);
            }
        } else {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Writing array of ").append(lllllllllllllllllIlllllllllIIIII).append(" to memory not supported")));
        }
    }

    public void read(long llllllllllllllllllIIIIlIIlIIllIl, int[] llllllllllllllllllIIIIlIIlIIllII, int llllllllllllllllllIIIIlIIlIIIllI, int llllllllllllllllllIIIIlIIlIIlIlI) {
        Pointer llllllllllllllllllIIIIlIIlIIlllI;
        Native.read(llllllllllllllllllIIIIlIIlIIlllI, llllllllllllllllllIIIIlIIlIIlllI.peer, llllllllllllllllllIIIIlIIlIIllIl, llllllllllllllllllIIIIlIIlIIllII, llllllllllllllllllIIIIlIIlIIIllI, llllllllllllllllllIIIIlIIlIIlIlI);
    }

    public double getDouble(long llllllllllllllllllIIIIIlIIIIllIl) {
        Pointer llllllllllllllllllIIIIIlIIIIllII;
        return Native.getDouble(llllllllllllllllllIIIIIlIIIIllII, llllllllllllllllllIIIIIlIIIIllII.peer, llllllllllllllllllIIIIIlIIIIllIl);
    }

    public void read(long llllllllllllllllllIIIIlIIIlIlIlI, float[] llllllllllllllllllIIIIlIIIlIlllI, int llllllllllllllllllIIIIlIIIlIllIl, int llllllllllllllllllIIIIlIIIlIllII) {
        Pointer llllllllllllllllllIIIIlIIIlIlIll;
        Native.read(llllllllllllllllllIIIIlIIIlIlIll, llllllllllllllllllIIIIlIIIlIlIll.peer, llllllllllllllllllIIIIlIIIlIlIlI, llllllllllllllllllIIIIlIIIlIlllI, llllllllllllllllllIIIIlIIIlIllIl, llllllllllllllllllIIIIlIIIlIllII);
    }

    @Deprecated
    public String[] getStringArray(long llllllllllllllllllIIIIIIIIllIlIl, int llllllllllllllllllIIIIIIIIllIlII, boolean llllllllllllllllllIIIIIIIIllIlll) {
        Pointer llllllllllllllllllIIIIIIIIlllIlI;
        return llllllllllllllllllIIIIIIIIlllIlI.getStringArray(llllllllllllllllllIIIIIIIIllIlIl, llllllllllllllllllIIIIIIIIllIlII, llllllllllllllllllIIIIIIIIllIlll ? "--WIDE-STRING--" : Native.getDefaultStringEncoding());
    }

    public String[] getStringArray(long llllllllllllllllllIIIIIIIllIlIIl) {
        Pointer llllllllllllllllllIIIIIIIllIlIlI;
        return llllllllllllllllllIIIIIIIllIlIlI.getStringArray(llllllllllllllllllIIIIIIIllIlIIl, -1, Native.getDefaultStringEncoding());
    }

    public int hashCode() {
        Pointer llllllllllllllllllIIIIlIlIIIlIll;
        return (int)((llllllllllllllllllIIIIlIlIIIlIll.peer >>> 32) + (llllllllllllllllllIIIIlIlIIIlIll.peer & 0xFFFFFFFFFFFFFFFFL));
    }

    public static final Pointer createConstant(long llllllllllllllllllIIIIlIlIllIIlI) {
        return new Opaque(llllllllllllllllllIIIIlIlIllIIlI);
    }

    private void readArray(long llllllllllllllllllIIIIIlIlIIlIlI, Object llllllllllllllllllIIIIIlIlIIIIll, Class<?> llllllllllllllllllIIIIIlIlIIlIII) {
        Pointer llllllllllllllllllIIIIIlIlIIlIll;
        int llllllllllllllllllIIIIIlIlIIIlll = 0;
        llllllllllllllllllIIIIIlIlIIIlll = Array.getLength(llllllllllllllllllIIIIIlIlIIIIll);
        Object llllllllllllllllllIIIIIlIlIIIllI = llllllllllllllllllIIIIIlIlIIIIll;
        if (llllllllllllllllllIIIIIlIlIIlIII == Byte.TYPE) {
            llllllllllllllllllIIIIIlIlIIlIll.read(llllllllllllllllllIIIIIlIlIIlIlI, (byte[])llllllllllllllllllIIIIIlIlIIIllI, 0, llllllllllllllllllIIIIIlIlIIIlll);
        } else if (llllllllllllllllllIIIIIlIlIIlIII == Short.TYPE) {
            llllllllllllllllllIIIIIlIlIIlIll.read(llllllllllllllllllIIIIIlIlIIlIlI, (short[])llllllllllllllllllIIIIIlIlIIIllI, 0, llllllllllllllllllIIIIIlIlIIIlll);
        } else if (llllllllllllllllllIIIIIlIlIIlIII == Character.TYPE) {
            llllllllllllllllllIIIIIlIlIIlIll.read(llllllllllllllllllIIIIIlIlIIlIlI, (char[])llllllllllllllllllIIIIIlIlIIIllI, 0, llllllllllllllllllIIIIIlIlIIIlll);
        } else if (llllllllllllllllllIIIIIlIlIIlIII == Integer.TYPE) {
            llllllllllllllllllIIIIIlIlIIlIll.read(llllllllllllllllllIIIIIlIlIIlIlI, (int[])llllllllllllllllllIIIIIlIlIIIllI, 0, llllllllllllllllllIIIIIlIlIIIlll);
        } else if (llllllllllllllllllIIIIIlIlIIlIII == Long.TYPE) {
            llllllllllllllllllIIIIIlIlIIlIll.read(llllllllllllllllllIIIIIlIlIIlIlI, (long[])llllllllllllllllllIIIIIlIlIIIllI, 0, llllllllllllllllllIIIIIlIlIIIlll);
        } else if (llllllllllllllllllIIIIIlIlIIlIII == Float.TYPE) {
            llllllllllllllllllIIIIIlIlIIlIll.read(llllllllllllllllllIIIIIlIlIIlIlI, (float[])llllllllllllllllllIIIIIlIlIIIllI, 0, llllllllllllllllllIIIIIlIlIIIlll);
        } else if (llllllllllllllllllIIIIIlIlIIlIII == Double.TYPE) {
            llllllllllllllllllIIIIIlIlIIlIll.read(llllllllllllllllllIIIIIlIlIIlIlI, (double[])llllllllllllllllllIIIIIlIlIIIllI, 0, llllllllllllllllllIIIIIlIlIIIlll);
        } else if (Pointer.class.isAssignableFrom(llllllllllllllllllIIIIIlIlIIlIII)) {
            llllllllllllllllllIIIIIlIlIIlIll.read(llllllllllllllllllIIIIIlIlIIlIlI, (Pointer[])llllllllllllllllllIIIIIlIlIIIllI, 0, llllllllllllllllllIIIIIlIlIIIlll);
        } else if (Structure.class.isAssignableFrom(llllllllllllllllllIIIIIlIlIIlIII)) {
            Structure[] llllllllllllllllllIIIIIlIlIlIIIl = (Structure[])llllllllllllllllllIIIIIlIlIIIllI;
            if (Structure.ByReference.class.isAssignableFrom(llllllllllllllllllIIIIIlIlIIlIII)) {
                Pointer[] llllllllllllllllllIIIIIlIlIlIlIl = llllllllllllllllllIIIIIlIlIIlIll.getPointerArray(llllllllllllllllllIIIIIlIlIIlIlI, llllllllllllllllllIIIIIlIlIlIIIl.length);
                for (int llllllllllllllllllIIIIIlIlIlIllI = 0; llllllllllllllllllIIIIIlIlIlIllI < llllllllllllllllllIIIIIlIlIlIIIl.length; ++llllllllllllllllllIIIIIlIlIlIllI) {
                    llllllllllllllllllIIIIIlIlIlIIIl[llllllllllllllllllIIIIIlIlIlIllI] = Structure.updateStructureByReference(llllllllllllllllllIIIIIlIlIIlIII, llllllllllllllllllIIIIIlIlIlIIIl[llllllllllllllllllIIIIIlIlIlIllI], llllllllllllllllllIIIIIlIlIlIlIl[llllllllllllllllllIIIIIlIlIlIllI]);
                }
            } else {
                Structure llllllllllllllllllIIIIIlIlIlIIll = llllllllllllllllllIIIIIlIlIlIIIl[0];
                if (llllllllllllllllllIIIIIlIlIlIIll == null) {
                    llllllllllllllllllIIIIIlIlIlIIll = Structure.newInstance(llllllllllllllllllIIIIIlIlIIlIII, llllllllllllllllllIIIIIlIlIIlIll.share(llllllllllllllllllIIIIIlIlIIlIlI));
                    llllllllllllllllllIIIIIlIlIlIIll.conditionalAutoRead();
                    llllllllllllllllllIIIIIlIlIlIIIl[0] = llllllllllllllllllIIIIIlIlIlIIll;
                } else {
                    llllllllllllllllllIIIIIlIlIlIIll.useMemory(llllllllllllllllllIIIIIlIlIIlIll, (int)llllllllllllllllllIIIIIlIlIIlIlI, true);
                    llllllllllllllllllIIIIIlIlIlIIll.read();
                }
                Structure[] llllllllllllllllllIIIIIlIlIlIIlI = llllllllllllllllllIIIIIlIlIlIIll.toArray(llllllllllllllllllIIIIIlIlIlIIIl.length);
                for (int llllllllllllllllllIIIIIlIlIlIlII = 1; llllllllllllllllllIIIIIlIlIlIlII < llllllllllllllllllIIIIIlIlIlIIIl.length; ++llllllllllllllllllIIIIIlIlIlIlII) {
                    if (llllllllllllllllllIIIIIlIlIlIIIl[llllllllllllllllllIIIIIlIlIlIlII] == null) {
                        llllllllllllllllllIIIIIlIlIlIIIl[llllllllllllllllllIIIIIlIlIlIlII] = llllllllllllllllllIIIIIlIlIlIIlI[llllllllllllllllllIIIIIlIlIlIlII];
                        continue;
                    }
                    llllllllllllllllllIIIIIlIlIlIIIl[llllllllllllllllllIIIIIlIlIlIlII].useMemory(llllllllllllllllllIIIIIlIlIIlIll, (int)(llllllllllllllllllIIIIIlIlIIlIlI + (long)(llllllllllllllllllIIIIIlIlIlIlII * llllllllllllllllllIIIIIlIlIlIIIl[llllllllllllllllllIIIIIlIlIlIlII].size())), true);
                    llllllllllllllllllIIIIIlIlIlIIIl[llllllllllllllllllIIIIIlIlIlIlII].read();
                }
            }
        } else if (NativeMapped.class.isAssignableFrom(llllllllllllllllllIIIIIlIlIIlIII)) {
            NativeMapped[] llllllllllllllllllIIIIIlIlIIlllI = (NativeMapped[])llllllllllllllllllIIIIIlIlIIIllI;
            NativeMappedConverter llllllllllllllllllIIIIIlIlIIllIl = NativeMappedConverter.getInstance(llllllllllllllllllIIIIIlIlIIlIII);
            int llllllllllllllllllIIIIIlIlIIllII = Native.getNativeSize(llllllllllllllllllIIIIIlIlIIIllI.getClass(), llllllllllllllllllIIIIIlIlIIIllI) / llllllllllllllllllIIIIIlIlIIlllI.length;
            for (int llllllllllllllllllIIIIIlIlIIllll = 0; llllllllllllllllllIIIIIlIlIIllll < llllllllllllllllllIIIIIlIlIIlllI.length; ++llllllllllllllllllIIIIIlIlIIllll) {
                Object llllllllllllllllllIIIIIlIlIlIIII = llllllllllllllllllIIIIIlIlIIlIll.getValue(llllllllllllllllllIIIIIlIlIIlIlI + (long)(llllllllllllllllllIIIIIlIlIIllII * llllllllllllllllllIIIIIlIlIIllll), llllllllllllllllllIIIIIlIlIIllIl.nativeType(), llllllllllllllllllIIIIIlIlIIlllI[llllllllllllllllllIIIIIlIlIIllll]);
                llllllllllllllllllIIIIIlIlIIlllI[llllllllllllllllllIIIIIlIlIIllll] = (NativeMapped)llllllllllllllllllIIIIIlIlIIllIl.fromNative(llllllllllllllllllIIIIIlIlIlIIII, new FromNativeContext(llllllllllllllllllIIIIIlIlIIlIII));
            }
        } else {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Reading array of ").append(llllllllllllllllllIIIIIlIlIIlIII).append(" from memory not supported")));
        }
    }

    public void write(long llllllllllllllllllIIIIIllIlIlllI, float[] llllllllllllllllllIIIIIllIlIlIII, int llllllllllllllllllIIIIIllIlIIlll, int llllllllllllllllllIIIIIllIlIlIll) {
        Pointer llllllllllllllllllIIIIIllIlIllll;
        Native.write(llllllllllllllllllIIIIIllIlIllll, llllllllllllllllllIIIIIllIlIllll.peer, llllllllllllllllllIIIIIllIlIlllI, llllllllllllllllllIIIIIllIlIlIII, llllllllllllllllllIIIIIllIlIIlll, llllllllllllllllllIIIIIllIlIlIll);
    }

    public void setShort(long lllllllllllllllllIlllllllIlllIIl, short lllllllllllllllllIlllllllIlllIll) {
        Pointer lllllllllllllllllIlllllllIlllIlI;
        Native.setShort(lllllllllllllllllIlllllllIlllIlI, lllllllllllllllllIlllllllIlllIlI.peer, lllllllllllllllllIlllllllIlllIIl, lllllllllllllllllIlllllllIlllIll);
    }

    public char[] getCharArray(long llllllllllllllllllIIIIIIllIIlIII, int llllllllllllllllllIIIIIIllIIIlll) {
        Pointer llllllllllllllllllIIIIIIllIIllIl;
        char[] llllllllllllllllllIIIIIIllIIlIlI = new char[llllllllllllllllllIIIIIIllIIIlll];
        llllllllllllllllllIIIIIIllIIllIl.read(llllllllllllllllllIIIIIIllIIlIII, llllllllllllllllllIIIIIIllIIlIlI, 0, llllllllllllllllllIIIIIIllIIIlll);
        return llllllllllllllllllIIIIIIllIIlIlI;
    }

    public short getShort(long llllllllllllllllllIIIIIlIIlIlIIl) {
        Pointer llllllllllllllllllIIIIIlIIlIllII;
        return Native.getShort(llllllllllllllllllIIIIIlIIlIllII, llllllllllllllllllIIIIIlIIlIllII.peer, llllllllllllllllllIIIIIlIIlIlIIl);
    }

    public void setByte(long lllllllllllllllllIllllllllIIIlIl, byte lllllllllllllllllIllllllllIIIIIl) {
        Pointer lllllllllllllllllIllllllllIIIIll;
        Native.setByte(lllllllllllllllllIllllllllIIIIll, lllllllllllllllllIllllllllIIIIll.peer, lllllllllllllllllIllllllllIIIlIl, lllllllllllllllllIllllllllIIIIIl);
    }

    public void setLong(long lllllllllllllllllIlllllllIlIIIIl, long lllllllllllllllllIlllllllIlIIIII) {
        Pointer lllllllllllllllllIlllllllIIlllll;
        Native.setLong(lllllllllllllllllIlllllllIIlllll, lllllllllllllllllIlllllllIIlllll.peer, lllllllllllllllllIlllllllIlIIIIl, lllllllllllllllllIlllllllIlIIIII);
    }

    public void read(long llllllllllllllllllIIIIlIIlllIlIl, byte[] llllllllllllllllllIIIIlIIlllIlII, int llllllllllllllllllIIIIlIIlllIIll, int llllllllllllllllllIIIIlIIlllIlll) {
        Pointer llllllllllllllllllIIIIlIIllllIll;
        Native.read(llllllllllllllllllIIIIlIIllllIll, llllllllllllllllllIIIIlIIllllIll.peer, llllllllllllllllllIIIIlIIlllIlIl, llllllllllllllllllIIIIlIIlllIlII, llllllllllllllllllIIIIlIIlllIIll, llllllllllllllllllIIIIlIIlllIlll);
    }

    public static final Pointer createConstant(int llllllllllllllllllIIIIlIlIllIIII) {
        return new Opaque((long)llllllllllllllllllIIIIlIlIllIIII & 0xFFFFFFFFFFFFFFFFL);
    }

    public byte getByte(long llllllllllllllllllIIIIIlIIllIlIl) {
        Pointer llllllllllllllllllIIIIIlIIlllIII;
        return Native.getByte(llllllllllllllllllIIIIIlIIlllIII, llllllllllllllllllIIIIIlIIlllIII.peer, llllllllllllllllllIIIIIlIIllIlIl);
    }

    public short[] getShortArray(long llllllllllllllllllIIIIIIllIIIIII, int llllllllllllllllllIIIIIIlIlllIll) {
        Pointer llllllllllllllllllIIIIIIllIIIIIl;
        short[] llllllllllllllllllIIIIIIlIlllllI = new short[llllllllllllllllllIIIIIIlIlllIll];
        llllllllllllllllllIIIIIIllIIIIIl.read(llllllllllllllllllIIIIIIllIIIIII, llllllllllllllllllIIIIIIlIlllllI, 0, llllllllllllllllllIIIIIIlIlllIll);
        return llllllllllllllllllIIIIIIlIlllllI;
    }

    private static class Opaque
    extends Pointer {
        private final /* synthetic */ String MSG;

        @Override
        public void read(long lIIIlIIIIIllIlI, byte[] lIIIlIIIIIllIIl, int lIIIlIIIIIllIII, int lIIIlIIIIIlIlll) {
            Opaque lIIIlIIIIIlIllI;
            throw new UnsupportedOperationException(lIIIlIIIIIlIllI.MSG);
        }

        @Override
        public void write(long lIIIIllllIIIllI, long[] lIIIIllllIIIlIl, int lIIIIllllIIIlII, int lIIIIllllIIIIll) {
            Opaque lIIIIllllIIIIlI;
            throw new UnsupportedOperationException(lIIIIllllIIIIlI.MSG);
        }

        @Override
        public String dump(long lIIIIllIlIIIIll, int lIIIIllIlIIIIlI) {
            Opaque lIIIIllIlIIIIIl;
            throw new UnsupportedOperationException(lIIIIllIlIIIIIl.MSG);
        }

        private Opaque(long lIIIlIIIIlIllIl) {
            super(lIIIlIIIIlIllIl);
            Opaque lIIIlIIIIlIlllI;
            lIIIlIIIIlIlllI.MSG = String.valueOf(new StringBuilder().append("This pointer is opaque: ").append(lIIIlIIIIlIlllI));
        }

        @Override
        public Pointer share(long lIIIlIIIIlIlIII, long lIIIlIIIIlIIlll) {
            Opaque lIIIlIIIIlIlIIl;
            throw new UnsupportedOperationException(lIIIlIIIIlIlIIl.MSG);
        }

        @Override
        public void setPointer(long lIIIIllIlIllIIl, Pointer lIIIIllIlIllIII) {
            Opaque lIIIIllIlIllIlI;
            throw new UnsupportedOperationException(lIIIIllIlIllIlI.MSG);
        }

        @Override
        public short getShort(long lIIIIlllIIlllIl) {
            Opaque lIIIIlllIIlllII;
            throw new UnsupportedOperationException(lIIIIlllIIlllII.MSG);
        }

        @Override
        public float getFloat(long lIIIIlllIIlIIIl) {
            Opaque lIIIIlllIIlIIlI;
            throw new UnsupportedOperationException(lIIIIlllIIlIIlI.MSG);
        }

        @Override
        public void read(long lIIIIllllllIIII, double[] lIIIIlllllIllll, int lIIIIlllllIlllI, int lIIIIlllllIllIl) {
            Opaque lIIIIlllllIllII;
            throw new UnsupportedOperationException(lIIIIlllllIllII.MSG);
        }

        @Override
        public void read(long lIIIlIIIIIIIlIl, int[] lIIIlIIIIIIIlII, int lIIIlIIIIIIIIll, int lIIIlIIIIIIIIlI) {
            Opaque lIIIlIIIIIIIIIl;
            throw new UnsupportedOperationException(lIIIlIIIIIIIIIl.MSG);
        }

        @Override
        public long indexOf(long lIIIlIIIIIlllll, byte lIIIlIIIIIllllI) {
            Opaque lIIIlIIIIlIIIII;
            throw new UnsupportedOperationException(lIIIlIIIIlIIIII.MSG);
        }

        @Override
        public byte getByte(long lIIIIlllIlIIlIl) {
            Opaque lIIIIlllIlIIllI;
            throw new UnsupportedOperationException(lIIIIlllIlIIllI.MSG);
        }

        @Override
        public void write(long lIIIIllllIIllIl, int[] lIIIIllllIIllII, int lIIIIllllIIlIll, int lIIIIllllIIlIlI) {
            Opaque lIIIIllllIIlIIl;
            throw new UnsupportedOperationException(lIIIIllllIIlIIl.MSG);
        }

        @Override
        public int getInt(long lIIIIlllIIllIIl) {
            Opaque lIIIIlllIIllIII;
            throw new UnsupportedOperationException(lIIIIlllIIllIII.MSG);
        }

        @Override
        public void clear(long lIIIlIIIIlIIIll) {
            Opaque lIIIlIIIIlIIlII;
            throw new UnsupportedOperationException(lIIIlIIIIlIIlII.MSG);
        }

        @Override
        public void read(long lIIIIllllllIlll, float[] lIIIIllllllIllI, int lIIIIllllllIlIl, int lIIIIllllllIlII) {
            Opaque lIIIIlllllllIII;
            throw new UnsupportedOperationException(lIIIIlllllllIII.MSG);
        }

        @Override
        public void setMemory(long lIIIIllIlIIlIIl, long lIIIIllIlIIlIII, byte lIIIIllIlIIIlll) {
            Opaque lIIIIllIlIIIllI;
            throw new UnsupportedOperationException(lIIIIllIlIIIllI.MSG);
        }

        @Override
        public void setByte(long lIIIIllIlllllII, byte lIIIIllIllllIll) {
            Opaque lIIIIllIllllIlI;
            throw new UnsupportedOperationException(lIIIIllIllllIlI.MSG);
        }

        @Override
        public void read(long lIIIlIIIIIIllII, short[] lIIIlIIIIIIlIll, int lIIIlIIIIIIlIlI, int lIIIlIIIIIIlIIl) {
            Opaque lIIIlIIIIIIlIII;
            throw new UnsupportedOperationException(lIIIlIIIIIIlIII.MSG);
        }

        @Override
        public void setLong(long lIIIIllIllIlIII, long lIIIIllIllIIlll) {
            Opaque lIIIIllIllIIllI;
            throw new UnsupportedOperationException(lIIIIllIllIIllI.MSG);
        }

        @Override
        public char getChar(long lIIIIlllIlIIIIl) {
            Opaque lIIIIlllIlIIIlI;
            throw new UnsupportedOperationException(lIIIIlllIlIIIlI.MSG);
        }

        @Override
        public void write(long lIIIIllllIlIlII, short[] lIIIIllllIlIIll, int lIIIIllllIlIIlI, int lIIIIllllIlIIIl) {
            Opaque lIIIIllllIlIIII;
            throw new UnsupportedOperationException(lIIIIllllIlIIII.MSG);
        }

        @Override
        public void write(long lIIIIlllllIIIlI, byte[] lIIIIlllllIIIIl, int lIIIIlllllIIIII, int lIIIIllllIlllll) {
            Opaque lIIIIllllIllllI;
            throw new UnsupportedOperationException(lIIIIllllIllllI.MSG);
        }

        @Override
        public void setFloat(long lIIIIllIllIIIll, float lIIIIllIllIIIlI) {
            Opaque lIIIIllIllIIlII;
            throw new UnsupportedOperationException(lIIIIllIllIIlII.MSG);
        }

        @Override
        public void write(long lIIIIlllIllllll, float[] lIIIIlllIlllllI, int lIIIIlllIllllIl, int lIIIIlllIllllII) {
            Opaque lIIIIlllIlllIll;
            throw new UnsupportedOperationException(lIIIIlllIlllIll.MSG);
        }

        @Override
        public void setString(long lIIIIllIlIlIlII, String lIIIIllIlIlIIll, String lIIIIllIlIlIIlI) {
            Opaque lIIIIllIlIlIIIl;
            throw new UnsupportedOperationException(lIIIIllIlIlIIIl.MSG);
        }

        @Override
        public void read(long lIIIIlllllllllI, long[] lIIIIllllllllIl, int lIIIIllllllllII, int lIIIIlllllllIll) {
            Opaque lIIIIllllllllll;
            throw new UnsupportedOperationException(lIIIIllllllllll.MSG);
        }

        @Override
        public Pointer getPointer(long lIIIIlllIIIlIIl) {
            Opaque lIIIIlllIIIlIII;
            throw new UnsupportedOperationException(lIIIIlllIIIlIII.MSG);
        }

        @Override
        public void read(long lIIIIlllllIlIIl, Pointer[] lIIIIlllllIlIII, int lIIIIlllllIIlll, int lIIIIlllllIIllI) {
            Opaque lIIIIlllllIIlIl;
            throw new UnsupportedOperationException(lIIIIlllllIIlIl.MSG);
        }

        @Override
        public void write(long lIIIIllllIllIll, char[] lIIIIllllIllIlI, int lIIIIllllIllIIl, int lIIIIllllIllIII) {
            Opaque lIIIIllllIlllII;
            throw new UnsupportedOperationException(lIIIIllllIlllII.MSG);
        }

        @Override
        public ByteBuffer getByteBuffer(long lIIIIlllIlIlIlI, long lIIIIlllIlIlIIl) {
            Opaque lIIIIlllIlIlIll;
            throw new UnsupportedOperationException(lIIIIlllIlIlIll.MSG);
        }

        @Override
        public void setDouble(long lIIIIllIlIllllI, double lIIIIllIlIlllIl) {
            Opaque lIIIIllIlIlllll;
            throw new UnsupportedOperationException(lIIIIllIlIlllll.MSG);
        }

        @Override
        public void write(long lIIIIlllIllIIIl, Pointer[] lIIIIlllIllIIII, int lIIIIlllIlIllll, int lIIIIlllIlIlllI) {
            Opaque lIIIIlllIlIllIl;
            throw new UnsupportedOperationException(lIIIIlllIlIllIl.MSG);
        }

        @Override
        public void setWideString(long lIIIIllIlIIlllI, String lIIIIllIlIIllIl) {
            Opaque lIIIIllIlIIllll;
            throw new UnsupportedOperationException(lIIIIllIlIIllll.MSG);
        }

        @Override
        public void setInt(long lIIIIllIllIllIl, int lIIIIllIllIllII) {
            Opaque lIIIIllIllIlllI;
            throw new UnsupportedOperationException(lIIIIllIllIlllI.MSG);
        }

        @Override
        public void read(long lIIIlIIIIIlIIll, char[] lIIIlIIIIIlIIlI, int lIIIlIIIIIlIIIl, int lIIIlIIIIIlIIII) {
            Opaque lIIIlIIIIIlIlII;
            throw new UnsupportedOperationException(lIIIlIIIIIlIlII.MSG);
        }

        @Override
        public String getWideString(long lIIIIlllIIIIIII) {
            Opaque lIIIIllIlllllll;
            throw new UnsupportedOperationException(lIIIIllIlllllll.MSG);
        }

        @Override
        public void setChar(long lIIIIllIlllIlll, char lIIIIllIlllIllI) {
            Opaque lIIIIllIllllIII;
            throw new UnsupportedOperationException(lIIIIllIllllIII.MSG);
        }

        @Override
        public void setShort(long lIIIIllIlllIIlI, short lIIIIllIlllIIIl) {
            Opaque lIIIIllIlllIIll;
            throw new UnsupportedOperationException(lIIIIllIlllIIll.MSG);
        }

        @Override
        public long getLong(long lIIIIlllIIlIlIl) {
            Opaque lIIIIlllIIlIlII;
            throw new UnsupportedOperationException(lIIIIlllIIlIlII.MSG);
        }

        @Override
        public String getString(long lIIIIlllIIIIlIl, String lIIIIlllIIIIlII) {
            Opaque lIIIIlllIIIIIll;
            throw new UnsupportedOperationException(lIIIIlllIIIIIll.MSG);
        }

        @Override
        public void write(long lIIIIlllIlllIII, double[] lIIIIlllIllIlll, int lIIIIlllIllIllI, int lIIIIlllIllIlIl) {
            Opaque lIIIIlllIllIlII;
            throw new UnsupportedOperationException(lIIIIlllIllIlII.MSG);
        }

        @Override
        public double getDouble(long lIIIIlllIIIllIl) {
            Opaque lIIIIlllIIIlllI;
            throw new UnsupportedOperationException(lIIIIlllIIIlllI.MSG);
        }

        @Override
        public String toString() {
            Opaque lIIIIllIIllllll;
            return String.valueOf(new StringBuilder().append("const@0x").append(Long.toHexString(lIIIIllIIllllll.peer)));
        }
    }
}

