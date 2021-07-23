/*
 * Decompiled with CFR 0.151.
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
    private boolean cursorVisible;
    private double cursorTimer;
    private double animProgress;

    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        if (this.cursorTimer >= 1.0) {
            this.cursorVisible = !this.cursorVisible;
            this.cursorTimer = 0.0;
        } else {
            this.cursorTimer += d3 * 1.75;
        }
        this.renderBackground(guiRenderer, this, false, false);
        this.renderTextAndCursor(guiRenderer, d3);
    }

    private void renderTextAndCursor(GuiRenderer guiRenderer, double d) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        double d2 = this.pad();
        double d3 = this.getOverflowWidthForRender();
        if (!this.text.isEmpty()) {
            guiRenderer.scissorStart(this.x + d2, this.y + d2, this.width - d2 * 2.0, this.height - d2 * 2.0);
            guiRenderer.text(this.text, this.x + d2 - d3, this.y + d2, meteorGuiTheme.textColor.get(), false);
            guiRenderer.scissorEnd();
        }
        this.animProgress += d * 10.0 * (double)(this.focused && this.cursorVisible ? 1 : -1);
        this.animProgress = Utils.clamp(this.animProgress, 0.0, 1.0);
        if (this.focused && this.cursorVisible || this.animProgress > 0.0) {
            guiRenderer.setAlpha(this.animProgress);
            guiRenderer.quad(this.x + d2 + this.getCursorTextWidth() - d3, this.y + d2, meteorGuiTheme.scale(1.0), meteorGuiTheme.textHeight(), meteorGuiTheme.textColor.get());
            guiRenderer.setAlpha(1.0);
        }
    }

    public WMeteorTextBox(String string, CharFilter charFilter) {
        super(string, charFilter);
    }

    @Override
    protected void onCursorChanged() {
        this.cursorVisible = true;
        this.cursorTimer = 0.0;
    }
}

