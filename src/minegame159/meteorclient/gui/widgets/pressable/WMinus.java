/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.widgets.pressable;

import minegame159.meteorclient.gui.widgets.pressable.WPressable;

public abstract class WMinus
extends WPressable {
    @Override
    protected void onCalculateSize() {
        double d = this.pad();
        double d2 = this.theme.textHeight();
        this.width = d + d2 + d;
        this.height = d + d2 + d;
    }
}

