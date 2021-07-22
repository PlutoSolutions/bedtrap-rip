/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Callback {
    public static final /* synthetic */ List<String> FORBIDDEN_NAMES;
    public static final /* synthetic */ String METHOD_NAME;

    static {
        METHOD_NAME = "callback";
        FORBIDDEN_NAMES = Collections.unmodifiableList(Arrays.asList("hashCode", "equals", "toString"));
    }

    public static interface UncaughtExceptionHandler {
        public void uncaughtException(Callback var1, Throwable var2);
    }
}

