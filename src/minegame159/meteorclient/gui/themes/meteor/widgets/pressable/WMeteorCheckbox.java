/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.pressable;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.utils.Utils;

public class WMeteorCheckbox
extends WCheckbox
implements MeteorWidget {
    private /* synthetic */ double animProgress;

    public WMeteorCheckbox(boolean llllllllllllllllllllIIIIIIllIlIl) {
        super(llllllllllllllllllllIIIIIIllIlIl);
        WMeteorCheckbox llllllllllllllllllllIIIIIIllIllI;
        llllllllllllllllllllIIIIIIllIllI.animProgress = llllllllllllllllllllIIIIIIllIlIl ? 1.0 : 0.0;
    }

    @Override
    protected void onRender(GuiRenderer llllllllllllllllllllIIIIIIlIllIl, double llllllllllllllllllllIIIIIIlIllII, double llllllllllllllllllllIIIIIIlIlIll, double llllllllllllllllllllIIIIIIlIIllI) {
        WMeteorCheckbox llllllllllllllllllllIIIIIIlIlIII;
        MeteorGuiTheme llllllllllllllllllllIIIIIIlIlIIl = llllllllllllllllllllIIIIIIlIlIII.theme();
        llllllllllllllllllllIIIIIIlIlIII.animProgress += (double)(llllllllllllllllllllIIIIIIlIlIII.checked ? 1 : -1) * llllllllllllllllllllIIIIIIlIIllI * 14.0;
        llllllllllllllllllllIIIIIIlIlIII.animProgress = Utils.clamp(llllllllllllllllllllIIIIIIlIlIII.animProgress, 0.0, 1.0);
        llllllllllllllllllllIIIIIIlIlIII.renderBackground(llllllllllllllllllllIIIIIIlIllIl, llllllllllllllllllllIIIIIIlIlIII, llllllllllllllllllllIIIIIIlIlIII.pressed, llllllllllllllllllllIIIIIIlIlIII.mouseOver);
        if (llllllllllllllllllllIIIIIIlIlIII.animProgress > 0.0) {
            double llllllllllllllllllllIIIIIIlIllll = (llllllllllllllllllllIIIIIIlIlIII.width - llllllllllllllllllllIIIIIIlIlIIl.scale(2.0)) / 1.75 * llllllllllllllllllllIIIIIIlIlIII.animProgress;
            llllllllllllllllllllIIIIIIlIllIl.quad(llllllllllllllllllllIIIIIIlIlIII.x + (llllllllllllllllllllIIIIIIlIlIII.width - llllllllllllllllllllIIIIIIlIllll) / 2.0, llllllllllllllllllllIIIIIIlIlIII.y + (llllllllllllllllllllIIIIIIlIlIII.height - llllllllllllllllllllIIIIIIlIllll) / 2.0, llllllllllllllllllllIIIIIIlIllll, llllllllllllllllllllIIIIIIlIllll, llllllllllllllllllllIIIIIIlIlIIl.checkboxColor.get());
        }
    }
}

