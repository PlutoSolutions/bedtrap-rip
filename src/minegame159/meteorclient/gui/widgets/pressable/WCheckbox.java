/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets.pressable;

import minegame159.meteorclient.gui.widgets.pressable.WPressable;

public abstract class WCheckbox
extends WPressable {
    public /* synthetic */ boolean checked;

    @Override
    protected void onCalculateSize() {
        WCheckbox lIIlIlllIlllIII;
        double lIIlIlllIlllIlI = lIIlIlllIlllIII.pad();
        double lIIlIlllIlllIIl = lIIlIlllIlllIII.theme.textHeight();
        lIIlIlllIlllIII.width = lIIlIlllIlllIlI + lIIlIlllIlllIIl + lIIlIlllIlllIlI;
        lIIlIlllIlllIII.height = lIIlIlllIlllIlI + lIIlIlllIlllIIl + lIIlIlllIlllIlI;
    }

    @Override
    protected void onPressed(int lIIlIlllIllIIll) {
        WCheckbox lIIlIlllIllIlII;
        lIIlIlllIllIlII.checked = !lIIlIlllIllIlII.checked;
    }

    public WCheckbox(boolean lIIlIlllIllllll) {
        WCheckbox lIIlIllllIIIIII;
        lIIlIllllIIIIII.checked = lIIlIlllIllllll;
    }
}

