/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Function;
import com.sun.jna.FunctionResultContext;
import java.lang.reflect.Method;

public class MethodResultContext
extends FunctionResultContext {
    private final /* synthetic */ Method method;

    MethodResultContext(Class<?> lIlIllIlIIlllI, Function lIlIllIlIIlIII, Object[] lIlIllIlIIIlll, Method lIlIllIlIIIllI) {
        super(lIlIllIlIIlllI, lIlIllIlIIlIII, lIlIllIlIIIlll);
        MethodResultContext lIlIllIlIIlIlI;
        lIlIllIlIIlIlI.method = lIlIllIlIIIllI;
    }

    public Method getMethod() {
        MethodResultContext lIlIllIlIIIIll;
        return lIlIllIlIIIIll.method;
    }
}

