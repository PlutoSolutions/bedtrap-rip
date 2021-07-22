/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.win32;

import com.sun.jna.Function;
import com.sun.jna.FunctionMapper;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeMapped;
import com.sun.jna.NativeMappedConverter;
import com.sun.jna.Pointer;
import java.lang.reflect.Method;

public class StdCallFunctionMapper
implements FunctionMapper {
    @Override
    public String getFunctionName(NativeLibrary lIIlllllIllIllI, Method lIIlllllIllIlIl) {
        Class<?>[] lIIlllllIlllIlI;
        String lIIlllllIllllII = lIIlllllIllIlIl.getName();
        int lIIlllllIlllIll = 0;
        for (Class<?> lIIllllllIIIIll : lIIlllllIlllIlI = lIIlllllIllIlIl.getParameterTypes()) {
            StdCallFunctionMapper lIIlllllIllllll;
            lIIlllllIlllIll += lIIlllllIllllll.getArgumentNativeStackSize(lIIllllllIIIIll);
        }
        String lIIlllllIlllIIl = String.valueOf(new StringBuilder().append(lIIlllllIllllII).append("@").append(lIIlllllIlllIll));
        int lIIlllllIlllIII = 63;
        try {
            Function lIIllllllIIIIlI = lIIlllllIllIllI.getFunction(lIIlllllIlllIIl, lIIlllllIlllIII);
            lIIlllllIllllII = lIIllllllIIIIlI.getName();
        }
        catch (UnsatisfiedLinkError lIIllllllIIIIII) {
            try {
                Function lIIllllllIIIIIl = lIIlllllIllIllI.getFunction(String.valueOf(new StringBuilder().append("_").append(lIIlllllIlllIIl)), lIIlllllIlllIII);
                lIIlllllIllllII = lIIllllllIIIIIl.getName();
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                // empty catch block
            }
        }
        return lIIlllllIllllII;
    }

    protected int getArgumentNativeStackSize(Class<?> lIIllllllIIllll) {
        if (NativeMapped.class.isAssignableFrom(lIIllllllIIllll)) {
            lIIllllllIIllll = NativeMappedConverter.getInstance(lIIllllllIIllll).nativeType();
        }
        if (lIIllllllIIllll.isArray()) {
            return Pointer.SIZE;
        }
        try {
            return Native.getNativeSize(lIIllllllIIllll);
        }
        catch (IllegalArgumentException lIIllllllIlIIlI) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unknown native stack allocation size for ").append(lIIllllllIIllll)));
        }
    }

    public StdCallFunctionMapper() {
        StdCallFunctionMapper lIIllllllIlIlIl;
    }
}

