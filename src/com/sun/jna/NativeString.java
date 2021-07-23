/*
 * Decompiled with CFR 0.151.
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
    static final String WIDE_STRING = "--WIDE-STRING--";
    private Pointer pointer;
    private String encoding;

    public NativeString(String string, boolean bl) {
        this(string, bl ? "--WIDE-STRING--" : Native.getDefaultStringEncoding());
    }

    public Pointer getPointer() {
        return this.pointer;
    }

    public NativeString(String string) {
        this(string, Native.getDefaultStringEncoding());
    }

    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        boolean bl = "--WIDE-STRING--".equals(this.encoding);
        String string = bl ? "const wchar_t*" : "const char*";
        string = String.valueOf(new StringBuilder().append(string).append("(").append(bl ? this.pointer.getWideString(0L) : this.pointer.getString(0L, this.encoding)).append(")"));
        return string;
    }

    @Override
    public CharSequence subSequence(int n, int n2) {
        return CharBuffer.wrap(this.toString()).subSequence(n, n2);
    }

    public NativeString(String string, String string2) {
        if (string == null) {
            throw new NullPointerException("String must not be null");
        }
        this.encoding = string2;
        if ("--WIDE-STRING--".equals(this.encoding)) {
            int n = (string.length() + 1) * Native.WCHAR_SIZE;
            this.pointer = new StringMemory(this, n);
            this.pointer.setWideString(0L, string);
        } else {
            byte[] byArray = Native.getBytes(string, string2);
            this.pointer = new StringMemory(this, byArray.length + 1);
            this.pointer.write(0L, byArray, 0, byArray.length);
            this.pointer.setByte(byArray.length, (byte)0);
        }
    }

    public boolean equals(Object object) {
        if (object instanceof CharSequence) {
            return this.compareTo(object) == 0;
        }
        return false;
    }

    public int compareTo(Object object) {
        if (object == null) {
            return 1;
        }
        return this.toString().compareTo(object.toString());
    }

    @Override
    public int length() {
        return this.toString().length();
    }

    @Override
    public char charAt(int n) {
        return this.toString().charAt(n);
    }

    public NativeString(WString wString) {
        this(wString.toString(), "--WIDE-STRING--");
    }

    private class StringMemory
    extends Memory {
        final NativeString this$0;

        public StringMemory(NativeString nativeString, long l) {
            this.this$0 = nativeString;
            super(l);
        }

        @Override
        public String toString() {
            return this.this$0.toString();
        }
    }
}

