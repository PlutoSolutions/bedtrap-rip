/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import com.sun.jna.NativeMapped;
import com.sun.jna.Pointer;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeConverter;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.WeakHashMap;

public class NativeMappedConverter
implements TypeConverter {
    private final NativeMapped instance;
    private static final Map<Class<?>, Reference<NativeMappedConverter>> converters = new WeakHashMap();
    private final Class<?> nativeType;
    private final Class<?> type;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static NativeMappedConverter getInstance(Class<?> class_) {
        Map<Class<?>, Reference<NativeMappedConverter>> map = converters;
        synchronized (map) {
            NativeMappedConverter nativeMappedConverter;
            Reference<NativeMappedConverter> reference = converters.get(class_);
            NativeMappedConverter nativeMappedConverter2 = nativeMappedConverter = reference != null ? reference.get() : null;
            if (nativeMappedConverter == null) {
                nativeMappedConverter = new NativeMappedConverter(class_);
                converters.put(class_, new SoftReference<NativeMappedConverter>(nativeMappedConverter));
            }
            return nativeMappedConverter;
        }
    }

    @Override
    public Class<?> nativeType() {
        return this.nativeType;
    }

    public NativeMappedConverter(Class<?> class_) {
        if (!NativeMapped.class.isAssignableFrom(class_)) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Type must derive from ").append(NativeMapped.class)));
        }
        this.type = class_;
        this.instance = this.defaultValue();
        this.nativeType = this.instance.nativeType();
    }

    @Override
    public Object fromNative(Object object, FromNativeContext fromNativeContext) {
        return this.instance.fromNative(object, fromNativeContext);
    }

    public NativeMapped defaultValue() {
        try {
            return (NativeMapped)this.type.newInstance();
        }
        catch (IllegalAccessException | InstantiationException reflectiveOperationException) {
            String string = String.valueOf(new StringBuilder().append("Can't create an instance of ").append(this.type).append(", requires a no-arg constructor: ").append(reflectiveOperationException));
            throw new IllegalArgumentException(string);
        }
    }

    @Override
    public Object toNative(Object object, ToNativeContext toNativeContext) {
        if (object == null) {
            if (Pointer.class.isAssignableFrom(this.nativeType)) {
                return null;
            }
            object = this.defaultValue();
        }
        return ((NativeMapped)object).toNative();
    }
}

