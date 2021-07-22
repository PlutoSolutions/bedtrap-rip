/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.WeakMemoryHolder;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.WeakHashMap;

public class Memory
extends Pointer {
    protected long size;
    private static final WeakMemoryHolder buffers;
    private static final Map<Memory, Reference<Memory>> allocatedMemory;

    @Override
    public void setLong(long l, long l2) {
        this.boundsCheck(l, 8L);
        super.setLong(l, l2);
    }

    @Override
    public void setWideString(long l, String string) {
        this.boundsCheck(l, ((long)string.length() + 1L) * (long)Native.WCHAR_SIZE);
        super.setWideString(l, string);
    }

    @Override
    public void setFloat(long l, float f) {
        this.boundsCheck(l, 4L);
        super.setFloat(l, f);
    }

    public void clear() {
        this.clear(this.size);
    }

    @Override
    public void write(long l, char[] arrc, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 2L);
        super.write(l, arrc, n, n2);
    }

    protected static long malloc(long l) {
        return Native.malloc(l);
    }

    @Override
    public void write(long l, long[] arrl, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 8L);
        super.write(l, arrl, n, n2);
    }

    @Override
    public void write(long l, float[] arrf, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 4L);
        super.write(l, arrf, n, n2);
    }

    @Override
    public long getLong(long l) {
        this.boundsCheck(l, 8L);
        return super.getLong(l);
    }

    @Override
    public void read(long l, double[] arrd, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 8L);
        super.read(l, arrd, n, n2);
    }

    @Override
    public void read(long l, float[] arrf, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 4L);
        super.read(l, arrf, n, n2);
    }

    protected Memory() {
    }

    @Override
    public Pointer share(long l) {
        return this.share(l, this.size() - l);
    }

    @Override
    public void setChar(long l, char c) {
        this.boundsCheck(l, Native.WCHAR_SIZE);
        super.setChar(l, c);
    }

    public String dump() {
        return this.dump(0L, (int)this.size());
    }

    @Override
    public void setDouble(long l, double d) {
        this.boundsCheck(l, 8L);
        super.setDouble(l, d);
    }

    @Override
    public Pointer share(long l, long l2) {
        this.boundsCheck(l, l2);
        return new SharedMemory(this, l, l2);
    }

    protected void boundsCheck(long l, long l2) {
        if (l < 0L) {
            throw new IndexOutOfBoundsException(String.valueOf(new StringBuilder().append("Invalid offset: ").append(l)));
        }
        if (l + l2 > this.size) {
            String string = String.valueOf(new StringBuilder().append("Bounds exceeds available space : size=").append(this.size).append(", offset=").append(l + l2));
            throw new IndexOutOfBoundsException(string);
        }
    }

    @Override
    public int getInt(long l) {
        this.boundsCheck(l, 4L);
        return super.getInt(l);
    }

    @Override
    public double getDouble(long l) {
        this.boundsCheck(l, 8L);
        return super.getDouble(l);
    }

    public long size() {
        return this.size;
    }

    @Override
    public void write(long l, double[] arrd, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 8L);
        super.write(l, arrd, n, n2);
    }

    static {
        allocatedMemory = Collections.synchronizedMap(new WeakHashMap());
        buffers = new WeakMemoryHolder();
    }

    @Override
    public void setShort(long l, short s) {
        this.boundsCheck(l, 2L);
        super.setShort(l, s);
    }

    @Override
    public char getChar(long l) {
        this.boundsCheck(l, 1L);
        return super.getChar(l);
    }

    @Override
    public void setInt(long l, int n) {
        this.boundsCheck(l, 4L);
        super.setInt(l, n);
    }

    @Override
    public Pointer getPointer(long l) {
        this.boundsCheck(l, Pointer.SIZE);
        return super.getPointer(l);
    }

    @Override
    public void setByte(long l, byte by) {
        this.boundsCheck(l, 1L);
        super.setByte(l, by);
    }

    @Override
    public void write(long l, byte[] arrby, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 1L);
        super.write(l, arrby, n, n2);
    }

    @Override
    public void read(long l, char[] arrc, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 2L);
        super.read(l, arrc, n, n2);
    }

    @Override
    public String getWideString(long l) {
        this.boundsCheck(l, 0L);
        return super.getWideString(l);
    }

    @Override
    public short getShort(long l) {
        this.boundsCheck(l, 2L);
        return super.getShort(l);
    }

    @Override
    public void read(long l, int[] arrn, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 4L);
        super.read(l, arrn, n, n2);
    }

    public static void purge() {
        buffers.clean();
    }

    @Override
    public float getFloat(long l) {
        this.boundsCheck(l, 4L);
        return super.getFloat(l);
    }

    @Override
    public byte getByte(long l) {
        this.boundsCheck(l, 1L);
        return super.getByte(l);
    }

    @Override
    public ByteBuffer getByteBuffer(long l, long l2) {
        this.boundsCheck(l, l2);
        ByteBuffer byteBuffer = super.getByteBuffer(l, l2);
        buffers.put(byteBuffer, this);
        return byteBuffer;
    }

    public Memory(long l) {
        this.size = l;
        if (l <= 0L) {
            throw new IllegalArgumentException("Allocation size must be greater than zero");
        }
        this.peer = Memory.malloc(l);
        if (this.peer == 0L) {
            throw new OutOfMemoryError(String.valueOf(new StringBuilder().append("Cannot allocate ").append(l).append(" bytes")));
        }
        allocatedMemory.put(this, new WeakReference<Memory>(this));
    }

    protected synchronized void dispose() {
        try {
            Memory.free(this.peer);
            return;
        }
        finally {
            allocatedMemory.remove(this);
            this.peer = 0L;
        }
    }

    public Memory align(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Byte boundary must be positive: ").append(n)));
        }
        for (int i = 0; i < 32; ++i) {
            if (n != 1 << i) continue;
            long l = (long)n - 1L ^ 0xFFFFFFFFFFFFFFFFL;
            if ((this.peer & l) != this.peer) {
                long l2 = this.peer + (long)n - 1L & l;
                long l3 = this.peer + this.size - l2;
                if (l3 <= 0L) {
                    throw new IllegalArgumentException("Insufficient memory to align to the requested boundary");
                }
                return (Memory)this.share(l2 - this.peer, l3);
            }
            return this;
        }
        throw new IllegalArgumentException("Byte boundary must be a power of two");
    }

    public static void disposeAll() {
        LinkedList<Memory> linkedList = new LinkedList<Memory>(allocatedMemory.keySet());
        for (Memory memory : linkedList) {
            memory.dispose();
        }
    }

    @Override
    public void write(long l, short[] arrs, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 2L);
        super.write(l, arrs, n, n2);
    }

    @Override
    public String getString(long l, String string) {
        this.boundsCheck(l, 0L);
        return super.getString(l, string);
    }

    @Override
    public void read(long l, byte[] arrby, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 1L);
        super.read(l, arrby, n, n2);
    }

    @Override
    public void write(long l, int[] arrn, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 4L);
        super.write(l, arrn, n, n2);
    }

    public boolean valid() {
        return this.peer != 0L;
    }

    @Override
    public void read(long l, long[] arrl, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 8L);
        super.read(l, arrl, n, n2);
    }

    @Override
    public void setString(long l, String string, String string2) {
        this.boundsCheck(l, (long)Native.getBytes(string, string2).length + 1L);
        super.setString(l, string, string2);
    }

    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append("allocated@0x").append(Long.toHexString(this.peer)).append(" (").append(this.size).append(" bytes)"));
    }

    @Override
    public void read(long l, short[] arrs, int n, int n2) {
        this.boundsCheck(l, (long)n2 * 2L);
        super.read(l, arrs, n, n2);
    }

    protected static void free(long l) {
        if (l != 0L) {
            Native.free(l);
        }
    }

    @Override
    public void setPointer(long l, Pointer pointer) {
        this.boundsCheck(l, Pointer.SIZE);
        super.setPointer(l, pointer);
    }

    protected void finalize() {
        this.dispose();
    }

    private class SharedMemory
    extends Memory {
        final Memory this$0;

        @Override
        public String toString() {
            return String.valueOf(new StringBuilder().append(super.toString()).append(" (shared from ").append(this.this$0.toString()).append(")"));
        }

        @Override
        protected void boundsCheck(long l, long l2) {
            this.this$0.boundsCheck(this.peer - this.this$0.peer + l, l2);
        }

        public SharedMemory(Memory memory, long l, long l2) {
            this.this$0 = memory;
            this.size = l2;
            this.peer = memory.peer + l;
        }

        @Override
        protected void dispose() {
            this.peer = 0L;
        }
    }
}

