/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Callback;

public class CallbackThreadInitializer {
    private /* synthetic */ String name;
    private /* synthetic */ boolean detach;
    private /* synthetic */ boolean daemon;
    private /* synthetic */ ThreadGroup group;

    public boolean isDaemon(Callback llllllllllllllllllIlllllIIllIlIl) {
        CallbackThreadInitializer llllllllllllllllllIlllllIIllIllI;
        return llllllllllllllllllIlllllIIllIllI.daemon;
    }

    public CallbackThreadInitializer(boolean llllllllllllllllllIlllllIlIlllII, boolean llllllllllllllllllIlllllIlIllllI) {
        llllllllllllllllllIlllllIllIIIII(llllllllllllllllllIlllllIlIlllII, llllllllllllllllllIlllllIlIllllI, null);
        CallbackThreadInitializer llllllllllllllllllIlllllIllIIIII;
    }

    public ThreadGroup getThreadGroup(Callback llllllllllllllllllIlllllIIlllIIl) {
        CallbackThreadInitializer llllllllllllllllllIlllllIIlllIII;
        return llllllllllllllllllIlllllIIlllIII.group;
    }

    public String getName(Callback llllllllllllllllllIlllllIIllllIl) {
        CallbackThreadInitializer llllllllllllllllllIlllllIIllllII;
        return llllllllllllllllllIlllllIIllllII.name;
    }

    public CallbackThreadInitializer() {
        llllllllllllllllllIlllllIllIlIll(true);
        CallbackThreadInitializer llllllllllllllllllIlllllIllIlIll;
    }

    public CallbackThreadInitializer(boolean llllllllllllllllllIlllllIlIlIIIl, boolean llllllllllllllllllIlllllIlIlIIII, String llllllllllllllllllIlllllIlIlIIll) {
        llllllllllllllllllIlllllIlIlIllI(llllllllllllllllllIlllllIlIlIIIl, llllllllllllllllllIlllllIlIlIIII, llllllllllllllllllIlllllIlIlIIll, null);
        CallbackThreadInitializer llllllllllllllllllIlllllIlIlIllI;
    }

    public CallbackThreadInitializer(boolean llllllllllllllllllIlllllIllIIlII) {
        llllllllllllllllllIlllllIllIIlll(llllllllllllllllllIlllllIllIIlII, false);
        CallbackThreadInitializer llllllllllllllllllIlllllIllIIlll;
    }

    public CallbackThreadInitializer(boolean llllllllllllllllllIlllllIlIIIIll, boolean llllllllllllllllllIlllllIlIIIIlI, String llllllllllllllllllIlllllIlIIIllI, ThreadGroup llllllllllllllllllIlllllIlIIIlIl) {
        CallbackThreadInitializer llllllllllllllllllIlllllIlIIlIIl;
        llllllllllllllllllIlllllIlIIlIIl.daemon = llllllllllllllllllIlllllIlIIIIll;
        llllllllllllllllllIlllllIlIIlIIl.detach = llllllllllllllllllIlllllIlIIIIlI;
        llllllllllllllllllIlllllIlIIlIIl.name = llllllllllllllllllIlllllIlIIIllI;
        llllllllllllllllllIlllllIlIIlIIl.group = llllllllllllllllllIlllllIlIIIlIl;
    }

    public boolean detach(Callback llllllllllllllllllIlllllIIllIIIl) {
        CallbackThreadInitializer llllllllllllllllllIlllllIIllIIlI;
        return llllllllllllllllllIlllllIIllIIlI.detach;
    }
}

