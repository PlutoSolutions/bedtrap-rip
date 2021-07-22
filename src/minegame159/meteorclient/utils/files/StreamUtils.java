/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtils {
    public static void copy(InputStream llllllllllllllllllIlIllllIIllIll, OutputStream llllllllllllllllllIlIllllIIlIlll) {
        byte[] llllllllllllllllllIlIllllIIllIIl = new byte[512];
        try {
            int llllllllllllllllllIlIllllIIlllIl;
            while ((llllllllllllllllllIlIllllIIlllIl = llllllllllllllllllIlIllllIIllIll.read(llllllllllllllllllIlIllllIIllIIl)) != -1) {
                llllllllllllllllllIlIllllIIlIlll.write(llllllllllllllllllIlIllllIIllIIl, 0, llllllllllllllllllIlIllllIIlllIl);
            }
        }
        catch (IOException llllllllllllllllllIlIllllIIlllII) {
            llllllllllllllllllIlIllllIIlllII.printStackTrace();
        }
    }

    public StreamUtils() {
        StreamUtils llllllllllllllllllIlIllllIlllIll;
    }

    public static void copy(InputStream llllllllllllllllllIlIllllIlIIlIl, File llllllllllllllllllIlIllllIlIIlII) {
        try {
            FileOutputStream llllllllllllllllllIlIllllIlIlIIl = new FileOutputStream(llllllllllllllllllIlIllllIlIIlII);
            StreamUtils.copy(llllllllllllllllllIlIllllIlIIlIl, llllllllllllllllllIlIllllIlIlIIl);
            llllllllllllllllllIlIllllIlIIlIl.close();
            ((OutputStream)llllllllllllllllllIlIllllIlIlIIl).close();
        }
        catch (IOException llllllllllllllllllIlIllllIlIlIII) {
            llllllllllllllllllIlIllllIlIlIII.printStackTrace();
        }
    }

    public static void copy(File llllllllllllllllllIlIllllIllIIlI, File llllllllllllllllllIlIllllIllIIIl) {
        try {
            FileInputStream llllllllllllllllllIlIllllIllIlIl = new FileInputStream(llllllllllllllllllIlIllllIllIIlI);
            FileOutputStream llllllllllllllllllIlIllllIllIlII = new FileOutputStream(llllllllllllllllllIlIllllIllIIIl);
            StreamUtils.copy((InputStream)llllllllllllllllllIlIllllIllIlIl, llllllllllllllllllIlIllllIllIlII);
            ((InputStream)llllllllllllllllllIlIllllIllIlIl).close();
            ((OutputStream)llllllllllllllllllIlIllllIllIlII).close();
        }
        catch (IOException llllllllllllllllllIlIllllIllIIll) {
            llllllllllllllllllIlIllllIllIIll.printStackTrace();
        }
    }
}

