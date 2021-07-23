/*
 * Decompiled with CFR 0.151.
 */
package minegame159.meteorclient.systems.modules.bedtrap;

import io.netty.util.internal.ConcurrentSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.PlayerMoveEvent;
import minegame159.meteorclient.events.entity.player.SendMovementPacketsEvent;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.mixin.PlayerPositionLookS2CPacketAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.utils.bedtrap.Timer;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2708;
import net.minecraft.class_2793;
import net.minecraft.class_2828;
import net.minecraft.class_434;

public class PacketFly
extends Module {
    private final SettingGroup sgGeneral;
    private final Setting<Boolean> resetID;
    private final Setting<Boolean> strafeFactor;
    private final Setting<Double> extraFactor;
    private final Setting<Integer> mapTime;
    private final Setting<Integer> loops;
    private final Setting<Boolean> noClip;
    private final Map<Integer, IDtime> teleportmap;
    private final Setting<Boolean> setID;
    private final Setting<Integer> flightMode;
    private final Setting<Boolean> setMove;
    private final Setting<Boolean> setYaw;
    private float pitch;
    private float yaw;
    private final Setting<Boolean> sendTeleport;
    private final Setting<Double> antiFactor;
    private final Set<class_2828> packets = new ConcurrentSet();
    private final Setting<Boolean> clearTeleMap;
    private final Setting<Boolean> updatePosition;
    private final Setting<Boolean> flight;
    private final Setting<Boolean> invalidPacket;
    private int teleportID = 0;
    private final Setting<Boolean> clearIDs;
    private int flightCounter = 0;
    private final Setting<Boolean> doAntiFactor;

    @EventHandler
    public void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.packet instanceof class_2708 && this.mc.field_1724 != null && this.mc.field_1687 != null) {
            class_2338 class_23382 = new class_2338(this.mc.field_1724.method_19538().field_1352, this.mc.field_1724.method_19538().field_1351, this.mc.field_1724.method_19538().field_1350);
            class_2708 class_27082 = (class_2708)receive.packet;
            if (this.mc.field_1724.method_5805() && this.mc.field_1687.method_8393(this.mc.field_1687.method_22350((class_2338)class_23382).method_12004().field_9181, this.mc.field_1687.method_22350((class_2338)class_23382).method_12004().field_9180) && !(this.mc.field_1755 instanceof class_434) && this.clearIDs.get().booleanValue()) {
                this.teleportmap.remove(class_27082.method_11737());
            }
            if (this.setYaw.get().booleanValue()) {
                ((PlayerPositionLookS2CPacketAccessor)receive.packet).setPitch(this.mc.field_1724.field_5965);
                ((PlayerPositionLookS2CPacketAccessor)receive.packet).setYaw(this.mc.field_1724.field_6031);
            }
            if (this.setID.get().booleanValue()) {
                this.teleportID = class_27082.method_11737();
            }
        }
    }

    private void teleportPacket(class_243 class_2432, boolean bl) {
        if (bl) {
            this.mc.field_1724.field_3944.method_2883((class_2596)new class_2793(++this.teleportID));
            this.teleportmap.put(this.teleportID, new IDtime(class_2432, new Timer()));
        }
    }

    private void sendPackets(double d, double d2, double d3, boolean bl) {
        class_243 class_2432 = new class_243(d, d2, d3);
        class_243 class_2433 = this.mc.field_1724.method_19538().method_1019(class_2432);
        class_243 class_2434 = this.outOfBoundsVec(class_2432, class_2433);
        this.packetSender((class_2828)new class_2828.class_2829(class_2433.field_1352, class_2433.field_1351, class_2433.field_1350, this.mc.field_1724.method_24828()));
        if (this.invalidPacket.get().booleanValue()) {
            this.packetSender((class_2828)new class_2828.class_2829(class_2434.field_1352, class_2434.field_1351, class_2434.field_1350, this.mc.field_1724.method_24828()));
        }
        if (this.updatePosition.get().booleanValue()) {
            this.mc.field_1724.method_5814(class_2433.field_1352, class_2433.field_1351, class_2433.field_1350);
        }
        this.teleportPacket(class_2433, bl);
    }

    @EventHandler
    public void onPacketSent(PacketEvent.Send send) {
        class_2828 class_28282;
        if (send.packet instanceof class_2828 && !this.packets.remove(class_28282 = (class_2828)send.packet)) {
            send.setCancelled(true);
        }
    }

    private boolean resetCounter(int n) {
        if (++this.flightCounter >= n) {
            this.flightCounter = 0;
            return true;
        }
        return false;
    }

    public PacketFly() {
        super(Categories.BedTrap, "packet-fly", "Fly using packets.");
        this.teleportmap = new ConcurrentHashMap<Integer, IDtime>();
        this.sgGeneral = this.settings.getDefaultGroup();
        this.flight = this.sgGeneral.add(new BoolSetting.Builder().name("flight").defaultValue(true).build());
        this.flightMode = this.sgGeneral.add(new IntSetting.Builder().name("flight-mode").defaultValue(0).sliderMin(0).sliderMax(1).min(0).max(1).build());
        this.doAntiFactor = this.sgGeneral.add(new BoolSetting.Builder().name("do-anti-factor").defaultValue(true).build());
        this.antiFactor = this.sgGeneral.add(new DoubleSetting.Builder().name("anti-factor").defaultValue(2.5).sliderMin(0.1).sliderMax(3.0).min(0.1).max(3.0).build());
        this.extraFactor = this.sgGeneral.add(new DoubleSetting.Builder().name("extra-factor").defaultValue(1.0).sliderMin(0.1).sliderMax(3.0).min(0.1).max(3.0).build());
        this.strafeFactor = this.sgGeneral.add(new BoolSetting.Builder().name("strafe-factor").defaultValue(true).build());
        this.loops = this.sgGeneral.add(new IntSetting.Builder().name("loops").defaultValue(1).sliderMin(1).sliderMax(10).min(1).max(10).build());
        this.clearTeleMap = this.sgGeneral.add(new BoolSetting.Builder().name("clear-teleport-map").defaultValue(true).build());
        this.mapTime = this.sgGeneral.add(new IntSetting.Builder().name("clear-time").defaultValue(30).sliderMin(1).sliderMax(500).min(1).max(500).build());
        this.clearIDs = this.sgGeneral.add(new BoolSetting.Builder().name("clear-IDs").defaultValue(true).build());
        this.setYaw = this.sgGeneral.add(new BoolSetting.Builder().name("set-yaw").defaultValue(true).build());
        this.setID = this.sgGeneral.add(new BoolSetting.Builder().name("set-ID").defaultValue(true).build());
        this.setMove = this.sgGeneral.add(new BoolSetting.Builder().name("set-move").defaultValue(false).build());
        this.noClip = this.sgGeneral.add(new BoolSetting.Builder().name("no-clip").defaultValue(false).build());
        this.sendTeleport = this.sgGeneral.add(new BoolSetting.Builder().name("teleport").defaultValue(true).build());
        this.resetID = this.sgGeneral.add(new BoolSetting.Builder().name("reset-ID").defaultValue(true).build());
        this.updatePosition = this.sgGeneral.add(new BoolSetting.Builder().name("set-pos").defaultValue(false).build());
        this.invalidPacket = this.sgGeneral.add(new BoolSetting.Builder().name("invalid-packet").defaultValue(true).build());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent playerMoveEvent) {
        if (this.setMove.get().booleanValue() && this.flightCounter != 0) {
            playerMoveEvent.movement = new class_243(this.mc.field_1724.method_18798().field_1352, this.mc.field_1724.method_18798().field_1351, this.mc.field_1724.method_18798().field_1350);
            if (this.noClip.get().booleanValue() && this.checkHitBoxes()) {
                this.mc.field_1724.field_5960 = true;
            }
        }
    }

    private void packetSender(class_2828 class_28282) {
        this.packets.add(class_28282);
        this.mc.field_1724.field_3944.method_2883((class_2596)class_28282);
    }

    private boolean checkHitBoxes() {
        return this.mc.field_1687.method_20812((class_1297)this.mc.field_1724, this.mc.field_1724.method_5829().method_1009(-0.0625, -0.0625, -0.0625)).count() != 0L;
    }

    private class_243 outOfBoundsVec(class_243 class_2432, class_243 class_2433) {
        return class_2433.method_1031(0.0, 1337.0, 0.0);
    }

    private void clean() {
        this.teleportmap.clear();
        this.flightCounter = 0;
        if (this.resetID.get().booleanValue()) {
            this.teleportID = 0;
        }
        this.packets.clear();
    }

    @EventHandler
    public void onSendMovementPackets(SendMovementPacketsEvent.Pre pre) {
        this.mc.field_1724.method_18800(0.0, 0.0, 0.0);
        double d = 0.0;
        boolean bl = this.checkHitBoxes();
        double d2 = this.mc.field_1724.field_3913.field_3904 && (bl || (double)this.mc.field_1724.field_3913.field_3905 == 0.0 && (double)this.mc.field_1724.field_3913.field_3907 == 0.0) ? (this.flight.get().booleanValue() && !bl ? (this.flightMode.get() == 0 ? (this.resetCounter(10) ? -0.032 : 0.062) : (this.resetCounter(20) ? -0.032 : 0.062)) : 0.062) : (this.mc.field_1724.field_3913.field_3903 ? -0.062 : (!bl ? (this.resetCounter(4) ? (this.flight.get().booleanValue() ? -0.04 : 0.0) : 0.0) : (d = 0.0)));
        if (this.doAntiFactor.get().booleanValue() && bl && ((double)this.mc.field_1724.field_3913.field_3905 != 0.0 || (double)this.mc.field_1724.field_3913.field_3907 != 0.0) && d != 0.0) {
            d /= this.antiFactor.get().doubleValue();
        }
        double[] dArray = this.getMotion(this.strafeFactor.get() != false && bl ? 0.031 : 0.26);
        for (int i = 1; i < this.loops.get() + 1; ++i) {
            this.mc.field_1724.method_18800(dArray[0] * (double)i * this.extraFactor.get(), d * (double)i, dArray[1] * (double)i * this.extraFactor.get());
            this.sendPackets(this.mc.field_1724.method_18798().field_1352, this.mc.field_1724.method_18798().field_1351, this.mc.field_1724.method_18798().field_1350, this.sendTeleport.get());
            if (!false) continue;
            return;
        }
    }

    private double[] getMotion(double d) {
        float f = this.mc.field_1724.field_3913.field_3905;
        float f2 = this.mc.field_1724.field_3913.field_3907;
        float f3 = this.mc.field_1724.field_5982 + (this.mc.field_1724.field_6031 - this.mc.field_1724.field_5982) * this.mc.method_1488();
        if (f != 0.0f) {
            if (f2 > 0.0f) {
                f3 += (float)(f > 0.0f ? -45 : 45);
            } else if (f2 < 0.0f) {
                f3 += (float)(f > 0.0f ? 45 : -45);
            }
            f2 = 0.0f;
            if (f > 0.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = -1.0f;
            }
        }
        double d2 = (double)f * d * -Math.sin(Math.toRadians(f3)) + (double)f2 * d * Math.cos(Math.toRadians(f3));
        double d3 = (double)f * d * Math.cos(Math.toRadians(f3)) - (double)f2 * d * -Math.sin(Math.toRadians(f3));
        return new double[]{d2, d3};
    }

    public static class IDtime {
        private final class_243 pos;
        private final Timer timer;

        public Timer getTimer() {
            return this.timer;
        }

        public class_243 getPos() {
            return this.pos;
        }

        public IDtime(class_243 class_2432, Timer timer) {
            this.pos = class_2432;
            this.timer = timer;
            this.timer.reset();
        }
    }
}

