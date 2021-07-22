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
    private final /* synthetic */ Setting<Double> verticalSpeed;
    private final /* synthetic */ Setting<Boolean> cancelServerPackets;
    private final /* synthetic */ Setting<Double> speed;
    private final /* synthetic */ Setting<Double> fallSpeed;
    private final /* synthetic */ SettingGroup sgGeneral;

    public BoatFly() {
        super(Categories.Movement, "boat-fly", "Transforms your boat into a plane.");
        BoatFly llllllllllllllllllllIIllllIIIlll;
        llllllllllllllllllllIIllllIIIlll.sgGeneral = llllllllllllllllllllIIllllIIIlll.settings.getDefaultGroup();
        llllllllllllllllllllIIllllIIIlll.speed = llllllllllllllllllllIIllllIIIlll.sgGeneral.add(new DoubleSetting.Builder().name("speed").description("Horizontal speed in blocks per second.").defaultValue(10.0).min(0.0).sliderMax(50.0).build());
        llllllllllllllllllllIIllllIIIlll.verticalSpeed = llllllllllllllllllllIIllllIIIlll.sgGeneral.add(new DoubleSetting.Builder().name("vertical-speed").description("Vertical speed in blocks per second.").defaultValue(6.0).min(0.0).sliderMax(20.0).build());
        llllllllllllllllllllIIllllIIIlll.fallSpeed = llllllllllllllllllllIIllllIIIlll.sgGeneral.add(new DoubleSetting.Builder().name("fall-speed").description("How fast you fall in blocks per second.").defaultValue(0.1).min(0.0).build());
        llllllllllllllllllllIIllllIIIlll.cancelServerPackets = llllllllllllllllllllIIllllIIIlll.sgGeneral.add(new BoolSetting.Builder().name("cancel-server-packets").description("Cancels incoming boat move packets.").defaultValue(false).build());
    }

    @EventHandler
    private void onBoatMove(BoatMoveEvent llllllllllllllllllllIIlllIlllIIl) {
        BoatFly llllllllllllllllllllIIllllIIIIII;
        if (llllllllllllllllllllIIlllIlllIIl.boat.method_5642() != llllllllllllllllllllIIllllIIIIII.mc.field_1724) {
            return;
        }
        llllllllllllllllllllIIlllIlllIIl.boat.field_6031 = llllllllllllllllllllIIllllIIIIII.mc.field_1724.field_6031;
        class_243 llllllllllllllllllllIIlllIlllllI = PlayerUtils.getHorizontalVelocity(llllllllllllllllllllIIllllIIIIII.speed.get());
        double llllllllllllllllllllIIlllIllllIl = llllllllllllllllllllIIlllIlllllI.method_10216();
        double llllllllllllllllllllIIlllIllllII = 0.0;
        double llllllllllllllllllllIIlllIlllIll = llllllllllllllllllllIIlllIlllllI.method_10215();
        if (llllllllllllllllllllIIllllIIIIII.mc.field_1690.field_1903.method_1434()) {
            llllllllllllllllllllIIlllIllllII += llllllllllllllllllllIIllllIIIIII.verticalSpeed.get() / 20.0;
        }
        llllllllllllllllllllIIlllIllllII = llllllllllllllllllllIIllllIIIIII.mc.field_1690.field_1867.method_1434() ? (llllllllllllllllllllIIlllIllllII -= llllllllllllllllllllIIllllIIIIII.verticalSpeed.get() / 20.0) : (llllllllllllllllllllIIlllIllllII -= llllllllllllllllllllIIllllIIIIII.fallSpeed.get() / 20.0);
        ((IVec3d)llllllllllllllllllllIIlllIlllIIl.boat.method_18798()).set(llllllllllllllllllllIIlllIllllIl, llllllllllllllllllllIIlllIllllII, llllllllllllllllllllIIlllIlllIll);
    }

    @EventHandler
    private void onReceivePacket(PacketEvent.Receive llllllllllllllllllllIIlllIlIllll) {
        BoatFly llllllllllllllllllllIIlllIllIIII;
        if (llllllllllllllllllllIIlllIlIllll.packet instanceof class_2692 && llllllllllllllllllllIIlllIllIIII.cancelServerPackets.get().booleanValue()) {
            llllllllllllllllllllIIlllIlIllll.cancel();
        }
    }
}

