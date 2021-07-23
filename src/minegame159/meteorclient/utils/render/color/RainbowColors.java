/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.utils.render.color;

import java.util.List;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.gui.GuiThemes;
import minegame159.meteorclient.gui.WidgetScreen;
import minegame159.meteorclient.gui.tabs.builtin.ConfigTab;
import minegame159.meteorclient.settings.ColorSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.waypoints.Waypoint;
import minegame159.meteorclient.systems.waypoints.Waypoints;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.misc.UnorderedArrayList;
import minegame159.meteorclient.utils.render.color.RainbowColor;
import minegame159.meteorclient.utils.render.color.SettingColor;

public class RainbowColors {
    private static final List<Setting<SettingColor>> colorSettings;
    private static final List<SettingColor> colors;
    private static final List<Runnable> listeners;
    public static final RainbowColor GLOBAL;

    @EventHandler
    private static void onTick(TickEvent.Post post) {
        GLOBAL.getNext();
        for (Setting<SettingColor> object : colorSettings) {
            if (object.module != null && !object.module.isActive()) continue;
            object.get().update();
        }
        for (SettingColor settingColor : colors) {
            settingColor.update();
        }
        for (Waypoint waypoint : Waypoints.get()) {
            waypoint.color.update();
        }
        if (Utils.mc.field_1755 instanceof WidgetScreen) {
            for (SettingGroup settingGroup : GuiThemes.get().settings) {
                for (Setting<?> setting : settingGroup) {
                    if (!(setting instanceof ColorSetting)) continue;
                    ((SettingColor)setting.get()).update();
                }
            }
        }
        for (Runnable runnable : listeners) {
            runnable.run();
        }
    }

    public static void removeSetting(Setting<SettingColor> setting) {
        colorSettings.remove(setting);
    }

    static {
        GLOBAL = new RainbowColor().setSpeed(ConfigTab.rainbowSpeed.get() / 100.0);
        colorSettings = new UnorderedArrayList<Setting<SettingColor>>();
        colors = new UnorderedArrayList<SettingColor>();
        listeners = new UnorderedArrayList<Runnable>();
    }

    public static void init() {
        MeteorClient.EVENT_BUS.subscribe(RainbowColors.class);
    }

    public static void add(SettingColor settingColor) {
        colors.add(settingColor);
    }

    public static void register(Runnable runnable) {
        listeners.add(runnable);
    }

    public static void addSetting(Setting<SettingColor> setting) {
        colorSettings.add(setting);
    }
}

