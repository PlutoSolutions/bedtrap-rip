/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Structure;
import com.sun.jna.ToNativeContext;
import java.lang.reflect.Field;

public class StructureWriteContext
extends ToNativeContext {
    private Field field;
    private Structure struct;

    public Field getField() {
        return this.field;
    }

    StructureWriteContext(Structure structure, Field field) {
        this.struct = structure;
        this.field = field;
    }

    public Structure getStructure() {
        return this.struct;
    }
}

