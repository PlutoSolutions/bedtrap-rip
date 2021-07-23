/*
 * Decompiled with CFR 0.151.
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

public final class Native
implements Version {
    private static final int CVT_BOOLEAN;
    private static final int TYPE_SIZE_T;
    private static final ThreadLocal<Memory> nativeThreadTerminationFlag;
    private static final int CVT_CALLBACK;
    static final int CB_OPTION_DIRECT;
    private static final int CVT_POINTER;
    private static final Object finalizer;
    private static final int TYPE_VOIDP;
    static final int MAX_ALIGNMENT;
    private static final int CVT_DEFAULT;
    private static final int CVT_UNSUPPORTED;
    private static final int TYPE_BOOL;
    private static final int CVT_NATIVE_MAPPED_WSTRING;
    private static final int CVT_NATIVE_MAPPED_STRING;
    private static final int CVT_FLOAT;
    private static final int CVT_TYPE_MAPPER;
    public static boolean DEBUG_LOAD;
    private static final Map<Class<?>, Reference<?>> libraries;
    private static final int TYPE_WCHAR_T;
    private static final int CVT_BUFFER;
    public static final int BOOL_SIZE;
    private static final int CVT_WSTRING;
    private static final int CVT_INTEGER_TYPE;
    static final int MAX_PADDING;
    private static final String _OPTION_ENCLOSING_LIBRARY;
    private static final int CVT_TYPE_MAPPER_WSTRING;
    private static final Callback.UncaughtExceptionHandler DEFAULT_HANDLER;
    private static final int CVT_ARRAY_INT;
    private static final int CVT_STRUCTURE_BYVAL;
    private static final Map<Class<?>, Map<String, Object>> typeOptions;
    public static final String DEFAULT_ENCODING;
    private static final Map<Thread, Pointer> nativeThreads;
    private static final int CVT_ARRAY_CHAR;
    public static final int POINTER_SIZE;
    static final int CB_HAS_INITIALIZER;
    private static final int CVT_ARRAY_BOOLEAN;
    private static final int CVT_NATIVE_MAPPED;
    public static boolean DEBUG_JNA_LOAD;
    private static final int CVT_ARRAY_BYTE;
    public static final int SIZE_T_SIZE;
    static String jnidispatchPath;
    static final String JNA_TMPLIB_PREFIX;
    private static Callback.UncaughtExceptionHandler callbackExceptionHandler;
    public static final int LONG_SIZE;
    private static Map<Class<?>, long[]> registeredClasses;
    private static final int CVT_STRING;
    private static final int CVT_ARRAY_LONG;
    private static Map<Class<?>, NativeLibrary> registeredLibraries;
    private static final int CVT_STRUCTURE;
    private static final int CVT_ARRAY_FLOAT;
    public static final int WCHAR_SIZE;
    private static final int CVT_ARRAY_SHORT;
    private static final int CVT_POINTER_TYPE;
    private static final int TYPE_LONG;
    private static final int CVT_TYPE_MAPPER_STRING;
    static final int CB_OPTION_IN_DLL;
    private static final int CVT_ARRAY_DOUBLE;

    static void markTemporaryFile(File file) {
        try {
            File file2 = new File(file.getParentFile(), String.valueOf(new StringBuilder().append(file.getName()).append(".x")));
            file2.createNewFile();
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    static native byte[] getStringBytes(Pointer var0, long var1, long var3);

    public static long getWindowID(Window window) throws HeadlessException {
        return AWT.getWindowID(window);
    }

    static String getSignature(Class<?> clazz) {
        if (clazz.isArray()) {
            return String.valueOf(new StringBuilder().append("[").append(Native.getSignature(clazz.getComponentType())));
        }
        if (clazz.isPrimitive()) {
            if (clazz == Void.TYPE) {
                return "V";
            }
            if (clazz == Boolean.TYPE) {
                return "Z";
            }
            if (clazz == Byte.TYPE) {
                return "B";
            }
            if (clazz == Short.TYPE) {
                return "S";
            }
            if (clazz == Character.TYPE) {
                return "C";
            }
            if (clazz == Integer.TYPE) {
                return "I";
            }
            if (clazz == Long.TYPE) {
                return "J";
            }
            if (clazz == Float.TYPE) {
                return "F";
            }
            if (clazz == Double.TYPE) {
                return "D";
            }
        }
        return String.valueOf(new StringBuilder().append("L").append(Native.replace(".", "/", clazz.getName())).append(";"));
    }

    private static native void setDetachState(boolean var0, long var1);

    public static String getDefaultStringEncoding() {
        return System.getProperty("jna.encoding", DEFAULT_ENCODING);
    }

    static native int initialize_ffi_type(long var0);

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void unregisterAll() {
        Map<Class<?>, long[]> map = registeredClasses;
        synchronized (map) {
            Iterator<Map.Entry<Class<?>, long[]>> iterator = registeredClasses.entrySet().iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    registeredClasses.clear();
                    return;
                }
                Map.Entry<Class<?>, long[]> entry = iterator.next();
                Native.unregister(entry.getKey(), entry.getValue());
            }
        }
    }

    public static native void free(long var0);

    private static Class<?> nativeType(Class<?> clazz) {
        return NativeMappedConverter.getInstance(clazz).nativeType();
    }

    public static Pointer getWindowPointer(Window window) throws HeadlessException {
        return new Pointer(AWT.getWindowID(window));
    }

    static Class<?> getCallingClass() {
        Class<?>[] classArray = new SecurityManager(){

            public Class<?>[] getClassContext() {
                return super.getClassContext();
            }
        }.getClassContext();
        if (classArray == null) {
            throw new IllegalStateException("The SecurityManager implementation on this platform is broken; you must explicitly provide the class to register");
        }
        if (classArray.length < 4) {
            throw new IllegalStateException("This method must be called from the static initializer of a class");
        }
        return classArray[3];
    }

    static native long invokeLong(Function var0, long var1, int var3, Object[] var4);

    public static Pointer getDirectBufferPointer(Buffer buffer) {
        long l = Native._getDirectBufferPointer(buffer);
        return l == 0L ? null : new Pointer(l);
    }

    static native void setWideString(Pointer var0, long var1, long var3, String var5);

    private static native void initIDs();

    public static String getWebStartLibraryPath(String string) {
        if (System.getProperty("javawebstart.version") == null) {
            return null;
        }
        try {
            ClassLoader classLoader = Native.class.getClassLoader();
            Method method = AccessController.doPrivileged(new PrivilegedAction<Method>(){

                @Override
                public Object run() {
                    return this.run();
                }

                @Override
                public Method run() {
                    try {
                        Method method = ClassLoader.class.getDeclaredMethod("findLibrary", String.class);
                        method.setAccessible(true);
                        return method;
                    }
                    catch (Exception exception) {
                        return null;
                    }
                }
            });
            String string2 = (String)method.invoke(classLoader, string);
            if (string2 != null) {
                return new File(string2).getParent();
            }
            return null;
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static synchronized native boolean isProtected();

    static native double getDouble(Pointer var0, long var1, long var3);

    private static void loadLibraryInstance(Class<?> clazz) {
        Map<Class<?>, Reference<?>> map = libraries;
        synchronized (map) {
            block5: {
                if (clazz == null || libraries.containsKey(clazz)) break block5;
                try {
                    Field[] fieldArray = clazz.getFields();
                    for (int i = 0; i < fieldArray.length; ++i) {
                        Field field = fieldArray[i];
                        if (field.getType() != clazz || !Modifier.isStatic(field.getModifiers())) continue;
                        libraries.put(clazz, new WeakReference<Object>(field.get(null)));
                        break;
                    }
                }
                catch (Exception | Throwable throwable) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Could not access instance of ").append(clazz).append(" (").append(throwable).append(")")));
                }
            }
        }
    }

    public static List<String> toStringList(char[] cArray, int n, int n2) {
        ArrayList<String> arrayList = new ArrayList<String>();
        int n3 = n;
        int n4 = n + n2;
        for (int i = n; i < n4; ++i) {
            if (cArray[i] != '\u0000') continue;
            if (n3 == i) {
                return arrayList;
            }
            String string = new String(cArray, n3, i - n3);
            arrayList.add(string);
            n3 = i + 1;
            if (null == null) continue;
            return null;
        }
        if (n3 < n4) {
            String string = new String(cArray, n3, n4 - n3);
            arrayList.add(string);
        }
        return arrayList;
    }

    static native void setByte(Pointer var0, long var1, long var3, byte var5);

    static Structure invokeStructure(Function function, long l, int n, Object[] objectArray, Structure structure) {
        Native.invokeStructure(function, l, n, objectArray, structure.getPointer().peer, structure.getTypeInfo().peer);
        return structure;
    }

    static native long findSymbol(long var0, String var2);

    static boolean isUnpacked(File file) {
        return file.getName().startsWith("jna");
    }

    private static native String getAPIChecksum();

    public static void register(String string) {
        Native.register(Native.findDirectMappedClass(Native.getCallingClass()), string);
    }

    public static String getStringEncoding(Class<?> clazz) {
        Map<String, Object> map = Native.getLibraryOptions(clazz);
        String string = (String)map.get("string-encoding");
        return string != null ? string : Native.getDefaultStringEncoding();
    }

    private static void loadNativeDispatchLibraryFromClasspath() {
        try {
            String string = String.valueOf(new StringBuilder().append("/com/sun/jna/").append(Platform.RESOURCE_PREFIX).append("/").append(System.mapLibraryName("jnidispatch").replace(".dylib", ".jnilib")));
            File file = Native.extractFromResourcePath(string, Native.class.getClassLoader());
            if (file == null && file == null) {
                throw new UnsatisfiedLinkError("Could not find JNA native support");
            }
            if (DEBUG_JNA_LOAD) {
                System.out.println(String.valueOf(new StringBuilder().append("Trying ").append(file.getAbsolutePath())));
            }
            System.setProperty("jnidispatch.path", file.getAbsolutePath());
            System.load(file.getAbsolutePath());
            jnidispatchPath = file.getAbsolutePath();
            if (DEBUG_JNA_LOAD) {
                System.out.println(String.valueOf(new StringBuilder().append("Found jnidispatch at ").append(jnidispatchPath)));
            }
            if (Native.isUnpacked(file) && !Boolean.getBoolean("jnidispatch.preserve")) {
                Native.deleteLibrary(file);
            }
        }
        catch (IOException iOException) {
            throw new UnsatisfiedLinkError(iOException.getMessage());
        }
    }

    public static int getNativeSize(Class<?> clazz) {
        if (NativeMapped.class.isAssignableFrom(clazz)) {
            clazz = NativeMappedConverter.getInstance(clazz).nativeType();
        }
        if (clazz == Boolean.TYPE || clazz == Boolean.class) {
            return 4;
        }
        if (clazz == Byte.TYPE || clazz == Byte.class) {
            return 1;
        }
        if (clazz == Short.TYPE || clazz == Short.class) {
            return 2;
        }
        if (clazz == Character.TYPE || clazz == Character.class) {
            return WCHAR_SIZE;
        }
        if (clazz == Integer.TYPE || clazz == Integer.class) {
            return 4;
        }
        if (clazz == Long.TYPE || clazz == Long.class) {
            return 8;
        }
        if (clazz == Float.TYPE || clazz == Float.class) {
            return 4;
        }
        if (clazz == Double.TYPE || clazz == Double.class) {
            return 8;
        }
        if (Structure.class.isAssignableFrom(clazz)) {
            if (Structure.ByValue.class.isAssignableFrom(clazz)) {
                return Structure.size(clazz);
            }
            return POINTER_SIZE;
        }
        if (Pointer.class.isAssignableFrom(clazz) || Platform.HAS_BUFFERS && Buffers.isBuffer(clazz) || Callback.class.isAssignableFrom(clazz) || String.class == clazz || WString.class == clazz) {
            return POINTER_SIZE;
        }
        throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Native size for type \"").append(clazz.getName()).append("\" is unknown")));
    }

    public static void detach(boolean bl) {
        Thread thread = Thread.currentThread();
        if (bl) {
            nativeThreads.remove(thread);
            Pointer pointer = nativeThreadTerminationFlag.get();
            Native.setDetachState(true, 0L);
        } else if (!nativeThreads.containsKey(thread)) {
            Pointer pointer = nativeThreadTerminationFlag.get();
            nativeThreads.put(thread, pointer);
            Native.setDetachState(false, pointer.peer);
        }
    }

    public static TypeMapper getTypeMapper(Class<?> clazz) {
        Map<String, Object> map = Native.getLibraryOptions(clazz);
        return (TypeMapper)map.get("type-mapper");
    }

    public static native long ffi_prep_cif(int var0, int var1, long var2, long var4);

    public static <T> T loadLibrary(String string, Class<T> clazz) {
        return Native.loadLibrary(string, clazz, Collections.emptyMap());
    }

    @Deprecated
    public static void setPreserveLastError(boolean bl) {
    }

    static native void read(Pointer var0, long var1, long var3, float[] var5, int var6, int var7);

    private static native void unregister(Class<?> var0, long[] var1);

    private static native int sizeof(int var0);

    public static boolean isSupportedNativeType(Class<?> clazz) {
        if (Structure.class.isAssignableFrom(clazz)) {
            return true;
        }
        try {
            return Native.getNativeSize(clazz) != 0;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            return false;
        }
    }

    static native void write(Pointer var0, long var1, long var3, long[] var5, int var6, int var7);

    public static char[] toCharArray(String string) {
        char[] cArray = string.toCharArray();
        char[] cArray2 = new char[cArray.length + 1];
        System.arraycopy(cArray, 0, cArray2, 0, cArray.length);
        return cArray2;
    }

    static native float getFloat(Pointer var0, long var1, long var3);

    private static native long registerMethod(Class<?> var0, String var1, String var2, int[] var3, long[] var4, long[] var5, int var6, long var7, long var9, Method var11, long var12, int var14, boolean var15, ToNativeConverter[] var16, FromNativeConverter var17, String var18);

    static native void read(Pointer var0, long var1, long var3, short[] var5, int var6, int var7);

    public static void main(String[] stringArray) {
        String string;
        String string2;
        String string3 = "Java Native Access (JNA)";
        String string4 = "4.4.0";
        String string5 = "4.4.0 (package information missing)";
        Package package_ = Native.class.getPackage();
        String string6 = string2 = package_ != null ? package_.getSpecificationTitle() : "Java Native Access (JNA)";
        if (string2 == null) {
            string2 = "Java Native Access (JNA)";
        }
        String string7 = string = package_ != null ? package_.getSpecificationVersion() : "4.4.0";
        if (string == null) {
            string = "4.4.0";
        }
        string2 = String.valueOf(new StringBuilder().append(string2).append(" API Version ").append(string));
        System.out.println(string2);
        String string8 = string = package_ != null ? package_.getImplementationVersion() : "4.4.0 (package information missing)";
        if (string == null) {
            string = "4.4.0 (package information missing)";
        }
        System.out.println(String.valueOf(new StringBuilder().append("Version: ").append(string)));
        System.out.println(String.valueOf(new StringBuilder().append(" Native: ").append(Native.getNativeVersion()).append(" (").append(Native.getAPIChecksum()).append(")")));
        System.out.println(String.valueOf(new StringBuilder().append(" Prefix: ").append(Platform.RESOURCE_PREFIX)));
    }

    static native char getChar(Pointer var0, long var1, long var3);

    static native void invokeVoid(Function var0, long var1, int var3, Object[] var4);

    public static native void ffi_call(long var0, long var2, long var4, long var6);

    public static String toString(char[] cArray) {
        int n = cArray.length;
        for (int i = 0; i < n; ++i) {
            if (cArray[i] != '\u0000') continue;
            n = i;
            break;
        }
        if (n == 0) {
            return "";
        }
        return new String(cArray, 0, n);
    }

    static long open(String string) {
        return Native.open(string, -1);
    }

    static native float invokeFloat(Function var0, long var1, int var3, Object[] var4);

    public static void setCallbackThreadInitializer(Callback callback, CallbackThreadInitializer callbackThreadInitializer) {
        CallbackReference.setCallbackThreadInitializer(callback, callbackThreadInitializer);
    }

    private static NativeMapped fromNative(Method method, Object object) {
        Class<?> clazz = method.getReturnType();
        return (NativeMapped)NativeMappedConverter.getInstance(clazz).fromNative(object, new MethodResultContext(clazz, null, null, method));
    }

    static native void read(Pointer var0, long var1, long var3, byte[] var5, int var6, int var7);

    static synchronized native void freeNativeCallback(long var0);

    static native void setMemory(Pointer var0, long var1, long var3, long var5, byte var7);

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static boolean registered(Class<?> clazz) {
        Map<Class<?>, long[]> map = registeredClasses;
        // MONITORENTER : map
        // MONITOREXIT : map
        return registeredClasses.containsKey(clazz);
    }

    static byte[] getBytes(String string, String string2) {
        if (string2 != null) {
            try {
                return string.getBytes(string2);
            }
            catch (UnsupportedEncodingException unsupportedEncodingException) {
                System.err.println(String.valueOf(new StringBuilder().append("JNA Warning: Encoding '").append(string2).append("' is unsupported")));
            }
        }
        System.err.println(String.valueOf(new StringBuilder().append("JNA Warning: Encoding with fallback ").append(System.getProperty("file.encoding"))));
        return string.getBytes();
    }

    public static synchronized native void setProtected(boolean var0);

    public static int getStructureAlignment(Class<?> clazz) {
        Integer n = (Integer)Native.getLibraryOptions(clazz).get("structure-alignment");
        return n == null ? 0 : n;
    }

    static native byte getByte(Pointer var0, long var1, long var3);

    private static Object fromNative(FromNativeConverter fromNativeConverter, Object object, Method method) {
        return fromNativeConverter.fromNative(object, new MethodResultContext(method.getReturnType(), null, null, method));
    }

    private static Object lookupField(Class<?> clazz, String string, Class<?> clazz2) {
        try {
            Field field = clazz.getField(string);
            field.setAccessible(true);
            return field.get(null);
        }
        catch (Exception | NoSuchFieldException exception) {
            return null;
        }
    }

    private static void loadNativeDispatchLibrary() {
        if (!Boolean.getBoolean("jna.nounpack")) {
            try {
                Native.removeTemporaryFiles();
            }
            catch (IOException iOException) {
                System.err.println(String.valueOf(new StringBuilder().append("JNA Warning: IOException removing temporary files: ").append(iOException.getMessage())));
            }
        }
        String string = System.getProperty("jna.boot.library.name", "jnidispatch");
        String string2 = System.getProperty("jna.boot.library.path");
        if (string2 != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(string2, File.pathSeparator);
            while (stringTokenizer.hasMoreTokens()) {
                String string3;
                String string4;
                String string5 = stringTokenizer.nextToken();
                File file = new File(new File(string5), System.mapLibraryName(string).replace(".dylib", ".jnilib"));
                String string6 = file.getAbsolutePath();
                if (DEBUG_JNA_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Looking in ").append(string6)));
                }
                if (file.exists()) {
                    try {
                        if (DEBUG_JNA_LOAD) {
                            System.out.println(String.valueOf(new StringBuilder().append("Trying ").append(string6)));
                        }
                        System.setProperty("jnidispatch.path", string6);
                        System.load(string6);
                        jnidispatchPath = string6;
                        if (DEBUG_JNA_LOAD) {
                            System.out.println(String.valueOf(new StringBuilder().append("Found jnidispatch at ").append(string6)));
                        }
                        return;
                    }
                    catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                        // empty catch block
                    }
                }
                if (!Platform.isMac()) continue;
                if (string6.endsWith("dylib")) {
                    string4 = "dylib";
                    string3 = "jnilib";
                } else {
                    string4 = "jnilib";
                    string3 = "dylib";
                }
                string6 = String.valueOf(new StringBuilder().append(string6.substring(0, string6.lastIndexOf(string4))).append(string3));
                if (DEBUG_JNA_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Looking in ").append(string6)));
                }
                if (!new File(string6).exists()) continue;
                try {
                    if (DEBUG_JNA_LOAD) {
                        System.out.println(String.valueOf(new StringBuilder().append("Trying ").append(string6)));
                    }
                    System.setProperty("jnidispatch.path", string6);
                    System.load(string6);
                    jnidispatchPath = string6;
                    if (DEBUG_JNA_LOAD) {
                        System.out.println(String.valueOf(new StringBuilder().append("Found jnidispatch at ").append(string6)));
                    }
                    return;
                }
                catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                    System.err.println(String.valueOf(new StringBuilder().append("File found at ").append(string6).append(" but not loadable: ").append(unsatisfiedLinkError.getMessage())));
                }
            }
        }
        if (!Boolean.getBoolean("jna.nosys")) {
            try {
                if (DEBUG_JNA_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Trying (via loadLibrary) ").append(string)));
                }
                System.loadLibrary(string);
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

    static Pointer getTerminationFlag(Thread thread) {
        return nativeThreads.get(thread);
    }

    private static void dispose() {
        CallbackReference.disposeAll();
        Memory.disposeAll();
        NativeLibrary.disposeAll();
        Native.unregisterAll();
        jnidispatchPath = null;
        System.setProperty("jna.loaded", "false");
    }

    static native void write(Pointer var0, long var1, long var3, short[] var5, int var6, int var7);

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Map<String, Object> getLibraryOptions(Class<?> clazz) {
        Map<String, Object> map;
        Class<?> clazz2 = libraries;
        synchronized (clazz2) {
            map = typeOptions.get(clazz);
            if (map != null) {
                return map;
            }
        }
        clazz2 = Native.findEnclosingLibraryClass(clazz);
        if (clazz2 != null) {
            Native.loadLibraryInstance(clazz2);
        } else {
            clazz2 = clazz;
        }
        Map<Class<?>, Reference<?>> map2 = libraries;
        synchronized (map2) {
            block16: {
                Class<?> clazz3;
                map = typeOptions.get(clazz2);
                if (map != null) {
                    typeOptions.put(clazz, map);
                    return map;
                }
                try {
                    clazz3 = clazz2;
                }
                catch (NoSuchFieldException noSuchFieldException) {
                    map = Collections.emptyMap();
                    break block16;
                }
                {
                    Field field = clazz3.getField("OPTIONS");
                    field.setAccessible(true);
                    map = (Map<String, Object>)field.get(null);
                    if (map != null) break block16;
                    throw new IllegalStateException("Null options field");
                }
            }
            map = new HashMap<String, Object>(map);
            if (!map.containsKey("type-mapper")) {
                map.put("type-mapper", Native.lookupField(clazz2, "TYPE_MAPPER", TypeMapper.class));
            }
            if (!map.containsKey("structure-alignment")) {
                map.put("structure-alignment", Native.lookupField(clazz2, "STRUCTURE_ALIGNMENT", Integer.class));
            }
            if (!map.containsKey("string-encoding")) {
                map.put("string-encoding", Native.lookupField(clazz2, "STRING_ENCODING", String.class));
            }
            map = Native.cacheOptions(clazz2, map, null);
            if (clazz != clazz2) {
                typeOptions.put(clazz, map);
            }
            return map;
        }
    }

    static native void setLong(Pointer var0, long var1, long var3, long var5);

    public static void setCallbackExceptionHandler(Callback.UncaughtExceptionHandler uncaughtExceptionHandler) {
        callbackExceptionHandler = uncaughtExceptionHandler == null ? DEFAULT_HANDLER : uncaughtExceptionHandler;
    }

    static synchronized native long createNativeCallback(Callback var0, Method var1, Class<?>[] var2, Class<?> var3, int var4, int var5, String var6);

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static File extractFromResourcePath(String var0, ClassLoader var1_1) throws IOException {
        v0 = var2_2 = Native.DEBUG_LOAD != false || Native.DEBUG_JNA_LOAD != false && var0.indexOf("jnidispatch") != -1;
        if (var1_1 == null && (var1_1 = Thread.currentThread().getContextClassLoader()) == null) {
            var1_1 = Native.class.getClassLoader();
        }
        if (var2_2) {
            System.out.println(String.valueOf(new StringBuilder().append("Looking in classpath from ").append(var1_1).append(" for ").append(var0)));
        }
        var3_3 = var0.startsWith("/") != false ? var0 : NativeLibrary.mapSharedLibraryName(var0);
        v1 = var4_4 = var0.startsWith("/") != false ? var0 : String.valueOf(new StringBuilder().append(Platform.RESOURCE_PREFIX).append("/").append(var3_3));
        if (var4_4.startsWith("/")) {
            var4_4 = var4_4.substring(1);
        }
        if ((var5_5 = var1_1.getResource(var4_4)) == null && var4_4.startsWith(Platform.RESOURCE_PREFIX)) {
            var5_5 = var1_1.getResource(var3_3);
        }
        if (var5_5 == null) {
            var6_6 = System.getProperty("java.class.path");
            if (!(var1_1 instanceof URLClassLoader)) throw new IOException(String.valueOf(new StringBuilder().append("Native library (").append(var4_4).append(") not found in resource path (").append(var6_6).append(")")));
            var6_6 = Arrays.asList(((URLClassLoader)var1_1).getURLs()).toString();
            throw new IOException(String.valueOf(new StringBuilder().append("Native library (").append(var4_4).append(") not found in resource path (").append(var6_6).append(")")));
        }
        if (var2_2) {
            System.out.println(String.valueOf(new StringBuilder().append("Found library resource at ").append(var5_5)));
        }
        var6_7 = null;
        if (var5_5.getProtocol().toLowerCase().equals("file")) {
            try {
                var6_7 = new File(new URI(var5_5.toString()));
            }
            catch (URISyntaxException var7_8) {
                var6_7 = new File(var5_5.getPath());
            }
            if (var2_2) {
                System.out.println(String.valueOf(new StringBuilder().append("Looking in ").append(var6_7.getAbsolutePath())));
            }
            if (var6_7.exists()) return var6_7;
            throw new IOException(String.valueOf(new StringBuilder().append("File URL ").append(var5_5).append(" could not be properly decoded")));
        }
        if (Boolean.getBoolean("jna.nounpack")) return var6_7;
        var7_9 = var1_1.getResourceAsStream(var4_4);
        if (var7_9 == null) {
            throw new IOException(String.valueOf(new StringBuilder().append("Can't obtain InputStream for ").append(var4_4)));
        }
        var8_10 = null;
        try {
            var9_11 = Native.getTempDir();
            var6_7 = File.createTempFile("jna", Platform.isWindows() != false ? ".dll" : null, var9_11);
            if (!Boolean.getBoolean("jnidispatch.preserve")) {
                var6_7.deleteOnExit();
            }
            var8_10 = new FileOutputStream(var6_7);
            var11_15 = new byte[1024];
            while ((var10_16 = var7_9.read(var11_15, 0, var11_15.length)) > 0) {
                var8_10.write(var11_15, 0, var10_16);
            }
            return var6_7;
        }
        catch (IOException var9_14) {}
        throw new IOException(String.valueOf(new StringBuilder().append("Failed to create temporary file for ").append(var0).append(" library: ").append(var9_14.getMessage())));
        finally {
            try {
                var7_9.close();
            }
            catch (IOException var13_18) {}
            if (var8_10 != null) {
                ** try [egrp 6[TRYBLOCK] [6 : 682->685)] { 
lbl56:
                // 1 sources

                var8_10.close();
            }
        }
    }

    static native void read(Pointer var0, long var1, long var3, int[] var5, int var6, int var7);

    @Deprecated
    public static float parseVersion(String string) {
        return Float.parseFloat(string.substring(0, string.lastIndexOf(".")));
    }

    static native long invokePointer(Function var0, long var1, int var3, Object[] var4);

    static native int invokeInt(Function var0, long var1, int var3, Object[] var4);

    public static Pointer getComponentPointer(Component component) throws HeadlessException {
        return new Pointer(AWT.getComponentID(component));
    }

    public static byte[] toByteArray(String string, String string2) {
        byte[] byArray = Native.getBytes(string, string2);
        byte[] byArray2 = new byte[byArray.length + 1];
        System.arraycopy(byArray, 0, byArray2, 0, byArray.length);
        return byArray2;
    }

    public static native long ffi_prep_closure(long var0, ffi_callback var2);

    public static void register(Class<?> clazz, String string) {
        NativeLibrary nativeLibrary = NativeLibrary.getInstance(string, Collections.singletonMap("classloader", clazz.getClassLoader()));
        Native.register(clazz, nativeLibrary);
    }

    static native void setChar(Pointer var0, long var1, long var3, char var5);

    static native long open(String var0, int var1);

    static native Object invokeObject(Function var0, long var1, int var3, Object[] var4);

    @Deprecated
    public static boolean getPreserveLastError() {
        return true;
    }

    static String getString(Pointer pointer, long l, String string) {
        byte[] byArray = Native.getStringBytes(pointer, pointer.peer, l);
        if (string != null) {
            try {
                return new String(byArray, string);
            }
            catch (UnsupportedEncodingException unsupportedEncodingException) {
                // empty catch block
            }
        }
        return new String(byArray);
    }

    static native void setInt(Pointer var0, long var1, long var3, int var5);

    public static List<String> toStringList(char[] cArray) {
        return Native.toStringList(cArray, 0, cArray.length);
    }

    public static String toString(byte[] byArray, String string) {
        int n = byArray.length;
        for (int i = 0; i < n; ++i) {
            if (byArray[i] != 0) continue;
            n = i;
            break;
        }
        if (n == 0) {
            return "";
        }
        if (string != null) {
            try {
                return new String(byArray, 0, n, string);
            }
            catch (UnsupportedEncodingException unsupportedEncodingException) {
                System.err.println(String.valueOf(new StringBuilder().append("JNA Warning: Encoding '").append(string).append("' is unsupported")));
            }
        }
        System.err.println(String.valueOf(new StringBuilder().append("JNA Warning: Decoding with fallback ").append(System.getProperty("file.encoding"))));
        return new String(byArray, 0, n);
    }

    @Deprecated
    public static native ByteBuffer getDirectByteBuffer(long var0, long var2);

    static native void setShort(Pointer var0, long var1, long var3, short var5);

    public static native void ffi_free_closure(long var0);

    private static native long _getDirectBufferPointer(Buffer var0);

    public static Library synchronizedLibrary(Library library) {
        Class<?> clazz = library.getClass();
        if (!Proxy.isProxyClass(clazz)) {
            throw new IllegalArgumentException("Library must be a proxy class");
        }
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(library);
        if (!(invocationHandler instanceof Library.Handler)) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unrecognized proxy handler: ").append(invocationHandler)));
        }
        Library.Handler handler = (Library.Handler)invocationHandler;
        InvocationHandler invocationHandler2 = new InvocationHandler(handler, library){
            final Library val$library;
            final Library.Handler val$handler;

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Converted monitor instructions to comments
             * Lifted jumps to return sites
             */
            @Override
            public Object invoke(Object object, Method method, Object[] objectArray) throws Throwable {
                NativeLibrary nativeLibrary = this.val$handler.getNativeLibrary();
                // MONITORENTER : nativeLibrary
                // MONITOREXIT : nativeLibrary
                return this.val$handler.invoke(this.val$library, method, objectArray);
            }
            {
                this.val$handler = handler;
                this.val$library = library;
            }
        };
        return (Library)Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), invocationHandler2);
    }

    public static byte[] toByteArray(String string) {
        return Native.toByteArray(string, Native.getDefaultStringEncoding());
    }

    private static NativeMapped fromNative(Class<?> clazz, Object object) {
        return (NativeMapped)NativeMappedConverter.getInstance(clazz).fromNative(object, new FromNativeContext(clazz));
    }

    static Class<?> findDirectMappedClass(Class<?> clazz) {
        Method[] methodArray;
        for (Method method : methodArray = clazz.getDeclaredMethods()) {
            if ((method.getModifiers() & 0x100) == 0) continue;
            return clazz;
        }
        int n = clazz.getName().lastIndexOf("$");
        if (n != -1) {
            String string = clazz.getName().substring(0, n);
            try {
                return Native.findDirectMappedClass(Class.forName(string, true, clazz.getClassLoader()));
            }
            catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
        }
        throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Can't determine class with native methods from the current context (").append(clazz).append(")")));
    }

    static native void write(Pointer var0, long var1, long var3, double[] var5, int var6, int var7);

    static boolean isCompatibleVersion(String string, String string2) {
        String[] stringArray = string.split("\\.");
        String[] stringArray2 = string2.split("\\.");
        if (stringArray.length < 3 || stringArray2.length < 3) {
            return false;
        }
        int n = Integer.parseInt(stringArray[0]);
        int n2 = Integer.parseInt(stringArray2[0]);
        int n3 = Integer.parseInt(stringArray[1]);
        int n4 = Integer.parseInt(stringArray2[1]);
        if (n != n2) {
            return false;
        }
        return n3 <= n4;
    }

    public static native long malloc(long var0);

    private static native long _getPointer(long var0);

    static native void setDouble(Pointer var0, long var1, long var3, double var5);

    private static native String getNativeVersion();

    static native void close(long var0);

    static native void read(Pointer var0, long var1, long var3, long[] var5, int var6, int var7);

    static native void setFloat(Pointer var0, long var1, long var3, float var5);

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void unregister(Class<?> clazz) {
        Map<Class<?>, long[]> map = registeredClasses;
        synchronized (map) {
            long[] lArray = registeredClasses.get(clazz);
            if (lArray == null) return;
            Native.unregister(clazz, lArray);
            registeredClasses.remove(clazz);
            registeredLibraries.remove(clazz);
            return;
        }
    }

    public static <T> T loadLibrary(Class<T> clazz, Map<String, ?> map) {
        return Native.loadLibrary(null, clazz, map);
    }

    private static native void invokeStructure(Function var0, long var1, int var3, Object[] var4, long var5, long var7);

    static void removeTemporaryFiles() throws IOException {
        File file = Native.getTempDir();
        FilenameFilter filenameFilter = new FilenameFilter(){

            @Override
            public boolean accept(File file, String string) {
                return string.endsWith(".x") && string.startsWith("jna");
            }
        };
        File[] fileArray = file.listFiles(filenameFilter);
        for (int i = 0; fileArray != null && i < fileArray.length; ++i) {
            File file2 = fileArray[i];
            String string = file2.getName();
            string = string.substring(0, string.length() - 2);
            File file3 = new File(file2.getParentFile(), string);
            if (file3.exists() && !file3.delete()) continue;
            file2.delete();
        }
    }

    static native long indexOf(Pointer var0, long var1, long var3, byte var5);

    public static String toString(byte[] byArray) {
        return Native.toString(byArray, Native.getDefaultStringEncoding());
    }

    static native short getShort(Pointer var0, long var1, long var3);

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
            public void uncaughtException(Callback callback, Throwable throwable) {
                System.err.println(String.valueOf(new StringBuilder().append("JNA: Callback ").append(callback).append(" threw the following exception:")));
                throwable.printStackTrace();
            }
        };
        Native.loadNativeDispatchLibrary();
        if (!Native.isCompatibleVersion("5.1.0", Native.getNativeVersion())) {
            String string = System.getProperty("line.separator");
            throw new Error(String.valueOf(new StringBuilder().append(string).append(string).append("There is an incompatible JNA native library installed on this system").append(string).append("Expected: ").append("5.1.0").append(string).append("Found:    ").append(Native.getNativeVersion()).append(string).append(jnidispatchPath != null ? String.valueOf(new StringBuilder().append("(at ").append(jnidispatchPath).append(")")) : System.getProperty("java.library.path")).append(".").append(string).append("To resolve this issue you may do one of the following:").append(string).append(" - remove or uninstall the offending library").append(string).append(" - set the system property jna.nosys=true").append(string).append(" - set jna.boot.library.path to include the path to the version of the ").append(string).append("   jnidispatch library included with the JNA jar file you are using").append(string)));
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

            protected void finalize() {
                Native.access$000();
            }
        };
        registeredClasses = new WeakHashMap();
        registeredLibraries = new WeakHashMap();
        nativeThreadTerminationFlag = new ThreadLocal<Memory>(){

            @Override
            protected Memory initialValue() {
                Memory memory = new Memory(4L);
                memory.clear();
                return memory;
            }

            @Override
            protected Object initialValue() {
                return this.initialValue();
            }
        };
        nativeThreads = Collections.synchronizedMap(new WeakHashMap());
    }

    public static int getNativeSize(Class<?> clazz, Object object) {
        if (clazz.isArray()) {
            int n = Array.getLength(object);
            if (n > 0) {
                Object object2 = Array.get(object, 0);
                return n * Native.getNativeSize(clazz.getComponentType(), object2);
            }
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Arrays of length zero not allowed: ").append(clazz)));
        }
        if (Structure.class.isAssignableFrom(clazz) && !Structure.ByReference.class.isAssignableFrom(clazz)) {
            return Structure.size(clazz, (Structure)object);
        }
        try {
            return Native.getNativeSize(clazz);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("The type \"").append(clazz.getName()).append("\" is not supported: ").append(illegalArgumentException.getMessage())));
        }
    }

    static Pointer getPointer(long l) {
        long l2 = Native._getPointer(l);
        return l2 == 0L ? null : new Pointer(l2);
    }

    private static int getConversion(Class<?> clazz, TypeMapper typeMapper) {
        Class<?> clazz2;
        if (clazz == Boolean.class) {
            clazz = Boolean.TYPE;
        } else if (clazz == Byte.class) {
            clazz = Byte.TYPE;
        } else if (clazz == Short.class) {
            clazz = Short.TYPE;
        } else if (clazz == Character.class) {
            clazz = Character.TYPE;
        } else if (clazz == Integer.class) {
            clazz = Integer.TYPE;
        } else if (clazz == Long.class) {
            clazz = Long.TYPE;
        } else if (clazz == Float.class) {
            clazz = Float.TYPE;
        } else if (clazz == Double.class) {
            clazz = Double.TYPE;
        } else if (clazz == Void.class) {
            clazz = Void.TYPE;
        }
        if (typeMapper != null) {
            clazz2 = typeMapper.getFromNativeConverter(clazz);
            ToNativeConverter toNativeConverter = typeMapper.getToNativeConverter(clazz);
            if (clazz2 != null) {
                Class<?> clazz3 = clazz2.nativeType();
                if (clazz3 == String.class) {
                    return 24;
                }
                if (clazz3 == WString.class) {
                    return 25;
                }
                return 23;
            }
            if (toNativeConverter != null) {
                Class<?> clazz4 = toNativeConverter.nativeType();
                if (clazz4 == String.class) {
                    return 24;
                }
                if (clazz4 == WString.class) {
                    return 25;
                }
                return 23;
            }
        }
        if (Pointer.class.isAssignableFrom(clazz)) {
            return 1;
        }
        if (String.class == clazz) {
            return 2;
        }
        if (WString.class.isAssignableFrom(clazz)) {
            return 20;
        }
        if (Platform.HAS_BUFFERS && Buffers.isBuffer(clazz)) {
            return 5;
        }
        if (Structure.class.isAssignableFrom(clazz)) {
            if (Structure.ByValue.class.isAssignableFrom(clazz)) {
                return 4;
            }
            return 3;
        }
        if (clazz.isArray()) {
            switch (clazz.getName().charAt(1)) {
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
        if (clazz.isPrimitive()) {
            return clazz == Boolean.TYPE ? 14 : 0;
        }
        if (Callback.class.isAssignableFrom(clazz)) {
            return 15;
        }
        if (IntegerType.class.isAssignableFrom(clazz)) {
            return 21;
        }
        if (PointerType.class.isAssignableFrom(clazz)) {
            return 22;
        }
        if (NativeMapped.class.isAssignableFrom(clazz)) {
            clazz2 = NativeMappedConverter.getInstance(clazz).nativeType();
            if (clazz2 == String.class) {
                return 18;
            }
            if (clazz2 == WString.class) {
                return 19;
            }
            return 17;
        }
        return -1;
    }

    public static long getComponentID(Component component) throws HeadlessException {
        return AWT.getComponentID(component);
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void register(Class<?> clazz, NativeLibrary nativeLibrary) {
        Method[] methodArray = clazz.getDeclaredMethods();
        ArrayList<Method> arrayList = new ArrayList<Method>();
        Map<String, Object> map = nativeLibrary.getOptions();
        TypeMapper typeMapper = (TypeMapper)map.get("type-mapper");
        map = Native.cacheOptions(clazz, map, null);
        for (Object object : methodArray) {
            if ((((Method)object).getModifiers() & 0x100) == 0) continue;
            arrayList.add((Method)object);
        }
        Object[] objectArray = new long[arrayList.size()];
        int n = 0;
        while (true) {
            Class<Pointer> clazz2;
            long l;
            long l2;
            boolean bl;
            int n2;
            FromNativeConverter fromNativeConverter;
            ToNativeConverter[] toNativeConverterArray;
            int[] nArray;
            long[] lArray;
            long[] lArray2;
            Class<?>[] classArray;
            Class<?> clazz3;
            Method method;
            Object object;
            if (n < objectArray.length) {
                method = (Method)arrayList.get(n);
                object = "(";
                clazz3 = method.getReturnType();
                classArray = method.getParameterTypes();
                lArray2 = new long[classArray.length];
                lArray = new long[classArray.length];
                nArray = new int[classArray.length];
                toNativeConverterArray = new ToNativeConverter[classArray.length];
                fromNativeConverter = null;
                n2 = Native.getConversion(clazz3, typeMapper);
                bl = false;
                switch (n2) {
                    case -1: {
                        throw new IllegalArgumentException(String.valueOf(new StringBuilder().append(clazz3).append(" is not a supported return type (in method ").append(method.getName()).append(" in ").append(clazz).append(")")));
                    }
                    case 23: 
                    case 24: 
                    case 25: {
                        fromNativeConverter = typeMapper.getFromNativeConverter(clazz3);
                        l2 = Structure.FFIType.get(clazz3.isPrimitive() ? clazz3 : Pointer.class).peer;
                        l = Structure.FFIType.get(fromNativeConverter.nativeType()).peer;
                        break;
                    }
                    case 17: 
                    case 18: 
                    case 19: 
                    case 21: 
                    case 22: {
                        l2 = Structure.FFIType.get(Pointer.class).peer;
                        l = Structure.FFIType.get(NativeMappedConverter.getInstance(clazz3).nativeType()).peer;
                        break;
                    }
                    case 3: {
                        l2 = l = Structure.FFIType.get(Pointer.class).peer;
                        break;
                    }
                    case 4: {
                        l2 = Structure.FFIType.get(Pointer.class).peer;
                        l = Structure.FFIType.get(clazz3).peer;
                        break;
                    }
                    default: {
                        l2 = l = Structure.FFIType.get(clazz3).peer;
                    }
                }
            } else {
                Map<Class<?>, long[]> map2 = registeredClasses;
                synchronized (map2) {
                    try {}
                    catch (Throwable throwable) {
                        int n3;
                        throw new UnsatisfiedLinkError(String.valueOf(new StringBuilder().append("No method ").append(n3.getName()).append(" with signature ").append((String)object).append(" in ").append(clazz)));
                    }
                    break;
                }
            }
            block18: for (int i = 0; i < classArray.length; ++i) {
                int n4;
                clazz2 = classArray[i];
                object = String.valueOf(new StringBuilder().append((String)object).append(Native.getSignature(clazz2)));
                nArray[i] = n4 = Native.getConversion(clazz2, typeMapper);
                if (n4 == -1) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append(clazz2).append(" is not a supported argument type (in method ").append(method.getName()).append(" in ").append(clazz).append(")")));
                }
                if (n4 == 17 || n4 == 18 || n4 == 19 || n4 == 21) {
                    clazz2 = NativeMappedConverter.getInstance(clazz2).nativeType();
                } else if (n4 == 23 || n4 == 24 || n4 == 25) {
                    toNativeConverterArray[i] = typeMapper.getToNativeConverter(clazz2);
                }
                switch (n4) {
                    case 4: 
                    case 17: 
                    case 18: 
                    case 19: 
                    case 21: 
                    case 22: {
                        lArray2[i] = Structure.FFIType.get((Object)clazz2).peer;
                        lArray[i] = Structure.FFIType.get(Pointer.class).peer;
                        continue block18;
                    }
                    case 23: 
                    case 24: 
                    case 25: {
                        lArray[i] = Structure.FFIType.get(clazz2.isPrimitive() ? clazz2 : Pointer.class).peer;
                        lArray2[i] = Structure.FFIType.get(toNativeConverterArray[i].nativeType()).peer;
                        continue block18;
                    }
                    case 0: {
                        lArray[i] = lArray2[i] = Structure.FFIType.get(clazz2).peer;
                        continue block18;
                    }
                    default: {
                        lArray[i] = lArray2[i] = Structure.FFIType.get(Pointer.class).peer;
                    }
                }
            }
            object = String.valueOf(new StringBuilder().append((String)object).append(")"));
            object = String.valueOf(new StringBuilder().append((String)object).append(Native.getSignature(clazz3)));
            Class<?>[] classArray2 = method.getExceptionTypes();
            for (int i = 0; i < classArray2.length; ++i) {
                if (!LastErrorException.class.isAssignableFrom(classArray2[i])) continue;
                bl = true;
                break;
            }
            clazz2 = nativeLibrary.getFunction(method.getName(), method);
            {
                objectArray[n] = (Method)Native.registerMethod(clazz, method.getName(), (String)object, nArray, lArray, lArray2, n2, l2, l, method, ((Function)clazz2).peer, ((Function)((Object)clazz2)).getCallingConvention(), bl, toNativeConverterArray, fromNativeConverter, ((Function)clazz2).encoding);
            }
            ++n;
        }
        {
            registeredClasses.put(clazz, (long[])objectArray);
            registeredLibraries.put(clazz, nativeLibrary);
            return;
        }
    }

    static native void write(Pointer var0, long var1, long var3, int[] var5, int var6, int var7);

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    static Class<?> findEnclosingLibraryClass(Class<?> clazz) {
        Class<?> clazz2;
        if (clazz == null) {
            return null;
        }
        Object object = libraries;
        // MONITORENTER : object
        if (typeOptions.containsKey(clazz)) {
            Map<String, Object> map = typeOptions.get(clazz);
            Class clazz3 = (Class)map.get("enclosing-library");
            if (clazz3 != null) {
                // MONITOREXIT : object
                return clazz3;
            }
            // MONITOREXIT : object
            return clazz;
        }
        // MONITOREXIT : object
        if (Library.class.isAssignableFrom(clazz)) {
            return clazz;
        }
        if (Callback.class.isAssignableFrom(clazz)) {
            clazz = CallbackReference.findCallbackClass(clazz);
        }
        if ((clazz2 = Native.findEnclosingLibraryClass(object = clazz.getDeclaringClass())) == null) return Native.findEnclosingLibraryClass(clazz.getSuperclass());
        return clazz2;
    }

    static String getString(Pointer pointer, long l) {
        return Native.getString(pointer, l, Native.getDefaultStringEncoding());
    }

    public static void register(NativeLibrary nativeLibrary) {
        Native.register(Native.findDirectMappedClass(Native.getCallingClass()), nativeLibrary);
    }

    static native void read(Pointer var0, long var1, long var3, double[] var5, int var6, int var7);

    static native ByteBuffer getDirectByteBuffer(Pointer var0, long var1, long var3, long var5);

    public static void unregister() {
        Native.unregister(Native.findDirectMappedClass(Native.getCallingClass()));
    }

    static byte[] getBytes(String string) {
        return Native.getBytes(string, Native.getDefaultStringEncoding());
    }

    static native long getWindowHandle0(Component var0);

    static File getTempDir() throws IOException {
        File file;
        String string = System.getProperty("jna.tmpdir");
        if (string != null) {
            file = new File(string);
            file.mkdirs();
        } else {
            File file2 = new File(System.getProperty("java.io.tmpdir"));
            file = new File(file2, String.valueOf(new StringBuilder().append("jna-").append(System.getProperty("user.name").hashCode())));
            file.mkdirs();
            if (!file.exists() || !file.canWrite()) {
                file = file2;
            }
        }
        if (!file.exists()) {
            throw new IOException(String.valueOf(new StringBuilder().append("JNA temporary directory '").append(file).append("' does not exist")));
        }
        if (!file.canWrite()) {
            throw new IOException(String.valueOf(new StringBuilder().append("JNA temporary directory '").append(file).append("' is not writable")));
        }
        return file;
    }

    static boolean deleteLibrary(File file) {
        if (file.delete()) {
            return true;
        }
        Native.markTemporaryFile(file);
        return false;
    }

    public static native int getLastError();

    public static <T> T loadLibrary(String string, Class<T> clazz, Map<String, ?> map) {
        if (!Library.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Interface (").append(clazz.getSimpleName()).append(") of library=").append(string).append(" does not extend ").append(Library.class.getSimpleName())));
        }
        Library.Handler handler = new Library.Handler(string, clazz, map);
        ClassLoader classLoader = clazz.getClassLoader();
        Object object = Proxy.newProxyInstance(classLoader, new Class[]{clazz}, handler);
        Native.cacheOptions(clazz, map, object);
        return clazz.cast(object);
    }

    static native void read(Pointer var0, long var1, long var3, char[] var5, int var6, int var7);

    static native int getInt(Pointer var0, long var1, long var3);

    static native double invokeDouble(Function var0, long var1, int var3, Object[] var4);

    static native void write(Pointer var0, long var1, long var3, float[] var5, int var6, int var7);

    public static native void setLastError(int var0);

    private static Object toNative(ToNativeConverter toNativeConverter, Object object) {
        return toNativeConverter.toNative(object, new ToNativeContext());
    }

    static native long getLong(Pointer var0, long var1, long var3);

    public static <T> T loadLibrary(Class<T> clazz) {
        return Native.loadLibrary(null, clazz);
    }

    static native String getWideString(Pointer var0, long var1, long var3);

    static void access$000() {
        Native.dispose();
    }

    private Native() {
    }

    static native void write(Pointer var0, long var1, long var3, char[] var5, int var6, int var7);

    static String replace(String string, String string2, String string3) {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int n;
            if ((n = string3.indexOf(string)) == -1) break;
            stringBuilder.append(string3.substring(0, n));
            stringBuilder.append(string2);
            string3 = string3.substring(n + string.length());
        }
        stringBuilder.append(string3);
        return String.valueOf(stringBuilder);
    }

    static native void write(Pointer var0, long var1, long var3, byte[] var5, int var6, int var7);

    public static File extractFromResourcePath(String string) throws IOException {
        return Native.extractFromResourcePath(string, null);
    }

    static native void setPointer(Pointer var0, long var1, long var3, long var5);

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Map<String, Object> cacheOptions(Class<?> clazz, Map<String, ?> map, Object object) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>(map);
        hashMap.put("enclosing-library", clazz);
        Map<Class<?>, Reference<?>> map2 = libraries;
        synchronized (map2) {
            Class<?>[] classArray;
            typeOptions.put(clazz, hashMap);
            if (object != null) {
                libraries.put(clazz, new WeakReference<Object>(object));
            }
            if (clazz.isInterface()) return hashMap;
            if (!Library.class.isAssignableFrom(clazz)) return hashMap;
            Class<?>[] classArray2 = classArray = clazz.getInterfaces();
            int n = classArray2.length;
            int n2 = 0;
            while (n2 < n) {
                Class<?> clazz2 = classArray2[n2];
                if (Library.class.isAssignableFrom(clazz2)) {
                    Native.cacheOptions(clazz2, hashMap, object);
                    return hashMap;
                }
                ++n2;
            }
            return hashMap;
        }
    }

    private static class AWT {
        static long getWindowID(Window window) throws HeadlessException {
            return AWT.getComponentID(window);
        }

        static long getComponentID(Object object) throws HeadlessException {
            if (GraphicsEnvironment.isHeadless()) {
                throw new HeadlessException("No native windows when headless");
            }
            Component component = (Component)object;
            if (component.isLightweight()) {
                throw new IllegalArgumentException("Component must be heavyweight");
            }
            if (!component.isDisplayable()) {
                throw new IllegalStateException("Component must be displayable");
            }
            if (Platform.isX11() && System.getProperty("java.version").startsWith("1.4") && !component.isVisible()) {
                throw new IllegalStateException("Component must be visible");
            }
            return Native.getWindowHandle0(component);
        }

        private AWT() {
        }
    }

    private static class Buffers {
        private Buffers() {
        }

        static boolean isBuffer(Class<?> clazz) {
            return Buffer.class.isAssignableFrom(clazz);
        }
    }

    public static interface ffi_callback {
        public void invoke(long var1, long var3, long var5);
    }
}

