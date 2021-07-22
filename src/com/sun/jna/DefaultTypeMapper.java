/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeConverter;
import com.sun.jna.ToNativeConverter;
import com.sun.jna.TypeConverter;
import com.sun.jna.TypeMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultTypeMapper
implements TypeMapper {
    private /* synthetic */ List<Entry> fromNativeConverters;
    private /* synthetic */ List<Entry> toNativeConverters;

    @Override
    public ToNativeConverter getToNativeConverter(Class<?> llIlllllIIIllII) {
        DefaultTypeMapper llIlllllIIIlIll;
        return (ToNativeConverter)llIlllllIIIlIll.lookupConverter(llIlllllIIIllII, llIlllllIIIlIll.toNativeConverters);
    }

    public void addTypeConverter(Class<?> llIlllllIlIIIll, TypeConverter llIlllllIlIIIlI) {
        DefaultTypeMapper llIlllllIlIIlII;
        llIlllllIlIIlII.addFromNativeConverter(llIlllllIlIIIll, llIlllllIlIIIlI);
        llIlllllIlIIlII.addToNativeConverter(llIlllllIlIIIll, llIlllllIlIIIlI);
    }

    public void addFromNativeConverter(Class<?> llIlllllIllIIIl, FromNativeConverter llIlllllIllIIII) {
        DefaultTypeMapper llIlllllIlIlllI;
        llIlllllIlIlllI.fromNativeConverters.add(new Entry(llIlllllIllIIIl, llIlllllIllIIII));
        Class<?> llIlllllIlIllll = llIlllllIlIlllI.getAltClass(llIlllllIllIIIl);
        if (llIlllllIlIllll != null) {
            llIlllllIlIlllI.fromNativeConverters.add(new Entry(llIlllllIlIllll, llIlllllIllIIII));
        }
    }

    public void addToNativeConverter(Class<?> llIlllllIlllIIl, ToNativeConverter llIlllllIlllIII) {
        DefaultTypeMapper llIlllllIlllIlI;
        llIlllllIlllIlI.toNativeConverters.add(new Entry(llIlllllIlllIIl, llIlllllIlllIII));
        Class<?> llIlllllIlllIll = llIlllllIlllIlI.getAltClass(llIlllllIlllIIl);
        if (llIlllllIlllIll != null) {
            llIlllllIlllIlI.toNativeConverters.add(new Entry(llIlllllIlllIll, llIlllllIlllIII));
        }
    }

    public DefaultTypeMapper() {
        DefaultTypeMapper llIllllllIIIlll;
        llIllllllIIIlll.toNativeConverters = new ArrayList<Entry>();
        llIllllllIIIlll.fromNativeConverters = new ArrayList<Entry>();
    }

    private Class<?> getAltClass(Class<?> llIllllllIIIlII) {
        if (llIllllllIIIlII == Boolean.class) {
            return Boolean.TYPE;
        }
        if (llIllllllIIIlII == Boolean.TYPE) {
            return Boolean.class;
        }
        if (llIllllllIIIlII == Byte.class) {
            return Byte.TYPE;
        }
        if (llIllllllIIIlII == Byte.TYPE) {
            return Byte.class;
        }
        if (llIllllllIIIlII == Character.class) {
            return Character.TYPE;
        }
        if (llIllllllIIIlII == Character.TYPE) {
            return Character.class;
        }
        if (llIllllllIIIlII == Short.class) {
            return Short.TYPE;
        }
        if (llIllllllIIIlII == Short.TYPE) {
            return Short.class;
        }
        if (llIllllllIIIlII == Integer.class) {
            return Integer.TYPE;
        }
        if (llIllllllIIIlII == Integer.TYPE) {
            return Integer.class;
        }
        if (llIllllllIIIlII == Long.class) {
            return Long.TYPE;
        }
        if (llIllllllIIIlII == Long.TYPE) {
            return Long.class;
        }
        if (llIllllllIIIlII == Float.class) {
            return Float.TYPE;
        }
        if (llIllllllIIIlII == Float.TYPE) {
            return Float.class;
        }
        if (llIllllllIIIlII == Double.class) {
            return Double.TYPE;
        }
        if (llIllllllIIIlII == Double.TYPE) {
            return Double.class;
        }
        return null;
    }

    @Override
    public FromNativeConverter getFromNativeConverter(Class<?> llIlllllIIlIIlI) {
        DefaultTypeMapper llIlllllIIlIIIl;
        return (FromNativeConverter)llIlllllIIlIIIl.lookupConverter(llIlllllIIlIIlI, llIlllllIIlIIIl.fromNativeConverters);
    }

    private Object lookupConverter(Class<?> llIlllllIIllIIl, Collection<? extends Entry> llIlllllIIllIII) {
        for (Entry entry : llIlllllIIllIII) {
            if (!entry.type.isAssignableFrom(llIlllllIIllIIl)) continue;
            return entry.converter;
        }
        return null;
    }

    private static class Entry {
        public /* synthetic */ Object converter;
        public /* synthetic */ Class<?> type;

        public Entry(Class<?> llIlIlIIlllIlIl, Object llIlIlIIlllIIIl) {
            Entry llIlIlIIlllIllI;
            llIlIlIIlllIllI.type = llIlIlIIlllIlIl;
            llIlIlIIlllIllI.converter = llIlIlIIlllIIIl;
        }
    }
}

