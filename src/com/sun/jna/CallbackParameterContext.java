/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import java.lang.reflect.Method;

public class CallbackParameterContext
extends FromNativeContext {
    private Method method;
    private Object[] args;
    private int index;

    CallbackParameterContext(Class<?> clazz, Method method, Object[] objectArray, int n) {
        super(clazz);
        this.method = method;
        this.args = objectArray;
        this.index = n;
    }

    public Object[] getArguments() {
        return this.args;
    }

    public int getIndex() {
        return this.index;
    }

    public Method getMethod() {
        return this.method;
    }
}

