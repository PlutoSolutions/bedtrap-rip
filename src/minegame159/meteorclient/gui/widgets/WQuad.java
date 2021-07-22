/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.widgets;

import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.utils.render.color.Color;

public abstract class WQuad
extends WWidget {
    public /* synthetic */ Color color;

    public WQuad(Color lllllllllllllllllIIIlllIIlIllIlI) {
        WQuad lllllllllllllllllIIIlllIIlIlllIl;
        lllllllllllllllllIIIlllIIlIlllIl.color = lllllllllllllllllIIIlllIIlIllIlI;
    }

    @Override
    protected void onCalculateSize() {
        WQuad lllllllllllllllllIIIlllIIlIlIlll;
        double lllllllllllllllllIIIlllIIlIlIllI;
        lllllllllllllllllIIIlllIIlIlIlll.width = lllllllllllllllllIIIlllIIlIlIllI = lllllllllllllllllIIIlllIIlIlIlll.theme.scale(32.0);
        lllllllllllllllllIIIlllIIlIlIlll.height = lllllllllllllllllIIIlllIIlIlIllI;
    }
}

