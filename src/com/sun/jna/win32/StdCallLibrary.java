/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna.win32;

import com.sun.jna.Callback;
import com.sun.jna.FunctionMapper;
import com.sun.jna.Library;
import com.sun.jna.win32.StdCall;
import com.sun.jna.win32.StdCallFunctionMapper;

public interface StdCallLibrary
extends Library,
StdCall {
    public static final FunctionMapper FUNCTION_MAPPER;
    public static final int STDCALL_CONVENTION;

    static {
        STDCALL_CONVENTION = 63;
        FUNCTION_MAPPER = new StdCallFunctionMapper();
    }

    public static interface StdCallCallback
    extends Callback,
    StdCall {
    }
}

