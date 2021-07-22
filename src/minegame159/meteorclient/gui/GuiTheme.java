/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1044
 *  net.minecraft.class_1799
 *  net.minecraft.class_2487
 *  net.minecraft.class_2520
 *  net.minecraft.class_437
 */
package minegame159.meteorclient.gui;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.gui.renderer.packer.GuiTexture;
import minegame159.meteorclient.gui.screens.AccountsScreen;
import minegame159.meteorclient.gui.screens.ModuleScreen;
import minegame159.meteorclient.gui.screens.ModulesScreen;
import minegame159.meteorclient.gui.screens.ProxiesScreen;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.utils.CharFilter;
import minegame159.meteorclient.gui.utils.SettingsWidgetFactory;
import minegame159.meteorclient.gui.utils.WindowConfig;
import minegame159.meteorclient.gui.widgets.WAccount;
import minegame159.meteorclient.gui.widgets.WHorizontalSeparator;
import minegame159.meteorclient.gui.widgets.WItem;
import minegame159.meteorclient.gui.widgets.WItemWithLabel;
import minegame159.meteorclient.gui.widgets.WKeybind;
import minegame159.meteorclient.gui.widgets.WLabel;
import minegame159.meteorclient.gui.widgets.WQuad;
import minegame159.meteorclient.gui.widgets.WTexture;
import minegame159.meteorclient.gui.widgets.WTooltip;
import minegame159.meteorclient.gui.widgets.WTopBar;
import minegame159.meteorclient.gui.widgets.WVerticalSeparator;
import minegame159.meteorclient.gui.widgets.WWidget;
import minegame159.meteorclient.gui.widgets.containers.WHorizontalList;
import minegame159.meteorclient.gui.widgets.containers.WSection;
import minegame159.meteorclient.gui.widgets.containers.WTable;
import minegame159.meteorclient.gui.widgets.containers.WVerticalList;
import minegame159.meteorclient.gui.widgets.containers.WView;
import minegame159.meteorclient.gui.widgets.containers.WWindow;
import minegame159.meteorclient.gui.widgets.input.WDoubleEdit;
import minegame159.meteorclient.gui.widgets.input.WDropdown;
import minegame159.meteorclient.gui.widgets.input.WIntEdit;
import minegame159.meteorclient.gui.widgets.input.WSlider;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.gui.widgets.pressable.WButton;
import minegame159.meteorclient.gui.widgets.pressable.WCheckbox;
import minegame159.meteorclient.gui.widgets.pressable.WMinus;
import minegame159.meteorclient.gui.widgets.pressable.WPlus;
import minegame159.meteorclient.gui.widgets.pressable.WTriangle;
import minegame159.meteorclient.rendering.text.TextRenderer;
import minegame159.meteorclient.settings.Settings;
import minegame159.meteorclient.systems.accounts.Account;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.misc.ISerializable;
import minegame159.meteorclient.utils.misc.Keybind;
import minegame159.meteorclient.utils.misc.Names;
import minegame159.meteorclient.utils.render.color.Color;
import net.minecraft.class_1044;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_437;

