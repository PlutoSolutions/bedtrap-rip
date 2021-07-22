/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Memory;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.IdentityHashMap;

public class WeakMemoryHolder {
    /* synthetic */ ReferenceQueue<Object> referenceQueue;
    /* synthetic */ IdentityHashMap<Reference<Object>, Memory> backingMap;

    public synchronized void clean() {
        WeakMemoryHolder lllIIlllIlIIlII;
        Reference<Object> lllIIlllIlIIlIl = lllIIlllIlIIlII.referenceQueue.poll();
        while (lllIIlllIlIIlIl != null) {
            lllIIlllIlIIlII.backingMap.remove(lllIIlllIlIIlIl);
            lllIIlllIlIIlIl = lllIIlllIlIIlII.referenceQueue.poll();
        }
    }

    public synchronized void put(Object lllIIlllIlIlIlI, Memory lllIIlllIlIlIIl) {
        WeakMemoryHolder lllIIlllIlIlIll;
        lllIIlllIlIlIll.clean();
        WeakReference<Object> lllIIlllIlIllII = new WeakReference<Object>(lllIIlllIlIlIlI, lllIIlllIlIlIll.referenceQueue);
        lllIIlllIlIlIll.backingMap.put(lllIIlllIlIllII, lllIIlllIlIlIIl);
    }

    public WeakMemoryHolder() {
        WeakMemoryHolder lllIIlllIllIlIl;
        lllIIlllIllIlIl.referenceQueue = new ReferenceQueue();
        lllIIlllIllIlIl.backingMap = new IdentityHashMap();
    }
}

