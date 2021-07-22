/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.misc;

import java.util.ArrayDeque;
import java.util.Queue;
import minegame159.meteorclient.utils.misc.Producer;

public class Pool<T> {
    private final /* synthetic */ Producer<T> producer;
    private final /* synthetic */ Queue<T> items;

    public Pool(Producer<T> lllllllllllllllllIIlIlIlIIlIlIIl) {
        Pool lllllllllllllllllIIlIlIlIIlIlIlI;
        lllllllllllllllllIIlIlIlIIlIlIlI.items = new ArrayDeque<T>();
        lllllllllllllllllIIlIlIlIIlIlIlI.producer = lllllllllllllllllIIlIlIlIIlIlIIl;
    }

    public synchronized void free(T lllllllllllllllllIIlIlIlIIIllllI) {
        Pool lllllllllllllllllIIlIlIlIIlIIIIl;
        lllllllllllllllllIIlIlIlIIlIIIIl.items.offer(lllllllllllllllllIIlIlIlIIIllllI);
    }

    public synchronized T get() {
        Pool lllllllllllllllllIIlIlIlIIlIIlII;
        if (lllllllllllllllllIIlIlIlIIlIIlII.items.size() > 0) {
            return lllllllllllllllllIIlIlIlIIlIIlII.items.poll();
        }
        return lllllllllllllllllIIlIlIlIIlIIlII.producer.create();
    }
}

