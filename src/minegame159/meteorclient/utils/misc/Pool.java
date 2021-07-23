/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.misc;

import java.util.ArrayDeque;
import java.util.Queue;
import minegame159.meteorclient.utils.misc.Producer;

public class Pool<T> {
    private final Producer<T> producer;
    private final Queue<T> items = new ArrayDeque<T>();

    public Pool(Producer<T> producer) {
        this.producer = producer;
    }

    public synchronized void free(T t) {
        this.items.offer(t);
    }

    public synchronized T get() {
        if (this.items.size() > 0) {
            return this.items.poll();
        }
        return this.producer.create();
    }
}

