/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.input;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.input.WSlider;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorSlider
extends WSlider
implements MeteorWidget {
    private void renderBar(GuiRenderer guiRenderer, double d) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        double d2 = meteorGuiTheme.scale(3.0);
        double d3 = this.handleSize();
        double d4 = this.x + d3 / 2.0;
        double d5 = this.y + this.height / 2.0 - d2 / 2.0;
        guiRenderer.quad(d4, d5, d, d2, meteorGuiTheme.sliderLeft.get());
        guiRenderer.quad(d4 + d, d5, this.width - d - d3, d2, meteorGuiTheme.sliderRight.get());
    }

    public WMeteorSlider(double d, double d2, double d3) {
        super(d, d2, d3);
    }

    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        double d4 = this.valueWidth();
        this.renderBar(guiRenderer, d4);
        this.renderHandle(guiRenderer, d4);
    }

    private void renderHandle(GuiRenderer guiRenderer, double d) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        double d2 = this.handleSize();
        guiRenderer.quad(this.x + d, this.y, d2, d2, GuiRenderer.CIRCLE, (Color)meteorGuiTheme.sliderHandle.get(this.dragging, this.handleMouseOver));
    }
}

