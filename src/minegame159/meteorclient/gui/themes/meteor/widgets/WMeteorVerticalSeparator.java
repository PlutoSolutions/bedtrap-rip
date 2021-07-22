/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WVerticalSeparator;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorVerticalSeparator
extends WVerticalSeparator
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        Color color = meteorGuiTheme.separatorEdges.get();
        Color color2 = meteorGuiTheme.separatorCenter.get();
        double d4 = meteorGuiTheme.scale(1.0);
        double d5 = Math.round(this.width / 2.0);
        guiRenderer.quad(this.x + d5, this.y, d4, this.height / 2.0, color, color, color2, color2);
        guiRenderer.quad(this.x + d5, this.y + this.height / 2.0, d4, this.height / 2.0, color2, color2, color, color);
    }
}

