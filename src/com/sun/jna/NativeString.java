/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import java.nio.CharBuffer;

class NativeString
implements CharSequence,
Comparable {
    static final /* synthetic */ String WIDE_STRING;
    private /* synthetic */ Pointer pointer;
    private /* synthetic */ String encoding;

    static {
        WIDE_STRING = "--WIDE-STRING--";
    }

    public NativeString(String llllllllllllllllllIlIllIlIllllll, boolean llllllllllllllllllIlIllIlIlllllI) {
        llllllllllllllllllIlIllIlIllllIl(llllllllllllllllllIlIllIlIllllll, llllllllllllllllllIlIllIlIlllllI ? "--WIDE-STRING--" : Native.getDefaultStringEncoding());
        NativeString llllllllllllllllllIlIllIlIllllIl;
    }

    public Pointer getPointer() {
        NativeString llllllllllllllllllIlIllIlIIlIlII;
        return llllllllllllllllllIlIllIlIIlIlII.pointer;
    }

    public NativeString(String llllllllllllllllllIlIllIllIIIlII) {
        llllllllllllllllllIlIllIllIIIlIl(llllllllllllllllllIlIllIllIIIlII, Native.getDefaultStringEncoding());
        NativeString llllllllllllllllllIlIllIllIIIlIl;
    }

    public int hashCode() {
        NativeString llllllllllllllllllIlIllIlIlIIlIl;
        return llllllllllllllllllIlIllIlIlIIlIl.toString().hashCode();
    }

    @Override
    public String toString() {
        NativeString llllllllllllllllllIlIllIlIIllIll;
        boolean llllllllllllllllllIlIllIlIIllIlI = "--WIDE-STRING--".equals(llllllllllllllllllIlIllIlIIllIll.encoding);
        String llllllllllllllllllIlIllIlIIllIIl = llllllllllllllllllIlIllIlIIllIlI ? "const wchar_t*" : "const char*";
        llllllllllllllllllIlIllIlIIllIIl = String.valueOf(new StringBuilder().append(llllllllllllllllllIlIllIlIIllIIl).append("(").append(llllllllllllllllllIlIllIlIIllIlI ? llllllllllllllllllIlIllIlIIllIll.pointer.getWideString(0L) : llllllllllllllllllIlIllIlIIllIll.pointer.getString(0L, llllllllllllllllllIlIllIlIIllIll.encoding)).append(")"));
        return llllllllllllllllllIlIllIlIIllIIl;
    }

    @Override
    public CharSequence subSequence(int llllllllllllllllllIlIllIlIIIIIlI, int llllllllllllllllllIlIllIlIIIIIIl) {
        NativeString llllllllllllllllllIlIllIlIIIIllI;
        return CharBuffer.wrap(llllllllllllllllllIlIllIlIIIIllI.toString()).subSequence(llllllllllllllllllIlIllIlIIIIIlI, llllllllllllllllllIlIllIlIIIIIIl);
    }

    public NativeString(String llllllllllllllllllIlIllIlIlIlIlI, String llllllllllllllllllIlIllIlIlIllII) {
        NativeString llllllllllllllllllIlIllIlIlIlIll;
        if (llllllllllllllllllIlIllIlIlIlIlI == null) {
            throw new NullPointerException("String must not be null");
        }
        llllllllllllllllllIlIllIlIlIlIll.encoding = llllllllllllllllllIlIllIlIlIllII;
        if ("--WIDE-STRING--".equals(llllllllllllllllllIlIllIlIlIlIll.encoding)) {
            int llllllllllllllllllIlIllIlIllIIII = (llllllllllllllllllIlIllIlIlIlIlI.length() + 1) * Native.WCHAR_SIZE;
            llllllllllllllllllIlIllIlIlIlIll.pointer = llllllllllllllllllIlIllIlIlIlIll.new StringMemory(llllllllllllllllllIlIllIlIllIIII);
            llllllllllllllllllIlIllIlIlIlIll.pointer.setWideString(0L, llllllllllllllllllIlIllIlIlIlIlI);
        } else {
            byte[] llllllllllllllllllIlIllIlIlIllll = Native.getBytes(llllllllllllllllllIlIllIlIlIlIlI, llllllllllllllllllIlIllIlIlIllII);
            llllllllllllllllllIlIllIlIlIlIll.pointer = llllllllllllllllllIlIllIlIlIlIll.new StringMemory(llllllllllllllllllIlIllIlIlIllll.length + 1);
            llllllllllllllllllIlIllIlIlIlIll.pointer.write(0L, llllllllllllllllllIlIllIlIlIllll, 0, llllllllllllllllllIlIllIlIlIllll.length);
            llllllllllllllllllIlIllIlIlIlIll.pointer.setByte(llllllllllllllllllIlIllIlIlIllll.length, (byte)0);
        }
    }

    public boolean equals(Object llllllllllllllllllIlIllIlIIlllll) {
        if (llllllllllllllllllIlIllIlIIlllll instanceof CharSequence) {
            NativeString llllllllllllllllllIlIllIlIlIIIlI;
            return llllllllllllllllllIlIllIlIlIIIlI.compareTo(llllllllllllllllllIlIllIlIIlllll) == 0;
        }
        return false;
    }

    public int compareTo(Object llllllllllllllllllIlIllIIllllIll) {
        NativeString llllllllllllllllllIlIllIIllllllI;
        if (llllllllllllllllllIlIllIIllllIll == null) {
            return 1;
        }
        return llllllllllllllllllIlIllIIllllllI.toString().compareTo(llllllllllllllllllIlIllIIllllIll.toString());
    }

    @Override
    public int length() {
        NativeString llllllllllllllllllIlIllIlIIIlIll;
        return llllllllllllllllllIlIllIlIIIlIll.toString().length();
    }

    @Override
    public char charAt(int llllllllllllllllllIlIllIlIIIllIl) {
        NativeString llllllllllllllllllIlIllIlIIIlllI;
        return llllllllllllllllllIlIllIlIIIlllI.toString().charAt(llllllllllllllllllIlIllIlIIIllIl);
    }

    public NativeString(WString llllllllllllllllllIlIllIlIllIlll) {
        llllllllllllllllllIlIllIlIlllIII(llllllllllllllllllIlIllIlIllIlll.toString(), "--WIDE-STRING--");
        NativeString llllllllllllllllllIlIllIlIlllIII;
    }

    private class StringMemory
    extends Memory {
        public StringMemory(long lIIIIllIIIlIllI) {
            StringMemory lIIIIllIIIlIlIl;
            super(lIIIIllIIIlIllI);
        }

        @Override
        public String toString() {
            StringMemory lIIIIllIIIlIIII;
            return lIIIIllIIIlIIII.NativeString.this.toString();
        }
    }
}

