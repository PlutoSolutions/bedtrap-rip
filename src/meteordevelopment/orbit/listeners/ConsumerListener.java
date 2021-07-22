/*
 * Decompiled with CFR 0.150.
 */
package meteordevelopment.orbit.listeners;

import java.util.function.Consumer;
import meteordevelopment.orbit.listeners.IListener;

public class ConsumerListener<T>
implements IListener {
    private final /* synthetic */ int priority;
    private final /* synthetic */ Class<?> target;
    private final /* synthetic */ Consumer<T> executor;

    @Override
    public boolean isStatic() {
        return false;
    }

    public ConsumerListener(Class<?> llllIIIIIlllIIl, int llllIIIIIllllII, Consumer<T> llllIIIIIllIlll) {
        ConsumerListener llllIIIIIlllIlI;
        llllIIIIIlllIlI.target = llllIIIIIlllIIl;
        llllIIIIIlllIlI.priority = llllIIIIIllllII;
        llllIIIIIlllIlI.executor = llllIIIIIllIlll;
    }

    @Override
    public void call(Object llllIIIIIlIlIII) {
        ConsumerListener llllIIIIIlIlIll;
        llllIIIIIlIlIll.executor.accept(llllIIIIIlIlIII);
    }

    @Override
    public Class<?> getTarget() {
        ConsumerListener llllIIIIIlIIllI;
        return llllIIIIIlIIllI.target;
    }

    public ConsumerListener(Class<?> llllIIIIIlIllll, Consumer<T> llllIIIIIllIIIl) {
        llllIIIIIllIIll(llllIIIIIlIllll, 0, llllIIIIIllIIIl);
        ConsumerListener<T> llllIIIIIllIIll;
    }

    @Override
    public int getPriority() {
        ConsumerListener llllIIIIIlIIIll;
        return llllIIIIIlIIIll.priority;
    }
}

