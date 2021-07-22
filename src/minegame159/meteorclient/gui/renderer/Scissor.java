/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package minegame159.meteorclient.gui.renderer;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.utils.Utils;
import org.lwjgl.opengl.GL11;

public class Scissor {
    public /* synthetic */ int y;
    public /* synthetic */ int x;
    public /* synthetic */ int height;
    public /* synthetic */ int width;
    public final /* synthetic */ List<Runnable> postTasks;

    public Scissor set(double llllllllllllllllIlIllllllllIIlll, double llllllllllllllllIlIllllllllIlIll, double llllllllllllllllIlIllllllllIlIlI, double llllllllllllllllIlIllllllllIIlII) {
        Scissor llllllllllllllllIlIllllllllIllIl;
        if (llllllllllllllllIlIllllllllIlIlI < 0.0) {
            llllllllllllllllIlIllllllllIlIlI = 0.0;
        }
        if (llllllllllllllllIlIllllllllIIlII < 0.0) {
            llllllllllllllllIlIllllllllIIlII = 0.0;
        }
        llllllllllllllllIlIllllllllIllIl.x = (int)Math.round(llllllllllllllllIlIllllllllIIlll);
        llllllllllllllllIlIllllllllIllIl.y = (int)Math.round(llllllllllllllllIlIllllllllIlIll);
        llllllllllllllllIlIllllllllIllIl.width = (int)Math.round(llllllllllllllllIlIllllllllIlIlI);
        llllllllllllllllIlIllllllllIllIl.height = (int)Math.round(llllllllllllllllIlIllllllllIIlII);
        llllllllllllllllIlIllllllllIllIl.postTasks.clear();
        return llllllllllllllllIlIllllllllIllIl;
    }

    public void apply() {
        Scissor llllllllllllllllIlIllllllllIIIIl;
        GL11.glScissor((int)llllllllllllllllIlIllllllllIIIIl.x, (int)(Utils.getWindowHeight() - llllllllllllllllIlIllllllllIIIIl.y - llllllllllllllllIlIllllllllIIIIl.height), (int)llllllllllllllllIlIllllllllIIIIl.width, (int)llllllllllllllllIlIllllllllIIIIl.height);
    }

    public Scissor() {
        Scissor llllllllllllllllIlIlllllllllIlII;
        llllllllllllllllIlIlllllllllIlII.postTasks = new ArrayList<Runnable>();
    }
}

