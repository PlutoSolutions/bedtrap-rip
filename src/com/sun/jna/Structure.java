/*
 * Decompiled with CFR 0.151.
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
    static final Map<Class<?>, List<String>> fieldOrder;
    protected static final int CALCULATE_SIZE;
    private static final ThreadLocal<Map<Pointer, Structure>> reads;
    private long typeInfo;
    private String encoding;
    private final Map<String, Object> nativeStrings = new HashMap<String, Object>();
    private Map<String, StructField> structFields;
    private int actualAlignType;
    private TypeMapper typeMapper;
    private boolean readCalled;
    private static final Pointer PLACEHOLDER_MEMORY;
    public static final int ALIGN_MSVC;
    static final Map<Class<?>, LayoutInfo> layoutInfo;
    public static final int ALIGN_DEFAULT;
    private Structure[] array;
    private boolean autoRead = true;
    private static final ThreadLocal<Set<Structure>> busy;
    public static final int ALIGN_NONE;
    private int alignType;
    private int structAlignment;
    private Pointer memory;
    public static final int ALIGN_GNUC;
    private int size = -1;
    private boolean autoWrite = true;

    public String toString() {
        return this.toString(Boolean.getBoolean("jna.dump_memory"));
    }

    Pointer getFieldTypeInfo(StructField structField) {
        ToNativeConverter toNativeConverter;
        Class<?> clazz = structField.type;
        Object object = this.getFieldValue(structField.field);
        if (this.typeMapper != null && (toNativeConverter = this.typeMapper.getToNativeConverter(clazz)) != null) {
            clazz = toNativeConverter.nativeType();
            object = toNativeConverter.toNative(object, new ToNativeContext());
        }
        return FFIType.access$800(object, clazz);
    }

    private void ensureAllocated(boolean bl) {
        if (this.memory == null) {
            this.allocateMemory(bl);
        } else if (this.size == -1) {
            this.size = this.calculateSize(true, bl);
            if (!(this.memory instanceof AutoAllocated)) {
                try {
                    this.memory = this.memory.share(0L, this.size);
                }
                catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    throw new IllegalArgumentException("Structure exceeds provided memory bounds", indexOutOfBoundsException);
                }
            }
        }
    }

    public boolean getAutoRead() {
        return this.autoRead;
    }

    private void allocateMemory(boolean bl) {
        this.allocateMemory(this.calculateSize(true, bl));
    }

    public void setAutoSynch(boolean bl) {
        this.setAutoRead(bl);
        this.setAutoWrite(bl);
    }

    protected List<Field> getFields(boolean bl) {
        List<Field> list = this.getFieldList();
        HashSet<String> hashSet2 = new HashSet<String>();
        for (Field hashSet3 : list) {
            hashSet2.add(hashSet3.getName());
        }
        List<String> list2 = this.fieldOrder();
        if (list2.size() != list.size() && list.size() > 1) {
            if (bl) {
                throw new Error(String.valueOf(new StringBuilder().append("Structure.getFieldOrder() on ").append(this.getClass()).append(" does not provide enough names [").append(list2.size()).append("] (").append(Structure.sort(list2)).append(") to match declared fields [").append(list.size()).append("] (").append(Structure.sort(hashSet2)).append(")")));
            }
            return null;
        }
        HashSet hashSet = new HashSet(list2);
        if (!hashSet.equals(hashSet2)) {
            throw new Error(String.valueOf(new StringBuilder().append("Structure.getFieldOrder() on ").append(this.getClass()).append(" returns names (").append(Structure.sort(list2)).append(") which do not match declared field names (").append(Structure.sort(hashSet2)).append(")")));
        }
        this.sortFields(list, list2);
        return list;
    }

    private int addPadding(int n) {
        return this.addPadding(n, this.structAlignment);
    }

    public static void autoRead(Structure[] structureArray) {
        Structure.structureArrayCheck(structureArray);
        if (structureArray[0].array == structureArray) {
            structureArray[0].autoRead();
        } else {
            for (int i = 0; i < structureArray.length; ++i) {
                if (structureArray[i] == null) continue;
                structureArray[i].autoRead();
                if (!false) continue;
                return;
            }
        }
    }

    public Object readField(String string) {
        this.ensureAllocated();
        StructField structField = this.fields().get(string);
        if (structField == null) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No such field: ").append(string)));
        }
        return this.readField(structField);
    }

    protected void allocateMemory(int n) {
        if (n == -1) {
            n = this.calculateSize(false);
        } else if (n <= 0) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Structure size must be greater than zero: ").append(n)));
        }
        if (n != -1) {
            if (this.memory == null || this.memory instanceof AutoAllocated) {
                this.memory = this.autoAllocate(n);
            }
            this.size = n;
        }
    }

    public void read() {
        if (this.memory == PLACEHOLDER_MEMORY) {
            return;
        }
        this.readCalled = true;
        this.ensureAllocated();
        if (Structure.busy().contains(this)) {
            return;
        }
        Structure.busy().add(this);
        if (this instanceof ByReference) {
            Structure.reading().put(this.getPointer(), this);
        }
        try {
            for (StructField structField : this.fields().values()) {
                this.readField(structField);
            }
        }
        finally {
            Structure.busy().remove(this);
            if (Structure.reading().get(this.getPointer()) == this) {
                Structure.reading().remove(this.getPointer());
            }
        }
    }

    protected void cacheTypeInfo(Pointer pointer) {
        this.typeInfo = pointer.peer;
    }

    @Deprecated
    protected final void setFieldOrder(String[] stringArray) {
        throw new Error("This method is obsolete, use getFieldOrder() instead");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static int size(Class<?> clazz, Structure structure) {
        LayoutInfo layoutInfo;
        Map<Class<?>, LayoutInfo> map = Structure.layoutInfo;
        synchronized (map) {
            layoutInfo = Structure.layoutInfo.get(clazz);
        }
        int n = layoutInfo != null && !LayoutInfo.access$000(layoutInfo) ? LayoutInfo.access$100(layoutInfo) : -1;
        if (n != -1) return n;
        if (structure != null) return structure.size();
        structure = Structure.newInstance(clazz, PLACEHOLDER_MEMORY);
        return structure.size();
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

            @Override
            protected synchronized Map<Pointer, Structure> initialValue() {
                return new HashMap<Pointer, Structure>();
            }

            @Override
            protected Object initialValue() {
                return this.initialValue();
            }
        };
        busy = new ThreadLocal<Set<Structure>>(){

            @Override
            protected synchronized Set<Structure> initialValue() {
                return new StructureSet();
            }

            @Override
            protected Object initialValue() {
                return this.initialValue();
            }
        };
        PLACEHOLDER_MEMORY = new Pointer(0L){

            @Override
            public Pointer share(long l, long l2) {
                return this;
            }
        };
    }

    protected int getStructAlignment() {
        if (this.size == -1) {
            this.calculateSize(true);
        }
        return this.structAlignment;
    }

    protected Structure() {
        this(0);
    }

    private int addPadding(int n, int n2) {
        if (this.actualAlignType != 1 && n % n2 != 0) {
            n += n2 - n % n2;
        }
        return n;
    }

    private void initializeFields() {
        List<Field> list = this.getFieldList();
        for (Field field : list) {
            try {
                Object object = field.get(this);
                if (object != null) continue;
                this.initializeField(field, field.getType());
            }
            catch (Exception exception) {
                throw new Error(String.valueOf(new StringBuilder().append("Exception reading field '").append(field.getName()).append("' in ").append(this.getClass())), exception);
            }
        }
    }

    private String toString(int n, boolean bl, boolean bl2) {
        Object object;
        this.ensureAllocated();
        String string = System.getProperty("line.separator");
        String string2 = String.valueOf(new StringBuilder().append(this.format(this.getClass())).append("(").append(this.getPointer()).append(")"));
        if (!(this.getPointer() instanceof Memory)) {
            string2 = String.valueOf(new StringBuilder().append(string2).append(" (").append(this.size()).append(" bytes)"));
        }
        String string3 = "";
        for (int i = 0; i < n; ++i) {
            string3 = String.valueOf(new StringBuilder().append(string3).append("  "));
            if (2 > 0) continue;
            return null;
        }
        String string4 = string;
        if (!bl) {
            string4 = "...}";
        } else {
            Iterator<StructField> iterator = this.fields().values().iterator();
            while (iterator.hasNext()) {
                object = iterator.next();
                Object object2 = this.getFieldValue(((StructField)object).field);
                String string5 = this.format(((StructField)object).type);
                String string6 = "";
                string4 = String.valueOf(new StringBuilder().append(string4).append(string3));
                if (((StructField)object).type.isArray() && object2 != null) {
                    string5 = this.format(((StructField)object).type.getComponentType());
                    string6 = String.valueOf(new StringBuilder().append("[").append(Array.getLength(object2)).append("]"));
                }
                string4 = String.valueOf(new StringBuilder().append(string4).append("  ").append(string5).append(" ").append(((StructField)object).name).append(string6).append("@").append(Integer.toHexString(((StructField)object).offset)));
                if (object2 instanceof Structure) {
                    object2 = ((Structure)object2).toString(n + 1, !(object2 instanceof ByReference), bl2);
                }
                string4 = String.valueOf(new StringBuilder().append(string4).append("="));
                string4 = object2 instanceof Long ? String.valueOf(new StringBuilder().append(string4).append(Long.toHexString((Long)object2))) : (object2 instanceof Integer ? String.valueOf(new StringBuilder().append(string4).append(Integer.toHexString((Integer)object2))) : (object2 instanceof Short ? String.valueOf(new StringBuilder().append(string4).append(Integer.toHexString(((Short)object2).shortValue()))) : (object2 instanceof Byte ? String.valueOf(new StringBuilder().append(string4).append(Integer.toHexString(((Byte)object2).byteValue()))) : String.valueOf(new StringBuilder().append(string4).append(String.valueOf(object2).trim())))));
                string4 = String.valueOf(new StringBuilder().append(string4).append(string));
                if (iterator.hasNext()) continue;
                string4 = String.valueOf(new StringBuilder().append(string4).append(string3).append("}"));
            }
        }
        if (n == 0 && bl2) {
            int n2 = 4;
            string4 = String.valueOf(new StringBuilder().append(string4).append(string).append("memory dump").append(string));
            object = this.getPointer().getByteArray(0L, this.size());
            for (int i = 0; i < ((Object)object).length; ++i) {
                if (i % 4 == 0) {
                    string4 = String.valueOf(new StringBuilder().append(string4).append("["));
                }
                if (object[i] >= 0 && object[i] < 16) {
                    string4 = String.valueOf(new StringBuilder().append(string4).append("0"));
                }
                string4 = String.valueOf(new StringBuilder().append(string4).append(Integer.toHexString(object[i] & 0xFF)));
                if (i % 4 != 3 || i >= ((Object)object).length - 1) continue;
                string4 = String.valueOf(new StringBuilder().append(string4).append("]").append(string));
                if (-2 <= 0) continue;
                return null;
            }
            string4 = String.valueOf(new StringBuilder().append(string4).append("]"));
        }
        return String.valueOf(new StringBuilder().append(string2).append(" {").append(string4));
    }

    protected Structure(Pointer pointer, int n) {
        this(pointer, n, null);
    }

    public static Structure newInstance(Class<?> clazz, Pointer pointer) throws IllegalArgumentException {
        try {
            Constructor<?> constructor = clazz.getConstructor(Pointer.class);
            return (Structure)constructor.newInstance(pointer);
        }
        catch (NoSuchMethodException noSuchMethodException) {
        }
        catch (SecurityException securityException) {
        }
        catch (IllegalAccessException | InstantiationException | InvocationTargetException reflectiveOperationException) {
            String string = String.valueOf(new StringBuilder().append("Can't instantiate ").append(clazz));
            throw new IllegalArgumentException(string, reflectiveOperationException);
        }
        Structure structure = Structure.newInstance(clazz);
        if (pointer != PLACEHOLDER_MEMORY) {
            structure.useMemory(pointer);
        }
        return structure;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private List<String> fieldOrder() {
        Class<?> clazz = this.getClass();
        Map<Class<?>, List<String>> map = fieldOrder;
        synchronized (map) {
            List<String> list = fieldOrder.get(clazz);
            if (list == null) {
                list = this.getFieldOrder();
                fieldOrder.put(clazz, list);
            }
            return list;
        }
    }

    public boolean equals(Object object) {
        return object instanceof Structure && object.getClass() == this.getClass() && ((Structure)object).getPointer().equals(this.getPointer());
    }

    protected void sortFields(List<Field> list, List<String> list2) {
        block0: for (int i = 0; i < list2.size(); ++i) {
            String string = list2.get(i);
            for (int j = 0; j < list.size(); ++j) {
                Field field = list.get(j);
                if (!string.equals(field.getName())) continue;
                Collections.swap(list, i, j);
                continue block0;
            }
            if (0 <= 0) continue;
            return;
        }
    }

    public void clear() {
        this.ensureAllocated();
        this.memory.clear(this.size());
    }

    static Pointer access$2000() {
        return PLACEHOLDER_MEMORY;
    }

    public boolean dataEquals(Structure structure, boolean bl) {
        byte[] byArray;
        byte[] byArray2;
        if (bl) {
            structure.getPointer().clear(structure.size());
            structure.write();
            this.getPointer().clear(this.size());
            this.write();
        }
        if ((byArray2 = structure.getPointer().getByteArray(0L, structure.size())).length == (byArray = this.getPointer().getByteArray(0L, this.size())).length) {
            for (int i = 0; i < byArray2.length; ++i) {
                if (byArray2[i] == byArray[i]) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    static Map<Pointer, Structure> reading() {
        return reads.get();
    }

    private LayoutInfo deriveLayout(boolean bl, boolean bl2) {
        int n = 0;
        List<Field> list = this.getFields(bl);
        if (list == null) {
            return null;
        }
        LayoutInfo layoutInfo = new LayoutInfo(null);
        LayoutInfo.access$202(layoutInfo, this.alignType);
        LayoutInfo.access$302(layoutInfo, this.typeMapper);
        boolean bl3 = true;
        for (Field field : list) {
            int n2 = field.getModifiers();
            Class<?> clazz = field.getType();
            if (clazz.isArray()) {
                LayoutInfo.access$002(layoutInfo, true);
            }
            StructField structField = new StructField();
            structField.isVolatile = Modifier.isVolatile(n2);
            structField.isReadOnly = Modifier.isFinal(n2);
            if (structField.isReadOnly) {
                if (!Platform.RO_FIELDS) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("This VM does not support read-only fields (field '").append(field.getName()).append("' within ").append(this.getClass()).append(")")));
                }
                field.setAccessible(true);
            }
            structField.field = field;
            structField.name = field.getName();
            structField.type = clazz;
            if (Callback.class.isAssignableFrom(clazz) && !clazz.isInterface()) {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Structure Callback field '").append(field.getName()).append("' must be an interface")));
            }
            if (clazz.isArray() && Structure.class.equals(clazz.getComponentType())) {
                String string = "Nested Structure arrays must use a derived Structure type so that the size of the elements can be determined";
                throw new IllegalArgumentException(string);
            }
            int n3 = 1;
            if (Modifier.isPublic(field.getModifiers())) {
                Object object;
                ToNativeConverter toNativeConverter;
                Object object2 = this.getFieldValue(structField.field);
                if (object2 == null && clazz.isArray()) {
                    if (bl) {
                        throw new IllegalStateException("Array fields must be initialized");
                    }
                    return null;
                }
                Class<Object> clazz2 = clazz;
                if (NativeMapped.class.isAssignableFrom(clazz)) {
                    toNativeConverter = NativeMappedConverter.getInstance(clazz);
                    clazz2 = ((NativeMappedConverter)toNativeConverter).nativeType();
                    structField.writeConverter = toNativeConverter;
                    structField.readConverter = toNativeConverter;
                    structField.context = new StructureReadContext(this, field);
                } else if (this.typeMapper != null) {
                    toNativeConverter = this.typeMapper.getToNativeConverter(clazz);
                    object = this.typeMapper.getFromNativeConverter(clazz);
                    if (toNativeConverter != null && object != null) {
                        clazz2 = (object2 = toNativeConverter.toNative(object2, new StructureWriteContext(this, structField.field))) != null ? object2.getClass() : Pointer.class;
                        structField.writeConverter = toNativeConverter;
                        structField.readConverter = object;
                        structField.context = new StructureReadContext(this, field);
                    } else if (toNativeConverter != null || object != null) {
                        String string = String.valueOf(new StringBuilder().append("Structures require bidirectional type conversion for ").append(clazz));
                        throw new IllegalArgumentException(string);
                    }
                }
                if (object2 == null) {
                    object2 = this.initializeField(structField.field, clazz);
                }
                try {
                    structField.size = this.getNativeSize(clazz2, object2);
                    n3 = this.getNativeAlignment(clazz2, object2, bl3);
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    if (!bl && this.typeMapper == null) {
                        return null;
                    }
                    object = String.valueOf(new StringBuilder().append("Invalid Structure field in ").append(this.getClass()).append(", field name '").append(structField.name).append("' (").append(structField.type).append("): ").append(illegalArgumentException.getMessage()));
                    throw new IllegalArgumentException((String)object, illegalArgumentException);
                }
                if (n3 == 0) {
                    throw new Error(String.valueOf(new StringBuilder().append("Field alignment is zero for field '").append(structField.name).append("' within ").append(this.getClass())));
                }
                LayoutInfo.access$402(layoutInfo, Math.max(LayoutInfo.access$400(layoutInfo), n3));
                if (n % n3 != 0) {
                    n += n3 - n % n3;
                }
                if (this instanceof Union) {
                    structField.offset = 0;
                    n = Math.max(n, structField.size);
                } else {
                    structField.offset = n;
                    n += structField.size;
                }
                LayoutInfo.access$500(layoutInfo).put(structField.name, structField);
                if (LayoutInfo.access$700(layoutInfo) == null || LayoutInfo.access$700((LayoutInfo)layoutInfo).size < structField.size || LayoutInfo.access$700((LayoutInfo)layoutInfo).size == structField.size && Structure.class.isAssignableFrom(structField.type)) {
                    LayoutInfo.access$702(layoutInfo, structField);
                }
            }
            bl3 = false;
        }
        if (n > 0) {
            int n4 = this.addPadding(n, LayoutInfo.access$400(layoutInfo));
            if (this instanceof ByValue && !bl2) {
                this.getTypeInfo();
            }
            LayoutInfo.access$102(layoutInfo, n4);
            return layoutInfo;
        }
        throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Structure ").append(this.getClass()).append(" has unknown or zero size (ensure all fields are public)")));
    }

    private void initializeTypeMapper(TypeMapper typeMapper) {
        if (typeMapper == null) {
            typeMapper = Native.getTypeMapper(this.getClass());
        }
        this.typeMapper = typeMapper;
        this.layoutChanged();
    }

    public static Structure newInstance(Class<?> clazz) throws IllegalArgumentException {
        try {
            Structure structure = (Structure)clazz.newInstance();
            if (structure instanceof ByValue) {
                structure.allocateMemory();
            }
            return structure;
        }
        catch (IllegalAccessException | InstantiationException reflectiveOperationException) {
            String string = String.valueOf(new StringBuilder().append("Can't instantiate ").append(clazz));
            throw new IllegalArgumentException(string, reflectiveOperationException);
        }
    }

    static void access$1900(Structure structure, boolean bl) {
        structure.ensureAllocated(bl);
    }

    protected Structure(Pointer pointer, int n, TypeMapper typeMapper) {
        this.setAlignType(n);
        this.setStringEncoding(Native.getStringEncoding(this.getClass()));
        this.initializeTypeMapper(typeMapper);
        this.validateFields();
        if (pointer != null) {
            this.useMemory(pointer, 0, true);
        } else {
            this.allocateMemory(-1);
        }
        this.initializeFields();
    }

    private String format(Class<?> clazz) {
        String string = clazz.getName();
        int n = string.lastIndexOf(".");
        return string.substring(n + 1);
    }

    static int size(Class<?> clazz) {
        return Structure.size(clazz, null);
    }

    public void setAutoWrite(boolean bl) {
        this.autoWrite = bl;
    }

    void conditionalAutoRead() {
        if (!this.readCalled) {
            this.autoRead();
        }
    }

    protected int getNativeSize(Class<?> clazz, Object object) {
        return Native.getNativeSize(clazz, object);
    }

    protected void useMemory(Pointer pointer, int n) {
        this.useMemory(pointer, n, false);
    }

    static Structure updateStructureByReference(Class<?> clazz, Structure structure, Pointer pointer) {
        if (pointer == null) {
            structure = null;
        } else if (structure == null || !pointer.equals(structure.getPointer())) {
            Structure structure2 = Structure.reading().get(pointer);
            if (structure2 != null && clazz.equals(structure2.getClass())) {
                structure = structure2;
                structure.autoRead();
            } else {
                structure = Structure.newInstance(clazz, pointer);
                structure.conditionalAutoRead();
            }
        } else {
            structure.autoRead();
        }
        return structure;
    }

    public String toString(boolean bl) {
        return this.toString(0, true, bl);
    }

    protected Structure(int n) {
        this(null, n);
    }

    public static List<String> createFieldsOrder(List<String> list, List<String> list2) {
        ArrayList<String> arrayList = new ArrayList<String>(list.size() + list2.size());
        arrayList.addAll(list);
        arrayList.addAll(list2);
        return Collections.unmodifiableList(arrayList);
    }

    static Set<Structure> busy() {
        return busy.get();
    }

    public void autoRead() {
        if (this.getAutoRead()) {
            this.read();
            if (this.array != null) {
                for (int i = 1; i < this.array.length; ++i) {
                    this.array[i].autoRead();
                    if (0 <= 3) continue;
                    return;
                }
            }
        }
    }

    protected int calculateSize(boolean bl) {
        return this.calculateSize(bl, false);
    }

    protected void setStringEncoding(String string) {
        this.encoding = string;
    }

    protected Object readField(StructField structField) {
        Pointer pointer;
        Object object;
        int n = structField.offset;
        Class<?> clazz = structField.type;
        FromNativeConverter fromNativeConverter = structField.readConverter;
        if (fromNativeConverter != null) {
            clazz = fromNativeConverter.nativeType();
        }
        Object object2 = object = Structure.class.isAssignableFrom(clazz) || Callback.class.isAssignableFrom(clazz) || Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(clazz) || Pointer.class.isAssignableFrom(clazz) || NativeMapped.class.isAssignableFrom(clazz) || clazz.isArray() ? this.getFieldValue(structField.field) : null;
        Object object3 = clazz == String.class ? ((pointer = this.memory.getPointer(n)) == null ? null : pointer.getString(0L, this.encoding)) : this.memory.getValue(n, clazz, object);
        if (fromNativeConverter != null) {
            object3 = fromNativeConverter.fromNative(object3, structField.context);
            if (object != null && object.equals(object3)) {
                object3 = object;
            }
        }
        if (clazz.equals(String.class) || clazz.equals(WString.class)) {
            this.nativeStrings.put(String.valueOf(new StringBuilder().append(structField.name).append(".ptr")), this.memory.getPointer(n));
            this.nativeStrings.put(String.valueOf(new StringBuilder().append(structField.name).append(".val")), object3);
        }
        this.setFieldValue(structField.field, object3, true);
        return object3;
    }

    public Structure[] toArray(Structure[] structureArray) {
        int n;
        this.ensureAllocated();
        if (this.memory instanceof AutoAllocated) {
            Memory memory = (Memory)this.memory;
            n = structureArray.length * this.size();
            if (memory.size() < (long)n) {
                this.useMemory(this.autoAllocate(n));
            }
        }
        structureArray[0] = this;
        int n2 = this.size();
        for (n = 1; n < structureArray.length; ++n) {
            structureArray[n] = Structure.newInstance(this.getClass(), this.memory.share(n * n2, n2));
            structureArray[n].conditionalAutoRead();
            if (2 > 0) continue;
            return null;
        }
        if (!(this instanceof ByValue)) {
            this.array = structureArray;
        }
        return structureArray;
    }

    private void validateField(String string, Class<?> clazz) {
        ToNativeConverter toNativeConverter;
        if (this.typeMapper != null && (toNativeConverter = this.typeMapper.getToNativeConverter(clazz)) != null) {
            this.validateField(string, toNativeConverter.nativeType());
            return;
        }
        if (clazz.isArray()) {
            this.validateField(string, clazz.getComponentType());
        } else {
            try {
                this.getNativeSize(clazz);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                String string2 = String.valueOf(new StringBuilder().append("Invalid Structure field in ").append(this.getClass()).append(", field name '").append(string).append("' (").append(clazz).append("): ").append(illegalArgumentException.getMessage()));
                throw new IllegalArgumentException(string2, illegalArgumentException);
            }
        }
    }

    protected void allocateMemory() {
        this.allocateMemory(false);
    }

    public static List<String> createFieldsOrder(List<String> list, String ... stringArray) {
        return Structure.createFieldsOrder(list, Arrays.asList(stringArray));
    }

    static void validate(Class<?> clazz) {
        Structure.newInstance(clazz, PLACEHOLDER_MEMORY);
    }

    public static void autoWrite(Structure[] structureArray) {
        Structure.structureArrayCheck(structureArray);
        if (structureArray[0].array == structureArray) {
            structureArray[0].autoWrite();
        } else {
            for (int i = 0; i < structureArray.length; ++i) {
                if (structureArray[i] == null) continue;
                structureArray[i].autoWrite();
            }
        }
    }

    private void validateFields() {
        List<Field> list = this.getFieldList();
        for (Field field : list) {
            this.validateField(field.getName(), field.getType());
        }
    }

    public static List<String> createFieldsOrder(String string) {
        return Collections.unmodifiableList(Collections.singletonList(string));
    }

    Pointer getTypeInfo() {
        Pointer pointer = Structure.getTypeInfo(this);
        this.cacheTypeInfo(pointer);
        return pointer;
    }

    protected String getStringEncoding() {
        return this.encoding;
    }

    private Object initializeField(Field field, Class<?> clazz) {
        Object object = null;
        if (Structure.class.isAssignableFrom(clazz) && !ByReference.class.isAssignableFrom(clazz)) {
            try {
                object = Structure.newInstance(clazz, PLACEHOLDER_MEMORY);
                this.setFieldValue(field, object);
            }
            catch (IllegalArgumentException illegalArgumentException) {
                String string = "Can't determine size of nested structure";
                throw new IllegalArgumentException(string, illegalArgumentException);
            }
        } else if (NativeMapped.class.isAssignableFrom(clazz)) {
            NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(clazz);
            object = nativeMappedConverter.defaultValue();
            this.setFieldValue(field, object);
        }
        return object;
    }

    private static Structure newInstance(Class<?> clazz, long l) {
        try {
            Structure structure = Structure.newInstance(clazz, l == 0L ? PLACEHOLDER_MEMORY : new Pointer(l));
            if (l != 0L) {
                structure.conditionalAutoRead();
            }
            return structure;
        }
        catch (Throwable throwable) {
            System.err.println(String.valueOf(new StringBuilder().append("JNA: Error creating structure: ").append(throwable)));
            return null;
        }
    }

    protected void writeField(StructField structField) {
        CharSequence charSequence;
        if (structField.isReadOnly) {
            return;
        }
        int n = structField.offset;
        Object object = this.getFieldValue(structField.field);
        Class<?> clazz = structField.type;
        ToNativeConverter toNativeConverter = structField.writeConverter;
        if (toNativeConverter != null) {
            object = toNativeConverter.toNative(object, new StructureWriteContext(this, structField.field));
            clazz = toNativeConverter.nativeType();
        }
        if (String.class == clazz || WString.class == clazz) {
            boolean bl;
            boolean bl2 = bl = clazz == WString.class;
            if (object != null) {
                if (this.nativeStrings.containsKey(String.valueOf(new StringBuilder().append(structField.name).append(".ptr"))) && object.equals(this.nativeStrings.get(String.valueOf(new StringBuilder().append(structField.name).append(".val"))))) {
                    return;
                }
                charSequence = bl ? new NativeString(object.toString(), true) : new NativeString(object.toString(), this.encoding);
                this.nativeStrings.put(structField.name, charSequence);
                object = ((NativeString)charSequence).getPointer();
            } else {
                this.nativeStrings.remove(structField.name);
            }
            this.nativeStrings.remove(String.valueOf(new StringBuilder().append(structField.name).append(".ptr")));
            this.nativeStrings.remove(String.valueOf(new StringBuilder().append(structField.name).append(".val")));
        }
        try {
            this.memory.setValue(n, object, clazz);
        }
        catch (IllegalArgumentException illegalArgumentException) {
            charSequence = String.valueOf(new StringBuilder().append("Structure field \"").append(structField.name).append("\" was declared as ").append(structField.type).append(structField.type == clazz ? "" : String.valueOf(new StringBuilder().append(" (native type ").append(clazz).append(")"))).append(", which is not supported within a Structure"));
            throw new IllegalArgumentException((String)charSequence, illegalArgumentException);
        }
    }

    protected Structure(Pointer pointer) {
        this(pointer, 0);
    }

    protected abstract List<String> getFieldOrder();

    public boolean dataEquals(Structure structure) {
        return this.dataEquals(structure, false);
    }

    protected Structure(TypeMapper typeMapper) {
        this(null, 0, typeMapper);
    }

    public boolean getAutoWrite() {
        return this.autoWrite;
    }

    public int hashCode() {
        Pointer pointer = this.getPointer();
        if (pointer != null) {
            return this.getPointer().hashCode();
        }
        return this.getClass().hashCode();
    }

    public void setAutoRead(boolean bl) {
        this.autoRead = bl;
    }

    protected Structure(int n, TypeMapper typeMapper) {
        this(null, n, typeMapper);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    StructField typeInfoField() {
        Map<Class<?>, LayoutInfo> map = layoutInfo;
        // MONITORENTER : map
        LayoutInfo layoutInfo = Structure.layoutInfo.get(this.getClass());
        // MONITOREXIT : map
        if (layoutInfo == null) return null;
        return LayoutInfo.access$700(layoutInfo);
    }

    private static <T extends Comparable<T>> List<T> sort(Collection<? extends T> collection) {
        ArrayList<? extends T> arrayList = new ArrayList<T>(collection);
        Collections.sort(arrayList);
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    int calculateSize(boolean bl, boolean bl2) {
        int n = -1;
        Class<?> clazz = this.getClass();
        Map<Class<?>, LayoutInfo> map = layoutInfo;
        // MONITORENTER : map
        LayoutInfo layoutInfo = Structure.layoutInfo.get(clazz);
        // MONITOREXIT : map
        if (layoutInfo == null || this.alignType != LayoutInfo.access$200(layoutInfo) || this.typeMapper != LayoutInfo.access$300(layoutInfo)) {
            layoutInfo = this.deriveLayout(bl, bl2);
        }
        if (layoutInfo == null) return n;
        this.structAlignment = LayoutInfo.access$400(layoutInfo);
        this.structFields = LayoutInfo.access$500(layoutInfo);
        if (LayoutInfo.access$000(layoutInfo)) return LayoutInfo.access$100(layoutInfo);
        map = Structure.layoutInfo;
        // MONITORENTER : map
        if (!Structure.layoutInfo.containsKey(clazz) || this.alignType != 0 || this.typeMapper != null) {
            Structure.layoutInfo.put(clazz, layoutInfo);
        }
        // MONITOREXIT : map
        return LayoutInfo.access$100(layoutInfo);
    }

    protected Memory autoAllocate(int n) {
        return new AutoAllocated(n);
    }

    public void autoWrite() {
        if (this.getAutoWrite()) {
            this.write();
            if (this.array != null) {
                for (int i = 1; i < this.array.length; ++i) {
                    this.array[i].autoWrite();
                    if (null == null) continue;
                    return;
                }
            }
        }
    }

    protected void setAlignType(int n) {
        this.alignType = n;
        if (n == 0 && (n = Native.getStructureAlignment(this.getClass())) == 0) {
            n = Platform.isWindows() ? 3 : 2;
        }
        this.actualAlignType = n;
        this.layoutChanged();
    }

    void useMemory(Pointer pointer, int n, boolean bl) {
        try {
            this.nativeStrings.clear();
            if (this instanceof ByValue && !bl) {
                byte[] byArray = new byte[this.size()];
                pointer.read(0L, byArray, 0, byArray.length);
                this.memory.write(0L, byArray, 0, byArray.length);
            } else {
                this.memory = pointer.share(n);
                if (this.size == -1) {
                    this.size = this.calculateSize(false);
                }
                if (this.size != -1) {
                    this.memory = pointer.share(n, this.size);
                }
            }
            this.array = null;
            this.readCalled = false;
            return;
        }
        catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new IllegalArgumentException("Structure exceeds provided memory bounds", indexOutOfBoundsException);
        }
    }

    public void write() {
        if (this.memory == PLACEHOLDER_MEMORY) {
            return;
        }
        this.ensureAllocated();
        if (this instanceof ByValue) {
            this.getTypeInfo();
        }
        if (Structure.busy().contains(this)) {
            return;
        }
        Structure.busy().add(this);
        try {
            for (StructField structField : this.fields().values()) {
                if (structField.isVolatile) continue;
                this.writeField(structField);
            }
        }
        finally {
            Structure.busy().remove(this);
        }
    }

    protected int fieldOffset(String string) {
        this.ensureAllocated();
        StructField structField = this.fields().get(string);
        if (structField == null) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No such field: ").append(string)));
        }
        return structField.offset;
    }

    public static List<String> createFieldsOrder(String ... stringArray) {
        return Collections.unmodifiableList(Arrays.asList(stringArray));
    }

    Map<String, StructField> fields() {
        return this.structFields;
    }

    public int size() {
        this.ensureAllocated();
        return this.size;
    }

    protected void useMemory(Pointer pointer) {
        this.useMemory(pointer, 0);
    }

    protected List<Field> getFieldList() {
        ArrayList<Field> arrayList = new ArrayList<Field>();
        Class<?> clazz = this.getClass();
        while (!clazz.equals(Structure.class)) {
            ArrayList<Field> arrayList2 = new ArrayList<Field>();
            Field[] fieldArray = clazz.getDeclaredFields();
            for (int i = 0; i < fieldArray.length; ++i) {
                int n = fieldArray[i].getModifiers();
                if (Modifier.isStatic(n) || !Modifier.isPublic(n)) continue;
                arrayList2.add(fieldArray[i]);
                if (2 >= 0) continue;
                return null;
            }
            arrayList.addAll(0, arrayList2);
            clazz = clazz.getSuperclass();
        }
        return arrayList;
    }

    void setFieldValue(Field field, Object object) {
        this.setFieldValue(field, object, false);
    }

    private Class<?> baseClass() {
        if ((this instanceof ByReference || this instanceof ByValue) && Structure.class.isAssignableFrom(this.getClass().getSuperclass())) {
            return this.getClass().getSuperclass();
        }
        return this.getClass();
    }

    private void setFieldValue(Field field, Object object, boolean bl) {
        try {
            field.set(this, object);
        }
        catch (IllegalAccessException illegalAccessException) {
            int n = field.getModifiers();
            if (Modifier.isFinal(n)) {
                if (bl) {
                    throw new UnsupportedOperationException(String.valueOf(new StringBuilder().append("This VM does not support Structures with final fields (field '").append(field.getName()).append("' within ").append(this.getClass()).append(")")), illegalAccessException);
                }
                throw new UnsupportedOperationException(String.valueOf(new StringBuilder().append("Attempt to write to read-only field '").append(field.getName()).append("' within ").append(this.getClass())), illegalAccessException);
            }
            throw new Error(String.valueOf(new StringBuilder().append("Unexpectedly unable to write to field '").append(field.getName()).append("' within ").append(this.getClass())), illegalAccessException);
        }
    }

    static Pointer getTypeInfo(Object object) {
        return FFIType.get(object);
    }

    protected int getNativeSize(Class<?> clazz) {
        return this.getNativeSize(clazz, null);
    }

    public Structure[] toArray(int n) {
        return this.toArray((Structure[])Array.newInstance(this.getClass(), n));
    }

    TypeMapper getTypeMapper() {
        return this.typeMapper;
    }

    private static void structureArrayCheck(Structure[] structureArray) {
        if (ByReference[].class.isAssignableFrom(structureArray.getClass())) {
            return;
        }
        Pointer pointer = structureArray[0].getPointer();
        int n = structureArray[0].size();
        for (int i = 1; i < structureArray.length; ++i) {
            if (structureArray[i].getPointer().peer == pointer.peer + (long)(n * i)) continue;
            String string = String.valueOf(new StringBuilder().append("Structure array elements must use contiguous memory (bad backing address at Structure array index ").append(i).append(")"));
            throw new IllegalArgumentException(string);
        }
    }

    protected void ensureAllocated() {
        this.ensureAllocated(false);
    }

    public void writeField(String string) {
        this.ensureAllocated();
        StructField structField = this.fields().get(string);
        if (structField == null) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No such field: ").append(string)));
        }
        this.writeField(structField);
    }

    public void writeField(String string, Object object) {
        this.ensureAllocated();
        StructField structField = this.fields().get(string);
        if (structField == null) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("No such field: ").append(string)));
        }
        this.setFieldValue(structField.field, object);
        this.writeField(structField);
    }

    Object getFieldValue(Field field) {
        try {
            return field.get(this);
        }
        catch (Exception exception) {
            throw new Error(String.valueOf(new StringBuilder().append("Exception reading field '").append(field.getName()).append("' in ").append(this.getClass())), exception);
        }
    }

    private void layoutChanged() {
        if (this.size != -1) {
            this.size = -1;
            if (this.memory instanceof AutoAllocated) {
                this.memory = null;
            }
            this.ensureAllocated();
        }
    }

    public Pointer getPointer() {
        this.ensureAllocated();
        return this.memory;
    }

    protected int getNativeAlignment(Class<?> clazz, Object object, boolean bl) {
        int n = 1;
        if (NativeMapped.class.isAssignableFrom(clazz)) {
            NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(clazz);
            clazz = nativeMappedConverter.nativeType();
            object = nativeMappedConverter.toNative(object, new ToNativeContext());
        }
        int n2 = Native.getNativeSize(clazz, object);
        if (clazz.isPrimitive() || Long.class == clazz || Integer.class == clazz || Short.class == clazz || Character.class == clazz || Byte.class == clazz || Boolean.class == clazz || Float.class == clazz || Double.class == clazz) {
            n = n2;
        } else if (Pointer.class.isAssignableFrom(clazz) && !Function.class.isAssignableFrom(clazz) || Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(clazz) || Callback.class.isAssignableFrom(clazz) || WString.class == clazz || String.class == clazz) {
            n = Pointer.SIZE;
        } else if (Structure.class.isAssignableFrom(clazz)) {
            if (ByReference.class.isAssignableFrom(clazz)) {
                n = Pointer.SIZE;
            } else {
                if (object == null) {
                    object = Structure.newInstance(clazz, PLACEHOLDER_MEMORY);
                }
                n = ((Structure)object).getStructAlignment();
            }
        } else if (clazz.isArray()) {
            n = this.getNativeAlignment(clazz.getComponentType(), null, bl);
        } else {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Type ").append(clazz).append(" has unknown native alignment")));
        }
        if (this.actualAlignType == 1) {
            n = 1;
        } else if (this.actualAlignType == 3) {
            n = Math.min(8, n);
        } else if (this.actualAlignType == 2) {
            if (!(bl && Platform.isMac() && Platform.isPPC())) {
                n = Math.min(Native.MAX_ALIGNMENT, n);
            }
            if (!bl && Platform.isAIX() && (clazz == Double.TYPE || clazz == Double.class)) {
                n = 4;
            }
        }
        return n;
    }

    public static interface ByReference {
    }

    static class StructureSet
    extends AbstractCollection<Structure>
    implements Set<Structure> {
        Structure[] elements;
        private int count;

        StructureSet() {
        }

        @Override
        public int size() {
            return this.count;
        }

        private void ensureCapacity(int n) {
            if (this.elements == null) {
                this.elements = new Structure[n * 3 / 2];
            } else if (this.elements.length < n) {
                Structure[] structureArray = new Structure[n * 3 / 2];
                System.arraycopy(this.elements, 0, structureArray, 0, this.elements.length);
                this.elements = structureArray;
            }
        }

        @Override
        public Iterator<Structure> iterator() {
            Structure[] structureArray = new Structure[this.count];
            if (this.count > 0) {
                System.arraycopy(this.elements, 0, structureArray, 0, this.count);
            }
            return Arrays.asList(structureArray).iterator();
        }

        @Override
        public boolean contains(Object object) {
            return this.indexOf((Structure)object) != -1;
        }

        public Structure[] getElements() {
            return this.elements;
        }

        @Override
        public boolean remove(Object object) {
            int n = this.indexOf((Structure)object);
            if (n != -1) {
                if (--this.count >= 0) {
                    this.elements[n] = this.elements[this.count];
                    this.elements[this.count] = null;
                }
                return true;
            }
            return false;
        }

        private int indexOf(Structure structure) {
            for (int i = 0; i < this.count; ++i) {
                Structure structure2 = this.elements[i];
                if (structure != structure2 && (structure.getClass() != structure2.getClass() || structure.size() != structure2.size() || !structure.getPointer().equals(structure2.getPointer()))) continue;
                return i;
            }
            return -1;
        }

        @Override
        public boolean add(Object object) {
            return this.add((Structure)object);
        }

        @Override
        public boolean add(Structure structure) {
            if (!this.contains(structure)) {
                this.ensureCapacity(this.count + 1);
                this.elements[this.count++] = structure;
            }
            return true;
        }
    }

    private static class AutoAllocated
    extends Memory {
        public AutoAllocated(int n) {
            super(n);
            super.clear();
        }

        @Override
        public String toString() {
            return String.valueOf(new StringBuilder().append("auto-").append(super.toString()));
        }
    }

    public static interface ByValue {
    }

    protected static class StructField {
        public ToNativeConverter writeConverter;
        public Class<?> type;
        public FromNativeConverter readConverter;
        public String name;
        public FromNativeContext context;
        public boolean isVolatile;
        public Field field;
        public int size = -1;
        public int offset = -1;
        public boolean isReadOnly;

        public String toString() {
            return String.valueOf(new StringBuilder().append(this.name).append("@").append(this.offset).append("[").append(this.size).append("] (").append(this.type).append(")"));
        }

        protected StructField() {
        }
    }

    private static class LayoutInfo {
        private boolean variable;
        private TypeMapper typeMapper;
        private int alignType = 0;
        private StructField typeInfoField;
        private int size = -1;
        private final Map<String, StructField> fields = Collections.synchronizedMap(new LinkedHashMap());
        private int alignment = 1;

        static int access$100(LayoutInfo layoutInfo) {
            return layoutInfo.size;
        }

        static boolean access$000(LayoutInfo layoutInfo) {
            return layoutInfo.variable;
        }

        static int access$200(LayoutInfo layoutInfo) {
            return layoutInfo.alignType;
        }

        static TypeMapper access$300(LayoutInfo layoutInfo) {
            return layoutInfo.typeMapper;
        }

        static Map access$500(LayoutInfo layoutInfo) {
            return layoutInfo.fields;
        }

        static TypeMapper access$302(LayoutInfo layoutInfo, TypeMapper typeMapper) {
            layoutInfo.typeMapper = typeMapper;
            return typeMapper;
        }

        static StructField access$700(LayoutInfo layoutInfo) {
            return layoutInfo.typeInfoField;
        }

        LayoutInfo(1 var1_1) {
            this();
        }

        static StructField access$702(LayoutInfo layoutInfo, StructField structField) {
            layoutInfo.typeInfoField = structField;
            return structField;
        }

        private LayoutInfo() {
        }

        static int access$102(LayoutInfo layoutInfo, int n) {
            layoutInfo.size = n;
            return n;
        }

        static int access$202(LayoutInfo layoutInfo, int n) {
            layoutInfo.alignType = n;
            return n;
        }

        static int access$400(LayoutInfo layoutInfo) {
            return layoutInfo.alignment;
        }

        static boolean access$002(LayoutInfo layoutInfo, boolean bl) {
            layoutInfo.variable = bl;
            return bl;
        }

        static int access$402(LayoutInfo layoutInfo, int n) {
            layoutInfo.alignment = n;
            return n;
        }
    }

    static class FFIType
    extends Structure {
        private static final int FFI_TYPE_STRUCT = 13;
        public size_t size;
        public short alignment;
        public short type = (short)13;
        public Pointer elements;
        private static final Map<Object, Object> typeInfoMap = new WeakHashMap<Object, Object>();

        private static Pointer get(Object object, Class<?> clazz) {
            Object object2;
            TypeMapper typeMapper = Native.getTypeMapper(clazz);
            if (typeMapper != null && (object2 = typeMapper.getToNativeConverter(clazz)) != null) {
                clazz = object2.nativeType();
            }
            object2 = typeInfoMap;
            synchronized (object2) {
                block15: {
                    block14: {
                        block13: {
                            Object object3;
                            block12: {
                                object3 = typeInfoMap.get(clazz);
                                if (!(object3 instanceof Pointer)) break block12;
                                return (Pointer)object3;
                            }
                            if (object3 instanceof FFIType) {
                                return ((FFIType)object3).getPointer();
                            }
                            if ((!Platform.HAS_BUFFERS || !Buffer.class.isAssignableFrom(clazz)) && !Callback.class.isAssignableFrom(clazz)) break block13;
                            typeInfoMap.put(clazz, FFITypes.access$1800());
                            return FFITypes.access$1800();
                        }
                        if (!Structure.class.isAssignableFrom(clazz)) break block14;
                        if (object == null) {
                            object = FFIType.newInstance(clazz, Structure.access$2000());
                        }
                        if (ByReference.class.isAssignableFrom(clazz)) {
                            typeInfoMap.put(clazz, FFITypes.access$1800());
                            return FFITypes.access$1800();
                        }
                        FFIType fFIType = new FFIType((Structure)object);
                        typeInfoMap.put(clazz, fFIType);
                        return fFIType.getPointer();
                    }
                    if (!NativeMapped.class.isAssignableFrom(clazz)) break block15;
                    NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(clazz);
                    return FFIType.get(nativeMappedConverter.toNative(object, new ToNativeContext()), nativeMappedConverter.nativeType());
                }
                if (clazz.isArray()) {
                    FFIType fFIType = new FFIType(object, clazz);
                    typeInfoMap.put(object, fFIType);
                    return fFIType.getPointer();
                }
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported type ").append(clazz)));
            }
        }

        static {
            if (Native.POINTER_SIZE == 0) {
                throw new Error("Native library not initialized");
            }
            if (FFITypes.access$900() == null) {
                throw new Error("FFI types not initialized");
            }
            typeInfoMap.put(Void.TYPE, FFITypes.access$900());
            typeInfoMap.put(Void.class, FFITypes.access$900());
            typeInfoMap.put(Float.TYPE, FFITypes.access$1000());
            typeInfoMap.put(Float.class, FFITypes.access$1000());
            typeInfoMap.put(Double.TYPE, FFITypes.access$1100());
            typeInfoMap.put(Double.class, FFITypes.access$1100());
            typeInfoMap.put(Long.TYPE, FFITypes.access$1200());
            typeInfoMap.put(Long.class, FFITypes.access$1200());
            typeInfoMap.put(Integer.TYPE, FFITypes.access$1300());
            typeInfoMap.put(Integer.class, FFITypes.access$1300());
            typeInfoMap.put(Short.TYPE, FFITypes.access$1400());
            typeInfoMap.put(Short.class, FFITypes.access$1400());
            Pointer pointer = Native.WCHAR_SIZE == 2 ? FFITypes.access$1500() : FFITypes.access$1600();
            typeInfoMap.put(Character.TYPE, pointer);
            typeInfoMap.put(Character.class, pointer);
            typeInfoMap.put(Byte.TYPE, FFITypes.access$1700());
            typeInfoMap.put(Byte.class, FFITypes.access$1700());
            typeInfoMap.put(Pointer.class, FFITypes.access$1800());
            typeInfoMap.put(String.class, FFITypes.access$1800());
            typeInfoMap.put(WString.class, FFITypes.access$1800());
            typeInfoMap.put(Boolean.TYPE, FFITypes.access$1600());
            typeInfoMap.put(Boolean.class, FFITypes.access$1600());
        }

        static Pointer access$800(Object object, Class clazz) {
            return FFIType.get(object, clazz);
        }

        static Pointer get(Object object) {
            if (object == null) {
                return FFITypes.access$1800();
            }
            if (object instanceof Class) {
                return FFIType.get(null, (Class)object);
            }
            return FFIType.get(object, object.getClass());
        }

        @Override
        protected List<String> getFieldOrder() {
            return Arrays.asList("size", "alignment", "type", "elements");
        }

        private void init(Pointer[] pointerArray) {
            this.elements = new Memory(Pointer.SIZE * pointerArray.length);
            this.elements.write(0L, pointerArray, 0, pointerArray.length);
            this.write();
        }

        private FFIType(Object object, Class<?> clazz) {
            int n = Array.getLength(object);
            Pointer[] pointerArray = new Pointer[n + 1];
            Pointer pointer = FFIType.get(null, clazz.getComponentType());
            for (int i = 0; i < n; ++i) {
                pointerArray[i] = pointer;
                if (0 >= -1) continue;
                throw null;
            }
            this.init(pointerArray);
        }

        private FFIType(Structure structure) {
            Pointer[] pointerArray;
            Structure.access$1900(structure, true);
            if (structure instanceof Union) {
                StructField structField = ((Union)structure).typeInfoField();
                pointerArray = new Pointer[]{FFIType.get(structure.getFieldValue(structField.field), structField.type), null};
            } else {
                pointerArray = new Pointer[structure.fields().size() + 1];
                int n = 0;
                for (StructField structField : structure.fields().values()) {
                    pointerArray[n++] = structure.getFieldTypeInfo(structField);
                }
            }
            this.init(pointerArray);
        }

        public static class size_t
        extends IntegerType {
            private static final long serialVersionUID = 1L;

            public size_t(long l) {
                super(Native.SIZE_T_SIZE, l);
            }

            public size_t() {
                this(0L);
            }
        }

        private static class FFITypes {
            private static Pointer ffi_type_double;
            private static Pointer ffi_type_uint16;
            private static Pointer ffi_type_sint64;
            private static Pointer ffi_type_sint8;
            private static Pointer ffi_type_float;
            private static Pointer ffi_type_sint32;
            private static Pointer ffi_type_void;
            private static Pointer ffi_type_sint16;
            private static Pointer ffi_type_uint64;
            private static Pointer ffi_type_pointer;
            private static Pointer ffi_type_longdouble;
            private static Pointer ffi_type_uint8;
            private static Pointer ffi_type_uint32;

            static Pointer access$1400() {
                return ffi_type_sint16;
            }

            private FFITypes() {
            }

            static Pointer access$1300() {
                return ffi_type_sint32;
            }

            static Pointer access$1000() {
                return ffi_type_float;
            }

            static Pointer access$900() {
                return ffi_type_void;
            }

            static Pointer access$1800() {
                return ffi_type_pointer;
            }

            static Pointer access$1200() {
                return ffi_type_sint64;
            }

            static Pointer access$1600() {
                return ffi_type_uint32;
            }

            static Pointer access$1700() {
                return ffi_type_sint8;
            }

            static Pointer access$1100() {
                return ffi_type_double;
            }

            static Pointer access$1500() {
                return ffi_type_uint16;
            }
        }
    }
}

