/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WTooltip;

public class WMeteorTooltip
extends WTooltip
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer lllIIIIIIIlIIII, double lllIIIIIIIIllll, double lllIIIIIIIIlllI, double lllIIIIIIIIllIl) {
        WMeteorTooltip lllIIIIIIIlIIIl;
        lllIIIIIIIlIIII.quad(lllIIIIIIIlIIIl, lllIIIIIIIlIIIl.theme().backgroundColor.get());
    }

    public WMeteorTooltip(String lllIIIIIIIlIllI) {
        super(lllIIIIIIIlIllI);
        WMeteorTooltip lllIIIIIIIlIlIl;
    }
}

