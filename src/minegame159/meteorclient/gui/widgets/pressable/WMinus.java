/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.pressable;

import minegame159.meteorclient.gui.widgets.pressable.WPressable;

public abstract class WMinus
extends WPressable {
    public WMinus() {
        WMinus llIllIIIIIIIII;
    }

    @Override
    protected void onCalculateSize() {
        WMinus llIlIllllllIll;
        double llIlIllllllIlI = llIlIllllllIll.pad();
        double llIlIllllllIIl = llIlIllllllIll.theme.textHeight();
        llIlIllllllIll.width = llIlIllllllIlI + llIlIllllllIIl + llIlIllllllIlI;
        llIlIllllllIll.height = llIlIllllllIlI + llIlIllllllIIl + llIlIllllllIlI;
    }
}

