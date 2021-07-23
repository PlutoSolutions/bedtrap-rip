/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.misc.input;

public enum KeyAction {
    Press,
    Repeat,
    Release;


    public static KeyAction get(int n) {
        if (n == 1) {
            return Press;
        }
        if (n == 0) {
            return Release;
        }
        return Repeat;
    }
}

