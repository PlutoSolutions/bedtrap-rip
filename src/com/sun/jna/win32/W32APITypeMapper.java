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
    public static final TypeMapper DEFAULT;
    public static final TypeMapper UNICODE;
    public static final TypeMapper ASCII;

    protected W32APITypeMapper(boolean bl) {
        TypeConverter typeConverter;
        if (bl) {
            typeConverter = new TypeConverter(this){
                final W32APITypeMapper this$0;
                {
                    this.this$0 = w32APITypeMapper;
                }

                @Override
                public Object fromNative(Object object, FromNativeContext fromNativeContext) {
                    if (object == null) {
                        return null;
                    }
                    return object.toString();
                }

                @Override
                public Object toNative(Object object, ToNativeContext toNativeContext) {
                    if (object == null) {
                        return null;
                    }
                    if (object instanceof String[]) {
                        return new StringArray((String[])object, true);
                    }
                    return new WString(object.toString());
                }

                @Override
                public Class<?> nativeType() {
                    return WString.class;
                }
            };
            this.addTypeConverter(String.class, typeConverter);
            this.addToNativeConverter(String[].class, typeConverter);
        }
        typeConverter = new TypeConverter(this){
            final W32APITypeMapper this$0;

            @Override
            public Object toNative(Object object, ToNativeContext toNativeContext) {
                return Boolean.TRUE.equals(object) ? 1 : 0;
            }

            @Override
            public Class<?> nativeType() {
                return Integer.class;
            }
            {
                this.this$0 = w32APITypeMapper;
            }

            @Override
            public Object fromNative(Object object, FromNativeContext fromNativeContext) {
                return (Integer)object != 0 ? Boolean.TRUE : Boolean.FALSE;
            }
        };
        this.addTypeConverter(Boolean.class, typeConverter);
    }

    static {
        UNICODE = new W32APITypeMapper(true);
        ASCII = new W32APITypeMapper(false);
        DEFAULT = Boolean.getBoolean("w32.ascii") ? ASCII : UNICODE;
    }
}

