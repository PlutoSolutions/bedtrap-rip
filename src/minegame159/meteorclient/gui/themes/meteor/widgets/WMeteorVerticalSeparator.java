/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WVerticalSeparator;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorVerticalSeparator
extends WVerticalSeparator
implements MeteorWidget {
    public WMeteorVerticalSeparator() {
        WMeteorVerticalSeparator llllllllllllllllllIIIllIIlllIlII;
    }

    @Override
    protected void onRender(GuiRenderer llllllllllllllllllIIIllIIllIlIlI, double llllllllllllllllllIIIllIIllIlIIl, double llllllllllllllllllIIIllIIllIlIII, double llllllllllllllllllIIIllIIllIIlll) {
        WMeteorVerticalSeparator llllllllllllllllllIIIllIIllIIIIl;
        MeteorGuiTheme llllllllllllllllllIIIllIIllIIllI = llllllllllllllllllIIIllIIllIIIIl.theme();
        Color llllllllllllllllllIIIllIIllIIlIl = llllllllllllllllllIIIllIIllIIllI.separatorEdges.get();
        Color llllllllllllllllllIIIllIIllIIlII = llllllllllllllllllIIIllIIllIIllI.separatorCenter.get();
        double llllllllllllllllllIIIllIIllIIIll = llllllllllllllllllIIIllIIllIIllI.scale(1.0);
        double llllllllllllllllllIIIllIIllIIIlI = Math.round(llllllllllllllllllIIIllIIllIIIIl.width / 2.0);
        llllllllllllllllllIIIllIIllIlIlI.quad(llllllllllllllllllIIIllIIllIIIIl.x + llllllllllllllllllIIIllIIllIIIlI, llllllllllllllllllIIIllIIllIIIIl.y, llllllllllllllllllIIIllIIllIIIll, llllllllllllllllllIIIllIIllIIIIl.height / 2.0, llllllllllllllllllIIIllIIllIIlIl, llllllllllllllllllIIIllIIllIIlIl, llllllllllllllllllIIIllIIllIIlII, llllllllllllllllllIIIllIIllIIlII);
        llllllllllllllllllIIIllIIllIlIlI.quad(llllllllllllllllllIIIllIIllIIIIl.x + llllllllllllllllllIIIllIIllIIIlI, llllllllllllllllllIIIllIIllIIIIl.y + llllllllllllllllllIIIllIIllIIIIl.height / 2.0, llllllllllllllllllIIIllIIllIIIll, llllllllllllllllllIIIllIIllIIIIl.height / 2.0, llllllllllllllllllIIIllIIllIIlII, llllllllllllllllllIIIllIIllIIlII, llllllllllllllllllIIIllIIllIIlIl, llllllllllllllllllIIIllIIllIIlIl);
    }
}

