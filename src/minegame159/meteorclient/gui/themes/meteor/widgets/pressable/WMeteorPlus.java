/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.pressable;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.pressable.WPlus;

public class WMeteorPlus
extends WPlus
implements MeteorWidget {
    public WMeteorPlus() {
        WMeteorPlus llIlllIIlIlll;
    }

    @Override
    protected void onRender(GuiRenderer llIlllIIIIlll, double llIlllIIIlllI, double llIlllIIIllIl, double llIlllIIIllII) {
        WMeteorPlus llIlllIIlIIII;
        MeteorGuiTheme llIlllIIIlIll = llIlllIIlIIII.theme();
        double llIlllIIIlIlI = llIlllIIlIIII.pad();
        double llIlllIIIlIIl = llIlllIIIlIll.scale(3.0);
        llIlllIIlIIII.renderBackground(llIlllIIIIlll, llIlllIIlIIII, llIlllIIlIIII.pressed, llIlllIIlIIII.mouseOver);
        llIlllIIIIlll.quad(llIlllIIlIIII.x + llIlllIIIlIlI, llIlllIIlIIII.y + llIlllIIlIIII.height / 2.0 - llIlllIIIlIIl / 2.0, llIlllIIlIIII.width - llIlllIIIlIlI * 2.0, llIlllIIIlIIl, llIlllIIIlIll.plusColor.get());
        llIlllIIIIlll.quad(llIlllIIlIIII.x + llIlllIIlIIII.width / 2.0 - llIlllIIIlIIl / 2.0, llIlllIIlIIII.y + llIlllIIIlIlI, llIlllIIIlIIl, llIlllIIlIIII.height - llIlllIIIlIlI * 2.0, llIlllIIIlIll.plusColor.get());
    }
}

