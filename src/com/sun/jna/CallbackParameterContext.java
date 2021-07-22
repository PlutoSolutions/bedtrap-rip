/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import java.lang.reflect.Method;

public class CallbackParameterContext
extends FromNativeContext {
    private /* synthetic */ Method method;
    private /* synthetic */ Object[] args;
    private /* synthetic */ int index;

    CallbackParameterContext(Class<?> lIllIlIllIllIl, Method lIllIlIllIllII, Object[] lIllIlIllIlIll, int lIllIlIllIlIlI) {
        super(lIllIlIllIllIl);
        CallbackParameterContext lIllIlIllIlIIl;
        lIllIlIllIlIIl.method = lIllIlIllIllII;
        lIllIlIllIlIIl.args = lIllIlIllIlIll;
        lIllIlIllIlIIl.index = lIllIlIllIlIlI;
    }

    public Object[] getArguments() {
        CallbackParameterContext lIllIlIllIIIII;
        return lIllIlIllIIIII.args;
    }

    public int getIndex() {
        CallbackParameterContext lIllIlIlIlllII;
        return lIllIlIlIlllII.index;
    }

    public Method getMethod() {
        CallbackParameterContext lIllIlIllIIIll;
        return lIllIlIllIIIll.method;
    }
}

