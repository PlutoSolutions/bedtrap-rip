/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WHorizontalSeparator;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorHorizontalSeparator
extends WHorizontalSeparator
implements MeteorWidget {
    public WMeteorHorizontalSeparator(String lIllIlIIlllIII) {
        super(lIllIlIIlllIII);
        WMeteorHorizontalSeparator lIllIlIIlllIIl;
    }

    private void renderWithoutText(GuiRenderer lIllIlIIlIIIIl) {
        WMeteorHorizontalSeparator lIllIlIIlIIlll;
        MeteorGuiTheme lIllIlIIlIIlIl = lIllIlIIlIIlll.theme();
        double lIllIlIIlIIlII = lIllIlIIlIIlIl.scale(1.0);
        double lIllIlIIlIIIll = lIllIlIIlIIlll.width / 2.0;
        lIllIlIIlIIIIl.quad(lIllIlIIlIIlll.x, lIllIlIIlIIlll.y + lIllIlIIlIIlII, lIllIlIIlIIIll, lIllIlIIlIIlII, lIllIlIIlIIlIl.separatorEdges.get(), (Color)lIllIlIIlIIlIl.separatorCenter.get());
        lIllIlIIlIIIIl.quad(lIllIlIIlIIlll.x + lIllIlIIlIIIll, lIllIlIIlIIlll.y + lIllIlIIlIIlII, lIllIlIIlIIIll, lIllIlIIlIIlII, lIllIlIIlIIlIl.separatorCenter.get(), (Color)lIllIlIIlIIlIl.separatorEdges.get());
    }

    @Override
    protected void onRender(GuiRenderer lIllIlIIlIllIl, double lIllIlIIllIIIl, double lIllIlIIllIIII, double lIllIlIIlIllll) {
        WMeteorHorizontalSeparator lIllIlIIlIlllI;
        if (lIllIlIIlIlllI.text == null) {
            lIllIlIIlIlllI.renderWithoutText(lIllIlIIlIllIl);
        } else {
            lIllIlIIlIlllI.renderWithText(lIllIlIIlIllIl);
        }
    }

    private void renderWithText(GuiRenderer lIllIlIIIIllII) {
        WMeteorHorizontalSeparator lIllIlIIIIllIl;
        MeteorGuiTheme lIllIlIIIlIIll = lIllIlIIIIllIl.theme();
        double lIllIlIIIlIIlI = lIllIlIIIlIIll.scale(2.0);
        double lIllIlIIIlIIIl = lIllIlIIIlIIll.scale(1.0);
        double lIllIlIIIlIIII = Math.round(lIllIlIIIIllIl.width / 2.0 - lIllIlIIIIllIl.textWidth / 2.0 - lIllIlIIIlIIlI);
        double lIllIlIIIIllll = lIllIlIIIlIIlI + lIllIlIIIlIIII + lIllIlIIIIllIl.textWidth + lIllIlIIIlIIlI;
        double lIllIlIIIIlllI = Math.round(lIllIlIIIIllIl.height / 2.0);
        lIllIlIIIIllII.quad(lIllIlIIIIllIl.x, lIllIlIIIIllIl.y + lIllIlIIIIlllI, lIllIlIIIlIIII, lIllIlIIIlIIIl, lIllIlIIIlIIll.separatorEdges.get(), (Color)lIllIlIIIlIIll.separatorCenter.get());
        lIllIlIIIIllII.text(lIllIlIIIIllIl.text, lIllIlIIIIllIl.x + lIllIlIIIlIIII + lIllIlIIIlIIlI, lIllIlIIIIllIl.y, lIllIlIIIlIIll.separatorText.get(), false);
        lIllIlIIIIllII.quad(lIllIlIIIIllIl.x + lIllIlIIIIllll, lIllIlIIIIllIl.y + lIllIlIIIIlllI, lIllIlIIIIllIl.width - lIllIlIIIIllll, lIllIlIIIlIIIl, lIllIlIIIlIIll.separatorCenter.get(), (Color)lIllIlIIIlIIll.separatorEdges.get());
    }
}

