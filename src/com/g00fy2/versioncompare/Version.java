/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 */
package com.g00fy2.versioncompare;

import com.g00fy2.versioncompare.VersionComparator;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Version
implements Comparable<Version> {
    @Nonnull
    private final /* synthetic */ List<Integer> subversionNumbersWithoutTrailingZeros;
    @Nonnull
    private /* synthetic */ String suffix;
    @Nonnull
    private final /* synthetic */ List<Integer> subversionNumbers;
    @Nullable
    private final /* synthetic */ String originalString;

    public boolean isLowerThan(Version llllllllllllllllIllIllllllIIIIII) {
        Version llllllllllllllllIllIlllllIlllllI;
        int llllllllllllllllIllIlllllIllllll = VersionComparator.compareSubversionNumbers(llllllllllllllllIllIlllllIlllllI.subversionNumbersWithoutTrailingZeros, llllllllllllllllIllIllllllIIIIII.subversionNumbersWithoutTrailingZeros);
        if (llllllllllllllllIllIlllllIllllll != 0) {
            return llllllllllllllllIllIlllllIllllll < 0;
        }
        return VersionComparator.compareSuffix(llllllllllllllllIllIlllllIlllllI.suffix, llllllllllllllllIllIllllllIIIIII.suffix) < 0;
    }

    public boolean isAtLeast(String llllllllllllllllIllIlllllIIlllll, boolean llllllllllllllllIllIlllllIIllllI) {
        Version llllllllllllllllIllIlllllIlIIIII;
        return llllllllllllllllIllIlllllIlIIIII.isAtLeast(new Version(llllllllllllllllIllIlllllIIlllll), llllllllllllllllIllIlllllIIllllI);
    }

    public boolean isEqual(String llllllllllllllllIllIlllllIlllIII) {
        Version llllllllllllllllIllIlllllIllIlll;
        return llllllllllllllllIllIlllllIllIlll.isEqual(new Version(llllllllllllllllIllIlllllIlllIII));
    }

    private void initVersion() {
        Version llllllllllllllllIllIlllllIIIIIII;
        if (llllllllllllllllIllIlllllIIIIIII.originalString != null && VersionComparator.startsNumeric(llllllllllllllllIllIlllllIIIIIII.originalString)) {
            String[] llllllllllllllllIllIlllllIIIIIll = llllllllllllllllIllIlllllIIIIIII.originalString.replaceAll("\\s", "").split("\\.");
            boolean llllllllllllllllIllIlllllIIIIIlI = false;
            StringBuilder llllllllllllllllIllIlllllIIIIIIl = null;
            block0: for (String llllllllllllllllIllIlllllIIIIlII : llllllllllllllllIllIlllllIIIIIll) {
                if (llllllllllllllllIllIlllllIIIIIlI) {
                    llllllllllllllllIllIlllllIIIIIIl.append(".");
                    llllllllllllllllIllIlllllIIIIIIl.append(llllllllllllllllIllIlllllIIIIlII);
                    continue;
                }
                if (VersionComparator.isNumeric(llllllllllllllllIllIlllllIIIIlII)) {
                    llllllllllllllllIllIlllllIIIIIII.subversionNumbers.add(VersionComparator.safeParseInt(llllllllllllllllIllIlllllIIIIlII));
                    continue;
                }
                for (int llllllllllllllllIllIlllllIIIIlIl = 0; llllllllllllllllIllIlllllIIIIlIl < llllllllllllllllIllIlllllIIIIlII.length(); ++llllllllllllllllIllIlllllIIIIlIl) {
                    if (Character.isDigit(llllllllllllllllIllIlllllIIIIlII.charAt(llllllllllllllllIllIlllllIIIIlIl))) continue;
                    llllllllllllllllIllIlllllIIIIIIl = new StringBuilder();
                    if (llllllllllllllllIllIlllllIIIIlIl > 0) {
                        llllllllllllllllIllIlllllIIIIIII.subversionNumbers.add(VersionComparator.safeParseInt(llllllllllllllllIllIlllllIIIIlII.substring(0, llllllllllllllllIllIlllllIIIIlIl)));
                        llllllllllllllllIllIlllllIIIIIIl.append(llllllllllllllllIllIlllllIIIIlII.substring(llllllllllllllllIllIlllllIIIIlIl));
                    } else {
                        llllllllllllllllIllIlllllIIIIIIl.append(llllllllllllllllIllIlllllIIIIlII);
                    }
                    llllllllllllllllIllIlllllIIIIIlI = true;
                    continue block0;
                }
            }
            llllllllllllllllIllIlllllIIIIIII.subversionNumbersWithoutTrailingZeros.addAll(llllllllllllllllIllIlllllIIIIIII.subversionNumbers);
            while (!llllllllllllllllIllIlllllIIIIIII.subversionNumbersWithoutTrailingZeros.isEmpty() && llllllllllllllllIllIlllllIIIIIII.subversionNumbersWithoutTrailingZeros.lastIndexOf(0) == llllllllllllllllIllIlllllIIIIIII.subversionNumbersWithoutTrailingZeros.size() - 1) {
                llllllllllllllllIllIlllllIIIIIII.subversionNumbersWithoutTrailingZeros.remove(llllllllllllllllIllIlllllIIIIIII.subversionNumbersWithoutTrailingZeros.lastIndexOf(0));
            }
            if (llllllllllllllllIllIlllllIIIIIIl != null) {
                llllllllllllllllIllIlllllIIIIIII.suffix = String.valueOf(llllllllllllllllIllIlllllIIIIIIl);
            }
        }
    }

    public int getPatch() {
        Version llllllllllllllllIllIlllllllIIlII;
        return llllllllllllllllIllIlllllllIIlII.subversionNumbers.size() > 2 ? llllllllllllllllIllIlllllllIIlII.subversionNumbers.get(2) : 0;
    }

    public boolean isHigherThan(String llllllllllllllllIllIllllllIlIlII) {
        Version llllllllllllllllIllIllllllIlIlll;
        return llllllllllllllllIllIllllllIlIlll.isHigherThan(new Version(llllllllllllllllIllIllllllIlIlII));
    }

    public Version(@Nullable String llllllllllllllllIllIllllllllIlll) {
        llllllllllllllllIllIllllllllIllI(llllllllllllllllIllIllllllllIlll, false);
        Version llllllllllllllllIllIllllllllIllI;
    }

    public boolean isAtLeast(String llllllllllllllllIllIlllllIlIlIlI) {
        Version llllllllllllllllIllIlllllIlIllIl;
        return llllllllllllllllIllIlllllIlIllIl.isAtLeast(new Version(llllllllllllllllIllIlllllIlIlIlI));
    }

    public boolean isEqual(Version llllllllllllllllIllIlllllIllIIII) {
        Version llllllllllllllllIllIlllllIllIIll;
        return VersionComparator.compareSubversionNumbers(llllllllllllllllIllIlllllIllIIll.subversionNumbersWithoutTrailingZeros, llllllllllllllllIllIlllllIllIIII.subversionNumbersWithoutTrailingZeros) == 0 && VersionComparator.compareSuffix(llllllllllllllllIllIlllllIllIIll.suffix, llllllllllllllllIllIlllllIllIIII.suffix) == 0;
    }

    public final boolean equals(Object llllllllllllllllIllIllllIllIllIl) {
        Version llllllllllllllllIllIllllIllIlllI;
        if (llllllllllllllllIllIllllIllIllIl instanceof Version && llllllllllllllllIllIllllIllIlllI.isEqual((Version)llllllllllllllllIllIllllIllIllIl)) {
            return true;
        }
        return super.equals(llllllllllllllllIllIllllIllIllIl);
    }

    public Version(@Nullable String llllllllllllllllIllIlllllllIllIl, boolean llllllllllllllllIllIlllllllIllll) {
        Version llllllllllllllllIllIllllllllIIIl;
        llllllllllllllllIllIllllllllIIIl.subversionNumbers = new ArrayList<Integer>();
        llllllllllllllllIllIllllllllIIIl.subversionNumbersWithoutTrailingZeros = new ArrayList<Integer>();
        llllllllllllllllIllIllllllllIIIl.suffix = "";
        if (llllllllllllllllIllIlllllllIllll) {
            if (llllllllllllllllIllIlllllllIllIl == null) {
                throw new NullPointerException("Argument versionString is null");
            }
            if (!VersionComparator.startsNumeric(llllllllllllllllIllIlllllllIllIl)) {
                throw new IllegalArgumentException("Argument versionString is no valid version");
            }
        }
        llllllllllllllllIllIllllllllIIIl.originalString = llllllllllllllllIllIlllllllIllIl;
        llllllllllllllllIllIllllllllIIIl.initVersion();
    }

    public boolean isAtLeast(Version llllllllllllllllIllIlllllIIlIlIl, boolean llllllllllllllllIllIlllllIIlIlII) {
        Version llllllllllllllllIllIlllllIIlIllI;
        int llllllllllllllllIllIlllllIIlIIll = VersionComparator.compareSubversionNumbers(llllllllllllllllIllIlllllIIlIllI.subversionNumbersWithoutTrailingZeros, llllllllllllllllIllIlllllIIlIlIl.subversionNumbersWithoutTrailingZeros);
        if (llllllllllllllllIllIlllllIIlIIll == 0 && !llllllllllllllllIllIlllllIIlIlII) {
            return VersionComparator.compareSuffix(llllllllllllllllIllIlllllIIlIllI.suffix, llllllllllllllllIllIlllllIIlIlIl.suffix) >= 0;
        }
        return llllllllllllllllIllIlllllIIlIIll >= 0;
    }

    public boolean isLowerThan(String llllllllllllllllIllIllllllIIIlll) {
        Version llllllllllllllllIllIllllllIIIllI;
        return llllllllllllllllIllIllllllIIIllI.isLowerThan(new Version(llllllllllllllllIllIllllllIIIlll));
    }

    @Nonnull
    public String getSuffix() {
        Version llllllllllllllllIllIllllllIlllIl;
        return llllllllllllllllIllIllllllIlllIl.suffix;
    }

    public int getMinor() {
        Version llllllllllllllllIllIlllllllIIllI;
        return llllllllllllllllIllIlllllllIIllI.subversionNumbers.size() > 1 ? llllllllllllllllIllIlllllllIIllI.subversionNumbers.get(1) : 0;
    }

    public boolean isHigherThan(Version llllllllllllllllIllIllllllIIllII) {
        Version llllllllllllllllIllIllllllIIllIl;
        int llllllllllllllllIllIllllllIIlllI = VersionComparator.compareSubversionNumbers(llllllllllllllllIllIllllllIIllIl.subversionNumbersWithoutTrailingZeros, llllllllllllllllIllIllllllIIllII.subversionNumbersWithoutTrailingZeros);
        if (llllllllllllllllIllIllllllIIlllI != 0) {
            return llllllllllllllllIllIllllllIIlllI > 0;
        }
        return VersionComparator.compareSuffix(llllllllllllllllIllIllllllIIllIl.suffix, llllllllllllllllIllIllllllIIllII.suffix) > 0;
    }

    public boolean isAtLeast(Version llllllllllllllllIllIlllllIlIIllI) {
        Version llllllllllllllllIllIlllllIlIIlll;
        return llllllllllllllllIllIlllllIlIIlll.isAtLeast(llllllllllllllllIllIlllllIlIIllI, false);
    }

    @Nonnull
    public List<Integer> getSubversionNumbers() {
        Version llllllllllllllllIllIlllllllIIIIl;
        return llllllllllllllllIllIlllllllIIIIl.subversionNumbers;
    }

    @Override
    public final int compareTo(@Nonnull Version llllllllllllllllIllIllllIlllIIll) {
        Version llllllllllllllllIllIllllIlllIIlI;
        if (llllllllllllllllIllIllllIlllIIlI.isEqual(llllllllllllllllIllIllllIlllIIll)) {
            return 0;
        }
        if (llllllllllllllllIllIllllIlllIIlI.isLowerThan(llllllllllllllllIllIllllIlllIIll)) {
            return -1;
        }
        return 1;
    }

    public final int hashCode() {
        Version llllllllllllllllIllIllllIllIIIII;
        int llllllllllllllllIllIllllIllIIlII = 31;
        int llllllllllllllllIllIllllIllIIIll = 1;
        llllllllllllllllIllIllllIllIIIll = 31 * llllllllllllllllIllIllllIllIIIll + llllllllllllllllIllIllllIllIIIII.subversionNumbersWithoutTrailingZeros.hashCode();
        if (llllllllllllllllIllIllllIllIIIII.suffix.isEmpty()) {
            return llllllllllllllllIllIllllIllIIIll;
        }
        int llllllllllllllllIllIllllIllIIIlI = VersionComparator.qualifierToNumber(llllllllllllllllIllIllllIllIIIII.suffix);
        int llllllllllllllllIllIllllIllIIIIl = VersionComparator.preReleaseVersion(llllllllllllllllIllIllllIllIIIII.suffix, llllllllllllllllIllIllllIllIIIlI);
        llllllllllllllllIllIllllIllIIIll = 31 * llllllllllllllllIllIllllIllIIIll + llllllllllllllllIllIllllIllIIIlI;
        llllllllllllllllIllIllllIllIIIll = 31 * llllllllllllllllIllIllllIllIIIll + llllllllllllllllIllIllllIllIIIIl;
        return llllllllllllllllIllIllllIllIIIll;
    }

    @Nullable
    public String getOriginalString() {
        Version llllllllllllllllIllIllllllIllIlI;
        return llllllllllllllllIllIllllllIllIlI.originalString;
    }

    public int getMajor() {
        Version llllllllllllllllIllIlllllllIlIlI;
        return llllllllllllllllIllIlllllllIlIlI.subversionNumbers.size() > 0 ? llllllllllllllllIllIlllllllIlIlI.subversionNumbers.get(0) : 0;
    }
}

