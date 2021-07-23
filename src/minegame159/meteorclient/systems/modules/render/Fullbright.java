/*
 * Decompiled with CFR 0.151.
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
    private final SettingGroup sgGeneral;
    public final Setting<Mode> mode;

    public static void disable() {
        StaticListener.access$010();
    }

    public Fullbright() {
        super(Categories.Render, "fullbright", "Lights up your world!");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The mode to use for Fullbright.").defaultValue(Mode.Luminance).onChanged(this::lambda$new$0).build());
        MeteorClient.EVENT_BUS.subscribe(StaticListener.class);
    }

    private void lambda$new$0(Mode mode) {
        if (mode == Mode.Luminance) {
            this.mc.field_1690.field_1840 = StaticListener.access$100();
        }
    }

    @Override
    public void onDeactivate() {
        Fullbright.disable();
    }

    public static void enable() {
        StaticListener.access$008();
    }

    public static boolean isEnabled() {
        return StaticListener.access$000() > 0;
    }

    @Override
    public void onActivate() {
        Fullbright.enable();
    }

    public static enum Mode {
        Gamma,
        Luminance;

    }

    private static class StaticListener {
        private static final class_310 mc = class_310.method_1551();
        private static int lastTimesEnabled;
        private static double prevGamma;
        private static final Fullbright fullbright;
        private static int timesEnabled;

        static {
            fullbright = Modules.get().get(Fullbright.class);
            prevGamma = StaticListener.mc.field_1690.field_1840;
        }

        static double access$100() {
            return prevGamma;
        }

        @EventHandler
        private static void onTick(TickEvent.Post post) {
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

        static int access$000() {
            return timesEnabled;
        }

        private StaticListener() {
        }

        static int access$008() {
            return timesEnabled++;
        }

        static int access$010() {
            return timesEnabled--;
        }
    }
}

