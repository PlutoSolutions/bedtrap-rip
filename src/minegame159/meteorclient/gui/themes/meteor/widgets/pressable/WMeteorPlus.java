/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.pressable;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.pressable.WPlus;

public class WMeteorPlus
extends WPlus
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        double d4 = this.pad();
        double d5 = meteorGuiTheme.scale(3.0);
        this.renderBackground(guiRenderer, this, this.pressed, this.mouseOver);
        guiRenderer.quad(this.x + d4, this.y + this.height / 2.0 - d5 / 2.0, this.width - d4 * 2.0, d5, meteorGuiTheme.plusColor.get());
        guiRenderer.quad(this.x + this.width / 2.0 - d5 / 2.0, this.y + d4, d5, this.height - d4 * 2.0, meteorGuiTheme.plusColor.get());
    }
}

