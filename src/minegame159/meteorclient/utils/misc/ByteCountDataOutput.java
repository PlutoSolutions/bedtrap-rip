/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.misc;

import java.io.DataOutput;
import java.io.IOException;

public class ByteCountDataOutput
implements DataOutput {
    public static final /* synthetic */ ByteCountDataOutput INSTANCE;
    private /* synthetic */ int count;

    @Override
    public void writeInt(int lIllllIIIllIlI) throws IOException {
        lIllllIIIllIll.count += 4;
    }

    @Override
    public void writeBoolean(boolean lIllllIIlIlIlI) throws IOException {
        ByteCountDataOutput lIllllIIlIlIll;
        ++lIllllIIlIlIll.count;
    }

    @Override
    public void write(int lIllllIIllllII) throws IOException {
        ByteCountDataOutput lIllllIIlllIll;
        ++lIllllIIlllIll.count;
    }

    @Override
    public void writeChars(String lIllllIIIIIIIl) throws IOException {
        lIllllIIIIIIlI.count += lIllllIIIIIIIl.length() * 2;
    }

    @Override
    public void writeDouble(double lIllllIIIIlllI) throws IOException {
        lIllllIIIIllIl.count += 8;
    }

    @Override
    public void writeUTF(String lIlllIllllllIl) throws IOException {
        ByteCountDataOutput lIlllIlllllllI;
        lIlllIlllllllI.count = (int)((long)lIlllIlllllllI.count + (2L + lIlllIlllllllI.getUTFLength(lIlllIllllllIl)));
    }

    @Override
    public void writeShort(int lIllllIIlIIIlI) throws IOException {
        lIllllIIlIIIll.count += 2;
    }

    @Override
    public void writeBytes(String lIllllIIIIIlll) throws IOException {
        lIllllIIIIlIlI.count += lIllllIIIIIlll.length();
    }

    @Override
    public void write(byte[] lIllllIIllIIIl, int lIllllIIllIIII, int lIllllIIlIllll) throws IOException {
        lIllllIIlIlllI.count += lIllllIIlIllll;
    }

    @Override
    public void writeFloat(float lIllllIIIlIIlI) throws IOException {
        lIllllIIIlIIIl.count += 4;
    }

    long getUTFLength(String lIlllIllllIIIl) {
        long lIlllIllllIIlI = 0L;
        for (int lIlllIllllIlIl = 0; lIlllIllllIlIl < lIlllIllllIIIl.length(); ++lIlllIllllIlIl) {
            char lIlllIllllIllI = lIlllIllllIIIl.charAt(lIlllIllllIlIl);
            if (lIlllIllllIllI >= '\u0001' && lIlllIllllIllI <= '\u007f') {
                ++lIlllIllllIIlI;
                continue;
            }
            if (lIlllIllllIllI > '\u07ff') {
                lIlllIllllIIlI += 3L;
                continue;
            }
            lIlllIllllIIlI += 2L;
        }
        return lIlllIllllIIlI;
    }

    public void reset() {
        lIllllIIllllll.count = 0;
    }

    @Override
    public void writeByte(int lIllllIIlIIllI) throws IOException {
        ByteCountDataOutput lIllllIIlIIlll;
        ++lIllllIIlIIlll.count;
    }

    @Override
    public void writeChar(int lIllllIIIllllI) throws IOException {
        lIllllIIIlllIl.count += 2;
    }

    public int getCount() {
        ByteCountDataOutput lIllllIlIIIIll;
        return lIllllIlIIIIll.count;
    }

    static {
        INSTANCE = new ByteCountDataOutput();
    }

    public ByteCountDataOutput() {
        ByteCountDataOutput lIllllIlIIIllI;
    }

    @Override
    public void write(byte[] lIllllIIllIlll) throws IOException {
        lIllllIIlllIII.count += lIllllIIllIlll.length;
    }

    @Override
    public void writeLong(long lIllllIIIlIllI) throws IOException {
        lIllllIIIlIlIl.count += 8;
    }
}

