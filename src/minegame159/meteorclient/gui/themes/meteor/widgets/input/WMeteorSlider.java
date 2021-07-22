/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.input;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.input.WSlider;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorSlider
extends WSlider
implements MeteorWidget {
    private void renderBar(GuiRenderer llIllllllIlllll, double llIllllllIllllI) {
        WMeteorSlider llIlllllllIIIII;
        MeteorGuiTheme llIlllllllIIlIl = llIlllllllIIIII.theme();
        double llIlllllllIIlII = llIlllllllIIlIl.scale(3.0);
        double llIlllllllIIIll = llIlllllllIIIII.handleSize();
        double llIlllllllIIIlI = llIlllllllIIIII.x + llIlllllllIIIll / 2.0;
        double llIlllllllIIIIl = llIlllllllIIIII.y + llIlllllllIIIII.height / 2.0 - llIlllllllIIlII / 2.0;
        llIllllllIlllll.quad(llIlllllllIIIlI, llIlllllllIIIIl, llIllllllIllllI, llIlllllllIIlII, llIlllllllIIlIl.sliderLeft.get());
        llIllllllIlllll.quad(llIlllllllIIIlI + llIllllllIllllI, llIlllllllIIIIl, llIlllllllIIIII.width - llIllllllIllllI - llIlllllllIIIll, llIlllllllIIlII, llIlllllllIIlIl.sliderRight.get());
    }

    public WMeteorSlider(double llIllllllllllll, double lllIIIIIIIIIIlI, double llIllllllllllIl) {
        super(llIllllllllllll, lllIIIIIIIIIIlI, llIllllllllllIl);
        WMeteorSlider lllIIIIIIIIIlII;
    }

    @Override
    protected void onRender(GuiRenderer llIllllllllIIlI, double llIllllllllIlll, double llIllllllllIllI, double llIllllllllIlIl) {
        WMeteorSlider llIlllllllllIIl;
        double llIllllllllIlII = llIlllllllllIIl.valueWidth();
        llIlllllllllIIl.renderBar(llIllllllllIIlI, llIllllllllIlII);
        llIlllllllllIIl.renderHandle(llIllllllllIIlI, llIllllllllIlII);
    }

    private void renderHandle(GuiRenderer llIllllllIlIIlI, double llIllllllIlIIIl) {
        WMeteorSlider llIllllllIIlllI;
        MeteorGuiTheme llIllllllIlIIII = llIllllllIIlllI.theme();
        double llIllllllIIllll = llIllllllIIlllI.handleSize();
        llIllllllIlIIlI.quad(llIllllllIIlllI.x + llIllllllIlIIIl, llIllllllIIlllI.y, llIllllllIIllll, llIllllllIIllll, GuiRenderer.CIRCLE, (Color)llIllllllIlIIII.sliderHandle.get(llIllllllIIlllI.dragging, llIllllllIIlllI.handleMouseOver));
    }
}

