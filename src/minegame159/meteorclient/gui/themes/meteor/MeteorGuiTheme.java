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
    public final /* synthetic */ Setting<SettingColor> minusColor;
    public final /* synthetic */ ThreeStateColorSetting outlineColor;
    private final /* synthetic */ SettingGroup sgTextColors;
    public final /* synthetic */ Setting<SettingColor> separatorText;
    private final /* synthetic */ SettingGroup sgSeparator;
    public final /* synthetic */ ThreeStateColorSetting backgroundColor;
    public final /* synthetic */ Setting<SettingColor> separatorEdges;
    public final /* synthetic */ Setting<AlignmentX> moduleAlignment;
    public final /* synthetic */ Setting<SettingColor> titleTextColor;
    private final /* synthetic */ SettingGroup sgOutline;
    public final /* synthetic */ Setting<SettingColor> accentColor;
    public final /* synthetic */ Setting<SettingColor> sliderLeft;
    public final /* synthetic */ Setting<Boolean> categoryIcons;
    public final /* synthetic */ Setting<SettingColor> textColor;
    private final /* synthetic */ SettingGroup sgColors;
    public final /* synthetic */ Setting<SettingColor> loggedInColor;
    private final /* synthetic */ SettingGroup sgBackgroundColors;
    public final /* synthetic */ Setting<SettingColor> separatorCenter;
    public final /* synthetic */ Setting<SettingColor> sliderRight;
    private final /* synthetic */ SettingGroup sgSlider;
    public final /* synthetic */ Setting<SettingColor> checkboxColor;
    public final /* synthetic */ Setting<Double> scale;
    public final /* synthetic */ Setting<Boolean> hideHUD;
    public final /* synthetic */ ThreeStateColorSetting sliderHandle;
    public final /* synthetic */ Setting<SettingColor> plusColor;
    public final /* synthetic */ Setting<SettingColor> moduleBackground;
    public final /* synthetic */ Setting<Boolean> blur;
    private final /* synthetic */ SettingGroup sgScrollbar;
    public final /* synthetic */ ThreeStateColorSetting scrollbarColor;
    public final /* synthetic */ Setting<SettingColor> textSecondaryColor;
    private final /* synthetic */ SettingGroup sgGeneral;

    @Override
    public WCheckbox checkbox(boolean llllllllllllllllIllIIllIlIIIlllI) {
        MeteorGuiTheme llllllllllllllllIllIIllIlIIIllll;
        return llllllllllllllllIllIIllIlIIIllll.w(new WMeteorCheckbox(llllllllllllllllIllIIllIlIIIlllI));
    }

    @Override
    public WMinus minus() {
        MeteorGuiTheme llllllllllllllllIllIIllIlIIllIII;
        return llllllllllllllllIllIIllIlIIllIII.w(new WMeteorMinus());
    }

    @Override
    public <T> WDropdown<T> dropdown(T[] llllllllllllllllIllIIllIIlllIlII, T llllllllllllllllIllIIllIIlllIIII) {
        MeteorGuiTheme llllllllllllllllIllIIllIIlllIlIl;
        return llllllllllllllllIllIIllIIlllIlIl.w(new WMeteorDropdown<T>(llllllllllllllllIllIIllIIlllIlII, llllllllllllllllIllIIllIIlllIIII));
    }

    @Override
    public WHorizontalSeparator horizontalSeparator(String llllllllllllllllIllIIllIlIlIIllI) {
        MeteorGuiTheme llllllllllllllllIllIIllIlIlIIlll;
        return llllllllllllllllIllIIllIlIlIIlll.w(new WMeteorHorizontalSeparator(llllllllllllllllIllIIllIlIlIIllI));
    }

    public MeteorGuiTheme() {
        super("Meteor");
        MeteorGuiTheme llllllllllllllllIllIIllIllIllIII;
        llllllllllllllllIllIIllIllIllIII.sgGeneral = llllllllllllllllIllIIllIllIllIII.settings.getDefaultGroup();
        llllllllllllllllIllIIllIllIllIII.sgColors = llllllllllllllllIllIIllIllIllIII.settings.createGroup("Colors");
        llllllllllllllllIllIIllIllIllIII.sgTextColors = llllllllllllllllIllIIllIllIllIII.settings.createGroup("Text");
        llllllllllllllllIllIIllIllIllIII.sgBackgroundColors = llllllllllllllllIllIIllIllIllIII.settings.createGroup("Background");
        llllllllllllllllIllIIllIllIllIII.sgOutline = llllllllllllllllIllIIllIllIllIII.settings.createGroup("Outline");
        llllllllllllllllIllIIllIllIllIII.sgSeparator = llllllllllllllllIllIIllIllIllIII.settings.createGroup("Separator");
        llllllllllllllllIllIIllIllIllIII.sgScrollbar = llllllllllllllllIllIIllIllIllIII.settings.createGroup("Scrollbar");
        llllllllllllllllIllIIllIllIllIII.sgSlider = llllllllllllllllIllIIllIllIllIII.settings.createGroup("Slider");
        llllllllllllllllIllIIllIllIllIII.scale = llllllllllllllllIllIIllIllIllIII.sgGeneral.add(new DoubleSetting.Builder().name("scale").description("Scale of the GUI.").defaultValue(1.0).min(0.75).sliderMin(0.75).sliderMax(4.0).onSliderRelease().onChanged(llllllllllllllllIllIIllIIIlIIllI -> {
            if (Utils.mc.field_1755 instanceof WidgetScreen) {
                ((WidgetScreen)Utils.mc.field_1755).invalidate();
            }
        }).build());
        llllllllllllllllIllIIllIllIllIII.moduleAlignment = llllllllllllllllIllIIllIllIllIII.sgGeneral.add(new EnumSetting.Builder().name("module-alignment").description("How module titles are aligned.").defaultValue(AlignmentX.Center).build());
        llllllllllllllllIllIIllIllIllIII.categoryIcons = llllllllllllllllIllIIllIllIllIII.sgGeneral.add(new BoolSetting.Builder().name("category-icons").description("Adds item icons to module categories.").defaultValue(false).build());
        llllllllllllllllIllIIllIllIllIII.blur = llllllllllllllllIllIIllIllIllIII.sgGeneral.add(new BoolSetting.Builder().name("blur").description("Apply blur behind the GUI.").defaultValue(false).build());
        llllllllllllllllIllIIllIllIllIII.hideHUD = llllllllllllllllIllIIllIllIllIII.sgGeneral.add(new BoolSetting.Builder().name("hide-HUD").description("Hide HUD when in GUI.").defaultValue(false).onChanged(llllllllllllllllIllIIllIIIlIIlll -> {
            if (Utils.mc.field_1755 instanceof WidgetScreen) {
                Utils.mc.field_1690.field_1842 = llllllllllllllllIllIIllIIIlIIlll;
            }
        }).build());
        llllllllllllllllIllIIllIllIllIII.accentColor = llllllllllllllllIllIIllIllIllIII.color("accent", "Main color of the GUI.", new SettingColor(135, 0, 255));
        llllllllllllllllIllIIllIllIllIII.checkboxColor = llllllllllllllllIllIIllIllIllIII.color("checkbox", "Color of checkbox.", new SettingColor(135, 0, 255));
        llllllllllllllllIllIIllIllIllIII.plusColor = llllllllllllllllIllIIllIllIllIII.color("plus", "Color of plus button.", new SettingColor(255, 255, 255));
        llllllllllllllllIllIIllIllIllIII.minusColor = llllllllllllllllIllIIllIllIllIII.color("minus", "Color of minus button.", new SettingColor(255, 255, 255));
        llllllllllllllllIllIIllIllIllIII.textColor = llllllllllllllllIllIIllIllIllIII.color(llllllllllllllllIllIIllIllIllIII.sgTextColors, "text", "Color of text.", new SettingColor(255, 255, 255));
        llllllllllllllllIllIIllIllIllIII.textSecondaryColor = llllllllllllllllIllIIllIllIllIII.color(llllllllllllllllIllIIllIllIllIII.sgTextColors, "text-secondary-text", "Color of secondary text.", new SettingColor(150, 150, 150));
        llllllllllllllllIllIIllIllIllIII.titleTextColor = llllllllllllllllIllIIllIllIllIII.color(llllllllllllllllIllIIllIllIllIII.sgTextColors, "title-text", "Color of title text.", new SettingColor(255, 255, 255));
        llllllllllllllllIllIIllIllIllIII.loggedInColor = llllllllllllllllIllIIllIllIllIII.color(llllllllllllllllIllIIllIllIllIII.sgTextColors, "logged-in-text", "Color of logged in account name.", new SettingColor(45, 225, 45));
        llllllllllllllllIllIIllIllIllIII.backgroundColor = llllllllllllllllIllIIllIllIllIII.new ThreeStateColorSetting(llllllllllllllllIllIIllIllIllIII.sgBackgroundColors, "background", new SettingColor(20, 20, 20, 200), new SettingColor(30, 30, 30, 200), new SettingColor(40, 40, 40, 200));
        llllllllllllllllIllIIllIllIllIII.moduleBackground = llllllllllllllllIllIIllIllIllIII.color(llllllllllllllllIllIIllIllIllIII.sgBackgroundColors, "module-background", "Color of module background when active.", new SettingColor(50, 50, 50));
        llllllllllllllllIllIIllIllIllIII.outlineColor = llllllllllllllllIllIIllIllIllIII.new ThreeStateColorSetting(llllllllllllllllIllIIllIllIllIII.sgOutline, "outline", new SettingColor(0, 0, 0), new SettingColor(10, 10, 10), new SettingColor(20, 20, 20));
        llllllllllllllllIllIIllIllIllIII.separatorText = llllllllllllllllIllIIllIllIllIII.color(llllllllllllllllIllIIllIllIllIII.sgSeparator, "separator-text", "Color of separator text", new SettingColor(255, 255, 255));
        llllllllllllllllIllIIllIllIllIII.separatorCenter = llllllllllllllllIllIIllIllIllIII.color(llllllllllllllllIllIIllIllIllIII.sgSeparator, "separator-center", "Center color of separators.", new SettingColor(255, 255, 255));
        llllllllllllllllIllIIllIllIllIII.separatorEdges = llllllllllllllllIllIIllIllIllIII.color(llllllllllllllllIllIIllIllIllIII.sgSeparator, "separator-edges", "Color of separator edges.", new SettingColor(225, 225, 225, 150));
        llllllllllllllllIllIIllIllIllIII.scrollbarColor = llllllllllllllllIllIIllIllIllIII.new ThreeStateColorSetting(llllllllllllllllIllIIllIllIllIII.sgScrollbar, "Scrollbar", new SettingColor(30, 30, 30, 200), new SettingColor(40, 40, 40, 200), new SettingColor(50, 50, 50, 200));
        llllllllllllllllIllIIllIllIllIII.sliderHandle = llllllllllllllllIllIIllIllIllIII.new ThreeStateColorSetting(llllllllllllllllIllIIllIllIllIII.sgSlider, "slider-handle", new SettingColor(0, 255, 180), new SettingColor(0, 240, 165), new SettingColor(0, 225, 150));
        llllllllllllllllIllIIllIllIllIII.sliderLeft = llllllllllllllllIllIIllIllIllIII.color(llllllllllllllllIllIIllIllIllIII.sgSlider, "slider-left", "Color of slider left part.", new SettingColor(0, 150, 80));
        llllllllllllllllIllIIllIllIllIII.sliderRight = llllllllllllllllIllIIllIllIllIII.color(llllllllllllllllIllIIllIllIllIII.sgSlider, "slider-right", "Color of slider right part.", new SettingColor(50, 50, 50));
        llllllllllllllllIllIIllIllIllIII.settingsFactory = new DefaultSettingsWidgetFactory(llllllllllllllllIllIIllIllIllIII);
    }

    @Override
    public WPlus plus() {
        MeteorGuiTheme llllllllllllllllIllIIllIlIIlIlIl;
        return llllllllllllllllIllIIllIlIIlIlIl.w(new WMeteorPlus());
    }

    @Override
    public WQuad quad(Color llllllllllllllllIllIIllIIlIIIIll) {
        MeteorGuiTheme llllllllllllllllIllIIllIIlIIIlII;
        return llllllllllllllllIllIIllIIlIIIlII.w(new WMeteorQuad(llllllllllllllllIllIIllIIlIIIIll));
    }

    @Override
    public boolean blur() {
        MeteorGuiTheme llllllllllllllllIllIIllIIIlIlllI;
        return llllllllllllllllIllIIllIIIlIlllI.blur.get();
    }

    @Override
    public WWidget module(Module llllllllllllllllIllIIllIIlIIlIIl) {
        MeteorGuiTheme llllllllllllllllIllIIllIIlIIllII;
        return llllllllllllllllIllIIllIIlIIllII.w(new WMeteorModule(llllllllllllllllIllIIllIIlIIlIIl));
    }

    @Override
    public WSlider slider(double llllllllllllllllIllIIllIlIIIIlII, double llllllllllllllllIllIIllIlIIIIIll, double llllllllllllllllIllIIllIlIIIIllI) {
        MeteorGuiTheme llllllllllllllllIllIIllIlIIIlIIl;
        return llllllllllllllllIllIIllIlIIIlIIl.w(new WMeteorSlider(llllllllllllllllIllIIllIlIIIIlII, llllllllllllllllIllIIllIlIIIIIll, llllllllllllllllIllIIllIlIIIIllI));
    }

    private Setting<SettingColor> color(SettingGroup llllllllllllllllIllIIllIllIIllIl, String llllllllllllllllIllIIllIllIIllII, String llllllllllllllllIllIIllIllIIllll, SettingColor llllllllllllllllIllIIllIllIIlllI) {
        return llllllllllllllllIllIIllIllIIllIl.add(new ColorSetting.Builder().name(String.valueOf(new StringBuilder().append(llllllllllllllllIllIIllIllIIllII).append("-color"))).description(llllllllllllllllIllIIllIllIIllll).defaultValue(llllllllllllllllIllIIllIllIIlllI).build());
    }

    @Override
    public WTriangle triangle() {
        MeteorGuiTheme llllllllllllllllIllIIllIIllIllIl;
        return llllllllllllllllIllIIllIIllIllIl.w(new WMeteorTriangle());
    }

    @Override
    public boolean categoryIcons() {
        MeteorGuiTheme llllllllllllllllIllIIllIIIllIIIl;
        return llllllllllllllllIllIIllIIIllIIIl.categoryIcons.get();
    }

    @Override
    public boolean hideHUD() {
        MeteorGuiTheme llllllllllllllllIllIIllIIIlIlIll;
        return llllllllllllllllIllIIllIIIlIlIll.hideHUD.get();
    }

    @Override
    public WView view() {
        MeteorGuiTheme llllllllllllllllIllIIllIIllIIlII;
        return llllllllllllllllIllIIllIIllIIlII.w(new WMeteorView());
    }

    @Override
    public WWindow window(String llllllllllllllllIllIIllIlIlllIII) {
        MeteorGuiTheme llllllllllllllllIllIIllIlIlllIll;
        return llllllllllllllllIllIIllIlIlllIll.w(new WMeteorWindow(llllllllllllllllIllIIllIlIlllIII));
    }

    @Override
    public WLabel label(String llllllllllllllllIllIIllIlIlIlllI, boolean llllllllllllllllIllIIllIlIllIIIl, double llllllllllllllllIllIIllIlIlIllII) {
        MeteorGuiTheme llllllllllllllllIllIIllIlIlIllll;
        if (llllllllllllllllIllIIllIlIlIllII == 0.0) {
            return llllllllllllllllIllIIllIlIlIllll.w(new WMeteorLabel(llllllllllllllllIllIIllIlIlIlllI, llllllllllllllllIllIIllIlIllIIIl));
        }
        return llllllllllllllllIllIIllIlIlIllll.w(new WMeteorMultiLabel(llllllllllllllllIllIIllIlIlIlllI, llllllllllllllllIllIIllIlIllIIIl, llllllllllllllllIllIIllIlIlIllII));
    }

    @Override
    public Color textSecondaryColor() {
        MeteorGuiTheme llllllllllllllllIllIIllIIIlllIlI;
        return llllllllllllllllIllIIllIIIlllIlI.textSecondaryColor.get();
    }

    @Override
    public TextRenderer textRenderer() {
        return TextRenderer.get();
    }

    @Override
    public WTextBox textBox(String llllllllllllllllIllIIllIIllllIlI, CharFilter llllllllllllllllIllIIllIIlllllII) {
        MeteorGuiTheme llllllllllllllllIllIIllIIllllIll;
        return llllllllllllllllIllIIllIIllllIll.w(new WMeteorTextBox(llllllllllllllllIllIIllIIllllIlI, llllllllllllllllIllIIllIIlllllII));
    }

    @Override
    public WTooltip tooltip(String llllllllllllllllIllIIllIIllIlIIl) {
        MeteorGuiTheme llllllllllllllllIllIIllIIllIlIlI;
        return llllllllllllllllIllIIllIIllIlIlI.w(new WMeteorTooltip(llllllllllllllllIllIIllIIllIlIIl));
    }

    @Override
    protected WButton button(String llllllllllllllllIllIIllIlIIllllI, GuiTexture llllllllllllllllIllIIllIlIIlllIl) {
        MeteorGuiTheme llllllllllllllllIllIIllIlIIlllII;
        return llllllllllllllllIllIIllIlIIlllII.w(new WMeteorButton(llllllllllllllllIllIIllIlIIllllI, llllllllllllllllIllIIllIlIIlllIl));
    }

    @Override
    public WSection section(String llllllllllllllllIllIIllIIlIllIlI, boolean llllllllllllllllIllIIllIIlIlllIl, WWidget llllllllllllllllIllIIllIIlIlllII) {
        MeteorGuiTheme llllllllllllllllIllIIllIIlIllIll;
        return llllllllllllllllIllIIllIIlIllIll.w(new WMeteorSection(llllllllllllllllIllIIllIIlIllIlI, llllllllllllllllIllIIllIIlIlllIl, llllllllllllllllIllIIllIIlIlllII));
    }

    @Override
    public WTopBar topBar() {
        MeteorGuiTheme llllllllllllllllIllIIllIIlIIIIIl;
        return llllllllllllllllIllIIllIIlIIIIIl.w(new WMeteorTopBar());
    }

    private Setting<SettingColor> color(String llllllllllllllllIllIIllIllIIIIII, String llllllllllllllllIllIIllIllIIIIll, SettingColor llllllllllllllllIllIIllIlIlllllI) {
        MeteorGuiTheme llllllllllllllllIllIIllIllIIIIIl;
        return llllllllllllllllIllIIllIllIIIIIl.color(llllllllllllllllIllIIllIllIIIIIl.sgColors, llllllllllllllllIllIIllIllIIIIII, llllllllllllllllIllIIllIllIIIIll, llllllllllllllllIllIIllIlIlllllI);
    }

    @Override
    public WAccount account(WidgetScreen llllllllllllllllIllIIllIIlIlIIII, Account<?> llllllllllllllllIllIIllIIlIIllll) {
        MeteorGuiTheme llllllllllllllllIllIIllIIlIlIlII;
        return llllllllllllllllIllIIllIIlIlIlII.w(new WMeteorAccount(llllllllllllllllIllIIllIIlIlIIII, llllllllllllllllIllIIllIIlIIllll));
    }

    @Override
    public WVerticalSeparator verticalSeparator() {
        MeteorGuiTheme llllllllllllllllIllIIllIlIlIIlII;
        return llllllllllllllllIllIIllIlIlIIlII.w(new WMeteorVerticalSeparator());
    }

    @Override
    public Color textColor() {
        MeteorGuiTheme llllllllllllllllIllIIllIIIlllllI;
        return llllllllllllllllIllIIllIIIlllllI.textColor.get();
    }

    @Override
    public double scale(double llllllllllllllllIllIIllIIIllIlIl) {
        MeteorGuiTheme llllllllllllllllIllIIllIIIllIlII;
        return llllllllllllllllIllIIllIIIllIlIl * llllllllllllllllIllIIllIIIllIlII.scale.get();
    }

    public class ThreeStateColorSetting {
        private final /* synthetic */ Setting<SettingColor> normal;
        private final /* synthetic */ Setting<SettingColor> pressed;
        private final /* synthetic */ Setting<SettingColor> hovered;

        public SettingColor get(boolean lllllllllllllllllIllIlIIIIIIlIlI, boolean lllllllllllllllllIllIlIIIIIIlIIl) {
            ThreeStateColorSetting lllllllllllllllllIllIlIIIIIIlIII;
            return lllllllllllllllllIllIlIIIIIIlIII.get(lllllllllllllllllIllIlIIIIIIlIlI, lllllllllllllllllIllIlIIIIIIlIIl, false);
        }

        public SettingColor get(boolean lllllllllllllllllIllIlIIIIIlIlIl, boolean lllllllllllllllllIllIlIIIIIlIIII, boolean lllllllllllllllllIllIlIIIIIlIIll) {
            ThreeStateColorSetting lllllllllllllllllIllIlIIIIIlIIlI;
            if (lllllllllllllllllIllIlIIIIIlIlIl) {
                return lllllllllllllllllIllIlIIIIIlIIlI.pressed.get();
            }
            return lllllllllllllllllIllIlIIIIIlIIII && (lllllllllllllllllIllIlIIIIIlIIll || !lllllllllllllllllIllIlIIIIIlIIlI.MeteorGuiTheme.this.disableHoverColor) ? lllllllllllllllllIllIlIIIIIlIIlI.hovered.get() : lllllllllllllllllIllIlIIIIIlIIlI.normal.get();
        }

        public ThreeStateColorSetting(SettingGroup lllllllllllllllllIllIlIIIIlIlIIl, String lllllllllllllllllIllIlIIIIlIlIII, SettingColor lllllllllllllllllIllIlIIIIlIIIII, SettingColor lllllllllllllllllIllIlIIIIIlllll, SettingColor lllllllllllllllllIllIlIIIIIllllI) {
            ThreeStateColorSetting lllllllllllllllllIllIlIIIIlIIlII;
            lllllllllllllllllIllIlIIIIlIIlII.normal = lllllllllllllllllIllIlIIIIlIIlII.MeteorGuiTheme.this.color(lllllllllllllllllIllIlIIIIlIlIIl, lllllllllllllllllIllIlIIIIlIlIII, String.valueOf(new StringBuilder().append("Color of ").append(lllllllllllllllllIllIlIIIIlIlIII).append(".")), lllllllllllllllllIllIlIIIIlIIIII);
            lllllllllllllllllIllIlIIIIlIIlII.hovered = lllllllllllllllllIllIlIIIIlIIlII.MeteorGuiTheme.this.color(lllllllllllllllllIllIlIIIIlIlIIl, String.valueOf(new StringBuilder().append("hovered-").append(lllllllllllllllllIllIlIIIIlIlIII)), String.valueOf(new StringBuilder().append("Color of ").append(lllllllllllllllllIllIlIIIIlIlIII).append(" when hovered.")), lllllllllllllllllIllIlIIIIIlllll);
            lllllllllllllllllIllIlIIIIlIIlII.pressed = lllllllllllllllllIllIlIIIIlIIlII.MeteorGuiTheme.this.color(lllllllllllllllllIllIlIIIIlIlIIl, String.valueOf(new StringBuilder().append("pressed-").append(lllllllllllllllllIllIlIIIIlIlIII)), String.valueOf(new StringBuilder().append("Color of ").append(lllllllllllllllllIllIlIIIIlIlIII).append(" when pressed.")), lllllllllllllllllIllIlIIIIIllllI);
        }

        public SettingColor get() {
            ThreeStateColorSetting lllllllllllllllllIllIlIIIIIlllII;
            return lllllllllllllllllIllIlIIIIIlllII.normal.get();
        }
    }
}

