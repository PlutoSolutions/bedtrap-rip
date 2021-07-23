/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.pressable;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.pressable.WTriangle;

public class WMeteorTriangle
extends WTriangle
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        guiRenderer.rotatedQuad(this.x, this.y, this.width, this.height, this.rotation, GuiRenderer.TRIANGLE, this.theme().backgroundColor.get(this.pressed, this.mouseOver));
    }
}

