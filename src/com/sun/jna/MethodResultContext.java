/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

import com.sun.jna.Function;
import com.sun.jna.FunctionResultContext;
import java.lang.reflect.Method;

public class MethodResultContext
extends FunctionResultContext {
    private final Method method;

    MethodResultContext(Class<?> clazz, Function function, Object[] objectArray, Method method) {
        super(clazz, function, objectArray);
        this.method = method;
    }

    public Method getMethod() {
        return this.method;
    }
}

