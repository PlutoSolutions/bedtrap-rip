/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.utils.render.color.Color;

public abstract class WQuad
extends WWidget {
    public Color color;

    public WQuad(Color color) {
        this.color = color;
    }

    @Override
    protected void onCalculateSize() {
        double d;
        this.width = d = this.theme.scale(32.0);
        this.height = d;
    }
}

