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
    public static final /* synthetic */ String OPTION_TYPE_MAPPER;
    public static final /* synthetic */ String OPTION_STRUCTURE_ALIGNMENT;
    public static final /* synthetic */ String OPTION_STRING_ENCODING;
    public static final /* synthetic */ String OPTION_ALLOW_OBJECTS;
    public static final /* synthetic */ String OPTION_CALLING_CONVENTION;
    public static final /* synthetic */ String OPTION_INVOCATION_MAPPER;
    public static final /* synthetic */ String OPTION_CLASSLOADER;
    public static final /* synthetic */ String OPTION_FUNCTION_MAPPER;
    public static final /* synthetic */ String OPTION_OPEN_FLAGS;

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
        private final /* synthetic */ Map<Method, FunctionInfo> functions;
        static final /* synthetic */ Method OBJECT_TOSTRING;
        static final /* synthetic */ Method OBJECT_HASHCODE;
        private final /* synthetic */ NativeLibrary nativeLibrary;
        private final /* synthetic */ Map<String, Object> options;
        static final /* synthetic */ Method OBJECT_EQUALS;
        private final /* synthetic */ InvocationMapper invocationMapper;
        private final /* synthetic */ Class<?> interfaceClass;

        public Class<?> getInterfaceClass() {
            Handler lllllllllllllllllIIIIIIIlIIlIIIl;
            return lllllllllllllllllIIIIIIIlIIlIIIl.interfaceClass;
        }

        public NativeLibrary getNativeLibrary() {
            Handler lllllllllllllllllIIIIIIIlIIlIlll;
            return lllllllllllllllllIIIIIIIlIIlIlll.nativeLibrary;
        }

        public Handler(String lllllllllllllllllIIIIIIIlIlIIIlI, Class<?> lllllllllllllllllIIIIIIIlIlIIIIl, Map<String, ?> lllllllllllllllllIIIIIIIlIIllIll) {
            int lllllllllllllllllIIIIIIIlIIlllll;
            Handler lllllllllllllllllIIIIIIIlIlIIIll;
            lllllllllllllllllIIIIIIIlIlIIIll.functions = new WeakHashMap<Method, FunctionInfo>();
            if (lllllllllllllllllIIIIIIIlIlIIIlI != null && "".equals(lllllllllllllllllIIIIIIIlIlIIIlI.trim())) {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Invalid library name \"").append(lllllllllllllllllIIIIIIIlIlIIIlI).append("\"")));
            }
            if (!lllllllllllllllllIIIIIIIlIlIIIIl.isInterface()) {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append(lllllllllllllllllIIIIIIIlIlIIIlI).append(" does not implement an interface: ").append(lllllllllllllllllIIIIIIIlIlIIIIl.getName())));
            }
            lllllllllllllllllIIIIIIIlIlIIIll.interfaceClass = lllllllllllllllllIIIIIIIlIlIIIIl;
            lllllllllllllllllIIIIIIIlIlIIIll.options = new HashMap(lllllllllllllllllIIIIIIIlIIllIll);
            int n = lllllllllllllllllIIIIIIIlIIlllll = AltCallingConvention.class.isAssignableFrom(lllllllllllllllllIIIIIIIlIlIIIIl) ? 63 : 0;
            if (lllllllllllllllllIIIIIIIlIlIIIll.options.get("calling-convention") == null) {
                lllllllllllllllllIIIIIIIlIlIIIll.options.put("calling-convention", lllllllllllllllllIIIIIIIlIIlllll);
            }
            if (lllllllllllllllllIIIIIIIlIlIIIll.options.get("classloader") == null) {
                lllllllllllllllllIIIIIIIlIlIIIll.options.put("classloader", lllllllllllllllllIIIIIIIlIlIIIIl.getClassLoader());
            }
            lllllllllllllllllIIIIIIIlIlIIIll.nativeLibrary = NativeLibrary.getInstance(lllllllllllllllllIIIIIIIlIlIIIlI, lllllllllllllllllIIIIIIIlIlIIIll.options);
            lllllllllllllllllIIIIIIIlIlIIIll.invocationMapper = (InvocationMapper)lllllllllllllllllIIIIIIIlIlIIIll.options.get("invocation-mapper");
        }

        public String getLibraryName() {
            Handler lllllllllllllllllIIIIIIIlIIlIlII;
            return lllllllllllllllllIIIIIIIlIIlIlII.nativeLibrary.getName();
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public Object invoke(Object lllllllllllllllllIIIIIIIIllllIII, Method lllllllllllllllllIIIIIIIIlllllII, Object[] lllllllllllllllllIIIIIIIIllllIll) throws Throwable {
            Handler lllllllllllllllllIIIIIIIIllllIIl;
            if (OBJECT_TOSTRING.equals(lllllllllllllllllIIIIIIIIlllllII)) {
                return String.valueOf(new StringBuilder().append("Proxy interface to ").append(lllllllllllllllllIIIIIIIIllllIIl.nativeLibrary));
            }
            if (OBJECT_HASHCODE.equals(lllllllllllllllllIIIIIIIIlllllII)) {
                return lllllllllllllllllIIIIIIIIllllIIl.hashCode();
            }
            if (OBJECT_EQUALS.equals(lllllllllllllllllIIIIIIIIlllllII)) {
                Object lllllllllllllllllIIIIIIIlIIIIlII = lllllllllllllllllIIIIIIIIllllIll[0];
                if (lllllllllllllllllIIIIIIIlIIIIlII != null && Proxy.isProxyClass(lllllllllllllllllIIIIIIIlIIIIlII.getClass())) {
                    return Function.valueOf(Proxy.getInvocationHandler(lllllllllllllllllIIIIIIIlIIIIlII) == lllllllllllllllllIIIIIIIIllllIIl);
                }
                return Boolean.FALSE;
            }
            FunctionInfo lllllllllllllllllIIIIIIIIllllIlI = lllllllllllllllllIIIIIIIIllllIIl.functions.get(lllllllllllllllllIIIIIIIIlllllII);
            if (lllllllllllllllllIIIIIIIIllllIlI == null) {
                Map<Method, FunctionInfo> lllllllllllllllllIIIIIIIIlllIlII = lllllllllllllllllIIIIIIIIllllIIl.functions;
                synchronized (lllllllllllllllllIIIIIIIIlllIlII) {
                    lllllllllllllllllIIIIIIIIllllIlI = lllllllllllllllllIIIIIIIIllllIIl.functions.get(lllllllllllllllllIIIIIIIIlllllII);
                    if (lllllllllllllllllIIIIIIIIllllIlI == null) {
                        boolean lllllllllllllllllIIIIIIIlIIIIIll = Function.isVarArgs(lllllllllllllllllIIIIIIIIlllllII);
                        InvocationHandler lllllllllllllllllIIIIIIIlIIIIIlI = null;
                        if (lllllllllllllllllIIIIIIIIllllIIl.invocationMapper != null) {
                            lllllllllllllllllIIIIIIIlIIIIIlI = lllllllllllllllllIIIIIIIIllllIIl.invocationMapper.getInvocationHandler(lllllllllllllllllIIIIIIIIllllIIl.nativeLibrary, lllllllllllllllllIIIIIIIIlllllII);
                        }
                        Function lllllllllllllllllIIIIIIIlIIIIIIl = null;
                        Class<?>[] lllllllllllllllllIIIIIIIlIIIIIII = null;
                        HashMap<String, Object> lllllllllllllllllIIIIIIIIlllllll = null;
                        if (lllllllllllllllllIIIIIIIlIIIIIlI == null) {
                            lllllllllllllllllIIIIIIIlIIIIIIl = lllllllllllllllllIIIIIIIIllllIIl.nativeLibrary.getFunction(lllllllllllllllllIIIIIIIIlllllII.getName(), lllllllllllllllllIIIIIIIIlllllII);
                            lllllllllllllllllIIIIIIIlIIIIIII = lllllllllllllllllIIIIIIIIlllllII.getParameterTypes();
                            lllllllllllllllllIIIIIIIIlllllll = new HashMap<String, Object>(lllllllllllllllllIIIIIIIIllllIIl.options);
                            lllllllllllllllllIIIIIIIIlllllll.put("invoking-method", lllllllllllllllllIIIIIIIIlllllII);
                        }
                        lllllllllllllllllIIIIIIIIllllIlI = new FunctionInfo(lllllllllllllllllIIIIIIIlIIIIIlI, lllllllllllllllllIIIIIIIlIIIIIIl, lllllllllllllllllIIIIIIIlIIIIIII, lllllllllllllllllIIIIIIIlIIIIIll, lllllllllllllllllIIIIIIIIlllllll);
                        lllllllllllllllllIIIIIIIIllllIIl.functions.put(lllllllllllllllllIIIIIIIIlllllII, lllllllllllllllllIIIIIIIIllllIlI);
                    }
                }
            }
            if (lllllllllllllllllIIIIIIIIllllIlI.isVarArgs) {
                lllllllllllllllllIIIIIIIIllllIll = Function.concatenateVarArgs(lllllllllllllllllIIIIIIIIllllIll);
            }
            if (lllllllllllllllllIIIIIIIIllllIlI.handler != null) {
                return lllllllllllllllllIIIIIIIIllllIlI.handler.invoke(lllllllllllllllllIIIIIIIIllllIII, lllllllllllllllllIIIIIIIIlllllII, lllllllllllllllllIIIIIIIIllllIll);
            }
            return lllllllllllllllllIIIIIIIIllllIlI.function.invoke(lllllllllllllllllIIIIIIIIlllllII, lllllllllllllllllIIIIIIIIllllIlI.parameterTypes, lllllllllllllllllIIIIIIIIlllllII.getReturnType(), lllllllllllllllllIIIIIIIIllllIll, lllllllllllllllllIIIIIIIIllllIlI.options);
        }

        static {
            try {
                OBJECT_TOSTRING = Object.class.getMethod("toString", new Class[0]);
                OBJECT_HASHCODE = Object.class.getMethod("hashCode", new Class[0]);
                OBJECT_EQUALS = Object.class.getMethod("equals", Object.class);
            }
            catch (Exception lllllllllllllllllIIIIIIIIllIllII) {
                throw new Error("Error retrieving Object.toString() method");
            }
        }

        private static final class FunctionInfo {
            final /* synthetic */ Map<String, ?> options;
            final /* synthetic */ boolean isVarArgs;
            final /* synthetic */ Function function;
            final /* synthetic */ Class<?>[] parameterTypes;
            final /* synthetic */ InvocationHandler handler;

            FunctionInfo(InvocationHandler lllIllIlIIlllll, Function lllIllIlIIllIII, Class<?>[] lllIllIlIIlllIl, boolean lllIllIlIIlIllI, Map<String, ?> lllIllIlIIlIlIl) {
                FunctionInfo lllIllIlIlIIIII;
                lllIllIlIlIIIII.handler = lllIllIlIIlllll;
                lllIllIlIlIIIII.function = lllIllIlIIllIII;
                lllIllIlIlIIIII.isVarArgs = lllIllIlIIlIllI;
                lllIllIlIlIIIII.options = lllIllIlIIlIlIl;
                lllIllIlIlIIIII.parameterTypes = lllIllIlIIlllIl;
            }
        }
    }
}

