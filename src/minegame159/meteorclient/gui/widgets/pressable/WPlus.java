/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.pressable;

import minegame159.meteorclient.gui.widgets.pressable.WPressable;

public abstract class WPlus
extends WPressable {
    @Override
    protected void onCalculateSize() {
        WPlus lllllllllllllllllIlIlIllIlIIIIII;
        double lllllllllllllllllIlIlIllIIllllll = lllllllllllllllllIlIlIllIlIIIIII.pad();
        double lllllllllllllllllIlIlIllIIlllllI = lllllllllllllllllIlIlIllIlIIIIII.theme.textHeight();
        lllllllllllllllllIlIlIllIlIIIIII.width = lllllllllllllllllIlIlIllIIllllll + lllllllllllllllllIlIlIllIIlllllI + lllllllllllllllllIlIlIllIIllllll;
        lllllllllllllllllIlIlIllIlIIIIII.height = lllllllllllllllllIlIlIllIIllllll + lllllllllllllllllIlIlIllIIlllllI + lllllllllllllllllIlIlIllIIllllll;
    }

    public WPlus() {
        WPlus lllllllllllllllllIlIlIllIlIIIlII;
    }
}

