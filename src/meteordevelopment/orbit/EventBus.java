/*
 * Decompiled with CFR 0.150.
 */
package meteordevelopment.orbit;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.ICancellable;
import meteordevelopment.orbit.IEventBus;
import meteordevelopment.orbit.listeners.IListener;
import meteordevelopment.orbit.listeners.LambdaListener;

public class EventBus
implements IEventBus {
    private final /* synthetic */ Map<Class<?>, List<IListener>> listenerMap;
    private final /* synthetic */ Map<Class<?>, List<IListener>> staticListenerCache;
    private final /* synthetic */ Map<Object, List<IListener>> listenerCache;

    @Override
    public void subscribe(IListener lllllllllllllllllIlllIIlIIIllIIl) {
        EventBus lllllllllllllllllIlllIIlIIIllIII;
        lllllllllllllllllIlllIIlIIIllIII.subscribe(lllllllllllllllllIlllIIlIIIllIIl, false);
    }

    @Override
    public void subscribe(Class<?> lllllllllllllllllIlllIIlIIlIIlll) {
        EventBus lllllllllllllllllIlllIIlIIlIIlIl;
        lllllllllllllllllIlllIIlIIlIIlIl.subscribe(lllllllllllllllllIlllIIlIIlIIlIl.getListeners(lllllllllllllllllIlllIIlIIlIIlll, null), true);
    }

    @Override
    public void subscribe(Object lllllllllllllllllIlllIIlIIllIIII) {
        EventBus lllllllllllllllllIlllIIlIIllIIll;
        lllllllllllllllllIlllIIlIIllIIll.subscribe(lllllllllllllllllIlllIIlIIllIIll.getListeners(lllllllllllllllllIlllIIlIIllIIII.getClass(), lllllllllllllllllIlllIIlIIllIIII), false);
    }

    private void subscribe(IListener lllllllllllllllllIlllIIlIIIIIIIl, boolean lllllllllllllllllIlllIIlIIIIIIII) {
        EventBus lllllllllllllllllIlllIIlIIIIIlIl;
        if (lllllllllllllllllIlllIIlIIIIIIII) {
            if (lllllllllllllllllIlllIIlIIIIIIIl.isStatic()) {
                lllllllllllllllllIlllIIlIIIIIlIl.insert(lllllllllllllllllIlllIIlIIIIIlIl.listenerMap.computeIfAbsent(lllllllllllllllllIlllIIlIIIIIIIl.getTarget(), lllllllllllllllllIlllIIIlIIlIIII -> new CopyOnWriteArrayList()), lllllllllllllllllIlllIIlIIIIIIIl);
            }
        } else {
            lllllllllllllllllIlllIIlIIIIIlIl.insert(lllllllllllllllllIlllIIlIIIIIlIl.listenerMap.computeIfAbsent(lllllllllllllllllIlllIIlIIIIIIIl.getTarget(), lllllllllllllllllIlllIIIlIIlIIIl -> new CopyOnWriteArrayList()), lllllllllllllllllIlllIIlIIIIIIIl);
        }
    }

    private void unsubscribe(IListener lllllllllllllllllIlllIIIllIlIIII, boolean lllllllllllllllllIlllIIIllIIllll) {
        EventBus lllllllllllllllllIlllIIIllIIllIl;
        List<IListener> lllllllllllllllllIlllIIIllIIlllI = lllllllllllllllllIlllIIIllIIllIl.listenerMap.get(lllllllllllllllllIlllIIIllIlIIII.getTarget());
        if (lllllllllllllllllIlllIIIllIIlllI != null) {
            if (lllllllllllllllllIlllIIIllIIllll) {
                if (lllllllllllllllllIlllIIIllIlIIII.isStatic()) {
                    lllllllllllllllllIlllIIIllIIlllI.remove(lllllllllllllllllIlllIIIllIlIIII);
                }
            } else {
                lllllllllllllllllIlllIIIllIIlllI.remove(lllllllllllllllllIlllIIIllIlIIII);
            }
        }
    }

    @Override
    public void unsubscribe(Object lllllllllllllllllIlllIIIllllIIII) {
        EventBus lllllllllllllllllIlllIIIllllIIll;
        lllllllllllllllllIlllIIIllllIIll.unsubscribe(lllllllllllllllllIlllIIIllllIIll.getListeners(lllllllllllllllllIlllIIIllllIIII.getClass(), lllllllllllllllllIlllIIIllllIIII), false);
    }

    private boolean isValid(Method lllllllllllllllllIlllIIIlIIlllll) {
        if (!lllllllllllllllllIlllIIIlIIlllll.isAnnotationPresent(EventHandler.class)) {
            return false;
        }
        if (lllllllllllllllllIlllIIIlIIlllll.getReturnType() != Void.TYPE) {
            return false;
        }
        if (lllllllllllllllllIlllIIIlIIlllll.getParameterCount() != 1) {
            return false;
        }
        return !lllllllllllllllllIlllIIIlIIlllll.getParameters()[0].getType().isPrimitive();
    }

    @Override
    public <T> T post(T lllllllllllllllllIlllIIlIlIlIlll) {
        EventBus lllllllllllllllllIlllIIlIlIllIIl;
        List<IListener> lllllllllllllllllIlllIIlIlIlIllI = lllllllllllllllllIlllIIlIlIllIIl.listenerMap.get(lllllllllllllllllIlllIIlIlIlIlll.getClass());
        if (lllllllllllllllllIlllIIlIlIlIllI != null) {
            for (IListener lllllllllllllllllIlllIIlIlIllIll : lllllllllllllllllIlllIIlIlIlIllI) {
                lllllllllllllllllIlllIIlIlIllIll.call(lllllllllllllllllIlllIIlIlIlIlll);
            }
        }
        return lllllllllllllllllIlllIIlIlIlIlll;
    }

    @Override
    public void unsubscribe(IListener lllllllllllllllllIlllIIIlllIIlII) {
        EventBus lllllllllllllllllIlllIIIlllIIlll;
        lllllllllllllllllIlllIIIlllIIlll.unsubscribe(lllllllllllllllllIlllIIIlllIIlII, false);
    }

    @Override
    public void unsubscribe(Class<?> lllllllllllllllllIlllIIIlllIlIlI) {
        EventBus lllllllllllllllllIlllIIIlllIllIl;
        lllllllllllllllllIlllIIIlllIllIl.unsubscribe(lllllllllllllllllIlllIIIlllIllIl.getListeners(lllllllllllllllllIlllIIIlllIlIlI, null), true);
    }

    private void subscribe(List<IListener> lllllllllllllllllIlllIIlIIIIllII, boolean lllllllllllllllllIlllIIlIIIIlllI) {
        for (IListener lllllllllllllllllIlllIIlIIIlIIIl : lllllllllllllllllIlllIIlIIIIllII) {
            EventBus lllllllllllllllllIlllIIlIIIlIIII;
            lllllllllllllllllIlllIIlIIIlIIII.subscribe(lllllllllllllllllIlllIIlIIIlIIIl, lllllllllllllllllIlllIIlIIIIlllI);
        }
    }

    private void insert(List<IListener> lllllllllllllllllIlllIIIlllllIII, IListener lllllllllllllllllIlllIIIllllIlll) {
        int lllllllllllllllllIlllIIIlllllIIl;
        for (lllllllllllllllllIlllIIIlllllIIl = 0; lllllllllllllllllIlllIIIlllllIIl < lllllllllllllllllIlllIIIlllllIII.size() && lllllllllllllllllIlllIIIllllIlll.getPriority() <= lllllllllllllllllIlllIIIlllllIII.get(lllllllllllllllllIlllIIIlllllIIl).getPriority(); ++lllllllllllllllllIlllIIIlllllIIl) {
        }
        lllllllllllllllllIlllIIIlllllIII.add(lllllllllllllllllIlllIIIlllllIIl, lllllllllllllllllIlllIIIllllIlll);
    }

    private List<IListener> getListeners(Class<?> lllllllllllllllllIlllIIIlIllllII, Object lllllllllllllllllIlllIIIllIIIIII) {
        EventBus lllllllllllllllllIlllIIIlIllllIl;
        Function<Object, List> lllllllllllllllllIlllIIIlIllllll = lllllllllllllllllIlllIIIlIIlIlll -> {
            EventBus lllllllllllllllllIlllIIIlIIllIlI;
            CopyOnWriteArrayList<IListener> lllllllllllllllllIlllIIIlIIlIllI = new CopyOnWriteArrayList<IListener>();
            lllllllllllllllllIlllIIIlIIllIlI.getListeners(lllllllllllllllllIlllIIIlIIlIllI, lllllllllllllllllIlllIIIlIllllII, lllllllllllllllllIlllIIIllIIIIII);
            return lllllllllllllllllIlllIIIlIIlIllI;
        };
        if (lllllllllllllllllIlllIIIllIIIIII == null) {
            return lllllllllllllllllIlllIIIlIllllIl.staticListenerCache.computeIfAbsent(lllllllllllllllllIlllIIIlIllllII, lllllllllllllllllIlllIIIlIllllll);
        }
        for (Object lllllllllllllllllIlllIIIllIIIIll : lllllllllllllllllIlllIIIlIllllIl.listenerCache.keySet()) {
            if (lllllllllllllllllIlllIIIllIIIIll != lllllllllllllllllIlllIIIllIIIIII) continue;
            return lllllllllllllllllIlllIIIlIllllIl.listenerCache.get(lllllllllllllllllIlllIIIllIIIIII);
        }
        List lllllllllllllllllIlllIIIlIlllllI = lllllllllllllllllIlllIIIlIllllll.apply(lllllllllllllllllIlllIIIllIIIIII);
        lllllllllllllllllIlllIIIlIllllIl.listenerCache.put(lllllllllllllllllIlllIIIllIIIIII, lllllllllllllllllIlllIIIlIlllllI);
        return lllllllllllllllllIlllIIIlIlllllI;
    }

    @Override
    public <T extends ICancellable> T post(T lllllllllllllllllIlllIIlIIlllIlI) {
        EventBus lllllllllllllllllIlllIIlIlIIIIII;
        List<IListener> lllllllllllllllllIlllIIlIIllllII = lllllllllllllllllIlllIIlIlIIIIII.listenerMap.get(lllllllllllllllllIlllIIlIIlllIlI.getClass());
        if (lllllllllllllllllIlllIIlIIllllII != null) {
            lllllllllllllllllIlllIIlIIlllIlI.setCancelled(false);
            for (IListener lllllllllllllllllIlllIIlIlIIIIlI : lllllllllllllllllIlllIIlIIllllII) {
                lllllllllllllllllIlllIIlIlIIIIlI.call(lllllllllllllllllIlllIIlIIlllIlI);
                if (!lllllllllllllllllIlllIIlIIlllIlI.isCancelled()) continue;
                break;
            }
        }
        return lllllllllllllllllIlllIIlIIlllIlI;
    }

    private void unsubscribe(List<IListener> lllllllllllllllllIlllIIIllIllIIl, boolean lllllllllllllllllIlllIIIllIllIll) {
        for (IListener lllllllllllllllllIlllIIIllIllllI : lllllllllllllllllIlllIIIllIllIIl) {
            EventBus lllllllllllllllllIlllIIIllIlllIl;
            lllllllllllllllllIlllIIIllIlllIl.unsubscribe(lllllllllllllllllIlllIIIllIllllI, lllllllllllllllllIlllIIIllIllIll);
        }
    }

    private void getListeners(List<IListener> lllllllllllllllllIlllIIIlIlIllIl, Class<?> lllllllllllllllllIlllIIIlIlIlIII, Object lllllllllllllllllIlllIIIlIlIlIll) {
        EventBus lllllllllllllllllIlllIIIlIlIlIlI;
        for (Method lllllllllllllllllIlllIIIlIlIllll : lllllllllllllllllIlllIIIlIlIlIII.getDeclaredMethods()) {
            if (!lllllllllllllllllIlllIIIlIlIlIlI.isValid(lllllllllllllllllIlllIIIlIlIllll)) continue;
            lllllllllllllllllIlllIIIlIlIllIl.add(new LambdaListener(lllllllllllllllllIlllIIIlIlIlIII, lllllllllllllllllIlllIIIlIlIlIll, lllllllllllllllllIlllIIIlIlIllll));
        }
        if (lllllllllllllllllIlllIIIlIlIlIII.getSuperclass() != null) {
            lllllllllllllllllIlllIIIlIlIlIlI.getListeners(lllllllllllllllllIlllIIIlIlIllIl, lllllllllllllllllIlllIIIlIlIlIII.getSuperclass(), lllllllllllllllllIlllIIIlIlIlIll);
        }
    }

    public EventBus() {
        EventBus lllllllllllllllllIlllIIlIllIlIIl;
        lllllllllllllllllIlllIIlIllIlIIl.listenerCache = new ConcurrentHashMap<Object, List<IListener>>();
        lllllllllllllllllIlllIIlIllIlIIl.staticListenerCache = new ConcurrentHashMap();
        lllllllllllllllllIlllIIlIllIlIIl.listenerMap = new ConcurrentHashMap();
    }
}

