/*
 * Decompiled with CFR 0.150.
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
    private final /* synthetic */ Setting<TimeType> timeType;
    private final /* synthetic */ SettingGroup sgGeneral;

    @Override
    protected String getRight() {
        TimeHud lllllllllllllllllIlIIllIIlIlllIl;
        String lllllllllllllllllIlIIllIIlIllllI = "00:00";
        switch (lllllllllllllllllIlIIllIIlIlllIl.timeType.get()) {
            case World: {
                if (lllllllllllllllllIlIIllIIlIlllIl.isInEditor()) {
                    return lllllllllllllllllIlIIllIIlIllllI;
                }
                int lllllllllllllllllIlIIllIIllIIIII = (int)(lllllllllllllllllIlIIllIIlIlllIl.mc.field_1687.method_8532() % 24000L);
                if ((lllllllllllllllllIlIIllIIllIIIII += 6000) > 24000) {
                    lllllllllllllllllIlIIllIIllIIIII -= 24000;
                }
                lllllllllllllllllIlIIllIIlIllllI = String.format("%02d:%02d", lllllllllllllllllIlIIllIIllIIIII / 1000, (int)((double)(lllllllllllllllllIlIIllIIllIIIII % 1000) / 1000.0 * 60.0));
                break;
            }
            case Local: {
                lllllllllllllllllIlIIllIIlIllllI = LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
            }
        }
        return lllllllllllllllllIlIIllIIlIllllI;
    }

    public TimeHud(HUD lllllllllllllllllIlIIllIIllIIlII) {
        super(lllllllllllllllllIlIIllIIllIIlII, "time", "Displays the world time.", "Time: ");
        TimeHud lllllllllllllllllIlIIllIIllIIlll;
        lllllllllllllllllIlIIllIIllIIlll.sgGeneral = lllllllllllllllllIlIIllIIllIIlll.settings.getDefaultGroup();
        lllllllllllllllllIlIIllIIllIIlll.timeType = lllllllllllllllllIlIIllIIllIIlll.sgGeneral.add(new EnumSetting.Builder().name("use-time").description("Which time to use.").defaultValue(TimeType.World).build());
    }

    public static enum TimeType {
        World,
        Local;


        private TimeType() {
            TimeType llllllllllllllllllllIIIlIIIlIIll;
        }
    }
}

