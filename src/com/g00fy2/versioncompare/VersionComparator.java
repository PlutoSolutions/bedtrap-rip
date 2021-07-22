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
    private static final int UNKNOWN;
    static final int MINOR;
    private static final int RC;
    private static final int ALPHA;
    private static final String RC_STRING;
    private static final String ALPHA_STRING;
    static final int MAJOR;
    private static final String PRE_STRING;
    private static final String SNAPSHOT_STRING;
    private static final int BETA;
    private static final String BETA_STRING;
    static final int PATCH;
    private static final int PRE_ALPHA;
    private static final int SNAPSHOT;

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

    private static boolean containsNumeric(@Nonnull CharSequence charSequence) {
        int n = charSequence.length();
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                if (!Character.isDigit(charSequence.charAt(i))) continue;
                return true;
            }
        }
        return false;
    }

    static int compareSuffix(@Nonnull String string, @Nonnull String string2) {
        if (string.length() > 0 || string2.length() > 0) {
            int n;
            int n2 = VersionComparator.qualifierToNumber(string);
            if (n2 > (n = VersionComparator.qualifierToNumber(string2))) {
                return 1;
            }
            if (n2 < n) {
                return -1;
            }
            if (n2 != 5 && n2 != 0) {
                int n3;
                int n4 = VersionComparator.preReleaseVersion(string, n2);
                if (n4 > (n3 = VersionComparator.preReleaseVersion(string2, n))) {
                    return 1;
                }
                if (n4 < n3) {
                    return -1;
                }
            }
        }
        return 0;
    }

    static boolean isNumeric(@Nonnull CharSequence charSequence) {
        int n = charSequence.length();
        if (n > 0) {
            for (int i = 0; i < n; ++i) {
                if (Character.isDigit(charSequence.charAt(i))) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    static int safeParseInt(@Nonnull String string) {
        if (string.length() > 9) {
            string = string.substring(0, 9);
        }
        return Integer.parseInt(string);
    }

    VersionComparator() {
    }

    static int preReleaseVersion(@Nonnull String string, int n) {
        int n2;
        int n3 = VersionComparator.indexOfQualifier(string, n);
        if (n3 < string.length() && VersionComparator.containsNumeric(string.substring(n3, n2 = Math.min(n3 + 2, string.length())))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = n3; i < string.length(); ++i) {
                char c = string.charAt(i);
                if (Character.isDigit(c)) {
                    stringBuilder.append(c);
                    continue;
                }
                if (i != n3) break;
                if (!false) continue;
                return 0;
            }
            return VersionComparator.safeParseInt(String.valueOf(stringBuilder));
        }
        return 0;
    }

    private static int indexOfQualifier(@Nonnull String string, int n) {
        if (n == 4) {
            return string.indexOf("rc") + "rc".length();
        }
        if (n == 3) {
            return string.indexOf("beta") + "beta".length();
        }
        if (n == 2 || n == 1) {
            return string.indexOf("alpha") + "alpha".length();
        }
        return 0;
    }

    static boolean startsNumeric(@Nonnull String string) {
        return (string = string.trim()).length() > 0 && Character.isDigit(string.charAt(0));
    }

    static int compareSubversionNumbers(@Nonnull List<Integer> list, @Nonnull List<Integer> list2) {
        int n = list.size();
        int n2 = list2.size();
        int n3 = Math.max(n, n2);
        for (int i = 0; i < n3; ++i) {
            if ((i < n ? list.get(i) : 0) > (i < n2 ? list2.get(i) : 0)) {
                return 1;
            }
            if ((i < n ? list.get(i) : 0) >= (i < n2 ? list2.get(i) : 0)) continue;
            return -1;
        }
        return 0;
    }

    static int qualifierToNumber(@Nonnull String string) {
        if (string.length() > 0) {
            if ((string = string.toLowerCase()).contains("rc")) {
                return 4;
            }
            if (string.contains("beta")) {
                return 3;
            }
            if (string.contains("alpha")) {
                if (string.substring(0, string.indexOf("alpha")).contains("pre")) {
                    return 1;
                }
                return 2;
            }
            if (string.contains("snapshot")) {
                return 0;
            }
        }
        return 5;
    }
}

