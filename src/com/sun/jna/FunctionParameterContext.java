/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

import com.sun.jna.Function;
import com.sun.jna.ToNativeContext;

public class FunctionParameterContext
extends ToNativeContext {
    private Function function;
    private Object[] args;
    private int index;

    public Function getFunction() {
        return this.function;
    }

    FunctionParameterContext(Function function, Object[] objectArray, int n) {
        this.function = function;
        this.args = objectArray;
        this.index = n;
    }

    public int getParameterIndex() {
        return this.index;
    }

    public Object[] getParameters() {
        return this.args;
    }
}

