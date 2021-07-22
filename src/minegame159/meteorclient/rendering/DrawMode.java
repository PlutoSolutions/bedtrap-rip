/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.rendering;

public enum DrawMode {
    Triangles,
    Lines,
    Quads;


    private DrawMode() {
        DrawMode lllllllllllllllllIlIIIIlIlIlIIIl;
    }

    public int toOpenGl() {
        DrawMode lllllllllllllllllIlIIIIlIlIIlIII;
        if (lllllllllllllllllIlIIIIlIlIIlIII == Triangles) {
            return 4;
        }
        if (lllllllllllllllllIlIIIIlIlIIlIII == Quads) {
            return 7;
        }
        return 1;
    }
}

