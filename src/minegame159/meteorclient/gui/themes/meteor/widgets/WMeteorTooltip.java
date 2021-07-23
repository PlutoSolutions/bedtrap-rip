/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.WTooltip;

public class WMeteorTooltip
extends WTooltip
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        guiRenderer.quad(this, this.theme().backgroundColor.get());
    }

    public WMeteorTooltip(String string) {
        super(string);
    }
}

