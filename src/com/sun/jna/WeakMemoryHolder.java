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
    ReferenceQueue<Object> referenceQueue = new ReferenceQueue();
    IdentityHashMap<Reference<Object>, Memory> backingMap = new IdentityHashMap();

    public synchronized void clean() {
        Reference<Object> reference = this.referenceQueue.poll();
        while (reference != null) {
            this.backingMap.remove(reference);
            reference = this.referenceQueue.poll();
        }
    }

    public synchronized void put(Object object, Memory memory) {
        this.clean();
        WeakReference<Object> weakReference = new WeakReference<Object>(object, this.referenceQueue);
        this.backingMap.put(weakReference, memory);
    }
}

