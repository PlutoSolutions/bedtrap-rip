/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Function;
import com.sun.jna.FunctionParameterContext;
import java.lang.reflect.Method;

public class MethodParameterContext
extends FunctionParameterContext {
    private Method method;

    public Method getMethod() {
        return this.method;
    }

    MethodParameterContext(Function function, Object[] arrobject, int n, Method method) {
        super(function, arrobject, n);
        this.method = method;
    }
}

