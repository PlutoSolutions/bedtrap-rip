/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL30C
 */
package minegame159.meteorclient.rendering.gl;

import org.lwjgl.opengl.GL30C;

public class Mesh {
    private final int ibo;
    private final int vao = GL30C.glGenVertexArrays();
    private final int indicesCount;
    private final int vbo;

    public void bind() {
        GL30C.glBindVertexArray((int)this.vao);
        GL30C.glBindBuffer((int)34963, (int)this.ibo);
    }

    public void render() {
        this.bind();
        this.renderMesh();
        this.unbind();
    }

    public Mesh(float[] arrf, int[] arrn, int ... arrn2) {
        GL30C.glBindVertexArray((int)this.vao);
        this.vbo = GL30C.glGenBuffers();
        GL30C.glBindBuffer((int)34962, (int)this.vbo);
        GL30C.glBufferData((int)34962, (float[])arrf, (int)35044);
        int n = 0;
        int[] arrn3 = arrn2;
        int n2 = arrn3.length;
        for (int i = 0; i < n2; ++i) {
            int n3 = arrn3[i];
            n += n3 * 4;
        }
        int n4 = 0;
        for (n2 = 0; n2 < arrn2.length; ++n2) {
            GL30C.glEnableVertexAttribArray((int)n2);
            GL30C.glVertexAttribPointer((int)n2, (int)arrn2[n2], (int)5126, (boolean)false, (int)n, (long)n4);
            n4 += arrn2[n2] * 4;
            if (-3 < 0) continue;
            throw null;
        }
        this.ibo = GL30C.glGenBuffers();
        GL30C.glBindBuffer((int)34963, (int)this.ibo);
        GL30C.glBufferData((int)34963, (int[])arrn, (int)35044);
        this.indicesCount = arrn.length;
        GL30C.glBindVertexArray((int)0);
        GL30C.glBindBuffer((int)34962, (int)0);
        GL30C.glBindBuffer((int)34963, (int)0);
    }

    public void unbind() {
        GL30C.glBindVertexArray((int)0);
        GL30C.glBindBuffer((int)34963, (int)0);
    }

    public void renderMesh() {
        GL30C.glDrawElements((int)4, (int)this.indicesCount, (int)5125, (long)0L);
    }
}

