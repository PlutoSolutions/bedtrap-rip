/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.utils.BaseWidget;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.utils.render.color.SettingColor;

public interface MeteorWidget
extends BaseWidget {
    default public void renderBackground(GuiRenderer lllllllllllllllllIlllIIIIIIlIIlI, WWidget lllllllllllllllllIlllIIIIIIlIIIl, boolean lllllllllllllllllIlllIIIIIIlIIII, boolean lllllllllllllllllIlllIIIIIIIllll) {
        MeteorWidget lllllllllllllllllIlllIIIIIIllIll;
        MeteorGuiTheme lllllllllllllllllIlllIIIIIIlIllI = lllllllllllllllllIlllIIIIIIllIll.theme();
        double lllllllllllllllllIlllIIIIIIlIlIl = lllllllllllllllllIlllIIIIIIlIllI.scale(2.0);
        lllllllllllllllllIlllIIIIIIlIIlI.quad(lllllllllllllllllIlllIIIIIIlIIIl.x + lllllllllllllllllIlllIIIIIIlIlIl, lllllllllllllllllIlllIIIIIIlIIIl.y + lllllllllllllllllIlllIIIIIIlIlIl, lllllllllllllllllIlllIIIIIIlIIIl.width - lllllllllllllllllIlllIIIIIIlIlIl * 2.0, lllllllllllllllllIlllIIIIIIlIIIl.height - lllllllllllllllllIlllIIIIIIlIlIl * 2.0, lllllllllllllllllIlllIIIIIIlIllI.backgroundColor.get(lllllllllllllllllIlllIIIIIIlIIII, lllllllllllllllllIlllIIIIIIIllll));
        SettingColor lllllllllllllllllIlllIIIIIIlIlII = lllllllllllllllllIlllIIIIIIlIllI.outlineColor.get(lllllllllllllllllIlllIIIIIIlIIII, lllllllllllllllllIlllIIIIIIIllll);
        lllllllllllllllllIlllIIIIIIlIIlI.quad(lllllllllllllllllIlllIIIIIIlIIIl.x, lllllllllllllllllIlllIIIIIIlIIIl.y, lllllllllllllllllIlllIIIIIIlIIIl.width, lllllllllllllllllIlllIIIIIIlIlIl, lllllllllllllllllIlllIIIIIIlIlII);
        lllllllllllllllllIlllIIIIIIlIIlI.quad(lllllllllllllllllIlllIIIIIIlIIIl.x, lllllllllllllllllIlllIIIIIIlIIIl.y + lllllllllllllllllIlllIIIIIIlIIIl.height - lllllllllllllllllIlllIIIIIIlIlIl, lllllllllllllllllIlllIIIIIIlIIIl.width, lllllllllllllllllIlllIIIIIIlIlIl, lllllllllllllllllIlllIIIIIIlIlII);
        lllllllllllllllllIlllIIIIIIlIIlI.quad(lllllllllllllllllIlllIIIIIIlIIIl.x, lllllllllllllllllIlllIIIIIIlIIIl.y + lllllllllllllllllIlllIIIIIIlIlIl, lllllllllllllllllIlllIIIIIIlIlIl, lllllllllllllllllIlllIIIIIIlIIIl.height - lllllllllllllllllIlllIIIIIIlIlIl * 2.0, lllllllllllllllllIlllIIIIIIlIlII);
        lllllllllllllllllIlllIIIIIIlIIlI.quad(lllllllllllllllllIlllIIIIIIlIIIl.x + lllllllllllllllllIlllIIIIIIlIIIl.width - lllllllllllllllllIlllIIIIIIlIlIl, lllllllllllllllllIlllIIIIIIlIIIl.y + lllllllllllllllllIlllIIIIIIlIlIl, lllllllllllllllllIlllIIIIIIlIlIl, lllllllllllllllllIlllIIIIIIlIIIl.height - lllllllllllllllllIlllIIIIIIlIlIl * 2.0, lllllllllllllllllIlllIIIIIIlIlII);
    }

    default public MeteorGuiTheme theme() {
        MeteorWidget lllllllllllllllllIlllIIIIIlIIlIl;
        return (MeteorGuiTheme)lllllllllllllllllIlllIIIIIlIIlIl.getTheme();
    }
}

