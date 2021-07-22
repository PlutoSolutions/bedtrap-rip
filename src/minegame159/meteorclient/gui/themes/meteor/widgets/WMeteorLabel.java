/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WLabel;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorLabel
extends WLabel
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer llllllllllllllllllllIIIIIIIlIIll, double llllllllllllllllllllIIIIIIIlIIlI, double llllllllllllllllllllIIIIIIIlIIIl, double llllllllllllllllllllIIIIIIIlIIII) {
        WMeteorLabel llllllllllllllllllllIIIIIIIlIlII;
        if (!llllllllllllllllllllIIIIIIIlIlII.text.isEmpty()) {
            llllllllllllllllllllIIIIIIIlIIll.text(llllllllllllllllllllIIIIIIIlIlII.text, llllllllllllllllllllIIIIIIIlIlII.x, llllllllllllllllllllIIIIIIIlIlII.y, llllllllllllllllllllIIIIIIIlIlII.color != null ? llllllllllllllllllllIIIIIIIlIlII.color : (llllllllllllllllllllIIIIIIIlIlII.title ? (Color)llllllllllllllllllllIIIIIIIlIlII.theme().titleTextColor.get() : (Color)llllllllllllllllllllIIIIIIIlIlII.theme().textColor.get()), llllllllllllllllllllIIIIIIIlIlII.title);
        }
    }

    public WMeteorLabel(String llllllllllllllllllllIIIIIIIllIII, boolean llllllllllllllllllllIIIIIIIllIlI) {
        super(llllllllllllllllllllIIIIIIIllIII, llllllllllllllllllllIIIIIIIllIlI);
        WMeteorLabel llllllllllllllllllllIIIIIIIllIIl;
    }
}

