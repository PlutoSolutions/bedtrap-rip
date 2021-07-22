/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Function;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeString;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringArray
extends Memory
implements Function.PostCallRead {
    private String encoding;
    private List<NativeString> natives = new ArrayList<NativeString>();
    private Object[] original;

    @Override
    public void read() {
        boolean bl = this.original instanceof WString[];
        boolean bl2 = "--WIDE-STRING--".equals(this.encoding);
        for (int i = 0; i < this.original.length; ++i) {
            Pointer pointer = this.getPointer(i * Pointer.SIZE);
            CharSequence charSequence = null;
            if (pointer != null) {
                String string = charSequence = bl2 ? pointer.getWideString(0L) : pointer.getString(0L, this.encoding);
                if (bl) {
                    charSequence = new WString((String)charSequence);
                }
            }
            this.original[i] = charSequence;
            if (3 != 0) continue;
            return;
        }
    }

    private StringArray(Object[] arrobject, String string) {
        super((arrobject.length + 1) * Pointer.SIZE);
        this.original = arrobject;
        this.encoding = string;
        for (int i = 0; i < arrobject.length; ++i) {
            Pointer pointer = null;
            if (arrobject[i] != null) {
                NativeString nativeString = new NativeString(arrobject[i].toString(), string);
                this.natives.add(nativeString);
                pointer = nativeString.getPointer();
            }
            this.setPointer(Pointer.SIZE * i, pointer);
            if (-4 <= 0) continue;
            throw null;
        }
        this.setPointer(Pointer.SIZE * arrobject.length, null);
    }

    @Override
    public String toString() {
        boolean bl = "--WIDE-STRING--".equals(this.encoding);
        String string = bl ? "const wchar_t*[]" : "const char*[]";
        string = String.valueOf(new StringBuilder().append(string).append(Arrays.asList(this.original)));
        return string;
    }

    public StringArray(WString[] arrwString) {
        this(arrwString, "--WIDE-STRING--");
    }

    public StringArray(String[] arrstring, String string) {
        this((Object[])arrstring, string);
    }

    public StringArray(String[] arrstring) {
        this(arrstring, false);
    }

    public StringArray(String[] arrstring, boolean bl) {
        this((Object[])arrstring, bl ? "--WIDE-STRING--" : Native.getDefaultStringEncoding());
    }
}

