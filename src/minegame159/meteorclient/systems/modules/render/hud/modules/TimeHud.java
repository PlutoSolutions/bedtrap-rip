/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.render.hud.modules;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.render.hud.HUD;
import minegame159.meteorclient.systems.modules.render.hud.modules.DoubleTextHudElement;

public class TimeHud
extends DoubleTextHudElement {
    private final Setting<TimeType> timeType;
    private final SettingGroup sgGeneral;

    @Override
    protected String getRight() {
        String string = "00:00";
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$render$hud$modules$TimeHud$TimeType[this.timeType.get().ordinal()]) {
            case 1: {
                if (this.isInEditor()) {
                    return string;
                }
                int n = (int)(this.mc.field_1687.method_8532() % 24000L);
                if ((n += 6000) > 24000) {
                    n -= 24000;
                }
                string = String.format("%02d:%02d", n / 1000, (int)((double)(n % 1000) / 1000.0 * 60.0));
                break;
            }
            case 2: {
                string = LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
            }
        }
        return string;
    }

    public TimeHud(HUD hUD) {
        super(hUD, "time", "Displays the world time.", "Time: ");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.timeType = this.sgGeneral.add(new EnumSetting.Builder().name("use-time").description("Which time to use.").defaultValue(TimeType.World).build());
    }

    public static enum TimeType {
        World,
        Local;

    }
}

