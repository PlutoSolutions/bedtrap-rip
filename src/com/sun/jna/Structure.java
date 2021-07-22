/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Callback;
import com.sun.jna.FromNativeContext;
import com.sun.jna.FromNativeConverter;
import com.sun.jna.Function;
import com.sun.jna.IntegerType;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeMapped;
import com.sun.jna.NativeMappedConverter;
import com.sun.jna.NativeString;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.StructureReadContext;
import com.sun.jna.StructureWriteContext;
import com.sun.jna.ToNativeContext;
import com.sun.jna.ToNativeConverter;
import com.sun.jna.TypeMapper;
import com.sun.jna.Union;
import com.sun.jna.WString;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public abstract class Structure {
    static final /* synthetic */ Map<Class<?>, List<String>> fieldOrder;
    protected static final /* synthetic */ int CALCULATE_SIZE;
    private static final /* synthetic */ ThreadLocal<Map<Pointer, Structure>> reads;
    private /* synthetic */ long typeInfo;
    private /* synthetic */ String encoding;
    private final /* synthetic */ Map<String, Object> nativeStrings;
    private /* synthetic */ Map<String, StructField> structFields;
    private /* synthetic */ int actualAlignType;
    private /* synthetic */ TypeMapper typeMapper;
    private /* synthetic */ boolean readCalled;
    private static final /* synthetic */ Pointer PLACEHOLDER_MEMORY;
    public static final /* synthetic */ int ALIGN_MSVC;
    static final /* synthetic */ Map<Class<?>, LayoutInfo> layoutInfo;
    public static final /* synthetic */ int ALIGN_DEFAULT;
    private /* synthetic */ Structure[] array;
    private /* synthetic */ boolean autoRead;
    private static final /* synthetic */ ThreadLocal<Set<Structure>> busy;
    public static final /* synthetic */ int ALIGN_NONE;
    private /* synthetic */ int alignType;
    private /* synthetic */ int structAlignment;
    private /* synthetic */ Pointer memory;
    public static final /* synthetic */ int ALIGN_GNUC;
    private /* synthetic */ int size;
    private /* synthetic */ boolean autoWrite;

    public String toString() {
        Structure lllIIIllll;
        return lllIIIllll.toString(Boolean.getBoolean("jna.dump_memory"));
    }

    Pointer getFieldTypeInfo(StructField llIIIIlIll) {
        ToNativeConverter llIIIIllIl;
        Structure llIIIIllII;
        Class<?> llIIIIlIlI = llIIIIlIll.type;
        Object llIIIIlIIl = llIIIIllII.getFieldValue(llIIIIlIll.field);
        if (llIIIIllII.typeMapper != null && (llIIIIllIl = llIIIIllII.typeMapper.getToNativeConverter(llIIIIlIlI)) != null) {
            llIIIIlIlI = llIIIIllIl.nativeType();
            llIIIIlIIl = llIIIIllIl.toNative(llIIIIlIIl, new ToNativeContext());
        }
        return FFIType.get(llIIIIlIIl, llIIIIlIlI);
    }

    private void ensureAllocated(boolean lIllIIIIIIl) {
        Structure lIllIIIIIII;
        if (lIllIIIIIII.memory == null) {
            lIllIIIIIII.allocateMemory(lIllIIIIIIl);
        } else if (lIllIIIIIII.size == -1) {
            lIllIIIIIII.size = lIllIIIIIII.calculateSize(true, lIllIIIIIIl);
            if (!(lIllIIIIIII.memory instanceof AutoAllocated)) {
                try {
                    lIllIIIIIII.memory = lIllIIIIIII.memory.share(0L, lIllIIIIIII.size);
                }
                catch (IndexOutOfBoundsException lIllIIIIIll) {
                    throw new IllegalArgumentException("Structure exceeds provided memory bounds", lIllIIIIIll);
                }
            }
        }
    }

    public boolean getAutoRead() {
        Structure lIlllIllll;
        return lIlllIllll.autoRead;
    }

    private void allocateMemory(boolean lIlIlllIlIl) {
        Structure lIlIllllIII;
        lIlIllllIII.allocateMemory(lIlIllllIII.calculateSize(true, lIlIlllIlIl));
    }

    public void setAutoSynch(boolean lIlllllIlI) {
        Structure lIlllllIIl;
        lIlllllIIl.setAutoRead(lIlllllIlI);
        lIlllllIIl.setAutoWrite(lIlllllIlI);
    }

    protected List<Field> getFields(boolean lIIIllIlIlI) {
        Structure lIIIllIIlIl;
        List<Field> lIIIllIlIIl = lIIIllIIlIl.getFieldList();
        HashSet<String> lIIIllIlIII = new HashSet<String>();
        for (Field lIIIllIllII : lIIIllIlIIl) {
            lIIIllIlIII.add(lIIIllIllII.getName());
        }
        List<String> lIIIllIIlll = lIIIllIIlIl.fieldOrder();
        if (lIIIllIIlll.size() != lIIIllIlIIl.size() && lIIIllIlIIl.size() > 1) {
            if (lIIIllIlIlI) {
                throw new Error(String.valueOf(new StringBuilder().append("Structure.getFieldOrder() on ").append(lIIIllIIlIl.getClass()).append(" does not provide enough names [").append(lIIIllIIlll.size()).append("] (").append(Structure.sort(lIIIllIIlll)).append(") to match declared fields [").append(lIIIllIlIIl.size()).append("] (").append(Structure.sort(lIIIllIlIII)).append(")")));
            }
            return null;
        }
        HashSet<String> lIIIllIIllI = new HashSet<String>(lIIIllIIlll);
        if (!lIIIllIIllI.equals(lIIIllIlIII)) {
            throw new Error(String.valueOf(new StringBuilder().append("Structure.getFieldOrder() on ").append(lIIIllIIlIl.getClass()).append(" returns names (").append(Structure.sort(lIIIllIIlll)).append(") which do not match declared field names (").append(Structure.sort(lIIIllIlIII)).append(")")));
        }
        lIIIllIIlIl.sortFields(lIIIllIlIIl, lIIIllIIlll);
        return lIIIllIlIIl;
    }

    private int addPadding(int lllIllIIll) {
        Structure lllIllIlII;
        return lllIllIlII.addPadding(lllIllIIll, lllIllIlII.structAlignment);
    }

    public static void autoRead(Structure[] lIlIIlllII) {
        Structure.structureArrayCheck(lIlIIlllII);
        if (lIlIIlllII[0].array == lIlIIlllII) {
            lIlIIlllII[0].autoRead();
        } else {
            for (int lIlIIllllI = 0; lIlIIllllI < lIlIIlllII.length; ++lIlIIllllI) {
                if (lIlIIlllII[lIlIIllllI] == null) continue;
                lIlIIlllII[lIlIIllllI].autoRead();
            }
        }
    }

    public Object readField(String lIlIlIIlIII) {
        Structure lIlIlIIllII;
        lIlIlIIllII.ensureAllocated();
        StructField lIlIlIIlIlI = lIlIlIIllII.fields().get(lIlIlIIlIII);
        if (lIlIlIIlIlI == null) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No such field: ").append(lIlIlIIlIII)));
        }
        return lIlIlIIllII.readField(lIlIlIIlIlI);
    }

    protected void allocateMemory(int lIlIllIllll) {
        Structure lIlIlllIIlI;
        if (lIlIllIllll == -1) {
            lIlIllIllll = lIlIlllIIlI.calculateSize(false);
        } else if (lIlIllIllll <= 0) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Structure size must be greater than zero: ").append(lIlIllIllll)));
        }
        if (lIlIllIllll != -1) {
            if (lIlIlllIIlI.memory == null || lIlIlllIIlI.memory instanceof AutoAllocated) {
                lIlIlllIIlI.memory = lIlIlllIIlI.autoAllocate(lIlIllIllll);
            }
            lIlIlllIIlI.size = lIlIllIllll;
        }
    }

    public void read() {
        Structure lIlIlIlllIl;
        if (lIlIlIlllIl.memory == PLACEHOLDER_MEMORY) {
            return;
        }
        lIlIlIlllIl.readCalled = true;
        lIlIlIlllIl.ensureAllocated();
        if (Structure.busy().contains(lIlIlIlllIl)) {
            return;
        }
        Structure.busy().add(lIlIlIlllIl);
        if (lIlIlIlllIl instanceof ByReference) {
            Structure.reading().put(lIlIlIlllIl.getPointer(), lIlIlIlllIl);
        }
        try {
            for (StructField lIlIlIllllI : lIlIlIlllIl.fields().values()) {
                lIlIlIlllIl.readField(lIlIlIllllI);
            }
        }
        finally {
            Structure.busy().remove(lIlIlIlllIl);
            if (Structure.reading().get(lIlIlIlllIl.getPointer()) == lIlIlIlllIl) {
                Structure.reading().remove(lIlIlIlllIl.getPointer());
            }
        }
    }

    protected void cacheTypeInfo(Pointer llIIIlIlIl) {
        llIIIlIllI.typeInfo = llIIIlIlIl.peer;
    }

    @Deprecated
    protected final void setFieldOrder(String[] lIIllIIIIll) {
        throw new Error("This method is obsolete, use getFieldOrder() instead");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    static int size(Class<?> lIIIlIlIIII, Structure lIIIlIIlIll) {
        void lIIIlIIlllI;
        int lIIIlIIllIl;
        Map<Class<?>, LayoutInfo> lIIIlIIlIIl = layoutInfo;
        synchronized (lIIIlIIlIIl) {
            LayoutInfo lIIIlIlIIIl = layoutInfo.get(lIIIlIlIIII);
        }
        int n = lIIIlIIllIl = lIIIlIIlllI != null && !((LayoutInfo)lIIIlIIlllI).variable ? ((LayoutInfo)lIIIlIIlllI).size : -1;
        if (lIIIlIIllIl == -1) {
            if (lIIIlIIlIll == null) {
                lIIIlIIlIll = Structure.newInstance(lIIIlIlIIII, PLACEHOLDER_MEMORY);
            }
            lIIIlIIllIl = lIIIlIIlIll.size();
        }
        return lIIIlIIllIl;
    }

    static {
        ALIGN_NONE = 1;
        ALIGN_GNUC = 2;
        ALIGN_MSVC = 3;
        ALIGN_DEFAULT = 0;
        CALCULATE_SIZE = -1;
        layoutInfo = new WeakHashMap();
        fieldOrder = new WeakHashMap();
        reads = new ThreadLocal<Map<Pointer, Structure>>(){
            {
                1 lllllllllllllllllIIlIIlIIlIIIIll;
            }

            @Override
            protected synchronized Map<Pointer, Structure> initialValue() {
                return new HashMap<Pointer, Structure>();
            }
        };
        busy = new ThreadLocal<Set<Structure>>(){

            @Override
            protected synchronized Set<Structure> initialValue() {
                return new StructureSet();
            }
            {
                2 llllllllllllllllllIllllIIIllIlIl;
            }
        };
        PLACEHOLDER_MEMORY = new Pointer(0L){

            @Override
            public Pointer share(long llllllllllllllllIlIlllllllIlIllI, long llllllllllllllllIlIlllllllIlIlIl) {
                3 llllllllllllllllIlIlllllllIlIlll;
                return llllllllllllllllIlIlllllllIlIlll;
            }
            {
                3 llllllllllllllllIlIlllllllIlllII;
            }
        };
    }

    protected int getStructAlignment() {
        Structure lllIlIIllI;
        if (lllIlIIllI.size == -1) {
            lllIlIIllI.calculateSize(true);
        }
        return lllIlIIllI.structAlignment;
    }

    protected Structure() {
        lIllllllIll(0);
        Structure lIllllllIll;
    }

    private int addPadding(int lllIlIllII, int lllIlIlIII) {
        Structure lllIlIlIlI;
        if (lllIlIlIlI.actualAlignType != 1 && lllIlIllII % lllIlIlIII != 0) {
            lllIlIllII += lllIlIlIII - lllIlIllII % lllIlIlIII;
        }
        return lllIlIllII;
    }

    private void initializeFields() {
        Structure llllIIlllI;
        List<Field> llllIIllll = llllIIlllI.getFieldList();
        for (Field llllIlIIIl : llllIIllll) {
            try {
                Object llllIlIIll = llllIlIIIl.get(llllIIlllI);
                if (llllIlIIll != null) continue;
                llllIIlllI.initializeField(llllIlIIIl, llllIlIIIl.getType());
            }
            catch (Exception llllIlIIlI) {
                throw new Error(String.valueOf(new StringBuilder().append("Exception reading field '").append(llllIlIIIl.getName()).append("' in ").append(llllIIlllI.getClass())), llllIlIIlI);
            }
        }
    }

    private String toString(int llIllIIlll, boolean llIllIIllI, boolean llIllIIlIl) {
        Structure llIllIlIII;
        llIllIlIII.ensureAllocated();
        String llIllIIlII = System.getProperty("line.separator");
        String llIllIIIll = String.valueOf(new StringBuilder().append(llIllIlIII.format(llIllIlIII.getClass())).append("(").append(llIllIlIII.getPointer()).append(")"));
        if (!(llIllIlIII.getPointer() instanceof Memory)) {
            llIllIIIll = String.valueOf(new StringBuilder().append(llIllIIIll).append(" (").append(llIllIlIII.size()).append(" bytes)"));
        }
        String llIllIIIlI = "";
        for (int llIlllIIIl = 0; llIlllIIIl < llIllIIlll; ++llIlllIIIl) {
            llIllIIIlI = String.valueOf(new StringBuilder().append(llIllIIIlI).append("  "));
        }
        String llIllIIIIl = llIllIIlII;
        if (!llIllIIllI) {
            llIllIIIIl = "...}";
        } else {
            Iterator<StructField> llIllIllII = llIllIlIII.fields().values().iterator();
            while (llIllIllII.hasNext()) {
                StructField llIlllIIII = llIllIllII.next();
                Object llIllIllll = llIllIlIII.getFieldValue(llIlllIIII.field);
                String llIllIlllI = llIllIlIII.format(llIlllIIII.type);
                String llIllIllIl = "";
                llIllIIIIl = String.valueOf(new StringBuilder().append(llIllIIIIl).append(llIllIIIlI));
                if (llIlllIIII.type.isArray() && llIllIllll != null) {
                    llIllIlllI = llIllIlIII.format(llIlllIIII.type.getComponentType());
                    llIllIllIl = String.valueOf(new StringBuilder().append("[").append(Array.getLength(llIllIllll)).append("]"));
                }
                llIllIIIIl = String.valueOf(new StringBuilder().append(llIllIIIIl).append("  ").append(llIllIlllI).append(" ").append(llIlllIIII.name).append(llIllIllIl).append("@").append(Integer.toHexString(llIlllIIII.offset)));
                if (llIllIllll instanceof Structure) {
                    llIllIllll = ((Structure)llIllIllll).toString(llIllIIlll + 1, !(llIllIllll instanceof ByReference), llIllIIlIl);
                }
                llIllIIIIl = String.valueOf(new StringBuilder().append(llIllIIIIl).append("="));
                llIllIIIIl = llIllIllll instanceof Long ? String.valueOf(new StringBuilder().append(llIllIIIIl).append(Long.toHexString((Long)llIllIllll))) : (llIllIllll instanceof Integer ? String.valueOf(new StringBuilder().append(llIllIIIIl).append(Integer.toHexString((Integer)llIllIllll))) : (llIllIllll instanceof Short ? String.valueOf(new StringBuilder().append(llIllIIIIl).append(Integer.toHexString(((Short)llIllIllll).shortValue()))) : (llIllIllll instanceof Byte ? String.valueOf(new StringBuilder().append(llIllIIIIl).append(Integer.toHexString(((Byte)llIllIllll).byteValue()))) : String.valueOf(new StringBuilder().append(llIllIIIIl).append(String.valueOf(llIllIllll).trim())))));
                llIllIIIIl = String.valueOf(new StringBuilder().append(llIllIIIIl).append(llIllIIlII));
                if (llIllIllII.hasNext()) continue;
                llIllIIIIl = String.valueOf(new StringBuilder().append(llIllIIIIl).append(llIllIIIlI).append("}"));
            }
        }
        if (llIllIIlll == 0 && llIllIIlIl) {
            int llIllIlIlI = 4;
            llIllIIIIl = String.valueOf(new StringBuilder().append(llIllIIIIl).append(llIllIIlII).append("memory dump").append(llIllIIlII));
            byte[] llIllIlIIl = llIllIlIII.getPointer().getByteArray(0L, llIllIlIII.size());
            for (int llIllIlIll = 0; llIllIlIll < llIllIlIIl.length; ++llIllIlIll) {
                if (llIllIlIll % 4 == 0) {
                    llIllIIIIl = String.valueOf(new StringBuilder().append(llIllIIIIl).append("["));
                }
                if (llIllIlIIl[llIllIlIll] >= 0 && llIllIlIIl[llIllIlIll] < 16) {
                    llIllIIIIl = String.valueOf(new StringBuilder().append(llIllIIIIl).append("0"));
                }
                llIllIIIIl = String.valueOf(new StringBuilder().append(llIllIIIIl).append(Integer.toHexString(llIllIlIIl[llIllIlIll] & 0xFF)));
                if (llIllIlIll % 4 != 3 || llIllIlIll >= llIllIlIIl.length - 1) continue;
                llIllIIIIl = String.valueOf(new StringBuilder().append(llIllIIIIl).append("]").append(llIllIIlII));
            }
            llIllIIIIl = String.valueOf(new StringBuilder().append(llIllIIIIl).append("]"));
        }
        return String.valueOf(new StringBuilder().append(llIllIIIll).append(" {").append(llIllIIIIl));
    }

    protected Structure(Pointer lIlllIllIII, int lIlllIllIlI) {
        lIlllIlllII(lIlllIllIII, lIlllIllIlI, null);
        Structure lIlllIlllII;
    }

    public static Structure newInstance(Class<?> lIllIIlIlI, Pointer lIllIIllII) throws IllegalArgumentException {
        try {
            Constructor<?> lIllIlIlII = lIllIIlIlI.getConstructor(Pointer.class);
            return (Structure)lIllIlIlII.newInstance(lIllIIllII);
        }
        catch (NoSuchMethodException lIllIlIlII) {
        }
        catch (SecurityException lIllIlIlII) {
        }
        catch (InstantiationException lIllIlIIlI) {
            String lIllIlIIll = String.valueOf(new StringBuilder().append("Can't instantiate ").append(lIllIIlIlI));
            throw new IllegalArgumentException(lIllIlIIll, lIllIlIIlI);
        }
        catch (IllegalAccessException lIllIlIIII) {
            String lIllIlIIIl = String.valueOf(new StringBuilder().append("Instantiation of ").append(lIllIIlIlI).append(" (Pointer) not allowed, is it public?"));
            throw new IllegalArgumentException(lIllIlIIIl, lIllIlIIII);
        }
        catch (InvocationTargetException lIllIIlllI) {
            String lIllIIllll = String.valueOf(new StringBuilder().append("Exception thrown while instantiating an instance of ").append(lIllIIlIlI));
            lIllIIlllI.printStackTrace();
            throw new IllegalArgumentException(lIllIIllll, lIllIIlllI);
        }
        Structure lIllIIlIll = Structure.newInstance(lIllIIlIlI);
        if (lIllIIllII != PLACEHOLDER_MEMORY) {
            lIllIIlIll.useMemory(lIllIIllII);
        }
        return lIllIIlIll;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private List<String> fieldOrder() {
        Structure lIIlIIlIlII;
        Class<?> lIIlIIlIIll = lIIlIIlIlII.getClass();
        Map<Class<?>, List<String>> lIIlIIlIIII = fieldOrder;
        synchronized (lIIlIIlIIII) {
            List<String> lIIlIIlIlIl = fieldOrder.get(lIIlIIlIIll);
            if (lIIlIIlIlIl == null) {
                lIIlIIlIlIl = lIIlIIlIlII.getFieldOrder();
                fieldOrder.put(lIIlIIlIIll, lIIlIIlIlIl);
            }
            return lIIlIIlIlIl;
        }
    }

    public boolean equals(Object llIIlIIIIl) {
        Structure llIIlIIIlI;
        return llIIlIIIIl instanceof Structure && llIIlIIIIl.getClass() == llIIlIIIlI.getClass() && ((Structure)llIIlIIIIl).getPointer().equals(llIIlIIIlI.getPointer());
    }

    protected void sortFields(List<Field> lIIlIllIlll, List<String> lIIlIllIllI) {
        block0: for (int lIIlIlllIIl = 0; lIIlIlllIIl < lIIlIllIllI.size(); ++lIIlIlllIIl) {
            String lIIlIlllIlI = lIIlIllIllI.get(lIIlIlllIIl);
            for (int lIIlIlllIll = 0; lIIlIlllIll < lIIlIllIlll.size(); ++lIIlIlllIll) {
                Field lIIlIllllII = lIIlIllIlll.get(lIIlIlllIll);
                if (!lIIlIlllIlI.equals(lIIlIllllII.getName())) continue;
                Collections.swap(lIIlIllIlll, lIIlIlllIIl, lIIlIlllIll);
                continue block0;
            }
        }
    }

    public void clear() {
        Structure lIlIllIlIIl;
        lIlIllIlIIl.ensureAllocated();
        lIlIllIlIIl.memory.clear(lIlIllIlIIl.size());
    }

    public boolean dataEquals(Structure llIIlIlllI, boolean llIIlIllIl) {
        byte[] llIIlIlIll;
        byte[] llIIlIllII;
        Structure llIIlIlIlI;
        if (llIIlIllIl) {
            llIIlIlllI.getPointer().clear(llIIlIlllI.size());
            llIIlIlllI.write();
            llIIlIlIlI.getPointer().clear(llIIlIlIlI.size());
            llIIlIlIlI.write();
        }
        if ((llIIlIllII = llIIlIlllI.getPointer().getByteArray(0L, llIIlIlllI.size())).length == (llIIlIlIll = llIIlIlIlI.getPointer().getByteArray(0L, llIIlIlIlI.size())).length) {
            for (int llIIllIIII = 0; llIIllIIII < llIIlIllII.length; ++llIIllIIII) {
                if (llIIlIllII[llIIllIIII] == llIIlIlIll[llIIllIIII]) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    static Map<Pointer, Structure> reading() {
        return reads.get();
    }

    private LayoutInfo deriveLayout(boolean lllllIlIIl, boolean lllllIllll) {
        Structure lllllIlIlI;
        int lllllIlllI = 0;
        List<Field> lllllIllIl = lllllIlIlI.getFields(lllllIlIIl);
        if (lllllIllIl == null) {
            return null;
        }
        LayoutInfo lllllIllII = new LayoutInfo();
        lllllIllII.alignType = lllllIlIlI.alignType;
        lllllIllII.typeMapper = lllllIlIlI.typeMapper;
        boolean lllllIlIll = true;
        for (Field lllllllIlI : lllllIllIl) {
            int lllllllIIl = lllllllIlI.getModifiers();
            Class<?> lllllllIII = lllllllIlI.getType();
            if (lllllllIII.isArray()) {
                lllllIllII.variable = true;
            }
            StructField llllllIlll = new StructField();
            llllllIlll.isVolatile = Modifier.isVolatile(lllllllIIl);
            llllllIlll.isReadOnly = Modifier.isFinal(lllllllIIl);
            if (llllllIlll.isReadOnly) {
                if (!Platform.RO_FIELDS) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("This VM does not support read-only fields (field '").append(lllllllIlI.getName()).append("' within ").append(lllllIlIlI.getClass()).append(")")));
                }
                lllllllIlI.setAccessible(true);
            }
            llllllIlll.field = lllllllIlI;
            llllllIlll.name = lllllllIlI.getName();
            llllllIlll.type = lllllllIII;
            if (Callback.class.isAssignableFrom(lllllllIII) && !lllllllIII.isInterface()) {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Structure Callback field '").append(lllllllIlI.getName()).append("' must be an interface")));
            }
            if (lllllllIII.isArray() && Structure.class.equals(lllllllIII.getComponentType())) {
                String lIIIIIIIIIl = "Nested Structure arrays must use a derived Structure type so that the size of the elements can be determined";
                throw new IllegalArgumentException(lIIIIIIIIIl);
            }
            int llllllIllI = 1;
            if (Modifier.isPublic(lllllllIlI.getModifiers())) {
                Object llllllIlIl = lllllIlIlI.getFieldValue(llllllIlll.field);
                if (llllllIlIl == null && lllllllIII.isArray()) {
                    if (lllllIlIIl) {
                        throw new IllegalStateException("Array fields must be initialized");
                    }
                    return null;
                }
                Class<Object> llllllIlII = lllllllIII;
                if (NativeMapped.class.isAssignableFrom(lllllllIII)) {
                    NativeMappedConverter lIIIIIIIIII = NativeMappedConverter.getInstance(lllllllIII);
                    llllllIlII = lIIIIIIIIII.nativeType();
                    llllllIlll.writeConverter = lIIIIIIIIII;
                    llllllIlll.readConverter = lIIIIIIIIII;
                    llllllIlll.context = new StructureReadContext(lllllIlIlI, lllllllIlI);
                } else if (lllllIlIlI.typeMapper != null) {
                    ToNativeConverter lllllllllI = lllllIlIlI.typeMapper.getToNativeConverter(lllllllIII);
                    FromNativeConverter llllllllIl = lllllIlIlI.typeMapper.getFromNativeConverter(lllllllIII);
                    if (lllllllllI != null && llllllllIl != null) {
                        llllllIlII = (llllllIlIl = lllllllllI.toNative(llllllIlIl, new StructureWriteContext(lllllIlIlI, llllllIlll.field))) != null ? llllllIlIl.getClass() : Pointer.class;
                        llllllIlll.writeConverter = lllllllllI;
                        llllllIlll.readConverter = llllllllIl;
                        llllllIlll.context = new StructureReadContext(lllllIlIlI, lllllllIlI);
                    } else if (lllllllllI != null || llllllllIl != null) {
                        String llllllllll = String.valueOf(new StringBuilder().append("Structures require bidirectional type conversion for ").append(lllllllIII));
                        throw new IllegalArgumentException(llllllllll);
                    }
                }
                if (llllllIlIl == null) {
                    llllllIlIl = lllllIlIlI.initializeField(llllllIlll.field, lllllllIII);
                }
                try {
                    llllllIlll.size = lllllIlIlI.getNativeSize(llllllIlII, llllllIlIl);
                    llllllIllI = lllllIlIlI.getNativeAlignment(llllllIlII, llllllIlIl, lllllIlIll);
                }
                catch (IllegalArgumentException lllllllIll) {
                    if (!lllllIlIIl && lllllIlIlI.typeMapper == null) {
                        return null;
                    }
                    String llllllllII = String.valueOf(new StringBuilder().append("Invalid Structure field in ").append(lllllIlIlI.getClass()).append(", field name '").append(llllllIlll.name).append("' (").append(llllllIlll.type).append("): ").append(lllllllIll.getMessage()));
                    throw new IllegalArgumentException(llllllllII, lllllllIll);
                }
                if (llllllIllI == 0) {
                    throw new Error(String.valueOf(new StringBuilder().append("Field alignment is zero for field '").append(llllllIlll.name).append("' within ").append(lllllIlIlI.getClass())));
                }
                lllllIllII.alignment = Math.max(lllllIllII.alignment, llllllIllI);
                if (lllllIlllI % llllllIllI != 0) {
                    lllllIlllI += llllllIllI - lllllIlllI % llllllIllI;
                }
                if (lllllIlIlI instanceof Union) {
                    llllllIlll.offset = 0;
                    lllllIlllI = Math.max(lllllIlllI, llllllIlll.size);
                } else {
                    llllllIlll.offset = lllllIlllI;
                    lllllIlllI += llllllIlll.size;
                }
                lllllIllII.fields.put(llllllIlll.name, llllllIlll);
                if (lllllIllII.typeInfoField == null || ((LayoutInfo)lllllIllII).typeInfoField.size < llllllIlll.size || ((LayoutInfo)lllllIllII).typeInfoField.size == llllllIlll.size && Structure.class.isAssignableFrom(llllllIlll.type)) {
                    lllllIllII.typeInfoField = llllllIlll;
                }
            }
            lllllIlIll = false;
        }
        if (lllllIlllI > 0) {
            int llllllIIlI = lllllIlIlI.addPadding(lllllIlllI, lllllIllII.alignment);
            if (lllllIlIlI instanceof ByValue && !lllllIllll) {
                lllllIlIlI.getTypeInfo();
            }
            lllllIllII.size = llllllIIlI;
            return lllllIllII;
        }
        throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Structure ").append(lllllIlIlI.getClass()).append(" has unknown or zero size (ensure all fields are public)")));
    }

    private void initializeTypeMapper(TypeMapper lIllIllllll) {
        Structure lIlllIIIIII;
        if (lIllIllllll == null) {
            lIllIllllll = Native.getTypeMapper(lIlllIIIIII.getClass());
        }
        lIlllIIIIII.typeMapper = lIllIllllll;
        lIlllIIIIII.layoutChanged();
    }

    public static Structure newInstance(Class<?> lIlIllllIl) throws IllegalArgumentException {
        try {
            Structure lIllIIIIll = (Structure)lIlIllllIl.newInstance();
            if (lIllIIIIll instanceof ByValue) {
                lIllIIIIll.allocateMemory();
            }
            return lIllIIIIll;
        }
        catch (InstantiationException lIllIIIIIl) {
            String lIllIIIIlI = String.valueOf(new StringBuilder().append("Can't instantiate ").append(lIlIllllIl));
            throw new IllegalArgumentException(lIllIIIIlI, lIllIIIIIl);
        }
        catch (IllegalAccessException lIlIllllll) {
            String lIllIIIIII = String.valueOf(new StringBuilder().append("Instantiation of ").append(lIlIllllIl).append(" not allowed, is it public?"));
            throw new IllegalArgumentException(lIllIIIIII, lIlIllllll);
        }
    }

    protected Structure(Pointer lIlllIIllIl, int lIlllIlIIII, TypeMapper lIlllIIlIll) {
        Structure lIlllIlIIlI;
        lIlllIlIIlI.size = -1;
        lIlllIlIIlI.nativeStrings = new HashMap<String, Object>();
        lIlllIlIIlI.autoRead = true;
        lIlllIlIIlI.autoWrite = true;
        lIlllIlIIlI.setAlignType(lIlllIlIIII);
        lIlllIlIIlI.setStringEncoding(Native.getStringEncoding(lIlllIlIIlI.getClass()));
        lIlllIlIIlI.initializeTypeMapper(lIlllIIlIll);
        lIlllIlIIlI.validateFields();
        if (lIlllIIllIl != null) {
            lIlllIlIIlI.useMemory(lIlllIIllIl, 0, true);
        } else {
            lIlllIlIIlI.allocateMemory(-1);
        }
        lIlllIlIIlI.initializeFields();
    }

    private String format(Class<?> lllIIIIIIl) {
        String lllIIIIIll = lllIIIIIIl.getName();
        int lllIIIIIlI = lllIIIIIll.lastIndexOf(".");
        return lllIIIIIll.substring(lllIIIIIlI + 1);
    }

    static int size(Class<?> lIIIlIlIlll) {
        return Structure.size(lIIIlIlIlll, null);
    }

    public void setAutoWrite(boolean lIlllIlIIl) {
        lIlllIllII.autoWrite = lIlllIlIIl;
    }

    void conditionalAutoRead() {
        Structure lIlIllIIlII;
        if (!lIlIllIIlII.readCalled) {
            lIlIllIIlII.autoRead();
        }
    }

    protected int getNativeSize(Class<?> lIIlllllIl, Object lIIllllllI) {
        return Native.getNativeSize(lIIlllllIl, lIIllllllI);
    }

    protected void useMemory(Pointer lIllIIllllI, int lIllIIlllIl) {
        Structure lIllIIlllll;
        lIllIIlllll.useMemory(lIllIIllllI, lIllIIlllIl, false);
    }

    static Structure updateStructureByReference(Class<?> lIlIIIllIlI, Structure lIlIIIlllII, Pointer lIlIIIllIII) {
        if (lIlIIIllIII == null) {
            lIlIIIlllII = null;
        } else if (lIlIIIlllII == null || !lIlIIIllIII.equals(lIlIIIlllII.getPointer())) {
            Structure lIlIIIllllI = Structure.reading().get(lIlIIIllIII);
            if (lIlIIIllllI != null && lIlIIIllIlI.equals(lIlIIIllllI.getClass())) {
                lIlIIIlllII = lIlIIIllllI;
                lIlIIIlllII.autoRead();
            } else {
                lIlIIIlllII = Structure.newInstance(lIlIIIllIlI, lIlIIIllIII);
                lIlIIIlllII.conditionalAutoRead();
            }
        } else {
            lIlIIIlllII.autoRead();
        }
        return lIlIIIlllII;
    }

    public String toString(boolean lllIIIlIll) {
        Structure lllIIIlIlI;
        return lllIIIlIlI.toString(0, true, lllIIIlIll);
    }

    protected Structure(int lIllllIllll) {
        lIlllllIIII(null, lIllllIllll);
        Structure lIlllllIIII;
    }

    public static List<String> createFieldsOrder(List<String> lIIlIIIIIIl, List<String> lIIlIIIIIII) {
        ArrayList<String> lIIlIIIIIlI = new ArrayList<String>(lIIlIIIIIIl.size() + lIIlIIIIIII.size());
        lIIlIIIIIlI.addAll(lIIlIIIIIIl);
        lIIlIIIIIlI.addAll(lIIlIIIIIII);
        return Collections.unmodifiableList(lIIlIIIIIlI);
    }

    static Set<Structure> busy() {
        return busy.get();
    }

    public void autoRead() {
        Structure lIlIIlIllI;
        if (lIlIIlIllI.getAutoRead()) {
            lIlIIlIllI.read();
            if (lIlIIlIllI.array != null) {
                for (int lIlIIllIII = 1; lIlIIllIII < lIlIIlIllI.array.length; ++lIlIIllIII) {
                    lIlIIlIllI.array[lIlIIllIII].autoRead();
                }
            }
        }
    }

    protected int calculateSize(boolean lIIIlIllIlI) {
        Structure lIIIlIlllIl;
        return lIIIlIlllIl.calculateSize(lIIIlIllIlI, false);
    }

    protected void setStringEncoding(String lIllIllIllI) {
        lIllIllIlll.encoding = lIllIllIllI;
    }

    protected Object readField(StructField lIlIIIIIlII) {
        Object lIlIIIIIllI;
        Structure lIlIIIIIlIl;
        Object lIlIIIIIlll;
        int lIlIIIIlIlI = lIlIIIIIlII.offset;
        Class<?> lIlIIIIlIIl = lIlIIIIIlII.type;
        FromNativeConverter lIlIIIIlIII = lIlIIIIIlII.readConverter;
        if (lIlIIIIlIII != null) {
            lIlIIIIlIIl = lIlIIIIlIII.nativeType();
        }
        Object object = lIlIIIIIlll = Structure.class.isAssignableFrom(lIlIIIIlIIl) || Callback.class.isAssignableFrom(lIlIIIIlIIl) || Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(lIlIIIIlIIl) || Pointer.class.isAssignableFrom(lIlIIIIlIIl) || NativeMapped.class.isAssignableFrom(lIlIIIIlIIl) || lIlIIIIlIIl.isArray() ? lIlIIIIIlIl.getFieldValue(lIlIIIIIlII.field) : null;
        if (lIlIIIIlIIl == String.class) {
            Pointer lIlIIIIlllI = lIlIIIIIlIl.memory.getPointer(lIlIIIIlIlI);
            String lIlIIIIllIl = lIlIIIIlllI == null ? null : lIlIIIIlllI.getString(0L, lIlIIIIIlIl.encoding);
        } else {
            lIlIIIIIllI = lIlIIIIIlIl.memory.getValue(lIlIIIIlIlI, lIlIIIIlIIl, lIlIIIIIlll);
        }
        if (lIlIIIIlIII != null) {
            lIlIIIIIllI = lIlIIIIlIII.fromNative(lIlIIIIIllI, lIlIIIIIlII.context);
            if (lIlIIIIIlll != null && lIlIIIIIlll.equals(lIlIIIIIllI)) {
                lIlIIIIIllI = lIlIIIIIlll;
            }
        }
        if (lIlIIIIlIIl.equals(String.class) || lIlIIIIlIIl.equals(WString.class)) {
            lIlIIIIIlIl.nativeStrings.put(String.valueOf(new StringBuilder().append(lIlIIIIIlII.name).append(".ptr")), lIlIIIIIlIl.memory.getPointer(lIlIIIIlIlI));
            lIlIIIIIlIl.nativeStrings.put(String.valueOf(new StringBuilder().append(lIlIIIIIlII.name).append(".val")), lIlIIIIIllI);
        }
        lIlIIIIIlIl.setFieldValue(lIlIIIIIlII.field, lIlIIIIIllI, true);
        return lIlIIIIIllI;
    }

    public Structure[] toArray(Structure[] llIlIIlIII) {
        Structure llIlIIlIIl;
        llIlIIlIIl.ensureAllocated();
        if (llIlIIlIIl.memory instanceof AutoAllocated) {
            Memory llIlIIllll = (Memory)llIlIIlIIl.memory;
            int llIlIIlllI = llIlIIlIII.length * llIlIIlIIl.size();
            if (llIlIIllll.size() < (long)llIlIIlllI) {
                llIlIIlIIl.useMemory(llIlIIlIIl.autoAllocate(llIlIIlllI));
            }
        }
        llIlIIlIII[0] = llIlIIlIIl;
        int llIlIIlIlI = llIlIIlIIl.size();
        for (int llIlIIllIl = 1; llIlIIllIl < llIlIIlIII.length; ++llIlIIllIl) {
            llIlIIlIII[llIlIIllIl] = Structure.newInstance(llIlIIlIIl.getClass(), llIlIIlIIl.memory.share(llIlIIllIl * llIlIIlIlI, llIlIIlIlI));
            llIlIIlIII[llIlIIllIl].conditionalAutoRead();
        }
        if (!(llIlIIlIIl instanceof ByValue)) {
            llIlIIlIIl.array = llIlIIlIII;
        }
        return llIlIIlIII;
    }

    private void validateField(String lIIIIlIIIlI, Class<?> lIIIIlIIlII) {
        ToNativeConverter lIIIIlIlIIl;
        Structure lIIIIlIIllI;
        if (lIIIIlIIllI.typeMapper != null && (lIIIIlIlIIl = lIIIIlIIllI.typeMapper.getToNativeConverter(lIIIIlIIlII)) != null) {
            lIIIIlIIllI.validateField(lIIIIlIIIlI, lIIIIlIlIIl.nativeType());
            return;
        }
        if (lIIIIlIIlII.isArray()) {
            lIIIIlIIllI.validateField(lIIIIlIIIlI, lIIIIlIIlII.getComponentType());
        } else {
            try {
                lIIIIlIIllI.getNativeSize(lIIIIlIIlII);
            }
            catch (IllegalArgumentException lIIIIlIIlll) {
                String lIIIIlIlIII = String.valueOf(new StringBuilder().append("Invalid Structure field in ").append(lIIIIlIIllI.getClass()).append(", field name '").append(lIIIIlIIIlI).append("' (").append(lIIIIlIIlII).append("): ").append(lIIIIlIIlll.getMessage()));
                throw new IllegalArgumentException(lIIIIlIlIII, lIIIIlIIlll);
            }
        }
    }

    protected void allocateMemory() {
        Structure lIlIlllllII;
        lIlIlllllII.allocateMemory(false);
    }

    public static List<String> createFieldsOrder(List<String> lIIlIIIlIIl, String ... lIIlIIIlIlI) {
        return Structure.createFieldsOrder(lIIlIIIlIIl, Arrays.asList(lIIlIIIlIlI));
    }

    static void validate(Class<?> lIIllllIIl) {
        Structure.newInstance(lIIllllIIl, PLACEHOLDER_MEMORY);
    }

    public static void autoWrite(Structure[] lIlIIlIIIl) {
        Structure.structureArrayCheck(lIlIIlIIIl);
        if (lIlIIlIIIl[0].array == lIlIIlIIIl) {
            lIlIIlIIIl[0].autoWrite();
        } else {
            for (int lIlIIlIIlI = 0; lIlIIlIIlI < lIlIIlIIIl.length; ++lIlIIlIIlI) {
                if (lIlIIlIIIl[lIlIIlIIlI] == null) continue;
                lIlIIlIIIl[lIlIIlIIlI].autoWrite();
            }
        }
    }

    private void validateFields() {
        Structure lIIIIIllIIl;
        List<Field> lIIIIIllIII = lIIIIIllIIl.getFieldList();
        for (Field lIIIIIllIlI : lIIIIIllIII) {
            lIIIIIllIIl.validateField(lIIIIIllIlI.getName(), lIIIIIllIlI.getType());
        }
    }

    public static List<String> createFieldsOrder(String lIIIlllllIl) {
        return Collections.unmodifiableList(Collections.singletonList(lIIIlllllIl));
    }

    Pointer getTypeInfo() {
        Structure lIllllllll;
        Pointer llIIIIIIII = Structure.getTypeInfo(lIllllllll);
        lIllllllll.cacheTypeInfo(llIIIIIIII);
        return llIIIIIIII;
    }

    protected String getStringEncoding() {
        Structure lIllIllIIll;
        return lIllIllIIll.encoding;
    }

    private Object initializeField(Field lllIlllIll, Class<?> lllIlllllI) {
        Structure llllIIIIII;
        Object lllIllllIl = null;
        if (Structure.class.isAssignableFrom(lllIlllllI) && !ByReference.class.isAssignableFrom(lllIlllllI)) {
            try {
                lllIllllIl = Structure.newInstance(lllIlllllI, PLACEHOLDER_MEMORY);
                llllIIIIII.setFieldValue(lllIlllIll, lllIllllIl);
            }
            catch (IllegalArgumentException llllIIIIlI) {
                String llllIIIIll = "Can't determine size of nested structure";
                throw new IllegalArgumentException(llllIIIIll, llllIIIIlI);
            }
        } else if (NativeMapped.class.isAssignableFrom(lllIlllllI)) {
            NativeMappedConverter llllIIIIIl = NativeMappedConverter.getInstance(lllIlllllI);
            lllIllllIl = llllIIIIIl.defaultValue();
            llllIIIIII.setFieldValue(lllIlllIll, lllIllllIl);
        }
        return lllIllllIl;
    }

    private static Structure newInstance(Class<?> lIllIllIll, long lIllIlllII) {
        try {
            Structure lIllIlllll = Structure.newInstance(lIllIllIll, lIllIlllII == 0L ? PLACEHOLDER_MEMORY : new Pointer(lIllIlllII));
            if (lIllIlllII != 0L) {
                lIllIlllll.conditionalAutoRead();
            }
            return lIllIlllll;
        }
        catch (Throwable lIllIllllI) {
            System.err.println(String.valueOf(new StringBuilder().append("JNA: Error creating structure: ").append(lIllIllllI)));
            return null;
        }
    }

    protected void writeField(StructField lIIllIIlIll) {
        Structure lIIllIIllII;
        if (lIIllIIlIll.isReadOnly) {
            return;
        }
        int lIIllIlIIII = lIIllIIlIll.offset;
        Object lIIllIIllll = lIIllIIllII.getFieldValue(lIIllIIlIll.field);
        Class<?> lIIllIIlllI = lIIllIIlIll.type;
        ToNativeConverter lIIllIIllIl = lIIllIIlIll.writeConverter;
        if (lIIllIIllIl != null) {
            lIIllIIllll = lIIllIIllIl.toNative(lIIllIIllll, new StructureWriteContext(lIIllIIllII, lIIllIIlIll.field));
            lIIllIIlllI = lIIllIIllIl.nativeType();
        }
        if (String.class == lIIllIIlllI || WString.class == lIIllIIlllI) {
            boolean lIIllIlIlIl;
            boolean bl = lIIllIlIlIl = lIIllIIlllI == WString.class;
            if (lIIllIIllll != null) {
                if (lIIllIIllII.nativeStrings.containsKey(String.valueOf(new StringBuilder().append(lIIllIIlIll.name).append(".ptr"))) && lIIllIIllll.equals(lIIllIIllII.nativeStrings.get(String.valueOf(new StringBuilder().append(lIIllIIlIll.name).append(".val"))))) {
                    return;
                }
                NativeString lIIllIlIllI = lIIllIlIlIl ? new NativeString(lIIllIIllll.toString(), true) : new NativeString(lIIllIIllll.toString(), lIIllIIllII.encoding);
                lIIllIIllII.nativeStrings.put(lIIllIIlIll.name, lIIllIlIllI);
                lIIllIIllll = lIIllIlIllI.getPointer();
            } else {
                lIIllIIllII.nativeStrings.remove(lIIllIIlIll.name);
            }
            lIIllIIllII.nativeStrings.remove(String.valueOf(new StringBuilder().append(lIIllIIlIll.name).append(".ptr")));
            lIIllIIllII.nativeStrings.remove(String.valueOf(new StringBuilder().append(lIIllIIlIll.name).append(".val")));
        }
        try {
            lIIllIIllII.memory.setValue(lIIllIlIIII, lIIllIIllll, lIIllIIlllI);
        }
        catch (IllegalArgumentException lIIllIlIIll) {
            String lIIllIlIlII = String.valueOf(new StringBuilder().append("Structure field \"").append(lIIllIIlIll.name).append("\" was declared as ").append(lIIllIIlIll.type).append(lIIllIIlIll.type == lIIllIIlllI ? "" : String.valueOf(new StringBuilder().append(" (native type ").append(lIIllIIlllI).append(")"))).append(", which is not supported within a Structure"));
            throw new IllegalArgumentException(lIIllIlIlII, lIIllIlIIll);
        }
    }

    protected Structure(Pointer lIllllIIIII) {
        lIllllIIIll(lIllllIIIII, 0);
        Structure lIllllIIIll;
    }

    protected abstract List<String> getFieldOrder();

    public boolean dataEquals(Structure llIIllIlll) {
        Structure llIIlllIlI;
        return llIIlllIlI.dataEquals(llIIllIlll, false);
    }

    protected Structure(TypeMapper lIlllllIlIl) {
        lIlllllIllI(null, 0, lIlllllIlIl);
        Structure lIlllllIllI;
    }

    public boolean getAutoWrite() {
        Structure lIlllIIlll;
        return lIlllIIlll.autoWrite;
    }

    public int hashCode() {
        Structure llIIIllIlI;
        Pointer llIIIllIll = llIIIllIlI.getPointer();
        if (llIIIllIll != null) {
            return llIIIllIlI.getPointer().hashCode();
        }
        return llIIIllIlI.getClass().hashCode();
    }

    public void setAutoRead(boolean lIllllIIlI) {
        lIllllIIll.autoRead = lIllllIIlI;
    }

    protected Structure(int lIllllIIlll, TypeMapper lIllllIlIIl) {
        lIllllIlIll(null, lIllllIIlll, lIllllIlIIl);
        Structure lIllllIlIll;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    StructField typeInfoField() {
        void lIlIllIlII;
        Map<Class<?>, LayoutInfo> lIlIllIIIl = layoutInfo;
        synchronized (lIlIllIIIl) {
            Structure lIlIllIlIl;
            LayoutInfo lIlIllIllI = layoutInfo.get(lIlIllIlIl.getClass());
        }
        if (lIlIllIlII != null) {
            return ((LayoutInfo)lIlIllIlII).typeInfoField;
        }
        return null;
    }

    private static <T extends Comparable<T>> List<T> sort(Collection<? extends T> lIIIlllIlII) {
        ArrayList<? extends T> lIIIlllIlIl = new ArrayList<T>(lIIIlllIlII);
        Collections.sort(lIIIlllIlIl);
        return lIIIlllIlIl;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    int calculateSize(boolean lIIIIllllII, boolean lIIIIllIlIl) {
        LayoutInfo lIIIIlllIII;
        Structure lIIIIllllIl;
        int lIIIIlllIlI = -1;
        Class<?> lIIIIlllIIl = lIIIIllllIl.getClass();
        Map<Class<?>, LayoutInfo> lIIIIllIIIl = layoutInfo;
        synchronized (lIIIIllIIIl) {
            LayoutInfo lIIIIlllllI = layoutInfo.get(lIIIIlllIIl);
        }
        if (lIIIIlllIII == null || lIIIIllllIl.alignType != lIIIIlllIII.alignType || lIIIIllllIl.typeMapper != lIIIIlllIII.typeMapper) {
            lIIIIlllIII = lIIIIllllIl.deriveLayout(lIIIIllllII, lIIIIllIlIl);
        }
        if (lIIIIlllIII != null) {
            lIIIIllllIl.structAlignment = lIIIIlllIII.alignment;
            lIIIIllllIl.structFields = lIIIIlllIII.fields;
            if (!lIIIIlllIII.variable) {
                lIIIIllIIIl = layoutInfo;
                synchronized (lIIIIllIIIl) {
                    if (!layoutInfo.containsKey(lIIIIlllIIl) || lIIIIllllIl.alignType != 0 || lIIIIllllIl.typeMapper != null) {
                        layoutInfo.put(lIIIIlllIIl, lIIIIlllIII);
                    }
                }
            }
            lIIIIlllIlI = lIIIIlllIII.size;
        }
        return lIIIIlllIlI;
    }

    protected Memory autoAllocate(int lIllIlIlIlI) {
        return new AutoAllocated(lIllIlIlIlI);
    }

    public void autoWrite() {
        Structure lIlIIIlIlI;
        if (lIlIIIlIlI.getAutoWrite()) {
            lIlIIIlIlI.write();
            if (lIlIIIlIlI.array != null) {
                for (int lIlIIIllII = 1; lIlIIIllII < lIlIIIlIlI.array.length; ++lIlIIIllII) {
                    lIlIIIlIlI.array[lIlIIIllII].autoWrite();
                }
            }
        }
    }

    protected void setAlignType(int lIllIlIllIl) {
        Structure lIllIlIlllI;
        lIllIlIlllI.alignType = lIllIlIllIl;
        if (lIllIlIllIl == 0 && (lIllIlIllIl = Native.getStructureAlignment(lIllIlIlllI.getClass())) == 0) {
            lIllIlIllIl = Platform.isWindows() ? 3 : 2;
        }
        lIllIlIlllI.actualAlignType = lIllIlIllIl;
        lIllIlIlllI.layoutChanged();
    }

    void useMemory(Pointer lIllIIIllIl, int lIllIIIllII, boolean lIllIIIllll) {
        try {
            Structure lIllIIlIIlI;
            lIllIIlIIlI.nativeStrings.clear();
            if (lIllIIlIIlI instanceof ByValue && !lIllIIIllll) {
                byte[] lIllIIlIlII = new byte[lIllIIlIIlI.size()];
                lIllIIIllIl.read(0L, lIllIIlIlII, 0, lIllIIlIlII.length);
                lIllIIlIIlI.memory.write(0L, lIllIIlIlII, 0, lIllIIlIlII.length);
            } else {
                lIllIIlIIlI.memory = lIllIIIllIl.share(lIllIIIllII);
                if (lIllIIlIIlI.size == -1) {
                    lIllIIlIIlI.size = lIllIIlIIlI.calculateSize(false);
                }
                if (lIllIIlIIlI.size != -1) {
                    lIllIIlIIlI.memory = lIllIIIllIl.share(lIllIIIllII, lIllIIlIIlI.size);
                }
            }
            lIllIIlIIlI.array = null;
            lIllIIlIIlI.readCalled = false;
        }
        catch (IndexOutOfBoundsException lIllIIlIIll) {
            throw new IllegalArgumentException("Structure exceeds provided memory bounds", lIllIIlIIll);
        }
    }

    public void write() {
        Structure lIIlllllIII;
        if (lIIlllllIII.memory == PLACEHOLDER_MEMORY) {
            return;
        }
        lIIlllllIII.ensureAllocated();
        if (lIIlllllIII instanceof ByValue) {
            lIIlllllIII.getTypeInfo();
        }
        if (Structure.busy().contains(lIIlllllIII)) {
            return;
        }
        Structure.busy().add(lIIlllllIII);
        try {
            for (StructField lIIlllllIIl : lIIlllllIII.fields().values()) {
                if (lIIlllllIIl.isVolatile) continue;
                lIIlllllIII.writeField(lIIlllllIIl);
            }
        }
        finally {
            Structure.busy().remove(lIIlllllIII);
        }
    }

    protected int fieldOffset(String lIlIlIlIIIl) {
        Structure lIlIlIlIlIl;
        lIlIlIlIlIl.ensureAllocated();
        StructField lIlIlIlIIll = lIlIlIlIlIl.fields().get(lIlIlIlIIIl);
        if (lIlIlIlIIll == null) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No such field: ").append(lIlIlIlIIIl)));
        }
        return lIlIlIlIIll.offset;
    }

    public static List<String> createFieldsOrder(String ... lIIIllllIlI) {
        return Collections.unmodifiableList(Arrays.asList(lIIIllllIlI));
    }

    Map<String, StructField> fields() {
        Structure lIlllIIlIII;
        return lIlllIIlIII.structFields;
    }

    public int size() {
        Structure lIlIllIllII;
        lIlIllIllII.ensureAllocated();
        return lIlIllIllII.size;
    }

    protected void useMemory(Pointer lIllIlIIIll) {
        Structure lIllIlIIllI;
        lIllIlIIllI.useMemory(lIllIlIIIll, 0);
    }

    protected List<Field> getFieldList() {
        Structure lIIlIlIIIll;
        ArrayList<Field> lIIlIlIIIlI = new ArrayList<Field>();
        Class<?> lIIlIlIIlII = lIIlIlIIIll.getClass();
        while (!lIIlIlIIlII.equals(Structure.class)) {
            ArrayList<Field> lIIlIlIIllI = new ArrayList<Field>();
            Field[] lIIlIlIIlIl = lIIlIlIIlII.getDeclaredFields();
            for (int lIIlIlIIlll = 0; lIIlIlIIlll < lIIlIlIIlIl.length; ++lIIlIlIIlll) {
                int lIIlIlIlIII = lIIlIlIIlIl[lIIlIlIIlll].getModifiers();
                if (Modifier.isStatic(lIIlIlIlIII) || !Modifier.isPublic(lIIlIlIlIII)) continue;
                lIIlIlIIllI.add(lIIlIlIIlIl[lIIlIlIIlll]);
            }
            lIIlIlIIIlI.addAll(0, lIIlIlIIllI);
            lIIlIlIIlII = lIIlIlIIlII.getSuperclass();
        }
        return lIIlIlIIIlI;
    }

    void setFieldValue(Field lIlIIllIllI, Object lIlIIlllIII) {
        Structure lIlIIllIlll;
        lIlIIllIlll.setFieldValue(lIlIIllIllI, lIlIIlllIII, false);
    }

    private Class<?> baseClass() {
        Structure llIIllllIl;
        if ((llIIllllIl instanceof ByReference || llIIllllIl instanceof ByValue) && Structure.class.isAssignableFrom(llIIllllIl.getClass().getSuperclass())) {
            return llIIllllIl.getClass().getSuperclass();
        }
        return llIIllllIl.getClass();
    }

    private void setFieldValue(Field lIlIIlIIlll, Object lIlIIlIlIlI, boolean lIlIIlIIlIl) {
        Structure lIlIIlIllII;
        try {
            lIlIIlIIlll.set(lIlIIlIllII, lIlIIlIlIlI);
        }
        catch (IllegalAccessException lIlIIlIllIl) {
            int lIlIIlIlllI = lIlIIlIIlll.getModifiers();
            if (Modifier.isFinal(lIlIIlIlllI)) {
                if (lIlIIlIIlIl) {
                    throw new UnsupportedOperationException(String.valueOf(new StringBuilder().append("This VM does not support Structures with final fields (field '").append(lIlIIlIIlll.getName()).append("' within ").append(lIlIIlIllII.getClass()).append(")")), lIlIIlIllIl);
                }
                throw new UnsupportedOperationException(String.valueOf(new StringBuilder().append("Attempt to write to read-only field '").append(lIlIIlIIlll.getName()).append("' within ").append(lIlIIlIllII.getClass())), lIlIIlIllIl);
            }
            throw new Error(String.valueOf(new StringBuilder().append("Unexpectedly unable to write to field '").append(lIlIIlIIlll.getName()).append("' within ").append(lIlIIlIllII.getClass())), lIlIIlIllIl);
        }
    }

    static Pointer getTypeInfo(Object lIlllIIIll) {
        return FFIType.get(lIlllIIIll);
    }

    protected int getNativeSize(Class<?> lIlIIIIlIl) {
        Structure lIlIIIIlII;
        return lIlIIIIlII.getNativeSize(lIlIIIIlIl, null);
    }

    public Structure[] toArray(int llIlIIIIII) {
        Structure llIlIIIIIl;
        return llIlIIIIIl.toArray((Structure[])Array.newInstance(llIlIIIIIl.getClass(), llIlIIIIII));
    }

    TypeMapper getTypeMapper() {
        Structure lIlllIIIlIl;
        return lIlllIIIlIl.typeMapper;
    }

    private static void structureArrayCheck(Structure[] lIlIlIIlIl) {
        if (ByReference[].class.isAssignableFrom(lIlIlIIlIl.getClass())) {
            return;
        }
        Pointer lIlIlIIlll = lIlIlIIlIl[0].getPointer();
        int lIlIlIIllI = lIlIlIIlIl[0].size();
        for (int lIlIlIlIIl = 1; lIlIlIlIIl < lIlIlIIlIl.length; ++lIlIlIlIIl) {
            if (lIlIlIIlIl[lIlIlIlIIl].getPointer().peer == lIlIlIIlll.peer + (long)(lIlIlIIllI * lIlIlIlIIl)) continue;
            String lIlIlIlIlI = String.valueOf(new StringBuilder().append("Structure array elements must use contiguous memory (bad backing address at Structure array index ").append(lIlIlIlIIl).append(")"));
            throw new IllegalArgumentException(lIlIlIlIlI);
        }
    }

    protected void ensureAllocated() {
        Structure lIllIIIIlll;
        lIllIIIIlll.ensureAllocated(false);
    }

    public void writeField(String lIIlllIllll) {
        Structure lIIllllIIII;
        lIIllllIIII.ensureAllocated();
        StructField lIIlllIlllI = lIIllllIIII.fields().get(lIIlllIllll);
        if (lIIlllIlllI == null) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No such field: ").append(lIIlllIllll)));
        }
        lIIllllIIII.writeField(lIIlllIlllI);
    }

    public void writeField(String lIIlllIIIIl, Object lIIlllIIlII) {
        Structure lIIlllIIIlI;
        lIIlllIIIlI.ensureAllocated();
        StructField lIIlllIIIll = lIIlllIIIlI.fields().get(lIIlllIIIIl);
        if (lIIlllIIIll == null) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No such field: ").append(lIIlllIIIIl)));
        }
        lIIlllIIIlI.setFieldValue(lIIlllIIIll.field, lIIlllIIlII);
        lIIlllIIIlI.writeField(lIIlllIIIll);
    }

    Object getFieldValue(Field lIlIlIIIIIl) {
        Structure lIlIlIIIIlI;
        try {
            return lIlIlIIIIIl.get(lIlIlIIIIlI);
        }
        catch (Exception lIlIlIIIIll) {
            throw new Error(String.valueOf(new StringBuilder().append("Exception reading field '").append(lIlIlIIIIIl.getName()).append("' in ").append(lIlIlIIIIlI.getClass())), lIlIlIIIIll);
        }
    }

    private void layoutChanged() {
        Structure lIllIllllIl;
        if (lIllIllllIl.size != -1) {
            lIllIllllIl.size = -1;
            if (lIllIllllIl.memory instanceof AutoAllocated) {
                lIllIllllIl.memory = null;
            }
            lIllIllllIl.ensureAllocated();
        }
    }

    public Pointer getPointer() {
        Structure lIlIllIIlll;
        lIlIllIIlll.ensureAllocated();
        return lIlIllIIlll.memory;
    }

    protected int getNativeAlignment(Class<?> lllIIlllII, Object lllIIlIlIl, boolean lllIIlIlII) {
        Structure lllIIlllIl;
        int lllIIllIIl = 1;
        if (NativeMapped.class.isAssignableFrom(lllIIlllII)) {
            NativeMappedConverter lllIIllllI = NativeMappedConverter.getInstance(lllIIlllII);
            lllIIlllII = lllIIllllI.nativeType();
            lllIIlIlIl = lllIIllllI.toNative(lllIIlIlIl, new ToNativeContext());
        }
        int lllIIllIII = Native.getNativeSize(lllIIlllII, lllIIlIlIl);
        if (lllIIlllII.isPrimitive() || Long.class == lllIIlllII || Integer.class == lllIIlllII || Short.class == lllIIlllII || Character.class == lllIIlllII || Byte.class == lllIIlllII || Boolean.class == lllIIlllII || Float.class == lllIIlllII || Double.class == lllIIlllII) {
            lllIIllIIl = lllIIllIII;
        } else if (Pointer.class.isAssignableFrom(lllIIlllII) && !Function.class.isAssignableFrom(lllIIlllII) || Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(lllIIlllII) || Callback.class.isAssignableFrom(lllIIlllII) || WString.class == lllIIlllII || String.class == lllIIlllII) {
            lllIIllIIl = Pointer.SIZE;
        } else if (Structure.class.isAssignableFrom(lllIIlllII)) {
            if (ByReference.class.isAssignableFrom(lllIIlllII)) {
                lllIIllIIl = Pointer.SIZE;
            } else {
                if (lllIIlIlIl == null) {
                    lllIIlIlIl = Structure.newInstance(lllIIlllII, PLACEHOLDER_MEMORY);
                }
                lllIIllIIl = ((Structure)lllIIlIlIl).getStructAlignment();
            }
        } else if (lllIIlllII.isArray()) {
            lllIIllIIl = lllIIlllIl.getNativeAlignment(lllIIlllII.getComponentType(), null, lllIIlIlII);
        } else {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Type ").append(lllIIlllII).append(" has unknown native alignment")));
        }
        if (lllIIlllIl.actualAlignType == 1) {
            lllIIllIIl = 1;
        } else if (lllIIlllIl.actualAlignType == 3) {
            lllIIllIIl = Math.min(8, lllIIllIIl);
        } else if (lllIIlllIl.actualAlignType == 2) {
            if (!(lllIIlIlII && Platform.isMac() && Platform.isPPC())) {
                lllIIllIIl = Math.min(Native.MAX_ALIGNMENT, lllIIllIIl);
            }
            if (!lllIIlIlII && Platform.isAIX() && (lllIIlllII == Double.TYPE || lllIIlllII == Double.class)) {
                lllIIllIIl = 4;
            }
        }
        return lllIIllIIl;
    }

    public static interface ByReference {
    }

    static class StructureSet
    extends AbstractCollection<Structure>
    implements Set<Structure> {
        /* synthetic */ Structure[] elements;
        private /* synthetic */ int count;

        StructureSet() {
            StructureSet llllllllllllllllIllIllllIIIIlIII;
        }

        @Override
        public int size() {
            StructureSet llllllllllllllllIllIlllIlllllIIl;
            return llllllllllllllllIllIlllIlllllIIl.count;
        }

        private void ensureCapacity(int llllllllllllllllIllIllllIIIIIIII) {
            StructureSet llllllllllllllllIllIllllIIIIIIIl;
            if (llllllllllllllllIllIllllIIIIIIIl.elements == null) {
                llllllllllllllllIllIllllIIIIIIIl.elements = new Structure[llllllllllllllllIllIllllIIIIIIII * 3 / 2];
            } else if (llllllllllllllllIllIllllIIIIIIIl.elements.length < llllllllllllllllIllIllllIIIIIIII) {
                Structure[] llllllllllllllllIllIllllIIIIIlII = new Structure[llllllllllllllllIllIllllIIIIIIII * 3 / 2];
                System.arraycopy(llllllllllllllllIllIllllIIIIIIIl.elements, 0, llllllllllllllllIllIllllIIIIIlII, 0, llllllllllllllllIllIllllIIIIIIIl.elements.length);
                llllllllllllllllIllIllllIIIIIIIl.elements = llllllllllllllllIllIllllIIIIIlII;
            }
        }

        @Override
        public Iterator<Structure> iterator() {
            StructureSet llllllllllllllllIllIlllIllIlIIll;
            Structure[] llllllllllllllllIllIlllIllIlIlII = new Structure[llllllllllllllllIllIlllIllIlIIll.count];
            if (llllllllllllllllIllIlllIllIlIIll.count > 0) {
                System.arraycopy(llllllllllllllllIllIlllIllIlIIll.elements, 0, llllllllllllllllIllIlllIllIlIlII, 0, llllllllllllllllIllIlllIllIlIIll.count);
            }
            return Arrays.asList(llllllllllllllllIllIlllIllIlIlII).iterator();
        }

        @Override
        public boolean contains(Object llllllllllllllllIllIlllIllllIlIl) {
            StructureSet llllllllllllllllIllIlllIllllIllI;
            return llllllllllllllllIllIlllIllllIllI.indexOf((Structure)llllllllllllllllIllIlllIllllIlIl) != -1;
        }

        public Structure[] getElements() {
            StructureSet llllllllllllllllIllIlllIllllllII;
            return llllllllllllllllIllIlllIllllllII.elements;
        }

        @Override
        public boolean remove(Object llllllllllllllllIllIlllIllIlllII) {
            StructureSet llllllllllllllllIllIlllIllIllIlI;
            int llllllllllllllllIllIlllIllIllIll = llllllllllllllllIllIlllIllIllIlI.indexOf((Structure)llllllllllllllllIllIlllIllIlllII);
            if (llllllllllllllllIllIlllIllIllIll != -1) {
                if (--llllllllllllllllIllIlllIllIllIlI.count >= 0) {
                    llllllllllllllllIllIlllIllIllIlI.elements[llllllllllllllllIllIlllIllIllIll] = llllllllllllllllIllIlllIllIllIlI.elements[llllllllllllllllIllIlllIllIllIlI.count];
                    llllllllllllllllIllIlllIllIllIlI.elements[llllllllllllllllIllIlllIllIllIlI.count] = null;
                }
                return true;
            }
            return false;
        }

        private int indexOf(Structure llllllllllllllllIllIlllIlllIIIll) {
            StructureSet llllllllllllllllIllIlllIlllIIllI;
            for (int llllllllllllllllIllIlllIlllIIlll = 0; llllllllllllllllIllIlllIlllIIlll < llllllllllllllllIllIlllIlllIIllI.count; ++llllllllllllllllIllIlllIlllIIlll) {
                Structure llllllllllllllllIllIlllIlllIlIII = llllllllllllllllIllIlllIlllIIllI.elements[llllllllllllllllIllIlllIlllIIlll];
                if (llllllllllllllllIllIlllIlllIIIll != llllllllllllllllIllIlllIlllIlIII && (llllllllllllllllIllIlllIlllIIIll.getClass() != llllllllllllllllIllIlllIlllIlIII.getClass() || llllllllllllllllIllIlllIlllIIIll.size() != llllllllllllllllIllIlllIlllIlIII.size() || !llllllllllllllllIllIlllIlllIIIll.getPointer().equals(llllllllllllllllIllIlllIlllIlIII.getPointer()))) continue;
                return llllllllllllllllIllIlllIlllIIlll;
            }
            return -1;
        }

        @Override
        public boolean add(Structure llllllllllllllllIllIlllIlllIllll) {
            StructureSet llllllllllllllllIllIlllIlllIlllI;
            if (!llllllllllllllllIllIlllIlllIlllI.contains(llllllllllllllllIllIlllIlllIllll)) {
                llllllllllllllllIllIlllIlllIlllI.ensureCapacity(llllllllllllllllIllIlllIlllIlllI.count + 1);
                llllllllllllllllIllIlllIlllIlllI.elements[llllllllllllllllIllIlllIlllIlllI.count++] = llllllllllllllllIllIlllIlllIllll;
            }
            return true;
        }
    }

    private static class AutoAllocated
    extends Memory {
        public AutoAllocated(int llIllIIIllIlIlI) {
            super(llIllIIIllIlIlI);
            AutoAllocated llIllIIIllIllIl;
            super.clear();
        }

        @Override
        public String toString() {
            AutoAllocated llIllIIIllIIlll;
            return String.valueOf(new StringBuilder().append("auto-").append(super.toString()));
        }
    }

    public static interface ByValue {
    }

    protected static class StructField {
        public /* synthetic */ ToNativeConverter writeConverter;
        public /* synthetic */ Class<?> type;
        public /* synthetic */ FromNativeConverter readConverter;
        public /* synthetic */ String name;
        public /* synthetic */ FromNativeContext context;
        public /* synthetic */ boolean isVolatile;
        public /* synthetic */ Field field;
        public /* synthetic */ int size;
        public /* synthetic */ int offset;
        public /* synthetic */ boolean isReadOnly;

        public String toString() {
            StructField llllllllllllllllIlllllIIIlIIlIlI;
            return String.valueOf(new StringBuilder().append(llllllllllllllllIlllllIIIlIIlIlI.name).append("@").append(llllllllllllllllIlllllIIIlIIlIlI.offset).append("[").append(llllllllllllllllIlllllIIIlIIlIlI.size).append("] (").append(llllllllllllllllIlllllIIIlIIlIlI.type).append(")"));
        }

        protected StructField() {
            StructField llllllllllllllllIlllllIIIlIIllIl;
            llllllllllllllllIlllllIIIlIIllIl.size = -1;
            llllllllllllllllIlllllIIIlIIllIl.offset = -1;
        }
    }

    private static class LayoutInfo {
        private /* synthetic */ boolean variable;
        private /* synthetic */ TypeMapper typeMapper;
        private /* synthetic */ int alignType;
        private /* synthetic */ StructField typeInfoField;
        private /* synthetic */ int size;
        private final /* synthetic */ Map<String, StructField> fields;
        private /* synthetic */ int alignment;

        private LayoutInfo() {
            LayoutInfo llllllllllllllllIlIlIlIIIIlIllIl;
            llllllllllllllllIlIlIlIIIIlIllIl.size = -1;
            llllllllllllllllIlIlIlIIIIlIllIl.alignment = 1;
            llllllllllllllllIlIlIlIIIIlIllIl.fields = Collections.synchronizedMap(new LinkedHashMap());
            llllllllllllllllIlIlIlIIIIlIllIl.alignType = 0;
        }
    }

    static class FFIType
    extends Structure {
        private static final /* synthetic */ int FFI_TYPE_STRUCT;
        public /* synthetic */ size_t size;
        public /* synthetic */ short alignment;
        public /* synthetic */ short type;
        public /* synthetic */ Pointer elements;
        private static final /* synthetic */ Map<Object, Object> typeInfoMap;

        private static Pointer get(Object llIIIIIIIIIlII, Class<?> llIIIIIIIIIllI) {
            ToNativeConverter llIIIIIIIIllII;
            TypeMapper llIIIIIIIIIlIl = Native.getTypeMapper(llIIIIIIIIIllI);
            if (llIIIIIIIIIlIl != null && (llIIIIIIIIllII = llIIIIIIIIIlIl.getToNativeConverter(llIIIIIIIIIllI)) != null) {
                llIIIIIIIIIllI = llIIIIIIIIllII.nativeType();
            }
            Map<Object, Object> map = typeInfoMap;
            synchronized (map) {
                Object llIIIIIIIIlIII = typeInfoMap.get(llIIIIIIIIIllI);
                if (llIIIIIIIIlIII instanceof Pointer) {
                    return (Pointer)llIIIIIIIIlIII;
                }
                if (llIIIIIIIIlIII instanceof FFIType) {
                    return ((FFIType)llIIIIIIIIlIII).getPointer();
                }
                if (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(llIIIIIIIIIllI) || Callback.class.isAssignableFrom(llIIIIIIIIIllI)) {
                    typeInfoMap.put(llIIIIIIIIIllI, FFITypes.ffi_type_pointer);
                    return FFITypes.ffi_type_pointer;
                }
                if (Structure.class.isAssignableFrom(llIIIIIIIIIllI)) {
                    if (llIIIIIIIIIlII == null) {
                        llIIIIIIIIIlII = FFIType.newInstance(llIIIIIIIIIllI, PLACEHOLDER_MEMORY);
                    }
                    if (ByReference.class.isAssignableFrom(llIIIIIIIIIllI)) {
                        typeInfoMap.put(llIIIIIIIIIllI, FFITypes.ffi_type_pointer);
                        return FFITypes.ffi_type_pointer;
                    }
                    FFIType llIIIIIIIIlIll = new FFIType((Structure)llIIIIIIIIIlII);
                    typeInfoMap.put(llIIIIIIIIIllI, llIIIIIIIIlIll);
                    return llIIIIIIIIlIll.getPointer();
                }
                if (NativeMapped.class.isAssignableFrom(llIIIIIIIIIllI)) {
                    NativeMappedConverter llIIIIIIIIlIlI = NativeMappedConverter.getInstance(llIIIIIIIIIllI);
                    return FFIType.get(llIIIIIIIIlIlI.toNative(llIIIIIIIIIlII, new ToNativeContext()), llIIIIIIIIlIlI.nativeType());
                }
                if (llIIIIIIIIIllI.isArray()) {
                    FFIType llIIIIIIIIlIIl = new FFIType(llIIIIIIIIIlII, llIIIIIIIIIllI);
                    typeInfoMap.put(llIIIIIIIIIlII, llIIIIIIIIlIIl);
                    return llIIIIIIIIlIIl.getPointer();
                }
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported type ").append(llIIIIIIIIIllI)));
            }
        }

        static {
            FFI_TYPE_STRUCT = 13;
            typeInfoMap = new WeakHashMap<Object, Object>();
            if (Native.POINTER_SIZE == 0) {
                throw new Error("Native library not initialized");
            }
            if (FFITypes.ffi_type_void == null) {
                throw new Error("FFI types not initialized");
            }
            typeInfoMap.put(Void.TYPE, FFITypes.ffi_type_void);
            typeInfoMap.put(Void.class, FFITypes.ffi_type_void);
            typeInfoMap.put(Float.TYPE, FFITypes.ffi_type_float);
            typeInfoMap.put(Float.class, FFITypes.ffi_type_float);
            typeInfoMap.put(Double.TYPE, FFITypes.ffi_type_double);
            typeInfoMap.put(Double.class, FFITypes.ffi_type_double);
            typeInfoMap.put(Long.TYPE, FFITypes.ffi_type_sint64);
            typeInfoMap.put(Long.class, FFITypes.ffi_type_sint64);
            typeInfoMap.put(Integer.TYPE, FFITypes.ffi_type_sint32);
            typeInfoMap.put(Integer.class, FFITypes.ffi_type_sint32);
            typeInfoMap.put(Short.TYPE, FFITypes.ffi_type_sint16);
            typeInfoMap.put(Short.class, FFITypes.ffi_type_sint16);
            Pointer lIllllllllIllI = Native.WCHAR_SIZE == 2 ? FFITypes.ffi_type_uint16 : FFITypes.ffi_type_uint32;
            typeInfoMap.put(Character.TYPE, lIllllllllIllI);
            typeInfoMap.put(Character.class, lIllllllllIllI);
            typeInfoMap.put(Byte.TYPE, FFITypes.ffi_type_sint8);
            typeInfoMap.put(Byte.class, FFITypes.ffi_type_sint8);
            typeInfoMap.put(Pointer.class, FFITypes.ffi_type_pointer);
            typeInfoMap.put(String.class, FFITypes.ffi_type_pointer);
            typeInfoMap.put(WString.class, FFITypes.ffi_type_pointer);
            typeInfoMap.put(Boolean.TYPE, FFITypes.ffi_type_uint32);
            typeInfoMap.put(Boolean.class, FFITypes.ffi_type_uint32);
        }

        static Pointer get(Object llIIIIIIIlIlIl) {
            if (llIIIIIIIlIlIl == null) {
                return FFITypes.ffi_type_pointer;
            }
            if (llIIIIIIIlIlIl instanceof Class) {
                return FFIType.get(null, (Class)llIIIIIIIlIlIl);
            }
            return FFIType.get(llIIIIIIIlIlIl, llIIIIIIIlIlIl.getClass());
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("size", "alignment", "type", "elements");
        }

        private void init(Pointer[] llIIIIIIIlIlll) {
            FFIType llIIIIIIIllIlI;
            llIIIIIIIllIlI.elements = new Memory(Pointer.SIZE * llIIIIIIIlIlll.length);
            llIIIIIIIllIlI.elements.write(0L, llIIIIIIIlIlll, 0, llIIIIIIIlIlll.length);
            llIIIIIIIllIlI.write();
        }

        private FFIType(Object llIIIIIIlIIIll, Class<?> llIIIIIIlIlIII) {
            FFIType llIIIIIIlIlIlI;
            llIIIIIIlIlIlI.type = (short)13;
            int llIIIIIIlIIlll = Array.getLength(llIIIIIIlIIIll);
            Pointer[] llIIIIIIlIIllI = new Pointer[llIIIIIIlIIlll + 1];
            Pointer llIIIIIIlIIlIl = FFIType.get(null, llIIIIIIlIlIII.getComponentType());
            for (int llIIIIIIlIlIll = 0; llIIIIIIlIlIll < llIIIIIIlIIlll; ++llIIIIIIlIlIll) {
                llIIIIIIlIIllI[llIIIIIIlIlIll] = llIIIIIIlIIlIl;
            }
            llIIIIIIlIlIlI.init(llIIIIIIlIIllI);
        }

        private FFIType(Structure llIIIIIIlllIlI) {
            Pointer[] llIIIIIIlllIIl;
            FFIType llIIIIIIlllIll;
            llIIIIIIlllIll.type = (short)13;
            llIIIIIIlllIlI.ensureAllocated(true);
            if (llIIIIIIlllIlI instanceof Union) {
                StructField llIIIIIIllllll = ((Union)llIIIIIIlllIlI).typeInfoField();
                Pointer[] llIIIIIIlllllI = new Pointer[]{FFIType.get(llIIIIIIlllIlI.getFieldValue(llIIIIIIllllll.field), llIIIIIIllllll.type), null};
            } else {
                llIIIIIIlllIIl = new Pointer[llIIIIIIlllIlI.fields().size() + 1];
                int llIIIIIIllllII = 0;
                for (StructField llIIIIIIllllIl : llIIIIIIlllIlI.fields().values()) {
                    llIIIIIIlllIIl[llIIIIIIllllII++] = llIIIIIIlllIlI.getFieldTypeInfo(llIIIIIIllllIl);
                }
            }
            llIIIIIIlllIll.init(llIIIIIIlllIIl);
        }

        public static class size_t
        extends IntegerType {
            private static final /* synthetic */ long serialVersionUID = 1L;

            public size_t(long lIllIllllIlIIl) {
                super(Native.SIZE_T_SIZE, lIllIllllIlIIl);
                size_t lIllIllllIlIlI;
            }

            public size_t() {
                lIllIllllIlllI(0L);
                size_t lIllIllllIlllI;
            }
        }

        private static class FFITypes {
            private static /* synthetic */ Pointer ffi_type_double;
            private static /* synthetic */ Pointer ffi_type_uint16;
            private static /* synthetic */ Pointer ffi_type_sint64;
            private static /* synthetic */ Pointer ffi_type_sint8;
            private static /* synthetic */ Pointer ffi_type_float;
            private static /* synthetic */ Pointer ffi_type_sint32;
            private static /* synthetic */ Pointer ffi_type_void;
            private static /* synthetic */ Pointer ffi_type_sint16;
            private static /* synthetic */ Pointer ffi_type_uint64;
            private static /* synthetic */ Pointer ffi_type_pointer;
            private static /* synthetic */ Pointer ffi_type_longdouble;
            private static /* synthetic */ Pointer ffi_type_uint8;
            private static /* synthetic */ Pointer ffi_type_uint32;

            private FFITypes() {
                FFITypes llllllllllllllllllIlIllIllIIlIll;
            }
        }
    }
}

