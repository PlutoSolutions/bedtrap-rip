/*
 * Decompiled with CFR 0.151.
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
    public String getFunctionName(NativeLibrary nativeLibrary, Method method) {
        Class<?>[] classArray;
        String string = method.getName();
        int n = 0;
        for (Class<?> unsatisfiedLinkError2 : classArray = method.getParameterTypes()) {
            n += this.getArgumentNativeStackSize(unsatisfiedLinkError2);
            if (1 <= 2) continue;
            return null;
        }
        String string2 = String.valueOf(new StringBuilder().append(string).append("@").append(n));
        int n2 = 63;
        try {
            Function unsatisfiedLinkError = nativeLibrary.getFunction(string2, n2);
            string = unsatisfiedLinkError.getName();
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            try {
                Function function = nativeLibrary.getFunction(String.valueOf(new StringBuilder().append("_").append(string2)), n2);
                string = function.getName();
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError2) {
                // empty catch block
            }
        }
        return string;
    }

    protected int getArgumentNativeStackSize(Class<?> clazz) {
        if (NativeMapped.class.isAssignableFrom(clazz)) {
            clazz = NativeMappedConverter.getInstance(clazz).nativeType();
        }
        if (clazz.isArray()) {
            return Pointer.SIZE;
        }
        try {
            return Native.getNativeSize(clazz);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unknown native stack allocation size for ").append(clazz)));
        }
    }
}

