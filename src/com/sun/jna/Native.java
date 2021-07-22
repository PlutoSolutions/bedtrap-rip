/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Callback;
import com.sun.jna.CallbackReference;
import com.sun.jna.CallbackThreadInitializer;
import com.sun.jna.FromNativeContext;
import com.sun.jna.FromNativeConverter;
import com.sun.jna.Function;
import com.sun.jna.IntegerType;
import com.sun.jna.LastErrorException;
import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.MethodResultContext;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeMapped;
import com.sun.jna.NativeMappedConverter;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.Structure;
import com.sun.jna.ToNativeContext;
import com.sun.jna.ToNativeConverter;
import com.sun.jna.TypeMapper;
import com.sun.jna.Version;
import com.sun.jna.WString;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Window;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

public final class Native
implements Version {
    private static final /* synthetic */ int CVT_BOOLEAN;
    private static final /* synthetic */ int TYPE_SIZE_T;
    private static final /* synthetic */ ThreadLocal<Memory> nativeThreadTerminationFlag;
    private static final /* synthetic */ int CVT_CALLBACK;
    static final /* synthetic */ int CB_OPTION_DIRECT;
    private static final /* synthetic */ int CVT_POINTER;
    private static final /* synthetic */ Object finalizer;
    private static final /* synthetic */ int TYPE_VOIDP;
    static final /* synthetic */ int MAX_ALIGNMENT;
    private static final /* synthetic */ int CVT_DEFAULT;
    private static final /* synthetic */ int CVT_UNSUPPORTED;
    private static final /* synthetic */ int TYPE_BOOL;
    private static final /* synthetic */ int CVT_NATIVE_MAPPED_WSTRING;
    private static final /* synthetic */ int CVT_NATIVE_MAPPED_STRING;
    private static final /* synthetic */ int CVT_FLOAT;
    private static final /* synthetic */ int CVT_TYPE_MAPPER;
    public static /* synthetic */ boolean DEBUG_LOAD;
    private static final /* synthetic */ Map<Class<?>, Reference<?>> libraries;
    private static final /* synthetic */ int TYPE_WCHAR_T;
    private static final /* synthetic */ int CVT_BUFFER;
    public static final /* synthetic */ int BOOL_SIZE;
    private static final /* synthetic */ int CVT_WSTRING;
    private static final /* synthetic */ int CVT_INTEGER_TYPE;
    static final /* synthetic */ int MAX_PADDING;
    private static final /* synthetic */ String _OPTION_ENCLOSING_LIBRARY;
    private static final /* synthetic */ int CVT_TYPE_MAPPER_WSTRING;
    private static final /* synthetic */ Callback.UncaughtExceptionHandler DEFAULT_HANDLER;
    private static final /* synthetic */ int CVT_ARRAY_INT;
    private static final /* synthetic */ int CVT_STRUCTURE_BYVAL;
    private static final /* synthetic */ Map<Class<?>, Map<String, Object>> typeOptions;
    public static final /* synthetic */ String DEFAULT_ENCODING;
    private static final /* synthetic */ Map<Thread, Pointer> nativeThreads;
    private static final /* synthetic */ int CVT_ARRAY_CHAR;
    public static final /* synthetic */ int POINTER_SIZE;
    static final /* synthetic */ int CB_HAS_INITIALIZER;
    private static final /* synthetic */ int CVT_ARRAY_BOOLEAN;
    private static final /* synthetic */ int CVT_NATIVE_MAPPED;
    public static /* synthetic */ boolean DEBUG_JNA_LOAD;
    private static final /* synthetic */ int CVT_ARRAY_BYTE;
    public static final /* synthetic */ int SIZE_T_SIZE;
    static /* synthetic */ String jnidispatchPath;
    static final /* synthetic */ String JNA_TMPLIB_PREFIX;
    private static /* synthetic */ Callback.UncaughtExceptionHandler callbackExceptionHandler;
    public static final /* synthetic */ int LONG_SIZE;
    private static /* synthetic */ Map<Class<?>, long[]> registeredClasses;
    private static final /* synthetic */ int CVT_STRING;
    private static final /* synthetic */ int CVT_ARRAY_LONG;
    private static /* synthetic */ Map<Class<?>, NativeLibrary> registeredLibraries;
    private static final /* synthetic */ int CVT_STRUCTURE;
    private static final /* synthetic */ int CVT_ARRAY_FLOAT;
    public static final /* synthetic */ int WCHAR_SIZE;
    private static final /* synthetic */ int CVT_ARRAY_SHORT;
    private static final /* synthetic */ int CVT_POINTER_TYPE;
    private static final /* synthetic */ int TYPE_LONG;
    private static final /* synthetic */ int CVT_TYPE_MAPPER_STRING;
    static final /* synthetic */ int CB_OPTION_IN_DLL;
    private static final /* synthetic */ int CVT_ARRAY_DOUBLE;

    static void markTemporaryFile(File llllllllllllllllIlIllIllIIllIIII) {
        try {
            File llllllllllllllllIlIllIllIIllIIll = new File(llllllllllllllllIlIllIllIIllIIII.getParentFile(), String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIllIIllIIII.getName()).append(".x")));
            llllllllllllllllIlIllIllIIllIIll.createNewFile();
        }
        catch (IOException llllllllllllllllIlIllIllIIllIIlI) {
            llllllllllllllllIlIllIllIIllIIlI.printStackTrace();
        }
    }

    static /* bridge */ native /* synthetic */ byte[] getStringBytes(Pointer var0, long var1, long var3);

    public static long getWindowID(Window llllllllllllllllIlIlllIIlIIIlIII) throws HeadlessException {
        return AWT.getWindowID(llllllllllllllllIlIlllIIlIIIlIII);
    }

    static String getSignature(Class<?> llllllllllllllllIlIllIlIlIlllIll) {
        if (llllllllllllllllIlIllIlIlIlllIll.isArray()) {
            return String.valueOf(new StringBuilder().append("[").append(Native.getSignature(llllllllllllllllIlIllIlIlIlllIll.getComponentType())));
        }
        if (llllllllllllllllIlIllIlIlIlllIll.isPrimitive()) {
            if (llllllllllllllllIlIllIlIlIlllIll == Void.TYPE) {
                return "V";
            }
            if (llllllllllllllllIlIllIlIlIlllIll == Boolean.TYPE) {
                return "Z";
            }
            if (llllllllllllllllIlIllIlIlIlllIll == Byte.TYPE) {
                return "B";
            }
            if (llllllllllllllllIlIllIlIlIlllIll == Short.TYPE) {
                return "S";
            }
            if (llllllllllllllllIlIllIlIlIlllIll == Character.TYPE) {
                return "C";
            }
            if (llllllllllllllllIlIllIlIlIlllIll == Integer.TYPE) {
                return "I";
            }
            if (llllllllllllllllIlIllIlIlIlllIll == Long.TYPE) {
                return "J";
            }
            if (llllllllllllllllIlIllIlIlIlllIll == Float.TYPE) {
                return "F";
            }
            if (llllllllllllllllIlIllIlIlIlllIll == Double.TYPE) {
                return "D";
            }
        }
        return String.valueOf(new StringBuilder().append("L").append(Native.replace(".", "/", llllllllllllllllIlIllIlIlIlllIll.getName())).append(";"));
    }

    private static /* bridge */ native /* synthetic */ void setDetachState(boolean var0, long var1);

    public static String getDefaultStringEncoding() {
        return System.getProperty("jna.encoding", DEFAULT_ENCODING);
    }

    static /* bridge */ native /* synthetic */ int initialize_ffi_type(long var0);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void unregisterAll() {
        Map<Class<?>, long[]> llllllllllllllllIlIllIlIllIlIIIl = registeredClasses;
        synchronized (llllllllllllllllIlIllIlIllIlIIIl) {
            for (Map.Entry<Class<?>, long[]> llllllllllllllllIlIllIlIllIlIIlI : registeredClasses.entrySet()) {
                Native.unregister(llllllllllllllllIlIllIlIllIlIIlI.getKey(), llllllllllllllllIlIllIlIllIlIIlI.getValue());
            }
            registeredClasses.clear();
        }
    }

    public static /* bridge */ native /* synthetic */ void free(long var0);

    private static Class<?> nativeType(Class<?> llllllllllllllllIlIllIIlIIIIllIl) {
        return NativeMappedConverter.getInstance(llllllllllllllllIlIllIIlIIIIllIl).nativeType();
    }

    public static Pointer getWindowPointer(Window llllllllllllllllIlIlllIIlIIIIIlI) throws HeadlessException {
        return new Pointer(AWT.getWindowID(llllllllllllllllIlIlllIIlIIIIIlI));
    }

    static Class<?> getCallingClass() {
        Class<?>[] llllllllllllllllIlIllIlIllIllllI = new SecurityManager(){

            @Override
            public Class<?>[] getClassContext() {
                6 llIIllIIIllIlIl;
                return super.getClassContext();
            }
            {
                6 llIIllIIIlllIII;
            }
        }.getClassContext();
        if (llllllllllllllllIlIllIlIllIllllI == null) {
            throw new IllegalStateException("The SecurityManager implementation on this platform is broken; you must explicitly provide the class to register");
        }
        if (llllllllllllllllIlIllIlIllIllllI.length < 4) {
            throw new IllegalStateException("This method must be called from the static initializer of a class");
        }
        return llllllllllllllllIlIllIlIllIllllI[3];
    }

    static /* bridge */ native /* synthetic */ long invokeLong(Function var0, long var1, int var3, Object[] var4);

    public static Pointer getDirectBufferPointer(Buffer llllllllllllllllIlIlllIIIllllIIl) {
        long llllllllllllllllIlIlllIIIllllIlI = Native._getDirectBufferPointer(llllllllllllllllIlIlllIIIllllIIl);
        return llllllllllllllllIlIlllIIIllllIlI == 0L ? null : new Pointer(llllllllllllllllIlIlllIIIllllIlI);
    }

    static /* bridge */ native /* synthetic */ void setWideString(Pointer var0, long var1, long var3, String var5);

    private static /* bridge */ native /* synthetic */ void initIDs();

    public static String getWebStartLibraryPath(String llllllllllllllllIlIllIllIIlllIIl) {
        if (System.getProperty("javawebstart.version") == null) {
            return null;
        }
        try {
            ClassLoader llllllllllllllllIlIllIllIIlllllI = Native.class.getClassLoader();
            Method llllllllllllllllIlIllIllIIllllIl = AccessController.doPrivileged(new PrivilegedAction<Method>(){

                @Override
                public Method run() {
                    try {
                        Method lllllllllllllllllllIIlIIIIlllllI = ClassLoader.class.getDeclaredMethod("findLibrary", String.class);
                        lllllllllllllllllllIIlIIIIlllllI.setAccessible(true);
                        return lllllllllllllllllllIIlIIIIlllllI;
                    }
                    catch (Exception lllllllllllllllllllIIlIIIIllllIl) {
                        return null;
                    }
                }
                {
                    4 lllllllllllllllllllIIlIIIlIIIIIl;
                }
            });
            String llllllllllllllllIlIllIllIIllllII = (String)llllllllllllllllIlIllIllIIllllIl.invoke(llllllllllllllllIlIllIllIIlllllI, llllllllllllllllIlIllIllIIlllIIl);
            if (llllllllllllllllIlIllIllIIllllII != null) {
                return new File(llllllllllllllllIlIllIllIIllllII).getParent();
            }
            return null;
        }
        catch (Exception llllllllllllllllIlIllIllIIlllIll) {
            return null;
        }
    }

    public static synchronized /* bridge */ native /* synthetic */ boolean isProtected();

    static /* bridge */ native /* synthetic */ double getDouble(Pointer var0, long var1, long var3);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void loadLibraryInstance(Class<?> llllllllllllllllIlIlllIIIIIlIlll) {
        Map<Class<?>, Reference<?>> llllllllllllllllIlIlllIIIIIlIlIl = libraries;
        synchronized (llllllllllllllllIlIlllIIIIIlIlIl) {
            if (llllllllllllllllIlIlllIIIIIlIlll != null && !libraries.containsKey(llllllllllllllllIlIlllIIIIIlIlll)) {
                try {
                    Field[] llllllllllllllllIlIlllIIIIIllIIl = llllllllllllllllIlIlllIIIIIlIlll.getFields();
                    for (int llllllllllllllllIlIlllIIIIIllIlI = 0; llllllllllllllllIlIlllIIIIIllIlI < llllllllllllllllIlIlllIIIIIllIIl.length; ++llllllllllllllllIlIlllIIIIIllIlI) {
                        Field llllllllllllllllIlIlllIIIIIllIll = llllllllllllllllIlIlllIIIIIllIIl[llllllllllllllllIlIlllIIIIIllIlI];
                        if (llllllllllllllllIlIlllIIIIIllIll.getType() != llllllllllllllllIlIlllIIIIIlIlll || !Modifier.isStatic(llllllllllllllllIlIlllIIIIIllIll.getModifiers())) continue;
                        libraries.put(llllllllllllllllIlIlllIIIIIlIlll, new WeakReference<Object>(llllllllllllllllIlIlllIIIIIllIll.get(null)));
                        break;
                    }
                }
                catch (Exception llllllllllllllllIlIlllIIIIIllIII) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Could not access instance of ").append(llllllllllllllllIlIlllIIIIIlIlll).append(" (").append(llllllllllllllllIlIlllIIIIIllIII).append(")")));
                }
            }
        }
    }

    public static List<String> toStringList(char[] llllllllllllllllIlIlllIIIlIlIIII, int llllllllllllllllIlIlllIIIlIIlIIl, int llllllllllllllllIlIlllIIIlIIlIII) {
        ArrayList<String> llllllllllllllllIlIlllIIIlIIllIl = new ArrayList<String>();
        int llllllllllllllllIlIlllIIIlIIllII = llllllllllllllllIlIlllIIIlIIlIIl;
        int llllllllllllllllIlIlllIIIlIIlIll = llllllllllllllllIlIlllIIIlIIlIIl + llllllllllllllllIlIlllIIIlIIlIII;
        for (int llllllllllllllllIlIlllIIIlIlIIlI = llllllllllllllllIlIlllIIIlIIlIIl; llllllllllllllllIlIlllIIIlIlIIlI < llllllllllllllllIlIlllIIIlIIlIll; ++llllllllllllllllIlIlllIIIlIlIIlI) {
            if (llllllllllllllllIlIlllIIIlIlIIII[llllllllllllllllIlIlllIIIlIlIIlI] != '\u0000') continue;
            if (llllllllllllllllIlIlllIIIlIIllII == llllllllllllllllIlIlllIIIlIlIIlI) {
                return llllllllllllllllIlIlllIIIlIIllIl;
            }
            String llllllllllllllllIlIlllIIIlIlIIll = new String(llllllllllllllllIlIlllIIIlIlIIII, llllllllllllllllIlIlllIIIlIIllII, llllllllllllllllIlIlllIIIlIlIIlI - llllllllllllllllIlIlllIIIlIIllII);
            llllllllllllllllIlIlllIIIlIIllIl.add(llllllllllllllllIlIlllIIIlIlIIll);
            llllllllllllllllIlIlllIIIlIIllII = llllllllllllllllIlIlllIIIlIlIIlI + 1;
        }
        if (llllllllllllllllIlIlllIIIlIIllII < llllllllllllllllIlIlllIIIlIIlIll) {
            String llllllllllllllllIlIlllIIIlIlIIIl = new String(llllllllllllllllIlIlllIIIlIlIIII, llllllllllllllllIlIlllIIIlIIllII, llllllllllllllllIlIlllIIIlIIlIll - llllllllllllllllIlIlllIIIlIIllII);
            llllllllllllllllIlIlllIIIlIIllIl.add(llllllllllllllllIlIlllIIIlIlIIIl);
        }
        return llllllllllllllllIlIlllIIIlIIllIl;
    }

    static /* bridge */ native /* synthetic */ void setByte(Pointer var0, long var1, long var3, byte var5);

    static Structure invokeStructure(Function llllllllllllllllIlIllIIIllIlllll, long llllllllllllllllIlIllIIIlllIIIll, int llllllllllllllllIlIllIIIlllIIIlI, Object[] llllllllllllllllIlIllIIIllIlllII, Structure llllllllllllllllIlIllIIIllIllIll) {
        Native.invokeStructure(llllllllllllllllIlIllIIIllIlllll, llllllllllllllllIlIllIIIlllIIIll, llllllllllllllllIlIllIIIlllIIIlI, llllllllllllllllIlIllIIIllIlllII, llllllllllllllllIlIllIIIllIllIll.getPointer().peer, llllllllllllllllIlIllIIIllIllIll.getTypeInfo().peer);
        return llllllllllllllllIlIllIIIllIllIll;
    }

    static /* bridge */ native /* synthetic */ long findSymbol(long var0, String var2);

    static boolean isUnpacked(File llllllllllllllllIlIllIlllIIIIIIl) {
        return llllllllllllllllIlIllIlllIIIIIIl.getName().startsWith("jna");
    }

    private static /* bridge */ native /* synthetic */ String getAPIChecksum();

    public static void register(String llllllllllllllllIlIllIlIllllIlII) {
        Native.register(Native.findDirectMappedClass(Native.getCallingClass()), llllllllllllllllIlIllIlIllllIlII);
    }

    public static String getStringEncoding(Class<?> llllllllllllllllIlIllIllllIlIlII) {
        Map<String, Object> llllllllllllllllIlIllIllllIlIllI = Native.getLibraryOptions(llllllllllllllllIlIllIllllIlIlII);
        String llllllllllllllllIlIllIllllIlIlIl = (String)llllllllllllllllIlIllIllllIlIllI.get("string-encoding");
        return llllllllllllllllIlIllIllllIlIlIl != null ? llllllllllllllllIlIllIllllIlIlIl : Native.getDefaultStringEncoding();
    }

    private static void loadNativeDispatchLibraryFromClasspath() {
        try {
            String llllllllllllllllIlIllIlllIIIIlll = String.valueOf(new StringBuilder().append("/com/sun/jna/").append(Platform.RESOURCE_PREFIX).append("/").append(System.mapLibraryName("jnidispatch").replace(".dylib", ".jnilib")));
            File llllllllllllllllIlIllIlllIIIIllI = Native.extractFromResourcePath(llllllllllllllllIlIllIlllIIIIlll, Native.class.getClassLoader());
            if (llllllllllllllllIlIllIlllIIIIllI == null && llllllllllllllllIlIllIlllIIIIllI == null) {
                throw new UnsatisfiedLinkError("Could not find JNA native support");
            }
            if (DEBUG_JNA_LOAD) {
                System.out.println(String.valueOf(new StringBuilder().append("Trying ").append(llllllllllllllllIlIllIlllIIIIllI.getAbsolutePath())));
            }
            System.setProperty("jnidispatch.path", llllllllllllllllIlIllIlllIIIIllI.getAbsolutePath());
            System.load(llllllllllllllllIlIllIlllIIIIllI.getAbsolutePath());
            jnidispatchPath = llllllllllllllllIlIllIlllIIIIllI.getAbsolutePath();
            if (DEBUG_JNA_LOAD) {
                System.out.println(String.valueOf(new StringBuilder().append("Found jnidispatch at ").append(jnidispatchPath)));
            }
            if (Native.isUnpacked(llllllllllllllllIlIllIlllIIIIllI) && !Boolean.getBoolean("jnidispatch.preserve")) {
                Native.deleteLibrary(llllllllllllllllIlIllIlllIIIIllI);
            }
        }
        catch (IOException llllllllllllllllIlIllIlllIIIIlIl) {
            throw new UnsatisfiedLinkError(llllllllllllllllIlIllIlllIIIIlIl.getMessage());
        }
    }

    public static int getNativeSize(Class<?> llllllllllllllllIlIllIllIIIIIIII) {
        if (NativeMapped.class.isAssignableFrom(llllllllllllllllIlIllIllIIIIIIII)) {
            llllllllllllllllIlIllIllIIIIIIII = NativeMappedConverter.getInstance(llllllllllllllllIlIllIllIIIIIIII).nativeType();
        }
        if (llllllllllllllllIlIllIllIIIIIIII == Boolean.TYPE || llllllllllllllllIlIllIllIIIIIIII == Boolean.class) {
            return 4;
        }
        if (llllllllllllllllIlIllIllIIIIIIII == Byte.TYPE || llllllllllllllllIlIllIllIIIIIIII == Byte.class) {
            return 1;
        }
        if (llllllllllllllllIlIllIllIIIIIIII == Short.TYPE || llllllllllllllllIlIllIllIIIIIIII == Short.class) {
            return 2;
        }
        if (llllllllllllllllIlIllIllIIIIIIII == Character.TYPE || llllllllllllllllIlIllIllIIIIIIII == Character.class) {
            return WCHAR_SIZE;
        }
        if (llllllllllllllllIlIllIllIIIIIIII == Integer.TYPE || llllllllllllllllIlIllIllIIIIIIII == Integer.class) {
            return 4;
        }
        if (llllllllllllllllIlIllIllIIIIIIII == Long.TYPE || llllllllllllllllIlIllIllIIIIIIII == Long.class) {
            return 8;
        }
        if (llllllllllllllllIlIllIllIIIIIIII == Float.TYPE || llllllllllllllllIlIllIllIIIIIIII == Float.class) {
            return 4;
        }
        if (llllllllllllllllIlIllIllIIIIIIII == Double.TYPE || llllllllllllllllIlIllIllIIIIIIII == Double.class) {
            return 8;
        }
        if (Structure.class.isAssignableFrom(llllllllllllllllIlIllIllIIIIIIII)) {
            if (Structure.ByValue.class.isAssignableFrom(llllllllllllllllIlIllIllIIIIIIII)) {
                return Structure.size(llllllllllllllllIlIllIllIIIIIIII);
            }
            return POINTER_SIZE;
        }
        if (Pointer.class.isAssignableFrom(llllllllllllllllIlIllIllIIIIIIII) || Platform.HAS_BUFFERS && Buffers.isBuffer(llllllllllllllllIlIllIllIIIIIIII) || Callback.class.isAssignableFrom(llllllllllllllllIlIllIllIIIIIIII) || String.class == llllllllllllllllIlIllIllIIIIIIII || WString.class == llllllllllllllllIlIllIllIIIIIIII) {
            return POINTER_SIZE;
        }
        throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Native size for type \"").append(llllllllllllllllIlIllIllIIIIIIII.getName()).append("\" is unknown")));
    }

    public static void detach(boolean llllllllllllllllIlIllIIIlIlllIII) {
        Thread llllllllllllllllIlIllIIIlIllIlll = Thread.currentThread();
        if (llllllllllllllllIlIllIIIlIlllIII) {
            nativeThreads.remove(llllllllllllllllIlIllIIIlIllIlll);
            Pointer llllllllllllllllIlIllIIIlIlllIlI = nativeThreadTerminationFlag.get();
            Native.setDetachState(true, 0L);
        } else if (!nativeThreads.containsKey(llllllllllllllllIlIllIIIlIllIlll)) {
            Pointer llllllllllllllllIlIllIIIlIlllIIl = nativeThreadTerminationFlag.get();
            nativeThreads.put(llllllllllllllllIlIllIIIlIllIlll, llllllllllllllllIlIllIIIlIlllIIl);
            Native.setDetachState(false, llllllllllllllllIlIllIIIlIlllIIl.peer);
        }
    }

    public static TypeMapper getTypeMapper(Class<?> llllllllllllllllIlIllIllllIlllII) {
        Map<String, Object> llllllllllllllllIlIllIllllIlllIl = Native.getLibraryOptions(llllllllllllllllIlIllIllllIlllII);
        return (TypeMapper)llllllllllllllllIlIllIllllIlllIl.get("type-mapper");
    }

    public static /* bridge */ native /* synthetic */ long ffi_prep_cif(int var0, int var1, long var2, long var4);

    public static <T> T loadLibrary(String llllllllllllllllIlIlllIIIIllIlIl, Class<T> llllllllllllllllIlIlllIIIIllIllI) {
        return Native.loadLibrary(llllllllllllllllIlIlllIIIIllIlIl, llllllllllllllllIlIlllIIIIllIllI, Collections.emptyMap());
    }

    @Deprecated
    public static void setPreserveLastError(boolean llllllllllllllllIlIlllIIlIIIlIlI) {
    }

    static /* bridge */ native /* synthetic */ void read(Pointer var0, long var1, long var3, float[] var5, int var6, int var7);

    private static /* bridge */ native /* synthetic */ void unregister(Class<?> var0, long[] var1);

    private static /* bridge */ native /* synthetic */ int sizeof(int var0);

    public static boolean isSupportedNativeType(Class<?> llllllllllllllllIlIllIlIllllllII) {
        if (Structure.class.isAssignableFrom(llllllllllllllllIlIllIlIllllllII)) {
            return true;
        }
        try {
            return Native.getNativeSize(llllllllllllllllIlIllIlIllllllII) != 0;
        }
        catch (IllegalArgumentException llllllllllllllllIlIllIlIllllllIl) {
            return false;
        }
    }

    static /* bridge */ native /* synthetic */ void write(Pointer var0, long var1, long var3, long[] var5, int var6, int var7);

    public static char[] toCharArray(String llllllllllllllllIlIllIlllIlIllIl) {
        char[] llllllllllllllllIlIllIlllIlIllII = llllllllllllllllIlIllIlllIlIllIl.toCharArray();
        char[] llllllllllllllllIlIllIlllIlIlIll = new char[llllllllllllllllIlIllIlllIlIllII.length + 1];
        System.arraycopy(llllllllllllllllIlIllIlllIlIllII, 0, llllllllllllllllIlIllIlllIlIlIll, 0, llllllllllllllllIlIllIlllIlIllII.length);
        return llllllllllllllllIlIllIlllIlIlIll;
    }

    static /* bridge */ native /* synthetic */ float getFloat(Pointer var0, long var1, long var3);

    private static /* bridge */ native /* synthetic */ long registerMethod(Class<?> var0, String var1, String var2, int[] var3, long[] var4, long[] var5, int var6, long var7, long var9, Method var11, long var12, int var14, boolean var15, ToNativeConverter[] var16, FromNativeConverter var17, String var18);

    static /* bridge */ native /* synthetic */ void read(Pointer var0, long var1, long var3, short[] var5, int var6, int var7);

    public static void main(String[] llllllllllllllllIlIllIIIllllIllI) {
        String llllllllllllllllIlIllIIIllllIIII;
        String llllllllllllllllIlIllIIIllllIIIl;
        String llllllllllllllllIlIllIIIllllIlIl = "Java Native Access (JNA)";
        String llllllllllllllllIlIllIIIllllIlII = "4.4.0";
        String llllllllllllllllIlIllIIIllllIIll = "4.4.0 (package information missing)";
        Package llllllllllllllllIlIllIIIllllIIlI = Native.class.getPackage();
        String string = llllllllllllllllIlIllIIIllllIIIl = llllllllllllllllIlIllIIIllllIIlI != null ? llllllllllllllllIlIllIIIllllIIlI.getSpecificationTitle() : "Java Native Access (JNA)";
        if (llllllllllllllllIlIllIIIllllIIIl == null) {
            llllllllllllllllIlIllIIIllllIIIl = "Java Native Access (JNA)";
        }
        String string2 = llllllllllllllllIlIllIIIllllIIII = llllllllllllllllIlIllIIIllllIIlI != null ? llllllllllllllllIlIllIIIllllIIlI.getSpecificationVersion() : "4.4.0";
        if (llllllllllllllllIlIllIIIllllIIII == null) {
            llllllllllllllllIlIllIIIllllIIII = "4.4.0";
        }
        llllllllllllllllIlIllIIIllllIIIl = String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIIllllIIIl).append(" API Version ").append(llllllllllllllllIlIllIIIllllIIII));
        System.out.println(llllllllllllllllIlIllIIIllllIIIl);
        String string3 = llllllllllllllllIlIllIIIllllIIII = llllllllllllllllIlIllIIIllllIIlI != null ? llllllllllllllllIlIllIIIllllIIlI.getImplementationVersion() : "4.4.0 (package information missing)";
        if (llllllllllllllllIlIllIIIllllIIII == null) {
            llllllllllllllllIlIllIIIllllIIII = "4.4.0 (package information missing)";
        }
        System.out.println(String.valueOf(new StringBuilder().append("Version: ").append(llllllllllllllllIlIllIIIllllIIII)));
        System.out.println(String.valueOf(new StringBuilder().append(" Native: ").append(Native.getNativeVersion()).append(" (").append(Native.getAPIChecksum()).append(")")));
        System.out.println(String.valueOf(new StringBuilder().append(" Prefix: ").append(Platform.RESOURCE_PREFIX)));
    }

    static /* bridge */ native /* synthetic */ char getChar(Pointer var0, long var1, long var3);

    static /* bridge */ native /* synthetic */ void invokeVoid(Function var0, long var1, int var3, Object[] var4);

    public static /* bridge */ native /* synthetic */ void ffi_call(long var0, long var2, long var4, long var6);

    public static String toString(char[] llllllllllllllllIlIlllIIIllIIIIl) {
        int llllllllllllllllIlIlllIIIllIIIlI = llllllllllllllllIlIlllIIIllIIIIl.length;
        for (int llllllllllllllllIlIlllIIIllIIlII = 0; llllllllllllllllIlIlllIIIllIIlII < llllllllllllllllIlIlllIIIllIIIlI; ++llllllllllllllllIlIlllIIIllIIlII) {
            if (llllllllllllllllIlIlllIIIllIIIIl[llllllllllllllllIlIlllIIIllIIlII] != '\u0000') continue;
            llllllllllllllllIlIlllIIIllIIIlI = llllllllllllllllIlIlllIIIllIIlII;
            break;
        }
        if (llllllllllllllllIlIlllIIIllIIIlI == 0) {
            return "";
        }
        return new String(llllllllllllllllIlIlllIIIllIIIIl, 0, llllllllllllllllIlIlllIIIllIIIlI);
    }

    static long open(String llllllllllllllllIlIllIIIllIllIII) {
        return Native.open(llllllllllllllllIlIllIIIllIllIII, -1);
    }

    static /* bridge */ native /* synthetic */ float invokeFloat(Function var0, long var1, int var3, Object[] var4);

    public static void setCallbackThreadInitializer(Callback llllllllllllllllIlIllIlIllIllIlI, CallbackThreadInitializer llllllllllllllllIlIllIlIllIlIlll) {
        CallbackReference.setCallbackThreadInitializer(llllllllllllllllIlIllIlIllIllIlI, llllllllllllllllIlIllIlIllIlIlll);
    }

    private static NativeMapped fromNative(Method llllllllllllllllIlIllIIlIIIlIlII, Object llllllllllllllllIlIllIIlIIIlIIII) {
        Class<?> llllllllllllllllIlIllIIlIIIlIIlI = llllllllllllllllIlIllIIlIIIlIlII.getReturnType();
        return (NativeMapped)NativeMappedConverter.getInstance(llllllllllllllllIlIllIIlIIIlIIlI).fromNative(llllllllllllllllIlIllIIlIIIlIIII, new MethodResultContext(llllllllllllllllIlIllIIlIIIlIIlI, null, null, llllllllllllllllIlIllIIlIIIlIlII));
    }

    static /* bridge */ native /* synthetic */ void read(Pointer var0, long var1, long var3, byte[] var5, int var6, int var7);

    static synchronized /* bridge */ native /* synthetic */ void freeNativeCallback(long var0);

    static /* bridge */ native /* synthetic */ void setMemory(Pointer var0, long var1, long var3, long var5, byte var7);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static boolean registered(Class<?> llllllllllllllllIlIllIlIllIIIIII) {
        Map<Class<?>, long[]> llllllllllllllllIlIllIlIlIlllllI = registeredClasses;
        synchronized (llllllllllllllllIlIllIlIlIlllllI) {
            return registeredClasses.containsKey(llllllllllllllllIlIllIlIllIIIIII);
        }
    }

    static byte[] getBytes(String llllllllllllllllIlIllIllllIIIIlI, String llllllllllllllllIlIllIllllIIIIIl) {
        if (llllllllllllllllIlIllIllllIIIIIl != null) {
            try {
                return llllllllllllllllIlIllIllllIIIIlI.getBytes(llllllllllllllllIlIllIllllIIIIIl);
            }
            catch (UnsupportedEncodingException llllllllllllllllIlIllIllllIIIlIl) {
                System.err.println(String.valueOf(new StringBuilder().append("JNA Warning: Encoding '").append(llllllllllllllllIlIllIllllIIIIIl).append("' is unsupported")));
            }
        }
        System.err.println(String.valueOf(new StringBuilder().append("JNA Warning: Encoding with fallback ").append(System.getProperty("file.encoding"))));
        return llllllllllllllllIlIllIllllIIIIlI.getBytes();
    }

    public static synchronized /* bridge */ native /* synthetic */ void setProtected(boolean var0);

    public static int getStructureAlignment(Class<?> llllllllllllllllIlIllIllllIIllll) {
        Integer llllllllllllllllIlIllIllllIIlllI = (Integer)Native.getLibraryOptions(llllllllllllllllIlIllIllllIIllll).get("structure-alignment");
        return llllllllllllllllIlIllIllllIIlllI == null ? 0 : llllllllllllllllIlIllIllllIIlllI;
    }

    static /* bridge */ native /* synthetic */ byte getByte(Pointer var0, long var1, long var3);

    private static Object fromNative(FromNativeConverter llllllllllllllllIlIllIIIllllllll, Object llllllllllllllllIlIllIIIlllllllI, Method llllllllllllllllIlIllIIlIIIIIIII) {
        return llllllllllllllllIlIllIIIllllllll.fromNative(llllllllllllllllIlIllIIIlllllllI, new MethodResultContext(llllllllllllllllIlIllIIlIIIIIIII.getReturnType(), null, null, llllllllllllllllIlIllIIlIIIIIIII));
    }

    private static Object lookupField(Class<?> llllllllllllllllIlIllIlllllIIlll, String llllllllllllllllIlIllIlllllIIllI, Class<?> llllllllllllllllIlIllIlllllIIIlI) {
        try {
            Field llllllllllllllllIlIllIlllllIlIlI = llllllllllllllllIlIllIlllllIIlll.getField(llllllllllllllllIlIllIlllllIIllI);
            llllllllllllllllIlIllIlllllIlIlI.setAccessible(true);
            return llllllllllllllllIlIllIlllllIlIlI.get(null);
        }
        catch (NoSuchFieldException llllllllllllllllIlIllIlllllIlIIl) {
            return null;
        }
        catch (Exception llllllllllllllllIlIllIlllllIlIII) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIlllllIIllI).append(" must be a public field of type ").append(llllllllllllllllIlIllIlllllIIIlI.getName()).append(" (").append(llllllllllllllllIlIllIlllllIlIII).append("): ").append(llllllllllllllllIlIllIlllllIIlll)));
        }
    }

    private static void loadNativeDispatchLibrary() {
        if (!Boolean.getBoolean("jna.nounpack")) {
            try {
                Native.removeTemporaryFiles();
            }
            catch (IOException llllllllllllllllIlIllIlllIIllllI) {
                System.err.println(String.valueOf(new StringBuilder().append("JNA Warning: IOException removing temporary files: ").append(llllllllllllllllIlIllIlllIIllllI.getMessage())));
            }
        }
        String llllllllllllllllIlIllIlllIIlIlII = System.getProperty("jna.boot.library.name", "jnidispatch");
        String llllllllllllllllIlIllIlllIIlIIll = System.getProperty("jna.boot.library.path");
        if (llllllllllllllllIlIllIlllIIlIIll != null) {
            StringTokenizer llllllllllllllllIlIllIlllIIlIlIl = new StringTokenizer(llllllllllllllllIlIllIlllIIlIIll, File.pathSeparator);
            while (llllllllllllllllIlIllIlllIIlIlIl.hasMoreTokens()) {
                String llllllllllllllllIlIllIlllIIllIIl;
                String llllllllllllllllIlIllIlllIIllIlI;
                String llllllllllllllllIlIllIlllIIllIII = llllllllllllllllIlIllIlllIIlIlIl.nextToken();
                File llllllllllllllllIlIllIlllIIlIlll = new File(new File(llllllllllllllllIlIllIlllIIllIII), System.mapLibraryName(llllllllllllllllIlIllIlllIIlIlII).replace(".dylib", ".jnilib"));
                String llllllllllllllllIlIllIlllIIlIllI = llllllllllllllllIlIllIlllIIlIlll.getAbsolutePath();
                if (DEBUG_JNA_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Looking in ").append(llllllllllllllllIlIllIlllIIlIllI)));
                }
                if (llllllllllllllllIlIllIlllIIlIlll.exists()) {
                    try {
                        if (DEBUG_JNA_LOAD) {
                            System.out.println(String.valueOf(new StringBuilder().append("Trying ").append(llllllllllllllllIlIllIlllIIlIllI)));
                        }
                        System.setProperty("jnidispatch.path", llllllllllllllllIlIllIlllIIlIllI);
                        System.load(llllllllllllllllIlIllIlllIIlIllI);
                        jnidispatchPath = llllllllllllllllIlIllIlllIIlIllI;
                        if (DEBUG_JNA_LOAD) {
                            System.out.println(String.valueOf(new StringBuilder().append("Found jnidispatch at ").append(llllllllllllllllIlIllIlllIIlIllI)));
                        }
                        return;
                    }
                    catch (UnsatisfiedLinkError llllllllllllllllIlIllIlllIIIllII) {
                        // empty catch block
                    }
                }
                if (!Platform.isMac()) continue;
                if (llllllllllllllllIlIllIlllIIlIllI.endsWith("dylib")) {
                    String llllllllllllllllIlIllIlllIIlllIl = "dylib";
                    String llllllllllllllllIlIllIlllIIlllII = "jnilib";
                } else {
                    llllllllllllllllIlIllIlllIIllIlI = "jnilib";
                    llllllllllllllllIlIllIlllIIllIIl = "dylib";
                }
                llllllllllllllllIlIllIlllIIlIllI = String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIlllIIlIllI.substring(0, llllllllllllllllIlIllIlllIIlIllI.lastIndexOf(llllllllllllllllIlIllIlllIIllIlI))).append(llllllllllllllllIlIllIlllIIllIIl));
                if (DEBUG_JNA_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Looking in ").append(llllllllllllllllIlIllIlllIIlIllI)));
                }
                if (!new File(llllllllllllllllIlIllIlllIIlIllI).exists()) continue;
                try {
                    if (DEBUG_JNA_LOAD) {
                        System.out.println(String.valueOf(new StringBuilder().append("Trying ").append(llllllllllllllllIlIllIlllIIlIllI)));
                    }
                    System.setProperty("jnidispatch.path", llllllllllllllllIlIllIlllIIlIllI);
                    System.load(llllllllllllllllIlIllIlllIIlIllI);
                    jnidispatchPath = llllllllllllllllIlIllIlllIIlIllI;
                    if (DEBUG_JNA_LOAD) {
                        System.out.println(String.valueOf(new StringBuilder().append("Found jnidispatch at ").append(llllllllllllllllIlIllIlllIIlIllI)));
                    }
                    return;
                }
                catch (UnsatisfiedLinkError llllllllllllllllIlIllIlllIIllIll) {
                    System.err.println(String.valueOf(new StringBuilder().append("File found at ").append(llllllllllllllllIlIllIlllIIlIllI).append(" but not loadable: ").append(llllllllllllllllIlIllIlllIIllIll.getMessage())));
                }
            }
        }
        if (!Boolean.getBoolean("jna.nosys")) {
            try {
                if (DEBUG_JNA_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Trying (via loadLibrary) ").append(llllllllllllllllIlIllIlllIIlIlII)));
                }
                System.loadLibrary(llllllllllllllllIlIllIlllIIlIlII);
                if (DEBUG_JNA_LOAD) {
                    System.out.println("Found jnidispatch on system path");
                }
                return;
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                // empty catch block
            }
        }
        if (Boolean.getBoolean("jna.noclasspath")) {
            throw new UnsatisfiedLinkError("Unable to locate JNA native support library");
        }
        Native.loadNativeDispatchLibraryFromClasspath();
    }

    static Pointer getTerminationFlag(Thread llllllllllllllllIlIllIIIlIllIIIl) {
        return nativeThreads.get(llllllllllllllllIlIllIIIlIllIIIl);
    }

    private static void dispose() {
        CallbackReference.disposeAll();
        Memory.disposeAll();
        NativeLibrary.disposeAll();
        Native.unregisterAll();
        jnidispatchPath = null;
        System.setProperty("jna.loaded", "false");
    }

    static /* bridge */ native /* synthetic */ void write(Pointer var0, long var1, long var3, short[] var5, int var6, int var7);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Map<String, Object> getLibraryOptions(Class<?> llllllllllllllllIlIllIllllllIlII) {
        Map<Class<?>, Reference<?>> llllllllllllllllIlIllIllllllIIlI = libraries;
        synchronized (llllllllllllllllIlIllIllllllIIlI) {
            Map<String, Object> llllllllllllllllIlIllIlllllllIll = typeOptions.get(llllllllllllllllIlIllIllllllIlII);
            if (llllllllllllllllIlIllIlllllllIll != null) {
                return llllllllllllllllIlIllIlllllllIll;
            }
        }
        Class<?> llllllllllllllllIlIllIllllllIlIl = Native.findEnclosingLibraryClass(llllllllllllllllIlIllIllllllIlII);
        if (llllllllllllllllIlIllIllllllIlIl != null) {
            Native.loadLibraryInstance(llllllllllllllllIlIllIllllllIlIl);
        } else {
            llllllllllllllllIlIllIllllllIlIl = llllllllllllllllIlIllIllllllIlII;
        }
        Map<Class<?>, Reference<?>> llllllllllllllllIlIllIllllllIIIl = libraries;
        synchronized (llllllllllllllllIlIllIllllllIIIl) {
            Map<String, Object> llllllllllllllllIlIllIllllllIllI = typeOptions.get(llllllllllllllllIlIllIllllllIlIl);
            if (llllllllllllllllIlIllIllllllIllI != null) {
                typeOptions.put(llllllllllllllllIlIllIllllllIlII, llllllllllllllllIlIllIllllllIllI);
                return llllllllllllllllIlIllIllllllIllI;
            }
            try {
                Field llllllllllllllllIlIllIlllllllIlI = llllllllllllllllIlIllIllllllIlIl.getField("OPTIONS");
                llllllllllllllllIlIllIlllllllIlI.setAccessible(true);
                llllllllllllllllIlIllIllllllIllI = (Map<String, Object>)llllllllllllllllIlIllIlllllllIlI.get(null);
                if (llllllllllllllllIlIllIllllllIllI == null) {
                    throw new IllegalStateException("Null options field");
                }
            }
            catch (NoSuchFieldException llllllllllllllllIlIllIlllllllIIl) {
                llllllllllllllllIlIllIllllllIllI = Collections.emptyMap();
            }
            catch (Exception llllllllllllllllIlIllIlllllllIII) {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("OPTIONS must be a public field of type java.util.Map (").append(llllllllllllllllIlIllIlllllllIII).append("): ").append(llllllllllllllllIlIllIllllllIlIl)));
            }
            llllllllllllllllIlIllIllllllIllI = new HashMap<String, Object>(llllllllllllllllIlIllIllllllIllI);
            if (!llllllllllllllllIlIllIllllllIllI.containsKey("type-mapper")) {
                llllllllllllllllIlIllIllllllIllI.put("type-mapper", Native.lookupField(llllllllllllllllIlIllIllllllIlIl, "TYPE_MAPPER", TypeMapper.class));
            }
            if (!llllllllllllllllIlIllIllllllIllI.containsKey("structure-alignment")) {
                llllllllllllllllIlIllIllllllIllI.put("structure-alignment", Native.lookupField(llllllllllllllllIlIllIllllllIlIl, "STRUCTURE_ALIGNMENT", Integer.class));
            }
            if (!llllllllllllllllIlIllIllllllIllI.containsKey("string-encoding")) {
                llllllllllllllllIlIllIllllllIllI.put("string-encoding", Native.lookupField(llllllllllllllllIlIllIllllllIlIl, "STRING_ENCODING", String.class));
            }
            llllllllllllllllIlIllIllllllIllI = Native.cacheOptions(llllllllllllllllIlIllIllllllIlIl, llllllllllllllllIlIllIllllllIllI, null);
            if (llllllllllllllllIlIllIllllllIlII != llllllllllllllllIlIllIllllllIlIl) {
                typeOptions.put(llllllllllllllllIlIllIllllllIlII, llllllllllllllllIlIllIllllllIllI);
            }
            return llllllllllllllllIlIllIllllllIllI;
        }
    }

    static /* bridge */ native /* synthetic */ void setLong(Pointer var0, long var1, long var3, long var5);

    public static void setCallbackExceptionHandler(Callback.UncaughtExceptionHandler llllllllllllllllIlIllIlIllllIlll) {
        callbackExceptionHandler = llllllllllllllllIlIllIlIllllIlll == null ? DEFAULT_HANDLER : llllllllllllllllIlIllIlIllllIlll;
    }

    static synchronized /* bridge */ native /* synthetic */ long createNativeCallback(Callback var0, Method var1, Class<?>[] var2, Class<?> var3, int var4, int var5, String var6);

    public static File extractFromResourcePath(String llllllllllllllllIlIllIllIlIlllll, ClassLoader llllllllllllllllIlIllIllIlIllllI) throws IOException {
        URL llllllllllllllllIlIllIllIllIIIIl;
        String llllllllllllllllIlIllIllIllIIIlI;
        boolean llllllllllllllllIlIllIllIllIIlII;
        boolean bl = llllllllllllllllIlIllIllIllIIlII = DEBUG_LOAD || DEBUG_JNA_LOAD && llllllllllllllllIlIllIllIlIlllll.indexOf("jnidispatch") != -1;
        if (llllllllllllllllIlIllIllIlIllllI == null && (llllllllllllllllIlIllIllIlIllllI = Thread.currentThread().getContextClassLoader()) == null) {
            llllllllllllllllIlIllIllIlIllllI = Native.class.getClassLoader();
        }
        if (llllllllllllllllIlIllIllIllIIlII) {
            System.out.println(String.valueOf(new StringBuilder().append("Looking in classpath from ").append(llllllllllllllllIlIllIllIlIllllI).append(" for ").append(llllllllllllllllIlIllIllIlIlllll)));
        }
        String llllllllllllllllIlIllIllIllIIIll = llllllllllllllllIlIllIllIlIlllll.startsWith("/") ? llllllllllllllllIlIllIllIlIlllll : NativeLibrary.mapSharedLibraryName(llllllllllllllllIlIllIllIlIlllll);
        String string = llllllllllllllllIlIllIllIllIIIlI = llllllllllllllllIlIllIllIlIlllll.startsWith("/") ? llllllllllllllllIlIllIllIlIlllll : String.valueOf(new StringBuilder().append(Platform.RESOURCE_PREFIX).append("/").append(llllllllllllllllIlIllIllIllIIIll));
        if (llllllllllllllllIlIllIllIllIIIlI.startsWith("/")) {
            llllllllllllllllIlIllIllIllIIIlI = llllllllllllllllIlIllIllIllIIIlI.substring(1);
        }
        if ((llllllllllllllllIlIllIllIllIIIIl = llllllllllllllllIlIllIllIlIllllI.getResource(llllllllllllllllIlIllIllIllIIIlI)) == null && llllllllllllllllIlIllIllIllIIIlI.startsWith(Platform.RESOURCE_PREFIX)) {
            llllllllllllllllIlIllIllIllIIIIl = llllllllllllllllIlIllIllIlIllllI.getResource(llllllllllllllllIlIllIllIllIIIll);
        }
        if (llllllllllllllllIlIllIllIllIIIIl == null) {
            String llllllllllllllllIlIllIllIllIlllI = System.getProperty("java.class.path");
            if (llllllllllllllllIlIllIllIlIllllI instanceof URLClassLoader) {
                llllllllllllllllIlIllIllIllIlllI = Arrays.asList(((URLClassLoader)llllllllllllllllIlIllIllIlIllllI).getURLs()).toString();
            }
            throw new IOException(String.valueOf(new StringBuilder().append("Native library (").append(llllllllllllllllIlIllIllIllIIIlI).append(") not found in resource path (").append(llllllllllllllllIlIllIllIllIlllI).append(")")));
        }
        if (llllllllllllllllIlIllIllIllIIlII) {
            System.out.println(String.valueOf(new StringBuilder().append("Found library resource at ").append(llllllllllllllllIlIllIllIllIIIIl)));
        }
        File llllllllllllllllIlIllIllIllIIIII = null;
        if (llllllllllllllllIlIllIllIllIIIIl.getProtocol().toLowerCase().equals("file")) {
            try {
                llllllllllllllllIlIllIllIllIIIII = new File(new URI(llllllllllllllllIlIllIllIllIIIIl.toString()));
            }
            catch (URISyntaxException llllllllllllllllIlIllIllIllIllIl) {
                llllllllllllllllIlIllIllIllIIIII = new File(llllllllllllllllIlIllIllIllIIIIl.getPath());
            }
            if (llllllllllllllllIlIllIllIllIIlII) {
                System.out.println(String.valueOf(new StringBuilder().append("Looking in ").append(llllllllllllllllIlIllIllIllIIIII.getAbsolutePath())));
            }
            if (!llllllllllllllllIlIllIllIllIIIII.exists()) {
                throw new IOException(String.valueOf(new StringBuilder().append("File URL ").append(llllllllllllllllIlIllIllIllIIIIl).append(" could not be properly decoded")));
            }
        } else if (!Boolean.getBoolean("jna.nounpack")) {
            InputStream llllllllllllllllIlIllIllIllIlIII = llllllllllllllllIlIllIllIlIllllI.getResourceAsStream(llllllllllllllllIlIllIllIllIIIlI);
            if (llllllllllllllllIlIllIllIllIlIII == null) {
                throw new IOException(String.valueOf(new StringBuilder().append("Can't obtain InputStream for ").append(llllllllllllllllIlIllIllIllIIIlI)));
            }
            FileOutputStream llllllllllllllllIlIllIllIllIIlll = null;
            try {
                int llllllllllllllllIlIllIllIllIlIll;
                File llllllllllllllllIlIllIllIllIllII = Native.getTempDir();
                llllllllllllllllIlIllIllIllIIIII = File.createTempFile("jna", Platform.isWindows() ? ".dll" : null, llllllllllllllllIlIllIllIllIllII);
                if (!Boolean.getBoolean("jnidispatch.preserve")) {
                    llllllllllllllllIlIllIllIllIIIII.deleteOnExit();
                }
                llllllllllllllllIlIllIllIllIIlll = new FileOutputStream(llllllllllllllllIlIllIllIllIIIII);
                byte[] llllllllllllllllIlIllIllIllIlIlI = new byte[1024];
                while ((llllllllllllllllIlIllIllIllIlIll = llllllllllllllllIlIllIllIllIlIII.read(llllllllllllllllIlIllIllIllIlIlI, 0, llllllllllllllllIlIllIllIllIlIlI.length)) > 0) {
                    llllllllllllllllIlIllIllIllIIlll.write(llllllllllllllllIlIllIllIllIlIlI, 0, llllllllllllllllIlIllIllIllIlIll);
                }
            }
            catch (IOException llllllllllllllllIlIllIllIllIlIIl) {
                throw new IOException(String.valueOf(new StringBuilder().append("Failed to create temporary file for ").append(llllllllllllllllIlIllIllIlIlllll).append(" library: ").append(llllllllllllllllIlIllIllIllIlIIl.getMessage())));
            }
            finally {
                try {
                    llllllllllllllllIlIllIllIllIlIII.close();
                }
                catch (IOException llllllllllllllllIlIllIllIlIlIIlI) {}
                if (llllllllllllllllIlIllIllIllIIlll != null) {
                    try {
                        llllllllllllllllIlIllIllIllIIlll.close();
                    }
                    catch (IOException llllllllllllllllIlIllIllIlIlIIlI) {}
                }
            }
        }
        return llllllllllllllllIlIllIllIllIIIII;
    }

    static /* bridge */ native /* synthetic */ void read(Pointer var0, long var1, long var3, int[] var5, int var6, int var7);

    @Deprecated
    public static float parseVersion(String llllllllllllllllIlIlllIIlIlIlIIl) {
        return Float.parseFloat(llllllllllllllllIlIlllIIlIlIlIIl.substring(0, llllllllllllllllIlIlllIIlIlIlIIl.lastIndexOf(".")));
    }

    static /* bridge */ native /* synthetic */ long invokePointer(Function var0, long var1, int var3, Object[] var4);

    static /* bridge */ native /* synthetic */ int invokeInt(Function var0, long var1, int var3, Object[] var4);

    public static Pointer getComponentPointer(Component llllllllllllllllIlIlllIIIlllllll) throws HeadlessException {
        return new Pointer(AWT.getComponentID(llllllllllllllllIlIlllIIIlllllll));
    }

    public static byte[] toByteArray(String llllllllllllllllIlIllIlllIllIlII, String llllllllllllllllIlIllIlllIllIlll) {
        byte[] llllllllllllllllIlIllIlllIllIllI = Native.getBytes(llllllllllllllllIlIllIlllIllIlII, llllllllllllllllIlIllIlllIllIlll);
        byte[] llllllllllllllllIlIllIlllIllIlIl = new byte[llllllllllllllllIlIllIlllIllIllI.length + 1];
        System.arraycopy(llllllllllllllllIlIllIlllIllIllI, 0, llllllllllllllllIlIllIlllIllIlIl, 0, llllllllllllllllIlIllIlllIllIllI.length);
        return llllllllllllllllIlIllIlllIllIlIl;
    }

    public static /* bridge */ native /* synthetic */ long ffi_prep_closure(long var0, ffi_callback var2);

    public static void register(Class<?> llllllllllllllllIlIllIlIlIIlIllI, String llllllllllllllllIlIllIlIlIIlIlIl) {
        NativeLibrary llllllllllllllllIlIllIlIlIIlIlII = NativeLibrary.getInstance(llllllllllllllllIlIllIlIlIIlIlIl, Collections.singletonMap("classloader", llllllllllllllllIlIllIlIlIIlIllI.getClassLoader()));
        Native.register(llllllllllllllllIlIllIlIlIIlIllI, llllllllllllllllIlIllIlIlIIlIlII);
    }

    static /* bridge */ native /* synthetic */ void setChar(Pointer var0, long var1, long var3, char var5);

    static /* bridge */ native /* synthetic */ long open(String var0, int var1);

    static /* bridge */ native /* synthetic */ Object invokeObject(Function var0, long var1, int var3, Object[] var4);

    @Deprecated
    public static boolean getPreserveLastError() {
        return true;
    }

    static String getString(Pointer llllllllllllllllIlIllIIIllIIIllI, long llllllllllllllllIlIllIIIllIIIIIl, String llllllllllllllllIlIllIIIllIIIlII) {
        byte[] llllllllllllllllIlIllIIIllIIIIll = Native.getStringBytes(llllllllllllllllIlIllIIIllIIIllI, llllllllllllllllIlIllIIIllIIIllI.peer, llllllllllllllllIlIllIIIllIIIIIl);
        if (llllllllllllllllIlIllIIIllIIIlII != null) {
            try {
                return new String(llllllllllllllllIlIllIIIllIIIIll, llllllllllllllllIlIllIIIllIIIlII);
            }
            catch (UnsupportedEncodingException llllllllllllllllIlIllIIIlIlllllI) {
                // empty catch block
            }
        }
        return new String(llllllllllllllllIlIllIIIllIIIIll);
    }

    static /* bridge */ native /* synthetic */ void setInt(Pointer var0, long var1, long var3, int var5);

    public static List<String> toStringList(char[] llllllllllllllllIlIlllIIIlIlllIl) {
        return Native.toStringList(llllllllllllllllIlIlllIIIlIlllIl, 0, llllllllllllllllIlIlllIIIlIlllIl.length);
    }

    public static String toString(byte[] llllllllllllllllIlIlllIIIllIlIll, String llllllllllllllllIlIlllIIIllIlIlI) {
        int llllllllllllllllIlIlllIIIllIllII = llllllllllllllllIlIlllIIIllIlIll.length;
        for (int llllllllllllllllIlIlllIIIlllIIII = 0; llllllllllllllllIlIlllIIIlllIIII < llllllllllllllllIlIlllIIIllIllII; ++llllllllllllllllIlIlllIIIlllIIII) {
            if (llllllllllllllllIlIlllIIIllIlIll[llllllllllllllllIlIlllIIIlllIIII] != 0) continue;
            llllllllllllllllIlIlllIIIllIllII = llllllllllllllllIlIlllIIIlllIIII;
            break;
        }
        if (llllllllllllllllIlIlllIIIllIllII == 0) {
            return "";
        }
        if (llllllllllllllllIlIlllIIIllIlIlI != null) {
            try {
                return new String(llllllllllllllllIlIlllIIIllIlIll, 0, llllllllllllllllIlIlllIIIllIllII, llllllllllllllllIlIlllIIIllIlIlI);
            }
            catch (UnsupportedEncodingException llllllllllllllllIlIlllIIIllIllll) {
                System.err.println(String.valueOf(new StringBuilder().append("JNA Warning: Encoding '").append(llllllllllllllllIlIlllIIIllIlIlI).append("' is unsupported")));
            }
        }
        System.err.println(String.valueOf(new StringBuilder().append("JNA Warning: Decoding with fallback ").append(System.getProperty("file.encoding"))));
        return new String(llllllllllllllllIlIlllIIIllIlIll, 0, llllllllllllllllIlIlllIIIllIllII);
    }

    @Deprecated
    public static /* bridge */ native /* synthetic */ ByteBuffer getDirectByteBuffer(long var0, long var2);

    static /* bridge */ native /* synthetic */ void setShort(Pointer var0, long var1, long var3, short var5);

    public static /* bridge */ native /* synthetic */ void ffi_free_closure(long var0);

    private static /* bridge */ native /* synthetic */ long _getDirectBufferPointer(Buffer var0);

    public static Library synchronizedLibrary(final Library llllllllllllllllIlIllIllIlIIIlll) {
        Class<?> llllllllllllllllIlIllIllIlIIlIll = llllllllllllllllIlIllIllIlIIIlll.getClass();
        if (!Proxy.isProxyClass(llllllllllllllllIlIllIllIlIIlIll)) {
            throw new IllegalArgumentException("Library must be a proxy class");
        }
        InvocationHandler llllllllllllllllIlIllIllIlIIlIlI = Proxy.getInvocationHandler(llllllllllllllllIlIllIllIlIIIlll);
        if (!(llllllllllllllllIlIllIllIlIIlIlI instanceof Library.Handler)) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unrecognized proxy handler: ").append(llllllllllllllllIlIllIllIlIIlIlI)));
        }
        final Library.Handler llllllllllllllllIlIllIllIlIIlIIl = (Library.Handler)llllllllllllllllIlIllIllIlIIlIlI;
        InvocationHandler llllllllllllllllIlIllIllIlIIlIII = new InvocationHandler(){

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            @Override
            public Object invoke(Object lllllllllllllllllIIIIlIlIIlIIIll, Method lllllllllllllllllIIIIlIlIIlIIIlI, Object[] lllllllllllllllllIIIIlIlIIlIIIIl) throws Throwable {
                3 lllllllllllllllllIIIIlIlIIlIIlII;
                NativeLibrary lllllllllllllllllIIIIlIlIIIlllIl = lllllllllllllllllIIIIlIlIIlIIlII.llllllllllllllllIlIllIllIlIIlIIl.getNativeLibrary();
                synchronized (lllllllllllllllllIIIIlIlIIIlllIl) {
                    return lllllllllllllllllIIIIlIlIIlIIlII.llllllllllllllllIlIllIllIlIIlIIl.invoke(lllllllllllllllllIIIIlIlIIlIIlII.llllllllllllllllIlIllIllIlIIIlll, lllllllllllllllllIIIIlIlIIlIIIlI, lllllllllllllllllIIIIlIlIIlIIIIl);
                }
            }
            {
                3 lllllllllllllllllIIIIlIlIIlIllIl;
            }
        };
        return (Library)Proxy.newProxyInstance(llllllllllllllllIlIllIllIlIIlIll.getClassLoader(), llllllllllllllllIlIllIllIlIIlIll.getInterfaces(), llllllllllllllllIlIllIllIlIIlIII);
    }

    public static byte[] toByteArray(String llllllllllllllllIlIllIlllIlllllI) {
        return Native.toByteArray(llllllllllllllllIlIllIlllIlllllI, Native.getDefaultStringEncoding());
    }

    private static NativeMapped fromNative(Class<?> llllllllllllllllIlIllIIlIIIllIIl, Object llllllllllllllllIlIllIIlIIIllIII) {
        return (NativeMapped)NativeMappedConverter.getInstance(llllllllllllllllIlIllIIlIIIllIIl).fromNative(llllllllllllllllIlIllIIlIIIllIII, new FromNativeContext(llllllllllllllllIlIllIIlIIIllIIl));
    }

    static Class<?> findDirectMappedClass(Class<?> llllllllllllllllIlIllIlIlllIIlIl) {
        Method[] llllllllllllllllIlIllIlIlllIIlll;
        for (Method llllllllllllllllIlIllIlIlllIlIlI : llllllllllllllllIlIllIlIlllIIlll = llllllllllllllllIlIllIlIlllIIlIl.getDeclaredMethods()) {
            if ((llllllllllllllllIlIllIlIlllIlIlI.getModifiers() & 0x100) == 0) continue;
            return llllllllllllllllIlIllIlIlllIIlIl;
        }
        int llllllllllllllllIlIllIlIlllIIllI = llllllllllllllllIlIllIlIlllIIlIl.getName().lastIndexOf("$");
        if (llllllllllllllllIlIllIlIlllIIllI != -1) {
            String llllllllllllllllIlIllIlIlllIlIIl = llllllllllllllllIlIllIlIlllIIlIl.getName().substring(0, llllllllllllllllIlIllIlIlllIIllI);
            try {
                return Native.findDirectMappedClass(Class.forName(llllllllllllllllIlIllIlIlllIlIIl, true, llllllllllllllllIlIllIlIlllIIlIl.getClassLoader()));
            }
            catch (ClassNotFoundException llllllllllllllllIlIllIlIlllIIIIl) {
                // empty catch block
            }
        }
        throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Can't determine class with native methods from the current context (").append(llllllllllllllllIlIllIlIlllIIlIl).append(")")));
    }

    static /* bridge */ native /* synthetic */ void write(Pointer var0, long var1, long var3, double[] var5, int var6, int var7);

    static boolean isCompatibleVersion(String llllllllllllllllIlIlllIIlIlIIIII, String llllllllllllllllIlIlllIIlIIlIlll) {
        String[] llllllllllllllllIlIlllIIlIIllllI = llllllllllllllllIlIlllIIlIlIIIII.split("\\.");
        String[] llllllllllllllllIlIlllIIlIIlllIl = llllllllllllllllIlIlllIIlIIlIlll.split("\\.");
        if (llllllllllllllllIlIlllIIlIIllllI.length < 3 || llllllllllllllllIlIlllIIlIIlllIl.length < 3) {
            return false;
        }
        int llllllllllllllllIlIlllIIlIIlllII = Integer.parseInt(llllllllllllllllIlIlllIIlIIllllI[0]);
        int llllllllllllllllIlIlllIIlIIllIll = Integer.parseInt(llllllllllllllllIlIlllIIlIIlllIl[0]);
        int llllllllllllllllIlIlllIIlIIllIlI = Integer.parseInt(llllllllllllllllIlIlllIIlIIllllI[1]);
        int llllllllllllllllIlIlllIIlIIllIIl = Integer.parseInt(llllllllllllllllIlIlllIIlIIlllIl[1]);
        if (llllllllllllllllIlIlllIIlIIlllII != llllllllllllllllIlIlllIIlIIllIll) {
            return false;
        }
        return llllllllllllllllIlIlllIIlIIllIlI <= llllllllllllllllIlIlllIIlIIllIIl;
    }

    public static /* bridge */ native /* synthetic */ long malloc(long var0);

    private static /* bridge */ native /* synthetic */ long _getPointer(long var0);

    static /* bridge */ native /* synthetic */ void setDouble(Pointer var0, long var1, long var3, double var5);

    private static /* bridge */ native /* synthetic */ String getNativeVersion();

    static /* bridge */ native /* synthetic */ void close(long var0);

    static /* bridge */ native /* synthetic */ void read(Pointer var0, long var1, long var3, long[] var5, int var6, int var7);

    static /* bridge */ native /* synthetic */ void setFloat(Pointer var0, long var1, long var3, float var5);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void unregister(Class<?> llllllllllllllllIlIllIlIllIIIlll) {
        Map<Class<?>, long[]> llllllllllllllllIlIllIlIllIIIllI = registeredClasses;
        synchronized (llllllllllllllllIlIllIlIllIIIllI) {
            long[] llllllllllllllllIlIllIlIllIIlIIl = registeredClasses.get(llllllllllllllllIlIllIlIllIIIlll);
            if (llllllllllllllllIlIllIlIllIIlIIl != null) {
                Native.unregister(llllllllllllllllIlIllIlIllIIIlll, llllllllllllllllIlIllIlIllIIlIIl);
                registeredClasses.remove(llllllllllllllllIlIllIlIllIIIlll);
                registeredLibraries.remove(llllllllllllllllIlIllIlIllIIIlll);
            }
        }
    }

    public static <T> T loadLibrary(Class<T> llllllllllllllllIlIlllIIIIlllIll, Map<String, ?> llllllllllllllllIlIlllIIIIllllII) {
        return Native.loadLibrary(null, llllllllllllllllIlIlllIIIIlllIll, llllllllllllllllIlIlllIIIIllllII);
    }

    private static /* bridge */ native /* synthetic */ void invokeStructure(Function var0, long var1, int var3, Object[] var4, long var5, long var7);

    static void removeTemporaryFiles() throws IOException {
        File llllllllllllllllIlIllIllIIIllIIl = Native.getTempDir();
        FilenameFilter llllllllllllllllIlIllIllIIIllIII = new FilenameFilter(){

            @Override
            public boolean accept(File llllllllllllllllIlllllIIlllllIII, String llllllllllllllllIlllllIIllllIlll) {
                return llllllllllllllllIlllllIIllllIlll.endsWith(".x") && llllllllllllllllIlllllIIllllIlll.startsWith("jna");
            }
            {
                5 llllllllllllllllIlllllIIllllllII;
            }
        };
        File[] llllllllllllllllIlIllIllIIIlIlll = llllllllllllllllIlIllIllIIIllIIl.listFiles(llllllllllllllllIlIllIllIIIllIII);
        for (int llllllllllllllllIlIllIllIIIllIlI = 0; llllllllllllllllIlIllIllIIIlIlll != null && llllllllllllllllIlIllIllIIIllIlI < llllllllllllllllIlIllIllIIIlIlll.length; ++llllllllllllllllIlIllIllIIIllIlI) {
            File llllllllllllllllIlIllIllIIIlllIl = llllllllllllllllIlIllIllIIIlIlll[llllllllllllllllIlIllIllIIIllIlI];
            String llllllllllllllllIlIllIllIIIlllII = llllllllllllllllIlIllIllIIIlllIl.getName();
            llllllllllllllllIlIllIllIIIlllII = llllllllllllllllIlIllIllIIIlllII.substring(0, llllllllllllllllIlIllIllIIIlllII.length() - 2);
            File llllllllllllllllIlIllIllIIIllIll = new File(llllllllllllllllIlIllIllIIIlllIl.getParentFile(), llllllllllllllllIlIllIllIIIlllII);
            if (llllllllllllllllIlIllIllIIIllIll.exists() && !llllllllllllllllIlIllIllIIIllIll.delete()) continue;
            llllllllllllllllIlIllIllIIIlllIl.delete();
        }
    }

    static /* bridge */ native /* synthetic */ long indexOf(Pointer var0, long var1, long var3, byte var5);

    public static String toString(byte[] llllllllllllllllIlIlllIIIlllIlIl) {
        return Native.toString(llllllllllllllllIlIlllIIIlllIlIl, Native.getDefaultStringEncoding());
    }

    static /* bridge */ native /* synthetic */ short getShort(Pointer var0, long var1, long var3);

    public static Callback.UncaughtExceptionHandler getCallbackExceptionHandler() {
        return callbackExceptionHandler;
    }

    static {
        CB_HAS_INITIALIZER = 1;
        TYPE_VOIDP = 0;
        CVT_ARRAY_SHORT = 7;
        CVT_STRING = 2;
        CVT_POINTER_TYPE = 22;
        CB_OPTION_DIRECT = 1;
        CB_OPTION_IN_DLL = 2;
        JNA_TMPLIB_PREFIX = "jna";
        CVT_INTEGER_TYPE = 21;
        CVT_NATIVE_MAPPED_WSTRING = 19;
        CVT_DEFAULT = 0;
        CVT_ARRAY_INT = 9;
        CVT_STRUCTURE_BYVAL = 4;
        CVT_TYPE_MAPPER_STRING = 24;
        CVT_WSTRING = 20;
        TYPE_WCHAR_T = 2;
        CVT_CALLBACK = 15;
        TYPE_LONG = 1;
        CVT_UNSUPPORTED = -1;
        CVT_TYPE_MAPPER_WSTRING = 25;
        CVT_STRUCTURE = 3;
        CVT_ARRAY_LONG = 10;
        CVT_BOOLEAN = 14;
        CVT_BUFFER = 5;
        CVT_ARRAY_BOOLEAN = 13;
        CVT_ARRAY_FLOAT = 11;
        CVT_ARRAY_DOUBLE = 12;
        CVT_FLOAT = 16;
        CVT_POINTER = 1;
        CVT_ARRAY_BYTE = 6;
        CVT_NATIVE_MAPPED_STRING = 18;
        CVT_ARRAY_CHAR = 8;
        _OPTION_ENCLOSING_LIBRARY = "enclosing-library";
        TYPE_SIZE_T = 3;
        TYPE_BOOL = 4;
        CVT_NATIVE_MAPPED = 17;
        CVT_TYPE_MAPPER = 23;
        DEFAULT_ENCODING = Charset.defaultCharset().name();
        DEBUG_LOAD = Boolean.getBoolean("jna.debug_load");
        DEBUG_JNA_LOAD = Boolean.getBoolean("jna.debug_load.jna");
        jnidispatchPath = null;
        typeOptions = new WeakHashMap();
        libraries = new WeakHashMap();
        callbackExceptionHandler = DEFAULT_HANDLER = new Callback.UncaughtExceptionHandler(){

            @Override
            public void uncaughtException(Callback lIIlIlIllIllIll, Throwable lIIlIlIllIllIlI) {
                System.err.println(String.valueOf(new StringBuilder().append("JNA: Callback ").append(lIIlIlIllIllIll).append(" threw the following exception:")));
                lIIlIlIllIllIlI.printStackTrace();
            }
            {
                1 lIIlIlIlllIIIIl;
            }
        };
        Native.loadNativeDispatchLibrary();
        if (!Native.isCompatibleVersion("5.1.0", Native.getNativeVersion())) {
            String llllllllllllllllIlIllIIIlIlIllll = System.getProperty("line.separator");
            throw new Error(String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIIlIlIllll).append(llllllllllllllllIlIllIIIlIlIllll).append("There is an incompatible JNA native library installed on this system").append(llllllllllllllllIlIllIIIlIlIllll).append("Expected: ").append("5.1.0").append(llllllllllllllllIlIllIIIlIlIllll).append("Found:    ").append(Native.getNativeVersion()).append(llllllllllllllllIlIllIIIlIlIllll).append(jnidispatchPath != null ? String.valueOf(new StringBuilder().append("(at ").append(jnidispatchPath).append(")")) : System.getProperty("java.library.path")).append(".").append(llllllllllllllllIlIllIIIlIlIllll).append("To resolve this issue you may do one of the following:").append(llllllllllllllllIlIllIIIlIlIllll).append(" - remove or uninstall the offending library").append(llllllllllllllllIlIllIIIlIlIllll).append(" - set the system property jna.nosys=true").append(llllllllllllllllIlIllIIIlIlIllll).append(" - set jna.boot.library.path to include the path to the version of the ").append(llllllllllllllllIlIllIIIlIlIllll).append("   jnidispatch library included with the JNA jar file you are using").append(llllllllllllllllIlIllIIIlIlIllll)));
        }
        POINTER_SIZE = Native.sizeof(0);
        LONG_SIZE = Native.sizeof(1);
        WCHAR_SIZE = Native.sizeof(2);
        SIZE_T_SIZE = Native.sizeof(3);
        BOOL_SIZE = Native.sizeof(4);
        Native.initIDs();
        if (Boolean.getBoolean("jna.protected")) {
            Native.setProtected(true);
        }
        MAX_ALIGNMENT = Platform.isSPARC() || Platform.isWindows() || Platform.isLinux() && (Platform.isARM() || Platform.isPPC()) || Platform.isAIX() || Platform.isAndroid() ? 8 : LONG_SIZE;
        MAX_PADDING = Platform.isMac() && Platform.isPPC() ? 8 : MAX_ALIGNMENT;
        System.setProperty("jna.loaded", "true");
        finalizer = new Object(){
            {
                2 lIIllIIIlIlllII;
            }

            protected void finalize() {
                Native.dispose();
            }
        };
        registeredClasses = new WeakHashMap();
        registeredLibraries = new WeakHashMap();
        nativeThreadTerminationFlag = new ThreadLocal<Memory>(){

            @Override
            protected Memory initialValue() {
                Memory llllllllllllllllIlllIlIlllIIIlll = new Memory(4L);
                llllllllllllllllIlllIlIlllIIIlll.clear();
                return llllllllllllllllIlllIlIlllIIIlll;
            }
            {
                7 llllllllllllllllIlllIlIlllIIlIlI;
            }
        };
        nativeThreads = Collections.synchronizedMap(new WeakHashMap());
    }

    public static int getNativeSize(Class<?> llllllllllllllllIlIllIllIIIIIllI, Object llllllllllllllllIlIllIllIIIIIlll) {
        if (llllllllllllllllIlIllIllIIIIIllI.isArray()) {
            int llllllllllllllllIlIllIllIIIIlIlI = Array.getLength(llllllllllllllllIlIllIllIIIIIlll);
            if (llllllllllllllllIlIllIllIIIIlIlI > 0) {
                Object llllllllllllllllIlIllIllIIIIlIll = Array.get(llllllllllllllllIlIllIllIIIIIlll, 0);
                return llllllllllllllllIlIllIllIIIIlIlI * Native.getNativeSize(llllllllllllllllIlIllIllIIIIIllI.getComponentType(), llllllllllllllllIlIllIllIIIIlIll);
            }
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Arrays of length zero not allowed: ").append(llllllllllllllllIlIllIllIIIIIllI)));
        }
        if (Structure.class.isAssignableFrom(llllllllllllllllIlIllIllIIIIIllI) && !Structure.ByReference.class.isAssignableFrom(llllllllllllllllIlIllIllIIIIIllI)) {
            return Structure.size(llllllllllllllllIlIllIllIIIIIllI, (Structure)llllllllllllllllIlIllIllIIIIIlll);
        }
        try {
            return Native.getNativeSize(llllllllllllllllIlIllIllIIIIIllI);
        }
        catch (IllegalArgumentException llllllllllllllllIlIllIllIIIIlIIl) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("The type \"").append(llllllllllllllllIlIllIllIIIIIllI.getName()).append("\" is not supported: ").append(llllllllllllllllIlIllIllIIIIlIIl.getMessage())));
        }
    }

    static Pointer getPointer(long llllllllllllllllIlIllIIIllIlIlIl) {
        long llllllllllllllllIlIllIIIllIlIlII = Native._getPointer(llllllllllllllllIlIllIIIllIlIlIl);
        return llllllllllllllllIlIllIIIllIlIlII == 0L ? null : new Pointer(llllllllllllllllIlIllIIIllIlIlII);
    }

    private static int getConversion(Class<?> llllllllllllllllIlIllIlIlIIllllI, TypeMapper llllllllllllllllIlIllIlIlIIlllIl) {
        if (llllllllllllllllIlIllIlIlIIllllI == Boolean.class) {
            llllllllllllllllIlIllIlIlIIllllI = Boolean.TYPE;
        } else if (llllllllllllllllIlIllIlIlIIllllI == Byte.class) {
            llllllllllllllllIlIllIlIlIIllllI = Byte.TYPE;
        } else if (llllllllllllllllIlIllIlIlIIllllI == Short.class) {
            llllllllllllllllIlIllIlIlIIllllI = Short.TYPE;
        } else if (llllllllllllllllIlIllIlIlIIllllI == Character.class) {
            llllllllllllllllIlIllIlIlIIllllI = Character.TYPE;
        } else if (llllllllllllllllIlIllIlIlIIllllI == Integer.class) {
            llllllllllllllllIlIllIlIlIIllllI = Integer.TYPE;
        } else if (llllllllllllllllIlIllIlIlIIllllI == Long.class) {
            llllllllllllllllIlIllIlIlIIllllI = Long.TYPE;
        } else if (llllllllllllllllIlIllIlIlIIllllI == Float.class) {
            llllllllllllllllIlIllIlIlIIllllI = Float.TYPE;
        } else if (llllllllllllllllIlIllIlIlIIllllI == Double.class) {
            llllllllllllllllIlIllIlIlIIllllI = Double.TYPE;
        } else if (llllllllllllllllIlIllIlIlIIllllI == Void.class) {
            llllllllllllllllIlIllIlIlIIllllI = Void.TYPE;
        }
        if (llllllllllllllllIlIllIlIlIIlllIl != null) {
            FromNativeConverter llllllllllllllllIlIllIlIlIlIIIll = llllllllllllllllIlIllIlIlIIlllIl.getFromNativeConverter(llllllllllllllllIlIllIlIlIIllllI);
            ToNativeConverter llllllllllllllllIlIllIlIlIlIIIlI = llllllllllllllllIlIllIlIlIIlllIl.getToNativeConverter(llllllllllllllllIlIllIlIlIIllllI);
            if (llllllllllllllllIlIllIlIlIlIIIll != null) {
                Class<?> llllllllllllllllIlIllIlIlIlIIlIl = llllllllllllllllIlIllIlIlIlIIIll.nativeType();
                if (llllllllllllllllIlIllIlIlIlIIlIl == String.class) {
                    return 24;
                }
                if (llllllllllllllllIlIllIlIlIlIIlIl == WString.class) {
                    return 25;
                }
                return 23;
            }
            if (llllllllllllllllIlIllIlIlIlIIIlI != null) {
                Class<?> llllllllllllllllIlIllIlIlIlIIlII = llllllllllllllllIlIllIlIlIlIIIlI.nativeType();
                if (llllllllllllllllIlIllIlIlIlIIlII == String.class) {
                    return 24;
                }
                if (llllllllllllllllIlIllIlIlIlIIlII == WString.class) {
                    return 25;
                }
                return 23;
            }
        }
        if (Pointer.class.isAssignableFrom(llllllllllllllllIlIllIlIlIIllllI)) {
            return 1;
        }
        if (String.class == llllllllllllllllIlIllIlIlIIllllI) {
            return 2;
        }
        if (WString.class.isAssignableFrom(llllllllllllllllIlIllIlIlIIllllI)) {
            return 20;
        }
        if (Platform.HAS_BUFFERS && Buffers.isBuffer(llllllllllllllllIlIllIlIlIIllllI)) {
            return 5;
        }
        if (Structure.class.isAssignableFrom(llllllllllllllllIlIllIlIlIIllllI)) {
            if (Structure.ByValue.class.isAssignableFrom(llllllllllllllllIlIllIlIlIIllllI)) {
                return 4;
            }
            return 3;
        }
        if (llllllllllllllllIlIllIlIlIIllllI.isArray()) {
            switch (llllllllllllllllIlIllIlIlIIllllI.getName().charAt(1)) {
                case 'Z': {
                    return 13;
                }
                case 'B': {
                    return 6;
                }
                case 'S': {
                    return 7;
                }
                case 'C': {
                    return 8;
                }
                case 'I': {
                    return 9;
                }
                case 'J': {
                    return 10;
                }
                case 'F': {
                    return 11;
                }
                case 'D': {
                    return 12;
                }
            }
        }
        if (llllllllllllllllIlIllIlIlIIllllI.isPrimitive()) {
            return llllllllllllllllIlIllIlIlIIllllI == Boolean.TYPE ? 14 : 0;
        }
        if (Callback.class.isAssignableFrom(llllllllllllllllIlIllIlIlIIllllI)) {
            return 15;
        }
        if (IntegerType.class.isAssignableFrom(llllllllllllllllIlIllIlIlIIllllI)) {
            return 21;
        }
        if (PointerType.class.isAssignableFrom(llllllllllllllllIlIllIlIlIIllllI)) {
            return 22;
        }
        if (NativeMapped.class.isAssignableFrom(llllllllllllllllIlIllIlIlIIllllI)) {
            Class<?> llllllllllllllllIlIllIlIlIlIIIIl = NativeMappedConverter.getInstance(llllllllllllllllIlIllIlIlIIllllI).nativeType();
            if (llllllllllllllllIlIllIlIlIlIIIIl == String.class) {
                return 18;
            }
            if (llllllllllllllllIlIllIlIlIlIIIIl == WString.class) {
                return 19;
            }
            return 17;
        }
        return -1;
    }

    public static long getComponentID(Component llllllllllllllllIlIlllIIlIIIIlIl) throws HeadlessException {
        return AWT.getComponentID(llllllllllllllllIlIlllIIlIIIIlIl);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void register(Class<?> llllllllllllllllIlIllIIlIlIllIIl, NativeLibrary llllllllllllllllIlIllIIlIlIlIIIl) {
        Method[] llllllllllllllllIlIllIIlIlIlIlll = llllllllllllllllIlIllIIlIlIllIIl.getDeclaredMethods();
        ArrayList<Method> llllllllllllllllIlIllIIlIlIlIllI = new ArrayList<Method>();
        Map<String, Object> llllllllllllllllIlIllIIlIlIlIlIl = llllllllllllllllIlIllIIlIlIlIIIl.getOptions();
        TypeMapper llllllllllllllllIlIllIIlIlIlIlII = (TypeMapper)llllllllllllllllIlIllIIlIlIlIlIl.get("type-mapper");
        llllllllllllllllIlIllIIlIlIlIlIl = Native.cacheOptions(llllllllllllllllIlIllIIlIlIllIIl, llllllllllllllllIlIllIIlIlIlIlIl, null);
        for (Method llllllllllllllllIlIllIIlIlllIlll : llllllllllllllllIlIllIIlIlIlIlll) {
            if ((llllllllllllllllIlIllIIlIlllIlll.getModifiers() & 0x100) == 0) continue;
            llllllllllllllllIlIllIIlIlIlIllI.add(llllllllllllllllIlIllIIlIlllIlll);
        }
        long[] llllllllllllllllIlIllIIlIlIlIIll = new long[llllllllllllllllIlIllIIlIlIlIllI.size()];
        for (int llllllllllllllllIlIllIIlIlIllIlI = 0; llllllllllllllllIlIllIIlIlIllIlI < llllllllllllllllIlIllIIlIlIlIIll.length; ++llllllllllllllllIlIllIIlIlIllIlI) {
            long llllllllllllllllIlIllIIlIllIIlIl;
            long llllllllllllllllIlIllIIlIllIIllI;
            Method llllllllllllllllIlIllIIlIllIlIIl = (Method)llllllllllllllllIlIllIIlIlIlIllI.get(llllllllllllllllIlIllIIlIlIllIlI);
            String llllllllllllllllIlIllIIlIllIlIII = "(";
            Class<?> llllllllllllllllIlIllIIlIllIIlll = llllllllllllllllIlIllIIlIllIlIIl.getReturnType();
            Class<?>[] llllllllllllllllIlIllIIlIllIIlII = llllllllllllllllIlIllIIlIllIlIIl.getParameterTypes();
            long[] llllllllllllllllIlIllIIlIllIIIll = new long[llllllllllllllllIlIllIIlIllIIlII.length];
            long[] llllllllllllllllIlIllIIlIllIIIlI = new long[llllllllllllllllIlIllIIlIllIIlII.length];
            int[] llllllllllllllllIlIllIIlIllIIIIl = new int[llllllllllllllllIlIllIIlIllIIlII.length];
            ToNativeConverter[] llllllllllllllllIlIllIIlIllIIIII = new ToNativeConverter[llllllllllllllllIlIllIIlIllIIlII.length];
            FromNativeConverter llllllllllllllllIlIllIIlIlIlllll = null;
            int llllllllllllllllIlIllIIlIlIllllI = Native.getConversion(llllllllllllllllIlIllIIlIllIIlll, llllllllllllllllIlIllIIlIlIlIlII);
            boolean llllllllllllllllIlIllIIlIlIlllIl = false;
            switch (llllllllllllllllIlIllIIlIlIllllI) {
                case -1: {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIlIllIIlll).append(" is not a supported return type (in method ").append(llllllllllllllllIlIllIIlIllIlIIl.getName()).append(" in ").append(llllllllllllllllIlIllIIlIlIllIIl).append(")")));
                }
                case 23: 
                case 24: 
                case 25: {
                    llllllllllllllllIlIllIIlIlIlllll = llllllllllllllllIlIllIIlIlIlIlII.getFromNativeConverter(llllllllllllllllIlIllIIlIllIIlll);
                    long llllllllllllllllIlIllIIlIlllIlIl = Structure.FFIType.get(llllllllllllllllIlIllIIlIllIIlll.isPrimitive() ? llllllllllllllllIlIllIIlIllIIlll : Pointer.class).peer;
                    long llllllllllllllllIlIllIIlIlllIllI = Structure.FFIType.get(llllllllllllllllIlIllIIlIlIlllll.nativeType()).peer;
                    break;
                }
                case 17: 
                case 18: 
                case 19: 
                case 21: 
                case 22: {
                    long llllllllllllllllIlIllIIlIlllIIll = Structure.FFIType.get(Pointer.class).peer;
                    long llllllllllllllllIlIllIIlIlllIlII = Structure.FFIType.get(NativeMappedConverter.getInstance(llllllllllllllllIlIllIIlIllIIlll).nativeType()).peer;
                    break;
                }
                case 3: {
                    long llllllllllllllllIlIllIIlIlllIIlI;
                    long llllllllllllllllIlIllIIlIlllIIIl = llllllllllllllllIlIllIIlIlllIIlI = Structure.FFIType.get(Pointer.class).peer;
                    break;
                }
                case 4: {
                    long llllllllllllllllIlIllIIlIllIllll = Structure.FFIType.get(Pointer.class).peer;
                    long llllllllllllllllIlIllIIlIlllIIII = Structure.FFIType.get(llllllllllllllllIlIllIIlIllIIlll).peer;
                    break;
                }
                default: {
                    llllllllllllllllIlIllIIlIllIIlIl = llllllllllllllllIlIllIIlIllIIllI = Structure.FFIType.get(llllllllllllllllIlIllIIlIllIIlll).peer;
                }
            }
            block19: for (int llllllllllllllllIlIllIIlIllIllII = 0; llllllllllllllllIlIllIIlIllIllII < llllllllllllllllIlIllIIlIllIIlII.length; ++llllllllllllllllIlIllIIlIllIllII) {
                int llllllllllllllllIlIllIIlIllIllIl;
                Class<?> llllllllllllllllIlIllIIlIllIlllI = llllllllllllllllIlIllIIlIllIIlII[llllllllllllllllIlIllIIlIllIllII];
                llllllllllllllllIlIllIIlIllIlIII = String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIlIllIlIII).append(Native.getSignature(llllllllllllllllIlIllIIlIllIlllI)));
                llllllllllllllllIlIllIIlIllIIIIl[llllllllllllllllIlIllIIlIllIllII] = llllllllllllllllIlIllIIlIllIllIl = Native.getConversion(llllllllllllllllIlIllIIlIllIlllI, llllllllllllllllIlIllIIlIlIlIlII);
                if (llllllllllllllllIlIllIIlIllIllIl == -1) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIlIllIlllI).append(" is not a supported argument type (in method ").append(llllllllllllllllIlIllIIlIllIlIIl.getName()).append(" in ").append(llllllllllllllllIlIllIIlIlIllIIl).append(")")));
                }
                if (llllllllllllllllIlIllIIlIllIllIl == 17 || llllllllllllllllIlIllIIlIllIllIl == 18 || llllllllllllllllIlIllIIlIllIllIl == 19 || llllllllllllllllIlIllIIlIllIllIl == 21) {
                    llllllllllllllllIlIllIIlIllIlllI = NativeMappedConverter.getInstance(llllllllllllllllIlIllIIlIllIlllI).nativeType();
                } else if (llllllllllllllllIlIllIIlIllIllIl == 23 || llllllllllllllllIlIllIIlIllIllIl == 24 || llllllllllllllllIlIllIIlIllIllIl == 25) {
                    llllllllllllllllIlIllIIlIllIIIII[llllllllllllllllIlIllIIlIllIllII] = llllllllllllllllIlIllIIlIlIlIlII.getToNativeConverter(llllllllllllllllIlIllIIlIllIlllI);
                }
                switch (llllllllllllllllIlIllIIlIllIllIl) {
                    case 4: 
                    case 17: 
                    case 18: 
                    case 19: 
                    case 21: 
                    case 22: {
                        llllllllllllllllIlIllIIlIllIIIll[llllllllllllllllIlIllIIlIllIllII] = Structure.FFIType.get(llllllllllllllllIlIllIIlIllIlllI).peer;
                        llllllllllllllllIlIllIIlIllIIIlI[llllllllllllllllIlIllIIlIllIllII] = Structure.FFIType.get(Pointer.class).peer;
                        continue block19;
                    }
                    case 23: 
                    case 24: 
                    case 25: {
                        llllllllllllllllIlIllIIlIllIIIlI[llllllllllllllllIlIllIIlIllIllII] = Structure.FFIType.get(llllllllllllllllIlIllIIlIllIlllI.isPrimitive() ? llllllllllllllllIlIllIIlIllIlllI : Pointer.class).peer;
                        llllllllllllllllIlIllIIlIllIIIll[llllllllllllllllIlIllIIlIllIllII] = Structure.FFIType.get(llllllllllllllllIlIllIIlIllIIIII[llllllllllllllllIlIllIIlIllIllII].nativeType()).peer;
                        continue block19;
                    }
                    case 0: {
                        llllllllllllllllIlIllIIlIllIIIlI[llllllllllllllllIlIllIIlIllIllII] = llllllllllllllllIlIllIIlIllIIIll[llllllllllllllllIlIllIIlIllIllII] = Structure.FFIType.get(llllllllllllllllIlIllIIlIllIlllI).peer;
                        continue block19;
                    }
                    default: {
                        llllllllllllllllIlIllIIlIllIIIlI[llllllllllllllllIlIllIIlIllIllII] = llllllllllllllllIlIllIIlIllIIIll[llllllllllllllllIlIllIIlIllIllII] = Structure.FFIType.get(Pointer.class).peer;
                    }
                }
            }
            llllllllllllllllIlIllIIlIllIlIII = String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIlIllIlIII).append(")"));
            llllllllllllllllIlIllIIlIllIlIII = String.valueOf(new StringBuilder().append(llllllllllllllllIlIllIIlIllIlIII).append(Native.getSignature(llllllllllllllllIlIllIIlIllIIlll)));
            Class<?>[] llllllllllllllllIlIllIIlIlIlllII = llllllllllllllllIlIllIIlIllIlIIl.getExceptionTypes();
            for (int llllllllllllllllIlIllIIlIllIlIll = 0; llllllllllllllllIlIllIIlIllIlIll < llllllllllllllllIlIllIIlIlIlllII.length; ++llllllllllllllllIlIllIIlIllIlIll) {
                if (!LastErrorException.class.isAssignableFrom(llllllllllllllllIlIllIIlIlIlllII[llllllllllllllllIlIllIIlIllIlIll])) continue;
                llllllllllllllllIlIllIIlIlIlllIl = true;
                break;
            }
            Function llllllllllllllllIlIllIIlIlIllIll = llllllllllllllllIlIllIIlIlIlIIIl.getFunction(llllllllllllllllIlIllIIlIllIlIIl.getName(), llllllllllllllllIlIllIIlIllIlIIl);
            try {
                llllllllllllllllIlIllIIlIlIlIIll[llllllllllllllllIlIllIIlIlIllIlI] = Native.registerMethod(llllllllllllllllIlIllIIlIlIllIIl, llllllllllllllllIlIllIIlIllIlIIl.getName(), llllllllllllllllIlIllIIlIllIlIII, llllllllllllllllIlIllIIlIllIIIIl, llllllllllllllllIlIllIIlIllIIIlI, llllllllllllllllIlIllIIlIllIIIll, llllllllllllllllIlIllIIlIlIllllI, llllllllllllllllIlIllIIlIllIIlIl, llllllllllllllllIlIllIIlIllIIllI, llllllllllllllllIlIllIIlIllIlIIl, llllllllllllllllIlIllIIlIlIllIll.peer, llllllllllllllllIlIllIIlIlIllIll.getCallingConvention(), llllllllllllllllIlIllIIlIlIlllIl, llllllllllllllllIlIllIIlIllIIIII, llllllllllllllllIlIllIIlIlIlllll, llllllllllllllllIlIllIIlIlIllIll.encoding);
                continue;
            }
            catch (NoSuchMethodError llllllllllllllllIlIllIIlIllIlIlI) {
                throw new UnsatisfiedLinkError(String.valueOf(new StringBuilder().append("No method ").append(llllllllllllllllIlIllIIlIllIlIIl.getName()).append(" with signature ").append(llllllllllllllllIlIllIIlIllIlIII).append(" in ").append(llllllllllllllllIlIllIIlIlIllIIl)));
            }
        }
        Map<Class<?>, long[]> map = registeredClasses;
        synchronized (map) {
            registeredClasses.put(llllllllllllllllIlIllIIlIlIllIIl, llllllllllllllllIlIllIIlIlIlIIll);
            registeredLibraries.put(llllllllllllllllIlIllIIlIlIllIIl, llllllllllllllllIlIllIIlIlIlIIIl);
        }
    }

    static /* bridge */ native /* synthetic */ void write(Pointer var0, long var1, long var3, int[] var5, int var6, int var7);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static Class<?> findEnclosingLibraryClass(Class<?> llllllllllllllllIlIlllIIIIIIIllI) {
        Class<?> llllllllllllllllIlIlllIIIIIIlIII;
        Class<?> llllllllllllllllIlIlllIIIIIIIlll;
        if (llllllllllllllllIlIlllIIIIIIIllI == null) {
            return null;
        }
        Map<Class<?>, Reference<?>> llllllllllllllllIlIlllIIIIIIIlIl = libraries;
        synchronized (llllllllllllllllIlIlllIIIIIIIlIl) {
            if (typeOptions.containsKey(llllllllllllllllIlIlllIIIIIIIllI)) {
                Map<String, Object> llllllllllllllllIlIlllIIIIIIlIll = typeOptions.get(llllllllllllllllIlIlllIIIIIIIllI);
                Class llllllllllllllllIlIlllIIIIIIlIlI = (Class)llllllllllllllllIlIlllIIIIIIlIll.get("enclosing-library");
                if (llllllllllllllllIlIlllIIIIIIlIlI != null) {
                    return llllllllllllllllIlIlllIIIIIIlIlI;
                }
                return llllllllllllllllIlIlllIIIIIIIllI;
            }
        }
        if (Library.class.isAssignableFrom(llllllllllllllllIlIlllIIIIIIIllI)) {
            return llllllllllllllllIlIlllIIIIIIIllI;
        }
        if (Callback.class.isAssignableFrom(llllllllllllllllIlIlllIIIIIIIllI)) {
            llllllllllllllllIlIlllIIIIIIIllI = CallbackReference.findCallbackClass(llllllllllllllllIlIlllIIIIIIIllI);
        }
        if ((llllllllllllllllIlIlllIIIIIIIlll = Native.findEnclosingLibraryClass(llllllllllllllllIlIlllIIIIIIlIII = llllllllllllllllIlIlllIIIIIIIllI.getDeclaringClass())) != null) {
            return llllllllllllllllIlIlllIIIIIIIlll;
        }
        return Native.findEnclosingLibraryClass(llllllllllllllllIlIlllIIIIIIIllI.getSuperclass());
    }

    static String getString(Pointer llllllllllllllllIlIllIIIllIIllIl, long llllllllllllllllIlIllIIIllIIlllI) {
        return Native.getString(llllllllllllllllIlIllIIIllIIllIl, llllllllllllllllIlIllIIIllIIlllI, Native.getDefaultStringEncoding());
    }

    public static void register(NativeLibrary llllllllllllllllIlIllIlIllllIIIl) {
        Native.register(Native.findDirectMappedClass(Native.getCallingClass()), llllllllllllllllIlIllIlIllllIIIl);
    }

    static /* bridge */ native /* synthetic */ void read(Pointer var0, long var1, long var3, double[] var5, int var6, int var7);

    static /* bridge */ native /* synthetic */ ByteBuffer getDirectByteBuffer(Pointer var0, long var1, long var3, long var5);

    public static void unregister() {
        Native.unregister(Native.findDirectMappedClass(Native.getCallingClass()));
    }

    static byte[] getBytes(String llllllllllllllllIlIllIllllIIlIlI) {
        return Native.getBytes(llllllllllllllllIlIllIllllIIlIlI, Native.getDefaultStringEncoding());
    }

    static /* bridge */ native /* synthetic */ long getWindowHandle0(Component var0);

    static File getTempDir() throws IOException {
        File llllllllllllllllIlIllIllIIlIlIIl;
        String llllllllllllllllIlIllIllIIlIlIII = System.getProperty("jna.tmpdir");
        if (llllllllllllllllIlIllIllIIlIlIII != null) {
            File llllllllllllllllIlIllIllIIlIlIll = new File(llllllllllllllllIlIllIllIIlIlIII);
            llllllllllllllllIlIllIllIIlIlIll.mkdirs();
        } else {
            File llllllllllllllllIlIllIllIIlIlIlI = new File(System.getProperty("java.io.tmpdir"));
            llllllllllllllllIlIllIllIIlIlIIl = new File(llllllllllllllllIlIllIllIIlIlIlI, String.valueOf(new StringBuilder().append("jna-").append(System.getProperty("user.name").hashCode())));
            llllllllllllllllIlIllIllIIlIlIIl.mkdirs();
            if (!llllllllllllllllIlIllIllIIlIlIIl.exists() || !llllllllllllllllIlIllIllIIlIlIIl.canWrite()) {
                llllllllllllllllIlIllIllIIlIlIIl = llllllllllllllllIlIllIllIIlIlIlI;
            }
        }
        if (!llllllllllllllllIlIllIllIIlIlIIl.exists()) {
            throw new IOException(String.valueOf(new StringBuilder().append("JNA temporary directory '").append(llllllllllllllllIlIllIllIIlIlIIl).append("' does not exist")));
        }
        if (!llllllllllllllllIlIllIllIIlIlIIl.canWrite()) {
            throw new IOException(String.valueOf(new StringBuilder().append("JNA temporary directory '").append(llllllllllllllllIlIllIllIIlIlIIl).append("' is not writable")));
        }
        return llllllllllllllllIlIllIllIIlIlIIl;
    }

    static boolean deleteLibrary(File llllllllllllllllIlIlllIIlIIIllll) {
        if (llllllllllllllllIlIlllIIlIIIllll.delete()) {
            return true;
        }
        Native.markTemporaryFile(llllllllllllllllIlIlllIIlIIIllll);
        return false;
    }

    public static /* bridge */ native /* synthetic */ int getLastError();

    public static <T> T loadLibrary(String llllllllllllllllIlIlllIIIIlIIlll, Class<T> llllllllllllllllIlIlllIIIIlIIllI, Map<String, ?> llllllllllllllllIlIlllIIIIlIIlIl) {
        if (!Library.class.isAssignableFrom(llllllllllllllllIlIlllIIIIlIIllI)) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Interface (").append(llllllllllllllllIlIlllIIIIlIIllI.getSimpleName()).append(") of library=").append(llllllllllllllllIlIlllIIIIlIIlll).append(" does not extend ").append(Library.class.getSimpleName())));
        }
        Library.Handler llllllllllllllllIlIlllIIIIlIlIlI = new Library.Handler(llllllllllllllllIlIlllIIIIlIIlll, llllllllllllllllIlIlllIIIIlIIllI, llllllllllllllllIlIlllIIIIlIIlIl);
        ClassLoader llllllllllllllllIlIlllIIIIlIlIIl = llllllllllllllllIlIlllIIIIlIIllI.getClassLoader();
        Object llllllllllllllllIlIlllIIIIlIlIII = Proxy.newProxyInstance(llllllllllllllllIlIlllIIIIlIlIIl, new Class[]{llllllllllllllllIlIlllIIIIlIIllI}, (InvocationHandler)llllllllllllllllIlIlllIIIIlIlIlI);
        Native.cacheOptions(llllllllllllllllIlIlllIIIIlIIllI, llllllllllllllllIlIlllIIIIlIIlIl, llllllllllllllllIlIlllIIIIlIlIII);
        return llllllllllllllllIlIlllIIIIlIIllI.cast(llllllllllllllllIlIlllIIIIlIlIII);
    }

    static /* bridge */ native /* synthetic */ void read(Pointer var0, long var1, long var3, char[] var5, int var6, int var7);

    static /* bridge */ native /* synthetic */ int getInt(Pointer var0, long var1, long var3);

    static /* bridge */ native /* synthetic */ double invokeDouble(Function var0, long var1, int var3, Object[] var4);

    static /* bridge */ native /* synthetic */ void write(Pointer var0, long var1, long var3, float[] var5, int var6, int var7);

    public static /* bridge */ native /* synthetic */ void setLastError(int var0);

    private static Object toNative(ToNativeConverter llllllllllllllllIlIllIIlIIIIIlll, Object llllllllllllllllIlIllIIlIIIIlIII) {
        return llllllllllllllllIlIllIIlIIIIIlll.toNative(llllllllllllllllIlIllIIlIIIIlIII, new ToNativeContext());
    }

    static /* bridge */ native /* synthetic */ long getLong(Pointer var0, long var1, long var3);

    public static <T> T loadLibrary(Class<T> llllllllllllllllIlIlllIIIlIIIIIl) {
        return Native.loadLibrary(null, llllllllllllllllIlIlllIIIlIIIIIl);
    }

    static /* bridge */ native /* synthetic */ String getWideString(Pointer var0, long var1, long var3);

    private Native() {
        Native llllllllllllllllIlIlllIIlIIIllII;
    }

    static /* bridge */ native /* synthetic */ void write(Pointer var0, long var1, long var3, char[] var5, int var6, int var7);

    static String replace(String llllllllllllllllIlIllIlIlIlIllll, String llllllllllllllllIlIllIlIlIllIIlI, String llllllllllllllllIlIllIlIlIllIIIl) {
        StringBuilder llllllllllllllllIlIllIlIlIllIIII = new StringBuilder();
        while (true) {
            int llllllllllllllllIlIllIlIlIllIlII;
            if ((llllllllllllllllIlIllIlIlIllIlII = llllllllllllllllIlIllIlIlIllIIIl.indexOf(llllllllllllllllIlIllIlIlIlIllll)) == -1) break;
            llllllllllllllllIlIllIlIlIllIIII.append(llllllllllllllllIlIllIlIlIllIIIl.substring(0, llllllllllllllllIlIllIlIlIllIlII));
            llllllllllllllllIlIllIlIlIllIIII.append(llllllllllllllllIlIllIlIlIllIIlI);
            llllllllllllllllIlIllIlIlIllIIIl = llllllllllllllllIlIllIlIlIllIIIl.substring(llllllllllllllllIlIllIlIlIllIlII + llllllllllllllllIlIllIlIlIlIllll.length());
        }
        llllllllllllllllIlIllIlIlIllIIII.append(llllllllllllllllIlIllIlIlIllIIIl);
        return String.valueOf(llllllllllllllllIlIllIlIlIllIIII);
    }

    static /* bridge */ native /* synthetic */ void write(Pointer var0, long var1, long var3, byte[] var5, int var6, int var7);

    public static File extractFromResourcePath(String llllllllllllllllIlIllIllIllllllI) throws IOException {
        return Native.extractFromResourcePath(llllllllllllllllIlIllIllIllllllI, null);
    }

    static /* bridge */ native /* synthetic */ void setPointer(Pointer var0, long var1, long var3, long var5);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static Map<String, Object> cacheOptions(Class<?> llllllllllllllllIlIllIIlIIlIllII, Map<String, ?> llllllllllllllllIlIllIIlIIlIIlll, Object llllllllllllllllIlIllIIlIIlIIllI) {
        HashMap<String, Object> llllllllllllllllIlIllIIlIIlIlIIl = new HashMap<String, Object>(llllllllllllllllIlIllIIlIIlIIlll);
        llllllllllllllllIlIllIIlIIlIlIIl.put("enclosing-library", llllllllllllllllIlIllIIlIIlIllII);
        Map<Class<?>, Reference<?>> llllllllllllllllIlIllIIlIIlIIlII = libraries;
        synchronized (llllllllllllllllIlIllIIlIIlIIlII) {
            typeOptions.put(llllllllllllllllIlIllIIlIIlIllII, llllllllllllllllIlIllIIlIIlIlIIl);
            if (llllllllllllllllIlIllIIlIIlIIllI != null) {
                libraries.put(llllllllllllllllIlIllIIlIIlIllII, new WeakReference<Object>(llllllllllllllllIlIllIIlIIlIIllI));
            }
            if (!llllllllllllllllIlIllIIlIIlIllII.isInterface() && Library.class.isAssignableFrom(llllllllllllllllIlIllIIlIIlIllII)) {
                Class<?>[] llllllllllllllllIlIllIIlIIlIllIl;
                for (Class<?> llllllllllllllllIlIllIIlIIlIlllI : llllllllllllllllIlIllIIlIIlIllIl = llllllllllllllllIlIllIIlIIlIllII.getInterfaces()) {
                    if (!Library.class.isAssignableFrom(llllllllllllllllIlIllIIlIIlIlllI)) continue;
                    Native.cacheOptions(llllllllllllllllIlIllIIlIIlIlllI, llllllllllllllllIlIllIIlIIlIlIIl, llllllllllllllllIlIllIIlIIlIIllI);
                    break;
                }
            }
        }
        return llllllllllllllllIlIllIIlIIlIlIIl;
    }

    private static class AWT {
        static long getWindowID(Window llllllllllllllllIlIlIllIIllllllI) throws HeadlessException {
            return AWT.getComponentID(llllllllllllllllIlIlIllIIllllllI);
        }

        static long getComponentID(Object llllllllllllllllIlIlIllIIllllIII) throws HeadlessException {
            if (GraphicsEnvironment.isHeadless()) {
                throw new HeadlessException("No native windows when headless");
            }
            Component llllllllllllllllIlIlIllIIllllIIl = (Component)llllllllllllllllIlIlIllIIllllIII;
            if (llllllllllllllllIlIlIllIIllllIIl.isLightweight()) {
                throw new IllegalArgumentException("Component must be heavyweight");
            }
            if (!llllllllllllllllIlIlIllIIllllIIl.isDisplayable()) {
                throw new IllegalStateException("Component must be displayable");
            }
            if (Platform.isX11() && System.getProperty("java.version").startsWith("1.4") && !llllllllllllllllIlIlIllIIllllIIl.isVisible()) {
                throw new IllegalStateException("Component must be visible");
            }
            return Native.getWindowHandle0(llllllllllllllllIlIlIllIIllllIIl);
        }

        private AWT() {
            AWT llllllllllllllllIlIlIllIlIIIIIIl;
        }
    }

    private static class Buffers {
        private Buffers() {
            Buffers lllllllllllllllllllIIIIIIlllIlIl;
        }

        static boolean isBuffer(Class<?> lllllllllllllllllllIIIIIIlllIIlI) {
            return Buffer.class.isAssignableFrom(lllllllllllllllllllIIIIIIlllIIlI);
        }
    }

    public static interface ffi_callback {
        public void invoke(long var1, long var3, long var5);
    }
}

