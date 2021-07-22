/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.pressable;

import minegame159.meteorclient.gui.widgets.WWidget;

public abstract class WPressable
extends WWidget {
    public /* synthetic */ Runnable action;
    protected /* synthetic */ boolean pressed;

    @Override
    public boolean onMouseReleased(double lllllllllllllllllIlIlIIlIIlIIlII, double lllllllllllllllllIlIlIIlIIlIIIll, int lllllllllllllllllIlIlIIlIIlIIIlI) {
        WPressable lllllllllllllllllIlIlIIlIIlIIlIl;
        if (lllllllllllllllllIlIlIIlIIlIIlIl.pressed) {
            lllllllllllllllllIlIlIIlIIlIIlIl.onPressed(lllllllllllllllllIlIlIIlIIlIIIlI);
            if (lllllllllllllllllIlIlIIlIIlIIlIl.action != null) {
                lllllllllllllllllIlIlIIlIIlIIlIl.action.run();
            }
            lllllllllllllllllIlIlIIlIIlIIlIl.pressed = false;
        }
        return false;
    }

    @Override
    public boolean onMouseClicked(double lllllllllllllllllIlIlIIlIIlIlllI, double lllllllllllllllllIlIlIIlIIlIllIl, int lllllllllllllllllIlIlIIlIIlIlIIl, boolean lllllllllllllllllIlIlIIlIIlIlIII) {
        WPressable lllllllllllllllllIlIlIIlIIlIllll;
        if (lllllllllllllllllIlIlIIlIIlIllll.mouseOver && (lllllllllllllllllIlIlIIlIIlIlIIl == 0 || lllllllllllllllllIlIlIIlIIlIlIIl == 1) && !lllllllllllllllllIlIlIIlIIlIlIII) {
            lllllllllllllllllIlIlIIlIIlIllll.pressed = true;
        }
        return lllllllllllllllllIlIlIIlIIlIllll.pressed;
    }

    public WPressable() {
        WPressable lllllllllllllllllIlIlIIlIIllIlII;
    }

    protected void onPressed(int lllllllllllllllllIlIlIIlIIIllllI) {
    }
}

