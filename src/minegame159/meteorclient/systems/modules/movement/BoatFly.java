/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_2692
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.BoatMoveEvent;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.player.PlayerUtils;
import net.minecraft.class_243;
import net.minecraft.class_2692;

public class BoatFly
extends Module {
    private final Setting<Double> verticalSpeed;
    private final Setting<Boolean> cancelServerPackets;
    private final Setting<Double> speed;
    private final Setting<Double> fallSpeed;
    private final SettingGroup sgGeneral;

    public BoatFly() {
        super(Categories.Movement, "boat-fly", "Transforms your boat into a plane.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.speed = this.sgGeneral.add(new DoubleSetting.Builder().name("speed").description("Horizontal speed in blocks per second.").defaultValue(10.0).min(0.0).sliderMax(50.0).build());
        this.verticalSpeed = this.sgGeneral.add(new DoubleSetting.Builder().name("vertical-speed").description("Vertical speed in blocks per second.").defaultValue(6.0).min(0.0).sliderMax(20.0).build());
        this.fallSpeed = this.sgGeneral.add(new DoubleSetting.Builder().name("fall-speed").description("How fast you fall in blocks per second.").defaultValue(0.1).min(0.0).build());
        this.cancelServerPackets = this.sgGeneral.add(new BoolSetting.Builder().name("cancel-server-packets").description("Cancels incoming boat move packets.").defaultValue(false).build());
    }

    @EventHandler
    private void onBoatMove(BoatMoveEvent boatMoveEvent) {
        if (boatMoveEvent.boat.method_5642() != this.mc.field_1724) {
            return;
        }
        boatMoveEvent.boat.field_6031 = this.mc.field_1724.field_6031;
        class_243 class_2432 = PlayerUtils.getHorizontalVelocity(this.speed.get());
        double d = class_2432.method_10216();
        double d2 = 0.0;
        double d3 = class_2432.method_10215();
        if (this.mc.field_1690.field_1903.method_1434()) {
            d2 += this.verticalSpeed.get() / 20.0;
        }
        d2 = this.mc.field_1690.field_1867.method_1434() ? (d2 -= this.verticalSpeed.get() / 20.0) : (d2 -= this.fallSpeed.get() / 20.0);
        ((IVec3d)boatMoveEvent.boat.method_18798()).set(d, d2, d3);
    }

    @EventHandler
    private void onReceivePacket(PacketEvent.Receive receive) {
        if (receive.packet instanceof class_2692 && this.cancelServerPackets.get().booleanValue()) {
            receive.cancel();
        }
    }
}

