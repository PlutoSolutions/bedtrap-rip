/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.widgets.pressable;

import minegame159.meteorclient.gui.widgets.pressable.WPressable;

public abstract class WTriangle
extends WPressable {
    public double rotation;

    @Override
    protected void onCalculateSize() {
        double d;
        this.width = d = this.theme.textHeight();
        this.height = d;
    }
}

