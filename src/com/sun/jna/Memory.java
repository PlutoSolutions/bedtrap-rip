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
    protected /* synthetic */ long size;
    private static final /* synthetic */ WeakMemoryHolder buffers;
    private static final /* synthetic */ Map<Memory, Reference<Memory>> allocatedMemory;

    @Override
    public void setLong(long llllllllllllllllIlllllIllIlIIlIl, long llllllllllllllllIlllllIllIlIIlII) {
        Memory llllllllllllllllIlllllIllIlIIllI;
        llllllllllllllllIlllllIllIlIIllI.boundsCheck(llllllllllllllllIlllllIllIlIIlIl, 8L);
        super.setLong(llllllllllllllllIlllllIllIlIIlIl, llllllllllllllllIlllllIllIlIIlII);
    }

    @Override
    public void setWideString(long llllllllllllllllIlllllIlIlllIlIl, String llllllllllllllllIlllllIlIlllIlll) {
        Memory llllllllllllllllIlllllIlIllllIIl;
        llllllllllllllllIlllllIlIllllIIl.boundsCheck(llllllllllllllllIlllllIlIlllIlIl, ((long)llllllllllllllllIlllllIlIlllIlll.length() + 1L) * (long)Native.WCHAR_SIZE);
        super.setWideString(llllllllllllllllIlllllIlIlllIlIl, llllllllllllllllIlllllIlIlllIlll);
    }

    @Override
    public void setFloat(long llllllllllllllllIlllllIllIIlllII, float llllllllllllllllIlllllIllIIllllI) {
        Memory llllllllllllllllIlllllIllIlIIIII;
        llllllllllllllllIlllllIllIlIIIII.boundsCheck(llllllllllllllllIlllllIllIIlllII, 4L);
        super.setFloat(llllllllllllllllIlllllIllIIlllII, llllllllllllllllIlllllIllIIllllI);
    }

    public void clear() {
        Memory llllllllllllllllIlllllllIIIIIIII;
        llllllllllllllllIlllllllIIIIIIII.clear(llllllllllllllllIlllllllIIIIIIII.size);
    }

    @Override
    public void write(long llllllllllllllllIllllllIIllIIIII, char[] llllllllllllllllIllllllIIlIllIlI, int llllllllllllllllIllllllIIlIllIIl, int llllllllllllllllIllllllIIlIlllIl) {
        Memory llllllllllllllllIllllllIIlIlllII;
        llllllllllllllllIllllllIIlIlllII.boundsCheck(llllllllllllllllIllllllIIllIIIII, (long)llllllllllllllllIllllllIIlIlllIl * 2L);
        super.write(llllllllllllllllIllllllIIllIIIII, llllllllllllllllIllllllIIlIllIlI, llllllllllllllllIllllllIIlIllIIl, llllllllllllllllIllllllIIlIlllIl);
    }

    protected static long malloc(long llllllllllllllllIlllllIlIllIlIll) {
        return Native.malloc(llllllllllllllllIlllllIlIllIlIll);
    }

    @Override
    public void write(long llllllllllllllllIllllllIIIllllIl, long[] llllllllllllllllIllllllIIIllllII, int llllllllllllllllIllllllIIlIIIIII, int llllllllllllllllIllllllIIIllllll) {
        Memory llllllllllllllllIllllllIIIlllllI;
        llllllllllllllllIllllllIIIlllllI.boundsCheck(llllllllllllllllIllllllIIIllllIl, (long)llllllllllllllllIllllllIIIllllll * 8L);
        super.write(llllllllllllllllIllllllIIIllllIl, llllllllllllllllIllllllIIIllllII, llllllllllllllllIllllllIIlIIIIII, llllllllllllllllIllllllIIIllllll);
    }

    @Override
    public void write(long llllllllllllllllIllllllIIIllIIll, float[] llllllllllllllllIllllllIIIllIIlI, int llllllllllllllllIllllllIIIlIllII, int llllllllllllllllIllllllIIIlIlIll) {
        Memory llllllllllllllllIllllllIIIlIllll;
        llllllllllllllllIllllllIIIlIllll.boundsCheck(llllllllllllllllIllllllIIIllIIll, (long)llllllllllllllllIllllllIIIlIlIll * 4L);
        super.write(llllllllllllllllIllllllIIIllIIll, llllllllllllllllIllllllIIIllIIlI, llllllllllllllllIllllllIIIlIllII, llllllllllllllllIllllllIIIlIlIll);
    }

    @Override
    public long getLong(long llllllllllllllllIllllllIIIIIIIII) {
        Memory llllllllllllllllIlllllIlllllllll;
        llllllllllllllllIlllllIlllllllll.boundsCheck(llllllllllllllllIllllllIIIIIIIII, 8L);
        return super.getLong(llllllllllllllllIllllllIIIIIIIII);
    }

    @Override
    public void read(long llllllllllllllllIllllllIlIIIlIII, double[] llllllllllllllllIllllllIlIIIllII, int llllllllllllllllIllllllIlIIIlIll, int llllllllllllllllIllllllIlIIIIlIl) {
        Memory llllllllllllllllIllllllIlIIIlllI;
        llllllllllllllllIllllllIlIIIlllI.boundsCheck(llllllllllllllllIllllllIlIIIlIII, (long)llllllllllllllllIllllllIlIIIIlIl * 8L);
        super.read(llllllllllllllllIllllllIlIIIlIII, llllllllllllllllIllllllIlIIIllII, llllllllllllllllIllllllIlIIIlIll, llllllllllllllllIllllllIlIIIIlIl);
    }

    @Override
    public void read(long llllllllllllllllIllllllIlIIlIlll, float[] llllllllllllllllIllllllIlIIlIllI, int llllllllllllllllIllllllIlIIllIlI, int llllllllllllllllIllllllIlIIlIlII) {
        Memory llllllllllllllllIllllllIlIIllIII;
        llllllllllllllllIllllllIlIIllIII.boundsCheck(llllllllllllllllIllllllIlIIlIlll, (long)llllllllllllllllIllllllIlIIlIlII * 4L);
        super.read(llllllllllllllllIllllllIlIIlIlll, llllllllllllllllIllllllIlIIlIllI, llllllllllllllllIllllllIlIIllIlI, llllllllllllllllIllllllIlIIlIlII);
    }

    protected Memory() {
        Memory llllllllllllllllIlllllllIIlIllIl;
    }

    @Override
    public Pointer share(long llllllllllllllllIlllllllIIlIlIII) {
        Memory llllllllllllllllIlllllllIIlIlIIl;
        return llllllllllllllllIlllllllIIlIlIIl.share(llllllllllllllllIlllllllIIlIlIII, llllllllllllllllIlllllllIIlIlIIl.size() - llllllllllllllllIlllllllIIlIlIII);
    }

    @Override
    public void setChar(long llllllllllllllllIlllllIlllIIIIII, char llllllllllllllllIlllllIlllIIIIlI) {
        Memory llllllllllllllllIlllllIlllIIIlII;
        llllllllllllllllIlllllIlllIIIlII.boundsCheck(llllllllllllllllIlllllIlllIIIIII, Native.WCHAR_SIZE);
        super.setChar(llllllllllllllllIlllllIlllIIIIII, llllllllllllllllIlllllIlllIIIIlI);
    }

    public String dump() {
        Memory llllllllllllllllIlllllIlIllIlIII;
        return llllllllllllllllIlllllIlIllIlIII.dump(0L, (int)llllllllllllllllIlllllIlIllIlIII.size());
    }

    @Override
    public void setDouble(long llllllllllllllllIlllllIllIIlIIll, double llllllllllllllllIlllllIllIIlIlIl) {
        Memory llllllllllllllllIlllllIllIIlIlII;
        llllllllllllllllIlllllIllIIlIlII.boundsCheck(llllllllllllllllIlllllIllIIlIIll, 8L);
        super.setDouble(llllllllllllllllIlllllIllIIlIIll, llllllllllllllllIlllllIllIIlIlIl);
    }

    @Override
    public Pointer share(long llllllllllllllllIlllllllIIIllllI, long llllllllllllllllIlllllllIIIlllIl) {
        Memory llllllllllllllllIlllllllIIlIIIlI;
        llllllllllllllllIlllllllIIlIIIlI.boundsCheck(llllllllllllllllIlllllllIIIllllI, llllllllllllllllIlllllllIIIlllIl);
        return llllllllllllllllIlllllllIIlIIIlI.new SharedMemory(llllllllllllllllIlllllllIIIllllI, llllllllllllllllIlllllllIIIlllIl);
    }

    protected void boundsCheck(long llllllllllllllllIllllllIllllIIll, long llllllllllllllllIllllllIlllIllll) {
        Memory llllllllllllllllIllllllIllllIIIl;
        if (llllllllllllllllIllllllIllllIIll < 0L) {
            throw new IndexOutOfBoundsException(String.valueOf(new StringBuilder().append("Invalid offset: ").append(llllllllllllllllIllllllIllllIIll)));
        }
        if (llllllllllllllllIllllllIllllIIll + llllllllllllllllIllllllIlllIllll > llllllllllllllllIllllllIllllIIIl.size) {
            String llllllllllllllllIllllllIllllIlIl = String.valueOf(new StringBuilder().append("Bounds exceeds available space : size=").append(llllllllllllllllIllllllIllllIIIl.size).append(", offset=").append(llllllllllllllllIllllllIllllIIll + llllllllllllllllIllllllIlllIllll));
            throw new IndexOutOfBoundsException(llllllllllllllllIllllllIllllIlIl);
        }
    }

    @Override
    public int getInt(long llllllllllllllllIllllllIIIIIIllI) {
        Memory llllllllllllllllIllllllIIIIIIlll;
        llllllllllllllllIllllllIIIIIIlll.boundsCheck(llllllllllllllllIllllllIIIIIIllI, 4L);
        return super.getInt(llllllllllllllllIllllllIIIIIIllI);
    }

    @Override
    public double getDouble(long llllllllllllllllIlllllIlllllIlII) {
        Memory llllllllllllllllIlllllIlllllIlIl;
        llllllllllllllllIlllllIlllllIlIl.boundsCheck(llllllllllllllllIlllllIlllllIlII, 8L);
        return super.getDouble(llllllllllllllllIlllllIlllllIlII);
    }

    public long size() {
        Memory llllllllllllllllIllllllIlllllIll;
        return llllllllllllllllIllllllIlllllIll.size;
    }

    @Override
    public void write(long llllllllllllllllIllllllIIIIlllll, double[] llllllllllllllllIllllllIIIlIIIll, int llllllllllllllllIllllllIIIlIIIlI, int llllllllllllllllIllllllIIIIlllII) {
        Memory llllllllllllllllIllllllIIIlIIIII;
        llllllllllllllllIllllllIIIlIIIII.boundsCheck(llllllllllllllllIllllllIIIIlllll, (long)llllllllllllllllIllllllIIIIlllII * 8L);
        super.write(llllllllllllllllIllllllIIIIlllll, llllllllllllllllIllllllIIIlIIIll, llllllllllllllllIllllllIIIlIIIlI, llllllllllllllllIllllllIIIIlllII);
    }

    static {
        allocatedMemory = Collections.synchronizedMap(new WeakHashMap());
        buffers = new WeakMemoryHolder();
    }

    @Override
    public void setShort(long llllllllllllllllIlllllIllIllIlll, short llllllllllllllllIlllllIllIllIllI) {
        Memory llllllllllllllllIlllllIllIlllIll;
        llllllllllllllllIlllllIllIlllIll.boundsCheck(llllllllllllllllIlllllIllIllIlll, 2L);
        super.setShort(llllllllllllllllIlllllIllIllIlll, llllllllllllllllIlllllIllIllIllI);
    }

    @Override
    public char getChar(long llllllllllllllllIllllllIIIIlIIlI) {
        Memory llllllllllllllllIllllllIIIIlIIll;
        llllllllllllllllIllllllIIIIlIIll.boundsCheck(llllllllllllllllIllllllIIIIlIIlI, 1L);
        return super.getChar(llllllllllllllllIllllllIIIIlIIlI);
    }

    @Override
    public void setInt(long llllllllllllllllIlllllIllIllIIIl, int llllllllllllllllIlllllIllIllIIII) {
        Memory llllllllllllllllIlllllIllIlIllll;
        llllllllllllllllIlllllIllIlIllll.boundsCheck(llllllllllllllllIlllllIllIllIIIl, 4L);
        super.setInt(llllllllllllllllIlllllIllIllIIIl, llllllllllllllllIlllllIllIllIIII);
    }

    @Override
    public Pointer getPointer(long llllllllllllllllIlllllIllllIllII) {
        Memory llllllllllllllllIlllllIllllIllll;
        llllllllllllllllIlllllIllllIllll.boundsCheck(llllllllllllllllIlllllIllllIllII, Pointer.SIZE);
        return super.getPointer(llllllllllllllllIlllllIllllIllII);
    }

    @Override
    public void setByte(long llllllllllllllllIlllllIlllIIllII, byte llllllllllllllllIlllllIlllIIlIll) {
        Memory llllllllllllllllIlllllIlllIIllIl;
        llllllllllllllllIlllllIlllIIllIl.boundsCheck(llllllllllllllllIlllllIlllIIllII, 1L);
        super.setByte(llllllllllllllllIlllllIlllIIllII, llllllllllllllllIlllllIlllIIlIll);
    }

    @Override
    public void write(long llllllllllllllllIllllllIIllllIIl, byte[] llllllllllllllllIllllllIIlllllIl, int llllllllllllllllIllllllIIlllllII, int llllllllllllllllIllllllIIllllIll) {
        Memory llllllllllllllllIllllllIIlllllll;
        llllllllllllllllIllllllIIlllllll.boundsCheck(llllllllllllllllIllllllIIllllIIl, (long)llllllllllllllllIllllllIIllllIll * 1L);
        super.write(llllllllllllllllIllllllIIllllIIl, llllllllllllllllIllllllIIlllllIl, llllllllllllllllIllllllIIlllllII, llllllllllllllllIllllllIIllllIll);
    }

    @Override
    public void read(long llllllllllllllllIllllllIllIIIlII, char[] llllllllllllllllIllllllIllIIIIll, int llllllllllllllllIllllllIllIIIlll, int llllllllllllllllIllllllIllIIIIIl) {
        Memory llllllllllllllllIllllllIllIIIlIl;
        llllllllllllllllIllllllIllIIIlIl.boundsCheck(llllllllllllllllIllllllIllIIIlII, (long)llllllllllllllllIllllllIllIIIIIl * 2L);
        super.read(llllllllllllllllIllllllIllIIIlII, llllllllllllllllIllllllIllIIIIll, llllllllllllllllIllllllIllIIIlll, llllllllllllllllIllllllIllIIIIIl);
    }

    @Override
    public String getWideString(long llllllllllllllllIlllllIlllIlIIIl) {
        Memory llllllllllllllllIlllllIlllIlIlII;
        llllllllllllllllIlllllIlllIlIlII.boundsCheck(llllllllllllllllIlllllIlllIlIIIl, 0L);
        return super.getWideString(llllllllllllllllIlllllIlllIlIIIl);
    }

    @Override
    public short getShort(long llllllllllllllllIllllllIIIIIllII) {
        Memory llllllllllllllllIllllllIIIIIllIl;
        llllllllllllllllIllllllIIIIIllIl.boundsCheck(llllllllllllllllIllllllIIIIIllII, 2L);
        return super.getShort(llllllllllllllllIllllllIIIIIllII);
    }

    @Override
    public void read(long llllllllllllllllIllllllIlIllIlIl, int[] llllllllllllllllIllllllIlIlllIIl, int llllllllllllllllIllllllIlIlllIII, int llllllllllllllllIllllllIlIllIlll) {
        Memory llllllllllllllllIllllllIlIllIllI;
        llllllllllllllllIllllllIlIllIllI.boundsCheck(llllllllllllllllIllllllIlIllIlIl, (long)llllllllllllllllIllllllIlIllIlll * 4L);
        super.read(llllllllllllllllIllllllIlIllIlIl, llllllllllllllllIllllllIlIlllIIl, llllllllllllllllIllllllIlIlllIII, llllllllllllllllIllllllIlIllIlll);
    }

    public static void purge() {
        buffers.clean();
    }

    @Override
    public float getFloat(long llllllllllllllllIlllllIllllllIlI) {
        Memory llllllllllllllllIlllllIllllllIIl;
        llllllllllllllllIlllllIllllllIIl.boundsCheck(llllllllllllllllIlllllIllllllIlI, 4L);
        return super.getFloat(llllllllllllllllIlllllIllllllIlI);
    }

    @Override
    public byte getByte(long llllllllllllllllIllllllIIIIllIII) {
        Memory llllllllllllllllIllllllIIIIllIIl;
        llllllllllllllllIllllllIIIIllIIl.boundsCheck(llllllllllllllllIllllllIIIIllIII, 1L);
        return super.getByte(llllllllllllllllIllllllIIIIllIII);
    }

    @Override
    public ByteBuffer getByteBuffer(long llllllllllllllllIlllllIllllIIIlI, long llllllllllllllllIlllllIllllIIIIl) {
        Memory llllllllllllllllIlllllIllllIIlll;
        llllllllllllllllIlllllIllllIIlll.boundsCheck(llllllllllllllllIlllllIllllIIIlI, llllllllllllllllIlllllIllllIIIIl);
        ByteBuffer llllllllllllllllIlllllIllllIIlII = super.getByteBuffer(llllllllllllllllIlllllIllllIIIlI, llllllllllllllllIlllllIllllIIIIl);
        buffers.put(llllllllllllllllIlllllIllllIIlII, llllllllllllllllIlllllIllllIIlll);
        return llllllllllllllllIlllllIllllIIlII;
    }

    public Memory(long llllllllllllllllIlllllllIIlIllll) {
        Memory llllllllllllllllIlllllllIIllIIlI;
        llllllllllllllllIlllllllIIllIIlI.size = llllllllllllllllIlllllllIIlIllll;
        if (llllllllllllllllIlllllllIIlIllll <= 0L) {
            throw new IllegalArgumentException("Allocation size must be greater than zero");
        }
        llllllllllllllllIlllllllIIllIIlI.peer = Memory.malloc(llllllllllllllllIlllllllIIlIllll);
        if (llllllllllllllllIlllllllIIllIIlI.peer == 0L) {
            throw new OutOfMemoryError(String.valueOf(new StringBuilder().append("Cannot allocate ").append(llllllllllllllllIlllllllIIlIllll).append(" bytes")));
        }
        allocatedMemory.put(llllllllllllllllIlllllllIIllIIlI, new WeakReference<Memory>(llllllllllllllllIlllllllIIllIIlI));
    }

    protected synchronized void dispose() {
        Memory llllllllllllllllIlllllllIIIIIlIl;
        try {
            Memory.free(llllllllllllllllIlllllllIIIIIlIl.peer);
        }
        finally {
            allocatedMemory.remove(llllllllllllllllIlllllllIIIIIlIl);
            llllllllllllllllIlllllllIIIIIlIl.peer = 0L;
        }
    }

    public Memory align(int llllllllllllllllIlllllllIIIlIIIl) {
        if (llllllllllllllllIlllllllIIIlIIIl <= 0) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Byte boundary must be positive: ").append(llllllllllllllllIlllllllIIIlIIIl)));
        }
        for (int llllllllllllllllIlllllllIIIlIIll = 0; llllllllllllllllIlllllllIIIlIIll < 32; ++llllllllllllllllIlllllllIIIlIIll) {
            Memory llllllllllllllllIlllllllIIIlIIlI;
            if (llllllllllllllllIlllllllIIIlIIIl != 1 << llllllllllllllllIlllllllIIIlIIll) continue;
            long llllllllllllllllIlllllllIIIlIlII = (long)llllllllllllllllIlllllllIIIlIIIl - 1L ^ 0xFFFFFFFFFFFFFFFFL;
            if ((llllllllllllllllIlllllllIIIlIIlI.peer & llllllllllllllllIlllllllIIIlIlII) != llllllllllllllllIlllllllIIIlIIlI.peer) {
                long llllllllllllllllIlllllllIIIlIllI = llllllllllllllllIlllllllIIIlIIlI.peer + (long)llllllllllllllllIlllllllIIIlIIIl - 1L & llllllllllllllllIlllllllIIIlIlII;
                long llllllllllllllllIlllllllIIIlIlIl = llllllllllllllllIlllllllIIIlIIlI.peer + llllllllllllllllIlllllllIIIlIIlI.size - llllllllllllllllIlllllllIIIlIllI;
                if (llllllllllllllllIlllllllIIIlIlIl <= 0L) {
                    throw new IllegalArgumentException("Insufficient memory to align to the requested boundary");
                }
                return (Memory)llllllllllllllllIlllllllIIIlIIlI.share(llllllllllllllllIlllllllIIIlIllI - llllllllllllllllIlllllllIIIlIIlI.peer, llllllllllllllllIlllllllIIIlIlIl);
            }
            return llllllllllllllllIlllllllIIIlIIlI;
        }
        throw new IllegalArgumentException("Byte boundary must be a power of two");
    }

    public static void disposeAll() {
        LinkedList<Memory> llllllllllllllllIlllllllIIlllIII = new LinkedList<Memory>(allocatedMemory.keySet());
        for (Memory llllllllllllllllIlllllllIIlllIIl : llllllllllllllllIlllllllIIlllIII) {
            llllllllllllllllIlllllllIIlllIIl.dispose();
        }
    }

    @Override
    public void write(long llllllllllllllllIllllllIIllIllll, short[] llllllllllllllllIllllllIIllIlllI, int llllllllllllllllIllllllIIllIlIII, int llllllllllllllllIllllllIIllIIlll) {
        Memory llllllllllllllllIllllllIIllIlIll;
        llllllllllllllllIllllllIIllIlIll.boundsCheck(llllllllllllllllIllllllIIllIllll, (long)llllllllllllllllIllllllIIllIIlll * 2L);
        super.write(llllllllllllllllIllllllIIllIllll, llllllllllllllllIllllllIIllIlllI, llllllllllllllllIllllllIIllIlIII, llllllllllllllllIllllllIIllIIlll);
    }

    @Override
    public String getString(long llllllllllllllllIlllllIlllIllIII, String llllllllllllllllIlllllIlllIlIlll) {
        Memory llllllllllllllllIlllllIlllIlllII;
        llllllllllllllllIlllllIlllIlllII.boundsCheck(llllllllllllllllIlllllIlllIllIII, 0L);
        return super.getString(llllllllllllllllIlllllIlllIllIII, llllllllllllllllIlllllIlllIlIlll);
    }

    @Override
    public void read(long llllllllllllllllIllllllIlllIIIlI, byte[] llllllllllllllllIllllllIlllIIllI, int llllllllllllllllIllllllIlllIIIII, int llllllllllllllllIllllllIllIlllll) {
        Memory llllllllllllllllIllllllIlllIIIll;
        llllllllllllllllIllllllIlllIIIll.boundsCheck(llllllllllllllllIllllllIlllIIIlI, (long)llllllllllllllllIllllllIllIlllll * 1L);
        super.read(llllllllllllllllIllllllIlllIIIlI, llllllllllllllllIllllllIlllIIllI, llllllllllllllllIllllllIlllIIIII, llllllllllllllllIllllllIllIlllll);
    }

    @Override
    public void write(long llllllllllllllllIllllllIIlIlIIIl, int[] llllllllllllllllIllllllIIlIIlIll, int llllllllllllllllIllllllIIlIIllll, int llllllllllllllllIllllllIIlIIlIIl) {
        Memory llllllllllllllllIllllllIIlIlIIlI;
        llllllllllllllllIllllllIIlIlIIlI.boundsCheck(llllllllllllllllIllllllIIlIlIIIl, (long)llllllllllllllllIllllllIIlIIlIIl * 4L);
        super.write(llllllllllllllllIllllllIIlIlIIIl, llllllllllllllllIllllllIIlIIlIll, llllllllllllllllIllllllIIlIIllll, llllllllllllllllIllllllIIlIIlIIl);
    }

    public boolean valid() {
        Memory llllllllllllllllIllllllIllllllIl;
        return llllllllllllllllIllllllIllllllIl.peer != 0L;
    }

    @Override
    public void read(long llllllllllllllllIllllllIlIlIIllI, long[] llllllllllllllllIllllllIlIlIIlIl, int llllllllllllllllIllllllIlIlIIlII, int llllllllllllllllIllllllIlIlIIIll) {
        Memory llllllllllllllllIllllllIlIlIIlll;
        llllllllllllllllIllllllIlIlIIlll.boundsCheck(llllllllllllllllIllllllIlIlIIllI, (long)llllllllllllllllIllllllIlIlIIIll * 8L);
        super.read(llllllllllllllllIllllllIlIlIIllI, llllllllllllllllIllllllIlIlIIlIl, llllllllllllllllIllllllIlIlIIlII, llllllllllllllllIllllllIlIlIIIll);
    }

    @Override
    public void setString(long llllllllllllllllIlllllIllIIIIIll, String llllllllllllllllIlllllIlIllllllI, String llllllllllllllllIlllllIllIIIIIIl) {
        Memory llllllllllllllllIlllllIllIIIIlII;
        llllllllllllllllIlllllIllIIIIlII.boundsCheck(llllllllllllllllIlllllIllIIIIIll, (long)Native.getBytes(llllllllllllllllIlllllIlIllllllI, llllllllllllllllIlllllIllIIIIIIl).length + 1L);
        super.setString(llllllllllllllllIlllllIllIIIIIll, llllllllllllllllIlllllIlIllllllI, llllllllllllllllIlllllIllIIIIIIl);
    }

    @Override
    public String toString() {
        Memory llllllllllllllllIlllllIlIlllIIIl;
        return String.valueOf(new StringBuilder().append("allocated@0x").append(Long.toHexString(llllllllllllllllIlllllIlIlllIIIl.peer)).append(" (").append(llllllllllllllllIlllllIlIlllIIIl.size).append(" bytes)"));
    }

    @Override
    public void read(long llllllllllllllllIllllllIllIlIIll, short[] llllllllllllllllIllllllIllIlIIlI, int llllllllllllllllIllllllIllIlIIIl, int llllllllllllllllIllllllIllIlIIII) {
        Memory llllllllllllllllIllllllIllIlIlII;
        llllllllllllllllIllllllIllIlIlII.boundsCheck(llllllllllllllllIllllllIllIlIIll, (long)llllllllllllllllIllllllIllIlIIII * 2L);
        super.read(llllllllllllllllIllllllIllIlIIll, llllllllllllllllIllllllIllIlIIlI, llllllllllllllllIllllllIllIlIIIl, llllllllllllllllIllllllIllIlIIII);
    }

    protected static void free(long llllllllllllllllIlllllIlIllIlllI) {
        if (llllllllllllllllIlllllIlIllIlllI != 0L) {
            Native.free(llllllllllllllllIlllllIlIllIlllI);
        }
    }

    @Override
    public void setPointer(long llllllllllllllllIlllllIllIIIlIlI, Pointer llllllllllllllllIlllllIllIIIlIIl) {
        Memory llllllllllllllllIlllllIllIIIlIll;
        llllllllllllllllIlllllIllIIIlIll.boundsCheck(llllllllllllllllIlllllIllIIIlIlI, Pointer.SIZE);
        super.setPointer(llllllllllllllllIlllllIllIIIlIlI, llllllllllllllllIlllllIllIIIlIIl);
    }

    protected void finalize() {
        Memory llllllllllllllllIlllllllIIIIlIII;
        llllllllllllllllIlllllllIIIIlIII.dispose();
    }

    private class SharedMemory
    extends Memory {
        @Override
        public String toString() {
            SharedMemory llllllIIIllIIIl;
            return String.valueOf(new StringBuilder().append(super.toString()).append(" (shared from ").append(llllllIIIllIIIl.Memory.this.toString()).append(")"));
        }

        @Override
        protected void boundsCheck(long llllllIIIllIlll, long llllllIIIllIIll) {
            SharedMemory llllllIIIlllIII;
            llllllIIIlllIII.Memory.this.boundsCheck(llllllIIIlllIII.peer - llllllIIIlllIII.Memory.this.peer + llllllIIIllIlll, llllllIIIllIIll);
        }

        public SharedMemory(long llllllIIlIIIlII, long llllllIIlIIIIll) {
            SharedMemory llllllIIlIIIlIl;
            llllllIIlIIIlIl.size = llllllIIlIIIIll;
            llllllIIlIIIlIl.peer = llllllIIlIIIlIl.Memory.this.peer + llllllIIlIIIlII;
        }

        @Override
        protected void dispose() {
            llllllIIIllllIl.peer = 0L;
        }
    }
}

