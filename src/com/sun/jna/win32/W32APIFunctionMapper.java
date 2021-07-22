/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.win32;

import com.sun.jna.FunctionMapper;
import com.sun.jna.NativeLibrary;
import java.lang.reflect.Method;

public class W32APIFunctionMapper
implements FunctionMapper {
    public static final /* synthetic */ FunctionMapper UNICODE;
    private final /* synthetic */ String suffix;
    public static final /* synthetic */ FunctionMapper ASCII;

    static {
        UNICODE = new W32APIFunctionMapper(true);
        ASCII = new W32APIFunctionMapper(false);
    }

    protected W32APIFunctionMapper(boolean llllllllllllllllIlIlllIlIIIlIlII) {
        W32APIFunctionMapper llllllllllllllllIlIlllIlIIIlIlll;
        llllllllllllllllIlIlllIlIIIlIlll.suffix = llllllllllllllllIlIlllIlIIIlIlII ? "W" : "A";
    }

    @Override
    public String getFunctionName(NativeLibrary llllllllllllllllIlIlllIlIIIIlIIl, Method llllllllllllllllIlIlllIlIIIIlIII) {
        String llllllllllllllllIlIlllIlIIIIlIll = llllllllllllllllIlIlllIlIIIIlIII.getName();
        if (!llllllllllllllllIlIlllIlIIIIlIll.endsWith("W") && !llllllllllllllllIlIlllIlIIIIlIll.endsWith("A")) {
            try {
                W32APIFunctionMapper llllllllllllllllIlIlllIlIIIIlIlI;
                llllllllllllllllIlIlllIlIIIIlIll = llllllllllllllllIlIlllIlIIIIlIIl.getFunction(String.valueOf(new StringBuilder().append(llllllllllllllllIlIlllIlIIIIlIll).append(llllllllllllllllIlIlllIlIIIIlIlI.suffix)), 63).getName();
            }
            catch (UnsatisfiedLinkError llllllllllllllllIlIlllIlIIIIIllI) {
                // empty catch block
            }
        }
        return llllllllllllllllIlIlllIlIIIIlIll;
    }
}

