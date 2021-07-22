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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class CallbackReference
extends WeakReference<Callback> {
    /* synthetic */ CallbackProxy proxy;
    private static final /* synthetic */ Map<Callback, CallbackThreadInitializer> initializers;
    static final /* synthetic */ Map<Object, Object> allocations;
    /* synthetic */ int callingConvention;
    static final /* synthetic */ Map<Callback, CallbackReference> directCallbackMap;
    static final /* synthetic */ Map<Pointer, Reference<Callback>> pointerCallbackMap;
    /* synthetic */ Pointer trampoline;
    private static final /* synthetic */ Map<CallbackReference, Reference<CallbackReference>> allocatedMemory;
    /* synthetic */ Pointer cbstruct;
    static final /* synthetic */ Map<Callback, CallbackReference> callbackMap;
    private static final /* synthetic */ Method PROXY_CALLBACK_METHOD;
    /* synthetic */ Method method;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static ThreadGroup initializeThread(Callback lllllllllllllllllIIllllIIIllIlIl, AttachOptions lllllllllllllllllIIllllIIIllIlII) {
        CallbackThreadInitializer lllllllllllllllllIIllllIIIllIIll = null;
        if (lllllllllllllllllIIllllIIIllIlIl instanceof DefaultCallbackProxy) {
            lllllllllllllllllIIllllIIIllIlIl = ((DefaultCallbackProxy)lllllllllllllllllIIllllIIIllIlIl).getCallback();
        }
        Map<Callback, CallbackThreadInitializer> lllllllllllllllllIIllllIIIlIlllI = initializers;
        synchronized (lllllllllllllllllIIllllIIIlIlllI) {
            lllllllllllllllllIIllllIIIllIIll = initializers.get(lllllllllllllllllIIllllIIIllIlIl);
        }
        ThreadGroup lllllllllllllllllIIllllIIIllIIlI = null;
        if (lllllllllllllllllIIllllIIIllIIll != null) {
            lllllllllllllllllIIllllIIIllIIlI = lllllllllllllllllIIllllIIIllIIll.getThreadGroup(lllllllllllllllllIIllllIIIllIlIl);
            lllllllllllllllllIIllllIIIllIlII.name = lllllllllllllllllIIllllIIIllIIll.getName(lllllllllllllllllIIllllIIIllIlIl);
            lllllllllllllllllIIllllIIIllIlII.daemon = lllllllllllllllllIIllllIIIllIIll.isDaemon(lllllllllllllllllIIllllIIIllIlIl);
            lllllllllllllllllIIllllIIIllIlII.detach = lllllllllllllllllIIllllIIIllIIll.detach(lllllllllllllllllIIllllIIIllIlIl);
            lllllllllllllllllIIllllIIIllIlII.write();
        }
        return lllllllllllllllllIIllllIIIllIIlI;
    }

    static void disposeAll() {
        LinkedList<CallbackReference> lllllllllllllllllIIlllIllIIlIIII = new LinkedList<CallbackReference>(allocatedMemory.keySet());
        for (CallbackReference lllllllllllllllllIIlllIllIIlIIIl : lllllllllllllllllIIlllIllIIlIIII) {
            lllllllllllllllllIIlllIllIIlIIIl.dispose();
        }
    }

    private Callback getCallback() {
        CallbackReference lllllllllllllllllIIlllIllIIIlIll;
        return (Callback)lllllllllllllllllIIlllIllIIIlIll.get();
    }

    private void setCallbackOptions(int lllllllllllllllllIIlllIllIlIIIII) {
        CallbackReference lllllllllllllllllIIlllIllIlIIIll;
        lllllllllllllllllIIlllIllIlIIIll.cbstruct.setInt(Pointer.SIZE, lllllllllllllllllIIlllIllIlIIIII);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Callback getCallback(Class<?> lllllllllllllllllIIllllIIIIlIIlI, Pointer lllllllllllllllllIIllllIIIIlIlIl, boolean lllllllllllllllllIIllllIIIIlIIII) {
        if (lllllllllllllllllIIllllIIIIlIlIl == null) {
            return null;
        }
        if (!lllllllllllllllllIIllllIIIIlIIlI.isInterface()) {
            throw new IllegalArgumentException("Callback type must be an interface");
        }
        Map<Callback, CallbackReference> lllllllllllllllllIIllllIIIIlIIll = lllllllllllllllllIIllllIIIIlIIII ? directCallbackMap : callbackMap;
        Map<Pointer, Reference<Callback>> lllllllllllllllllIIllllIIIIIlllI = pointerCallbackMap;
        synchronized (lllllllllllllllllIIllllIIIIIlllI) {
            Callback lllllllllllllllllIIllllIIIIllIll = null;
            Reference<Callback> lllllllllllllllllIIllllIIIIllIlI = pointerCallbackMap.get(lllllllllllllllllIIllllIIIIlIlIl);
            if (lllllllllllllllllIIllllIIIIllIlI != null) {
                lllllllllllllllllIIllllIIIIllIll = lllllllllllllllllIIllllIIIIllIlI.get();
                if (lllllllllllllllllIIllllIIIIllIll != null && !lllllllllllllllllIIllllIIIIlIIlI.isAssignableFrom(lllllllllllllllllIIllllIIIIllIll.getClass())) {
                    throw new IllegalStateException(String.valueOf(new StringBuilder().append("Pointer ").append(lllllllllllllllllIIllllIIIIlIlIl).append(" already mapped to ").append(lllllllllllllllllIIllllIIIIllIll).append(".\nNative code may be re-using a default function pointer, in which case you may need to use a common Callback class wherever the function pointer is reused.")));
                }
                return lllllllllllllllllIIllllIIIIllIll;
            }
            int lllllllllllllllllIIllllIIIIllIIl = AltCallingConvention.class.isAssignableFrom(lllllllllllllllllIIllllIIIIlIIlI) ? 63 : 0;
            HashMap<String, Object> lllllllllllllllllIIllllIIIIllIII = new HashMap<String, Object>(Native.getLibraryOptions(lllllllllllllllllIIllllIIIIlIIlI));
            lllllllllllllllllIIllllIIIIllIII.put("invoking-method", CallbackReference.getCallbackMethod(lllllllllllllllllIIllllIIIIlIIlI));
            NativeFunctionHandler lllllllllllllllllIIllllIIIIlIlll = new NativeFunctionHandler(lllllllllllllllllIIllllIIIIlIlIl, lllllllllllllllllIIllllIIIIllIIl, lllllllllllllllllIIllllIIIIllIII);
            lllllllllllllllllIIllllIIIIllIll = (Callback)Proxy.newProxyInstance(lllllllllllllllllIIllllIIIIlIIlI.getClassLoader(), new Class[]{lllllllllllllllllIIllllIIIIlIIlI}, (InvocationHandler)lllllllllllllllllIIllllIIIIlIlll);
            lllllllllllllllllIIllllIIIIlIIll.remove(lllllllllllllllllIIllllIIIIllIll);
            pointerCallbackMap.put(lllllllllllllllllIIllllIIIIlIlIl, new WeakReference<Callback>(lllllllllllllllllIIllllIIIIllIll));
            return lllllllllllllllllIIllllIIIIllIll;
        }
    }

    protected synchronized void dispose() {
        CallbackReference lllllllllllllllllIIlllIllIIlIlll;
        if (lllllllllllllllllIIlllIllIIlIlll.cbstruct != null) {
            try {
                Native.freeNativeCallback(lllllllllllllllllIIlllIllIIlIlll.cbstruct.peer);
            }
            finally {
                lllllllllllllllllIIlllIllIIlIlll.cbstruct.peer = 0L;
                lllllllllllllllllIIlllIllIIlIlll.cbstruct = null;
                allocatedMemory.remove(lllllllllllllllllIIlllIllIIlIlll);
            }
        }
    }

    private static Pointer getNativeString(Object lllllllllllllllllIIlllIlIllIIIII, boolean lllllllllllllllllIIlllIlIlIlllIl) {
        if (lllllllllllllllllIIlllIlIllIIIII != null) {
            NativeString lllllllllllllllllIIlllIlIllIIIIl = new NativeString(lllllllllllllllllIIlllIlIllIIIII.toString(), lllllllllllllllllIIlllIlIlIlllIl);
            allocations.put(lllllllllllllllllIIlllIlIllIIIII, lllllllllllllllllIIlllIlIllIIIIl);
            return lllllllllllllllllIIlllIlIllIIIIl.getPointer();
        }
        return null;
    }

    protected void finalize() {
        CallbackReference lllllllllllllllllIIlllIllIIllIlI;
        lllllllllllllllllIIlllIllIIllIlI.dispose();
    }

    private static Method getCallbackMethod(Callback lllllllllllllllllIIlllIllIlllllI) {
        return CallbackReference.getCallbackMethod(CallbackReference.findCallbackClass(lllllllllllllllllIIlllIllIlllllI.getClass()));
    }

    private static Pointer getNativeFunctionPointer(Callback lllllllllllllllllIIlllIllIIIIllI) {
        InvocationHandler lllllllllllllllllIIlllIllIIIIlll;
        if (Proxy.isProxyClass(lllllllllllllllllIIlllIllIIIIllI.getClass()) && (lllllllllllllllllIIlllIllIIIIlll = Proxy.getInvocationHandler(lllllllllllllllllIIlllIllIIIIllI)) instanceof NativeFunctionHandler) {
            return ((NativeFunctionHandler)lllllllllllllllllIIlllIllIIIIlll).getPointer();
        }
        return null;
    }

    private CallbackReference(Callback lllllllllllllllllIIlllIllllIIIlI, int lllllllllllllllllIIlllIllllIIIIl, boolean lllllllllllllllllIIlllIllllIIIII) {
        super(lllllllllllllllllIIlllIllllIIIlI);
        CallbackReference lllllllllllllllllIIlllIllllIIIll;
        TypeMapper lllllllllllllllllIIlllIllllIlIIl = Native.getTypeMapper(lllllllllllllllllIIlllIllllIIIlI.getClass());
        lllllllllllllllllIIlllIllllIIIll.callingConvention = lllllllllllllllllIIlllIllllIIIIl;
        boolean lllllllllllllllllIIlllIllllIIllI = Platform.isPPC();
        if (lllllllllllllllllIIlllIllllIIIII) {
            Method lllllllllllllllllIIlllIllllllIIl = CallbackReference.getCallbackMethod(lllllllllllllllllIIlllIllllIIIlI);
            Class<?>[] lllllllllllllllllIIlllIllllllIII = lllllllllllllllllIIlllIllllllIIl.getParameterTypes();
            for (int lllllllllllllllllIIlllIllllllIlI = 0; lllllllllllllllllIIlllIllllllIlI < lllllllllllllllllIIlllIllllllIII.length; ++lllllllllllllllllIIlllIllllllIlI) {
                if (lllllllllllllllllIIlllIllllIIllI && (lllllllllllllllllIIlllIllllllIII[lllllllllllllllllIIlllIllllllIlI] == Float.TYPE || lllllllllllllllllIIlllIllllllIII[lllllllllllllllllIIlllIllllllIlI] == Double.TYPE)) {
                    lllllllllllllllllIIlllIllllIIIII = false;
                    break;
                }
                if (lllllllllllllllllIIlllIllllIlIIl == null || lllllllllllllllllIIlllIllllIlIIl.getFromNativeConverter(lllllllllllllllllIIlllIllllllIII[lllllllllllllllllIIlllIllllllIlI]) == null) continue;
                lllllllllllllllllIIlllIllllIIIII = false;
                break;
            }
            if (lllllllllllllllllIIlllIllllIlIIl != null && lllllllllllllllllIIlllIllllIlIIl.getToNativeConverter(lllllllllllllllllIIlllIllllllIIl.getReturnType()) != null) {
                lllllllllllllllllIIlllIllllIIIII = false;
            }
        }
        String lllllllllllllllllIIlllIllllIIlIl = Native.getStringEncoding(lllllllllllllllllIIlllIllllIIIlI.getClass());
        long lllllllllllllllllIIlllIllllIIlII = 0L;
        if (lllllllllllllllllIIlllIllllIIIII) {
            lllllllllllllllllIIlllIllllIIIll.method = CallbackReference.getCallbackMethod(lllllllllllllllllIIlllIllllIIIlI);
            Class<?>[] lllllllllllllllllIIlllIlllllIllI = lllllllllllllllllIIlllIllllIIIll.method.getParameterTypes();
            Class<?> lllllllllllllllllIIlllIlllllIlIl = lllllllllllllllllIIlllIllllIIIll.method.getReturnType();
            int lllllllllllllllllIIlllIlllllIlll = 1;
            if (lllllllllllllllllIIlllIllllIIIlI instanceof DLLCallback) {
                lllllllllllllllllIIlllIlllllIlll |= 2;
            }
            lllllllllllllllllIIlllIllllIIlII = Native.createNativeCallback(lllllllllllllllllIIlllIllllIIIlI, lllllllllllllllllIIlllIllllIIIll.method, lllllllllllllllllIIlllIlllllIllI, lllllllllllllllllIIlllIlllllIlIl, lllllllllllllllllIIlllIllllIIIIl, lllllllllllllllllIIlllIlllllIlll, lllllllllllllllllIIlllIllllIIlIl);
        } else {
            lllllllllllllllllIIlllIllllIIIll.proxy = lllllllllllllllllIIlllIllllIIIlI instanceof CallbackProxy ? (CallbackProxy)lllllllllllllllllIIlllIllllIIIlI : lllllllllllllllllIIlllIllllIIIll.new DefaultCallbackProxy(CallbackReference.getCallbackMethod(lllllllllllllllllIIlllIllllIIIlI), lllllllllllllllllIIlllIllllIlIIl, lllllllllllllllllIIlllIllllIIlIl);
            Class<?>[] lllllllllllllllllIIlllIllllIlIII = lllllllllllllllllIIlllIllllIIIll.proxy.getParameterTypes();
            Class<?> lllllllllllllllllIIlllIllllIIlll = lllllllllllllllllIIlllIllllIIIll.proxy.getReturnType();
            if (lllllllllllllllllIIlllIllllIlIIl != null) {
                for (int lllllllllllllllllIIlllIlllllIIll = 0; lllllllllllllllllIIlllIlllllIIll < lllllllllllllllllIIlllIllllIlIII.length; ++lllllllllllllllllIIlllIlllllIIll) {
                    FromNativeConverter lllllllllllllllllIIlllIlllllIlII = lllllllllllllllllIIlllIllllIlIIl.getFromNativeConverter(lllllllllllllllllIIlllIllllIlIII[lllllllllllllllllIIlllIlllllIIll]);
                    if (lllllllllllllllllIIlllIlllllIlII == null) continue;
                    lllllllllllllllllIIlllIllllIlIII[lllllllllllllllllIIlllIlllllIIll] = lllllllllllllllllIIlllIlllllIlII.nativeType();
                }
                ToNativeConverter lllllllllllllllllIIlllIlllllIIlI = lllllllllllllllllIIlllIllllIlIIl.getToNativeConverter(lllllllllllllllllIIlllIllllIIlll);
                if (lllllllllllllllllIIlllIlllllIIlI != null) {
                    lllllllllllllllllIIlllIllllIIlll = lllllllllllllllllIIlllIlllllIIlI.nativeType();
                }
            }
            for (int lllllllllllllllllIIlllIlllllIIII = 0; lllllllllllllllllIIlllIlllllIIII < lllllllllllllllllIIlllIllllIlIII.length; ++lllllllllllllllllIIlllIlllllIIII) {
                lllllllllllllllllIIlllIllllIlIII[lllllllllllllllllIIlllIlllllIIII] = lllllllllllllllllIIlllIllllIIIll.getNativeType(lllllllllllllllllIIlllIllllIlIII[lllllllllllllllllIIlllIlllllIIII]);
                if (CallbackReference.isAllowableNativeType(lllllllllllllllllIIlllIllllIlIII[lllllllllllllllllIIlllIlllllIIII])) continue;
                String lllllllllllllllllIIlllIlllllIIIl = String.valueOf(new StringBuilder().append("Callback argument ").append(lllllllllllllllllIIlllIllllIlIII[lllllllllllllllllIIlllIlllllIIII]).append(" requires custom type conversion"));
                throw new IllegalArgumentException(lllllllllllllllllIIlllIlllllIIIl);
            }
            if (!CallbackReference.isAllowableNativeType(lllllllllllllllllIIlllIllllIIlll = lllllllllllllllllIIlllIllllIIIll.getNativeType(lllllllllllllllllIIlllIllllIIlll))) {
                String lllllllllllllllllIIlllIllllIllll = String.valueOf(new StringBuilder().append("Callback return type ").append(lllllllllllllllllIIlllIllllIIlll).append(" requires custom type conversion"));
                throw new IllegalArgumentException(lllllllllllllllllIIlllIllllIllll);
            }
            int lllllllllllllllllIIlllIllllIlllI = lllllllllllllllllIIlllIllllIIIlI instanceof DLLCallback ? 2 : 0;
            lllllllllllllllllIIlllIllllIIlII = Native.createNativeCallback(lllllllllllllllllIIlllIllllIIIll.proxy, PROXY_CALLBACK_METHOD, lllllllllllllllllIIlllIllllIlIII, lllllllllllllllllIIlllIllllIIlll, lllllllllllllllllIIlllIllllIIIIl, lllllllllllllllllIIlllIllllIlllI, lllllllllllllllllIIlllIllllIIlIl);
        }
        lllllllllllllllllIIlllIllllIIIll.cbstruct = lllllllllllllllllIIlllIllllIIlII != 0L ? new Pointer(lllllllllllllllllIIlllIllllIIlII) : null;
        allocatedMemory.put(lllllllllllllllllIIlllIllllIIIll, new WeakReference<CallbackReference>(lllllllllllllllllIIlllIllllIIIll));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static CallbackThreadInitializer setCallbackThreadInitializer(Callback lllllllllllllllllIIllllIIIlllllI, CallbackThreadInitializer lllllllllllllllllIIllllIIIllllIl) {
        Map<Callback, CallbackThreadInitializer> lllllllllllllllllIIllllIIIllllII = initializers;
        synchronized (lllllllllllllllllIIllllIIIllllII) {
            if (lllllllllllllllllIIllllIIIllllIl != null) {
                return initializers.put(lllllllllllllllllIIllllIIIlllllI, lllllllllllllllllIIllllIIIllllIl);
            }
            return initializers.remove(lllllllllllllllllIIllllIIIlllllI);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Pointer getFunctionPointer(Callback lllllllllllllllllIIlllIlIlllIIII, boolean lllllllllllllllllIIlllIlIlllIlIl) {
        Pointer lllllllllllllllllIIlllIlIlllIlII = null;
        if (lllllllllllllllllIIlllIlIlllIIII == null) {
            return null;
        }
        lllllllllllllllllIIlllIlIlllIlII = CallbackReference.getNativeFunctionPointer(lllllllllllllllllIIlllIlIlllIIII);
        if (lllllllllllllllllIIlllIlIlllIlII != null) {
            return lllllllllllllllllIIlllIlIlllIlII;
        }
        Map<String, Object> lllllllllllllllllIIlllIlIlllIIll = Native.getLibraryOptions(lllllllllllllllllIIlllIlIlllIIII.getClass());
        int lllllllllllllllllIIlllIlIlllIIlI = lllllllllllllllllIIlllIlIlllIIII instanceof AltCallingConvention ? 63 : (lllllllllllllllllIIlllIlIlllIIll != null && lllllllllllllllllIIlllIlIlllIIll.containsKey("calling-convention") ? (Integer)lllllllllllllllllIIlllIlIlllIIll.get("calling-convention") : 0);
        Map<Callback, CallbackReference> lllllllllllllllllIIlllIlIlllIIIl = lllllllllllllllllIIlllIlIlllIlIl ? directCallbackMap : callbackMap;
        Map<Pointer, Reference<Callback>> lllllllllllllllllIIlllIlIllIlIlI = pointerCallbackMap;
        synchronized (lllllllllllllllllIIlllIlIllIlIlI) {
            CallbackReference lllllllllllllllllIIlllIlIlllIlll = lllllllllllllllllIIlllIlIlllIIIl.get(lllllllllllllllllIIlllIlIlllIIII);
            if (lllllllllllllllllIIlllIlIlllIlll == null) {
                lllllllllllllllllIIlllIlIlllIlll = new CallbackReference(lllllllllllllllllIIlllIlIlllIIII, lllllllllllllllllIIlllIlIlllIIlI, lllllllllllllllllIIlllIlIlllIlIl);
                lllllllllllllllllIIlllIlIlllIIIl.put(lllllllllllllllllIIlllIlIlllIIII, lllllllllllllllllIIlllIlIlllIlll);
                pointerCallbackMap.put(lllllllllllllllllIIlllIlIlllIlll.getTrampoline(), new WeakReference<Callback>(lllllllllllllllllIIlllIlIlllIIII));
                if (initializers.containsKey(lllllllllllllllllIIlllIlIlllIIII)) {
                    lllllllllllllllllIIlllIlIlllIlll.setCallbackOptions(1);
                }
            }
            return lllllllllllllllllIIlllIlIlllIlll.getTrampoline();
        }
    }

    public static Callback getCallback(Class<?> lllllllllllllllllIIllllIIIlIlIlI, Pointer lllllllllllllllllIIllllIIIlIIlll) {
        return CallbackReference.getCallback(lllllllllllllllllIIllllIIIlIlIlI, lllllllllllllllllIIllllIIIlIIlll, false);
    }

    static Class<?> findCallbackClass(Class<?> lllllllllllllllllIIlllIlllIIIlII) {
        if (!Callback.class.isAssignableFrom(lllllllllllllllllIIlllIlllIIIlII)) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append(lllllllllllllllllIIlllIlllIIIlII.getName()).append(" is not derived from com.sun.jna.Callback")));
        }
        if (lllllllllllllllllIIlllIlllIIIlII.isInterface()) {
            return lllllllllllllllllIIlllIlllIIIlII;
        }
        Class<?>[] lllllllllllllllllIIlllIlllIIIlIl = lllllllllllllllllIIlllIlllIIIlII.getInterfaces();
        for (int lllllllllllllllllIIlllIlllIIIlll = 0; lllllllllllllllllIIlllIlllIIIlll < lllllllllllllllllIIlllIlllIIIlIl.length; ++lllllllllllllllllIIlllIlllIIIlll) {
            if (!Callback.class.isAssignableFrom(lllllllllllllllllIIlllIlllIIIlIl[lllllllllllllllllIIlllIlllIIIlll])) continue;
            try {
                CallbackReference.getCallbackMethod(lllllllllllllllllIIlllIlllIIIlIl[lllllllllllllllllIIlllIlllIIIlll]);
                return lllllllllllllllllIIlllIlllIIIlIl[lllllllllllllllllIIlllIlllIIIlll];
            }
            catch (IllegalArgumentException lllllllllllllllllIIlllIlllIIlIII) {
                break;
            }
        }
        if (Callback.class.isAssignableFrom(lllllllllllllllllIIlllIlllIIIlII.getSuperclass())) {
            return CallbackReference.findCallbackClass(lllllllllllllllllIIlllIlllIIIlII.getSuperclass());
        }
        return lllllllllllllllllIIlllIlllIIIlII;
    }

    private Class<?> getNativeType(Class<?> lllllllllllllllllIIlllIlllIlIlII) {
        if (Structure.class.isAssignableFrom(lllllllllllllllllIIlllIlllIlIlII)) {
            Structure.validate(lllllllllllllllllIIlllIlllIlIlII);
            if (!Structure.ByValue.class.isAssignableFrom(lllllllllllllllllIIlllIlllIlIlII)) {
                return Pointer.class;
            }
        } else {
            if (NativeMapped.class.isAssignableFrom(lllllllllllllllllIIlllIlllIlIlII)) {
                return NativeMappedConverter.getInstance(lllllllllllllllllIIlllIlllIlIlII).nativeType();
            }
            if (lllllllllllllllllIIlllIlllIlIlII == String.class || lllllllllllllllllIIlllIlllIlIlII == WString.class || lllllllllllllllllIIlllIlllIlIlII == String[].class || lllllllllllllllllIIlllIlllIlIlII == WString[].class || Callback.class.isAssignableFrom(lllllllllllllllllIIlllIlllIlIlII)) {
                return Pointer.class;
            }
        }
        return lllllllllllllllllIIlllIlllIlIlII;
    }

    public Pointer getTrampoline() {
        CallbackReference lllllllllllllllllIIlllIllIIllllI;
        if (lllllllllllllllllIIlllIllIIllllI.trampoline == null) {
            lllllllllllllllllIIlllIllIIllllI.trampoline = lllllllllllllllllIIlllIllIIllllI.cbstruct.getPointer(0L);
        }
        return lllllllllllllllllIIlllIllIIllllI.trampoline;
    }

    private static Method getCallbackMethod(Class<?> lllllllllllllllllIIlllIllIlIllII) {
        Method[] lllllllllllllllllIIlllIllIllIIIl = lllllllllllllllllIIlllIllIlIllII.getDeclaredMethods();
        Method[] lllllllllllllllllIIlllIllIllIIII = lllllllllllllllllIIlllIllIlIllII.getMethods();
        HashSet<Method> lllllllllllllllllIIlllIllIlIllll = new HashSet<Method>(Arrays.asList(lllllllllllllllllIIlllIllIllIIIl));
        lllllllllllllllllIIlllIllIlIllll.retainAll(Arrays.asList(lllllllllllllllllIIlllIllIllIIII));
        Iterator lllllllllllllllllIIlllIllIllIlIl = lllllllllllllllllIIlllIllIlIllll.iterator();
        while (lllllllllllllllllIIlllIllIllIlIl.hasNext()) {
            Method lllllllllllllllllIIlllIllIllIllI = (Method)lllllllllllllllllIIlllIllIllIlIl.next();
            if (!Callback.FORBIDDEN_NAMES.contains(lllllllllllllllllIIlllIllIllIllI.getName())) continue;
            lllllllllllllllllIIlllIllIllIlIl.remove();
        }
        Method[] lllllllllllllllllIIlllIllIlIlllI = lllllllllllllllllIIlllIllIlIllll.toArray(new Method[lllllllllllllllllIIlllIllIlIllll.size()]);
        if (lllllllllllllllllIIlllIllIlIlllI.length == 1) {
            return CallbackReference.checkMethod(lllllllllllllllllIIlllIllIlIlllI[0]);
        }
        for (int lllllllllllllllllIIlllIllIllIIll = 0; lllllllllllllllllIIlllIllIllIIll < lllllllllllllllllIIlllIllIlIlllI.length; ++lllllllllllllllllIIlllIllIllIIll) {
            Method lllllllllllllllllIIlllIllIllIlII = lllllllllllllllllIIlllIllIlIlllI[lllllllllllllllllIIlllIllIllIIll];
            if (!"callback".equals(lllllllllllllllllIIlllIllIllIlII.getName())) continue;
            return CallbackReference.checkMethod(lllllllllllllllllIIlllIllIllIlII);
        }
        String lllllllllllllllllIIlllIllIlIllIl = "Callback must implement a single public method, or one public method named 'callback'";
        throw new IllegalArgumentException(lllllllllllllllllIIlllIllIlIllIl);
    }

    public static Pointer getFunctionPointer(Callback lllllllllllllllllIIlllIllIIIIIlI) {
        return CallbackReference.getFunctionPointer(lllllllllllllllllIIlllIllIIIIIlI, false);
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
        catch (Exception lllllllllllllllllIIlllIlIlIlIIIl) {
            throw new Error("Error looking up CallbackProxy.callback() method");
        }
        initializers = new WeakHashMap<Callback, CallbackThreadInitializer>();
    }

    private static boolean isAllowableNativeType(Class<?> lllllllllllllllllIIlllIlIllIIlIl) {
        return lllllllllllllllllIIlllIlIllIIlIl == Void.TYPE || lllllllllllllllllIIlllIlIllIIlIl == Void.class || lllllllllllllllllIIlllIlIllIIlIl == Boolean.TYPE || lllllllllllllllllIIlllIlIllIIlIl == Boolean.class || lllllllllllllllllIIlllIlIllIIlIl == Byte.TYPE || lllllllllllllllllIIlllIlIllIIlIl == Byte.class || lllllllllllllllllIIlllIlIllIIlIl == Short.TYPE || lllllllllllllllllIIlllIlIllIIlIl == Short.class || lllllllllllllllllIIlllIlIllIIlIl == Character.TYPE || lllllllllllllllllIIlllIlIllIIlIl == Character.class || lllllllllllllllllIIlllIlIllIIlIl == Integer.TYPE || lllllllllllllllllIIlllIlIllIIlIl == Integer.class || lllllllllllllllllIIlllIlIllIIlIl == Long.TYPE || lllllllllllllllllIIlllIlIllIIlIl == Long.class || lllllllllllllllllIIlllIlIllIIlIl == Float.TYPE || lllllllllllllllllIIlllIlIllIIlIl == Float.class || lllllllllllllllllIIlllIlIllIIlIl == Double.TYPE || lllllllllllllllllIIlllIlIllIIlIl == Double.class || Structure.ByValue.class.isAssignableFrom(lllllllllllllllllIIlllIlIllIIlIl) && Structure.class.isAssignableFrom(lllllllllllllllllIIlllIlIllIIlIl) || Pointer.class.isAssignableFrom(lllllllllllllllllIIlllIlIllIIlIl);
    }

    private static Method checkMethod(Method lllllllllllllllllIIlllIlllIIllll) {
        if (lllllllllllllllllIIlllIlllIIllll.getParameterTypes().length > 256) {
            String lllllllllllllllllIIlllIlllIlIIII = String.valueOf(new StringBuilder().append("Method signature exceeds the maximum parameter count: ").append(lllllllllllllllllIIlllIlllIIllll));
            throw new UnsupportedOperationException(lllllllllllllllllIIlllIlllIlIIII);
        }
        return lllllllllllllllllIIlllIlllIIllll;
    }

    private static class NativeFunctionHandler
    implements InvocationHandler {
        private final /* synthetic */ Map<String, ?> options;
        private final /* synthetic */ Function function;

        @Override
        public Object invoke(Object lllIIlIIlII, Method lllIIlIIIll, Object[] lllIIlIIIlI) throws Throwable {
            NativeFunctionHandler lllIIlIIIIl;
            if (Library.Handler.OBJECT_TOSTRING.equals(lllIIlIIIll)) {
                String lllIIlIlIIl = String.valueOf(new StringBuilder().append("Proxy interface to ").append(lllIIlIIIIl.function));
                Method lllIIlIlIII = (Method)lllIIlIIIIl.options.get("invoking-method");
                Class<?> lllIIlIIlll = CallbackReference.findCallbackClass(lllIIlIlIII.getDeclaringClass());
                lllIIlIlIIl = String.valueOf(new StringBuilder().append(lllIIlIlIIl).append(" (").append(lllIIlIIlll.getName()).append(")"));
                return lllIIlIlIIl;
            }
            if (Library.Handler.OBJECT_HASHCODE.equals(lllIIlIIIll)) {
                return lllIIlIIIIl.hashCode();
            }
            if (Library.Handler.OBJECT_EQUALS.equals(lllIIlIIIll)) {
                Object lllIIlIIllI = lllIIlIIIlI[0];
                if (lllIIlIIllI != null && Proxy.isProxyClass(lllIIlIIllI.getClass())) {
                    return Function.valueOf(Proxy.getInvocationHandler(lllIIlIIllI) == lllIIlIIIIl);
                }
                return Boolean.FALSE;
            }
            if (Function.isVarArgs(lllIIlIIIll)) {
                lllIIlIIIlI = Function.concatenateVarArgs(lllIIlIIIlI);
            }
            return lllIIlIIIIl.function.invoke(lllIIlIIIll.getReturnType(), lllIIlIIIlI, lllIIlIIIIl.options);
        }

        public NativeFunctionHandler(Pointer lllIIllIIlI, int lllIIllIIIl, Map<String, ?> lllIIllIIII) {
            NativeFunctionHandler lllIIllIIll;
            lllIIllIIll.options = lllIIllIIII;
            lllIIllIIll.function = new Function(lllIIllIIlI, lllIIllIIIl, (String)lllIIllIIII.get("string-encoding"));
        }

        public Pointer getPointer() {
            NativeFunctionHandler lllIIIllIIl;
            return lllIIIllIIl.function;
        }
    }

    private class DefaultCallbackProxy
    implements CallbackProxy {
        private final /* synthetic */ String encoding;
        private final /* synthetic */ Method callbackMethod;
        private final /* synthetic */ FromNativeConverter[] fromNative;
        private /* synthetic */ ToNativeConverter toNative;

        @Override
        public Class<?> getReturnType() {
            DefaultCallbackProxy lllllllllllllllllIlIlllIIIlIlIlI;
            return lllllllllllllllllIlIlllIIIlIlIlI.callbackMethod.getReturnType();
        }

        private Object invokeCallback(Object[] lllllllllllllllllIlIlllIIlIllIlI) {
            DefaultCallbackProxy lllllllllllllllllIlIlllIIllIIIIl;
            Class<?>[] lllllllllllllllllIlIlllIIlIlllll = lllllllllllllllllIlIlllIIllIIIIl.callbackMethod.getParameterTypes();
            Object[] lllllllllllllllllIlIlllIIlIllllI = new Object[lllllllllllllllllIlIlllIIlIllIlI.length];
            for (int lllllllllllllllllIlIlllIIllIIllI = 0; lllllllllllllllllIlIlllIIllIIllI < lllllllllllllllllIlIlllIIlIllIlI.length; ++lllllllllllllllllIlIlllIIllIIllI) {
                Class<?> lllllllllllllllllIlIlllIIllIlIII = lllllllllllllllllIlIlllIIlIlllll[lllllllllllllllllIlIlllIIllIIllI];
                Object lllllllllllllllllIlIlllIIllIIlll = lllllllllllllllllIlIlllIIlIllIlI[lllllllllllllllllIlIlllIIllIIllI];
                if (lllllllllllllllllIlIlllIIllIIIIl.fromNative[lllllllllllllllllIlIlllIIllIIllI] != null) {
                    CallbackParameterContext lllllllllllllllllIlIlllIIllIlIIl = new CallbackParameterContext(lllllllllllllllllIlIlllIIllIlIII, lllllllllllllllllIlIlllIIllIIIIl.callbackMethod, lllllllllllllllllIlIlllIIlIllIlI, lllllllllllllllllIlIlllIIllIIllI);
                    lllllllllllllllllIlIlllIIlIllllI[lllllllllllllllllIlIlllIIllIIllI] = lllllllllllllllllIlIlllIIllIIIIl.fromNative[lllllllllllllllllIlIlllIIllIIllI].fromNative(lllllllllllllllllIlIlllIIllIIlll, lllllllllllllllllIlIlllIIllIlIIl);
                    continue;
                }
                lllllllllllllllllIlIlllIIlIllllI[lllllllllllllllllIlIlllIIllIIllI] = lllllllllllllllllIlIlllIIllIIIIl.convertArgument(lllllllllllllllllIlIlllIIllIIlll, lllllllllllllllllIlIlllIIllIlIII);
            }
            Object lllllllllllllllllIlIlllIIlIlllIl = null;
            Callback lllllllllllllllllIlIlllIIlIlllII = lllllllllllllllllIlIlllIIllIIIIl.getCallback();
            if (lllllllllllllllllIlIlllIIlIlllII != null) {
                try {
                    lllllllllllllllllIlIlllIIlIlllIl = lllllllllllllllllIlIlllIIllIIIIl.convertResult(lllllllllllllllllIlIlllIIllIIIIl.callbackMethod.invoke(lllllllllllllllllIlIlllIIlIlllII, lllllllllllllllllIlIlllIIlIllllI));
                }
                catch (IllegalArgumentException lllllllllllllllllIlIlllIIllIIlIl) {
                    Native.getCallbackExceptionHandler().uncaughtException(lllllllllllllllllIlIlllIIlIlllII, lllllllllllllllllIlIlllIIllIIlIl);
                }
                catch (IllegalAccessException lllllllllllllllllIlIlllIIllIIlII) {
                    Native.getCallbackExceptionHandler().uncaughtException(lllllllllllllllllIlIlllIIlIlllII, lllllllllllllllllIlIlllIIllIIlII);
                }
                catch (InvocationTargetException lllllllllllllllllIlIlllIIllIIIll) {
                    Native.getCallbackExceptionHandler().uncaughtException(lllllllllllllllllIlIlllIIlIlllII, lllllllllllllllllIlIlllIIllIIIll.getTargetException());
                }
            }
            for (int lllllllllllllllllIlIlllIIllIIIlI = 0; lllllllllllllllllIlIlllIIllIIIlI < lllllllllllllllllIlIlllIIlIllllI.length; ++lllllllllllllllllIlIlllIIllIIIlI) {
                if (!(lllllllllllllllllIlIlllIIlIllllI[lllllllllllllllllIlIlllIIllIIIlI] instanceof Structure) || lllllllllllllllllIlIlllIIlIllllI[lllllllllllllllllIlIlllIIllIIIlI] instanceof Structure.ByValue) continue;
                ((Structure)lllllllllllllllllIlIlllIIlIllllI[lllllllllllllllllIlIlllIIllIIIlI]).autoWrite();
            }
            return lllllllllllllllllIlIlllIIlIlllIl;
        }

        private Object convertArgument(Object lllllllllllllllllIlIlllIIIlllllI, Class<?> lllllllllllllllllIlIlllIIIllllIl) {
            if (lllllllllllllllllIlIlllIIIlllllI instanceof Pointer) {
                DefaultCallbackProxy lllllllllllllllllIlIlllIIlIIIIlI;
                if (lllllllllllllllllIlIlllIIIllllIl == String.class) {
                    lllllllllllllllllIlIlllIIIlllllI = ((Pointer)lllllllllllllllllIlIlllIIIlllllI).getString(0L, lllllllllllllllllIlIlllIIlIIIIlI.encoding);
                } else if (lllllllllllllllllIlIlllIIIllllIl == WString.class) {
                    lllllllllllllllllIlIlllIIIlllllI = new WString(((Pointer)lllllllllllllllllIlIlllIIIlllllI).getWideString(0L));
                } else if (lllllllllllllllllIlIlllIIIllllIl == String[].class) {
                    lllllllllllllllllIlIlllIIIlllllI = ((Pointer)lllllllllllllllllIlIlllIIIlllllI).getStringArray(0L, lllllllllllllllllIlIlllIIlIIIIlI.encoding);
                } else if (lllllllllllllllllIlIlllIIIllllIl == WString[].class) {
                    lllllllllllllllllIlIlllIIIlllllI = ((Pointer)lllllllllllllllllIlIlllIIIlllllI).getWideStringArray(0L);
                } else if (Callback.class.isAssignableFrom(lllllllllllllllllIlIlllIIIllllIl)) {
                    lllllllllllllllllIlIlllIIIlllllI = CallbackReference.getCallback(lllllllllllllllllIlIlllIIIllllIl, (Pointer)lllllllllllllllllIlIlllIIIlllllI);
                } else if (Structure.class.isAssignableFrom(lllllllllllllllllIlIlllIIIllllIl)) {
                    if (Structure.ByValue.class.isAssignableFrom(lllllllllllllllllIlIlllIIIllllIl)) {
                        Structure lllllllllllllllllIlIlllIIlIIIlIl = Structure.newInstance(lllllllllllllllllIlIlllIIIllllIl);
                        byte[] lllllllllllllllllIlIlllIIlIIIlII = new byte[lllllllllllllllllIlIlllIIlIIIlIl.size()];
                        ((Pointer)lllllllllllllllllIlIlllIIIlllllI).read(0L, lllllllllllllllllIlIlllIIlIIIlII, 0, lllllllllllllllllIlIlllIIlIIIlII.length);
                        lllllllllllllllllIlIlllIIlIIIlIl.getPointer().write(0L, lllllllllllllllllIlIlllIIlIIIlII, 0, lllllllllllllllllIlIlllIIlIIIlII.length);
                        lllllllllllllllllIlIlllIIlIIIlIl.read();
                        lllllllllllllllllIlIlllIIIlllllI = lllllllllllllllllIlIlllIIlIIIlIl;
                    } else {
                        Structure lllllllllllllllllIlIlllIIlIIIIll = Structure.newInstance(lllllllllllllllllIlIlllIIIllllIl, (Pointer)lllllllllllllllllIlIlllIIIlllllI);
                        lllllllllllllllllIlIlllIIlIIIIll.conditionalAutoRead();
                        lllllllllllllllllIlIlllIIIlllllI = lllllllllllllllllIlIlllIIlIIIIll;
                    }
                }
            } else if ((Boolean.TYPE == lllllllllllllllllIlIlllIIIllllIl || Boolean.class == lllllllllllllllllIlIlllIIIllllIl) && lllllllllllllllllIlIlllIIIlllllI instanceof Number) {
                lllllllllllllllllIlIlllIIIlllllI = Function.valueOf(((Number)lllllllllllllllllIlIlllIIIlllllI).intValue() != 0);
            }
            return lllllllllllllllllIlIlllIIIlllllI;
        }

        private Object convertResult(Object lllllllllllllllllIlIlllIIIllIIIl) {
            DefaultCallbackProxy lllllllllllllllllIlIlllIIIllIIlI;
            if (lllllllllllllllllIlIlllIIIllIIlI.toNative != null) {
                lllllllllllllllllIlIlllIIIllIIIl = lllllllllllllllllIlIlllIIIllIIlI.toNative.toNative(lllllllllllllllllIlIlllIIIllIIIl, new CallbackResultContext(lllllllllllllllllIlIlllIIIllIIlI.callbackMethod));
            }
            if (lllllllllllllllllIlIlllIIIllIIIl == null) {
                return null;
            }
            Class<?> lllllllllllllllllIlIlllIIIllIIll = lllllllllllllllllIlIlllIIIllIIIl.getClass();
            if (Structure.class.isAssignableFrom(lllllllllllllllllIlIlllIIIllIIll)) {
                if (Structure.ByValue.class.isAssignableFrom(lllllllllllllllllIlIlllIIIllIIll)) {
                    return lllllllllllllllllIlIlllIIIllIIIl;
                }
                return ((Structure)lllllllllllllllllIlIlllIIIllIIIl).getPointer();
            }
            if (lllllllllllllllllIlIlllIIIllIIll == Boolean.TYPE || lllllllllllllllllIlIlllIIIllIIll == Boolean.class) {
                return Boolean.TRUE.equals(lllllllllllllllllIlIlllIIIllIIIl) ? Function.INTEGER_TRUE : Function.INTEGER_FALSE;
            }
            if (lllllllllllllllllIlIlllIIIllIIll == String.class || lllllllllllllllllIlIlllIIIllIIll == WString.class) {
                return CallbackReference.getNativeString(lllllllllllllllllIlIlllIIIllIIIl, lllllllllllllllllIlIlllIIIllIIll == WString.class);
            }
            if (lllllllllllllllllIlIlllIIIllIIll == String[].class || lllllllllllllllllIlIlllIIIllIIll == WString.class) {
                StringArray lllllllllllllllllIlIlllIIIllIllI = lllllllllllllllllIlIlllIIIllIIll == String[].class ? new StringArray((String[])lllllllllllllllllIlIlllIIIllIIIl, lllllllllllllllllIlIlllIIIllIIlI.encoding) : new StringArray((WString[])lllllllllllllllllIlIlllIIIllIIIl);
                allocations.put(lllllllllllllllllIlIlllIIIllIIIl, lllllllllllllllllIlIlllIIIllIllI);
                return lllllllllllllllllIlIlllIIIllIllI;
            }
            if (Callback.class.isAssignableFrom(lllllllllllllllllIlIlllIIIllIIll)) {
                return CallbackReference.getFunctionPointer((Callback)lllllllllllllllllIlIlllIIIllIIIl);
            }
            return lllllllllllllllllIlIlllIIIllIIIl;
        }

        @Override
        public Class<?>[] getParameterTypes() {
            DefaultCallbackProxy lllllllllllllllllIlIlllIIIlIllII;
            return lllllllllllllllllIlIlllIIIlIllII.callbackMethod.getParameterTypes();
        }

        public DefaultCallbackProxy(Method lllllllllllllllllIlIlllIlIIIIIIl, TypeMapper lllllllllllllllllIlIlllIlIIIIIII, String lllllllllllllllllIlIlllIIlllllll) {
            DefaultCallbackProxy lllllllllllllllllIlIlllIlIIIIIlI;
            lllllllllllllllllIlIlllIlIIIIIlI.callbackMethod = lllllllllllllllllIlIlllIlIIIIIIl;
            lllllllllllllllllIlIlllIlIIIIIlI.encoding = lllllllllllllllllIlIlllIIlllllll;
            Class<?>[] lllllllllllllllllIlIlllIIllllllI = lllllllllllllllllIlIlllIlIIIIIIl.getParameterTypes();
            Class<?> lllllllllllllllllIlIlllIIlllllIl = lllllllllllllllllIlIlllIlIIIIIIl.getReturnType();
            lllllllllllllllllIlIlllIlIIIIIlI.fromNative = new FromNativeConverter[lllllllllllllllllIlIlllIIllllllI.length];
            if (NativeMapped.class.isAssignableFrom(lllllllllllllllllIlIlllIIlllllIl)) {
                lllllllllllllllllIlIlllIlIIIIIlI.toNative = NativeMappedConverter.getInstance(lllllllllllllllllIlIlllIIlllllIl);
            } else if (lllllllllllllllllIlIlllIlIIIIIII != null) {
                lllllllllllllllllIlIlllIlIIIIIlI.toNative = lllllllllllllllllIlIlllIlIIIIIII.getToNativeConverter(lllllllllllllllllIlIlllIIlllllIl);
            }
            for (int lllllllllllllllllIlIlllIlIIIIlII = 0; lllllllllllllllllIlIlllIlIIIIlII < lllllllllllllllllIlIlllIlIIIIIlI.fromNative.length; ++lllllllllllllllllIlIlllIlIIIIlII) {
                if (NativeMapped.class.isAssignableFrom(lllllllllllllllllIlIlllIIllllllI[lllllllllllllllllIlIlllIlIIIIlII])) {
                    lllllllllllllllllIlIlllIlIIIIIlI.fromNative[lllllllllllllllllIlIlllIlIIIIlII] = new NativeMappedConverter(lllllllllllllllllIlIlllIIllllllI[lllllllllllllllllIlIlllIlIIIIlII]);
                    continue;
                }
                if (lllllllllllllllllIlIlllIlIIIIIII == null) continue;
                lllllllllllllllllIlIlllIlIIIIIlI.fromNative[lllllllllllllllllIlIlllIlIIIIlII] = lllllllllllllllllIlIlllIlIIIIIII.getFromNativeConverter(lllllllllllllllllIlIlllIIllllllI[lllllllllllllllllIlIlllIlIIIIlII]);
            }
            if (!lllllllllllllllllIlIlllIlIIIIIIl.isAccessible()) {
                try {
                    lllllllllllllllllIlIlllIlIIIIIIl.setAccessible(true);
                }
                catch (SecurityException lllllllllllllllllIlIlllIlIIIIIll) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Callback method is inaccessible, make sure the interface is public: ").append(lllllllllllllllllIlIlllIlIIIIIIl)));
                }
            }
        }

        @Override
        public Object callback(Object[] lllllllllllllllllIlIlllIIlIIlllI) {
            DefaultCallbackProxy lllllllllllllllllIlIlllIIlIIllll;
            try {
                return lllllllllllllllllIlIlllIIlIIllll.invokeCallback(lllllllllllllllllIlIlllIIlIIlllI);
            }
            catch (Throwable lllllllllllllllllIlIlllIIlIlIIII) {
                Native.getCallbackExceptionHandler().uncaughtException(lllllllllllllllllIlIlllIIlIIllll.getCallback(), lllllllllllllllllIlIlllIIlIlIIII);
                return null;
            }
        }

        public Callback getCallback() {
            DefaultCallbackProxy lllllllllllllllllIlIlllIIlllIIlI;
            return lllllllllllllllllIlIlllIIlllIIlI.CallbackReference.this.getCallback();
        }
    }

    static class AttachOptions
    extends Structure {
        public /* synthetic */ String name;
        public /* synthetic */ boolean detach;
        public /* synthetic */ boolean daemon;
        public static final /* synthetic */ List<String> FIELDS;

        static {
            FIELDS = AttachOptions.createFieldsOrder("daemon", "detach", "name");
        }

        AttachOptions() {
            AttachOptions lIIIllllllllI;
            lIIIllllllllI.setStringEncoding("utf8");
        }

        @Override
        protected List<String> getFieldOrder() {
            return FIELDS;
        }
    }
}

