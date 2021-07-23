/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.renderer;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.utils.Utils;
import org.lwjgl.opengl.GL11;

public class Scissor {
    public int y;
    public int x;
    public int height;
    public int width;
    public final List<Runnable> postTasks = new ArrayList<Runnable>();

    public Scissor set(double d, double d2, double d3, double d4) {
        if (d3 < 0.0) {
            d3 = 0.0;
        }
        if (d4 < 0.0) {
            d4 = 0.0;
        }
        this.x = (int)Math.round(d);
        this.y = (int)Math.round(d2);
        this.width = (int)Math.round(d3);
        this.height = (int)Math.round(d4);
        this.postTasks.clear();
        return this;
    }

    public void apply() {
        GL11.glScissor((int)this.x, (int)(Utils.getWindowHeight() - this.y - this.height), (int)this.width, (int)this.height);
    }
}

