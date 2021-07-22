/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import com.sun.jna.Function;

public class FunctionResultContext
extends FromNativeContext {
    private /* synthetic */ Object[] args;
    private /* synthetic */ Function function;

    public Function getFunction() {
        FunctionResultContext llllllllllllllllIllIIIIllIIIIIIl;
        return llllllllllllllllIllIIIIllIIIIIIl.function;
    }

    FunctionResultContext(Class<?> llllllllllllllllIllIIIIllIIIlIIl, Function llllllllllllllllIllIIIIllIIIIlII, Object[] llllllllllllllllIllIIIIllIIIIIll) {
        super(llllllllllllllllIllIIIIllIIIlIIl);
        FunctionResultContext llllllllllllllllIllIIIIllIIIIllI;
        llllllllllllllllIllIIIIllIIIIllI.function = llllllllllllllllIllIIIIllIIIIlII;
        llllllllllllllllIllIIIIllIIIIllI.args = llllllllllllllllIllIIIIllIIIIIll;
    }

    public Object[] getArguments() {
        FunctionResultContext llllllllllllllllIllIIIIlIllllllI;
        return llllllllllllllllIllIIIIlIllllllI.args;
    }
}

