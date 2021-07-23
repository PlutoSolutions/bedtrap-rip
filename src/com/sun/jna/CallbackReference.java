/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

import com.sun.jna.AltCallingConvention;
import com.sun.jna.Callback;
import com.sun.jna.CallbackParameterContext;
import com.sun.jna.CallbackProxy;
import com.sun.jna.CallbackResultContext;
import com.sun.jna.CallbackThreadInitializer;
import com.sun.jna.FromNativeConverter;
import com.sun.jna.Function;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeMapped;
import com.sun.jna.NativeMappedConverter;
import com.sun.jna.NativeString;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.StringArray;
import com.sun.jna.Structure;
import com.sun.jna.ToNativeConverter;
import com.sun.jna.TypeMapper;
import com.sun.jna.WString;
import com.sun.jna.win32.DLLCallback;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class CallbackReference
extends WeakReference<Callback> {
    CallbackProxy proxy;
    private static final Map<Callback, CallbackThreadInitializer> initializers;
    static final Map<Object, Object> allocations;
    int callingConvention;
    static final Map<Callback, CallbackReference> directCallbackMap;
    static final Map<Pointer, Reference<Callback>> pointerCallbackMap;
    Pointer trampoline;
    private static final Map<CallbackReference, Reference<CallbackReference>> allocatedMemory;
    Pointer cbstruct;
    static final Map<Callback, CallbackReference> callbackMap;
    private static final Method PROXY_CALLBACK_METHOD;
    Method method;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static ThreadGroup initializeThread(Callback callback, AttachOptions attachOptions) {
        CallbackThreadInitializer callbackThreadInitializer = null;
        if (callback instanceof DefaultCallbackProxy) {
            callback = ((DefaultCallbackProxy)callback).getCallback();
        }
        Object object = initializers;
        synchronized (object) {
            callbackThreadInitializer = initializers.get(callback);
        }
        object = null;
        if (callbackThreadInitializer != null) {
            object = callbackThreadInitializer.getThreadGroup(callback);
            attachOptions.name = callbackThreadInitializer.getName(callback);
            attachOptions.daemon = callbackThreadInitializer.isDaemon(callback);
            attachOptions.detach = callbackThreadInitializer.detach(callback);
            attachOptions.write();
        }
        return object;
    }

    static void disposeAll() {
        LinkedList<CallbackReference> linkedList = new LinkedList<CallbackReference>(allocatedMemory.keySet());
        for (CallbackReference callbackReference : linkedList) {
            callbackReference.dispose();
        }
    }

    private Callback getCallback() {
        return (Callback)this.get();
    }

    private void setCallbackOptions(int n) {
        this.cbstruct.setInt(Pointer.SIZE, n);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Callback getCallback(Class<?> clazz, Pointer pointer, boolean bl) {
        if (pointer == null) {
            return null;
        }
        if (!clazz.isInterface()) {
            throw new IllegalArgumentException("Callback type must be an interface");
        }
        Map<Callback, CallbackReference> map = bl ? directCallbackMap : callbackMap;
        Map<Pointer, Reference<Callback>> map2 = pointerCallbackMap;
        synchronized (map2) {
            Callback callback = null;
            Reference<Callback> reference = pointerCallbackMap.get(pointer);
            if (reference != null) {
                callback = reference.get();
                if (callback != null && !clazz.isAssignableFrom(callback.getClass())) {
                    throw new IllegalStateException(String.valueOf(new StringBuilder().append("Pointer ").append(pointer).append(" already mapped to ").append(callback).append(".\nNative code may be re-using a default function pointer, in which case you may need to use a common Callback class wherever the function pointer is reused.")));
                }
                return callback;
            }
            int n = AltCallingConvention.class.isAssignableFrom(clazz) ? 63 : 0;
            HashMap<String, Object> hashMap = new HashMap<String, Object>(Native.getLibraryOptions(clazz));
            hashMap.put("invoking-method", CallbackReference.getCallbackMethod(clazz));
            NativeFunctionHandler nativeFunctionHandler = new NativeFunctionHandler(pointer, n, hashMap);
            callback = (Callback)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, nativeFunctionHandler);
            map.remove(callback);
            pointerCallbackMap.put(pointer, new WeakReference<Callback>(callback));
            return callback;
        }
    }

    protected synchronized void dispose() {
        if (this.cbstruct != null) {
            try {
                Native.freeNativeCallback(this.cbstruct.peer);
            }
            finally {
                this.cbstruct.peer = 0L;
                this.cbstruct = null;
                allocatedMemory.remove(this);
            }
        }
    }

    private static Pointer getNativeString(Object object, boolean bl) {
        if (object != null) {
            NativeString nativeString = new NativeString(object.toString(), bl);
            allocations.put(object, nativeString);
            return nativeString.getPointer();
        }
        return null;
    }

    protected void finalize() {
        this.dispose();
    }

    private static Method getCallbackMethod(Callback callback) {
        return CallbackReference.getCallbackMethod(CallbackReference.findCallbackClass(callback.getClass()));
    }

    private static Pointer getNativeFunctionPointer(Callback callback) {
        InvocationHandler invocationHandler;
        if (Proxy.isProxyClass(callback.getClass()) && (invocationHandler = Proxy.getInvocationHandler(callback)) instanceof NativeFunctionHandler) {
            return ((NativeFunctionHandler)invocationHandler).getPointer();
        }
        return null;
    }

    private CallbackReference(Callback callback, int n, boolean bl) {
        super(callback);
        Object object;
        TypeMapper typeMapper = Native.getTypeMapper(callback.getClass());
        this.callingConvention = n;
        boolean bl2 = Platform.isPPC();
        if (bl) {
            object = CallbackReference.getCallbackMethod(callback);
            Class<?>[] classArray = ((Method)object).getParameterTypes();
            for (int i = 0; i < classArray.length; ++i) {
                if (bl2 && (classArray[i] == Float.TYPE || classArray[i] == Double.TYPE)) {
                    bl = false;
                    break;
                }
                if (typeMapper == null || typeMapper.getFromNativeConverter(classArray[i]) == null) continue;
                bl = false;
                break;
            }
            if (typeMapper != null && typeMapper.getToNativeConverter(((Method)object).getReturnType()) != null) {
                bl = false;
            }
        }
        object = Native.getStringEncoding(callback.getClass());
        long l = 0L;
        if (bl) {
            this.method = CallbackReference.getCallbackMethod(callback);
            Class<?>[] classArray = this.method.getParameterTypes();
            Class<?> clazz = this.method.getReturnType();
            int n2 = 1;
            if (callback instanceof DLLCallback) {
                n2 |= 2;
            }
            l = Native.createNativeCallback(callback, this.method, classArray, clazz, n, n2, (String)object);
        } else {
            int n3;
            Object object2;
            this.proxy = callback instanceof CallbackProxy ? (CallbackProxy)callback : new DefaultCallbackProxy(this, CallbackReference.getCallbackMethod(callback), typeMapper, (String)object);
            Class<?>[] classArray = this.proxy.getParameterTypes();
            Class<?> clazz = this.proxy.getReturnType();
            if (typeMapper != null) {
                for (int i = 0; i < classArray.length; ++i) {
                    object2 = typeMapper.getFromNativeConverter(classArray[i]);
                    if (object2 == null) continue;
                    classArray[i] = object2.nativeType();
                    if (null == null) continue;
                    throw null;
                }
                ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter(clazz);
                if (toNativeConverter != null) {
                    clazz = toNativeConverter.nativeType();
                }
            }
            for (n3 = 0; n3 < classArray.length; ++n3) {
                classArray[n3] = this.getNativeType(classArray[n3]);
                if (CallbackReference.isAllowableNativeType(classArray[n3])) continue;
                object2 = String.valueOf(new StringBuilder().append("Callback argument ").append(classArray[n3]).append(" requires custom type conversion"));
                throw new IllegalArgumentException((String)object2);
            }
            if (!CallbackReference.isAllowableNativeType(clazz = this.getNativeType(clazz))) {
                String string = String.valueOf(new StringBuilder().append("Callback return type ").append(clazz).append(" requires custom type conversion"));
                throw new IllegalArgumentException(string);
            }
            n3 = callback instanceof DLLCallback ? 2 : 0;
            l = Native.createNativeCallback(this.proxy, PROXY_CALLBACK_METHOD, classArray, clazz, n, n3, (String)object);
        }
        this.cbstruct = l != 0L ? new Pointer(l) : null;
        allocatedMemory.put(this, new WeakReference<CallbackReference>(this));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static CallbackThreadInitializer setCallbackThreadInitializer(Callback callback, CallbackThreadInitializer callbackThreadInitializer) {
        Map<Callback, CallbackThreadInitializer> map = initializers;
        synchronized (map) {
            if (callbackThreadInitializer != null) {
                return initializers.put(callback, callbackThreadInitializer);
            }
            return initializers.remove(callback);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Pointer getFunctionPointer(Callback callback, boolean bl) {
        Pointer pointer = null;
        if (callback == null) {
            return null;
        }
        pointer = CallbackReference.getNativeFunctionPointer(callback);
        if (pointer != null) {
            return pointer;
        }
        Map<String, Object> map = Native.getLibraryOptions(callback.getClass());
        int n = callback instanceof AltCallingConvention ? 63 : (map != null && map.containsKey("calling-convention") ? (Integer)map.get("calling-convention") : 0);
        Map<Callback, CallbackReference> map2 = bl ? directCallbackMap : callbackMap;
        Map<Pointer, Reference<Callback>> map3 = pointerCallbackMap;
        synchronized (map3) {
            CallbackReference callbackReference = map2.get(callback);
            if (callbackReference == null) {
                callbackReference = new CallbackReference(callback, n, bl);
                map2.put(callback, callbackReference);
                pointerCallbackMap.put(callbackReference.getTrampoline(), new WeakReference<Callback>(callback));
                if (initializers.containsKey(callback)) {
                    callbackReference.setCallbackOptions(1);
                }
            }
            return callbackReference.getTrampoline();
        }
    }

    static Callback access$000(CallbackReference callbackReference) {
        return callbackReference.getCallback();
    }

    public static Callback getCallback(Class<?> clazz, Pointer pointer) {
        return CallbackReference.getCallback(clazz, pointer, false);
    }

    static Pointer access$100(Object object, boolean bl) {
        return CallbackReference.getNativeString(object, bl);
    }

    static Class<?> findCallbackClass(Class<?> clazz) {
        if (!Callback.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append(clazz.getName()).append(" is not derived from com.sun.jna.Callback")));
        }
        if (clazz.isInterface()) {
            return clazz;
        }
        Class<?>[] classArray = clazz.getInterfaces();
        for (int i = 0; i < classArray.length; ++i) {
            if (!Callback.class.isAssignableFrom(classArray[i])) continue;
            try {
                CallbackReference.getCallbackMethod(classArray[i]);
                return classArray[i];
            }
            catch (IllegalArgumentException illegalArgumentException) {
                break;
            }
        }
        if (Callback.class.isAssignableFrom(clazz.getSuperclass())) {
            return CallbackReference.findCallbackClass(clazz.getSuperclass());
        }
        return clazz;
    }

    private Class<?> getNativeType(Class<?> clazz) {
        if (Structure.class.isAssignableFrom(clazz)) {
            Structure.validate(clazz);
            if (!Structure.ByValue.class.isAssignableFrom(clazz)) {
                return Pointer.class;
            }
        } else {
            if (NativeMapped.class.isAssignableFrom(clazz)) {
                return NativeMappedConverter.getInstance(clazz).nativeType();
            }
            if (clazz == String.class || clazz == WString.class || clazz == String[].class || clazz == WString[].class || Callback.class.isAssignableFrom(clazz)) {
                return Pointer.class;
            }
        }
        return clazz;
    }

    public Pointer getTrampoline() {
        if (this.trampoline == null) {
            this.trampoline = this.cbstruct.getPointer(0L);
        }
        return this.trampoline;
    }

    private static Method getCallbackMethod(Class<?> clazz) {
        Method[] methodArray = clazz.getDeclaredMethods();
        Method[] methodArray2 = clazz.getMethods();
        HashSet<Method> hashSet = new HashSet<Method>(Arrays.asList(methodArray));
        hashSet.retainAll(Arrays.asList(methodArray2));
        Method[] methodArray3 = hashSet.iterator();
        while (methodArray3.hasNext()) {
            Method method = (Method)methodArray3.next();
            if (!Callback.FORBIDDEN_NAMES.contains(method.getName())) continue;
            methodArray3.remove();
        }
        methodArray3 = hashSet.toArray(new Method[hashSet.size()]);
        if (methodArray3.length == 1) {
            return CallbackReference.checkMethod(methodArray3[0]);
        }
        for (int i = 0; i < methodArray3.length; ++i) {
            Method method = methodArray3[i];
            if (!"callback".equals(method.getName())) continue;
            return CallbackReference.checkMethod(method);
        }
        String string = "Callback must implement a single public method, or one public method named 'callback'";
        throw new IllegalArgumentException(string);
    }

    public static Pointer getFunctionPointer(Callback callback) {
        return CallbackReference.getFunctionPointer(callback, false);
    }

    static {
        callbackMap = new WeakHashMap<Callback, CallbackReference>();
        directCallbackMap = new WeakHashMap<Callback, CallbackReference>();
        pointerCallbackMap = new WeakHashMap<Pointer, Reference<Callback>>();
        allocations = new WeakHashMap<Object, Object>();
        allocatedMemory = Collections.synchronizedMap(new WeakHashMap());
        try {
            PROXY_CALLBACK_METHOD = CallbackProxy.class.getMethod("callback", Object[].class);
        }
        catch (Exception exception) {
            throw new Error("Error looking up CallbackProxy.callback() method");
        }
        initializers = new WeakHashMap<Callback, CallbackThreadInitializer>();
    }

    private static boolean isAllowableNativeType(Class<?> clazz) {
        return clazz == Void.TYPE || clazz == Void.class || clazz == Boolean.TYPE || clazz == Boolean.class || clazz == Byte.TYPE || clazz == Byte.class || clazz == Short.TYPE || clazz == Short.class || clazz == Character.TYPE || clazz == Character.class || clazz == Integer.TYPE || clazz == Integer.class || clazz == Long.TYPE || clazz == Long.class || clazz == Float.TYPE || clazz == Float.class || clazz == Double.TYPE || clazz == Double.class || Structure.ByValue.class.isAssignableFrom(clazz) && Structure.class.isAssignableFrom(clazz) || Pointer.class.isAssignableFrom(clazz);
    }

    private static Method checkMethod(Method method) {
        if (method.getParameterTypes().length > 256) {
            String string = String.valueOf(new StringBuilder().append("Method signature exceeds the maximum parameter count: ").append(method));
            throw new UnsupportedOperationException(string);
        }
        return method;
    }

    private static class NativeFunctionHandler
    implements InvocationHandler {
        private final Map<String, ?> options;
        private final Function function;

        @Override
        public Object invoke(Object object, Method method, Object[] objectArray) throws Throwable {
            if (Library.Handler.OBJECT_TOSTRING.equals(method)) {
                String string = String.valueOf(new StringBuilder().append("Proxy interface to ").append(this.function));
                Method method2 = (Method)this.options.get("invoking-method");
                Class<?> clazz = CallbackReference.findCallbackClass(method2.getDeclaringClass());
                string = String.valueOf(new StringBuilder().append(string).append(" (").append(clazz.getName()).append(")"));
                return string;
            }
            if (Library.Handler.OBJECT_HASHCODE.equals(method)) {
                return this.hashCode();
            }
            if (Library.Handler.OBJECT_EQUALS.equals(method)) {
                Object object2 = objectArray[0];
                if (object2 != null && Proxy.isProxyClass(object2.getClass())) {
                    return Function.valueOf(Proxy.getInvocationHandler(object2) == this);
                }
                return Boolean.FALSE;
            }
            if (Function.isVarArgs(method)) {
                objectArray = Function.concatenateVarArgs(objectArray);
            }
            return this.function.invoke(method.getReturnType(), objectArray, this.options);
        }

        public NativeFunctionHandler(Pointer pointer, int n, Map<String, ?> map) {
            this.options = map;
            this.function = new Function(pointer, n, (String)map.get("string-encoding"));
        }

        public Pointer getPointer() {
            return this.function;
        }
    }

    private class DefaultCallbackProxy
    implements CallbackProxy {
        private final String encoding;
        private final Method callbackMethod;
        private final FromNativeConverter[] fromNative;
        private ToNativeConverter toNative;
        final CallbackReference this$0;

        @Override
        public Class<?> getReturnType() {
            return this.callbackMethod.getReturnType();
        }

        private Object invokeCallback(Object[] objectArray) {
            Object object;
            Class<?>[] classArray = this.callbackMethod.getParameterTypes();
            Object[] objectArray2 = new Object[objectArray.length];
            for (int i = 0; i < objectArray.length; ++i) {
                object = classArray[i];
                Object object2 = objectArray[i];
                if (this.fromNative[i] != null) {
                    CallbackParameterContext callbackParameterContext = new CallbackParameterContext((Class<?>)object, this.callbackMethod, objectArray, i);
                    objectArray2[i] = this.fromNative[i].fromNative(object2, callbackParameterContext);
                    continue;
                }
                objectArray2[i] = this.convertArgument(object2, (Class<?>)object);
                if (3 >= 0) continue;
                return null;
            }
            Object object3 = null;
            object = this.getCallback();
            if (object != null) {
                try {
                    object3 = this.convertResult(this.callbackMethod.invoke(object, objectArray2));
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    Native.getCallbackExceptionHandler().uncaughtException((Callback)object, illegalArgumentException);
                }
                catch (IllegalAccessException illegalAccessException) {
                    Native.getCallbackExceptionHandler().uncaughtException((Callback)object, illegalAccessException);
                }
                catch (InvocationTargetException invocationTargetException) {
                    Native.getCallbackExceptionHandler().uncaughtException((Callback)object, invocationTargetException.getTargetException());
                }
            }
            for (int i = 0; i < objectArray2.length; ++i) {
                if (!(objectArray2[i] instanceof Structure) || objectArray2[i] instanceof Structure.ByValue) continue;
                ((Structure)objectArray2[i]).autoWrite();
                if (2 != 0) continue;
                return null;
            }
            return object3;
        }

        private Object convertArgument(Object object, Class<?> clazz) {
            if (object instanceof Pointer) {
                if (clazz == String.class) {
                    object = ((Pointer)object).getString(0L, this.encoding);
                } else if (clazz == WString.class) {
                    object = new WString(((Pointer)object).getWideString(0L));
                } else if (clazz == String[].class) {
                    object = ((Pointer)object).getStringArray(0L, this.encoding);
                } else if (clazz == WString[].class) {
                    object = ((Pointer)object).getWideStringArray(0L);
                } else if (Callback.class.isAssignableFrom(clazz)) {
                    object = CallbackReference.getCallback(clazz, (Pointer)object);
                } else if (Structure.class.isAssignableFrom(clazz)) {
                    if (Structure.ByValue.class.isAssignableFrom(clazz)) {
                        Structure structure = Structure.newInstance(clazz);
                        byte[] byArray = new byte[structure.size()];
                        ((Pointer)object).read(0L, byArray, 0, byArray.length);
                        structure.getPointer().write(0L, byArray, 0, byArray.length);
                        structure.read();
                        object = structure;
                    } else {
                        Structure structure = Structure.newInstance(clazz, (Pointer)object);
                        structure.conditionalAutoRead();
                        object = structure;
                    }
                }
            } else if ((Boolean.TYPE == clazz || Boolean.class == clazz) && object instanceof Number) {
                object = Function.valueOf(((Number)object).intValue() != 0);
            }
            return object;
        }

        private Object convertResult(Object object) {
            if (this.toNative != null) {
                object = this.toNative.toNative(object, new CallbackResultContext(this.callbackMethod));
            }
            if (object == null) {
                return null;
            }
            Class<?> clazz = object.getClass();
            if (Structure.class.isAssignableFrom(clazz)) {
                if (Structure.ByValue.class.isAssignableFrom(clazz)) {
                    return object;
                }
                return ((Structure)object).getPointer();
            }
            if (clazz == Boolean.TYPE || clazz == Boolean.class) {
                return Boolean.TRUE.equals(object) ? Function.INTEGER_TRUE : Function.INTEGER_FALSE;
            }
            if (clazz == String.class || clazz == WString.class) {
                return CallbackReference.access$100(object, clazz == WString.class);
            }
            if (clazz == String[].class || clazz == WString.class) {
                StringArray stringArray = clazz == String[].class ? new StringArray((String[])object, this.encoding) : new StringArray((WString[])object);
                allocations.put(object, stringArray);
                return stringArray;
            }
            if (Callback.class.isAssignableFrom(clazz)) {
                return CallbackReference.getFunctionPointer((Callback)object);
            }
            return object;
        }

        @Override
        public Class<?>[] getParameterTypes() {
            return this.callbackMethod.getParameterTypes();
        }

        public DefaultCallbackProxy(CallbackReference callbackReference, Method method, TypeMapper typeMapper, String string) {
            this.this$0 = callbackReference;
            this.callbackMethod = method;
            this.encoding = string;
            Class<?>[] classArray = method.getParameterTypes();
            Class<?> clazz = method.getReturnType();
            this.fromNative = new FromNativeConverter[classArray.length];
            if (NativeMapped.class.isAssignableFrom(clazz)) {
                this.toNative = NativeMappedConverter.getInstance(clazz);
            } else if (typeMapper != null) {
                this.toNative = typeMapper.getToNativeConverter(clazz);
            }
            for (int i = 0; i < this.fromNative.length; ++i) {
                if (NativeMapped.class.isAssignableFrom(classArray[i])) {
                    this.fromNative[i] = new NativeMappedConverter(classArray[i]);
                    continue;
                }
                if (typeMapper == null) continue;
                this.fromNative[i] = typeMapper.getFromNativeConverter(classArray[i]);
                if (!false) continue;
                throw null;
            }
            if (!method.isAccessible()) {
                try {
                    method.setAccessible(true);
                }
                catch (SecurityException securityException) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Callback method is inaccessible, make sure the interface is public: ").append(method)));
                }
            }
        }

        @Override
        public Object callback(Object[] objectArray) {
            try {
                return this.invokeCallback(objectArray);
            }
            catch (Throwable throwable) {
                Native.getCallbackExceptionHandler().uncaughtException(this.getCallback(), throwable);
                return null;
            }
        }

        public Callback getCallback() {
            return CallbackReference.access$000(this.this$0);
        }
    }

    static class AttachOptions
    extends Structure {
        public String name;
        public boolean detach;
        public boolean daemon;
        public static final List<String> FIELDS = AttachOptions.createFieldsOrder("daemon", "detach", "name");

        AttachOptions() {
            this.setStringEncoding("utf8");
        }

        @Override
        protected List<String> getFieldOrder() {
            return FIELDS;
        }
    }
}

