/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.notebot.nbs;

import java.util.HashMap;
import minegame159.meteorclient.utils.notebot.nbs.Note;

public class Layer {
    private /* synthetic */ HashMap<Integer, Note> hashMap;
    private /* synthetic */ String name;
    private /* synthetic */ byte volume;

    public Note getNote(int lIIlIllllIllI) {
        Layer lIIlIllllIlll;
        return lIIlIllllIlll.hashMap.get(lIIlIllllIllI);
    }

    public void setHashMap(HashMap<Integer, Note> lIIllIIIIIIll) {
        lIIllIIIIIllI.hashMap = lIIllIIIIIIll;
    }

    public byte getVolume() {
        Layer lIIlIlllIlIII;
        return lIIlIlllIlIII.volume;
    }

    public void setNote(int lIIlIlllIllII, Note lIIlIlllIlllI) {
        Layer lIIlIllllIIII;
        lIIlIllllIIII.hashMap.put(lIIlIlllIllII, lIIlIlllIlllI);
    }

    public void setName(String lIIlIllllllII) {
        lIIlIllllllIl.name = lIIlIllllllII;
    }

    public void setVolume(byte lIIlIlllIIlII) {
        lIIlIlllIIIll.volume = lIIlIlllIIlII;
    }

    public String getName() {
        Layer lIIllIIIIIIIl;
        return lIIllIIIIIIIl.name;
    }

    public HashMap<Integer, Note> getHashMap() {
        Layer lIIllIIIIlIIl;
        return lIIllIIIIlIIl.hashMap;
    }

    public Layer() {
        Layer lIIllIIIIllIl;
        lIIllIIIIllIl.hashMap = new HashMap();
        lIIllIIIIllIl.volume = (byte)100;
        lIIllIIIIllIl.name = "";
    }
}

