/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Platform;

public class LastErrorException
extends RuntimeException {
    private /* synthetic */ int errorCode;
    private static final /* synthetic */ long serialVersionUID = 1L;

    public LastErrorException(String lIIlIllIllIIlII) {
        super(LastErrorException.parseMessage(lIIlIllIllIIlII.trim()));
        LastErrorException lIIlIllIllIIIll;
        try {
            if (lIIlIllIllIIlII.startsWith("[")) {
                lIIlIllIllIIlII = lIIlIllIllIIlII.substring(1, lIIlIllIllIIlII.indexOf("]"));
            }
            lIIlIllIllIIIll.errorCode = Integer.parseInt(lIIlIllIllIIlII);
        }
        catch (NumberFormatException lIIlIllIllIIllI) {
            lIIlIllIllIIIll.errorCode = -1;
        }
    }

    protected LastErrorException(int lIIlIllIlIlIllI, String lIIlIllIlIlIIlI) {
        super(lIIlIllIlIlIIlI);
        LastErrorException lIIlIllIlIlIlII;
        lIIlIllIlIlIlII.errorCode = lIIlIllIlIlIllI;
    }

    private static String formatMessage(int lIIlIllIlllIlII) {
        return Platform.isWindows() ? String.valueOf(new StringBuilder().append("GetLastError() returned ").append(lIIlIllIlllIlII)) : String.valueOf(new StringBuilder().append("errno was ").append(lIIlIllIlllIlII));
    }

    public LastErrorException(int lIIlIllIlIllIll) {
        lIIlIllIlIllllI(lIIlIllIlIllIll, LastErrorException.formatMessage(lIIlIllIlIllIll));
        LastErrorException lIIlIllIlIllllI;
    }

    private static String parseMessage(String lIIlIllIllIlllI) {
        try {
            return LastErrorException.formatMessage(Integer.parseInt(lIIlIllIllIlllI));
        }
        catch (NumberFormatException lIIlIllIlllIIII) {
            return lIIlIllIllIlllI;
        }
    }

    public int getErrorCode() {
        LastErrorException lIIlIllIllIlIlI;
        return lIIlIllIllIlIlI.errorCode;
    }
}

