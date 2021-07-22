/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 */
package com.g00fy2.versioncompare;

import java.util.List;
import javax.annotation.Nonnull;

final class VersionComparator {
    private static final /* synthetic */ int UNKNOWN;
    static final /* synthetic */ int MINOR;
    private static final /* synthetic */ int RC;
    private static final /* synthetic */ int ALPHA;
    private static final /* synthetic */ String RC_STRING;
    private static final /* synthetic */ String ALPHA_STRING;
    static final /* synthetic */ int MAJOR;
    private static final /* synthetic */ String PRE_STRING;
    private static final /* synthetic */ String SNAPSHOT_STRING;
    private static final /* synthetic */ int BETA;
    private static final /* synthetic */ String BETA_STRING;
    static final /* synthetic */ int PATCH;
    private static final /* synthetic */ int PRE_ALPHA;
    private static final /* synthetic */ int SNAPSHOT;

    static {
        SNAPSHOT = 0;
        BETA = 3;
        MINOR = 1;
        BETA_STRING = "beta";
        PRE_ALPHA = 1;
        UNKNOWN = 5;
        SNAPSHOT_STRING = "snapshot";
        RC_STRING = "rc";
        ALPHA = 2;
        MAJOR = 0;
        PRE_STRING = "pre";
        ALPHA_STRING = "alpha";
        RC = 4;
        PATCH = 2;
    }

    private static boolean containsNumeric(@Nonnull CharSequence llllllllllllllllIlllIIIlllIlIllI) {
        int llllllllllllllllIlllIIIlllIlIlll = llllllllllllllllIlllIIIlllIlIllI.length();
        if (llllllllllllllllIlllIIIlllIlIlll > 0) {
            for (int llllllllllllllllIlllIIIlllIllIIl = 0; llllllllllllllllIlllIIIlllIllIIl < llllllllllllllllIlllIIIlllIlIlll; ++llllllllllllllllIlllIIIlllIllIIl) {
                if (!Character.isDigit(llllllllllllllllIlllIIIlllIlIllI.charAt(llllllllllllllllIlllIIIlllIllIIl))) continue;
                return true;
            }
        }
        return false;
    }

    static int compareSuffix(@Nonnull String llllllllllllllllIlllIIlIIIIlIIIl, @Nonnull String llllllllllllllllIlllIIlIIIIlIIII) {
        if (llllllllllllllllIlllIIlIIIIlIIIl.length() > 0 || llllllllllllllllIlllIIlIIIIlIIII.length() > 0) {
            int llllllllllllllllIlllIIlIIIIlIIlI;
            int llllllllllllllllIlllIIlIIIIlIIll = VersionComparator.qualifierToNumber(llllllllllllllllIlllIIlIIIIlIIIl);
            if (llllllllllllllllIlllIIlIIIIlIIll > (llllllllllllllllIlllIIlIIIIlIIlI = VersionComparator.qualifierToNumber(llllllllllllllllIlllIIlIIIIlIIII))) {
                return 1;
            }
            if (llllllllllllllllIlllIIlIIIIlIIll < llllllllllllllllIlllIIlIIIIlIIlI) {
                return -1;
            }
            if (llllllllllllllllIlllIIlIIIIlIIll != 5 && llllllllllllllllIlllIIlIIIIlIIll != 0) {
                int llllllllllllllllIlllIIlIIIIlIlII;
                int llllllllllllllllIlllIIlIIIIlIlIl = VersionComparator.preReleaseVersion(llllllllllllllllIlllIIlIIIIlIIIl, llllllllllllllllIlllIIlIIIIlIIll);
                if (llllllllllllllllIlllIIlIIIIlIlIl > (llllllllllllllllIlllIIlIIIIlIlII = VersionComparator.preReleaseVersion(llllllllllllllllIlllIIlIIIIlIIII, llllllllllllllllIlllIIlIIIIlIIlI))) {
                    return 1;
                }
                if (llllllllllllllllIlllIIlIIIIlIlIl < llllllllllllllllIlllIIlIIIIlIlII) {
                    return -1;
                }
            }
        }
        return 0;
    }

