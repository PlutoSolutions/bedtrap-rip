/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2244
 *  net.minecraft.class_2338
 *  net.minecraft.class_2338$class_2339
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_2244;
import net.minecraft.class_2338;

public class ReverseStep
extends Module {
    private final SettingGroup sgGeneral;
    private final Setting<Double> fallSpeed;
    private final Setting<Double> fallDistance;

    private boolean check(class_2338.class_2339 class_23392, int n, int n2) {
        class_23392.method_10100(n, 0, n2);
        boolean bl = this.mc.field_1687.method_8320((class_2338)class_23392).method_26204() instanceof class_2244;
        class_23392.method_10100(-n, 0, -n2);
        return bl;
    }

    public ReverseStep() {
        super(Categories.Movement, "reverse-step", "Allows you to fall down blocks at a greater speed.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.fallSpeed = this.sgGeneral.add(new DoubleSetting.Builder().name("fall-speed").description("How fast to fall in blocks per second.").defaultValue(3.0).min(0.0).sliderMax(10.0).build());
        this.fallDistance = this.sgGeneral.add(new DoubleSetting.Builder().name("fall-distance").description("The maximum fall distance this setting will activate at.").defaultValue(3.0).min(0.0).sliderMax(10.0).build());
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        if (!this.mc.field_1724.method_24828() || this.mc.field_1724.method_21754() || this.mc.field_1724.method_5869() || this.mc.field_1724.method_5771() || this.mc.field_1690.field_1903.method_1434() || this.mc.field_1724.field_5960 || this.mc.field_1724.field_6250 == 0.0f && this.mc.field_1724.field_6212 == 0.0f) {
            return;
        }
        if (!this.isOnBed() && !this.mc.field_1687.method_18026(this.mc.field_1724.method_5829().method_989(0.0, (double)((float)(-(this.fallDistance.get() + 0.01))), 0.0))) {
            ((IVec3d)this.mc.field_1724.method_18798()).setY(-this.fallSpeed.get().doubleValue());
        }
    }

    private boolean isOnBed() {
        class_2338.class_2339 class_23392 = this.mc.field_1724.method_24515().method_25503();
        if (this.check(class_23392, 0, 0)) {
            return true;
        }
        double d = this.mc.field_1724.method_23317() - (double)class_23392.method_10263();
        double d2 = this.mc.field_1724.method_23321() - (double)class_23392.method_10260();
        if (d >= 0.0 && d <= 0.3 && this.check(class_23392, -1, 0)) {
            return true;
        }
        if (d >= 0.7 && this.check(class_23392, 1, 0)) {
            return true;
        }
        if (d2 >= 0.0 && d2 <= 0.3 && this.check(class_23392, 0, -1)) {
            return true;
        }
        if (d2 >= 0.7 && this.check(class_23392, 0, 1)) {
            return true;
        }
        if (d >= 0.0 && d <= 0.3 && d2 >= 0.0 && d2 <= 0.3 && this.check(class_23392, -1, -1)) {
            return true;
        }
        if (d >= 0.0 && d <= 0.3 && d2 >= 0.7 && this.check(class_23392, -1, 1)) {
            return true;
        }
        if (d >= 0.7 && d2 >= 0.0 && d2 <= 0.3 && this.check(class_23392, 1, -1)) {
            return true;
        }
        return d >= 0.7 && d2 >= 0.7 && this.check(class_23392, 1, 1);
    }
}

