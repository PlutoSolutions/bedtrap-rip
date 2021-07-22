/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.screens.settings;

import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WindowScreen;
import minegame159.meteorclient.gui.renderer.GuiRenderer;
import minegame159.meteorclient.gui.widgets.WQuad;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.input.WIntEdit;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;

public class ColorSettingScreen
extends WindowScreen {
    private final /* synthetic */ WHueQuad hueQuad;
    private final /* synthetic */ WIntEdit aItb;
    private static final /* synthetic */ Color WHITE;
    private final /* synthetic */ WIntEdit gItb;
    private static final /* synthetic */ Color[] HUE_COLORS;
    private final /* synthetic */ WIntEdit bItb;
    private final /* synthetic */ WIntEdit rItb;
    private final /* synthetic */ WQuad displayQuad;
    private static final /* synthetic */ Color BLACK;
    private final /* synthetic */ Setting<SettingColor> setting;
    private final /* synthetic */ WBrightnessQuad brightnessQuad;
    private final /* synthetic */ WCheckbox rainbow;
    public /* synthetic */ Runnable action;

    private void callAction() {
        ColorSettingScreen llllllllllllllllllIllIllIIIIIlII;
        if (llllllllllllllllllIllIllIIIIIlII.action != null) {
            llllllllllllllllllIllIllIIIIIlII.action.run();
        }
    }

    static {
        HUE_COLORS = new Color[]{new Color(255, 0, 0), new Color(255, 255, 0), new Color(0, 255, 0), new Color(0, 255, 255), new Color(0, 0, 255), new Color(255, 0, 255), new Color(255, 0, 0)};
        WHITE = new Color(255, 255, 255);
        BLACK = new Color(0, 0, 0);
    }

    public ColorSettingScreen(GuiTheme llllllllllllllllllIllIllIIIlIIlI, Setting<SettingColor> llllllllllllllllllIllIllIIIllIIl) {
        super(llllllllllllllllllIllIllIIIlIIlI, "Select Color");
        ColorSettingScreen llllllllllllllllllIllIllIIIllIll;
        llllllllllllllllllIllIllIIIllIll.setting = llllllllllllllllllIllIllIIIllIIl;
        llllllllllllllllllIllIllIIIllIll.displayQuad = llllllllllllllllllIllIllIIIllIll.add(llllllllllllllllllIllIllIIIlIIlI.quad(llllllllllllllllllIllIllIIIllIIl.get())).expandX().widget();
        llllllllllllllllllIllIllIIIllIll.brightnessQuad = llllllllllllllllllIllIllIIIllIll.add(llllllllllllllllllIllIllIIIllIll.new WBrightnessQuad()).expandX().widget();
        llllllllllllllllllIllIllIIIllIll.hueQuad = llllllllllllllllllIllIllIIIllIll.add(llllllllllllllllllIllIllIIIllIll.new WHueQuad()).expandX().widget();
        WTable llllllllllllllllllIllIllIIIllIII = llllllllllllllllllIllIllIIIllIll.add(llllllllllllllllllIllIllIIIlIIlI.table()).expandX().widget();
        llllllllllllllllllIllIllIIIllIII.add(llllllllllllllllllIllIllIIIlIIlI.label("R:"));
        llllllllllllllllllIllIllIIIllIll.rItb = llllllllllllllllllIllIllIIIllIII.add(llllllllllllllllllIllIllIIIlIIlI.intEdit(llllllllllllllllllIllIllIIIllIIl.get().r, 0, 255)).expandX().widget();
        llllllllllllllllllIllIllIIIllIll.rItb.min = 0;
        llllllllllllllllllIllIllIIIllIll.rItb.max = 255;
        llllllllllllllllllIllIllIIIllIll.rItb.action = llllllllllllllllllIllIllIIIllIll::rgbaChanged;
        llllllllllllllllllIllIllIIIllIII.row();
        llllllllllllllllllIllIllIIIllIII.add(llllllllllllllllllIllIllIIIlIIlI.label("G:"));
        llllllllllllllllllIllIllIIIllIll.gItb = llllllllllllllllllIllIllIIIllIII.add(llllllllllllllllllIllIllIIIlIIlI.intEdit(llllllllllllllllllIllIllIIIllIIl.get().g, 0, 255)).expandX().widget();
        llllllllllllllllllIllIllIIIllIll.gItb.min = 0;
        llllllllllllllllllIllIllIIIllIll.gItb.max = 255;
        llllllllllllllllllIllIllIIIllIll.gItb.action = llllllllllllllllllIllIllIIIllIll::rgbaChanged;
        llllllllllllllllllIllIllIIIllIII.row();
        llllllllllllllllllIllIllIIIllIII.add(llllllllllllllllllIllIllIIIlIIlI.label("B:"));
        llllllllllllllllllIllIllIIIllIll.bItb = llllllllllllllllllIllIllIIIllIII.add(llllllllllllllllllIllIllIIIlIIlI.intEdit(llllllllllllllllllIllIllIIIllIIl.get().b, 0, 255)).expandX().widget();
        llllllllllllllllllIllIllIIIllIll.bItb.min = 0;
        llllllllllllllllllIllIllIIIllIll.bItb.max = 255;
        llllllllllllllllllIllIllIIIllIll.bItb.action = llllllllllllllllllIllIllIIIllIll::rgbaChanged;
        llllllllllllllllllIllIllIIIllIII.row();
        llllllllllllllllllIllIllIIIllIII.add(llllllllllllllllllIllIllIIIlIIlI.label("A:"));
        llllllllllllllllllIllIllIIIllIll.aItb = llllllllllllllllllIllIllIIIllIII.add(llllllllllllllllllIllIllIIIlIIlI.intEdit(llllllllllllllllllIllIllIIIllIIl.get().a, 0, 255)).expandX().widget();
        llllllllllllllllllIllIllIIIllIll.aItb.min = 0;
        llllllllllllllllllIllIllIIIllIll.aItb.max = 255;
        llllllllllllllllllIllIllIIIllIll.aItb.action = llllllllllllllllllIllIllIIIllIll::rgbaChanged;
        WHorizontalList llllllllllllllllllIllIllIIIlIlll = llllllllllllllllllIllIllIIIllIll.add(llllllllllllllllllIllIllIIIlIIlI.horizontalList()).expandX().widget();
        llllllllllllllllllIllIllIIIlIlll.add(llllllllllllllllllIllIllIIIlIIlI.label("Rainbow: "));
        llllllllllllllllllIllIllIIIllIll.rainbow = llllllllllllllllllIllIllIIIlIIlI.checkbox(llllllllllllllllllIllIllIIIllIIl.get().rainbow);
        llllllllllllllllllIllIllIIIllIll.rainbow.action = () -> {
            ColorSettingScreen llllllllllllllllllIllIlIllIIlIll;
            ((SettingColor)llllllllllllllllllIllIlIllIIllII.get()).rainbow = llllllllllllllllllIllIlIllIIlIll.rainbow.checked;
            llllllllllllllllllIllIllIIIllIIl.changed();
        };
        llllllllllllllllllIllIllIIIlIlll.add(llllllllllllllllllIllIllIIIllIll.rainbow).expandCellX().right();
        WHorizontalList llllllllllllllllllIllIllIIIlIllI = llllllllllllllllllIllIllIIIllIll.add(llllllllllllllllllIllIllIIIlIIlI.horizontalList()).expandX().widget();
        WButton llllllllllllllllllIllIllIIIlIlIl = llllllllllllllllllIllIllIIIlIllI.add(llllllllllllllllllIllIllIIIlIIlI.button("Back")).expandX().widget();
        llllllllllllllllllIllIllIIIlIlIl.action = llllllllllllllllllIllIllIIIllIll::method_25419;
        WButton llllllllllllllllllIllIllIIIlIlII = llllllllllllllllllIllIllIIIlIllI.add(llllllllllllllllllIllIllIIIlIIlI.button(GuiRenderer.RESET)).widget();
        llllllllllllllllllIllIllIIIlIlII.action = () -> {
            ColorSettingScreen llllllllllllllllllIllIlIllIlIIll;
            llllllllllllllllllIllIllIIIllIIl.reset();
            llllllllllllllllllIllIlIllIlIIll.setFromSetting();
            llllllllllllllllllIllIlIllIlIIll.callAction();
        };
        llllllllllllllllllIllIllIIIllIll.hueQuad.calculateFromSetting(false);
        llllllllllllllllllIllIllIIIllIll.brightnessQuad.calculateFromColor(llllllllllllllllllIllIllIIIllIIl.get(), false);
    }

    private void rgbaChanged() {
        ColorSettingScreen llllllllllllllllllIllIlIlllllIll;
        Color llllllllllllllllllIllIlIllllllII = llllllllllllllllllIllIlIlllllIll.setting.get();
        llllllllllllllllllIllIlIllllllII.r = llllllllllllllllllIllIlIlllllIll.rItb.get();
        llllllllllllllllllIllIlIllllllII.g = llllllllllllllllllIllIlIlllllIll.gItb.get();
        llllllllllllllllllIllIlIllllllII.b = llllllllllllllllllIllIlIlllllIll.bItb.get();
        llllllllllllllllllIllIlIllllllII.a = llllllllllllllllllIllIlIlllllIll.aItb.get();
        llllllllllllllllllIllIlIllllllII.validate();
        if (llllllllllllllllllIllIlIllllllII.r != llllllllllllllllllIllIlIlllllIll.rItb.get()) {
            llllllllllllllllllIllIlIlllllIll.rItb.set(llllllllllllllllllIllIlIllllllII.r);
        }
        if (llllllllllllllllllIllIlIllllllII.g != llllllllllllllllllIllIlIlllllIll.gItb.get()) {
            llllllllllllllllllIllIlIlllllIll.gItb.set(llllllllllllllllllIllIlIllllllII.g);
        }
        if (llllllllllllllllllIllIlIllllllII.b != llllllllllllllllllIllIlIlllllIll.bItb.get()) {
            llllllllllllllllllIllIlIlllllIll.bItb.set(llllllllllllllllllIllIlIllllllII.b);
        }
        if (llllllllllllllllllIllIlIllllllII.a != llllllllllllllllllIllIlIlllllIll.aItb.get()) {
            llllllllllllllllllIllIlIlllllIll.aItb.set(llllllllllllllllllIllIlIllllllII.a);
        }
        llllllllllllllllllIllIlIlllllIll.displayQuad.color.set(llllllllllllllllllIllIlIllllllII);
        llllllllllllllllllIllIlIlllllIll.hueQuad.calculateFromSetting(true);
        llllllllllllllllllIllIlIlllllIll.brightnessQuad.calculateFromColor(llllllllllllllllllIllIlIlllllIll.setting.get(), true);
        llllllllllllllllllIllIlIlllllIll.setting.changed();
        llllllllllllllllllIllIlIlllllIll.callAction();
    }

    private void setFromSetting() {
        ColorSettingScreen llllllllllllllllllIllIllIIIIlIIl;
        SettingColor llllllllllllllllllIllIllIIIIlIII = llllllllllllllllllIllIllIIIIlIIl.setting.get();
        if (llllllllllllllllllIllIllIIIIlIII.r != llllllllllllllllllIllIllIIIIlIIl.rItb.get()) {
            llllllllllllllllllIllIllIIIIlIIl.rItb.set(llllllllllllllllllIllIllIIIIlIII.r);
        }
        if (llllllllllllllllllIllIllIIIIlIII.g != llllllllllllllllllIllIllIIIIlIIl.gItb.get()) {
            llllllllllllllllllIllIllIIIIlIIl.gItb.set(llllllllllllllllllIllIllIIIIlIII.g);
        }
        if (llllllllllllllllllIllIllIIIIlIII.b != llllllllllllllllllIllIllIIIIlIIl.bItb.get()) {
            llllllllllllllllllIllIllIIIIlIIl.bItb.set(llllllllllllllllllIllIllIIIIlIII.b);
        }
        if (llllllllllllllllllIllIllIIIIlIII.a != llllllllllllllllllIllIllIIIIlIIl.aItb.get()) {
            llllllllllllllllllIllIllIIIIlIIl.aItb.set(llllllllllllllllllIllIllIIIIlIII.a);
        }
        llllllllllllllllllIllIllIIIIlIIl.rainbow.checked = llllllllllllllllllIllIllIIIIlIII.rainbow;
        llllllllllllllllllIllIllIIIIlIIl.displayQuad.color.set(llllllllllllllllllIllIllIIIIlIIl.setting.get());
        llllllllllllllllllIllIllIIIIlIIl.hueQuad.calculateFromSetting(true);
        llllllllllllllllllIllIllIIIIlIIl.brightnessQuad.calculateFromColor(llllllllllllllllllIllIllIIIIlIIl.setting.get(), true);
    }

    public void method_25393() {
        ColorSettingScreen llllllllllllllllllIllIllIIIIIIII;
        super.method_25393();
        if (llllllllllllllllllIllIllIIIIIIII.setting.get().rainbow) {
            llllllllllllllllllIllIllIIIIIIII.setFromSetting();
        }
    }

    private void hsvChanged() {
        ColorSettingScreen llllllllllllllllllIllIlIlllIIIII;
        double llllllllllllllllllIllIlIlllIIllI = 0.0;
        double llllllllllllllllllIllIlIlllIIlIl = 0.0;
        double llllllllllllllllllIllIlIlllIIlII = 0.0;
        boolean llllllllllllllllllIllIlIlllIIIll = false;
        if (llllllllllllllllllIllIlIlllIIIII.brightnessQuad.saturation <= 0.0) {
            llllllllllllllllllIllIlIlllIIllI = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value;
            llllllllllllllllllIllIlIlllIIlIl = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value;
            llllllllllllllllllIllIlIlllIIlII = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value;
            llllllllllllllllllIllIlIlllIIIll = true;
        }
        if (!llllllllllllllllllIllIlIlllIIIll) {
            double llllllllllllllllllIllIlIlllIllIl = llllllllllllllllllIllIlIlllIIIII.hueQuad.hueAngle;
            if (llllllllllllllllllIllIlIlllIllIl >= 360.0) {
                llllllllllllllllllIllIlIlllIllIl = 0.0;
            }
            int llllllllllllllllllIllIlIlllIlIII = (int)(llllllllllllllllllIllIlIlllIllIl /= 60.0);
            double llllllllllllllllllIllIlIlllIlIIl = llllllllllllllllllIllIlIlllIllIl - (double)llllllllllllllllllIllIlIlllIlIII;
            double llllllllllllllllllIllIlIlllIllII = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value * (1.0 - llllllllllllllllllIllIlIlllIIIII.brightnessQuad.saturation);
            double llllllllllllllllllIllIlIlllIlIll = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value * (1.0 - llllllllllllllllllIllIlIlllIIIII.brightnessQuad.saturation * llllllllllllllllllIllIlIlllIlIIl);
            double llllllllllllllllllIllIlIlllIlIlI = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value * (1.0 - llllllllllllllllllIllIlIlllIIIII.brightnessQuad.saturation * (1.0 - llllllllllllllllllIllIlIlllIlIIl));
            switch (llllllllllllllllllIllIlIlllIlIII) {
                case 0: {
                    llllllllllllllllllIllIlIlllIIllI = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value;
                    llllllllllllllllllIllIlIlllIIlIl = llllllllllllllllllIllIlIlllIlIlI;
                    llllllllllllllllllIllIlIlllIIlII = llllllllllllllllllIllIlIlllIllII;
                    break;
                }
                case 1: {
                    llllllllllllllllllIllIlIlllIIllI = llllllllllllllllllIllIlIlllIlIll;
                    llllllllllllllllllIllIlIlllIIlIl = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value;
                    llllllllllllllllllIllIlIlllIIlII = llllllllllllllllllIllIlIlllIllII;
                    break;
                }
                case 2: {
                    llllllllllllllllllIllIlIlllIIllI = llllllllllllllllllIllIlIlllIllII;
                    llllllllllllllllllIllIlIlllIIlIl = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value;
                    llllllllllllllllllIllIlIlllIIlII = llllllllllllllllllIllIlIlllIlIlI;
                    break;
                }
                case 3: {
                    llllllllllllllllllIllIlIlllIIllI = llllllllllllllllllIllIlIlllIllII;
                    llllllllllllllllllIllIlIlllIIlIl = llllllllllllllllllIllIlIlllIlIll;
                    llllllllllllllllllIllIlIlllIIlII = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value;
                    break;
                }
                case 4: {
                    llllllllllllllllllIllIlIlllIIllI = llllllllllllllllllIllIlIlllIlIlI;
                    llllllllllllllllllIllIlIlllIIlIl = llllllllllllllllllIllIlIlllIllII;
                    llllllllllllllllllIllIlIlllIIlII = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value;
                    break;
                }
                default: {
                    llllllllllllllllllIllIlIlllIIllI = llllllllllllllllllIllIlIlllIIIII.brightnessQuad.value;
                    llllllllllllllllllIllIlIlllIIlIl = llllllllllllllllllIllIlIlllIllII;
                    llllllllllllllllllIllIlIlllIIlII = llllllllllllllllllIllIlIlllIlIll;
                }
            }
        }
        Color llllllllllllllllllIllIlIlllIIIlI = llllllllllllllllllIllIlIlllIIIII.setting.get();
        llllllllllllllllllIllIlIlllIIIlI.r = (int)(llllllllllllllllllIllIlIlllIIllI * 255.0);
        llllllllllllllllllIllIlIlllIIIlI.g = (int)(llllllllllllllllllIllIlIlllIIlIl * 255.0);
        llllllllllllllllllIllIlIlllIIIlI.b = (int)(llllllllllllllllllIllIlIlllIIlII * 255.0);
        llllllllllllllllllIllIlIlllIIIlI.validate();
        llllllllllllllllllIllIlIlllIIIII.rItb.set(llllllllllllllllllIllIlIlllIIIlI.r);
        llllllllllllllllllIllIlIlllIIIII.gItb.set(llllllllllllllllllIllIlIlllIIIlI.g);
        llllllllllllllllllIllIlIlllIIIII.bItb.set(llllllllllllllllllIllIlIlllIIIlI.b);
        llllllllllllllllllIllIlIlllIIIII.displayQuad.color.set(llllllllllllllllllIllIlIlllIIIlI);
        llllllllllllllllllIllIlIlllIIIII.setting.changed();
        llllllllllllllllllIllIlIlllIIIII.callAction();
    }

    private class WBrightnessQuad
    extends WWidget {
        /* synthetic */ double fixedHeight;
        /* synthetic */ boolean dragging;
        /* synthetic */ boolean calculateHandlePosOnLayout;
        /* synthetic */ double handleX;
        /* synthetic */ double handleY;
        /* synthetic */ double saturation;
        /* synthetic */ double lastMouseY;
        /* synthetic */ double value;
        /* synthetic */ double lastMouseX;

        @Override
        protected void onCalculateWidgetPositions() {
            WBrightnessQuad llllIIIlllIllll;
            if (llllIIIlllIllll.calculateHandlePosOnLayout) {
                llllIIIlllIllll.handleX = llllIIIlllIllll.saturation * llllIIIlllIllll.width;
                llllIIIlllIllll.handleY = (1.0 - llllIIIlllIllll.value) * llllIIIlllIllll.height;
                llllIIIlllIllll.calculateHandlePosOnLayout = false;
            }
            super.onCalculateWidgetPositions();
        }

        @Override
        public boolean onMouseReleased(double llllIIIlllIIIll, double llllIIIlllIIIlI, int llllIIIlllIIIIl) {
            WBrightnessQuad llllIIIlllIIlII;
            if (llllIIIlllIIlII.dragging) {
                llllIIIlllIIlII.dragging = false;
            }
            return false;
        }

        void calculateFromColor(Color llllIIIllllIllI, boolean llllIIIlllllIll) {
            double llllIIIlllllIlI = Math.min(Math.min(llllIIIllllIllI.r, llllIIIllllIllI.g), llllIIIllllIllI.b);
            double llllIIIlllllIIl = Math.max(Math.max(llllIIIllllIllI.r, llllIIIllllIllI.g), llllIIIllllIllI.b);
            double llllIIIlllllIII = llllIIIlllllIIl - llllIIIlllllIlI;
            llllIIIllllIlll.value = llllIIIlllllIIl / 255.0;
            llllIIIllllIlll.saturation = llllIIIlllllIII == 0.0 ? 0.0 : llllIIIlllllIII / llllIIIlllllIIl;
            if (llllIIIlllllIll) {
                WBrightnessQuad llllIIIllllIlll;
                llllIIIllllIlll.handleX = llllIIIllllIlll.saturation * llllIIIllllIlll.width;
                llllIIIllllIlll.handleY = (1.0 - llllIIIllllIlll.value) * llllIIIllllIlll.height;
            } else {
                llllIIIllllIlll.calculateHandlePosOnLayout = true;
            }
        }

        @Override
        protected void onCalculateSize() {
            WBrightnessQuad llllIIlIIIIIlll;
            double llllIIlIIIIIllI;
            llllIIlIIIIIlll.width = llllIIlIIIIIllI = llllIIlIIIIIlll.theme.scale(75.0);
            llllIIlIIIIIlll.height = llllIIlIIIIIllI;
            if (llllIIlIIIIIlll.fixedHeight != -1.0) {
                llllIIlIIIIIlll.height = llllIIlIIIIIlll.fixedHeight;
                llllIIlIIIIIlll.fixedHeight = -1.0;
            }
        }

        @Override
        protected void onRender(GuiRenderer llllIIIlIllllIl, double llllIIIllIIIIlI, double llllIIIllIIIIIl, double llllIIIllIIIIII) {
            WBrightnessQuad llllIIIlIlllllI;
            if (llllIIIlIlllllI.height != llllIIIlIlllllI.width) {
                llllIIIlIlllllI.fixedHeight = llllIIIlIlllllI.width;
                llllIIIlIlllllI.invalidate();
            }
            llllIIIlIlllllI.ColorSettingScreen.this.hueQuad.calculateColor();
            llllIIIlIllllIl.quad(llllIIIlIlllllI.x, llllIIIlIlllllI.y, llllIIIlIlllllI.width, llllIIIlIlllllI.height, WHITE, llllIIIlIlllllI.ColorSettingScreen.this.hueQuad.color, BLACK, BLACK);
            double llllIIIlIllllll = llllIIIlIlllllI.theme.scale(2.0);
            llllIIIlIllllIl.quad(llllIIIlIlllllI.x + llllIIIlIlllllI.handleX - llllIIIlIllllll / 2.0, llllIIIlIlllllI.y + llllIIIlIlllllI.handleY - llllIIIlIllllll / 2.0, llllIIIlIllllll, llllIIIlIllllll, WHITE);
        }

        @Override
        public boolean onMouseClicked(double llllIIIlllIlIll, double llllIIIlllIlIlI, int llllIIIlllIlIIl, boolean llllIIIlllIlIII) {
            WBrightnessQuad llllIIIlllIIlll;
            if (llllIIIlllIlIII) {
                return false;
            }
            if (llllIIIlllIIlll.mouseOver) {
                llllIIIlllIIlll.dragging = true;
                llllIIIlllIIlll.handleX = llllIIIlllIIlll.lastMouseX - llllIIIlllIIlll.x;
                llllIIIlllIIlll.handleY = llllIIIlllIIlll.lastMouseY - llllIIIlllIIlll.y;
                llllIIIlllIIlll.handleMoved();
                return true;
            }
            return false;
        }

        void handleMoved() {
            WBrightnessQuad llllIIIllIIlIlI;
            double llllIIIllIIllII = llllIIIllIIlIlI.handleX / llllIIIllIIlIlI.width;
            double llllIIIllIIlIll = llllIIIllIIlIlI.handleY / llllIIIllIIlIlI.height;
            llllIIIllIIlIlI.saturation = llllIIIllIIllII;
            llllIIIllIIlIlI.value = 1.0 - llllIIIllIIlIll;
            llllIIIllIIlIlI.ColorSettingScreen.this.hsvChanged();
        }

        private WBrightnessQuad() {
            WBrightnessQuad llllIIlIIIIllII;
            llllIIlIIIIllII.fixedHeight = -1.0;
        }

        @Override
        public void onMouseMoved(double llllIIIllIllIIl, double llllIIIllIlIIll, double llllIIIllIlIlll, double llllIIIllIlIllI) {
            WBrightnessQuad llllIIIllIlIlIl;
            if (llllIIIllIlIlIl.dragging) {
                if (llllIIIllIllIIl >= llllIIIllIlIlIl.x && llllIIIllIllIIl <= llllIIIllIlIlIl.x + llllIIIllIlIlIl.width) {
                    llllIIIllIlIlIl.handleX += llllIIIllIllIIl - llllIIIllIlIlll;
                } else if (llllIIIllIlIlIl.handleX > 0.0 && llllIIIllIllIIl < llllIIIllIlIlIl.x) {
                    llllIIIllIlIlIl.handleX = 0.0;
                } else if (llllIIIllIlIlIl.handleX < llllIIIllIlIlIl.width && llllIIIllIllIIl > llllIIIllIlIlIl.x + llllIIIllIlIlIl.width) {
                    llllIIIllIlIlIl.handleX = llllIIIllIlIlIl.width;
                }
                if (llllIIIllIlIIll >= llllIIIllIlIlIl.y && llllIIIllIlIIll <= llllIIIllIlIlIl.y + llllIIIllIlIlIl.height) {
                    llllIIIllIlIlIl.handleY += llllIIIllIlIIll - llllIIIllIlIllI;
                } else if (llllIIIllIlIlIl.handleY > 0.0 && llllIIIllIlIIll < llllIIIllIlIlIl.y) {
                    llllIIIllIlIlIl.handleY = 0.0;
                } else if (llllIIIllIlIlIl.handleY < llllIIIllIlIlIl.height && llllIIIllIlIIll > llllIIIllIlIlIl.y + llllIIIllIlIlIl.height) {
                    llllIIIllIlIlIl.handleY = llllIIIllIlIlIl.height;
                }
                llllIIIllIlIlIl.handleMoved();
            }
            llllIIIllIlIlIl.lastMouseX = llllIIIllIllIIl;
            llllIIIllIlIlIl.lastMouseY = llllIIIllIlIIll;
        }
    }

    private class WHueQuad
    extends WWidget {
        private /* synthetic */ boolean calculateHandleXOnLayout;
        private /* synthetic */ double handleX;
        private /* synthetic */ boolean dragging;
        private /* synthetic */ double hueAngle;
        private final /* synthetic */ Color color;
        private /* synthetic */ double lastMouseX;

        @Override
        public void onMouseMoved(double lIIIllIlIIllll, double lIIIllIlIlIIll, double lIIIllIlIIlllI, double lIIIllIlIlIIIl) {
            WHueQuad lIIIllIlIlIlIl;
            if (lIIIllIlIlIlIl.dragging) {
                if (lIIIllIlIIllll >= lIIIllIlIlIlIl.x && lIIIllIlIIllll <= lIIIllIlIlIlIl.x + lIIIllIlIlIlIl.width) {
                    lIIIllIlIlIlIl.handleX += lIIIllIlIIllll - lIIIllIlIIlllI;
                    lIIIllIlIlIlIl.handleX = Utils.clamp(lIIIllIlIlIlIl.handleX, 0.0, lIIIllIlIlIlIl.width);
                } else if (lIIIllIlIlIlIl.handleX > 0.0 && lIIIllIlIIllll < lIIIllIlIlIlIl.x) {
                    lIIIllIlIlIlIl.handleX = 0.0;
                } else if (lIIIllIlIlIlIl.handleX < lIIIllIlIlIlIl.width && lIIIllIlIIllll > lIIIllIlIlIlIl.x + lIIIllIlIlIlIl.width) {
                    lIIIllIlIlIlIl.handleX = lIIIllIlIlIlIl.width;
                }
                lIIIllIlIlIlIl.calculateHueAngleFromHandleX();
                lIIIllIlIlIlIl.ColorSettingScreen.this.hsvChanged();
            }
            lIIIllIlIlIlIl.lastMouseX = lIIIllIlIIllll;
        }

        @Override
        protected void onCalculateWidgetPositions() {
            WHueQuad lIIIlllIIlIlll;
            if (lIIIlllIIlIlll.calculateHandleXOnLayout) {
                double lIIIlllIIllIII = lIIIlllIIlIlll.hueAngle / 360.0;
                lIIIlllIIlIlll.handleX = lIIIlllIIllIII * lIIIlllIIlIlll.width;
                lIIIlllIIlIlll.calculateHandleXOnLayout = false;
            }
            super.onCalculateWidgetPositions();
        }

        @Override
        public boolean onMouseReleased(double lIIIllIlIlllII, double lIIIllIlIllIll, int lIIIllIlIllIlI) {
            WHueQuad lIIIllIlIllIIl;
            if (lIIIllIlIllIIl.dragging) {
                lIIIllIlIllIIl.dragging = false;
            }
            return lIIIllIlIllIIl.mouseOver;
        }

        private WHueQuad() {
            WHueQuad lIIIlllIllIlll;
            lIIIlllIllIlll.color = new Color();
        }

        void calculateHueAngleFromHandleX() {
            WHueQuad lIIIllIlIIlIll;
            double lIIIllIlIIlIlI = lIIIllIlIIlIll.handleX / (lIIIllIlIIlIll.width - 4.0);
            lIIIllIlIIlIll.hueAngle = lIIIllIlIIlIlI * 360.0;
        }

        void calculateFromSetting(boolean lIIIlllIlIIIIl) {
            WHueQuad lIIIlllIlIlIIl;
            Color lIIIlllIlIIlll = (Color)lIIIlllIlIlIIl.ColorSettingScreen.this.setting.get();
            boolean lIIIlllIlIIllI = false;
            double lIIIlllIlIIlIl = Math.min(lIIIlllIlIIlll.r, lIIIlllIlIIlll.g);
            lIIIlllIlIIlIl = lIIIlllIlIIlIl < (double)lIIIlllIlIIlll.b ? lIIIlllIlIIlIl : (double)lIIIlllIlIIlll.b;
            double lIIIlllIlIIlII = Math.max(lIIIlllIlIIlll.r, lIIIlllIlIIlll.g);
            double lIIIlllIlIIIll = (lIIIlllIlIIlII = lIIIlllIlIIlII > (double)lIIIlllIlIIlll.b ? lIIIlllIlIIlII : (double)lIIIlllIlIIlll.b) - lIIIlllIlIIlIl;
            if (lIIIlllIlIIIll < 1.0E-5) {
                lIIIlllIlIlIIl.hueAngle = 0.0;
                lIIIlllIlIIllI = true;
            }
            if (!lIIIlllIlIIllI) {
                if (lIIIlllIlIIlII <= 0.0) {
                    lIIIlllIlIlIIl.hueAngle = 0.0;
                    lIIIlllIlIIllI = true;
                }
                if (!lIIIlllIlIIllI) {
                    lIIIlllIlIlIIl.hueAngle = (double)lIIIlllIlIIlll.r >= lIIIlllIlIIlII ? (double)(lIIIlllIlIIlll.g - lIIIlllIlIIlll.b) / lIIIlllIlIIIll : ((double)lIIIlllIlIIlll.g >= lIIIlllIlIIlII ? 2.0 + (double)(lIIIlllIlIIlll.b - lIIIlllIlIIlll.r) / lIIIlllIlIIIll : 4.0 + (double)(lIIIlllIlIIlll.r - lIIIlllIlIIlll.g) / lIIIlllIlIIIll);
                    lIIIlllIlIlIIl.hueAngle *= 60.0;
                    if (lIIIlllIlIlIIl.hueAngle < 0.0) {
                        lIIIlllIlIlIIl.hueAngle += 360.0;
                    }
                }
            }
            if (lIIIlllIlIIIIl) {
                double lIIIlllIlIlIlI = lIIIlllIlIlIIl.hueAngle / 360.0;
                lIIIlllIlIlIIl.handleX = lIIIlllIlIlIlI * lIIIlllIlIlIIl.width;
            } else {
                lIIIlllIlIlIIl.calculateHandleXOnLayout = true;
            }
        }

        @Override
        public boolean onMouseClicked(double lIIIllIllIIlII, double lIIIllIllIIIll, int lIIIllIllIIIlI, boolean lIIIllIlIlllll) {
            WHueQuad lIIIllIllIIlIl;
            if (lIIIllIlIlllll) {
                return false;
            }
            if (lIIIllIllIIlIl.mouseOver) {
                lIIIllIllIIlIl.dragging = true;
                lIIIllIllIIlIl.handleX = lIIIllIllIIlIl.lastMouseX - lIIIllIllIIlIl.x;
                lIIIllIllIIlIl.calculateHueAngleFromHandleX();
                lIIIllIllIIlIl.ColorSettingScreen.this.hsvChanged();
                return true;
            }
            return false;
        }

        @Override
        protected void onRender(GuiRenderer lIIIllIlIIIIII, double lIIIllIIllllll, double lIIIllIIlllllI, double lIIIllIIllllIl) {
            WHueQuad lIIIllIIlllIIl;
            double lIIIllIIllllII = lIIIllIIlllIIl.width / (double)(HUE_COLORS.length - 1);
            double lIIIllIIlllIll = lIIIllIIlllIIl.x;
            for (int lIIIllIlIIIIlI = 0; lIIIllIlIIIIlI < HUE_COLORS.length - 1; ++lIIIllIlIIIIlI) {
                lIIIllIlIIIIII.quad(lIIIllIIlllIll, lIIIllIIlllIIl.y, lIIIllIIllllII, lIIIllIIlllIIl.height, HUE_COLORS[lIIIllIlIIIIlI], HUE_COLORS[lIIIllIlIIIIlI + 1], HUE_COLORS[lIIIllIlIIIIlI + 1], HUE_COLORS[lIIIllIlIIIIlI]);
                lIIIllIIlllIll += lIIIllIIllllII;
            }
            double lIIIllIIlllIlI = lIIIllIIlllIIl.theme.scale(2.0);
            lIIIllIlIIIIII.quad(lIIIllIIlllIIl.x + lIIIllIIlllIIl.handleX - lIIIllIIlllIlI / 2.0, lIIIllIIlllIIl.y, lIIIllIIlllIlI, lIIIllIIlllIIl.height, WHITE);
        }

        void calculateColor() {
            double lIIIllIlllIIlI;
            double lIIIllIlllIIll;
            double lIIIllIlllIlII;
            WHueQuad lIIIllIllllIll;
            double lIIIllIllllIlI = lIIIllIllllIll.hueAngle;
            if (lIIIllIllllIlI >= 360.0) {
                lIIIllIllllIlI = 0.0;
            }
            int lIIIllIlllIlIl = (int)(lIIIllIllllIlI /= 60.0);
            double lIIIllIlllIllI = lIIIllIllllIlI - (double)lIIIllIlllIlIl;
            double lIIIllIllllIIl = 0.0;
            double lIIIllIllllIII = 1.0 * (1.0 - 1.0 * lIIIllIlllIllI);
            double lIIIllIlllIlll = 1.0 * (1.0 - 1.0 * (1.0 - lIIIllIlllIllI));
            switch (lIIIllIlllIlIl) {
                case 0: {
                    double lIIIlllIIIlIlI = 1.0;
                    double lIIIlllIIIlIIl = lIIIllIlllIlll;
                    double lIIIlllIIIlIII = lIIIllIllllIIl;
                    break;
                }
                case 1: {
                    double lIIIlllIIIIlll = lIIIllIllllIII;
                    double lIIIlllIIIIllI = 1.0;
                    double lIIIlllIIIIlIl = lIIIllIllllIIl;
                    break;
                }
                case 2: {
                    double lIIIlllIIIIlII = lIIIllIllllIIl;
                    double lIIIlllIIIIIll = 1.0;
                    double lIIIlllIIIIIlI = lIIIllIlllIlll;
                    break;
                }
                case 3: {
                    double lIIIlllIIIIIIl = lIIIllIllllIIl;
                    double lIIIlllIIIIIII = lIIIllIllllIII;
                    double lIIIllIlllllll = 1.0;
                    break;
                }
                case 4: {
                    double lIIIllIllllllI = lIIIllIlllIlll;
                    double lIIIllIlllllIl = lIIIllIllllIIl;
                    double lIIIllIlllllII = 1.0;
                    break;
                }
                default: {
                    lIIIllIlllIlII = 1.0;
                    lIIIllIlllIIll = lIIIllIllllIIl;
                    lIIIllIlllIIlI = lIIIllIllllIII;
                }
            }
            lIIIllIllllIll.color.r = (int)(lIIIllIlllIlII * 255.0);
            lIIIllIllllIll.color.g = (int)(lIIIllIlllIIll * 255.0);
            lIIIllIllllIll.color.b = (int)(lIIIllIlllIIlI * 255.0);
            lIIIllIllllIll.color.validate();
        }

        @Override
        protected void onCalculateSize() {
            WHueQuad lIIIlllIllIIll;
            lIIIlllIllIIll.width = lIIIlllIllIIll.theme.scale(75.0);
            lIIIlllIllIIll.height = lIIIlllIllIIll.theme.scale(10.0);
        }
    }
}

