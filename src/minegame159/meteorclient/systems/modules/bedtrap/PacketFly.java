/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.util.internal.ConcurrentSet
 *  net.minecraft.class_1297
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2708
 *  net.minecraft.class_2793
 *  net.minecraft.class_2828
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_434
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
    private final /* synthetic */ SettingGroup sgGeneral;
    private final /* synthetic */ Setting<Boolean> resetID;
    private final /* synthetic */ Setting<Boolean> strafeFactor;
    private final /* synthetic */ Setting<Double> extraFactor;
    private final /* synthetic */ Setting<Integer> mapTime;
    private final /* synthetic */ Setting<Integer> loops;
    private final /* synthetic */ Setting<Boolean> noClip;
    private final /* synthetic */ Map<Integer, IDtime> teleportmap;
    private final /* synthetic */ Setting<Boolean> setID;
    private final /* synthetic */ Setting<Integer> flightMode;
    private final /* synthetic */ Setting<Boolean> setMove;
    private final /* synthetic */ Setting<Boolean> setYaw;
    private /* synthetic */ float pitch;
    private /* synthetic */ float yaw;
    private final /* synthetic */ Setting<Boolean> sendTeleport;
    private final /* synthetic */ Setting<Double> antiFactor;
    private final /* synthetic */ Set<class_2828> packets;
    private final /* synthetic */ Setting<Boolean> clearTeleMap;
    private final /* synthetic */ Setting<Boolean> updatePosition;
    private final /* synthetic */ Setting<Boolean> flight;
    private final /* synthetic */ Setting<Boolean> invalidPacket;
    private /* synthetic */ int teleportID;
    private final /* synthetic */ Setting<Boolean> clearIDs;
    private /* synthetic */ int flightCounter;
    private final /* synthetic */ Setting<Boolean> doAntiFactor;

    @EventHandler
    public void onPacketReceive(PacketEvent.Receive llllllllllllllllIlllIlIlIIIIIllI) {
        PacketFly llllllllllllllllIlllIlIlIIIIIlll;
        if (llllllllllllllllIlllIlIlIIIIIllI.packet instanceof class_2708 && llllllllllllllllIlllIlIlIIIIIlll.mc.field_1724 != null && llllllllllllllllIlllIlIlIIIIIlll.mc.field_1687 != null) {
            class_2338 llllllllllllllllIlllIlIlIIIIlIll = new class_2338(llllllllllllllllIlllIlIlIIIIIlll.mc.field_1724.method_19538().field_1352, llllllllllllllllIlllIlIlIIIIIlll.mc.field_1724.method_19538().field_1351, llllllllllllllllIlllIlIlIIIIIlll.mc.field_1724.method_19538().field_1350);
            class_2708 llllllllllllllllIlllIlIlIIIIlIlI = (class_2708)llllllllllllllllIlllIlIlIIIIIllI.packet;
            if (llllllllllllllllIlllIlIlIIIIIlll.mc.field_1724.method_5805() && llllllllllllllllIlllIlIlIIIIIlll.mc.field_1687.method_8393(llllllllllllllllIlllIlIlIIIIIlll.mc.field_1687.method_22350((class_2338)llllllllllllllllIlllIlIlIIIIlIll).method_12004().field_9181, llllllllllllllllIlllIlIlIIIIIlll.mc.field_1687.method_22350((class_2338)llllllllllllllllIlllIlIlIIIIlIll).method_12004().field_9180) && !(llllllllllllllllIlllIlIlIIIIIlll.mc.field_1755 instanceof class_434) && llllllllllllllllIlllIlIlIIIIIlll.clearIDs.get().booleanValue()) {
                llllllllllllllllIlllIlIlIIIIIlll.teleportmap.remove(llllllllllllllllIlllIlIlIIIIlIlI.method_11737());
            }
            if (llllllllllllllllIlllIlIlIIIIIlll.setYaw.get().booleanValue()) {
                ((PlayerPositionLookS2CPacketAccessor)llllllllllllllllIlllIlIlIIIIIllI.packet).setPitch(llllllllllllllllIlllIlIlIIIIIlll.mc.field_1724.field_5965);
                ((PlayerPositionLookS2CPacketAccessor)llllllllllllllllIlllIlIlIIIIIllI.packet).setYaw(llllllllllllllllIlllIlIlIIIIIlll.mc.field_1724.field_6031);
            }
            if (llllllllllllllllIlllIlIlIIIIIlll.setID.get().booleanValue()) {
                llllllllllllllllIlllIlIlIIIIIlll.teleportID = llllllllllllllllIlllIlIlIIIIlIlI.method_11737();
            }
        }
    }

    private void teleportPacket(class_243 llllllllllllllllIlllIlIIllIIlIIl, boolean llllllllllllllllIlllIlIIllIIlIII) {
        if (llllllllllllllllIlllIlIIllIIlIII) {
            PacketFly llllllllllllllllIlllIlIIllIIlIlI;
            llllllllllllllllIlllIlIIllIIlIlI.mc.field_1724.field_3944.method_2883((class_2596)new class_2793(++llllllllllllllllIlllIlIIllIIlIlI.teleportID));
            llllllllllllllllIlllIlIIllIIlIlI.teleportmap.put(llllllllllllllllIlllIlIIllIIlIlI.teleportID, new IDtime(llllllllllllllllIlllIlIIllIIlIIl, new Timer()));
        }
    }

    private void sendPackets(double llllllllllllllllIlllIlIIllIlIlII, double llllllllllllllllIlllIlIIllIlIIll, double llllllllllllllllIlllIlIIllIllIlI, boolean llllllllllllllllIlllIlIIllIllIIl) {
        PacketFly llllllllllllllllIlllIlIIllIlIlIl;
        class_243 llllllllllllllllIlllIlIIllIllIII = new class_243(llllllllllllllllIlllIlIIllIlIlII, llllllllllllllllIlllIlIIllIlIIll, llllllllllllllllIlllIlIIllIllIlI);
        class_243 llllllllllllllllIlllIlIIllIlIlll = llllllllllllllllIlllIlIIllIlIlIl.mc.field_1724.method_19538().method_1019(llllllllllllllllIlllIlIIllIllIII);
        class_243 llllllllllllllllIlllIlIIllIlIllI = llllllllllllllllIlllIlIIllIlIlIl.outOfBoundsVec(llllllllllllllllIlllIlIIllIllIII, llllllllllllllllIlllIlIIllIlIlll);
        llllllllllllllllIlllIlIIllIlIlIl.packetSender((class_2828)new class_2828.class_2829(llllllllllllllllIlllIlIIllIlIlll.field_1352, llllllllllllllllIlllIlIIllIlIlll.field_1351, llllllllllllllllIlllIlIIllIlIlll.field_1350, llllllllllllllllIlllIlIIllIlIlIl.mc.field_1724.method_24828()));
        if (llllllllllllllllIlllIlIIllIlIlIl.invalidPacket.get().booleanValue()) {
            llllllllllllllllIlllIlIIllIlIlIl.packetSender((class_2828)new class_2828.class_2829(llllllllllllllllIlllIlIIllIlIllI.field_1352, llllllllllllllllIlllIlIIllIlIllI.field_1351, llllllllllllllllIlllIlIIllIlIllI.field_1350, llllllllllllllllIlllIlIIllIlIlIl.mc.field_1724.method_24828()));
        }
        if (llllllllllllllllIlllIlIIllIlIlIl.updatePosition.get().booleanValue()) {
            llllllllllllllllIlllIlIIllIlIlIl.mc.field_1724.method_5814(llllllllllllllllIlllIlIIllIlIlll.field_1352, llllllllllllllllIlllIlIIllIlIlll.field_1351, llllllllllllllllIlllIlIIllIlIlll.field_1350);
        }
        llllllllllllllllIlllIlIIllIlIlIl.teleportPacket(llllllllllllllllIlllIlIIllIlIlll, llllllllllllllllIlllIlIIllIllIIl);
    }

    @EventHandler
    public void onPacketSent(PacketEvent.Send llllllllllllllllIlllIlIlIIIlIIIl) {
        class_2828 llllllllllllllllIlllIlIlIIIlIlIl;
        PacketFly llllllllllllllllIlllIlIlIIIlIlII;
        if (llllllllllllllllIlllIlIlIIIlIIIl.packet instanceof class_2828 && !llllllllllllllllIlllIlIlIIIlIlII.packets.remove((Object)(llllllllllllllllIlllIlIlIIIlIlIl = (class_2828)llllllllllllllllIlllIlIlIIIlIIIl.packet))) {
            llllllllllllllllIlllIlIlIIIlIIIl.setCancelled(true);
        }
    }

    private boolean resetCounter(int llllllllllllllllIlllIlIIlllllIll) {
        PacketFly llllllllllllllllIlllIlIIlllllllI;
        if (++llllllllllllllllIlllIlIIlllllllI.flightCounter >= llllllllllllllllIlllIlIIlllllIll) {
            llllllllllllllllIlllIlIIlllllllI.flightCounter = 0;
            return true;
        }
        return false;
    }

    public PacketFly() {
        super(Categories.BedTrap, "packet-fly", "Fly using packets.");
        PacketFly llllllllllllllllIlllIlIlIIlIllll;
        llllllllllllllllIlllIlIlIIlIllll.packets = new ConcurrentSet();
        llllllllllllllllIlllIlIlIIlIllll.teleportmap = new ConcurrentHashMap<Integer, IDtime>();
        llllllllllllllllIlllIlIlIIlIllll.flightCounter = 0;
        llllllllllllllllIlllIlIlIIlIllll.teleportID = 0;
        llllllllllllllllIlllIlIlIIlIllll.sgGeneral = llllllllllllllllIlllIlIlIIlIllll.settings.getDefaultGroup();
        llllllllllllllllIlllIlIlIIlIllll.flight = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("flight").defaultValue(true).build());
        llllllllllllllllIlllIlIlIIlIllll.flightMode = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new IntSetting.Builder().name("flight-mode").defaultValue(0).sliderMin(0).sliderMax(1).min(0).max(1).build());
        llllllllllllllllIlllIlIlIIlIllll.doAntiFactor = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("do-anti-factor").defaultValue(true).build());
        llllllllllllllllIlllIlIlIIlIllll.antiFactor = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new DoubleSetting.Builder().name("anti-factor").defaultValue(2.5).sliderMin(0.1).sliderMax(3.0).min(0.1).max(3.0).build());
        llllllllllllllllIlllIlIlIIlIllll.extraFactor = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new DoubleSetting.Builder().name("extra-factor").defaultValue(1.0).sliderMin(0.1).sliderMax(3.0).min(0.1).max(3.0).build());
        llllllllllllllllIlllIlIlIIlIllll.strafeFactor = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("strafe-factor").defaultValue(true).build());
        llllllllllllllllIlllIlIlIIlIllll.loops = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new IntSetting.Builder().name("loops").defaultValue(1).sliderMin(1).sliderMax(10).min(1).max(10).build());
        llllllllllllllllIlllIlIlIIlIllll.clearTeleMap = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("clear-teleport-map").defaultValue(true).build());
        llllllllllllllllIlllIlIlIIlIllll.mapTime = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new IntSetting.Builder().name("clear-time").defaultValue(30).sliderMin(1).sliderMax(500).min(1).max(500).build());
        llllllllllllllllIlllIlIlIIlIllll.clearIDs = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("clear-IDs").defaultValue(true).build());
        llllllllllllllllIlllIlIlIIlIllll.setYaw = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("set-yaw").defaultValue(true).build());
        llllllllllllllllIlllIlIlIIlIllll.setID = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("set-ID").defaultValue(true).build());
        llllllllllllllllIlllIlIlIIlIllll.setMove = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("set-move").defaultValue(false).build());
        llllllllllllllllIlllIlIlIIlIllll.noClip = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("no-clip").defaultValue(false).build());
        llllllllllllllllIlllIlIlIIlIllll.sendTeleport = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("teleport").defaultValue(true).build());
        llllllllllllllllIlllIlIlIIlIllll.resetID = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("reset-ID").defaultValue(true).build());
        llllllllllllllllIlllIlIlIIlIllll.updatePosition = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("set-pos").defaultValue(false).build());
        llllllllllllllllIlllIlIlIIlIllll.invalidPacket = llllllllllllllllIlllIlIlIIlIllll.sgGeneral.add(new BoolSetting.Builder().name("invalid-packet").defaultValue(true).build());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent llllllllllllllllIlllIlIlIIIllIll) {
        PacketFly llllllllllllllllIlllIlIlIIIllIlI;
        if (llllllllllllllllIlllIlIlIIIllIlI.setMove.get().booleanValue() && llllllllllllllllIlllIlIlIIIllIlI.flightCounter != 0) {
            llllllllllllllllIlllIlIlIIIllIll.movement = new class_243(llllllllllllllllIlllIlIlIIIllIlI.mc.field_1724.method_18798().field_1352, llllllllllllllllIlllIlIlIIIllIlI.mc.field_1724.method_18798().field_1351, llllllllllllllllIlllIlIlIIIllIlI.mc.field_1724.method_18798().field_1350);
            if (llllllllllllllllIlllIlIlIIIllIlI.noClip.get().booleanValue() && llllllllllllllllIlllIlIlIIIllIlI.checkHitBoxes()) {
                llllllllllllllllIlllIlIlIIIllIlI.mc.field_1724.field_5960 = true;
            }
        }
    }

    private void packetSender(class_2828 llllllllllllllllIlllIlIIlIlllIlI) {
        PacketFly llllllllllllllllIlllIlIIlIllllIl;
        llllllllllllllllIlllIlIIlIllllIl.packets.add(llllllllllllllllIlllIlIIlIlllIlI);
        llllllllllllllllIlllIlIIlIllllIl.mc.field_1724.field_3944.method_2883((class_2596)llllllllllllllllIlllIlIIlIlllIlI);
    }

    private boolean checkHitBoxes() {
        PacketFly llllllllllllllllIlllIlIlIIIIIIIl;
        return llllllllllllllllIlllIlIlIIIIIIIl.mc.field_1687.method_20812((class_1297)llllllllllllllllIlllIlIlIIIIIIIl.mc.field_1724, llllllllllllllllIlllIlIlIIIIIIIl.mc.field_1724.method_5829().method_1009(-0.0625, -0.0625, -0.0625)).count() != 0L;
    }

    private class_243 outOfBoundsVec(class_243 llllllllllllllllIlllIlIIllIIIIlI, class_243 llllllllllllllllIlllIlIIllIIIIIl) {
        return llllllllllllllllIlllIlIIllIIIIIl.method_1031(0.0, 1337.0, 0.0);
    }

    private void clean() {
        PacketFly llllllllllllllllIlllIlIIlIlllIII;
        llllllllllllllllIlllIlIIlIlllIII.teleportmap.clear();
        llllllllllllllllIlllIlIIlIlllIII.flightCounter = 0;
        if (llllllllllllllllIlllIlIIlIlllIII.resetID.get().booleanValue()) {
            llllllllllllllllIlllIlIIlIlllIII.teleportID = 0;
        }
        llllllllllllllllIlllIlIIlIlllIII.packets.clear();
    }

    @EventHandler
    public void onSendMovementPackets(SendMovementPacketsEvent.Pre llllllllllllllllIlllIlIlIIlIIlll) {
        PacketFly llllllllllllllllIlllIlIlIIlIlIII;
        llllllllllllllllIlllIlIlIIlIlIII.mc.field_1724.method_18800(0.0, 0.0, 0.0);
        double llllllllllllllllIlllIlIlIIlIIllI = 0.0;
        boolean llllllllllllllllIlllIlIlIIlIIlIl = llllllllllllllllIlllIlIlIIlIlIII.checkHitBoxes();
        double d = llllllllllllllllIlllIlIlIIlIlIII.mc.field_1724.field_3913.field_3904 && (llllllllllllllllIlllIlIlIIlIIlIl || (double)llllllllllllllllIlllIlIlIIlIlIII.mc.field_1724.field_3913.field_3905 == 0.0 && (double)llllllllllllllllIlllIlIlIIlIlIII.mc.field_1724.field_3913.field_3907 == 0.0) ? (llllllllllllllllIlllIlIlIIlIlIII.flight.get().booleanValue() && !llllllllllllllllIlllIlIlIIlIIlIl ? (llllllllllllllllIlllIlIlIIlIlIII.flightMode.get() == 0 ? (llllllllllllllllIlllIlIlIIlIlIII.resetCounter(10) ? -0.032 : 0.062) : (llllllllllllllllIlllIlIlIIlIlIII.resetCounter(20) ? -0.032 : 0.062)) : 0.062) : (llllllllllllllllIlllIlIlIIlIlIII.mc.field_1724.field_3913.field_3903 ? -0.062 : (!llllllllllllllllIlllIlIlIIlIIlIl ? (llllllllllllllllIlllIlIlIIlIlIII.resetCounter(4) ? (llllllllllllllllIlllIlIlIIlIlIII.flight.get().booleanValue() ? -0.04 : 0.0) : 0.0) : (llllllllllllllllIlllIlIlIIlIIllI = 0.0)));
        if (llllllllllllllllIlllIlIlIIlIlIII.doAntiFactor.get().booleanValue() && llllllllllllllllIlllIlIlIIlIIlIl && ((double)llllllllllllllllIlllIlIlIIlIlIII.mc.field_1724.field_3913.field_3905 != 0.0 || (double)llllllllllllllllIlllIlIlIIlIlIII.mc.field_1724.field_3913.field_3907 != 0.0) && llllllllllllllllIlllIlIlIIlIIllI != 0.0) {
            llllllllllllllllIlllIlIlIIlIIllI /= llllllllllllllllIlllIlIlIIlIlIII.antiFactor.get().doubleValue();
        }
        double[] llllllllllllllllIlllIlIlIIlIIlII = llllllllllllllllIlllIlIlIIlIlIII.getMotion(llllllllllllllllIlllIlIlIIlIlIII.strafeFactor.get() != false && llllllllllllllllIlllIlIlIIlIIlIl ? 0.031 : 0.26);
        for (int llllllllllllllllIlllIlIlIIlIlIIl = 1; llllllllllllllllIlllIlIlIIlIlIIl < llllllllllllllllIlllIlIlIIlIlIII.loops.get() + 1; ++llllllllllllllllIlllIlIlIIlIlIIl) {
            llllllllllllllllIlllIlIlIIlIlIII.mc.field_1724.method_18800(llllllllllllllllIlllIlIlIIlIIlII[0] * (double)llllllllllllllllIlllIlIlIIlIlIIl * llllllllllllllllIlllIlIlIIlIlIII.extraFactor.get(), llllllllllllllllIlllIlIlIIlIIllI * (double)llllllllllllllllIlllIlIlIIlIlIIl, llllllllllllllllIlllIlIlIIlIIlII[1] * (double)llllllllllllllllIlllIlIlIIlIlIIl * llllllllllllllllIlllIlIlIIlIlIII.extraFactor.get());
            llllllllllllllllIlllIlIlIIlIlIII.sendPackets(llllllllllllllllIlllIlIlIIlIlIII.mc.field_1724.method_18798().field_1352, llllllllllllllllIlllIlIlIIlIlIII.mc.field_1724.method_18798().field_1351, llllllllllllllllIlllIlIlIIlIlIII.mc.field_1724.method_18798().field_1350, llllllllllllllllIlllIlIlIIlIlIII.sendTeleport.get());
        }
    }

    private double[] getMotion(double llllllllllllllllIlllIlIIllllIIlI) {
        PacketFly llllllllllllllllIlllIlIIllllIIll;
        float llllllllllllllllIlllIlIIllllIIIl = llllllllllllllllIlllIlIIllllIIll.mc.field_1724.field_3913.field_3905;
        float llllllllllllllllIlllIlIIllllIIII = llllllllllllllllIlllIlIIllllIIll.mc.field_1724.field_3913.field_3907;
        float llllllllllllllllIlllIlIIlllIllll = llllllllllllllllIlllIlIIllllIIll.mc.field_1724.field_5982 + (llllllllllllllllIlllIlIIllllIIll.mc.field_1724.field_6031 - llllllllllllllllIlllIlIIllllIIll.mc.field_1724.field_5982) * llllllllllllllllIlllIlIIllllIIll.mc.method_1488();
        if (llllllllllllllllIlllIlIIllllIIIl != 0.0f) {
            if (llllllllllllllllIlllIlIIllllIIII > 0.0f) {
                llllllllllllllllIlllIlIIlllIllll += (float)(llllllllllllllllIlllIlIIllllIIIl > 0.0f ? -45 : 45);
            } else if (llllllllllllllllIlllIlIIllllIIII < 0.0f) {
                llllllllllllllllIlllIlIIlllIllll += (float)(llllllllllllllllIlllIlIIllllIIIl > 0.0f ? 45 : -45);
            }
            llllllllllllllllIlllIlIIllllIIII = 0.0f;
            if (llllllllllllllllIlllIlIIllllIIIl > 0.0f) {
                llllllllllllllllIlllIlIIllllIIIl = 1.0f;
            } else if (llllllllllllllllIlllIlIIllllIIIl < 0.0f) {
                llllllllllllllllIlllIlIIllllIIIl = -1.0f;
            }
        }
        double llllllllllllllllIlllIlIIlllIlllI = (double)llllllllllllllllIlllIlIIllllIIIl * llllllllllllllllIlllIlIIllllIIlI * -Math.sin(Math.toRadians(llllllllllllllllIlllIlIIlllIllll)) + (double)llllllllllllllllIlllIlIIllllIIII * llllllllllllllllIlllIlIIllllIIlI * Math.cos(Math.toRadians(llllllllllllllllIlllIlIIlllIllll));
        double llllllllllllllllIlllIlIIlllIllIl = (double)llllllllllllllllIlllIlIIllllIIIl * llllllllllllllllIlllIlIIllllIIlI * Math.cos(Math.toRadians(llllllllllllllllIlllIlIIlllIllll)) - (double)llllllllllllllllIlllIlIIllllIIII * llllllllllllllllIlllIlIIllllIIlI * -Math.sin(Math.toRadians(llllllllllllllllIlllIlIIlllIllll));
        return new double[]{llllllllllllllllIlllIlIIlllIlllI, llllllllllllllllIlllIlIIlllIllIl};
    }

    public static class IDtime {
        private final /* synthetic */ class_243 pos;
        private final /* synthetic */ Timer timer;

        public Timer getTimer() {
            IDtime llllllllllllllllllIIlllllIlIIIIl;
            return llllllllllllllllllIIlllllIlIIIIl.timer;
        }

        public class_243 getPos() {
            IDtime llllllllllllllllllIIlllllIlIIlII;
            return llllllllllllllllllIIlllllIlIIlII.pos;
        }

        public IDtime(class_243 llllllllllllllllllIIlllllIlIIlll, Timer llllllllllllllllllIIlllllIlIlIIl) {
            IDtime llllllllllllllllllIIlllllIlIlIII;
            llllllllllllllllllIIlllllIlIlIII.pos = llllllllllllllllllIIlllllIlIIlll;
            llllllllllllllllllIIlllllIlIlIII.timer = llllllllllllllllllIIlllllIlIlIIl;
            llllllllllllllllllIIlllllIlIlIII.timer.reset();
        }
    }
}

