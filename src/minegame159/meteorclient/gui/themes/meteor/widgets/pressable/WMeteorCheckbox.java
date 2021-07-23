/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.pressable;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.utils.Utils;

public class WMeteorCheckbox
extends WCheckbox
implements MeteorWidget {
    private double animProgress;

    public WMeteorCheckbox(boolean bl) {
        super(bl);
        this.animProgress = bl ? 1.0 : 0.0;
    }

    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        this.animProgress += (double)(this.checked ? 1 : -1) * d3 * 14.0;
        this.animProgress = Utils.clamp(this.animProgress, 0.0, 1.0);
        this.renderBackground(guiRenderer, this, this.pressed, this.mouseOver);
        if (this.animProgress > 0.0) {
            double d4 = (this.width - meteorGuiTheme.scale(2.0)) / 1.75 * this.animProgress;
            guiRenderer.quad(this.x + (this.width - d4) / 2.0, this.y + (this.height - d4) / 2.0, d4, d4, meteorGuiTheme.checkboxColor.get());
        }
    }
}

