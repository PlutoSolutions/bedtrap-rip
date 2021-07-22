/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Structure;
import com.sun.jna.ToNativeContext;
import java.lang.reflect.Field;

public class StructureWriteContext
extends ToNativeContext {
    private /* synthetic */ Field field;
    private /* synthetic */ Structure struct;

    public Field getField() {
        StructureWriteContext llllllllllllllllIllIIIlllllllIlI;
        return llllllllllllllllIllIIIlllllllIlI.field;
    }

    StructureWriteContext(Structure llllllllllllllllIllIIlIIIIIIIIII, Field llllllllllllllllIllIIIllllllllll) {
        StructureWriteContext llllllllllllllllIllIIlIIIIIIIIIl;
        llllllllllllllllIllIIlIIIIIIIIIl.struct = llllllllllllllllIllIIlIIIIIIIIII;
        llllllllllllllllIllIIlIIIIIIIIIl.field = llllllllllllllllIllIIIllllllllll;
    }

    public Structure getStructure() {
        StructureWriteContext llllllllllllllllIllIIIllllllllII;
        return llllllllllllllllIllIIIllllllllII.struct;
    }
}

