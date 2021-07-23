/*
 * Decompiled with CFR 0.151.
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
import net.minecraft.class_310;
import net.minecraft.class_3959;
import net.minecraft.class_3965;

public class ElytraFly
extends Module {
    private final Setting<Boolean> enableAutopilot;
    public final Setting<ElytraFlightModes> flightMode;
    private ElytraFlightMode currentMode;
    public final Setting<Double> autoPilotFireworkDelay;
    private final StaticGroundListener staticGroundListener;
    private final SettingGroup sgAutopilot;
    public final Setting<Boolean> stopInWater;
    public final Setting<Boolean> dontGoIntoUnloadedChunks;
    public final Setting<Double> verticalSpeed;
    public final Setting<Integer> replaceDurability;
    public final Setting<ChestSwapMode> chestSwap;
    public final Setting<Boolean> autoTakeOff;
    public final Setting<Boolean> noCrash;
    public final Setting<Integer> crashLookAhead;
    public final Setting<Boolean> replace;
    public final Setting<Double> fallMultiplier;
    public final Setting<Boolean> moveForward;
    public final Setting<Double> autoPilotMinimumHeight;
    private final Setting<Boolean> instaDrop;
    private final StaticInstaDropListener staticInstadropListener;
    public final Setting<Boolean> useFireworks;
    private final SettingGroup sgGeneral;
    public final Setting<Double> horizontalSpeed;

    static class_310 access$000(ElytraFly elytraFly) {
        return elytraFly.mc;
    }

    @EventHandler
    private void onTick(TickEvent.Post post) {
        this.currentMode.onTick();
    }

    @Override
    public void onActivate() {
        this.currentMode.onActivate();
        if ((this.chestSwap.get() == ChestSwapMode.Always || this.chestSwap.get() == ChestSwapMode.WaitForGround) && this.mc.field_1724.method_6118(class_1304.field_6174).method_7909() != class_1802.field_8833) {
            Modules.get().get(ChestSwap.class).swap();
        }
    }

    protected void enableInstaDropListener() {
        MeteorClient.EVENT_BUS.subscribe(this.staticInstadropListener);
    }

    static class_310 access$200(ElytraFly elytraFly) {
        return elytraFly.mc;
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        if (!(this.mc.field_1724.method_6118(class_1304.field_6174).method_7909() instanceof class_1770)) {
            return;
        }
        this.currentMode.autoTakeoff();
        if (this.mc.field_1724.method_6128()) {
            this.currentMode.velX = 0.0;
            this.currentMode.velY = playerMoveEvent.movement.field_1351;
            this.currentMode.velZ = 0.0;
            this.currentMode.forward = class_243.method_1030((float)0.0f, (float)this.mc.field_1724.field_6031).method_1021(0.1);
            this.currentMode.right = class_243.method_1030((float)0.0f, (float)(this.mc.field_1724.field_6031 + 90.0f)).method_1021(0.1);
            if (this.mc.field_1724.method_5799() && this.stopInWater.get().booleanValue()) {
                this.mc.method_1562().method_2883((class_2596)new class_2848((class_1297)this.mc.field_1724, class_2848.class_2849.field_12982));
                return;
            }
            this.currentMode.handleFallMultiplier();
            if (this.enableAutopilot.get().booleanValue()) {
                this.currentMode.handleAutopilot();
            }
            this.currentMode.handleHorizontalSpeed();
            this.currentMode.handleVerticalSpeed();
            int n = (int)((this.mc.field_1724.method_23317() + this.currentMode.velX) / 16.0);
            int n2 = (int)((this.mc.field_1724.method_23321() + this.currentMode.velZ) / 16.0);
            if (this.dontGoIntoUnloadedChunks.get().booleanValue()) {
                if (this.mc.field_1687.method_2935().method_12123(n, n2)) {
                    ((IVec3d)playerMoveEvent.movement).set(this.currentMode.velX, this.currentMode.velY, this.currentMode.velZ);
                } else {
                    ((IVec3d)playerMoveEvent.movement).set(0.0, this.currentMode.velY, 0.0);
                }
            } else {
                ((IVec3d)playerMoveEvent.movement).set(this.currentMode.velX, this.currentMode.velY, this.currentMode.velZ);
            }
            this.currentMode.onPlayerMove();
        } else if (this.currentMode.lastForwardPressed) {
            this.mc.field_1690.field_1894.method_23481(false);
            this.currentMode.lastForwardPressed = false;
        }
        if (this.noCrash.get().booleanValue() && this.mc.field_1724.method_6128()) {
            class_243 class_2432 = this.mc.field_1724.method_19538().method_1019(this.mc.field_1724.method_18798().method_1029().method_1021((double)this.crashLookAhead.get().intValue()));
            class_3959 class_39592 = new class_3959(this.mc.field_1724.method_19538(), new class_243(class_2432.method_10216(), this.mc.field_1724.method_23318(), class_2432.method_10215()), class_3959.class_3960.field_17558, class_3959.class_242.field_1348, (class_1297)this.mc.field_1724);
            class_3965 class_39652 = this.mc.field_1687.method_17742(class_39592);
            if (class_39652 != null && class_39652.method_17783() == class_239.class_240.field_1332) {
                ((IVec3d)playerMoveEvent.movement).set(0.0, this.currentMode.velY, 0.0);
            }
        }
    }

    protected void enableGroundListener() {
        MeteorClient.EVENT_BUS.subscribe(this.staticGroundListener);
    }

    static class_310 access$400(ElytraFly elytraFly) {
        return elytraFly.mc;
    }

    private void onModeChanged(ElytraFlightModes elytraFlightModes) {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$movement$elytrafly$ElytraFlightModes[elytraFlightModes.ordinal()]) {
            case 1: {
                this.currentMode = new Vanilla();
                break;
            }
            case 2: {
                this.currentMode = new Packet();
            }
        }
    }

    static class_310 access$600(ElytraFly elytraFly) {
        return elytraFly.mc;
    }

    protected void disableGroundListener() {
        MeteorClient.EVENT_BUS.unsubscribe(this.staticGroundListener);
    }

    static class_310 access$500(ElytraFly elytraFly) {
        return elytraFly.mc;
    }

    static class_310 access$700(ElytraFly elytraFly) {
        return elytraFly.mc;
    }

    static class_310 access$100(ElytraFly elytraFly) {
        return elytraFly.mc;
    }

    public ElytraFly() {
        super(Categories.Movement, "elytra-fly", "Gives you more control over your elytra.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgAutopilot = this.settings.createGroup("Autopilot");
        this.flightMode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The mode of flying.").defaultValue(ElytraFlightModes.Vanilla).onModuleActivated(this::lambda$new$0).onChanged(this::onModeChanged).build());
        this.autoTakeOff = this.sgGeneral.add(new BoolSetting.Builder().name("auto-take-off").description("Automatically takes off when you hold jump without needing to double jump.").defaultValue(false).build());
        this.replace = this.sgGeneral.add(new BoolSetting.Builder().name("elytra-replace").description("Replaces broken elytra with a new elytra.").defaultValue(false).build());
        this.replaceDurability = this.sgGeneral.add(new IntSetting.Builder().name("replace-durability").description("The durability threshold your elytra will be replaced at.").defaultValue(2).min(1).max(class_1802.field_8833.method_7841() - 1).sliderMax(20).build());
        this.fallMultiplier = this.sgGeneral.add(new DoubleSetting.Builder().name("fall-multiplier").description("Controls how fast will you go down naturally.").defaultValue(0.01).min(0.0).build());
        this.horizontalSpeed = this.sgGeneral.add(new DoubleSetting.Builder().name("horizontal-speed").description("How fast you go forward and backward.").defaultValue(1.0).min(0.0).build());
        this.verticalSpeed = this.sgGeneral.add(new DoubleSetting.Builder().name("vertical-speed").description("How fast you go up and down.").defaultValue(1.0).min(0.0).build());
        this.stopInWater = this.sgGeneral.add(new BoolSetting.Builder().name("stop-in-water").description("Stops flying in water.").defaultValue(true).build());
        this.dontGoIntoUnloadedChunks = this.sgGeneral.add(new BoolSetting.Builder().name("no-unloaded-chunks").description("Stops you from going into unloaded chunks.").defaultValue(true).build());
        this.noCrash = this.sgGeneral.add(new BoolSetting.Builder().name("no-crash").description("Stops you from going into walls.").defaultValue(true).build());
        this.crashLookAhead = this.sgGeneral.add(new IntSetting.Builder().name("crash-look-ahead").description("Distance to look ahead when flying.").defaultValue(5).min(1).max(15).sliderMin(1).sliderMax(10).visible(this.noCrash::get).build());
        this.chestSwap = this.sgGeneral.add(new EnumSetting.Builder().name("chest-swap").description("Enables ChestSwap when toggling this module.").defaultValue(ChestSwapMode.Never).build());
        this.instaDrop = this.sgGeneral.add(new BoolSetting.Builder().name("insta-drop").description("Makes you drop out of flight instantly.").defaultValue(false).build());
        this.enableAutopilot = this.sgAutopilot.add(new BoolSetting.Builder().name("enable-autopilot").description("Use autopilot.").defaultValue(false).build());
        this.useFireworks = this.sgAutopilot.add(new BoolSetting.Builder().name("use-fireworks").description("Uses firework rockets every second of your choice.").defaultValue(false).build());
        this.autoPilotFireworkDelay = this.sgAutopilot.add(new DoubleSetting.Builder().name("firework-delay").description("The delay in seconds in between using fireworks if \"Use Fireworks\" is enabled.").min(1.0).defaultValue(10.0).sliderMax(20.0).visible(this.useFireworks::get).build());
        this.moveForward = this.sgAutopilot.add(new BoolSetting.Builder().name("move-forward").description("Moves forward while elytra flying.").defaultValue(false).build());
        this.autoPilotMinimumHeight = this.sgAutopilot.add(new DoubleSetting.Builder().name("minimum-height").description("The minimum height for moving forward.").defaultValue(120.0).min(0.0).sliderMax(260.0).visible(this.moveForward::get).build());
        this.staticGroundListener = new StaticGroundListener(this, null);
        this.staticInstadropListener = new StaticInstaDropListener(this, null);
    }

    @Override
    public String getInfoString() {
        return this.currentMode.getHudString();
    }

    protected void disableInstaDropListener() {
        MeteorClient.EVENT_BUS.unsubscribe(this.staticInstadropListener);
    }

    @Override
    public void onDeactivate() {
        if (this.moveForward.get().booleanValue()) {
            this.mc.field_1690.field_1894.method_23481(false);
        }
        if (this.chestSwap.get() == ChestSwapMode.Always && this.mc.field_1724.method_6118(class_1304.field_6174).method_7909() == class_1802.field_8833) {
            Modules.get().get(ChestSwap.class).swap();
        } else if (this.chestSwap.get() == ChestSwapMode.WaitForGround) {
            this.enableGroundListener();
        }
        if (this.mc.field_1724.method_6128() && this.instaDrop.get().booleanValue()) {
            this.enableInstaDropListener();
        }
        this.currentMode.onDeactivate();
    }

    @EventHandler
    private void onPacketSend(PacketEvent.Send send) {
        this.currentMode.onPacketSend(send);
    }

    private void lambda$new$0(Setting setting) {
        this.onModeChanged((ElytraFlightModes)((Object)setting.get()));
    }

    private class StaticInstaDropListener {
        final ElytraFly this$0;

        StaticInstaDropListener(ElytraFly elytraFly, 1 var2_2) {
            this(elytraFly);
        }

        private StaticInstaDropListener(ElytraFly elytraFly) {
            this.this$0 = elytraFly;
        }

        @EventHandler
        private void onInstadropTick(TickEvent.Post post) {
            if (ElytraFly.access$400((ElytraFly)this.this$0).field_1724 != null && ElytraFly.access$500((ElytraFly)this.this$0).field_1724.method_6128()) {
                ElytraFly.access$600((ElytraFly)this.this$0).field_1724.method_18800(0.0, 0.0, 0.0);
                ElytraFly.access$700((ElytraFly)this.this$0).field_1724.field_3944.method_2883((class_2596)new class_2828(true));
            } else {
                this.this$0.disableInstaDropListener();
            }
        }
    }

    public static enum ChestSwapMode {
        Always,
        Never,
        WaitForGround;

    }

    private class StaticGroundListener {
        final ElytraFly this$0;

        @EventHandler
        private void chestSwapGroundListener(PlayerMoveEvent playerMoveEvent) {
            if (ElytraFly.access$000((ElytraFly)this.this$0).field_1724 != null && ElytraFly.access$100((ElytraFly)this.this$0).field_1724.method_24828() && ElytraFly.access$200((ElytraFly)this.this$0).field_1724.method_6118(class_1304.field_6174).method_7909() == class_1802.field_8833) {
                Modules.get().get(ChestSwap.class).swap();
                this.this$0.disableGroundListener();
            }
        }

        StaticGroundListener(ElytraFly elytraFly, 1 var2_2) {
            this(elytraFly);
        }

        private StaticGroundListener(ElytraFly elytraFly) {
            this.this$0 = elytraFly;
        }
    }
}

