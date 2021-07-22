/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WMultiLabel;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorMultiLabel
extends WMultiLabel
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer lllllIlIlllIll, double lllllIllIIIIIl, double lllllIllIIIIII, double lllllIlIllllll) {
        WMeteorMultiLabel lllllIllIIIIll;
        double lllllIlIlllllI = lllllIllIIIIll.theme.textHeight(lllllIllIIIIll.title);
        Color lllllIlIllllIl = lllllIllIIIIll.theme().textColor.get();
        for (int lllllIllIIIlII = 0; lllllIllIIIlII < lllllIllIIIIll.lines.size(); ++lllllIllIIIlII) {
            lllllIlIlllIll.text((String)lllllIllIIIIll.lines.get(lllllIllIIIlII), lllllIllIIIIll.x, lllllIllIIIIll.y + lllllIlIlllllI * (double)lllllIllIIIlII, lllllIlIllllIl, false);
        }
    }

    public WMeteorMultiLabel(String lllllIllIlllII, boolean lllllIllIlIIll, double lllllIllIlIIlI) {
        super(lllllIllIlllII, lllllIllIlIIll, lllllIllIlIIlI);
        WMeteorMultiLabel lllllIllIlIllI;
    }
}

