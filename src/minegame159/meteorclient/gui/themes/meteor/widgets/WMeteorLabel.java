/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WLabel;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorLabel
extends WLabel
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        if (!this.text.isEmpty()) {
            guiRenderer.text(this.text, this.x, this.y, this.color != null ? this.color : (this.title ? (Color)this.theme().titleTextColor.get() : (Color)this.theme().textColor.get()), this.title);
        }
    }

    public WMeteorLabel(String string, boolean bl) {
        super(string, bl);
    }
}

