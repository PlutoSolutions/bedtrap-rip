/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.utils.render.color.Color;

public class HudRenderer {
    private final /* synthetic */ List<Runnable> postTasks;
    public /* synthetic */ double delta;

    public HudRenderer() {
        HudRenderer llllllllllllllllIlIlIIllIIIIIIIl;
        llllllllllllllllIlIlIIllIIIIIIIl.postTasks = new ArrayList<Runnable>();
    }

    public double textWidth(String llllllllllllllllIlIlIIlIllIlllIl) {
        return TextRenderer.get().getWidth(llllllllllllllllIlIlIIlIllIlllIl);
    }

    public double textHeight() {
        return TextRenderer.get().getHeight();
    }

    public void end() {
        HudRenderer llllllllllllllllIlIlIIlIllllIIII;
        TextRenderer.get().end();
        for (Runnable llllllllllllllllIlIlIIlIllllIIIl : llllllllllllllllIlIlIIlIllllIIII.postTasks) {
            llllllllllllllllIlIlIIlIllllIIIl.run();
        }
        llllllllllllllllIlIlIIlIllllIIII.postTasks.clear();
    }

    public void addPostTask(Runnable llllllllllllllllIlIlIIlIllIlIlll) {
        HudRenderer llllllllllllllllIlIlIIlIllIllIII;
        llllllllllllllllIlIlIIlIllIllIII.postTasks.add(llllllllllllllllIlIlIIlIllIlIlll);
    }

    public void begin(double llllllllllllllllIlIlIIlIllllIlll, double llllllllllllllllIlIlIIlIlllllIlI, boolean llllllllllllllllIlIlIIlIllllIlIl) {
        TextRenderer.get().begin(llllllllllllllllIlIlIIlIllllIlll, llllllllllllllllIlIlIIlIllllIlIl, false);
        llllllllllllllllIlIlIIlIllllllII.delta = llllllllllllllllIlIlIIlIlllllIlI;
    }

    public void text(String llllllllllllllllIlIlIIlIlllIIlll, double llllllllllllllllIlIlIIlIlllIIIlI, double llllllllllllllllIlIlIIlIlllIIlIl, Color llllllllllllllllIlIlIIlIlllIIlII) {
        TextRenderer.get().render(llllllllllllllllIlIlIIlIlllIIlll, llllllllllllllllIlIlIIlIlllIIIlI, llllllllllllllllIlIlIIlIlllIIlIl, llllllllllllllllIlIlIIlIlllIIlII, true);
    }
}

