/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.rendering;

public enum DrawMode {
    Triangles,
    Lines,
    Quads;


    public int toOpenGl() {
        if (this == Triangles) {
            return 4;
        }
        if (this == Quads) {
            return 7;
        }
        return 1;
    }
}

