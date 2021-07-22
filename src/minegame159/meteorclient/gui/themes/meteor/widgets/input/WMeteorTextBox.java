/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.input;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.utils.CharFilter;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.utils.Utils;

public class WMeteorTextBox
extends WTextBox
implements MeteorWidget {
    private /* synthetic */ boolean cursorVisible;
    private /* synthetic */ double cursorTimer;
    private /* synthetic */ double animProgress;

    @Override
    protected void onRender(GuiRenderer lIlIllIlllIIII, double lIlIllIlllIlII, double lIlIllIlllIIll, double lIlIllIlllIIlI) {
        WMeteorTextBox lIlIllIlllIllI;
        if (lIlIllIlllIllI.cursorTimer >= 1.0) {
            lIlIllIlllIllI.cursorVisible = !lIlIllIlllIllI.cursorVisible;
            lIlIllIlllIllI.cursorTimer = 0.0;
        } else {
            lIlIllIlllIllI.cursorTimer += lIlIllIlllIIlI * 1.75;
        }
        lIlIllIlllIllI.renderBackground(lIlIllIlllIIII, lIlIllIlllIllI, false, false);
        lIlIllIlllIllI.renderTextAndCursor(lIlIllIlllIIII, lIlIllIlllIIlI);
    }

    private void renderTextAndCursor(GuiRenderer lIlIllIllIIIIl, double lIlIllIllIIIII) {
        WMeteorTextBox lIlIllIllIIIlI;
        MeteorGuiTheme lIlIllIllIIlIl = lIlIllIllIIIlI.theme();
        double lIlIllIllIIlII = lIlIllIllIIIlI.pad();
        double lIlIllIllIIIll = lIlIllIllIIIlI.getOverflowWidthForRender();
        if (!lIlIllIllIIIlI.text.isEmpty()) {
            lIlIllIllIIIIl.scissorStart(lIlIllIllIIIlI.x + lIlIllIllIIlII, lIlIllIllIIIlI.y + lIlIllIllIIlII, lIlIllIllIIIlI.width - lIlIllIllIIlII * 2.0, lIlIllIllIIIlI.height - lIlIllIllIIlII * 2.0);
            lIlIllIllIIIIl.text(lIlIllIllIIIlI.text, lIlIllIllIIIlI.x + lIlIllIllIIlII - lIlIllIllIIIll, lIlIllIllIIIlI.y + lIlIllIllIIlII, lIlIllIllIIlIl.textColor.get(), false);
            lIlIllIllIIIIl.scissorEnd();
        }
        lIlIllIllIIIlI.animProgress += lIlIllIllIIIII * 10.0 * (double)(lIlIllIllIIIlI.focused && lIlIllIllIIIlI.cursorVisible ? 1 : -1);
        lIlIllIllIIIlI.animProgress = Utils.clamp(lIlIllIllIIIlI.animProgress, 0.0, 1.0);
        if (lIlIllIllIIIlI.focused && lIlIllIllIIIlI.cursorVisible || lIlIllIllIIIlI.animProgress > 0.0) {
            lIlIllIllIIIIl.setAlpha(lIlIllIllIIIlI.animProgress);
            lIlIllIllIIIIl.quad(lIlIllIllIIIlI.x + lIlIllIllIIlII + lIlIllIllIIIlI.getCursorTextWidth() - lIlIllIllIIIll, lIlIllIllIIIlI.y + lIlIllIllIIlII, lIlIllIllIIlIl.scale(1.0), lIlIllIllIIlIl.textHeight(), lIlIllIllIIlIl.textColor.get());
            lIlIllIllIIIIl.setAlpha(1.0);
        }
    }

    public WMeteorTextBox(String lIlIllIllllllI, CharFilter lIlIlllIIIIIII) {
        super(lIlIllIllllllI, lIlIlllIIIIIII);
        WMeteorTextBox lIlIlllIIIIIlI;
    }

    @Override
    protected void onCursorChanged() {
        lIlIllIllllIll.cursorVisible = true;
        lIlIllIllllIll.cursorTimer = 0.0;
    }
}

