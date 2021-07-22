/*
 * Decompiled with CFR 0.150.
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
    public WMeteorDropdown(T[] llllllllllllllllllllllIlllIlIlII, T llllllllllllllllllllllIlllIlIllI) {
        super(llllllllllllllllllllllIlllIlIlII, llllllllllllllllllllllIlllIlIllI);
        WMeteorDropdown llllllllllllllllllllllIlllIlIlIl;
    }

    @Override
    protected WDropdown.WDropdownValue createValueWidget() {
        WMeteorDropdown llllllllllllllllllllllIlllIlIIII;
        return llllllllllllllllllllllIlllIlIIII.new WValue();
    }

    @Override
    protected WDropdown.WDropdownRoot createRootWidget() {
        return new WRoot();
    }

    @Override
    protected void onRender(GuiRenderer llllllllllllllllllllllIlllIIIllI, double llllllllllllllllllllllIlllIIIlIl, double llllllllllllllllllllllIlllIIIlII, double llllllllllllllllllllllIlllIIIIll) {
        WMeteorDropdown llllllllllllllllllllllIllIllllIl;
        MeteorGuiTheme llllllllllllllllllllllIlllIIIIlI = llllllllllllllllllllllIllIllllIl.theme();
        double llllllllllllllllllllllIlllIIIIIl = llllllllllllllllllllllIllIllllIl.pad();
        double llllllllllllllllllllllIlllIIIIII = llllllllllllllllllllllIlllIIIIlI.textHeight();
        llllllllllllllllllllllIllIllllIl.renderBackground(llllllllllllllllllllllIlllIIIllI, llllllllllllllllllllllIllIllllIl, llllllllllllllllllllllIllIllllIl.pressed, llllllllllllllllllllllIllIllllIl.mouseOver);
        String llllllllllllllllllllllIllIllllll = llllllllllllllllllllllIllIllllIl.get().toString();
        double llllllllllllllllllllllIllIlllllI = llllllllllllllllllllllIlllIIIIlI.textWidth(llllllllllllllllllllllIllIllllll);
        llllllllllllllllllllllIlllIIIllI.text(llllllllllllllllllllllIllIllllll, llllllllllllllllllllllIllIllllIl.x + llllllllllllllllllllllIlllIIIIIl + llllllllllllllllllllllIllIllllIl.maxValueWidth / 2.0 - llllllllllllllllllllllIllIlllllI / 2.0, llllllllllllllllllllllIllIllllIl.y + llllllllllllllllllllllIlllIIIIIl, llllllllllllllllllllllIlllIIIIlI.textColor.get(), false);
        llllllllllllllllllllllIlllIIIllI.rotatedQuad(llllllllllllllllllllllIllIllllIl.x + llllllllllllllllllllllIlllIIIIIl + llllllllllllllllllllllIllIllllIl.maxValueWidth + llllllllllllllllllllllIlllIIIIIl, llllllllllllllllllllllIllIllllIl.y + llllllllllllllllllllllIlllIIIIIl, llllllllllllllllllllllIlllIIIIII, llllllllllllllllllllllIlllIIIIII, 0.0, GuiRenderer.TRIANGLE, llllllllllllllllllllllIlllIIIIlI.textColor.get());
    }

    private class WValue
    extends WDropdown.WDropdownValue
    implements MeteorWidget {
        @Override
        protected void onCalculateSize() {
            WValue lllllllllllllllllIllIlIIlIIlIlII;
            double lllllllllllllllllIllIlIIlIIlIIll = lllllllllllllllllIllIlIIlIIlIlII.pad();
            lllllllllllllllllIllIlIIlIIlIlII.width = lllllllllllllllllIllIlIIlIIlIIll + lllllllllllllllllIllIlIIlIIlIlII.theme.textWidth(lllllllllllllllllIllIlIIlIIlIlII.value.toString()) + lllllllllllllllllIllIlIIlIIlIIll;
            lllllllllllllllllIllIlIIlIIlIlII.height = lllllllllllllllllIllIlIIlIIlIIll + lllllllllllllllllIllIlIIlIIlIlII.theme.textHeight() + lllllllllllllllllIllIlIIlIIlIIll;
        }

        private WValue() {
            WValue lllllllllllllllllIllIlIIlIIllIII;
        }

        @Override
        protected void onRender(GuiRenderer lllllllllllllllllIllIlIIlIIIIIII, double lllllllllllllllllIllIlIIlIIIlIII, double lllllllllllllllllIllIlIIlIIIIlll, double lllllllllllllllllIllIlIIlIIIIllI) {
            WValue lllllllllllllllllIllIlIIlIIIIIIl;
            MeteorGuiTheme lllllllllllllllllIllIlIIlIIIIlIl = lllllllllllllllllIllIlIIlIIIIIIl.theme();
            SettingColor lllllllllllllllllIllIlIIlIIIIlII = lllllllllllllllllIllIlIIlIIIIlIl.backgroundColor.get(lllllllllllllllllIllIlIIlIIIIIIl.pressed, lllllllllllllllllIllIlIIlIIIIIIl.mouseOver, true);
            int lllllllllllllllllIllIlIIlIIIIIll = lllllllllllllllllIllIlIIlIIIIlII.a;
            lllllllllllllllllIllIlIIlIIIIlII.a += lllllllllllllllllIllIlIIlIIIIlII.a / 2;
            lllllllllllllllllIllIlIIlIIIIlII.validate();
            lllllllllllllllllIllIlIIlIIIIIII.quad(lllllllllllllllllIllIlIIlIIIIIIl, lllllllllllllllllIllIlIIlIIIIlII);
            lllllllllllllllllIllIlIIlIIIIlII.a = lllllllllllllllllIllIlIIlIIIIIll;
            String lllllllllllllllllIllIlIIlIIIIIlI = lllllllllllllllllIllIlIIlIIIIIIl.value.toString();
            lllllllllllllllllIllIlIIlIIIIIII.text(lllllllllllllllllIllIlIIlIIIIIlI, lllllllllllllllllIllIlIIlIIIIIIl.x + lllllllllllllllllIllIlIIlIIIIIIl.width / 2.0 - lllllllllllllllllIllIlIIlIIIIlIl.textWidth(lllllllllllllllllIllIlIIlIIIIIlI) / 2.0, lllllllllllllllllIllIlIIlIIIIIIl.y + lllllllllllllllllIllIlIIlIIIIIIl.pad(), lllllllllllllllllIllIlIIlIIIIlIl.textColor.get(), false);
        }
    }

    private static class WRoot
    extends WDropdown.WDropdownRoot
    implements MeteorWidget {
        private WRoot() {
            WRoot lllllllllllllllllIIIlllIIllllIII;
        }

        @Override
        protected void onRender(GuiRenderer lllllllllllllllllIIIlllIIlllIIIl, double lllllllllllllllllIIIlllIIlllIIII, double lllllllllllllllllIIIlllIIllIllll, double lllllllllllllllllIIIlllIIllIlllI) {
            WRoot lllllllllllllllllIIIlllIIlllIIlI;
            MeteorGuiTheme lllllllllllllllllIIIlllIIllIllIl = lllllllllllllllllIIIlllIIlllIIlI.theme();
            double lllllllllllllllllIIIlllIIllIllII = lllllllllllllllllIIIlllIIllIllIl.scale(2.0);
            SettingColor lllllllllllllllllIIIlllIIllIlIll = lllllllllllllllllIIIlllIIllIllIl.outlineColor.get();
            lllllllllllllllllIIIlllIIlllIIIl.quad(lllllllllllllllllIIIlllIIlllIIlI.x, lllllllllllllllllIIIlllIIlllIIlI.y + lllllllllllllllllIIIlllIIlllIIlI.height - lllllllllllllllllIIIlllIIllIllII, lllllllllllllllllIIIlllIIlllIIlI.width, lllllllllllllllllIIIlllIIllIllII, lllllllllllllllllIIIlllIIllIlIll);
            lllllllllllllllllIIIlllIIlllIIIl.quad(lllllllllllllllllIIIlllIIlllIIlI.x, lllllllllllllllllIIIlllIIlllIIlI.y, lllllllllllllllllIIIlllIIllIllII, lllllllllllllllllIIIlllIIlllIIlI.height - lllllllllllllllllIIIlllIIllIllII, lllllllllllllllllIIIlllIIllIlIll);
            lllllllllllllllllIIIlllIIlllIIIl.quad(lllllllllllllllllIIIlllIIlllIIlI.x + lllllllllllllllllIIIlllIIlllIIlI.width - lllllllllllllllllIIIlllIIllIllII, lllllllllllllllllIIIlllIIlllIIlI.y, lllllllllllllllllIIIlllIIllIllII, lllllllllllllllllIIIlllIIlllIIlI.height - lllllllllllllllllIIIlllIIllIllII, lllllllllllllllllIIIlllIIllIlIll);
        }
    }
}

