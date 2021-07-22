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
    private List<Entry> fromNativeConverters;
    private List<Entry> toNativeConverters = new ArrayList<Entry>();

    @Override
    public ToNativeConverter getToNativeConverter(Class<?> class_) {
        return (ToNativeConverter)this.lookupConverter(class_, this.toNativeConverters);
    }

    public void addTypeConverter(Class<?> class_, TypeConverter typeConverter) {
        this.addFromNativeConverter(class_, typeConverter);
        this.addToNativeConverter(class_, typeConverter);
    }

    public void addFromNativeConverter(Class<?> class_, FromNativeConverter fromNativeConverter) {
        this.fromNativeConverters.add(new Entry(class_, fromNativeConverter));
        Class<?> class_2 = this.getAltClass(class_);
        if (class_2 != null) {
            this.fromNativeConverters.add(new Entry(class_2, fromNativeConverter));
        }
    }

    public void addToNativeConverter(Class<?> class_, ToNativeConverter toNativeConverter) {
        this.toNativeConverters.add(new Entry(class_, toNativeConverter));
        Class<?> class_2 = this.getAltClass(class_);
        if (class_2 != null) {
            this.toNativeConverters.add(new Entry(class_2, toNativeConverter));
        }
    }

    public DefaultTypeMapper() {
        this.fromNativeConverters = new ArrayList<Entry>();
    }

    private Class<?> getAltClass(Class<?> class_) {
        if (class_ == Boolean.class) {
            return Boolean.TYPE;
        }
        if (class_ == Boolean.TYPE) {
            return Boolean.class;
        }
        if (class_ == Byte.class) {
            return Byte.TYPE;
        }
        if (class_ == Byte.TYPE) {
            return Byte.class;
        }
        if (class_ == Character.class) {
            return Character.TYPE;
        }
        if (class_ == Character.TYPE) {
            return Character.class;
        }
        if (class_ == Short.class) {
            return Short.TYPE;
        }
        if (class_ == Short.TYPE) {
            return Short.class;
        }
        if (class_ == Integer.class) {
            return Integer.TYPE;
        }
        if (class_ == Integer.TYPE) {
            return Integer.class;
        }
        if (class_ == Long.class) {
            return Long.TYPE;
        }
        if (class_ == Long.TYPE) {
            return Long.class;
        }
        if (class_ == Float.class) {
            return Float.TYPE;
        }
        if (class_ == Float.TYPE) {
            return Float.class;
        }
        if (class_ == Double.class) {
            return Double.TYPE;
        }
        if (class_ == Double.TYPE) {
            return Double.class;
        }
        return null;
    }

    @Override
    public FromNativeConverter getFromNativeConverter(Class<?> class_) {
        return (FromNativeConverter)this.lookupConverter(class_, this.fromNativeConverters);
    }

    private Object lookupConverter(Class<?> class_, Collection<? extends Entry> collection) {
        for (Entry entry : collection) {
            if (!entry.type.isAssignableFrom(class_)) continue;
            return entry.converter;
        }
        return null;
    }

    private static class Entry {
        public Object converter;
        public Class<?> type;

        public Entry(Class<?> class_, Object object) {
            this.type = class_;
            this.converter = object;
        }
    }
}

