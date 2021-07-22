/*
 * Decompiled with CFR 0.150.
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

    public int invokeInt(Object[] arrobject) {
        return (Integer)this.invoke(Integer.class, arrobject);
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

    public Object invokeObject(Object[] arrobject) {
        return this.invoke(Object.class, arrobject);
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

    public void invoke(Object[] arrobject) {
        this.invoke(Void.class, arrobject);
    }

    private String invokeString(int n, Object[] arrobject, boolean bl) {
        Pointer pointer = this.invokePointer(n, arrobject);
        String string = null;
        if (pointer != null) {
            string = bl ? pointer.getWideString(0L) : pointer.getString(0L, this.encoding);
        }
        return string;
    }

    private Object convertArgument(Object[] arrobject, int n, Method method, TypeMapper typeMapper, boolean bl, Class<?> class_) {
        Object object;
        Structure[] arrstructure;
        Class<?> class_2;
        Object object2 = arrobject[n];
        if (object2 != null) {
            class_2 = object2.getClass();
            arrstructure = null;
            if (NativeMapped.class.isAssignableFrom(class_2)) {
                arrstructure = NativeMappedConverter.getInstance(class_2);
            } else if (typeMapper != null) {
                arrstructure = typeMapper.getToNativeConverter(class_2);
            }
            if (arrstructure != null) {
                object = method != null ? new MethodParameterContext(this, arrobject, n, method) : new FunctionParameterContext(this, arrobject, n);
                object2 = arrstructure.toNative(object2, (ToNativeContext)object);
            }
        }
        if (object2 == null || this.isPrimitiveArray(object2.getClass())) {
            return object2;
        }
        class_2 = object2.getClass();
        if (object2 instanceof Structure) {
            arrstructure = (Structure)object2;
            arrstructure.autoWrite();
            if (arrstructure instanceof Structure.ByValue) {
                object = arrstructure.getClass();
                if (method != null) {
                    Class<?>[] arrclass = method.getParameterTypes();
                    if (IS_VARARGS.isVarArgs(method)) {
                        if (n < arrclass.length - 1) {
                            object = arrclass[n];
                        } else {
                            Class<?> class_3 = arrclass[arrclass.length - 1].getComponentType();
                            if (class_3 != Object.class) {
                                object = class_3;
                            }
                        }
                    } else {
                        object = arrclass[n];
                    }
                }
                if (Structure.ByValue.class.isAssignableFrom((Class<?>)object)) {
                    return arrstructure;
                }
            }
            return arrstructure.getPointer();
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
        if (String[].class == class_2) {
            return new StringArray((String[])object2, this.encoding);
        }
        if (WString[].class == class_2) {
            return new StringArray((WString[])object2);
        }
        if (Pointer[].class == class_2) {
            return new PointerArray((Pointer[])object2);
        }
        if (NativeMapped[].class.isAssignableFrom(class_2)) {
            return new NativeMappedArray((NativeMapped[])object2);
        }
        if (Structure[].class.isAssignableFrom(class_2)) {
            arrstructure = (Structure[])object2;
            object = class_2.getComponentType();
            boolean bl2 = Structure.ByReference.class.isAssignableFrom((Class<?>)object);
            if (class_ != null && !Structure.ByReference[].class.isAssignableFrom(class_)) {
                if (bl2) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Function ").append(this.getName()).append(" declared Structure[] at parameter ").append(n).append(" but array of ").append(object).append(" was passed")));
                }
                for (int i = 0; i < arrstructure.length; ++i) {
                    if (!(arrstructure[i] instanceof Structure.ByReference)) continue;
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Function ").append(this.getName()).append(" declared Structure[] at parameter ").append(n).append(" but element ").append(i).append(" is of Structure.ByReference type")));
                }
            }
            if (bl2) {
                Structure.autoWrite(arrstructure);
                Pointer[] arrpointer = new Pointer[arrstructure.length + 1];
                for (int i = 0; i < arrstructure.length; ++i) {
                    arrpointer[i] = arrstructure[i] != null ? arrstructure[i].getPointer() : null;
                }
                return new PointerArray(arrpointer);
            }
            if (arrstructure.length == 0) {
                throw new IllegalArgumentException("Structure array must have non-zero length");
            }
            if (arrstructure[0] == null) {
                Structure.newInstance(object).toArray(arrstructure);
                return arrstructure[0].getPointer();
            }
            Structure.autoWrite(arrstructure);
            return arrstructure[0].getPointer();
        }
        if (class_2.isArray()) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported array argument type: ").append(class_2.getComponentType())));
        }
        if (bl) {
            return object2;
        }
        if (!Native.isSupportedNativeType(object2.getClass())) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported argument type ").append(object2.getClass().getName()).append(" at parameter ").append(n).append(" of function ").append(this.getName())));
        }
        return object2;
    }

    public Object invoke(Class<?> class_, Object[] arrobject, Map<String, ?> map) {
        Method method = (Method)map.get("invoking-method");
        Class<?>[] arrclass = method != null ? method.getParameterTypes() : null;
        return this.invoke(method, arrclass, class_, arrobject, map);
    }

    Object invoke(Object[] arrobject, Class<?> class_, boolean bl) {
        return this.invoke(arrobject, class_, bl, 0);
    }

    public Pointer invokePointer(Object[] arrobject) {
        return (Pointer)this.invoke(Pointer.class, arrobject);
    }

    public String getName() {
        return this.functionName;
    }

    public String invokeString(Object[] arrobject, boolean bl) {
        Object object = this.invoke(bl ? WString.class : String.class, arrobject);
        return object != null ? object.toString() : null;
    }

    Object invoke(Object[] arrobject, Class<?> class_, boolean bl, int n) {
        Object object = null;
        int n2 = this.callFlags | (n & 3) << 7;
        if (class_ == null || class_ == Void.TYPE || class_ == Void.class) {
            Native.invokeVoid(this, this.peer, n2, arrobject);
            object = null;
        } else if (class_ == Boolean.TYPE || class_ == Boolean.class) {
            object = Function.valueOf(Native.invokeInt(this, this.peer, n2, arrobject) != 0);
        } else if (class_ == Byte.TYPE || class_ == Byte.class) {
            object = (byte)Native.invokeInt(this, this.peer, n2, arrobject);
        } else if (class_ == Short.TYPE || class_ == Short.class) {
            object = (short)Native.invokeInt(this, this.peer, n2, arrobject);
        } else if (class_ == Character.TYPE || class_ == Character.class) {
            object = Character.valueOf((char)Native.invokeInt(this, this.peer, n2, arrobject));
        } else if (class_ == Integer.TYPE || class_ == Integer.class) {
            object = Native.invokeInt(this, this.peer, n2, arrobject);
        } else if (class_ == Long.TYPE || class_ == Long.class) {
            object = Native.invokeLong(this, this.peer, n2, arrobject);
        } else if (class_ == Float.TYPE || class_ == Float.class) {
            object = Float.valueOf(Native.invokeFloat(this, this.peer, n2, arrobject));
        } else if (class_ == Double.TYPE || class_ == Double.class) {
            object = Native.invokeDouble(this, this.peer, n2, arrobject);
        } else if (class_ == String.class) {
            object = this.invokeString(n2, arrobject, false);
        } else if (class_ == WString.class) {
            String string = this.invokeString(n2, arrobject, true);
            if (string != null) {
                object = new WString(string);
            }
        } else {
            if (Pointer.class.isAssignableFrom(class_)) {
                return this.invokePointer(n2, arrobject);
            }
            if (Structure.class.isAssignableFrom(class_)) {
                if (Structure.ByValue.class.isAssignableFrom(class_)) {
                    Structure structure = Native.invokeStructure(this, this.peer, n2, arrobject, Structure.newInstance(class_));
                    structure.autoRead();
                    object = structure;
                } else {
                    object = this.invokePointer(n2, arrobject);
                    if (object != null) {
                        Structure structure = Structure.newInstance(class_, (Pointer)object);
                        structure.conditionalAutoRead();
                        object = structure;
                    }
                }
            } else if (Callback.class.isAssignableFrom(class_)) {
                object = this.invokePointer(n2, arrobject);
                if (object != null) {
                    object = CallbackReference.getCallback(class_, (Pointer)object);
                }
            } else if (class_ == String[].class) {
                Pointer pointer = this.invokePointer(n2, arrobject);
                if (pointer != null) {
                    object = pointer.getStringArray(0L, this.encoding);
                }
            } else if (class_ == WString[].class) {
                Pointer pointer = this.invokePointer(n2, arrobject);
                if (pointer != null) {
                    String[] arrstring = pointer.getWideStringArray(0L);
                    WString[] arrwString = new WString[arrstring.length];
                    for (int i = 0; i < arrstring.length; ++i) {
                        arrwString[i] = new WString(arrstring[i]);
                        if (-2 <= 0) continue;
                        return null;
                    }
                    object = arrwString;
                }
            } else if (class_ == Pointer[].class) {
                Pointer pointer = this.invokePointer(n2, arrobject);
                if (pointer != null) {
                    object = pointer.getPointerArray(0L);
                }
            } else if (bl) {
                object = Native.invokeObject(this, this.peer, n2, arrobject);
                if (object != null && !class_.isAssignableFrom(object.getClass())) {
                    throw new ClassCastException(String.valueOf(new StringBuilder().append("Return type ").append(class_).append(" does not match result ").append(object.getClass())));
                }
            } else {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported return type ").append(class_).append(" in function ").append(this.getName())));
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

    private boolean isPrimitiveArray(Class<?> class_) {
        return class_.isArray() && class_.getComponentType().isPrimitive();
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

    public Object invoke(Class<?> class_, Object[] arrobject) {
        return this.invoke(class_, arrobject, this.options);
    }

    private Pointer invokePointer(int n, Object[] arrobject) {
        long l = Native.invokePointer(this, this.peer, n, arrobject);
        return l == 0L ? null : new Pointer(l);
    }

    public static Function getFunction(Pointer pointer, int n, String string) {
        return new Function(pointer, n, string);
    }

    static Object[] concatenateVarArgs(Object[] arrobject) {
        if (arrobject != null && arrobject.length > 0) {
            Class<?> class_;
            Object object = arrobject[arrobject.length - 1];
            Class<?> class_2 = class_ = object != null ? object.getClass() : null;
            if (class_ != null && class_.isArray()) {
                Object[] arrobject2 = (Object[])object;
                for (int i = 0; i < arrobject2.length; ++i) {
                    if (!(arrobject2[i] instanceof Float)) continue;
                    arrobject2[i] = (double)((Float)arrobject2[i]).floatValue();
                    if (-1 < 1) continue;
                    return null;
                }
                Object[] arrobject3 = new Object[arrobject.length + arrobject2.length];
                System.arraycopy(arrobject, 0, arrobject3, 0, arrobject.length - 1);
                System.arraycopy(arrobject2, 0, arrobject3, arrobject.length - 1, arrobject2.length);
                arrobject3[arrobject3.length - 1] = null;
                arrobject = arrobject3;
            }
        }
        return arrobject;
    }

    public double invokeDouble(Object[] arrobject) {
        return (Double)this.invoke(Double.class, arrobject);
    }

    @Override
    public int hashCode() {
        return this.callFlags + this.options.hashCode() + super.hashCode();
    }

    Object invoke(Method method, Class<?>[] arrclass, Class<?> class_, Object[] arrobject, Map<String, ?> map) {
        Object object;
        Object object2;
        Object[] arrobject2 = new Object[]{};
        if (arrobject != null) {
            if (arrobject.length > 256) {
                throw new UnsupportedOperationException("Maximum argument count is 256");
            }
            arrobject2 = new Object[arrobject.length];
            System.arraycopy(arrobject, 0, arrobject2, 0, arrobject2.length);
        }
        TypeMapper typeMapper = (TypeMapper)map.get("type-mapper");
        boolean bl = Boolean.TRUE.equals(map.get("allow-objects"));
        boolean bl2 = arrobject2.length > 0 && method != null ? Function.isVarArgs(method) : false;
        int n = arrobject2.length > 0 && method != null ? Function.fixedArgs(method) : 0;
        for (int i = 0; i < arrobject2.length; ++i) {
            object2 = method != null ? (bl2 && i >= arrclass.length - 1 ? arrclass[arrclass.length - 1].getComponentType() : arrclass[i]) : null;
            arrobject2[i] = this.convertArgument(arrobject2, i, method, typeMapper, bl, (Class<?>)object2);
            if (1 != 2) continue;
            return null;
        }
        Class<?> class_2 = class_;
        object2 = null;
        if (NativeMapped.class.isAssignableFrom(class_)) {
            object2 = object = NativeMappedConverter.getInstance(class_);
            class_2 = ((NativeMappedConverter)object).nativeType();
        } else if (typeMapper != null && (object2 = typeMapper.getFromNativeConverter(class_)) != null) {
            class_2 = object2.nativeType();
        }
        object = this.invoke(arrobject2, class_2, bl, n);
        if (object2 != null) {
            FunctionResultContext functionResultContext = method != null ? new MethodResultContext(class_, this, arrobject, method) : new FunctionResultContext(class_, this, arrobject);
            object = object2.fromNative(object, functionResultContext);
        }
        if (arrobject != null) {
            for (int i = 0; i < arrobject.length; ++i) {
                Object object3 = arrobject[i];
                if (object3 == null) continue;
                if (object3 instanceof Structure) {
                    if (!(object3 instanceof Structure.ByValue)) {
                        ((Structure)object3).autoRead();
                    }
                    continue;
                }
                if (arrobject2[i] instanceof PostCallRead) {
                    ((PostCallRead)arrobject2[i]).read();
                    if (!(arrobject2[i] instanceof PointerArray)) continue;
                    PointerArray pointerArray = (PointerArray)arrobject2[i];
                    if (!Structure.ByReference[].class.isAssignableFrom(object3.getClass())) continue;
                    Class<?> class_3 = object3.getClass().getComponentType();
                    Structure[] arrstructure = (Structure[])object3;
                    for (int j = 0; j < arrstructure.length; ++j) {
                        Pointer pointer = pointerArray.getPointer(Pointer.SIZE * j);
                        arrstructure[j] = Structure.updateStructureByReference(class_3, arrstructure[j], pointer);
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

    public float invokeFloat(Object[] arrobject) {
        return ((Float)this.invoke(Float.class, arrobject)).floatValue();
    }

    public void invokeVoid(Object[] arrobject) {
        this.invoke(Void.class, arrobject);
    }

    public long invokeLong(Object[] arrobject) {
        return (Long)this.invoke(Long.class, arrobject);
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

        public PointerArray(Pointer[] arrpointer) {
            super(Pointer.SIZE * (arrpointer.length + 1));
            this.original = arrpointer;
            for (int i = 0; i < arrpointer.length; ++i) {
                this.setPointer(i * Pointer.SIZE, arrpointer[i]);
                if (-1 < 0) continue;
                throw null;
            }
            this.setPointer(Pointer.SIZE * arrpointer.length, null);
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

        public NativeMappedArray(NativeMapped[] arrnativeMapped) {
            super(Native.getNativeSize(arrnativeMapped.getClass(), arrnativeMapped));
            this.original = arrnativeMapped;
            this.setValue(0L, this.original, this.original.getClass());
        }
    }
}

