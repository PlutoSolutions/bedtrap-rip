/*
 * Decompiled with CFR 0.150.
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
    private static final /* synthetic */ List<Setting<SettingColor>> colorSettings;
    private static final /* synthetic */ List<SettingColor> colors;
    private static final /* synthetic */ List<Runnable> listeners;
    public static final /* synthetic */ RainbowColor GLOBAL;

    public RainbowColors() {
        RainbowColors llllllllllllllllIlllIIlIlllllIll;
    }

    @EventHandler
    private static void onTick(TickEvent.Post llllllllllllllllIlllIIlIlllIIlII) {
        GLOBAL.getNext();
        for (Setting<SettingColor> llllllllllllllllIlllIIlIlllIlIlI : colorSettings) {
            if (llllllllllllllllIlllIIlIlllIlIlI.module != null && !llllllllllllllllIlllIIlIlllIlIlI.module.isActive()) continue;
            llllllllllllllllIlllIIlIlllIlIlI.get().update();
        }
        for (SettingColor llllllllllllllllIlllIIlIlllIlIIl : colors) {
            llllllllllllllllIlllIIlIlllIlIIl.update();
        }
        for (Waypoint llllllllllllllllIlllIIlIlllIlIII : Waypoints.get()) {
            llllllllllllllllIlllIIlIlllIlIII.color.update();
        }
        if (Utils.mc.field_1755 instanceof WidgetScreen) {
            for (SettingGroup llllllllllllllllIlllIIlIlllIIllI : GuiThemes.get().settings) {
                for (Setting<?> llllllllllllllllIlllIIlIlllIIlll : llllllllllllllllIlllIIlIlllIIllI) {
                    if (!(llllllllllllllllIlllIIlIlllIIlll instanceof ColorSetting)) continue;
                    ((SettingColor)llllllllllllllllIlllIIlIlllIIlll.get()).update();
                }
            }
        }
        for (Runnable llllllllllllllllIlllIIlIlllIIlIl : listeners) {
            llllllllllllllllIlllIIlIlllIIlIl.run();
        }
    }

    public static void removeSetting(Setting<SettingColor> llllllllllllllllIlllIIlIllllIllI) {
        colorSettings.remove(llllllllllllllllIlllIIlIllllIllI);
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

    public static void add(SettingColor llllllllllllllllIlllIIlIllllIIlI) {
        colors.add(llllllllllllllllIlllIIlIllllIIlI);
    }

    public static void register(Runnable llllllllllllllllIlllIIlIllllIIII) {
        listeners.add(llllllllllllllllIlllIIlIllllIIII);
    }

    public static void addSetting(Setting<SettingColor> llllllllllllllllIlllIIlIlllllIII) {
        colorSettings.add(llllllllllllllllIlllIIlIlllllIII);
    }
}

