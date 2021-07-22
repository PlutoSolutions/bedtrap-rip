/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.utils.render.color.Color;

public abstract class WLabel
extends WWidget {
    protected /* synthetic */ String text;
    public /* synthetic */ Color color;
    protected /* synthetic */ boolean title;

    public void set(String lIIIlIIlIIIllIl) {
        WLabel lIIIlIIlIIIlllI;
        if ((double)Math.round(lIIIlIIlIIIlllI.theme.textWidth(lIIIlIIlIIIllIl, lIIIlIIlIIIllIl.length(), lIIIlIIlIIIlllI.title)) != lIIIlIIlIIIlllI.width) {
            lIIIlIIlIIIlllI.invalidate();
        }
        lIIIlIIlIIIlllI.text = lIIIlIIlIIIllIl;
    }

    public String get() {
        WLabel lIIIlIIlIIIlIlI;
        return lIIIlIIlIIIlIlI.text;
    }

    public WLabel(String lIIIlIIlIIllIlI, boolean lIIIlIIlIIlIllI) {
        WLabel lIIIlIIlIIllIII;
        lIIIlIIlIIllIII.text = lIIIlIIlIIllIlI;
        lIIIlIIlIIllIII.title = lIIIlIIlIIlIllI;
    }

    @Override
    protected void onCalculateSize() {
        WLabel lIIIlIIlIIlIlII;
        lIIIlIIlIIlIlII.width = lIIIlIIlIIlIlII.theme.textWidth(lIIIlIIlIIlIlII.text, lIIIlIIlIIlIlII.text.length(), lIIIlIIlIIlIlII.title);
        lIIIlIIlIIlIlII.height = lIIIlIIlIIlIlII.theme.textHeight(lIIIlIIlIIlIlII.title);
    }
}

