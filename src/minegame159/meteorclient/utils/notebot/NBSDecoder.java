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
    public static final Logger LOG = LogManager.getLogger();

    private static String readString(DataInputStream dataInputStream) throws IOException {
        int n;
        StringBuilder stringBuilder = new StringBuilder(n);
        for (n = NBSDecoder.readInt(dataInputStream); n > 0; --n) {
            char c = (char)dataInputStream.readByte();
            if (c == '\r') {
                c = ' ';
            }
            stringBuilder.append(c);
            if (null == null) continue;
            return null;
        }
        return String.valueOf(stringBuilder);
    }

    public static Song parse(InputStream inputStream) {
        return NBSDecoder.parse(inputStream, null);
    }

    private static Song parseOpenNBS(DataInputStream dataInputStream, File file) throws IOException {
        int n;
        HashMap<Integer, Layer> hashMap = new HashMap<Integer, Layer>();
        byte by = dataInputStream.readByte();
        if (by != 5) {
            return null;
        }
        dataInputStream.readByte();
        short s = NBSDecoder.readShort(dataInputStream);
        int n2 = NBSDecoder.readShort(dataInputStream);
        String string = NBSDecoder.readString(dataInputStream);
        String string2 = NBSDecoder.readString(dataInputStream);
        NBSDecoder.readString(dataInputStream);
        String string3 = NBSDecoder.readString(dataInputStream);
        float f = (float)NBSDecoder.readShort(dataInputStream) / 100.0f;
        dataInputStream.readByte();
        dataInputStream.readByte();
        dataInputStream.readByte();
        NBSDecoder.readInt(dataInputStream);
        NBSDecoder.readInt(dataInputStream);
        NBSDecoder.readInt(dataInputStream);
        NBSDecoder.readInt(dataInputStream);
        NBSDecoder.readInt(dataInputStream);
        NBSDecoder.readString(dataInputStream);
        dataInputStream.readByte();
        dataInputStream.readByte();
        NBSDecoder.readShort(dataInputStream);
        int n3 = -1;
        while ((n = NBSDecoder.readShort(dataInputStream)) != 0) {
            short s2;
            n3 = (short)(n3 + n);
            int n4 = -1;
            while ((s2 = NBSDecoder.readShort(dataInputStream)) != 0) {
                n4 = (short)(n4 + s2);
                NBSDecoder.setNote(n4, n3, dataInputStream.readByte(), dataInputStream.readByte(), hashMap);
                dataInputStream.readByte();
                dataInputStream.readByte();
                NBSDecoder.readShort(dataInputStream);
            }
        }
        for (n = 0; n < n2; ++n) {
            Layer layer = (Layer)hashMap.get(n);
            if (layer == null) continue;
            layer.setName(NBSDecoder.readString(dataInputStream));
            dataInputStream.readByte();
            layer.setVolume(dataInputStream.readByte());
            dataInputStream.readByte();
            if (0 >= -1) continue;
            return null;
        }
        return new Song(f, hashMap, (short)n2, s, string, string2, string3, file);
    }

    private static Song parse(InputStream inputStream, File file) {
        DataInputStream dataInputStream;
        block4: {
            dataInputStream = new DataInputStream(inputStream);
            short s = NBSDecoder.readShort(dataInputStream);
            if (s == 0) break block4;
            return NBSDecoder.parseClassic(dataInputStream, file, s);
        }
        try {
            return NBSDecoder.parseOpenNBS(dataInputStream, file);
        }
        catch (FileNotFoundException fileNotFoundException) {
            LOG.error((Object)fileNotFoundException.getStackTrace());
        }
        catch (IOException iOException) {
            LOG.error((Object)iOException.getStackTrace());
        }
        return null;
    }

    private static Song parseClassic(DataInputStream dataInputStream, File file, short s) throws IOException {
        int n;
        HashMap<Integer, Layer> hashMap = new HashMap<Integer, Layer>();
        int n2 = NBSDecoder.readShort(dataInputStream);
        String string = NBSDecoder.readString(dataInputStream);
        String string2 = NBSDecoder.readString(dataInputStream);
        NBSDecoder.readString(dataInputStream);
        String string3 = NBSDecoder.readString(dataInputStream);
        float f = (float)NBSDecoder.readShort(dataInputStream) / 100.0f;
        dataInputStream.readBoolean();
        dataInputStream.readByte();
        dataInputStream.readByte();
        NBSDecoder.readInt(dataInputStream);
        NBSDecoder.readInt(dataInputStream);
        NBSDecoder.readInt(dataInputStream);
        NBSDecoder.readInt(dataInputStream);
        NBSDecoder.readInt(dataInputStream);
        NBSDecoder.readString(dataInputStream);
        int n3 = -1;
        while ((n = NBSDecoder.readShort(dataInputStream)) != 0) {
            short s2;
            n3 = (short)(n3 + n);
            int n4 = -1;
            while ((s2 = NBSDecoder.readShort(dataInputStream)) != 0) {
                n4 = (short)(n4 + s2);
                NBSDecoder.setNote(n4, n3, dataInputStream.readByte(), dataInputStream.readByte(), hashMap);
            }
        }
        for (n = 0; n < n2; ++n) {
            Layer layer = (Layer)hashMap.get(n);
            if (layer == null) continue;
            layer.setName(NBSDecoder.readString(dataInputStream));
            layer.setVolume(dataInputStream.readByte());
            if (!false) continue;
            return null;
        }
        return new Song(f, hashMap, (short)n2, s, string, string2, string3, file);
    }

    private static short readShort(DataInputStream dataInputStream) throws IOException {
        int n = dataInputStream.readUnsignedByte();
        int n2 = dataInputStream.readUnsignedByte();
        return (short)(n + (n2 << 8));
    }

    private static int readInt(DataInputStream dataInputStream) throws IOException {
        int n = dataInputStream.readUnsignedByte();
        int n2 = dataInputStream.readUnsignedByte();
        int n3 = dataInputStream.readUnsignedByte();
        int n4 = dataInputStream.readUnsignedByte();
        return n + (n2 << 8) + (n3 << 16) + (n4 << 24);
    }

    private static void setNote(int n, int n2, byte by, byte by2, HashMap<Integer, Layer> hashMap) {
        Layer layer = hashMap.get(n);
        if (layer == null) {
            layer = new Layer();
            hashMap.put(n, layer);
        }
        layer.setNote(n2, new Note(by, by2));
    }

    public static Song parse(File file) {
        try {
            return NBSDecoder.parse(new FileInputStream(file), file);
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            return null;
        }
    }
}

