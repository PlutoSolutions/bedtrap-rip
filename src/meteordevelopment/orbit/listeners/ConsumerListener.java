/*
 * Decompiled with CFR 0.150.
 */
package meteordevelopment.orbit.listeners;

import java.util.function.Consumer;
import meteordevelopment.orbit.listeners.IListener;

public class ConsumerListener<T>
implements IListener {
    private final int priority;
    private final Class<?> target;
    private final Consumer<T> executor;

    @Override
    public boolean isStatic() {
        return false;
    }

    public ConsumerListener(Class<?> class_, int n, Consumer<T> consumer) {
        this.target = class_;
        this.priority = n;
        this.executor = consumer;
    }

    @Override
    public void call(Object object) {
        this.executor.accept(object);
    }

    @Override
    public Class<?> getTarget() {
        return this.target;
    }

    public ConsumerListener(Class<?> class_, Consumer<T> consumer) {
        this(class_, 0, consumer);
    }

    @Override
    public int getPriority() {
        return this.priority;
    }
}

