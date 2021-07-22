/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Function;
import com.sun.jna.ToNativeContext;

public class FunctionParameterContext
extends ToNativeContext {
    private /* synthetic */ Function function;
    private /* synthetic */ Object[] args;
    private /* synthetic */ int index;

    public Function getFunction() {
        FunctionParameterContext lIlIlllllIIIIl;
        return lIlIlllllIIIIl.function;
    }

    FunctionParameterContext(Function lIlIlllllIlIIl, Object[] lIlIlllllIIlII, int lIlIlllllIIIll) {
        FunctionParameterContext lIlIlllllIIllI;
        lIlIlllllIIllI.function = lIlIlllllIlIIl;
        lIlIlllllIIllI.args = lIlIlllllIIlII;
        lIlIlllllIIllI.index = lIlIlllllIIIll;
    }

    public int getParameterIndex() {
        FunctionParameterContext lIlIllllIllIlI;
        return lIlIllllIllIlI.index;
    }

    public Object[] getParameters() {
        FunctionParameterContext lIlIllllIlllIl;
        return lIlIllllIlllIl.args;
    }
}

