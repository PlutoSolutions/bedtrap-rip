/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 */
package minegame159.meteorclient.systems.modules.render;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import net.minecraft.class_310;

public class Fullbright
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    public final /* synthetic */ Setting<Mode> mode;

    public static void disable() {
        StaticListener.timesEnabled--;
    }

    public Fullbright() {
        super(Categories.Render, "fullbright", "Lights up your world!");
        Fullbright lIIlIlIllIIlIll;
        lIIlIlIllIIlIll.sgGeneral = lIIlIlIllIIlIll.settings.getDefaultGroup();
        lIIlIlIllIIlIll.mode = lIIlIlIllIIlIll.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The mode to use for Fullbright.").defaultValue(Mode.Luminance).onChanged(lIIlIlIllIIIIlI -> {
            if (lIIlIlIllIIIIlI == Mode.Luminance) {
                lIIlIlIllIIIlIl.mc.field_1690.field_1840 = StaticListener.prevGamma;
            }
        }).build());
        MeteorClient.EVENT_BUS.subscribe(StaticListener.class);
    }

    @Override
    public void onDeactivate() {
        Fullbright.disable();
    }

    public static void enable() {
        StaticListener.timesEnabled++;
    }

    public static boolean isEnabled() {
        return StaticListener.timesEnabled > 0;
    }

    @Override
    public void onActivate() {
        Fullbright.enable();
    }

    public static enum Mode {
        Gamma,
        Luminance;


        private Mode() {
            Mode lIIlIllIIlllll;
        }
    }

    private static class StaticListener {
        private static final /* synthetic */ class_310 mc;
        private static /* synthetic */ int lastTimesEnabled;
        private static /* synthetic */ double prevGamma;
        private static final /* synthetic */ Fullbright fullbright;
        private static /* synthetic */ int timesEnabled;

        static {
            mc = class_310.method_1551();
            fullbright = Modules.get().get(Fullbright.class);
            prevGamma = StaticListener.mc.field_1690.field_1840;
        }

        @EventHandler
        private static void onTick(TickEvent.Post lIIllIIIllIIlll) {
            if (timesEnabled > 0 && lastTimesEnabled == 0) {
                prevGamma = StaticListener.mc.field_1690.field_1840;
            } else if (timesEnabled == 0 && lastTimesEnabled > 0 && StaticListener.fullbright.mode.get() == Mode.Gamma) {
                double d = StaticListener.mc.field_1690.field_1840 = prevGamma == 16.0 ? 1.0 : prevGamma;
            }
            if (timesEnabled > 0 && StaticListener.fullbright.mode.get() == Mode.Gamma) {
                StaticListener.mc.field_1690.field_1840 = 16.0;
            }
            lastTimesEnabled = timesEnabled;
        }

        private StaticListener() {
            StaticListener lIIllIIIllIlIIl;
        }
    }
}

