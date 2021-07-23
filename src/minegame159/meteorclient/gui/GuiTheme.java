/*
 * Decompiled with CFR 0.151.
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

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class GuiTheme
implements ISerializable<GuiTheme> {
    public static final double TITLE_TEXT_SCALE = 1.25;
    public final String name;
    public final Settings settings = new Settings();
    protected final Map<String, WindowConfig> windowConfigs = new HashMap<String, WindowConfig>();
    protected SettingsWidgetFactory settingsFactory;
    public boolean disableHoverColor;

    public abstract WView view();

    public abstract WVerticalSeparator verticalSeparator();

    public double textHeight(boolean bl) {
        return this.scale(this.textRenderer().getHeight() * (bl ? 1.25 : 1.0));
    }

    public TabScreen modulesScreen() {
        return new ModulesScreen(this);
    }

    private static boolean lambda$textBox$0(String string, char c) {
        return true;
    }

    public abstract boolean blur();

    public abstract WQuad quad(Color var1);

    public WTextBox textBox(String string) {
        return this.textBox(string, GuiTheme::lambda$textBox$0);
    }

    public abstract WLabel label(String var1, boolean var2, double var3);

    @Override
    public GuiTheme fromTag(class_2487 class_24872) {
        this.settings.fromTag(class_24872.method_10562("settings"));
        class_2487 class_24873 = class_24872.method_10562("windowConfigs");
        for (String string : class_24873.method_10541()) {
            this.windowConfigs.put(string, (WindowConfig)new WindowConfig().fromTag(class_24873.method_10562(string)));
        }
        return this;
    }

    public GuiTheme(String string) {
        this.name = string;
    }

    public abstract WTextBox textBox(String var1, CharFilter var2);

    public WWidget settings(Settings settings, String string) {
        return this.settingsFactory.create(this, settings, string);
    }

    public WTable table() {
        return this.w(new WTable());
    }

    public WindowConfig getWindowConfig(String string) {
        WindowConfig windowConfig = this.windowConfigs.get(string);
        if (windowConfig != null) {
            return windowConfig;
        }
        windowConfig = new WindowConfig();
        this.windowConfigs.put(string, windowConfig);
        return windowConfig;
    }

    public abstract WTooltip tooltip(String var1);

    public abstract WHorizontalSeparator horizontalSeparator(String var1);

    public abstract WAccount account(WidgetScreen var1, Account<?> var2);

    public WKeybind keybind(Keybind keybind, int n) {
        return this.w(new WKeybind(keybind, n));
    }

    public abstract double scale(double var1);

    public abstract <T> WDropdown<T> dropdown(T[] var1, T var2);

    public WHorizontalSeparator horizontalSeparator() {
        return this.horizontalSeparator(null);
    }

    public double textHeight() {
        return this.textHeight(false);
    }

    protected <T extends WWidget> T w(T t) {
        t.theme = this;
        return t;
    }

    public double pad() {
        return this.scale(6.0);
    }

    public void clearWindowConfigs() {
        this.windowConfigs.clear();
    }

    public void beforeRender() {
        this.disableHoverColor = false;
    }

    public WItemWithLabel itemWithLabel(class_1799 class_17992, String string) {
        return this.w(new WItemWithLabel(class_17992, string));
    }

    public WSection section(String string, boolean bl) {
        return this.section(string, bl, null);
    }

    public WButton button(String string) {
        return this.button(string, null);
    }

    @Override
    public Object fromTag(class_2487 class_24872) {
        return this.fromTag(class_24872);
    }

    public WSection section(String string) {
        return this.section(string, true);
    }

    public abstract WTopBar topBar();

    public WWidget settings(Settings settings) {
        return this.settings(settings, "");
    }

    public WHorizontalList horizontalList() {
        return this.w(new WHorizontalList());
    }

    public WItem item(class_1799 class_17992) {
        return this.w(new WItem(class_17992));
    }

    public WVerticalList verticalList() {
        return this.w(new WVerticalList());
    }

    protected abstract WButton button(String var1, GuiTexture var2);

    public WLabel label(String string) {
        return this.label(string, false);
    }

    public abstract boolean categoryIcons();

    public WIntEdit intEdit(int n, int n2, int n3) {
        return this.w(new WIntEdit(n, n2, n3));
    }

    public abstract WSection section(String var1, boolean var2, WWidget var3);

    public abstract boolean hideHUD();

    public abstract WTriangle triangle();

    public WidgetScreen accountsScreen() {
        return new AccountsScreen(this);
    }

    public abstract WSlider slider(double var1, double var3, double var5);

    public <T extends Enum<?>> WDropdown<T> dropdown(T t) {
        Class<?> clazz = t.getClass();
        Enum[] enumArray = null;
        try {
            enumArray = (Enum[])clazz.getDeclaredMethod("values", new Class[0]).invoke(null, new Object[0]);
        }
        catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException reflectiveOperationException) {
            reflectiveOperationException.printStackTrace();
        }
        return this.dropdown(enumArray, t);
    }

    public WTexture texture(double d, double d2, double d3, class_1044 class_10442) {
        return this.w(new WTexture(d, d2, d3, class_10442));
    }

    public WLabel label(String string, double d) {
        return this.label(string, false, d);
    }

    public WLabel label(String string, boolean bl) {
        return this.label(string, bl, 0.0);
    }

    @Override
    public class_2487 toTag() {
        class_2487 class_24872 = new class_2487();
        class_24872.method_10582("name", this.name);
        class_24872.method_10566("settings", (class_2520)this.settings.toTag());
        class_2487 class_24873 = new class_2487();
        for (String string : this.windowConfigs.keySet()) {
            class_24873.method_10566(string, (class_2520)this.windowConfigs.get(string).toTag());
        }
        class_24872.method_10566("windowConfigs", (class_2520)class_24873);
        return class_24872;
    }

    public double textWidth(String string) {
        return this.textWidth(string, string.length(), false);
    }

    public double textWidth(String string, int n, boolean bl) {
        return this.scale(this.textRenderer().getWidth(string, n) * (bl ? 1.25 : 1.0));
    }

    public WKeybind keybind(Keybind keybind) {
        return this.keybind(keybind, -1);
    }

    public abstract Color textSecondaryColor();

    public boolean isModulesScreen(class_437 class_4372) {
        return class_4372 instanceof ModulesScreen;
    }

    public WButton button(GuiTexture guiTexture) {
        return this.button(null, guiTexture);
    }

    public WidgetScreen proxiesScreen() {
        return new ProxiesScreen(this);
    }

    public abstract WCheckbox checkbox(boolean var1);

    public WItemWithLabel itemWithLabel(class_1799 class_17992) {
        return this.itemWithLabel(class_17992, Names.get(class_17992.method_7909()));
    }

    public abstract WWidget module(Module var1);

    public abstract TextRenderer textRenderer();

    public abstract WPlus plus();

    public abstract WMinus minus();

    public WDoubleEdit doubleEdit(double d, double d2, double d3) {
        return this.w(new WDoubleEdit(d, d2, d3));
    }

    public WidgetScreen moduleScreen(Module module) {
        return new ModuleScreen(this, module);
    }

    public abstract Color textColor();

    public abstract WWindow window(String var1);
}

