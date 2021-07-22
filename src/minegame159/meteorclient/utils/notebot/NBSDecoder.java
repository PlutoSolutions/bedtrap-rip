/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package minegame159.meteorclient.utils.notebot;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import minegame159.meteorclient.utils.notebot.nbs.Layer;
import minegame159.meteorclient.utils.notebot.nbs.Note;
import minegame159.meteorclient.utils.notebot.nbs.Song;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NBSDecoder {
    public static final /* synthetic */ Logger LOG;

    private static String readString(DataInputStream lllIIIlIlllIIl) throws IOException {
        int lllIIIlIlllIII;
        StringBuilder lllIIIlIllIlll = new StringBuilder(lllIIIlIlllIII);
        for (lllIIIlIlllIII = NBSDecoder.readInt(lllIIIlIlllIIl); lllIIIlIlllIII > 0; --lllIIIlIlllIII) {
            char lllIIIlIlllIlI = (char)lllIIIlIlllIIl.readByte();
            if (lllIIIlIlllIlI == '\r') {
                lllIIIlIlllIlI = ' ';
            }
            lllIIIlIllIlll.append(lllIIIlIlllIlI);
        }
        return String.valueOf(lllIIIlIllIlll);
    }

    public NBSDecoder() {
        NBSDecoder lllIIlIlIlIlIl;
    }

    public static Song parse(InputStream lllIIlIlIIllIl) {
        return NBSDecoder.parse(lllIIlIlIIllIl, null);
    }

    private static Song parseOpenNBS(DataInputStream lllIIlIIIIIIIl, File lllIIIllllIlIl) throws IOException {
        short lllIIlIIIIIlIl;
        HashMap<Integer, Layer> lllIIIllllllll = new HashMap<Integer, Layer>();
        byte lllIIIlllllllI = lllIIlIIIIIIIl.readByte();
        if (lllIIIlllllllI != 5) {
            return null;
        }
        lllIIlIIIIIIIl.readByte();
        short lllIIIllllllIl = NBSDecoder.readShort(lllIIlIIIIIIIl);
        int lllIIIllllllII = NBSDecoder.readShort(lllIIlIIIIIIIl);
        String lllIIIlllllIll = NBSDecoder.readString(lllIIlIIIIIIIl);
        String lllIIIlllllIlI = NBSDecoder.readString(lllIIlIIIIIIIl);
        NBSDecoder.readString(lllIIlIIIIIIIl);
        String lllIIIlllllIIl = NBSDecoder.readString(lllIIlIIIIIIIl);
        float lllIIIlllllIII = (float)NBSDecoder.readShort(lllIIlIIIIIIIl) / 100.0f;
        lllIIlIIIIIIIl.readByte();
        lllIIlIIIIIIIl.readByte();
        lllIIlIIIIIIIl.readByte();
        NBSDecoder.readInt(lllIIlIIIIIIIl);
        NBSDecoder.readInt(lllIIlIIIIIIIl);
        NBSDecoder.readInt(lllIIlIIIIIIIl);
        NBSDecoder.readInt(lllIIlIIIIIIIl);
        NBSDecoder.readInt(lllIIlIIIIIIIl);
        NBSDecoder.readString(lllIIlIIIIIIIl);
        lllIIlIIIIIIIl.readByte();
        lllIIlIIIIIIIl.readByte();
        NBSDecoder.readShort(lllIIlIIIIIIIl);
        int lllIIIllllIlll = -1;
        while ((lllIIlIIIIIlIl = NBSDecoder.readShort(lllIIlIIIIIIIl)) != 0) {
            short lllIIlIIIIIllI;
            lllIIIllllIlll = (short)(lllIIIllllIlll + lllIIlIIIIIlIl);
            int lllIIlIIIIIlII = -1;
            while ((lllIIlIIIIIllI = NBSDecoder.readShort(lllIIlIIIIIIIl)) != 0) {
                lllIIlIIIIIlII = (short)(lllIIlIIIIIlII + lllIIlIIIIIllI);
                NBSDecoder.setNote(lllIIlIIIIIlII, lllIIIllllIlll, lllIIlIIIIIIIl.readByte(), lllIIlIIIIIIIl.readByte(), lllIIIllllllll);
                lllIIlIIIIIIIl.readByte();
                lllIIlIIIIIIIl.readByte();
                NBSDecoder.readShort(lllIIlIIIIIIIl);
            }
        }
        for (int lllIIlIIIIIIlI = 0; lllIIlIIIIIIlI < lllIIIllllllII; ++lllIIlIIIIIIlI) {
            Layer lllIIlIIIIIIll = (Layer)lllIIIllllllll.get(lllIIlIIIIIIlI);
            if (lllIIlIIIIIIll == null) continue;
            lllIIlIIIIIIll.setName(NBSDecoder.readString(lllIIlIIIIIIIl));
            lllIIlIIIIIIIl.readByte();
            lllIIlIIIIIIll.setVolume(lllIIlIIIIIIIl.readByte());
            lllIIlIIIIIIIl.readByte();
        }
        return new Song(lllIIIlllllIII, lllIIIllllllll, (short)lllIIIllllllII, lllIIIllllllIl, lllIIIlllllIll, lllIIIlllllIlI, lllIIIlllllIIl, lllIIIllllIlIl);
    }

    private static Song parse(InputStream lllIIlIlIIIIll, File lllIIlIlIIIIII) {
        try {
            DataInputStream lllIIlIlIIIlll = new DataInputStream(lllIIlIlIIIIll);
            short lllIIlIlIIIllI = NBSDecoder.readShort(lllIIlIlIIIlll);
            if (lllIIlIlIIIllI != 0) {
                return NBSDecoder.parseClassic(lllIIlIlIIIlll, lllIIlIlIIIIII, lllIIlIlIIIllI);
            }
            return NBSDecoder.parseOpenNBS(lllIIlIlIIIlll, lllIIlIlIIIIII);
        }
        catch (FileNotFoundException lllIIlIlIIIlIl) {
            LOG.error((Object)lllIIlIlIIIlIl.getStackTrace());
        }
        catch (IOException lllIIlIlIIIlII) {
            LOG.error((Object)lllIIlIlIIIlII.getStackTrace());
        }
        return null;
    }

    private static Song parseClassic(DataInputStream lllIIlIIlIIIIl, File lllIIlIIlIIIII, short lllIIlIIlIlIIl) throws IOException {
        short lllIIlIIlIllll;
        HashMap<Integer, Layer> lllIIlIIlIlIII = new HashMap<Integer, Layer>();
        int lllIIlIIlIIlll = NBSDecoder.readShort(lllIIlIIlIIIIl);
        String lllIIlIIlIIllI = NBSDecoder.readString(lllIIlIIlIIIIl);
        String lllIIlIIlIIlIl = NBSDecoder.readString(lllIIlIIlIIIIl);
        NBSDecoder.readString(lllIIlIIlIIIIl);
        String lllIIlIIlIIlII = NBSDecoder.readString(lllIIlIIlIIIIl);
        float lllIIlIIlIIIll = (float)NBSDecoder.readShort(lllIIlIIlIIIIl) / 100.0f;
        lllIIlIIlIIIIl.readBoolean();
        lllIIlIIlIIIIl.readByte();
        lllIIlIIlIIIIl.readByte();
        NBSDecoder.readInt(lllIIlIIlIIIIl);
        NBSDecoder.readInt(lllIIlIIlIIIIl);
        NBSDecoder.readInt(lllIIlIIlIIIIl);
        NBSDecoder.readInt(lllIIlIIlIIIIl);
        NBSDecoder.readInt(lllIIlIIlIIIIl);
        NBSDecoder.readString(lllIIlIIlIIIIl);
        int lllIIlIIlIIIlI = -1;
        while ((lllIIlIIlIllll = NBSDecoder.readShort(lllIIlIIlIIIIl)) != 0) {
            short lllIIlIIllIIII;
            lllIIlIIlIIIlI = (short)(lllIIlIIlIIIlI + lllIIlIIlIllll);
            int lllIIlIIlIlllI = -1;
            while ((lllIIlIIllIIII = NBSDecoder.readShort(lllIIlIIlIIIIl)) != 0) {
                lllIIlIIlIlllI = (short)(lllIIlIIlIlllI + lllIIlIIllIIII);
                NBSDecoder.setNote(lllIIlIIlIlllI, lllIIlIIlIIIlI, lllIIlIIlIIIIl.readByte(), lllIIlIIlIIIIl.readByte(), lllIIlIIlIlIII);
            }
        }
        for (int lllIIlIIlIllII = 0; lllIIlIIlIllII < lllIIlIIlIIlll; ++lllIIlIIlIllII) {
            Layer lllIIlIIlIllIl = (Layer)lllIIlIIlIlIII.get(lllIIlIIlIllII);
            if (lllIIlIIlIllIl == null) continue;
            lllIIlIIlIllIl.setName(NBSDecoder.readString(lllIIlIIlIIIIl));
            lllIIlIIlIllIl.setVolume(lllIIlIIlIIIIl.readByte());
        }
        return new Song(lllIIlIIlIIIll, lllIIlIIlIlIII, (short)lllIIlIIlIIlll, lllIIlIIlIlIIl, lllIIlIIlIIllI, lllIIlIIlIIlIl, lllIIlIIlIIlII, lllIIlIIlIIIII);
    }

    private static short readShort(DataInputStream lllIIIllIlIIII) throws IOException {
        int lllIIIllIlIIlI = lllIIIllIlIIII.readUnsignedByte();
        int lllIIIllIlIIIl = lllIIIllIlIIII.readUnsignedByte();
        return (short)(lllIIIllIlIIlI + (lllIIIllIlIIIl << 8));
    }

    static {
        LOG = LogManager.getLogger();
    }

    private static int readInt(DataInputStream lllIIIllIIlIII) throws IOException {
        int lllIIIllIIIlll = lllIIIllIIlIII.readUnsignedByte();
        int lllIIIllIIIllI = lllIIIllIIlIII.readUnsignedByte();
        int lllIIIllIIIlIl = lllIIIllIIlIII.readUnsignedByte();
        int lllIIIllIIIlII = lllIIIllIIlIII.readUnsignedByte();
        return lllIIIllIIIlll + (lllIIIllIIIllI << 8) + (lllIIIllIIIlIl << 16) + (lllIIIllIIIlII << 24);
    }

    private static void setNote(int lllIIIllIlllII, int lllIIIllIllIll, byte lllIIIlllIIIII, byte lllIIIllIllIIl, HashMap<Integer, Layer> lllIIIllIllIII) {
        Layer lllIIIllIlllIl = lllIIIllIllIII.get(lllIIIllIlllII);
        if (lllIIIllIlllIl == null) {
            lllIIIllIlllIl = new Layer();
            lllIIIllIllIII.put(lllIIIllIlllII, lllIIIllIlllIl);
        }
        lllIIIllIlllIl.setNote(lllIIIllIllIll, new Note(lllIIIlllIIIII, lllIIIllIllIIl));
    }

    public static Song parse(File lllIIlIlIlIIIl) {
        try {
            return NBSDecoder.parse(new FileInputStream(lllIIlIlIlIIIl), lllIIlIlIlIIIl);
        }
        catch (FileNotFoundException lllIIlIlIlIIlI) {
            lllIIlIlIlIIlI.printStackTrace();
            return null;
        }
    }
}

