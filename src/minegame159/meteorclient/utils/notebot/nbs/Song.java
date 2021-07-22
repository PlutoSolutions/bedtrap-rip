/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.notebot.nbs;

import java.io.File;
import java.util.HashMap;
import minegame159.meteorclient.utils.notebot.nbs.Layer;

public class Song {
    private final /* synthetic */ short songHeight;
    private final /* synthetic */ File path;
    private final /* synthetic */ short length;
    private final /* synthetic */ String title;
    private final /* synthetic */ String author;
    private final /* synthetic */ float speed;
    private /* synthetic */ HashMap<Integer, Layer> layerHashMap;
    private final /* synthetic */ String description;
    private final /* synthetic */ float delay;

    public Song(Song llllllllllllllllIllIlIllIllIllII) {
        Song llllllllllllllllIllIlIllIllIlIll;
        llllllllllllllllIllIlIllIllIlIll.layerHashMap = new HashMap();
        llllllllllllllllIllIlIllIllIlIll.speed = llllllllllllllllIllIlIllIllIllII.getSpeed();
        llllllllllllllllIllIlIllIllIlIll.delay = 20.0f / llllllllllllllllIllIlIllIllIlIll.speed;
        llllllllllllllllIllIlIllIllIlIll.layerHashMap = llllllllllllllllIllIlIllIllIllII.getLayerHashMap();
        llllllllllllllllIllIlIllIllIlIll.songHeight = llllllllllllllllIllIlIllIllIllII.getSongHeight();
        llllllllllllllllIllIlIllIllIlIll.length = llllllllllllllllIllIlIllIllIllII.getLength();
        llllllllllllllllIllIlIllIllIlIll.title = llllllllllllllllIllIlIllIllIllII.getTitle();
        llllllllllllllllIllIlIllIllIlIll.author = llllllllllllllllIllIlIllIllIllII.getAuthor();
        llllllllllllllllIllIlIllIllIlIll.description = llllllllllllllllIllIlIllIllIllII.getDescription();
        llllllllllllllllIllIlIllIllIlIll.path = llllllllllllllllIllIlIllIllIllII.getPath();
    }

    public HashMap<Integer, Layer> getLayerHashMap() {
        Song llllllllllllllllIllIlIllIlIIllIl;
        return llllllllllllllllIllIlIllIlIIllIl.layerHashMap;
    }

    public File getPath() {
        Song llllllllllllllllIllIlIllIIlllllI;
        return llllllllllllllllIllIlIllIIlllllI.path;
    }

    public Song(float llllllllllllllllIllIlIllIlIlIllI, HashMap<Integer, Layer> llllllllllllllllIllIlIllIlIllllI, short llllllllllllllllIllIlIllIlIlIlII, short llllllllllllllllIllIlIllIlIlllII, String llllllllllllllllIllIlIllIlIlIIlI, String llllllllllllllllIllIlIllIlIlIIIl, String llllllllllllllllIllIlIllIlIllIIl, File llllllllllllllllIllIlIllIlIIllll) {
        Song llllllllllllllllIllIlIllIlIlIlll;
        llllllllllllllllIllIlIllIlIlIlll.layerHashMap = new HashMap();
        llllllllllllllllIllIlIllIlIlIlll.speed = llllllllllllllllIllIlIllIlIlIllI;
        llllllllllllllllIllIlIllIlIlIlll.delay = 20.0f / llllllllllllllllIllIlIllIlIlIllI;
        llllllllllllllllIllIlIllIlIlIlll.layerHashMap = llllllllllllllllIllIlIllIlIllllI;
        llllllllllllllllIllIlIllIlIlIlll.songHeight = llllllllllllllllIllIlIllIlIlIlII;
        llllllllllllllllIllIlIllIlIlIlll.length = llllllllllllllllIllIlIllIlIlllII;
        llllllllllllllllIllIlIllIlIlIlll.title = llllllllllllllllIllIlIllIlIlIIlI;
        llllllllllllllllIllIlIllIlIlIlll.author = llllllllllllllllIllIlIllIlIlIIIl;
        llllllllllllllllIllIlIllIlIlIlll.description = llllllllllllllllIllIlIllIlIllIIl;
        llllllllllllllllIllIlIllIlIlIlll.path = llllllllllllllllIllIlIllIlIIllll;
    }

    public short getLength() {
        Song llllllllllllllllIllIlIllIlIIIllI;
        return llllllllllllllllIllIlIllIlIIIllI.length;
    }

    public float getDelay() {
        Song llllllllllllllllIllIlIllIIllIlII;
        return llllllllllllllllIllIlIllIIllIlII.delay;
    }

    public String getDescription() {
        Song llllllllllllllllIllIlIllIIlllIll;
        return llllllllllllllllIllIlIllIIlllIll.description;
    }

    public float getSpeed() {
        Song llllllllllllllllIllIlIllIIllIlll;
        return llllllllllllllllIllIlIllIIllIlll.speed;
    }

    public String getAuthor() {
        Song llllllllllllllllIllIlIllIlIIIIII;
        return llllllllllllllllIllIlIllIlIIIIII.author;
    }

    public String getTitle() {
        Song llllllllllllllllIllIlIllIlIIIIll;
        return llllllllllllllllIllIlIllIlIIIIll.title;
    }

    public short getSongHeight() {
        Song llllllllllllllllIllIlIllIlIIlIIl;
        return llllllllllllllllIllIlIllIlIIlIIl.songHeight;
    }
}

