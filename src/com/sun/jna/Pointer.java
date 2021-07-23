/*
 * Decompiled with CFR 0.151.
 */
package com.sun.jna;

import com.sun.jna.Callback;
import com.sun.jna.CallbackReference;
import com.sun.jna.FromNativeContext;
import com.sun.jna.Function;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.NativeMapped;
import com.sun.jna.NativeMappedConverter;
import com.sun.jna.Platform;
import com.sun.jna.Structure;
import com.sun.jna.ToNativeContext;
import com.sun.jna.WString;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class Pointer {
    public static final int SIZE = Native.POINTER_SIZE;
    public static final Pointer NULL;
    protected long peer;

    public void write(long l, Pointer[] pointerArray, int n, int n2) {
        for (int i = 0; i < n2; ++i) {
            this.setPointer(l + (long)(i * SIZE), pointerArray[n + i]);
            if (!false) continue;
            return;
        }
    }

    public void setNativeLong(long l, NativeLong nativeLong) {
        if (NativeLong.SIZE == 8) {
            this.setLong(l, nativeLong.longValue());
        } else {
            this.setInt(l, nativeLong.intValue());
        }
    }

    public String[] getWideStringArray(long l) {
        return this.getWideStringArray(l, -1);
    }

    public Pointer[] getPointerArray(long l) {
        ArrayList<Pointer> arrayList = new ArrayList<Pointer>();
        int n = 0;
        Pointer pointer = this.getPointer(l);
        while (pointer != null) {
            arrayList.add(pointer);
            pointer = this.getPointer(l + (long)(n += SIZE));
        }
        return arrayList.toArray(new Pointer[arrayList.size()]);
    }

    public void read(long l, double[] dArray, int n, int n2) {
        Native.read(this, this.peer, l, dArray, n, n2);
    }

    public String getWideString(long l) {
        return Native.getWideString(this, this.peer, l);
    }

    public void read(long l, Pointer[] pointerArray, int n, int n2) {
        for (int i = 0; i < n2; ++i) {
            Pointer pointer = this.getPointer(l + (long)(i * SIZE));
            Pointer pointer2 = pointerArray[i + n];
            if (pointer2 != null && pointer != null && pointer.peer == pointer2.peer) continue;
            pointerArray[i + n] = pointer;
            if (3 <= 3) continue;
            return;
        }
    }

    public void setInt(long l, int n) {
        Native.setInt(this, this.peer, l, n);
    }

    @Deprecated
    public String getString(long l, boolean bl) {
        return bl ? this.getWideString(l) : this.getString(l);
    }

    public void write(long l, double[] dArray, int n, int n2) {
        Native.write(this, this.peer, l, dArray, n, n2);
    }

    public void setFloat(long l, float f) {
        Native.setFloat(this, this.peer, l, f);
    }

    public float getFloat(long l) {
        return Native.getFloat(this, this.peer, l);
    }

    public long indexOf(long l, byte by) {
        return Native.indexOf(this, this.peer, l, by);
    }

    public void write(long l, char[] cArray, int n, int n2) {
        Native.write(this, this.peer, l, cArray, n, n2);
    }

    public String getString(long l, String string) {
        return Native.getString(this, l, string);
    }

    public void setChar(long l, char c) {
        Native.setChar(this, this.peer, l, c);
    }

    Pointer() {
    }

    public void write(long l, long[] lArray, int n, int n2) {
        Native.write(this, this.peer, l, lArray, n, n2);
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        return object instanceof Pointer && ((Pointer)object).peer == this.peer;
    }

    public Pointer(long l) {
        this.peer = l;
    }

    static {
        if (SIZE == 0) {
            throw new Error("Native library not initialized");
        }
        NULL = null;
    }

    public void setString(long l, String string, String string2) {
        byte[] byArray = Native.getBytes(string, string2);
        this.write(l, byArray, 0, byArray.length);
        this.setByte(l + (long)byArray.length, (byte)0);
    }

    public void setString(long l, WString wString) {
        this.setWideString(l, wString == null ? null : wString.toString());
    }

    public void write(long l, byte[] byArray, int n, int n2) {
        Native.write(this, this.peer, l, byArray, n, n2);
    }

    public String dump(long l, int n) {
        int n2 = 4;
        String string = "memory dump";
        StringWriter stringWriter = new StringWriter("memory dump".length() + 2 + n * 2 + n / 4 * 4);
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println("memory dump");
        for (int i = 0; i < n; ++i) {
            byte by = this.getByte(l + (long)i);
            if (i % 4 == 0) {
                printWriter.print("[");
            }
            if (by >= 0 && by < 16) {
                printWriter.print("0");
            }
            printWriter.print(Integer.toHexString(by & 0xFF));
            if (i % 4 != 3 || i >= n - 1) continue;
            printWriter.println("]");
            if (2 == 2) continue;
            return null;
        }
        if (stringWriter.getBuffer().charAt(stringWriter.getBuffer().length() - 2) != ']') {
            printWriter.println("]");
        }
        return stringWriter.toString();
    }

    public static long nativeValue(Pointer pointer) {
        return pointer == null ? 0L : pointer.peer;
    }

    public Pointer getPointer(long l) {
        return Native.getPointer(this.peer + l);
    }

    public void clear(long l) {
        this.setMemory(0L, l, (byte)0);
    }

    public String toString() {
        return String.valueOf(new StringBuilder().append("native@0x").append(Long.toHexString(this.peer)));
    }

    public String[] getStringArray(long l, int n, String string) {
        ArrayList<String> arrayList = new ArrayList<String>();
        int n2 = 0;
        if (n != -1) {
            Pointer pointer = this.getPointer(l + (long)n2);
            int n3 = 0;
            while (n3++ < n) {
                String string2 = pointer == null ? null : ("--WIDE-STRING--".equals(string) ? pointer.getWideString(0L) : pointer.getString(0L, string));
                arrayList.add(string2);
                if (n3 >= n) continue;
                pointer = this.getPointer(l + (long)(n2 += SIZE));
            }
        } else {
            Pointer pointer;
            while ((pointer = this.getPointer(l + (long)n2)) != null) {
                String string3 = pointer == null ? null : ("--WIDE-STRING--".equals(string) ? pointer.getWideString(0L) : pointer.getString(0L, string));
                arrayList.add(string3);
                n2 += SIZE;
            }
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }

    public long getLong(long l) {
        return Native.getLong(this, this.peer, l);
    }

    public Pointer share(long l, long l2) {
        if (l == 0L) {
            return this;
        }
        return new Pointer(this.peer + l);
    }

    Object getValue(long l, Class<?> clazz, Object object) {
        Object object2 = null;
        if (Structure.class.isAssignableFrom(clazz)) {
            Structure structure = (Structure)object;
            if (Structure.ByReference.class.isAssignableFrom(clazz)) {
                structure = Structure.updateStructureByReference(clazz, structure, this.getPointer(l));
            } else {
                structure.useMemory(this, (int)l, true);
                structure.read();
            }
            object2 = structure;
        } else if (clazz == Boolean.TYPE || clazz == Boolean.class) {
            object2 = Function.valueOf(this.getInt(l) != 0);
        } else if (clazz == Byte.TYPE || clazz == Byte.class) {
            object2 = this.getByte(l);
        } else if (clazz == Short.TYPE || clazz == Short.class) {
            object2 = this.getShort(l);
        } else if (clazz == Character.TYPE || clazz == Character.class) {
            object2 = Character.valueOf(this.getChar(l));
        } else if (clazz == Integer.TYPE || clazz == Integer.class) {
            object2 = this.getInt(l);
        } else if (clazz == Long.TYPE || clazz == Long.class) {
            object2 = this.getLong(l);
        } else if (clazz == Float.TYPE || clazz == Float.class) {
            object2 = Float.valueOf(this.getFloat(l));
        } else if (clazz == Double.TYPE || clazz == Double.class) {
            object2 = this.getDouble(l);
        } else if (Pointer.class.isAssignableFrom(clazz)) {
            Pointer pointer = this.getPointer(l);
            if (pointer != null) {
                Pointer pointer2;
                Pointer pointer3 = pointer2 = object instanceof Pointer ? (Pointer)object : null;
                object2 = pointer2 == null || pointer.peer != pointer2.peer ? pointer : pointer2;
            }
        } else if (clazz == String.class) {
            Pointer pointer = this.getPointer(l);
            object2 = pointer != null ? pointer.getString(0L) : null;
        } else if (clazz == WString.class) {
            Pointer pointer = this.getPointer(l);
            object2 = pointer != null ? new WString(pointer.getWideString(0L)) : null;
        } else if (Callback.class.isAssignableFrom(clazz)) {
            Pointer pointer = this.getPointer(l);
            if (pointer == null) {
                object2 = null;
            } else {
                Callback callback = (Callback)object;
                Pointer pointer4 = CallbackReference.getFunctionPointer(callback);
                if (!pointer.equals(pointer4)) {
                    callback = CallbackReference.getCallback(clazz, pointer);
                }
                object2 = callback;
            }
        } else if (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(clazz)) {
            Pointer pointer = this.getPointer(l);
            if (pointer == null) {
                object2 = null;
            } else {
                Pointer pointer5;
                Pointer pointer6 = pointer5 = object == null ? null : Native.getDirectBufferPointer((Buffer)object);
                if (pointer5 == null || !pointer5.equals(pointer)) {
                    throw new IllegalStateException("Can't autogenerate a direct buffer on memory read");
                }
                object2 = object;
            }
        } else if (NativeMapped.class.isAssignableFrom(clazz)) {
            NativeMapped nativeMapped = (NativeMapped)object;
            if (nativeMapped != null) {
                Object object3 = this.getValue(l, nativeMapped.nativeType(), null);
                object2 = nativeMapped.fromNative(object3, new FromNativeContext(clazz));
                if (nativeMapped.equals(object2)) {
                    object2 = nativeMapped;
                }
            } else {
                NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(clazz);
                Object object4 = this.getValue(l, nativeMappedConverter.nativeType(), null);
                object2 = nativeMappedConverter.fromNative(object4, new FromNativeContext(clazz));
            }
        } else if (clazz.isArray()) {
            object2 = object;
            if (object2 == null) {
                throw new IllegalStateException("Need an initialized array");
            }
            this.readArray(l, object2, clazz.getComponentType());
        } else {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Reading \"").append(clazz).append("\" from memory is not supported")));
        }
        return object2;
    }

    public int getInt(long l) {
        return Native.getInt(this, this.peer, l);
    }

    public char getChar(long l) {
        return Native.getChar(this, this.peer, l);
    }

    public void write(long l, short[] sArray, int n, int n2) {
        Native.write(this, this.peer, l, sArray, n, n2);
    }

    void setValue(long l, Object object, Class<?> clazz) {
        if (clazz == Boolean.TYPE || clazz == Boolean.class) {
            this.setInt(l, Boolean.TRUE.equals(object) ? -1 : 0);
        } else if (clazz == Byte.TYPE || clazz == Byte.class) {
            this.setByte(l, object == null ? (byte)0 : (Byte)object);
        } else if (clazz == Short.TYPE || clazz == Short.class) {
            this.setShort(l, object == null ? (short)0 : (Short)object);
        } else if (clazz == Character.TYPE || clazz == Character.class) {
            this.setChar(l, object == null ? (char)'\u0000' : ((Character)object).charValue());
        } else if (clazz == Integer.TYPE || clazz == Integer.class) {
            this.setInt(l, object == null ? 0 : (Integer)object);
        } else if (clazz == Long.TYPE || clazz == Long.class) {
            this.setLong(l, object == null ? 0L : (Long)object);
        } else if (clazz == Float.TYPE || clazz == Float.class) {
            this.setFloat(l, object == null ? 0.0f : ((Float)object).floatValue());
        } else if (clazz == Double.TYPE || clazz == Double.class) {
            this.setDouble(l, object == null ? 0.0 : (Double)object);
        } else if (clazz == Pointer.class) {
            this.setPointer(l, (Pointer)object);
        } else if (clazz == String.class) {
            this.setPointer(l, (Pointer)object);
        } else if (clazz == WString.class) {
            this.setPointer(l, (Pointer)object);
        } else if (Structure.class.isAssignableFrom(clazz)) {
            Structure structure = (Structure)object;
            if (Structure.ByReference.class.isAssignableFrom(clazz)) {
                this.setPointer(l, structure == null ? null : structure.getPointer());
                if (structure != null) {
                    structure.autoWrite();
                }
            } else {
                structure.useMemory(this, (int)l, true);
                structure.write();
            }
        } else if (Callback.class.isAssignableFrom(clazz)) {
            this.setPointer(l, CallbackReference.getFunctionPointer((Callback)object));
        } else if (Platform.HAS_BUFFERS && Buffer.class.isAssignableFrom(clazz)) {
            Pointer pointer = object == null ? null : Native.getDirectBufferPointer((Buffer)object);
            this.setPointer(l, pointer);
        } else if (NativeMapped.class.isAssignableFrom(clazz)) {
            NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(clazz);
            Class<?> clazz2 = nativeMappedConverter.nativeType();
            this.setValue(l, nativeMappedConverter.toNative(object, new ToNativeContext()), clazz2);
        } else if (clazz.isArray()) {
            this.writeArray(l, object, clazz.getComponentType());
        } else {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Writing ").append(clazz).append(" to memory is not supported")));
        }
    }

    public void setDouble(long l, double d) {
        Native.setDouble(this, this.peer, l, d);
    }

    public NativeLong getNativeLong(long l) {
        return new NativeLong(NativeLong.SIZE == 8 ? this.getLong(l) : (long)this.getInt(l));
    }

    public Pointer[] getPointerArray(long l, int n) {
        Pointer[] pointerArray = new Pointer[n];
        this.read(l, pointerArray, 0, n);
        return pointerArray;
    }

    public Pointer share(long l) {
        return this.share(l, 0L);
    }

    public String[] getWideStringArray(long l, int n) {
        return this.getStringArray(l, n, "--WIDE-STRING--");
    }

    public void setPointer(long l, Pointer pointer) {
        Native.setPointer(this, this.peer, l, pointer != null ? pointer.peer : 0L);
    }

    public ByteBuffer getByteBuffer(long l, long l2) {
        return Native.getDirectByteBuffer(this, this.peer, l, l2).order(ByteOrder.nativeOrder());
    }

    @Deprecated
    public String[] getStringArray(long l, boolean bl) {
        return this.getStringArray(l, -1, bl);
    }

    public void read(long l, char[] cArray, int n, int n2) {
        Native.read(this, this.peer, l, cArray, n, n2);
    }

    @Deprecated
    public void setString(long l, String string, boolean bl) {
        if (bl) {
            this.setWideString(l, string);
        } else {
            this.setString(l, string);
        }
    }

    public void setString(long l, String string) {
        this.setString(l, string, Native.getDefaultStringEncoding());
    }

    public double[] getDoubleArray(long l, int n) {
        double[] dArray = new double[n];
        this.read(l, dArray, 0, n);
        return dArray;
    }

    public void read(long l, short[] sArray, int n, int n2) {
        Native.read(this, this.peer, l, sArray, n, n2);
    }

    public void write(long l, int[] nArray, int n, int n2) {
        Native.write(this, this.peer, l, nArray, n, n2);
    }

    public String[] getStringArray(long l, String string) {
        return this.getStringArray(l, -1, string);
    }

    public String[] getStringArray(long l, int n) {
        return this.getStringArray(l, n, Native.getDefaultStringEncoding());
    }

    public void setMemory(long l, long l2, byte by) {
        Native.setMemory(this, this.peer, l, l2, by);
    }

    public int[] getIntArray(long l, int n) {
        int[] nArray = new int[n];
        this.read(l, nArray, 0, n);
        return nArray;
    }

    public String getString(long l) {
        return this.getString(l, Native.getDefaultStringEncoding());
    }

    public void setWideString(long l, String string) {
        Native.setWideString(this, this.peer, l, string);
    }

    public static void nativeValue(Pointer pointer, long l) {
        pointer.peer = l;
    }

    public void read(long l, long[] lArray, int n, int n2) {
        Native.read(this, this.peer, l, lArray, n, n2);
    }

    public float[] getFloatArray(long l, int n) {
        float[] fArray = new float[n];
        this.read(l, fArray, 0, n);
        return fArray;
    }

    public byte[] getByteArray(long l, int n) {
        byte[] byArray = new byte[n];
        this.read(l, byArray, 0, n);
        return byArray;
    }

    public long[] getLongArray(long l, int n) {
        long[] lArray = new long[n];
        this.read(l, lArray, 0, n);
        return lArray;
    }

    private void writeArray(long l, Object object, Class<?> clazz) {
        if (clazz == Byte.TYPE) {
            byte[] byArray = (byte[])object;
            this.write(l, byArray, 0, byArray.length);
        } else if (clazz == Short.TYPE) {
            short[] sArray = (short[])object;
            this.write(l, sArray, 0, sArray.length);
        } else if (clazz == Character.TYPE) {
            char[] cArray = (char[])object;
            this.write(l, cArray, 0, cArray.length);
        } else if (clazz == Integer.TYPE) {
            int[] nArray = (int[])object;
            this.write(l, nArray, 0, nArray.length);
        } else if (clazz == Long.TYPE) {
            long[] lArray = (long[])object;
            this.write(l, lArray, 0, lArray.length);
        } else if (clazz == Float.TYPE) {
            float[] fArray = (float[])object;
            this.write(l, fArray, 0, fArray.length);
        } else if (clazz == Double.TYPE) {
            double[] dArray = (double[])object;
            this.write(l, dArray, 0, dArray.length);
        } else if (Pointer.class.isAssignableFrom(clazz)) {
            Pointer[] pointerArray = (Pointer[])object;
            this.write(l, pointerArray, 0, pointerArray.length);
        } else if (Structure.class.isAssignableFrom(clazz)) {
            Structure[] structureArray = (Structure[])object;
            if (Structure.ByReference.class.isAssignableFrom(clazz)) {
                Pointer[] pointerArray = new Pointer[structureArray.length];
                for (int i = 0; i < structureArray.length; ++i) {
                    if (structureArray[i] == null) {
                        pointerArray[i] = null;
                        continue;
                    }
                    pointerArray[i] = structureArray[i].getPointer();
                    structureArray[i].write();
                    if (0 != 3) continue;
                    return;
                }
                this.write(l, pointerArray, 0, pointerArray.length);
            } else {
                Structure structure = structureArray[0];
                if (structure == null) {
                    structureArray[0] = structure = Structure.newInstance(clazz, this.share(l));
                } else {
                    structure.useMemory(this, (int)l, true);
                }
                structure.write();
                Structure[] structureArray2 = structure.toArray(structureArray.length);
                for (int i = 1; i < structureArray.length; ++i) {
                    if (structureArray[i] == null) {
                        structureArray[i] = structureArray2[i];
                    } else {
                        structureArray[i].useMemory(this, (int)(l + (long)(i * structureArray[i].size())), true);
                    }
                    structureArray[i].write();
                    if (null == null) continue;
                    return;
                }
            }
        } else if (NativeMapped.class.isAssignableFrom(clazz)) {
            NativeMapped[] nativeMappedArray = (NativeMapped[])object;
            NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(clazz);
            Class<?> clazz2 = nativeMappedConverter.nativeType();
            int n = Native.getNativeSize(object.getClass(), object) / nativeMappedArray.length;
            for (int i = 0; i < nativeMappedArray.length; ++i) {
                Object object2 = nativeMappedConverter.toNative(nativeMappedArray[i], new ToNativeContext());
                this.setValue(l + (long)(i * n), object2, clazz2);
                if (1 >= 0) continue;
                return;
            }
        } else {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Writing array of ").append(clazz).append(" to memory not supported")));
        }
    }

    public void read(long l, int[] nArray, int n, int n2) {
        Native.read(this, this.peer, l, nArray, n, n2);
    }

    public double getDouble(long l) {
        return Native.getDouble(this, this.peer, l);
    }

    public void read(long l, float[] fArray, int n, int n2) {
        Native.read(this, this.peer, l, fArray, n, n2);
    }

    @Deprecated
    public String[] getStringArray(long l, int n, boolean bl) {
        return this.getStringArray(l, n, bl ? "--WIDE-STRING--" : Native.getDefaultStringEncoding());
    }

    public String[] getStringArray(long l) {
        return this.getStringArray(l, -1, Native.getDefaultStringEncoding());
    }

    public int hashCode() {
        return (int)((this.peer >>> 32) + (this.peer & 0xFFFFFFFFFFFFFFFFL));
    }

    public static final Pointer createConstant(long l) {
        return new Opaque(l, null);
    }

    private void readArray(long l, Object object, Class<?> clazz) {
        int n = 0;
        n = Array.getLength(object);
        Object object2 = object;
        if (clazz == Byte.TYPE) {
            this.read(l, (byte[])object2, 0, n);
        } else if (clazz == Short.TYPE) {
            this.read(l, (short[])object2, 0, n);
        } else if (clazz == Character.TYPE) {
            this.read(l, (char[])object2, 0, n);
        } else if (clazz == Integer.TYPE) {
            this.read(l, (int[])object2, 0, n);
        } else if (clazz == Long.TYPE) {
            this.read(l, (long[])object2, 0, n);
        } else if (clazz == Float.TYPE) {
            this.read(l, (float[])object2, 0, n);
        } else if (clazz == Double.TYPE) {
            this.read(l, (double[])object2, 0, n);
        } else if (Pointer.class.isAssignableFrom(clazz)) {
            this.read(l, (Pointer[])object2, 0, n);
        } else if (Structure.class.isAssignableFrom(clazz)) {
            Structure[] structureArray = (Structure[])object2;
            if (Structure.ByReference.class.isAssignableFrom(clazz)) {
                Pointer[] pointerArray = this.getPointerArray(l, structureArray.length);
                for (int i = 0; i < structureArray.length; ++i) {
                    structureArray[i] = Structure.updateStructureByReference(clazz, structureArray[i], pointerArray[i]);
                    if (!false) continue;
                    return;
                }
            } else {
                Structure structure = structureArray[0];
                if (structure == null) {
                    structure = Structure.newInstance(clazz, this.share(l));
                    structure.conditionalAutoRead();
                    structureArray[0] = structure;
                } else {
                    structure.useMemory(this, (int)l, true);
                    structure.read();
                }
                Structure[] structureArray2 = structure.toArray(structureArray.length);
                for (int i = 1; i < structureArray.length; ++i) {
                    if (structureArray[i] == null) {
                        structureArray[i] = structureArray2[i];
                        continue;
                    }
                    structureArray[i].useMemory(this, (int)(l + (long)(i * structureArray[i].size())), true);
                    structureArray[i].read();
                }
            }
        } else if (NativeMapped.class.isAssignableFrom(clazz)) {
            NativeMapped[] nativeMappedArray = (NativeMapped[])object2;
            NativeMappedConverter nativeMappedConverter = NativeMappedConverter.getInstance(clazz);
            int n2 = Native.getNativeSize(object2.getClass(), object2) / nativeMappedArray.length;
            for (int i = 0; i < nativeMappedArray.length; ++i) {
                Object object3 = this.getValue(l + (long)(n2 * i), nativeMappedConverter.nativeType(), nativeMappedArray[i]);
                nativeMappedArray[i] = (NativeMapped)nativeMappedConverter.fromNative(object3, new FromNativeContext(clazz));
                if (!false) continue;
                return;
            }
        } else {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Reading array of ").append(clazz).append(" from memory not supported")));
        }
    }

    public void write(long l, float[] fArray, int n, int n2) {
        Native.write(this, this.peer, l, fArray, n, n2);
    }

    public void setShort(long l, short s) {
        Native.setShort(this, this.peer, l, s);
    }

    public char[] getCharArray(long l, int n) {
        char[] cArray = new char[n];
        this.read(l, cArray, 0, n);
        return cArray;
    }

    public short getShort(long l) {
        return Native.getShort(this, this.peer, l);
    }

    public void setByte(long l, byte by) {
        Native.setByte(this, this.peer, l, by);
    }

    public void setLong(long l, long l2) {
        Native.setLong(this, this.peer, l, l2);
    }

    public void read(long l, byte[] byArray, int n, int n2) {
        Native.read(this, this.peer, l, byArray, n, n2);
    }

    public static final Pointer createConstant(int n) {
        return new Opaque((long)n & 0xFFFFFFFFFFFFFFFFL, null);
    }

    public byte getByte(long l) {
        return Native.getByte(this, this.peer, l);
    }

    public short[] getShortArray(long l, int n) {
        short[] sArray = new short[n];
        this.read(l, sArray, 0, n);
        return sArray;
    }

    private static class Opaque
    extends Pointer {
        private final String MSG = String.valueOf(new StringBuilder().append("This pointer is opaque: ").append(this));

        @Override
        public void read(long l, byte[] byArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void write(long l, long[] lArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public String dump(long l, int n) {
            throw new UnsupportedOperationException(this.MSG);
        }

        private Opaque(long l) {
            super(l);
        }

        @Override
        public Pointer share(long l, long l2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void setPointer(long l, Pointer pointer) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public short getShort(long l) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public float getFloat(long l) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void read(long l, double[] dArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void read(long l, int[] nArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public long indexOf(long l, byte by) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public byte getByte(long l) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void write(long l, int[] nArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public int getInt(long l) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void clear(long l) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void read(long l, float[] fArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void setMemory(long l, long l2, byte by) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void setByte(long l, byte by) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void read(long l, short[] sArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void setLong(long l, long l2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public char getChar(long l) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void write(long l, short[] sArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void write(long l, byte[] byArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void setFloat(long l, float f) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void write(long l, float[] fArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void setString(long l, String string, String string2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void read(long l, long[] lArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public Pointer getPointer(long l) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void read(long l, Pointer[] pointerArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void write(long l, char[] cArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public ByteBuffer getByteBuffer(long l, long l2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void setDouble(long l, double d) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void write(long l, Pointer[] pointerArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void setWideString(long l, String string) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void setInt(long l, int n) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void read(long l, char[] cArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public String getWideString(long l) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void setChar(long l, char c) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void setShort(long l, short s) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public long getLong(long l) {
            throw new UnsupportedOperationException(this.MSG);
        }

        Opaque(long l, 1 var3_2) {
            this(l);
        }

        @Override
        public String getString(long l, String string) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public void write(long l, double[] dArray, int n, int n2) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public double getDouble(long l) {
            throw new UnsupportedOperationException(this.MSG);
        }

        @Override
        public String toString() {
            return String.valueOf(new StringBuilder().append("const@0x").append(Long.toHexString(this.peer)));
        }
    }
}

