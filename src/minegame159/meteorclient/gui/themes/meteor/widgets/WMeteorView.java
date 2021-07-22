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
    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        if (this.canScroll && this.hasScrollBar) {
            guiRenderer.quad(this.handleX(), this.handleY(), this.handleWidth(), this.handleHeight(), this.theme().scrollbarColor.get(this.handlePressed, this.handleMouseOver));
        }
    }
}

