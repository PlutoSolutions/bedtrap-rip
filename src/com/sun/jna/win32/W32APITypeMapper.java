/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna.win32;

import com.sun.jna.DefaultTypeMapper;
import com.sun.jna.FromNativeContext;
import com.sun.jna.StringArray;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeConverter;
import com.sun.jna.TypeMapper;
import com.sun.jna.WString;

public class W32APITypeMapper
extends DefaultTypeMapper {
    public static final /* synthetic */ TypeMapper DEFAULT;
    public static final /* synthetic */ TypeMapper UNICODE;
    public static final /* synthetic */ TypeMapper ASCII;

    protected W32APITypeMapper(boolean lllIIllIIIIlIlI) {
        W32APITypeMapper lllIIllIIIIlIll;
        if (lllIIllIIIIlIlI) {
            TypeConverter lllIIllIIIIllII = new TypeConverter(){
                {
                    1 lllllllllllllllllIlIlIIIIlllIIll;
                }

                @Override
                public Object fromNative(Object lllllllllllllllllIlIlIIIIlIllIlI, FromNativeContext lllllllllllllllllIlIlIIIIlIllIIl) {
                    if (lllllllllllllllllIlIlIIIIlIllIlI == null) {
                        return null;
                    }
                    return lllllllllllllllllIlIlIIIIlIllIlI.toString();
                }

                @Override
                public Object toNative(Object lllllllllllllllllIlIlIIIIllIIlIl, ToNativeContext lllllllllllllllllIlIlIIIIllIIlII) {
                    if (lllllllllllllllllIlIlIIIIllIIlIl == null) {
                        return null;
                    }
                    if (lllllllllllllllllIlIlIIIIllIIlIl instanceof String[]) {
                        return new StringArray((String[])lllllllllllllllllIlIlIIIIllIIlIl, true);
                    }
                    return new WString(lllllllllllllllllIlIlIIIIllIIlIl.toString());
                }

                @Override
                public Class<?> nativeType() {
                    return WString.class;
                }
            };
            lllIIllIIIIlIll.addTypeConverter(String.class, lllIIllIIIIllII);
            lllIIllIIIIlIll.addToNativeConverter(String[].class, lllIIllIIIIllII);
        }
        TypeConverter lllIIllIIIIlIIl = new TypeConverter(){

            @Override
            public Object toNative(Object lIIlIlIlllIllII, ToNativeContext lIIlIlIlllIllIl) {
                return Boolean.TRUE.equals(lIIlIlIlllIllII) ? 1 : 0;
            }

            @Override
            public Class<?> nativeType() {
                return Integer.class;
            }
            {
                2 lIIlIlIllllIIlI;
            }

            @Override
            public Object fromNative(Object lIIlIlIlllIlIIl, FromNativeContext lIIlIlIlllIlIII) {
                return (Integer)lIIlIlIlllIlIIl != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
        };
        lllIIllIIIIlIll.addTypeConverter(Boolean.class, lllIIllIIIIlIIl);
    }

    static {
        UNICODE = new W32APITypeMapper(true);
        ASCII = new W32APITypeMapper(false);
        DEFAULT = Boolean.getBoolean("w32.ascii") ? ASCII : UNICODE;
    }
}