    static boolean isNumeric(@Nonnull CharSequence llllllllllllllllIlllIIIllllIIIIl) {
        int llllllllllllllllIlllIIIllllIIIII = llllllllllllllllIlllIIIllllIIIIl.length();
        if (llllllllllllllllIlllIIIllllIIIII > 0) {
            for (int llllllllllllllllIlllIIIllllIIIlI = 0; llllllllllllllllIlllIIIllllIIIlI < llllllllllllllllIlllIIIllllIIIII; ++llllllllllllllllIlllIIIllllIIIlI) {
                if (Character.isDigit(llllllllllllllllIlllIIIllllIIIIl.charAt(llllllllllllllllIlllIIIllllIIIlI))) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    static int safeParseInt(@Nonnull String llllllllllllllllIlllIIIllllIIlll) {
        if (llllllllllllllllIlllIIIllllIIlll.length() > 9) {
            llllllllllllllllIlllIIIllllIIlll = llllllllllllllllIlllIIIllllIIlll.substring(0, 9);
        }
        return Integer.parseInt(llllllllllllllllIlllIIIllllIIlll);
    }

    VersionComparator() {
        VersionComparator llllllllllllllllIlllIIlIIIlIllll;
    }

    static int preReleaseVersion(@Nonnull String llllllllllllllllIlllIIIllllllIII, int llllllllllllllllIlllIIIlllllIlll) {
        int llllllllllllllllIlllIIIlllllllII;
        int llllllllllllllllIlllIIIllllllIIl = VersionComparator.indexOfQualifier(llllllllllllllllIlllIIIllllllIII, llllllllllllllllIlllIIIlllllIlll);
        if (llllllllllllllllIlllIIIllllllIIl < llllllllllllllllIlllIIIllllllIII.length() && VersionComparator.containsNumeric(llllllllllllllllIlllIIIllllllIII.substring(llllllllllllllllIlllIIIllllllIIl, llllllllllllllllIlllIIIlllllllII = Math.min(llllllllllllllllIlllIIIllllllIIl + 2, llllllllllllllllIlllIIIllllllIII.length())))) {
            StringBuilder llllllllllllllllIlllIIIlllllllIl = new StringBuilder();
            for (int llllllllllllllllIlllIIIllllllllI = llllllllllllllllIlllIIIllllllIIl; llllllllllllllllIlllIIIllllllllI < llllllllllllllllIlllIIIllllllIII.length(); ++llllllllllllllllIlllIIIllllllllI) {
                char llllllllllllllllIlllIIIlllllllll = llllllllllllllllIlllIIIllllllIII.charAt(llllllllllllllllIlllIIIllllllllI);
                if (Character.isDigit(llllllllllllllllIlllIIIlllllllll)) {
                    llllllllllllllllIlllIIIlllllllIl.append(llllllllllllllllIlllIIIlllllllll);
                    continue;
                }
                if (llllllllllllllllIlllIIIllllllllI != llllllllllllllllIlllIIIllllllIIl) break;
            }
            return VersionComparator.safeParseInt(String.valueOf(llllllllllllllllIlllIIIlllllllIl));
        }
        return 0;
    }

    private static int indexOfQualifier(@Nonnull String llllllllllllllllIlllIIIllllIllIl, int llllllllllllllllIlllIIIllllIlllI) {
        if (llllllllllllllllIlllIIIllllIlllI == 4) {
            return llllllllllllllllIlllIIIllllIllIl.indexOf("rc") + "rc".length();
        }
        if (llllllllllllllllIlllIIIllllIlllI == 3) {
            return llllllllllllllllIlllIIIllllIllIl.indexOf("beta") + "beta".length();
        }
        if (llllllllllllllllIlllIIIllllIlllI == 2 || llllllllllllllllIlllIIIllllIlllI == 1) {
            return llllllllllllllllIlllIIIllllIllIl.indexOf("alpha") + "alpha".length();
        }
        return 0;
    }

    static boolean startsNumeric(@Nonnull String llllllllllllllllIlllIIIllllIlIIl) {
        return (llllllllllllllllIlllIIIllllIlIIl = llllllllllllllllIlllIIIllllIlIIl.trim()).length() > 0 && Character.isDigit(llllllllllllllllIlllIIIllllIlIIl.charAt(0));
    }

    static int compareSubversionNumbers(@Nonnull List<Integer> llllllllllllllllIlllIIlIIIlIIIIl, @Nonnull List<Integer> llllllllllllllllIlllIIlIIIlIIIII) {
        int llllllllllllllllIlllIIlIIIlIIlII = llllllllllllllllIlllIIlIIIlIIIIl.size();
        int llllllllllllllllIlllIIlIIIlIIIll = llllllllllllllllIlllIIlIIIlIIIII.size();
        int llllllllllllllllIlllIIlIIIlIIIlI = Math.max(llllllllllllllllIlllIIlIIIlIIlII, llllllllllllllllIlllIIlIIIlIIIll);
        for (int llllllllllllllllIlllIIlIIIlIIlll = 0; llllllllllllllllIlllIIlIIIlIIlll < llllllllllllllllIlllIIlIIIlIIIlI; ++llllllllllllllllIlllIIlIIIlIIlll) {
            if ((llllllllllllllllIlllIIlIIIlIIlll < llllllllllllllllIlllIIlIIIlIIlII ? llllllllllllllllIlllIIlIIIlIIIIl.get(llllllllllllllllIlllIIlIIIlIIlll) : 0) > (llllllllllllllllIlllIIlIIIlIIlll < llllllllllllllllIlllIIlIIIlIIIll ? llllllllllllllllIlllIIlIIIlIIIII.get(llllllllllllllllIlllIIlIIIlIIlll) : 0)) {
                return 1;
            }
            if ((llllllllllllllllIlllIIlIIIlIIlll < llllllllllllllllIlllIIlIIIlIIlII ? llllllllllllllllIlllIIlIIIlIIIIl.get(llllllllllllllllIlllIIlIIIlIIlll) : 0) >= (llllllllllllllllIlllIIlIIIlIIlll < llllllllllllllllIlllIIlIIIlIIIll ? llllllllllllllllIlllIIlIIIlIIIII.get(llllllllllllllllIlllIIlIIIlIIlll) : 0)) continue;
            return -1;
        }
        return 0;
    }

    static int qualifierToNumber(@Nonnull String llllllllllllllllIlllIIlIIIIIlIII) {
        if (llllllllllllllllIlllIIlIIIIIlIII.length() > 0) {
            if ((llllllllllllllllIlllIIlIIIIIlIII = llllllllllllllllIlllIIlIIIIIlIII.toLowerCase()).contains("rc")) {
                return 4;
            }
            if (llllllllllllllllIlllIIlIIIIIlIII.contains("beta")) {
                return 3;
            }
            if (llllllllllllllllIlllIIlIIIIIlIII.contains("alpha")) {
                if (llllllllllllllllIlllIIlIIIIIlIII.substring(0, llllllllllllllllIlllIIlIIIIIlIII.indexOf("alpha")).contains("pre")) {
                    return 1;
                }
                return 2;
            }
            if (llllllllllllllllIlllIIlIIIIIlIII.contains("snapshot")) {
                return 0;
            }
        }
        return 5;
    }
}

