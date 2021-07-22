/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.pressable;

import minegame159.meteorclient.gui.widgets.WWidget;

public abstract class WPressable
extends WWidget {
    public Runnable action;
    protected boolean pressed;

    @Override
    public boolean onMouseReleased(double d, double d2, int n) {
        if (this.pressed) {
            this.onPressed(n);
            if (this.action != null) {
                this.action.run();
            }
            this.pressed = false;
        }
        return false;
    }

    @Override
    public boolean onMouseClicked(double d, double d2, int n, boolean bl) {
        if (this.mouseOver && (n == 0 || n == 1) && !bl) {
            this.pressed = true;
        }
        return this.pressed;
    }

    protected void onPressed(int n) {
    }
}

