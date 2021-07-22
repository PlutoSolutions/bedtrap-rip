/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.containers.WWindow;

public class WMeteorWindow
extends WWindow
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer lllllIllIIIIIll, double lllllIllIIIIIlI, double lllllIllIIIIIIl, double lllllIllIIIIIII) {
        WMeteorWindow lllllIllIIIIlII;
        if (lllllIllIIIIlII.expanded || lllllIllIIIIlII.animProgress > 0.0) {
            lllllIllIIIIIll.quad(lllllIllIIIIlII.x, lllllIllIIIIlII.y + lllllIllIIIIlII.header.height, lllllIllIIIIlII.width, lllllIllIIIIlII.height - lllllIllIIIIlII.header.height, lllllIllIIIIlII.theme().backgroundColor.get());
        }
    }

    public WMeteorWindow(String lllllIllIIIlIlI) {
        super(lllllIllIIIlIlI);
        WMeteorWindow lllllIllIIIllIl;
    }

    @Override
    protected WWindow.WHeader header() {
        WMeteorWindow lllllIllIIIIlll;
        return lllllIllIIIIlll.new WMeteorHeader();
    }

    private class WMeteorHeader
    extends WWindow.WHeader {
        @Override
        protected void onRender(GuiRenderer llllllllllllllllIlIlllllllIIlIll, double llllllllllllllllIlIlllllllIIlIlI, double llllllllllllllllIlIlllllllIIlIIl, double llllllllllllllllIlIlllllllIIlIII) {
            WMeteorHeader llllllllllllllllIlIlllllllIIIlll;
            llllllllllllllllIlIlllllllIIlIll.quad(llllllllllllllllIlIlllllllIIIlll, llllllllllllllllIlIlllllllIIIlll.WMeteorWindow.this.theme().accentColor.get());
        }

        private WMeteorHeader() {
            WMeteorHeader llllllllllllllllIlIlllllllIlIIIl;
            super(llllllllllllllllIlIlllllllIlIIIl.WMeteorWindow.this);
        }
    }
}

