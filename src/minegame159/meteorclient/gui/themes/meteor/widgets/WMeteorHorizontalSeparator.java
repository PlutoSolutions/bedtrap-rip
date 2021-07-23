/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WHorizontalSeparator;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorHorizontalSeparator
extends WHorizontalSeparator
implements MeteorWidget {
    public WMeteorHorizontalSeparator(String string) {
        super(string);
    }

    private void renderWithoutText(GuiRenderer guiRenderer) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        double d = meteorGuiTheme.scale(1.0);
        double d2 = this.width / 2.0;
        guiRenderer.quad(this.x, this.y + d, d2, d, meteorGuiTheme.separatorEdges.get(), (Color)meteorGuiTheme.separatorCenter.get());
        guiRenderer.quad(this.x + d2, this.y + d, d2, d, meteorGuiTheme.separatorCenter.get(), (Color)meteorGuiTheme.separatorEdges.get());
    }

    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        if (this.text == null) {
            this.renderWithoutText(guiRenderer);
        } else {
            this.renderWithText(guiRenderer);
        }
    }

    private void renderWithText(GuiRenderer guiRenderer) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        double d = meteorGuiTheme.scale(2.0);
        double d2 = meteorGuiTheme.scale(1.0);
        double d3 = Math.round(this.width / 2.0 - this.textWidth / 2.0 - d);
        double d4 = d + d3 + this.textWidth + d;
        double d5 = Math.round(this.height / 2.0);
        guiRenderer.quad(this.x, this.y + d5, d3, d2, meteorGuiTheme.separatorEdges.get(), (Color)meteorGuiTheme.separatorCenter.get());
        guiRenderer.text(this.text, this.x + d3 + d, this.y, meteorGuiTheme.separatorText.get(), false);
        guiRenderer.quad(this.x + d4, this.y + d5, this.width - d4, d2, meteorGuiTheme.separatorCenter.get(), (Color)meteorGuiTheme.separatorEdges.get());
    }
}

