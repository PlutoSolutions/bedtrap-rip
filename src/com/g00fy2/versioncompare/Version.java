/*
 * Decompiled with CFR 0.151.
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
    private final List<Integer> subversionNumbersWithoutTrailingZeros;
    @Nonnull
    private String suffix = "";
    @Nonnull
    private final List<Integer> subversionNumbers = new ArrayList<Integer>();
    @Nullable
    private final String originalString;

    public boolean isLowerThan(Version version) {
        int n = VersionComparator.compareSubversionNumbers(this.subversionNumbersWithoutTrailingZeros, version.subversionNumbersWithoutTrailingZeros);
        if (n != 0) {
            return n < 0;
        }
        return VersionComparator.compareSuffix(this.suffix, version.suffix) < 0;
    }

    public boolean isAtLeast(String string, boolean bl) {
        return this.isAtLeast(new Version(string), bl);
    }

    public boolean isEqual(String string) {
        return this.isEqual(new Version(string));
    }

    private void initVersion() {
        if (this.originalString != null && VersionComparator.startsNumeric(this.originalString)) {
            String[] stringArray = this.originalString.replaceAll("\\s", "").split("\\.");
            boolean bl = false;
            StringBuilder stringBuilder = null;
            for (String string : stringArray) {
                if (bl) {
                    stringBuilder.append(".");
                    stringBuilder.append(string);
                    continue;
                }
                if (VersionComparator.isNumeric(string)) {
                    this.subversionNumbers.add(VersionComparator.safeParseInt(string));
                    continue;
                }
                for (int i = 0; i < string.length(); ++i) {
                    if (Character.isDigit(string.charAt(i))) continue;
                    stringBuilder = new StringBuilder();
                    if (i > 0) {
                        this.subversionNumbers.add(VersionComparator.safeParseInt(string.substring(0, i)));
                        stringBuilder.append(string.substring(i));
                    } else {
                        stringBuilder.append(string);
                    }
                    bl = true;
                    break;
                }
            }
            this.subversionNumbersWithoutTrailingZeros.addAll(this.subversionNumbers);
            while (!this.subversionNumbersWithoutTrailingZeros.isEmpty() && this.subversionNumbersWithoutTrailingZeros.lastIndexOf(0) == this.subversionNumbersWithoutTrailingZeros.size() - 1) {
                this.subversionNumbersWithoutTrailingZeros.remove(this.subversionNumbersWithoutTrailingZeros.lastIndexOf(0));
            }
            if (stringBuilder != null) {
                this.suffix = String.valueOf(stringBuilder);
            }
        }
    }

    public int getPatch() {
        return this.subversionNumbers.size() > 2 ? this.subversionNumbers.get(2) : 0;
    }

    public boolean isHigherThan(String string) {
        return this.isHigherThan(new Version(string));
    }

    public Version(@Nullable String string) {
        this(string, false);
    }

    public boolean isAtLeast(String string) {
        return this.isAtLeast(new Version(string));
    }

    public boolean isEqual(Version version) {
        return VersionComparator.compareSubversionNumbers(this.subversionNumbersWithoutTrailingZeros, version.subversionNumbersWithoutTrailingZeros) == 0 && VersionComparator.compareSuffix(this.suffix, version.suffix) == 0;
    }

    public final boolean equals(Object object) {
        if (object instanceof Version && this.isEqual((Version)object)) {
            return true;
        }
        return super.equals(object);
    }

    @Override
    public int compareTo(@Nonnull Object object) {
        return this.compareTo((Version)object);
    }

    public Version(@Nullable String string, boolean bl) {
        this.subversionNumbersWithoutTrailingZeros = new ArrayList<Integer>();
        if (bl) {
            if (string == null) {
                throw new NullPointerException("Argument versionString is null");
            }
            if (!VersionComparator.startsNumeric(string)) {
                throw new IllegalArgumentException("Argument versionString is no valid version");
            }
        }
        this.originalString = string;
        this.initVersion();
    }

    public boolean isAtLeast(Version version, boolean bl) {
        int n = VersionComparator.compareSubversionNumbers(this.subversionNumbersWithoutTrailingZeros, version.subversionNumbersWithoutTrailingZeros);
        if (n == 0 && !bl) {
            return VersionComparator.compareSuffix(this.suffix, version.suffix) >= 0;
        }
        return n >= 0;
    }

    public boolean isLowerThan(String string) {
        return this.isLowerThan(new Version(string));
    }

    @Nonnull
    public String getSuffix() {
        return this.suffix;
    }

    public int getMinor() {
        return this.subversionNumbers.size() > 1 ? this.subversionNumbers.get(1) : 0;
    }

    public boolean isHigherThan(Version version) {
        int n = VersionComparator.compareSubversionNumbers(this.subversionNumbersWithoutTrailingZeros, version.subversionNumbersWithoutTrailingZeros);
        if (n != 0) {
            return n > 0;
        }
        return VersionComparator.compareSuffix(this.suffix, version.suffix) > 0;
    }

    public boolean isAtLeast(Version version) {
        return this.isAtLeast(version, false);
    }

    @Nonnull
    public List<Integer> getSubversionNumbers() {
        return this.subversionNumbers;
    }

    @Override
    public final int compareTo(@Nonnull Version version) {
        if (this.isEqual(version)) {
            return 0;
        }
        if (this.isLowerThan(version)) {
            return -1;
        }
        return 1;
    }

    public final int hashCode() {
        int n = 31;
        int n2 = 1;
        n2 = 31 * n2 + this.subversionNumbersWithoutTrailingZeros.hashCode();
        if (this.suffix.isEmpty()) {
            return n2;
        }
        int n3 = VersionComparator.qualifierToNumber(this.suffix);
        int n4 = VersionComparator.preReleaseVersion(this.suffix, n3);
        n2 = 31 * n2 + n3;
        n2 = 31 * n2 + n4;
        return n2;
    }

    @Nullable
    public String getOriginalString() {
        return this.originalString;
    }

    public int getMajor() {
        return this.subversionNumbers.size() > 0 ? this.subversionNumbers.get(0) : 0;
    }
}

