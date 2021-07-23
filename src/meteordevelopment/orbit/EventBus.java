/*
 * Decompiled with CFR 0.151.
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
    private final Map<Class<?>, List<IListener>> listenerMap;
    private final Map<Class<?>, List<IListener>> staticListenerCache;
    private final Map<Object, List<IListener>> listenerCache = new ConcurrentHashMap<Object, List<IListener>>();

    private static List lambda$subscribe$0(Class clazz) {
        return new CopyOnWriteArrayList();
    }

    @Override
    public void subscribe(IListener iListener) {
        this.subscribe(iListener, false);
    }

    @Override
    public void subscribe(Class<?> clazz) {
        this.subscribe(this.getListeners(clazz, null), true);
    }

    @Override
    public void subscribe(Object object) {
        this.subscribe(this.getListeners(object.getClass(), object), false);
    }

    private void subscribe(IListener iListener, boolean bl) {
        if (bl) {
            if (iListener.isStatic()) {
                this.insert(this.listenerMap.computeIfAbsent(iListener.getTarget(), EventBus::lambda$subscribe$0), iListener);
            }
        } else {
            this.insert(this.listenerMap.computeIfAbsent(iListener.getTarget(), EventBus::lambda$subscribe$1), iListener);
        }
    }

    private void unsubscribe(IListener iListener, boolean bl) {
        List<IListener> list = this.listenerMap.get(iListener.getTarget());
        if (list != null) {
            if (bl) {
                if (iListener.isStatic()) {
                    list.remove(iListener);
                }
            } else {
                list.remove(iListener);
            }
        }
    }

    @Override
    public void unsubscribe(Object object) {
        this.unsubscribe(this.getListeners(object.getClass(), object), false);
    }

    private static List lambda$subscribe$1(Class clazz) {
        return new CopyOnWriteArrayList();
    }

    private boolean isValid(Method method) {
        if (!method.isAnnotationPresent(EventHandler.class)) {
            return false;
        }
        if (method.getReturnType() != Void.TYPE) {
            return false;
        }
        if (method.getParameterCount() != 1) {
            return false;
        }
        return !method.getParameters()[0].getType().isPrimitive();
    }

    @Override
    public <T> T post(T t) {
        List<IListener> list = this.listenerMap.get(t.getClass());
        if (list != null) {
            for (IListener iListener : list) {
                iListener.call(t);
            }
        }
        return t;
    }

    @Override
    public void unsubscribe(IListener iListener) {
        this.unsubscribe(iListener, false);
    }

    @Override
    public void unsubscribe(Class<?> clazz) {
        this.unsubscribe(this.getListeners(clazz, null), true);
    }

    private List lambda$getListeners$2(Class clazz, Object object, Object object2) {
        CopyOnWriteArrayList<IListener> copyOnWriteArrayList = new CopyOnWriteArrayList<IListener>();
        this.getListeners(copyOnWriteArrayList, clazz, object);
        return copyOnWriteArrayList;
    }

    private void subscribe(List<IListener> list, boolean bl) {
        for (IListener iListener : list) {
            this.subscribe(iListener, bl);
        }
    }

    private void insert(List<IListener> list, IListener iListener) {
        int n;
        for (n = 0; n < list.size() && iListener.getPriority() <= list.get(n).getPriority(); ++n) {
            if (-3 <= 0) continue;
            return;
        }
        list.add(n, iListener);
    }

    private List<IListener> getListeners(Class<?> clazz, Object object) {
        Function<Object, List> function = arg_0 -> this.lambda$getListeners$2(clazz, object, arg_0);
        if (object == null) {
            return this.staticListenerCache.computeIfAbsent(clazz, function);
        }
        for (Object object2 : this.listenerCache.keySet()) {
            if (object2 != object) continue;
            return this.listenerCache.get(object);
        }
        List list = function.apply(object);
        this.listenerCache.put(object, list);
        return list;
    }

    @Override
    public <T extends ICancellable> T post(T t) {
        List<IListener> list = this.listenerMap.get(t.getClass());
        if (list != null) {
            t.setCancelled(false);
            for (IListener iListener : list) {
                iListener.call(t);
                if (!t.isCancelled()) continue;
                break;
            }
        }
        return t;
    }

    private void unsubscribe(List<IListener> list, boolean bl) {
        for (IListener iListener : list) {
            this.unsubscribe(iListener, bl);
        }
    }

    private void getListeners(List<IListener> list, Class<?> clazz, Object object) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (!this.isValid(method)) continue;
            list.add(new LambdaListener(clazz, object, method));
            if (null == null) continue;
            return;
        }
        if (clazz.getSuperclass() != null) {
            this.getListeners(list, clazz.getSuperclass(), object);
        }
    }

    public EventBus() {
        this.staticListenerCache = new ConcurrentHashMap();
        this.listenerMap = new ConcurrentHashMap();
    }
}

