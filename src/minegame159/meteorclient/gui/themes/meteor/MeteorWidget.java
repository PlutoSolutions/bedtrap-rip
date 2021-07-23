/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.themes.meteor;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.utils.BaseWidget;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.utils.render.color.SettingColor;

public interface MeteorWidget
extends BaseWidget {
    default public void renderBackground(GuiRenderer guiRenderer, WWidget wWidget, boolean bl, boolean bl2) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        double d = meteorGuiTheme.scale(2.0);
        guiRenderer.quad(wWidget.x + d, wWidget.y + d, wWidget.width - d * 2.0, wWidget.height - d * 2.0, meteorGuiTheme.backgroundColor.get(bl, bl2));
        SettingColor settingColor = meteorGuiTheme.outlineColor.get(bl, bl2);
        guiRenderer.quad(wWidget.x, wWidget.y, wWidget.width, d, settingColor);
        guiRenderer.quad(wWidget.x, wWidget.y + wWidget.height - d, wWidget.width, d, settingColor);
        guiRenderer.quad(wWidget.x, wWidget.y + d, d, wWidget.height - d * 2.0, settingColor);
        guiRenderer.quad(wWidget.x + wWidget.width - d, wWidget.y + d, d, wWidget.height - d * 2.0, settingColor);
    }

    default public MeteorGuiTheme theme() {
        return (MeteorGuiTheme)this.getTheme();
    }
}

