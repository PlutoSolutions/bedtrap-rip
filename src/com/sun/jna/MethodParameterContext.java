/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Function;
import com.sun.jna.FunctionParameterContext;
import java.lang.reflect.Method;

public class MethodParameterContext
extends FunctionParameterContext {
    private /* synthetic */ Method method;

    public Method getMethod() {
        MethodParameterContext llllllllllllllllllIlIlIlIIlIllll;
        return llllllllllllllllllIlIlIlIIlIllll.method;
    }

    MethodParameterContext(Function llllllllllllllllllIlIlIlIIlllIIl, Object[] llllllllllllllllllIlIlIlIIlllIII, int llllllllllllllllllIlIlIlIIllIlll, Method llllllllllllllllllIlIlIlIIllIIIl) {
        super(llllllllllllllllllIlIlIlIIlllIIl, llllllllllllllllllIlIlIlIIlllIII, llllllllllllllllllIlIlIlIIllIlll);
        MethodParameterContext llllllllllllllllllIlIlIlIIllIlIl;
        llllllllllllllllllIlIlIlIIllIlIl.method = llllllllllllllllllIlIlIlIIllIIIl;
    }
}

