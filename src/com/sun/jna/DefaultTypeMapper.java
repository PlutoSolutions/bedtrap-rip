/*
 * Decompiled with CFR 0.151.
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
    private List<Entry> fromNativeConverters;
    private List<Entry> toNativeConverters = new ArrayList<Entry>();

    @Override
    public ToNativeConverter getToNativeConverter(Class<?> clazz) {
        return (ToNativeConverter)this.lookupConverter(clazz, this.toNativeConverters);
    }

    public void addTypeConverter(Class<?> clazz, TypeConverter typeConverter) {
        this.addFromNativeConverter(clazz, typeConverter);
        this.addToNativeConverter(clazz, typeConverter);
    }

    public void addFromNativeConverter(Class<?> clazz, FromNativeConverter fromNativeConverter) {
        this.fromNativeConverters.add(new Entry(clazz, fromNativeConverter));
        Class<?> clazz2 = this.getAltClass(clazz);
        if (clazz2 != null) {
            this.fromNativeConverters.add(new Entry(clazz2, fromNativeConverter));
        }
    }

    public void addToNativeConverter(Class<?> clazz, ToNativeConverter toNativeConverter) {
        this.toNativeConverters.add(new Entry(clazz, toNativeConverter));
        Class<?> clazz2 = this.getAltClass(clazz);
        if (clazz2 != null) {
            this.toNativeConverters.add(new Entry(clazz2, toNativeConverter));
        }
    }

    public DefaultTypeMapper() {
        this.fromNativeConverters = new ArrayList<Entry>();
    }

    private Class<?> getAltClass(Class<?> clazz) {
        if (clazz == Boolean.class) {
            return Boolean.TYPE;
        }
        if (clazz == Boolean.TYPE) {
            return Boolean.class;
        }
        if (clazz == Byte.class) {
            return Byte.TYPE;
        }
        if (clazz == Byte.TYPE) {
            return Byte.class;
        }
        if (clazz == Character.class) {
            return Character.TYPE;
        }
        if (clazz == Character.TYPE) {
            return Character.class;
        }
        if (clazz == Short.class) {
            return Short.TYPE;
        }
        if (clazz == Short.TYPE) {
            return Short.class;
        }
        if (clazz == Integer.class) {
            return Integer.TYPE;
        }
        if (clazz == Integer.TYPE) {
            return Integer.class;
        }
        if (clazz == Long.class) {
            return Long.TYPE;
        }
        if (clazz == Long.TYPE) {
            return Long.class;
        }
        if (clazz == Float.class) {
            return Float.TYPE;
        }
        if (clazz == Float.TYPE) {
            return Float.class;
        }
        if (clazz == Double.class) {
            return Double.TYPE;
        }
        if (clazz == Double.TYPE) {
            return Double.class;
        }
        return null;
    }

    @Override
    public FromNativeConverter getFromNativeConverter(Class<?> clazz) {
        return (FromNativeConverter)this.lookupConverter(clazz, this.fromNativeConverters);
    }

    private Object lookupConverter(Class<?> clazz, Collection<? extends Entry> collection) {
        for (Entry entry : collection) {
            if (!entry.type.isAssignableFrom(clazz)) continue;
            return entry.converter;
        }
        return null;
    }

    private static class Entry {
        public Object converter;
        public Class<?> type;

        public Entry(Class<?> clazz, Object object) {
            this.type = clazz;
            this.converter = object;
        }
    }
}

