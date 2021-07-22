/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1560
 *  net.minecraft.class_243
 */
package minegame159.meteorclient.systems.modules.world;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.entity.Target;
import minegame159.meteorclient.utils.player.Rotations;
import net.minecraft.class_1297;
import net.minecraft.class_1560;
import net.minecraft.class_243;

public class EndermanLook
extends Module {
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Mode> lookMode;

    public EndermanLook() {
        super(Categories.World, "enderman-look", "Either looks at all Endermen or prevents you from looking at Endermen.");
        EndermanLook lllllllllllllllllIllIllIlIllllll;
        lllllllllllllllllIllIllIlIllllll.sgGeneral = lllllllllllllllllIllIllIlIllllll.settings.getDefaultGroup();
        lllllllllllllllllIllIllIlIllllll.lookMode = lllllllllllllllllIllIllIlIllllll.sgGeneral.add(new EnumSetting.Builder().name("look-mode").description("How this module behaves.").defaultValue(Mode.LookAway).build());
    }

    private boolean shouldLook() {
        EndermanLook lllllllllllllllllIllIllIlIlIllIl;
        for (class_1297 lllllllllllllllllIllIllIlIlIllll : lllllllllllllllllIllIllIlIlIllIl.mc.field_1687.method_18112()) {
            if (!(lllllllllllllllllIllIllIlIlIllll instanceof class_1560) || !lllllllllllllllllIllIllIlIlIllll.method_5805() || !lllllllllllllllllIllIllIlIlIllIl.angleCheck(lllllllllllllllllIllIllIlIlIllll)) continue;
            return true;
        }
        return false;
    }

    @EventHandler
    private void onTick(TickEvent.Pre lllllllllllllllllIllIllIlIllIlll) {
        EndermanLook lllllllllllllllllIllIllIlIlllIII;
        if (lllllllllllllllllIllIllIlIlllIII.lookMode.get() == Mode.LookAway) {
            if (lllllllllllllllllIllIllIlIlllIII.mc.field_1724.field_7503.field_7477 || !lllllllllllllllllIllIllIlIlllIII.shouldLook()) {
                return;
            }
            Rotations.rotate(lllllllllllllllllIllIllIlIlllIII.mc.field_1724.field_6031, 90.0, -75, null);
        } else {
            for (class_1297 lllllllllllllllllIllIllIlIlllIIl : lllllllllllllllllIllIllIlIlllIII.mc.field_1687.method_18112()) {
                class_1560 lllllllllllllllllIllIllIlIlllIlI;
                if (!(lllllllllllllllllIllIllIlIlllIIl instanceof class_1560) || (lllllllllllllllllIllIllIlIlllIlI = (class_1560)lllllllllllllllllIllIllIlIlllIIl).method_7028() || !lllllllllllllllllIllIllIlIlllIlI.method_5805() || !lllllllllllllllllIllIllIlIlllIII.mc.field_1724.method_6057((class_1297)lllllllllllllllllIllIllIlIlllIlI)) continue;
                Rotations.rotate(Rotations.getYaw((class_1297)lllllllllllllllllIllIllIlIlllIlI), Rotations.getPitch((class_1297)lllllllllllllllllIllIllIlIlllIlI, Target.Head), -75, null);
                break;
            }
        }
    }

    private boolean angleCheck(class_1297 lllllllllllllllllIllIllIlIlIIIll) {
        EndermanLook lllllllllllllllllIllIllIlIlIIlII;
        class_243 lllllllllllllllllIllIllIlIlIIIlI = lllllllllllllllllIllIllIlIlIIlII.mc.field_1724.method_5828(1.0f).method_1029();
        class_243 lllllllllllllllllIllIllIlIlIIIIl = new class_243(lllllllllllllllllIllIllIlIlIIIll.method_23317() - lllllllllllllllllIllIllIlIlIIlII.mc.field_1724.method_23317(), lllllllllllllllllIllIllIlIlIIIll.method_23320() - lllllllllllllllllIllIllIlIlIIlII.mc.field_1724.method_23320(), lllllllllllllllllIllIllIlIlIIIll.method_23321() - lllllllllllllllllIllIllIlIlIIlII.mc.field_1724.method_23321());
        double lllllllllllllllllIllIllIlIlIIIII = lllllllllllllllllIllIllIlIlIIIIl.method_1033();
        double lllllllllllllllllIllIllIlIIlllll = lllllllllllllllllIllIllIlIlIIIlI.method_1026(lllllllllllllllllIllIllIlIlIIIIl = lllllllllllllllllIllIllIlIlIIIIl.method_1029());
        return lllllllllllllllllIllIllIlIIlllll > 1.0 - 0.025 / lllllllllllllllllIllIllIlIlIIIII && lllllllllllllllllIllIllIlIlIIlII.mc.field_1724.method_6057(lllllllllllllllllIllIllIlIlIIIll);
    }

    public static enum Mode {
        LookAt,
        LookAway;


        private Mode() {
            Mode lllllllllllllllllIIlIllllllIlIlI;
        }
    }
}

