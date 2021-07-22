/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Platform;

public class LastErrorException
extends RuntimeException {
    private int errorCode;
    private static final long serialVersionUID = 1L;

    public LastErrorException(String string) {
        super(LastErrorException.parseMessage(string.trim()));
        try {
            if (string.startsWith("[")) {
                string = string.substring(1, string.indexOf("]"));
            }
            this.errorCode = Integer.parseInt(string);
        }
        catch (NumberFormatException numberFormatException) {
            this.errorCode = -1;
        }
    }

    protected LastErrorException(int n, String string) {
        super(string);
        this.errorCode = n;
    }

    private static String formatMessage(int n) {
        return Platform.isWindows() ? String.valueOf(new StringBuilder().append("GetLastError() returned ").append(n)) : String.valueOf(new StringBuilder().append("errno was ").append(n));
    }

    public LastErrorException(int n) {
        this(n, LastErrorException.formatMessage(n));
    }

    private static String parseMessage(String string) {
        try {
            return LastErrorException.formatMessage(Integer.parseInt(string));
        }
        catch (NumberFormatException numberFormatException) {
            return string;
        }
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}

