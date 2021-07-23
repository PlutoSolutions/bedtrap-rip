/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.utils.render.color.Color;

public abstract class WLabel
extends WWidget {
    protected String text;
    public Color color;
    protected boolean title;

    public void set(String string) {
        if ((double)Math.round(this.theme.textWidth(string, string.length(), this.title)) != this.width) {
            this.invalidate();
        }
        this.text = string;
    }

    public String get() {
        return this.text;
    }

    public WLabel(String string, boolean bl) {
        this.text = string;
        this.title = bl;
    }

    @Override
    protected void onCalculateSize() {
        this.width = this.theme.textWidth(this.text, this.text.length(), this.title);
        this.height = this.theme.textHeight(this.title);
    }
}

