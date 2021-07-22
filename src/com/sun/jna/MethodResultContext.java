/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Function;
import com.sun.jna.FunctionResultContext;
import java.lang.reflect.Method;

public class MethodResultContext
extends FunctionResultContext {
    private final Method method;

    MethodResultContext(Class<?> class_, Function function, Object[] arrobject, Method method) {
        super(class_, function, arrobject);
        this.method = method;
    }

    public Method getMethod() {
        return this.method;
    }
}

