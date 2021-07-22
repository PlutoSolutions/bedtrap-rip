/*
 * Decompiled with CFR 0.150.
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

    FunctionResultContext(Class<?> class_, Function function, Object[] arrobject) {
        super(class_);
        this.function = function;
        this.args = arrobject;
    }

    public Object[] getArguments() {
        return this.args;
    }
}

