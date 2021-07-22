/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.notebot.nbs;

import java.util.HashMap;
import minegame159.meteorclient.utils.notebot.nbs.Note;

public class Layer {
    private HashMap<Integer, Note> hashMap = new HashMap();
    private String name = "";
    private byte volume = (byte)100;

    public Note getNote(int n) {
        return this.hashMap.get(n);
    }

    public void setHashMap(HashMap<Integer, Note> hashMap) {
        this.hashMap = hashMap;
    }

    public byte getVolume() {
        return this.volume;
    }

    public void setNote(int n, Note note) {
        this.hashMap.put(n, note);
    }

    public void setName(String string) {
        this.name = string;
    }

    public void setVolume(byte by) {
        this.volume = by;
    }

    public String getName() {
        return this.name;
    }

    public HashMap<Integer, Note> getHashMap() {
        return this.hashMap;
    }
}

