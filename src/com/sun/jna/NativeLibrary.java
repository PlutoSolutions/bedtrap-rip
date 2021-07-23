/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

import com.sun.jna.Function;
import com.sun.jna.FunctionMapper;
import com.sun.jna.LastErrorException;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class NativeLibrary {
    final int callFlags;
    private static final List<String> librarySearchPath;
    final Map<String, ?> options;
    private static final int DEFAULT_OPEN_OPTIONS;
    private final Map<String, Function> functions = new HashMap<String, Function>();
    private final String libraryName;
    private static final Map<String, List<String>> searchPaths;
    private String encoding;
    private long handle;
    private final String libraryPath;
    private static final Map<String, Reference<NativeLibrary>> libraries;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Function getFunction(String string, int n, String string2) {
        if (string == null) {
            throw new NullPointerException("Function name may not be null");
        }
        Map<String, Function> map = this.functions;
        synchronized (map) {
            String string3 = NativeLibrary.functionKey(string, n, string2);
            Function function = this.functions.get(string3);
            if (function == null) {
                function = new Function(this, string, n, string2);
                this.functions.put(string3, function);
            }
            return function;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static void disposeAll() {
        LinkedHashSet<Reference<NativeLibrary>> linkedHashSet;
        Object object = libraries;
        synchronized (object) {
            linkedHashSet = new LinkedHashSet<Reference<NativeLibrary>>(libraries.values());
        }
        object = linkedHashSet.iterator();
        while (object.hasNext()) {
            Reference reference = (Reference)object.next();
            NativeLibrary nativeLibrary = (NativeLibrary)reference.get();
            if (nativeLibrary == null) continue;
            nativeLibrary.dispose();
        }
        return;
    }

    public static final NativeLibrary getInstance(String string, ClassLoader classLoader) {
        return NativeLibrary.getInstance(string, Collections.singletonMap("classloader", classLoader));
    }

    private static boolean isVersionedName(String string) {
        int n;
        if (string.startsWith("lib") && (n = string.lastIndexOf(".so.")) != -1 && n + 4 < string.length()) {
            for (int i = n + 4; i < string.length(); ++i) {
                char c = string.charAt(i);
                if (Character.isDigit(c) || c == '.') continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public static final NativeLibrary getInstance(String string) {
        return NativeLibrary.getInstance(string, Collections.emptyMap());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static NativeLibrary loadLibrary(String string, Map<String, ?> map) {
        long l;
        Object object;
        block50: {
            List<String> list;
            if (Native.DEBUG_LOAD) {
                System.out.println(String.valueOf(new StringBuilder().append("Looking for library '").append(string).append("'")));
            }
            boolean bl = new File(string).isAbsolute();
            ArrayList<String> arrayList = new ArrayList<String>();
            int n = NativeLibrary.openFlags(map);
            String string2 = Native.getWebStartLibraryPath(string);
            if (string2 != null) {
                if (Native.DEBUG_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Adding web start path ").append(string2)));
                }
                arrayList.add(string2);
            }
            if ((list = searchPaths.get(string)) != null) {
                object = list;
                synchronized (object) {
                    arrayList.addAll(0, list);
                }
            }
            if (Native.DEBUG_LOAD) {
                System.out.println(String.valueOf(new StringBuilder().append("Adding paths from jna.library.path: ").append(System.getProperty("jna.library.path"))));
            }
            arrayList.addAll(NativeLibrary.initPaths("jna.library.path"));
            object = NativeLibrary.findLibraryPath(string, arrayList);
            l = 0L;
            try {
                if (Native.DEBUG_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Trying ").append((String)object)));
                }
                l = Native.open((String)object, n);
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                if (Native.DEBUG_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Adding system paths: ").append(librarySearchPath)));
                }
                arrayList.addAll(librarySearchPath);
            }
            try {
                if (l == 0L) {
                    object = NativeLibrary.findLibraryPath(string, arrayList);
                    if (Native.DEBUG_LOAD) {
                        System.out.println(String.valueOf(new StringBuilder().append("Trying ").append((String)object)));
                    }
                    if ((l = Native.open((String)object, n)) == 0L) {
                        throw new UnsatisfiedLinkError(String.valueOf(new StringBuilder().append("Failed to load library '").append(string).append("'")));
                    }
                }
            }
            catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                UnsatisfiedLinkError unsatisfiedLinkError2;
                if (Platform.isAndroid()) {
                    try {
                        if (Native.DEBUG_LOAD) {
                            System.out.println(String.valueOf(new StringBuilder().append("Preload (via System.loadLibrary) ").append(string)));
                        }
                        System.loadLibrary(string);
                        l = Native.open((String)object, n);
                    }
                    catch (UnsatisfiedLinkError unsatisfiedLinkError3) {
                        unsatisfiedLinkError2 = unsatisfiedLinkError3;
                    }
                } else if (Platform.isLinux() || Platform.isFreeBSD()) {
                    if (Native.DEBUG_LOAD) {
                        System.out.println("Looking for version variants");
                    }
                    if ((object = NativeLibrary.matchLibrary(string, arrayList)) != null) {
                        if (Native.DEBUG_LOAD) {
                            System.out.println(String.valueOf(new StringBuilder().append("Trying ").append((String)object)));
                        }
                        try {
                            l = Native.open((String)object, n);
                        }
                        catch (UnsatisfiedLinkError unsatisfiedLinkError4) {
                            unsatisfiedLinkError2 = unsatisfiedLinkError4;
                        }
                    }
                } else if (Platform.isMac() && !string.endsWith(".dylib")) {
                    if (Native.DEBUG_LOAD) {
                        System.out.println("Looking for matching frameworks");
                    }
                    if ((object = NativeLibrary.matchFramework(string)) != null) {
                        try {
                            if (Native.DEBUG_LOAD) {
                                System.out.println(String.valueOf(new StringBuilder().append("Trying ").append((String)object)));
                            }
                            l = Native.open((String)object, n);
                        }
                        catch (UnsatisfiedLinkError unsatisfiedLinkError5) {
                            unsatisfiedLinkError2 = unsatisfiedLinkError5;
                        }
                    }
                } else if (Platform.isWindows() && !bl) {
                    if (Native.DEBUG_LOAD) {
                        System.out.println("Looking for lib- prefix");
                    }
                    if ((object = NativeLibrary.findLibraryPath(String.valueOf(new StringBuilder().append("lib").append(string)), arrayList)) != null) {
                        if (Native.DEBUG_LOAD) {
                            System.out.println(String.valueOf(new StringBuilder().append("Trying ").append((String)object)));
                        }
                        try {
                            l = Native.open((String)object, n);
                        }
                        catch (UnsatisfiedLinkError unsatisfiedLinkError6) {
                            unsatisfiedLinkError2 = unsatisfiedLinkError6;
                        }
                    }
                }
                if (l == 0L) {
                    try {
                        File file = Native.extractFromResourcePath(string, (ClassLoader)map.get("classloader"));
                        try {
                            l = Native.open(file.getAbsolutePath(), n);
                            object = file.getAbsolutePath();
                        }
                        finally {
                            if (Native.isUnpacked(file)) {
                                Native.deleteLibrary(file);
                            }
                        }
                    }
                    catch (IOException iOException) {
                        unsatisfiedLinkError2 = new UnsatisfiedLinkError(iOException.getMessage());
                    }
                }
                if (l != 0L) break block50;
                throw new UnsatisfiedLinkError(String.valueOf(new StringBuilder().append("Unable to load library '").append(string).append("': ").append(unsatisfiedLinkError2.getMessage())));
            }
        }
        if (Native.DEBUG_LOAD) {
            System.out.println(String.valueOf(new StringBuilder().append("Found library '").append(string).append("' at ").append((String)object)));
        }
        return new NativeLibrary(string, (String)object, l, map);
    }

    public Pointer getGlobalVariableAddress(String string) {
        try {
            return new Pointer(this.getSymbolAddress(string));
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            throw new UnsatisfiedLinkError(String.valueOf(new StringBuilder().append("Error looking up '").append(string).append("': ").append(unsatisfiedLinkError.getMessage())));
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static final void addSearchPath(String string, String string2) {
        Map<String, List<String>> map = searchPaths;
        synchronized (map) {
            List<String> list = searchPaths.get(string);
            if (list == null) {
                list = Collections.synchronizedList(new ArrayList());
                searchPaths.put(string, list);
            }
            list.add(string2);
            return;
        }
    }

    private static String functionKey(String string, int n, String string2) {
        return String.valueOf(new StringBuilder().append(string).append("|").append(n).append("|").append(string2));
    }

    public Function getFunction(String string) {
        return this.getFunction(string, this.callFlags);
    }

    static {
        block10: {
            DEFAULT_OPEN_OPTIONS = -1;
            libraries = new HashMap<String, Reference<NativeLibrary>>();
            searchPaths = Collections.synchronizedMap(new HashMap());
            librarySearchPath = new ArrayList<String>();
            if (Native.POINTER_SIZE == 0) {
                throw new Error("Native library not initialized");
            }
            String string = Native.getWebStartLibraryPath("jnidispatch");
            if (string != null) {
                librarySearchPath.add(string);
            }
            if (System.getProperty("jna.platform.library.path") == null && !Platform.isWindows()) {
                Object object;
                String string2 = "";
                String string3 = "";
                String string4 = "";
                if (Platform.isLinux() || Platform.isSolaris() || Platform.isFreeBSD() || Platform.iskFreeBSD()) {
                    string4 = String.valueOf(new StringBuilder().append(Platform.isSolaris() ? "/" : "").append(Pointer.SIZE * 8));
                }
                String[] stringArray = new String[]{String.valueOf(new StringBuilder().append("/usr/lib").append(string4)), String.valueOf(new StringBuilder().append("/lib").append(string4)), "/usr/lib", "/lib"};
                if (Platform.isLinux() || Platform.iskFreeBSD() || Platform.isGNU()) {
                    object = NativeLibrary.getMultiArchPath();
                    stringArray = new String[]{String.valueOf(new StringBuilder().append("/usr/lib/").append((String)object)), String.valueOf(new StringBuilder().append("/lib/").append((String)object)), String.valueOf(new StringBuilder().append("/usr/lib").append(string4)), String.valueOf(new StringBuilder().append("/lib").append(string4)), "/usr/lib", "/lib"};
                }
                if (Platform.isLinux()) {
                    object = NativeLibrary.getLinuxLdPaths();
                    for (int i = stringArray.length - 1; 0 <= i; --i) {
                        int n = ((ArrayList)object).indexOf(stringArray[i]);
                        if (n != -1) {
                            ((ArrayList)object).remove(n);
                        }
                        ((ArrayList)object).add(0, stringArray[i]);
                    }
                    stringArray = ((ArrayList)object).toArray(new String[((ArrayList)object).size()]);
                }
                for (int i = 0; i < stringArray.length; ++i) {
                    File file = new File(stringArray[i]);
                    if (!file.exists() || !file.isDirectory()) continue;
                    string2 = String.valueOf(new StringBuilder().append(string2).append(string3).append(stringArray[i]));
                    string3 = File.pathSeparator;
                    if (-2 <= 0) continue;
                    break block10;
                }
                if (!"".equals(string2)) {
                    System.setProperty("jna.platform.library.path", string2);
                }
            }
            librarySearchPath.addAll(NativeLibrary.initPaths("jna.platform.library.path"));
        }
    }

    static boolean access$000(String string) {
        return NativeLibrary.isVersionedName(string);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void dispose() {
        HashSet hashSet = new HashSet();
        Object object = libraries;
        synchronized (object) {
            for (Map.Entry<String, Reference<NativeLibrary>> entry : libraries.entrySet()) {
                Reference<NativeLibrary> reference = entry.getValue();
                if (reference.get() != this) continue;
                hashSet.add(entry.getKey());
            }
            Iterator<Map.Entry<String, Reference<NativeLibrary>>> iterator = hashSet.iterator();
            while (true) {
                Map.Entry<String, Reference<NativeLibrary>> entry;
                if (!iterator.hasNext()) {
                    // MONITOREXIT @DISABLED, blocks:[2, 5, 6] lbl15 : MonitorExitStatement: MONITOREXIT : var2_2
                    object = this;
                    synchronized (object) {
                        if (this.handle == 0L) return;
                        Native.close(this.handle);
                        this.handle = 0L;
                        return;
                    }
                }
                entry = (String)((Object)iterator.next());
                libraries.remove(entry);
            }
        }
    }

    public File getFile() {
        if (this.libraryPath == null) {
            return null;
        }
        return new File(this.libraryPath);
    }

    private static List<String> initPaths(String string) {
        String string2 = System.getProperty(string, "");
        if ("".equals(string2)) {
            return Collections.emptyList();
        }
        StringTokenizer stringTokenizer = new StringTokenizer(string2, File.pathSeparator);
        ArrayList<String> arrayList = new ArrayList<String>();
        while (stringTokenizer.hasMoreTokens()) {
            String string3 = stringTokenizer.nextToken();
            if ("".equals(string3)) continue;
            arrayList.add(string3);
        }
        return arrayList;
    }

    private static ArrayList<String> getLinuxLdPaths() {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            Process process = Runtime.getRuntime().exec("/sbin/ldconfig -p");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String string = "";
            while ((string = bufferedReader.readLine()) != null) {
                String string2;
                int n = string.indexOf(" => ");
                int n2 = string.lastIndexOf(47);
                if (n == -1 || n2 == -1 || n >= n2 || arrayList.contains(string2 = string.substring(n + 4, n2))) continue;
                arrayList.add(string2);
            }
            bufferedReader.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
        return arrayList;
    }

    private static String getMultiArchPath() {
        String string = Platform.ARCH;
        String string2 = Platform.iskFreeBSD() ? "-kfreebsd" : (Platform.isGNU() ? "" : "-linux");
        String string3 = "-gnu";
        if (Platform.isIntel()) {
            string = Platform.is64Bit() ? "x86_64" : "i386";
        } else if (Platform.isPPC()) {
            string = Platform.is64Bit() ? "powerpc64" : "powerpc";
        } else if (Platform.isARM()) {
            string = "arm";
            string3 = "-gnueabi";
        }
        return String.valueOf(new StringBuilder().append(string).append(string2).append(string3));
    }

    static double parseVersion(String string) {
        double d = 0.0;
        double d2 = 1.0;
        int n = string.indexOf(".");
        while (string != null) {
            String string2;
            if (n != -1) {
                string2 = string.substring(0, n);
                string = string.substring(n + 1);
                n = string.indexOf(".");
            } else {
                string2 = string;
                string = null;
            }
            try {
                d += (double)Integer.parseInt(string2) / d2;
            }
            catch (NumberFormatException numberFormatException) {
                return 0.0;
            }
            d2 *= 100.0;
        }
        return d;
    }

    static String matchLibrary(String string, List<String> list) {
        Object object;
        File file = new File(string);
        if (file.isAbsolute()) {
            list = Arrays.asList(file.getParent());
        }
        FilenameFilter filenameFilter = new FilenameFilter(string){
            final String val$libName;
            {
                this.val$libName = string;
            }

            @Override
            public boolean accept(File file, String string) {
                return (string.startsWith(String.valueOf(new StringBuilder().append("lib").append(this.val$libName).append(".so"))) || string.startsWith(String.valueOf(new StringBuilder().append(this.val$libName).append(".so"))) && this.val$libName.startsWith("lib")) && NativeLibrary.access$000(string);
            }
        };
        LinkedList<File> linkedList = new LinkedList<File>();
        for (String string2 : list) {
            object = new File(string2).listFiles(filenameFilter);
            if (object == null || ((File[])object).length <= 0) continue;
            linkedList.addAll(Arrays.asList(object));
        }
        double d = -1.0;
        object = null;
        for (File file2 : linkedList) {
            String string3 = file2.getAbsolutePath();
            String string4 = string3.substring(string3.lastIndexOf(".so.") + 4);
            double d2 = NativeLibrary.parseVersion(string4);
            if (!(d2 > d)) continue;
            d = d2;
            object = string3;
        }
        return object;
    }

    private static int openFlags(Map<String, ?> map) {
        Object obj = map.get("open-flags");
        if (obj instanceof Number) {
            return ((Number)obj).intValue();
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static final NativeLibrary getInstance(String string, Map<String, ?> map) {
        HashMap hashMap = new HashMap(map);
        if (hashMap.get("calling-convention") == null) {
            hashMap.put("calling-convention", 0);
        }
        if ((Platform.isLinux() || Platform.isFreeBSD() || Platform.isAIX()) && Platform.C_LIBRARY_NAME.equals(string)) {
            string = null;
        }
        Map<String, Reference<NativeLibrary>> map2 = libraries;
        synchronized (map2) {
            NativeLibrary nativeLibrary;
            Reference<NativeLibrary> reference = libraries.get(String.valueOf(new StringBuilder().append(string).append(hashMap)));
            NativeLibrary nativeLibrary2 = nativeLibrary = reference != null ? reference.get() : null;
            if (nativeLibrary == null) {
                nativeLibrary = string == null ? new NativeLibrary("<process>", null, Native.open(null, NativeLibrary.openFlags(hashMap)), hashMap) : NativeLibrary.loadLibrary(string, hashMap);
                reference = new WeakReference<NativeLibrary>(nativeLibrary);
                libraries.put(String.valueOf(new StringBuilder().append(nativeLibrary.getName()).append(hashMap)), reference);
                File file = nativeLibrary.getFile();
                if (file != null) {
                    libraries.put(String.valueOf(new StringBuilder().append(file.getAbsolutePath()).append(hashMap)), reference);
                    libraries.put(String.valueOf(new StringBuilder().append(file.getName()).append(hashMap)), reference);
                }
            }
            return nativeLibrary;
        }
    }

    Function getFunction(String string, Method method) {
        String string2;
        FunctionMapper functionMapper = (FunctionMapper)this.options.get("function-mapper");
        if (functionMapper != null) {
            string = functionMapper.getFunctionName(this, method);
        }
        if (string.startsWith(string2 = System.getProperty("jna.profiler.prefix", "$$YJP$$"))) {
            string = string.substring(string2.length());
        }
        int n = this.callFlags;
        Class<?>[] classArray = method.getExceptionTypes();
        for (int i = 0; i < classArray.length; ++i) {
            if (!LastErrorException.class.isAssignableFrom(classArray[i])) continue;
            n |= 0x40;
            if (3 > 0) continue;
            return null;
        }
        return this.getFunction(string, n);
    }

    private String getLibraryName(String string) {
        String string2;
        int n;
        String string3 = string;
        String string4 = "---";
        String string5 = NativeLibrary.mapSharedLibraryName("---");
        int n2 = string5.indexOf("---");
        if (n2 > 0 && string3.startsWith(string5.substring(0, n2))) {
            string3 = string3.substring(n2);
        }
        if ((n = string3.indexOf(string2 = string5.substring(n2 + "---".length()))) != -1) {
            string3 = string3.substring(0, n);
        }
        return string3;
    }

    static String matchFramework(String string) {
        File file = new File(string);
        if (file.isAbsolute()) {
            if (string.indexOf(".framework") != -1 && file.exists()) {
                return file.getAbsolutePath();
            }
            if ((file = new File(new File(file.getParentFile(), String.valueOf(new StringBuilder().append(file.getName()).append(".framework"))), file.getName())).exists()) {
                return file.getAbsolutePath();
            }
        } else {
            String[] stringArray = new String[]{System.getProperty("user.home"), "", "/System"};
            String string2 = string.indexOf(".framework") == -1 ? String.valueOf(new StringBuilder().append(string).append(".framework/").append(string)) : string;
            for (int i = 0; i < stringArray.length; ++i) {
                String string3 = String.valueOf(new StringBuilder().append(stringArray[i]).append("/Library/Frameworks/").append(string2));
                if (!new File(string3).exists()) continue;
                return string3;
            }
        }
        return null;
    }

    public Map<String, ?> getOptions() {
        return this.options;
    }

    private static String findLibraryPath(String string, List<String> list) {
        if (new File(string).isAbsolute()) {
            return string;
        }
        String string2 = NativeLibrary.mapSharedLibraryName(string);
        for (String string3 : list) {
            File file = new File(string3, string2);
            if (file.exists()) {
                return file.getAbsolutePath();
            }
            if (!Platform.isMac() || !string2.endsWith(".dylib") || !(file = new File(string3, String.valueOf(new StringBuilder().append(string2.substring(0, string2.lastIndexOf(".dylib"))).append(".jnilib")))).exists()) continue;
            return file.getAbsolutePath();
        }
        return string2;
    }

    public Function getFunction(String string, int n) {
        return this.getFunction(string, n, this.encoding);
    }

    long getSymbolAddress(String string) {
        if (this.handle == 0L) {
            throw new UnsatisfiedLinkError("Library has been unloaded");
        }
        return Native.findSymbol(this.handle, string);
    }

    protected void finalize() {
        this.dispose();
    }

    public String toString() {
        return String.valueOf(new StringBuilder().append("Native Library <").append(this.libraryPath).append("@").append(this.handle).append(">"));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private NativeLibrary(String string, String string2, long l, Map<String, ?> map) {
        int n;
        this.libraryName = this.getLibraryName(string);
        this.libraryPath = string2;
        this.handle = l;
        Object obj = map.get("calling-convention");
        this.callFlags = n = obj instanceof Number ? ((Number)obj).intValue() : 0;
        this.options = map;
        this.encoding = (String)map.get("string-encoding");
        if (this.encoding == null) {
            this.encoding = Native.getDefaultStringEncoding();
        }
        if (!Platform.isWindows()) return;
        if (!"kernel32".equals(this.libraryName.toLowerCase())) return;
        Map<String, Function> map2 = this.functions;
        synchronized (map2) {
            Function function = new Function(this, this, "GetLastError", 63, this.encoding){
                final NativeLibrary this$0;
                {
                    this.this$0 = nativeLibrary;
                    super(nativeLibrary2, string, n, string2);
                }

                @Override
                Object invoke(Method method, Class<?>[] classArray, Class<?> clazz, Object[] objectArray, Map<String, ?> map) {
                    return Native.getLastError();
                }

                @Override
                Object invoke(Object[] objectArray, Class<?> clazz, boolean bl, int n) {
                    return Native.getLastError();
                }
            };
            this.functions.put(NativeLibrary.functionKey("GetLastError", this.callFlags, this.encoding), function);
            return;
        }
    }

    public String getName() {
        return this.libraryName;
    }

    public static final synchronized NativeLibrary getProcess(Map<String, ?> map) {
        return NativeLibrary.getInstance(null, map);
    }

    public static final synchronized NativeLibrary getProcess() {
        return NativeLibrary.getInstance(null);
    }

    static String mapSharedLibraryName(String string) {
        if (Platform.isMac()) {
            if (string.startsWith("lib") && (string.endsWith(".dylib") || string.endsWith(".jnilib"))) {
                return string;
            }
            String string2 = System.mapLibraryName(string);
            if (string2.endsWith(".jnilib")) {
                return String.valueOf(new StringBuilder().append(string2.substring(0, string2.lastIndexOf(".jnilib"))).append(".dylib"));
            }
            return string2;
        }
        if (Platform.isLinux() || Platform.isFreeBSD() ? NativeLibrary.isVersionedName(string) || string.endsWith(".so") : (Platform.isAIX() ? string.startsWith("lib") : Platform.isWindows() && (string.endsWith(".drv") || string.endsWith(".dll")))) {
            return string;
        }
        return System.mapLibraryName(string);
    }
}

