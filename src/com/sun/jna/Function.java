/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Callback;
import com.sun.jna.CallbackReference;
import com.sun.jna.FromNativeConverter;
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
import com.sun.jna.ToNativeConverter;
import com.sun.jna.TypeMapper;
import com.sun.jna.VarArgsChecker;
import com.sun.jna.WString;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

public class Function
extends Pointer {
    public static final /* synthetic */ int ALT_CONVENTION;
    static final /* synthetic */ Integer INTEGER_TRUE;
    private /* synthetic */ NativeLibrary library;
    public static final /* synthetic */ int MAX_NARGS;
    final /* synthetic */ String encoding;
    public static final /* synthetic */ int C_CONVENTION;
    static final /* synthetic */ String OPTION_INVOKING_METHOD;
    final /* synthetic */ Map<String, ?> options;
    public static final /* synthetic */ int THROW_LAST_ERROR;
    private final /* synthetic */ String functionName;
    private static final /* synthetic */ VarArgsChecker IS_VARARGS;
    static final /* synthetic */ Integer INTEGER_FALSE;
    private static final /* synthetic */ int MASK_CC;
    final /* synthetic */ int callFlags;
    public static final /* synthetic */ int USE_VARARGS;

    public static Function getFunction(String lIIlIIIllllIlII, String lIIlIIIllllIIll) {
        return NativeLibrary.getInstance(lIIlIIIllllIlII).getFunction(lIIlIIIllllIIll);
    }

    public static Function getFunction(String lIIlIIIlllIllII, String lIIlIIIlllIlllI, int lIIlIIIlllIlIlI) {
        return NativeLibrary.getInstance(lIIlIIIlllIllII).getFunction(lIIlIIIlllIlllI, lIIlIIIlllIlIlI, null);
    }

    @Override
    public String toString() {
        Function lIIIllllIllIllI;
        if (lIIIllllIllIllI.library != null) {
            return String.valueOf(new StringBuilder().append("native function ").append(lIIIllllIllIllI.functionName).append("(").append(lIIIllllIllIllI.library.getName()).append(")@0x").append(Long.toHexString(lIIIllllIllIllI.peer)));
        }
        return String.valueOf(new StringBuilder().append("native function@0x").append(Long.toHexString(lIIIllllIllIllI.peer)));
    }

    Function(NativeLibrary lIIlIIIllIIIIll, String lIIlIIIllIIIIlI, int lIIlIIIllIIIIIl, String lIIlIIIllIIIIII) {
        Function lIIlIIIllIIIlII;
        lIIlIIIllIIIlII.checkCallingConvention(lIIlIIIllIIIIIl & 0x3F);
        if (lIIlIIIllIIIIlI == null) {
            throw new NullPointerException("Function name must not be null");
        }
        lIIlIIIllIIIlII.library = lIIlIIIllIIIIll;
        lIIlIIIllIIIlII.functionName = lIIlIIIllIIIIlI;
        lIIlIIIllIIIlII.callFlags = lIIlIIIllIIIIIl;
        lIIlIIIllIIIlII.options = lIIlIIIllIIIIll.options;
        lIIlIIIllIIIlII.encoding = lIIlIIIllIIIIII != null ? lIIlIIIllIIIIII : Native.getDefaultStringEncoding();
        try {
            lIIlIIIllIIIlII.peer = lIIlIIIllIIIIll.getSymbolAddress(lIIlIIIllIIIIlI);
        }
        catch (UnsatisfiedLinkError lIIlIIIllIIIlIl) {
            throw new UnsatisfiedLinkError(String.valueOf(new StringBuilder().append("Error looking up function '").append(lIIlIIIllIIIIlI).append("': ").append(lIIlIIIllIIIlIl.getMessage())));
        }
    }

    public int invokeInt(Object[] lIIIllllIIlIlll) {
        Function lIIIllllIIllIlI;
        return (Integer)lIIIllllIIllIlI.invoke(Integer.class, lIIIllllIIlIlll);
    }

    static int fixedArgs(Method lIIIlllIlIIlIII) {
        return IS_VARARGS.fixedArgs(lIIIlllIlIIlIII);
    }

    public static Function getFunction(Pointer lIIlIIIllIllIII, int lIIlIIIllIlIlll) {
        return Function.getFunction(lIIlIIIllIllIII, lIIlIIIllIlIlll, null);
    }

    Function(Pointer lIIlIIIlIllIIII, int lIIlIIIlIlIllll, String lIIlIIIlIlIlllI) {
        Function lIIlIIIlIllIlIl;
        lIIlIIIlIllIlIl.checkCallingConvention(lIIlIIIlIlIllll & 0x3F);
        if (lIIlIIIlIllIIII == null || lIIlIIIlIllIIII.peer == 0L) {
            throw new NullPointerException("Function address may not be null");
        }
        lIIlIIIlIllIlIl.functionName = lIIlIIIlIllIIII.toString();
        lIIlIIIlIllIlIl.callFlags = lIIlIIIlIlIllll;
        lIIlIIIlIllIlIl.peer = lIIlIIIlIllIIII.peer;
        lIIlIIIlIllIlIl.options = Collections.EMPTY_MAP;
        lIIlIIIlIllIlIl.encoding = lIIlIIIlIlIlllI != null ? lIIlIIIlIlIlllI : Native.getDefaultStringEncoding();
    }

    public Object invokeObject(Object[] lIIIllllIllIIIl) {
        Function lIIIllllIllIIlI;
        return lIIIllllIllIIlI.invoke(Object.class, lIIIllllIllIIIl);
    }

    @Override
    public boolean equals(Object lIIIlllIllllIIl) {
        Function lIIIlllIllllIlI;
        if (lIIIlllIllllIIl == lIIIlllIllllIlI) {
            return true;
        }
        if (lIIIlllIllllIIl == null) {
            return false;
        }
        if (lIIIlllIllllIIl.getClass() == lIIIlllIllllIlI.getClass()) {
            Function lIIIlllIllllIll = (Function)lIIIlllIllllIIl;
            return lIIIlllIllllIll.callFlags == lIIIlllIllllIlI.callFlags && lIIIlllIllllIll.options.equals(lIIIlllIllllIlI.options) && lIIIlllIllllIll.peer == lIIIlllIllllIlI.peer;
        }
        return false;
    }

    public void invoke(Object[] lIIIlllllIIlIlI) {
        Function lIIIlllllIIllIl;
        lIIIlllllIIllIl.invoke(Void.class, lIIIlllllIIlIlI);
    }

    private String invokeString(int lIIIllllIllllII, Object[] lIIIlllllIIIIIl, boolean lIIIlllllIIIIII) {
        Function lIIIllllIllllIl;
        Pointer lIIIllllIllllll = lIIIllllIllllIl.invokePointer(lIIIllllIllllII, lIIIlllllIIIIIl);
        String lIIIllllIlllllI = null;
        if (lIIIllllIllllll != null) {
            lIIIllllIlllllI = lIIIlllllIIIIII ? lIIIllllIllllll.getWideString(0L) : lIIIllllIllllll.getString(0L, lIIIllllIllllIl.encoding);
        }
        return lIIIllllIlllllI;
    }

    private Object convertArgument(Object[] lIIIllllllIIIII, int lIIIllllllIlIII, Method lIIIllllllIIlll, TypeMapper lIIIllllllIIllI, boolean lIIIllllllIIlIl, Class<?> lIIIlllllIllIll) {
        Function lIIIllllllIIIIl;
        Object lIIIllllllIIIll = lIIIllllllIIIII[lIIIllllllIlIII];
        if (lIIIllllllIIIll != null) {
            Class<?> lIIIlllllllIllI = lIIIllllllIIIll.getClass();
            ToNativeConverter lIIIlllllllIlIl = null;
            if (NativeMapped.class.isAssignableFrom(lIIIlllllllIllI)) {
                lIIIlllllllIlIl = NativeMappedConverter.getInstance(lIIIlllllllIllI);
            } else if (lIIIllllllIIllI != null) {
                lIIIlllllllIlIl = lIIIllllllIIllI.getToNativeConverter(lIIIlllllllIllI);
            }
            if (lIIIlllllllIlIl != null) {
                FunctionParameterContext lIIIlllllllIlll;
                if (lIIIllllllIIlll != null) {
                    MethodParameterContext lIIIllllllllIII = new MethodParameterContext(lIIIllllllIIIIl, lIIIllllllIIIII, lIIIllllllIlIII, lIIIllllllIIlll);
                } else {
                    lIIIlllllllIlll = new FunctionParameterContext(lIIIllllllIIIIl, lIIIllllllIIIII, lIIIllllllIlIII);
                }
                lIIIllllllIIIll = lIIIlllllllIlIl.toNative(lIIIllllllIIIll, lIIIlllllllIlll);
            }
        }
        if (lIIIllllllIIIll == null || lIIIllllllIIIIl.isPrimitiveArray(lIIIllllllIIIll.getClass())) {
            return lIIIllllllIIIll;
        }
        Class<?> lIIIllllllIIIlI = lIIIllllllIIIll.getClass();
        if (lIIIllllllIIIll instanceof Structure) {
            Structure lIIIlllllllIIIl = (Structure)lIIIllllllIIIll;
            lIIIlllllllIIIl.autoWrite();
            if (lIIIlllllllIIIl instanceof Structure.ByValue) {
                Class<?> lIIIlllllllIIlI = lIIIlllllllIIIl.getClass();
                if (lIIIllllllIIlll != null) {
                    Class<?>[] lIIIlllllllIIll = lIIIllllllIIlll.getParameterTypes();
                    if (IS_VARARGS.isVarArgs(lIIIllllllIIlll)) {
                        if (lIIIllllllIlIII < lIIIlllllllIIll.length - 1) {
                            lIIIlllllllIIlI = lIIIlllllllIIll[lIIIllllllIlIII];
                        } else {
                            Class<?> lIIIlllllllIlII = lIIIlllllllIIll[lIIIlllllllIIll.length - 1].getComponentType();
                            if (lIIIlllllllIlII != Object.class) {
                                lIIIlllllllIIlI = lIIIlllllllIlII;
                            }
                        }
                    } else {
                        lIIIlllllllIIlI = lIIIlllllllIIll[lIIIllllllIlIII];
                    }
                }
                if (Structure.ByValue.class.isAssignableFrom(lIIIlllllllIIlI)) {
                    return lIIIlllllllIIIl;
                }
            }
            return lIIIlllllllIIIl.getPointer();
        }
        if (lIIIllllllIIIll instanceof Callback) {
            return CallbackReference.getFunctionPointer((Callback)lIIIllllllIIIll);
        }
        if (lIIIllllllIIIll instanceof String) {
            return new NativeString((String)lIIIllllllIIIll, false).getPointer();
        }
        if (lIIIllllllIIIll instanceof WString) {
            return new NativeString(lIIIllllllIIIll.toString(), true).getPointer();
        }
        if (lIIIllllllIIIll instanceof Boolean) {
            return Boolean.TRUE.equals(lIIIllllllIIIll) ? INTEGER_TRUE : INTEGER_FALSE;
        }
        if (String[].class == lIIIllllllIIIlI) {
            return new StringArray((String[])lIIIllllllIIIll, lIIIllllllIIIIl.encoding);
        }
        if (WString[].class == lIIIllllllIIIlI) {
            return new StringArray((WString[])lIIIllllllIIIll);
        }
        if (Pointer[].class == lIIIllllllIIIlI) {
            return new PointerArray((Pointer[])lIIIllllllIIIll);
        }
        if (NativeMapped[].class.isAssignableFrom(lIIIllllllIIIlI)) {
            return new NativeMappedArray((NativeMapped[])lIIIllllllIIIll);
        }
        if (Structure[].class.isAssignableFrom(lIIIllllllIIIlI)) {
            Structure[] lIIIllllllIllIl = (Structure[])lIIIllllllIIIll;
            Class<?> lIIIllllllIllII = lIIIllllllIIIlI.getComponentType();
            boolean lIIIllllllIlIll = Structure.ByReference.class.isAssignableFrom(lIIIllllllIllII);
            if (lIIIlllllIllIll != null && !Structure.ByReference[].class.isAssignableFrom(lIIIlllllIllIll)) {
                if (lIIIllllllIlIll) {
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Function ").append(lIIIllllllIIIIl.getName()).append(" declared Structure[] at parameter ").append(lIIIllllllIlIII).append(" but array of ").append(lIIIllllllIllII).append(" was passed")));
                }
                for (int lIIIlllllllIIII = 0; lIIIlllllllIIII < lIIIllllllIllIl.length; ++lIIIlllllllIIII) {
                    if (!(lIIIllllllIllIl[lIIIlllllllIIII] instanceof Structure.ByReference)) continue;
                    throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Function ").append(lIIIllllllIIIIl.getName()).append(" declared Structure[] at parameter ").append(lIIIllllllIlIII).append(" but element ").append(lIIIlllllllIIII).append(" is of Structure.ByReference type")));
                }
            }
            if (lIIIllllllIlIll) {
                Structure.autoWrite(lIIIllllllIllIl);
                Pointer[] lIIIllllllIlllI = new Pointer[lIIIllllllIllIl.length + 1];
                for (int lIIIllllllIllll = 0; lIIIllllllIllll < lIIIllllllIllIl.length; ++lIIIllllllIllll) {
                    lIIIllllllIlllI[lIIIllllllIllll] = lIIIllllllIllIl[lIIIllllllIllll] != null ? lIIIllllllIllIl[lIIIllllllIllll].getPointer() : null;
                }
                return new PointerArray(lIIIllllllIlllI);
            }
            if (lIIIllllllIllIl.length == 0) {
                throw new IllegalArgumentException("Structure array must have non-zero length");
            }
            if (lIIIllllllIllIl[0] == null) {
                Structure.newInstance(lIIIllllllIllII).toArray(lIIIllllllIllIl);
                return lIIIllllllIllIl[0].getPointer();
            }
            Structure.autoWrite(lIIIllllllIllIl);
            return lIIIllllllIllIl[0].getPointer();
        }
        if (lIIIllllllIIIlI.isArray()) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported array argument type: ").append(lIIIllllllIIIlI.getComponentType())));
        }
        if (lIIIllllllIIlIl) {
            return lIIIllllllIIIll;
        }
        if (!Native.isSupportedNativeType(lIIIllllllIIIll.getClass())) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported argument type ").append(lIIIllllllIIIll.getClass().getName()).append(" at parameter ").append(lIIIllllllIlIII).append(" of function ").append(lIIIllllllIIIIl.getName())));
        }
        return lIIIllllllIIIll;
    }

    public Object invoke(Class<?> lIIlIIIlIIIllIl, Object[] lIIlIIIlIIlIIlI, Map<String, ?> lIIlIIIlIIlIIIl) {
        Function lIIlIIIlIIlIlII;
        Method lIIlIIIlIIlIIII = (Method)lIIlIIIlIIlIIIl.get("invoking-method");
        Class<?>[] lIIlIIIlIIIllll = lIIlIIIlIIlIIII != null ? lIIlIIIlIIlIIII.getParameterTypes() : null;
        return lIIlIIIlIIlIlII.invoke(lIIlIIIlIIlIIII, lIIlIIIlIIIllll, lIIlIIIlIIIllIl, lIIlIIIlIIlIIlI, lIIlIIIlIIlIIIl);
    }

    Object invoke(Object[] lIIlIIIIIlllIll, Class<?> lIIlIIIIIlllllI, boolean lIIlIIIIIlllIIl) {
        Function lIIlIIIIIllllII;
        return lIIlIIIIIllllII.invoke(lIIlIIIIIlllIll, lIIlIIIIIlllllI, lIIlIIIIIlllIIl, 0);
    }

    public Pointer invokePointer(Object[] lIIIllllIlIlIIl) {
        Function lIIIllllIlIllII;
        return (Pointer)lIIIllllIlIllII.invoke(Pointer.class, lIIIllllIlIlIIl);
    }

    public String getName() {
        Function lIIlIIIlIlIIlll;
        return lIIlIIIlIlIIlll.functionName;
    }

    public String invokeString(Object[] lIIIllllIlIIIll, boolean lIIIllllIIllllI) {
        Function lIIIllllIlIIlII;
        Object lIIIllllIlIIIIl = lIIIllllIlIIlII.invoke(lIIIllllIIllllI ? WString.class : String.class, lIIIllllIlIIIll);
        return lIIIllllIlIIIIl != null ? lIIIllllIlIIIIl.toString() : null;
    }

    Object invoke(Object[] lIIlIIIIIlIIIll, Class<?> lIIlIIIIIlIIIlI, boolean lIIlIIIIIlIIIIl, int lIIlIIIIIIllIIl) {
        Function lIIlIIIIIlIIlII;
        Object lIIlIIIIIIlllll = null;
        int lIIlIIIIIIllllI = lIIlIIIIIlIIlII.callFlags | (lIIlIIIIIIllIIl & 3) << 7;
        if (lIIlIIIIIlIIIlI == null || lIIlIIIIIlIIIlI == Void.TYPE || lIIlIIIIIlIIIlI == Void.class) {
            Native.invokeVoid(lIIlIIIIIlIIlII, lIIlIIIIIlIIlII.peer, lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
            lIIlIIIIIIlllll = null;
        } else if (lIIlIIIIIlIIIlI == Boolean.TYPE || lIIlIIIIIlIIIlI == Boolean.class) {
            lIIlIIIIIIlllll = Function.valueOf(Native.invokeInt(lIIlIIIIIlIIlII, lIIlIIIIIlIIlII.peer, lIIlIIIIIIllllI, lIIlIIIIIlIIIll) != 0);
        } else if (lIIlIIIIIlIIIlI == Byte.TYPE || lIIlIIIIIlIIIlI == Byte.class) {
            lIIlIIIIIIlllll = (byte)Native.invokeInt(lIIlIIIIIlIIlII, lIIlIIIIIlIIlII.peer, lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
        } else if (lIIlIIIIIlIIIlI == Short.TYPE || lIIlIIIIIlIIIlI == Short.class) {
            lIIlIIIIIIlllll = (short)Native.invokeInt(lIIlIIIIIlIIlII, lIIlIIIIIlIIlII.peer, lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
        } else if (lIIlIIIIIlIIIlI == Character.TYPE || lIIlIIIIIlIIIlI == Character.class) {
            lIIlIIIIIIlllll = Character.valueOf((char)Native.invokeInt(lIIlIIIIIlIIlII, lIIlIIIIIlIIlII.peer, lIIlIIIIIIllllI, lIIlIIIIIlIIIll));
        } else if (lIIlIIIIIlIIIlI == Integer.TYPE || lIIlIIIIIlIIIlI == Integer.class) {
            lIIlIIIIIIlllll = Native.invokeInt(lIIlIIIIIlIIlII, lIIlIIIIIlIIlII.peer, lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
        } else if (lIIlIIIIIlIIIlI == Long.TYPE || lIIlIIIIIlIIIlI == Long.class) {
            lIIlIIIIIIlllll = Native.invokeLong(lIIlIIIIIlIIlII, lIIlIIIIIlIIlII.peer, lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
        } else if (lIIlIIIIIlIIIlI == Float.TYPE || lIIlIIIIIlIIIlI == Float.class) {
            lIIlIIIIIIlllll = Float.valueOf(Native.invokeFloat(lIIlIIIIIlIIlII, lIIlIIIIIlIIlII.peer, lIIlIIIIIIllllI, lIIlIIIIIlIIIll));
        } else if (lIIlIIIIIlIIIlI == Double.TYPE || lIIlIIIIIlIIIlI == Double.class) {
            lIIlIIIIIIlllll = Native.invokeDouble(lIIlIIIIIlIIlII, lIIlIIIIIlIIlII.peer, lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
        } else if (lIIlIIIIIlIIIlI == String.class) {
            lIIlIIIIIIlllll = lIIlIIIIIlIIlII.invokeString(lIIlIIIIIIllllI, lIIlIIIIIlIIIll, false);
        } else if (lIIlIIIIIlIIIlI == WString.class) {
            String lIIlIIIIIlIllIl = lIIlIIIIIlIIlII.invokeString(lIIlIIIIIIllllI, lIIlIIIIIlIIIll, true);
            if (lIIlIIIIIlIllIl != null) {
                lIIlIIIIIIlllll = new WString(lIIlIIIIIlIllIl);
            }
        } else {
            if (Pointer.class.isAssignableFrom(lIIlIIIIIlIIIlI)) {
                return lIIlIIIIIlIIlII.invokePointer(lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
            }
            if (Structure.class.isAssignableFrom(lIIlIIIIIlIIIlI)) {
                if (Structure.ByValue.class.isAssignableFrom(lIIlIIIIIlIIIlI)) {
                    Structure lIIlIIIIIlIllII = Native.invokeStructure(lIIlIIIIIlIIlII, lIIlIIIIIlIIlII.peer, lIIlIIIIIIllllI, lIIlIIIIIlIIIll, Structure.newInstance(lIIlIIIIIlIIIlI));
                    lIIlIIIIIlIllII.autoRead();
                    lIIlIIIIIIlllll = lIIlIIIIIlIllII;
                } else {
                    lIIlIIIIIIlllll = lIIlIIIIIlIIlII.invokePointer(lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
                    if (lIIlIIIIIIlllll != null) {
                        Structure lIIlIIIIIlIlIll = Structure.newInstance(lIIlIIIIIlIIIlI, (Pointer)lIIlIIIIIIlllll);
                        lIIlIIIIIlIlIll.conditionalAutoRead();
                        lIIlIIIIIIlllll = lIIlIIIIIlIlIll;
                    }
                }
            } else if (Callback.class.isAssignableFrom(lIIlIIIIIlIIIlI)) {
                lIIlIIIIIIlllll = lIIlIIIIIlIIlII.invokePointer(lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
                if (lIIlIIIIIIlllll != null) {
                    lIIlIIIIIIlllll = CallbackReference.getCallback(lIIlIIIIIlIIIlI, (Pointer)lIIlIIIIIIlllll);
                }
            } else if (lIIlIIIIIlIIIlI == String[].class) {
                Pointer lIIlIIIIIlIlIlI = lIIlIIIIIlIIlII.invokePointer(lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
                if (lIIlIIIIIlIlIlI != null) {
                    lIIlIIIIIIlllll = lIIlIIIIIlIlIlI.getStringArray(0L, lIIlIIIIIlIIlII.encoding);
                }
            } else if (lIIlIIIIIlIIIlI == WString[].class) {
                Pointer lIIlIIIIIlIIllI = lIIlIIIIIlIIlII.invokePointer(lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
                if (lIIlIIIIIlIIllI != null) {
                    String[] lIIlIIIIIlIlIII = lIIlIIIIIlIIllI.getWideStringArray(0L);
                    WString[] lIIlIIIIIlIIlll = new WString[lIIlIIIIIlIlIII.length];
                    for (int lIIlIIIIIlIlIIl = 0; lIIlIIIIIlIlIIl < lIIlIIIIIlIlIII.length; ++lIIlIIIIIlIlIIl) {
                        lIIlIIIIIlIIlll[lIIlIIIIIlIlIIl] = new WString(lIIlIIIIIlIlIII[lIIlIIIIIlIlIIl]);
                    }
                    lIIlIIIIIIlllll = lIIlIIIIIlIIlll;
                }
            } else if (lIIlIIIIIlIIIlI == Pointer[].class) {
                Pointer lIIlIIIIIlIIlIl = lIIlIIIIIlIIlII.invokePointer(lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
                if (lIIlIIIIIlIIlIl != null) {
                    lIIlIIIIIIlllll = lIIlIIIIIlIIlIl.getPointerArray(0L);
                }
            } else if (lIIlIIIIIlIIIIl) {
                lIIlIIIIIIlllll = Native.invokeObject(lIIlIIIIIlIIlII, lIIlIIIIIlIIlII.peer, lIIlIIIIIIllllI, lIIlIIIIIlIIIll);
                if (lIIlIIIIIIlllll != null && !lIIlIIIIIlIIIlI.isAssignableFrom(lIIlIIIIIIlllll.getClass())) {
                    throw new ClassCastException(String.valueOf(new StringBuilder().append("Return type ").append(lIIlIIIIIlIIIlI).append(" does not match result ").append(lIIlIIIIIIlllll.getClass())));
                }
            } else {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unsupported return type ").append(lIIlIIIIIlIIIlI).append(" in function ").append(lIIlIIIIIlIIlII.getName())));
            }
        }
        return lIIlIIIIIIlllll;
    }

    public static Function getFunction(Pointer lIIlIIIllIlllII) {
        return Function.getFunction(lIIlIIIllIlllII, 0, null);
    }

    public int getCallingConvention() {
        Function lIIlIIIlIlIIlII;
        return lIIlIIIlIlIIlII.callFlags & 0x3F;
    }

    private boolean isPrimitiveArray(Class<?> lIIIlllllIlIIII) {
        return lIIIlllllIlIIII.isArray() && lIIIlllllIlIIII.getComponentType().isPrimitive();
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

    public static Function getFunction(String lIIlIIIlllIIlIl, String lIIlIIIlllIIlII, int lIIlIIIllIlllll, String lIIlIIIllIllllI) {
        return NativeLibrary.getInstance(lIIlIIIlllIIlIl).getFunction(lIIlIIIlllIIlII, lIIlIIIllIlllll, lIIlIIIllIllllI);
    }

    public Object invoke(Class<?> lIIlIIIlIIlllll, Object[] lIIlIIIlIIllllI) {
        Function lIIlIIIlIlIIIII;
        return lIIlIIIlIlIIIII.invoke(lIIlIIIlIIlllll, lIIlIIIlIIllllI, lIIlIIIlIlIIIII.options);
    }

    private Pointer invokePointer(int lIIlIIIIIIIllIl, Object[] lIIlIIIIIIIlIII) {
        Function lIIlIIIIIIIlllI;
        long lIIlIIIIIIIlIll = Native.invokePointer(lIIlIIIIIIIlllI, lIIlIIIIIIIlllI.peer, lIIlIIIIIIIllIl, lIIlIIIIIIIlIII);
        return lIIlIIIIIIIlIll == 0L ? null : new Pointer(lIIlIIIIIIIlIll);
    }

    public static Function getFunction(Pointer lIIlIIIllIIlllI, int lIIlIIIllIlIIII, String lIIlIIIllIIllII) {
        return new Function(lIIlIIIllIIlllI, lIIlIIIllIlIIII, lIIlIIIllIIllII);
    }

    static Object[] concatenateVarArgs(Object[] lIIIlllIlIlIlIl) {
        if (lIIIlllIlIlIlIl != null && lIIIlllIlIlIlIl.length > 0) {
            Class<?> lIIIlllIlIllIIl;
            Object lIIIlllIlIllIll = lIIIlllIlIlIlIl[lIIIlllIlIlIlIl.length - 1];
            Class<?> class_ = lIIIlllIlIllIIl = lIIIlllIlIllIll != null ? lIIIlllIlIllIll.getClass() : null;
            if (lIIIlllIlIllIIl != null && lIIIlllIlIllIIl.isArray()) {
                Object[] lIIIlllIlIlllll = (Object[])lIIIlllIlIllIll;
                for (int lIIIlllIllIIIIl = 0; lIIIlllIllIIIIl < lIIIlllIlIlllll.length; ++lIIIlllIllIIIIl) {
                    if (!(lIIIlllIlIlllll[lIIIlllIllIIIIl] instanceof Float)) continue;
                    lIIIlllIlIlllll[lIIIlllIllIIIIl] = (double)((Float)lIIIlllIlIlllll[lIIIlllIllIIIIl]).floatValue();
                }
                Object[] lIIIlllIlIlllIl = new Object[lIIIlllIlIlIlIl.length + lIIIlllIlIlllll.length];
                System.arraycopy(lIIIlllIlIlIlIl, 0, lIIIlllIlIlllIl, 0, lIIIlllIlIlIlIl.length - 1);
                System.arraycopy(lIIIlllIlIlllll, 0, lIIIlllIlIlllIl, lIIIlllIlIlIlIl.length - 1, lIIIlllIlIlllll.length);
                lIIIlllIlIlllIl[lIIIlllIlIlllIl.length - 1] = null;
                lIIIlllIlIlIlIl = lIIIlllIlIlllIl;
            }
        }
        return lIIIlllIlIlIlIl;
    }

    public double invokeDouble(Object[] lIIIllllIIIIlIl) {
        Function lIIIllllIIIlIII;
        return (Double)lIIIllllIIIlIII.invoke(Double.class, lIIIllllIIIIlIl);
    }

    @Override
    public int hashCode() {
        Function lIIIlllIlllIIll;
        return lIIIlllIlllIIll.callFlags + lIIIlllIlllIIll.options.hashCode() + super.hashCode();
    }

    Object invoke(Method lIIlIIIIllIIllI, Class<?>[] lIIlIIIIlIlIlll, Class<?> lIIlIIIIlIlIllI, Object[] lIIlIIIIlIlIlIl, Map<String, ?> lIIlIIIIlIlIlII) {
        Function lIIlIIIIlIllIIl;
        Object[] lIIlIIIIllIIIIl = new Object[]{};
        if (lIIlIIIIlIlIlIl != null) {
            if (lIIlIIIIlIlIlIl.length > 256) {
                throw new UnsupportedOperationException("Maximum argument count is 256");
            }
            lIIlIIIIllIIIIl = new Object[lIIlIIIIlIlIlIl.length];
            System.arraycopy(lIIlIIIIlIlIlIl, 0, lIIlIIIIllIIIIl, 0, lIIlIIIIllIIIIl.length);
        }
        TypeMapper lIIlIIIIllIIIII = (TypeMapper)lIIlIIIIlIlIlII.get("type-mapper");
        boolean lIIlIIIIlIlllll = Boolean.TRUE.equals(lIIlIIIIlIlIlII.get("allow-objects"));
        boolean lIIlIIIIlIllllI = lIIlIIIIllIIIIl.length > 0 && lIIlIIIIllIIllI != null ? Function.isVarArgs(lIIlIIIIllIIllI) : false;
        int lIIlIIIIlIlllIl = lIIlIIIIllIIIIl.length > 0 && lIIlIIIIllIIllI != null ? Function.fixedArgs(lIIlIIIIllIIllI) : 0;
        for (int lIIlIIIIlllIIlI = 0; lIIlIIIIlllIIlI < lIIlIIIIllIIIIl.length; ++lIIlIIIIlllIIlI) {
            Class<?> lIIlIIIIlllIIll = lIIlIIIIllIIllI != null ? (lIIlIIIIlIllllI && lIIlIIIIlllIIlI >= lIIlIIIIlIlIlll.length - 1 ? lIIlIIIIlIlIlll[lIIlIIIIlIlIlll.length - 1].getComponentType() : lIIlIIIIlIlIlll[lIIlIIIIlllIIlI]) : null;
            lIIlIIIIllIIIIl[lIIlIIIIlllIIlI] = lIIlIIIIlIllIIl.convertArgument(lIIlIIIIllIIIIl, lIIlIIIIlllIIlI, lIIlIIIIllIIllI, lIIlIIIIllIIIII, lIIlIIIIlIlllll, lIIlIIIIlllIIll);
        }
        Class<?> lIIlIIIIlIlllII = lIIlIIIIlIlIllI;
        FromNativeConverter lIIlIIIIlIllIll = null;
        if (NativeMapped.class.isAssignableFrom(lIIlIIIIlIlIllI)) {
            NativeMappedConverter lIIlIIIIlllIIIl = NativeMappedConverter.getInstance(lIIlIIIIlIlIllI);
            lIIlIIIIlIllIll = lIIlIIIIlllIIIl;
            lIIlIIIIlIlllII = lIIlIIIIlllIIIl.nativeType();
        } else if (lIIlIIIIllIIIII != null && (lIIlIIIIlIllIll = lIIlIIIIllIIIII.getFromNativeConverter(lIIlIIIIlIlIllI)) != null) {
            lIIlIIIIlIlllII = lIIlIIIIlIllIll.nativeType();
        }
        Object lIIlIIIIlIllIlI = lIIlIIIIlIllIIl.invoke(lIIlIIIIllIIIIl, lIIlIIIIlIlllII, lIIlIIIIlIlllll, lIIlIIIIlIlllIl);
        if (lIIlIIIIlIllIll != null) {
            FunctionResultContext lIIlIIIIllIllll;
            if (lIIlIIIIllIIllI != null) {
                MethodResultContext lIIlIIIIlllIIII = new MethodResultContext(lIIlIIIIlIlIllI, lIIlIIIIlIllIIl, lIIlIIIIlIlIlIl, lIIlIIIIllIIllI);
            } else {
                lIIlIIIIllIllll = new FunctionResultContext(lIIlIIIIlIlIllI, lIIlIIIIlIllIIl, lIIlIIIIlIlIlIl);
            }
            lIIlIIIIlIllIlI = lIIlIIIIlIllIll.fromNative(lIIlIIIIlIllIlI, lIIlIIIIllIllll);
        }
        if (lIIlIIIIlIlIlIl != null) {
            for (int lIIlIIIIllIlIII = 0; lIIlIIIIllIlIII < lIIlIIIIlIlIlIl.length; ++lIIlIIIIllIlIII) {
                Object lIIlIIIIllIlIIl = lIIlIIIIlIlIlIl[lIIlIIIIllIlIII];
                if (lIIlIIIIllIlIIl == null) continue;
                if (lIIlIIIIllIlIIl instanceof Structure) {
                    if (lIIlIIIIllIlIIl instanceof Structure.ByValue) continue;
                    ((Structure)lIIlIIIIllIlIIl).autoRead();
                    continue;
                }
                if (lIIlIIIIllIIIIl[lIIlIIIIllIlIII] instanceof PostCallRead) {
                    ((PostCallRead)lIIlIIIIllIIIIl[lIIlIIIIllIlIII]).read();
                    if (!(lIIlIIIIllIIIIl[lIIlIIIIllIlIII] instanceof PointerArray)) continue;
                    PointerArray lIIlIIIIllIlIlI = (PointerArray)lIIlIIIIllIIIIl[lIIlIIIIllIlIII];
                    if (!Structure.ByReference[].class.isAssignableFrom(lIIlIIIIllIlIIl.getClass())) continue;
                    Class<?> lIIlIIIIllIllII = lIIlIIIIllIlIIl.getClass().getComponentType();
                    Structure[] lIIlIIIIllIlIll = (Structure[])lIIlIIIIllIlIIl;
                    for (int lIIlIIIIllIllIl = 0; lIIlIIIIllIllIl < lIIlIIIIllIlIll.length; ++lIIlIIIIllIllIl) {
                        Pointer lIIlIIIIllIlllI = lIIlIIIIllIlIlI.getPointer(Pointer.SIZE * lIIlIIIIllIllIl);
                        lIIlIIIIllIlIll[lIIlIIIIllIllIl] = Structure.updateStructureByReference(lIIlIIIIllIllII, lIIlIIIIllIlIll[lIIlIIIIllIllIl], lIIlIIIIllIlllI);
                    }
                    continue;
                }
                if (!Structure[].class.isAssignableFrom(lIIlIIIIllIlIIl.getClass())) continue;
                Structure.autoRead((Structure[])lIIlIIIIllIlIIl);
            }
        }
        return lIIlIIIIlIllIlI;
    }

    static boolean isVarArgs(Method lIIIlllIlIIlIll) {
        return IS_VARARGS.isVarArgs(lIIIlllIlIIlIll);
    }

    static Boolean valueOf(boolean lIIIlllIlIIIIll) {
        return lIIIlllIlIIIIll ? Boolean.TRUE : Boolean.FALSE;
    }

    public float invokeFloat(Object[] lIIIllllIIIlIll) {
        Function lIIIllllIIIllII;
        return ((Float)lIIIllllIIIllII.invoke(Float.class, lIIIllllIIIlIll)).floatValue();
    }

    public void invokeVoid(Object[] lIIIlllIlllllll) {
        Function lIIIllllIIIIIII;
        lIIIllllIIIIIII.invoke(Void.class, lIIIlllIlllllll);
    }

    public long invokeLong(Object[] lIIIllllIIlIIIl) {
        Function lIIIllllIIlIIlI;
        return (Long)lIIIllllIIlIIlI.invoke(Long.class, lIIIllllIIlIIIl);
    }

    private void checkCallingConvention(int lIIlIIIlIlIlIll) throws IllegalArgumentException {
        if ((lIIlIIIlIlIlIll & 0x3F) != lIIlIIIlIlIlIll) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Unrecognized calling convention: ").append(lIIlIIIlIlIlIll)));
        }
    }

    public static interface PostCallRead {
        public void read();
    }

    private static class PointerArray
    extends Memory
    implements PostCallRead {
        private final /* synthetic */ Pointer[] original;

        public PointerArray(Pointer[] lllllllllllllllllIIIIllIIIlIIIII) {
            super(Pointer.SIZE * (lllllllllllllllllIIIIllIIIlIIIII.length + 1));
            PointerArray lllllllllllllllllIIIIllIIIlIIIIl;
            lllllllllllllllllIIIIllIIIlIIIIl.original = lllllllllllllllllIIIIllIIIlIIIII;
            for (int lllllllllllllllllIIIIllIIIlIIlII = 0; lllllllllllllllllIIIIllIIIlIIlII < lllllllllllllllllIIIIllIIIlIIIII.length; ++lllllllllllllllllIIIIllIIIlIIlII) {
                lllllllllllllllllIIIIllIIIlIIIIl.setPointer(lllllllllllllllllIIIIllIIIlIIlII * Pointer.SIZE, lllllllllllllllllIIIIllIIIlIIIII[lllllllllllllllllIIIIllIIIlIIlII]);
            }
            lllllllllllllllllIIIIllIIIlIIIIl.setPointer(Pointer.SIZE * lllllllllllllllllIIIIllIIIlIIIII.length, null);
        }

        @Override
        public void read() {
            PointerArray lllllllllllllllllIIIIllIIIIlllIl;
            lllllllllllllllllIIIIllIIIIlllIl.read(0L, lllllllllllllllllIIIIllIIIIlllIl.original, 0, lllllllllllllllllIIIIllIIIIlllIl.original.length);
        }
    }

    private static class NativeMappedArray
    extends Memory
    implements PostCallRead {
        private final /* synthetic */ NativeMapped[] original;

        @Override
        public void read() {
            NativeMappedArray lllllllllllllllllIlIIIIIlIlIIIIl;
            lllllllllllllllllIlIIIIIlIlIIIIl.getValue(0L, lllllllllllllllllIlIIIIIlIlIIIIl.original.getClass(), lllllllllllllllllIlIIIIIlIlIIIIl.original);
        }

        public NativeMappedArray(NativeMapped[] lllllllllllllllllIlIIIIIlIlIIllI) {
            super(Native.getNativeSize(lllllllllllllllllIlIIIIIlIlIIllI.getClass(), lllllllllllllllllIlIIIIIlIlIIllI));
            NativeMappedArray lllllllllllllllllIlIIIIIlIlIIlll;
            lllllllllllllllllIlIIIIIlIlIIlll.original = lllllllllllllllllIlIIIIIlIlIIllI;
            lllllllllllllllllIlIIIIIlIlIIlll.setValue(0L, lllllllllllllllllIlIIIIIlIlIIlll.original, lllllllllllllllllIlIIIIIlIlIIlll.original.getClass());
        }
    }
}

