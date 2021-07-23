/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.themes.meteor.widgets.input;

import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.themes.meteor.MeteorGuiTheme;
import minegame159.meteorclient.gui.themes.meteor.MeteorWidget;
import minegame159.meteorclient.gui.widgets.input.WDropdown;
import minegame159.meteorclient.utils.render.color.SettingColor;

public class WMeteorDropdown<T>
extends WDropdown<T>
implements MeteorWidget {
    public WMeteorDropdown(T[] TArray, T t) {
        super(TArray, t);
    }

    @Override
    protected WDropdown.WDropdownValue createValueWidget() {
        return new WValue(this, null);
    }

    @Override
    protected WDropdown.WDropdownRoot createRootWidget() {
        return new WRoot(null);
    }

    @Override
    protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
        MeteorGuiTheme meteorGuiTheme = this.theme();
        double d4 = this.pad();
        double d5 = meteorGuiTheme.textHeight();
        this.renderBackground(guiRenderer, this, this.pressed, this.mouseOver);
        String string = this.get().toString();
        double d6 = meteorGuiTheme.textWidth(string);
        guiRenderer.text(string, this.x + d4 + this.maxValueWidth / 2.0 - d6 / 2.0, this.y + d4, meteorGuiTheme.textColor.get(), false);
        guiRenderer.rotatedQuad(this.x + d4 + this.maxValueWidth + d4, this.y + d4, d5, d5, 0.0, GuiRenderer.TRIANGLE, meteorGuiTheme.textColor.get());
    }

    private class WValue
    extends WDropdown.WDropdownValue
    implements MeteorWidget {
        final WMeteorDropdown this$0;

        WValue(WMeteorDropdown wMeteorDropdown, 1 var2_2) {
            this(wMeteorDropdown);
        }

        @Override
        protected void onCalculateSize() {
            double d = this.pad();
            this.width = d + this.theme.textWidth(this.value.toString()) + d;
            this.height = d + this.theme.textHeight() + d;
        }

        private WValue(WMeteorDropdown wMeteorDropdown) {
            this.this$0 = wMeteorDropdown;
            super(wMeteorDropdown);
        }

        @Override
        protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
            MeteorGuiTheme meteorGuiTheme = this.theme();
            SettingColor settingColor = meteorGuiTheme.backgroundColor.get(this.pressed, this.mouseOver, true);
            int n = settingColor.a;
            settingColor.a += settingColor.a / 2;
            settingColor.validate();
            guiRenderer.quad(this, settingColor);
            settingColor.a = n;
            String string = this.value.toString();
            guiRenderer.text(string, this.x + this.width / 2.0 - meteorGuiTheme.textWidth(string) / 2.0, this.y + this.pad(), meteorGuiTheme.textColor.get(), false);
        }
    }

    private static class WRoot
    extends WDropdown.WDropdownRoot
    implements MeteorWidget {
        private WRoot() {
        }

        WRoot(1 var1_1) {
            this();
        }

        @Override
        protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
            MeteorGuiTheme meteorGuiTheme = this.theme();
            double d4 = meteorGuiTheme.scale(2.0);
            SettingColor settingColor = meteorGuiTheme.outlineColor.get();
            guiRenderer.quad(this.x, this.y + this.height - d4, this.width, d4, settingColor);
            guiRenderer.quad(this.x, this.y, d4, this.height - d4, settingColor);
            guiRenderer.quad(this.x + this.width - d4, this.y, d4, this.height - d4, settingColor);
        }
    }
}

