/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1293
 *  net.minecraft.class_1294
 */
package minegame159.meteorclient.systems.modules.player;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.StatusEffectInstanceAccessor;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_1293;
import net.minecraft.class_1294;

public class SpeedMine
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    public final /* synthetic */ Setting<Double> modifier;
    public final /* synthetic */ Setting<Mode> mode;

    public SpeedMine() {
        super(Categories.Player, "speed-mine", "Allows you to quickly mine blocks.");
        SpeedMine lIlIlIllIIIIlII;
        lIlIlIllIIIIlII.sgGeneral = lIlIlIllIIIIlII.settings.getDefaultGroup();
        lIlIlIllIIIIlII.mode = lIlIlIllIIIIlII.sgGeneral.add(new EnumSetting.Builder().name("mode").defaultValue(Mode.Normal).build());
        lIlIlIllIIIIlII.modifier = lIlIlIllIIIIlII.sgGeneral.add(new DoubleSetting.Builder().name("modifier").description("Mining speed modifier. An additional value of 0.2 is equivalent to one haste level (1.2 = haste 1).").defaultValue(1.4).min(0.0).sliderMin(1.0).sliderMax(10.0).build());
    }

    @EventHandler
    public void onTick(TickEvent.Post lIlIlIlIllllIll) {
        SpeedMine lIlIlIlIlllllII;
        Mode lIlIlIlIllllIlI = lIlIlIlIlllllII.mode.get();
        if (lIlIlIlIllllIlI == Mode.Haste1 || lIlIlIlIllllIlI == Mode.Haste2) {
            int lIlIlIlIlllllIl;
            int n = lIlIlIlIlllllIl = lIlIlIlIllllIlI == Mode.Haste2 ? 1 : 0;
            if (lIlIlIlIlllllII.mc.field_1724.method_6059(class_1294.field_5917)) {
                class_1293 lIlIlIlIllllllI = lIlIlIlIlllllII.mc.field_1724.method_6112(class_1294.field_5917);
                ((StatusEffectInstanceAccessor)lIlIlIlIllllllI).setAmplifier(lIlIlIlIlllllIl);
                if (lIlIlIlIllllllI.method_5584() < 20) {
                    ((StatusEffectInstanceAccessor)lIlIlIlIllllllI).setDuration(20);
                }
            } else {
                lIlIlIlIlllllII.mc.field_1724.method_6092(new class_1293(class_1294.field_5917, 20, lIlIlIlIlllllIl, false, false, false));
            }
        }
    }

    public static enum Mode {
        Normal,
        Haste1,
        Haste2;


        private Mode() {
            Mode lIIlIIlllllIlII;
        }
    }
}

