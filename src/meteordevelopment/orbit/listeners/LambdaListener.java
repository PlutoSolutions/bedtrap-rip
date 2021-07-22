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
    private final /* synthetic */ int priority;
    private static /* synthetic */ boolean isJava1dot8;
    private final /* synthetic */ Class<?> target;
    private static /* synthetic */ Method privateLookupInMethod;
    private /* synthetic */ Consumer<Object> executor;
    private static /* synthetic */ Constructor<MethodHandles.Lookup> lookupConstructor;
    private final /* synthetic */ boolean isStatic;

    @Override
    public void call(Object llIlIlllIllllll) {
        LambdaListener llIlIllllIIIIlI;
        llIlIllllIIIIlI.executor.accept(llIlIlllIllllll);
    }

    @Override
    public Class<?> getTarget() {
        LambdaListener llIlIlllIllllII;
        return llIlIlllIllllII.target;
    }

    @Override
    public int getPriority() {
        LambdaListener llIlIlllIlllIIl;
        return llIlIlllIlllIIl.priority;
    }

    public LambdaListener(Class<?> llIlIllllIlIIIl, Object llIlIllllIlIIII, Method llIlIllllIIllll) {
        LambdaListener llIlIllllIIlllI;
        llIlIllllIIlllI.target = llIlIllllIIllll.getParameters()[0].getType();
        llIlIllllIIlllI.isStatic = Modifier.isStatic(llIlIllllIIllll.getModifiers());
        llIlIllllIIlllI.priority = llIlIllllIIllll.getAnnotation(EventHandler.class).priority();
        try {
            MethodType llIlIllllIlIlIl;
            MethodHandle llIlIllllIlIllI;
            MethodHandles.Lookup llIlIllllIllIII;
            String llIlIllllIllIIl = llIlIllllIIllll.getName();
            if (isJava1dot8) {
                boolean llIlIllllIlllIl = lookupConstructor.isAccessible();
                lookupConstructor.setAccessible(true);
                MethodHandles.Lookup llIlIllllIlllII = lookupConstructor.newInstance(llIlIllllIlIIIl);
                lookupConstructor.setAccessible(llIlIllllIlllIl);
            } else {
                llIlIllllIllIII = (MethodHandles.Lookup)privateLookupInMethod.invoke(null, llIlIllllIlIIIl, MethodHandles.lookup());
            }
            MethodType llIlIllllIlIlll = MethodType.methodType(Void.TYPE, llIlIllllIIllll.getParameters()[0].getType());
            if (llIlIllllIIlllI.isStatic) {
                MethodHandle llIlIllllIllIll = llIlIllllIllIII.findStatic(llIlIllllIlIIIl, llIlIllllIllIIl, llIlIllllIlIlll);
                MethodType llIlIllllIllIlI = MethodType.methodType(Consumer.class);
            } else {
                llIlIllllIlIllI = llIlIllllIllIII.findVirtual(llIlIllllIlIIIl, llIlIllllIllIIl, llIlIllllIlIlll);
                llIlIllllIlIlIl = MethodType.methodType(Consumer.class, llIlIllllIlIIIl);
            }
            MethodHandle llIlIllllIlIlII = LambdaMetafactory.metafactory(llIlIllllIllIII, "accept", llIlIllllIlIlIl, MethodType.methodType(Void.TYPE, Object.class), llIlIllllIlIllI, llIlIllllIlIlll).getTarget();
            llIlIllllIIlllI.executor = llIlIllllIIlllI.isStatic ? llIlIllllIlIlII.invoke() : llIlIllllIlIlII.invoke(llIlIllllIlIIII);
        }
        catch (Throwable llIlIllllIlIIll) {
            llIlIllllIlIIll.printStackTrace();
        }
    }

    @Override
    public boolean isStatic() {
        LambdaListener llIlIlllIllIlll;
        return llIlIlllIllIlll.isStatic;
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
        catch (NoSuchMethodException llIlIlllIllIlII) {
            llIlIlllIllIlII.printStackTrace();
        }
    }
}

