/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.pressable;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.renderer.packer.GuiTexture;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorButton
extends WButton
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        double d4 = this.pad();
        this.renderBackground(guiRenderer, this, this.pressed, this.mouseOver);
        if (this.text != null) {
            guiRenderer.text(this.text, this.x + this.width / 2.0 - this.textWidth / 2.0, this.y + d4, meteorGuiTheme.textColor.get(), false);
        } else {
            double d5 = meteorGuiTheme.textHeight();
            guiRenderer.quad(this.x + this.width / 2.0 - d5 / 2.0, this.y + d4, d5, d5, this.texture, (Color)meteorGuiTheme.textColor.get());
        }
    }

    public WMeteorButton(String string, GuiTexture guiTexture) {
        super(string, guiTexture);
    }
}

