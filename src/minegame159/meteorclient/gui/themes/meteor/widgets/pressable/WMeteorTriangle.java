/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.pressable;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.pressable.WTriangle;

public class WMeteorTriangle
extends WTriangle
implements MeteorWidget {
    public WMeteorTriangle() {
        WMeteorTriangle lllllllllllllllllIlIllIIlIllIlIl;
    }

    @Override
    protected void onRender(GuiRenderer lllllllllllllllllIlIllIIlIllIIIl, double lllllllllllllllllIlIllIIlIllIIII, double lllllllllllllllllIlIllIIlIlIllll, double lllllllllllllllllIlIllIIlIlIlllI) {
        WMeteorTriangle lllllllllllllllllIlIllIIlIlIllIl;
        lllllllllllllllllIlIllIIlIllIIIl.rotatedQuad(lllllllllllllllllIlIllIIlIlIllIl.x, lllllllllllllllllIlIllIIlIlIllIl.y, lllllllllllllllllIlIllIIlIlIllIl.width, lllllllllllllllllIlIllIIlIlIllIl.height, lllllllllllllllllIlIllIIlIlIllIl.rotation, GuiRenderer.TRIANGLE, lllllllllllllllllIlIllIIlIlIllIl.theme().backgroundColor.get(lllllllllllllllllIlIllIIlIlIllIl.pressed, lllllllllllllllllIlIllIIlIlIllIl.mouseOver));
    }
}

