/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

public final class WString
implements CharSequence,
Comparable {
    private /* synthetic */ String string;

    public int hashCode() {
        WString llIllIlllIllIl;
        return llIllIlllIllIl.toString().hashCode();
    }

    public int compareTo(Object llIllIlllIIlll) {
        WString llIllIlllIlIlI;
        return llIllIlllIlIlI.toString().compareTo(llIllIlllIIlll.toString());
    }

    @Override
    public CharSequence subSequence(int llIllIllIllIIl, int llIllIllIllIII) {
        WString llIllIllIllIlI;
        return llIllIllIllIlI.toString().subSequence(llIllIllIllIIl, llIllIllIllIII);
    }

    @Override
    public char charAt(int llIllIlllIIIII) {
        WString llIllIlllIIIIl;
        return llIllIlllIIIIl.toString().charAt(llIllIlllIIIII);
    }

    @Override
    public int length() {
        WString llIllIlllIIlII;
        return llIllIlllIIlII.toString().length();
    }

    public WString(String llIllIlllllIll) {
        WString llIllIlllllIlI;
        if (llIllIlllllIll == null) {
            throw new NullPointerException("String initializer must be non-null");
        }
        llIllIlllllIlI.string = llIllIlllllIll;
    }

    public boolean equals(Object llIllIllllIIII) {
        WString llIllIllllIIIl;
        return llIllIllllIIII instanceof WString && llIllIllllIIIl.toString().equals(llIllIllllIIII.toString());
    }

    @Override
    public String toString() {
        WString llIllIllllIllI;
        return llIllIllllIllI.string;
    }
}

