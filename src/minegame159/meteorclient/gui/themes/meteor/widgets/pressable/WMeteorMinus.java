/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.pressable;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;

public class WMeteorMinus
extends WMinus
implements MeteorWidget {
    public WMeteorMinus() {
        WMeteorMinus lllllllllllllllllllIIlIIIlllllII;
    }

    @Override
    protected void onRender(GuiRenderer lllllllllllllllllllIIlIIIllIIIIl, double lllllllllllllllllllIIlIIIllIllII, double lllllllllllllllllllIIlIIIllIlIll, double lllllllllllllllllllIIlIIIllIlIIl) {
        WMeteorMinus lllllllllllllllllllIIlIIIllIIIll;
        double lllllllllllllllllllIIlIIIllIIlll = lllllllllllllllllllIIlIIIllIIIll.pad();
        double lllllllllllllllllllIIlIIIllIIlIl = lllllllllllllllllllIIlIIIllIIIll.theme.scale(3.0);
        lllllllllllllllllllIIlIIIllIIIll.renderBackground(lllllllllllllllllllIIlIIIllIIIIl, lllllllllllllllllllIIlIIIllIIIll, lllllllllllllllllllIIlIIIllIIIll.pressed, lllllllllllllllllllIIlIIIllIIIll.mouseOver);
        lllllllllllllllllllIIlIIIllIIIIl.quad(lllllllllllllllllllIIlIIIllIIIll.x + lllllllllllllllllllIIlIIIllIIlll, lllllllllllllllllllIIlIIIllIIIll.y + lllllllllllllllllllIIlIIIllIIIll.height / 2.0 - lllllllllllllllllllIIlIIIllIIlIl / 2.0, lllllllllllllllllllIIlIIIllIIIll.width - lllllllllllllllllllIIlIIIllIIlll * 2.0, lllllllllllllllllllIIlIIIllIIlIl, lllllllllllllllllllIIlIIIllIIIll.theme().minusColor.get());
    }
}

