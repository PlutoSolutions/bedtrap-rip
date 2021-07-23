/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

import com.sun.jna.Callback;

public class CallbackThreadInitializer {
    private String name;
    private boolean detach;
    private boolean daemon;
    private ThreadGroup group;

    public boolean isDaemon(Callback callback) {
        return this.daemon;
    }

    public CallbackThreadInitializer(boolean bl, boolean bl2) {
        this(bl, bl2, null);
    }

    public ThreadGroup getThreadGroup(Callback callback) {
        return this.group;
    }

    public String getName(Callback callback) {
        return this.name;
    }

    public CallbackThreadInitializer() {
        this(true);
    }

    public CallbackThreadInitializer(boolean bl, boolean bl2, String string) {
        this(bl, bl2, string, null);
    }

    public CallbackThreadInitializer(boolean bl) {
        this(bl, false);
    }

    public CallbackThreadInitializer(boolean bl, boolean bl2, String string, ThreadGroup threadGroup) {
        this.daemon = bl;
        this.detach = bl2;
        this.name = string;
        this.group = threadGroup;
    }

    public boolean detach(Callback callback) {
        return this.detach;
    }
}

