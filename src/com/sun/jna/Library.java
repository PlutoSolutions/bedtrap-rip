/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.AltCallingConvention;
import com.sun.jna.Function;
import com.sun.jna.InvocationMapper;
import com.sun.jna.NativeLibrary;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public interface Library {
    public static final String OPTION_TYPE_MAPPER;
    public static final String OPTION_STRUCTURE_ALIGNMENT;
    public static final String OPTION_STRING_ENCODING;
    public static final String OPTION_ALLOW_OBJECTS;
    public static final String OPTION_CALLING_CONVENTION;
    public static final String OPTION_INVOCATION_MAPPER;
    public static final String OPTION_CLASSLOADER;
    public static final String OPTION_FUNCTION_MAPPER;
    public static final String OPTION_OPEN_FLAGS;

    static {
        OPTION_INVOCATION_MAPPER = "invocation-mapper";
        OPTION_STRING_ENCODING = "string-encoding";
        OPTION_CALLING_CONVENTION = "calling-convention";
        OPTION_CLASSLOADER = "classloader";
        OPTION_FUNCTION_MAPPER = "function-mapper";
        OPTION_OPEN_FLAGS = "open-flags";
        OPTION_TYPE_MAPPER = "type-mapper";
        OPTION_ALLOW_OBJECTS = "allow-objects";
        OPTION_STRUCTURE_ALIGNMENT = "structure-alignment";
    }

    public static class Handler
    implements InvocationHandler {
        private final Map<Method, FunctionInfo> functions = new WeakHashMap<Method, FunctionInfo>();
        static final Method OBJECT_TOSTRING;
        static final Method OBJECT_HASHCODE;
        private final NativeLibrary nativeLibrary;
        private final Map<String, Object> options;
        static final Method OBJECT_EQUALS;
        private final InvocationMapper invocationMapper;
        private final Class<?> interfaceClass;

        public Class<?> getInterfaceClass() {
            return this.interfaceClass;
        }

        public NativeLibrary getNativeLibrary() {
            return this.nativeLibrary;
        }

        public Handler(String string, Class<?> class_, Map<String, ?> map) {
            int n;
            if (string != null && "".equals(string.trim())) {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Invalid library name \"").append(string).append("\"")));
            }
            if (!class_.isInterface()) {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append(string).append(" does not implement an interface: ").append(class_.getName())));
            }
            this.interfaceClass = class_;
            this.options = new HashMap(map);
            int n2 = n = AltCallingConvention.class.isAssignableFrom(class_) ? 63 : 0;
            if (this.options.get("calling-convention") == null) {
                this.options.put("calling-convention", n);
            }
            if (this.options.get("classloader") == null) {
                this.options.put("classloader", class_.getClassLoader());
            }
            this.nativeLibrary = NativeLibrary.getInstance(string, this.options);
            this.invocationMapper = (InvocationMapper)this.options.get("invocation-mapper");
        }

        public String getLibraryName() {
            return this.nativeLibrary.getName();
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public Object invoke(Object object, Method method, Object[] arrobject) throws Throwable {
            if (OBJECT_TOSTRING.equals(method)) {
                return String.valueOf(new StringBuilder().append("Proxy interface to ").append(this.nativeLibrary));
            }
            if (OBJECT_HASHCODE.equals(method)) {
                return this.hashCode();
            }
            if (OBJECT_EQUALS.equals(method)) {
                boolean bl;
                Object object2 = arrobject[0];
                if (object2 == null) return Boolean.FALSE;
                if (!Proxy.isProxyClass(object2.getClass())) return Boolean.FALSE;
                if (Proxy.getInvocationHandler(object2) == this) {
                    bl = true;
                    return Function.valueOf(bl);
                }
                bl = false;
                return Function.valueOf(bl);
            }
            FunctionInfo functionInfo = this.functions.get(method);
            if (functionInfo == null) {
                Map<Method, FunctionInfo> map = this.functions;
                synchronized (map) {
                    functionInfo = this.functions.get(method);
                    if (functionInfo == null) {
                        boolean bl = Function.isVarArgs(method);
                        InvocationHandler invocationHandler = null;
                        if (this.invocationMapper != null) {
                            invocationHandler = this.invocationMapper.getInvocationHandler(this.nativeLibrary, method);
                        }
                        Function function = null;
                        Class<?>[] arrclass = null;
                        HashMap<String, Object> hashMap = null;
                        if (invocationHandler == null) {
                            function = this.nativeLibrary.getFunction(method.getName(), method);
                            arrclass = method.getParameterTypes();
                            hashMap = new HashMap<String, Object>(this.options);
                            hashMap.put("invoking-method", method);
                        }
                        functionInfo = new FunctionInfo(invocationHandler, function, arrclass, bl, hashMap);
                        this.functions.put(method, functionInfo);
                    }
                }
            }
            if (functionInfo.isVarArgs) {
                arrobject = Function.concatenateVarArgs(arrobject);
            }
            if (functionInfo.handler == null) return functionInfo.function.invoke(method, functionInfo.parameterTypes, method.getReturnType(), arrobject, functionInfo.options);
            return functionInfo.handler.invoke(object, method, arrobject);
        }

        static {
            try {
                OBJECT_TOSTRING = Object.class.getMethod("toString", new Class[0]);
                OBJECT_HASHCODE = Object.class.getMethod("hashCode", new Class[0]);
                OBJECT_EQUALS = Object.class.getMethod("equals", Object.class);
            }
            catch (Exception exception) {
                throw new Error("Error retrieving Object.toString() method");
            }
        }

        private static final class FunctionInfo {
            final Map<String, ?> options;
            final boolean isVarArgs;
            final Function function;
            final Class<?>[] parameterTypes;
            final InvocationHandler handler;

            FunctionInfo(InvocationHandler invocationHandler, Function function, Class<?>[] arrclass, boolean bl, Map<String, ?> map) {
                this.handler = invocationHandler;
                this.function = function;
                this.isVarArgs = bl;
                this.options = map;
                this.parameterTypes = arrclass;
            }
        }
    }
}

