/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

public final class WString
implements CharSequence,
Comparable {
    private String string;

    public int hashCode() {
        return this.toString().hashCode();
    }

    public int compareTo(Object object) {
        return this.toString().compareTo(object.toString());
    }

    @Override
    public CharSequence subSequence(int n, int n2) {
        return this.toString().subSequence(n, n2);
    }

    @Override
    public char charAt(int n) {
        return this.toString().charAt(n);
    }

    @Override
    public int length() {
        return this.toString().length();
    }

    public WString(String string) {
        if (string == null) {
            throw new NullPointerException("String initializer must be non-null");
        }
        this.string = string;
    }

    public boolean equals(Object object) {
        return object instanceof WString && this.toString().equals(object.toString());
    }

    @Override
    public String toString() {
        return this.string;
    }
}