public abstract class GuiTheme
implements ISerializable<GuiTheme> {
    public static final /* synthetic */ double TITLE_TEXT_SCALE = 1.25;
    public final /* synthetic */ String name;
    public final /* synthetic */ Settings settings;
    protected final /* synthetic */ Map<String, WindowConfig> windowConfigs;
    protected /* synthetic */ SettingsWidgetFactory settingsFactory;
    public /* synthetic */ boolean disableHoverColor;

    public abstract WView view();

    public abstract WVerticalSeparator verticalSeparator();

    public double textHeight(boolean lllllllllllllllllIIIlIIllIIlIIIl) {
        GuiTheme lllllllllllllllllIIIlIIllIIlIIlI;
        return lllllllllllllllllIIIlIIllIIlIIlI.scale(lllllllllllllllllIIIlIIllIIlIIlI.textRenderer().getHeight() * (lllllllllllllllllIIIlIIllIIlIIIl ? 1.25 : 1.0));
    }

    public TabScreen modulesScreen() {
        GuiTheme lllllllllllllllllIIIlIIllIlllIlI;
        return new ModulesScreen(lllllllllllllllllIIIlIIllIlllIlI);
    }

    public abstract boolean blur();

    public abstract WQuad quad(Color var1);

    public WTextBox textBox(String lllllllllllllllllIIIlIlIIIllllIl) {
        GuiTheme lllllllllllllllllIIIlIlIIlIIIIII;
        return lllllllllllllllllIIIlIlIIlIIIIII.textBox(lllllllllllllllllIIIlIlIIIllllIl, (lllllllllllllllllIIIlIIlIlIlIlll, lllllllllllllllllIIIlIIlIlIlIllI) -> true);
    }

    public abstract WLabel label(String var1, boolean var2, double var3);

    @Override
    public GuiTheme fromTag(class_2487 lllllllllllllllllIIIlIIlIllIIIII) {
        GuiTheme lllllllllllllllllIIIlIIlIllIIlII;
        lllllllllllllllllIIIlIIlIllIIlII.settings.fromTag(lllllllllllllllllIIIlIIlIllIIIII.method_10562("settings"));
        class_2487 lllllllllllllllllIIIlIIlIllIIIlI = lllllllllllllllllIIIlIIlIllIIIII.method_10562("windowConfigs");
        for (String lllllllllllllllllIIIlIIlIllIIlIl : lllllllllllllllllIIIlIIlIllIIIlI.method_10541()) {
            lllllllllllllllllIIIlIIlIllIIlII.windowConfigs.put(lllllllllllllllllIIIlIIlIllIIlIl, (WindowConfig)new WindowConfig().fromTag(lllllllllllllllllIIIlIIlIllIIIlI.method_10562(lllllllllllllllllIIIlIIlIllIIlIl)));
        }
        return lllllllllllllllllIIIlIIlIllIIlII;
    }

    public GuiTheme(String lllllllllllllllllIIIlIlIIllIllIl) {
        GuiTheme lllllllllllllllllIIIlIlIIlllIIII;
        lllllllllllllllllIIIlIlIIlllIIII.settings = new Settings();
        lllllllllllllllllIIIlIlIIlllIIII.windowConfigs = new HashMap<String, WindowConfig>();
        lllllllllllllllllIIIlIlIIlllIIII.name = lllllllllllllllllIIIlIlIIllIllIl;
    }

    public abstract WTextBox textBox(String var1, CharFilter var2);

    public WWidget settings(Settings lllllllllllllllllIIIlIIlllIIIIll, String lllllllllllllllllIIIlIIlllIIIlIl) {
        GuiTheme lllllllllllllllllIIIlIIlllIIIlII;
        return lllllllllllllllllIIIlIIlllIIIlII.settingsFactory.create(lllllllllllllllllIIIlIIlllIIIlII, lllllllllllllllllIIIlIIlllIIIIll, lllllllllllllllllIIIlIIlllIIIlIl);
    }

    public WTable table() {
        GuiTheme lllllllllllllllllIIIlIlIIIlIIllI;
        return lllllllllllllllllIIIlIlIIIlIIllI.w(new WTable());
    }

    public WindowConfig getWindowConfig(String lllllllllllllllllIIIlIIllIIIIllI) {
        GuiTheme lllllllllllllllllIIIlIIllIIIIlll;
        WindowConfig lllllllllllllllllIIIlIIllIIIIlIl = lllllllllllllllllIIIlIIllIIIIlll.windowConfigs.get(lllllllllllllllllIIIlIIllIIIIllI);
        if (lllllllllllllllllIIIlIIllIIIIlIl != null) {
            return lllllllllllllllllIIIlIIllIIIIlIl;
        }
        lllllllllllllllllIIIlIIllIIIIlIl = new WindowConfig();
        lllllllllllllllllIIIlIIllIIIIlll.windowConfigs.put(lllllllllllllllllIIIlIIllIIIIllI, lllllllllllllllllIIIlIIllIIIIlIl);
        return lllllllllllllllllIIIlIIllIIIIlIl;
    }

    public abstract WTooltip tooltip(String var1);

    public abstract WHorizontalSeparator horizontalSeparator(String var1);

    public abstract WAccount account(WidgetScreen var1, Account<?> var2);

    public WKeybind keybind(Keybind lllllllllllllllllIIIlIIlllIIllll, int lllllllllllllllllIIIlIIlllIIlIll) {
        GuiTheme lllllllllllllllllIIIlIIlllIlIIII;
        return lllllllllllllllllIIIlIIlllIlIIII.w(new WKeybind(lllllllllllllllllIIIlIIlllIIllll, lllllllllllllllllIIIlIIlllIIlIll));
    }

    public abstract double scale(double var1);

    public abstract <T> WDropdown<T> dropdown(T[] var1, T var2);

    public WHorizontalSeparator horizontalSeparator() {
        GuiTheme lllllllllllllllllIIIlIlIIlIlIIII;
        return lllllllllllllllllIIIlIlIIlIlIIII.horizontalSeparator(null);
    }

    public double textHeight() {
        GuiTheme lllllllllllllllllIIIlIIllIIIlllI;
        return lllllllllllllllllIIIlIIllIIIlllI.textHeight(false);
    }

    protected <T extends WWidget> T w(T lllllllllllllllllIIIlIIlIllllIIl) {
        GuiTheme lllllllllllllllllIIIlIIlIllllIlI;
        lllllllllllllllllIIIlIIlIllllIIl.theme = lllllllllllllllllIIIlIIlIllllIlI;
        return lllllllllllllllllIIIlIIlIllllIIl;
    }

    public double pad() {
        GuiTheme lllllllllllllllllIIIlIIllIIIllII;
        return lllllllllllllllllIIIlIIllIIIllII.scale(6.0);
    }

    public void clearWindowConfigs() {
        GuiTheme lllllllllllllllllIIIlIIlIlllllll;
        lllllllllllllllllIIIlIIlIlllllll.windowConfigs.clear();
    }

    public void beforeRender() {
        lllllllllllllllllIIIlIlIIllIlIlI.disableHoverColor = false;
    }

    public WItemWithLabel itemWithLabel(class_1799 lllllllllllllllllIIIlIlIIIIIlIll, String lllllllllllllllllIIIlIlIIIIIlIlI) {
        GuiTheme lllllllllllllllllIIIlIlIIIIIlIIl;
        return lllllllllllllllllIIIlIlIIIIIlIIl.w(new WItemWithLabel(lllllllllllllllllIIIlIlIIIIIlIll, lllllllllllllllllIIIlIlIIIIIlIlI));
    }

    public WSection section(String lllllllllllllllllIIIlIlIIIlIIIII, boolean lllllllllllllllllIIIlIlIIIIlllII) {
        GuiTheme lllllllllllllllllIIIlIlIIIlIIIIl;
        return lllllllllllllllllIIIlIlIIIlIIIIl.section(lllllllllllllllllIIIlIlIIIlIIIII, lllllllllllllllllIIIlIlIIIIlllII, null);
    }

    public WButton button(String lllllllllllllllllIIIlIlIIlIIlIIl) {
        GuiTheme lllllllllllllllllIIIlIlIIlIIllII;
        return lllllllllllllllllIIIlIlIIlIIllII.button(lllllllllllllllllIIIlIlIIlIIlIIl, null);
    }

    public WSection section(String lllllllllllllllllIIIlIlIIIIllIII) {
        GuiTheme lllllllllllllllllIIIlIlIIIIllIIl;
        return lllllllllllllllllIIIlIlIIIIllIIl.section(lllllllllllllllllIIIlIlIIIIllIII, true);
    }

    public abstract WTopBar topBar();

    public WWidget settings(Settings lllllllllllllllllIIIlIIllIllllII) {
        GuiTheme lllllllllllllllllIIIlIIllIllllIl;
        return lllllllllllllllllIIIlIIllIllllIl.settings(lllllllllllllllllIIIlIIllIllllII, "");
    }

    public WHorizontalList horizontalList() {
        GuiTheme lllllllllllllllllIIIlIlIIIlIlIII;
        return lllllllllllllllllIIIlIlIIIlIlIII.w(new WHorizontalList());
    }

    public WItem item(class_1799 lllllllllllllllllIIIlIlIIIIlIIII) {
        GuiTheme lllllllllllllllllIIIlIlIIIIlIIIl;
        return lllllllllllllllllIIIlIlIIIIlIIIl.w(new WItem(lllllllllllllllllIIIlIlIIIIlIIII));
    }

    public WVerticalList verticalList() {
        GuiTheme lllllllllllllllllIIIlIlIIIlIllII;
        return lllllllllllllllllIIIlIlIIIlIllII.w(new WVerticalList());
    }

    protected abstract WButton button(String var1, GuiTexture var2);

    public WLabel label(String lllllllllllllllllIIIlIlIIlIlIIlI) {
        GuiTheme lllllllllllllllllIIIlIlIIlIlIlIl;
        return lllllllllllllllllIIIlIlIIlIlIlIl.label(lllllllllllllllllIIIlIlIIlIlIIlI, false);
    }

    public abstract boolean categoryIcons();

    public WIntEdit intEdit(int lllllllllllllllllIIIlIIllllIlIII, int lllllllllllllllllIIIlIIllllIIlll, int lllllllllllllllllIIIlIIllllIlIlI) {
        GuiTheme lllllllllllllllllIIIlIIllllIlIIl;
        return lllllllllllllllllIIIlIIllllIlIIl.w(new WIntEdit(lllllllllllllllllIIIlIIllllIlIII, lllllllllllllllllIIIlIIllllIIlll, lllllllllllllllllIIIlIIllllIlIlI));
    }

    public abstract WSection section(String var1, boolean var2, WWidget var3);

    public abstract boolean hideHUD();

    public abstract WTriangle triangle();

    public WidgetScreen accountsScreen() {
        GuiTheme lllllllllllllllllIIIlIIllIlIllIl;
        return new AccountsScreen(lllllllllllllllllIIIlIIllIlIllIl);
    }

    public abstract WSlider slider(double var1, double var3, double var5);

    public <T extends Enum<?>> WDropdown<T> dropdown(T lllllllllllllllllIIIlIlIIIllIIIl) {
        GuiTheme lllllllllllllllllIIIlIlIIIllIIlI;
        Class<?> lllllllllllllllllIIIlIlIIIllIlII = lllllllllllllllllIIIlIlIIIllIIIl.getClass();
        Enum[] lllllllllllllllllIIIlIlIIIllIIll = null;
        try {
            lllllllllllllllllIIIlIlIIIllIIll = (Enum[])lllllllllllllllllIIIlIlIIIllIlII.getDeclaredMethod("values", new Class[0]).invoke(null, new Object[0]);
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException lllllllllllllllllIIIlIlIIIllIlll) {
            lllllllllllllllllIIIlIlIIIllIlll.printStackTrace();
        }
        return lllllllllllllllllIIIlIlIIIllIIlI.dropdown(lllllllllllllllllIIIlIlIIIllIIll, lllllllllllllllllIIIlIlIIIllIIIl);
    }

    public WTexture texture(double lllllllllllllllllIIIlIIllllllIlI, double lllllllllllllllllIIIlIIllllllIIl, double lllllllllllllllllIIIlIIlllllIIll, class_1044 lllllllllllllllllIIIlIIlllllIlll) {
        GuiTheme lllllllllllllllllIIIlIIllllllIll;
        return lllllllllllllllllIIIlIIllllllIll.w(new WTexture(lllllllllllllllllIIIlIIllllllIlI, lllllllllllllllllIIIlIIllllllIIl, lllllllllllllllllIIIlIIlllllIIll, lllllllllllllllllIIIlIIlllllIlll));
    }

    public WLabel label(String lllllllllllllllllIIIlIlIIlIllIIl, double lllllllllllllllllIIIlIlIIlIllIll) {
        GuiTheme lllllllllllllllllIIIlIlIIlIllIlI;
        return lllllllllllllllllIIIlIlIIlIllIlI.label(lllllllllllllllllIIIlIlIIlIllIIl, false, lllllllllllllllllIIIlIlIIlIllIll);
    }

    public WLabel label(String lllllllllllllllllIIIlIlIIllIIIlI, boolean lllllllllllllllllIIIlIlIIllIIIIl) {
        GuiTheme lllllllllllllllllIIIlIlIIllIIIll;
        return lllllllllllllllllIIIlIlIIllIIIll.label(lllllllllllllllllIIIlIlIIllIIIlI, lllllllllllllllllIIIlIlIIllIIIIl, 0.0);
    }

    @Override
    public class_2487 toTag() {
        GuiTheme lllllllllllllllllIIIlIIlIllIllll;
        class_2487 lllllllllllllllllIIIlIIlIlllIIIl = new class_2487();
        lllllllllllllllllIIIlIIlIlllIIIl.method_10582("name", lllllllllllllllllIIIlIIlIllIllll.name);
        lllllllllllllllllIIIlIIlIlllIIIl.method_10566("settings", (class_2520)lllllllllllllllllIIIlIIlIllIllll.settings.toTag());
        class_2487 lllllllllllllllllIIIlIIlIlllIIII = new class_2487();
        for (String lllllllllllllllllIIIlIIlIlllIIll : lllllllllllllllllIIIlIIlIllIllll.windowConfigs.keySet()) {
            lllllllllllllllllIIIlIIlIlllIIII.method_10566(lllllllllllllllllIIIlIIlIlllIIll, (class_2520)lllllllllllllllllIIIlIIlIllIllll.windowConfigs.get(lllllllllllllllllIIIlIIlIlllIIll).toTag());
        }
        lllllllllllllllllIIIlIIlIlllIIIl.method_10566("windowConfigs", (class_2520)lllllllllllllllllIIIlIIlIlllIIII);
        return lllllllllllllllllIIIlIIlIlllIIIl;
    }

    public double textWidth(String lllllllllllllllllIIIlIIllIIlIlll) {
        GuiTheme lllllllllllllllllIIIlIIllIIllIlI;
        return lllllllllllllllllIIIlIIllIIllIlI.textWidth(lllllllllllllllllIIIlIIllIIlIlll, lllllllllllllllllIIIlIIllIIlIlll.length(), false);
    }

    public double textWidth(String lllllllllllllllllIIIlIIllIIlllll, int lllllllllllllllllIIIlIIllIlIIIlI, boolean lllllllllllllllllIIIlIIllIIlllIl) {
        GuiTheme lllllllllllllllllIIIlIIllIlIIlII;
        return lllllllllllllllllIIIlIIllIlIIlII.scale(lllllllllllllllllIIIlIIllIlIIlII.textRenderer().getWidth(lllllllllllllllllIIIlIIllIIlllll, lllllllllllllllllIIIlIIllIlIIIlI) * (lllllllllllllllllIIIlIIllIIlllIl ? 1.25 : 1.0));
    }

    public WKeybind keybind(Keybind lllllllllllllllllIIIlIIlllIlIlII) {
        GuiTheme lllllllllllllllllIIIlIIlllIlIlll;
        return lllllllllllllllllIIIlIIlllIlIlll.keybind(lllllllllllllllllIIIlIIlllIlIlII, -1);
    }

    public abstract Color textSecondaryColor();

    public boolean isModulesScreen(class_437 lllllllllllllllllIIIlIIllIllIlIl) {
        return lllllllllllllllllIIIlIIllIllIlIl instanceof ModulesScreen;
    }

    public WButton button(GuiTexture lllllllllllllllllIIIlIlIIlIIIlIl) {
        GuiTheme lllllllllllllllllIIIlIlIIlIIIllI;
        return lllllllllllllllllIIIlIlIIlIIIllI.button(null, lllllllllllllllllIIIlIlIIlIIIlIl);
    }

    public WidgetScreen proxiesScreen() {
        GuiTheme lllllllllllllllllIIIlIIllIlIlIIl;
        return new ProxiesScreen(lllllllllllllllllIIIlIIllIlIlIIl);
    }

    public abstract WCheckbox checkbox(boolean var1);

    public WItemWithLabel itemWithLabel(class_1799 lllllllllllllllllIIIlIlIIIIIIIIl) {
        GuiTheme lllllllllllllllllIIIlIlIIIIIIlII;
        return lllllllllllllllllIIIlIlIIIIIIlII.itemWithLabel(lllllllllllllllllIIIlIlIIIIIIIIl, Names.get(lllllllllllllllllIIIlIlIIIIIIIIl.method_7909()));
    }

    public abstract WWidget module(Module var1);

    public abstract TextRenderer textRenderer();

    public abstract WPlus plus();

    public abstract WMinus minus();

    public WDoubleEdit doubleEdit(double lllllllllllllllllIIIlIIlllIlllII, double lllllllllllllllllIIIlIIlllIllIll, double lllllllllllllllllIIIlIIlllIllIlI) {
        GuiTheme lllllllllllllllllIIIlIIllllIIIIl;
        return lllllllllllllllllIIIlIIllllIIIIl.w(new WDoubleEdit(lllllllllllllllllIIIlIIlllIlllII, lllllllllllllllllIIIlIIlllIllIll, lllllllllllllllllIIIlIIlllIllIlI));
    }

    public WidgetScreen moduleScreen(Module lllllllllllllllllIIIlIIllIllIIIl) {
        GuiTheme lllllllllllllllllIIIlIIllIllIIII;
        return new ModuleScreen(lllllllllllllllllIIIlIIllIllIIII, lllllllllllllllllIIIlIIllIllIIIl);
    }

    public abstract Color textColor();

    public abstract WWindow window(String var1);
}

