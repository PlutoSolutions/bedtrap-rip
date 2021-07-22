/*
 * Decompiled with CFR 0.150.
 */
package minegame159.meteorclient.gui.themes.meteor;

import minegame159.meteorclient.gui.DefaultSettingsWidgetFactory;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.gui.renderer.packer.GuiTexture;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorAccount;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorHorizontalSeparator;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorLabel;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorModule;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorMultiLabel;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorQuad;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorSection;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorTooltip;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorTopBar;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorVerticalSeparator;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorView;
import minegame159.meteorclient.gui.themes.meteor.widgets.WMeteorWindow;
import minegame159.meteorclient.gui.themes.meteor.widgets.input.WMeteorDropdown;
import minegame159.meteorclient.gui.themes.meteor.widgets.input.WMeteorSlider;
import minegame159.meteorclient.gui.themes.meteor.widgets.input.WMeteorTextBox;
import minegame159.meteorclient.gui.themes.meteor.widgets.pressable.WMeteorButton;
import minegame159.meteorclient.gui.themes.meteor.widgets.pressable.WMeteorCheckbox;
import minegame159.meteorclient.gui.themes.meteor.widgets.pressable.WMeteorMinus;
import minegame159.meteorclient.gui.themes.meteor.widgets.pressable.WMeteorPlus;
import minegame159.meteorclient.gui.themes.meteor.widgets.pressable.WMeteorTriangle;
import minegame159.meteorclient.gui.utils.AlignmentX;
import minegame159.meteorclient.gui.utils.CharFilter;
import minegame159.meteorclient.gui.widgets.WAccount;
import minegame159.meteorclient.gui.widgets.WHorizontalSeparator;
import minegame159.meteorclient.gui.widgets.WLabel;
import minegame159.meteorclient.gui.widgets.WQuad;
import minegame159.meteorclient.gui.widgets.WTooltip;
import minegame159.meteorclient.gui.widgets.WTopBar;
import minegame159.meteorclient.gui.widgets.WVerticalSeparator;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WSection;
import minegame159.meteorclient.gui.widgets.containers.WView;
import minegame159.meteorclient.gui.widgets.containers.WWindow;
import minegame159.meteorclient.gui.widgets.input.WDropdown;
import minegame159.meteorclient.gui.widgets.input.WSlider;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.gui.widgets.pressable.WPlus;
import minegame159.meteorclient.gui.widgets.pressable.WTriangle;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.accounts.Account;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.render.color.Color;
import minegame159.meteorclient.utils.render.color.SettingColor;

