/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.TypeMapper;
import com.sun.jna.WString;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class Union
extends Structure {
    private /* synthetic */ Structure.StructField activeField;

    protected Union(Pointer lllllllllllllllllllIIIIIIIlllIlI) {
        super(lllllllllllllllllllIIIIIIIlllIlI);
        Union lllllllllllllllllllIIIIIIIlllIIl;
    }

    public void setType(Class<?> lllllllllllllllllllIIIIIIIIIIllI) {
        Union lllllllllllllllllllIIIIIIIIIIlll;
        lllllllllllllllllllIIIIIIIIIIlll.ensureAllocated();
        for (Structure.StructField lllllllllllllllllllIIIIIIIIIlIlI : lllllllllllllllllllIIIIIIIIIIlll.fields().values()) {
            if (lllllllllllllllllllIIIIIIIIIlIlI.type != lllllllllllllllllllIIIIIIIIIIllI) continue;
            lllllllllllllllllllIIIIIIIIIIlll.activeField = lllllllllllllllllllIIIIIIIIIlIlI;
            return;
        }
        throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No field of type ").append(lllllllllllllllllllIIIIIIIIIIllI).append(" in ").append(lllllllllllllllllllIIIIIIIIIIlll)));
    }

    protected Union() {
        Union lllllllllllllllllllIIIIIIIlllllI;
    }

    public Object setTypedValue(Object llllllllllllllllllIlllllllIlIIll) {
        Union llllllllllllllllllIlllllllIlIlll;
        Structure.StructField llllllllllllllllllIlllllllIlIlIl = llllllllllllllllllIlllllllIlIlll.findField(llllllllllllllllllIlllllllIlIIll.getClass());
        if (llllllllllllllllllIlllllllIlIlIl != null) {
            llllllllllllllllllIlllllllIlIlll.activeField = llllllllllllllllllIlllllllIlIlIl;
            llllllllllllllllllIlllllllIlIlll.setFieldValue(llllllllllllllllllIlllllllIlIlIl.field, llllllllllllllllllIlllllllIlIIll);
            return llllllllllllllllllIlllllllIlIlll;
        }
        throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No field of type ").append(llllllllllllllllllIlllllllIlIIll.getClass()).append(" in ").append(llllllllllllllllllIlllllllIlIlll)));
    }

    protected Union(Pointer lllllllllllllllllllIIIIIIIIlllll, int lllllllllllllllllllIIIIIIIlIIIlI, TypeMapper lllllllllllllllllllIIIIIIIlIIIIl) {
        super(lllllllllllllllllllIIIIIIIIlllll, lllllllllllllllllllIIIIIIIlIIIlI, lllllllllllllllllllIIIIIIIlIIIIl);
        Union lllllllllllllllllllIIIIIIIlIIlII;
    }

    @Override
    public void writeField(String llllllllllllllllllIlllllllllIIIl) {
        Union llllllllllllllllllIlllllllllIIlI;
        llllllllllllllllllIlllllllllIIlI.ensureAllocated();
        llllllllllllllllllIlllllllllIIlI.setType(llllllllllllllllllIlllllllllIIIl);
        super.writeField(llllllllllllllllllIlllllllllIIIl);
    }

    @Override
    protected void writeField(Structure.StructField llllllllllllllllllIlllllllIIIIll) {
        Union llllllllllllllllllIlllllllIIIIlI;
        if (llllllllllllllllllIlllllllIIIIll == llllllllllllllllllIlllllllIIIIlI.activeField) {
            super.writeField(llllllllllllllllllIlllllllIIIIll);
        }
    }

    @Override
    protected int getNativeAlignment(Class<?> llllllllllllllllllIllllllIllIllI, Object llllllllllllllllllIllllllIllIlIl, boolean llllllllllllllllllIllllllIllIlII) {
        Union llllllllllllllllllIllllllIllIIll;
        return super.getNativeAlignment(llllllllllllllllllIllllllIllIllI, llllllllllllllllllIllllllIllIlIl, true);
    }

    @Override
    protected Object readField(Structure.StructField llllllllllllllllllIllllllIlllIll) {
        Union llllllllllllllllllIllllllIllllII;
        if (llllllllllllllllllIllllllIlllIll == llllllllllllllllllIllllllIllllII.activeField || !Structure.class.isAssignableFrom(llllllllllllllllllIllllllIlllIll.type) && !String.class.isAssignableFrom(llllllllllllllllllIllllllIlllIll.type) && !WString.class.isAssignableFrom(llllllllllllllllllIllllllIlllIll.type)) {
            return super.readField(llllllllllllllllllIllllllIlllIll);
        }
        return null;
    }

    public Object getTypedValue(Class<?> llllllllllllllllllIlllllllIlllll) {
        Union llllllllllllllllllIllllllllIIIII;
        llllllllllllllllllIllllllllIIIII.ensureAllocated();
        for (Structure.StructField llllllllllllllllllIllllllllIIIIl : llllllllllllllllllIllllllllIIIII.fields().values()) {
            if (llllllllllllllllllIllllllllIIIIl.type != llllllllllllllllllIlllllllIlllll) continue;
            llllllllllllllllllIllllllllIIIII.activeField = llllllllllllllllllIllllllllIIIIl;
            llllllllllllllllllIllllllllIIIII.read();
            return llllllllllllllllllIllllllllIIIII.getFieldValue(llllllllllllllllllIllllllllIIIII.activeField.field);
        }
        throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No field of type ").append(llllllllllllllllllIlllllllIlllll).append(" in ").append(llllllllllllllllllIllllllllIIIII)));
    }

    private Structure.StructField findField(Class<?> llllllllllllllllllIlllllllIIlIIl) {
        Union llllllllllllllllllIlllllllIIllII;
        llllllllllllllllllIlllllllIIllII.ensureAllocated();
        for (Structure.StructField llllllllllllllllllIlllllllIIllIl : llllllllllllllllllIlllllllIIllII.fields().values()) {
            if (!llllllllllllllllllIlllllllIIllIl.type.isAssignableFrom(llllllllllllllllllIlllllllIIlIIl)) continue;
            return llllllllllllllllllIlllllllIIllIl;
        }
        return null;
    }

    @Override
    protected List<String> getFieldOrder() {
        Union lllllllllllllllllllIIIIIIIIlIIll;
        List<Field> lllllllllllllllllllIIIIIIIIlIlIl = lllllllllllllllllllIIIIIIIIlIIll.getFieldList();
        ArrayList<String> lllllllllllllllllllIIIIIIIIlIlII = new ArrayList<String>(lllllllllllllllllllIIIIIIIIlIlIl.size());
        for (Field lllllllllllllllllllIIIIIIIIlIlll : lllllllllllllllllllIIIIIIIIlIlIl) {
            lllllllllllllllllllIIIIIIIIlIlII.add(lllllllllllllllllllIIIIIIIIlIlll.getName());
        }
        return lllllllllllllllllllIIIIIIIIlIlII;
    }

    protected Union(TypeMapper lllllllllllllllllllIIIIIIIlIlIll) {
        super(lllllllllllllllllllIIIIIIIlIlIll);
        Union lllllllllllllllllllIIIIIIIlIllII;
    }

    @Override
    public Object readField(String llllllllllllllllllIlllllllllIlIl) {
        Union llllllllllllllllllIllllllllllIII;
        llllllllllllllllllIllllllllllIII.ensureAllocated();
        llllllllllllllllllIllllllllllIII.setType(llllllllllllllllllIlllllllllIlIl);
        return super.readField(llllllllllllllllllIlllllllllIlIl);
    }

    @Override
    public void writeField(String llllllllllllllllllIllllllllIlIlI, Object llllllllllllllllllIllllllllIlIIl) {
        Union llllllllllllllllllIllllllllIlIII;
        llllllllllllllllllIllllllllIlIII.ensureAllocated();
        llllllllllllllllllIllllllllIlIII.setType(llllllllllllllllllIllllllllIlIlI);
        super.writeField(llllllllllllllllllIllllllllIlIlI, llllllllllllllllllIllllllllIlIIl);
    }

    protected Union(Pointer lllllllllllllllllllIIIIIIIllIIll, int lllllllllllllllllllIIIIIIIllIIlI) {
        super(lllllllllllllllllllIIIIIIIllIIll, lllllllllllllllllllIIIIIIIllIIlI);
        Union lllllllllllllllllllIIIIIIIllIIIl;
    }

    public void setType(String llllllllllllllllllIlllllllllllll) {
        Union llllllllllllllllllIlllllllllllIl;
        llllllllllllllllllIlllllllllllIl.ensureAllocated();
        Structure.StructField llllllllllllllllllIllllllllllllI = llllllllllllllllllIlllllllllllIl.fields().get(llllllllllllllllllIlllllllllllll);
        if (llllllllllllllllllIllllllllllllI == null) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No field named ").append(llllllllllllllllllIlllllllllllll).append(" in ").append(llllllllllllllllllIlllllllllllIl)));
        }
        llllllllllllllllllIlllllllllllIl.activeField = llllllllllllllllllIllllllllllllI;
    }
}

