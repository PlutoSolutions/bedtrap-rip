/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.win32;

import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface W32APIOptions
extends StdCallLibrary {
    public static final Map<String, Object> DEFAULT_OPTIONS;
    public static final Map<String, Object> ASCII_OPTIONS;
    public static final Map<String, Object> UNICODE_OPTIONS;

    static {
        UNICODE_OPTIONS = Collections.unmodifiableMap(new HashMap<String, Object>(){
            private static final long serialVersionUID = 1L;
            {
                this.put("type-mapper", W32APITypeMapper.UNICODE);
                this.put("function-mapper", W32APIFunctionMapper.UNICODE);
            }
        });
        ASCII_OPTIONS = Collections.unmodifiableMap(new HashMap<String, Object>(){
            private static final long serialVersionUID = 1L;
            {
                this.put("type-mapper", W32APITypeMapper.ASCII);
                this.put("function-mapper", W32APIFunctionMapper.ASCII);
            }
        });
        DEFAULT_OPTIONS = Boolean.getBoolean("w32.ascii") ? ASCII_OPTIONS : UNICODE_OPTIONS;
    }
}

