/*
 * Decompiled with CFR 0.151.
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
    public final Setting<Boolean> vanillaOnGround;
    public final Setting<Double> timer;
    public final Setting<Double> ncpSpeed;
    public final Setting<Boolean> inLiquids;
    public final Setting<Double> vanillaSpeed;
    private SpeedMode currentMode;
    private final SettingGroup sgGeneral;
    public final Setting<Boolean> whenSneaking;
    public final Setting<SpeedModes> speedMode;
    public final Setting<Boolean> ncpSpeedLimit;

    @Override
    public String getInfoString() {
        return this.currentMode.getHudString();
    }

    @EventHandler
    private void onPreTick(TickEvent.Pre pre) {
        if (this.mc.field_1724.method_6128() || this.mc.field_1724.method_6101() || this.mc.field_1724.method_5854() != null) {
            return;
        }
        if (!this.whenSneaking.get().booleanValue() && this.mc.field_1724.method_5715()) {
            return;
        }
        if (this.vanillaOnGround.get().booleanValue() && !this.mc.field_1724.method_24828() && this.speedMode.get() == SpeedModes.Vanilla) {
            return;
        }
        if (!this.inLiquids.get().booleanValue() && (this.mc.field_1724.method_5799() || this.mc.field_1724.method_5771())) {
            return;
        }
        this.currentMode.onTick();
    }

    private boolean lambda$new$4() {
        return this.speedMode.get() == SpeedModes.Vanilla;
    }

    private void lambda$new$0(Setting setting) {
        this.onSpeedModeChanged((SpeedModes)((Object)setting.get()));
    }

    @Override
    public void onActivate() {
        this.currentMode.onActivate();
    }

    private boolean lambda$new$3() {
        return this.speedMode.get() == SpeedModes.Strafe;
    }

    private boolean lambda$new$2() {
        return this.speedMode.get() == SpeedModes.Strafe;
    }

    private void onSpeedModeChanged(SpeedModes speedModes) {
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$movement$speed$SpeedModes[speedModes.ordinal()]) {
            case 1: {
                this.currentMode = new Vanilla();
                break;
            }
            case 2: {
                this.currentMode = new Strafe();
            }
        }
    }

    public Speed() {
        super(Categories.Movement, "speed", "Modifies your movement speed when moving on the ground.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.speedMode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The method of applying speed.").defaultValue(SpeedModes.Vanilla).onModuleActivated(this::lambda$new$0).onChanged(this::onSpeedModeChanged).build());
        this.vanillaSpeed = this.sgGeneral.add(new DoubleSetting.Builder().name("vanilla-speed").description("The speed in blocks per second.").defaultValue(5.6).min(0.0).sliderMax(20.0).visible(this::lambda$new$1).build());
        this.ncpSpeed = this.sgGeneral.add(new DoubleSetting.Builder().name("strafe-speed").description("The speed.").visible(this::lambda$new$2).defaultValue(1.6).min(0.0).sliderMax(3.0).build());
        this.ncpSpeedLimit = this.sgGeneral.add(new BoolSetting.Builder().name("speed-limit").description("Limits your speed on servers with very strict anticheats.").visible(this::lambda$new$3).defaultValue(false).build());
        this.timer = this.sgGeneral.add(new DoubleSetting.Builder().name("timer").description("Timer override.").defaultValue(1.0).min(0.01).sliderMin(0.01).sliderMax(10.0).build());
        this.inLiquids = this.sgGeneral.add(new BoolSetting.Builder().name("in-liquids").description("Uses speed when in lava or water.").defaultValue(false).build());
        this.whenSneaking = this.sgGeneral.add(new BoolSetting.Builder().name("when-sneaking").description("Uses speed when sneaking.").defaultValue(false).build());
        this.vanillaOnGround = this.sgGeneral.add(new BoolSetting.Builder().name("only-on-ground").description("Uses speed only when standing on a block.").visible(this::lambda$new$4).defaultValue(false).build());
        this.onSpeedModeChanged(this.speedMode.get());
    }

    private boolean lambda$new$1() {
        return this.speedMode.get() == SpeedModes.Vanilla;
    }

    @Override
    public void onDeactivate() {
        Modules.get().get(Timer.class).setOverride(1.0);
        this.currentMode.onDeactivate();
    }

    @EventHandler
    private void onPacketReceive(PacketEvent.Receive receive) {
        if (receive.packet instanceof class_2708) {
            this.currentMode.onRubberband();
        }
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        if (playerMoveEvent.type != class_1313.field_6308 || this.mc.field_1724.method_6128() || this.mc.field_1724.method_6101() || this.mc.field_1724.method_5854() != null) {
            return;
        }
        if (!this.whenSneaking.get().booleanValue() && this.mc.field_1724.method_5715()) {
            return;
        }
        if (this.vanillaOnGround.get().booleanValue() && !this.mc.field_1724.method_24828() && this.speedMode.get() == SpeedModes.Vanilla) {
            return;
        }
        if (!this.inLiquids.get().booleanValue() && (this.mc.field_1724.method_5799() || this.mc.field_1724.method_5771())) {
            return;
        }
        Modules.get().get(Timer.class).setOverride(PlayerUtils.isMoving() ? this.timer.get() : 1.0);
        this.currentMode.onMove(playerMoveEvent);
    }
}

