/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.systems.modules.render.hud;

import java.util.ArrayList;
import java.util.List;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.utils.render.color.Color;

public class HudRenderer {
    private final List<Runnable> postTasks = new ArrayList<Runnable>();
    public double delta;

    public double textWidth(String string) {
        return TextRenderer.get().getWidth(string);
    }

    public double textHeight() {
        return TextRenderer.get().getHeight();
    }

    public void end() {
        TextRenderer.get().end();
        for (Runnable runnable : this.postTasks) {
            runnable.run();
        }
        this.postTasks.clear();
    }

    public void addPostTask(Runnable runnable) {
        this.postTasks.add(runnable);
    }

    public void begin(double d, double d2, boolean bl) {
        TextRenderer.get().begin(d, bl, false);
        this.delta = d2;
    }

    public void text(String string, double d, double d2, Color color) {
        TextRenderer.get().render(string, d, d2, color, true);
    }
}

