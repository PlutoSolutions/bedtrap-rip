/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 *  net.minecraft.class_2828
 */
package minegame159.meteorclient.systems.modules.movement;

import meteordevelopment.orbit.EventHandler;
import minegame159.meteorclient.events.packets.PacketEvent;
import minegame159.meteorclient.events.world.TickEvent;
import minegame159.meteorclient.mixin.PlayerMoveC2SPacketAccessor;
import minegame159.meteorclient.settings.BoolSetting;
import minegame159.meteorclient.settings.DoubleSetting;
import minegame159.meteorclient.settings.EnumSetting;
import minegame159.meteorclient.settings.IntSetting;
import minegame159.meteorclient.settings.Setting;
import minegame159.meteorclient.settings.SettingGroup;
import minegame159.meteorclient.systems.modules.Categories;
import minegame159.meteorclient.systems.modules.Module;
import net.minecraft.class_243;
import net.minecraft.class_2828;

public class Flight
extends Module {
    private long lastModifiedTime;
    private final Setting<Mode> mode;
    private float lastYaw;
    private double lastY;
    private final Setting<Integer> offTime;
    private int offLeft;
    private final Setting<Boolean> verticalSpeedMatch;
    private final SettingGroup sgGeneral;
    private final Setting<Double> speed;
    private final Setting<Integer> delay;
    private int delayLeft;
    private final SettingGroup sgAntiKick;
    private boolean flip;
    private final Setting<AntiKickMode> antiKickMode;

    @EventHandler
    private void onPreTick(TickEvent.Pre pre) {
        float f = this.mc.field_1724.field_6031;
        if (this.mc.field_1724.field_6017 >= 3.0f && f == this.lastYaw && this.mc.field_1724.method_18798().method_1033() < 0.003) {
            this.mc.field_1724.field_6031 += this.flip ? 1.0f : -1.0f;
            this.flip = !this.flip;
        }
        this.lastYaw = f;
    }

    @EventHandler
    private void onPostTick(TickEvent.Post post) {
        if (this.antiKickMode.get() == AntiKickMode.Normal && this.delayLeft > 0) {
            --this.delayLeft;
        } else {
            if (this.antiKickMode.get() == AntiKickMode.Normal && this.delayLeft <= 0 && this.offLeft > 0) {
                --this.offLeft;
                if (this.mode.get() == Mode.Abilities) {
                    this.mc.field_1724.field_7503.field_7479 = false;
                    this.mc.field_1724.field_7503.method_7248(0.05f);
                    if (this.mc.field_1724.field_7503.field_7477) {
                        return;
                    }
                    this.mc.field_1724.field_7503.field_7478 = false;
                }
                return;
            }
            if (this.antiKickMode.get() == AntiKickMode.Normal && this.delayLeft <= 0 && this.offLeft <= 0) {
                this.delayLeft = this.delay.get();
                this.offLeft = this.offTime.get();
            }
        }
        if (this.mc.field_1724.field_6031 != this.lastYaw) {
            this.mc.field_1724.field_6031 = this.lastYaw;
        }
        switch (1.$SwitchMap$minegame159$meteorclient$systems$modules$movement$Flight$Mode[this.mode.get().ordinal()]) {
            case 1: {
                this.mc.field_1724.field_7503.field_7479 = false;
                this.mc.field_1724.field_6281 = this.speed.get().floatValue() * (this.mc.field_1724.method_5624() ? 15.0f : 10.0f);
                this.mc.field_1724.method_18800(0.0, 0.0, 0.0);
                class_243 class_2432 = this.mc.field_1724.method_18798();
                if (this.mc.field_1690.field_1903.method_1434()) {
                    this.mc.field_1724.method_18799(class_2432.method_1031(0.0, this.speed.get() * (double)(this.verticalSpeedMatch.get() != false ? 10.0f : 5.0f), 0.0));
                }
                if (!this.mc.field_1690.field_1832.method_1434()) break;
                this.mc.field_1724.method_18799(class_2432.method_1023(0.0, this.speed.get() * (double)(this.verticalSpeedMatch.get() != false ? 10.0f : 5.0f), 0.0));
                break;
            }
            case 2: {
                if (this.mc.field_1724.method_7325()) {
                    return;
                }
                this.mc.field_1724.field_7503.method_7248(this.speed.get().floatValue());
                this.mc.field_1724.field_7503.field_7479 = true;
                if (this.mc.field_1724.field_7503.field_7477) {
                    return;
                }
                this.mc.field_1724.field_7503.field_7478 = true;
            }
        }
    }

    private boolean lambda$new$0() {
        return this.antiKickMode.get() == AntiKickMode.Normal;
    }

    private boolean lambda$new$1() {
        return this.antiKickMode.get() == AntiKickMode.Normal;
    }

    @EventHandler
    private void onSendPacket(PacketEvent.Send send) {
        if (!(send.packet instanceof class_2828) || this.antiKickMode.get() != AntiKickMode.Packet) {
            return;
        }
        class_2828 class_28282 = (class_2828)send.packet;
        long l = System.currentTimeMillis();
        double d = class_28282.method_12268(Double.MAX_VALUE);
        if (d != Double.MAX_VALUE) {
            if (l - this.lastModifiedTime > 1000L && this.lastY != Double.MAX_VALUE && this.mc.field_1687.method_8320(this.mc.field_1724.method_24515().method_10074()).method_26215()) {
                ((PlayerMoveC2SPacketAccessor)class_28282).setY(this.lastY - 0.0313);
                this.lastModifiedTime = l;
            } else {
                this.lastY = d;
            }
        }
    }

    @Override
    public void onDeactivate() {
        if (this.mode.get() == Mode.Abilities && !this.mc.field_1724.method_7325()) {
            this.mc.field_1724.field_7503.field_7479 = false;
            this.mc.field_1724.field_7503.method_7248(0.05f);
            if (this.mc.field_1724.field_7503.field_7477) {
                return;
            }
            this.mc.field_1724.field_7503.field_7478 = false;
        }
    }

    @Override
    public void onActivate() {
        if (this.mode.get() == Mode.Abilities && !this.mc.field_1724.method_7325()) {
            this.mc.field_1724.field_7503.field_7479 = true;
            if (this.mc.field_1724.field_7503.field_7477) {
                return;
            }
            this.mc.field_1724.field_7503.field_7478 = true;
        }
    }

    public Flight() {
        super(Categories.Movement, "flight", "FLYYYY! No Fall is recommended with this module.");
        this.sgGeneral = this.settings.getDefaultGroup();
        this.sgAntiKick = this.settings.createGroup("Anti Kick");
        this.mode = this.sgGeneral.add(new EnumSetting.Builder().name("mode").description("The mode for Flight.").defaultValue(Mode.Abilities).build());
        this.speed = this.sgGeneral.add(new DoubleSetting.Builder().name("speed").description("Your speed when flying.").defaultValue(0.1).min(0.0).build());
        this.verticalSpeedMatch = this.sgGeneral.add(new BoolSetting.Builder().name("vertical-speed-match").description("Matches your vertical speed to your horizontal speed, otherwise uses vanilla ratio.").defaultValue(false).build());
        this.antiKickMode = this.sgAntiKick.add(new EnumSetting.Builder().name("mode").description("The mode for anti kick.").defaultValue(AntiKickMode.Packet).build());
        this.delay = this.sgAntiKick.add(new IntSetting.Builder().name("delay").description("The amount of delay, in ticks, between toggles in normal mode.").defaultValue(80).min(1).max(5000).sliderMax(200).visible(this::lambda$new$0).build());
        this.offTime = this.sgAntiKick.add(new IntSetting.Builder().name("off-time").description("The amount of delay, in ticks, that Flight is toggled off for in normal mode.").defaultValue(5).min(1).max(20).sliderMax(10).visible(this::lambda$new$1).build());
        this.delayLeft = this.delay.get();
        this.offLeft = this.offTime.get();
        this.lastModifiedTime = 0L;
        this.lastY = Double.MAX_VALUE;
    }

    public static enum AntiKickMode {
        Normal,
        Packet,
        None;

    }

    public static enum Mode {
        Abilities,
        Velocity;

    }
}

