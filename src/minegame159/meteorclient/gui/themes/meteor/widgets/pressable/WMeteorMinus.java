/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.pressable;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;

public class WMeteorMinus
extends WMinus
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        double d4 = this.pad();
        double d5 = this.theme.scale(3.0);
        this.renderBackground(guiRenderer, this, this.pressed, this.mouseOver);
        guiRenderer.quad(this.x + d4, this.y + this.height / 2.0 - d5 / 2.0, this.width - d4 * 2.0, d5, this.theme().minusColor.get());
    }
}

