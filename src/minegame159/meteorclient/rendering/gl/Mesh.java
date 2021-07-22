/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL30C
 */
package minegame159.meteorclient.rendering.gl;

import org.lwjgl.opengl.GL30C;

public class Mesh {
    private final /* synthetic */ int ibo;
    private final /* synthetic */ int vao;
    private final /* synthetic */ int indicesCount;
    private final /* synthetic */ int vbo;

    public void bind() {
        Mesh lllllllllllllllllIllIlIIIlIllIII;
        GL30C.glBindVertexArray((int)lllllllllllllllllIllIlIIIlIllIII.vao);
        GL30C.glBindBuffer((int)34963, (int)lllllllllllllllllIllIlIIIlIllIII.ibo);
    }

    public void render() {
        Mesh lllllllllllllllllIllIlIIIlIlIIlI;
        lllllllllllllllllIllIlIIIlIlIIlI.bind();
        lllllllllllllllllIllIlIIIlIlIIlI.renderMesh();
        lllllllllllllllllIllIlIIIlIlIIlI.unbind();
    }

    public Mesh(float[] lllllllllllllllllIllIlIIIllIIIlI, int[] lllllllllllllllllIllIlIIIllIIIIl, int ... lllllllllllllllllIllIlIIIllIIIII) {
        Mesh lllllllllllllllllIllIlIIIllIIIll;
        lllllllllllllllllIllIlIIIllIIIll.vao = GL30C.glGenVertexArrays();
        GL30C.glBindVertexArray((int)lllllllllllllllllIllIlIIIllIIIll.vao);
        lllllllllllllllllIllIlIIIllIIIll.vbo = GL30C.glGenBuffers();
        GL30C.glBindBuffer((int)34962, (int)lllllllllllllllllIllIlIIIllIIIll.vbo);
        GL30C.glBufferData((int)34962, (float[])lllllllllllllllllIllIlIIIllIIIlI, (int)35044);
        int lllllllllllllllllIllIlIIIllIIlIl = 0;
        for (int lllllllllllllllllIllIlIIIllIlIll : lllllllllllllllllIllIlIIIllIIIII) {
            lllllllllllllllllIllIlIIIllIIlIl += lllllllllllllllllIllIlIIIllIlIll * 4;
        }
        int lllllllllllllllllIllIlIIIllIIlII = 0;
        for (int lllllllllllllllllIllIlIIIllIlIlI = 0; lllllllllllllllllIllIlIIIllIlIlI < lllllllllllllllllIllIlIIIllIIIII.length; ++lllllllllllllllllIllIlIIIllIlIlI) {
            GL30C.glEnableVertexAttribArray((int)lllllllllllllllllIllIlIIIllIlIlI);
            GL30C.glVertexAttribPointer((int)lllllllllllllllllIllIlIIIllIlIlI, (int)lllllllllllllllllIllIlIIIllIIIII[lllllllllllllllllIllIlIIIllIlIlI], (int)5126, (boolean)false, (int)lllllllllllllllllIllIlIIIllIIlIl, (long)lllllllllllllllllIllIlIIIllIIlII);
            lllllllllllllllllIllIlIIIllIIlII += lllllllllllllllllIllIlIIIllIIIII[lllllllllllllllllIllIlIIIllIlIlI] * 4;
        }
        lllllllllllllllllIllIlIIIllIIIll.ibo = GL30C.glGenBuffers();
        GL30C.glBindBuffer((int)34963, (int)lllllllllllllllllIllIlIIIllIIIll.ibo);
        GL30C.glBufferData((int)34963, (int[])lllllllllllllllllIllIlIIIllIIIIl, (int)35044);
        lllllllllllllllllIllIlIIIllIIIll.indicesCount = lllllllllllllllllIllIlIIIllIIIIl.length;
        GL30C.glBindVertexArray((int)0);
        GL30C.glBindBuffer((int)34962, (int)0);
        GL30C.glBindBuffer((int)34963, (int)0);
    }

    public void unbind() {
        GL30C.glBindVertexArray((int)0);
        GL30C.glBindBuffer((int)34963, (int)0);
    }

    public void renderMesh() {
        Mesh lllllllllllllllllIllIlIIIlIlIllI;
        GL30C.glDrawElements((int)4, (int)lllllllllllllllllIllIlIIIlIlIllI.indicesCount, (int)5125, (long)0L);
    }
}

