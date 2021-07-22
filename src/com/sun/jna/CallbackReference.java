/*
 * Decompiled with CFR 0.150.
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
    private static Callback getCallback(Class<?> class_, Pointer pointer, boolean bl) {
        if (pointer == null) {
            return null;
        }
        if (!class_.isInterface()) {
            throw new IllegalArgumentException("Callback type must be an interface");
        }
        Map<Callback, CallbackReference> map = bl ? directCallbackMap : callbackMap;
        Map<Pointer, Reference<Callback>> map2 = pointerCallbackMap;
        synchronized (map2) {
            Callback callback = null;
            Reference<Callback> reference = pointerCallbackMap.get(pointer);
            if (reference != null) {
                callback = reference.get();
                if (callback != null && !class_.isAssignableFrom(callback.getClass())) {
                    throw new IllegalStateException(String.valueOf(new StringBuilder().append("Pointer ").append(pointer).append(" already mapped to ").append(callback).append(".\nNative code may be re-using a default function pointer, in which case you may need to use a common Callback class wherever the function pointer is reused.")));
                }
                return callback;
            }
            int n = AltCallingConvention.class.isAssignableFrom(class_) ? 63 : 0;
            HashMap<String, Object> hashMap = new HashMap<String, Object>(Native.getLibraryOptions(class_));
            hashMap.put("invoking-method", CallbackReference.getCallbackMethod(class_));
            NativeFunctionHandler nativeFunctionHandler = new NativeFunctionHandler(pointer, n, hashMap);
            callback = (Callback)Proxy.newProxyInstance(class_.getClassLoader(), new Class[]{class_}, (InvocationHandler)nativeFunctionHandler);
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
            Class<?>[] arrclass = ((Method)object).getParameterTypes();
            for (int i = 0; i < arrclass.length; ++i) {
                if (bl2 && (arrclass[i] == Float.TYPE || arrclass[i] == Double.TYPE)) {
                    bl = false;
                    break;
                }
                if (typeMapper == null || typeMapper.getFromNativeConverter(arrclass[i]) == null) continue;
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
            Class<?>[] arrclass = this.method.getParameterTypes();
            Class<?> class_ = this.method.getReturnType();
            int n2 = 1;
            if (callback instanceof DLLCallback) {
                n2 |= 2;
            }
            l = Native.createNativeCallback(callback, this.method, arrclass, class_, n, n2, (String)object);
        } else {
            int n3;
            Object object2;
            this.proxy = callback instanceof CallbackProxy ? (CallbackProxy)callback : new DefaultCallbackProxy(this, CallbackReference.getCallbackMethod(callback), typeMapper, (String)object);
            Class<?>[] arrclass = this.proxy.getParameterTypes();
            Class<?> class_ = this.proxy.getReturnType();
            if (typeMapper != null) {
                for (int i = 0; i < arrclass.length; ++i) {
                    object2 = typeMapper.getFromNativeConverter(arrclass[i]);
                    if (object2 == null) continue;
                    arrclass[i] = object2.nativeType();
                    if (null == null) continue;
                    throw null;
                }
                ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter(class_);
                if (toNativeConverter != null) {
                    class_ = toNativeConverter.nativeType();
                }
            }
            for (n3 = 0; n3 < arrclass.length; ++n3) {
                arrclass[n3] = this.getNativeType(arrclass[n3]);
                if (CallbackReference.isAllowableNativeType(arrclass[n3])) continue;
                object2 = String.valueOf(new StringBuilder().append("Callback argument ").append(arrclass[n3]).append(" requires custom type conversion"));
                throw new IllegalArgumentException((String)object2);
            }
            if (!CallbackReference.isAllowableNativeType(class_ = this.getNativeType(class_))) {
                String string = String.valueOf(new StringBuilder().append("Callback return type ").append(class_).append(" requires custom type conversion"));
                throw new IllegalArgumentException(string);
            }
            n3 = callback instanceof DLLCallback ? 2 : 0;
            l = Native.createNativeCallback(this.proxy, PROXY_CALLBACK_METHOD, arrclass, class_, n, n3, (String)object);
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

    public static Callback getCallback(Class<?> class_, Pointer pointer) {
        return CallbackReference.getCallback(class_, pointer, false);
    }

    static Pointer access$100(Object object, boolean bl) {
        return CallbackReference.getNativeString(object, bl);
    }

    static Class<?> findCallbackClass(Class<?> class_) {
        if (!Callback.class.isAssignableFrom(class_)) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append(class_.getName()).append(" is not derived from com.sun.jna.Callback")));
        }
        if (class_.isInterface()) {
            return class_;
        }
        Class<?>[] arrclass = class_.getInterfaces();
        for (int i = 0; i < arrclass.length; ++i) {
            if (!Callback.class.isAssignableFrom(arrclass[i])) continue;
            try {
                CallbackReference.getCallbackMethod(arrclass[i]);
                return arrclass[i];
            }
            catch (IllegalArgumentException illegalArgumentException) {
                break;
            }
        }
        if (Callback.class.isAssignableFrom(class_.getSuperclass())) {
            return CallbackReference.findCallbackClass(class_.getSuperclass());
        }
        return class_;
    }

    private Class<?> getNativeType(Class<?> class_) {
        if (Structure.class.isAssignableFrom(class_)) {
            Structure.validate(class_);
            if (!Structure.ByValue.class.isAssignableFrom(class_)) {
                return Pointer.class;
            }
        } else {
            if (NativeMapped.class.isAssignableFrom(class_)) {
                return NativeMappedConverter.getInstance(class_).nativeType();
            }
            if (class_ == String.class || class_ == WString.class || class_ == String[].class || class_ == WString[].class || Callback.class.isAssignableFrom(class_)) {
                return Pointer.class;
            }
        }
        return class_;
    }

    public Pointer getTrampoline() {
        if (this.trampoline == null) {
            this.trampoline = this.cbstruct.getPointer(0L);
        }
        return this.trampoline;
    }

    private static Method getCallbackMethod(Class<?> class_) {
        Method[] arrmethod = class_.getDeclaredMethods();
        Method[] arrmethod2 = class_.getMethods();
        HashSet<Method> hashSet = new HashSet<Method>(Arrays.asList(arrmethod));
        hashSet.retainAll(Arrays.asList(arrmethod2));
        Method[] arrmethod3 = hashSet.iterator();
        while (arrmethod3.hasNext()) {
            Method method = (Method)arrmethod3.next();
            if (!Callback.FORBIDDEN_NAMES.contains(method.getName())) continue;
            arrmethod3.remove();
        }
        arrmethod3 = hashSet.toArray(new Method[hashSet.size()]);
        if (arrmethod3.length == 1) {
            return CallbackReference.checkMethod(arrmethod3[0]);
        }
        for (int i = 0; i < arrmethod3.length; ++i) {
            Method method = arrmethod3[i];
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

    private static boolean isAllowableNativeType(Class<?> class_) {
        return class_ == Void.TYPE || class_ == Void.class || class_ == Boolean.TYPE || class_ == Boolean.class || class_ == Byte.TYPE || class_ == Byte.class || class_ == Short.TYPE || class_ == Short.class || class_ == Character.TYPE || class_ == Character.class || class_ == Integer.TYPE || class_ == Integer.class || class_ == Long.TYPE || class_ == Long.class || class_ == Float.TYPE || class_ == Float.class || class_ == Double.TYPE || class_ == Double.class || Structure.ByValue.class.isAssignableFrom(class_) && Structure.class.isAssignableFrom(class_) || Pointer.class.isAssignableFrom(class_);
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
        public Object invoke(Object object, Method method, Object[] arrobject) throws Throwable {
            if (Library.Handler.OBJECT_TOSTRING.equals(method)) {
                String string = String.valueOf(new StringBuilder().append("Proxy interface to ").append(this.function));
                Method method2 = (Method)this.options.get("invoking-method");
                Class<?> class_ = CallbackReference.findCallbackClass(method2.getDeclaringClass());
                string = String.valueOf(new StringBuilder().append(string).append(" (").append(class_.getName()).append(")"));
                return string;
            }
            if (Library.Handler.OBJECT_HASHCODE.equals(method)) {
                return this.hashCode();
            }
            if (Library.Handler.OBJECT_EQUALS.equals(method)) {
                Object object2 = arrobject[0];
                if (object2 != null && Proxy.isProxyClass(object2.getClass())) {
                    return Function.valueOf(Proxy.getInvocationHandler(object2) == this);
                }
                return Boolean.FALSE;
            }
            if (Function.isVarArgs(method)) {
                arrobject = Function.concatenateVarArgs(arrobject);
            }
            return this.function.invoke(method.getReturnType(), arrobject, this.options);
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

        private Object invokeCallback(Object[] arrobject) {
            Object object;
            Class<?>[] arrclass = this.callbackMethod.getParameterTypes();
            Object[] arrobject2 = new Object[arrobject.length];
            for (int i = 0; i < arrobject.length; ++i) {
                object = arrclass[i];
                Object object2 = arrobject[i];
                if (this.fromNative[i] != null) {
                    CallbackParameterContext callbackParameterContext = new CallbackParameterContext((Class<?>)object, this.callbackMethod, arrobject, i);
                    arrobject2[i] = this.fromNative[i].fromNative(object2, callbackParameterContext);
                    continue;
                }
                arrobject2[i] = this.convertArgument(object2, (Class<?>)object);
                if (3 >= 0) continue;
                return null;
            }
            Object object3 = null;
            object = this.getCallback();
            if (object != null) {
                try {
                    object3 = this.convertResult(this.callbackMethod.invoke(object, arrobject2));
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
            for (int i = 0; i < arrobject2.length; ++i) {
                if (!(arrobject2[i] instanceof Structure) || arrobject2[i] instanceof Structure.ByValue) continue;
                ((Structure)arrobject2[i]).autoWrite();
                if (2 != 0) continue;
                return null;
            }
            return object3;
        }

        private Object convertArgument(Object object, Class<?> class_) {
            if (object instanceof Pointer) {
                if (class_ == String.class) {
                    object = ((Pointer)object).getString(0L, this.encoding);
                } else if (class_ == WString.class) {
                    object = new WString(((Pointer)object).getWideString(0L));
                } else if (class_ == String[].class) {
                    object = ((Pointer)object).getStringArray(0L, this.encoding);
                } else if (class_ == WString[].class) {
                    object = ((Pointer)object).getWideStringArray(0L);
                } else if (Callback.class.isAssignableFrom(class_)) {
                    object = CallbackReference.getCallback(class_, (Pointer)object);
                } else if (Structure.class.isAssignableFrom(class_)) {
                    if (Structure.ByValue.class.isAssignableFrom(class_)) {
                        Structure structure = Structure.newInstance(class_);
                        byte[] arrby = new byte[structure.size()];
                        ((Pointer)object).read(0L, arrby, 0, arrby.length);
                        structure.getPointer().write(0L, arrby, 0, arrby.length);
                        structure.read();
                        object = structure;
                    } else {
                        Structure structure = Structure.newInstance(class_, (Pointer)object);
                        structure.conditionalAutoRead();
                        object = structure;
                    }
                }
            } else if ((Boolean.TYPE == class_ || Boolean.class == class_) && object instanceof Number) {
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
            Class<?> class_ = object.getClass();
            if (Structure.class.isAssignableFrom(class_)) {
                if (Structure.ByValue.class.isAssignableFrom(class_)) {
                    return object;
                }
                return ((Structure)object).getPointer();
            }
            if (class_ == Boolean.TYPE || class_ == Boolean.class) {
                return Boolean.TRUE.equals(object) ? Function.INTEGER_TRUE : Function.INTEGER_FALSE;
            }
            if (class_ == String.class || class_ == WString.class) {
                return CallbackReference.access$100(object, class_ == WString.class);
            }
            if (class_ == String[].class || class_ == WString.class) {
                StringArray stringArray = class_ == String[].class ? new StringArray((String[])object, this.encoding) : new StringArray((WString[])object);
                allocations.put(object, stringArray);
                return stringArray;
            }
            if (Callback.class.isAssignableFrom(class_)) {
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
            Class<?>[] arrclass = method.getParameterTypes();
            Class<?> class_ = method.getReturnType();
            this.fromNative = new FromNativeConverter[arrclass.length];
            if (NativeMapped.class.isAssignableFrom(class_)) {
                this.toNative = NativeMappedConverter.getInstance(class_);
            } else if (typeMapper != null) {
                this.toNative = typeMapper.getToNativeConverter(class_);
            }
            for (int i = 0; i < this.fromNative.length; ++i) {
                if (NativeMapped.class.isAssignableFrom(arrclass[i])) {
                    this.fromNative[i] = new NativeMappedConverter(arrclass[i]);
                    continue;
                }
                if (typeMapper == null) continue;
                this.fromNative[i] = typeMapper.getFromNativeConverter(arrclass[i]);
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
        public Object callback(Object[] arrobject) {
            try {
                return this.invokeCallback(arrobject);
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

