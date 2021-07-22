/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.containers.WView;

public class WMeteorView
extends WView
implements MeteorWidget {
    public WMeteorView() {
        WMeteorView lIIllIlIllIlIlI;
    }

    @Override
    protected void onRender(GuiRenderer lIIllIlIllIIIIl, double lIIllIlIllIIlIl, double lIIllIlIllIIlII, double lIIllIlIllIIIll) {
        WMeteorView lIIllIlIllIIIlI;
        if (lIIllIlIllIIIlI.canScroll && lIIllIlIllIIIlI.hasScrollBar) {
            lIIllIlIllIIIIl.quad(lIIllIlIllIIIlI.handleX(), lIIllIlIllIIIlI.handleY(), lIIllIlIllIIIlI.handleWidth(), lIIllIlIllIIIlI.handleHeight(), lIIllIlIllIIIlI.theme().scrollbarColor.get(lIIllIlIllIIIlI.handlePressed, lIIllIlIllIIIlI.handleMouseOver));
        }
    }
}

