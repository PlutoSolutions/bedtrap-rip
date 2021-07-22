/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.FromNativeContext;
import com.sun.jna.Structure;
import java.lang.reflect.Field;

public class StructureReadContext
extends FromNativeContext {
    private /* synthetic */ Structure structure;
    private /* synthetic */ Field field;

    public Field getField() {
        StructureReadContext lllllllllllllllllllIIlIlIlIIlIIl;
        return lllllllllllllllllllIIlIlIlIIlIIl.field;
    }

    StructureReadContext(Structure lllllllllllllllllllIIlIlIlIlIIII, Field lllllllllllllllllllIIlIlIlIlIIlI) {
        super(lllllllllllllllllllIIlIlIlIlIIlI.getType());
        StructureReadContext lllllllllllllllllllIIlIlIlIlIIIl;
        lllllllllllllllllllIIlIlIlIlIIIl.structure = lllllllllllllllllllIIlIlIlIlIIII;
        lllllllllllllllllllIIlIlIlIlIIIl.field = lllllllllllllllllllIIlIlIlIlIIlI;
    }

    public Structure getStructure() {
        StructureReadContext lllllllllllllllllllIIlIlIlIIllIl;
        return lllllllllllllllllllIIlIlIlIIllIl.structure;
    }
}

