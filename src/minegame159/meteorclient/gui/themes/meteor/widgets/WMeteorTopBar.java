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
    @Override
    protected Color getNameColor() {
        return this.theme().textColor.get();
    }

    @Override
    protected Color getButtonColor(boolean bl, boolean bl2) {
        return this.theme().backgroundColor.get(bl, bl2);
    }
}

