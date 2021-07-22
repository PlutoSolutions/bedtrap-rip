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
    private final WHueQuad hueQuad;
    private final WIntEdit aItb;
    private static final Color WHITE;
    private final WIntEdit gItb;
    private static final Color[] HUE_COLORS;
    private final WIntEdit bItb;
    private final WIntEdit rItb;
    private final WQuad displayQuad;
    private static final Color BLACK;
    private final Setting<SettingColor> setting;
    private final WBrightnessQuad brightnessQuad;
    private final WCheckbox rainbow;
    public Runnable action;

    static void access$300(ColorSettingScreen colorSettingScreen) {
        colorSettingScreen.hsvChanged();
    }

    private void callAction() {
        if (this.action != null) {
            this.action.run();
        }
    }

    static Color[] access$900() {
        return HUE_COLORS;
    }

    private void lambda$new$1(Setting setting) {
        setting.reset();
        this.setFromSetting();
        this.callAction();
    }

    static {
        HUE_COLORS = new Color[]{new Color(255, 0, 0), new Color(255, 255, 0), new Color(0, 255, 0), new Color(0, 255, 255), new Color(0, 0, 255), new Color(255, 0, 255), new Color(255, 0, 0)};
        WHITE = new Color(255, 255, 255);
        BLACK = new Color(0, 0, 0);
    }

    public ColorSettingScreen(GuiTheme guiTheme, Setting<SettingColor> setting) {
        super(guiTheme, "Select Color");
        this.setting = setting;
        this.displayQuad = this.add(guiTheme.quad(setting.get())).expandX().widget();
        this.brightnessQuad = this.add(new WBrightnessQuad(this, null)).expandX().widget();
        this.hueQuad = this.add(new WHueQuad(this, null)).expandX().widget();
        WTable wTable = this.add(guiTheme.table()).expandX().widget();
        wTable.add(guiTheme.label("R:"));
        this.rItb = wTable.add(guiTheme.intEdit(setting.get().r, 0, 255)).expandX().widget();
        this.rItb.min = 0;
        this.rItb.max = 255;
        this.rItb.action = this::rgbaChanged;
        wTable.row();
        wTable.add(guiTheme.label("G:"));
        this.gItb = wTable.add(guiTheme.intEdit(setting.get().g, 0, 255)).expandX().widget();
        this.gItb.min = 0;
        this.gItb.max = 255;
        this.gItb.action = this::rgbaChanged;
        wTable.row();
        wTable.add(guiTheme.label("B:"));
        this.bItb = wTable.add(guiTheme.intEdit(setting.get().b, 0, 255)).expandX().widget();
        this.bItb.min = 0;
        this.bItb.max = 255;
        this.bItb.action = this::rgbaChanged;
        wTable.row();
        wTable.add(guiTheme.label("A:"));
        this.aItb = wTable.add(guiTheme.intEdit(setting.get().a, 0, 255)).expandX().widget();
        this.aItb.min = 0;
        this.aItb.max = 255;
        this.aItb.action = this::rgbaChanged;
        WHorizontalList wHorizontalList = this.add(guiTheme.horizontalList()).expandX().widget();
        wHorizontalList.add(guiTheme.label("Rainbow: "));
        this.rainbow = guiTheme.checkbox(setting.get().rainbow);
        this.rainbow.action = () -> this.lambda$new$0(setting);
        wHorizontalList.add(this.rainbow).expandCellX().right();
        WHorizontalList wHorizontalList2 = this.add(guiTheme.horizontalList()).expandX().widget();
        WButton wButton = wHorizontalList2.add(guiTheme.button("Back")).expandX().widget();
        wButton.action = this::method_25419;
        WButton wButton2 = wHorizontalList2.add(guiTheme.button(GuiRenderer.RESET)).widget();
        wButton2.action = () -> this.lambda$new$1(setting);
        this.hueQuad.calculateFromSetting(false);
        this.brightnessQuad.calculateFromColor(setting.get(), false);
    }

    private void lambda$new$0(Setting setting) {
        ((SettingColor)setting.get()).rainbow = this.rainbow.checked;
        setting.changed();
    }

    private void rgbaChanged() {
        Color color = this.setting.get();
        color.r = this.rItb.get();
        color.g = this.gItb.get();
        color.b = this.bItb.get();
        color.a = this.aItb.get();
        color.validate();
        if (color.r != this.rItb.get()) {
            this.rItb.set(color.r);
        }
        if (color.g != this.gItb.get()) {
            this.gItb.set(color.g);
        }
        if (color.b != this.bItb.get()) {
            this.bItb.set(color.b);
        }
        if (color.a != this.aItb.get()) {
            this.aItb.set(color.a);
        }
        this.displayQuad.color.set(color);
        this.hueQuad.calculateFromSetting(true);
        this.brightnessQuad.calculateFromColor(this.setting.get(), true);
        this.setting.changed();
        this.callAction();
    }

    static Color access$700() {
        return BLACK;
    }

    private void setFromSetting() {
        SettingColor settingColor = this.setting.get();
        if (settingColor.r != this.rItb.get()) {
            this.rItb.set(settingColor.r);
        }
        if (settingColor.g != this.gItb.get()) {
            this.gItb.set(settingColor.g);
        }
        if (settingColor.b != this.bItb.get()) {
            this.bItb.set(settingColor.b);
        }
        if (settingColor.a != this.aItb.get()) {
            this.aItb.set(settingColor.a);
        }
        this.rainbow.checked = settingColor.rainbow;
        this.displayQuad.color.set(this.setting.get());
        this.hueQuad.calculateFromSetting(true);
        this.brightnessQuad.calculateFromColor(this.setting.get(), true);
    }

    static Color access$500() {
        return WHITE;
    }

    static Setting access$800(ColorSettingScreen colorSettingScreen) {
        return colorSettingScreen.setting;
    }

    public void method_25393() {
        super.method_25393();
        if (this.setting.get().rainbow) {
            this.setFromSetting();
        }
    }

    static WHueQuad access$400(ColorSettingScreen colorSettingScreen) {
        return colorSettingScreen.hueQuad;
    }

    private void hsvChanged() {
        double d = 0.0;
        double d2 = 0.0;
        double d3 = 0.0;
        boolean bl = false;
        if (this.brightnessQuad.saturation <= 0.0) {
            d = this.brightnessQuad.value;
            d2 = this.brightnessQuad.value;
            d3 = this.brightnessQuad.value;
            bl = true;
        }
        if (!bl) {
            double d4 = WHueQuad.access$200(this.hueQuad);
            if (d4 >= 360.0) {
                d4 = 0.0;
            }
            int n = (int)(d4 /= 60.0);
            double d5 = d4 - (double)n;
            double d6 = this.brightnessQuad.value * (1.0 - this.brightnessQuad.saturation);
            double d7 = this.brightnessQuad.value * (1.0 - this.brightnessQuad.saturation * d5);
            double d8 = this.brightnessQuad.value * (1.0 - this.brightnessQuad.saturation * (1.0 - d5));
            switch (n) {
                case 0: {
                    d = this.brightnessQuad.value;
                    d2 = d8;
                    d3 = d6;
                    break;
                }
                case 1: {
                    d = d7;
                    d2 = this.brightnessQuad.value;
                    d3 = d6;
                    break;
                }
                case 2: {
                    d = d6;
                    d2 = this.brightnessQuad.value;
                    d3 = d8;
                    break;
                }
                case 3: {
                    d = d6;
                    d2 = d7;
                    d3 = this.brightnessQuad.value;
                    break;
                }
                case 4: {
                    d = d8;
                    d2 = d6;
                    d3 = this.brightnessQuad.value;
                    break;
                }
                default: {
                    d = this.brightnessQuad.value;
                    d2 = d6;
                    d3 = d7;
                }
            }
        }
        Color color = this.setting.get();
        color.r = (int)(d * 255.0);
        color.g = (int)(d2 * 255.0);
        color.b = (int)(d3 * 255.0);
        color.validate();
        this.rItb.set(color.r);
        this.gItb.set(color.g);
        this.bItb.set(color.b);
        this.displayQuad.color.set(color);
        this.setting.changed();
        this.callAction();
    }

    private class WBrightnessQuad
    extends WWidget {
        double fixedHeight;
        boolean dragging;
        boolean calculateHandlePosOnLayout;
        double handleX;
        double handleY;
        double saturation;
        final ColorSettingScreen this$0;
        double lastMouseY;
        double value;
        double lastMouseX;

        @Override
        protected void onCalculateWidgetPositions() {
            if (this.calculateHandlePosOnLayout) {
                this.handleX = this.saturation * this.width;
                this.handleY = (1.0 - this.value) * this.height;
                this.calculateHandlePosOnLayout = false;
            }
            super.onCalculateWidgetPositions();
        }

        @Override
        public boolean onMouseReleased(double d, double d2, int n) {
            if (this.dragging) {
                this.dragging = false;
            }
            return false;
        }

        WBrightnessQuad(ColorSettingScreen colorSettingScreen, 1 var2_2) {
            this(colorSettingScreen);
        }

        void calculateFromColor(Color color, boolean bl) {
            double d = Math.min(Math.min(color.r, color.g), color.b);
            double d2 = Math.max(Math.max(color.r, color.g), color.b);
            double d3 = d2 - d;
            this.value = d2 / 255.0;
            this.saturation = d3 == 0.0 ? 0.0 : d3 / d2;
            if (bl) {
                this.handleX = this.saturation * this.width;
                this.handleY = (1.0 - this.value) * this.height;
            } else {
                this.calculateHandlePosOnLayout = true;
            }
        }

        @Override
        protected void onCalculateSize() {
            double d;
            this.width = d = this.theme.scale(75.0);
            this.height = d;
            if (this.fixedHeight != -1.0) {
                this.height = this.fixedHeight;
                this.fixedHeight = -1.0;
            }
        }

        @Override
        protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
            if (this.height != this.width) {
                this.fixedHeight = this.width;
                this.invalidate();
            }
            ColorSettingScreen.access$400(this.this$0).calculateColor();
            guiRenderer.quad(this.x, this.y, this.width, this.height, ColorSettingScreen.access$500(), WHueQuad.access$600(ColorSettingScreen.access$400(this.this$0)), ColorSettingScreen.access$700(), ColorSettingScreen.access$700());
            double d4 = this.theme.scale(2.0);
            guiRenderer.quad(this.x + this.handleX - d4 / 2.0, this.y + this.handleY - d4 / 2.0, d4, d4, ColorSettingScreen.access$500());
        }

        @Override
        public boolean onMouseClicked(double d, double d2, int n, boolean bl) {
            if (bl) {
                return false;
            }
            if (this.mouseOver) {
                this.dragging = true;
                this.handleX = this.lastMouseX - this.x;
                this.handleY = this.lastMouseY - this.y;
                this.handleMoved();
                return true;
            }
            return false;
        }

        void handleMoved() {
            double d = this.handleX / this.width;
            double d2 = this.handleY / this.height;
            this.saturation = d;
            this.value = 1.0 - d2;
            ColorSettingScreen.access$300(this.this$0);
        }

        private WBrightnessQuad(ColorSettingScreen colorSettingScreen) {
            this.this$0 = colorSettingScreen;
            this.fixedHeight = -1.0;
        }

        @Override
        public void onMouseMoved(double d, double d2, double d3, double d4) {
            if (this.dragging) {
                if (d >= this.x && d <= this.x + this.width) {
                    this.handleX += d - d3;
                } else if (this.handleX > 0.0 && d < this.x) {
                    this.handleX = 0.0;
                } else if (this.handleX < this.width && d > this.x + this.width) {
                    this.handleX = this.width;
                }
                if (d2 >= this.y && d2 <= this.y + this.height) {
                    this.handleY += d2 - d4;
                } else if (this.handleY > 0.0 && d2 < this.y) {
                    this.handleY = 0.0;
                } else if (this.handleY < this.height && d2 > this.y + this.height) {
                    this.handleY = this.height;
                }
                this.handleMoved();
            }
            this.lastMouseX = d;
            this.lastMouseY = d2;
        }
    }

    private class WHueQuad
    extends WWidget {
        private boolean calculateHandleXOnLayout;
        final ColorSettingScreen this$0;
        private double handleX;
        private boolean dragging;
        private double hueAngle;
        private final Color color;
        private double lastMouseX;

        @Override
        public void onMouseMoved(double d, double d2, double d3, double d4) {
            if (this.dragging) {
                if (d >= this.x && d <= this.x + this.width) {
                    this.handleX += d - d3;
                    this.handleX = Utils.clamp(this.handleX, 0.0, this.width);
                } else if (this.handleX > 0.0 && d < this.x) {
                    this.handleX = 0.0;
                } else if (this.handleX < this.width && d > this.x + this.width) {
                    this.handleX = this.width;
                }
                this.calculateHueAngleFromHandleX();
                ColorSettingScreen.access$300(this.this$0);
            }
            this.lastMouseX = d;
        }

        static Color access$600(WHueQuad wHueQuad) {
            return wHueQuad.color;
        }

        @Override
        protected void onCalculateWidgetPositions() {
            if (this.calculateHandleXOnLayout) {
                double d = this.hueAngle / 360.0;
                this.handleX = d * this.width;
                this.calculateHandleXOnLayout = false;
            }
            super.onCalculateWidgetPositions();
        }

        @Override
        public boolean onMouseReleased(double d, double d2, int n) {
            if (this.dragging) {
                this.dragging = false;
            }
            return this.mouseOver;
        }

        private WHueQuad(ColorSettingScreen colorSettingScreen) {
            this.this$0 = colorSettingScreen;
            this.color = new Color();
        }

        void calculateHueAngleFromHandleX() {
            double d = this.handleX / (this.width - 4.0);
            this.hueAngle = d * 360.0;
        }

        void calculateFromSetting(boolean bl) {
            Color color = (Color)ColorSettingScreen.access$800(this.this$0).get();
            boolean bl2 = false;
            double d = Math.min(color.r, color.g);
            d = d < (double)color.b ? d : (double)color.b;
            double d2 = Math.max(color.r, color.g);
            double d3 = (d2 = d2 > (double)color.b ? d2 : (double)color.b) - d;
            if (d3 < 1.0E-5) {
                this.hueAngle = 0.0;
                bl2 = true;
            }
            if (!bl2) {
                if (d2 <= 0.0) {
                    this.hueAngle = 0.0;
                    bl2 = true;
                }
                if (!bl2) {
                    this.hueAngle = (double)color.r >= d2 ? (double)(color.g - color.b) / d3 : ((double)color.g >= d2 ? 2.0 + (double)(color.b - color.r) / d3 : 4.0 + (double)(color.r - color.g) / d3);
                    this.hueAngle *= 60.0;
                    if (this.hueAngle < 0.0) {
                        this.hueAngle += 360.0;
                    }
                }
            }
            if (bl) {
                double d4 = this.hueAngle / 360.0;
                this.handleX = d4 * this.width;
            } else {
                this.calculateHandleXOnLayout = true;
            }
        }

        @Override
        public boolean onMouseClicked(double d, double d2, int n, boolean bl) {
            if (bl) {
                return false;
            }
            if (this.mouseOver) {
                this.dragging = true;
                this.handleX = this.lastMouseX - this.x;
                this.calculateHueAngleFromHandleX();
                ColorSettingScreen.access$300(this.this$0);
                return true;
            }
            return false;
        }

        static double access$200(WHueQuad wHueQuad) {
            return wHueQuad.hueAngle;
        }

        @Override
        protected void onRender(GuiRenderer guiRenderer, double d, double d2, double d3) {
            double d4 = this.width / (double)(ColorSettingScreen.access$900().length - 1);
            double d5 = this.x;
            for (int i = 0; i < ColorSettingScreen.access$900().length - 1; ++i) {
                guiRenderer.quad(d5, this.y, d4, this.height, ColorSettingScreen.access$900()[i], ColorSettingScreen.access$900()[i + 1], ColorSettingScreen.access$900()[i + 1], ColorSettingScreen.access$900()[i]);
                d5 += d4;
                if (-1 <= 0) continue;
                return;
            }
            double d6 = this.theme.scale(2.0);
            guiRenderer.quad(this.x + this.handleX - d6 / 2.0, this.y, d6, this.height, ColorSettingScreen.access$500());
        }

        WHueQuad(ColorSettingScreen colorSettingScreen, 1 var2_2) {
            this(colorSettingScreen);
        }

        void calculateColor() {
            double d;
            double d2;
            double d3;
            double d4 = this.hueAngle;
            if (d4 >= 360.0) {
                d4 = 0.0;
            }
            int n = (int)(d4 /= 60.0);
            double d5 = d4 - (double)n;
            double d6 = 0.0;
            double d7 = 1.0 * (1.0 - 1.0 * d5);
            double d8 = 1.0 * (1.0 - 1.0 * (1.0 - d5));
            switch (n) {
                case 0: {
                    d3 = 1.0;
                    d2 = d8;
                    d = d6;
                    break;
                }
                case 1: {
                    d3 = d7;
                    d2 = 1.0;
                    d = d6;
                    break;
                }
                case 2: {
                    d3 = d6;
                    d2 = 1.0;
                    d = d8;
                    break;
                }
                case 3: {
                    d3 = d6;
                    d2 = d7;
                    d = 1.0;
                    break;
                }
                case 4: {
                    d3 = d8;
                    d2 = d6;
                    d = 1.0;
                    break;
                }
                default: {
                    d3 = 1.0;
                    d2 = d6;
                    d = d7;
                }
            }
            this.color.r = (int)(d3 * 255.0);
            this.color.g = (int)(d2 * 255.0);
            this.color.b = (int)(d * 255.0);
            this.color.validate();
        }

        @Override
        protected void onCalculateSize() {
            this.width = this.theme.scale(75.0);
            this.height = this.theme.scale(10.0);
        }
    }
}

