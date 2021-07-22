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
    private /* synthetic */ String encoding;
    private /* synthetic */ List<NativeString> natives;
    private /* synthetic */ Object[] original;

    @Override
    public void read() {
        StringArray llllllllllllllllllIIllllllllIlll;
        boolean llllllllllllllllllIIlllllllllIIl = llllllllllllllllllIIllllllllIlll.original instanceof WString[];
        boolean llllllllllllllllllIIlllllllllIII = "--WIDE-STRING--".equals(llllllllllllllllllIIllllllllIlll.encoding);
        for (int llllllllllllllllllIIlllllllllIll = 0; llllllllllllllllllIIlllllllllIll < llllllllllllllllllIIllllllllIlll.original.length; ++llllllllllllllllllIIlllllllllIll) {
            Pointer llllllllllllllllllIIllllllllllIl = llllllllllllllllllIIllllllllIlll.getPointer(llllllllllllllllllIIlllllllllIll * Pointer.SIZE);
            CharSequence llllllllllllllllllIIllllllllllII = null;
            if (llllllllllllllllllIIllllllllllIl != null) {
                String string = llllllllllllllllllIIllllllllllII = llllllllllllllllllIIlllllllllIII ? llllllllllllllllllIIllllllllllIl.getWideString(0L) : llllllllllllllllllIIllllllllllIl.getString(0L, llllllllllllllllllIIllllllllIlll.encoding);
                if (llllllllllllllllllIIlllllllllIIl) {
                    llllllllllllllllllIIllllllllllII = new WString((String)llllllllllllllllllIIllllllllllII);
                }
            }
            llllllllllllllllllIIllllllllIlll.original[llllllllllllllllllIIlllllllllIll] = llllllllllllllllllIIllllllllllII;
        }
    }

    private StringArray(Object[] llllllllllllllllllIlIIIIIIIIlIII, String llllllllllllllllllIlIIIIIIIIlIlI) {
        super((llllllllllllllllllIlIIIIIIIIlIII.length + 1) * Pointer.SIZE);
        StringArray llllllllllllllllllIlIIIIIIIIlIIl;
        llllllllllllllllllIlIIIIIIIIlIIl.natives = new ArrayList<NativeString>();
        llllllllllllllllllIlIIIIIIIIlIIl.original = llllllllllllllllllIlIIIIIIIIlIII;
        llllllllllllllllllIlIIIIIIIIlIIl.encoding = llllllllllllllllllIlIIIIIIIIlIlI;
        for (int llllllllllllllllllIlIIIIIIIIllIl = 0; llllllllllllllllllIlIIIIIIIIllIl < llllllllllllllllllIlIIIIIIIIlIII.length; ++llllllllllllllllllIlIIIIIIIIllIl) {
            Pointer llllllllllllllllllIlIIIIIIIIlllI = null;
            if (llllllllllllllllllIlIIIIIIIIlIII[llllllllllllllllllIlIIIIIIIIllIl] != null) {
                NativeString llllllllllllllllllIlIIIIIIIIllll = new NativeString(llllllllllllllllllIlIIIIIIIIlIII[llllllllllllllllllIlIIIIIIIIllIl].toString(), llllllllllllllllllIlIIIIIIIIlIlI);
                llllllllllllllllllIlIIIIIIIIlIIl.natives.add(llllllllllllllllllIlIIIIIIIIllll);
                llllllllllllllllllIlIIIIIIIIlllI = llllllllllllllllllIlIIIIIIIIllll.getPointer();
            }
            llllllllllllllllllIlIIIIIIIIlIIl.setPointer(Pointer.SIZE * llllllllllllllllllIlIIIIIIIIllIl, llllllllllllllllllIlIIIIIIIIlllI);
        }
        llllllllllllllllllIlIIIIIIIIlIIl.setPointer(Pointer.SIZE * llllllllllllllllllIlIIIIIIIIlIII.length, null);
    }

    @Override
    public String toString() {
        StringArray llllllllllllllllllIIlllllllIlIll;
        boolean llllllllllllllllllIIlllllllIllIl = "--WIDE-STRING--".equals(llllllllllllllllllIIlllllllIlIll.encoding);
        String llllllllllllllllllIIlllllllIllII = llllllllllllllllllIIlllllllIllIl ? "const wchar_t*[]" : "const char*[]";
        llllllllllllllllllIIlllllllIllII = String.valueOf(new StringBuilder().append(llllllllllllllllllIIlllllllIllII).append(Arrays.asList(llllllllllllllllllIIlllllllIlIll.original)));
        return llllllllllllllllllIIlllllllIllII;
    }

    public StringArray(WString[] llllllllllllllllllIlIIIIIIIlIllI) {
        llllllllllllllllllIlIIIIIIIlIlll(llllllllllllllllllIlIIIIIIIlIllI, "--WIDE-STRING--");
        StringArray llllllllllllllllllIlIIIIIIIlIlll;
    }

    public StringArray(String[] llllllllllllllllllIlIIIIIIlIIIII, String llllllllllllllllllIlIIIIIIIlllII) {
        llllllllllllllllllIlIIIIIIlIIIIl((Object[])llllllllllllllllllIlIIIIIIlIIIII, llllllllllllllllllIlIIIIIIIlllII);
        StringArray llllllllllllllllllIlIIIIIIlIIIIl;
    }

    public StringArray(String[] llllllllllllllllllIlIIIIIIllIIII) {
        llllllllllllllllllIlIIIIIIllIIIl(llllllllllllllllllIlIIIIIIllIIII, false);
        StringArray llllllllllllllllllIlIIIIIIllIIIl;
    }

    public StringArray(String[] llllllllllllllllllIlIIIIIIlIIllI, boolean llllllllllllllllllIlIIIIIIlIlIII) {
        llllllllllllllllllIlIIIIIIlIlIlI((Object[])llllllllllllllllllIlIIIIIIlIIllI, llllllllllllllllllIlIIIIIIlIlIII ? "--WIDE-STRING--" : Native.getDefaultStringEncoding());
        StringArray llllllllllllllllllIlIIIIIIlIlIlI;
    }
}

