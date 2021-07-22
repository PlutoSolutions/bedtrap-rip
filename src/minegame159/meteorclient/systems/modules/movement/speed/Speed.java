/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1313
 *  net.minecraft.class_2708
 */
package minegame159.meteorclient.systems.modules.movement.speed;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.entity.player.PlayerMoveEvent;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import minegame159.meteorclient.systems.modules.Modules;
import minegame159.meteorclient.systems.modules.movement.speed.SpeedMode;
import minegame159.meteorclient.systems.modules.movement.speed.SpeedModes;
import minegame159.meteorclient.systems.modules.movement.speed.modes.Strafe;
import minegame159.meteorclient.systems.modules.movement.speed.modes.Vanilla;
import minegame159.meteorclient.systems.modules.world.Timer;
import minegame159.meteorclient.utils.player.PlayerUtils;
import net.minecraft.class_1313;
import net.minecraft.class_2708;

public class Speed
extends Module {
    public final /* synthetic */ Setting<Boolean> vanillaOnGround;
    public final /* synthetic */ Setting<Double> timer;
    public final /* synthetic */ Setting<Double> ncpSpeed;
    public final /* synthetic */ Setting<Boolean> inLiquids;
    public final /* synthetic */ Setting<Double> vanillaSpeed;
    private /* synthetic */ SpeedMode currentMode;
    private final /* synthetic */ SettingGroup sgGeneral;
    public final /* synthetic */ Setting<Boolean> whenSneaking;
    public final /* synthetic */ Setting<SpeedModes> speedMode;
    public final /* synthetic */ Setting<Boolean> ncpSpeedLimit;

    @Override
    public String getInfoString() {
        Speed llllllllllllllllllIIlIIIIlllIllI;
        return llllllllllllllllllIIlIIIIlllIllI.currentMode.getHudString();
    }

    @EventHandler
    private void onPreTick(TickEvent.Pre llllllllllllllllllIIlIIIlIIIIllI) {
        Speed llllllllllllllllllIIlIIIlIIIIlIl;
        if (llllllllllllllllllIIlIIIlIIIIlIl.mc.field_1724.method_6128() || llllllllllllllllllIIlIIIlIIIIlIl.mc.field_1724.method_6101() || llllllllllllllllllIIlIIIlIIIIlIl.mc.field_1724.method_5854() != null) {
            return;
        }
        if (!llllllllllllllllllIIlIIIlIIIIlIl.whenSneaking.get().booleanValue() && llllllllllllllllllIIlIIIlIIIIlIl.mc.field_1724.method_5715()) {
            return;
        }
        if (llllllllllllllllllIIlIIIlIIIIlIl.vanillaOnGround.get().booleanValue() && !llllllllllllllllllIIlIIIlIIIIlIl.mc.field_1724.method_24828() && llllllllllllllllllIIlIIIlIIIIlIl.speedMode.get() == SpeedModes.Vanilla) {
            return;
        }
        if (!llllllllllllllllllIIlIIIlIIIIlIl.inLiquids.get().booleanValue() && (llllllllllllllllllIIlIIIlIIIIlIl.mc.field_1724.method_5799() || llllllllllllllllllIIlIIIlIIIIlIl.mc.field_1724.method_5771())) {
            return;
        }
        llllllllllllllllllIIlIIIlIIIIlIl.currentMode.onTick();
    }

    @Override
    public void onActivate() {
        Speed llllllllllllllllllIIlIIIlIIlIIll;
        llllllllllllllllllIIlIIIlIIlIIll.currentMode.onActivate();
    }

    private void onSpeedModeChanged(SpeedModes llllllllllllllllllIIlIIIIllllIIl) {
        switch (llllllllllllllllllIIlIIIIllllIIl) {
            case Vanilla: {
                llllllllllllllllllIIlIIIIlllllII.currentMode = new Vanilla();
                break;
            }
            case Strafe: {
                llllllllllllllllllIIlIIIIlllllII.currentMode = new Strafe();
            }
        }
    }

    public Speed() {
        super(Categories.Movement, "speed", "Modifies your movement speed when moving on the ground.");
        Speed llllllllllllllllllIIlIIIlIIlIllI;
        llllllllllllllllllIIlIIIlIIlIllI.sgGeneral = llllllllllllllllllIIlIIIlIIlIllI.settings.getDefaultGroup();
        llllllllllllllllllIIlIIIlIIlIllI.speedMode = llllllllllllllllllIIlIIIlIIlIllI.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The method of applying speed.").defaultValue(SpeedModes.Vanilla).onModuleActivated(llllllllllllllllllIIlIIIIllIIlII -> {
            Speed llllllllllllllllllIIlIIIIllIIlIl;
            llllllllllllllllllIIlIIIIllIIlIl.onSpeedModeChanged((SpeedModes)((Object)((Object)llllllllllllllllllIIlIIIIllIIlII.get())));
        }).onChanged(llllllllllllllllllIIlIIIlIIlIllI::onSpeedModeChanged).build());
        llllllllllllllllllIIlIIIlIIlIllI.vanillaSpeed = llllllllllllllllllIIlIIIlIIlIllI.sgGeneral.add(new DoubleSetting.Builder().name("vanilla-speed").description("The speed in blocks per second.").defaultValue(5.6).min(0.0).sliderMax(20.0).visible(() -> {
            Speed llllllllllllllllllIIlIIIIllIlIlI;
            return llllllllllllllllllIIlIIIIllIlIlI.speedMode.get() == SpeedModes.Vanilla;
        }).build());
        llllllllllllllllllIIlIIIlIIlIllI.ncpSpeed = llllllllllllllllllIIlIIIlIIlIllI.sgGeneral.add(new DoubleSetting.Builder().name("strafe-speed").description("The speed.").visible(() -> {
            Speed llllllllllllllllllIIlIIIIllIlllI;
            return llllllllllllllllllIIlIIIIllIlllI.speedMode.get() == SpeedModes.Strafe;
        }).defaultValue(1.6).min(0.0).sliderMax(3.0).build());
        llllllllllllllllllIIlIIIlIIlIllI.ncpSpeedLimit = llllllllllllllllllIIlIIIlIIlIllI.sgGeneral.add(new BoolSetting.Builder().name("speed-limit").description("Limits your speed on servers with very strict anticheats.").visible(() -> {
            Speed llllllllllllllllllIIlIIIIlllIIIl;
            return llllllllllllllllllIIlIIIIlllIIIl.speedMode.get() == SpeedModes.Strafe;
        }).defaultValue(false).build());
        llllllllllllllllllIIlIIIlIIlIllI.timer = llllllllllllllllllIIlIIIlIIlIllI.sgGeneral.add(new DoubleSetting.Builder().name("timer").description("Timer override.").defaultValue(1.0).min(0.01).sliderMin(0.01).sliderMax(10.0).build());
        llllllllllllllllllIIlIIIlIIlIllI.inLiquids = llllllllllllllllllIIlIIIlIIlIllI.sgGeneral.add(new BoolSetting.Builder().name("in-liquids").description("Uses speed when in lava or water.").defaultValue(false).build());
        llllllllllllllllllIIlIIIlIIlIllI.whenSneaking = llllllllllllllllllIIlIIIlIIlIllI.sgGeneral.add(new BoolSetting.Builder().name("when-sneaking").description("Uses speed when sneaking.").defaultValue(false).build());
        llllllllllllllllllIIlIIIlIIlIllI.vanillaOnGround = llllllllllllllllllIIlIIIlIIlIllI.sgGeneral.add(new BoolSetting.Builder().name("only-on-ground").description("Uses speed only when standing on a block.").visible(() -> {
            Speed llllllllllllllllllIIlIIIIlllIIll;
            return llllllllllllllllllIIlIIIIlllIIll.speedMode.get() == SpeedModes.Vanilla;
        }).defaultValue(false).build());
        llllllllllllllllllIIlIIIlIIlIllI.onSpeedModeChanged(llllllllllllllllllIIlIIIlIIlIllI.speedMode.get());
    }

    @Override
    public void onDeactivate() {
        Speed llllllllllllllllllIIlIIIlIIIllll;
        Modules.get().get(Timer.class).setOverride(1.0);
        llllllllllllllllllIIlIIIlIIIllll.currentMode.onDeactivate();
    }

    @EventHandler
    private void onPacketReceive(PacketEvent.Receive llllllllllllllllllIIlIIIlIIIIIIl) {
        if (llllllllllllllllllIIlIIIlIIIIIIl.packet instanceof class_2708) {
            Speed llllllllllllllllllIIlIIIlIIIIIlI;
            llllllllllllllllllIIlIIIlIIIIIlI.currentMode.onRubberband();
        }
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent llllllllllllllllllIIlIIIlIIIlIIl) {
        Speed llllllllllllllllllIIlIIIlIIIlIlI;
        if (llllllllllllllllllIIlIIIlIIIlIIl.type != class_1313.field_6308 || llllllllllllllllllIIlIIIlIIIlIlI.mc.field_1724.method_6128() || llllllllllllllllllIIlIIIlIIIlIlI.mc.field_1724.method_6101() || llllllllllllllllllIIlIIIlIIIlIlI.mc.field_1724.method_5854() != null) {
            return;
        }
        if (!llllllllllllllllllIIlIIIlIIIlIlI.whenSneaking.get().booleanValue() && llllllllllllllllllIIlIIIlIIIlIlI.mc.field_1724.method_5715()) {
            return;
        }
        if (llllllllllllllllllIIlIIIlIIIlIlI.vanillaOnGround.get().booleanValue() && !llllllllllllllllllIIlIIIlIIIlIlI.mc.field_1724.method_24828() && llllllllllllllllllIIlIIIlIIIlIlI.speedMode.get() == SpeedModes.Vanilla) {
            return;
        }
        if (!llllllllllllllllllIIlIIIlIIIlIlI.inLiquids.get().booleanValue() && (llllllllllllllllllIIlIIIlIIIlIlI.mc.field_1724.method_5799() || llllllllllllllllllIIlIIIlIIIlIlI.mc.field_1724.method_5771())) {
            return;
        }
        Modules.get().get(Timer.class).setOverride(PlayerUtils.isMoving() ? llllllllllllllllllIIlIIIlIIIlIlI.timer.get() : 1.0);
        llllllllllllllllllIIlIIIlIIIlIlI.currentMode.onMove(llllllllllllllllllIIlIIIlIIIlIIl);
    }
}

