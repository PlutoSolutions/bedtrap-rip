/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.renderer;

import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.render.color.Color;

public abstract class GuiRenderOperation<T extends GuiRenderOperation<T>> {
    protected double x;
    protected Color color;
    protected double y;

    public void run(Pool<T> pool) {
        this.onRun();
        pool.free(this);
    }

    protected abstract void onRun();

    public void set(double d, double d2, Color color) {
        this.x = d;
        this.y = d2;
        this.color = color;
    }
}

