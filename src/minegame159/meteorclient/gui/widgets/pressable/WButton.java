/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.pressable;

import minegame159.meteorclient.gui.renderer.packer.GuiTexture;
import minegame159.meteorclient.gui.widgets.pressable.WPressable;

public abstract class WButton
extends WPressable {
    protected GuiTexture texture;
    protected String text;
    protected double textWidth;

    public WButton(String string, GuiTexture guiTexture) {
        this.text = string;
        this.texture = guiTexture;
    }

    public void set(String string) {
        if (this.text == null || (double)Math.round(this.theme.textWidth(string)) != this.textWidth) {
            this.invalidate();
        }
        this.text = string;
    }

    @Override
    protected void onCalculateSize() {
        double d = this.pad();
        if (this.text != null) {
            this.textWidth = this.theme.textWidth(this.text);
            this.width = d + this.textWidth + d;
            this.height = d + this.theme.textHeight() + d;
        } else {
            double d2 = this.theme.textHeight();
            this.width = d + d2 + d;
            this.height = d + d2 + d;
        }
    }
}

