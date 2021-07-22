/*
 * Decompiled with CFR 0.150.
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
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class NativeLibrary {
    final /* synthetic */ int callFlags;
    private static final /* synthetic */ List<String> librarySearchPath;
    final /* synthetic */ Map<String, ?> options;
    private static final /* synthetic */ int DEFAULT_OPEN_OPTIONS;
    private final /* synthetic */ Map<String, Function> functions;
    private final /* synthetic */ String libraryName;
    private static final /* synthetic */ Map<String, List<String>> searchPaths;
    private /* synthetic */ String encoding;
    private /* synthetic */ long handle;
    private final /* synthetic */ String libraryPath;
    private static final /* synthetic */ Map<String, Reference<NativeLibrary>> libraries;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Function getFunction(String llllllllllllllllIllIllIlIIllllIl, int llllllllllllllllIllIllIlIIlllIII, String llllllllllllllllIllIllIlIIllIlll) {
        NativeLibrary llllllllllllllllIllIllIlIIlllIlI;
        if (llllllllllllllllIllIllIlIIllllIl == null) {
            throw new NullPointerException("Function name may not be null");
        }
        Map<String, Function> llllllllllllllllIllIllIlIIllIllI = llllllllllllllllIllIllIlIIlllIlI.functions;
        synchronized (llllllllllllllllIllIllIlIIllIllI) {
            String llllllllllllllllIllIllIlIlIIIIII = NativeLibrary.functionKey(llllllllllllllllIllIllIlIIllllIl, llllllllllllllllIllIllIlIIlllIII, llllllllllllllllIllIllIlIIllIlll);
            Function llllllllllllllllIllIllIlIIllllll = llllllllllllllllIllIllIlIIlllIlI.functions.get(llllllllllllllllIllIllIlIlIIIIII);
            if (llllllllllllllllIllIllIlIIllllll == null) {
                llllllllllllllllIllIllIlIIllllll = new Function(llllllllllllllllIllIllIlIIlllIlI, llllllllllllllllIllIllIlIIllllIl, llllllllllllllllIllIllIlIIlllIII, llllllllllllllllIllIllIlIIllIlll);
                llllllllllllllllIllIllIlIIlllIlI.functions.put(llllllllllllllllIllIllIlIlIIIIII, llllllllllllllllIllIllIlIIllllll);
            }
            return llllllllllllllllIllIllIlIIllllll;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    static void disposeAll() {
        void llllllllllllllllIllIllIlIIIIllIl;
        Map<String, Reference<NativeLibrary>> llllllllllllllllIllIllIlIIIIlIll = libraries;
        synchronized (llllllllllllllllIllIllIlIIIIlIll) {
            LinkedHashSet<Reference<NativeLibrary>> llllllllllllllllIllIllIlIIIlIIII = new LinkedHashSet<Reference<NativeLibrary>>(libraries.values());
        }
        for (Reference llllllllllllllllIllIllIlIIIIlllI : llllllllllllllllIllIllIlIIIIllIl) {
            NativeLibrary llllllllllllllllIllIllIlIIIIllll = (NativeLibrary)llllllllllllllllIllIllIlIIIIlllI.get();
            if (llllllllllllllllIllIllIlIIIIllll == null) continue;
            llllllllllllllllIllIllIlIIIIllll.dispose();
        }
    }

    public static final NativeLibrary getInstance(String llllllllllllllllIllIllIllIIllIIl, ClassLoader llllllllllllllllIllIllIllIIlIllI) {
        return NativeLibrary.getInstance(llllllllllllllllIllIllIllIIllIIl, Collections.singletonMap("classloader", llllllllllllllllIllIllIllIIlIllI));
    }

    private static boolean isVersionedName(String llllllllllllllllIllIllIIllIIIllI) {
        int llllllllllllllllIllIllIIllIIIlll;
        if (llllllllllllllllIllIllIIllIIIllI.startsWith("lib") && (llllllllllllllllIllIllIIllIIIlll = llllllllllllllllIllIllIIllIIIllI.lastIndexOf(".so.")) != -1 && llllllllllllllllIllIllIIllIIIlll + 4 < llllllllllllllllIllIllIIllIIIllI.length()) {
            for (int llllllllllllllllIllIllIIllIIlIII = llllllllllllllllIllIllIIllIIIlll + 4; llllllllllllllllIllIllIIllIIlIII < llllllllllllllllIllIllIIllIIIllI.length(); ++llllllllllllllllIllIllIIllIIlIII) {
                char llllllllllllllllIllIllIIllIIlIIl = llllllllllllllllIllIllIIllIIIllI.charAt(llllllllllllllllIllIllIIllIIlIII);
                if (Character.isDigit(llllllllllllllllIllIllIIllIIlIIl) || llllllllllllllllIllIllIIllIIlIIl == '.') continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public static final NativeLibrary getInstance(String llllllllllllllllIllIllIllIIlllIl) {
        return NativeLibrary.getInstance(llllllllllllllllIllIllIllIIlllIl, Collections.emptyMap());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static NativeLibrary loadLibrary(String llllllllllllllllIllIllIlllIllIll, Map<String, ?> llllllllllllllllIllIllIlllIlIIIl) {
        long llllllllllllllllIllIllIlllIlIIll;
        String llllllllllllllllIllIllIlllIlIlII;
        block51: {
            List<String> llllllllllllllllIllIllIlllIlIlIl;
            if (Native.DEBUG_LOAD) {
                System.out.println(String.valueOf(new StringBuilder().append("Looking for library '").append(llllllllllllllllIllIllIlllIllIll).append("'")));
            }
            boolean llllllllllllllllIllIllIlllIllIIl = new File(llllllllllllllllIllIllIlllIllIll).isAbsolute();
            ArrayList<String> llllllllllllllllIllIllIlllIllIII = new ArrayList<String>();
            int llllllllllllllllIllIllIlllIlIlll = NativeLibrary.openFlags(llllllllllllllllIllIllIlllIlIIIl);
            String llllllllllllllllIllIllIlllIlIllI = Native.getWebStartLibraryPath(llllllllllllllllIllIllIlllIllIll);
            if (llllllllllllllllIllIllIlllIlIllI != null) {
                if (Native.DEBUG_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Adding web start path ").append(llllllllllllllllIllIllIlllIlIllI)));
                }
                llllllllllllllllIllIllIlllIllIII.add(llllllllllllllllIllIllIlllIlIllI);
            }
            if ((llllllllllllllllIllIllIlllIlIlIl = searchPaths.get(llllllllllllllllIllIllIlllIllIll)) != null) {
                List<String> llllllllllllllllIllIllIlllIIlIll = llllllllllllllllIllIllIlllIlIlIl;
                synchronized (llllllllllllllllIllIllIlllIIlIll) {
                    llllllllllllllllIllIllIlllIllIII.addAll(0, llllllllllllllllIllIllIlllIlIlIl);
                }
            }
            if (Native.DEBUG_LOAD) {
                System.out.println(String.valueOf(new StringBuilder().append("Adding paths from jna.library.path: ").append(System.getProperty("jna.library.path"))));
            }
            llllllllllllllllIllIllIlllIllIII.addAll(NativeLibrary.initPaths("jna.library.path"));
            llllllllllllllllIllIllIlllIlIlII = NativeLibrary.findLibraryPath(llllllllllllllllIllIllIlllIllIll, llllllllllllllllIllIllIlllIllIII);
            llllllllllllllllIllIllIlllIlIIll = 0L;
            try {
                if (Native.DEBUG_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Trying ").append(llllllllllllllllIllIllIlllIlIlII)));
                }
                llllllllllllllllIllIllIlllIlIIll = Native.open(llllllllllllllllIllIllIlllIlIlII, llllllllllllllllIllIllIlllIlIlll);
            }
            catch (UnsatisfiedLinkError llllllllllllllllIllIllIllllIIIll) {
                if (Native.DEBUG_LOAD) {
                    System.out.println(String.valueOf(new StringBuilder().append("Adding system paths: ").append(librarySearchPath)));
                }
                llllllllllllllllIllIllIlllIllIII.addAll(librarySearchPath);
            }
            try {
                if (llllllllllllllllIllIllIlllIlIIll == 0L) {
                    llllllllllllllllIllIllIlllIlIlII = NativeLibrary.findLibraryPath(llllllllllllllllIllIllIlllIllIll, llllllllllllllllIllIllIlllIllIII);
                    if (Native.DEBUG_LOAD) {
                        System.out.println(String.valueOf(new StringBuilder().append("Trying ").append(llllllllllllllllIllIllIlllIlIlII)));
                    }
                    if ((llllllllllllllllIllIllIlllIlIIll = Native.open(llllllllllllllllIllIllIlllIlIlII, llllllllllllllllIllIllIlllIlIlll)) == 0L) {
                        throw new UnsatisfiedLinkError(String.valueOf(new StringBuilder().append("Failed to load library '").append(llllllllllllllllIllIllIlllIllIll).append("'")));
                    }
                }
            }
            catch (UnsatisfiedLinkError llllllllllllllllIllIllIlllIlllII) {
                if (Platform.isAndroid()) {
                    try {
                        if (Native.DEBUG_LOAD) {
                            System.out.println(String.valueOf(new StringBuilder().append("Preload (via System.loadLibrary) ").append(llllllllllllllllIllIllIlllIllIll)));
                        }
                        System.loadLibrary(llllllllllllllllIllIllIlllIllIll);
                        llllllllllllllllIllIllIlllIlIIll = Native.open(llllllllllllllllIllIllIlllIlIlII, llllllllllllllllIllIllIlllIlIlll);
                    }
                    catch (UnsatisfiedLinkError llllllllllllllllIllIllIllllIIIlI) {
                        llllllllllllllllIllIllIlllIlllII = llllllllllllllllIllIllIllllIIIlI;
                    }
                } else if (Platform.isLinux() || Platform.isFreeBSD()) {
                    if (Native.DEBUG_LOAD) {
                        System.out.println("Looking for version variants");
                    }
                    if ((llllllllllllllllIllIllIlllIlIlII = NativeLibrary.matchLibrary(llllllllllllllllIllIllIlllIllIll, llllllllllllllllIllIllIlllIllIII)) != null) {
                        if (Native.DEBUG_LOAD) {
                            System.out.println(String.valueOf(new StringBuilder().append("Trying ").append(llllllllllllllllIllIllIlllIlIlII)));
                        }
                        try {
                            llllllllllllllllIllIllIlllIlIIll = Native.open(llllllllllllllllIllIllIlllIlIlII, llllllllllllllllIllIllIlllIlIlll);
                        }
                        catch (UnsatisfiedLinkError llllllllllllllllIllIllIllllIIIIl) {
                            llllllllllllllllIllIllIlllIlllII = llllllllllllllllIllIllIllllIIIIl;
                        }
                    }
                } else if (Platform.isMac() && !llllllllllllllllIllIllIlllIllIll.endsWith(".dylib")) {
                    if (Native.DEBUG_LOAD) {
                        System.out.println("Looking for matching frameworks");
                    }
                    if ((llllllllllllllllIllIllIlllIlIlII = NativeLibrary.matchFramework(llllllllllllllllIllIllIlllIllIll)) != null) {
                        try {
                            if (Native.DEBUG_LOAD) {
                                System.out.println(String.valueOf(new StringBuilder().append("Trying ").append(llllllllllllllllIllIllIlllIlIlII)));
                            }
                            llllllllllllllllIllIllIlllIlIIll = Native.open(llllllllllllllllIllIllIlllIlIlII, llllllllllllllllIllIllIlllIlIlll);
                        }
                        catch (UnsatisfiedLinkError llllllllllllllllIllIllIllllIIIII) {
                            llllllllllllllllIllIllIlllIlllII = llllllllllllllllIllIllIllllIIIII;
                        }
                    }
                } else if (Platform.isWindows() && !llllllllllllllllIllIllIlllIllIIl) {
                    if (Native.DEBUG_LOAD) {
                        System.out.println("Looking for lib- prefix");
                    }
                    if ((llllllllllllllllIllIllIlllIlIlII = NativeLibrary.findLibraryPath(String.valueOf(new StringBuilder().append("lib").append(llllllllllllllllIllIllIlllIllIll)), llllllllllllllllIllIllIlllIllIII)) != null) {
                        if (Native.DEBUG_LOAD) {
                            System.out.println(String.valueOf(new StringBuilder().append("Trying ").append(llllllllllllllllIllIllIlllIlIlII)));
                        }
                        try {
                            llllllllllllllllIllIllIlllIlIIll = Native.open(llllllllllllllllIllIllIlllIlIlII, llllllllllllllllIllIllIlllIlIlll);
                        }
                        catch (UnsatisfiedLinkError llllllllllllllllIllIllIlllIlllll) {
                            llllllllllllllllIllIllIlllIlllII = llllllllllllllllIllIllIlllIlllll;
                        }
                    }
                }
                if (llllllllllllllllIllIllIlllIlIIll == 0L) {
                    try {
                        File llllllllllllllllIllIllIlllIllllI = Native.extractFromResourcePath(llllllllllllllllIllIllIlllIllIll, (ClassLoader)llllllllllllllllIllIllIlllIlIIIl.get("classloader"));
                        try {
                            llllllllllllllllIllIllIlllIlIIll = Native.open(llllllllllllllllIllIllIlllIllllI.getAbsolutePath(), llllllllllllllllIllIllIlllIlIlll);
                            llllllllllllllllIllIllIlllIlIlII = llllllllllllllllIllIllIlllIllllI.getAbsolutePath();
                        }
                        finally {
                            if (Native.isUnpacked(llllllllllllllllIllIllIlllIllllI)) {
                                Native.deleteLibrary(llllllllllllllllIllIllIlllIllllI);
                            }
                        }
                    }
                    catch (IOException llllllllllllllllIllIllIlllIlllIl) {
                        llllllllllllllllIllIllIlllIlllII = new UnsatisfiedLinkError(llllllllllllllllIllIllIlllIlllIl.getMessage());
                    }
                }
                if (llllllllllllllllIllIllIlllIlIIll != 0L) break block51;
                throw new UnsatisfiedLinkError(String.valueOf(new StringBuilder().append("Unable to load library '").append(llllllllllllllllIllIllIlllIllIll).append("': ").append(llllllllllllllllIllIllIlllIlllII.getMessage())));
            }
        }
        if (Native.DEBUG_LOAD) {
            System.out.println(String.valueOf(new StringBuilder().append("Found library '").append(llllllllllllllllIllIllIlllIllIll).append("' at ").append(llllllllllllllllIllIllIlllIlIlII)));
        }
        return new NativeLibrary(llllllllllllllllIllIllIlllIllIll, llllllllllllllllIllIllIlllIlIlII, llllllllllllllllIllIllIlllIlIIll, llllllllllllllllIllIllIlllIlIIIl);
    }

    public Pointer getGlobalVariableAddress(String llllllllllllllllIllIllIlIIlIlIlI) {
        try {
            NativeLibrary llllllllllllllllIllIllIlIIlIlIll;
            return new Pointer(llllllllllllllllIllIllIlIIlIlIll.getSymbolAddress(llllllllllllllllIllIllIlIIlIlIlI));
        }
        catch (UnsatisfiedLinkError llllllllllllllllIllIllIlIIlIllII) {
            throw new UnsatisfiedLinkError(String.valueOf(new StringBuilder().append("Error looking up '").append(llllllllllllllllIllIllIlIIlIlIlI).append("': ").append(llllllllllllllllIllIllIlIIlIllII.getMessage())));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final void addSearchPath(String llllllllllllllllIllIllIlIlllIllI, String llllllllllllllllIllIllIlIlllIlIl) {
        Map<String, List<String>> llllllllllllllllIllIllIlIlllIIlI = searchPaths;
        synchronized (llllllllllllllllIllIllIlIlllIIlI) {
            List<String> llllllllllllllllIllIllIlIlllIlll = searchPaths.get(llllllllllllllllIllIllIlIlllIllI);
            if (llllllllllllllllIllIllIlIlllIlll == null) {
                llllllllllllllllIllIllIlIlllIlll = Collections.synchronizedList(new ArrayList());
                searchPaths.put(llllllllllllllllIllIllIlIlllIllI, llllllllllllllllIllIllIlIlllIlll);
            }
            llllllllllllllllIllIllIlIlllIlll.add(llllllllllllllllIllIllIlIlllIlIl);
        }
    }

    private static String functionKey(String llllllllllllllllIllIlllIIIIlIlll, int llllllllllllllllIllIlllIIIIlIIll, String llllllllllllllllIllIlllIIIIlIlIl) {
        return String.valueOf(new StringBuilder().append(llllllllllllllllIllIlllIIIIlIlll).append("|").append(llllllllllllllllIllIlllIIIIlIIll).append("|").append(llllllllllllllllIllIlllIIIIlIlIl));
    }

    public Function getFunction(String llllllllllllllllIllIllIlIllIlIlI) {
        NativeLibrary llllllllllllllllIllIllIlIllIlIll;
        return llllllllllllllllIllIllIlIllIlIll.getFunction(llllllllllllllllIllIllIlIllIlIlI, llllllllllllllllIllIllIlIllIlIll.callFlags);
    }

    static {
        DEFAULT_OPEN_OPTIONS = -1;
        libraries = new HashMap<String, Reference<NativeLibrary>>();
        searchPaths = Collections.synchronizedMap(new HashMap());
        librarySearchPath = new ArrayList<String>();
        if (Native.POINTER_SIZE == 0) {
            throw new Error("Native library not initialized");
        }
        String llllllllllllllllIllIllIIIlIlIlII = Native.getWebStartLibraryPath("jnidispatch");
        if (llllllllllllllllIllIllIIIlIlIlII != null) {
            librarySearchPath.add(llllllllllllllllIllIllIIIlIlIlII);
        }
        if (System.getProperty("jna.platform.library.path") == null && !Platform.isWindows()) {
            String llllllllllllllllIllIllIIIlIllIII = "";
            String llllllllllllllllIllIllIIIlIlIlll = "";
            String llllllllllllllllIllIllIIIlIlIllI = "";
            if (Platform.isLinux() || Platform.isSolaris() || Platform.isFreeBSD() || Platform.iskFreeBSD()) {
                llllllllllllllllIllIllIIIlIlIllI = String.valueOf(new StringBuilder().append(Platform.isSolaris() ? "/" : "").append(Pointer.SIZE * 8));
            }
            String[] llllllllllllllllIllIllIIIlIlIlIl = new String[]{String.valueOf(new StringBuilder().append("/usr/lib").append(llllllllllllllllIllIllIIIlIlIllI)), String.valueOf(new StringBuilder().append("/lib").append(llllllllllllllllIllIllIIIlIlIllI)), "/usr/lib", "/lib"};
            if (Platform.isLinux() || Platform.iskFreeBSD() || Platform.isGNU()) {
                String llllllllllllllllIllIllIIIlIllllI = NativeLibrary.getMultiArchPath();
                llllllllllllllllIllIllIIIlIlIlIl = new String[]{String.valueOf(new StringBuilder().append("/usr/lib/").append(llllllllllllllllIllIllIIIlIllllI)), String.valueOf(new StringBuilder().append("/lib/").append(llllllllllllllllIllIllIIIlIllllI)), String.valueOf(new StringBuilder().append("/usr/lib").append(llllllllllllllllIllIllIIIlIlIllI)), String.valueOf(new StringBuilder().append("/lib").append(llllllllllllllllIllIllIIIlIlIllI)), "/usr/lib", "/lib"};
            }
            if (Platform.isLinux()) {
                ArrayList<String> llllllllllllllllIllIllIIIlIllIll = NativeLibrary.getLinuxLdPaths();
                for (int llllllllllllllllIllIllIIIlIlllII = llllllllllllllllIllIllIIIlIlIlIl.length - 1; 0 <= llllllllllllllllIllIllIIIlIlllII; --llllllllllllllllIllIllIIIlIlllII) {
                    int llllllllllllllllIllIllIIIlIlllIl = llllllllllllllllIllIllIIIlIllIll.indexOf(llllllllllllllllIllIllIIIlIlIlIl[llllllllllllllllIllIllIIIlIlllII]);
                    if (llllllllllllllllIllIllIIIlIlllIl != -1) {
                        llllllllllllllllIllIllIIIlIllIll.remove(llllllllllllllllIllIllIIIlIlllIl);
                    }
                    llllllllllllllllIllIllIIIlIllIll.add(0, llllllllllllllllIllIllIIIlIlIlIl[llllllllllllllllIllIllIIIlIlllII]);
                }
                llllllllllllllllIllIllIIIlIlIlIl = llllllllllllllllIllIllIIIlIllIll.toArray(new String[llllllllllllllllIllIllIIIlIllIll.size()]);
            }
            for (int llllllllllllllllIllIllIIIlIllIIl = 0; llllllllllllllllIllIllIIIlIllIIl < llllllllllllllllIllIllIIIlIlIlIl.length; ++llllllllllllllllIllIllIIIlIllIIl) {
                File llllllllllllllllIllIllIIIlIllIlI = new File(llllllllllllllllIllIllIIIlIlIlIl[llllllllllllllllIllIllIIIlIllIIl]);
                if (!llllllllllllllllIllIllIIIlIllIlI.exists() || !llllllllllllllllIllIllIIIlIllIlI.isDirectory()) continue;
                llllllllllllllllIllIllIIIlIllIII = String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIIIlIllIII).append(llllllllllllllllIllIllIIIlIlIlll).append(llllllllllllllllIllIllIIIlIlIlIl[llllllllllllllllIllIllIIIlIllIIl]));
                llllllllllllllllIllIllIIIlIlIlll = File.pathSeparator;
            }
            if (!"".equals(llllllllllllllllIllIllIIIlIllIII)) {
                System.setProperty("jna.platform.library.path", llllllllllllllllIllIllIIIlIllIII);
            }
        }
        librarySearchPath.addAll(NativeLibrary.initPaths("jna.platform.library.path"));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void dispose() {
        NativeLibrary llllllllllllllllIllIllIIllllllIl;
        HashSet<String> llllllllllllllllIllIllIIllllllII = new HashSet<String>();
        Object llllllllllllllllIllIllIIlllllIIl = libraries;
        synchronized (llllllllllllllllIllIllIIlllllIIl) {
            for (Map.Entry<String, Reference<NativeLibrary>> llllllllllllllllIllIllIIllllllll : libraries.entrySet()) {
                Reference<NativeLibrary> llllllllllllllllIllIllIlIIIIIIII = llllllllllllllllIllIllIIllllllll.getValue();
                if (llllllllllllllllIllIllIlIIIIIIII.get() != llllllllllllllllIllIllIIllllllIl) continue;
                llllllllllllllllIllIllIIllllllII.add(llllllllllllllllIllIllIIllllllll.getKey());
            }
            for (String llllllllllllllllIllIllIIlllllllI : llllllllllllllllIllIllIIllllllII) {
                libraries.remove(llllllllllllllllIllIllIIlllllllI);
            }
        }
        llllllllllllllllIllIllIIlllllIIl = llllllllllllllllIllIllIIllllllIl;
        synchronized (llllllllllllllllIllIllIIlllllIIl) {
            if (llllllllllllllllIllIllIIllllllIl.handle != 0L) {
                Native.close(llllllllllllllllIllIllIIllllllIl.handle);
                llllllllllllllllIllIllIIllllllIl.handle = 0L;
            }
        }
    }

    public File getFile() {
        NativeLibrary llllllllllllllllIllIllIlIIIllIIl;
        if (llllllllllllllllIllIllIlIIIllIIl.libraryPath == null) {
            return null;
        }
        return new File(llllllllllllllllIllIllIlIIIllIIl.libraryPath);
    }

    private static List<String> initPaths(String llllllllllllllllIllIllIIlllIlIIl) {
        String llllllllllllllllIllIllIIlllIllII = System.getProperty(llllllllllllllllIllIllIIlllIlIIl, "");
        if ("".equals(llllllllllllllllIllIllIIlllIllII)) {
            return Collections.emptyList();
        }
        StringTokenizer llllllllllllllllIllIllIIlllIlIll = new StringTokenizer(llllllllllllllllIllIllIIlllIllII, File.pathSeparator);
        ArrayList<String> llllllllllllllllIllIllIIlllIlIlI = new ArrayList<String>();
        while (llllllllllllllllIllIllIIlllIlIll.hasMoreTokens()) {
            String llllllllllllllllIllIllIIlllIlllI = llllllllllllllllIllIllIIlllIlIll.nextToken();
            if ("".equals(llllllllllllllllIllIllIIlllIlllI)) continue;
            llllllllllllllllIllIllIIlllIlIlI.add(llllllllllllllllIllIllIIlllIlllI);
        }
        return llllllllllllllllIllIllIIlllIlIlI;
    }

    private static ArrayList<String> getLinuxLdPaths() {
        ArrayList<String> llllllllllllllllIllIllIIIlllIIIl = new ArrayList<String>();
        try {
            Process llllllllllllllllIllIllIIIlllIlII = Runtime.getRuntime().exec("/sbin/ldconfig -p");
            BufferedReader llllllllllllllllIllIllIIIlllIIll = new BufferedReader(new InputStreamReader(llllllllllllllllIllIllIIIlllIlII.getInputStream()));
            String llllllllllllllllIllIllIIIlllIIlI = "";
            while ((llllllllllllllllIllIllIIIlllIIlI = llllllllllllllllIllIllIIIlllIIll.readLine()) != null) {
                String llllllllllllllllIllIllIIIlllIlll;
                int llllllllllllllllIllIllIIIlllIllI = llllllllllllllllIllIllIIIlllIIlI.indexOf(" => ");
                int llllllllllllllllIllIllIIIlllIlIl = llllllllllllllllIllIllIIIlllIIlI.lastIndexOf(47);
                if (llllllllllllllllIllIllIIIlllIllI == -1 || llllllllllllllllIllIllIIIlllIlIl == -1 || llllllllllllllllIllIllIIIlllIllI >= llllllllllllllllIllIllIIIlllIlIl || llllllllllllllllIllIllIIIlllIIIl.contains(llllllllllllllllIllIllIIIlllIlll = llllllllllllllllIllIllIIIlllIIlI.substring(llllllllllllllllIllIllIIIlllIllI + 4, llllllllllllllllIllIllIIIlllIlIl))) continue;
                llllllllllllllllIllIllIIIlllIIIl.add(llllllllllllllllIllIllIIIlllIlll);
            }
            llllllllllllllllIllIllIIIlllIIll.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
        return llllllllllllllllIllIllIIIlllIIIl;
    }

    private static String getMultiArchPath() {
        String llllllllllllllllIllIllIIlIIIIlII = Platform.ARCH;
        String llllllllllllllllIllIllIIlIIIIIll = Platform.iskFreeBSD() ? "-kfreebsd" : (Platform.isGNU() ? "" : "-linux");
        String llllllllllllllllIllIllIIlIIIIIlI = "-gnu";
        if (Platform.isIntel()) {
            llllllllllllllllIllIllIIlIIIIlII = Platform.is64Bit() ? "x86_64" : "i386";
        } else if (Platform.isPPC()) {
            llllllllllllllllIllIllIIlIIIIlII = Platform.is64Bit() ? "powerpc64" : "powerpc";
        } else if (Platform.isARM()) {
            llllllllllllllllIllIllIIlIIIIlII = "arm";
            llllllllllllllllIllIllIIlIIIIIlI = "-gnueabi";
        }
        return String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIIlIIIIlII).append(llllllllllllllllIllIllIIlIIIIIll).append(llllllllllllllllIllIllIIlIIIIIlI));
    }

    static double parseVersion(String llllllllllllllllIllIllIIlIIlIIIl) {
        double llllllllllllllllIllIllIIlIIlIIII = 0.0;
        double llllllllllllllllIllIllIIlIIIllll = 1.0;
        int llllllllllllllllIllIllIIlIIIlllI = llllllllllllllllIllIllIIlIIlIIIl.indexOf(".");
        while (llllllllllllllllIllIllIIlIIlIIIl != null) {
            String llllllllllllllllIllIllIIlIIlIIlI;
            if (llllllllllllllllIllIllIIlIIIlllI != -1) {
                String llllllllllllllllIllIllIIlIIlIlII = llllllllllllllllIllIllIIlIIlIIIl.substring(0, llllllllllllllllIllIllIIlIIIlllI);
                llllllllllllllllIllIllIIlIIlIIIl = llllllllllllllllIllIllIIlIIlIIIl.substring(llllllllllllllllIllIllIIlIIIlllI + 1);
                llllllllllllllllIllIllIIlIIIlllI = llllllllllllllllIllIllIIlIIlIIIl.indexOf(".");
            } else {
                llllllllllllllllIllIllIIlIIlIIlI = llllllllllllllllIllIllIIlIIlIIIl;
                llllllllllllllllIllIllIIlIIlIIIl = null;
            }
            try {
                llllllllllllllllIllIllIIlIIlIIII += (double)Integer.parseInt(llllllllllllllllIllIllIIlIIlIIlI) / llllllllllllllllIllIllIIlIIIllll;
            }
            catch (NumberFormatException llllllllllllllllIllIllIIlIIlIIll) {
                return 0.0;
            }
            llllllllllllllllIllIllIIlIIIllll *= 100.0;
        }
        return llllllllllllllllIllIllIIlIIlIIII;
    }

    static String matchLibrary(final String llllllllllllllllIllIllIIlIlIIlll, List<String> llllllllllllllllIllIllIIlIlIIllI) {
        File llllllllllllllllIllIllIIlIlIllII = new File(llllllllllllllllIllIllIIlIlIIlll);
        if (llllllllllllllllIllIllIIlIlIllII.isAbsolute()) {
            llllllllllllllllIllIllIIlIlIIllI = Arrays.asList(llllllllllllllllIllIllIIlIlIllII.getParent());
        }
        FilenameFilter llllllllllllllllIllIllIIlIlIlIll = new FilenameFilter(){
            {
                2 lllIIlllIIlllIl;
            }

            @Override
            public boolean accept(File lllIIlllIIlIlll, String lllIIlllIIlIllI) {
                2 lllIIlllIIllIII;
                return (lllIIlllIIlIllI.startsWith(String.valueOf(new StringBuilder().append("lib").append(lllIIlllIIllIII.llllllllllllllllIllIllIIlIlIIlll).append(".so"))) || lllIIlllIIlIllI.startsWith(String.valueOf(new StringBuilder().append(lllIIlllIIllIII.llllllllllllllllIllIllIIlIlIIlll).append(".so"))) && lllIIlllIIllIII.llllllllllllllllIllIllIIlIlIIlll.startsWith("lib")) && NativeLibrary.isVersionedName(lllIIlllIIlIllI);
            }
        };
        LinkedList<File> llllllllllllllllIllIllIIlIlIlIlI = new LinkedList<File>();
        for (String llllllllllllllllIllIllIIlIllIIll : llllllllllllllllIllIllIIlIlIIllI) {
            File[] llllllllllllllllIllIllIIlIllIlII = new File(llllllllllllllllIllIllIIlIllIIll).listFiles(llllllllllllllllIllIllIIlIlIlIll);
            if (llllllllllllllllIllIllIIlIllIlII == null || llllllllllllllllIllIllIIlIllIlII.length <= 0) continue;
            llllllllllllllllIllIllIIlIlIlIlI.addAll(Arrays.asList(llllllllllllllllIllIllIIlIllIlII));
        }
        double llllllllllllllllIllIllIIlIlIlIIl = -1.0;
        String llllllllllllllllIllIllIIlIlIlIII = null;
        for (File llllllllllllllllIllIllIIlIlIllll : llllllllllllllllIllIllIIlIlIlIlI) {
            String llllllllllllllllIllIllIIlIllIIlI = llllllllllllllllIllIllIIlIlIllll.getAbsolutePath();
            String llllllllllllllllIllIllIIlIllIIIl = llllllllllllllllIllIllIIlIllIIlI.substring(llllllllllllllllIllIllIIlIllIIlI.lastIndexOf(".so.") + 4);
            double llllllllllllllllIllIllIIlIllIIII = NativeLibrary.parseVersion(llllllllllllllllIllIllIIlIllIIIl);
            if (!(llllllllllllllllIllIllIIlIllIIII > llllllllllllllllIllIllIIlIlIlIIl)) continue;
            llllllllllllllllIllIllIIlIlIlIIl = llllllllllllllllIllIllIIlIllIIII;
            llllllllllllllllIllIllIIlIlIlIII = llllllllllllllllIllIllIIlIllIIlI;
        }
        return llllllllllllllllIllIllIIlIlIlIII;
    }

    private static int openFlags(Map<String, ?> llllllllllllllllIllIllIlllllIIll) {
        Object llllllllllllllllIllIllIlllllIIlI = llllllllllllllllIllIllIlllllIIll.get("open-flags");
        if (llllllllllllllllIllIllIlllllIIlI instanceof Number) {
            return ((Number)llllllllllllllllIllIllIlllllIIlI).intValue();
        }
        return -1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static final NativeLibrary getInstance(String llllllllllllllllIllIllIllIIIlIlI, Map<String, ?> llllllllllllllllIllIllIllIIIlIIl) {
        HashMap llllllllllllllllIllIllIllIIIlIII = new HashMap(llllllllllllllllIllIllIllIIIlIIl);
        if (llllllllllllllllIllIllIllIIIlIII.get("calling-convention") == null) {
            llllllllllllllllIllIllIllIIIlIII.put("calling-convention", 0);
        }
        if ((Platform.isLinux() || Platform.isFreeBSD() || Platform.isAIX()) && Platform.C_LIBRARY_NAME.equals(llllllllllllllllIllIllIllIIIlIlI)) {
            llllllllllllllllIllIllIllIIIlIlI = null;
        }
        Map<String, Reference<NativeLibrary>> llllllllllllllllIllIllIllIIIIlII = libraries;
        synchronized (llllllllllllllllIllIllIllIIIIlII) {
            NativeLibrary llllllllllllllllIllIllIllIIIlIll;
            Reference<NativeLibrary> llllllllllllllllIllIllIllIIIllII = libraries.get(String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIllIIIlIlI).append(llllllllllllllllIllIllIllIIIlIII)));
            NativeLibrary nativeLibrary = llllllllllllllllIllIllIllIIIlIll = llllllllllllllllIllIllIllIIIllII != null ? llllllllllllllllIllIllIllIIIllII.get() : null;
            if (llllllllllllllllIllIllIllIIIlIll == null) {
                llllllllllllllllIllIllIllIIIlIll = llllllllllllllllIllIllIllIIIlIlI == null ? new NativeLibrary("<process>", null, Native.open(null, NativeLibrary.openFlags(llllllllllllllllIllIllIllIIIlIII)), llllllllllllllllIllIllIllIIIlIII) : NativeLibrary.loadLibrary(llllllllllllllllIllIllIllIIIlIlI, llllllllllllllllIllIllIllIIIlIII);
                llllllllllllllllIllIllIllIIIllII = new WeakReference<NativeLibrary>(llllllllllllllllIllIllIllIIIlIll);
                libraries.put(String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIllIIIlIll.getName()).append(llllllllllllllllIllIllIllIIIlIII)), llllllllllllllllIllIllIllIIIllII);
                File llllllllllllllllIllIllIllIIIllIl = llllllllllllllllIllIllIllIIIlIll.getFile();
                if (llllllllllllllllIllIllIllIIIllIl != null) {
                    libraries.put(String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIllIIIllIl.getAbsolutePath()).append(llllllllllllllllIllIllIllIIIlIII)), llllllllllllllllIllIllIllIIIllII);
                    libraries.put(String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIllIIIllIl.getName()).append(llllllllllllllllIllIllIllIIIlIII)), llllllllllllllllIllIllIllIIIllII);
                }
            }
            return llllllllllllllllIllIllIllIIIlIll;
        }
    }

    Function getFunction(String llllllllllllllllIllIllIlIlIlllll, Method llllllllllllllllIllIllIlIlIllllI) {
        String llllllllllllllllIllIllIlIlIlllII;
        NativeLibrary llllllllllllllllIllIllIlIllIIIII;
        FunctionMapper llllllllllllllllIllIllIlIlIlllIl = (FunctionMapper)llllllllllllllllIllIllIlIllIIIII.options.get("function-mapper");
        if (llllllllllllllllIllIllIlIlIlllIl != null) {
            llllllllllllllllIllIllIlIlIlllll = llllllllllllllllIllIllIlIlIlllIl.getFunctionName(llllllllllllllllIllIllIlIllIIIII, llllllllllllllllIllIllIlIlIllllI);
        }
        if (llllllllllllllllIllIllIlIlIlllll.startsWith(llllllllllllllllIllIllIlIlIlllII = System.getProperty("jna.profiler.prefix", "$$YJP$$"))) {
            llllllllllllllllIllIllIlIlIlllll = llllllllllllllllIllIllIlIlIlllll.substring(llllllllllllllllIllIllIlIlIlllII.length());
        }
        int llllllllllllllllIllIllIlIlIllIll = llllllllllllllllIllIllIlIllIIIII.callFlags;
        Class<?>[] llllllllllllllllIllIllIlIlIllIlI = llllllllllllllllIllIllIlIlIllllI.getExceptionTypes();
        for (int llllllllllllllllIllIllIlIllIIIIl = 0; llllllllllllllllIllIllIlIllIIIIl < llllllllllllllllIllIllIlIlIllIlI.length; ++llllllllllllllllIllIllIlIllIIIIl) {
            if (!LastErrorException.class.isAssignableFrom(llllllllllllllllIllIllIlIlIllIlI[llllllllllllllllIllIllIlIllIIIIl])) continue;
            llllllllllllllllIllIllIlIlIllIll |= 0x40;
        }
        return llllllllllllllllIllIllIlIllIIIII.getFunction(llllllllllllllllIllIllIlIlIlllll, llllllllllllllllIllIllIlIlIllIll);
    }

    private String getLibraryName(String llllllllllllllllIllIllIllIlIllII) {
        String llllllllllllllllIllIllIllIlIIlll;
        int llllllllllllllllIllIllIllIlIIllI;
        String llllllllllllllllIllIllIllIlIlIll = llllllllllllllllIllIllIllIlIllII;
        String llllllllllllllllIllIllIllIlIlIlI = "---";
        String llllllllllllllllIllIllIllIlIlIIl = NativeLibrary.mapSharedLibraryName("---");
        int llllllllllllllllIllIllIllIlIlIII = llllllllllllllllIllIllIllIlIlIIl.indexOf("---");
        if (llllllllllllllllIllIllIllIlIlIII > 0 && llllllllllllllllIllIllIllIlIlIll.startsWith(llllllllllllllllIllIllIllIlIlIIl.substring(0, llllllllllllllllIllIllIllIlIlIII))) {
            llllllllllllllllIllIllIllIlIlIll = llllllllllllllllIllIllIllIlIlIll.substring(llllllllllllllllIllIllIllIlIlIII);
        }
        if ((llllllllllllllllIllIllIllIlIIllI = llllllllllllllllIllIllIllIlIlIll.indexOf(llllllllllllllllIllIllIllIlIIlll = llllllllllllllllIllIllIllIlIlIIl.substring(llllllllllllllllIllIllIllIlIlIII + "---".length()))) != -1) {
            llllllllllllllllIllIllIllIlIlIll = llllllllllllllllIllIllIllIlIlIll.substring(0, llllllllllllllllIllIllIllIlIIllI);
        }
        return llllllllllllllllIllIllIllIlIlIll;
    }

    static String matchFramework(String llllllllllllllllIllIllIllIlllIlI) {
        File llllllllllllllllIllIllIllIlllIll = new File(llllllllllllllllIllIllIllIlllIlI);
        if (llllllllllllllllIllIllIllIlllIll.isAbsolute()) {
            if (llllllllllllllllIllIllIllIlllIlI.indexOf(".framework") != -1 && llllllllllllllllIllIllIllIlllIll.exists()) {
                return llllllllllllllllIllIllIllIlllIll.getAbsolutePath();
            }
            if ((llllllllllllllllIllIllIllIlllIll = new File(new File(llllllllllllllllIllIllIllIlllIll.getParentFile(), String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIllIlllIll.getName()).append(".framework"))), llllllllllllllllIllIllIllIlllIll.getName())).exists()) {
                return llllllllllllllllIllIllIllIlllIll.getAbsolutePath();
            }
        } else {
            String[] llllllllllllllllIllIllIllIlllllI = new String[]{System.getProperty("user.home"), "", "/System"};
            String llllllllllllllllIllIllIllIllllIl = llllllllllllllllIllIllIllIlllIlI.indexOf(".framework") == -1 ? String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIllIlllIlI).append(".framework/").append(llllllllllllllllIllIllIllIlllIlI)) : llllllllllllllllIllIllIllIlllIlI;
            for (int llllllllllllllllIllIllIllIllllll = 0; llllllllllllllllIllIllIllIllllll < llllllllllllllllIllIllIllIlllllI.length; ++llllllllllllllllIllIllIllIllllll) {
                String llllllllllllllllIllIllIlllIIIIII = String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIllIlllllI[llllllllllllllllIllIllIllIllllll]).append("/Library/Frameworks/").append(llllllllllllllllIllIllIllIllllIl));
                if (!new File(llllllllllllllllIllIllIlllIIIIII).exists()) continue;
                return llllllllllllllllIllIllIlllIIIIII;
            }
        }
        return null;
    }

    public Map<String, ?> getOptions() {
        NativeLibrary llllllllllllllllIllIllIlIIllIIIl;
        return llllllllllllllllIllIllIlIIllIIIl.options;
    }

    private static String findLibraryPath(String llllllllllllllllIllIllIIllIllIIl, List<String> llllllllllllllllIllIllIIllIllIll) {
        if (new File(llllllllllllllllIllIllIIllIllIIl).isAbsolute()) {
            return llllllllllllllllIllIllIIllIllIIl;
        }
        String llllllllllllllllIllIllIIllIllIlI = NativeLibrary.mapSharedLibraryName(llllllllllllllllIllIllIIllIllIIl);
        for (String llllllllllllllllIllIllIIllIlllIl : llllllllllllllllIllIllIIllIllIll) {
            File llllllllllllllllIllIllIIllIllllI = new File(llllllllllllllllIllIllIIllIlllIl, llllllllllllllllIllIllIIllIllIlI);
            if (llllllllllllllllIllIllIIllIllllI.exists()) {
                return llllllllllllllllIllIllIIllIllllI.getAbsolutePath();
            }
            if (!Platform.isMac() || !llllllllllllllllIllIllIIllIllIlI.endsWith(".dylib") || !(llllllllllllllllIllIllIIllIllllI = new File(llllllllllllllllIllIllIIllIlllIl, String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIIllIllIlI.substring(0, llllllllllllllllIllIllIIllIllIlI.lastIndexOf(".dylib"))).append(".jnilib")))).exists()) continue;
            return llllllllllllllllIllIllIIllIllllI.getAbsolutePath();
        }
        return llllllllllllllllIllIllIIllIllIlI;
    }

    public Function getFunction(String llllllllllllllllIllIllIlIlIIlIlI, int llllllllllllllllIllIllIlIlIIllII) {
        NativeLibrary llllllllllllllllIllIllIlIlIIlIll;
        return llllllllllllllllIllIllIlIlIIlIll.getFunction(llllllllllllllllIllIllIlIlIIlIlI, llllllllllllllllIllIllIlIlIIllII, llllllllllllllllIllIllIlIlIIlIll.encoding);
    }

    long getSymbolAddress(String llllllllllllllllIllIllIlIIlIIIIl) {
        NativeLibrary llllllllllllllllIllIllIlIIlIIlII;
        if (llllllllllllllllIllIllIlIIlIIlII.handle == 0L) {
            throw new UnsatisfiedLinkError("Library has been unloaded");
        }
        return Native.findSymbol(llllllllllllllllIllIllIlIIlIIlII.handle, llllllllllllllllIllIllIlIIlIIIIl);
    }

    protected void finalize() {
        NativeLibrary llllllllllllllllIllIllIlIIIlIlIl;
        llllllllllllllllIllIllIlIIIlIlIl.dispose();
    }

    public String toString() {
        NativeLibrary llllllllllllllllIllIllIlIIIlllll;
        return String.valueOf(new StringBuilder().append("Native Library <").append(llllllllllllllllIllIllIlIIIlllll.libraryPath).append("@").append(llllllllllllllllIllIllIlIIIlllll.handle).append(">"));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private NativeLibrary(String llllllllllllllllIllIllIllllllllI, String llllllllllllllllIllIlllIIIIIIlII, long llllllllllllllllIllIllIlllllllII, Map<String, ?> llllllllllllllllIllIllIllllllIll) {
        int llllllllllllllllIllIlllIIIIIIIII;
        NativeLibrary llllllllllllllllIllIlllIIIIIIllI;
        llllllllllllllllIllIlllIIIIIIllI.functions = new HashMap<String, Function>();
        llllllllllllllllIllIlllIIIIIIllI.libraryName = llllllllllllllllIllIlllIIIIIIllI.getLibraryName(llllllllllllllllIllIllIllllllllI);
        llllllllllllllllIllIlllIIIIIIllI.libraryPath = llllllllllllllllIllIlllIIIIIIlII;
        llllllllllllllllIllIlllIIIIIIllI.handle = llllllllllllllllIllIllIlllllllII;
        Object llllllllllllllllIllIlllIIIIIIIIl = llllllllllllllllIllIllIllllllIll.get("calling-convention");
        llllllllllllllllIllIlllIIIIIIllI.callFlags = llllllllllllllllIllIlllIIIIIIIII = llllllllllllllllIllIlllIIIIIIIIl instanceof Number ? ((Number)llllllllllllllllIllIlllIIIIIIIIl).intValue() : 0;
        llllllllllllllllIllIlllIIIIIIllI.options = llllllllllllllllIllIllIllllllIll;
        llllllllllllllllIllIlllIIIIIIllI.encoding = (String)llllllllllllllllIllIllIllllllIll.get("string-encoding");
        if (llllllllllllllllIllIlllIIIIIIllI.encoding == null) {
            llllllllllllllllIllIlllIIIIIIllI.encoding = Native.getDefaultStringEncoding();
        }
        if (Platform.isWindows() && "kernel32".equals(llllllllllllllllIllIlllIIIIIIllI.libraryName.toLowerCase())) {
            Map<String, Function> llllllllllllllllIllIllIllllllIII = llllllllllllllllIllIlllIIIIIIllI.functions;
            synchronized (llllllllllllllllIllIllIllllllIII) {
                Function llllllllllllllllIllIlllIIIIIIlll = new Function(llllllllllllllllIllIlllIIIIIIllI, "GetLastError", 63, llllllllllllllllIllIlllIIIIIIllI.encoding){
                    {
                        1 llllllllllllllllllllllIlIlIllIll;
                        super(llllllllllllllllllllllIlIlIllIIl, llllllllllllllllllllllIlIlIllIII, llllllllllllllllllllllIlIlIlllIl, llllllllllllllllllllllIlIlIlllII);
                    }

                    @Override
                    Object invoke(Method llllllllllllllllllllllIlIlIIllll, Class<?>[] llllllllllllllllllllllIlIlIIlllI, Class<?> llllllllllllllllllllllIlIlIIllIl, Object[] llllllllllllllllllllllIlIlIIllII, Map<String, ?> llllllllllllllllllllllIlIlIIlIll) {
                        return Native.getLastError();
                    }

                    @Override
                    Object invoke(Object[] llllllllllllllllllllllIlIlIlIlII, Class<?> llllllllllllllllllllllIlIlIlIIll, boolean llllllllllllllllllllllIlIlIlIIlI, int llllllllllllllllllllllIlIlIlIIIl) {
                        return Native.getLastError();
                    }
                };
                llllllllllllllllIllIlllIIIIIIllI.functions.put(NativeLibrary.functionKey("GetLastError", llllllllllllllllIllIlllIIIIIIllI.callFlags, llllllllllllllllIllIlllIIIIIIllI.encoding), llllllllllllllllIllIlllIIIIIIlll);
            }
        }
    }

    public String getName() {
        NativeLibrary llllllllllllllllIllIllIlIIIllIll;
        return llllllllllllllllIllIllIlIIIllIll.libraryName;
    }

    public static final synchronized NativeLibrary getProcess(Map<String, ?> llllllllllllllllIllIllIlIlllllIl) {
        return NativeLibrary.getInstance(null, llllllllllllllllIllIllIlIlllllIl);
    }

    public static final synchronized NativeLibrary getProcess() {
        return NativeLibrary.getInstance(null);
    }

    static String mapSharedLibraryName(String llllllllllllllllIllIllIIllIlIIII) {
        if (Platform.isMac()) {
            if (llllllllllllllllIllIllIIllIlIIII.startsWith("lib") && (llllllllllllllllIllIllIIllIlIIII.endsWith(".dylib") || llllllllllllllllIllIllIIllIlIIII.endsWith(".jnilib"))) {
                return llllllllllllllllIllIllIIllIlIIII;
            }
            String llllllllllllllllIllIllIIllIlIIIl = System.mapLibraryName(llllllllllllllllIllIllIIllIlIIII);
            if (llllllllllllllllIllIllIIllIlIIIl.endsWith(".jnilib")) {
                return String.valueOf(new StringBuilder().append(llllllllllllllllIllIllIIllIlIIIl.substring(0, llllllllllllllllIllIllIIllIlIIIl.lastIndexOf(".jnilib"))).append(".dylib"));
            }
            return llllllllllllllllIllIllIIllIlIIIl;
        }
        if (Platform.isLinux() || Platform.isFreeBSD() ? NativeLibrary.isVersionedName(llllllllllllllllIllIllIIllIlIIII) || llllllllllllllllIllIllIIllIlIIII.endsWith(".so") : (Platform.isAIX() ? llllllllllllllllIllIllIIllIlIIII.startsWith("lib") : Platform.isWindows() && (llllllllllllllllIllIllIIllIlIIII.endsWith(".drv") || llllllllllllllllIllIllIIllIlIIII.endsWith(".dll")))) {
            return llllllllllllllllIllIllIIllIlIIII;
        }
        return System.mapLibraryName(llllllllllllllllIllIllIIllIlIIII);
    }
}

