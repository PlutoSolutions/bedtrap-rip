/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

import com.sun.jna.Callback;
import com.sun.jna.CallbackReference;
import com.sun.jna.FunctionParameterContext;
import com.sun.jna.FunctionResultContext;
import com.sun.jna.Memory;
import com.sun.jna.MethodParameterContext;
import com.sun.jna.MethodResultContext;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.NativeMapped;
import com.sun.jna.NativeMappedConverter;
import com.sun.jna.NativeString;
import com.sun.jna.Pointer;
import com.sun.jna.StringArray;
import com.sun.jna.Structure;
import com.sun.jna.ToNativeContext;
import com.sun.jna.TypeMapper;
import com.sun.jna.VarArgsChecker;
import com.sun.jna.WString;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

public class Function
extends Pointer {
    public static final int ALT_CONVENTION;
    static final Integer INTEGER_TRUE;
    private NativeLibrary library;
    public static final int MAX_NARGS;
    final String encoding;
    public static final int C_CONVENTION;
    static final String OPTION_INVOKING_METHOD;
    final Map<String, ?> options;
    public static final int THROW_LAST_ERROR;
    private final String functionName;
    private static final VarArgsChecker IS_VARARGS;
    static final Integer INTEGER_FALSE;
    private static final int MASK_CC;
    final int callFlags;
    public static final int USE_VARARGS;

    public static Function getFunction(String string, String string2) {
        return NativeLibrary.getInstance(string).getFunction(string2);
    }

    public static Function getFunction(String string, String string2, int n) {
        return NativeLibrary.getInstance(string).getFunction(string2, n, null);
    }

    @Override
    public String toString() {
        if (this.library != null) {
            return String.valueOf(new StringBuilder().append("native function ").append(this.functionName).append("(").append(this.library.getName()).append(")@0x").append(Long.toHexString(this.peer)));
        }
        return String.valueOf(new StringBuilder().append("native function@0x").append(Long.toHexString(this.peer)));
    }

    Function(NativeLibrary nativeLibrary, String string, int n, String string2) {
        this.checkCallingConvention(n & 0x3F);
        if (string == null) {
            throw new NullPointerException("Function name must not be null");
        }
        this.library = nativeLibrary;
        this.functionName = string;
        this.callFlags = n;
        this.options = nativeLibrary.options;
        this.encoding = string2 != null ? string2 : Native.getDefaultStringEncoding();
        try {
            this.peer = nativeLibrary.getSymbolAddress(string);
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            throw new UnsatisfiedLinkError(String.valueOf(new StringBuilder().append("Error looking up function '").append(string).append("': ").append(unsatisfiedLinkError.getMessage())));
        }
    }

    public int invokeInt(Object[] objectArray) {
        return (Integer)this.invoke(Integer.class, objectArray);
    }

    static int fixedArgs(Method method) {
        return IS_VARARGS.fixedArgs(method);
    }

    public static Function getFunction(Pointer pointer, int n) {
        return Function.getFunction(pointer, n, null);
    }

    Function(Pointer pointer, int n, String string) {
        this.checkCallingConvention(n & 0x3F);
        if (pointer == null || pointer.peer == 0L) {
            throw new NullPointerException("Function address may not be null");
        }
        this.functionName = pointer.toString();
        this.callFlags = n;
        this.peer = pointer.peer;
        this.options = Collections.EMPTY_MAP;
        this.encoding = string != null ? string : Native.getDefaultStringEncoding();
    }

    public Object invokeObject(Object[] objectArray) {
        return this.invoke(Object.class, objectArray);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (object.getClass() == this.getClass()) {
            Function function = (Function)object;
            return function.callFlags == this.callFlags && function.options.equals(this.options) && function.peer == this.peer;
        }
        return false;
    }

    public void invoke(Object[] objectArray) {
        this.invoke(Void.class, objectArray);
    }

    private String invokeString(int n, Object[] objectArray, boolean bl) {
        Pointer pointer = this.invokePointer(n, objectArray);
        String string = null;
        if (pointer != null) {
            string = bl ? pointer.getWideString(0L) : pointer.getString(0L, this.encoding);
        }
        return string;
    }

    private Object convertArgument(Object[] objectArray, int n, Method method, TypeMapper typeMapper, boolean bl, Class<?> clazz) {
        Object object;
        Structure[] structureArray;
        Class<?> clazz2;
        Object object2 = objectArray[n];
        if (object2 != null) {
            clazz2 = object2.getClass();
            structureArray = null;
            if (NativeMapped.class.isAssignableFrom(clazz2)) {
                structureArray = NativeMappedConverter.getInstance(clazz2);
            } else if (typeMapper != null) {
                structureArray = typeMapper.getToNativeConverter(clazz2);
            }
            if (structureArray != null) {
                object = method != null ? new MethodParameterContext(this, objectArray, n, method) : new FunctionParameterContext(this, objectArray, n);
                object2 = structureArray.toNative(object2, (ToNativeContext)object);
            }
        }
        if (object2 == null || this.isPrimitiveArray(object2.getClass())) {
            return object2;
        }
        clazz2 = object2.getClass();
        if (object2 instanceof Structure) {
            structureArray = (Structure)object2;
            structureArray.autoWrite();
            if (structureArray instanceof Structure.ByValue) {
                object = structureArray.getClass();
                if (method != null) {
                    Class<?>[] classArray = method.getParameterTypes();
                    if (IS_VARARGS.isVarArgs(method)) {
                        if (n < classArray.length - 1) {
                            object = classArray[n];
                        } else {
                            Class<?> clazz3 = classArray[classArray.length - 1].getComponentType();
                            if (clazz3 != Object.class) {
                                object = clazz3;
                            }
                        }
                    } else {
                        object = classArray[n];
                    }
                }
                if (Structure.ByValue.class.isAssignableFrom((Class<?>)object)) {
                    return structureArray;
                }
            }
            return structureArray.getPointer();
        }
        if (object2 instanceof Callback) {
            return CallbackReference.getFunctionPointer((Callback)object2);
        }
        if (object2 instanceof String) {
            return new NativeString((String)object2, false).getPointer();
        }
        if (object2 instanceof WString) {
            return new NativeString(object2.toString(), true).getPointer();
        }
        if (object2 instanceof Boolean) {
            return Boolean.TRUE.equals(object2) ? INTEGER_TRUE : INTEGER_FALSE;
        }
        if (String[].class == clazz2) {
            return new StringArray((String[])object2, this.encoding);
        }
        if (WString[].class == clazz2) {
            return new StringArray((WString[])object2);
        }
        if (Pointer[].class == clazz2) {
            return new PointerArray((Pointer[])object2);
        }
        if (NativeMapped[].class.isAssignableFrom(clazz2)) {
            return new NativeMappedArray((NativeMapped[])object2);
        }
        if (Structure[].class.isAssignableFrom(clazz2)) {
            structureArray = (Structure[])object2;
            object = clazz2.getComponentType();
            boolean bl2 = Structure.ByReference.class.isAssignableFrom((Class<?>)object);
            if (clazz != null && !Structure.ByReference[].class.isAssignableFrom(clazz)) {
                if (bl2) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Function ").append(this.getName()).append(" declared Structure[] at parameter ").append(n).append(" but array of ").append(object).append(" was passed")));
                }
                for (int i = 0; i < structureArray.length; ++i) {
                    if (!(structureArray[i] instanceof Structure.ByReference)) continue;
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Function ").append(this.getName()).append(" declared Structure[] at parameter ").append(n).append(" but element ").append(i).append(" is of Structure.ByReference type")));
                }
            }
            if (bl2) {
                Structure.autoWrite(structureArray);
                Pointer[] pointerArray = new Pointer[structureArray.length + 1];
                for (int i = 0; i < structureArray.length; ++i) {
                    pointerArray[i] = structureArray[i] != null ? structureArray[i].getPointer() : null;
                }
                return new PointerArray(pointerArray);
            }
            if (structureArray.length == 0) {
                throw new IllegalArgumentException("Structure array must have non-zero length");
            }
            if (structureArray[0] == null) {
                Structure.newInstance(object).toArray(structureArray);
                return structureArray[0].getPointer();
            }
            Structure.autoWrite(structureArray);
            return structureArray[0].getPointer();
        }
        if (clazz2.isArray()) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported array argument type: ").append(clazz2.getComponentType())));
        }
        if (bl) {
            return object2;
        }
        if (!Native.isSupportedNativeType(object2.getClass())) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported argument type ").append(object2.getClass().getName()).append(" at parameter ").append(n).append(" of function ").append(this.getName())));
        }
        return object2;
    }

    public Object invoke(Class<?> clazz, Object[] objectArray, Map<String, ?> map) {
        Method method = (Method)map.get("invoking-method");
        Class<?>[] classArray = method != null ? method.getParameterTypes() : null;
        return this.invoke(method, classArray, clazz, objectArray, map);
    }

    Object invoke(Object[] objectArray, Class<?> clazz, boolean bl) {
        return this.invoke(objectArray, clazz, bl, 0);
    }

    public Pointer invokePointer(Object[] objectArray) {
        return (Pointer)this.invoke(Pointer.class, objectArray);
    }

    public String getName() {
        return this.functionName;
    }

    public String invokeString(Object[] objectArray, boolean bl) {
        Object object = this.invoke(bl ? WString.class : String.class, objectArray);
        return object != null ? object.toString() : null;
    }

    Object invoke(Object[] objectArray, Class<?> clazz, boolean bl, int n) {
        Object object = null;
        int n2 = this.callFlags | (n & 3) << 7;
        if (clazz == null || clazz == Void.TYPE || clazz == Void.class) {
            Native.invokeVoid(this, this.peer, n2, objectArray);
            object = null;
        } else if (clazz == Boolean.TYPE || clazz == Boolean.class) {
            object = Function.valueOf(Native.invokeInt(this, this.peer, n2, objectArray) != 0);
        } else if (clazz == Byte.TYPE || clazz == Byte.class) {
            object = (byte)Native.invokeInt(this, this.peer, n2, objectArray);
        } else if (clazz == Short.TYPE || clazz == Short.class) {
            object = (short)Native.invokeInt(this, this.peer, n2, objectArray);
        } else if (clazz == Character.TYPE || clazz == Character.class) {
            object = Character.valueOf((char)Native.invokeInt(this, this.peer, n2, objectArray));
        } else if (clazz == Integer.TYPE || clazz == Integer.class) {
            object = Native.invokeInt(this, this.peer, n2, objectArray);
        } else if (clazz == Long.TYPE || clazz == Long.class) {
            object = Native.invokeLong(this, this.peer, n2, objectArray);
        } else if (clazz == Float.TYPE || clazz == Float.class) {
            object = Float.valueOf(Native.invokeFloat(this, this.peer, n2, objectArray));
        } else if (clazz == Double.TYPE || clazz == Double.class) {
            object = Native.invokeDouble(this, this.peer, n2, objectArray);
        } else if (clazz == String.class) {
            object = this.invokeString(n2, objectArray, false);
        } else if (clazz == WString.class) {
            String string = this.invokeString(n2, objectArray, true);
            if (string != null) {
                object = new WString(string);
            }
        } else {
            if (Pointer.class.isAssignableFrom(clazz)) {
                return this.invokePointer(n2, objectArray);
            }
            if (Structure.class.isAssignableFrom(clazz)) {
                if (Structure.ByValue.class.isAssignableFrom(clazz)) {
                    Structure structure = Native.invokeStructure(this, this.peer, n2, objectArray, Structure.newInstance(clazz));
                    structure.autoRead();
                    object = structure;
                } else {
                    object = this.invokePointer(n2, objectArray);
                    if (object != null) {
                        Structure structure = Structure.newInstance(clazz, (Pointer)object);
                        structure.conditionalAutoRead();
                        object = structure;
                    }
                }
            } else if (Callback.class.isAssignableFrom(clazz)) {
                object = this.invokePointer(n2, objectArray);
                if (object != null) {
                    object = CallbackReference.getCallback(clazz, (Pointer)object);
                }
            } else if (clazz == String[].class) {
                Pointer pointer = this.invokePointer(n2, objectArray);
                if (pointer != null) {
                    object = pointer.getStringArray(0L, this.encoding);
                }
            } else if (clazz == WString[].class) {
                Pointer pointer = this.invokePointer(n2, objectArray);
                if (pointer != null) {
                    String[] stringArray = pointer.getWideStringArray(0L);
                    WString[] wStringArray = new WString[stringArray.length];
                    for (int i = 0; i < stringArray.length; ++i) {
                        wStringArray[i] = new WString(stringArray[i]);
                        if (-2 <= 0) continue;
                        return null;
                    }
                    object = wStringArray;
                }
            } else if (clazz == Pointer[].class) {
                Pointer pointer = this.invokePointer(n2, objectArray);
                if (pointer != null) {
                    object = pointer.getPointerArray(0L);
                }
            } else if (bl) {
                object = Native.invokeObject(this, this.peer, n2, objectArray);
                if (object != null && !clazz.isAssignableFrom(object.getClass())) {
                    throw new ClassCastException(String.valueOf(new StringBuilder().append("Return type ").append(clazz).append(" does not match result ").append(object.getClass())));
                }
            } else {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported return type ").append(clazz).append(" in function ").append(this.getName())));
            }
        }
        return object;
    }

    public static Function getFunction(Pointer pointer) {
        return Function.getFunction(pointer, 0, null);
    }

    public int getCallingConvention() {
        return this.callFlags & 0x3F;
    }

    private boolean isPrimitiveArray(Class<?> clazz) {
        return clazz.isArray() && clazz.getComponentType().isPrimitive();
    }

    static {
        THROW_LAST_ERROR = 64;
        MASK_CC = 63;
        OPTION_INVOKING_METHOD = "invoking-method";
        USE_VARARGS = 384;
        MAX_NARGS = 256;
        C_CONVENTION = 0;
        ALT_CONVENTION = 63;
        INTEGER_TRUE = -1;
        INTEGER_FALSE = 0;
        IS_VARARGS = VarArgsChecker.create();
    }

    public static Function getFunction(String string, String string2, int n, String string3) {
        return NativeLibrary.getInstance(string).getFunction(string2, n, string3);
    }

    public Object invoke(Class<?> clazz, Object[] objectArray) {
        return this.invoke(clazz, objectArray, this.options);
    }

    private Pointer invokePointer(int n, Object[] objectArray) {
        long l = Native.invokePointer(this, this.peer, n, objectArray);
        return l == 0L ? null : new Pointer(l);
    }

    public static Function getFunction(Pointer pointer, int n, String string) {
        return new Function(pointer, n, string);
    }

    static Object[] concatenateVarArgs(Object[] objectArray) {
        if (objectArray != null && objectArray.length > 0) {
            Class<?> clazz;
            Object object = objectArray[objectArray.length - 1];
            Class<?> clazz2 = clazz = object != null ? object.getClass() : null;
            if (clazz != null && clazz.isArray()) {
                Object[] objectArray2 = (Object[])object;
                for (int i = 0; i < objectArray2.length; ++i) {
                    if (!(objectArray2[i] instanceof Float)) continue;
                    objectArray2[i] = (double)((Float)objectArray2[i]).floatValue();
                    if (-1 < 1) continue;
                    return null;
                }
                Object[] objectArray3 = new Object[objectArray.length + objectArray2.length];
                System.arraycopy(objectArray, 0, objectArray3, 0, objectArray.length - 1);
                System.arraycopy(objectArray2, 0, objectArray3, objectArray.length - 1, objectArray2.length);
                objectArray3[objectArray3.length - 1] = null;
                objectArray = objectArray3;
            }
        }
        return objectArray;
    }

    public double invokeDouble(Object[] objectArray) {
        return (Double)this.invoke(Double.class, objectArray);
    }

    @Override
    public int hashCode() {
        return this.callFlags + this.options.hashCode() + super.hashCode();
    }

    Object invoke(Method method, Class<?>[] classArray, Class<?> clazz, Object[] objectArray, Map<String, ?> map) {
        Object object;
        Object object2;
        Object[] objectArray2 = new Object[]{};
        if (objectArray != null) {
            if (objectArray.length > 256) {
                throw new UnsupportedOperationException("Maximum argument count is 256");
            }
            objectArray2 = new Object[objectArray.length];
            System.arraycopy(objectArray, 0, objectArray2, 0, objectArray2.length);
        }
        TypeMapper typeMapper = (TypeMapper)map.get("type-mapper");
        boolean bl = Boolean.TRUE.equals(map.get("allow-objects"));
        boolean bl2 = objectArray2.length > 0 && method != null ? Function.isVarArgs(method) : false;
        int n = objectArray2.length > 0 && method != null ? Function.fixedArgs(method) : 0;
        for (int i = 0; i < objectArray2.length; ++i) {
            object2 = method != null ? (bl2 && i >= classArray.length - 1 ? classArray[classArray.length - 1].getComponentType() : classArray[i]) : null;
            objectArray2[i] = this.convertArgument(objectArray2, i, method, typeMapper, bl, (Class<?>)object2);
            if (1 != 2) continue;
            return null;
        }
        Class<?> clazz2 = clazz;
        object2 = null;
        if (NativeMapped.class.isAssignableFrom(clazz)) {
            object2 = object = NativeMappedConverter.getInstance(clazz);
            clazz2 = ((NativeMappedConverter)object).nativeType();
        } else if (typeMapper != null && (object2 = typeMapper.getFromNativeConverter(clazz)) != null) {
            clazz2 = object2.nativeType();
        }
        object = this.invoke(objectArray2, clazz2, bl, n);
        if (object2 != null) {
            FunctionResultContext functionResultContext = method != null ? new MethodResultContext(clazz, this, objectArray, method) : new FunctionResultContext(clazz, this, objectArray);
            object = object2.fromNative(object, functionResultContext);
        }
        if (objectArray != null) {
            for (int i = 0; i < objectArray.length; ++i) {
                Object object3 = objectArray[i];
                if (object3 == null) continue;
                if (object3 instanceof Structure) {
                    if (!(object3 instanceof Structure.ByValue)) {
                        ((Structure)object3).autoRead();
                    }
                    continue;
                }
                if (objectArray2[i] instanceof PostCallRead) {
                    ((PostCallRead)objectArray2[i]).read();
                    if (!(objectArray2[i] instanceof PointerArray)) continue;
                    PointerArray pointerArray = (PointerArray)objectArray2[i];
                    if (!Structure.ByReference[].class.isAssignableFrom(object3.getClass())) continue;
                    Class<?> clazz3 = object3.getClass().getComponentType();
                    Structure[] structureArray = (Structure[])object3;
                    for (int j = 0; j < structureArray.length; ++j) {
                        Pointer pointer = pointerArray.getPointer(Pointer.SIZE * j);
                        structureArray[j] = Structure.updateStructureByReference(clazz3, structureArray[j], pointer);
                        if (2 > 0) continue;
                        return null;
                    }
                    continue;
                }
                if (!Structure[].class.isAssignableFrom(object3.getClass())) continue;
                Structure.autoRead((Structure[])object3);
            }
        }
        return object;
    }

    static boolean isVarArgs(Method method) {
        return IS_VARARGS.isVarArgs(method);
    }

    static Boolean valueOf(boolean bl) {
        return bl ? Boolean.TRUE : Boolean.FALSE;
    }

    public float invokeFloat(Object[] objectArray) {
        return ((Float)this.invoke(Float.class, objectArray)).floatValue();
    }

    public void invokeVoid(Object[] objectArray) {
        this.invoke(Void.class, objectArray);
    }

    public long invokeLong(Object[] objectArray) {
        return (Long)this.invoke(Long.class, objectArray);
    }

    private void checkCallingConvention(int n) throws IllegalArgumentException {
        if ((n & 0x3F) != n) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unrecognized calling convention: ").append(n)));
        }
    }

    public static interface PostCallRead {
        public void read();
    }

    private static class PointerArray
    extends Memory
    implements PostCallRead {
        private final Pointer[] original;

        public PointerArray(Pointer[] pointerArray) {
            super(Pointer.SIZE * (pointerArray.length + 1));
            this.original = pointerArray;
            for (int i = 0; i < pointerArray.length; ++i) {
                this.setPointer(i * Pointer.SIZE, pointerArray[i]);
                if (-1 < 0) continue;
                throw null;
            }
            this.setPointer(Pointer.SIZE * pointerArray.length, null);
        }

        @Override
        public void read() {
            this.read(0L, this.original, 0, this.original.length);
        }
    }

    private static class NativeMappedArray
    extends Memory
    implements PostCallRead {
        private final NativeMapped[] original;

        @Override
        public void read() {
            this.getValue(0L, this.original.getClass(), this.original);
        }

        public NativeMappedArray(NativeMapped[] nativeMappedArray) {
            super(Native.getNativeSize(nativeMappedArray.getClass(), nativeMappedArray));
            this.original = nativeMappedArray;
            this.setValue(0L, this.original, this.original.getClass());
        }
    }
}

