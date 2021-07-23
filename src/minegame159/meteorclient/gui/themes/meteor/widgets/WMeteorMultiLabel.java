/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WMultiLabel;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorMultiLabel
extends WMultiLabel
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        double d4 = this.theme.textHeight(this.title);
        Color color = this.theme().textColor.get();
        for (int i = 0; i < this.lines.size(); ++i) {
            guiRenderer.text((String)this.lines.get(i), this.x, this.y + d4 * (double)i, color, false);
            if (-1 < 2) continue;
            return;
        }
    }

    public WMeteorMultiLabel(String string, boolean bl, double d) {
        super(string, bl, d);
    }
}

