/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WTopBar;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorTopBar
extends WTopBar
implements MeteorWidget {
    public WMeteorTopBar() {
        WMeteorTopBar lIIlIllIlIIll;
    }

    @Override
    protected Color getNameColor() {
        WMeteorTopBar lIIlIllIIIlll;
        return lIIlIllIIIlll.theme().textColor.get();
    }

    @Override
    protected Color getButtonColor(boolean lIIlIllIIllIl, boolean lIIlIllIIlIIl) {
        WMeteorTopBar lIIlIllIIlllI;
        return lIIlIllIIlllI.theme().backgroundColor.get(lIIlIllIIllIl, lIIlIllIIlIIl);
    }
}

