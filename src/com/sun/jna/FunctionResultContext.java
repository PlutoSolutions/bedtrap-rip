/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import com.sun.jna.Function;

public class FunctionResultContext
extends FromNativeContext {
    private Object[] args;
    private Function function;

    public Function getFunction() {
        return this.function;
    }

    FunctionResultContext(Class<?> clazz, Function function, Object[] objectArray) {
        super(clazz);
        this.function = function;
        this.args = objectArray;
    }

    public Object[] getArguments() {
        return this.args;
    }
}

