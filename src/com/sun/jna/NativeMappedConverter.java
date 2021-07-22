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
    private final /* synthetic */ NativeMapped instance;
    private static final /* synthetic */ Map<Class<?>, Reference<NativeMappedConverter>> converters;
    private final /* synthetic */ Class<?> nativeType;
    private final /* synthetic */ Class<?> type;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static NativeMappedConverter getInstance(Class<?> lllllllllllllllllIIlIlIlIIIlIlII) {
        Map<Class<?>, Reference<NativeMappedConverter>> lllllllllllllllllIIlIlIlIIIlIIlI = converters;
        synchronized (lllllllllllllllllIIlIlIlIIIlIIlI) {
            NativeMappedConverter lllllllllllllllllIIlIlIlIIIlIlIl;
            Reference<NativeMappedConverter> lllllllllllllllllIIlIlIlIIIlIllI = converters.get(lllllllllllllllllIIlIlIlIIIlIlII);
            NativeMappedConverter nativeMappedConverter = lllllllllllllllllIIlIlIlIIIlIlIl = lllllllllllllllllIIlIlIlIIIlIllI != null ? lllllllllllllllllIIlIlIlIIIlIllI.get() : null;
            if (lllllllllllllllllIIlIlIlIIIlIlIl == null) {
                lllllllllllllllllIIlIlIlIIIlIlIl = new NativeMappedConverter(lllllllllllllllllIIlIlIlIIIlIlII);
                converters.put(lllllllllllllllllIIlIlIlIIIlIlII, new SoftReference<NativeMappedConverter>(lllllllllllllllllIIlIlIlIIIlIlIl));
            }
            return lllllllllllllllllIIlIlIlIIIlIlIl;
        }
    }

    @Override
    public Class<?> nativeType() {
        NativeMappedConverter lllllllllllllllllIIlIlIIllllIIll;
        return lllllllllllllllllIIlIlIIllllIIll.nativeType;
    }

    public NativeMappedConverter(Class<?> lllllllllllllllllIIlIlIlIIIIlIIl) {
        NativeMappedConverter lllllllllllllllllIIlIlIlIIIIlIlI;
        if (!NativeMapped.class.isAssignableFrom(lllllllllllllllllIIlIlIlIIIIlIIl)) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Type must derive from ").append(NativeMapped.class)));
        }
        lllllllllllllllllIIlIlIlIIIIlIlI.type = lllllllllllllllllIIlIlIlIIIIlIIl;
        lllllllllllllllllIIlIlIlIIIIlIlI.instance = lllllllllllllllllIIlIlIlIIIIlIlI.defaultValue();
        lllllllllllllllllIIlIlIlIIIIlIlI.nativeType = lllllllllllllllllIIlIlIlIIIIlIlI.instance.nativeType();
    }

    @Override
    public Object fromNative(Object lllllllllllllllllIIlIlIIllllIllI, FromNativeContext lllllllllllllllllIIlIlIIllllIlIl) {
        NativeMappedConverter lllllllllllllllllIIlIlIIlllllIlI;
        return lllllllllllllllllIIlIlIIlllllIlI.instance.fromNative(lllllllllllllllllIIlIlIIllllIllI, lllllllllllllllllIIlIlIIllllIlIl);
    }

    static {
        converters = new WeakHashMap();
    }

    public NativeMapped defaultValue() {
        NativeMappedConverter lllllllllllllllllIIlIlIlIIIIIIII;
        try {
            return (NativeMapped)lllllllllllllllllIIlIlIlIIIIIIII.type.newInstance();
        }
        catch (InstantiationException lllllllllllllllllIIlIlIlIIIIIlII) {
            String lllllllllllllllllIIlIlIlIIIIIlIl = String.valueOf(new StringBuilder().append("Can't create an instance of ").append(lllllllllllllllllIIlIlIlIIIIIIII.type).append(", requires a no-arg constructor: ").append(lllllllllllllllllIIlIlIlIIIIIlII));
            throw new IllegalArgumentException(lllllllllllllllllIIlIlIlIIIIIlIl);
        }
        catch (IllegalAccessException lllllllllllllllllIIlIlIlIIIIIIlI) {
            String lllllllllllllllllIIlIlIlIIIIIIll = String.valueOf(new StringBuilder().append("Not allowed to create an instance of ").append(lllllllllllllllllIIlIlIlIIIIIIII.type).append(", requires a public, no-arg constructor: ").append(lllllllllllllllllIIlIlIlIIIIIIlI));
            throw new IllegalArgumentException(lllllllllllllllllIIlIlIlIIIIIIll);
        }
    }

    @Override
    public Object toNative(Object lllllllllllllllllIIlIlIIlllIlllI, ToNativeContext lllllllllllllllllIIlIlIIlllIllIl) {
        if (lllllllllllllllllIIlIlIIlllIlllI == null) {
            NativeMappedConverter lllllllllllllllllIIlIlIIlllIllll;
            if (Pointer.class.isAssignableFrom(lllllllllllllllllIIlIlIIlllIllll.nativeType)) {
                return null;
            }
            lllllllllllllllllIIlIlIIlllIlllI = lllllllllllllllllIIlIlIIlllIllll.defaultValue();
        }
        return ((NativeMapped)lllllllllllllllllIIlIlIIlllIlllI).toNative();
    }
}