public class MeteorGuiTheme
extends GuiTheme {
    public final Setting<SettingColor> minusColor;
    public final ThreeStateColorSetting outlineColor;
    private final SettingGroup sgTextColors;
    public final Setting<SettingColor> separatorText;
    private final SettingGroup sgSeparator;
    public final ThreeStateColorSetting backgroundColor;
    public final Setting<SettingColor> separatorEdges;
    public final Setting<AlignmentX> moduleAlignment;
    public final Setting<SettingColor> titleTextColor;
    private final SettingGroup sgOutline;
    public final Setting<SettingColor> accentColor;
    public final Setting<SettingColor> sliderLeft;
    public final Setting<Boolean> categoryIcons;
    public final Setting<SettingColor> textColor;
    private final SettingGroup sgColors;
    public final Setting<SettingColor> loggedInColor;
    private final SettingGroup sgBackgroundColors;
    public final Setting<SettingColor> separatorCenter;
    public final Setting<SettingColor> sliderRight;
    private final SettingGroup sgSlider;
    public final Setting<SettingColor> checkboxColor;
    public final Setting<Double> scale;
    public final Setting<Boolean> hideHUD;
    public final ThreeStateColorSetting sliderHandle;
    public final Setting<SettingColor> plusColor;
    public final Setting<SettingColor> moduleBackground;
    public final Setting<Boolean> blur;
    private final SettingGroup sgScrollbar;
    public final ThreeStateColorSetting scrollbarColor;
    public final Setting<SettingColor> textSecondaryColor;
    private final SettingGroup sgGeneral;

    @Override
    public WCheckbox checkbox(boolean bl) {
        return this.w(new WMeteorCheckbox(bl));
    }

    @Override
    public WMinus minus() {
        return this.w(new WMeteorMinus());
    }

    @Override
    public <T> WDropdown<T> dropdown(T[] arrT, T t) {
        return this.w(new WMeteorDropdown<T>(arrT, t));
    }

    private static void lambda$new$1(Boolean bl) {
        if (Utils.mc.field_1755 instanceof WidgetScreen) {
            Utils.mc.field_1690.field_1842 = bl;
        }
    }

    @Override
    public WHorizontalSeparator horizontalSeparator(String string) {
        return this.w(new WMeteorHorizontalSeparator(string));
    }

    private static void lambda$new$0(Double d) {
        if (Utils.mc.field_1755 instanceof WidgetScreen) {
            ((WidgetScreen)Utils.mc.field_1755).invalidate();
        }
    }

    public MeteorGuiTheme() {
        super("Meteor");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgColors = this.settings.createGroup("Colors");
        this.sgTextColors = this.settings.createGroup("Text");
        this.sgBackgroundColors = this.settings.createGroup("Background");
        this.sgOutline = this.settings.createGroup("Outline");
        this.sgSeparator = this.settings.createGroup("Separator");
        this.sgScrollbar = this.settings.createGroup("Scrollbar");
        this.sgSlider = this.settings.createGroup("Slider");
        this.scale = this.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of the GUI.").defaultValue(1.0).min(0.75).sliderMin(0.75).sliderMax(4.0).onSliderRelease().onChanged(MeteorGuiTheme::lambda$new$0).build());
        this.moduleAlignment = this.sgGeneral.add(new EnumSetting.Builder().name("module-alignment").description("How module titles are aligned.").defaultValue(AlignmentX.Center).build());
        this.categoryIcons = this.sgGeneral.add(new BoolSetting.Builder().name("category-icons").description("Adds item icons to module categories.").defaultValue(false).build());
        this.blur = this.sgGeneral.add(new BoolSetting.Builder().name("blur").description("Apply blur behind the GUI.").defaultValue(false).build());
        this.hideHUD = this.sgGeneral.add(new BoolSetting.Builder().name("hide-HUD").description("Hide HUD when in GUI.").defaultValue(false).onChanged(MeteorGuiTheme::lambda$new$1).build());
        this.accentColor = this.color("accent", "Main color of the GUI.", new SettingColor(135, 0, 255));
        this.checkboxColor = this.color("checkbox", "Color of checkbox.", new SettingColor(135, 0, 255));
        this.plusColor = this.color("plus", "Color of plus button.", new SettingColor(255, 255, 255));
        this.minusColor = this.color("minus", "Color of minus button.", new SettingColor(255, 255, 255));
        this.textColor = this.color(this.sgTextColors, "text", "Color of text.", new SettingColor(255, 255, 255));
        this.textSecondaryColor = this.color(this.sgTextColors, "text-secondary-text", "Color of secondary text.", new SettingColor(150, 150, 150));
        this.titleTextColor = this.color(this.sgTextColors, "title-text", "Color of title text.", new SettingColor(255, 255, 255));
        this.loggedInColor = this.color(this.sgTextColors, "logged-in-text", "Color of logged in account name.", new SettingColor(45, 225, 45));
        this.backgroundColor = new ThreeStateColorSetting(this, this.sgBackgroundColors, "background", new SettingColor(20, 20, 20, 200), new SettingColor(30, 30, 30, 200), new SettingColor(40, 40, 40, 200));
        this.moduleBackground = this.color(this.sgBackgroundColors, "module-background", "Color of module background when active.", new SettingColor(50, 50, 50));
        this.outlineColor = new ThreeStateColorSetting(this, this.sgOutline, "outline", new SettingColor(0, 0, 0), new SettingColor(10, 10, 10), new SettingColor(20, 20, 20));
        this.separatorText = this.color(this.sgSeparator, "separator-text", "Color of separator text", new SettingColor(255, 255, 255));
        this.separatorCenter = this.color(this.sgSeparator, "separator-center", "Center color of separators.", new SettingColor(255, 255, 255));
        this.separatorEdges = this.color(this.sgSeparator, "separator-edges", "Color of separator edges.", new SettingColor(225, 225, 225, 150));
        this.scrollbarColor = new ThreeStateColorSetting(this, this.sgScrollbar, "Scrollbar", new SettingColor(30, 30, 30, 200), new SettingColor(40, 40, 40, 200), new SettingColor(50, 50, 50, 200));
        this.sliderHandle = new ThreeStateColorSetting(this, this.sgSlider, "slider-handle", new SettingColor(0, 255, 180), new SettingColor(0, 240, 165), new SettingColor(0, 225, 150));
        this.sliderLeft = this.color(this.sgSlider, "slider-left", "Color of slider left part.", new SettingColor(0, 150, 80));
        this.sliderRight = this.color(this.sgSlider, "slider-right", "Color of slider right part.", new SettingColor(50, 50, 50));
        this.settingsFactory = new DefaultSettingsWidgetFactory(this);
    }

    @Override
    public WPlus plus() {
        return this.w(new WMeteorPlus());
    }

    @Override
    public WQuad quad(Color color) {
        return this.w(new WMeteorQuad(color));
    }

    static Setting access$000(MeteorGuiTheme meteorGuiTheme, SettingGroup settingGroup, String string, String string2, SettingColor settingColor) {
        return meteorGuiTheme.color(settingGroup, string, string2, settingColor);
    }

    @Override
    public boolean blur() {
        return this.blur.get();
    }

    @Override
    public WWidget module(Module module) {
        return this.w(new WMeteorModule(module));
    }

    @Override
    public WSlider slider(double d, double d2, double d3) {
        return this.w(new WMeteorSlider(d, d2, d3));
    }

    private Setting<SettingColor> color(SettingGroup settingGroup, String string, String string2, SettingColor settingColor) {
        return settingGroup.add(new ColorSetting.Builder().name(String.valueOf(new StringBuilder().append(string).append("-color"))).description(string2).defaultValue(settingColor).build());
    }

    @Override
    public WTriangle triangle() {
        return this.w(new WMeteorTriangle());
    }

    @Override
    public boolean categoryIcons() {
        return this.categoryIcons.get();
    }

    @Override
    public boolean hideHUD() {
        return this.hideHUD.get();
    }

    @Override
    public WView view() {
        return this.w(new WMeteorView());
    }

    @Override
    public WWindow window(String string) {
        return this.w(new WMeteorWindow(string));
    }

    @Override
    public WLabel label(String string, boolean bl, double d) {
        if (d == 0.0) {
            return this.w(new WMeteorLabel(string, bl));
        }
        return this.w(new WMeteorMultiLabel(string, bl, d));
    }

    @Override
    public Color textSecondaryColor() {
        return this.textSecondaryColor.get();
    }

    @Override
    public TextRenderer textRenderer() {
        return TextRenderer.get();
    }

    @Override
    public WTextBox textBox(String string, CharFilter charFilter) {
        return this.w(new WMeteorTextBox(string, charFilter));
    }

    @Override
    public WTooltip tooltip(String string) {
        return this.w(new WMeteorTooltip(string));
    }

    @Override
    protected WButton button(String string, GuiTexture guiTexture) {
        return this.w(new WMeteorButton(string, guiTexture));
    }

    @Override
    public WSection section(String string, boolean bl, WWidget wWidget) {
        return this.w(new WMeteorSection(string, bl, wWidget));
    }

    @Override
    public WTopBar topBar() {
        return this.w(new WMeteorTopBar());
    }

    private Setting<SettingColor> color(String string, String string2, SettingColor settingColor) {
        return this.color(this.sgColors, string, string2, settingColor);
    }

    @Override
    public WAccount account(WidgetScreen widgetScreen, Account<?> account) {
        return this.w(new WMeteorAccount(widgetScreen, account));
    }

    @Override
    public WVerticalSeparator verticalSeparator() {
        return this.w(new WMeteorVerticalSeparator());
    }

    @Override
    public Color textColor() {
        return this.textColor.get();
    }

    @Override
    public double scale(double d) {
        return d * this.scale.get();
    }

    public class ThreeStateColorSetting {
        private final Setting<SettingColor> normal;
        private final Setting<SettingColor> pressed;
        private final Setting<SettingColor> hovered;
        final MeteorGuiTheme this$0;

        public SettingColor get(boolean bl, boolean bl2) {
            return this.get(bl, bl2, false);
        }

        public SettingColor get(boolean bl, boolean bl2, boolean bl3) {
            if (bl) {
                return this.pressed.get();
            }
            return bl2 && (bl3 || !this.this$0.disableHoverColor) ? this.hovered.get() : this.normal.get();
        }

        public ThreeStateColorSetting(MeteorGuiTheme meteorGuiTheme, SettingGroup settingGroup, String string, SettingColor settingColor, SettingColor settingColor2, SettingColor settingColor3) {
            this.this$0 = meteorGuiTheme;
            this.normal = MeteorGuiTheme.access$000(meteorGuiTheme, settingGroup, string, String.valueOf(new StringBuilder().append("Color of ").append(string).append(".")), settingColor);
            this.hovered = MeteorGuiTheme.access$000(meteorGuiTheme, settingGroup, String.valueOf(new StringBuilder().append("hovered-").append(string)), String.valueOf(new StringBuilder().append("Color of ").append(string).append(" when hovered.")), settingColor2);
            this.pressed = MeteorGuiTheme.access$000(meteorGuiTheme, settingGroup, String.valueOf(new StringBuilder().append("pressed-").append(string)), String.valueOf(new StringBuilder().append("Color of ").append(string).append(" when pressed.")), settingColor3);
        }

        public SettingColor get() {
            return this.normal.get();
        }
    }
}

