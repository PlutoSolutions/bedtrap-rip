/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.rendering;

import minegame159.meteorclient.gui.GuiThemes;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.rendering.gl.PostProcessRenderer;
import minegame159.meteorclient.rendering.gl.Shader;
import minegame159.meteorclient.utils.Utils;
import net.minecraft.class_276;
import net.minecraft.class_4493;

public class Blur {
    private static Shader shaderUp;
    private static final int OFFSET;
    private static Shader shaderDown;
    private static final int ITERATIONS;

    public static void init() {
        shaderDown = new Shader("shaders/simple.vert", "shaders/kawase_down.frag");
        shaderUp = new Shader("shaders/simple.vert", "shaders/kawase_up.frag");
    }

    static {
        ITERATIONS = 4;
        OFFSET = 4;
    }

    public static void render() {
        int n;
        if (!GuiThemes.get().blur() || !(Utils.mc.field_1755 instanceof WidgetScreen)) {
            return;
        }
        class_276 class_2762 = Utils.mc.method_1522();
        class_4493.method_22077((int)33984);
        class_4493.method_22081((int)class_2762.method_30277());
        PostProcessRenderer.begin();
        shaderDown.bind();
        shaderDown.set("u_Texture", 0);
        shaderDown.set("u_Size", class_2762.field_1482, class_2762.field_1481);
        shaderDown.set("u_Offset", 4.0f, 4.0f);
        shaderDown.set("u_HalfPixel", 0.5f / (float)class_2762.field_1482, 0.5f / (float)class_2762.field_1481);
        for (n = 0; n < 4; ++n) {
            PostProcessRenderer.renderMesh();
            if (2 != 4) continue;
            return;
        }
        shaderUp.bind();
        shaderUp.set("u_Texture", 0);
        shaderUp.set("u_Size", class_2762.field_1482, class_2762.field_1481);
        shaderUp.set("u_Offset", 4.0f, 4.0f);
        shaderUp.set("u_HalfPixel", 0.5f / (float)class_2762.field_1482, 0.5f / (float)class_2762.field_1481);
        shaderUp.set("u_Alpha", 1.0f);
        for (n = 0; n < 4; ++n) {
            if (n == 3) {
                shaderUp.set("u_Alpha", (float)((WidgetScreen)Utils.mc.field_1755).animProgress);
            }
            PostProcessRenderer.renderMesh();
            if (null == null) continue;
            return;
        }
        shaderUp.unbind();
        class_4493.method_22081((int)0);
        PostProcessRenderer.end();
    }
}

