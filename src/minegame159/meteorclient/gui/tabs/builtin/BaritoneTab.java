/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  baritone.api.BaritoneAPI
 *  baritone.api.Settings
 *  baritone.api.Settings$Setting
 *  baritone.api.utils.SettingsUtil
 *  net.minecraft.class_437
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
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.settings.Settings;
import minegame159.meteorclient.utils.render.color.SettingColor;
import net.minecraft.class_437;

public class BaritoneTab
extends Tab {
    private static /* synthetic */ Settings settings;

    public BaritoneTab() {
        super("Baritone");
        BaritoneTab llllllllllllllllllIIIlIIlllIIlII;
    }

    @Override
    public boolean isScreen(class_437 llllllllllllllllllIIIlIIllIllIIl) {
        return llllllllllllllllllIIIlIIllIllIIl instanceof BaritoneScreen;
    }

    private static Settings getSettings() {
        if (settings != null) {
            return settings;
        }
        settings = new Settings();
        SettingGroup llllllllllllllllllIIIlIIllIIIlII = settings.createGroup("Checkboxes");
        SettingGroup llllllllllllllllllIIIlIIllIIIIll = settings.createGroup("Numbers");
        SettingGroup llllllllllllllllllIIIlIIllIIIIlI = settings.createGroup("Whole Numbers");
        SettingGroup llllllllllllllllllIIIlIIllIIIIIl = settings.createGroup("Colors");
        try {
            Class<?> llllllllllllllllllIIIlIIllIIIllI = BaritoneAPI.getSettings().getClass();
            for (Field llllllllllllllllllIIIlIIllIIIlll : llllllllllllllllllIIIlIIllIIIllI.getDeclaredFields()) {
                Object llllllllllllllllllIIIlIIllIIlIlI = llllllllllllllllllIIIlIIllIIIlll.get((Object)BaritoneAPI.getSettings());
                if (!(llllllllllllllllllIIIlIIllIIlIlI instanceof Settings.Setting)) continue;
                Settings.Setting llllllllllllllllllIIIlIIllIIlIIl = (Settings.Setting)llllllllllllllllllIIIlIIllIIlIlI;
                Object llllllllllllllllllIIIlIIllIIlIII = llllllllllllllllllIIIlIIllIIlIIl.value;
                if (llllllllllllllllllIIIlIIllIIlIII instanceof Boolean) {
                    llllllllllllllllllIIIlIIllIIIlII.add(new BoolSetting.Builder().name(llllllllllllllllllIIIlIIllIIlIIl.getName()).description(llllllllllllllllllIIIlIIllIIlIIl.getName()).defaultValue((Boolean)llllllllllllllllllIIIlIIllIIlIIl.defaultValue).onChanged(llllllllllllllllllIIIlIIIllIllII -> {
                        llllllllllllllllllIIIlIIIllIllIl.value = llllllllllllllllllIIIlIIIllIllII;
                    }).onModuleActivated(llllllllllllllllllIIIlIIIlllIlII -> llllllllllllllllllIIIlIIIlllIlII.set((Boolean)llllllllllllllllllIIIlIIIlllIIll.value)).build());
                    continue;
                }
                if (llllllllllllllllllIIIlIIllIIlIII instanceof Double) {
                    llllllllllllllllllIIIlIIllIIIIll.add(new DoubleSetting.Builder().name(llllllllllllllllllIIIlIIllIIlIIl.getName()).description(llllllllllllllllllIIIlIIllIIlIIl.getName()).defaultValue((Double)llllllllllllllllllIIIlIIllIIlIIl.defaultValue).onChanged(llllllllllllllllllIIIlIIIllllIlI -> {
                        llllllllllllllllllIIIlIIIllllIIl.value = llllllllllllllllllIIIlIIIllllIlI;
                    }).onModuleActivated(llllllllllllllllllIIIlIIlIIIIIII -> llllllllllllllllllIIIlIIlIIIIIII.set((Double)llllllllllllllllllIIIlIIIlllllll.value)).build());
                    continue;
                }
                if (llllllllllllllllllIIIlIIllIIlIII instanceof Float) {
                    llllllllllllllllllIIIlIIllIIIIll.add(new DoubleSetting.Builder().name(llllllllllllllllllIIIlIIllIIlIIl.getName()).description(llllllllllllllllllIIIlIIllIIlIIl.getName()).defaultValue(((Float)llllllllllllllllllIIIlIIllIIlIIl.defaultValue).doubleValue()).onChanged(llllllllllllllllllIIIlIIlIIIIlII -> {
                        llllllllllllllllllIIIlIIlIIIIlIl.value = Float.valueOf(llllllllllllllllllIIIlIIlIIIIlII.floatValue());
                    }).onModuleActivated(llllllllllllllllllIIIlIIlIIIlIlI -> llllllllllllllllllIIIlIIlIIIlIlI.set(((Float)llllllllllllllllllIIIlIIlIIIlIll.value).doubleValue())).build());
                    continue;
                }
                if (llllllllllllllllllIIIlIIllIIlIII instanceof Integer) {
                    llllllllllllllllllIIIlIIllIIIIlI.add(new IntSetting.Builder().name(llllllllllllllllllIIIlIIllIIlIIl.getName()).description(llllllllllllllllllIIIlIIllIIlIIl.getName()).defaultValue((Integer)llllllllllllllllllIIIlIIllIIlIIl.defaultValue).onChanged(llllllllllllllllllIIIlIIlIIlIIII -> {
                        llllllllllllllllllIIIlIIlIIlIIll.value = llllllllllllllllllIIIlIIlIIlIIII;
                    }).onModuleActivated(llllllllllllllllllIIIlIIlIIllIII -> llllllllllllllllllIIIlIIlIIllIII.set((Integer)llllllllllllllllllIIIlIIlIIlIlll.value)).build());
                    continue;
                }
                if (llllllllllllllllllIIIlIIllIIlIII instanceof Long) {
                    llllllllllllllllllIIIlIIllIIIIlI.add(new IntSetting.Builder().name(llllllllllllllllllIIIlIIllIIlIIl.getName()).description(llllllllllllllllllIIIlIIllIIlIIl.getName()).defaultValue(((Long)llllllllllllllllllIIIlIIllIIlIIl.defaultValue).intValue()).onChanged(llllllllllllllllllIIIlIIlIIlllII -> {
                        llllllllllllllllllIIIlIIlIIlllIl.value = llllllllllllllllllIIIlIIlIIlllII.longValue();
                    }).onModuleActivated(llllllllllllllllllIIIlIIlIlIIlII -> llllllllllllllllllIIIlIIlIlIIlII.set(((Long)llllllllllllllllllIIIlIIlIlIIIll.value).intValue())).build());
                    continue;
                }
                if (!(llllllllllllllllllIIIlIIllIIlIII instanceof Color)) continue;
                Color llllllllllllllllllIIIlIIllIIlIll = (Color)llllllllllllllllllIIIlIIllIIlIIl.value;
                llllllllllllllllllIIIlIIllIIIIIl.add(new ColorSetting.Builder().name(llllllllllllllllllIIIlIIllIIlIIl.getName()).description(llllllllllllllllllIIIlIIllIIlIIl.getName()).defaultValue(new SettingColor(llllllllllllllllllIIIlIIllIIlIll.getRed(), llllllllllllllllllIIIlIIllIIlIll.getGreen(), llllllllllllllllllIIIlIIllIIlIll.getBlue(), llllllllllllllllllIIIlIIllIIlIll.getAlpha())).onChanged(llllllllllllllllllIIIlIIlIlIlIlI -> {
                    llllllllllllllllllIIIlIIlIlIlIll.value = new Color(llllllllllllllllllIIIlIIlIlIlIlI.r, llllllllllllllllllIIIlIIlIlIlIlI.g, llllllllllllllllllIIIlIIlIlIlIlI.b, llllllllllllllllllIIIlIIlIlIlIlI.a);
                }).onModuleActivated(llllllllllllllllllIIIlIIlIlIlllI -> llllllllllllllllllIIIlIIlIlIlllI.set(new SettingColor(llllllllllllllllllIIIlIIllIIlIll.getRed(), llllllllllllllllllIIIlIIllIIlIll.getGreen(), llllllllllllllllllIIIlIIllIIlIll.getBlue(), llllllllllllllllllIIIlIIllIIlIll.getAlpha()))).build());
            }
        }
        catch (IllegalAccessException llllllllllllllllllIIIlIIllIIIlIl) {
            llllllllllllllllllIIIlIIllIIIlIl.printStackTrace();
        }
        return settings;
    }

    @Override
    public TabScreen createScreen(GuiTheme llllllllllllllllllIIIlIIllIlllIl) {
        BaritoneTab llllllllllllllllllIIIlIIllIllllI;
        return new BaritoneScreen(llllllllllllllllllIIIlIIllIlllIl, llllllllllllllllllIIIlIIllIllllI);
    }

    private static class BaritoneScreen
    extends WindowTabScreen {
        @Override
        protected void onClosed() {
            SettingsUtil.save((baritone.api.Settings)BaritoneAPI.getSettings());
        }

        public BaritoneScreen(GuiTheme lllllllllllllllllIIlIIlIlIIIlIIl, Tab lllllllllllllllllIIlIIlIlIIIlIII) {
            super(lllllllllllllllllIIlIIlIlIIIlIIl, lllllllllllllllllIIlIIlIlIIIlIII);
            BaritoneScreen lllllllllllllllllIIlIIlIlIIIlllI;
            WTextBox lllllllllllllllllIIlIIlIlIIIlIll = lllllllllllllllllIIlIIlIlIIIlllI.add(lllllllllllllllllIIlIIlIlIIIlIIl.textBox("")).minWidth(400.0).expandX().widget();
            lllllllllllllllllIIlIIlIlIIIlIll.setFocused(true);
            lllllllllllllllllIIlIIlIlIIIlIll.action = () -> {
                BaritoneScreen lllllllllllllllllIIlIIlIIlllllll;
                lllllllllllllllllIIlIIlIIlllllll.clear();
                lllllllllllllllllIIlIIlIIlllllll.add(lllllllllllllllllIIlIIlIlIIIlIll);
                lllllllllllllllllIIlIIlIIlllllll.add(lllllllllllllllllIIlIIlIlIIIlIIl.settings(BaritoneTab.getSettings(), lllllllllllllllllIIlIIlIlIIIlIll.get().trim())).expandX();
            };
            BaritoneTab.getSettings().onActivated();
            lllllllllllllllllIIlIIlIlIIIlllI.add(lllllllllllllllllIIlIIlIlIIIlIIl.settings(BaritoneTab.getSettings(), lllllllllllllllllIIlIIlIlIIIlIll.get().trim())).expandX();
        }
    }
}

