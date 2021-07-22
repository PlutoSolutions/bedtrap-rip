/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1304
 *  net.minecraft.class_1770
 *  net.minecraft.class_1802
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_243
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3965
 */
package minegame159.meteorclient.systems.modules.movement.elytrafly;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.MeteorClient;
import minegame159.meteorclient.events.entity.player.PlayerMoveEvent;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixininterface.IVec3d;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.elytrafly.ElytraFlightMode;
import minegame159.meteorclient.systems.modules.movement.elytrafly.ElytraFlightModes;
import minegame159.meteorclient.systems.modules.movement.elytrafly.modes.Packet;
import minegame159.meteorclient.systems.modules.movement.elytrafly.modes.Vanilla;
import minegame159.meteorclient.systems.modules.player.ChestSwap;
import net.minecraft.class_1297;
import net.minecraft.class_1304;
import net.minecraft.class_1770;
import net.minecraft.class_1802;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2848;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public class ElytraFly
extends Module {
    private final /* synthetic */ Setting<Boolean> enableAutopilot;
    public final /* synthetic */ Setting<ElytraFlightModes> flightMode;
    private /* synthetic */ ElytraFlightMode currentMode;
    public final /* synthetic */ Setting<Double> autoPilotFireworkDelay;
    private final /* synthetic */ StaticGroundListener staticGroundListener;
    private final /* synthetic */ SettingGroup sgAutopilot;
    public final /* synthetic */ Setting<Boolean> stopInWater;
    public final /* synthetic */ Setting<Boolean> dontGoIntoUnloadedChunks;
    public final /* synthetic */ Setting<Double> verticalSpeed;
    public final /* synthetic */ Setting<Integer> replaceDurability;
    public final /* synthetic */ Setting<ChestSwapMode> chestSwap;
    public final /* synthetic */ Setting<Boolean> autoTakeOff;
    public final /* synthetic */ Setting<Boolean> noCrash;
    public final /* synthetic */ Setting<Integer> crashLookAhead;
    public final /* synthetic */ Setting<Boolean> replace;
    public final /* synthetic */ Setting<Double> fallMultiplier;
    public final /* synthetic */ Setting<Boolean> moveForward;
    public final /* synthetic */ Setting<Double> autoPilotMinimumHeight;
    private final /* synthetic */ Setting<Boolean> instaDrop;
    private final /* synthetic */ StaticInstaDropListener staticInstadropListener;
    public final /* synthetic */ Setting<Boolean> useFireworks;
    private final /* synthetic */ SettingGroup sgGeneral;
    public final /* synthetic */ Setting<Double> horizontalSpeed;

    @EventHandler
    private void onTick(TickEvent.Post lllIIlIIIlIIlll) {
        ElytraFly lllIIlIIIlIlIII;
        lllIIlIIIlIlIII.currentMode.onTick();
    }

    @Override
    public void onActivate() {
        ElytraFly lllIIlIIIllllll;
        lllIIlIIIllllll.currentMode.onActivate();
        if ((lllIIlIIIllllll.chestSwap.get() == ChestSwapMode.Always || lllIIlIIIllllll.chestSwap.get() == ChestSwapMode.WaitForGround) && lllIIlIIIllllll.mc.field_1724.method_6118(class_1304.field_6174).method_7909() != class_1802.field_8833) {
            Modules.get().get(ChestSwap.class).swap();
        }
    }

    protected void enableInstaDropListener() {
        ElytraFly lllIIlIIIIlIIIl;
        MeteorClient.EVENT_BUS.subscribe(lllIIlIIIIlIIIl.staticInstadropListener);
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent lllIIlIIIlIllIl) {
        ElytraFly lllIIlIIIllIIII;
        if (!(lllIIlIIIllIIII.mc.field_1724.method_6118(class_1304.field_6174).method_7909() instanceof class_1770)) {
            return;
        }
        lllIIlIIIllIIII.currentMode.autoTakeoff();
        if (lllIIlIIIllIIII.mc.field_1724.method_6128()) {
            lllIIlIIIllIIII.currentMode.velX = 0.0;
            lllIIlIIIllIIII.currentMode.velY = lllIIlIIIlIllIl.movement.field_1351;
            lllIIlIIIllIIII.currentMode.velZ = 0.0;
            lllIIlIIIllIIII.currentMode.forward = class_243.method_1030((float)0.0f, (float)lllIIlIIIllIIII.mc.field_1724.field_6031).method_1021(0.1);
            lllIIlIIIllIIII.currentMode.right = class_243.method_1030((float)0.0f, (float)(lllIIlIIIllIIII.mc.field_1724.field_6031 + 90.0f)).method_1021(0.1);
            if (lllIIlIIIllIIII.mc.field_1724.method_5799() && lllIIlIIIllIIII.stopInWater.get().booleanValue()) {
                lllIIlIIIllIIII.mc.method_1562().method_2883((class_2596)new class_2848((class_1297)lllIIlIIIllIIII.mc.field_1724, class_2848.class_2849.field_12982));
                return;
            }
            lllIIlIIIllIIII.currentMode.handleFallMultiplier();
            if (lllIIlIIIllIIII.enableAutopilot.get().booleanValue()) {
                lllIIlIIIllIIII.currentMode.handleAutopilot();
            }
            lllIIlIIIllIIII.currentMode.handleHorizontalSpeed();
            lllIIlIIIllIIII.currentMode.handleVerticalSpeed();
            int lllIIlIIIllIlIl = (int)((lllIIlIIIllIIII.mc.field_1724.method_23317() + lllIIlIIIllIIII.currentMode.velX) / 16.0);
            int lllIIlIIIllIlII = (int)((lllIIlIIIllIIII.mc.field_1724.method_23321() + lllIIlIIIllIIII.currentMode.velZ) / 16.0);
            if (lllIIlIIIllIIII.dontGoIntoUnloadedChunks.get().booleanValue()) {
                if (lllIIlIIIllIIII.mc.field_1687.method_2935().method_12123(lllIIlIIIllIlIl, lllIIlIIIllIlII)) {
                    ((IVec3d)lllIIlIIIlIllIl.movement).set(lllIIlIIIllIIII.currentMode.velX, lllIIlIIIllIIII.currentMode.velY, lllIIlIIIllIIII.currentMode.velZ);
                } else {
                    ((IVec3d)lllIIlIIIlIllIl.movement).set(0.0, lllIIlIIIllIIII.currentMode.velY, 0.0);
                }
            } else {
                ((IVec3d)lllIIlIIIlIllIl.movement).set(lllIIlIIIllIIII.currentMode.velX, lllIIlIIIllIIII.currentMode.velY, lllIIlIIIllIIII.currentMode.velZ);
            }
            lllIIlIIIllIIII.currentMode.onPlayerMove();
        } else if (lllIIlIIIllIIII.currentMode.lastForwardPressed) {
            lllIIlIIIllIIII.mc.field_1690.field_1894.method_23481(false);
            lllIIlIIIllIIII.currentMode.lastForwardPressed = false;
        }
        if (lllIIlIIIllIIII.noCrash.get().booleanValue() && lllIIlIIIllIIII.mc.field_1724.method_6128()) {
            class_243 lllIIlIIIllIIll = lllIIlIIIllIIII.mc.field_1724.method_19538().method_1019(lllIIlIIIllIIII.mc.field_1724.method_18798().method_1029().method_1021((double)lllIIlIIIllIIII.crashLookAhead.get().intValue()));
            class_3959 lllIIlIIIllIIlI = new class_3959(lllIIlIIIllIIII.mc.field_1724.method_19538(), new class_243(lllIIlIIIllIIll.method_10216(), lllIIlIIIllIIII.mc.field_1724.method_23318(), lllIIlIIIllIIll.method_10215()), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)lllIIlIIIllIIII.mc.field_1724);
            class_3965 lllIIlIIIllIIIl = lllIIlIIIllIIII.mc.field_1687.method_17742(lllIIlIIIllIIlI);
            if (lllIIlIIIllIIIl != null && lllIIlIIIllIIIl.method_17783() == class_239.class_240.field_1332) {
                ((IVec3d)lllIIlIIIlIllIl.movement).set(0.0, lllIIlIIIllIIII.currentMode.velY, 0.0);
            }
        }
    }

    protected void enableGroundListener() {
        ElytraFly lllIIlIIIIllIII;
        MeteorClient.EVENT_BUS.subscribe(lllIIlIIIIllIII.staticGroundListener);
    }

    private void onModeChanged(ElytraFlightModes lllIIlIIIIlllII) {
        switch (lllIIlIIIIlllII) {
            case Vanilla: {
                lllIIlIIIIllIll.currentMode = new Vanilla();
                break;
            }
            case Packet: {
                lllIIlIIIIllIll.currentMode = new Packet();
            }
        }
    }

    protected void disableGroundListener() {
        ElytraFly lllIIlIIIIlIlII;
        MeteorClient.EVENT_BUS.unsubscribe(lllIIlIIIIlIlII.staticGroundListener);
    }

    public ElytraFly() {
        super(Categories.Movement, "elytra-fly", "Gives you more control over your elytra.");
        ElytraFly lllIIlIIlIIIIlI;
        lllIIlIIlIIIIlI.sgGeneral = lllIIlIIlIIIIlI.settings.getDefaultGroup();
        lllIIlIIlIIIIlI.sgAutopilot = lllIIlIIlIIIIlI.settings.createGroup("Autopilot");
        lllIIlIIlIIIIlI.flightMode = lllIIlIIlIIIIlI.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The mode of flying.").defaultValue(ElytraFlightModes.Vanilla).onModuleActivated(lllIIlIIIIIIlIl -> {
            ElytraFly lllIIlIIIIIlIII;
            lllIIlIIIIIlIII.onModeChanged((ElytraFlightModes)((Object)((Object)lllIIlIIIIIIlIl.get())));
        }).onChanged(lllIIlIIlIIIIlI::onModeChanged).build());
        lllIIlIIlIIIIlI.autoTakeOff = lllIIlIIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("auto-take-off").description("Automatically takes off when you hold jump without needing to double jump.").defaultValue(false).build());
        lllIIlIIlIIIIlI.replace = lllIIlIIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("elytra-replace").description("Replaces broken elytra with a new elytra.").defaultValue(false).build());
        lllIIlIIlIIIIlI.replaceDurability = lllIIlIIlIIIIlI.sgGeneral.add(new IntSetting.Builder().name("replace-durability").description("The durability threshold your elytra will be replaced at.").defaultValue(2).min(1).max(class_1802.field_8833.method_7841() - 1).sliderMax(20).build());
        lllIIlIIlIIIIlI.fallMultiplier = lllIIlIIlIIIIlI.sgGeneral.add(new DoubleSetting.Builder().name("fall-multiplier").description("Controls how fast will you go down naturally.").defaultValue(0.01).min(0.0).build());
        lllIIlIIlIIIIlI.horizontalSpeed = lllIIlIIlIIIIlI.sgGeneral.add(new DoubleSetting.Builder().name("horizontal-speed").description("How fast you go forward and backward.").defaultValue(1.0).min(0.0).build());
        lllIIlIIlIIIIlI.verticalSpeed = lllIIlIIlIIIIlI.sgGeneral.add(new DoubleSetting.Builder().name("vertical-speed").description("How fast you go up and down.").defaultValue(1.0).min(0.0).build());
        lllIIlIIlIIIIlI.stopInWater = lllIIlIIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("stop-in-water").description("Stops flying in water.").defaultValue(true).build());
        lllIIlIIlIIIIlI.dontGoIntoUnloadedChunks = lllIIlIIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("no-unloaded-chunks").description("Stops you from going into unloaded chunks.").defaultValue(true).build());
        lllIIlIIlIIIIlI.noCrash = lllIIlIIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("no-crash").description("Stops you from going into walls.").defaultValue(true).build());
        lllIIlIIlIIIIlI.crashLookAhead = lllIIlIIlIIIIlI.sgGeneral.add(new IntSetting.Builder().name("crash-look-ahead").description("Distance to look ahead when flying.").defaultValue(5).min(1).max(15).sliderMin(1).sliderMax(10).visible(lllIIlIIlIIIIlI.noCrash::get).build());
        lllIIlIIlIIIIlI.chestSwap = lllIIlIIlIIIIlI.sgGeneral.add(new EnumSetting.Builder().name("chest-swap").description("Enables ChestSwap when toggling this module.").defaultValue(ChestSwapMode.Never).build());
        lllIIlIIlIIIIlI.instaDrop = lllIIlIIlIIIIlI.sgGeneral.add(new BoolSetting.Builder().name("insta-drop").description("Makes you drop out of flight instantly.").defaultValue(false).build());
        lllIIlIIlIIIIlI.enableAutopilot = lllIIlIIlIIIIlI.sgAutopilot.add(new BoolSetting.Builder().name("enable-autopilot").description("Use autopilot.").defaultValue(false).build());
        lllIIlIIlIIIIlI.useFireworks = lllIIlIIlIIIIlI.sgAutopilot.add(new BoolSetting.Builder().name("use-fireworks").description("Uses firework rockets every second of your choice.").defaultValue(false).build());
        lllIIlIIlIIIIlI.autoPilotFireworkDelay = lllIIlIIlIIIIlI.sgAutopilot.add(new DoubleSetting.Builder().name("firework-delay").description("The delay in seconds in between using fireworks if \"Use Fireworks\" is enabled.").min(1.0).defaultValue(10.0).sliderMax(20.0).visible(lllIIlIIlIIIIlI.useFireworks::get).build());
        lllIIlIIlIIIIlI.moveForward = lllIIlIIlIIIIlI.sgAutopilot.add(new BoolSetting.Builder().name("move-forward").description("Moves forward while elytra flying.").defaultValue(false).build());
        lllIIlIIlIIIIlI.autoPilotMinimumHeight = lllIIlIIlIIIIlI.sgAutopilot.add(new DoubleSetting.Builder().name("minimum-height").description("The minimum height for moving forward.").defaultValue(120.0).min(0.0).sliderMax(260.0).visible(lllIIlIIlIIIIlI.moveForward::get).build());
        lllIIlIIlIIIIlI.staticGroundListener = lllIIlIIlIIIIlI.new StaticGroundListener();
        lllIIlIIlIIIIlI.staticInstadropListener = lllIIlIIlIIIIlI.new StaticInstaDropListener();
    }

    @Override
    public String getInfoString() {
        ElytraFly lllIIlIIIIIlIll;
        return lllIIlIIIIIlIll.currentMode.getHudString();
    }

    protected void disableInstaDropListener() {
        ElytraFly lllIIlIIIIIlllI;
        MeteorClient.EVENT_BUS.unsubscribe(lllIIlIIIIIlllI.staticInstadropListener);
    }

    @Override
    public void onDeactivate() {
        ElytraFly lllIIlIIIlllIll;
        if (lllIIlIIIlllIll.moveForward.get().booleanValue()) {
            lllIIlIIIlllIll.mc.field_1690.field_1894.method_23481(false);
        }
        if (lllIIlIIIlllIll.chestSwap.get() == ChestSwapMode.Always && lllIIlIIIlllIll.mc.field_1724.method_6118(class_1304.field_6174).method_7909() == class_1802.field_8833) {
            Modules.get().get(ChestSwap.class).swap();
        } else if (lllIIlIIIlllIll.chestSwap.get() == ChestSwapMode.WaitForGround) {
            lllIIlIIIlllIll.enableGroundListener();
        }
        if (lllIIlIIIlllIll.mc.field_1724.method_6128() && lllIIlIIIlllIll.instaDrop.get().booleanValue()) {
            lllIIlIIIlllIll.enableInstaDropListener();
        }
        lllIIlIIIlllIll.currentMode.onDeactivate();
    }

    @EventHandler
    private void onPacketSend(PacketEvent.Send lllIIlIIIlIIIII) {
        ElytraFly lllIIlIIIlIIIll;
        lllIIlIIIlIIIll.currentMode.onPacketSend(lllIIlIIIlIIIII);
    }

    private class StaticInstaDropListener {
        private StaticInstaDropListener() {
            StaticInstaDropListener lIlIlIIlllIIllI;
        }

        @EventHandler
        private void onInstadropTick(TickEvent.Post lIlIlIIlllIIIIl) {
            StaticInstaDropListener lIlIlIIlllIIIII;
            if (((ElytraFly)lIlIlIIlllIIIII.ElytraFly.this).mc.field_1724 != null && ((ElytraFly)lIlIlIIlllIIIII.ElytraFly.this).mc.field_1724.method_6128()) {
                ((ElytraFly)lIlIlIIlllIIIII.ElytraFly.this).mc.field_1724.method_18800(0.0, 0.0, 0.0);
                ((ElytraFly)lIlIlIIlllIIIII.ElytraFly.this).mc.field_1724.field_3944.method_2883((class_2596)new class_2828(true));
            } else {
                lIlIlIIlllIIIII.ElytraFly.this.disableInstaDropListener();
            }
        }
    }

    public static enum ChestSwapMode {
        Always,
        Never,
        WaitForGround;


        private ChestSwapMode() {
            ChestSwapMode llllllllllllllllIlIllIIIIlIIllll;
        }
    }

    private class StaticGroundListener {
        @EventHandler
        private void chestSwapGroundListener(PlayerMoveEvent llllllllllllllllllIIIlIlIlllIIlI) {
            StaticGroundListener llllllllllllllllllIIIlIlIlllIIll;
            if (((ElytraFly)llllllllllllllllllIIIlIlIlllIIll.ElytraFly.this).mc.field_1724 != null && ((ElytraFly)llllllllllllllllllIIIlIlIlllIIll.ElytraFly.this).mc.field_1724.method_24828() && ((ElytraFly)llllllllllllllllllIIIlIlIlllIIll.ElytraFly.this).mc.field_1724.method_6118(class_1304.field_6174).method_7909() == class_1802.field_8833) {
                Modules.get().get(ChestSwap.class).swap();
                llllllllllllllllllIIIlIlIlllIIll.ElytraFly.this.disableGroundListener();
            }
        }

        private StaticGroundListener() {
            StaticGroundListener llllllllllllllllllIIIlIlIlllIllI;
        }
    }
}

