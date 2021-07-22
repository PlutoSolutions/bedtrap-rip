/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import java.lang.reflect.Method;

public class CallbackParameterContext
extends FromNativeContext {
    private Method method;
    private Object[] args;
    private int index;

    CallbackParameterContext(Class<?> class_, Method method, Object[] arrobject, int n) {
        super(class_);
        this.method = method;
        this.args = arrobject;
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

