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
    private final SettingGroup sgGeneral;
    private final Setting<Mode> lookMode;

    public EndermanLook() {
        super(Categories.World, "enderman-look", "Either looks at all Endermen or prevents you from looking at Endermen.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.lookMode = this.sgGeneral.add(new EnumSetting.Builder().name("look-mode").description("How this module behaves.").defaultValue(Mode.LookAway).build());
    }

    private boolean shouldLook() {
        for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
            if (!(class_12972 instanceof class_1560) || !class_12972.method_5805() || !this.angleCheck(class_12972)) continue;
            return true;
        }
        return false;
    }

    @EventHandler
    private void onTick(TickEvent.Pre pre) {
        if (this.lookMode.get() == Mode.LookAway) {
            if (this.mc.field_1724.field_7503.field_7477 || !this.shouldLook()) {
                return;
            }
            Rotations.rotate(this.mc.field_1724.field_6031, 90.0, -75, null);
        } else {
            for (class_1297 class_12972 : this.mc.field_1687.method_18112()) {
                class_1560 class_15602;
                if (!(class_12972 instanceof class_1560) || (class_15602 = (class_1560)class_12972).method_7028() || !class_15602.method_5805() || !this.mc.field_1724.method_6057((class_1297)class_15602)) continue;
                Rotations.rotate(Rotations.getYaw((class_1297)class_15602), Rotations.getPitch((class_1297)class_15602, Target.Head), -75, null);
                break;
            }
        }
    }

    private boolean angleCheck(class_1297 class_12972) {
        class_243 class_2432 = this.mc.field_1724.method_5828(1.0f).method_1029();
        class_243 class_2433 = new class_243(class_12972.method_23317() - this.mc.field_1724.method_23317(), class_12972.method_23320() - this.mc.field_1724.method_23320(), class_12972.method_23321() - this.mc.field_1724.method_23321());
        double d = class_2433.method_1033();
        double d2 = class_2432.method_1026(class_2433 = class_2433.method_1029());
        return d2 > 1.0 - 0.025 / d && this.mc.field_1724.method_6057(class_12972);
    }

    public static enum Mode {
        LookAt,
        LookAway;

    }
}

