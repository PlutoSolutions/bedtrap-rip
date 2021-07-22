/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.pressable;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.renderer.packer.GuiTexture;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.utils.render.color.Color;

public class WMeteorButton
extends WButton
implements MeteorWidget {
    @Override
    protected void onRender(GuiRenderer llIllIIll, double llIllIIlI, double llIllIIIl, double llIllIIII) {
        WMeteorButton llIllIlII;
        MeteorGuiTheme llIlIllll = llIllIlII.theme();
        double llIlIlllI = llIllIlII.pad();
        llIllIlII.renderBackground(llIllIIll, llIllIlII, llIllIlII.pressed, llIllIlII.mouseOver);
        if (llIllIlII.text != null) {
            llIllIIll.text(llIllIlII.text, llIllIlII.x + llIllIlII.width / 2.0 - llIllIlII.textWidth / 2.0, llIllIlII.y + llIlIlllI, llIlIllll.textColor.get(), false);
        } else {
            double llIllIlIl = llIlIllll.textHeight();
            llIllIIll.quad(llIllIlII.x + llIllIlII.width / 2.0 - llIllIlIl / 2.0, llIllIlII.y + llIlIlllI, llIllIlIl, llIllIlIl, llIllIlII.texture, (Color)llIlIllll.textColor.get());
        }
    }

    public WMeteorButton(String llIllllll, GuiTexture llIlllIll) {
        super(llIllllll, llIlllIll);
        WMeteorButton lllIIIIII;
    }
}

