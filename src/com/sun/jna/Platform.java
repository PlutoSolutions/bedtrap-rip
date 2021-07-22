/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.ELFAnalyser;
import com.sun.jna.Native;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Platform {
    public static final /* synthetic */ boolean HAS_DLL_CALLBACKS;
    public static final /* synthetic */ int KFREEBSD;
    public static final /* synthetic */ int GNU;
    public static final /* synthetic */ int AIX;
    public static final /* synthetic */ int MAC;
    public static final /* synthetic */ int SOLARIS;
    public static final /* synthetic */ boolean HAS_AWT;
    public static final /* synthetic */ String C_LIBRARY_NAME;
    public static final /* synthetic */ int FREEBSD;
    public static final /* synthetic */ int WINDOWSCE;
    public static final /* synthetic */ boolean RO_FIELDS;
    public static final /* synthetic */ int NETBSD;
    public static final /* synthetic */ String MATH_LIBRARY_NAME;
    public static final /* synthetic */ int UNSPECIFIED;
    public static final /* synthetic */ int WINDOWS;
    public static final /* synthetic */ String ARCH;
    public static final /* synthetic */ int ANDROID;
    public static final /* synthetic */ int LINUX;
    public static final /* synthetic */ boolean HAS_JAWT;
    private static final /* synthetic */ int osType;
    public static final /* synthetic */ boolean HAS_BUFFERS;
    public static final /* synthetic */ String RESOURCE_PREFIX;
    public static final /* synthetic */ int OPENBSD;

    public static final boolean isGNU() {
        return osType == 9;
    }

    public static final boolean isX11() {
        return !Platform.isWindows() && !Platform.isMac();
    }

    public static final boolean isLinux() {
        return osType == 1;
    }

    public static final boolean isAIX() {
        return osType == 7;
    }

    private Platform() {
        Platform lllllllllllllllllIIlIIIIIlIIIIIl;
    }

    public static final boolean isARM() {
        return ARCH.startsWith("arm");
    }

    public static final boolean isNetBSD() {
        return osType == 11;
    }

    public static final int getOSType() {
        return osType;
    }

    public static final boolean isOpenBSD() {
        return osType == 5;
    }

    public static final boolean hasRuntimeExec() {
        return !Platform.isWindowsCE() || !"J9".equals(System.getProperty("java.vm.name"));
    }

    public static final boolean isSPARC() {
        return ARCH.startsWith("sparc");
    }

    public static final boolean isIntel() {
        return ARCH.startsWith("x86");
    }

    static {
        KFREEBSD = 10;
        LINUX = 1;
        SOLARIS = 3;
        NETBSD = 11;
        WINDOWS = 2;
        ANDROID = 8;
        OPENBSD = 5;
        WINDOWSCE = 6;
        FREEBSD = 4;
        UNSPECIFIED = -1;
        MAC = 0;
        AIX = 7;
        GNU = 9;
        String lllllllllllllllllIIlIIIIIIIIIlIl = System.getProperty("os.name");
        if (lllllllllllllllllIIlIIIIIIIIIlIl.startsWith("Linux")) {
            if ("dalvik".equals(System.getProperty("java.vm.name").toLowerCase())) {
                osType = 8;
                System.setProperty("jna.nounpack", "true");
            } else {
                osType = 1;
            }
        } else {
            osType = lllllllllllllllllIIlIIIIIIIIIlIl.startsWith("AIX") ? 7 : (lllllllllllllllllIIlIIIIIIIIIlIl.startsWith("Mac") || lllllllllllllllllIIlIIIIIIIIIlIl.startsWith("Darwin") ? 0 : (lllllllllllllllllIIlIIIIIIIIIlIl.startsWith("Windows CE") ? 6 : (lllllllllllllllllIIlIIIIIIIIIlIl.startsWith("Windows") ? 2 : (lllllllllllllllllIIlIIIIIIIIIlIl.startsWith("Solaris") || lllllllllllllllllIIlIIIIIIIIIlIl.startsWith("SunOS") ? 3 : (lllllllllllllllllIIlIIIIIIIIIlIl.startsWith("FreeBSD") ? 4 : (lllllllllllllllllIIlIIIIIIIIIlIl.startsWith("OpenBSD") ? 5 : (lllllllllllllllllIIlIIIIIIIIIlIl.equalsIgnoreCase("gnu") ? 9 : (lllllllllllllllllIIlIIIIIIIIIlIl.equalsIgnoreCase("gnu/kfreebsd") ? 10 : (lllllllllllllllllIIlIIIIIIIIIlIl.equalsIgnoreCase("netbsd") ? 11 : -1)))))))));
        }
        boolean lllllllllllllllllIIlIIIIIIIIIlII = false;
        try {
            Class.forName("java.nio.Buffer");
            lllllllllllllllllIIlIIIIIIIIIlII = true;
        }
        catch (ClassNotFoundException lllllllllllllllllIIlIIIIIIIIIIIl) {
            // empty catch block
        }
        HAS_AWT = osType != 6 && osType != 8 && osType != 7;
        HAS_JAWT = HAS_AWT && osType != 0;
        HAS_BUFFERS = lllllllllllllllllIIlIIIIIIIIIlII;
        boolean bl = RO_FIELDS = osType != 6;
        String string = osType == 2 ? "msvcrt" : (C_LIBRARY_NAME = osType == 6 ? "coredll" : "c");
        MATH_LIBRARY_NAME = osType == 2 ? "msvcrt" : (osType == 6 ? "coredll" : "m");
        HAS_DLL_CALLBACKS = osType == 2;
        ARCH = Platform.getCanonicalArchitecture(System.getProperty("os.arch"), Platform.isSoftFloat());
        RESOURCE_PREFIX = Platform.getNativeLibraryResourcePrefix();
    }

    public static final boolean isAix() {
        return Platform.isAIX();
    }

    public static final boolean isMac() {
        return osType == 0;
    }

    public static final boolean isAndroid() {
        return osType == 8;
    }

    public static final boolean isPPC() {
        return ARCH.startsWith("ppc");
    }

    public static final boolean isWindowsCE() {
        return osType == 6;
    }

    static String getCanonicalArchitecture(String lllllllllllllllllIIlIIIIIIlllIll, boolean lllllllllllllllllIIlIIIIIIlllIII) {
        if ("powerpc".equals(lllllllllllllllllIIlIIIIIIlllIll = lllllllllllllllllIIlIIIIIIlllIll.toLowerCase().trim())) {
            lllllllllllllllllIIlIIIIIIlllIll = "ppc";
        } else if ("powerpc64".equals(lllllllllllllllllIIlIIIIIIlllIll)) {
            lllllllllllllllllIIlIIIIIIlllIll = "ppc64";
        } else if ("i386".equals(lllllllllllllllllIIlIIIIIIlllIll) || "i686".equals(lllllllllllllllllIIlIIIIIIlllIll)) {
            lllllllllllllllllIIlIIIIIIlllIll = "x86";
        } else if ("x86_64".equals(lllllllllllllllllIIlIIIIIIlllIll) || "amd64".equals(lllllllllllllllllIIlIIIIIIlllIll)) {
            lllllllllllllllllIIlIIIIIIlllIll = "x86-64";
        }
        if ("ppc64".equals(lllllllllllllllllIIlIIIIIIlllIll) && "little".equals(System.getProperty("sun.cpu.endian"))) {
            lllllllllllllllllIIlIIIIIIlllIll = "ppc64le";
        }
        if ("arm".equals(lllllllllllllllllIIlIIIIIIlllIll) && lllllllllllllllllIIlIIIIIIlllIII) {
            lllllllllllllllllIIlIIIIIIlllIll = "armel";
        }
        return lllllllllllllllllIIlIIIIIIlllIll;
    }

    static String getNativeLibraryResourcePrefix(int lllllllllllllllllIIlIIIIIIlIIlll, String lllllllllllllllllIIlIIIIIIlIlIIl, String lllllllllllllllllIIlIIIIIIlIIlIl) {
        return Platform.getNativeLibraryResourcePrefix(lllllllllllllllllIIlIIIIIIlIIlll, lllllllllllllllllIIlIIIIIIlIlIIl, lllllllllllllllllIIlIIIIIIlIIlIl, Platform.isSoftFloat());
    }

    public static final boolean isWindows() {
        return osType == 2 || osType == 6;
    }

    public static final boolean is64Bit() {
        String lllllllllllllllllIIlIIIIIIllllll = System.getProperty("sun.arch.data.model", System.getProperty("com.ibm.vm.bitmode"));
        if (lllllllllllllllllIIlIIIIIIllllll != null) {
            return "64".equals(lllllllllllllllllIIlIIIIIIllllll);
        }
        if ("x86-64".equals(ARCH) || "ia64".equals(ARCH) || "ppc64".equals(ARCH) || "ppc64le".equals(ARCH) || "sparcv9".equals(ARCH) || "amd64".equals(ARCH)) {
            return true;
        }
        return Native.POINTER_SIZE == 8;
    }

    static String getNativeLibraryResourcePrefix() {
        String lllllllllllllllllIIlIIIIIIlIllll = System.getProperty("jna.prefix");
        if (lllllllllllllllllIIlIIIIIIlIllll != null) {
            return lllllllllllllllllIIlIIIIIIlIllll;
        }
        return Platform.getNativeLibraryResourcePrefix(Platform.getOSType(), System.getProperty("os.arch"), System.getProperty("os.name"));
    }

    static String getNativeLibraryResourcePrefix(int lllllllllllllllllIIlIIIIIIIIlllI, String lllllllllllllllllIIlIIIIIIIIllIl, String lllllllllllllllllIIlIIIIIIIIllII, boolean lllllllllllllllllIIlIIIIIIIlIIII) {
        String lllllllllllllllllIIlIIIIIIIIllll;
        lllllllllllllllllIIlIIIIIIIIllIl = Platform.getCanonicalArchitecture(lllllllllllllllllIIlIIIIIIIIllIl, lllllllllllllllllIIlIIIIIIIlIIII);
        switch (lllllllllllllllllIIlIIIIIIIIlllI) {
            case 8: {
                if (lllllllllllllllllIIlIIIIIIIIllIl.startsWith("arm")) {
                    lllllllllllllllllIIlIIIIIIIIllIl = "arm";
                }
                String lllllllllllllllllIIlIIIIIIIllllI = String.valueOf(new StringBuilder().append("android-").append(lllllllllllllllllIIlIIIIIIIIllIl));
                break;
            }
            case 2: {
                String lllllllllllllllllIIlIIIIIIIlllIl = String.valueOf(new StringBuilder().append("win32-").append(lllllllllllllllllIIlIIIIIIIIllIl));
                break;
            }
            case 6: {
                String lllllllllllllllllIIlIIIIIIIlllII = String.valueOf(new StringBuilder().append("w32ce-").append(lllllllllllllllllIIlIIIIIIIIllIl));
                break;
            }
            case 0: {
                String lllllllllllllllllIIlIIIIIIIllIll = "darwin";
                break;
            }
            case 1: {
                String lllllllllllllllllIIlIIIIIIIllIlI = String.valueOf(new StringBuilder().append("linux-").append(lllllllllllllllllIIlIIIIIIIIllIl));
                break;
            }
            case 3: {
                String lllllllllllllllllIIlIIIIIIIllIIl = String.valueOf(new StringBuilder().append("sunos-").append(lllllllllllllllllIIlIIIIIIIIllIl));
                break;
            }
            case 4: {
                String lllllllllllllllllIIlIIIIIIIllIII = String.valueOf(new StringBuilder().append("freebsd-").append(lllllllllllllllllIIlIIIIIIIIllIl));
                break;
            }
            case 5: {
                String lllllllllllllllllIIlIIIIIIIlIlll = String.valueOf(new StringBuilder().append("openbsd-").append(lllllllllllllllllIIlIIIIIIIIllIl));
                break;
            }
            case 11: {
                String lllllllllllllllllIIlIIIIIIIlIllI = String.valueOf(new StringBuilder().append("netbsd-").append(lllllllllllllllllIIlIIIIIIIIllIl));
                break;
            }
            case 10: {
                String lllllllllllllllllIIlIIIIIIIlIlIl = String.valueOf(new StringBuilder().append("kfreebsd-").append(lllllllllllllllllIIlIIIIIIIIllIl));
                break;
            }
            default: {
                lllllllllllllllllIIlIIIIIIIIllll = lllllllllllllllllIIlIIIIIIIIllII.toLowerCase();
                int lllllllllllllllllIIlIIIIIIIlIlII = lllllllllllllllllIIlIIIIIIIIllll.indexOf(" ");
                if (lllllllllllllllllIIlIIIIIIIlIlII != -1) {
                    lllllllllllllllllIIlIIIIIIIIllll = lllllllllllllllllIIlIIIIIIIIllll.substring(0, lllllllllllllllllIIlIIIIIIIlIlII);
                }
                lllllllllllllllllIIlIIIIIIIIllll = String.valueOf(new StringBuilder().append(lllllllllllllllllIIlIIIIIIIIllll).append("-").append(lllllllllllllllllIIlIIIIIIIIllIl));
            }
        }
        return lllllllllllllllllIIlIIIIIIIIllll;
    }

    private static boolean isSoftFloat() {
        try {
            File lllllllllllllllllIIlIIIIIIllIlIl = new File("/proc/self/exe");
            ELFAnalyser lllllllllllllllllIIlIIIIIIllIlII = ELFAnalyser.analyse(lllllllllllllllllIIlIIIIIIllIlIl.getCanonicalPath());
            return lllllllllllllllllIIlIIIIIIllIlII.isArmSoftFloat();
        }
        catch (IOException lllllllllllllllllIIlIIIIIIllIIll) {
            Logger.getLogger(Platform.class.getName()).log(Level.FINE, null, lllllllllllllllllIIlIIIIIIllIIll);
            return false;
        }
    }

    public static final boolean isFreeBSD() {
        return osType == 4;
    }

    public static final boolean iskFreeBSD() {
        return osType == 10;
    }

    public static final boolean isSolaris() {
        return osType == 3;
    }
}

