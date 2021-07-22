/*
 * Decompiled with CFR 0.150.
 */
package com.sun.jna;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

class ELFAnalyser {
    private /* synthetic */ boolean arm;
    private static final /* synthetic */ int EF_ARM_ABI_FLOAT_HARD;
    private static final /* synthetic */ int EI_DATA_BIG_ENDIAN;
    private /* synthetic */ boolean armSoftFloat;
    private /* synthetic */ boolean _64Bit;
    private static final /* synthetic */ byte[] ELF_MAGIC;
    private static final /* synthetic */ int E_MACHINE_ARM;
    private static final /* synthetic */ int EI_CLASS_64BIT;
    private /* synthetic */ boolean ELF;
    private static final /* synthetic */ int EF_ARM_ABI_FLOAT_SOFT;
    private final /* synthetic */ String filename;
    private /* synthetic */ boolean bigEndian;
    private /* synthetic */ boolean armHardFloat;

    public static ELFAnalyser analyse(String llllllllllllllllllIIIllIIlIllIII) throws IOException {
        ELFAnalyser llllllllllllllllllIIIllIIlIlIlll = new ELFAnalyser(llllllllllllllllllIIIllIIlIllIII);
        llllllllllllllllllIIIllIIlIlIlll.runDetection();
        return llllllllllllllllllIIIllIIlIlIlll;
    }

    static {
        EI_DATA_BIG_ENDIAN = 2;
        EF_ARM_ABI_FLOAT_SOFT = 512;
        EI_CLASS_64BIT = 2;
        E_MACHINE_ARM = 40;
        EF_ARM_ABI_FLOAT_HARD = 1024;
        ELF_MAGIC = new byte[]{127, 69, 76, 70};
    }

    public boolean isArm() {
        ELFAnalyser llllllllllllllllllIIIllIIlIIIIII;
        return llllllllllllllllllIIIllIIlIIIIII.arm;
    }

    public boolean isELF() {
        ELFAnalyser llllllllllllllllllIIIllIIlIlIIll;
        return llllllllllllllllllIIIllIIlIlIIll.ELF;
    }

    public boolean isBigEndian() {
        ELFAnalyser llllllllllllllllllIIIllIIlIIllIl;
        return llllllllllllllllllIIIllIIlIIllIl.bigEndian;
    }

    public boolean isArmSoftFloat() {
        ELFAnalyser llllllllllllllllllIIIllIIlIIIIll;
        return llllllllllllllllllIIIllIIlIIIIll.armSoftFloat;
    }

    private ELFAnalyser(String llllllllllllllllllIIIllIIIlllIlI) {
        ELFAnalyser llllllllllllllllllIIIllIIIllllIl;
        llllllllllllllllllIIIllIIIllllIl.ELF = false;
        llllllllllllllllllIIIllIIIllllIl._64Bit = false;
        llllllllllllllllllIIIllIIIllllIl.bigEndian = false;
        llllllllllllllllllIIIllIIIllllIl.armHardFloat = false;
        llllllllllllllllllIIIllIIIllllIl.armSoftFloat = false;
        llllllllllllllllllIIIllIIIllllIl.arm = false;
        llllllllllllllllllIIIllIIIllllIl.filename = llllllllllllllllllIIIllIIIlllIlI;
    }

    public boolean isArmHardFloat() {
        ELFAnalyser llllllllllllllllllIIIllIIlIIIlll;
        return llllllllllllllllllIIIllIIlIIIlll.armHardFloat;
    }

    public boolean is64Bit() {
        ELFAnalyser llllllllllllllllllIIIllIIlIIllll;
        return llllllllllllllllllIIIllIIlIIllll._64Bit;
    }

    private void runDetection() throws IOException {
        ELFAnalyser llllllllllllllllllIIIllIIIlIlllI;
        RandomAccessFile llllllllllllllllllIIIllIIIllIIIl = new RandomAccessFile(llllllllllllllllllIIIllIIIlIlllI.filename, "r");
        if (llllllllllllllllllIIIllIIIllIIIl.length() > 4L) {
            byte[] llllllllllllllllllIIIllIIIllIlII = new byte[4];
            llllllllllllllllllIIIllIIIllIIIl.seek(0L);
            llllllllllllllllllIIIllIIIllIIIl.read(llllllllllllllllllIIIllIIIllIlII);
            if (Arrays.equals(llllllllllllllllllIIIllIIIllIlII, ELF_MAGIC)) {
                llllllllllllllllllIIIllIIIlIlllI.ELF = true;
            }
        }
        if (!llllllllllllllllllIIIllIIIlIlllI.ELF) {
            return;
        }
        llllllllllllllllllIIIllIIIllIIIl.seek(4L);
        byte llllllllllllllllllIIIllIIIllIIII = llllllllllllllllllIIIllIIIllIIIl.readByte();
        llllllllllllllllllIIIllIIIlIlllI._64Bit = llllllllllllllllllIIIllIIIllIIII == 2;
        llllllllllllllllllIIIllIIIllIIIl.seek(0L);
        ByteBuffer llllllllllllllllllIIIllIIIlIllll = ByteBuffer.allocate(llllllllllllllllllIIIllIIIlIlllI._64Bit ? 64 : 52);
        llllllllllllllllllIIIllIIIllIIIl.getChannel().read(llllllllllllllllllIIIllIIIlIllll, 0L);
        llllllllllllllllllIIIllIIIlIlllI.bigEndian = llllllllllllllllllIIIllIIIlIllll.get(5) == 2;
        llllllllllllllllllIIIllIIIlIllll.order(llllllllllllllllllIIIllIIIlIlllI.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        boolean bl = llllllllllllllllllIIIllIIIlIlllI.arm = llllllllllllllllllIIIllIIIlIllll.get(18) == 40;
        if (llllllllllllllllllIIIllIIIlIlllI.arm) {
            int llllllllllllllllllIIIllIIIllIIll = llllllllllllllllllIIIllIIIlIllll.getInt(llllllllllllllllllIIIllIIIlIlllI._64Bit ? 48 : 36);
            llllllllllllllllllIIIllIIIlIlllI.armSoftFloat = (llllllllllllllllllIIIllIIIllIIll & 0x200) == 512;
            llllllllllllllllllIIIllIIIlIlllI.armHardFloat = (llllllllllllllllllIIIllIIIllIIll & 0x400) == 1024;
        }
    }

    public String getFilename() {
        ELFAnalyser llllllllllllllllllIIIllIIlIIlIIl;
        return llllllllllllllllllIIIllIIlIIlIIl.filename;
    }
}

