/*
 * Decompiled with CFR 0.150.
 */
package meteordevelopment.orbit.listeners;

import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Consumer;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.listeners.IListener;

public class LambdaListener
implements IListener {
    private final int priority;
    private static boolean isJava1dot8;
    private final Class<?> target;
    private static Method privateLookupInMethod;
    private Consumer<Object> executor;
    private static Constructor<MethodHandles.Lookup> lookupConstructor;
    private final boolean isStatic;

    @Override
    public void call(Object object) {
        this.executor.accept(object);
    }

    @Override
    public Class<?> getTarget() {
        return this.target;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    public LambdaListener(Class<?> class_, Object object, Method method) {
        this.target = method.getParameters()[0].getType();
        this.isStatic = Modifier.isStatic(method.getModifiers());
        this.priority = method.getAnnotation(EventHandler.class).priority();
        try {
            MethodType methodType;
            MethodHandle methodHandle;
            MethodHandles.Lookup lookup;
            String string = method.getName();
            if (isJava1dot8) {
                boolean bl = lookupConstructor.isAccessible();
                lookupConstructor.setAccessible(true);
                lookup = lookupConstructor.newInstance(class_);
                lookupConstructor.setAccessible(bl);
            } else {
                lookup = (MethodHandles.Lookup)privateLookupInMethod.invoke(null, class_, MethodHandles.lookup());
            }
            MethodType methodType2 = MethodType.methodType(Void.TYPE, method.getParameters()[0].getType());
            if (this.isStatic) {
                methodHandle = lookup.findStatic(class_, string, methodType2);
                methodType = MethodType.methodType(Consumer.class);
            } else {
                methodHandle = lookup.findVirtual(class_, string, methodType2);
                methodType = MethodType.methodType(Consumer.class, class_);
            }
            MethodHandle methodHandle2 = LambdaMetafactory.metafactory(lookup, "accept", methodType, MethodType.methodType(Void.TYPE, Object.class), methodHandle, methodType2).getTarget();
            this.executor = this.isStatic ? methodHandle2.invoke() : methodHandle2.invoke(object);
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public boolean isStatic() {
        return this.isStatic;
    }

    static {
        try {
            isJava1dot8 = System.getProperty("java.version").startsWith("1.8");
            if (isJava1dot8) {
                lookupConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class);
            } else {
                privateLookupInMethod = MethodHandles.class.getDeclaredMethod("privateLookupIn", Class.class, MethodHandles.Lookup.class);
            }
        }
        catch (NoSuchMethodException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        }
    }
}

