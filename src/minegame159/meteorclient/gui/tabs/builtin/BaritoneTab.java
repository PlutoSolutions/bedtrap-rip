/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.gui.tabs.builtin;

import baritone.api.BaritoneAPI;
import baritone.api.Settings;
import baritone.api.utils.SettingsUtil;
import java.awt.Color;
import java.lang.reflect.Field;
import minegame159.meteorclient.gui.GuiTheme;
import minegame159.meteorclient.gui.tabs.Tab;
import minegame159.meteorclient.gui.tabs.TabScreen;
import minegame159.meteorclient.gui.tabs.WindowTabScreen;
import minegame159.meteorclient.gui.widgets.input.WTextBox;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.Settings;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_437;

public class BaritoneTab
extends Tab {
    private static Settings settings;

    private static void lambda$getSettings$9(Settings.Setting setting, Setting setting2) {
        setting2.set(((Long)setting.value).intValue());
    }

    public BaritoneTab() {
        super("Baritone");
    }

    @Override
    public boolean isScreen(class_437 class_4372) {
        return class_4372 instanceof BaritoneScreen;
    }

    private static Settings getSettings() {
        if (settings != null) {
            return settings;
        }
        settings = new Settings();
        SettingGroup settingGroup = settings.createGroup("Checkboxes");
        SettingGroup settingGroup2 = settings.createGroup("Numbers");
        SettingGroup settingGroup3 = settings.createGroup("Whole Numbers");
        SettingGroup settingGroup4 = settings.createGroup("Colors");
        try {
            Class<?> clazz = BaritoneAPI.getSettings().getClass();
            for (Field field : clazz.getDeclaredFields()) {
                Object object = field.get(BaritoneAPI.getSettings());
                if (!(object instanceof Settings.Setting)) continue;
                Settings.Setting setting = (Settings.Setting)object;
                Object object2 = setting.value;
                if (object2 instanceof Boolean) {
                    settingGroup.add(new BoolSetting.Builder().name(setting.getName()).description(setting.getName()).defaultValue((Boolean)setting.defaultValue).onChanged(arg_0 -> BaritoneTab.lambda$getSettings$0(setting, arg_0)).onModuleActivated(arg_0 -> BaritoneTab.lambda$getSettings$1(setting, arg_0)).build());
                    continue;
                }
                if (object2 instanceof Double) {
                    settingGroup2.add(new DoubleSetting.Builder().name(setting.getName()).description(setting.getName()).defaultValue((Double)setting.defaultValue).onChanged(arg_0 -> BaritoneTab.lambda$getSettings$2(setting, arg_0)).onModuleActivated(arg_0 -> BaritoneTab.lambda$getSettings$3(setting, arg_0)).build());
                    continue;
                }
                if (object2 instanceof Float) {
                    settingGroup2.add(new DoubleSetting.Builder().name(setting.getName()).description(setting.getName()).defaultValue(((Float)setting.defaultValue).doubleValue()).onChanged(arg_0 -> BaritoneTab.lambda$getSettings$4(setting, arg_0)).onModuleActivated(arg_0 -> BaritoneTab.lambda$getSettings$5(setting, arg_0)).build());
                    continue;
                }
                if (object2 instanceof Integer) {
                    settingGroup3.add(new IntSetting.Builder().name(setting.getName()).description(setting.getName()).defaultValue((Integer)setting.defaultValue).onChanged(arg_0 -> BaritoneTab.lambda$getSettings$6(setting, arg_0)).onModuleActivated(arg_0 -> BaritoneTab.lambda$getSettings$7(setting, arg_0)).build());
                    continue;
                }
                if (object2 instanceof Long) {
                    settingGroup3.add(new IntSetting.Builder().name(setting.getName()).description(setting.getName()).defaultValue(((Long)setting.defaultValue).intValue()).onChanged(arg_0 -> BaritoneTab.lambda$getSettings$8(setting, arg_0)).onModuleActivated(arg_0 -> BaritoneTab.lambda$getSettings$9(setting, arg_0)).build());
                    continue;
                }
                if (!(object2 instanceof Color)) continue;
                Color color = (Color)setting.value;
                settingGroup4.add(new ColorSetting.Builder().name(setting.getName()).description(setting.getName()).defaultValue(new SettingColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha())).onChanged(arg_0 -> BaritoneTab.lambda$getSettings$10(setting, arg_0)).onModuleActivated(arg_0 -> BaritoneTab.lambda$getSettings$11(color, arg_0)).build());
                if (3 <= 4) continue;
                return null;
            }
        }
        catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        }
        return settings;
    }

    private static void lambda$getSettings$10(Settings.Setting setting, SettingColor settingColor) {
        setting.value = new Color(settingColor.r, settingColor.g, settingColor.b, settingColor.a);
    }

    private static void lambda$getSettings$7(Settings.Setting setting, Setting setting2) {
        setting2.set((Integer)setting.value);
    }

    private static void lambda$getSettings$8(Settings.Setting setting, Integer n) {
        setting.value = n.longValue();
    }

    private static void lambda$getSettings$0(Settings.Setting setting, Boolean bl) {
        setting.value = bl;
    }

    private static void lambda$getSettings$4(Settings.Setting setting, Double d) {
        setting.value = Float.valueOf(d.floatValue());
    }

    private static void lambda$getSettings$11(Color color, Setting setting) {
        setting.set(new SettingColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
    }

    private static void lambda$getSettings$5(Settings.Setting setting, Setting setting2) {
        setting2.set(((Float)setting.value).doubleValue());
    }

    static Settings access$000() {
        return BaritoneTab.getSettings();
    }

    private static void lambda$getSettings$3(Settings.Setting setting, Setting setting2) {
        setting2.set((Double)setting.value);
    }

    private static void lambda$getSettings$2(Settings.Setting setting, Double d) {
        setting.value = d;
    }

    private static void lambda$getSettings$6(Settings.Setting setting, Integer n) {
        setting.value = n;
    }

    private static void lambda$getSettings$1(Settings.Setting setting, Setting setting2) {
        setting2.set((Boolean)setting.value);
    }

    @Override
    public TabScreen createScreen(GuiTheme guiTheme) {
        return new BaritoneScreen(guiTheme, this);
    }

    private static class BaritoneScreen
    extends WindowTabScreen {
        private void lambda$new$0(WTextBox wTextBox, GuiTheme guiTheme) {
            this.clear();
            this.add(wTextBox);
            this.add(guiTheme.settings(BaritoneTab.access$000(), wTextBox.get().trim())).expandX();
        }

        @Override
        protected void onClosed() {
            SettingsUtil.save((baritone.api.Settings)BaritoneAPI.getSettings());
        }

        public BaritoneScreen(GuiTheme guiTheme, Tab tab) {
            super(guiTheme, tab);
            WTextBox wTextBox = this.add(guiTheme.textBox("")).minWidth(400.0).expandX().widget();
            wTextBox.setFocused(true);
            wTextBox.action = () -> this.lambda$new$0(wTextBox, guiTheme);
            BaritoneTab.access$000().onActivated();
            this.add(guiTheme.settings(BaritoneTab.access$000(), wTextBox.get().trim())).expandX();
        }
    }
}

