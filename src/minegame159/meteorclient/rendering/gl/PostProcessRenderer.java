/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.rendering.gl;

import com.mojang.blaze3d.systems.RenderSystem;
import minegame159.meteorclient.rendering.gl.Mesh;
import net.minecraft.class_4493;
import org.lwjgl.opengl.GL11;

public class PostProcessRenderer {
    private static Mesh mesh;

    public static void begin() {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc((class_4493.class_4535)class_4493.class_4535.field_22541, (class_4493.class_4534)class_4493.class_4534.field_22523);
        RenderSystem.disableDepthTest();
        RenderSystem.disableTexture();
        RenderSystem.disableLighting();
        RenderSystem.disableCull();
        RenderSystem.disableAlphaTest();
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glMatrixMode((int)5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        mesh.bind();
    }

    public static void render() {
        PostProcessRenderer.begin();
        PostProcessRenderer.renderMesh();
        PostProcessRenderer.end();
    }

    public static void init() {
        float[] fArray = new float[]{-1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f};
        int[] nArray = new int[]{0, 1, 2, 2, 3, 0};
        mesh = new Mesh(fArray, nArray, 2);
    }

    public static void end() {
        mesh.unbind();
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        GL11.glPopMatrix();
        RenderSystem.enableDepthTest();
    }

    public static void renderMesh() {
        mesh.renderMesh();
    }
}

