/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.utils.misc.input;

public enum KeyAction {
    Press,
    Repeat,
    Release;


    private KeyAction() {
        KeyAction llllllllllllllllllIlllllIIlIlIIl;
    }

    public static KeyAction get(int llllllllllllllllllIlllllIIlIIIll) {
        if (llllllllllllllllllIlllllIIlIIIll == 1) {
            return Press;
        }
        if (llllllllllllllllllIlllllIIlIIIll == 0) {
            return Release;
        }
        return Repeat;
    }
}

