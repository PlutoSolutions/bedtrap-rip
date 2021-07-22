/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_243;

public class FastClimb
extends Module {
    private final SettingGroup sgGeneral;
    private final Setting<Double> speed;

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (!this.mc.field_1724.method_6101() || !this.mc.field_1724.field_5976) {
            return;
        }
        if (this.mc.field_1724.field_3913.field_3905 == 0.0f && this.mc.field_1724.field_3913.field_3907 == 0.0f) {
            return;
        }
        class_243 class_2432 = this.mc.field_1724.method_18798();
        this.mc.field_1724.method_18800(class_2432.field_1352, this.speed.get().doubleValue(), class_2432.field_1350);
    }

    public FastClimb() {
        super(Categories.Movement, "fast-climb", "Allows you to climb faster.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.speed = this.sgGeneral.add(new DoubleSetting.Builder().name("climb-speed").description("Your climb speed.").defaultValue(0.2872).min(0.0).build());
    }
}

