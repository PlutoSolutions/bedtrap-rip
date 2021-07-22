/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.renderer;

import minegame159.meteorclient.utils.misc.Pool;
import minegame159.meteorclient.utils.render.color.Color;

public abstract class GuiRenderOperation<T extends GuiRenderOperation<T>> {
    protected /* synthetic */ double x;
    protected /* synthetic */ Color color;
    protected /* synthetic */ double y;

    public void run(Pool<T> llllllllllllllllIlIlIIlIllIIIIII) {
        GuiRenderOperation llllllllllllllllIlIlIIlIlIllllll;
        llllllllllllllllIlIlIIlIlIllllll.onRun();
        llllllllllllllllIlIlIIlIllIIIIII.free(llllllllllllllllIlIlIIlIlIllllll);
    }

    public GuiRenderOperation() {
        GuiRenderOperation llllllllllllllllIlIlIIlIllIlIIII;
    }

    protected abstract void onRun();

    public void set(double llllllllllllllllIlIlIIlIllIIlIlI, double llllllllllllllllIlIlIIlIllIIlIIl, Color llllllllllllllllIlIlIIlIllIIlIII) {
        llllllllllllllllIlIlIIlIllIIlIll.x = llllllllllllllllIlIlIIlIllIIlIlI;
        llllllllllllllllIlIlIIlIllIIlIll.y = llllllllllllllllIlIlIIlIllIIlIIl;
        llllllllllllllllIlIlIIlIllIIlIll.color = llllllllllllllllIlIlIIlIllIIlIII;
    }
}

