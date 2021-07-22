/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.misc;

import java.io.DataOutput;
import java.io.IOException;

public class ByteCountDataOutput
implements DataOutput {
    public static final ByteCountDataOutput INSTANCE = new ByteCountDataOutput();
    private int count;

    @Override
    public void writeInt(int n) throws IOException {
        this.count += 4;
    }

    @Override
    public void writeBoolean(boolean bl) throws IOException {
        ++this.count;
    }

    @Override
    public void write(int n) throws IOException {
        ++this.count;
    }

    @Override
    public void writeChars(String string) throws IOException {
        this.count += string.length() * 2;
    }

    @Override
    public void writeDouble(double d) throws IOException {
        this.count += 8;
    }

    @Override
    public void writeUTF(String string) throws IOException {
        this.count = (int)((long)this.count + (2L + this.getUTFLength(string)));
    }

    @Override
    public void writeShort(int n) throws IOException {
        this.count += 2;
    }

    @Override
    public void writeBytes(String string) throws IOException {
        this.count += string.length();
    }

    @Override
    public void write(byte[] arrby, int n, int n2) throws IOException {
        this.count += n2;
    }

    @Override
    public void writeFloat(float f) throws IOException {
        this.count += 4;
    }

    long getUTFLength(String string) {
        long l = 0L;
        for (int i = 0; i < string.length(); ++i) {
            char c = string.charAt(i);
            if (c >= '\u0001' && c <= '\u007f') {
                ++l;
                continue;
            }
            if (c > '\u07ff') {
                l += 3L;
                continue;
            }
            l += 2L;
            if (null == null) continue;
            return 0L;
        }
        return l;
    }

    public void reset() {
        this.count = 0;
    }

    @Override
    public void writeByte(int n) throws IOException {
        ++this.count;
    }

    @Override
    public void writeChar(int n) throws IOException {
        this.count += 2;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public void write(byte[] arrby) throws IOException {
        this.count += arrby.length;
    }

    @Override
    public void writeLong(long l) throws IOException {
        this.count += 8;
    }
}

