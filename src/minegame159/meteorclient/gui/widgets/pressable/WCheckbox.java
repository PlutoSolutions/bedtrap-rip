/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.widgets.pressable;

import minegame159.meteorclient.gui.widgets.pressable.WPressable;

public abstract class WCheckbox
extends WPressable {
    public boolean checked;

    @Override
    protected void onCalculateSize() {
        double d = this.pad();
        double d2 = this.theme.textHeight();
        this.width = d + d2 + d;
        this.height = d + d2 + d;
    }

    @Override
    protected void onPressed(int n) {
        this.checked = !this.checked;
    }

    public WCheckbox(boolean bl) {
        this.checked = bl;
    }
}

