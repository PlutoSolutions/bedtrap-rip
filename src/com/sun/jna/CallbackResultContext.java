/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.ToNativeContext;
import java.lang.reflect.Method;

public class CallbackResultContext
extends ToNativeContext {
    private /* synthetic */ Method method;

    CallbackResultContext(Method lIlllIlllIIIlII) {
        CallbackResultContext lIlllIlllIIIIll;
        lIlllIlllIIIIll.method = lIlllIlllIIIlII;
    }

    public Method getMethod() {
        CallbackResultContext lIlllIllIllllll;
        return lIlllIllIllllll.method;
    }
}

