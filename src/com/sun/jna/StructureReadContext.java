/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import com.sun.jna.Structure;
import java.lang.reflect.Field;

public class StructureReadContext
extends FromNativeContext {
    private Structure structure;
    private Field field;

    public Field getField() {
        return this.field;
    }

    StructureReadContext(Structure structure, Field field) {
        super(field.getType());
        this.structure = structure;
        this.field = field;
    }

    public Structure getStructure() {
        return this.structure;
    }
}

